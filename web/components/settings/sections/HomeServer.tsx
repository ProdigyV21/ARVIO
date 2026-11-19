"use client";
// Home servers: priority order, on/off, editing an existing connection (with
// a connection test) and which libraries the Android app uses. Saved per
// profile in profileSettingsById.<id>.homeServerConnectionJson, the field the
// Android app reads.

import { Pencil, Plus, Trash2 } from "lucide-react";
import { useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { moveIndex } from "@/lib/ordering";
import { useAppPick } from "@/lib/store";
import { isManagedId } from "@/lib/managedConfiguration";
import { ManagedConnections } from "../ManagedConnections";
import type { HomeServerCollectionConfig, HomeServerConfig } from "@/lib/types";
import { SortableList } from "../SortableList";
import { Empty, Group, ScopeBadge, SectionHeader, Segmented, Toggle, safeArray } from "../ui";

/** Keep the user's library on/off choices when a test returns a fresh library list. */
export function mergeLibraryChoices(fresh: HomeServerCollectionConfig[] | undefined, previous: HomeServerCollectionConfig[] | undefined): HomeServerCollectionConfig[] | undefined {
  if (!fresh) return previous;
  const before = new Map((previous ?? []).map((library) => [library.id, library.enabled]));
  return fresh.map((library) => ({ ...library, enabled: before.get(library.id) ?? library.enabled }));
}

type Draft = { name: string; url: string; token: string; username: string; password: string };

function draftOf(server: HomeServerConfig): Draft {
  return { name: server.name ?? "", url: server.url ?? "", token: server.token ?? "", username: server.username ?? server.userName ?? "", password: "" };
}

export function HomeServerSection() {
  const translateUi = useTranslation();
  const { settings, updateSettings, setToast } = useAppPick("settings", "updateSettings", "setToast");
  const [type, setType] = useState<HomeServerConfig["type"]>("jellyfin");
  const [name, setName] = useState("");
  const [url, setUrl] = useState("");
  const [token, setToken] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [testing, setTesting] = useState(false);
  const [adding, setAdding] = useState(false);
  const [editing, setEditing] = useState<string | null>(null);
  const servers = safeArray(settings.homeServers).filter(server => !isManagedId(server.id));
  const update = (next: HomeServerConfig[]) => updateSettings({ homeServers: next });
  const editingServer = servers.find((server) => server.id === editing) ?? null;

  const buildDraft = (): HomeServerConfig => ({
    id: crypto.randomUUID(), type, name: name.trim() || type, url: url.trim(),
    token: token.trim() || undefined, username: username.trim() || undefined, password: password || undefined, enabled: true,
  });
  const validate = () => {
    if (!url.trim()) { setToast("Enter a Home Server URL first."); return false; }
    if (type === "plex" && !token.trim()) { setToast("Plex needs an access token (X-Plex-Token)."); return false; }
    return true;
  };
  const testConnection = async () => {
    if (!validate()) return;
    setTesting(true);
    try {
      const { testHomeServerConnection } = await import("@/lib/homeserver");
      const result = await testHomeServerConnection(buildDraft());
      setToast(result.ok ? `Connected to ${result.serverName || "server"}${result.libraryCount ? ` — ${result.libraryCount} libraries` : ""}.` : `Could not connect: ${result.error}`);
    } catch (error) {
      setToast(error instanceof Error ? error.message : "Connection test failed.");
    } finally {
      setTesting(false);
    }
  };
  const addConnection = async () => {
    if (!validate()) return;
    setAdding(true);
    try {
      const { testHomeServerConnection } = await import("@/lib/homeserver");
      const result = await testHomeServerConnection(buildDraft());
      if (!result.ok || !result.connection) { setToast(`Could not connect: ${result.error || "Connection failed"}`); return; }
      update([result.connection, ...servers]);
      setName(""); setUrl(""); setToken(""); setUsername(""); setPassword("");
      setToast(`Connected to ${result.serverName || result.connection.name} and saved ${result.libraryCount ?? 0} libraries.`);
    } catch (error) {
      setToast(error instanceof Error ? error.message : "Connection failed.");
    } finally {
      setAdding(false);
    }
  };

  return (
    <>
      <SectionHeader title="Home server" description="Connect Plex, Jellyfin or Emby. Matching movies and episodes appear as sources, and the connection syncs to this profile on your other ARVIO apps." />
      <ManagedConnections items={settings.homeServers.filter(server => isManagedId(server.id))} />
      <Group title={`${translateUi("Servers")} · ${servers.length}`}
        description={<>{translateUi("When several servers have the same title, the one higher in the list is used first.")} <ScopeBadge scope="all" /></>}>
        {servers.length === 0 ? <Empty>{translateUi("No home servers configured.")}</Empty> : (
          <SortableList
            items={servers}
            label={translateUi("Home servers")}
            getKey={(server) => server.id}
            getLabel={(server) => server.name || server.type}
            onMove={(from, to) => update(moveIndex(servers, from, to))}
            edgeButtons={servers.length > 2}
            rowClassName={(server) => server.enabled ? "" : "is-off"}
            renderMain={(server) => {
              const libraries = safeArray(server.collections);
              const on = libraries.filter((library) => library.enabled !== false).length;
              return (
                <>
                  <span className="st-sort-title"><span className="st-sort-name">{server.name || server.type || translateUi("Home server")}</span><span className="st-tag">{server.type || "server"}</span></span>
                  <span className="st-sort-sub">{server.url || translateUi("No URL")}{libraries.length ? ` · ${translateUi("{value0} of {value1} libraries", { value0: on, value1: libraries.length })}` : ""}</span>
                </>
              );
            }}
            renderActions={(server) => (
              <>
                <Toggle label={server.name || "server"} value={server.enabled} onChange={() => update(servers.map((s) => s.id === server.id ? { ...s, enabled: !s.enabled } : s))} />
                <button type="button" className="st-icon-btn" aria-pressed={editing === server.id} aria-label={translateUi("Edit {value0}", { value0: server.name || "server" })} title={translateUi("Edit")}
                  onClick={() => setEditing(editing === server.id ? null : server.id)}><Pencil size={17} /></button>
                <button type="button" className="st-icon-btn is-danger" aria-label={translateUi("Remove {value0}", { value0: server.name || "server" })}
                  onClick={() => { if (editing === server.id) setEditing(null); update(servers.filter((s) => s.id !== server.id)); }}><Trash2 size={17} /></button>
              </>
            )}
          />
        )}
        {editingServer && (
          <EditServer key={editingServer.id} server={editingServer} onClose={() => setEditing(null)}
            onSave={(next) => update(servers.map((s) => s.id === next.id ? next : s))} notify={setToast} />
        )}
      </Group>
      <Group title="Add a server" description={type === "plex" ? "Plex needs an access token (X-Plex-Token)." : type === "jellyfin" ? "Use an API token or a username and password. Silo: use the Jellyfin-compatible address. Optional profile: username#ProfileName. Protected profile: password#PIN." : "Use an API token or a username and password."}>
        <div className="st-tabs">
          <Segmented label="Server type" value={type} onChange={setType} options={[["jellyfin", "Jellyfin / Silo"], ["emby", "Emby"], ["plex", "Plex"]]} />
        </div>
        <div className="st-form">
          <input className="st-input" value={name} onChange={(e) => setName(e.target.value)} placeholder={translateUi("Name")} aria-label={translateUi("Name")} />
          <input className="st-input" value={url} onChange={(e) => setUrl(e.target.value)} placeholder="https://server:8096" aria-label={translateUi("Server address")} inputMode="url" />
          <input className="st-input" value={token} onChange={(e) => setToken(e.target.value)} aria-label={translateUi("Access token")} autoComplete="off"
            placeholder={type === "plex" ? translateUi("X-Plex-Token (required)") : translateUi("API token (optional)")} />
          {type !== "plex" && <input className="st-input" value={username} onChange={(e) => setUsername(e.target.value)} placeholder={translateUi("Username (optional)")} aria-label={translateUi("Username")} autoComplete="off" />}
          {type !== "plex" && <input className="st-input" value={password} onChange={(e) => setPassword(e.target.value)} placeholder={translateUi("Password")} aria-label={translateUi("Password")} type="password" autoComplete="new-password" />}
          <div className="st-form-actions">
            <button type="button" className="st-btn" disabled={testing} onClick={() => void testConnection()}>{(testing ? translateUi("Testing…") : translateUi("Test"))}</button>
            <button type="button" className="st-btn is-primary" disabled={adding || testing} onClick={() => void addConnection()}><Plus size={16} /> {(adding ? translateUi("Connecting…") : translateUi("Add"))}</button>
          </div>
        </div>
      </Group>
    </>
  );
}

function EditServer({ server, onSave, onClose, notify }: {
  server: HomeServerConfig;
  onSave: (server: HomeServerConfig) => void;
  onClose: () => void;
  notify: (message: string) => void;
}) {
  const translateUi = useTranslation();
  const [draft, setDraft] = useState<Draft>(() => draftOf(server));
  const [busy, setBusy] = useState<"test" | "save" | null>(null);
  const original = draftOf(server);
  const connectionChanged = draft.url.trim() !== original.url || draft.token.trim() !== original.token ||
    draft.username.trim() !== original.username || Boolean(draft.password);
  const set = (patch: Partial<Draft>) => setDraft((current) => ({ ...current, ...patch }));
  const candidate = (): HomeServerConfig => ({
    ...server,
    name: draft.name.trim() || server.name,
    url: draft.url.trim(),
    token: draft.token.trim() || undefined,
    username: draft.username.trim() || undefined,
    password: draft.password || undefined,
  });

  const run = async (save: boolean) => {
    if (!draft.url.trim()) { notify("Enter a Home Server URL first."); return; }
    if (server.type === "plex" && !draft.token.trim()) { notify("Plex needs an access token (X-Plex-Token)."); return; }
    if (save && !connectionChanged) {
      onSave({ ...server, name: draft.name.trim() || server.name });
      notify("Server saved.");
      onClose();
      return;
    }
    setBusy(save ? "save" : "test");
    try {
      const { testHomeServerConnection } = await import("@/lib/homeserver");
      const result = await testHomeServerConnection(candidate());
      if (!result.ok || !result.connection) { notify(`Could not connect: ${result.error || "Connection failed"}`); return; }
      if (!save) { notify(`Connected to ${result.serverName || "server"}${result.libraryCount ? ` — ${result.libraryCount} libraries` : ""}.`); return; }
      onSave({
        ...result.connection,
        id: server.id,
        enabled: server.enabled,
        name: draft.name.trim() || server.name,
        collections: mergeLibraryChoices(result.connection.collections, server.collections),
      });
      notify("Server saved.");
      onClose();
    } catch (error) {
      notify(error instanceof Error ? error.message : "Connection failed.");
    } finally {
      setBusy(null);
    }
  };
  const libraries = safeArray(server.collections);

  return (
    <div className="st-edit-panel" role="region" aria-label={translateUi("Edit {value0}", { value0: server.name || "server" })}>
      <div className="st-subhead" style={{ padding: "12px 0 0" }}><h4>{translateUi("Edit {value0}", { value0: server.name || server.type })}</h4></div>
      <div className="st-form">
        <input className="st-input" value={draft.name} onChange={(e) => set({ name: e.target.value })} placeholder={translateUi("Name")} aria-label={translateUi("Name")} />
        <input className="st-input" value={draft.url} onChange={(e) => set({ url: e.target.value })} placeholder="https://server:8096" aria-label={translateUi("Server address")} inputMode="url" />
        <input className="st-input" type="password" value={draft.token} onChange={(e) => set({ token: e.target.value })} aria-label={translateUi("Access token")} autoComplete="new-password" data-1p-ignore="" data-lpignore="true"
          placeholder={server.type === "plex" ? translateUi("X-Plex-Token (required)") : translateUi("API token (optional)")} />
        {server.type !== "plex" && <input className="st-input" value={draft.username} onChange={(e) => set({ username: e.target.value })} placeholder={translateUi("Username (optional)")} aria-label={translateUi("Username")} autoComplete="off" />}
        {server.type !== "plex" && <input className="st-input" value={draft.password} onChange={(e) => set({ password: e.target.value })} placeholder={translateUi("New password (optional)")} aria-label={translateUi("Password")} type="password" autoComplete="new-password" />}
        <div className="st-form-actions">
          <button type="button" className="st-btn" disabled={Boolean(busy)} onClick={() => void run(false)}>{translateUi(busy === "test" ? "Testing…" : "Test")}</button>
          <button type="button" className="st-btn is-primary" disabled={Boolean(busy)} onClick={() => void run(true)}>{(busy === "save" ? translateUi("Saving…") : connectionChanged ? translateUi("Test and save") : translateUi("Save"))}</button>
          <button type="button" className="st-btn is-ghost" onClick={onClose}>{translateUi("Cancel")}</button>
        </div>
      </div>
      {libraries.length > 0 && (
        <>
          <div className="st-subhead" style={{ padding: "14px 0 2px" }}>
            <h4>{translateUi("Libraries")}</h4><ScopeBadge scope="all" />
          </div>
          <p className="st-hint" style={{ padding: "0 0 6px" }}>{translateUi("Libraries that are off are not searched for sources and get no Home row on the Android and TV app.")}</p>
          <ul className="st-libraries">
            {libraries.map((library) => (
              <li key={library.id}>
                <span>{library.name || library.id} <small>{library.type}</small></span>
                <Toggle label={library.name || library.id} value={library.enabled !== false}
                  onChange={(enabled) => onSave({ ...server, collections: libraries.map((item) => item.id === library.id ? { ...item, enabled } : item) })} />
              </li>
            ))}
          </ul>
        </>
      )}
    </div>
  );
}
