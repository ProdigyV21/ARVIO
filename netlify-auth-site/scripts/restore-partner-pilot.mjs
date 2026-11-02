import fs from 'node:fs/promises';
import path from 'node:path';
import crypto from 'node:crypto';
import { execFileSync } from 'node:child_process';
import { createRequire } from 'node:module';
import { fileURLToPath, pathToFileURL } from 'node:url';
import toml from '@iarna/toml';

// Restore only the private pilot, preserving current production function bundles.
const siteId = '17ccb668-bf3c-412d-9573-a28a09e52122';
const pilotId = '6ae07b4da15fb9c661b3bb32';
const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '../..');
const cfg = JSON.parse(await fs.readFile(path.join(process.env.APPDATA, 'netlify/Config/config.json'), 'utf8'));
const token = cfg.users[cfg.userId].auth.token;
const sha = bytes => crypto.createHash('sha1').update(bytes).digest('hex');
async function api(route, method = 'GET', body) {
  const r = await fetch(`https://api.netlify.com/api/v1${route}`, {
    method, headers: { Authorization: `Bearer ${token}`, 'content-type': 'application/json' },
    ...(body ? { body: JSON.stringify(body) } : {}), signal: AbortSignal.timeout(30000)
  });
  if (!r.ok) throw new Error(`Netlify ${method} ${route}: ${r.status}`);
  return r.json();
}
const site = await api(`/sites/${siteId}`);
if (site.name !== 'arvio-auth' || site.ssl_url !== 'https://auth.arvio.tv') throw new Error('Unexpected site');
const current = await api(`/deploys/${site.published_deploy.id}`);
const pilot = await api(`/deploys/${pilotId}`);
if (pilot.site_id !== siteId || pilot.state !== 'ready') throw new Error('Pilot bundle is unavailable');
const currentFiles = await api(`/deploys/${current.id}/files`);
const pilotFiles = await api(`/deploys/${pilot.id}/files`);
const files = Object.fromEntries(currentFiles.map(f => [f.path, f.sha]));
for (const file of pilotFiles.filter(f => /^\/(partner\/|partner-assets\/|arvio-icon-192\.png$)/.test(f.path))) files[file.path] = file.sha;
if (!files['/partner/index.html'] || !files['/partner-assets/app.js']) throw new Error('Missing pilot assets');

const uploads = new Map();
const raw = execFileSync('git', ['show', `${current.commit_ref}:netlify-auth-site/netlify.toml`], { cwd: root });
if (sha(raw) !== files['/netlify.toml']) throw new Error('Production routing does not match its Git revision; inspect before replacing');
const routing = toml.parse(raw.toString('utf8'));
routing.redirects = [...(routing.redirects || []),
  { from: '/partner', to: '/partner/index.html', status: 200 },
  { from: '/partner/*', to: '/partner/index.html', status: 200 }];
routing.headers = [...(routing.headers || []), { for: '/partner*', values: {
  'X-Robots-Tag': 'noindex, nofollow', 'Cache-Control': 'no-store',
  'Referrer-Policy': 'no-referrer', 'X-Content-Type-Options': 'nosniff',
  'Content-Security-Policy': "default-src 'none'; script-src 'self'; style-src 'self'; img-src 'self'; connect-src 'self' https://auth.arvio.tv; font-src 'self'; base-uri 'none'; frame-ancestors 'none'; form-action 'self'"
} }];
const routingBytes = Buffer.from(toml.stringify(routing));
files['/netlify.toml'] = sha(routingBytes);
uploads.set(files['/netlify.toml'], { name: '/netlify.toml', bytes: routingBytes });

