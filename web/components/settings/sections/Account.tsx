"use client";
import { CheckCircle2, ExternalLink, LogOut, RefreshCw, User, UserCircle } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { weTrakrClient, type WeTrakrCode } from "@/lib/wetrakr";
import { useTranslation } from "@/lib/i18n";
import { config, getAuthPortalUrl, hasNetlifyBackendConfig, hasSimklConfig, hasSupabaseConfig, hasTraktConfig } from "@/lib/config";
import { useApp, useAppPick } from "@/lib/store";
import { PremiumAccount } from "@/components/shell/PremiumAccount";

// One translatable sentence with the link in the middle, so every language can
// put the link where its word order needs it.
const LINK_SLOT = "\u0000link\u0000";
function splitAroundLink(text: string): string[] {
  return text.split(/(\u0000link\u0000)/).filter(Boolean);
}
import { Empty, Group, Row, Select, SectionHeader, Status, Toggle } from "../ui";

function cloudConfigured() {
  return hasNetlifyBackendConfig() || hasSupabaseConfig();
}

function buildStamp() {
  const stamp = process.env.NEXT_PUBLIC_BUILD_STAMP;
  if (!stamp) return "";
  return new Date(Number(stamp)).toLocaleString("en-GB", { day: "2-digit", month: "short", hour: "2-digit", minute: "2-digit" });
}

export function AccountSection() {
  const translateUi = useTranslation();
  const { auth, signOut, refreshData, settingsSyncState, activeProfile } = useAppPick("auth", "signOut", "refreshData", "settingsSyncState", "activeProfile");
  const [syncBusy, setSyncBusy] = useState(false);
  const ready = cloudConfigured();
  const signIn = () => {
    const redirectUri = window.location.origin + "/";
    window.location.href = `${getAuthPortalUrl()}?redirect_uri=${encodeURIComponent(redirectUri)}`;
  };
  const syncNow = async () => {
    setSyncBusy(true);
    try { await refreshData(); } finally { setSyncBusy(false); }
  };
  const stamp = buildStamp();
  return (
    <>
      <SectionHeader
        title={config.selfHosted ? "Local account" : "ARVIO account"}
        description={config.selfHosted
          ? "This copy of ARVIO Web keeps profiles and settings in this browser."
          : "Your ARVIO account keeps profiles, settings, addons and progress in sync between ARVIO Web and the Android and TV app."}
      />
      <Group>
        {auth ? (
          <div className="st-account">
            <span className="st-account-avatar"><UserCircle size={26} /></span>
            <div className="st-account-text">
              <strong>{auth.email}</strong>
              <span>{translateUi("Signed in to ARVIO Cloud")}{activeProfile ? ` · ${translateUi("Profile")}: ${activeProfile.name}` : ""}</span>
            </div>
            <button type="button" className="st-btn" onClick={signOut}><LogOut size={16} /> {translateUi("Sign out")}</button>
          </div>
        ) : config.selfHosted ? (
          <Empty>{translateUi("Profiles and settings are saved in this browser. ARVIO Cloud is not connected.")}</Empty>
        ) : (
          <div className="st-account">
            <span className="st-account-avatar"><User size={24} /></span>
            <div className="st-account-text">
              <strong>{translateUi("Not signed in")}</strong>
              <span>{translateUi("Sign in with the same account as your Android or TV app to sync your settings.")}</span>
            </div>
            <button type="button" className="st-btn is-primary" disabled={!ready} onClick={signIn}>{translateUi("Sign in")}</button>
          </div>
        )}
      </Group>

      {auth && !config.selfHosted && (
        <Group
          title="Sync with your Android and TV app"
          description="Settings marked “All devices” or “Android & TV” are saved to your account as you change them. The app picks them up the next time it opens or comes back to the foreground, or straight away from Settings → Sync on the TV."
          footer={<>
            <button type="button" className="st-btn" disabled={syncBusy} onClick={() => void syncNow()}>
              <RefreshCw size={16} className={syncBusy ? "spin" : undefined} /> {translateUi(syncBusy ? "Syncing..." : "Sync now")}
            </button>
            <span className="st-row-hint">{translateUi("Pulls the latest changes from your other devices.")}</span>
          </>}
        >
          <Row label="Status">
            {settingsSyncState === "saved" ? <Status tone="ok">{translateUi("Up to date")}</Status>
              : settingsSyncState === "error" ? <Status tone="warn">{translateUi("Couldn't save — retrying")}</Status>
              : settingsSyncState === "pending" ? <Status tone="busy">{translateUi("Saving…")}</Status>
              : <Status tone="off">{translateUi("Local only")}</Status>}
          </Row>
          <Row label="Use the same profile" hint="Most settings belong to a profile. Pick the same profile here as on your TV to change its settings." />
        </Group>
      )}

      {!ready && !config.selfHosted && (
        <Group><Empty>{translateUi("ARVIO Cloud backend env is missing. Add backend values in web/.env.local.")}</Empty></Group>
      )}

      <Group title="This version">
        <Row label="ARVIO Web" hint="Updates itself when a new version is released.">
          <span className="st-row-hint">{stamp ? translateUi("Built {value0}", { value0: stamp }) : translateUi("unknown")}</span>
        </Row>
      </Group>
    </>
  );
}

