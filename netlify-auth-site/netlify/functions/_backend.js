const { Pool } = require("pg");
const crypto = require("crypto");
const { connectLambda, getStore } = require("@netlify/blobs");

const JSON_HEADERS = {
  "content-type": "application/json; charset=utf-8",
  "access-control-allow-origin": "*",
  "access-control-allow-methods": "GET,POST,DELETE,OPTIONS",
  "access-control-allow-headers": "authorization,apikey,content-type,x-arvio-user-id,x-arvio-email,x-client-info,x-user-token"
};

let pool;

function getPool() {
  if (pool) return pool;
  const connectionString =
    process.env.NETLIFY_DB_URL ||
    process.env.NETLIFY_DATABASE_URL ||
    process.env.DATABASE_URL;
  if (!connectionString) {
    throw new Error("NETLIFY_DB_URL is not configured");
  }
  pool = new Pool({
    connectionString,
    max: Number(process.env.DB_POOL_MAX || 4),
    idleTimeoutMillis: 10_000,
    connectionTimeoutMillis: 8_000
  });
  return pool;
}

function json(statusCode, body) {
  return {
    statusCode,
    headers: JSON_HEADERS,
    body: JSON.stringify(body)
  };
}

function options(event) {
  return event.httpMethod === "OPTIONS" ? json(204, {}) : null;
}

function normalizeEmail(email) {
  return String(email || "").trim().toLowerCase();
}

function sha256(value) {
  return crypto.createHash("sha256").update(String(value || "")).digest("hex");
}

function parseBody(event) {
  if (!event.body) return {};
  const raw = event.isBase64Encoded
    ? Buffer.from(event.body, "base64").toString("utf8")
    : event.body;
  return JSON.parse(String(raw || "").replace(/^\uFEFF/, ""));
}

function appAnonKey() {
  return process.env.APP_ANON_KEY || "";
}

function assertAppRequest(event) {
  const expected = appAnonKey();
  if (!expected) {
    throw new Error("APP_ANON_KEY is not configured");
  }
  const apiKey = String(event.headers.apikey || event.headers.Apikey || "").trim();
  const auth = event.headers.authorization || event.headers.Authorization || "";
  const bearer = auth.match(/^Bearer\s+(.+)$/i)?.[1]?.trim() || "";
  if (apiKey === expected || bearer === expected) return;
  const error = new Error("Unauthorized");
  error.statusCode = 401;
  throw error;
}

function errorMessage(error) {
  return error instanceof Error ? error.message : String(error);
}

function publicError(error, fallback = "Unexpected error") {
  if (!error) return fallback;
  if (typeof error === "string") return error;
  return error.message || fallback;
}

function parseAuthError(raw) {
  try {
    const data = JSON.parse(raw);
    return String(data.error_description || data.msg || data.message || data.error || raw);
  } catch {
    return raw || "Auth request failed";
  }
}

const EMAIL_RE = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,63}$/i;
const BLOCKED_EMAIL_DOMAINS = new Set([
  "10minutemail.com",
  "20minutemail.com",
  "dispostable.com",
  "emailondeck.com",
  "example.com",
  "example.net",
  "example.org",
  "fakeinbox.com",
  "getnada.com",
  "grr.la",
  "guerrillamail.biz",
  "guerrillamail.com",
  "guerrillamail.de",
  "guerrillamail.info",
  "guerrillamail.net",
  "guerrillamail.org",
  "invalid",
  "localhost",
  "maildrop.cc",
  "mailinator.com",
  "moakt.com",
  "sharklasers.com",
  "temp-mail.org",
  "tempmail.com",
  "tempmailo.com",
  "trashmail.com",
  "yopmail.com"
]);
const BLOCKED_EMAIL_DOMAIN_FRAGMENTS = [
  "10minutemail",
  "disposable",
  "fakeinbox",
  "guerrillamail",
  "maildrop",
  "mailinator",
  "tempmail",
  "temp-mail",
  "trashmail",
  "yopmail"
];
const BLOCKED_SIGNUP_LOCAL_PARTS = new Set([
  "asdf",
  "example",
  "fake",
  "invalid",
  "no-reply",
  "none",
  "noreply",
  "null",
  "qwerty",
  "test"
]);

function validateEmail(email, rejectDisposable = true) {
  const normalized = normalizeEmail(email);
  if (!normalized) return "Email is required";
  if (normalized.length > 254 || !EMAIL_RE.test(normalized)) return "Enter a valid email address";
  if ((normalized.match(/@/g) || []).length !== 1) return "Enter a valid email address";

  const [localPart, domain = ""] = normalized.split("@");
  if (!localPart || !domain) return "Use a real email address";
  if (localPart.length > 64 || localPart.startsWith(".") || localPart.endsWith(".") || localPart.includes("..")) {
    return "Enter a valid email address";
  }
  const labels = domain.split(".");
  if (labels.length < 2 || labels.some((part) => !part || part.length > 63)) return "Enter a valid email address";
  if (labels.some((part) => part.startsWith("-") || part.endsWith("-"))) return "Enter a valid email address";
  if (/^\d+$/.test(labels[labels.length - 1])) return "Enter a valid email address";

  const blockedDomain = BLOCKED_EMAIL_DOMAINS.has(domain) ||
    BLOCKED_EMAIL_DOMAIN_FRAGMENTS.some((fragment) => domain.includes(fragment));
  if (rejectDisposable && BLOCKED_SIGNUP_LOCAL_PARTS.has(localPart)) return "Use a real email address";
  if (rejectDisposable && blockedDomain) return "Use a real email address";
  if (
    rejectDisposable &&
    (
      domain.endsWith(".example") ||
      domain.endsWith(".invalid") ||
      domain.endsWith(".localhost") ||
      domain.endsWith(".local") ||
      domain.endsWith(".test")
    )
  ) {
    return "Use a real email address";
  }
  return "";
}

const AUTH_ISSUER = "arvio-netlify";
const ACCESS_TOKEN_TTL_SECONDS = 7 * 24 * 60 * 60;
const REFRESH_TOKEN_TTL_MS = 90 * 24 * 60 * 60 * 1000;
const PASSWORD_SETUP_TTL_MS = 60 * 60 * 1000;

