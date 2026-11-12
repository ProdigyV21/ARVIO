"use client";
// Settings → Live TV → Stalker portals: the Android app's multi-portal list
// (iptvByProfile.<id>.stalkerPortals). Add, edit, test, remove, reorder and
// switch portals on/off. Each portal is its own source on the Live TV page.

import "./liveTvSettings.css";
import { Pencil, Plus, Trash2 } from "lucide-react";
import { useRef, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { testIptvPlaylist } from "@/lib/iptv";
import { playlistErrorCause } from "@/lib/iptvGuide";
import { maskedUrl } from "@/lib/maskUrl";
import { moveIndex } from "@/lib/ordering";
import { MAX_STALKER_PORTALS, nextStalkerPortalId, portalDraftProblem, referencedStalkerPortalIds, stalkerPortalsOf, stalkerPortalsPatch, upsertStalkerPortal } from "@/lib/stalkerPortals";
import type { AppSettings, StalkerPortalEntry } from "@/lib/types";
import { usePlaylistErrorText, usePortalProblemText } from "@/components/livetv/PlaylistManager";
import { SortableList } from "./SortableList";
import { Empty, Group, ScopeBadge, Status, Toggle } from "./ui";

type Draft = { name: string; url: string; mac: string };
type TestState = { state: "idle" } | { state: "testing" } | { state: "ok"; channels: number } | { state: "error"; text: string };
const emptyDraft: Draft = { name: "", url: "", mac: "" };

export function StalkerPortalSettings({ settings, updateSettings }: {
  settings: AppSettings;
  updateSettings: (patch: Partial<AppSettings>) => void;
}) {
  const translateUi = useTranslation();
  const portals = stalkerPortalsOf(settings);
  const [editing, setEditing] = useState<string | null>(null);
  const save = (next: StalkerPortalEntry[]) => updateSettings(stalkerPortalsPatch(next));
  const editingPortal = portals.find((portal) => portal.id === editing) ?? null;
  const full = portals.length >= MAX_STALKER_PORTALS;

  return (
    <>
      <Group title={`${translateUi("Stalker portals")} · ${portals.length}`} description={<>
        {translateUi("For providers that give you a portal address and a MAC address instead of a playlist. Each portal is its own source in Live TV, with its own categories.")} <ScopeBadge scope="all" />
      </>}>
        {portals.length === 0 ? <Empty>{translateUi("No Stalker portals configured.")}</Empty> : (
          <SortableList
            items={portals}
            label={translateUi("Stalker portals")}
            getKey={(portal) => portal.id}
            getLabel={(portal) => portal.name}
            onMove={(from, to) => save(moveIndex(portals, from, to))}
            edgeButtons={portals.length > 2}
            rowClassName={(portal) => portal.enabled !== false ? "" : "is-off"}
            renderMain={(portal) => (
              <>
                <span className="st-sort-title"><span className="st-sort-name">{portal.name}</span></span>
                <span className="st-sort-sub">{maskedUrl(portal.portalUrl)} · {portal.macAddress}</span>
                <span className="st-import-flags">
                  <span className="st-sort-sub">{translateUi("Import on Android and TV")}</span>
                  {([["importLiveTv", "Live channels"], ["importVod", "Movies"], ["importSeries", "Series"]] as const).map(([flag, label]) => (
                    <label key={flag} className="st-check">
                      <input type="checkbox" checked={portal[flag] !== false}
                        onChange={(event) => save(portals.map((item) => item.id === portal.id ? { ...item, [flag]: event.target.checked } : item))} />
                      {translateUi(label)}
                    </label>
                  ))}
                </span>
              </>
            )}
            renderActions={(portal) => (
              <>
                <Toggle label={portal.name} value={portal.enabled !== false}
                  onChange={(enabled) => save(portals.map((item) => item.id === portal.id ? { ...item, enabled } : item))} />
                <button type="button" className="st-icon-btn" aria-pressed={editing === portal.id} aria-label={translateUi("Edit {value0}", { value0: portal.name })} title={translateUi("Edit")}
                  onClick={() => setEditing(editing === portal.id ? null : portal.id)}><Pencil size={17} /></button>
                <button type="button" className="st-icon-btn is-danger" aria-label={translateUi("Remove {value0}", { value0: portal.name })} title={translateUi("Remove")}
                  onClick={() => { if (editing === portal.id) setEditing(null); save(portals.filter((item) => item.id !== portal.id)); }}><Trash2 size={17} /></button>
              </>
            )}
          />
        )}
        {editingPortal && (
          <div className="st-edit-panel" role="region" aria-label={translateUi("Edit {value0}", { value0: editingPortal.name })}>
            <div className="st-subhead stalker-edit-head"><h4>{translateUi("Edit {value0}", { value0: editingPortal.name })}</h4></div>
            <PortalForm key={editingPortal.id} portal={editingPortal} portals={portals} userAgent={settings.customUserAgent}
              submitLabel={translateUi("Save")}
              onSubmit={(draft) => { save(upsertStalkerPortal(portals, { id: editingPortal.id, name: draft.name, portalUrl: draft.url, macAddress: draft.mac })); setEditing(null); }}
              onCancel={() => setEditing(null)} />
          </div>
        )}
      </Group>
      <Group title="Add a Stalker portal" description={full ? translateUi("You can add up to {value0} portals.", { value0: MAX_STALKER_PORTALS }) : undefined}>
        {!full && <PortalForm portals={portals} userAgent={settings.customUserAgent} submitLabel={translateUi("Add portal")} resetOnSubmit
          // A new portal never reuses the id of a removed one that settings still refer to.
          onSubmit={(draft) => save(upsertStalkerPortal(portals, { name: draft.name, portalUrl: draft.url, macAddress: draft.mac }, referencedStalkerPortalIds(settings)))} />}
      </Group>
    </>
  );
}

/** Name, portal address and MAC, with a Test button that loads the portal's channel list. */
export function PortalForm({ portal, portals, userAgent, submitLabel, resetOnSubmit, onSubmit, onCancel }: {
  portal?: StalkerPortalEntry;
  portals: StalkerPortalEntry[];
  userAgent?: string;
  submitLabel: string;
  resetOnSubmit?: boolean;
  onSubmit: (draft: Draft) => void;
  onCancel?: () => void;
}) {
  const translateUi = useTranslation();
  const causeText = usePlaylistErrorText();
  const problemText = usePortalProblemText();
  const [draft, setDraft] = useState<Draft>(() => portal ? { name: portal.name, url: portal.portalUrl, mac: portal.macAddress } : emptyDraft);
  const [test, setTest] = useState<TestState>({ state: "idle" });
  const [error, setError] = useState("");
  const run = useRef(0);
  const set = (patch: Partial<Draft>) => { setDraft((current) => ({ ...current, ...patch })); setTest({ state: "idle" }); setError(""); run.current++; };
  const check = () => {
    const problem = portalDraftProblem(draft.url, draft.mac);
    if (problem) setError(problemText(problem));
    return !problem;
  };
  const runTest = async () => {
    if (!check()) return;
    const id = portal?.id ?? nextStalkerPortalId(portals.map((item) => item.id)) ?? "stalker1";
    const [candidate] = upsertStalkerPortal([], { name: draft.name, portalUrl: draft.url, macAddress: draft.mac });
    const current = ++run.current;
    setTest({ state: "testing" });
    const result = await testIptvPlaylist(null, { stalkerPortals: [{ ...candidate, id }], userAgent });
    if (current !== run.current) return;
    setTest(result.ok ? { state: "ok", channels: result.channels } : { state: "error", text: causeText(playlistErrorCause(result.message)) });
  };
  const submit = () => {
    if (!check()) return;
    run.current++;
    onSubmit(draft);
    if (resetOnSubmit) { setDraft(emptyDraft); setTest({ state: "idle" }); }
  };

  return (
    <form className="st-form" onSubmit={(event) => { event.preventDefault(); submit(); }}>
      <input className="st-input" value={draft.name} onChange={(event) => set({ name: event.target.value })}
        placeholder={translateUi("Name (e.g. Portal 1)")} aria-label={translateUi("Portal name")} autoComplete="off" />
      <input className="st-input" value={draft.url} onChange={(event) => set({ url: event.target.value })} inputMode="url"
        placeholder="http://portal.example/c/" aria-label={translateUi("Portal address")} autoComplete="off" autoCapitalize="none" spellCheck={false} />
      <input className="st-input" value={draft.mac} onChange={(event) => set({ mac: event.target.value })}
        placeholder="00:1A:79:00:00:00" aria-label={translateUi("MAC address")} autoComplete="off" autoCapitalize="characters" spellCheck={false} />
      {(error || test.state !== "idle") && (
        <p className="st-row-hint stalker-test" role={error ? "alert" : "status"}>
          {error ? <Status tone="warn">{error}</Status>
            : test.state === "testing" ? <Status tone="busy">{translateUi("Testing…")}</Status>
            : test.state === "ok" ? <Status tone="ok">{translateUi("Works: {value0} channels found.", { value0: test.channels.toLocaleString() })}</Status>
            : test.state === "error" ? <Status tone="warn">{test.text}</Status> : null}
        </p>
      )}
      <div className="st-form-actions">
        <button type="button" className="st-btn" disabled={test.state === "testing"} onClick={() => void runTest()}>{translateUi("Test")}</button>
        <button type="submit" className="st-btn is-primary">{!portal && <Plus size={16} />} {submitLabel}</button>
        {onCancel && <button type="button" className="st-btn is-ghost" onClick={onCancel}>{translateUi("Cancel")}</button>}
      </div>
    </form>
  );
}
