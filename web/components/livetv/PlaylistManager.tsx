"use client";
import { isManagedId } from "@/lib/managedConfiguration";
import { MAX_IPTV_PLAYLISTS } from "@/lib/iptvPlaylistIdentity";
import { useTranslation } from "@/lib/i18n";
import { AlertTriangle, CheckCircle2, Eye, Pencil, Plus, RefreshCw, Settings, Trash2, X } from "lucide-react";
import { useEffect, useId, useRef, useState } from "react";
import { normalizeIptvInput, testIptvPlaylist, xtreamInfoFromUrl } from "@/lib/iptv";
import { playlistErrorCause, xtreamPlaylistUrls, type PlaylistErrorCause } from "@/lib/iptvGuide";
import { maskedUrl } from "@/lib/maskUrl";
import { groupDisplayName } from "@/lib/iptvGroups";
import { MAX_STALKER_PORTALS, nextStalkerPortalId, portalDraftProblem, upsertStalkerPortal, type PortalDraftProblem } from "@/lib/stalkerPortals";
import type { IptvPlaylistEntry, StalkerPortalEntry } from "@/lib/types";

export type PlaylistError = { playlistId: string; name: string; message: string };

export function usePlaylistErrorText() {
  const translateUi = useTranslation();
  return (cause: PlaylistErrorCause) => {
    switch (cause) {
      case "not-found": return translateUi("The playlist address was not found (404). Check the link with your provider.");
      case "html": return translateUi("The provider sent a web page (often a login or error page) instead of a playlist. Check your username, password and subscription.");
      case "unreachable": return translateUi("The provider could not be reached. Check the address, or try again later.");
      case "blocked": return translateUi("This address can't be opened from a web browser (it may be a local or private address). Use the provider's public address, or the Android app.");
      case "auth": return translateUi("The provider refused the login. Check your username, password and that your subscription is active.");
      case "paused": return translateUi("The provider is limiting requests. Wait a minute, then retry.");
      case "empty": return translateUi("The playlist loaded but contained no channels.");
      default: return translateUi("The playlist could not be loaded.");
    }
  };
}

/** Every failing playlist with a plain cause and what to do next. */
export function PlaylistErrors({ errors, retrying, onEdit, onRetry, onOpenSettings }: {
  errors: PlaylistError[];
  retrying: boolean;
  onEdit: (playlistId: string) => void;
  onRetry: () => void;
  onOpenSettings: () => void;
}) {
  const translateUi = useTranslation();
  const causeText = usePlaylistErrorText();
  if (!errors.length) return null;
  return (
    <section className="livetv-warning livetv-playlist-errors" role="alert" aria-label={translateUi("Playlist problem")}>
      <strong><AlertTriangle size={17} /> {errors.length === 1 ? translateUi("A playlist could not be loaded") : translateUi("{value0} playlists could not be loaded", { value0: errors.length })}</strong>
      <ul>
        {errors.map((error) => (
          <li key={error.playlistId}>
            <div>
              <b>{error.name}</b>
              <span>{causeText(playlistErrorCause(error.message))}</span>
              <details><summary>{translateUi("Details")}</summary><code>{error.message}</code></details>
            </div>
            <div className="livetv-playlist-error-actions">
              {error.playlistId !== "stalker" && !error.playlistId.startsWith("warning-") && <button type="button" className="secondary" onClick={() => onEdit(error.playlistId)}><Pencil size={15} /> {translateUi("Edit")}</button>}
              <button type="button" className="secondary" onClick={onRetry} disabled={retrying}><RefreshCw size={15} className={retrying ? "is-spinning" : ""} /> {translateUi("Retry")}</button>
              <button type="button" className="secondary" onClick={onOpenSettings}><Settings size={15} /> {translateUi("Open in Settings")}</button>
            </div>
          </li>
        ))}
      </ul>
    </section>
  );
}

