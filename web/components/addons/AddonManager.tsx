"use client";
import { useTranslation } from "@/lib/i18n";
import "./addons.css";

import { AlertTriangle, ArrowDown, ArrowUp, Eye, EyeOff, Puzzle, Settings2, Trash2, X } from "lucide-react";
import { useEffect, useId, useRef, useState } from "react";
import { installAddon as fetchAddonManifest } from "@/lib/addons";
import { AddonAlreadyInstalledError, addonCapabilities, addonConfigureUrl, duplicateAddons, isSameInstall, type AddonInstallMode } from "@/lib/addonInstall";
import { maskedUrl } from "@/lib/maskUrl";
import { useAppPick } from "@/lib/store";
import type { InstalledAddon } from "@/lib/types";

export type AddonErrorKind = "invalid" | "not-found" | "html" | "unreachable" | "not-addon" | "other";

/** A plain cause for a failed manifest fetch (the raw errors read "Invalid url", "Failed to fetch"…). */
export function addonErrorKind(input: string, error: unknown): AddonErrorKind {
  try { const url = new URL(input.trim()); if (!/^https?:$/.test(url.protocol)) return "invalid"; } catch { return "invalid"; }
  const status = Number((error as { status?: number })?.status);
  const message = error instanceof Error ? error.message.toLowerCase() : "";
  if (status === 404 || /\b404\b|not found/.test(message)) return "not-found";
  if (/unexpected token|<!doctype|<html|not valid json|json/.test(message)) return "html";
  if (/failed to fetch|networkerror|load failed|took too long|timed? ?out|blocked/.test(message)) return "unreachable";
  if (status >= 400) return "unreachable";
  return "other";
}

/** A manifest with neither an id nor any resources is not an addon. */
export function looksLikeAddon(addon: InstalledAddon) {
  return Boolean(addon.name && addon.name !== "Unnamed addon") || (Array.isArray(addon.resources) && addon.resources.length > 0) || (Array.isArray(addon.catalogs) && addon.catalogs.length > 0);
}

function AddonLogo({ addon, size = 44 }: { addon: InstalledAddon; size?: number }) {
  const [failed, setFailed] = useState(false);
  const logo = addon.logo || (addon as InstalledAddon & { manifest?: { logo?: string | null } }).manifest?.logo;
  return <span className="addon-logo" style={{ width: size, height: size }} aria-hidden="true">
    {logo && !failed ? <img src={logo} alt="" loading="lazy" onError={() => setFailed(true)} /> : <Puzzle size={Math.round(size * 0.5)} />}
  </span>;
}

function Capabilities({ addon }: { addon: InstalledAddon }) {
  const translateUi = useTranslation();
  const caps = addonCapabilities(addon);
  const chips = [
    caps.streams ? translateUi("Streams") : null,
    caps.subtitles ? translateUi("Subtitles") : null,
    caps.catalogs === 1 ? translateUi("1 catalog") : caps.catalogs ? translateUi("{value0} catalogs", { value0: caps.catalogs }) : null,
    caps.meta ? translateUi("Details") : null
  ].filter(Boolean) as string[];
  return <span className="addon-chips">{(chips.length ? chips : [translateUi("Manifest")]).map((chip) => <span key={chip}>{chip}</span>)}</span>;
}

/** The manifest link, host only until revealed: links often embed debrid/API keys. */
export function MaskedAddonUrl({ url }: { url: string }) {
  const translateUi = useTranslation();
  const [shown, setShown] = useState(false);
  return <span className="addon-url">
    <code title={shown ? url : undefined}>{shown ? url : maskedUrl(url)}</code>
    <button type="button" className="addon-url-toggle" aria-pressed={shown} onClick={() => setShown(!shown)}
      aria-label={shown ? translateUi("Hide addon link") : translateUi("Show addon link")} title={shown ? translateUi("Hide addon link") : translateUi("Show addon link")}>
      {shown ? <EyeOff size={15} /> : <Eye size={15} />}
    </button>
  </span>;
}