const functions = Object.fromEntries(current.available_functions.map(f => [f.n, f.d]));
const cliRequire = createRequire(path.join(process.env.APPDATA, 'npm/node_modules/netlify-cli/package.json'));
const { zipFunction } = await import(pathToFileURL(cliRequire.resolve('@netlify/zip-it-and-ship-it')).href);
const bundleDir = await fs.mkdtemp(path.join(root, 'artifacts/partner-restore-'));
const functionUploads = new Map();
for (const name of ['partner-api', 'partner-reconcile']) {
  if (functions[name]) throw new Error(`${name} already exists; do not replace a newer implementation`);
  const fn = await zipFunction(path.join(root, 'netlify-auth-site/netlify/functions', `${name}.js`), bundleDir, {
    basePath: path.join(root, 'netlify-auth-site'), repositoryRoot: root,
    config: { '*': { nodeBundler: 'nft', nodeVersion: '22.x' } }
  });
  if (!fn?.path) throw new Error(`Could not package ${name}`);
  const bytes = await fs.readFile(fn.path);
  const digest = crypto.createHash('sha256').update(bytes).digest('hex');
  functions[name] = digest;
  functionUploads.set(digest, { name, bytes, runtime: fn.runtimeVersion || 'nodejs22.x' });
}
const functions_config = Object.fromEntries(current.available_functions.map(f => [f.n, {
  ...(f.bd ? { build_data: f.bd } : {}), ...(f.dn ? { display_name: f.dn } : {}),
  ...(f.g ? { generator: f.g } : {}), ...(f.p !== undefined ? { priority: f.p } : {})
}]));
const plan = { previousDeploy: current.id, preservedFunctions: current.available_functions.length,
  restoredFunctions: ['partner-api', 'partner-reconcile'], restoredAssets: 4, bundleDir };
console.log(JSON.stringify(plan));
if (process.argv[2] !== '--confirm-production') process.exit(0);
if ((await api(`/sites/${siteId}`)).published_deploy.id !== current.id) throw new Error('Production changed during preparation; retry from current state');
let deploy = await api(`/sites/${siteId}/deploys`, 'POST', {
  title: 'Restore private Partner pilot; preserve current production functions',
  draft: false, branch: 'main', files, functions, functions_config,
  function_schedules: current.function_schedules || []
});
console.log(JSON.stringify({ deployId: deploy.id, state: deploy.state }));
for (const hash of deploy.required_functions || []) {
  const fn = functionUploads.get(hash);
  if (!fn) throw new Error('An existing production function requires re-upload; refusing to replace it');
  const r = await fetch(`https://api.netlify.com/api/v1/deploys/${deploy.id}/functions/${fn.name}?runtime=${encodeURIComponent(fn.runtime)}`, {
    method: 'PUT', headers: { Authorization: `Bearer ${token}`, 'content-type': 'application/octet-stream' },
    body: fn.bytes, signal: AbortSignal.timeout(120000)
  });
  if (!r.ok) throw new Error(`Partner function upload failed: ${r.status}`);
}
for (const hash of deploy.required || []) {
  const file = uploads.get(hash);
  if (!file) throw new Error('An existing static asset requires re-upload; deployment is not complete');
  const r = await fetch(`https://api.netlify.com/api/v1/deploys/${deploy.id}/files${file.name}`, {
    method: 'PUT', headers: { Authorization: `Bearer ${token}`, 'content-type': 'application/octet-stream' },
    body: file.bytes, signal: AbortSignal.timeout(30000)
  });
  if (!r.ok) throw new Error(`Routing upload failed: ${r.status}`);
}
for (let attempts = 0; attempts < 40 && !['ready', 'error'].includes(deploy.state); attempts++) {
  await new Promise(resolve => setTimeout(resolve, 3000));
  deploy = await api(`/deploys/${deploy.id}`);
}
if (deploy.state !== 'ready') throw new Error(`Deployment state: ${deploy.state}`);
const after = await api(`/sites/${siteId}`);
if (after.published_deploy.id !== deploy.id) throw new Error('New deployment is not the published version');
for (const fn of current.available_functions) {
  if (!deploy.available_functions.some(f => f.n === fn.n && f.d === fn.d)) throw new Error(`Function changed unexpectedly: ${fn.n}`);
}
console.log(JSON.stringify({ ...plan, deployId: deploy.id, published: true, preservedFunctionHashesVerified: true }));