type Kind = "m3u" | "xtream" | "stalker";
type Draft = { kind: Kind; name: string; url: string; epg: string; host: string; username: string; password: string; portal: string; mac: string };
const emptyDraft: Draft = { kind: "m3u", name: "", url: "", epg: "", host: "", username: "", password: "", portal: "", mac: "" };

function draftFor(playlist: IptvPlaylistEntry): Draft {
  const xtream = xtreamInfoFromUrl(playlist.m3uUrl);
  return xtream
    ? { ...emptyDraft, kind: "xtream", name: playlist.name, host: xtream.baseUrl, username: xtream.username, password: xtream.password }
    : { ...emptyDraft, kind: "m3u", name: playlist.name, url: playlist.m3uUrl, epg: playlist.epgUrl ?? "" };
}

/** Draft → the playlist entry ARVIO stores (or an error to show under the form). */
export type DraftProblem = "missing-xtream" | "missing-url" | "bad-url";
export function playlistFromDraft(draft: Pick<Draft, "kind" | "name" | "url" | "epg" | "host" | "username" | "password">, id: string): IptvPlaylistEntry | DraftProblem {
  if (draft.kind === "xtream") {
    const urls = xtreamPlaylistUrls(draft.host, draft.username, draft.password);
    if (!urls) return "missing-xtream";
    return { id, name: draft.name.trim() || "Playlist", m3uUrl: urls.m3uUrl, epgUrl: urls.epgUrl, enabled: true };
  }
  const url = draft.url.trim();
  if (!url) return "missing-url";
  if (!/^https?:\/\//i.test(normalizeIptvInput(url))) return "bad-url";
  return { id, name: draft.name.trim() || "Playlist", m3uUrl: url, epgUrl: draft.epg.trim(), enabled: true };
}

/** Message for a portal the user typed that cannot be saved or tested yet. */
export function usePortalProblemText() {
  const translateUi = useTranslation();
  return (problem: PortalDraftProblem) => problem === "missing" ? translateUi("Enter the portal address and MAC address.")
    : problem === "bad-url" ? translateUi("The portal address must start with http:// or https://.")
    : translateUi("Enter the MAC address as six pairs, e.g. 00:1A:79:12:34:56.");
}

export function PlaylistManager({ playlists, hiddenGroups, groupNames, portals, reservedPortalIds = [], userAgent, editRequest, onSavePlaylist, onRemovePlaylist, onTogglePlaylist, onSavePortals, onShowGroup, onClose }: {
  playlists: IptvPlaylistEntry[];
  hiddenGroups: string[];
  groupNames?: Record<string, string>;
  /** Every Stalker portal, enabled or not (Android's stalkerPortals list). */
  portals: StalkerPortalEntry[];
  /** Portal ids settings still refer to (referencedStalkerPortalIds); a new portal does not take one. */
  reservedPortalIds?: string[];
  userAgent?: string;
  /** A playlist id to open for editing (from the error panel), or "new". */
  editRequest: { id: string; at: number } | null;
  onSavePlaylist: (playlist: IptvPlaylistEntry) => void;
  onRemovePlaylist: (id: string) => void;
  onTogglePlaylist: (id: string) => void;
  /** Save the whole portal list; `closeManager` after adding or editing a portal. */
  onSavePortals: (portals: StalkerPortalEntry[], closeManager: boolean) => void;
  onShowGroup: (id: string) => void;
  onClose: () => void;
}) {
  const translateUi = useTranslation();
  const causeText = usePlaylistErrorText();
  const portalProblemText = usePortalProblemText();
  const formId = useId();
  const [editing, setEditing] = useState<string | null>(playlists.length || portals.length ? null : "new");
  const isPortal = (id: string | null) => Boolean(id && portals.some((portal) => portal.id === id));
  const portalsFull = portals.length >= MAX_STALKER_PORTALS;
  const [draft, setDraft] = useState<Draft>(emptyDraft);
  const [confirmRemove, setConfirmRemove] = useState<string | null>(null);
  const [test, setTest] = useState<{ state: "idle" } | { state: "testing" } | { state: "ok"; channels: number } | { state: "error"; text: string }>({ state: "idle" });
  const [formError, setFormError] = useState("");
  const firstField = useRef<HTMLInputElement>(null);
  const problemText = (problem: DraftProblem) => problem === "missing-xtream" ? translateUi("Enter the server address, username and password.")
    : problem === "missing-url" ? translateUi("Enter the playlist link from your provider.") : translateUi("The playlist link must start with http:// or https://.");
  const testRun = useRef(0);

  const open = (id: string) => {
    if (isManagedId(id)) return;
    const playlist = playlists.find((item) => item.id === id);
    const portal = portals.find((item) => item.id === id);
    setDraft(portal ? { ...emptyDraft, kind: "stalker", name: portal.name, portal: portal.portalUrl, mac: portal.macAddress } : playlist ? draftFor(playlist) : emptyDraft);
    setEditing(id);
    setTest({ state: "idle" });
    setFormError("");
    testRun.current++;
  };
  useEffect(() => { if (editRequest) open(editRequest.id); // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [editRequest]);
  useEffect(() => { if (editing) requestAnimationFrame(() => firstField.current?.focus()); }, [editing, draft.kind]);

  const set = (patch: Partial<Draft>) => { setDraft((current) => ({ ...current, ...patch })); setTest({ state: "idle" }); setFormError(""); };
  const candidate = () => draft.kind === "stalker" ? null : playlistFromDraft(draft, editing && editing !== "new" ? editing : crypto.randomUUID());

  const runTest = async () => {
    const run = ++testRun.current;
    if (draft.kind === "stalker") {
      const problem = portalDraftProblem(draft.portal, draft.mac);
      if (problem) { setFormError(portalProblemText(problem)); return; }
      setTest({ state: "testing" });
      const id = isPortal(editing) ? editing! : nextStalkerPortalId(portals.map((portal) => portal.id), reservedPortalIds) ?? "stalker1";
      const [candidatePortal] = upsertStalkerPortal([], { name: draft.name, portalUrl: draft.portal, macAddress: draft.mac });
      const result = await testIptvPlaylist(null, { stalkerPortals: [{ ...candidatePortal, id }], userAgent });
      if (run !== testRun.current) return;
      setTest(result.ok ? { state: "ok", channels: result.channels } : { state: "error", text: causeText(playlistErrorCause(result.message)) });
      return;
    }
    const entry = candidate();
    if (!entry) return;
    if (typeof entry === "string") { setFormError(problemText(entry)); return; }
    setTest({ state: "testing" });
    const result = await testIptvPlaylist(entry, { userAgent });
    if (run !== testRun.current) return;
    setTest(result.ok ? { state: "ok", channels: result.channels } : { state: "error", text: causeText(playlistErrorCause(result.message)) });
  };

  const save = () => {
    if (draft.kind === "stalker") {
      const problem = portalDraftProblem(draft.portal, draft.mac);
      if (problem) { setFormError(portalProblemText(problem)); return; }
      if (!isPortal(editing) && portalsFull) { setFormError(translateUi("You can add up to {value0} portals.", { value0: MAX_STALKER_PORTALS })); return; }
      onSavePortals(upsertStalkerPortal(portals, { id: isPortal(editing) ? editing! : undefined, name: draft.name, portalUrl: draft.portal, macAddress: draft.mac }, reservedPortalIds), true);
    } else {
      const entry = candidate();
      if (!entry) return;
      if (typeof entry === "string") { setFormError(problemText(entry)); return; }
      const existing = playlists.find((item) => item.id === entry.id);
      if (!existing && playlists.length >= MAX_IPTV_PLAYLISTS) {
        setFormError(translateUi("Maximum of 10 IPTV playlists reached"));
        return;
      }
      onSavePlaylist(existing ? { ...existing, ...entry, enabled: existing.enabled } : entry);
    }
    testRun.current++;
    setEditing(null);
    setDraft(emptyDraft);
  };

  const field = (key: keyof Draft, label: string, help: string, options: { type?: string; placeholder?: string; autoComplete?: string; first?: boolean } = {}) => (
    <div className="livetv-field">
      <label htmlFor={`${formId}-${key}-input`}>{label}</label>
      <input id={`${formId}-${key}-input`} ref={options.first ? firstField : undefined} type={options.type ?? "text"} value={draft[key]} placeholder={options.placeholder}
        autoCapitalize="none" autoCorrect="off" spellCheck={false} autoComplete={options.autoComplete ?? "off"}
        inputMode={options.type === "url" ? "url" : undefined}
        onChange={(event) => set({ [key]: event.target.value } as Partial<Draft>)} aria-describedby={help ? `${formId}-${key}` : undefined} />
      {help && <small id={`${formId}-${key}`}>{help}</small>}
    </div>
  );

  return (
    <section className="livetv-manage" aria-label={translateUi("Playlists")}>
      <header className="livetv-manage-head">
        <h3>{translateUi("Playlists")}</h3>
        <button type="button" className="secondary" onClick={() => open("new")}><Plus size={16} /> {translateUi("Add playlist")}</button>
        <button type="button" className="livetv-chipbtn" onClick={onClose} aria-label={translateUi("Close playlists")}><X size={17} /></button>
      </header>
      {hiddenGroups.length > 0 && <div className="hidden-groups"><strong>{translateUi("Hidden categories")}</strong>{hiddenGroups.map((group) => <button type="button" key={group} className="secondary" onClick={() => onShowGroup(group)} aria-label={translateUi("Show {value0}", { value0: groupDisplayName(groupNames, group) })}><Eye size={16} />{groupDisplayName(groupNames, group)}</button>)}</div>}
      {[...playlists.map((playlist) => ({ id: playlist.id, portal: false, name: playlist.name, detail: isManagedId(playlist.id) ? "Managed connection" : maskedUrl(playlist.m3uUrl), epg: Boolean(playlist.epgUrl || playlist.epgUrls?.length), enabled: playlist.enabled })),
        ...portals.map((portal) => ({ id: portal.id, portal: true, name: portal.name, detail: maskedUrl(portal.portalUrl), epg: true, enabled: portal.enabled !== false }))].map((row) => (
        <div className="livetv-manage-row" key={row.id}>
          <button type="button" disabled={isManagedId(row.id)} className={`livetv-switch ${row.enabled ? "is-on" : ""}`} role="switch" aria-checked={row.enabled}
            onClick={() => row.portal ? onSavePortals(portals.map((portal) => portal.id === row.id ? { ...portal, enabled: !row.enabled } : portal), false) : onTogglePlaylist(row.id)}
            aria-label={row.enabled ? translateUi("Disable {value0}", { value0: row.name }) : translateUi("Enable {value0}", { value0: row.name })} />
          <span className="livetv-manage-name">
            <strong>{row.name}</strong>
            <em>{row.detail} · {row.portal ? translateUi("Stalker portal") : row.epg ? translateUi("Playlist + EPG") : translateUi("Playlist")}</em>
          </span>
          {isManagedId(row.id) ? <a href="/partner/account">Managed</a> : confirmRemove === row.id ? (
            <span className="livetv-manage-confirm" role="group" aria-label={translateUi("Remove {value0}?", { value0: row.name })}>
              <span>{translateUi("Remove from all your devices?")}</span>
              <button type="button" className="danger" autoFocus onClick={() => { setConfirmRemove(null); if (row.portal) onSavePortals(portals.filter((portal) => portal.id !== row.id), false); else onRemovePlaylist(row.id); }}>{translateUi("Remove")}</button>
              <button type="button" className="secondary" onClick={() => setConfirmRemove(null)}>{translateUi("Cancel")}</button>
            </span>
          ) : <>
            <button type="button" className="livetv-manage-icon" onClick={() => open(row.id)} aria-label={translateUi("Edit {value0}", { value0: row.name })} title={translateUi("Edit")}><Pencil size={16} /></button>
            <button type="button" className="livetv-manage-icon livetv-manage-remove" onClick={() => setConfirmRemove(row.id)} aria-label={translateUi("Remove {value0}", { value0: row.name })} title={translateUi("Remove")}><Trash2 size={16} /></button>
          </>}
        </div>
      ))}
      {editing && (
        <form className="livetv-manage-form" aria-label={editing === "new" ? translateUi("Add playlist") : translateUi("Edit playlist")} onSubmit={(event) => { event.preventDefault(); save(); }}>
          <div className="livetv-kind" role="group" aria-label={translateUi("Playlist type")}>
            {([["m3u", "M3U link"], ["xtream", "Xtream Codes"], ["stalker", "Stalker portal"]] as const).map(([kind, label]) => (
              <button type="button" key={kind} aria-pressed={draft.kind === kind} className={draft.kind === kind ? "is-active" : ""}
                disabled={editing !== "new" ? (isPortal(editing) ? kind !== "stalker" : kind === "stalker") : kind === "stalker" && portalsFull}
                title={editing === "new" && kind === "stalker" && portalsFull ? translateUi("You can add up to {value0} portals.", { value0: MAX_STALKER_PORTALS }) : undefined}
                onClick={() => set({ kind })}>{translateUi(label)}</button>
            ))}
          </div>
          {field("name", translateUi("Name"), translateUi("Shown in the Live TV categories, e.g. your provider's name."), { first: true, placeholder: draft.kind === "stalker" ? translateUi("Portal 1") : translateUi("My provider") })}
          {draft.kind === "m3u" && <>
            {field("url", translateUi("Playlist link (M3U)"), translateUi("The M3U or M3U8 link from your provider. Xtream get.php links work too."), { type: "url", placeholder: "https://provider.example/playlist.m3u" })}
            {field("epg", translateUi("Guide link (XMLTV, optional)"), translateUi("Leave empty to use the guide named inside the playlist."), { type: "url", placeholder: "https://provider.example/epg.xml" })}
          </>}
          {draft.kind === "xtream" && <>
            {field("host", translateUi("Server address"), translateUi("From your provider, including the port, e.g. http://provider.example:8080"), { type: "url", placeholder: "http://provider.example:8080" })}
            {field("username", translateUi("Username"), "", { autoComplete: "username" })}
            {field("password", translateUi("Password"), translateUi("ARVIO builds the playlist and guide links from these and syncs them to your other devices."), { type: "password", autoComplete: "current-password" })}
          </>}
          {draft.kind === "stalker" && <>
            {field("portal", translateUi("Portal address"), translateUi("The portal URL from your provider, e.g. http://portal.example/c/"), { type: "url", placeholder: "http://portal.example/c/" })}
            {field("mac", translateUi("MAC address"), translateUi("The MAC address registered with your provider, e.g. 00:1A:79:12:34:56"), { placeholder: "00:1A:79:00:00:00" })}
          </>}
          {formError && <p className="livetv-form-error" role="alert">{formError}</p>}
          {test.state !== "idle" && <p className={`livetv-test-result is-${test.state}`} role="status">
            {test.state === "testing" ? <><RefreshCw size={15} className="is-spinning" /> {translateUi("Testing…")}</>
              : test.state === "ok" ? <><CheckCircle2 size={15} /> {translateUi("Works: {value0} channels found.", { value0: test.channels.toLocaleString() })}</>
              : <><AlertTriangle size={15} /> {test.text}</>}
          </p>}
          <div className="livetv-form-actions">
            <button type="button" className="secondary" onClick={() => void runTest()} disabled={test.state === "testing"}>{translateUi("Test")}</button>
            <button type="submit" className="primary">{editing === "new" ? translateUi("Add") : translateUi("Save")}</button>
            <button type="button" className="secondary" onClick={() => { testRun.current++; setEditing(null); }}>{translateUi("Cancel")}</button>
          </div>
        </form>
      )}
    </section>
  );
}
