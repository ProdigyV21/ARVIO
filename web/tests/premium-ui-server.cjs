// Run locally after installing web and netlify-auth-site dependencies.
// npm's production build never includes this fixture server.
const { build } = require('../../netlify-auth-site/node_modules/esbuild');
const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const root = path.resolve(__dirname, '..');
(async () => {
  const result = await build({ entryPoints: [path.join(__dirname, 'fixtures/premium-ui.tsx')], bundle: true, write: false, outdir: '/fixture', jsx: 'automatic',
    define: { 'process.env': '{}', 'process.env.NODE_ENV': '"development"', 'process.env.NEXT_PUBLIC_PAYWALL_ENABLED': '"true"', 'process.env.NEXT_PUBLIC_KOFI_URL': '"https://ko-fi.com/arvio/tiers"' },
    plugins: [{ name: 'test-only-transport', setup(builder) {
      builder.onResolve({ filter: /^@\// }, args => ({ path: args.path === '@/lib/store' ? path.join(__dirname, 'fixtures/premium-store.ts') : path.join(root, args.path.slice(2) + (args.path.endsWith('Paywall') ? '.tsx' : '.ts')) }));
      builder.onResolve({ filter: /(^|\/)http$/ }, args => ({ path: path.join(__dirname, 'fixtures/premium-http.ts') }));
    } }]
  });
  const js = result.outputFiles.find(file => file.path.endsWith('.js')).contents;
  const css = result.outputFiles.find(file => file.path.endsWith('.css')).contents;
  http.createServer((req, res) => {
    const url = new URL(req.url, 'http://localhost');
    res.setHeader('Content-Security-Policy', "default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; connect-src 'none'");
    if (url.pathname === '/bundle.js' || url.pathname === '/bundle.css') {
      res.setHeader('content-type', url.pathname.endsWith('js') ? 'text/javascript' : 'text/css');
      res.end(url.pathname.endsWith('js') ? js : css); return;
    }
    if (['/arvio-logo.svg', '/arvio-wordmark.svg'].includes(url.pathname)) {
      res.setHeader('content-type', 'image/svg+xml'); res.end(fs.readFileSync(path.join(root, 'public', url.pathname))); return;
    }
    res.setHeader('content-type', 'text/html');
    res.end('<!doctype html><html><head><meta name="viewport" content="width=device-width,initial-scale=1"><title>Premium flow test</title><link rel="stylesheet" href="/bundle.css"></head><body><div id="root"></div><script src="/bundle.js"></script></body></html>');
  }).listen(3098, '127.0.0.1', () => console.log('Premium UI fixture: http://127.0.0.1:3098 (trial), /?case=paywall, /?case=error'));
})().catch(error => { console.error(error); process.exit(1); });
