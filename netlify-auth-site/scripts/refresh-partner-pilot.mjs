import fs from 'node:fs/promises';
import path from 'node:path';
import crypto from 'node:crypto';
import { createRequire } from 'node:module';
import { fileURLToPath, pathToFileURL } from 'node:url';
import { execFileSync } from 'node:child_process';

// Refresh function environment snapshots without rebuilding or replacing production code.
const siteId = '17ccb668-bf3c-412d-9573-a28a09e52122';
const address = String(process.argv[2] || '').trim().toLowerCase();
if (!address.includes('@')) throw new Error('Provide the newly allowlisted pilot email');
const config = JSON.parse(await fs.readFile(path.join(process.env.APPDATA, 'netlify/Config/config.json'), 'utf8'));
const token = config.users[config.userId].auth.token;
async function api(route, method = 'GET', body) {
  const response = await fetch(`https://api.netlify.com/api/v1${route}`, {
    method, headers: { Authorization: `Bearer ${token}`, 'content-type': 'application/json' },
    ...(body ? { body: JSON.stringify(body) } : {}), signal: AbortSignal.timeout(30000)
  });
  if (!response.ok) throw new Error(`Netlify ${method} ${route}: ${response.status}`);
  return response.json();
}

const site = await api(`/sites/${siteId}`);
if (site.name !== 'arvio-auth' || site.ssl_url !== 'https://auth.arvio.tv') throw new Error('Unexpected site');
const vars = await api(`/accounts/${site.account_id}/env?site_id=${siteId}`);
const value = key => {
  const item = vars.find(item => item.key === key);
  return item?.values.find(v => v.context === 'production')?.value || item?.values.find(v => v.context === 'all')?.value;
};
if (value('PARTNER_ENABLED') !== 'true' || value('PARTNER_PILOT_MODE') !== 'true' ||
    !String(value('PARTNER_PILOT_OWNERS')).split(',').map(v => v.trim().toLowerCase()).includes(address)) {
  throw new Error('The account is not allowlisted in the restricted pilot');
}
const current = await api(`/deploys/${site.published_deploy.id}`);
const currentFiles = await api(`/deploys/${current.id}/files`);
if (!current.available_functions.some(fn => fn.n === 'partner-api') ||
    !currentFiles.some(file => file.path === '/partner/index.html')) throw new Error('The current deployment does not contain the pilot');
