# ARVIO Resolver Worker

Cloudflare Worker used by ARVIO Web for server-side addon stream discovery and subtitle normalization.

It is intentionally lightweight:

- Resolves Stremio-compatible addon `/stream/{type}/{id}.json` endpoints from Cloudflare instead of the browser.
- Streams source results back as NDJSON so the source picker can show fast addons first.
- Adds browser/iOS playability hints for HLS/MP4 versus Android-only containers/codecs.
- Converts remote SRT subtitles to WebVTT through `/subtitle`.
- Uses optional KV short-cache to reduce repeated addon calls.

This is not a full transcoder. It improves addon discovery, CORS, subtitle loading, and direct HLS/MP4 playback. MKV, unsupported audio, torrent `infoHash` streams without direct URLs, and encrypted/host-blocked streams still require an external player or a real transcoding service.

## Setup

1. Install dependencies:

   ```bash
   npm install
   ```

2. Review `wrangler.toml`. It already contains the Worker name, entrypoint, allowed origins, and timeout defaults.

3. Create a KV namespace:

   ```bash
   npx wrangler kv namespace create RESOLVER_KV
   ```

4. Put the returned namespace id into `wrangler.toml` by uncommenting the KV block:

   ```toml
   [[kv_namespaces]]
   binding = "RESOLVER_KV"
   id = "your_namespace_id"
   ```

5. Deploy:

   ```bash
   npm run deploy
   ```

6. Add a Cloudflare route or custom domain, for example:

   ```text
   resolve.arvio.tv/*
   ```

7. Set the web app environment variable:

   ```text
   NEXT_PUBLIC_ARVIO_RESOLVER_URL=https://resolve.arvio.tv
   ```

## Cloudflare Permissions Needed

Create a scoped Cloudflare API token instead of using a global API key:

- Account: Workers Scripts Edit
- Account: Workers KV Storage Edit
- Account: Account Settings Read
- Zone: DNS Edit, only if Codex should configure `resolve.arvio.tv`
- Zone: Zone Read

If an old global API key was pasted into chat or logs, rotate it in Cloudflare before deploying.

## Local Dev

```bash
npm run dev
```

Then set the web app to:

```text
NEXT_PUBLIC_ARVIO_RESOLVER_URL=http://localhost:8787
```

The web app falls back to direct browser addon lookups if the resolver is not configured or unavailable.

## Premium-only media relay (optional)

`/media` relays video for ARVIO Web. To serve it only to ARVIO Premium members, give the Worker the same secret as the auth site's `STREAM_TICKET_SECRET` (32+ random characters):

```bash
npx wrangler secret put STREAM_TICKET_SECRET
```

ARVIO Web then fetches a short-lived signed ticket from the auth site (`/stream-ticket`, Premium accounts only) and adds it to `/media` URLs as `t`; playlists rewritten by the Worker carry it to every segment. Without the secret the relay behaves as before. Deploy order: auth site and web app first, then set the secret on the Worker.
