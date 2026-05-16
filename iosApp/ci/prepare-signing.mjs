import crypto from "node:crypto";
import fs from "node:fs";
import https from "node:https";
import os from "node:os";
import path from "node:path";
import { spawnSync } from "node:child_process";

const requiredEnv = [
  "APP_STORE_CONNECT_KEY_ID",
  "APP_STORE_CONNECT_ISSUER_ID",
  "APPLE_TEAM_ID",
  "IOS_BUNDLE_ID",
  "ASC_KEY_PATH",
  "RUNNER_TEMP",
  "GITHUB_ENV",
  "GITHUB_RUN_ID",
];

for (const name of requiredEnv) {
  if (!process.env[name]) {
    throw new Error(`Missing required environment variable: ${name}`);
  }
}

const keyId = process.env.APP_STORE_CONNECT_KEY_ID;
const issuerId = process.env.APP_STORE_CONNECT_ISSUER_ID;
const teamId = process.env.APPLE_TEAM_ID;
const bundleIdentifier = process.env.IOS_BUNDLE_ID;
const ascKeyPath = process.env.ASC_KEY_PATH;
const runnerTemp = process.env.RUNNER_TEMP;
const githubEnv = process.env.GITHUB_ENV;
const runId = process.env.GITHUB_RUN_ID;
const apiBase = "https://api.appstoreconnect.apple.com/v1";

function base64Url(input) {
  return Buffer.from(input)
    .toString("base64")
    .replace(/=/g, "")
    .replace(/\+/g, "-")
    .replace(/\//g, "_");
}

function createJwt() {
  const now = Math.floor(Date.now() / 1000);
  const header = base64Url(JSON.stringify({ alg: "ES256", kid: keyId, typ: "JWT" }));
  const payload = base64Url(JSON.stringify({
    iss: issuerId,
    iat: now,
    exp: now + 1200,
    aud: "appstoreconnect-v1",
  }));
  const unsignedToken = `${header}.${payload}`;
  const signature = crypto.sign("sha256", Buffer.from(unsignedToken), {
    key: fs.readFileSync(ascKeyPath, "utf8"),
    dsaEncoding: "ieee-p1363",
  });
  return `${unsignedToken}.${base64Url(signature)}`;
}

function apiRequest(method, endpoint, body = undefined) {
  const url = new URL(`${apiBase}${endpoint}`);
  const payload = body ? JSON.stringify(body) : undefined;
  const token = createJwt();

  return new Promise((resolve, reject) => {
    const request = https.request(url, {
      method,
      headers: {
        Authorization: `Bearer ${token}`,
        Accept: "application/json",
        ...(payload ? { "Content-Type": "application/json", "Content-Length": Buffer.byteLength(payload) } : {}),
      },
    }, (response) => {
      let data = "";
      response.setEncoding("utf8");
      response.on("data", (chunk) => { data += chunk; });
      response.on("end", () => {
        const parsed = data ? JSON.parse(data) : {};
        if (response.statusCode >= 200 && response.statusCode < 300) {
          resolve(parsed);
          return;
        }
        reject(new Error(`${method} ${endpoint} failed with ${response.statusCode}: ${data}`));
      });
    });

    request.on("error", reject);
    if (payload) request.write(payload);
    request.end();
  });
}

function run(command, args) {
  const result = spawnSync(command, args, { stdio: "inherit" });
  if (result.status !== 0) {
    throw new Error(`${command} ${args.join(" ")} failed with exit code ${result.status}`);
  }
}

async function findOrCreateBundleId() {
  const query = `?filter%5Bidentifier%5D=${encodeURIComponent(bundleIdentifier)}&filter%5Bplatform%5D=IOS`;
  const existing = await apiRequest("GET", `/bundleIds${query}`);
  if (existing.data?.length) {
    return existing.data[0];
  }

  return (await apiRequest("POST", "/bundleIds", {
    data: {
      type: "bundleIds",
      attributes: {
        identifier: bundleIdentifier,
        name: "ARVIO iOS",
        platform: "IOS",
      },
    },
  })).data;
}

async function main() {
  const signingDir = path.join(runnerTemp, "ios-signing");
  fs.mkdirSync(signingDir, { recursive: true });

  const privateKeyPath = path.join(signingDir, "ios_distribution.key");
  const csrPath = path.join(signingDir, "ios_distribution.csr");
  const certificatePath = path.join(signingDir, "ios_distribution.cer");
  const certificatePemPath = path.join(signingDir, "ios_distribution.pem");
  const p12Path = path.join(signingDir, "ios_distribution.p12");
  const profilePath = path.join(signingDir, "ARVIO_App_Store.mobileprovision");
  const p12Password = crypto.randomBytes(18).toString("base64url");
  const profileName = `ARVIO CI App Store ${runId}`;

  run("openssl", ["genrsa", "-out", privateKeyPath, "2048"]);
  run("openssl", [
    "req",
    "-new",
    "-key",
    privateKeyPath,
    "-out",
    csrPath,
    "-subj",
    `/CN=ARVIO CI/OU=${teamId}/O=ARVIO/C=US`,
  ]);

  const certificate = (await apiRequest("POST", "/certificates", {
    data: {
      type: "certificates",
      attributes: {
        certificateType: "IOS_DISTRIBUTION",
        csrContent: fs.readFileSync(csrPath, "utf8"),
      },
    },
  })).data;

  fs.writeFileSync(certificatePath, Buffer.from(certificate.attributes.certificateContent, "base64"));
  run("openssl", ["x509", "-inform", "DER", "-in", certificatePath, "-out", certificatePemPath]);
  run("openssl", [
    "pkcs12",
    "-export",
    "-inkey",
    privateKeyPath,
    "-in",
    certificatePemPath,
    "-out",
    p12Path,
    "-password",
    `pass:${p12Password}`,
  ]);

  const bundleId = await findOrCreateBundleId();
  const profile = (await apiRequest("POST", "/profiles", {
    data: {
      type: "profiles",
      attributes: {
        name: profileName,
        profileType: "IOS_APP_STORE",
      },
      relationships: {
        bundleId: {
          data: { type: "bundleIds", id: bundleId.id },
        },
        certificates: {
          data: [{ type: "certificates", id: certificate.id }],
        },
      },
    },
  })).data;

  fs.writeFileSync(profilePath, Buffer.from(profile.attributes.profileContent, "base64"));
  fs.appendFileSync(githubEnv, [
    `IOS_CERT_P12_PATH=${p12Path}`,
    `IOS_CERT_PASSWORD=${p12Password}`,
    `IOS_PROFILE_PATH=${profilePath}`,
    `IOS_PROFILE_UUID=${profile.attributes.uuid}`,
    `IOS_PROFILE_NAME=${profileName}`,
    `IOS_BUNDLE_ID=${bundleIdentifier}`,
    `IOS_CREATED_CERTIFICATE_ID=${certificate.id}`,
    `IOS_CREATED_PROFILE_ID=${profile.id}`,
    "",
  ].join(os.EOL));

  console.log(`Prepared App Store signing profile ${profile.attributes.uuid} for ${bundleIdentifier}.`);
}

main().catch((error) => {
  console.error(error.message);
  process.exit(1);
});
