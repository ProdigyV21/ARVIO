// GET /stream-ticket-key → { keys: [public JWK, ...] }
//
// The public half of the stream-ticket signing key (see _stream-keys.js). The
// relay worker fetches it to verify ARVIO Premium stream tickets. It is
// public by design: it can verify tickets but never create them.
const { json, options } = require("./_backend");
const { streamKeysStore, publicKeys } = require("./_stream-keys");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "GET") return json(405, { error: "method_not_allowed" });
  try {
    const keys = await publicKeys(streamKeysStore(event));
    const response = json(200, { keys });
    response.headers = { ...(response.headers || {}), "Cache-Control": "public, max-age=300" };
    return response;
  } catch (error) {
    console.error("stream-ticket-key failed", error && error.message);
    return json(500, { error: "stream_ticket_key_failed" });
  }
};
