# Framer + Supabase Setup (arvio.tv)

This repo already uses Supabase for:
- Auth + cloud sync (Android TV app)
- TV device-code style sign-in via Edge Functions (`tv-auth-start`, `tv-auth-status`)

This document adds the missing piece: approving TV sign-in and editing settings from a Framer site hosted at `https://arvio.tv`.

## 1) Supabase Dashboard Settings

In Supabase Dashboard:
- Auth -> URL Configuration
  - Site URL: `https://arvio.tv`
  - Redirect URLs:
    - `https://arvio.tv/*`
    - `https://www.arvio.tv/*` (only if you use `www`)

## 2) Deploy Edge Functions

From `C:\\Users\\arvin\\ARVIO-APP`:

```powershell
# Link once (needs project ref and a Supabase access token)
npx supabase login
npx supabase link --project-ref <your-project-ref>

# Deploy updated/new functions
npx supabase functions deploy tv-auth-start
npx supabase functions deploy tv-auth-status
npx supabase functions deploy tv-auth-approve
```

Set the function secret so the TV QR points to your Framer site:

```powershell
npx supabase secrets set TV_AUTH_VERIFY_BASE_URL=https://arvio.tv/pair
```

Notes:
- `SUPABASE_URL`, `SUPABASE_ANON_KEY`, and `SUPABASE_SERVICE_ROLE_KEY` are available to Edge Functions in Supabase.
- `tv-auth-approve` requires the caller to be logged in (user JWT).

## 3) Push DB Migration (Optional)

This repo adds a simple `public.user_settings` table (RLS enabled) for web-editable preferences.

```powershell
npx supabase db push
```

## 4) Framer Pages

### A) `/pair` (approve TV sign-in)

Behavior:
1. User opens `https://arvio.tv/pair?code=ABCD-EFGH` from the TV QR code.
2. User signs up / signs in via Supabase Auth on the website.
3. Website calls `tv-auth-approve` with the code + the current session refresh token.
4. TV polls `tv-auth-status`, receives tokens, and signs in.

Minimal client logic:

```ts
import { createClient } from "@supabase/supabase-js"

const supabase = createClient(SUPABASE_URL, SUPABASE_ANON_KEY)

export async function approveTvCode(code: string) {
  const { data: { session } } = await supabase.auth.getSession()
  if (!session) throw new Error("Please sign in first.")

  const resp = await fetch(`${SUPABASE_URL}/functions/v1/tv-auth-approve`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      apikey: SUPABASE_ANON_KEY,
      Authorization: `Bearer ${session.access_token}`,
    },
    body: JSON.stringify({
      code,
      refresh_token: session.refresh_token,
    }),
  })

  const json = await resp.json().catch(() => ({}))
  if (!resp.ok) throw new Error(json.error ?? "Failed to approve TV sign-in")
  return true
}
```

### B) `/settings` (edit Arvio settings)

Current Android TV app reads/writes:
- `public.profiles` for simple flags (ex: `default_subtitle`, `auto_play_next`)
- `public.account_sync_state` for full snapshot backups/restores

For a web settings UI, you can start by editing `profiles` fields (simple toggles) and later expand into editing `account_sync_state.payload` (advanced).

## 5) Android TV App Changes (Already Included Here)

This repo now shows a QR pairing modal instead of forcing email/password entry on TV:
- `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsViewModel.kt`
- `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsScreen.kt`
- `app/src/main/kotlin/com/arflix/tv/ui/components/QrCodeImage.kt` (ZXing QR rendering)

The TV still supports the email/password fallback if needed.