function authSecret() {
  const secret = process.env.ARVIO_AUTH_SECRET || "";
  if (!secret || secret.length < 32) {
    const error = new Error("ARVIO_AUTH_SECRET is not configured");
    error.statusCode = 503;
    throw error;
  }
  return secret;
}

function base64urlJson(value) {
  return Buffer.from(JSON.stringify(value)).toString("base64url");
}

function randomToken(bytes = 32) {
  return crypto.randomBytes(bytes).toString("base64url");
}

function authStores(event) {
  connectLambda(event);
  return getStore("arvio-auth");
}

function accountKeyForEmail(email) {
  return `accounts/email/${sha256(normalizeEmail(email))}.json`;
}

function refreshKeyForToken(token) {
  return `refresh/${sha256(token)}.json`;
}

function passwordSetupKeyForToken(token) {
  return `password-setup/${sha256(token)}.json`;
}

function legacyAccountIdForEmail(email) {
  return `legacy_${sha256(normalizeEmail(email))}`;
}

function signArvioAccessToken(account) {
  const now = Math.floor(Date.now() / 1000);
  const header = { alg: "HS256", typ: "JWT" };
  const payload = {
    iss: AUTH_ISSUER,
    sub: account.accountId,
    email: normalizeEmail(account.email),
    iat: now,
    exp: now + ACCESS_TOKEN_TTL_SECONDS
  };
  const signingInput = `${base64urlJson(header)}.${base64urlJson(payload)}`;
  const signature = crypto
    .createHmac("sha256", authSecret())
    .update(signingInput)
    .digest("base64url");
  return `${signingInput}.${signature}`;
}

function verifyArvioAccessToken(accessToken) {
  const parts = String(accessToken || "").split(".");
  if (parts.length !== 3) {
    throw new Error("Invalid ARVIO token");
  }
  const signingInput = `${parts[0]}.${parts[1]}`;
  const expected = crypto
    .createHmac("sha256", authSecret())
    .update(signingInput)
    .digest("base64url");
  const actual = parts[2];
  const expectedBuffer = Buffer.from(expected);
  const actualBuffer = Buffer.from(actual);
  if (
    expectedBuffer.length !== actualBuffer.length ||
    !crypto.timingSafeEqual(expectedBuffer, actualBuffer)
  ) {
    throw new Error("Invalid ARVIO token signature");
  }

  let payload;
  try {
    payload = JSON.parse(Buffer.from(parts[1], "base64url").toString("utf8"));
  } catch {
    throw new Error("Invalid ARVIO token payload");
  }
  if (payload.iss !== AUTH_ISSUER || !payload.sub || !payload.email) {
    throw new Error("Invalid ARVIO token claims");
  }
  if (Number(payload.exp || 0) <= Math.floor(Date.now() / 1000)) {
    throw new Error("ARVIO token expired");
  }
  return {
    supabaseUserId: String(payload.sub),
    email: normalizeEmail(payload.email),
    authProvider: "netlify"
  };
}

async function hashPassword(password) {
  const salt = crypto.randomBytes(16).toString("base64url");
  const n = 16384;
  const r = 8;
  const p = 1;
  const hash = await new Promise((resolve, reject) => {
    crypto.scrypt(password, salt, 64, { N: n, r, p }, (error, derivedKey) => {
      if (error) reject(error);
      else resolve(derivedKey.toString("base64url"));
    });
  });
  return `scrypt:${n}:${r}:${p}:${salt}:${hash}`;
}

async function verifyPassword(password, encoded) {
  const parts = String(encoded || "").split(":");
  if (parts.length !== 6 || parts[0] !== "scrypt") return false;
  const [, nRaw, rRaw, pRaw, salt, expected] = parts;
  const n = Number(nRaw);
  const r = Number(rRaw);
  const p = Number(pRaw);
  if (!n || !r || !p || !salt || !expected) return false;
  const actual = await new Promise((resolve, reject) => {
    crypto.scrypt(password, salt, 64, { N: n, r, p }, (error, derivedKey) => {
      if (error) reject(error);
      else resolve(derivedKey.toString("base64url"));
    });
  });
  const actualBuffer = Buffer.from(actual);
  const expectedBuffer = Buffer.from(expected);
  return actualBuffer.length === expectedBuffer.length &&
    crypto.timingSafeEqual(actualBuffer, expectedBuffer);
}

async function loadAuthAccount(event, email) {
  const store = authStores(event);
  return getJSONOrNull(store, accountKeyForEmail(email));
}

async function saveAuthAccount(event, account) {
  const store = authStores(event);
  const normalizedEmail = normalizeEmail(account.email);
  const saved = {
    ...account,
    email: normalizedEmail,
    updatedAt: new Date().toISOString()
  };
  await store.setJSON(accountKeyForEmail(normalizedEmail), saved, {
    metadata: {
      accountId: saved.accountId,
      email: normalizedEmail,
      updatedAt: saved.updatedAt
    }
  });
  return saved;
}

async function loadLegacySnapshotByEmail(event, email) {
  const stores = snapshotStores(event);
  return getJSONOrNull(stores.legacy, `email/${sha256(normalizeEmail(email))}.json`);
}

async function loadLegacyUserByEmail(email) {
  const normalizedEmail = normalizeEmail(email);
  if (!normalizedEmail) return null;
  try {
    const result = await getPool().query(
      `SELECT supabase_user_id, email, email_normalized
         FROM public.legacy_supabase_users
        WHERE email_normalized = $1
        LIMIT 1`,
      [normalizedEmail]
    );
    return result.rows[0] || null;
  } catch (error) {
    console.warn(`legacy user lookup failed for ${normalizedEmail}: ${error.message}`);
    return null;
  }
}

async function loadLegacyAccountReference(event, email) {
  const [legacyUser, legacySnapshot] = await Promise.all([
    loadLegacyUserByEmail(email),
    loadLegacySnapshotByEmail(event, email)
  ]);
  if (!legacyUser && !legacySnapshot) return null;
  return {
    user: legacyUser,
    snapshot: legacySnapshot,
    accountId: legacyUser?.supabase_user_id || legacyAccountIdForEmail(email)
  };
}

