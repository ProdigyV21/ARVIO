"use client";
import "./sportsReminder.css";
import { useEffect, useRef, useState } from "react";
import { Trophy } from "lucide-react";
import { authClient, useAppPick } from "@/lib/store";
import { useTranslation } from "@/lib/i18n";
import { setSportsFailover, sportsFailover, sportsWatchingEventId, useSportsPreferences } from "@/lib/sportsPreferences";
import { loadSportsScores } from "@/lib/sportsArtwork";
import { anyActiveGame, detectScoreAlerts, failoverCovers, followedScores, nextFailover, SPORTS_SCORE_POLL_IDLE_MS, SPORTS_SCORE_POLL_LIVE_MS,
  type SportsScoreAlert, type SportsScoreState } from "@/lib/sportsExtras";

/**
 * Score alerts for followed teams (a small card in a corner for a few seconds,
 * never taking focus) and channel failover for a game started from Sports:
 * when its channel fails for good, the next channel showing the game plays.
 */
export function SportsLiveHost() {
  const translateUi = useTranslation();
  const { activeProfile, activeChannel, playChannel, setToast } = useAppPick("activeProfile", "activeChannel", "playChannel", "setToast");
  const { follows, scoreAlerts } = useSportsPreferences(authClient, activeProfile?.id);
  const [queue, setQueue] = useState<SportsScoreAlert[]>([]);
  const followsTeams = follows.some(f => f.kind === "team");
  useEffect(() => {
    if (!scoreAlerts || !followsTeams) return;
    let active = true, timer: number | undefined;
    let seen: Map<string, SportsScoreState> | null = null;
    const poll = async () => {
      let wait = SPORTS_SCORE_POLL_IDLE_MS;
      if (!document.hidden) {
        const items = await loadSportsScores();
        if (!active) return;
        if (items) {
          const current = followedScores(items, follows);
          const alerts = detectScoreAlerts(seen, current, sportsWatchingEventId());
          if (alerts.length) setQueue(q => [...q, ...alerts].slice(-4));
          seen = new Map([...current].map(([id, entry]) => [id, entry.state]));
          if (anyActiveGame(current, Date.now())) wait = SPORTS_SCORE_POLL_LIVE_MS;
        } else wait = 2 * SPORTS_SCORE_POLL_LIVE_MS;
      } else wait = SPORTS_SCORE_POLL_LIVE_MS;
      timer = window.setTimeout(() => void poll(), wait);
    };
    void poll();
    return () => { active = false; window.clearTimeout(timer); };
  }, [scoreAlerts, followsTeams, follows]);
  const alert = queue[0];
  useEffect(() => {
    if (!alert) return;
    const timer = window.setTimeout(() => setQueue(q => q.slice(1)), 8_000);
    return () => window.clearTimeout(timer);
  }, [alert]);

  // Failover: forget it once something else plays; move on when the player gives up.
  const channelRef = useRef(activeChannel);
  channelRef.current = activeChannel;
  useEffect(() => {
    if (!failoverCovers(sportsFailover(), activeChannel?.id)) setSportsFailover(null, null);
  }, [activeChannel?.id]);
  useEffect(() => {
    // The player's final failure event does not bubble; listen in the capture phase.
    const onFailed = () => {
      const failover = sportsFailover(), failedId = channelRef.current?.id;
      if (!failover || !failedId || !failoverCovers(failover, failedId)) return;
      const next = nextFailover(failover, failedId);
      if (!next) return;
      setSportsFailover(next.failover, sportsWatchingEventId());
      setToast(translateUi("Channel failed. Trying {name}…", { name: next.channel.name }));
      window.setTimeout(() => { if (channelRef.current?.id === failedId && sportsFailover() === next.failover) playChannel(next.channel); }, 1_500);
    };
    window.addEventListener("arvio-playback-failed", onFailed, true);
    return () => window.removeEventListener("arvio-playback-failed", onFailed, true);
  }, [playChannel, setToast, translateUi]);

  if (!alert) return null;
  return <aside className="sports-score-alert" role="status" aria-live="polite">
    <p><Trophy size={15} />{alert.final ? translateUi("Full time") : translateUi("Score update")}</p>
    <strong>{alert.homeTeam} {alert.homeScore} – {alert.awayScore} {alert.awayTeam}</strong>
  </aside>;
}
