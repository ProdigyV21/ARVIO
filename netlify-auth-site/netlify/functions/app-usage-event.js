const {
  json,
  options,
  parseBody,
  snapshotStores,
  sha256
} = require("./_backend");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") {
    return json(405, { error: "method_not_allowed" });
  }

  try {
    const stores = snapshotStores(event);
    const body = parseBody(event);
    const eventName = String(body.event_name || "app_open").slice(0, 80);
    const installId = String(body.install_id || "").slice(0, 160);
    if (!installId) return json(400, { error: "missing_install_id" });

    const email = String(body.email || "").trim().toLowerCase();
    const supabaseUserId = String(body.user_id || "").trim();
    const date = new Date().toISOString().slice(0, 10);
    const key = `${date}/${sha256(`${supabaseUserId}:${email}:${installId}:${eventName}`)}.json`;
    const existing = await stores.usage.get(key, { type: "json", consistency: "strong" }).catch(() => null);
    await stores.usage.setJSON(key, {
      date,
      eventName,
      installId,
      userId: supabaseUserId || null,
      email: email || null,
      profileId: body.profile_id || null,
      platform: body.platform || null,
      deviceType: body.device_type || null,
      appVersion: body.app_version || null,
      appVersionCode: body.app_version_code || null,
      distribution: body.distribution || null,
      metadata: body.metadata || {},
      count: Number(existing?.count || 0) + 1,
      updatedAt: new Date().toISOString()
    });

    return json(200, { ok: true });
  } catch (error) {
    console.error("app-usage-event failed", error);
    return json(500, { error: "usage_event_failed", message: error.message });
  }
};