export function PremiumSection() {
  const translateUi = useTranslation();
  const { auth } = useAppPick("auth");
  return (
    <>
      <SectionHeader title="ARVIO Web Premium" description="Hosted ARVIO Web on iPhone, iPad, Mac and PC. The Android and TV app stays free." />
      {auth ? <PremiumAccount /> : <Group><Empty>{translateUi("Sign in to see or start your membership.")}</Empty></Group>}
    </>
  );
}

export function ProfilesSection() {
  const translateUi = useTranslation();
  const app = useApp();
  const { settings, profiles, activeProfile } = app;
  return (
    <>
      <SectionHeader title="Profiles" description="Each profile has its own watch progress, home rows and most settings. Changes here apply to the profile you're using." />
      <Group>
        <Row id="active-profile" label="Current profile" hint={activeProfile ? activeProfile.name : undefined}>
          <button type="button" className="st-btn" onClick={app.switchProfile}><User size={16} /> {translateUi("Manage profiles")}</button>
        </Row>
        <Row id="skip-profile-selection" label="Skip profile selection on launch" hint="Open straight into the last profile used." scope="all">
          <Toggle label="Skip profile selection on launch" value={settings.skipProfileSelection} onChange={(v) => app.updateSettings({ skipProfileSelection: v })} />
        </Row>
      </Group>
      {profiles.length > 0 && (
        <Group title="On this account">
          <ul className="st-list">
            {profiles.map((profile) => (
              <li className="st-item" key={profile.id}>
                <div className="st-item-main">
                  <span className="st-item-title"><span>{profile.name}</span>{profile.id === activeProfile?.id && <span className="st-tag">{translateUi("In use")}</span>}</span>
                </div>
              </li>
            ))}
          </ul>
        </Group>
      )}
    </>
  );
}

