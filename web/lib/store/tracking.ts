import { weTrakrClient } from "../wetrakr";
// App store (lib/store.tsx): Trakt, Simkl and MDBList: connect and disconnect, read routing, lists and libraries.
import { useCallback, useEffect, useRef } from "react";
import { HttpError } from "../http";
import { saveCloudTrackingSelection } from "../cloud";
import { dedupeMedia, hydrateTraktItems, traktItemToMedia } from "../mappers";
import { loadStored } from "../storage";
import { mdblistClient, type MdbListDeviceCode, type MdbListOAuthToken } from "../mdblist";
import { simklClient } from "../simkl";
import { loadTrackingPreferences, saveTrackingPreferences, type TrackingPreferences } from "../sync";
import type { MediaItem } from "../types";
import { authClient, traktClient } from "./clients";
import { saveCachedList } from "./listCache";
import type { useRefreshData } from "./refresh";
import type { StoreCore } from "./core";

type MdblistAttempt = {
  profileId: string | null;
  accountId: string | null;
  controller: AbortController;
  code: MdbListDeviceCode | null;
  polling: Promise<void> | null;
  intervalMs: number;
  nextPollAt: number;
};

export function useTrackingActions(store: StoreCore & Pick<ReturnType<typeof useRefreshData>, "refreshData">) {
  const { activeProfileIdRef, deviceCodeRef, mdblistDeviceCodeRef, refreshData, setDeviceCode, setMdblistConnected, setMdblistDeviceCode, setSimklConnected, setSimklDeviceCode, setTrackingPreferences, setTraktConnected, simklDeviceCodeRef } = store;
  const mdblistAttempt = useRef<MdblistAttempt | null>(null);
  const cancelMdblistLink = useCallback(() => {
    mdblistAttempt.current?.controller.abort();
    mdblistAttempt.current = null;
    mdblistDeviceCodeRef.current = null;
    setMdblistDeviceCode(null);
  }, []);
  useEffect(() => {
    cancelMdblistLink();
    return cancelMdblistLink;
  }, [store.activeProfileId, store.auth?.userId, cancelMdblistLink]);
  const isCurrentMdblistAttempt = useCallback((attempt: MdblistAttempt) =>
    mdblistAttempt.current === attempt && !attempt.controller.signal.aborted &&
    activeProfileIdRef.current === attempt.profileId && (authClient.session?.userId ?? null) === attempt.accountId, []);
  const newMdblistAttempt = useCallback(() => {
    cancelMdblistLink();
    const attempt: MdblistAttempt = { profileId: activeProfileIdRef.current, accountId: authClient.session?.userId ?? null,
      controller: new AbortController(), code: null, polling: null, intervalMs: 5000, nextPollAt: 0 };
    mdblistAttempt.current = attempt;
    mdblistClient.setProfile(attempt.profileId);
    return attempt;
  }, [cancelMdblistLink]);
  const beginTrakt = useCallback(async () => {
    traktClient.setProfile(activeProfileIdRef.current);
    setDeviceCode(await traktClient.beginDeviceLink());
  }, []);

  const pollTrakt = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    const code = deviceCodeRef.current;
    if (!code) return;
    const token = await traktClient.pollDeviceToken(code.device_code);
    if (activeProfileIdRef.current !== targetProfileId) return;
    traktClient.setProfile(targetProfileId);
    mdblistClient.setProfile(targetProfileId);
    simklClient.setProfile(targetProfileId);
    traktClient.setToken(token);
    setTraktConnected(true);
    setDeviceCode(null);
    const previousPreferences = loadTrackingPreferences(targetProfileId);
    const upgradeMode = (current: TrackingPreferences["watchlistReadMode"]) =>
      current === "auto" ? "trakt" as const : current;
    const nextPreferences: TrackingPreferences = {
      ...previousPreferences,
      watchlistReadMode: upgradeMode(previousPreferences.watchlistReadMode),
      continueWatchingReadMode: upgradeMode(previousPreferences.continueWatchingReadMode),
      watchedReadMode: upgradeMode(previousPreferences.watchedReadMode),
      writeToTrakt: true,
      writeToSimkl: simklClient.isConnected && previousPreferences.writeToSimkl
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: "TRAKT",
        traktToken: token,
        mdbListApiKey: mdblistClient.key,
        mdbListAccessToken: mdblistClient.token?.accessToken ?? null,
        mdbListRefreshToken: mdblistClient.token?.refreshToken ?? null,
        mdbListTokenExpiresAt: mdblistClient.token?.expiresAt ?? null,
        simklToken: simklClient.token,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "trakt"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const disconnectTrakt = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    traktClient.setProfile(targetProfileId);
    traktClient.disconnect();
    setTraktConnected(false);
    const current = loadTrackingPreferences(targetProfileId);
    const fallback = simklClient.isConnected ? "simkl" as const : mdblistClient.isConnected ? "mdblist" as const : "auto" as const;
    const replaceTrakt = (mode: typeof current.watchlistReadMode) =>
      mode === "trakt" || mode === "both" ? fallback : mode;
    const nextPreferences: TrackingPreferences = {
      ...current,
      watchlistReadMode: replaceTrakt(current.watchlistReadMode),
      continueWatchingReadMode: replaceTrakt(current.continueWatchingReadMode),
      watchedReadMode: replaceTrakt(current.watchedReadMode),
      writeToTrakt: false,
      writeToSimkl: simklClient.isConnected && current.writeToSimkl
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: simklClient.isConnected ? "SIMKL" : mdblistClient.isConnected ? "MDBLIST" : "NONE",
        traktToken: null,
        mdbListApiKey: mdblistClient.key,
        mdbListAccessToken: mdblistClient.token?.accessToken ?? null,
        mdbListRefreshToken: mdblistClient.token?.refreshToken ?? null,
        mdbListTokenExpiresAt: mdblistClient.token?.expiresAt ?? null,
        simklToken: simklClient.token,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "trakt"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const finalizeMdblistConnection = useCallback(async (
    attempt: MdblistAttempt,
    credential: { apiKey: string | null; token: MdbListOAuthToken | null }
  ) => {
    if (!isCurrentMdblistAttempt(attempt)) return;
    const targetProfileId = attempt.profileId;
    traktClient.setProfile(targetProfileId);
    mdblistClient.setProfile(targetProfileId);
    simklClient.setProfile(targetProfileId);
    if (credential.token) mdblistClient.setToken(credential.token);
    else mdblistClient.setKey(credential.apiKey);
    setMdblistConnected(true);
    mdblistDeviceCodeRef.current = null;
    setMdblistDeviceCode(null);
    const currentPreferences = loadTrackingPreferences(targetProfileId);
    const hasPrimaryTracker = traktClient.isConnected || simklClient.isConnected;
    const mdbPreferences: TrackingPreferences = {
      watchlistReadMode: hasPrimaryTracker ? currentPreferences.watchlistReadMode : "mdblist",
      continueWatchingReadMode: hasPrimaryTracker ? currentPreferences.continueWatchingReadMode : "mdblist",
      watchedReadMode: hasPrimaryTracker ? currentPreferences.watchedReadMode : "mdblist",
      writeToTrakt: traktClient.isConnected && currentPreferences.writeToTrakt,
      writeToSimkl: simklClient.isConnected && currentPreferences.writeToSimkl
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, mdbPreferences);
    setTrackingPreferences(mdbPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: traktClient.isConnected ? "TRAKT" : simklClient.isConnected ? "SIMKL" : "MDBLIST",
        traktToken: traktClient.token,
        mdbListApiKey: credential.apiKey,
        mdbListAccessToken: credential.token?.accessToken ?? null,
        mdbListRefreshToken: credential.token?.refreshToken ?? null,
        mdbListTokenExpiresAt: credential.token?.expiresAt ?? null,
        simklToken: simklClient.token,
        trackingPreferences: mdbPreferences,
        changedDomains: ["routing", "mdblist"]
      }).catch(() => undefined);
    }
    if (isCurrentMdblistAttempt(attempt)) {
      await refreshData(targetProfileId);
    }
    if (isCurrentMdblistAttempt(attempt)) mdblistAttempt.current = null;
  }, [refreshData, isCurrentMdblistAttempt]);

  const beginMdblist = useCallback(async () => {
    const attempt = newMdblistAttempt();
    try {
      const code = await mdblistClient.beginDeviceLink(attempt.controller.signal);
      if (!isCurrentMdblistAttempt(attempt)) return;
      attempt.code = code;
      attempt.intervalMs = Math.max(5, code.interval || 5) * 1000;
      mdblistDeviceCodeRef.current = code;
      setMdblistDeviceCode(code);
    } catch (error) {
      if (isCurrentMdblistAttempt(attempt)) throw error;
    }
  }, [newMdblistAttempt, isCurrentMdblistAttempt]);

  const pollMdblist = useCallback((): Promise<void> => {
    const attempt = mdblistAttempt.current;
    const code = mdblistDeviceCodeRef.current;
    if (!attempt || !code || attempt.code !== code || !isCurrentMdblistAttempt(attempt)) return Promise.resolve();
    if (attempt.polling) return attempt.polling;
    // Both the timer and the manual button share one exchange and one commit.
    const task = Promise.resolve().then(async () => {
      if (!isCurrentMdblistAttempt(attempt)) return;
      if (code.expiresAt && Date.now() >= code.expiresAt) {
        throw new HttpError(400, "This MDBList code has expired. Get a new code and try again.");
      }
      if (Date.now() < attempt.nextPollAt) {
        throw new HttpError(400, "MDBList has not approved this device yet.", String(Math.ceil((attempt.nextPollAt - Date.now()) / 1000)));
      }
      try {
        const token = await mdblistClient.pollDeviceToken(code.device_code, attempt.controller.signal);
        if (!isCurrentMdblistAttempt(attempt) || mdblistDeviceCodeRef.current !== code) return;
        await finalizeMdblistConnection(attempt, { apiKey: null, token });
      } catch (error) {
        if (!isCurrentMdblistAttempt(attempt)) return;
        if (error instanceof HttpError && (/limiting|slow_down/i.test(error.message) || error.status === 429)) attempt.intervalMs += 5000;
        const retryAfter = (error as { retryAfter?: string | null })?.retryAfter;
        const retryMs = retryAfter ? (/^\d+$/.test(retryAfter) ? Number(retryAfter) * 1000 : Date.parse(retryAfter) - Date.now()) : 0;
        attempt.nextPollAt = Date.now() + Math.max(attempt.intervalMs, Number.isFinite(retryMs) ? Math.min(86_400_000, retryMs) : 0);
        throw error;
      }
    }).finally(() => { if (attempt.polling === task) attempt.polling = null; });
    attempt.polling = task;
    return task;
  }, [finalizeMdblistConnection, isCurrentMdblistAttempt]);

  const connectMdblist = useCallback(async (key: string) => {
    const attempt = newMdblistAttempt();
    const ok = await mdblistClient.validateKey(key, attempt.controller.signal);
    if (!isCurrentMdblistAttempt(attempt)) return;
    if (!ok) throw new Error("Invalid MDBList API key");
    await finalizeMdblistConnection(attempt, { apiKey: key, token: null });
  }, [finalizeMdblistConnection, newMdblistAttempt, isCurrentMdblistAttempt]);

  const disconnectMdblist = useCallback(async () => {
    cancelMdblistLink();
    const targetProfileId = activeProfileIdRef.current;
    mdblistClient.setProfile(targetProfileId);
    mdblistClient.disconnect();
    setMdblistConnected(false);
    setMdblistDeviceCode(null);
    const current = loadTrackingPreferences(targetProfileId);
    const fallback = traktClient.isConnected
        ? "trakt" as const
        : simklClient.isConnected
          ? "simkl" as const
          : "auto" as const;
    const replaceMdbList = (mode: TrackingPreferences["watchlistReadMode"]) => mode === "mdblist" ? fallback : mode;
    const nextPreferences: TrackingPreferences = {
      ...current,
      watchlistReadMode: replaceMdbList(current.watchlistReadMode),
      continueWatchingReadMode: replaceMdbList(current.continueWatchingReadMode),
      watchedReadMode: replaceMdbList(current.watchedReadMode),
      writeToTrakt: traktClient.isConnected && current.writeToTrakt,
      writeToSimkl: simklClient.isConnected && current.writeToSimkl
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: traktClient.isConnected ? "TRAKT" : simklClient.isConnected ? "SIMKL" : "NONE",
        traktToken: traktClient.token,
        mdbListApiKey: null,
        mdbListAccessToken: null,
        mdbListRefreshToken: null,
        mdbListTokenExpiresAt: null,
        simklToken: simklClient.token,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "mdblist"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData, cancelMdblistLink]);

  const beginSimkl = useCallback(async () => {
    simklClient.setProfile(activeProfileIdRef.current);
    setSimklDeviceCode(await simklClient.beginPinAuth());
  }, []);

  const pollSimkl = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    const code = simklDeviceCodeRef.current;
    if (!code) return;
    const token = await simklClient.pollPinToken(code.user_code);
    if (!token) {
      throw new Error("Simkl has not approved this PIN yet. Please approve the code on Simkl.");
    }
    if (activeProfileIdRef.current !== targetProfileId) return;
    traktClient.setProfile(targetProfileId);
    mdblistClient.setProfile(targetProfileId);
    simklClient.setProfile(targetProfileId);
    simklClient.setToken(token);
    setSimklConnected(true);
    setSimklDeviceCode(null);
    const previousPreferences = loadTrackingPreferences(targetProfileId);
    const mode = traktClient.isConnected ? "trakt" as const : "simkl" as const;
    const upgradeMode = (current: TrackingPreferences["watchlistReadMode"]) =>
      current === "auto" ? mode : current;
    const nextPreferences: TrackingPreferences = {
      ...previousPreferences,
      watchlistReadMode: upgradeMode(previousPreferences.watchlistReadMode),
      continueWatchingReadMode: upgradeMode(previousPreferences.continueWatchingReadMode),
      watchedReadMode: upgradeMode(previousPreferences.watchedReadMode),
      writeToTrakt: traktClient.isConnected && previousPreferences.writeToTrakt,
      writeToSimkl: true
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: traktClient.isConnected ? "TRAKT" : "SIMKL",
        traktToken: traktClient.token,
        mdbListApiKey: mdblistClient.key,
        mdbListAccessToken: mdblistClient.token?.accessToken ?? null,
        mdbListRefreshToken: mdblistClient.token?.refreshToken ?? null,
        mdbListTokenExpiresAt: mdblistClient.token?.expiresAt ?? null,
        simklToken: token,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "simkl"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const disconnectSimkl = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    simklClient.setProfile(targetProfileId);
    const disconnect = simklClient.disconnect().catch(() => undefined);
    setSimklConnected(false);
    await disconnect;
    if (activeProfileIdRef.current !== targetProfileId) return;
    const current = loadTrackingPreferences(targetProfileId);
    const fallback = traktClient.isConnected ? "trakt" as const : mdblistClient.isConnected ? "mdblist" as const : "auto" as const;
    const replaceSimkl = (mode: typeof current.watchlistReadMode) =>
      mode === "simkl" || mode === "both" ? fallback : mode;
    const nextPreferences: TrackingPreferences = {
      ...current,
      watchlistReadMode: replaceSimkl(current.watchlistReadMode),
      continueWatchingReadMode: replaceSimkl(current.continueWatchingReadMode),
      watchedReadMode: replaceSimkl(current.watchedReadMode),
      writeToTrakt: traktClient.isConnected && current.writeToTrakt,
      writeToSimkl: false
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: traktClient.isConnected ? "TRAKT" : mdblistClient.isConnected ? "MDBLIST" : "NONE",
        traktToken: traktClient.token,
        mdbListApiKey: mdblistClient.key,
        mdbListAccessToken: mdblistClient.token?.accessToken ?? null,
        mdbListRefreshToken: mdblistClient.token?.refreshToken ?? null,
        mdbListTokenExpiresAt: mdblistClient.token?.expiresAt ?? null,
        simklToken: null,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "simkl"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const updateTrackingPreferences = useCallback(async (patch: Partial<TrackingPreferences>) => {
    const profileId = activeProfileIdRef.current;
    if (!profileId) return;
    const next = saveTrackingPreferences(profileId, {
      ...loadTrackingPreferences(profileId),
      ...patch
    });
    setTrackingPreferences(next);
    await saveCloudTrackingSelection(authClient, profileId, {
      provider: traktClient.isConnected
        ? "TRAKT"
        : simklClient.isConnected
          ? "SIMKL"
          : mdblistClient.isConnected
            ? "MDBLIST"
            : weTrakrClient.isConnected ? "WETRAKR" : "NONE",
      traktToken: traktClient.token,
      mdbListApiKey: mdblistClient.key,
      mdbListAccessToken: mdblistClient.token?.accessToken ?? null,
      mdbListRefreshToken: mdblistClient.token?.refreshToken ?? null,
      mdbListTokenExpiresAt: mdblistClient.token?.expiresAt ?? null,
      simklToken: simklClient.token,
      trackingPreferences: next,
      changedDomains: ["routing"]
    }).catch(() => undefined);
    await refreshData(profileId);
  }, [refreshData]);

  // Watchlist list-source switcher. Returns the user's custom Trakt lists to
  // populate the dropdown (built-in Watchlist/Collection are added by the UI).
  const loadTraktLists = useCallback(async (): Promise<Array<{ id: string; name: string }>> => {
    if (!traktClient.isConnected) return [];
    const lists = await traktClient.userLists();
    return (lists as Array<Record<string, unknown>>)
      .map((list) => {
        const ids = (list.ids ?? {}) as Record<string, unknown>;
        const id = String(ids.trakt ?? ids.slug ?? "");
        const name = String(list.name ?? "").trim();
        return id && name ? { id, name } : null;
      })
      .filter((v): v is { id: string; name: string } => Boolean(v));
  }, []);

  // Fetch + hydrate a chosen list source into MediaItems. `source` is one of:
  // "watchlist", "collection", or "list:<id>".
  const fetchTraktListItems = useCallback(async (source: string, cacheKey: string): Promise<MediaItem[]> => {
    let rows: unknown[] = [];
    if (source === "collection") {
      const [movies, shows] = await Promise.all([
        traktClient.collection("movies"),
        traktClient.collection("shows")
      ]);
      rows = [...(movies as unknown[]), ...(shows as unknown[])];
    } else if (source.startsWith("list:")) {
      rows = await traktClient.listItems(source.slice(5));
    } else {
      rows = await traktClient.watchlist();
    }
    const hydrated = await hydrateTraktItems(rows.map(traktItemToMedia));
    saveCachedList(cacheKey, hydrated, 200);
    return hydrated;
  }, []);

  // Returns cached items instantly (with a background refresh) when fresh, else
  // fetches. Cached per-source for instant re-selection.
  const loadTraktListItems = useCallback(async (source: string): Promise<MediaItem[]> => {
    if (!traktClient.isConnected) return [];
    const cacheKey = `arvio.web.traktlist.v2:${authClient.session?.userId ?? "local"}:${traktClient.currentProfileId}:${source}`;
    try {
      const cached = loadStored<{ at: number; items: MediaItem[] } | null>(cacheKey, null);
      if (cached?.items?.length && Date.now() - cached.at < 30 * 60 * 1000) {
        void fetchTraktListItems(source, cacheKey).catch(() => undefined);
        return cached.items;
      }
    } catch {
      // ignore cache errors
    }
    return fetchTraktListItems(source, cacheKey);
  }, [fetchTraktListItems]);

  const loadTrackerLibrary = useCallback(async (provider: "trakt" | "simkl" | "wetrakr", source: string): Promise<MediaItem[]> => {
    if (provider === "trakt") {
      if (!traktClient.isConnected) throw new Error("Connect Trakt in Settings.");
      if (source === "watched") {
        const [movies, shows] = await Promise.all([traktClient.watched("movies"), traktClient.watched("shows")]);
        return hydrateTraktItems(dedupeMedia([...(movies as unknown[]), ...(shows as unknown[])].map(traktItemToMedia)));
      }
      return fetchTraktListItems(source, `arvio.web.traktlist.v2:${authClient.session?.userId ?? "local"}:${traktClient.currentProfileId}:${source}`);
    }
    if (provider === "wetrakr") {
      if (!weTrakrClient.isConnected) throw new Error("Connect WeTrakr in Settings.");
      return hydrateTraktItems(dedupeMedia((await weTrakrClient.library(source)).map(traktItemToMedia)));
    }
    if (!simklClient.isConnected) throw new Error("Connect Simkl in Settings.");
    if (!["plantowatch", "watching", "completed", "hold", "dropped"].includes(source)) throw new Error("Unknown Simkl library");
    const rows = await simklClient.library(source as "plantowatch" | "watching" | "completed" | "hold" | "dropped");
    return hydrateTraktItems(dedupeMedia(rows.map(traktItemToMedia)));
  }, [fetchTraktListItems]);
  return { beginTrakt, pollTrakt, disconnectTrakt, beginMdblist, pollMdblist, cancelMdblistLink, connectMdblist, disconnectMdblist, beginSimkl, pollSimkl, disconnectSimkl, updateTrackingPreferences, loadTraktLists, fetchTraktListItems, loadTraktListItems, loadTrackerLibrary };
}