const files = Object.fromEntries(currentFiles.map(file => [file.path, file.sha]));
const functions = Object.fromEntries(current.available_functions.map(fn => [fn.n, fn.d]));
const functions_config = Object.fromEntries(current.available_functions.map(fn => [fn.n, {
  ...(fn.bd ? { build_data: fn.bd } : {}), ...(fn.dn ? { display_name: fn.dn } : {}),
  ...(fn.g ? { generator: fn.g } : {}), ...(fn.p !== undefined ? { priority: fn.p } : {})
}]));
const bundles = new Map();
const stageArgument = process.argv.find(value => value.startsWith('--rebuild-deployed='));
if (stageArgument) {
  const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '../..');
  const stage = path.resolve(stageArgument.slice('--rebuild-deployed='.length));
  if (!stage.startsWith(path.join(root, 'artifacts') + path.sep)) throw new Error('Staging must be inside repository artifacts');
  const sourceDeploy = await api('/deploys/6ae0f747de851cc9227cf182');
  const commit = 'c02256fddab9954b7cead983695a8a4dcc8cd9d5';
  if (sourceDeploy.commit_ref !== commit || sourceDeploy.site_id !== siteId) throw new Error('Unexpected source deployment');
  for (const fn of sourceDeploy.available_functions) {
    if (!current.available_functions.some(other => other.n === fn.n && other.d === fn.d)) throw new Error(`Production function no longer matches source revision: ${fn.n}`);
  }
  const tracked = execFileSync('git', ['ls-tree', '-r', '--name-only', commit, 'netlify-auth-site'], { cwd: root, encoding: 'utf8' }).trim().split('\n');
  for (const name of tracked) {
    const expected = execFileSync('git', ['show', `${commit}:${name}`], { cwd: root });
    const actual = await fs.readFile(path.join(stage, name));
    const identicalText = !expected.includes(0) && Buffer.from(expected.toString('utf8')).equals(expected) &&
      Buffer.from(actual.toString('utf8')).equals(actual) &&
      expected.toString('utf8').replace(/\r\n/g, '\n') === actual.toString('utf8').replace(/\r\n/g, '\n');
    if (!expected.equals(actual) && !identicalText) throw new Error(`Staged source differs from deployed revision: ${name}`);
  }
  const cliRequire = createRequire(path.join(process.env.APPDATA, 'npm/node_modules/netlify-cli/package.json'));
  const { zipFunction } = await import(pathToFileURL(cliRequire.resolve('@netlify/zip-it-and-ship-it')).href);
  const bundleDir = path.join(stage, 'bundles');
  await fs.mkdir(bundleDir, { recursive: true });
  const sourceFunctions = path.join(stage, 'netlify-auth-site/netlify/functions');
  const entryNames = await fs.readdir(sourceFunctions);
  for (const fn of current.available_functions) {
    let bytes;
    if (['partner-api', 'partner-reconcile'].includes(fn.n)) {
      bytes = await fs.readFile(path.join(root, 'artifacts/partner-restore-ByO9al', `${fn.n}.zip`));
      if (crypto.createHash('sha256').update(bytes).digest('hex') !== fn.d) throw new Error(`Partner bundle does not match production: ${fn.n}`);
    } else {
      const entry = entryNames.find(name => name === `${fn.n}.js` || name === `${fn.n}.mjs` || name === `${fn.n}.ts`);
      if (!entry) throw new Error(`Missing deployed function source: ${fn.n}`);
      const result = await zipFunction(path.join(sourceFunctions, entry), bundleDir, {
        basePath: path.join(stage, 'netlify-auth-site'), repositoryRoot: stage,
        config: { '*': { nodeBundler: 'nft', nodeVersion: '22.x',
          ...(fn.im ? { includedFiles: ['partner-pilot-deployment.json'] } : {}) } }
      });
      if (!result?.path) throw new Error(`Packaging failed: ${fn.n}`);
      bytes = await fs.readFile(result.path);
      if (result.runtimeAPIVersion) functions_config[fn.n].build_data = {
        ...(functions_config[fn.n].build_data || {}), runtimeAPIVersion: result.runtimeAPIVersion,
        ...(result.bootstrapVersion ? { bootstrapVersion: result.bootstrapVersion } : {})
      };
    }
    const digest = crypto.createHash('sha256').update(bytes).digest('hex');
    functions[fn.n] = digest;
    bundles.set(digest, { name: fn.n, bytes, runtime: fn.r || 'nodejs22.x', invocationMode: fn.im });
  }
}
const plan = { previousDeploy: current.id, preservedFiles: currentFiles.length, preservedFunctions: current.available_functions.length, pilotAllowlisted: true };
console.log(JSON.stringify(plan));
if (process.argv[3] !== '--confirm-production') process.exit(0);
if ((await api(`/sites/${siteId}`)).published_deploy.id !== current.id) throw new Error('Production changed; retry from the current deployment');
let deploy = await api(`/sites/${siteId}/deploys`, 'POST', {
  title: 'Refresh private Partner pilot access; preserve production code',
  draft: false, branch: 'main', files, functions, functions_config,
  function_schedules: current.function_schedules || []
});
console.log(JSON.stringify({ deployId: deploy.id, state: deploy.state }));
for (let attempt = 0; attempt < 30 && ['new', 'preparing'].includes(deploy.state); attempt++) {
  await new Promise(resolve => setTimeout(resolve, 1000));
  deploy = await api(`/deploys/${deploy.id}`);
}
if (deploy.required?.length || (deploy.required_functions || []).some(hash => !bundles.has(hash))) throw new Error('Cached bundles unavailable; deployment is incomplete and production has not been replaced');
for (const hash of deploy.required_functions || []) {
  const fn = bundles.get(hash);
  const params = new URLSearchParams({ runtime: fn.runtime });
  if (fn.invocationMode) params.set('invocation_mode', fn.invocationMode);
  const response = await fetch(`https://api.netlify.com/api/v1/deploys/${deploy.id}/functions/${fn.name}?${params}`, {
    method: 'PUT', headers: { Authorization: `Bearer ${token}`, 'content-type': 'application/octet-stream' },
    body: fn.bytes, signal: AbortSignal.timeout(120000)
  });
  if (!response.ok) throw new Error(`Function upload failed: ${fn.name}: ${response.status}`);
}
for (let attempt = 0; attempt < 40 && !['ready', 'error'].includes(deploy.state); attempt++) {
  await new Promise(resolve => setTimeout(resolve, 3000));
  deploy = await api(`/deploys/${deploy.id}`);
}
if (deploy.state !== 'ready') throw new Error(`Deployment state: ${deploy.state}`);
if ((await api(`/sites/${siteId}`)).published_deploy.id !== deploy.id) throw new Error('New deployment was not published');
const afterFiles = await api(`/deploys/${deploy.id}/files`);
if (afterFiles.length !== currentFiles.length || currentFiles.some(file => !afterFiles.some(after => after.path === file.path && after.sha === file.sha))) throw new Error('Static files changed unexpectedly');
if (deploy.available_functions.length !== current.available_functions.length ||
    current.available_functions.some(fn => !deploy.available_functions.some(after => after.n === fn.n && after.d === functions[fn.n]))) throw new Error('Function bundles changed unexpectedly');
if (current.available_functions.some(fn => fn.im && !deploy.available_functions.some(after => after.n === fn.n && after.im === fn.im))) throw new Error('Function invocation mode was not preserved');
console.log(JSON.stringify({ ...plan, deployId: deploy.id, published: true, staticHashesPreserved: true, partnerHashesPreserved: true, rebuiltFromDeployedRevision: Boolean(stageArgument) }));