export function ServicesSection() {
  const translateUi = useTranslation();
  const {
    traktConnected, mdblistConnected, simklConnected, trackingPreferences, updateTrackingPreferences,
    deviceCode, simklDeviceCode, beginTrakt, pollTrakt, disconnectTrakt, connectMdblist, disconnectMdblist,
    beginSimkl, pollSimkl, disconnectSimkl,
  } = useAppPick("traktConnected", "mdblistConnected", "simklConnected", "trackingPreferences", "updateTrackingPreferences", "deviceCode", "simklDeviceCode", "beginTrakt", "pollTrakt", "disconnectTrakt", "connectMdblist", "disconnectMdblist", "beginSimkl", "pollSimkl", "disconnectSimkl");
  const { activeProfile, refreshData } = useAppPick("activeProfile", "refreshData");
  const [weTrakrConnected, setWeTrakrConnected] = useState(weTrakrClient.isConnected);
  const [weTrakrBusy, setWeTrakrBusy] = useState(false);
  const [weTrakrError, setWeTrakrError] = useState<string | null>(null);
  const [weTrakrCode, setWeTrakrCode] = useState<WeTrakrCode | null>(null);
  const weTrakrGeneration = useRef(0);
  useEffect(() => {
    const generation = ++weTrakrGeneration.current;
    weTrakrClient.setProfile(activeProfile?.id || null);
    setWeTrakrCode(null); setWeTrakrBusy(false); setWeTrakrError(null); setWeTrakrConnected(false);
    if (activeProfile) void weTrakrClient.restoreSession().then(() => {
      if (generation === weTrakrGeneration.current) setWeTrakrConnected(weTrakrClient.isConnected);
    }).catch(() => undefined);
    return () => { weTrakrGeneration.current++; };
  }, [activeProfile?.id]);
  useEffect(() => {
    if (!weTrakrCode) return;
    let cancelled = false;
    let timer: ReturnType<typeof setTimeout>;
    const poll = async () => {
      try {
        if (await weTrakrClient.poll(weTrakrCode)) {
          if (cancelled) return;
          setWeTrakrConnected(true); setWeTrakrCode(null);
          const mode = (value: typeof trackingPreferences.watchlistReadMode) => value === "auto" ? "wetrakr" as const : value;
          await updateTrackingPreferences({ writeToWeTrakr: true, watchlistReadMode: mode(trackingPreferences.watchlistReadMode),
            continueWatchingReadMode: mode(trackingPreferences.continueWatchingReadMode), watchedReadMode: mode(trackingPreferences.watchedReadMode) });
          if (!cancelled) await refreshData();
          return;
        }
      } catch (error) {
        if (!cancelled) { setWeTrakrError(error instanceof Error ? error.message : "WeTrakr unavailable"); setWeTrakrCode(null); }
        return;
      }
      if (!cancelled) timer = setTimeout(poll, weTrakrCode.interval * 1000);
    };
    timer = setTimeout(poll, weTrakrCode.interval * 1000);
    return () => { cancelled = true; clearTimeout(timer); };
  }, [weTrakrCode]);
  const changeWeTrakr = async () => {
    const generation = weTrakrGeneration.current;
    setWeTrakrBusy(true); setWeTrakrError(null);
    try {
      if (weTrakrClient.isConnected || weTrakrCode) {
        await weTrakrClient.disconnect();
        if (generation !== weTrakrGeneration.current) return;
        setWeTrakrConnected(false); setWeTrakrCode(null);
        const mode = (value: typeof trackingPreferences.watchlistReadMode) => value === "wetrakr" ? "auto" as const : value;
        await updateTrackingPreferences({ writeToWeTrakr: false, watchlistReadMode: mode(trackingPreferences.watchlistReadMode),
          continueWatchingReadMode: mode(trackingPreferences.continueWatchingReadMode), watchedReadMode: mode(trackingPreferences.watchedReadMode) });
      } else {
        const code = await weTrakrClient.begin();
        if (generation === weTrakrGeneration.current) setWeTrakrCode(code);
      }
    } catch (error) { if (generation === weTrakrGeneration.current) setWeTrakrError(error instanceof Error ? error.message : "WeTrakr unavailable"); }
    finally { if (generation === weTrakrGeneration.current) setWeTrakrBusy(false); }
  };
  const [traktError, setTraktError] = useState<string | null>(null);
  const [traktBusy, setTraktBusy] = useState<"start" | "poll" | null>(null);
  const [simklError, setSimklError] = useState<string | null>(null);
  const [simklBusy, setSimklBusy] = useState<"start" | "poll" | null>(null);
  const [mdblistKey, setMdblistKey] = useState("");
  const [mdblistError, setMdblistError] = useState<string | null>(null);
  const [mdblistBusy, setMdblistBusy] = useState(false);

  const run = async (
    busy: (v: "start" | "poll" | null) => void,
    error: (v: string | null) => void,
    phase: "start" | "poll",
    task: () => Promise<unknown>,
    fallback: string,
  ) => {
    busy(phase);
    error(null);
    try { await task(); } catch (e) { error(e instanceof Error ? e.message : fallback); } finally { busy(null); }
  };

  const routingOptions: Array<[typeof trackingPreferences.watchlistReadMode, string]> = [];
  if (traktConnected && simklConnected) routingOptions.push(["both", "Trakt + Simkl"]);
  if (traktConnected) routingOptions.push(["trakt", "Trakt"]);
  if (simklConnected) routingOptions.push(["simkl", "Simkl"]);
  if (weTrakrConnected) routingOptions.push(["wetrakr", "WeTrakr"]);
  if (mdblistConnected) routingOptions.push(["mdblist", "MDBList"]);

  return (
    <>
      <SectionHeader title="Connected services" description="Connect your tracking services for watchlists, watched history and playback progress. WeTrakr connects separately in each browser or device." />

      <Group title="Trakt" description="Scrobbles what you watch and fills your watchlist and Continue Watching.">
        {traktError && <p className="st-error">{translateUi(traktError)}</p>}
        <Row label="Status" scope="all">
          {traktConnected ? <Status tone="ok">{translateUi("Connected")}</Status> : hasTraktConfig() ? <Status tone="off">{translateUi("Not linked")}</Status> : <Status tone="warn">{translateUi("Missing config")}</Status>}
        </Row>
        {deviceCode && !traktConnected && (
          <div className="st-code">
            <strong>{deviceCode.user_code}</strong>
            <p>{splitAroundLink(translateUi("Open {value0} and enter this code, then come back here.", { value0: LINK_SLOT })).map((part, index) => part === LINK_SLOT
              ? <a key={index} className="st-link" href={deviceCode.verification_url} target="_blank" rel="noreferrer">{deviceCode.verification_url}</a>
              : <span key={index}>{part}</span>)}</p>
            <button type="button" className="st-btn is-primary" disabled={traktBusy === "poll"} onClick={() => void run(setTraktBusy, setTraktError, "poll", pollTrakt, "Trakt has not approved this device yet.")}>
              {traktBusy === "poll" ? translateUi("Checking...") : translateUi("I approved it")}
            </button>
          </div>
        )}
        <div className="st-group-foot">
          {traktConnected
            ? <button type="button" className="st-btn is-danger" onClick={disconnectTrakt}>{translateUi("Disconnect Trakt")}</button>
            : <button type="button" className="st-btn is-primary" disabled={traktBusy === "start" || !hasTraktConfig()} onClick={() => void run(setTraktBusy, setTraktError, "start", beginTrakt, "Could not start Trakt device link.")}>
                {traktBusy === "start" ? translateUi("Starting...") : translateUi(deviceCode ? "Get a new code" : "Connect Trakt")}
              </button>}
        </div>
      </Group>

      <Group title="Simkl" description="Tracks movies, shows and anime, and can fill your watchlist.">
        {simklError && <p className="st-error">{translateUi(simklError)}</p>}
        <Row label="Status" scope="all">
          {simklConnected ? <Status tone="ok">{translateUi("Connected")}</Status> : hasSimklConfig() ? <Status tone="off">{translateUi("Not linked")}</Status> : <Status tone="warn">{translateUi("Missing config")}</Status>}
        </Row>
        {simklDeviceCode && !simklConnected && (
          <div className="st-code">
            <strong>{simklDeviceCode.user_code}</strong>
            <p>{translateUi("Open")}{" "}<a className="st-link" href={simklDeviceCode.verification_url || "https://simkl.com/pin"} target="_blank" rel="noreferrer">{simklDeviceCode.verification_url || "https://simkl.com/pin"}</a>{" "}{translateUi("and enter the code above")}</p>
            <button type="button" className="st-btn is-primary" disabled={simklBusy === "poll"} onClick={() => void run(setSimklBusy, setSimklError, "poll", pollSimkl, "Simkl has not approved this device yet.")}>
              {simklBusy === "poll" ? translateUi("Checking...") : translateUi("I approved it")}
            </button>
          </div>
        )}
        <div className="st-group-foot">
          {simklConnected
            ? <button type="button" className="st-btn is-danger" onClick={disconnectSimkl}>{translateUi("Disconnect Simkl")}</button>
            : <button type="button" className="st-btn is-primary" disabled={simklBusy === "start" || !hasSimklConfig()} onClick={() => void run(setSimklBusy, setSimklError, "start", beginSimkl, "Could not start Simkl device link.")}>
                {simklBusy === "start" ? translateUi("Starting...") : translateUi(simklDeviceCode ? "Get a new code" : "Connect Simkl")}
              </button>}
        </div>
      </Group>

      <Group title="WeTrakr" description="Watchlists, watched history and playback progress. This connection belongs to this browser and profile.">
        {weTrakrError && <p className="st-error" role="alert">{translateUi(weTrakrError)}</p>}
        <Row label="Status" scope="browser"><Status tone={weTrakrConnected ? "ok" : "off"}>{translateUi(weTrakrConnected ? "Connected" : "Not linked")}</Status></Row>
        {weTrakrCode && <div className="st-code"><strong>{weTrakrCode.user_code}</strong>
          <p>{translateUi("Open")} <a className="st-link" href={weTrakrCode.verification_url} target="_blank" rel="noreferrer">wetrakr.com/activate</a> {translateUi("and enter the code above")}</p>
          <p aria-live="polite">{translateUi("Waiting for approval...")}</p></div>}
        <div className="st-group-foot"><button type="button" className={`st-btn ${weTrakrConnected ? "is-danger" : "is-primary"}`} disabled={weTrakrBusy} onClick={() => void changeWeTrakr()}>
          {translateUi(weTrakrBusy ? "Please wait..." : weTrakrConnected ? "Disconnect WeTrakr" : weTrakrCode ? "Cancel" : "Connect WeTrakr")}</button></div>
      </Group>

      <Group title="MDBList" description={<>{translateUi("Lists and ratings from MDBList. Get your API key from")} <a className="st-link" href="https://mdblist.com/preferences/" target="_blank" rel="noreferrer">mdblist.com/preferences <ExternalLink size={12} /></a></>}>
        {mdblistError && <p className="st-error">{translateUi(mdblistError)}</p>}
        <Row label="Status" scope="all">
          {mdblistConnected ? <Status tone="ok">{translateUi("Connected")}</Status> : <Status tone="off">{translateUi("Not linked")}</Status>}
        </Row>
        {mdblistConnected ? (
          <div className="st-group-foot"><button type="button" className="st-btn is-danger" onClick={disconnectMdblist}>{translateUi("Disconnect MDBList")}</button></div>
        ) : (
          <div className="st-form">
            <input className="st-input" type="password" placeholder={translateUi("MDBList API key")} aria-label={translateUi("MDBList API key")} value={mdblistKey} onChange={(e) => setMdblistKey(e.target.value)} autoComplete="new-password" data-1p-ignore="" data-lpignore="true" />
            <div className="st-form-actions">
              <button type="button" className="st-btn is-primary" disabled={mdblistBusy || !mdblistKey.trim()} onClick={async () => {
                setMdblistBusy(true); setMdblistError(null);
                try { await connectMdblist(mdblistKey); setMdblistKey(""); }
                catch (error) { setMdblistError(error instanceof Error ? error.message : "Could not connect MDBList."); }
                finally { setMdblistBusy(false); }
              }}>{mdblistBusy ? translateUi("Connecting...") : translateUi("Connect")}</button>
            </div>
          </div>
        )}
      </Group>

      {routingOptions.length > 0 && (
        <Group title="Tracking behavior" description="Choose which connected service fills each part of ARVIO.">
          <Row id="watchlist-source" label="Watchlist source" scope="all">
            <Select label="Watchlist source" value={trackingPreferences.watchlistReadMode} options={routingOptions} onChange={(watchlistReadMode) => void updateTrackingPreferences({ watchlistReadMode })} />
          </Row>
          <Row id="continue-watching-source" label="Continue Watching source" hint="Choose one service or merge progress from both." scope="all">
            <Select label="Continue Watching source" value={trackingPreferences.continueWatchingReadMode} options={routingOptions} onChange={(continueWatchingReadMode) => void updateTrackingPreferences({ continueWatchingReadMode })} />
          </Row>
          <Row id="watched-source" label="Watched history source" hint="Watched badges merge safely when both is selected." scope="all">
            <Select label="Watched history source" value={trackingPreferences.watchedReadMode} options={routingOptions} onChange={(watchedReadMode) => void updateTrackingPreferences({ watchedReadMode })} />
          </Row>
          {traktConnected && (
            <Row id="write-trakt" label="Update Trakt while watching" scope="all">
              <Toggle label="Update Trakt while watching" value={trackingPreferences.writeToTrakt} onChange={(writeToTrakt) => void updateTrackingPreferences({ writeToTrakt })} />
            </Row>
          )}
          {weTrakrConnected && <Row id="write-wetrakr" label="Update WeTrakr while watching" scope="all"><Toggle label="Update WeTrakr while watching" value={trackingPreferences.writeToWeTrakr === true} onChange={(writeToWeTrakr) => void updateTrackingPreferences({ writeToWeTrakr })} /></Row>}
          {simklConnected && (
            <Row id="write-simkl" label="Update Simkl while watching" scope="all">
              <Toggle label="Update Simkl while watching" value={trackingPreferences.writeToSimkl} onChange={(writeToSimkl) => void updateTrackingPreferences({ writeToSimkl })} />
            </Row>
          )}
        </Group>
      )}
      {!traktConnected && !simklConnected && !mdblistConnected && !weTrakrConnected && (
        <p className="st-row-hint" style={{ display: "flex", gap: 6, alignItems: "center" }}><CheckCircle2 size={14} /> {translateUi("Linked services on your TV show up here automatically.")}</p>
      )}
    </>
  );
}