async function issueArvioSession(event, account) {
  const normalizedAccount = {
    ...account,
    email: normalizeEmail(account.email)
  };
  const accessToken = signArvioAccessToken(normalizedAccount);
  const refreshToken = randomToken(48);
  const expiresAt = new Date(Date.now() + REFRESH_TOKEN_TTL_MS).toISOString();
  const store = authStores(event);
  await store.setJSON(refreshKeyForToken(refreshToken), {
    accountId: normalizedAccount.accountId,
    email: normalizedAccount.email,
    createdAt: new Date().toISOString(),
    expiresAt
  }, {
    metadata: {
      accountId: normalizedAccount.accountId,
      email: normalizedAccount.email,
      expiresAt
    }
  });
  return {
    access_token: accessToken,
    refresh_token: refreshToken,
    expires_in: ACCESS_TOKEN_TTL_SECONDS,
    token_type: "bearer",
    user: {
      id: normalizedAccount.accountId,
      email: normalizedAccount.email
    }
  };
}

async function refreshArvioSession(event, refreshToken) {
  const store = authStores(event);
  const session = await getJSONOrNull(store, refreshKeyForToken(refreshToken));
  if (!session || !session.accountId || !session.email) {
    const error = new Error("Invalid refresh token");
    error.statusCode = 401;
    throw error;
  }
  if (Date.now() > Date.parse(session.expiresAt || "")) {
    const error = new Error("Refresh token expired");
    error.statusCode = 401;
    throw error;
  }
  const account = await loadAuthAccount(event, session.email) || {
    accountId: session.accountId,
    email: session.email
  };
  return issueArvioSession(event, account);
}

function emailProviderName() {
  if (process.env.RESEND_API_KEY) return "resend";
  if (process.env.POSTMARK_SERVER_TOKEN) return "postmark";
  if (process.env.SENDGRID_API_KEY) return "sendgrid";
  return "";
}

async function sendPasswordSetupEmail(email, setupUrl) {
  const provider = emailProviderName();
  if (!provider) {
    const error = new Error("Password setup email is not configured yet");
    error.statusCode = 503;
    throw error;
  }

  const from = process.env.AUTH_EMAIL_FROM || "ARVIO <noreply@auth.arvio.tv>";
  const subject = "Create your ARVIO Cloud password";
  const text = [
    "ARVIO Cloud moved to a new secure server.",
    "To keep your account protected, create a new ARVIO Cloud password:",
    setupUrl,
    "This link expires in 1 hour."
  ].join("\n\n");
  const html = `
    <p>ARVIO Cloud moved to a new secure server.</p>
    <p>To keep your account protected, create a new ARVIO Cloud password:</p>
    <p><a href="${setupUrl}">Create new password</a></p>
    <p>This link expires in 1 hour.</p>
  `;

  if (provider === "resend") {
    const response = await fetch("https://api.resend.com/emails", {
      method: "POST",
      headers: {
        authorization: `Bearer ${process.env.RESEND_API_KEY}`,
        "content-type": "application/json"
      },
      body: JSON.stringify({ from, to: [email], subject, html, text })
    });
    const result = await response.json().catch(() => ({}));
    if (!response.ok) {
      throw new Error(`Email delivery failed (${response.status}): ${publicError(result, response.statusText)}`);
    }
    return { provider, id: result?.id || null };
  }

  if (provider === "postmark") {
    const response = await fetch("https://api.postmarkapp.com/email", {
      method: "POST",
      headers: {
        "X-Postmark-Server-Token": process.env.POSTMARK_SERVER_TOKEN,
        "content-type": "application/json"
      },
      body: JSON.stringify({ From: from, To: email, Subject: subject, HtmlBody: html, TextBody: text })
    });
    const result = await response.json().catch(() => ({}));
    if (!response.ok) {
      throw new Error(`Email delivery failed (${response.status}): ${publicError(result, response.statusText)}`);
    }
    return { provider, id: result?.MessageID || result?.MessageId || null };
  }

  if (provider === "sendgrid") {
    const response = await fetch("https://api.sendgrid.com/v3/mail/send", {
      method: "POST",
      headers: {
        authorization: `Bearer ${process.env.SENDGRID_API_KEY}`,
        "content-type": "application/json"
      },
      body: JSON.stringify({
        personalizations: [{ to: [{ email }] }],
        from: { email: from.replace(/^.*<(.+)>$/, "$1"), name: "ARVIO" },
        subject,
        content: [
          { type: "text/plain", value: text },
          { type: "text/html", value: html }
        ]
      })
    });
    if (!response.ok) throw new Error(`Email delivery failed (${response.status})`);
    return { provider, id: response.headers.get("x-message-id") || null };
  }
  return { provider, id: null };
}

async function startPasswordSetup(event, email) {
  const normalizedEmail = normalizeEmail(email);
  const account = await loadAuthAccount(event, normalizedEmail);
  const legacy = account ? null : await loadLegacyAccountReference(event, normalizedEmail);
  if (!account && !legacy) {
    return { exists: false, emailSent: false };
  }

  const token = randomToken(48);
  const expiresAt = new Date(Date.now() + PASSWORD_SETUP_TTL_MS).toISOString();
  const pending = {
    email: normalizedEmail,
    accountId: account?.accountId || legacy?.accountId || legacyAccountIdForEmail(normalizedEmail),
    createdAt: new Date().toISOString(),
    expiresAt
  };
  const store = authStores(event);
  await store.setJSON(passwordSetupKeyForToken(token), pending, {
    metadata: {
      email: normalizedEmail,
      accountId: pending.accountId,
      expiresAt
    }
  });

  const baseUrl = (process.env.SITE_URL || process.env.TV_AUTH_VERIFY_BASE_URL || "https://auth.arvio.tv").replace(/\/+$/, "");
  const setupUrl = `${baseUrl}/?mode=set-password&token=${encodeURIComponent(token)}`;
  const emailResult = await sendPasswordSetupEmail(normalizedEmail, setupUrl);
  return {
    exists: true,
    emailSent: true,
    emailProvider: emailResult?.provider || emailProviderName(),
    emailId: emailResult?.id || null
  };
}

