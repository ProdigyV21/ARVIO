"use client";

import { useEffect } from "react";
import { useAppPick } from "@/lib/store";
import { syncMusicProfile } from "@/lib/music/library";
import { pauseMusic, stopMusic } from "@/lib/music/player";
import { MusicPlayerBar } from "./MusicPlayerBar";

/**
 * Mounted by the app shell once the Music tab was opened, and then kept: it follows the profile
 * and language for the music library, pauses music when a video or channel starts, and shows the
 * player on every screen.
 */
export function MusicHost() {
  const { auth, activeProfile, settings, activeStream, activeChannel } = useAppPick("auth", "activeProfile", "settings", "activeStream", "activeChannel");
  useEffect(() => {
    syncMusicProfile(auth?.userId, activeProfile?.id, settings.language || "en-US", settings.homeServers);
  }, [auth?.userId, activeProfile?.id, settings.language, settings.homeServers]);
  // Leaving the music library (a kids profile, signing out) stops what was playing.
  useEffect(() => stopMusic, []);
  const videoPlaying = Boolean(activeStream || activeChannel);
  useEffect(() => {
    if (videoPlaying) pauseMusic();
  }, [videoPlaying]);
  return <MusicPlayerBar hidden={videoPlaying} />;
}
