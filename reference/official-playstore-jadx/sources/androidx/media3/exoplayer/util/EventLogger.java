package androidx.media3.exoplayer.util;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.compose.runtime.ComposerKt;
import androidx.fragment.app.a2;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.a;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.drm.KeyRequestInfo;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.common.base.o;
import com.google.common.collect.h1;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class EventLogger implements AnalyticsListener {
    private static final o COMMA_JOINER = new o(", ");
    private static final String DEFAULT_TAG = "EventLogger";
    private static final int MAX_TIMELINE_ITEM_LINES = 3;
    private static final NumberFormat TIME_FORMAT;
    private final Timeline.Period period;
    private final long startTimeMs;
    private final String tag;
    private final Timeline.Window window;

    static {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        TIME_FORMAT = numberFormat;
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
    }

    public EventLogger() {
        this(DEFAULT_TAG);
    }

    private static String channelConfigAsString(int i10) {
        switch (i10) {
            case 4:
                return "mono";
            case 12:
                return "stereo";
            case ComposerKt.providerMapsKey /* 204 */:
                return "quad";
            case 252:
                return "5.1";
            case 6396:
                return "7.1";
            case 737532:
                return "5.1.4";
            case 743676:
                return "7.1.4";
            case 3145980:
                return "5.1.2";
            case 3152124:
                return "7.1.2";
            case 202070268:
                return "9.1.4";
            case 205215996:
                return "9.1.6";
            default:
                return "0x" + Integer.toHexString(i10);
        }
    }

    private static String encodingAsString(int i10) {
        if (i10 == 30) {
            return "dts-uhd-p2";
        }
        if (i10 == 268435456) {
            return "pcm-16be";
        }
        if (i10 == 1073741824) {
            return "aac-er-bsac";
        }
        if (i10 == 1342177280) {
            return "pcm-24be";
        }
        if (i10 == 1610612736) {
            return "pcm-32be";
        }
        switch (i10) {
            case 2:
                return "pcm-16";
            case 3:
                return "pcm-8";
            case 4:
                return "pcm-float";
            case 5:
                return HlsSegmentFormat.AC3;
            case 6:
                return "eac3";
            case 7:
                return "dts";
            case 8:
                return "dts-hd";
            case 9:
                return HlsSegmentFormat.MP3;
            case 10:
                return "aac-lc";
            case 11:
                return "aac-he-v1";
            case 12:
                return "aac-he-v2";
            default:
                switch (i10) {
                    case 14:
                        return "truehd";
                    case 15:
                        return "aac-eld";
                    case 16:
                        return "aac-xhe";
                    case 17:
                        return "ac4";
                    case 18:
                        return "eac3-joc";
                    default:
                        switch (i10) {
                            case 20:
                                return "opus";
                            case 21:
                                return "pcm-24";
                            case 22:
                                return "pcm-32";
                            default:
                                return String.valueOf(i10);
                        }
                }
        }
    }

    private static String getAudioTrackConfigString(AudioSink.AudioTrackConfig audioTrackConfig) {
        ArrayList arrayList = new ArrayList();
        if (audioTrackConfig.encoding != -1) {
            arrayList.add("enc=" + encodingAsString(audioTrackConfig.encoding));
        }
        arrayList.add("channelConf=" + channelConfigAsString(audioTrackConfig.channelConfig));
        arrayList.add("sampleRate=" + audioTrackConfig.sampleRate);
        arrayList.add("bufferSize=" + audioTrackConfig.bufferSize);
        if (audioTrackConfig.tunneling) {
            arrayList.add("tunneling");
        }
        if (audioTrackConfig.offload) {
            arrayList.add("offload");
        }
        return COMMA_JOINER.b(arrayList);
    }

    private static String getDiscontinuityReasonString(int i10) {
        switch (i10) {
            case 0:
                return "AUTO_TRANSITION";
            case 1:
                return "SEEK";
            case 2:
                return "SEEK_ADJUSTMENT";
            case 3:
                return HlsMediaPlaylist.Interstitial.NAVIGATION_RESTRICTION_SKIP;
            case 4:
                return "REMOVE";
            case 5:
                return "INTERNAL";
            case 6:
                return "SILENCE_SKIP";
            default:
                return "?";
        }
    }

    private String getEventString(AnalyticsListener.EventTime eventTime, String str, String str2, Throwable th) {
        StringBuilder sbP = a2.p(str, " [");
        sbP.append(getEventTimeString(eventTime));
        String string = sbP.toString();
        if (th instanceof PlaybackException) {
            StringBuilder sbP2 = a2.p(string, ", errorCode=");
            sbP2.append(((PlaybackException) th).getErrorCodeName());
            string = sbP2.toString();
        }
        if (str2 != null) {
            string = c.t(string, ", ", str2);
        }
        String throwableString = Log.getThrowableString(th);
        if (!TextUtils.isEmpty(throwableString)) {
            StringBuilder sbP3 = a2.p(string, "\n  ");
            sbP3.append(throwableString.replace("\n", "\n  "));
            sbP3.append('\n');
            string = sbP3.toString();
        }
        return d.m(string, "]");
    }

    private String getEventTimeString(AnalyticsListener.EventTime eventTime) {
        String string = "window=" + eventTime.windowIndex;
        if (eventTime.mediaPeriodId != null) {
            StringBuilder sbP = a2.p(string, ", period=");
            sbP.append(eventTime.timeline.getIndexOfPeriod(eventTime.mediaPeriodId.periodUid));
            string = sbP.toString();
            if (eventTime.mediaPeriodId.isAd()) {
                StringBuilder sbP2 = a2.p(string, ", adGroup=");
                sbP2.append(eventTime.mediaPeriodId.adGroupIndex);
                StringBuilder sbP3 = a2.p(sbP2.toString(), ", ad=");
                sbP3.append(eventTime.mediaPeriodId.adIndexInAdGroup);
                string = sbP3.toString();
            }
        }
        StringBuilder sb2 = new StringBuilder("eventTime=");
        sb2.append(getTimeString(eventTime.realtimeMs - this.startTimeMs));
        sb2.append(", mediaPos=");
        return d.q(sb2, getTimeString(eventTime.eventPlaybackPositionMs), ", ", string);
    }

    private static String getMediaItemTransitionReasonString(int i10) {
        return i10 != 0 ? i10 != 1 ? i10 != 2 ? i10 != 3 ? "?" : "PLAYLIST_CHANGED" : "SEEK" : "AUTO" : "REPEAT";
    }

    private static String getPlayWhenReadyChangeReasonString(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? i10 != 5 ? "?" : "END_OF_MEDIA_ITEM" : "REMOTE" : "AUDIO_BECOMING_NOISY" : "AUDIO_FOCUS_LOSS" : "USER_REQUEST";
    }

    private static String getPlaybackSuppressionReasonString(int i10) {
        return i10 != 0 ? i10 != 1 ? i10 != 3 ? i10 != 4 ? "?" : "SCRUBBING" : "UNSUITABLE_AUDIO_OUTPUT" : "TRANSIENT_AUDIO_FOCUS_LOSS" : "NONE";
    }

    private static String getRepeatModeString(int i10) {
        return i10 != 0 ? i10 != 1 ? i10 != 2 ? "?" : "ALL" : "ONE" : "OFF";
    }

    private static String getStateString(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? "?" : "ENDED" : "READY" : "BUFFERING" : "IDLE";
    }

    private static String getTimeString(long j10) {
        return j10 == C.TIME_UNSET ? "?" : TIME_FORMAT.format(j10 / 1000.0f);
    }

    private static String getTimelineChangeReasonString(int i10) {
        return i10 != 0 ? i10 != 1 ? "?" : "SOURCE_UPDATE" : "PLAYLIST_CHANGED";
    }

    private static String getTrackStatusString(boolean z) {
        return z ? "[X]" : "[ ]";
    }

    private void printInternalError(AnalyticsListener.EventTime eventTime, String str, Exception exc) {
        loge(eventTime, "internalError", str, exc);
    }

    private void printMetadata(Metadata metadata, String str) {
        for (int i10 = 0; i10 < metadata.length(); i10++) {
            StringBuilder sbS = d.s(str);
            sbS.append(metadata.get(i10));
            logd(sbS.toString());
        }
    }

    public void logd(String str) {
        Log.d(this.tag, str);
    }

    public void loge(String str) {
        Log.e(this.tag, str);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioAttributesChanged(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
        logd(eventTime, "audioAttributes", audioAttributes.contentType + "," + audioAttributes.flags + "," + audioAttributes.usage + "," + audioAttributes.allowedCapturePolicy);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onAudioCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
        a.b(this, eventTime, exc);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j10) {
        a.c(this, eventTime, str, j10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
        logd(eventTime, "audioDecoderReleased", str);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        logd(eventTime, "audioDisabled");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        logd(eventTime, "audioEnabled");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        logd(eventTime, "audioInputFormat", Format.toLogString(format));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioPositionAdvancing(AnalyticsListener.EventTime eventTime, long j10) {
        logd(eventTime, "audioPositionAdvancing", "since " + getTimeString((SystemClock.elapsedRealtime() + (j10 - System.currentTimeMillis())) - this.startTimeMs));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioSessionIdChanged(AnalyticsListener.EventTime eventTime, int i10) {
        logd(eventTime, "audioSessionId", Integer.toString(i10));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onAudioSinkError(AnalyticsListener.EventTime eventTime, Exception exc) {
        a.k(this, eventTime, exc);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioTrackInitialized(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
        logd(eventTime, "audioTrackInit", getAudioTrackConfigString(audioTrackConfig));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioTrackReleased(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
        logd(eventTime, "audioTrackReleased", getAudioTrackConfigString(audioTrackConfig));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioUnderrun(AnalyticsListener.EventTime eventTime, int i10, long j10, long j11) {
        loge(eventTime, "audioTrackUnderrun", i10 + ", " + j10 + ", " + j11, null);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onAvailableCommandsChanged(AnalyticsListener.EventTime eventTime, Player.Commands commands) {
        a.o(this, eventTime, commands);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i10, long j10, long j11) {
        a.p(this, eventTime, i10, j10, j11);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, CueGroup cueGroup) {
        a.q(this, eventTime, cueGroup);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onDeviceInfoChanged(AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
        a.s(this, eventTime, deviceInfo);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onDeviceVolumeChanged(AnalyticsListener.EventTime eventTime, int i10, boolean z) {
        a.t(this, eventTime, i10, z);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        logd(eventTime, "downstreamFormat", Format.toLogString(mediaLoadData.trackFormat));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime) {
        a.v(this, eventTime);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDrmKeysRemoved(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmKeysRemoved");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDrmKeysRestored(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmKeysRestored");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime) {
        a.z(this, eventTime);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
        printInternalError(eventTime, "drmSessionManagerError", exc);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDrmSessionReleased(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmSessionReleased");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDroppedSeeksWhileScrubbing(AnalyticsListener.EventTime eventTime, int i10) {
        logd(eventTime, "droppedSeeksWhileScrubbing", Integer.toString(i10));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i10, long j10) {
        logd(eventTime, "droppedFrames", Integer.toString(i10));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onEvents(Player player, AnalyticsListener.Events events) {
        a.F(this, player, events);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onIsLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        logd(eventTime, "loading", Boolean.toString(z));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onIsPlayingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        logd(eventTime, "isPlaying", Boolean.toString(z));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onLoadCanceled(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        a.I(this, eventTime, loadEventInfo, mediaLoadData);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onLoadCompleted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        a.J(this, eventTime, loadEventInfo, mediaLoadData);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        printInternalError(eventTime, "loadError", iOException);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        a.L(this, eventTime, loadEventInfo, mediaLoadData);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        a.N(this, eventTime, z);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onMaxSeekToPreviousPositionChanged(AnalyticsListener.EventTime eventTime, long j10) {
        a.O(this, eventTime, j10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onMediaItemTransition(AnalyticsListener.EventTime eventTime, MediaItem mediaItem, int i10) {
        logd("mediaItem [" + getEventTimeString(eventTime) + ", reason=" + getMediaItemTransitionReasonString(i10) + "]");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onMediaMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
        a.Q(this, eventTime, mediaMetadata);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onMetadata(AnalyticsListener.EventTime eventTime, Metadata metadata) {
        logd("metadata [" + getEventTimeString(eventTime));
        printMetadata(metadata, "  ");
        logd("]");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPlayWhenReadyChanged(AnalyticsListener.EventTime eventTime, boolean z, int i10) {
        logd(eventTime, "playWhenReady", z + ", " + getPlayWhenReadyChangeReasonString(i10));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPlaybackParametersChanged(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
        logd(eventTime, "playbackParameters", playbackParameters.toString());
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPlaybackStateChanged(AnalyticsListener.EventTime eventTime, int i10) {
        logd(eventTime, "state", getStateString(i10));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPlaybackSuppressionReasonChanged(AnalyticsListener.EventTime eventTime, int i10) {
        logd(eventTime, "playbackSuppressionReason", getPlaybackSuppressionReasonString(i10));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        loge(eventTime, "playerFailed", playbackException);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onPlayerErrorChanged(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        a.X(this, eventTime, playbackException);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onPlayerReleased(AnalyticsListener.EventTime eventTime) {
        a.Y(this, eventTime);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onPlayerStateChanged(AnalyticsListener.EventTime eventTime, boolean z, int i10) {
        a.Z(this, eventTime, z, i10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onPlaylistMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
        a.a0(this, eventTime, mediaMetadata);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i10) {
        a.b0(this, eventTime, i10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onRenderedFirstFrame(AnalyticsListener.EventTime eventTime, Object obj, long j10) {
        logd(eventTime, "renderedFirstFrame", String.valueOf(obj));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onRendererReadyChanged(AnalyticsListener.EventTime eventTime, int i10, int i11, boolean z) {
        StringBuilder sbS = a0.c.s(i10, "rendererIndex=", ", ");
        sbS.append(Util.getTrackTypeString(i11));
        sbS.append(", ");
        sbS.append(z);
        logd(eventTime, "rendererReady", sbS.toString());
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onRepeatModeChanged(AnalyticsListener.EventTime eventTime, int i10) {
        logd(eventTime, "repeatMode", getRepeatModeString(i10));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onSeekBackIncrementChanged(AnalyticsListener.EventTime eventTime, long j10) {
        a.g0(this, eventTime, j10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onSeekForwardIncrementChanged(AnalyticsListener.EventTime eventTime, long j10) {
        a.h0(this, eventTime, j10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime eventTime) {
        a.i0(this, eventTime);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onShuffleModeChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        logd(eventTime, "shuffleModeEnabled", Boolean.toString(z));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onSkipSilenceEnabledChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        logd(eventTime, "skipSilenceEnabled", Boolean.toString(z));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onSurfaceSizeChanged(AnalyticsListener.EventTime eventTime, int i10, int i11) {
        logd(eventTime, "surfaceSize", c.n(i10, i11, "w=", ", h="));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i10) {
        int periodCount = eventTime.timeline.getPeriodCount();
        int windowCount = eventTime.timeline.getWindowCount();
        StringBuilder sb2 = new StringBuilder("timeline [");
        d.A(sb2, getEventTimeString(eventTime), ", periodCount=", periodCount, ", windowCount=");
        sb2.append(windowCount);
        sb2.append(", reason=");
        sb2.append(getTimelineChangeReasonString(i10));
        logd(sb2.toString());
        for (int i11 = 0; i11 < Math.min(periodCount, 3); i11++) {
            eventTime.timeline.getPeriod(i11, this.period);
            logd("  period [" + getTimeString(this.period.getDurationMs()) + "]");
        }
        if (periodCount > 3) {
            logd("  ...");
        }
        for (int i12 = 0; i12 < Math.min(windowCount, 3); i12++) {
            eventTime.timeline.getWindow(i12, this.window);
            logd("  window [" + getTimeString(this.window.getDurationMs()) + ", seekable=" + this.window.isSeekable + ", dynamic=" + this.window.isDynamic + "]");
        }
        if (windowCount > 3) {
            logd("  ...");
        }
        logd("]");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onTrackSelectionParametersChanged(AnalyticsListener.EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
        a.n0(this, eventTime, trackSelectionParameters);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onTracksChanged(AnalyticsListener.EventTime eventTime, Tracks tracks) {
        Metadata metadata;
        logd("tracks [" + getEventTimeString(eventTime));
        h1 groups = tracks.getGroups();
        for (int i10 = 0; i10 < groups.size(); i10++) {
            Tracks.Group group = (Tracks.Group) groups.get(i10);
            logd("  group [ id=" + group.getMediaTrackGroup().id);
            for (int i11 = 0; i11 < group.length; i11++) {
                String trackStatusString = getTrackStatusString(group.isTrackSelected(i11));
                String formatSupportString = Util.getFormatSupportString(group.getTrackSupport(i11));
                StringBuilder sbQ = a2.q("    ", trackStatusString, " Track:", i11, ", ");
                sbQ.append(Format.toLogString(group.getTrackFormat(i11)));
                sbQ.append(", supported=");
                sbQ.append(formatSupportString);
                logd(sbQ.toString());
            }
            logd("  ]");
        }
        boolean z = false;
        for (int i12 = 0; !z && i12 < groups.size(); i12++) {
            Tracks.Group group2 = (Tracks.Group) groups.get(i12);
            for (int i13 = 0; !z && i13 < group2.length; i13++) {
                if (group2.isTrackSelected(i13) && (metadata = group2.getTrackFormat(i13).metadata) != null && metadata.length() > 0) {
                    logd("  Metadata [");
                    printMetadata(metadata, "    ");
                    logd("  ]");
                    z = true;
                }
            }
        }
        logd("]");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onUpstreamDiscarded(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        logd(eventTime, "upstreamDiscarded", Format.toLogString(mediaLoadData.trackFormat));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onVideoCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
        a.q0(this, eventTime, exc);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j10) {
        a.r0(this, eventTime, str, j10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
        logd(eventTime, "videoDecoderReleased", str);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        logd(eventTime, "videoDisabled");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        logd(eventTime, "videoEnabled");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onVideoFrameProcessingOffset(AnalyticsListener.EventTime eventTime, long j10, int i10) {
        a.w0(this, eventTime, j10, i10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        logd(eventTime, "videoInputFormat", Format.toLogString(format));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, int i10, int i11, int i12, float f10) {
        a.y0(this, eventTime, i10, i11, i12, f10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVolumeChanged(AnalyticsListener.EventTime eventTime, float f10) {
        logd(eventTime, "volume", Float.toString(f10));
    }

    public EventLogger(String str) {
        this.tag = str;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.startTimeMs = SystemClock.elapsedRealtime();
    }

    private void logd(AnalyticsListener.EventTime eventTime, String str) {
        logd(getEventString(eventTime, str, null, null));
    }

    private void loge(AnalyticsListener.EventTime eventTime, String str, Throwable th) {
        loge(getEventString(eventTime, str, null, th));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j10, long j11) {
        logd(eventTime, "audioDecoderInitialized", str);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, List list) {
        a.r(this, eventTime, list);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime, KeyRequestInfo keyRequestInfo) {
        logd(eventTime, "drmKeysLoaded");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime, int i10) {
        logd(eventTime, "drmSessionAcquired", a0.c.i(i10, "state="));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public final /* synthetic */ void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i10) {
        a.M(this, eventTime, loadEventInfo, mediaLoadData, i10);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
        logd(eventTime, "positionDiscontinuity", "reason=" + getDiscontinuityReasonString(i10) + ", PositionInfo:old [" + positionInfo + "], PositionInfo:new [" + positionInfo2 + "]");
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j10, long j11) {
        logd(eventTime, "videoDecoderInitialized", str);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        StringBuilder sb2 = new StringBuilder("w=" + videoSize.width + ", h=" + videoSize.height);
        if (videoSize.pixelWidthHeightRatio != 1.0f) {
            sb2.append(", par=");
            sb2.append(videoSize.pixelWidthHeightRatio);
        }
        logd(eventTime, "videoSize", sb2.toString());
    }

    private void logd(AnalyticsListener.EventTime eventTime, String str, String str2) {
        logd(getEventString(eventTime, str, str2, null));
    }

    private void loge(AnalyticsListener.EventTime eventTime, String str, String str2, Throwable th) {
        loge(getEventString(eventTime, str, str2, th));
    }

    @Deprecated
    public EventLogger(MappingTrackSelector mappingTrackSelector) {
        this(DEFAULT_TAG);
    }

    @Deprecated
    public EventLogger(MappingTrackSelector mappingTrackSelector, String str) {
        this(str);
    }
}
