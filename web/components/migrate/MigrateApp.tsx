"use client";

import { useCallback, useEffect, useState } from "react";
import { AlertTriangle, ArrowRightLeft, Check, Download, Loader2, LogIn, Plus, RefreshCw, ShieldCheck, Trash2 } from "lucide-react";
import { authClient } from "@/lib/store";
import { installAddon, normalizeAddons } from "@/lib/addons";
import { pullCloudPayload, pullCloudProfiles, saveCloudAddons } from "@/lib/cloud";
import { applyNuvioImport } from "@/lib/nuvioImportRunner";
import {
  defaultChoices, discoverNuvioBackend, fetchNuvioSnapshot, reconcileChoices, signInToNuvio, summarizePlan, OFFICIAL_NUVIO_URL,
  type MigrationChoice, type NuvioSnapshot, type ProfileImportSummary
} from "@/lib/nuvioMigration";
import { defaultSettings } from "@/lib/store";
import { LanguageProvider, useTranslation } from "@/lib/i18n";
import { CONTENT_LANGUAGE_OPTIONS } from "@/lib/i18n/languageOptions";
import { loadStored, saveStored } from "@/lib/storage";
import type { InstalledAddon, Profile } from "@/lib/types";

const LANGUAGE_KEY = "arvio.web.setupLanguage";

/**
 * Free setup page for ARVIO accounts, outside the web app's subscription: sign
 * in, install addons, and bring a Nuvio account over. Everything is written to
 * the ARVIO cloud account, which the Android and TV apps sync for free.
 */
export function MigrateApp() {
  // English by default; the choice is remembered on this device.
  const [language, setLanguage] = useState("en-US");
  // The language list is built with Intl.DisplayNames, which resolves names
  // from the host's locale data — the server's and the browser's disagree, so
  // the picker is rendered after mount instead of being hydrated.
  const [mounted, setMounted] = useState(false);
  useEffect(() => {
    setLanguage(loadStored<string>(LANGUAGE_KEY, "en-US"));
    setMounted(true);
  }, []);
  const chooseLanguage = (next: string) => { setLanguage(next); saveStored(LANGUAGE_KEY, next); };
  return <LanguageProvider language={language}>
    <SetupTools language={language} onLanguageChange={chooseLanguage} showLanguagePicker={mounted} />
  </LanguageProvider>;
}

