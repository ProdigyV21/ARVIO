"use client";

import { Plus, Puzzle, Server, Tv, X } from "lucide-react";
import { useCallback, useEffect, useMemo, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { useAppPick } from "@/lib/store";
import { requestSourceSettings, sourceSetupState } from "@/lib/sourceSetup";
import { isSourcePromptDismissed, isSourcePromptExpected, setSourcePromptDismissed, setSourcePromptExpected, SOURCE_PROMPT_EVENT } from "@/lib/sourcePrompt";

/**
 * Per-profile dismissal of the "Add a source" banner. It persists in localStorage
 * (sessionStorage was cleared on every launch of an iPhone home-screen app, so
 * the banner came back each time) and is shared between the banner and the
 * quieter "Add a source" entry point on Home.
 */
function useSourcePrompt() {
  const { view, section, activeProfile, addons, addonsReady, settings } = useAppPick("view", "section", "activeProfile", "addons", "addonsReady", "settings");
  const profileId = activeProfile?.id ?? "";
  const [dismissal, setDismissal] = useState<{ profileId: string; dismissed: boolean } | null>(null);

  useEffect(() => {
    const read = () => setDismissal({ profileId, dismissed: isSourcePromptDismissed(profileId) });
    read();
    window.addEventListener(SOURCE_PROMPT_EVENT, read);
    return () => window.removeEventListener(SOURCE_PROMPT_EVENT, read);
  }, [profileId]);

  const sources = sourceSetupState(addons, settings);
  // Until the cloud addon pull lands, trust the last answer for this profile
  // (no late layout shift); afterwards record the real one for next launch.
  const expected = useMemo(() => isSourcePromptExpected(profileId), [profileId]);
  useEffect(() => {
    if (addonsReady && profileId) setSourcePromptExpected(profileId, !sources.hasAny);
  }, [addonsReady, profileId, sources.hasAny]);
  const eligible = view === "app" && section === "home" && Boolean(profileId) && (addonsReady || expected) && !sources.hasAny &&
    dismissal?.profileId === profileId;
  const setDismissed = useCallback((dismissed: boolean) => {
    setSourcePromptDismissed(profileId, dismissed);
    setDismissal({ profileId, dismissed });
  }, [profileId]);
  return { eligible, dismissed: Boolean(dismissal?.dismissed), sources, setDismissed };
}

export function NoAddonsPrompt() {
  const translateUi = useTranslation();
  const { setSection } = useAppPick("setSection");
  const { eligible, dismissed, sources, setDismissed } = useSourcePrompt();
  if (!eligible || dismissed) return null;

  const openSettings = (target: "homeserver" | "tv") => {
    requestSourceSettings(target);
    setSection("settings");
  };

  return <aside className="source-setup-card" aria-labelledby="source-setup-title">
    <div className="source-setup-copy">
      <h2 id="source-setup-title"><Puzzle size={20} aria-hidden />{translateUi("Add a source to start watching")}</h2>
      <p>{translateUi("ARVIO plays from your own Plex, Jellyfin or Emby server, your IPTV playlist, or addons from services you are authorized to use. Sources you added in the ARVIO Android app sync here automatically.")}</p>
      {sources.catalogOnly && <p className="source-setup-warning">{translateUi("None of your enabled addons provide playback sources.")}</p>}
    </div>
    <div className="source-setup-actions">
      <button type="button" className="primary" onClick={() => setSection("addons")}><Puzzle size={17} aria-hidden />{translateUi("Add an addon")}</button>
      <button type="button" className="secondary" onClick={() => openSettings("homeserver")}><Server size={17} aria-hidden />{translateUi("Connect home server")}</button>
      <button type="button" className="secondary" onClick={() => openSettings("tv")}><Tv size={17} aria-hidden />{translateUi("Add IPTV")}</button>
    </div>
    <button type="button" className="no-addons-close" onClick={() => setDismissed(true)} aria-label={translateUi("Close")}><X size={20} /></button>
  </aside>;
}

/** The quiet entry point that replaces a dismissed banner: reopens it. */
export function AddSourceChip() {
  const translateUi = useTranslation();
  const { eligible, dismissed, setDismissed } = useSourcePrompt();
  if (!eligible || !dismissed) return null;
  return <button type="button" className="add-source-chip" onClick={() => {
    setDismissed(false);
    window.scrollTo({ top: 0, behavior: "smooth" });
  }}><Plus size={16} aria-hidden />{translateUi("Add a source")}</button>;
}