/** Install preview: what the addon is and provides, before anything is saved. */
function InstallPreview({ addon, existing, busy, onInstall, onCancel }: {
  addon: InstalledAddon;
  existing: InstalledAddon[];
  busy: boolean;
  onInstall: (mode?: AddonInstallMode) => void;
  onCancel: () => void;
}) {
  const translateUi = useTranslation();
  const configure = addonConfigureUrl(addon);
  const { addons } = useAppPick("addons");
  const sameLink = isSameInstall(addons, addon);
  const primary = useRef<HTMLButtonElement>(null);
  useEffect(() => { primary.current?.focus({ preventScroll: true }); }, [existing.length]);
  return <section className="addon-preview" aria-label={translateUi("Addon preview")}>
    <div className="addon-preview-head">
      <AddonLogo addon={addon} size={56} />
      <div>
        <h3>{addon.name}</h3>
        <p className="addon-preview-version">v{addon.version || "1.0.0"}</p>
      </div>
    </div>
    {addon.description && <p className="addon-preview-description">{addon.description}</p>}
    <p className="addon-preview-provides"><span>{translateUi("Provides")}</span> <Capabilities addon={addon} /></p>
    <p className="addon-preview-warning"><AlertTriangle size={16} /> {translateUi("This addon is run by a third party and can see what you watch. Only install addons you trust.")}</p>
    {configure && <p className="addon-preview-configure">{translateUi("This addon has its own settings page.")} <a href={configure} target="_blank" rel="noopener noreferrer"><Settings2 size={15} /> {translateUi("Configure")}</a></p>}
    {existing.length > 0 ? <div className="addon-duplicate" role="alert">
      <p><strong>{translateUi("{value0} is already installed.", { value0: existing[0].name })}</strong> {sameLink
        ? translateUi("This is the same link. Replace it to update its details.")
        : translateUi("Replace it with this configuration, or keep both side by side.")}</p>
      <div className="addon-actions">
        <button ref={primary} type="button" className="primary" disabled={busy} onClick={() => onInstall("replace")}>{translateUi("Replace")}</button>
        {!sameLink && <button type="button" className="secondary" disabled={busy} onClick={() => onInstall("keep-both")}>{translateUi("Keep both")}</button>}
        <button type="button" className="secondary" onClick={onCancel}>{translateUi("Cancel")}</button>
      </div>
    </div> : <div className="addon-actions">
      <button ref={primary} type="button" className="primary" disabled={busy} onClick={() => onInstall()}>{busy ? translateUi("Installing...") : translateUi("Install")}</button>
      <button type="button" className="secondary" onClick={onCancel}>{translateUi("Cancel")}</button>
    </div>}
  </section>;
}

/** Install by link with a preview, then the full list. Reused by Settings → Addons. */
export function AddonInstaller() {
  const translateUi = useTranslation();
  const { addons, installAddon, setToast } = useAppPick("addons", "installAddon", "setToast");
  const inputId = useId();
  const [url, setUrl] = useState("");
  const [loading, setLoading] = useState(false);
  const [preview, setPreview] = useState<{ addon: InstalledAddon; existing: InstalledAddon[] } | null>(null);
  const [error, setError] = useState<AddonErrorKind | "duplicate-link" | null>(null);
  const errorText = (kind: AddonErrorKind | "duplicate-link") => {
    switch (kind) {
      case "invalid": return translateUi("Enter the full addon link, starting with https:// (it usually ends in manifest.json).");
      case "not-found": return translateUi("No addon was found at this link (404). Copy the install link again from the addon's page.");
      case "html": return translateUi("This link opens a web page, not an addon. Use the addon's install link (manifest.json).");
      case "unreachable": return translateUi("The addon could not be reached. Check the link, or try again later.");
      case "not-addon": return translateUi("This link did not return an addon manifest.");
      case "duplicate-link": return translateUi("This exact addon link is already installed.");
      default: return translateUi("Could not install this addon.");
    }
  };

  const load = async () => {
    const value = url.trim();
    setError(null);
    if (!value) { setError("invalid"); return; }
    try { const parsed = new URL(value); if (!/^https?:$/.test(parsed.protocol)) throw new Error("protocol"); }
    catch { setError("invalid"); return; }
    setLoading(true);
    try {
      const addon = await fetchAddonManifest(value);
      if (!looksLikeAddon(addon)) { setError("not-addon"); return; }
      // Same rule the store applies when installing.
      setPreview({ addon, existing: duplicateAddons(addons, addon) });
    } catch (cause) {
      setError(addonErrorKind(value, cause));
    } finally {
      setLoading(false);
    }
  };

  const install = async (mode?: AddonInstallMode) => {
    if (!preview) return;
    setLoading(true);
    try {
      const installed = await installAddon(preview.addon, { mode });
      setToast(translateUi("{value0} installed.", { value0: installed.name }));
      setPreview(null);
      setUrl("");
    } catch (cause) {
      if (cause instanceof AddonAlreadyInstalledError) setPreview({ addon: preview.addon, existing: cause.existing });
      else if (cause instanceof Error && cause.message === "This exact addon link is already installed.") setError("duplicate-link");
      else setToast(cause instanceof Error ? cause.message : "Could not install addon.");
    } finally {
      setLoading(false);
    }
  };

  return <div className="addon-installer">
    <form className="addon-install-form" onSubmit={(event) => { event.preventDefault(); if (!loading) void load(); }}>
      <label htmlFor={inputId}>{translateUi("Addon link")}</label>
      <div className="addon-install-row">
        <input id={inputId} value={url} onChange={(event) => { setUrl(event.target.value); setError(null); setPreview(null); }} autoCapitalize="none" autoCorrect="off" spellCheck={false}
          inputMode="url" placeholder="https://addon.example.com/manifest.json" aria-describedby={`${inputId}-help`} aria-invalid={Boolean(error)} />
        <button type="submit" className="primary" disabled={loading || !url.trim()}>{loading && !preview ? translateUi("Checking...") : translateUi("Continue")}</button>
      </div>
      <small id={`${inputId}-help`}>{translateUi("Paste a Stremio-compatible addon link. You'll see what it provides before anything is installed.")}</small>
      {error && <p className="addon-error" role="alert">{errorText(error)}</p>}
    </form>
    {preview && <InstallPreview addon={preview.addon} existing={preview.existing} busy={loading} onInstall={(mode) => void install(mode)} onCancel={() => setPreview(null)} />}
  </div>;
}