function SetupTools({ language, onLanguageChange, showLanguagePicker }: {
  language: string;
  onLanguageChange: (value: string) => void;
  showLanguagePicker: boolean;
}) {
  // Named `text`, not `translateUi`, so this standalone page reuses the shared
  // dictionaries without enrolling its own wording in the translated corpus.
  const text = useTranslation();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [signedIn, setSignedIn] = useState(false);
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [notice, setNotice] = useState<string | null>(null);

  const [profiles, setProfiles] = useState<Profile[]>([]);
  const [accountReady, setAccountReady] = useState(false);
  const [accountLoading, setAccountLoading] = useState(false);
  const [profileId, setProfileId] = useState<string | null>(null);
  const [addons, setAddons] = useState<InstalledAddon[]>([]);
  const [addonUrl, setAddonUrl] = useState("");

  const [nuvioServer, setNuvioServer] = useState("");
  const [selfHosted, setSelfHosted] = useState(false);
  const [nuvioEmail, setNuvioEmail] = useState("");
  const [nuvioPassword, setNuvioPassword] = useState("");
  const [snapshot, setSnapshot] = useState<NuvioSnapshot | null>(null);
  const [choices, setChoices] = useState<MigrationChoice[]>([]);
  const [results, setResults] = useState<ProfileImportSummary[] | null>(null);
  const [progress, setProgress] = useState("");

  const loadAccount = useCallback(async (preferredProfileId?: string | null) => {
    setAccountReady(false);
    setAccountLoading(true);
    try {
      const cloud = await pullCloudProfiles(authClient);
      setProfiles(cloud.profiles);
      const nextId = preferredProfileId ?? cloud.activeProfileId ?? cloud.profiles[0]?.id ?? null;
      setProfileId(nextId);
      if (nextId) {
        const payload = await pullCloudPayload(authClient, nextId).catch(() => null);
        setAddons(normalizeAddons(payload?.addons ?? []));
      }
      setAccountReady(true);
    } finally {
      setAccountLoading(false);
    }
  }, []);

  useEffect(() => {
    if (!authClient.session) return;
    setSignedIn(true);
    setEmail(authClient.session.email);
    void loadAccount().catch(() => setError("Could not read your ARVIO account"));
  }, [loadAccount]);

  const run = async (work: () => Promise<void>) => {
    setBusy(true);
    setError(null);
    try {
      await work();
    } catch (failure) {
      setError(failure instanceof Error ? failure.message : "Something went wrong");
    } finally {
      setBusy(false);
      setProgress("");
    }
  };

  const signIn = () => run(async () => {
    await authClient.signIn(email.trim(), password);
    setPassword("");
    setSignedIn(true);
    await loadAccount();
  });

  const selectProfile = (id: string) => run(async () => {
    setProfileId(id);
    const payload = await pullCloudPayload(authClient, id).catch(() => null);
    setAddons(normalizeAddons(payload?.addons ?? []));
  });

  const addAddon = () => run(async () => {
    if (!profileId) throw new Error("Choose a profile first");
    const installed = await installAddon(addonUrl.trim());
    const next = normalizeAddons([...addons, installed]);
    await saveCloudAddons(authClient, next, profileId, { changes: undefined });
    setAddons(next);
    setAddonUrl("");
    setNotice(`Installed ${installed.name}`);
  });

  const removeAddon = (addon: InstalledAddon) => run(async () => {
    if (!profileId) return;
    const next = addons.filter(entry => entry.id !== addon.id);
    await saveCloudAddons(authClient, next, profileId, { removedIds: [addon.id] });
    setAddons(next);
  });

  /** Keeps a copy of what Nuvio returned, for people without an ARVIO account. */
  const downloadSnapshot = () => {
    if (!snapshot) return;
    const blob = new Blob([JSON.stringify(snapshot, null, 2)], { type: "application/json" });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = "nuvio-setup.json";
    link.click();
    URL.revokeObjectURL(url);
  };

  const connectNuvio = () => run(async () => {
    // The normal case is the public Nuvio cloud, which publishes its own
    // settings; only a self-hoster has to name a server.
    const discovery = await discoverNuvioBackend(selfHosted ? nuvioServer : OFFICIAL_NUVIO_URL);
    const session = await signInToNuvio(discovery, nuvioEmail, nuvioPassword);
    const pulled = await fetchNuvioSnapshot(discovery, session);
    setNuvioPassword("");
    setSnapshot(pulled);
    setChoices(defaultChoices(pulled, profiles));
    setResults(null);
  });

  // The Nuvio step runs before the ARVIO sign-in, so the first plan is drawn up
  // against an empty profile list and proposes creating everything. Redo it the
  // moment real profiles arrive — without touching a choice already made by hand.
  useEffect(() => {
    if (!snapshot) return;
    setChoices(current => reconcileChoices(snapshot, profiles, current));
  }, [snapshot, profiles]);

  const setTarget = (nuvioProfileId: number, value: string) => {
    setChoices(current => current.map(choice => choice.nuvioProfileId !== nuvioProfileId ? choice : {
      ...choice,
      userSet: true,
      target: value === "__skip" ? { kind: "skip" } : value === "__create" ? { kind: "create" } : { kind: "existing", profileId: value }
    }));
  };

  const runImport = () => run(async () => {
    if (!snapshot) return;
    if (!accountReady) throw new Error("Load your ARVIO account successfully before importing");
    const outcome = await applyNuvioImport({
      auth: authClient, profiles, snapshot, choices,
      baseSettings: defaultSettings, onProgress: setProgress
    });
    setResults(outcome.summaries);
    await loadAccount(profileId);
  });

  const planned = snapshot ? summarizePlan(snapshot, choices) : [];

  return (
    <main className="setup">
      <div className="setup__inner">
      <div className="setup__brand">
        <img src="/arvio-icon-192.png" alt="" width={40} height={40} />
        <span>ARVIO</span>
      </div>
      <header className="setup__header">
        <div className="setup__header-row">
          <h1>{text("ARVIO setup")}</h1>
          {showLanguagePicker && <label className="setup__language">
            <span>{text("App Language")}</span>
            <select value={language} onChange={event => onLanguageChange(event.target.value)}>
              {CONTENT_LANGUAGE_OPTIONS.map(([code, label]) => <option key={code} value={code}>{label}</option>)}
            </select>
          </label>}
        </div>
        <p>Install addons and bring your Nuvio setup over. Everything is saved to your ARVIO account,
          so the free Android and TV apps pick it up on their next sync — no web subscription needed.</p>
      </header>

      {/* Nuvio comes first: reading a Nuvio account needs no ARVIO account, so
          someone who has not signed up yet can still pull their setup out. */}
      <section className="setup__card">
        <h2>Move from Nuvio</h2>
        {!snapshot && <>
          <p className="setup__muted">Sign in with your Nuvio account to read its profiles, addons and collections.
            Nothing in Nuvio is changed, and you do not need an ARVIO account for this step.</p>
          <label><span>Nuvio email</span>
            <input type="email" value={nuvioEmail} onChange={event => setNuvioEmail(event.target.value)} autoComplete="off" /></label>
          <label><span>Nuvio password</span>
            <input type="password" value={nuvioPassword} onChange={event => setNuvioPassword(event.target.value)} autoComplete="off" />
            <small><ShieldCheck size={14} aria-hidden="true" /> Used once to read your setup. It is never stored.</small></label>
          {selfHosted
            ? <label><span>Nuvio server</span>
                <input value={nuvioServer} onChange={event => setNuvioServer(event.target.value)}
                  placeholder="nuvio.example.com" spellCheck={false} />
                <small>Only for a Nuvio you host yourself. Leave this off for the normal Nuvio account.</small>
              </label>
            : <button type="button" className="setup__link" onClick={() => setSelfHosted(true)}>
                I host Nuvio myself
              </button>}
          <button type="button" className="setup__primary"
            disabled={busy || !nuvioEmail.trim() || !nuvioPassword || (selfHosted && !nuvioServer.trim())}
            onClick={connectNuvio}>
            {busy ? <Loader2 size={18} className="spin" aria-hidden="true" /> : <LogIn size={18} aria-hidden="true" />} Connect to Nuvio
          </button>
        </>}

        {snapshot && snapshot.warnings.length > 0 && <>
          {snapshot.warnings.map(warning => <p key={warning} className="setup__warning" role="status">
            <AlertTriangle size={14} aria-hidden="true" /> {warning}
          </p>)}
          <button type="button" className="setup__ghost" disabled={busy} onClick={() => {
            setSnapshot(null);
            setResults(null);
            setChoices([]);
          }}>
            <RefreshCw size={18} aria-hidden="true" /> Reconnect to Nuvio
          </button>
        </>}

        {snapshot && !results && <>
          <p>Found {snapshot.profiles.length} profiles in {snapshot.email}.</p>
          <ul className="setup__list">
            {snapshot.profiles.map(profile => {
              const choice = choices.find(entry => entry.nuvioProfileId === profile.profileId);
              const value = choice?.target.kind === "existing" ? choice.target.profileId
                : choice?.target.kind === "skip" ? "__skip" : "__create";
              const collections = Array.isArray(profile.collectionsJson) ? profile.collectionsJson.length : 0;
              const disabled = profile.addons.filter(addon => !addon.enabled).length;
              return <li key={profile.profileId}>
                <span>
                  <strong>{profile.name}</strong>
                  <em>{profile.addons.length} addons · {collections} collections
                    {disabled > 0 && ` · ${disabled} switched off in Nuvio`}
                    {profile.plugins.length > 0 && ` · ${profile.plugins.length} plugins stay in Nuvio`}</em>
                </span>
                <select value={value} onChange={event => setTarget(profile.profileId, event.target.value)}
                  aria-label={`Copy ${profile.name} into`}>
                  <option value="__create">Create a new ARVIO profile</option>
                  {profiles.map(target => <option key={target.id} value={target.id}>{target.name}</option>)}
                  <option value="__skip">Skip</option>
                </select>
              </li>;
            })}
          </ul>
          <div className="setup__row">
            <button type="button" className="setup__primary" disabled={busy || !signedIn || !accountReady || !planned.length} onClick={runImport}>
              <ArrowRightLeft size={18} aria-hidden="true" /> Copy {planned.length} profiles into ARVIO
            </button>
            <button type="button" className="setup__ghost" onClick={downloadSnapshot}>
              <Download size={18} aria-hidden="true" /> Download a copy
            </button>
          </div>
          {!signedIn && <p className="setup__muted">Sign in to ARVIO below to copy this into an account, or download it and keep it for later.</p>}
        </>}

        {results && <div className="setup__results">
          <p><Check size={18} aria-hidden="true" /> {snapshot?.warnings.length || results.some(result => result.failed || result.addonsFailed) ? "Import finished with warnings" : "Import finished"}</p>
          <ul className="setup__list">
            {results.map(result => <li key={result.profileName}>
              {result.failed
                // Nothing was written for this profile, so say so plainly rather
                // than letting a zero count read as "there was nothing to bring".
                ? <span><strong>{result.profileName}</strong>
                    <em className="setup__error">Not imported — {result.failed}. Nothing was changed on this profile.</em></span>
                : <span><strong>{result.profileName}</strong>
                    <em>{result.addons} addons · {result.collections} collections{result.created ? " · new profile" : ""}
                      {result.addonsDisabled > 0 && ` · ${result.addonsDisabled} left switched off`}
                      {result.addonsFailed > 0 && ` · ${result.addonsFailed} could not be reached`}</em></span>}
            </li>)}
          </ul>
          <p className="setup__muted">Open ARVIO on your TV or phone and sign in with this account to see them.</p>
        </div>}
      </section>

      {!signedIn && <section className="setup__card">
        <h2>Sign in to ARVIO</h2>
        <p className="setup__muted">Needed to save addons, or to copy a Nuvio account into ARVIO.</p>
        <label><span>Email</span>
          <input type="email" value={email} onChange={event => setEmail(event.target.value)} autoComplete="username" /></label>
        <label><span>Password</span>
          <input type="password" value={password} onChange={event => setPassword(event.target.value)} autoComplete="current-password" /></label>
        <button type="button" className="setup__primary" disabled={busy || !email.trim() || !password} onClick={signIn}>
          {busy ? <Loader2 size={18} className="spin" aria-hidden="true" /> : <LogIn size={18} aria-hidden="true" />} Sign in
        </button>
      </section>}

      {signedIn && <>
        <section className="setup__card">
          <h2>Profile</h2>
          {!accountReady && <p className="setup__muted">{accountLoading ? "Loading your ARVIO account..." : "Your ARVIO account could not be loaded. Retry before importing."}</p>}
          {!accountReady && <button type="button" className="setup__ghost" disabled={busy || accountLoading} onClick={() => run(() => loadAccount())}>
            <RefreshCw size={18} aria-hidden="true" /> Retry loading account
          </button>}
          {accountReady && profiles.length === 0 && <p className="setup__muted">This account has no profiles yet. Importing from Nuvio will create them.</p>}
          <div className="setup__profiles">
            {profiles.map(profile => <button key={profile.id} type="button"
              className={`setup__profile ${profile.id === profileId ? "is-active" : ""}`}
              disabled={busy || !accountReady} onClick={() => selectProfile(profile.id)}>{profile.name}</button>)}
          </div>
        </section>

        <section className="setup__card">
          <h2>Addons</h2>
          <p className="setup__muted">Paste a Stremio addon manifest URL to install it for this profile.</p>
          <div className="setup__row">
            <input value={addonUrl} onChange={event => setAddonUrl(event.target.value)}
              placeholder="https://example.com/manifest.json" spellCheck={false} />
            <button type="button" className="setup__primary" disabled={busy || !accountReady || !addonUrl.trim() || !profileId} onClick={addAddon}>
              <Plus size={18} aria-hidden="true" /> Install
            </button>
          </div>
          <ul className="setup__list">
            {addons.map(addon => <li key={addon.id}>
              <span>{addon.name}</span>
              <button type="button" className="setup__icon" aria-label={`Remove ${addon.name}`} onClick={() => removeAddon(addon)}>
                <Trash2 size={16} aria-hidden="true" />
              </button>
            </li>)}
            {addons.length === 0 && <li className="setup__muted">No addons on this profile yet.</li>}
          </ul>
        </section>

      </>}

      {busy && progress && <p className="setup__progress"><Loader2 size={16} className="spin" aria-hidden="true" /> {progress}</p>}
      {error && <p className="setup__error" role="alert">{error}</p>}
      {notice && !error && <p className="setup__notice">{notice}</p>}
      </div>
    </main>
  );
}
