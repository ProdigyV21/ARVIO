"use client";

import { Loader2, RefreshCw, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { parsePartnerLink, partnerLinkSignature, runPartnerLink, stripPartnerLink } from "@/lib/partnerLinks";
import { resolvePartnerMedia } from "@/lib/partnerMedia";
import { getPriorityConfig, useApp } from "@/lib/store";
import type { MediaItem, NavSection } from "@/lib/types";
import styles from "./PartnerLinkHandler.module.css";

/** Mount only INSIDE EntitlementGate: a partner link is not a membership/profile bypass. */
export function PartnerLinkHandler() {
  const { partnerLinkReady, activeProfile, auth, selected, section, settings, openDetails, closePlayer } = useApp();
  const translateUi = useTranslation();
  const [search, setSearch] = useState(() => window.location.search);
  const [status, setStatus] = useState<"idle" | "loading" | "error">("idle");
  const [message, setMessage] = useState<string | null>(null);
  const [retry, setRetry] = useState(0);
  const linkKey = partnerLinkSignature(search);
  const generation = useRef(0);
  const baseline = useRef<{ selected: MediaItem | null; section: NavSection } | null>(null);
  const latest = useRef({ selected, section, profile: activeProfile?.id, account: auth?.userId });
  latest.current = { selected, section, profile: activeProfile?.id, account: auth?.userId };

  const clear = () => {
    generation.current++;
    baseline.current = null;
    // Never erase a newer link while an older request is completing/canceling.
    if (partnerLinkSignature(window.location.search) === linkKey) {
      window.history.replaceState(window.history.state, "", stripPartnerLink(window.location.href));
      setSearch(window.location.search);
    }
    setStatus("idle");
    setMessage(null);
  };

  useEffect(() => {
    const readLocation = () => setSearch(window.location.search);
    window.addEventListener("popstate", readLocation);
    window.addEventListener("hashchange", readLocation);
    return () => {
      window.removeEventListener("popstate", readLocation);
      window.removeEventListener("hashchange", readLocation);
    };
  }, []);

  useEffect(() => {
    const parsed = parsePartnerLink(search);
    if (parsed.status === "none") { setStatus("idle"); setMessage(null); return; }
    if (parsed.status === "invalid") { setStatus("error"); setMessage(parsed.error); return; }
    if (!partnerLinkReady) return;
    const currentGeneration = ++generation.current;
    const snapshot = { ...latest.current };
    baseline.current = { selected: snapshot.selected, section: snapshot.section };
    setStatus("loading");
    setMessage(null);
    const isCurrent = () => generation.current === currentGeneration && partnerLinkSignature(window.location.search) === linkKey &&
      latest.current.selected === snapshot.selected && latest.current.section === snapshot.section &&
      latest.current.profile === snapshot.profile && latest.current.account === snapshot.account;
    void runPartnerLink({
      ready: partnerLinkReady,
      isCurrent,
      resolve: () => resolvePartnerMedia(parsed.target, settings.language, getPriorityConfig(settings).customTmdbApiKey),
      open: item => {
        clear();
        closePlayer();
        // Normal details/source flow only. No playStream/playTrailer/autoplay calls.
        void openDetails(item);
      },
      error: error => { baseline.current = null; setMessage(error); setStatus("error"); }
    });
    return () => { generation.current++; baseline.current = null; };
    // Details selection/section are checked using a live ref, not restarted on user navigation.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [linkKey, retry, partnerLinkReady, activeProfile?.id, auth?.userId]);

  useEffect(() => {
    if (baseline.current && (baseline.current.selected !== selected || baseline.current.section !== section)) clear();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selected, section]);

  if (status === "idle") return null;
  const invalid = parsePartnerLink(search).status === "invalid";
  return (
    <aside className={styles.notice} role={status === "error" ? "alert" : "status"} aria-live="polite">
      <div className={styles.message}>
        {status === "loading" ? <Loader2 className={styles.spinner} size={18} aria-hidden="true" /> : null}
        <span>{translateUi(message ?? "Opening your title link…")}</span>
      </div>
      <div className={styles.actions}>
        {status === "error" && !invalid ? (
          <button type="button" onClick={() => setRetry(value => value + 1)}><RefreshCw size={16} /> {translateUi("Retry")}</button>
        ) : null}
        <button type="button" onClick={clear}><X size={16} /> {translateUi("Cancel")}</button>
      </div>
    </aside>
  );
}