async function completePasswordSetup(event, token, password) {
  const store = authStores(event);
  const pending = await getJSONOrNull(store, passwordSetupKeyForToken(token));
  if (!pending || !pending.email || !pending.accountId) {
    const error = new Error("Password setup link is invalid or expired");
    error.statusCode = 400;
    throw error;
  }
  if (Date.now() > Date.parse(pending.expiresAt || "")) {
    const error = new Error("Password setup link expired. Request a new one.");
    error.statusCode = 400;
    throw error;
  }
  const existing = await loadAuthAccount(event, pending.email);
  const account = await saveAuthAccount(event, {
    ...(existing || {}),
    accountId: existing?.accountId || pending.accountId,
    email: pending.email,
    passwordHash: await hashPassword(password),
    passwordSetupCompletedAt: new Date().toISOString(),
    migrationSource: "password_setup",
    migratedAt: existing?.migratedAt || new Date().toISOString(),
    createdAt: existing?.createdAt || new Date().toISOString()
  });
  if (typeof store.delete === "function") {
    await store.delete(passwordSetupKeyForToken(token)).catch(() => {});
  }
  return issueArvioSession(event, account);
}

function requiresExplicitPasswordSetup(account) {
  return account?.migrationSource === "supabase_password_bridge" && !account?.passwordSetupCompletedAt;
}

async function throwPasswordSetupRequired(event, email, message) {
  const error = new Error(message);
  error.statusCode = 409;
  error.code = "password_setup_required";
  try {
    const setup = await startPasswordSetup(event, email);
    error.emailSent = !!setup.emailSent;
    error.emailProvider = setup.emailProvider || null;
    error.emailId = setup.emailId || null;
  } catch (sendError) {
    error.emailSent = false;
    error.setupError = publicError(sendError, "Password setup email failed");
  }
  throw error;
}

async function authenticateNetlifyPassword(event, email, password) {
  const account = await loadAuthAccount(event, email);
  if (requiresExplicitPasswordSetup(account)) {
    await throwPasswordSetupRequired(
      event,
      email,
      "ARVIO Cloud moved to a new secure server. To keep your data protected, create a new ARVIO Cloud password from the email we sent you."
    );
  }
  if (!account || !account.passwordHash) {
    const legacy = account ? null : await loadLegacyAccountReference(event, email);
    if (legacy || account) {
      await throwPasswordSetupRequired(
        event,
        email,
        "ARVIO Cloud moved to a new secure server. To keep your data protected, create a new ARVIO Cloud password from the email we sent you."
      );
    }
    const error = new Error("Invalid email or password");
    error.statusCode = 401;
    throw error;
  }
  const ok = await verifyPassword(password, account.passwordHash);
  if (!ok) {
    const error = new Error("Invalid email or password");
    error.statusCode = 401;
    throw error;
  }
  return issueArvioSession(event, account);
}

async function createNetlifyAccount(event, email, password) {
  const existing = await loadAuthAccount(event, email);
  const legacy = existing ? null : await loadLegacyAccountReference(event, email);
  if (requiresExplicitPasswordSetup(existing)) {
    await throwPasswordSetupRequired(
      event,
      email,
      "ARVIO Cloud moved to a new secure server. Create a new ARVIO Cloud password to keep your existing data."
    );
  }
  if (legacy && !existing?.passwordHash) {
    await throwPasswordSetupRequired(
      event,
      email,
      "ARVIO Cloud moved to a new secure server. Create a new ARVIO Cloud password to keep your existing data."
    );
  }
  if (existing?.passwordHash) {
    const error = new Error("Account already exists. Sign in instead.");
    error.statusCode = 409;
    throw error;
  }

  const account = await saveAuthAccount(event, {
    accountId: existing?.accountId || crypto.randomUUID(),
    email,
    passwordHash: await hashPassword(password),
    createdAt: existing?.createdAt || new Date().toISOString()
  });
  return issueArvioSession(event, account);
}

async function handleAuthLogin(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const body = parseBody(event);
    const email = normalizeEmail(body.email);
    const password = String(body.password || "");
    const emailError = validateEmail(email, false);
    if (emailError) return json(400, { error: emailError });
    if (!password) return json(400, { error: "Password is required" });
    const token = await authenticateNetlifyPassword(event, email, password);
    return json(200, token);
  } catch (error) {
    if (error?.code === "password_setup_required") {
      return json(409, {
        code: "password_setup_required",
        error: error.message,
        email_sent: !!error.emailSent,
        email_provider: error.emailProvider || null,
        email_id: error.emailId || null,
        setup_error: error.setupError || null
      });
    }
    return handlerError(event, error, "Sign in failed");
  }
}

async function handleAuthPasswordStart(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const body = parseBody(event);
    const email = normalizeEmail(body.email);
    const emailError = validateEmail(email, false);
    if (emailError) return json(400, { error: emailError });
    const setup = await startPasswordSetup(event, email);
    return json(200, {
      ok: true,
      email_sent: !!setup.emailSent,
      account_exists: !!setup.exists,
      email_provider: setup.emailProvider || null,
      email_id: setup.emailId || null
    });
  } catch (error) {
    return handlerError(event, error, "Password setup failed");
  }
}

async function handleAuthPasswordComplete(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    const body = parseBody(event);
    const token = String(body.token || "").trim();
    const password = String(body.password || "");
    if (!token) return json(400, { error: "Password setup token is required" });
    if (password.length < 6) return json(400, { error: "Password must be at least 6 characters" });
    const session = await completePasswordSetup(event, token, password);
    return json(200, session);
  } catch (error) {
    return handlerError(event, error, "Password setup failed");
  }
}

function randomCode(length) {
  const alphabet = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
  const bytes = crypto.randomBytes(length);
  return Array.from(bytes).map((byte) => alphabet[byte % alphabet.length]).join("");
}

function tvSessionStores(event) {
  connectLambda(event);
  return getStore("tv-auth-sessions");
}

function tvSessionKeys(session) {
  return {
    device: `device/${session.deviceCode}.json`,
    code: `code/${String(session.userCode || "").toUpperCase()}.json`
  };
}

async function saveTvSession(event, session) {
  const store = tvSessionStores(event);
  const keys = tvSessionKeys(session);
  await store.setJSON(keys.device, session, {
    metadata: {
      deviceCode: session.deviceCode,
      userCode: session.userCode,
      status: session.status,
      expiresAt: session.expiresAt
    }
  });
  await store.setJSON(keys.code, session, {
    metadata: {
      deviceCode: session.deviceCode,
      userCode: session.userCode,
      status: session.status,
      expiresAt: session.expiresAt
    }
  });
}

