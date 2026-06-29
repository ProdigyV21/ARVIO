const { json, options } = require("./_backend");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;

  return json(200, {
    backend: "netlify",
    cloudSync: "netlify_account_snapshot",
    authSurface: "netlify_native_auth",
    tvAuthStorage: "netlify_blobs",
    migratedPasswordSetup: true,
    emailProviderConfigured: !!(
      process.env.RESEND_API_KEY ||
      process.env.POSTMARK_SERVER_TOKEN ||
      process.env.SENDGRID_API_KEY
    ),
    authSecretConfigured: !!process.env.ARVIO_AUTH_SECRET,
    proxies: {
      tmdb: true,
      trakt: true
    },
    supabaseFallback: false,
    mediaProxy: false,
    timestamp: new Date().toISOString()
  });
};
