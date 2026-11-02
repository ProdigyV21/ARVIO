// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { useEffect, type RefObject, type Dispatch, type SetStateAction } from "react";
import { playbackFailureKind, classifyMediaError, diagnoseSourceFailure } from "@/lib/playerRecovery";
import { canProviderTranscode, hasDolbyVision, recordBrowserPlaybackFailure, canTryRemux } from "@/lib/streamCompatibility";
import { browserAutoplayCandidates } from "@/lib/browserAutoplay";
import { type PlaybackHandle, attachPlayback, type PlaybackError, type PlaybackTracks, type PlaybackTransport, type PlaybackMediaInfo, preferNativeHls } from "@/lib/player";
import { planLiveAttempts, attemptAllowed, providerRefusal, mayRefreshChannel, mayRetryChannel, classifyLiveFailure, liveMediaNotice, browserName, isAppleWebKit, decodeCheck, isInsecureForPage, type LiveAttempt, type LiveFailure, type LiveFault, type LivePlan } from "@/lib/liveTvPlayback";
import { getMediaCapabilities, mediaSourceConstructor } from "@/lib/capabilities";
import { currentStreamTicket } from "@/lib/streamTicket";
import { resolverMediaUrl } from "@/lib/resolver";
import { config } from "@/lib/config";
import { proxiedUrl } from "@/lib/http";
import { parseDebridStream, invalidateDebridDirectUrl, resolveDebridDirectUrl } from "@/lib/debrid";
import { REMUX_STUCK_TICKS, xtreamHlsVariant, liveTvProxyHeaders, needsBrowserHeaderRelay, workerManifestUrl, isLikelyHlsUrl, directManifestUrl } from "./helpers";
import type { StreamSource, AppSettings } from "@/lib/types";
import type { RemuxAudioTrack } from "@/lib/remux";