async function loadTvSessionByDevice(event, deviceCode) {
  const store = tvSessionStores(event);
  return getJSONOrNull(store, `device/${deviceCode}.json`);
}

async function loadTvSessionByCode(event, userCode) {
  const store = tvSessionStores(event);
  return getJSONOrNull(store, `code/${String(userCode || "").toUpperCase()}.json`);
}

function isTvSessionExpired(session) {
  return !session?.expiresAt || Date.now() > Date.parse(session.expiresAt);
}

function methodGuard(event, methods) {
  const method = event.httpMethod || "GET";
  if (methods.includes(method)) return null;
  return json(405, { error: "Method not allowed" });
}

function handlerError(event, error, fallback = "Unexpected error") {
  const status = error?.statusCode || error?.status || 500;
  return json(status, { error: publicError(error, fallback) });
}

async function handleCloudAuthEmail(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const body = parseBody(event);
    const email = normalizeEmail(body.email);
    const password = String(body.password || "");
    const emailError = validateEmail(email, true);
    if (emailError) return json(400, { error: emailError });
    if (password.length < 6) return json(400, { error: "Password must be at least 6 characters" });

    const token = await createNetlifyAccount(event, email, password);
    return json(200, token);
  } catch (error) {
    if (error?.code === "password_setup_required") {
      return json(409, {
        code: "password_setup_required",
        error: error.message,
        email_sent: !!error.emailSent,
        email_provider: error.emailProvider || null,
        email_id: error.emailId || null,
        setup_error: error.setupError || null
      });
    }
    return handlerError(event, error, "Account creation failed");
  }
}

async function handleCloudAuthReset(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const body = parseBody(event);
    const email = normalizeEmail(body.email);
    const emailError = validateEmail(email, true);
    if (emailError) return json(400, { error: emailError });
    const setup = await startPasswordSetup(event, email);
    return json(200, {
      ok: true,
      email_sent: !!setup.emailSent,
      account_exists: !!setup.exists,
      email_provider: setup.emailProvider || null,
      email_id: setup.emailId || null
    });
  } catch (error) {
    return handlerError(event, error, "Password reset failed");
  }
}

async function handleAuthRefresh(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const body = parseBody(event);
    const refreshToken = String(body.refresh_token || "").trim();
    if (!refreshToken) return json(400, { error: "refresh_token is required" });
    const token = await refreshArvioSession(event, refreshToken);
    return json(200, token);
  } catch (error) {
    return handlerError(event, error, "Session refresh failed");
  }
}

async function handleTvAuthStart(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const deviceCode = randomCode(32);
    const userCode = `${randomCode(4)}-${randomCode(4)}`;
    const expiresAt = new Date(Date.now() + 10 * 60 * 1000).toISOString();
    const session = {
      deviceCode,
      userCode,
      status: "pending",
      createdAt: new Date().toISOString(),
      expiresAt
    };
    await saveTvSession(event, session);
    const verifyBase = (process.env.TV_AUTH_VERIFY_BASE_URL || process.env.SITE_URL || "https://auth.arvio.tv").replace(/\/+$/, "");
    return json(200, {
      device_code: deviceCode,
      user_code: userCode,
      verification_url: `${verifyBase}/?code=${encodeURIComponent(userCode)}`,
      verification_uri: `${verifyBase}/?code=${encodeURIComponent(userCode)}`,
      expires_in: 600,
      interval: 3
    });
  } catch (error) {
    return handlerError(event, error, "Failed to start TV auth");
  }
}

async function handleTvAuthStatus(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const body = parseBody(event);
    const deviceCode = String(body.device_code || "").trim();
    if (!deviceCode) return json(400, { error: "device_code is required" });
    const session = await loadTvSessionByDevice(event, deviceCode);
    if (!session) return json(200, { status: "expired", message: "Session not found" });
    if (isTvSessionExpired(session) && session.status === "pending") {
      await saveTvSession(event, { ...session, status: "expired", expiredAt: new Date().toISOString() });
      return json(200, { status: "expired", message: "Code expired" });
    }
    if (session.status === "approved" && session.accessToken && session.refreshToken) {
      await saveTvSession(event, {
        ...session,
        status: "consumed",
        consumedAt: new Date().toISOString(),
        accessToken: null,
        refreshToken: null
      });
      return json(200, {
        status: "approved",
        access_token: session.accessToken,
        refresh_token: session.refreshToken,
        email: session.userEmail || null
      });
    }
    if (session.status === "expired" || session.status === "consumed") {
      return json(200, { status: "expired", message: "Code expired" });
    }
    return json(200, { status: "pending" });
  } catch (error) {
    return handlerError(event, error, "Failed to poll TV auth status");
  }
}

async function handleTvAuthApprove(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const accessToken = bearerToken(event);
    if (!accessToken) return json(401, { error: "Missing user access token" });
    const identity = await resolveIdentity(event);
    const body = parseBody(event);
    const code = String(body.code || "").trim().toUpperCase();
    const refreshToken = String(body.refresh_token || "").trim();
    if (!code || !refreshToken) return json(400, { error: "Missing required fields" });
    const session = await loadTvSessionByCode(event, code);
    if (!session || isTvSessionExpired(session) || session.status !== "pending") {
      return json(400, { error: "Invalid or expired code" });
    }
    await saveTvSession(event, {
      ...session,
      status: "approved",
      approvedAt: new Date().toISOString(),
      userId: identity.supabaseUserId,
      userEmail: identity.email,
      accessToken,
      refreshToken
    });
    return json(200, { ok: true });
  } catch (error) {
    return handlerError(event, error, "TV pairing failed");
  }
}

