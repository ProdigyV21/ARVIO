"use client";
import { LogOut, Send } from "lucide-react";
import { useEffect, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { config } from "@/lib/config";
import { useAppPick } from "@/lib/store";
import { Empty, Group, Row, SectionHeader, Status, Toggle } from "../ui";

// Lazily-loaded shape of @/lib/telegram. GramJS is a large browser-only bundle,
// so the module (and its dynamic gramjs import) only load when this section is
// first opened.
type TgModule = typeof import("@/lib/telegram");
type TgAuthState = import("@/lib/telegram").TgAuthState;

export function TelegramSection() {
  const translateUi = useTranslation();
  const { settings, updateSettings, setToast } = useAppPick("settings", "updateSettings", "setToast");
  const [mod, setMod] = useState<TgModule | null>(null);
  const [authState, setAuthState] = useState<TgAuthState>({ k: "idle" });
  const [qrDataUrl, setQrDataUrl] = useState<string | null>(null);
  const [phone, setPhone] = useState("+");
  const [code, setCode] = useState("");
  const [password, setPassword] = useState("");
  const [usePhone, setUsePhone] = useState(false);
  const [confirmDisconnect, setConfirmDisconnect] = useState(false);

  // Load the Telegram module and subscribe to its auth state.
  useEffect(() => {
    if (!config.telegramEnabled) return;
    let unsub: (() => void) | undefined;
    let active = true;
    void (async () => {
      try {
        const m = await import("@/lib/telegram");
        if (!active) return;
        setMod(m);
        unsub = m.subscribe(setAuthState);
      } catch {
        setToast("Telegram module failed to load in this browser.");
      }
    })();
    return () => {
      active = false;
      unsub?.();
    };
  }, [setToast]);

  // Render the QR login link to an image whenever it (re)appears.
  useEffect(() => {
    if (authState.k !== "waitQr") {
      setQrDataUrl(null);
      return;
    }
    const url = authState.url;
    let active = true;
    void (async () => {
      try {
        const QRCode = await import("qrcode");
        const dataUrl = await QRCode.toDataURL(url, { margin: 1, width: 320 });
        if (active) setQrDataUrl(dataUrl);
      } catch {
        /* Fall back to showing the raw link below. */
      }
    })();
    return () => {
      active = false;
    };
  }, [authState]);

  const header = (
    <SectionHeader title="Telegram" description="Stream video files from your Telegram chats and channels as sources. In ARVIO Web everything runs in your browser; nothing is sent to ARVIO servers." />
  );
  const tvOption = (
    <Group title="On your Android and TV app" description="Telegram sign-in is separate on each device. This option syncs.">
      <Row id="telegram-on-click" label="Only search Telegram when I select it" hint="Avoids Telegram's search limit. Turn off to search Telegram automatically with the other sources." scope="android">
        <Toggle label="Only search Telegram when I select it" value={settings.telegramSearchOnClickOnly} onChange={(v) => updateSettings({ telegramSearchOnClickOnly: v })} />
      </Row>
    </Group>
  );

  if (!mod) {
    return <>{header}<Group><Empty>{translateUi("Loading…")}</Empty></Group>{tvOption}</>;
  }

  if (!mod.isTelegramConfigured()) {
    return (
      <>
        {header}
        <Group><Empty>{translateUi("Telegram integration is not configured in this build. Please configure NEXT_PUBLIC_TELEGRAM_API_ID and NEXT_PUBLIC_TELEGRAM_API_HASH in your environment.")}</Empty></Group>
        {tvOption}
      </>
    );
  }

  const connect = () => {
    setUsePhone(false);
    void mod.startQrAuth();
  };
  const connectPhone = () => {
    const digits = phone.replace(/\D/g, "");
    if (!phone.startsWith("+") || digits.length < 7) {
      setToast("Enter a valid phone number in international format (e.g. +1 650 555 1234).");
      return;
    }
    void mod.startPhoneAuth(phone.trim());
  };

  return (
    <>
      {header}
      <Group title="In this browser">
        <Row label="Status" scope="browser">
          {authState.k === "ready" ? <Status tone="ok">{translateUi("Connected")}</Status>
            : authState.k === "error" ? <Status tone="warn">{translateUi("Connection failed")}</Status>
            : authState.k === "idle" ? <Status tone="off">{translateUi("Not connected")}</Status>
            : <Status tone="busy">{translateUi("Signing in…")}</Status>}
        </Row>

        {authState.k === "idle" && !usePhone && (
          <div className="st-group-foot">
            <button type="button" className="st-btn is-primary" onClick={connect}><Send size={16} /> {translateUi("Connect with QR")}</button>
            <button type="button" className="st-btn" onClick={() => setUsePhone(true)}>{translateUi("Use phone number instead")}</button>
          </div>
        )}

        {authState.k === "idle" && usePhone && (
          <div className="st-form">
            <input className="st-input" value={phone} onChange={(e) => setPhone(e.target.value)} placeholder={translateUi("+1 650 555 1234")} aria-label={translateUi("Phone number")} inputMode="tel" />
            <div className="st-form-actions">
              <button type="button" className="st-btn is-primary" onClick={connectPhone}>{translateUi("Send code")}</button>
              <button type="button" className="st-btn" onClick={() => setUsePhone(false)}>{translateUi("Back to QR")}</button>
            </div>
          </div>
        )}

        {authState.k === "initializing" && <Empty>{translateUi("Connecting to Telegram…")}</Empty>}

        {authState.k === "waitQr" && (
          <div className="st-code">
            {qrDataUrl
              // eslint-disable-next-line @next/next/no-img-element
              ? <img src={qrDataUrl} alt={translateUi("Telegram login QR code")} width={200} height={200} style={{ borderRadius: 8, background: "#fff" }} />
              : <p>{translateUi("Generating QR…")}</p>}
            <p>
              {translateUi("Open Telegram on your phone → Settings → Devices → Link Desktop Device, then scan:")}
              <br />{translateUi("The code refreshes automatically. Approving it on your phone signs you in here.")}
            </p>
            <button type="button" className="st-btn" onClick={() => { mod.resetToIdle(); setUsePhone(true); }}>{translateUi("Use phone number instead")}</button>
          </div>
        )}

        {authState.k === "waitCode" && (
          <div className="st-form">
            <input className="st-input" value={code} aria-label={translateUi("Login code")} placeholder={translateUi("Login code")} inputMode="numeric"
              onChange={(e) => setCode(e.target.value.replace(/\D/g, "").slice(0, authState.codeLength))} />
            <div className="st-form-actions">
              <button type="button" className="st-btn is-primary" onClick={() => { if (code) mod.submitCode(code); }}>{translateUi("Confirm")}</button>
            </div>
            <p className="st-row-hint" style={{ margin: 0 }}>{translateUi("Enter the ")}{authState.codeLength}{translateUi("-digit code Telegram just sent you.")}</p>
          </div>
        )}

        {authState.k === "waitPassword" && (
          <div className="st-form">
            <input className="st-input" value={password} type="password" autoComplete="new-password" data-1p-ignore="" data-lpignore="true" aria-label={translateUi("2FA password")} placeholder={translateUi("2FA password")} onChange={(e) => setPassword(e.target.value)} />
            <div className="st-form-actions">
              <button type="button" className="st-btn is-primary" onClick={() => { if (password) mod.submitPassword(password); setPassword(""); }}>{translateUi("Confirm")}</button>
            </div>
            <p className="st-row-hint" style={{ margin: 0 }}>
              {translateUi("Two-step verification is on. Enter your Telegram password")}{authState.hint ? translateUi(" (hint: {value0})", { value0: authState.hint }) : ""}.
            </p>
          </div>
        )}

        {authState.k === "ready" && (
          <>
            <Row label="Signed in as">{authState.firstName || translateUi("your account")}</Row>
            <div className="st-group-foot">
              {!confirmDisconnect ? (
                <button type="button" className="st-btn" onClick={() => setConfirmDisconnect(true)}><LogOut size={16} /> {translateUi("Disconnect")}</button>
              ) : (
                <>
                  <span className="st-row-hint">{translateUi("Disconnect and forget this session?")}</span>
                  <button type="button" className="st-btn is-danger" onClick={() => { setConfirmDisconnect(false); void mod.disconnect(); }}>{translateUi("Disconnect")}</button>
                  <button type="button" className="st-btn" onClick={() => setConfirmDisconnect(false)}>{translateUi("Cancel")}</button>
                </>
              )}
            </div>
          </>
        )}

        {authState.k === "error" && (
          <>
            <p className="st-error">{translateUi("Connection failed: ")}{authState.message}</p>
            <div className="st-group-foot"><button type="button" className="st-btn is-primary" onClick={connect}>{translateUi("Try again")}</button></div>
          </>
        )}
      </Group>
      {tvOption}
    </>
  );
}