/** Installed addons: enable/disable, order, configure, remove (with confirm). */
export function AddonList() {
  const translateUi = useTranslation();
  const { addons, setAddonsState, removeAddon, setToast } = useAppPick("addons", "setAddonsState", "removeAddon", "setToast");
  const [confirm, setConfirm] = useState<string | null>(null);
  const save = (next: InstalledAddon[]) => void setAddonsState(next).catch((error) => setToast(error instanceof Error ? error.message : "Could not save addons."));
  const move = (index: number, direction: -1 | 1) => {
    const target = index + direction;
    if (target < 0 || target >= addons.length) return;
    const next = [...addons];
    [next[index], next[target]] = [next[target], next[index]];
    save(next);
  };
  if (!addons.length) return <p className="addon-empty">{translateUi("No addons yet. Install one by link above.")}</p>;
  return <ul className="addon-list" aria-label={translateUi("Installed addons")}>
    {addons.map((addon, index) => {
      const off = addon.enabled === false;
      const configure = addonConfigureUrl(addon);
      return <li key={addon.id} className={`addon-item ${off ? "is-off" : ""}`}>
        <AddonLogo addon={addon} />
        <div className="addon-item-main">
          <span className="addon-item-title"><strong>{addon.name}</strong><span className="addon-version">v{addon.version || "1.0.0"}</span>{off && <span className="addon-off">{translateUi("Off")}</span>}</span>
          <Capabilities addon={addon} />
          {addon.managedOrganizationId ? <a href="/partner/account">Managed connection</a> : <MaskedAddonUrl url={addon.manifestUrl} />}
        </div>
        {addon.managedOrganizationId ? null : confirm === addon.id ? <div className="addon-confirm" role="group" aria-label={translateUi("Remove {value0}?", { value0: addon.name })}>
          <span>{translateUi("Remove {value0} from every device and profile?", { value0: addon.name })}</span>
          <button type="button" className="danger" autoFocus onClick={() => { setConfirm(null); void removeAddon(addon).catch((error) => setToast(error instanceof Error ? error.message : "Could not remove addon.")); }}>{translateUi("Remove")}</button>
          <button type="button" className="secondary" onClick={() => setConfirm(null)}><X size={15} /> {translateUi("Cancel")}</button>
        </div> : <div className="addon-item-actions">
          <button type="button" role="switch" aria-checked={!off} className={`addon-switch ${off ? "" : "is-on"}`}
            aria-label={off ? translateUi("Enable {value0}", { value0: addon.name }) : translateUi("Disable {value0}", { value0: addon.name })}
            onClick={() => save(addons.map((item) => item.id === addon.id ? { ...item, enabled: off } : item))} />
          <button type="button" className="addon-icon" disabled={index === 0} onClick={() => move(index, -1)} aria-label={translateUi("Move {value0} up", { value0: addon.name })} title={translateUi("Move up")}><ArrowUp size={16} /></button>
          <button type="button" className="addon-icon" disabled={index === addons.length - 1} onClick={() => move(index, 1)} aria-label={translateUi("Move {value0} down", { value0: addon.name })} title={translateUi("Move down")}><ArrowDown size={16} /></button>
          {configure && <a className="addon-icon" href={configure} target="_blank" rel="noopener noreferrer" aria-label={translateUi("Configure {value0}", { value0: addon.name })} title={translateUi("Configure")}><Settings2 size={16} /></a>}
          <button type="button" className="addon-icon is-danger" onClick={() => setConfirm(addon.id)} aria-label={translateUi("Remove {value0}", { value0: addon.name })} title={translateUi("Remove")}><Trash2 size={16} /></button>
        </div>}
      </li>;
    })}
  </ul>;
}

/** The whole manager: installer plus list. */
export function AddonManager() {
  return <div className="addon-manager"><AddonInstaller /><AddonList /></div>;
}