async function handleTvAuthComplete(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  const wrongMethod = methodGuard(event, ["POST"]);
  if (wrongMethod) return wrongMethod;
  try {
    assertAppRequest(event);
    const body = parseBody(event);
    const code = String(body.code || "").trim().toUpperCase();
    const email = normalizeEmail(body.email);
    const password = String(body.password || "");
    const intent = String(body.intent || body.action || "signin").trim().toLowerCase();
    if (!code || !email || !password) return json(400, { error: "Missing required fields" });
    const emailError = validateEmail(email, intent === "signup");
    if (emailError) return json(400, { error: emailError });
    const session = await loadTvSessionByCode(event, code);
    if (!session || isTvSessionExpired(session) || session.status !== "pending") {
      return json(400, { error: "Invalid or expired code" });
    }
    const token = intent === "signup"
      ? await createNetlifyAccount(event, email, password)
      : await authenticateNetlifyPassword(event, email, password);
    if (!token.access_token || !token.refresh_token || !token.user?.id) {
      throw new Error("Auth response incomplete");
    }
    await saveTvSession(event, {
      ...session,
      status: "approved",
      approvedAt: new Date().toISOString(),
      userId: token.user.id,
      userEmail: token.user.email || email,
      accessToken: token.access_token,
      refreshToken: token.refresh_token
    });
    return json(200, { ok: true });
  } catch (error) {
    if (error?.code === "password_setup_required") {
      return json(409, {
        code: "password_setup_required",
        error: error.message,
        email_sent: !!error.emailSent,
        email_provider: error.emailProvider || null,
        email_id: error.emailId || null,
        setup_error: error.setupError || null
      });
    }
    const status = error?.statusCode === 400 ? 401 : (error?.statusCode || 500);
    return json(status, { error: status === 401 ? "Invalid email or password" : publicError(error, "TV pairing failed") });
  }
}

const TMDB_ALLOWED_PATHS = [
  "/trending/",
  "/movie/",
  "/tv/",
  "/search/",
  "/discover/",
  "/find/",
  "/genre/",
  "/person/",
  "/collection/",
  "/watch/providers",
  "/configuration"
];

async function handleTmdbProxy(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  try {
    assertAppRequest(event);
    const pathParam = event.queryStringParameters?.path || "";
    if (!pathParam) return json(400, { error: "Missing path parameter" });
    if (!TMDB_ALLOWED_PATHS.some((allowed) => pathParam.startsWith(allowed))) {
      return json(403, { error: "Path not allowed" });
    }
    const tmdbKey = process.env.TMDB_API_KEY || "";
    if (!tmdbKey) throw new Error("TMDB_API_KEY not configured");
    const tmdbUrl = new URL(`https://api.themoviedb.org/3${pathParam}`);
    tmdbUrl.searchParams.set("api_key", tmdbKey);
    Object.entries(event.queryStringParameters || {}).forEach(([key, value]) => {
      if (key !== "path" && value !== undefined && value !== null) tmdbUrl.searchParams.set(key, String(value));
    });
    const response = await fetch(tmdbUrl, {
      headers: {
        accept: "application/json",
        "accept-encoding": "identity;q=1, *;q=0",
        "cache-control": "max-age=300",
        "user-agent": "ARVIO-Netlify-TMDB-Proxy/1.0"
      }
    });
    const text = await response.text();
    return {
      statusCode: response.status,
      headers: {
        ...JSON_HEADERS,
        "cache-control": response.ok ? "public, max-age=3600, stale-while-revalidate=86400" : "no-store"
      },
      body: text
    };
  } catch (error) {
    return json(502, { error: errorMessage(error) });
  }
}

const TRAKT_ALLOWED_PATHS = [
  "/oauth/device/code",
  "/oauth/device/token",
  "/oauth/token",
  "/users/me",
  "/users/",
  "/sync/last_activities",
  "/sync/history",
  "/sync/watchlist",
  "/sync/watched",
  "/sync/playback",
  "/scrobble/",
  "/movies/",
  "/shows/",
  "/lists/",
  "/search/",
  "/calendars/"
];

async function handleTraktProxy(event) {
  const preflight = options(event);
  if (preflight) return preflight;
  try {
    assertAppRequest(event);
    const pathParam = event.queryStringParameters?.path || "";
    const method = String(event.queryStringParameters?.method || "GET").toUpperCase();
    if (!pathParam) return json(400, { error: "Missing path parameter" });
    if (!TRAKT_ALLOWED_PATHS.some((allowed) => pathParam.startsWith(allowed))) {
      return json(403, { error: "Path not allowed" });
    }
    const clientId = process.env.TRAKT_CLIENT_ID || "";
    const clientSecret = process.env.TRAKT_CLIENT_SECRET || "";
    if (!clientId || !clientSecret) throw new Error("Trakt credentials not configured");
    const traktUrl = new URL(`https://api.trakt.tv${pathParam}`);
    Object.entries(event.queryStringParameters || {}).forEach(([key, value]) => {
      if (key !== "path" && key !== "method" && value !== undefined && value !== null) {
        traktUrl.searchParams.set(key, String(value));
      }
    });

    let requestBody = undefined;
    if (method === "POST" || method === "DELETE") {
      let body = {};
      try {
        body = event.body
          ? JSON.parse(event.isBase64Encoded ? Buffer.from(event.body, "base64").toString("utf8") : event.body)
          : {};
      } catch {
        body = {};
      }
      if (pathParam.includes("/oauth/device/code")) {
        body.client_id = clientId;
      } else if (pathParam.includes("/oauth/device/token") || pathParam.includes("/oauth/token")) {
        body.client_id = clientId;
        body.client_secret = clientSecret;
      }
      requestBody = Object.keys(body).length > 0 ? JSON.stringify(body) : undefined;
    }

    const headers = {
      "content-type": "application/json",
      "trakt-api-key": clientId,
      "trakt-api-version": "2"
    };
    const userToken = event.headers["x-user-token"] || event.headers["X-User-Token"];
    if (userToken) headers.authorization = `Bearer ${userToken}`;

    const response = await fetch(traktUrl, { method, headers, body: requestBody });
    const text = await response.text();
    let data;
    try {
      data = text ? JSON.parse(text) : { status: response.status };
    } catch {
      data = text ? { raw: text } : { status: response.status };
    }
    return {
      statusCode: response.status,
      headers: {
        ...JSON_HEADERS,
        "cache-control": "no-store",
        "x-pagination-page": response.headers.get("x-pagination-page") || "",
        "x-pagination-limit": response.headers.get("x-pagination-limit") || "",
        "x-pagination-page-count": response.headers.get("x-pagination-page-count") || "",
        "x-pagination-item-count": response.headers.get("x-pagination-item-count") || ""
      },
      body: JSON.stringify(data)
    };
  } catch (error) {
    return json(502, { error: errorMessage(error) });
  }
}