/** Attaches the source to the <video>: the remux path, the URL ladder (direct, relays, debrid link refresh) and the escalation to remux, provider conversion or the next source. */
export function usePlaybackEngine({ autoSourceHopsRef, currentStreamRef, failureDiagnosticRef, lastSavedRef, liveSourceOverrideRef, liveTv, liveXtreamOrigin, notify, onSelectStream, onToast, playbackIdentity, playbackRate, refreshLiveSource, remuxAudioIndexRef, remuxRestartKey, resumeAtRef, setBootStatus, setBuffering, setError, setErrorDetail, setLiveFailure, setPlayBlocked, setRemuxAudioIndex, setRemuxTracks, setShowControls, settings, setTransportTracks, sourceListRef, stream, transportRef, tryNextSource, videoRef }: { autoSourceHopsRef: RefObject<number>;
  /** A translated toast with values (Live TV notices). */
  notify?: (message: string, values?: Record<string, string | number>) => void;
  /** Re-resolve an expiring channel link (Stalker create_link); null when nothing changed. */
  refreshLiveSource?: () => Promise<{ url: string; headers?: Record<string, string> } | null>;
  /** A refreshed channel link, kept for restarts, external players and Copy link while `from` is still the stream's URL. */
  liveSourceOverrideRef?: RefObject<{ from: string; url: string; headers?: Record<string, string> } | null>;
  /** Origin of the channel's Xtream server, which makes /user/pass/<id> links Xtream ids. */
  liveXtreamOrigin?: string | null;
  setLiveFailure?: Dispatch<SetStateAction<LiveFailure | null>>; currentStreamRef: RefObject<StreamSource>; failureDiagnosticRef: RefObject<{ failure_kind: string; phase: "startup" | "playback"; transport?: string; }>; lastSavedRef: RefObject<number>; liveTv: boolean; onSelectStream: (next: StreamSource, options?: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean; }) => void; onToast: (message: string) => void; playbackIdentity: string; playbackRate: number; remuxAudioIndexRef: RefObject<number>; remuxRestartKey: number; resumeAtRef: RefObject<number>; setBootStatus: Dispatch<SetStateAction<{ label: string; values?: Record<string, string | number>; }>>; setBuffering: Dispatch<SetStateAction<boolean>>; setError: Dispatch<SetStateAction<boolean>>; setErrorDetail: Dispatch<SetStateAction<string>>; setPlayBlocked: Dispatch<SetStateAction<boolean>>; setRemuxAudioIndex: Dispatch<SetStateAction<number>>; setRemuxTracks: Dispatch<SetStateAction<RemuxAudioTrack[]>>; setShowControls: Dispatch<SetStateAction<boolean>>; settings: AppSettings; setTransportTracks: Dispatch<SetStateAction<PlaybackTracks>>; sourceListRef: RefObject<StreamSource[]>; stream: StreamSource; transportRef: RefObject<PlaybackHandle | null>; tryNextSource: () => boolean; videoRef: RefObject<HTMLVideoElement | null> }) {
  useEffect(() => {
    const video = videoRef.current;
    if (!video || !stream.url) return undefined;
    setPlayBlocked(false);

    // In-browser remux path (Tier 3): repackage an MKV direct link and play the
    // browser-safe audio track. Takes over the element entirely for this source.
    if (stream.remux) {
      transportRef.current = null;
      setTransportTracks({ audioTracks: [], qualities: [], selectedAudioTrackId: null, selectedQualityId: null });
      let cancelled = false;
      let recovering = false;
      const controller = new AbortController();
      let handle: { destroy: () => void } | null = null;
      let remuxWatchdog: number | undefined;
      setError(false);
      setErrorDetail("");
      failureDiagnosticRef.current = { failure_kind: "unknown", phase: "startup" };
      setBuffering(true);
      setBootStatus({ label: stream.convertAudio ? "Converting the audio for your browser…" : "Preparing this file for your browser…" });
      setRemuxTracks([]);
      lastSavedRef.current = 0;
      const remuxFailed = (message: string) => {
        if (cancelled || recovering) return;
        recovering = true;
        failureDiagnosticRef.current = { failure_kind: playbackFailureKind({ message }), phase: video.currentTime > 0 ? "playback" : "startup" };
        if (video.currentTime > 0) resumeAtRef.current = video.currentTime;
        window.clearInterval(remuxWatchdog);
        video.pause();
        // Callback + rejected start() + watchdog can report the same fault.
        // Convert the selected file once, before considering another source.
        // Capture the playhead before abort/destroy resets the video element.
        if (canProviderTranscode(stream) && !stream.transcoded) {
          onToast("Requesting provider conversion for this source...");
          onSelectStream(stream, { forceBrowser: true, forceTranscode: true });
        } else if (!tryNextSource()) {
          setErrorDetail(message);
          setBuffering(false); setError(true); setShowControls(true);
        }
        controller.abort();
        handle?.destroy();
        handle = null;
      };
      void (async () => {
        try {
          const { probeAndPrepareRemux } = await import("@/lib/remux");
          const directIptv = stream.addonId === "iptv_xtream_vod" && stream.originalUrl && stream.originalUrl !== stream.url;
          const prepared = await probeAndPrepareRemux(directIptv ? stream.originalUrl! : stream.url!,
            directIptv ? undefined : stream.behaviorHints?.proxyHeaders?.request, settings.audioLanguage,
            { signal: controller.signal, onError: remuxFailed, expectDolbyVision: hasDolbyVision(stream),
              fallbackUrl: directIptv ? stream.url! : undefined });
          if (cancelled || recovering) { prepared?.destroy(); return; }
          handle = prepared;
          if (!prepared || (prepared.probe.audioTracks.length > 0 && prepared.probe.chosenAudioIndex < 0) || !prepared.probe.videoPlayable) {
            const reason = !prepared ? "Browser preparation is unavailable for this source."
              : !prepared.probe.videoPlayable ? prepared.probe.videoReason ?? "This browser cannot decode the selected video track."
              : "No compatible audio track found. Use provider conversion or an external player.";
            if (prepared) recordBrowserPlaybackFailure(stream, reason, !!stream.transcoded);
            remuxFailed(reason);
            return;
          }
          setRemuxTracks(prepared.probe.audioTracks);
          const requestedTrack = prepared.probe.audioTracks.find((track) => track.index === remuxAudioIndexRef.current && track.browserPlayable);
          const startIndex = requestedTrack?.index ?? prepared.probe.chosenAudioIndex;
          setRemuxAudioIndex(startIndex);
          try {
            await prepared.start(video, startIndex, resumeAtRef.current);
          } catch (error) {
            remuxFailed(error instanceof Error ? error.message : "Browser playback could not start.");
            return;
          }
          if (cancelled || recovering) return;
          resumeAtRef.current = 0;
          void video.play().catch((reason: unknown) => {
            if (cancelled || recovering) return;
            if (reason instanceof DOMException && reason.name === "NotAllowedError") {
              setPlayBlocked(true); setBuffering(false); setShowControls(true);
            } else if (video.error) remuxFailed("Browser playback could not start.");
          });
          // A CDN can stop supplying packets without an error. Require clock
          // progress too, and use the same bounded recovery as decoder failures.
          let lastSeen = -1;
          let stuckTicks = 0;
          remuxWatchdog = window.setInterval(() => {
            if (cancelled || recovering) return;
            if (video.paused || video.ended || video.seeking) { lastSeen = video.currentTime; stuckTicks = 0; return; }
            const advancing = video.currentTime > lastSeen + 0.05;
            lastSeen = video.currentTime;
            if (advancing && video.readyState >= 2) { stuckTicks = 0; return; }
            stuckTicks += 1;
            if (stuckTicks < REMUX_STUCK_TICKS) return;
            remuxFailed("Browser preparation stopped delivering playable media.");
          }, 1000);
        } catch (error) {
          remuxFailed(error instanceof Error ? error.message : "Browser preparation failed.");
        }
      })();
      return () => {
        cancelled = true;
        controller.abort();
        window.clearInterval(remuxWatchdog);
        handle?.destroy();
        video.removeAttribute("src");
        video.load();
      };
    }

    setError(false);
    setErrorDetail("");
    setLiveFailure?.(null);
    failureDiagnosticRef.current = { failure_kind: "unknown", phase: "startup" };
    setBuffering(true);
    const hops = autoSourceHopsRef.current;
    const candidateCount = Math.max(hops + 1, browserAutoplayCandidates(sourceListRef.current).length);
    setBootStatus(hops > 0
      ? { label: "Trying next source ({value0} of {value1})", values: { value0: hops + 1, value1: candidateCount } }
      : stream.transcoded ? { label: "Waiting for the provider's conversion…" } : { label: "Connecting…" });
    lastSavedRef.current = 0;
    const headers = stream.behaviorHints?.proxyHeaders?.request;
    let handlingError = false;
    let cancelled = false;
    let detach: PlaybackHandle | undefined;
    let clearAttemptStart = () => {};
    setTransportTracks({ audioTracks: [], qualities: [], selectedAudioTrackId: null, selectedQualityId: null });
    type Rung = { url: string; transport?: PlaybackTransport; hlsEngine?: "native" | "mse"; headers?: Record<string, string>; startupMs?: number; live?: LiveAttempt };
    let currentRung: Rung = { url: stream.url };
    let noticeShown = false;
    let pendingInfo: PlaybackMediaInfo | null = null;
    let noticeTimer: number | undefined;
    // A few seconds into a live channel, say so when the picture or the sound
    // is missing because this browser cannot decode it (engines drop such
    // tracks silently): never a black screen or silence without a reason.
    const checkLiveMedia = () => {
      if (cancelled || noticeShown || !hasPlayed) return;
      const element = video as HTMLVideoElement & { webkitAudioDecodedByteCount?: number; mozHasAudio?: boolean };
      const sound = typeof element.mozHasAudio === "boolean" ? element.mozHasAudio
        : typeof element.webkitAudioDecodedByteCount === "number" ? element.webkitAudioDecodedByteCount > 0 : null;
      const notice = liveMediaNotice({ picture: video.videoWidth > 0, sound, info: pendingInfo }, liveContext());
      if (notice) { noticeShown = true; notify?.(notice.message, notice.values); }
    };
    const attach = (rung: Rung) => {
      currentRung = rung;
      const url = rung.url;
      pendingInfo = null;
      clearAttemptStart();
      // URL refreshes/relay fallbacks need their own resume listeners. Keeping
      // one persistent canplay handler would also resume a user's paused seek.
      video.addEventListener("loadedmetadata", onReadyToStart, { once: true });
      video.addEventListener("canplay", onReadyToStart, { once: true });
      clearAttemptStart = () => {
        video.removeEventListener("loadedmetadata", onReadyToStart);
        video.removeEventListener("canplay", onReadyToStart);
      };
      const handle = attachPlayback(video, url, {
        onError: handlePlaybackError, live: liveTv,
        transport: rung.transport, hlsEngine: rung.hlsEngine,
        requestHeaders: rung.headers,
        onTracks: (tracks) => { if (!cancelled) setTransportTracks(tracks); },
        // Notices wait for real frames: a track list alone is not playback.
        onMediaInfo: liveTv ? (info) => { pendingInfo = info; } : undefined
      });
      transportRef.current = handle;
      return handle;
    };
    // A source that has played gets one in-place network recovery before the
    // startup ladder is used again. Silent stalls use the separate watchdog.
    let hasPlayed = false;
    let hasEverPlayed = false;
    let retriedInterruption = false;
    // Playback ladder: direct first (free for CORS-friendly providers), then the
    // Cloudflare resolver media proxy for live TV (fixes CORS/ORB without Netlify
    // bandwidth), then the legacy Netlify fallbacks.
    const attempts: string[] = [stream.url];
    // Catch-up recordings come from the same IPTV panels as live channels, so
    // they get the live relay hops — but keep VOD controls (seekable).
    const iptvVod = stream.addonId === "iptv_xtream_vod";
    // Live channels walk their own plan (planLiveAttempts below).
    const iptvRelay = !liveTv && (stream.addonName === "Catch-up" || iptvVod);
    if (iptvRelay) {
      const hlsTwin = iptvVod ? null : xtreamHlsVariant(stream.url);
      if (hlsTwin) attempts.push(hlsTwin);
      const workerUrl = resolverMediaUrl(stream.url, { ...liveTvProxyHeaders(), ...headers });
      if (workerUrl) {
        // Browsers cannot set these source-required headers on direct requests.
        // IPTV VOD can be subscriber-IP restricted: preserve working native
        // playback before trying a different network egress through the relay.
        if (!iptvVod && needsBrowserHeaderRelay(headers)) attempts.unshift(workerUrl);
        else attempts.push(workerUrl);
      }
      if (hlsTwin) {
        const workerTwin = resolverMediaUrl(hlsTwin, { ...liveTvProxyHeaders(), ...headers });
        if (workerTwin) attempts.push(workerTwin);
        attempts.push(workerManifestUrl(hlsTwin, headers));
      }
      if (isLikelyHlsUrl(stream.url)) {
        if (workerUrl) attempts.push(workerManifestUrl(stream.url, headers));
        attempts.push(directManifestUrl(stream.url, headers));
      }
      if (config.allowNetlifyMediaProxy) {
        attempts.push(proxiedUrl(hlsTwin ?? stream.url, { ...liveTvProxyHeaders(), ...headers }));
      }
    }
    if (config.allowNetlifyMediaProxy && !headers && /^https?:\/\//i.test(stream.url)) {
      attempts.push(proxiedUrl(stream.url));
    }
    const uniqueAttempts = [...new Set(attempts)];
    let attemptIndex = 0;
    // Live TV: shape-aware engines and routes, see lib/liveTvPlayback.ts.
    const liveEnvironment = () => {
      const caps = getMediaCapabilities();
      return { pageProtocol: window.location.protocol, mse: caps.mse, nativeHls: caps.nativeHls, preferNativeHls: preferNativeHls() };
    };
    const livePlanFor = (url: string, requestHeaders?: Record<string, string>): LivePlan => planLiveAttempts({ url, headers: requestHeaders, xtreamOrigin: liveXtreamOrigin }, liveEnvironment(), {
      relay: (target) => resolverMediaUrl(target, { ...liveTvProxyHeaders(), ...requestHeaders }),
      manifestRelay: (target) => workerManifestUrl(target, requestHeaders),
      manifestDirect: (target) => directManifestUrl(target, requestHeaders),
      appProxy: config.allowNetlifyMediaProxy ? (target) => proxiedUrl(target, { ...liveTvProxyHeaders(), ...requestHeaders }) : undefined
    });
    const rungsOf = (plan: LivePlan): Rung[] => plan.attempts.map((attempt) => ({ url: attempt.url, transport: attempt.transport, hlsEngine: attempt.hlsEngine, headers: attempt.headers, startupMs: attempt.startupMs, live: attempt }));
    // A link refreshed earlier for this same stream (Stalker) outlives restarts.
    const override = liveTv && liveSourceOverrideRef?.current?.from === stream.url ? liveSourceOverrideRef.current : null;
    let liveSource = override ? { url: override.url, headers: override.headers } : { url: stream.url, headers };
    let livePlan: LivePlan | null = liveTv ? livePlanFor(liveSource.url, liveSource.headers) : null;
    let ladder: Rung[] = livePlan ? rungsOf(livePlan)
      : uniqueAttempts.map((url) => url === stream.url ? { url, transport: stream.transport, headers } : { url });
    const liveFaults: LiveFault[] = [];
    let liveRefreshed = false;
    let liveRestarted = false;
    let lastRecoveryAt = 0;
    function liveContext() {
      const MediaSourceType = mediaSourceConstructor();
      const probe = document.createElement("video");
      return {
        browser: browserName(navigator.userAgent),
        canDecode: decodeCheck((type) => { try { return !!MediaSourceType?.isTypeSupported(type) || probe.canPlayType(type) !== ""; } catch { return false; } }),
        insecureSource: isInsecureForPage(liveSource.url, window.location.protocol),
        appleWebKit: isAppleWebKit(navigator.userAgent), relayNeedsTicket: config.paywallEnabled,
        relayAvailable: !!config.resolverUrl, relayTicket: !!currentStreamTicket(), played: hasEverPlayed
      };
    }
    // Some sources hang forever without ever firing an "error" event (the CDN
    // accepts the connection but never delivers a playable moov/metadata). A
    // plain error-based ladder can't recover from that. Arm a per-attempt stall
    // timeout: if the element hasn't reached at least metadata within the window,
    // treat it as a failure and escalate down the ladder (next URL, then remux).
    let stallTimer: number | undefined;
    let playableWatchdog: number | undefined;
    // The last request the engine saw fail during this attempt (see player.ts).
    let lastFailedResource: string | undefined;
    const onResourceError = (event: Event) => { lastFailedResource = (event as CustomEvent<{ url?: string }>).detail?.url ?? lastFailedResource; };
    video.addEventListener("arvio-resource-error", onResourceError);
    const armStallTimer = () => {
      lastFailedResource = undefined;
      window.clearTimeout(stallTimer);
      window.clearTimeout(playableWatchdog);
      // Live channels that hang (provider accepts the connection but never sends
      // data) need a shorter leash than VOD so the ladder keeps moving.
      stallTimer = window.setTimeout(() => {
        if (cancelled) return;
        if (video.readyState < 1) handlePlaybackError({ transport: currentRung.transport ?? stream.transport ?? "file", kind: "network", fatal: true, retryable: true, code: "STARTUP_TIMEOUT", message: "The source did not respond. Please try again or choose another source.", ...(lastFailedResource ? { resourceUrl: lastFailedResource } : {}) });
        // A cold debrid link has to be fetched and cached by the provider
        // before the first byte arrives, which regularly exceeds the VOD
        // budget — condemning sources that were about to work.
      }, currentRung.startupMs ?? (liveTv ? 10000 : (parseDebridStream(stream.originalUrl ?? stream.url) ? 25000 : 13000)));
      // Metadata can arrive without a playable frame. Give each fallback its
      // own frame deadline; the original attempt must not cancel a new relay.
      playableWatchdog = window.setTimeout(() => {
        if (cancelled || hasPlayed || video.readyState >= 2) return;
        handlePlaybackError({ transport: currentRung.transport ?? stream.transport ?? "file", kind: "network", fatal: true, retryable: true, code: "STARTUP_TIMEOUT", message: "The source did not respond. Please try again or choose another source.", ...(lastFailedResource ? { resourceUrl: lastFailedResource } : {}) });
      }, liveTv ? (currentRung.startupMs ?? 10000) + 5000 : (parseDebridStream(stream.originalUrl ?? stream.url) ? 38000 : 20000));
    };
    let awaitingGesture = false;
    const requestPlayback = () => {
      if (cancelled || !video.paused) return;
      setError(false);
      setBuffering(true);
      const attempt = video.play();
      if (attempt && typeof attempt.catch === "function") {
        attempt.catch((reason: unknown) => {
          if (cancelled || (reason instanceof DOMException && reason.name === "AbortError")) return;
          // A rejected play() during source replacement or autoplay blocking
          // does not establish that the media is broken.
          if (video.error) { handlePlaybackError(); return; }
          if (reason instanceof DOMException && reason.name === "NotAllowedError") {
            awaitingGesture = true;
            window.clearTimeout(stallTimer);
            window.clearTimeout(playableWatchdog);
            setPlayBlocked(true);
          }
          setBuffering(false);
          setShowControls(true);
        });
      }
    };
    let refreshedLink = false;
    const handlePlaybackError = (fault?: PlaybackError) => {
      if (cancelled || handlingError) return;
      if (!fault && video.error) {
        const code = video.error.code;
        fault = { transport: stream.transport ?? "file", kind: code === 3 ? "media" : code === 4 ? "unsupported" : "network",
          fatal: true, retryable: classifyMediaError(code) === "retryable", code, message: code === 3 || code === 4
            ? "This source could not be played in the browser."
            : "The source did not respond. Please try again or choose another source." };
      }
      if (fault) {
        setErrorDetail(fault.message);
        failureDiagnosticRef.current = { failure_kind: playbackFailureKind(fault), phase: hasEverPlayed ? "playback" : "startup", transport: fault.transport };
      }
      if (currentRung.live) {
        liveFaults.push({ attempt: currentRung.live, fault });
        // The provider is limiting connections: every further request makes it worse.
        if (providerRefusal(liveFaults, liveContext()) === 429) { failLive(); return; }
      }
      // A live channel that has played a good while since its last
      // reconnect earns another in-place reconnect (providers drop
      // connections every few minutes); VOD keeps the single recovery.
      if (liveTv && retriedInterruption && Date.now() - lastRecoveryAt > 60_000) retriedInterruption = false;
      // Transport teardown may pause the element. Recover an interrupted
      // connection explicitly: a watchdog that ignores user-paused playback
      // cannot detect that case. This also covers live streams.
      if (hasPlayed) {
        const position = fault?.positionSeconds ?? video.currentTime;
        if (!liveTv && position > 0) resumeAtRef.current = position + (stream.playbackSession?.startOffset ?? 0);
        const decodeFailure = fault ? fault.kind === "media" || fault.kind === "unsupported" : classifyMediaError(video.error?.code) === "fatal";
        // Live: a decoder error mid-stream (a corrupt packet, a track change)
        // gets the same one reconnect, then the plan and its explanation.
        if (decodeFailure && !liveTv) {
          if (!stream.transcoded && canProviderTranscode(stream)) {
            cancelled = true;
            onSelectStream(stream, { forceBrowser: true, forceTranscode: true });
            detach?.();
            return;
          }
          setBuffering(false);
          setError(true);
          setShowControls(true);
          onToast("This source stopped decoding partway through. Try another source or open it in VLC.");
          return;
        }
        if (!retriedInterruption && (fault?.retryable !== false || (liveTv && decodeFailure))) {
          retriedInterruption = true;
          lastRecoveryAt = Date.now();
          handlingError = true;
          hasPlayed = false;
          setBuffering(true);
          detach?.reload(liveTv ? undefined : position);
          armStallTimer();
          handlingError = false;
          requestPlayback();
          return;
        }
        // The single in-place recovery failed. Preserve the last good position
        // and continue the existing bounded fallback ladder.
        hasPlayed = false;
      }
      handlingError = true;
      // A debrid CDN link is presigned and short-lived. When one expires the
      // CDN rejects it (TorBox: "Invalid Presigned Token", HTTP 400) and every
      // remaining attempt for this source replays the SAME dead url — which is
      // why a single stale token used to cascade into "source failed" across
      // the whole list. Drop the cached link and re-resolve once before
      // treating the source as dead; the sources themselves are usually fine,
      // which is why they still play in VLC and the APK.
      if (!refreshedLink && !liveTv && !stream.transcoded && stream.originalUrl && parseDebridStream(stream.originalUrl)) {
        refreshedLink = true;
        invalidateDebridDirectUrl(stream.originalUrl);
        const debridInfo = parseDebridStream(stream.originalUrl);
        if (debridInfo) {
          detach?.();
          setBootStatus({ label: "Refreshing the source link…" });
          void resolveDebridDirectUrl(debridInfo).then((result) => {
            if (cancelled) return;
            if (result.url && result.url !== stream.url) {
              setError(false);
              setBuffering(true);
              detach = attach({ url: result.url });
              armStallTimer();
              requestPlayback();
              handlingError = false;
            } else {
              handlingError = false;
              handlePlaybackError();
            }
          }).catch(() => {
            handlingError = false;
            handlePlaybackError();
          });
          return;
        }
      }
      attemptIndex += 1;
      // Skip engines that would only repeat an HTTP refusal for the same request.
      // Skip what the current pass already ruled out: a request the provider
      // refused, the relay after it refused the session, anything but the
      // Xtream twin after the provider refused the channel.
      const passContext = liveContext();
      while (ladder[attemptIndex]?.live && !attemptAllowed(ladder[attemptIndex].live!, liveFaults, passContext)) attemptIndex += 1;
      const next = ladder[attemptIndex];
      if (next) {
        setError(false);
        setBuffering(true);
        setBootStatus({ label: "Trying another connection ({value0} of {value1})", values: { value0: attemptIndex + 1, value1: ladder.length } });
        detach?.();
        detach = attach(next);
        armStallTimer();
        requestPlayback();
        handlingError = false;
        return;
      }
      if (liveTv && livePlan) {
        // Expiring portal links: fetch a fresh one once and walk the plan again.
        if (refreshLiveSource && !liveRefreshed && mayRefreshChannel(liveFaults, liveContext())) {
          liveRefreshed = true;
          detach?.();
          setBootStatus({ label: "Refreshing the channel link…" });
          void refreshLiveSource().then((fresh) => {
            if (cancelled) return;
            if (!fresh?.url || fresh.url === liveSource.url) { handlingError = false; restartOrFail(); return; }
            liveSource = { url: fresh.url, headers: fresh.headers };
            // Restarts, VLC and Copy link use the fresh link from now on.
            if (liveSourceOverrideRef && stream.url) liveSourceOverrideRef.current = { from: stream.url, url: fresh.url, headers: fresh.headers };
            startPlan(livePlanFor(fresh.url, fresh.headers));
          }).catch(() => { if (!cancelled) { handlingError = false; restartOrFail(); } });
          return;
        }
        restartOrFail();
        return;
      }
      // Direct attempts exhausted. For a VOD source the browser couldn't decode
      // (MKV container / lossless audio), auto-escalate to the in-browser remux
      // path instead of surfacing an error — this is the instant-first ladder.
      // Only when the plan says a remux can actually succeed here: escalating a
      // source whose audio this browser cannot decode just burns CPU and time
      // before failing, when the honest move is to fall through to VLC.
      if (!liveTv && !stream.homeServer && !stream.remux && !stream.transcoded && canTryRemux(stream)) {
        cancelled = true;
        const playhead = video.currentTime;
        if (playhead > 5) resumeAtRef.current = playhead;
        onSelectStream(stream, { forceRemux: true });
        detach?.();
        return;
      }
      if (!liveTv && !stream.transcoded && canProviderTranscode(stream)) {
        cancelled = true;
        onSelectStream(stream, { forceTranscode: true, forceBrowser: true });
        detach?.();
        return;
      }
      // This source is dead — hop to the next playable one before giving up.
      if (tryNextSource()) {
        cancelled = true;
        detach?.();
        return;
      }
      cancelled = true;
      window.clearTimeout(stallTimer);
      window.clearTimeout(playableWatchdog);
      detach?.();
      setBuffering(false);
      setError(true);
      setShowControls(true);
      // <video> reports a refused request (503, expired link) as an
      // unsupported format. Ask the host once for the real reason.
      if (!liveTv && (!fault || fault.kind !== "media")) {
        const failedUrl = stream.url;
        void diagnoseSourceFailure(failedUrl).then((reason) => {
          if (reason && currentStreamRef.current.url === failedUrl) setErrorDetail(reason);
        });
      }
    };
    // Live: begin the plan again (fresh link, or one reconnect after a drop).
    function startPlan(plan: LivePlan) {
      livePlan = plan;
      // Each pass is judged on its own faults: an old 404 must not skip URLs
      // now, an old recovered decoder error must not explain a later drop.
      liveFaults.length = 0;
      ladder = rungsOf(plan);
      attemptIndex = 0;
      hasPlayed = false;
      handlingError = false;
      if (!ladder.length) { failLive(); return; }
      setError(false);
      setBuffering(true);
      setBootStatus({ label: "Connecting…" });
      detach?.();
      detach = attach(ladder[0]);
      armStallTimer();
      requestPlayback();
    }
    function restartOrFail() {
      if (hasEverPlayed && !liveRestarted && mayRetryChannel(liveFaults, liveContext())) {
        liveRestarted = true;
        notify?.("Reconnecting to the channel…");
        startPlan(livePlanFor(liveSource.url, liveSource.headers));
        return;
      }
      failLive();
    }
    function failLive() {
      cancelled = true;
      window.clearTimeout(stallTimer);
      window.clearTimeout(playableWatchdog);
      detach?.();
      setBuffering(false);
      setError(true);
      setShowControls(true);
      const failure = classifyLiveFailure(liveFaults, liveContext(), livePlan?.blocked, livePlan?.shape);
      // Support diagnostics without credentials: which routes failed and how
      // (status, error code, the failing host). Read from the console as
      // window.__arvioLiveDiagnostics.
      try {
        const hostOf = (url?: string) => { try { const parsed = new URL(url ?? ""); const inner = parsed.searchParams.get("url"); return inner ? `${parsed.host}→${new URL(inner).protocol}//${new URL(inner).host}` : `${parsed.protocol}//${parsed.host}`; } catch { return ""; } };
        (window as unknown as { __arvioLiveDiagnostics?: unknown }).__arvioLiveDiagnostics = { kind: failure.kind, attempts: liveFaults.map(({ attempt, fault }) => ({
          route: attempt.route, variant: attempt.variant, transport: attempt.transport, engine: attempt.hlsEngine,
          status: fault?.httpStatus, code: fault?.code, kind: fault?.kind, resource: hostOf(fault?.resourceUrl)
        })) };
      } catch { /* diagnostics are best-effort */ }
      setLiveFailure?.(failure);
      setErrorDetail(failure.message);
    }
    if (livePlan && !ladder.length) {
      failLive();
      return () => { cancelled = true; };
    }
    detach = attach(ladder[0]);
    armStallTimer();
    function onReadyToStart() {
      if (cancelled || !video) return;
      window.clearTimeout(stallTimer);
      if (video.playbackRate !== playbackRate) video.playbackRate = playbackRate;
      // Restore the position carried over from a source hop / remux switch.
      // Guarded so it only fires once and never seeks past the end.
      const resumeAt = resumeAtRef.current;
      if (resumeAt > 0 && video.currentTime < 1) {
        const relativePosition = Math.max(0, resumeAt - (stream.playbackSession?.startOffset ?? 0));
        const target = video.duration > 0 ? Math.min(relativePosition, Math.max(0, video.duration - 0.1)) : relativePosition;
        try {
          if (target > 0) video.currentTime = target;
          resumeAtRef.current = 0;
        } catch { /* Keep the pending position for canplay if metadata is not seekable yet. */ }
      }
      requestPlayback();
    }
    const startTimer = window.setTimeout(requestPlayback, 0);
    const onUserPlay = () => {
      if (cancelled || !awaitingGesture) return;
      awaitingGesture = false;
      setPlayBlocked(false);
      armStallTimer();
    };
    video.addEventListener("play", onUserPlay);
    // Each engine owns its error recovery and emits a terminal typed fault.
    // Listening to the same raw video error here bypasses HLS recovery and can
    // run the fallback ladder twice for one failure.
    // The moment real frames arrive this source has proven it plays here, so
    // retire the startup watchdogs. Keep listening so a recovered connection
    // can prove it is delivering playable media again.
    const onFirstPlaying = () => {
      if (video.readyState < 3) return;
      const first = !hasPlayed;
      // Faults from before this (re)start played are history now.
      if (first) liveFaults.length = 0;
      hasPlayed = true;
      hasEverPlayed = true;
      window.clearTimeout(stallTimer);
      window.clearTimeout(playableWatchdog);
      if (first && liveTv && !noticeShown) { window.clearTimeout(noticeTimer); noticeTimer = window.setTimeout(checkLiveMedia, 3000); }
    };
    video.addEventListener("playing", onFirstPlaying);
    // `playing` can be missed when a source starts already-buffered; a moving
    // clock is the same proof.
    video.addEventListener("timeupdate", onFirstPlaying);
    return () => {
      cancelled = true;
      window.clearTimeout(startTimer);
      window.clearTimeout(noticeTimer);
      window.clearTimeout(stallTimer);
      window.clearTimeout(playableWatchdog);
      video.removeEventListener("play", onUserPlay);
      video.removeEventListener("arvio-resource-error", onResourceError);
      video.removeEventListener("playing", onFirstPlaying);
      video.removeEventListener("timeupdate", onFirstPlaying);
      clearAttemptStart();
      detach?.();
      if (transportRef.current === detach) transportRef.current = null;
    };
    // Artwork, source enrichment and subtitle updates must not restart a playing URL.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [playbackIdentity, remuxRestartKey, liveTv]);
}
