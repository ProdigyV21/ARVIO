const assert = require('node:assert/strict');
const test = require('node:test');
const { handleTraktProxy } = require('../netlify/functions/_backend');

test('upstream HTML denial is a retryable outage, not an invalid account or empty list', async () => {
  const previous = { fetch: global.fetch, key: process.env.APP_ANON_KEY, id: process.env.TRAKT_CLIENT_ID, secret: process.env.TRAKT_CLIENT_SECRET };
  process.env.APP_ANON_KEY = 'test-only-app-key';
  process.env.TRAKT_CLIENT_ID = 'test-only-client';
  process.env.TRAKT_CLIENT_SECRET = 'test-only-secret';
  const request = { httpMethod: 'GET', headers: { apikey: 'test-only-app-key' }, queryStringParameters: { path: '/sync/watchlist' } };
  try {
    global.fetch = async () => new Response('<!doctype html><html><title>Attention Required</title></html>', { status: 403, headers: { 'content-type': 'text/html' } });
    const blocked = await handleTraktProxy(request);
    assert.equal(blocked.statusCode, 503);
    assert.equal(JSON.parse(blocked.body).error, 'trakt_upstream_blocked');
    global.fetch = async () => new Response('{"error":"invalid_token"}', { status: 401, headers: { 'content-type': 'application/json' } });
    assert.equal((await handleTraktProxy(request)).statusCode, 401);
    global.fetch = async () => new Response('[]', { status: 200, headers: { 'content-type': 'application/json' } });
    const healthy = await handleTraktProxy(request);
    assert.equal(healthy.statusCode, 200);
    assert.equal(healthy.body, '[]');
  } finally {
    global.fetch = previous.fetch;
    for (const [name, value] of [['APP_ANON_KEY', previous.key], ['TRAKT_CLIENT_ID', previous.id], ['TRAKT_CLIENT_SECRET', previous.secret]]) {
      if (value === undefined) delete process.env[name]; else process.env[name] = value;
    }
  }
});