function payloadMetrics(payload) {
  const root = typeof payload === "string" ? JSON.parse(payload) : payload;
  const profiles = Array.isArray(root.profiles) ? root.profiles : null;
  const profileCount = profiles ? profiles.length : null;
  const profileIds = new Set(
    (profiles || [])
      .map((profile) => profile && profile.id)
      .filter((id) => typeof id === "string" && id.length > 0)
  );
  const scopedKeys = [
    "profileSettingsById",
    "addonsByProfile",
    "catalogsByProfile",
    "hiddenPreinstalledByProfile",
    "hiddenAddonByProfile",
    "hiddenHomeServerByProfile",
    "iptvByProfile",
    "watchlistByProfile"
  ];
  const scopedCoverage = scopedKeys.reduce((total, key) => {
    const obj = root[key];
    if (!obj || typeof obj !== "object" || Array.isArray(obj)) return total;
    let count = 0;
    profileIds.forEach((profileId) => {
      if (Object.prototype.hasOwnProperty.call(obj, profileId)) count += 1;
    });
    return total + count;
  }, 0);

  const hasFullShape = scopedKeys.some((key) => Object.prototype.hasOwnProperty.call(root, key));
  const hasConfiguredState =
    (Array.isArray(root.addons) && root.addons.length > 0) ||
    Boolean(String(root.iptvM3uUrl || "").trim()) ||
    Object.values(root.addonsByProfile || {}).some((value) => Array.isArray(value) && value.length > 0) ||
    Object.values(root.watchlistByProfile || {}).some((value) => Array.isArray(value) && value.length > 0) ||
    Object.values(root.iptvByProfile || {}).some((value) => {
      if (!value || typeof value !== "object") return false;
      return Boolean(String(value.m3uUrl || "").trim()) ||
        Boolean(String(value.epgUrl || "").trim()) ||
        (Array.isArray(value.playlists) && value.playlists.length > 0) ||
        (Array.isArray(value.favoriteChannels) && value.favoriteChannels.length > 0) ||
        (Array.isArray(value.favoriteGroups) && value.favoriteGroups.length > 0);
    });

  let usefulProfiles = false;
  if (profileCount > 1) {
    usefulProfiles = true;
  } else if (profileCount === 1) {
    const profile = profiles[0] || {};
    usefulProfiles = !(
      String(profile.name || "").toLowerCase() === "profile 1" &&
      Number(profile.avatarId || 0) === 0 &&
      Number(profile.avatarImageVersion || 0) <= 0 &&
      !profile.isKidsProfile &&
      !profile.isLocked &&
      !String(profile.pin || "").trim()
    );
  }

  let restoreRank;
  if (profileCount !== null && profileCount <= 0) restoreRank = 0;
  else if (profileCount !== null && profileCount > 1 && hasFullShape) restoreRank = 80;
  else if (profileCount !== null && profileCount > 1) restoreRank = 70;
  else if ((usefulProfiles || hasConfiguredState) && hasFullShape) restoreRank = 50;
  else if (usefulProfiles || hasConfiguredState) restoreRank = 40;
  else if (profileCount === null && hasFullShape) restoreRank = 30;
  else if (profileCount === null) restoreRank = 20;
  else restoreRank = 10;

  return {
    payload: root,
    profileCount,
    scopedCoverage,
    restoreRank,
    payloadVersion: Number(root.version || 1),
    payloadUpdatedAt: Number(root.updatedAt || 0) > 0
      ? new Date(Number(root.updatedAt)).toISOString()
      : null
  };
}

function isExistingSnapshotRicher(existing, incoming) {
  if (!existing) return false;
  const existingRank = Number(existing.restore_rank ?? existing.restoreRank ?? 0);
  const incomingRank = Number(incoming?.restoreRank ?? 0);

  if (incomingRank >= 40) return false;
  if (existingRank >= 40 && incomingRank < 40) return true;
  return existingRank > incomingRank;
}

function bearerToken(event) {
  const auth = event.headers.authorization || event.headers.Authorization || "";
  const match = auth.match(/^Bearer\s+(.+)$/i);
  return match ? match[1].trim() : "";
}

async function resolveIdentity(event) {
  const token = bearerToken(event);
  if (!token) {
    throw new Error("Missing Authorization bearer token");
  }
  try {
    return verifyArvioAccessToken(token);
  } catch (error) {
    const rejected = new Error(`Token rejected (${publicError(error)})`);
    rejected.statusCode = 401;
    throw rejected;
  }
}

function snapshotStores(event) {
  connectLambda(event);
  return {
    account: getStore("account-sync"),
    legacy: getStore("legacy-supabase-sync"),
    events: getStore("account-sync-events"),
    usage: getStore("app-usage")
  };
}

function snapshotKeys(identity) {
  const supabaseUserId = String(identity.supabaseUserId || "").trim();
  const email = normalizeEmail(identity.email);
  return {
    supabase: `supabase/${supabaseUserId}.json`,
    email: `email/${sha256(email)}.json`
  };
}

async function getJSONOrNull(store, key) {
  try {
    return await store.get(key, { type: "json", consistency: "strong" });
  } catch (error) {
    if (String(error?.message || "").includes("uncachedEdgeURL")) {
      return await store.get(key, { type: "json" });
    }
    if (error?.status === 404 || error?.name === "BlobNotFoundError") return null;
    throw error;
  }
}

async function loadSnapshotFromBlobs(event, identity) {
  const stores = snapshotStores(event);
  const keys = snapshotKeys(identity);
  const accountSnapshot = await getJSONOrNull(stores.account, keys.supabase) ||
    await getJSONOrNull(stores.account, keys.email);
  if (accountSnapshot) return { ...accountSnapshot, source: accountSnapshot.source || "netlify" };

  const legacySnapshot = await getJSONOrNull(stores.legacy, keys.supabase) ||
    await getJSONOrNull(stores.legacy, keys.email);
  if (!legacySnapshot) return null;

  const claimed = {
    ...legacySnapshot,
    source: "supabase_import_claimed",
    claimedAt: new Date().toISOString()
  };
  await saveSnapshotToBlobs(event, identity, claimed);
  return claimed;
}

async function saveSnapshotToBlobs(event, identity, snapshot) {
  const stores = snapshotStores(event);
  const keys = snapshotKeys(identity);
  const normalized = {
    payload: snapshot.payload,
    payloadVersion: snapshot.payloadVersion ?? snapshot.payload_version ?? 1,
    restoreRank: snapshot.restoreRank ?? snapshot.restore_rank ?? 0,
    profileCount: snapshot.profileCount ?? snapshot.profile_count ?? null,
    scopedCoverage: snapshot.scopedCoverage ?? snapshot.scoped_coverage ?? 0,
    payloadUpdatedAt: snapshot.payloadUpdatedAt ?? snapshot.payload_updated_at ?? null,
    source: snapshot.source || "netlify",
    updatedAt: snapshot.updatedAt || new Date().toISOString()
  };
  const metadata = {
    email: normalizeEmail(identity.email),
    supabaseUserId: identity.supabaseUserId,
    restoreRank: String(normalized.restoreRank),
    profileCount: String(normalized.profileCount ?? ""),
    updatedAt: normalized.updatedAt
  };
  await stores.account.setJSON(keys.supabase, normalized, { metadata });
  await stores.account.setJSON(keys.email, normalized, { metadata });
  return normalized;
}

async function appendSnapshotEvent(event, identity, snapshot) {
  const stores = snapshotStores(event);
  const cursor = Date.now();
  const keys = snapshotKeys(identity);
  await stores.events.setJSON(`supabase/${identity.supabaseUserId}/${cursor}.json`, {
    event_id: cursor,
    scope: "snapshot",
    profile_id: "",
    entity_key: "account",
    operation: "upsert",
    payload: snapshot.payload,
    item_version: cursor,
    created_at: new Date(cursor).toISOString()
  }, {
    metadata: {
      supabaseUserId: identity.supabaseUserId,
      email: normalizeEmail(identity.email),
      accountKey: keys.supabase
    }
  });
  return cursor;
}

async function getOrCreateAccount(client, identity) {
  const email = normalizeEmail(identity.email);
  const existing = await client.query(
    `SELECT *
       FROM public.arvio_accounts
      WHERE supabase_user_id = $1 OR email_normalized = $2
      ORDER BY CASE WHEN supabase_user_id = $1 THEN 0 ELSE 1 END
      LIMIT 1`,
    [identity.supabaseUserId, email]
  );
  if (existing.rows[0]) {
    const account = existing.rows[0];
    await client.query(
      `UPDATE public.arvio_accounts
          SET email = $2,
              email_normalized = $3,
              supabase_user_id = COALESCE(supabase_user_id, $1::uuid),
              updated_at = now(),
              last_seen_at = now()
        WHERE id = $4`,
      [identity.supabaseUserId, identity.email, email, account.id]
    );
    return { ...account, email: identity.email, email_normalized: email };
  }

  const inserted = await client.query(
    `INSERT INTO public.arvio_accounts (email, email_normalized, supabase_user_id, last_seen_at)
     VALUES ($1, $2, $3::uuid, now())
     RETURNING *`,
    [identity.email, email, identity.supabaseUserId]
  );
  return inserted.rows[0];
}

async function claimLegacySnapshotIfNeeded(client, account, identity) {
  const current = await client.query(
    `SELECT payload, payload_version, restore_rank, profile_count, scoped_coverage,
            payload_updated_at, updated_at, source
       FROM public.account_sync_snapshots
      WHERE account_id = $1`,
    [account.id]
  );
  if (current.rows[0]) return current.rows[0];

  const legacy = await client.query(
    `SELECT *
       FROM public.legacy_supabase_snapshots
      WHERE supabase_user_id = $1::uuid OR email_normalized = $2
      ORDER BY restore_rank DESC, profile_count DESC NULLS LAST, scoped_coverage DESC, payload_updated_at DESC NULLS LAST
      LIMIT 1`,
    [identity.supabaseUserId, normalizeEmail(identity.email)]
  );
  const row = legacy.rows[0];
  if (!row) return null;

  await client.query(
    `INSERT INTO public.account_sync_snapshots (
       account_id, payload, payload_version, restore_rank, profile_count,
       scoped_coverage, payload_updated_at, source, updated_at
     )
     VALUES ($1, $2::jsonb, $3, $4, $5, $6, $7, 'supabase_import', now())
     ON CONFLICT (account_id) DO NOTHING`,
    [
      account.id,
      JSON.stringify(row.payload),
      row.payload_version,
      row.restore_rank,
      row.profile_count,
      row.scoped_coverage,
      row.payload_updated_at
    ]
  );
  await client.query(
    `UPDATE public.legacy_supabase_snapshots
        SET claimed_account_id = $2,
            claimed_at = now()
      WHERE supabase_user_id = $1::uuid`,
    [identity.supabaseUserId, account.id]
  );

  return {
    payload: row.payload,
    payload_version: row.payload_version,
    restore_rank: row.restore_rank,
    profile_count: row.profile_count,
    scoped_coverage: row.scoped_coverage,
    payload_updated_at: row.payload_updated_at,
    updated_at: row.imported_at,
    source: "supabase_import"
  };
}

module.exports = {
  getPool,
  json,
  options,
  parseBody,
  payloadMetrics,
  isExistingSnapshotRicher,
  resolveIdentity,
  getOrCreateAccount,
  claimLegacySnapshotIfNeeded,
  normalizeEmail,
  sha256,
  snapshotStores,
  snapshotKeys,
  loadSnapshotFromBlobs,
  saveSnapshotToBlobs,
  appendSnapshotEvent,
  handleAuthLogin,
  handleAuthPasswordStart,
  handleAuthPasswordComplete,
  handleAuthRefresh,
  handleCloudAuthEmail,
  handleCloudAuthReset,
  handleTmdbProxy,
  handleTraktProxy,
  handleTvAuthApprove,
  handleTvAuthComplete,
  handleTvAuthStart,
  handleTvAuthStatus
};
