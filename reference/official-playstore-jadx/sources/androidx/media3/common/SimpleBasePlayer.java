package androidx.media3.common;

import android.graphics.Rect;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.compose.ui.text.input.c;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import com.google.common.util.concurrent.d1;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SimpleBasePlayer extends BasePlayer {
    private static final long POSITION_DISCONTINUITY_THRESHOLD_MS = 1000;
    private final HandlerWrapper applicationHandler;
    private final Looper applicationLooper;
    private final ListenerSet<Player.Listener> listeners;
    private final HashSet<d1<?>> pendingOperations;
    private final Timeline.Period period;
    private boolean released;
    private State state;

    public static final class LivePositionSupplier implements PositionSupplier {
        private long finalValue = C.TIME_UNSET;
        private final PositionSupplier livePosition;

        public LivePositionSupplier(PositionSupplier positionSupplier) {
            this.livePosition = positionSupplier;
        }

        public void disconnect(long j10) {
            this.finalValue = j10;
        }

        @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
        public long get() {
            long j10 = this.finalValue;
            return j10 != C.TIME_UNSET ? j10 : this.livePosition.get();
        }
    }

    public static final class MediaItemData {
        public final long defaultPositionUs;
        public final long durationUs;
        public final long elapsedRealtimeEpochOffsetMs;
        public final boolean isDynamic;
        public final boolean isPlaceholder;
        public final boolean isSeekable;
        public final MediaItem.LiveConfiguration liveConfiguration;
        public final Object manifest;
        public final MediaItem mediaItem;
        public final MediaMetadata mediaMetadata;
        private final long[] periodPositionInWindowUs;
        public final h1 periods;
        public final long positionInFirstPeriodUs;
        public final long presentationStartTimeMs;
        public final Tracks tracks;
        public final Object uid;
        public final long windowStartTimeMs;

        public static final class Builder {
            private long defaultPositionUs;
            private long durationUs;
            private long elapsedRealtimeEpochOffsetMs;
            private boolean isDynamic;
            private boolean isPlaceholder;
            private boolean isSeekable;
            private MediaItem.LiveConfiguration liveConfiguration;
            private Object manifest;
            private MediaItem mediaItem;
            private MediaMetadata mediaMetadata;
            private h1 periods;
            private long positionInFirstPeriodUs;
            private long presentationStartTimeMs;
            private Tracks tracks;
            private Object uid;
            private long windowStartTimeMs;

            public MediaItemData build() {
                return new MediaItemData(this);
            }

            public Builder setDefaultPositionUs(long j10) {
                ac.b.j(j10 >= 0);
                this.defaultPositionUs = j10;
                return this;
            }

            public Builder setDurationUs(long j10) {
                ac.b.j(j10 == C.TIME_UNSET || j10 >= 0);
                this.durationUs = j10;
                return this;
            }

            public Builder setElapsedRealtimeEpochOffsetMs(long j10) {
                this.elapsedRealtimeEpochOffsetMs = j10;
                return this;
            }

            public Builder setIsDynamic(boolean z) {
                this.isDynamic = z;
                return this;
            }

            public Builder setIsPlaceholder(boolean z) {
                this.isPlaceholder = z;
                return this;
            }

            public Builder setIsSeekable(boolean z) {
                this.isSeekable = z;
                return this;
            }

            public Builder setLiveConfiguration(MediaItem.LiveConfiguration liveConfiguration) {
                this.liveConfiguration = liveConfiguration;
                return this;
            }

            public Builder setManifest(Object obj) {
                this.manifest = obj;
                return this;
            }

            public Builder setMediaItem(MediaItem mediaItem) {
                this.mediaItem = mediaItem;
                return this;
            }

            public Builder setMediaMetadata(MediaMetadata mediaMetadata) {
                this.mediaMetadata = mediaMetadata;
                return this;
            }

            public Builder setPeriods(List<PeriodData> list) {
                int size = list.size();
                int i10 = 0;
                while (i10 < size - 1) {
                    ac.b.k(list.get(i10).durationUs != C.TIME_UNSET, "Periods other than last need a duration");
                    int i11 = i10 + 1;
                    for (int i12 = i11; i12 < size; i12++) {
                        ac.b.k(!list.get(i10).uid.equals(list.get(i12).uid), "Duplicate PeriodData UIDs in period list");
                    }
                    i10 = i11;
                }
                this.periods = h1.n(list);
                return this;
            }

            public Builder setPositionInFirstPeriodUs(long j10) {
                ac.b.j(j10 >= 0);
                this.positionInFirstPeriodUs = j10;
                return this;
            }

            public Builder setPresentationStartTimeMs(long j10) {
                this.presentationStartTimeMs = j10;
                return this;
            }

            public Builder setTracks(Tracks tracks) {
                this.tracks = tracks;
                return this;
            }

            public Builder setUid(Object obj) {
                this.uid = obj;
                return this;
            }

            public Builder setWindowStartTimeMs(long j10) {
                this.windowStartTimeMs = j10;
                return this;
            }

            public Builder(Object obj) {
                this.uid = obj;
                this.tracks = Tracks.EMPTY;
                this.mediaItem = MediaItem.EMPTY;
                this.mediaMetadata = null;
                this.manifest = null;
                this.liveConfiguration = null;
                this.presentationStartTimeMs = C.TIME_UNSET;
                this.windowStartTimeMs = C.TIME_UNSET;
                this.elapsedRealtimeEpochOffsetMs = C.TIME_UNSET;
                this.isSeekable = false;
                this.isDynamic = false;
                this.defaultPositionUs = 0L;
                this.durationUs = C.TIME_UNSET;
                this.positionInFirstPeriodUs = 0L;
                this.isPlaceholder = false;
                f1 f1Var = h1.f14020l;
                this.periods = o3.f14078o;
            }

            private Builder(MediaItemData mediaItemData) {
                this.uid = mediaItemData.uid;
                this.tracks = mediaItemData.tracks;
                this.mediaItem = mediaItemData.mediaItem;
                this.mediaMetadata = mediaItemData.mediaMetadata;
                this.manifest = mediaItemData.manifest;
                this.liveConfiguration = mediaItemData.liveConfiguration;
                this.presentationStartTimeMs = mediaItemData.presentationStartTimeMs;
                this.windowStartTimeMs = mediaItemData.windowStartTimeMs;
                this.elapsedRealtimeEpochOffsetMs = mediaItemData.elapsedRealtimeEpochOffsetMs;
                this.isSeekable = mediaItemData.isSeekable;
                this.isDynamic = mediaItemData.isDynamic;
                this.defaultPositionUs = mediaItemData.defaultPositionUs;
                this.durationUs = mediaItemData.durationUs;
                this.positionInFirstPeriodUs = mediaItemData.positionInFirstPeriodUs;
                this.isPlaceholder = mediaItemData.isPlaceholder;
                this.periods = mediaItemData.periods;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static MediaItemData buildFromState(State state, int i10, Timeline.Period period, Timeline.Window window) {
            boolean z = SimpleBasePlayer.getCurrentMediaItemIndexInternal(state) == i10;
            state.timeline.getWindow(i10, window);
            e1 e1VarK = h1.k();
            for (int i11 = window.firstPeriodIndex; i11 <= window.lastPeriodIndex; i11++) {
                state.timeline.getPeriod(i11, period, true);
                Object obj = period.uid;
                obj.getClass();
                e1VarK.c(new PeriodData.Builder(obj).setAdPlaybackState(period.adPlaybackState).setDurationUs(period.durationUs).setIsPlaceholder(period.isPlaceholder).build());
            }
            return new Builder(window.uid).setDefaultPositionUs(window.defaultPositionUs).setDurationUs(window.durationUs).setElapsedRealtimeEpochOffsetMs(window.elapsedRealtimeEpochOffsetMs).setIsDynamic(window.isDynamic).setIsPlaceholder(window.isPlaceholder).setIsSeekable(window.isSeekable).setLiveConfiguration(window.liveConfiguration).setManifest(window.manifest).setMediaItem(window.mediaItem).setMediaMetadata(z ? state.currentMetadata : null).setPeriods(e1VarK.f()).setPositionInFirstPeriodUs(window.positionInFirstPeriodUs).setPresentationStartTimeMs(window.presentationStartTimeMs).setTracks(z ? state.currentTracks : Tracks.EMPTY).setWindowStartTimeMs(window.windowStartTimeMs).build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Timeline.Period getPeriod(int i10, int i11, Timeline.Period period) {
            if (this.periods.isEmpty()) {
                Object obj = this.uid;
                long j10 = this.positionInFirstPeriodUs;
                period.set(obj, obj, i10, j10 + this.durationUs, -j10, AdPlaybackState.NONE, this.isPlaceholder);
                return period;
            }
            PeriodData periodData = (PeriodData) this.periods.get(i11);
            Object obj2 = periodData.uid;
            period.set(obj2, Pair.create(this.uid, obj2), i10, periodData.durationUs, this.periodPositionInWindowUs[i11], periodData.adPlaybackState, periodData.isPlaceholder);
            return period;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object getPeriodUid(int i10) {
            if (this.periods.isEmpty()) {
                return this.uid;
            }
            return Pair.create(this.uid, ((PeriodData) this.periods.get(i10)).uid);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Timeline.Window getWindow(int i10, Timeline.Window window) {
            int size;
            boolean z;
            if (this.periods.isEmpty()) {
                size = 1;
                z = true;
            } else {
                size = this.periods.size();
                z = true;
            }
            window.set(this.uid, this.mediaItem, this.manifest, this.presentationStartTimeMs, this.windowStartTimeMs, this.elapsedRealtimeEpochOffsetMs, this.isSeekable, this.isDynamic, this.liveConfiguration, this.defaultPositionUs, this.durationUs, i10, (i10 + size) - 1, this.positionInFirstPeriodUs);
            window.isPlaceholder = this.isPlaceholder;
            return window;
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaItemData)) {
                return false;
            }
            MediaItemData mediaItemData = (MediaItemData) obj;
            return this.uid.equals(mediaItemData.uid) && this.tracks.equals(mediaItemData.tracks) && this.mediaItem.equals(mediaItemData.mediaItem) && Objects.equals(this.mediaMetadata, mediaItemData.mediaMetadata) && Objects.equals(this.manifest, mediaItemData.manifest) && Objects.equals(this.liveConfiguration, mediaItemData.liveConfiguration) && this.presentationStartTimeMs == mediaItemData.presentationStartTimeMs && this.windowStartTimeMs == mediaItemData.windowStartTimeMs && this.elapsedRealtimeEpochOffsetMs == mediaItemData.elapsedRealtimeEpochOffsetMs && this.isSeekable == mediaItemData.isSeekable && this.isDynamic == mediaItemData.isDynamic && this.defaultPositionUs == mediaItemData.defaultPositionUs && this.durationUs == mediaItemData.durationUs && this.positionInFirstPeriodUs == mediaItemData.positionInFirstPeriodUs && this.isPlaceholder == mediaItemData.isPlaceholder && this.periods.equals(mediaItemData.periods);
        }

        public int hashCode() {
            int iHashCode = (this.mediaItem.hashCode() + ((this.tracks.hashCode() + ((this.uid.hashCode() + 217) * 31)) * 31)) * 31;
            MediaMetadata mediaMetadata = this.mediaMetadata;
            int iHashCode2 = (iHashCode + (mediaMetadata == null ? 0 : mediaMetadata.hashCode())) * 31;
            Object obj = this.manifest;
            int iHashCode3 = (iHashCode2 + (obj == null ? 0 : obj.hashCode())) * 31;
            MediaItem.LiveConfiguration liveConfiguration = this.liveConfiguration;
            int iHashCode4 = liveConfiguration != null ? liveConfiguration.hashCode() : 0;
            long j10 = this.presentationStartTimeMs;
            int i10 = (((iHashCode3 + iHashCode4) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            long j11 = this.windowStartTimeMs;
            int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
            long j12 = this.elapsedRealtimeEpochOffsetMs;
            int i12 = (((((i11 + ((int) (j12 ^ (j12 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31;
            long j13 = this.defaultPositionUs;
            int i13 = (i12 + ((int) (j13 ^ (j13 >>> 32)))) * 31;
            long j14 = this.durationUs;
            int i14 = (i13 + ((int) (j14 ^ (j14 >>> 32)))) * 31;
            long j15 = this.positionInFirstPeriodUs;
            return this.periods.hashCode() + ((((i14 + ((int) (j15 ^ (j15 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31);
        }

        private MediaItemData(Builder builder) {
            int i10 = 0;
            if (builder.liveConfiguration == null) {
                ac.b.k(builder.presentationStartTimeMs == C.TIME_UNSET, "presentationStartTimeMs can only be set if liveConfiguration != null");
                ac.b.k(builder.windowStartTimeMs == C.TIME_UNSET, "windowStartTimeMs can only be set if liveConfiguration != null");
                ac.b.k(builder.elapsedRealtimeEpochOffsetMs == C.TIME_UNSET, "elapsedRealtimeEpochOffsetMs can only be set if liveConfiguration != null");
            } else if (builder.presentationStartTimeMs != C.TIME_UNSET && builder.windowStartTimeMs != C.TIME_UNSET) {
                ac.b.k(builder.windowStartTimeMs >= builder.presentationStartTimeMs, "windowStartTimeMs can't be less than presentationStartTimeMs");
            }
            int size = builder.periods.size();
            if (builder.durationUs != C.TIME_UNSET) {
                ac.b.k(builder.defaultPositionUs <= builder.durationUs, "defaultPositionUs can't be greater than durationUs");
            }
            this.uid = builder.uid;
            this.tracks = builder.tracks;
            this.mediaItem = builder.mediaItem;
            this.mediaMetadata = builder.mediaMetadata;
            this.manifest = builder.manifest;
            this.liveConfiguration = builder.liveConfiguration;
            this.presentationStartTimeMs = builder.presentationStartTimeMs;
            this.windowStartTimeMs = builder.windowStartTimeMs;
            this.elapsedRealtimeEpochOffsetMs = builder.elapsedRealtimeEpochOffsetMs;
            this.isSeekable = builder.isSeekable;
            this.isDynamic = builder.isDynamic;
            this.defaultPositionUs = builder.defaultPositionUs;
            this.durationUs = builder.durationUs;
            long j10 = builder.positionInFirstPeriodUs;
            this.positionInFirstPeriodUs = j10;
            this.isPlaceholder = builder.isPlaceholder;
            h1 h1Var = builder.periods;
            this.periods = h1Var;
            long[] jArr = new long[h1Var.size()];
            this.periodPositionInWindowUs = jArr;
            if (h1Var.isEmpty()) {
                return;
            }
            jArr[0] = -j10;
            while (i10 < size - 1) {
                long[] jArr2 = this.periodPositionInWindowUs;
                int i11 = i10 + 1;
                jArr2[i11] = jArr2[i10] + ((PeriodData) this.periods.get(i10)).durationUs;
                i10 = i11;
            }
        }
    }

    public static final class PeriodData {
        public final AdPlaybackState adPlaybackState;
        public final long durationUs;
        public final boolean isPlaceholder;
        public final Object uid;

        public static final class Builder {
            private AdPlaybackState adPlaybackState;
            private long durationUs;
            private boolean isPlaceholder;
            private Object uid;

            public PeriodData build() {
                return new PeriodData(this);
            }

            public Builder setAdPlaybackState(AdPlaybackState adPlaybackState) {
                this.adPlaybackState = adPlaybackState;
                return this;
            }

            public Builder setDurationUs(long j10) {
                ac.b.j(j10 == C.TIME_UNSET || j10 >= 0);
                this.durationUs = j10;
                return this;
            }

            public Builder setIsPlaceholder(boolean z) {
                this.isPlaceholder = z;
                return this;
            }

            public Builder setUid(Object obj) {
                this.uid = obj;
                return this;
            }

            public Builder(Object obj) {
                this.uid = obj;
                this.durationUs = 0L;
                this.adPlaybackState = AdPlaybackState.NONE;
                this.isPlaceholder = false;
            }

            private Builder(PeriodData periodData) {
                this.uid = periodData.uid;
                this.durationUs = periodData.durationUs;
                this.adPlaybackState = periodData.adPlaybackState;
                this.isPlaceholder = periodData.isPlaceholder;
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PeriodData)) {
                return false;
            }
            PeriodData periodData = (PeriodData) obj;
            return this.uid.equals(periodData.uid) && this.durationUs == periodData.durationUs && this.adPlaybackState.equals(periodData.adPlaybackState) && this.isPlaceholder == periodData.isPlaceholder;
        }

        public int hashCode() {
            int iHashCode = (this.uid.hashCode() + 217) * 31;
            long j10 = this.durationUs;
            return ((this.adPlaybackState.hashCode() + ((iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31)) * 31) + (this.isPlaceholder ? 1 : 0);
        }

        private PeriodData(Builder builder) {
            this.uid = builder.uid;
            this.durationUs = builder.durationUs;
            this.adPlaybackState = builder.adPlaybackState;
            this.isPlaceholder = builder.isPlaceholder;
        }
    }

    public static final class PlaceholderUid {
        private PlaceholderUid() {
        }
    }

    public static final class PlaylistTimeline extends Timeline {
        private final int[] firstPeriodIndexByWindowIndex;
        private final HashMap<Object, Integer> periodIndexByUid;
        private final h1 playlist;
        private final int[] windowIndexByPeriodIndex;

        public PlaylistTimeline(List<MediaItemData> list) {
            int size = list.size();
            this.playlist = h1.n(list);
            this.firstPeriodIndexByWindowIndex = new int[size];
            int periodCountInMediaItem = 0;
            for (int i10 = 0; i10 < size; i10++) {
                MediaItemData mediaItemData = list.get(i10);
                this.firstPeriodIndexByWindowIndex[i10] = periodCountInMediaItem;
                periodCountInMediaItem += getPeriodCountInMediaItem(mediaItemData);
            }
            this.windowIndexByPeriodIndex = new int[periodCountInMediaItem];
            this.periodIndexByUid = new HashMap<>();
            int i11 = 0;
            for (int i12 = 0; i12 < size; i12++) {
                MediaItemData mediaItemData2 = list.get(i12);
                for (int i13 = 0; i13 < getPeriodCountInMediaItem(mediaItemData2); i13++) {
                    this.periodIndexByUid.put(mediaItemData2.getPeriodUid(i13), Integer.valueOf(i11));
                    this.windowIndexByPeriodIndex[i11] = i12;
                    i11++;
                }
            }
        }

        private static int getPeriodCountInMediaItem(MediaItemData mediaItemData) {
            if (mediaItemData.periods.isEmpty()) {
                return 1;
            }
            return mediaItemData.periods.size();
        }

        @Override // androidx.media3.common.Timeline
        public int getFirstWindowIndex(boolean z) {
            return super.getFirstWindowIndex(z);
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            Integer num = this.periodIndexByUid.get(obj);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        @Override // androidx.media3.common.Timeline
        public int getLastWindowIndex(boolean z) {
            return super.getLastWindowIndex(z);
        }

        @Override // androidx.media3.common.Timeline
        public int getNextWindowIndex(int i10, int i11, boolean z) {
            return super.getNextWindowIndex(i10, i11, z);
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
            int i11 = this.windowIndexByPeriodIndex[i10];
            return ((MediaItemData) this.playlist.get(i11)).getPeriod(i11, i10 - this.firstPeriodIndexByWindowIndex[i11], period);
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            Integer num = this.periodIndexByUid.get(obj);
            num.getClass();
            return getPeriod(num.intValue(), period, true);
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.windowIndexByPeriodIndex.length;
        }

        @Override // androidx.media3.common.Timeline
        public int getPreviousWindowIndex(int i10, int i11, boolean z) {
            return super.getPreviousWindowIndex(i10, i11, z);
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i10) {
            int i11 = this.windowIndexByPeriodIndex[i10];
            return ((MediaItemData) this.playlist.get(i11)).getPeriodUid(i10 - this.firstPeriodIndexByWindowIndex[i11]);
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i10, Timeline.Window window, long j10) {
            return ((MediaItemData) this.playlist.get(i10)).getWindow(this.firstPeriodIndexByWindowIndex[i10], window);
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return this.playlist.size();
        }
    }

    public interface PositionSupplier {
        public static final PositionSupplier ZERO = b0.a(0);

        long get();
    }

    public static final class State {
        public final PositionSupplier adBufferedPositionMsSupplier;
        public final PositionSupplier adPositionMsSupplier;
        public final AudioAttributes audioAttributes;
        public final int audioSessionId;
        public final Player.Commands availableCommands;
        public final PositionSupplier contentBufferedPositionMsSupplier;
        public final PositionSupplier contentPositionMsSupplier;
        public final int currentAdGroupIndex;
        public final int currentAdIndexInAdGroup;
        public final CueGroup currentCues;
        public final int currentMediaItemIndex;
        public final MediaMetadata currentMetadata;
        public final Tracks currentTracks;
        public final DeviceInfo deviceInfo;
        public final int deviceVolume;
        public final long discontinuityPositionMs;
        public final boolean hasPositionDiscontinuity;
        public final boolean isDeviceMuted;
        public final boolean isLoading;
        public final long maxSeekToPreviousPositionMs;
        public final boolean newlyRenderedFirstFrame;
        public final boolean playWhenReady;
        public final int playWhenReadyChangeReason;
        public final PlaybackParameters playbackParameters;
        public final int playbackState;
        public final int playbackSuppressionReason;
        public final PlaybackException playerError;
        public final MediaMetadata playlistMetadata;
        public final int positionDiscontinuityReason;
        public final int repeatMode;
        public final long seekBackIncrementMs;
        public final long seekForwardIncrementMs;
        public final boolean shuffleModeEnabled;
        public final Size surfaceSize;
        public final Metadata timedMetadata;
        public final Timeline timeline;
        public final PositionSupplier totalBufferedDurationMsSupplier;
        public final TrackSelectionParameters trackSelectionParameters;
        public final float unmuteVolume;
        private final boolean usesDerivedMediaMetadata;
        public final VideoSize videoSize;
        public final float volume;

        public static final class Builder {
            private PositionSupplier adBufferedPositionMsSupplier;
            private Long adPositionMs;
            private PositionSupplier adPositionMsSupplier;
            private AudioAttributes audioAttributes;
            private int audioSessionId;
            private Player.Commands availableCommands;
            private PositionSupplier contentBufferedPositionMsSupplier;
            private Long contentPositionMs;
            private PositionSupplier contentPositionMsSupplier;
            private int currentAdGroupIndex;
            private int currentAdIndexInAdGroup;
            private CueGroup currentCues;
            private int currentMediaItemIndex;
            private MediaMetadata currentMetadata;
            private Tracks currentTracks;
            private DeviceInfo deviceInfo;
            private int deviceVolume;
            private long discontinuityPositionMs;
            private boolean hasPositionDiscontinuity;
            private boolean isDeviceMuted;
            private boolean isLoading;
            private long maxSeekToPreviousPositionMs;
            private boolean newlyRenderedFirstFrame;
            private boolean playWhenReady;
            private int playWhenReadyChangeReason;
            private PlaybackParameters playbackParameters;
            private int playbackState;
            private int playbackSuppressionReason;
            private PlaybackException playerError;
            private h1 playlist;
            private MediaMetadata playlistMetadata;
            private int positionDiscontinuityReason;
            private int repeatMode;
            private long seekBackIncrementMs;
            private long seekForwardIncrementMs;
            private boolean shuffleModeEnabled;
            private Size surfaceSize;
            private Metadata timedMetadata;
            private Timeline timeline;
            private PositionSupplier totalBufferedDurationMsSupplier;
            private TrackSelectionParameters trackSelectionParameters;
            private float unmuteVolume;
            private VideoSize videoSize;
            private float volume;

            public State build() {
                return new State(this);
            }

            public Builder clearPositionDiscontinuity() {
                this.hasPositionDiscontinuity = false;
                return this;
            }

            public Builder setAdBufferedPositionMs(PositionSupplier positionSupplier) {
                this.adBufferedPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder setAdPositionMs(long j10) {
                this.adPositionMs = Long.valueOf(j10);
                return this;
            }

            public Builder setAudioAttributes(AudioAttributes audioAttributes) {
                this.audioAttributes = audioAttributes;
                return this;
            }

            public Builder setAudioSessionId(int i10) {
                this.audioSessionId = i10;
                return this;
            }

            public Builder setAvailableCommands(Player.Commands commands) {
                this.availableCommands = commands;
                return this;
            }

            public Builder setContentBufferedPositionMs(PositionSupplier positionSupplier) {
                this.contentBufferedPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder setContentPositionMs(long j10) {
                this.contentPositionMs = Long.valueOf(j10);
                return this;
            }

            public Builder setCurrentAd(int i10, int i11) {
                ac.b.j((i10 == -1) == (i11 == -1));
                this.currentAdGroupIndex = i10;
                this.currentAdIndexInAdGroup = i11;
                return this;
            }

            public Builder setCurrentCues(CueGroup cueGroup) {
                this.currentCues = cueGroup;
                return this;
            }

            public Builder setCurrentMediaItemIndex(int i10) {
                this.currentMediaItemIndex = i10;
                return this;
            }

            public Builder setDeviceInfo(DeviceInfo deviceInfo) {
                this.deviceInfo = deviceInfo;
                return this;
            }

            public Builder setDeviceVolume(int i10) {
                ac.b.j(i10 >= 0);
                this.deviceVolume = i10;
                return this;
            }

            public Builder setIsDeviceMuted(boolean z) {
                this.isDeviceMuted = z;
                return this;
            }

            public Builder setIsLoading(boolean z) {
                this.isLoading = z;
                return this;
            }

            public Builder setMaxSeekToPreviousPositionMs(long j10) {
                this.maxSeekToPreviousPositionMs = j10;
                return this;
            }

            public Builder setNewlyRenderedFirstFrame(boolean z) {
                this.newlyRenderedFirstFrame = z;
                return this;
            }

            public Builder setPlayWhenReady(boolean z, int i10) {
                this.playWhenReady = z;
                this.playWhenReadyChangeReason = i10;
                return this;
            }

            public Builder setPlaybackParameters(PlaybackParameters playbackParameters) {
                this.playbackParameters = playbackParameters;
                return this;
            }

            public Builder setPlaybackState(int i10) {
                this.playbackState = i10;
                return this;
            }

            public Builder setPlaybackSuppressionReason(int i10) {
                this.playbackSuppressionReason = i10;
                return this;
            }

            public Builder setPlayerError(PlaybackException playbackException) {
                this.playerError = playbackException;
                return this;
            }

            public Builder setPlaylist(List<MediaItemData> list) {
                HashSet hashSet = new HashSet();
                for (int i10 = 0; i10 < list.size(); i10++) {
                    ac.b.k(hashSet.add(list.get(i10).uid), "Duplicate MediaItemData UID in playlist");
                }
                this.playlist = h1.n(list);
                this.timeline = new PlaylistTimeline(this.playlist);
                this.currentTracks = null;
                this.currentMetadata = null;
                return this;
            }

            public Builder setPlaylistMetadata(MediaMetadata mediaMetadata) {
                this.playlistMetadata = mediaMetadata;
                return this;
            }

            public Builder setPositionDiscontinuity(int i10, long j10) {
                this.hasPositionDiscontinuity = true;
                this.positionDiscontinuityReason = i10;
                this.discontinuityPositionMs = j10;
                return this;
            }

            public Builder setRepeatMode(int i10) {
                this.repeatMode = i10;
                return this;
            }

            public Builder setSeekBackIncrementMs(long j10) {
                this.seekBackIncrementMs = j10;
                return this;
            }

            public Builder setSeekForwardIncrementMs(long j10) {
                this.seekForwardIncrementMs = j10;
                return this;
            }

            public Builder setShuffleModeEnabled(boolean z) {
                this.shuffleModeEnabled = z;
                return this;
            }

            public Builder setSurfaceSize(Size size) {
                this.surfaceSize = size;
                return this;
            }

            public Builder setTimedMetadata(Metadata metadata) {
                this.timedMetadata = metadata;
                return this;
            }

            public Builder setTotalBufferedDurationMs(PositionSupplier positionSupplier) {
                this.totalBufferedDurationMsSupplier = positionSupplier;
                return this;
            }

            public Builder setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
                this.trackSelectionParameters = trackSelectionParameters;
                return this;
            }

            public Builder setUnmuteVolume(float f10) {
                ac.b.j(f10 >= 0.0f && f10 <= 1.0f);
                this.unmuteVolume = f10;
                return this;
            }

            public Builder setVideoSize(VideoSize videoSize) {
                this.videoSize = videoSize;
                return this;
            }

            public Builder setVolume(float f10) {
                ac.b.j(f10 >= 0.0f && f10 <= 1.0f);
                this.unmuteVolume = f10 != 0.0f ? f10 : this.volume;
                this.volume = f10;
                return this;
            }

            public Builder() {
                this.availableCommands = Player.Commands.EMPTY;
                this.playWhenReady = false;
                this.playWhenReadyChangeReason = 1;
                this.playbackState = 1;
                this.playbackSuppressionReason = 0;
                this.playerError = null;
                this.repeatMode = 0;
                this.shuffleModeEnabled = false;
                this.isLoading = false;
                this.seekBackIncrementMs = 5000L;
                this.seekForwardIncrementMs = 15000L;
                this.maxSeekToPreviousPositionMs = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
                this.playbackParameters = PlaybackParameters.DEFAULT;
                this.trackSelectionParameters = TrackSelectionParameters.DEFAULT;
                this.audioAttributes = AudioAttributes.DEFAULT;
                this.audioSessionId = 0;
                this.volume = 1.0f;
                this.unmuteVolume = 1.0f;
                this.videoSize = VideoSize.UNKNOWN;
                this.currentCues = CueGroup.EMPTY_TIME_ZERO;
                this.deviceInfo = DeviceInfo.UNKNOWN;
                this.deviceVolume = 0;
                this.isDeviceMuted = false;
                this.surfaceSize = Size.UNKNOWN;
                this.newlyRenderedFirstFrame = false;
                this.timedMetadata = new Metadata(C.TIME_UNSET, new Metadata.Entry[0]);
                f1 f1Var = h1.f14020l;
                this.playlist = o3.f14078o;
                this.timeline = Timeline.EMPTY;
                this.currentTracks = null;
                this.currentMetadata = null;
                this.playlistMetadata = MediaMetadata.EMPTY;
                this.currentMediaItemIndex = -1;
                this.currentAdGroupIndex = -1;
                this.currentAdIndexInAdGroup = -1;
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = b0.a(C.TIME_UNSET);
                this.adPositionMs = null;
                PositionSupplier positionSupplier = PositionSupplier.ZERO;
                this.adPositionMsSupplier = positionSupplier;
                this.contentBufferedPositionMsSupplier = b0.a(C.TIME_UNSET);
                this.adBufferedPositionMsSupplier = positionSupplier;
                this.totalBufferedDurationMsSupplier = positionSupplier;
                this.hasPositionDiscontinuity = false;
                this.positionDiscontinuityReason = 5;
                this.discontinuityPositionMs = 0L;
            }

            public Builder setAdPositionMs(PositionSupplier positionSupplier) {
                this.adPositionMs = null;
                this.adPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder setContentPositionMs(PositionSupplier positionSupplier) {
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder setPlaylist(Timeline timeline, Tracks tracks, MediaMetadata mediaMetadata) {
                this.playlist = null;
                this.timeline = timeline;
                this.currentTracks = tracks;
                this.currentMetadata = mediaMetadata;
                return this;
            }

            private Builder(State state) {
                this.availableCommands = state.availableCommands;
                this.playWhenReady = state.playWhenReady;
                this.playWhenReadyChangeReason = state.playWhenReadyChangeReason;
                this.playbackState = state.playbackState;
                this.playbackSuppressionReason = state.playbackSuppressionReason;
                this.playerError = state.playerError;
                this.repeatMode = state.repeatMode;
                this.shuffleModeEnabled = state.shuffleModeEnabled;
                this.isLoading = state.isLoading;
                this.seekBackIncrementMs = state.seekBackIncrementMs;
                this.seekForwardIncrementMs = state.seekForwardIncrementMs;
                this.maxSeekToPreviousPositionMs = state.maxSeekToPreviousPositionMs;
                this.playbackParameters = state.playbackParameters;
                this.trackSelectionParameters = state.trackSelectionParameters;
                this.audioAttributes = state.audioAttributes;
                this.audioSessionId = state.audioSessionId;
                this.volume = state.volume;
                this.unmuteVolume = state.unmuteVolume;
                this.videoSize = state.videoSize;
                this.currentCues = state.currentCues;
                this.deviceInfo = state.deviceInfo;
                this.deviceVolume = state.deviceVolume;
                this.isDeviceMuted = state.isDeviceMuted;
                this.surfaceSize = state.surfaceSize;
                this.newlyRenderedFirstFrame = state.newlyRenderedFirstFrame;
                this.timedMetadata = state.timedMetadata;
                Timeline timeline = state.timeline;
                this.timeline = timeline;
                if (timeline instanceof PlaylistTimeline) {
                    this.playlist = ((PlaylistTimeline) timeline).playlist;
                } else {
                    this.currentTracks = state.currentTracks;
                    this.currentMetadata = state.usesDerivedMediaMetadata ? null : state.currentMetadata;
                }
                this.playlistMetadata = state.playlistMetadata;
                this.currentMediaItemIndex = state.currentMediaItemIndex;
                this.currentAdGroupIndex = state.currentAdGroupIndex;
                this.currentAdIndexInAdGroup = state.currentAdIndexInAdGroup;
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = state.contentPositionMsSupplier;
                this.adPositionMs = null;
                this.adPositionMsSupplier = state.adPositionMsSupplier;
                this.contentBufferedPositionMsSupplier = state.contentBufferedPositionMsSupplier;
                this.adBufferedPositionMsSupplier = state.adBufferedPositionMsSupplier;
                this.totalBufferedDurationMsSupplier = state.totalBufferedDurationMsSupplier;
                this.hasPositionDiscontinuity = state.hasPositionDiscontinuity;
                this.positionDiscontinuityReason = state.positionDiscontinuityReason;
                this.discontinuityPositionMs = state.discontinuityPositionMs;
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof State)) {
                return false;
            }
            State state = (State) obj;
            return this.playWhenReady == state.playWhenReady && this.playWhenReadyChangeReason == state.playWhenReadyChangeReason && this.availableCommands.equals(state.availableCommands) && this.playbackState == state.playbackState && this.playbackSuppressionReason == state.playbackSuppressionReason && Objects.equals(this.playerError, state.playerError) && this.repeatMode == state.repeatMode && this.shuffleModeEnabled == state.shuffleModeEnabled && this.isLoading == state.isLoading && this.seekBackIncrementMs == state.seekBackIncrementMs && this.seekForwardIncrementMs == state.seekForwardIncrementMs && this.maxSeekToPreviousPositionMs == state.maxSeekToPreviousPositionMs && this.playbackParameters.equals(state.playbackParameters) && this.trackSelectionParameters.equals(state.trackSelectionParameters) && this.audioAttributes.equals(state.audioAttributes) && this.volume == state.volume && this.unmuteVolume == state.unmuteVolume && this.videoSize.equals(state.videoSize) && this.currentCues.equals(state.currentCues) && this.deviceInfo.equals(state.deviceInfo) && this.deviceVolume == state.deviceVolume && this.isDeviceMuted == state.isDeviceMuted && this.surfaceSize.equals(state.surfaceSize) && this.newlyRenderedFirstFrame == state.newlyRenderedFirstFrame && this.timedMetadata.equals(state.timedMetadata) && this.timeline.equals(state.timeline) && this.currentTracks.equals(state.currentTracks) && this.currentMetadata.equals(state.currentMetadata) && this.playlistMetadata.equals(state.playlistMetadata) && this.currentMediaItemIndex == state.currentMediaItemIndex && this.currentAdGroupIndex == state.currentAdGroupIndex && this.currentAdIndexInAdGroup == state.currentAdIndexInAdGroup && this.contentPositionMsSupplier.equals(state.contentPositionMsSupplier) && this.adPositionMsSupplier.equals(state.adPositionMsSupplier) && this.contentBufferedPositionMsSupplier.equals(state.contentBufferedPositionMsSupplier) && this.adBufferedPositionMsSupplier.equals(state.adBufferedPositionMsSupplier) && this.totalBufferedDurationMsSupplier.equals(state.totalBufferedDurationMsSupplier) && this.hasPositionDiscontinuity == state.hasPositionDiscontinuity && this.positionDiscontinuityReason == state.positionDiscontinuityReason && this.discontinuityPositionMs == state.discontinuityPositionMs;
        }

        public h1 getPlaylist() {
            Timeline timeline = this.timeline;
            if (timeline instanceof PlaylistTimeline) {
                return ((PlaylistTimeline) timeline).playlist;
            }
            Timeline.Window window = new Timeline.Window();
            Timeline.Period period = new Timeline.Period();
            e1 e1VarM = h1.m(this.timeline.getWindowCount());
            for (int i10 = 0; i10 < this.timeline.getWindowCount(); i10++) {
                e1VarM.c(MediaItemData.buildFromState(this, i10, period, window));
            }
            return e1VarM.f();
        }

        public int hashCode() {
            int iHashCode = (((((((((this.availableCommands.hashCode() + 217) * 31) + (this.playWhenReady ? 1 : 0)) * 31) + this.playWhenReadyChangeReason) * 31) + this.playbackState) * 31) + this.playbackSuppressionReason) * 31;
            PlaybackException playbackException = this.playerError;
            int iHashCode2 = (((((((iHashCode + (playbackException == null ? 0 : playbackException.hashCode())) * 31) + this.repeatMode) * 31) + (this.shuffleModeEnabled ? 1 : 0)) * 31) + (this.isLoading ? 1 : 0)) * 31;
            long j10 = this.seekBackIncrementMs;
            int i10 = (iHashCode2 + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            long j11 = this.seekForwardIncrementMs;
            int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
            long j12 = this.maxSeekToPreviousPositionMs;
            int iHashCode3 = (((((this.totalBufferedDurationMsSupplier.hashCode() + ((this.adBufferedPositionMsSupplier.hashCode() + ((this.contentBufferedPositionMsSupplier.hashCode() + ((this.adPositionMsSupplier.hashCode() + ((this.contentPositionMsSupplier.hashCode() + ((((((((this.playlistMetadata.hashCode() + ((this.currentMetadata.hashCode() + ((this.currentTracks.hashCode() + ((this.timeline.hashCode() + ((this.timedMetadata.hashCode() + ((((this.surfaceSize.hashCode() + ((((((this.deviceInfo.hashCode() + ((this.currentCues.hashCode() + ((this.videoSize.hashCode() + ((Float.floatToRawIntBits(this.unmuteVolume) + ((Float.floatToRawIntBits(this.volume) + ((this.audioAttributes.hashCode() + ((this.trackSelectionParameters.hashCode() + ((this.playbackParameters.hashCode() + ((i11 + ((int) (j12 ^ (j12 >>> 32)))) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + this.deviceVolume) * 31) + (this.isDeviceMuted ? 1 : 0)) * 31)) * 31) + (this.newlyRenderedFirstFrame ? 1 : 0)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + this.currentMediaItemIndex) * 31) + this.currentAdGroupIndex) * 31) + this.currentAdIndexInAdGroup) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.hasPositionDiscontinuity ? 1 : 0)) * 31) + this.positionDiscontinuityReason) * 31;
            long j13 = this.discontinuityPositionMs;
            return iHashCode3 + ((int) ((j13 >>> 32) ^ j13));
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0120  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0135  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0187  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private State(androidx.media3.common.SimpleBasePlayer.State.Builder r18) {
            /*
                Method dump skipped, instruction units count: 678
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.SimpleBasePlayer.State.<init>(androidx.media3.common.SimpleBasePlayer$State$Builder):void");
        }
    }

    public SimpleBasePlayer(Looper looper) {
        this(looper, Clock.DEFAULT);
    }

    private static List<MediaItemData> buildMutablePlaylistFromState(State state, Timeline.Period period, Timeline.Window window) {
        if (state.timeline instanceof PlaylistTimeline) {
            return new ArrayList(((PlaylistTimeline) state.timeline).playlist);
        }
        ArrayList arrayList = new ArrayList(state.timeline.getWindowCount());
        for (int i10 = 0; i10 < state.timeline.getWindowCount(); i10++) {
            arrayList.add(MediaItemData.buildFromState(state, i10, period, window));
        }
        return arrayList;
    }

    private static State buildStateForNewPosition(State.Builder builder, State state, long j10, Timeline timeline, int i10, long j11, boolean z, Timeline.Window window) {
        long defaultPositionMs;
        int i11 = i10;
        long positionOrDefaultInMediaItem = getPositionOrDefaultInMediaItem(j10, state, window);
        boolean z5 = false;
        if (timeline.isEmpty() || (i11 != -1 && i11 < timeline.getWindowCount())) {
            defaultPositionMs = j11;
        } else {
            defaultPositionMs = -9223372036854775807L;
            i11 = 0;
        }
        if (!timeline.isEmpty() && defaultPositionMs == C.TIME_UNSET) {
            defaultPositionMs = timeline.getWindow(i11, window).getDefaultPositionMs();
        }
        boolean z10 = state.timeline.isEmpty() || timeline.isEmpty();
        boolean z11 = (z10 || state.timeline.getWindow(getCurrentMediaItemIndexInternal(state), window).uid.equals(timeline.getWindow(i11, window).uid)) ? false : true;
        if (timeline.isEmpty()) {
            builder.setPlaylist(timeline, Tracks.EMPTY, null);
        } else if (timeline instanceof PlaylistTimeline) {
            MediaItemData mediaItemData = (MediaItemData) ((PlaylistTimeline) timeline).playlist.get(i11);
            builder.setPlaylist(timeline, mediaItemData.tracks, mediaItemData.mediaMetadata);
        } else {
            if (!z10 && !z11) {
                z5 = true;
            }
            builder.setPlaylist(timeline, z5 ? state.currentTracks : Tracks.EMPTY, z5 ? state.currentMetadata : null);
        }
        if (z10 || z11 || defaultPositionMs < positionOrDefaultInMediaItem) {
            builder.setCurrentMediaItemIndex(i11).setCurrentAd(-1, -1).setContentPositionMs(defaultPositionMs).setContentBufferedPositionMs(b0.a(defaultPositionMs)).setTotalBufferedDurationMs(PositionSupplier.ZERO);
        } else if (defaultPositionMs == positionOrDefaultInMediaItem) {
            builder.setCurrentMediaItemIndex(i11);
            if (state.currentAdGroupIndex == -1 || !z) {
                builder.setCurrentAd(-1, -1).setTotalBufferedDurationMs(b0.a(getContentBufferedPositionMsInternal(state, window) - positionOrDefaultInMediaItem));
            } else {
                builder.setTotalBufferedDurationMs(b0.a(state.adBufferedPositionMsSupplier.get() - state.adPositionMsSupplier.get()));
            }
        } else {
            builder.setCurrentMediaItemIndex(i11).setCurrentAd(-1, -1).setContentPositionMs(defaultPositionMs).setContentBufferedPositionMs(b0.a(Math.max(getContentBufferedPositionMsInternal(state, window), defaultPositionMs))).setTotalBufferedDurationMs(b0.a(Math.max(0L, state.totalBufferedDurationMsSupplier.get() - (defaultPositionMs - positionOrDefaultInMediaItem))));
        }
        return builder.build();
    }

    private void clearVideoOutput(Object obj) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(27)) {
            updateStateForPendingOperation(handleClearVideoOutput(obj), new k(state, 6));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaMetadata getCombinedMediaMetadata(MediaItem mediaItem, Tracks tracks) {
        MediaMetadata.Builder builder = new MediaMetadata.Builder();
        int size = tracks.getGroups().size();
        for (int i10 = 0; i10 < size; i10++) {
            Tracks.Group group = (Tracks.Group) tracks.getGroups().get(i10);
            for (int i11 = 0; i11 < group.length; i11++) {
                if (group.isTrackSelected(i11)) {
                    Format trackFormat = group.getTrackFormat(i11);
                    if (trackFormat.metadata != null) {
                        for (int i12 = 0; i12 < trackFormat.metadata.length(); i12++) {
                            trackFormat.metadata.get(i12).populateMediaMetadata(builder);
                        }
                    }
                }
            }
        }
        return builder.populate(mediaItem.mediaMetadata).build();
    }

    private static long getContentBufferedPositionMsInternal(State state, Timeline.Window window) {
        return getPositionOrDefaultInMediaItem(state.contentBufferedPositionMsSupplier.get(), state, window);
    }

    private static long getContentPositionMsInternal(State state, Timeline.Window window) {
        return getPositionOrDefaultInMediaItem(state.contentPositionMsSupplier.get(), state, window);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getCurrentMediaItemIndexInternal(State state) {
        int i10 = state.currentMediaItemIndex;
        if (i10 != -1) {
            return i10;
        }
        return 0;
    }

    private static int getCurrentPeriodIndexInternal(State state, Timeline.Window window, Timeline.Period period) {
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(state);
        return state.timeline.isEmpty() ? currentMediaItemIndexInternal : getPeriodIndexFromWindowPosition(state.timeline, currentMediaItemIndexInternal, getContentPositionMsInternal(state, window), window, period);
    }

    private static long getCurrentPeriodOrAdPositionMs(State state, Object obj, Timeline.Period period, Timeline.Window window) {
        return state.currentAdGroupIndex != -1 ? state.adPositionMsSupplier.get() : getContentPositionMsInternal(state, window) - state.timeline.getPeriodByUid(obj, period).getPositionInWindowMs();
    }

    private static int getMediaItemIndexInNewPlaylist(Timeline timeline, Timeline timeline2, int i10, Timeline.Period period, Timeline.Window window) {
        if (timeline.isEmpty()) {
            if (i10 < timeline2.getWindowCount()) {
                return i10;
            }
            return -1;
        }
        Object obj = timeline.getPeriod(timeline.getWindow(i10, window).firstPeriodIndex, period, true).uid;
        obj.getClass();
        if (timeline2.getIndexOfPeriod(obj) == -1) {
            return -1;
        }
        return timeline2.getPeriodByUid(obj, period).windowIndex;
    }

    private static int getMediaItemTransitionReason(State state, State state2, int i10, boolean z, Timeline.Window window) {
        Timeline timeline = state.timeline;
        Timeline timeline2 = state2.timeline;
        if (timeline2.isEmpty() && timeline.isEmpty()) {
            return -1;
        }
        if (timeline2.isEmpty() != timeline.isEmpty()) {
            return 3;
        }
        Object obj = state.timeline.getWindow(getCurrentMediaItemIndexInternal(state), window).uid;
        Object obj2 = state2.timeline.getWindow(getCurrentMediaItemIndexInternal(state2), window).uid;
        if ((obj instanceof PlaceholderUid) && !(obj2 instanceof PlaceholderUid)) {
            return -1;
        }
        if (!obj.equals(obj2)) {
            if (i10 == 0) {
                return 1;
            }
            return i10 == 1 ? 2 : 3;
        }
        if (i10 != 0 || getContentPositionMsInternal(state, window) <= getContentPositionMsInternal(state2, window)) {
            return (i10 == 1 && z) ? 2 : -1;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getPeriodIndexFromWindowPosition(Timeline timeline, int i10, long j10, Timeline.Window window, Timeline.Period period) {
        return timeline.getIndexOfPeriod(timeline.getPeriodPositionUs(window, period, i10, Util.msToUs(j10)).first);
    }

    private static long getPeriodOrAdDurationMs(State state, Object obj, Timeline.Period period) {
        state.timeline.getPeriodByUid(obj, period);
        int i10 = state.currentAdGroupIndex;
        return Util.usToMs(i10 == -1 ? period.durationUs : period.getAdDurationUs(i10, state.currentAdIndexInAdGroup));
    }

    private static int getPositionDiscontinuityReason(State state, State state2, boolean z, Timeline.Window window, Timeline.Period period) {
        if (state2.hasPositionDiscontinuity) {
            return state2.positionDiscontinuityReason;
        }
        if (z) {
            return 1;
        }
        if (state.timeline.isEmpty()) {
            return -1;
        }
        if (state2.timeline.isEmpty()) {
            return 4;
        }
        Object uidOfPeriod = state.timeline.getUidOfPeriod(getCurrentPeriodIndexInternal(state, window, period));
        Object uidOfPeriod2 = state2.timeline.getUidOfPeriod(getCurrentPeriodIndexInternal(state2, window, period));
        if ((uidOfPeriod instanceof PlaceholderUid) && !(uidOfPeriod2 instanceof PlaceholderUid)) {
            return -1;
        }
        if (uidOfPeriod2.equals(uidOfPeriod) && state.currentAdGroupIndex == state2.currentAdGroupIndex && state.currentAdIndexInAdGroup == state2.currentAdIndexInAdGroup) {
            long currentPeriodOrAdPositionMs = getCurrentPeriodOrAdPositionMs(state, uidOfPeriod, period, window);
            if (Math.abs(currentPeriodOrAdPositionMs - getCurrentPeriodOrAdPositionMs(state2, uidOfPeriod2, period, window)) < 1000) {
                return -1;
            }
            long periodOrAdDurationMs = getPeriodOrAdDurationMs(state, uidOfPeriod, period);
            return (periodOrAdDurationMs == C.TIME_UNSET || currentPeriodOrAdPositionMs < periodOrAdDurationMs) ? 5 : 0;
        }
        if (state2.timeline.getIndexOfPeriod(uidOfPeriod) == -1) {
            return 4;
        }
        long currentPeriodOrAdPositionMs2 = getCurrentPeriodOrAdPositionMs(state, uidOfPeriod, period, window);
        long periodOrAdDurationMs2 = getPeriodOrAdDurationMs(state, uidOfPeriod, period);
        return (periodOrAdDurationMs2 == C.TIME_UNSET || currentPeriodOrAdPositionMs2 < periodOrAdDurationMs2) ? 3 : 0;
    }

    private static Player.PositionInfo getPositionInfo(State state, boolean z, Timeline.Window window, Timeline.Period period) {
        Object obj;
        MediaItem mediaItem;
        Object obj2;
        long contentPositionMsInternal;
        long j10;
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(state);
        int currentPeriodIndexInternal = getCurrentPeriodIndexInternal(state, window, period);
        if (state.timeline.isEmpty()) {
            obj = null;
            mediaItem = null;
            obj2 = null;
        } else {
            Object obj3 = state.timeline.getPeriod(currentPeriodIndexInternal, period, true).uid;
            Object obj4 = state.timeline.getWindow(currentMediaItemIndexInternal, window).uid;
            obj2 = obj3;
            mediaItem = window.mediaItem;
            obj = obj4;
        }
        if (z) {
            j10 = state.discontinuityPositionMs;
            contentPositionMsInternal = state.currentAdGroupIndex == -1 ? j10 : getContentPositionMsInternal(state, window);
        } else {
            contentPositionMsInternal = getContentPositionMsInternal(state, window);
            j10 = state.currentAdGroupIndex != -1 ? state.adPositionMsSupplier.get() : contentPositionMsInternal;
        }
        return new Player.PositionInfo(obj, currentMediaItemIndexInternal, mediaItem, obj2, currentPeriodIndexInternal, j10, contentPositionMsInternal, state.currentAdGroupIndex, state.currentAdIndexInAdGroup);
    }

    private static long getPositionOrDefaultInMediaItem(long j10, State state, Timeline.Window window) {
        if (j10 != C.TIME_UNSET) {
            return j10;
        }
        if (state.timeline.isEmpty()) {
            return 0L;
        }
        return state.timeline.getWindow(getCurrentMediaItemIndexInternal(state), window).getDefaultPositionMs();
    }

    private static State getStateWithNewPlaylist(State state, List<MediaItemData> list, Timeline.Period period, Timeline.Window window) {
        State.Builder builderBuildUpon = state.buildUpon();
        PlaylistTimeline playlistTimeline = new PlaylistTimeline(list);
        Timeline timeline = state.timeline;
        long j10 = state.contentPositionMsSupplier.get();
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(state);
        int mediaItemIndexInNewPlaylist = getMediaItemIndexInNewPlaylist(timeline, playlistTimeline, currentMediaItemIndexInternal, period, window);
        long j11 = mediaItemIndexInNewPlaylist == -1 ? C.TIME_UNSET : j10;
        for (int i10 = currentMediaItemIndexInternal + 1; mediaItemIndexInNewPlaylist == -1 && i10 < timeline.getWindowCount(); i10++) {
            mediaItemIndexInNewPlaylist = getMediaItemIndexInNewPlaylist(timeline, playlistTimeline, i10, period, window);
        }
        if (state.playbackState != 1 && mediaItemIndexInNewPlaylist == -1) {
            builderBuildUpon.setPlaybackState(4).setIsLoading(false);
        }
        return buildStateForNewPosition(builderBuildUpon, state, j10, playlistTimeline, mediaItemIndexInNewPlaylist, j11, true, window);
    }

    private static State getStateWithNewPlaylistAndPosition(State state, List<MediaItemData> list, int i10, long j10, Timeline.Window window) {
        State.Builder builderBuildUpon = state.buildUpon();
        Timeline playlistTimeline = list == null ? state.timeline : new PlaylistTimeline(list);
        if (state.playbackState != 1) {
            if (playlistTimeline.isEmpty() || (i10 != -1 && i10 >= playlistTimeline.getWindowCount())) {
                builderBuildUpon.setPlaybackState(4).setIsLoading(false);
            } else {
                builderBuildUpon.setPlaybackState(2);
            }
        }
        return buildStateForNewPosition(builderBuildUpon, state, state.contentPositionMsSupplier.get(), playlistTimeline, i10, j10, false, window);
    }

    private static Size getSurfaceHolderSize(SurfaceHolder surfaceHolder) {
        if (!surfaceHolder.getSurface().isValid()) {
            return Size.ZERO;
        }
        Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
        return new Size(surfaceFrame.width(), surfaceFrame.height());
    }

    private static int getTimelineChangeReason(Timeline timeline, Timeline timeline2, Timeline.Window window) {
        if (timeline.getWindowCount() != timeline2.getWindowCount()) {
            return 0;
        }
        int i10 = 0;
        while (true) {
            if (i10 >= timeline.getWindowCount()) {
                return 1;
            }
            Object obj = timeline.getWindow(i10, window).uid;
            Object obj2 = timeline2.getWindow(i10, window).uid;
            boolean z = (obj instanceof PlaceholderUid) && !(obj2 instanceof PlaceholderUid);
            if (!obj.equals(obj2) && !z) {
                return 0;
            }
            i10++;
        }
    }

    private static boolean isPlaying(State state) {
        return state.playWhenReady && state.playbackState == 3 && state.playbackSuppressionReason == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ State lambda$addMediaItems$3(State state, List list, int i10) {
        List<MediaItemData> listBuildMutablePlaylistFromState = buildMutablePlaylistFromState(state, this.period, this.window);
        for (int i11 = 0; i11 < list.size(); i11++) {
            listBuildMutablePlaylistFromState.add(i11 + i10, getPlaceholderMediaItemData((MediaItem) list.get(i11)));
        }
        return !state.timeline.isEmpty() ? getStateWithNewPlaylist(state, listBuildMutablePlaylistFromState, this.period, this.window) : getStateWithNewPlaylistAndPosition(state, listBuildMutablePlaylistFromState, state.currentMediaItemIndex, state.contentPositionMsSupplier.get(), this.window);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$clearVideoOutput$23(State state) {
        return state.buildUpon().setSurfaceSize(Size.ZERO).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$decreaseDeviceVolume$28(State state) {
        return state.buildUpon().setDeviceVolume(Math.max(0, state.deviceVolume - 1)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$decreaseDeviceVolume$29(State state) {
        return state.buildUpon().setDeviceVolume(Math.max(0, state.deviceVolume - 1)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ d1 lambda$handleReplaceMediaItems$33(d1 d1Var, Object obj) throws Exception {
        return d1Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$increaseDeviceVolume$26(State state) {
        return state.buildUpon().setDeviceVolume(state.deviceVolume + 1).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$increaseDeviceVolume$27(State state) {
        return state.buildUpon().setDeviceVolume(state.deviceVolume + 1).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ State lambda$moveMediaItems$4(State state, int i10, int i11, int i12) {
        List<MediaItemData> listBuildMutablePlaylistFromState = buildMutablePlaylistFromState(state, this.period, this.window);
        Util.moveItems(listBuildMutablePlaylistFromState, i10, i11, i12);
        return getStateWithNewPlaylist(state, listBuildMutablePlaylistFromState, this.period, this.window);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$mute$17(State state) {
        return state.buildUpon().setVolume(0.0f).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(this, new Player.Events(flagSet));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$prepare$7(State state) {
        return state.buildUpon().setPlayerError(null).setPlaybackState(state.timeline.isEmpty() ? 4 : 2).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$release$13(State state) {
        return state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ State lambda$removeMediaItems$6(State state, int i10, int i11) {
        List<MediaItemData> listBuildMutablePlaylistFromState = buildMutablePlaylistFromState(state, this.period, this.window);
        Util.removeRange(listBuildMutablePlaylistFromState, i10, i11);
        return getStateWithNewPlaylist(state, listBuildMutablePlaylistFromState, this.period, this.window);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ State lambda$replaceMediaItems$5(State state, List list, int i10, int i11) {
        List<MediaItemData> listBuildMutablePlaylistFromState = buildMutablePlaylistFromState(state, this.period, this.window);
        for (int i12 = 0; i12 < list.size(); i12++) {
            listBuildMutablePlaylistFromState.add(i12 + i10, getPlaceholderMediaItemData((MediaItem) list.get(i12)));
        }
        State stateWithNewPlaylist = !state.timeline.isEmpty() ? getStateWithNewPlaylist(state, listBuildMutablePlaylistFromState, this.period, this.window) : getStateWithNewPlaylistAndPosition(state, listBuildMutablePlaylistFromState, state.currentMediaItemIndex, state.contentPositionMsSupplier.get(), this.window);
        if (i11 >= i10) {
            return stateWithNewPlaylist;
        }
        Util.removeRange(listBuildMutablePlaylistFromState, i11, i10);
        return getStateWithNewPlaylist(stateWithNewPlaylist, listBuildMutablePlaylistFromState, this.period, this.window);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ State lambda$seekTo$10(boolean z, State state, int i10, long j10) {
        return z ? state : getStateWithNewPlaylistAndPosition(state, null, i10, j10, this.window);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setAudioAttributes$32(State state, AudioAttributes audioAttributes) {
        return state.buildUpon().setAudioAttributes(audioAttributes).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setDeviceMuted$30(State state, boolean z) {
        return state.buildUpon().setIsDeviceMuted(z).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setDeviceMuted$31(State state, boolean z) {
        return state.buildUpon().setIsDeviceMuted(z).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setDeviceVolume$24(State state, int i10) {
        return state.buildUpon().setDeviceVolume(i10).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setDeviceVolume$25(State state, int i10) {
        return state.buildUpon().setDeviceVolume(i10).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ State lambda$setMediaItemsInternal$2(List list, State state, int i10, long j10) {
        ArrayList arrayList = new ArrayList();
        for (int i11 = 0; i11 < list.size(); i11++) {
            arrayList.add(getPlaceholderMediaItemData((MediaItem) list.get(i11)));
        }
        return getStateWithNewPlaylistAndPosition(state, arrayList, i10, j10, this.window);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setPlayWhenReady$1(State state, boolean z) {
        return state.buildUpon().setPlayWhenReady(z, 1).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setPlaybackParameters$11(State state, PlaybackParameters playbackParameters) {
        return state.buildUpon().setPlaybackParameters(playbackParameters).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setPlaylistMetadata$15(State state, MediaMetadata mediaMetadata) {
        return state.buildUpon().setPlaylistMetadata(mediaMetadata).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setRepeatMode$8(State state, int i10) {
        return state.buildUpon().setRepeatMode(i10).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setShuffleModeEnabled$9(State state, boolean z) {
        return state.buildUpon().setShuffleModeEnabled(z).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setTrackSelectionParameters$14(State state, TrackSelectionParameters trackSelectionParameters) {
        return state.buildUpon().setTrackSelectionParameters(trackSelectionParameters).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setVideoSurface$19(State state) {
        return state.buildUpon().setSurfaceSize(Size.UNKNOWN).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setVideoSurfaceHolder$20(State state, SurfaceHolder surfaceHolder) {
        return state.buildUpon().setSurfaceSize(getSurfaceHolderSize(surfaceHolder)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setVideoSurfaceView$21(State state, SurfaceView surfaceView) {
        return state.buildUpon().setSurfaceSize(getSurfaceHolderSize(surfaceView.getHolder())).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setVideoTextureView$22(State state, Size size) {
        return state.buildUpon().setSurfaceSize(size).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$setVolume$16(State state, float f10) {
        return state.buildUpon().setVolume(f10).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ State lambda$stop$12(State state) {
        return state.buildUpon().setPlaybackState(1).setTotalBufferedDurationMs(PositionSupplier.ZERO).setContentBufferedPositionMs(b0.a(getContentPositionMsInternal(state, this.window))).setAdBufferedPositionMs(state.adPositionMsSupplier).setIsLoading(false).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ State lambda$unmute$18(State state) {
        return state.buildUpon().setVolume(state.unmuteVolume).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$34(State state, int i10, Player.Listener listener) {
        listener.onTimelineChanged(state.timeline, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$35(int i10, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(i10);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$37(State state, Player.Listener listener) {
        listener.onPlayerErrorChanged(state.playerError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$38(State state, Player.Listener listener) {
        listener.onPlayerError((PlaybackException) Util.castNonNull(state.playerError));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$39(State state, Player.Listener listener) {
        listener.onTrackSelectionParametersChanged(state.trackSelectionParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$40(State state, Player.Listener listener) {
        listener.onTracksChanged(state.currentTracks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$41(State state, Player.Listener listener) {
        listener.onMediaMetadataChanged(state.currentMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$42(State state, Player.Listener listener) {
        listener.onLoadingChanged(state.isLoading);
        listener.onIsLoadingChanged(state.isLoading);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$43(State state, Player.Listener listener) {
        listener.onPlayerStateChanged(state.playWhenReady, state.playbackState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$44(State state, Player.Listener listener) {
        listener.onPlaybackStateChanged(state.playbackState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$45(State state, Player.Listener listener) {
        listener.onPlayWhenReadyChanged(state.playWhenReady, state.playWhenReadyChangeReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$46(State state, Player.Listener listener) {
        listener.onPlaybackSuppressionReasonChanged(state.playbackSuppressionReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$47(State state, Player.Listener listener) {
        listener.onIsPlayingChanged(isPlaying(state));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$48(State state, Player.Listener listener) {
        listener.onPlaybackParametersChanged(state.playbackParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$49(State state, Player.Listener listener) {
        listener.onRepeatModeChanged(state.repeatMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$50(State state, Player.Listener listener) {
        listener.onShuffleModeEnabledChanged(state.shuffleModeEnabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$51(State state, Player.Listener listener) {
        listener.onSeekBackIncrementChanged(state.seekBackIncrementMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$52(State state, Player.Listener listener) {
        listener.onSeekForwardIncrementChanged(state.seekForwardIncrementMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$53(State state, Player.Listener listener) {
        listener.onMaxSeekToPreviousPositionChanged(state.maxSeekToPreviousPositionMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$54(State state, Player.Listener listener) {
        listener.onAudioAttributesChanged(state.audioAttributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$55(State state, Player.Listener listener) {
        listener.onVideoSizeChanged(state.videoSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$56(State state, Player.Listener listener) {
        listener.onDeviceInfoChanged(state.deviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$57(State state, Player.Listener listener) {
        listener.onPlaylistMetadataChanged(state.playlistMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$58(State state, Player.Listener listener) {
        listener.onSurfaceSizeChanged(state.surfaceSize.getWidth(), state.surfaceSize.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$59(State state, Player.Listener listener) {
        listener.onVolumeChanged(state.volume);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$60(State state, Player.Listener listener) {
        listener.onDeviceVolumeChanged(state.deviceVolume, state.isDeviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$61(State state, Player.Listener listener) {
        listener.onCues(state.currentCues.cues);
        listener.onCues(state.currentCues);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$62(State state, Player.Listener listener) {
        listener.onMetadata(state.timedMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStateAndInformListeners$63(State state, Player.Listener listener) {
        listener.onAvailableCommandsChanged(state.availableCommands);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateStateForPendingOperation$64(d1 d1Var) {
        Util.castNonNull(this.state);
        this.pendingOperations.remove(d1Var);
        if (!this.pendingOperations.isEmpty() || this.released) {
            return;
        }
        updateStateAndInformListeners(getState(), false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postOrRunOnApplicationHandler(Runnable runnable) {
        if (this.applicationHandler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            this.applicationHandler.post(runnable);
        }
    }

    @RequiresNonNull({"state"})
    private void setMediaItemsInternal(final List<MediaItem> list, final int i10, final long j10) {
        ac.b.j(i10 == -1 || i10 >= 0);
        final State state = this.state;
        if (shouldHandleCommand(20) || (list.size() == 1 && shouldHandleCommand(31))) {
            updateStateForPendingOperation(handleSetMediaItems(list, i10, j10), new com.google.common.base.c0() { // from class: androidx.media3.common.s
                @Override // com.google.common.base.c0
                public final Object get() {
                    return this.f3396i.lambda$setMediaItemsInternal$2(list, state, i10, j10);
                }
            });
        }
    }

    @RequiresNonNull({"state"})
    private boolean shouldHandleCommand(int i10) {
        return !this.released && this.state.availableCommands.contains(i10);
    }

    @RequiresNonNull({"state"})
    private void updateStateAndInformListeners(final State state, boolean z, boolean z5) {
        State state2 = this.state;
        this.state = state;
        if (state.hasPositionDiscontinuity || state.newlyRenderedFirstFrame) {
            this.state = state.buildUpon().clearPositionDiscontinuity().setNewlyRenderedFirstFrame(false).build();
        }
        boolean z10 = state2.playWhenReady != state.playWhenReady;
        boolean z11 = state2.playbackState != state.playbackState;
        int positionDiscontinuityReason = getPositionDiscontinuityReason(state2, state, z, this.window, this.period);
        boolean zEquals = state2.timeline.equals(state.timeline);
        final int mediaItemTransitionReason = getMediaItemTransitionReason(state2, state, positionDiscontinuityReason, z5, this.window);
        if (!zEquals) {
            final int timelineChangeReason = getTimelineChangeReason(state2.timeline, state.timeline, this.window);
            final int i10 = 1;
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.common.o
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i10) {
                        case 0:
                            ((Player.Listener) obj).onMediaItemTransition((MediaItem) state, timelineChangeReason);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$34((SimpleBasePlayer.State) state, timelineChangeReason, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (positionDiscontinuityReason != -1) {
            this.listeners.queueEvent(11, new m(getPositionInfo(state2, false, this.window, this.period), getPositionInfo(state, state.hasPositionDiscontinuity, this.window, this.period), positionDiscontinuityReason));
        }
        if (mediaItemTransitionReason != -1) {
            final MediaItem mediaItem = state.timeline.isEmpty() ? null : state.timeline.getWindow(getCurrentMediaItemIndexInternal(state), this.window).mediaItem;
            final int i11 = 0;
            this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.common.o
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i11) {
                        case 0:
                            ((Player.Listener) obj).onMediaItemTransition((MediaItem) mediaItem, mediaItemTransitionReason);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$34((SimpleBasePlayer.State) mediaItem, mediaItemTransitionReason, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!Objects.equals(state2.playerError, state.playerError)) {
            final int i12 = 15;
            this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i12) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
            if (state.playerError != null) {
                final int i13 = 16;
                this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.common.l
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        switch (i13) {
                            case 0:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                                break;
                            case 1:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                                break;
                            case 2:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                                break;
                            case 3:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                                break;
                            case 4:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                                break;
                            case 5:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                                break;
                            case 6:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                                break;
                            case 7:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                                break;
                            case 8:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                                break;
                            case 9:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                                break;
                            case 10:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                                break;
                            case 11:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                                break;
                            case 12:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                                break;
                            case 13:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                                break;
                            case 14:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                                break;
                            case 15:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                                break;
                            case 16:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                                break;
                            case 17:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                                break;
                            case 18:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                                break;
                            case 19:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                                break;
                            case 20:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                                break;
                            case 21:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                                break;
                            case 22:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                                break;
                            case 23:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                                break;
                            case 24:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                                break;
                            case 25:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                                break;
                            default:
                                SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                                break;
                        }
                    }
                });
            }
        }
        if (!state2.trackSelectionParameters.equals(state.trackSelectionParameters)) {
            final int i14 = 17;
            this.listeners.queueEvent(19, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i14) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.currentTracks.equals(state.currentTracks)) {
            final int i15 = 18;
            this.listeners.queueEvent(2, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i15) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.currentMetadata.equals(state.currentMetadata)) {
            final int i16 = 19;
            this.listeners.queueEvent(14, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i16) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.isLoading != state.isLoading) {
            final int i17 = 20;
            this.listeners.queueEvent(3, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i17) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (z10 || z11) {
            final int i18 = 21;
            this.listeners.queueEvent(-1, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i18) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (z11) {
            final int i19 = 22;
            this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i19) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (z10 || state2.playWhenReadyChangeReason != state.playWhenReadyChangeReason) {
            final int i20 = 23;
            this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i20) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.playbackSuppressionReason != state.playbackSuppressionReason) {
            final int i21 = 24;
            this.listeners.queueEvent(6, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i21) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (isPlaying(state2) != isPlaying(state)) {
            final int i22 = 25;
            this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i22) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.playbackParameters.equals(state.playbackParameters)) {
            final int i23 = 26;
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i23) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.repeatMode != state.repeatMode) {
            final int i24 = 0;
            this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i24) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.shuffleModeEnabled != state.shuffleModeEnabled) {
            final int i25 = 1;
            this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i25) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.seekBackIncrementMs != state.seekBackIncrementMs) {
            final int i26 = 2;
            this.listeners.queueEvent(16, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i26) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.seekForwardIncrementMs != state.seekForwardIncrementMs) {
            final int i27 = 3;
            this.listeners.queueEvent(17, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i27) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.maxSeekToPreviousPositionMs != state.maxSeekToPreviousPositionMs) {
            final int i28 = 4;
            this.listeners.queueEvent(18, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i28) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.audioAttributes.equals(state.audioAttributes)) {
            final int i29 = 5;
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i29) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.videoSize.equals(state.videoSize)) {
            final int i30 = 6;
            this.listeners.queueEvent(25, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i30) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.deviceInfo.equals(state.deviceInfo)) {
            final int i31 = 7;
            this.listeners.queueEvent(29, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i31) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.playlistMetadata.equals(state.playlistMetadata)) {
            final int i32 = 8;
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i32) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state.newlyRenderedFirstFrame) {
            this.listeners.queueEvent(26, new androidx.credentials.d(24));
        }
        if (!state2.surfaceSize.equals(state.surfaceSize)) {
            final int i33 = 9;
            this.listeners.queueEvent(24, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i33) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.volume != state.volume) {
            final int i34 = 10;
            this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i34) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (state2.deviceVolume != state.deviceVolume || state2.isDeviceMuted != state.isDeviceMuted) {
            final int i35 = 11;
            this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i35) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.currentCues.equals(state.currentCues)) {
            final int i36 = 12;
            this.listeners.queueEvent(27, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i36) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.timedMetadata.equals(state.timedMetadata) && state.timedMetadata.presentationTimeUs != C.TIME_UNSET) {
            final int i37 = 13;
            this.listeners.queueEvent(28, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i37) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!state2.availableCommands.equals(state.availableCommands)) {
            final int i38 = 14;
            this.listeners.queueEvent(13, new ListenerSet.Event() { // from class: androidx.media3.common.l
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i38) {
                        case 0:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$49(state, (Player.Listener) obj);
                            break;
                        case 1:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$50(state, (Player.Listener) obj);
                            break;
                        case 2:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$51(state, (Player.Listener) obj);
                            break;
                        case 3:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$52(state, (Player.Listener) obj);
                            break;
                        case 4:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$53(state, (Player.Listener) obj);
                            break;
                        case 5:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$54(state, (Player.Listener) obj);
                            break;
                        case 6:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$55(state, (Player.Listener) obj);
                            break;
                        case 7:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$56(state, (Player.Listener) obj);
                            break;
                        case 8:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$57(state, (Player.Listener) obj);
                            break;
                        case 9:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$58(state, (Player.Listener) obj);
                            break;
                        case 10:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$59(state, (Player.Listener) obj);
                            break;
                        case 11:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$60(state, (Player.Listener) obj);
                            break;
                        case 12:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$61(state, (Player.Listener) obj);
                            break;
                        case 13:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$62(state, (Player.Listener) obj);
                            break;
                        case 14:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$63(state, (Player.Listener) obj);
                            break;
                        case 15:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$37(state, (Player.Listener) obj);
                            break;
                        case 16:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$38(state, (Player.Listener) obj);
                            break;
                        case 17:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$39(state, (Player.Listener) obj);
                            break;
                        case 18:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$40(state, (Player.Listener) obj);
                            break;
                        case 19:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$41(state, (Player.Listener) obj);
                            break;
                        case 20:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$42(state, (Player.Listener) obj);
                            break;
                        case 21:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$43(state, (Player.Listener) obj);
                            break;
                        case 22:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$44(state, (Player.Listener) obj);
                            break;
                        case 23:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$45(state, (Player.Listener) obj);
                            break;
                        case 24:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$46(state, (Player.Listener) obj);
                            break;
                        case 25:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$47(state, (Player.Listener) obj);
                            break;
                        default:
                            SimpleBasePlayer.lambda$updateStateAndInformListeners$48(state, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        this.listeners.flushEvents();
    }

    @RequiresNonNull({"state"})
    private void updateStateForPendingOperation(d1<?> d1Var, com.google.common.base.c0<State> c0Var) {
        updateStateForPendingOperation(d1Var, c0Var, false, false);
    }

    @EnsuresNonNull({"state"})
    private void verifyApplicationThreadAndInitState() {
        verifyApplicationThread();
        if (this.state == null) {
            this.state = getState();
        }
    }

    @Override // androidx.media3.common.Player
    public final void addListener(Player.Listener listener) {
        ListenerSet<Player.Listener> listenerSet = this.listeners;
        listener.getClass();
        listenerSet.add(listener);
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItems(int i10, final List<MediaItem> list) {
        verifyApplicationThreadAndInitState();
        ac.b.j(i10 >= 0);
        final State state = this.state;
        int windowCount = state.timeline.getWindowCount();
        if (!shouldHandleCommand(20) || list.isEmpty()) {
            return;
        }
        final int iMin = Math.min(i10, windowCount);
        updateStateForPendingOperation(handleAddMediaItems(iMin, list), new com.google.common.base.c0() { // from class: androidx.media3.common.u
            @Override // com.google.common.base.c0
            public final Object get() {
                return this.f3404i.lambda$addMediaItems$3(state, list, iMin);
            }
        });
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurface() {
        clearVideoOutput(null);
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        clearVideoOutput(surfaceHolder);
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurfaceView(SurfaceView surfaceView) {
        clearVideoOutput(surfaceView);
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoTextureView(TextureView textureView) {
        clearVideoOutput(textureView);
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void decreaseDeviceVolume() {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(26)) {
            updateStateForPendingOperation(handleDecreaseDeviceVolume(1), new k(state, 4));
        }
    }

    @Override // androidx.media3.common.Player
    public final Looper getApplicationLooper() {
        return this.applicationLooper;
    }

    @Override // androidx.media3.common.Player
    public final AudioAttributes getAudioAttributes() {
        verifyApplicationThreadAndInitState();
        return this.state.audioAttributes;
    }

    @Override // androidx.media3.common.BasePlayer, androidx.media3.common.Player
    public final int getAudioSessionId() {
        verifyApplicationThreadAndInitState();
        return this.state.audioSessionId;
    }

    @Override // androidx.media3.common.Player
    public final Player.Commands getAvailableCommands() {
        verifyApplicationThreadAndInitState();
        return this.state.availableCommands;
    }

    @Override // androidx.media3.common.Player
    public final long getBufferedPosition() {
        verifyApplicationThreadAndInitState();
        return isPlayingAd() ? Math.max(this.state.adBufferedPositionMsSupplier.get(), this.state.adPositionMsSupplier.get()) : getContentBufferedPosition();
    }

    @Override // androidx.media3.common.Player
    public final long getContentBufferedPosition() {
        verifyApplicationThreadAndInitState();
        return Math.max(getContentBufferedPositionMsInternal(this.state, this.window), getContentPositionMsInternal(this.state, this.window));
    }

    @Override // androidx.media3.common.Player
    public final long getContentPosition() {
        verifyApplicationThreadAndInitState();
        return getContentPositionMsInternal(this.state, this.window);
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentAdGroupIndex() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdGroupIndex;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentAdIndexInAdGroup() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdIndexInAdGroup;
    }

    @Override // androidx.media3.common.Player
    public final CueGroup getCurrentCues() {
        verifyApplicationThreadAndInitState();
        return this.state.currentCues;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentMediaItemIndex() {
        verifyApplicationThreadAndInitState();
        return getCurrentMediaItemIndexInternal(this.state);
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentPeriodIndex() {
        verifyApplicationThreadAndInitState();
        return getCurrentPeriodIndexInternal(this.state, this.window, this.period);
    }

    @Override // androidx.media3.common.Player
    public final long getCurrentPosition() {
        verifyApplicationThreadAndInitState();
        return isPlayingAd() ? this.state.adPositionMsSupplier.get() : getContentPosition();
    }

    @Override // androidx.media3.common.Player
    public final Timeline getCurrentTimeline() {
        verifyApplicationThreadAndInitState();
        return this.state.timeline;
    }

    @Override // androidx.media3.common.Player
    public final Tracks getCurrentTracks() {
        verifyApplicationThreadAndInitState();
        return this.state.currentTracks;
    }

    @Override // androidx.media3.common.Player
    public final DeviceInfo getDeviceInfo() {
        verifyApplicationThreadAndInitState();
        return this.state.deviceInfo;
    }

    @Override // androidx.media3.common.Player
    public final int getDeviceVolume() {
        verifyApplicationThreadAndInitState();
        return this.state.deviceVolume;
    }

    @Override // androidx.media3.common.Player
    public final long getDuration() {
        verifyApplicationThreadAndInitState();
        if (!isPlayingAd()) {
            return getContentDuration();
        }
        this.state.timeline.getPeriod(getCurrentPeriodIndex(), this.period);
        Timeline.Period period = this.period;
        State state = this.state;
        return Util.usToMs(period.getAdDurationUs(state.currentAdGroupIndex, state.currentAdIndexInAdGroup));
    }

    @Override // androidx.media3.common.Player
    public final long getMaxSeekToPreviousPosition() {
        verifyApplicationThreadAndInitState();
        return this.state.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.common.Player
    public final MediaMetadata getMediaMetadata() {
        verifyApplicationThreadAndInitState();
        return this.state.currentMetadata;
    }

    public MediaItemData getPlaceholderMediaItemData(MediaItem mediaItem) {
        return new MediaItemData.Builder(new PlaceholderUid()).setMediaItem(mediaItem).setIsDynamic(true).setIsPlaceholder(true).build();
    }

    public State getPlaceholderState(State state) {
        return state;
    }

    @Override // androidx.media3.common.Player
    public final boolean getPlayWhenReady() {
        verifyApplicationThreadAndInitState();
        return this.state.playWhenReady;
    }

    @Override // androidx.media3.common.Player
    public final PlaybackParameters getPlaybackParameters() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackParameters;
    }

    @Override // androidx.media3.common.Player
    public final int getPlaybackState() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackState;
    }

    @Override // androidx.media3.common.Player
    public final int getPlaybackSuppressionReason() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackSuppressionReason;
    }

    @Override // androidx.media3.common.Player
    public final PlaybackException getPlayerError() {
        verifyApplicationThreadAndInitState();
        return this.state.playerError;
    }

    @Override // androidx.media3.common.Player
    public final MediaMetadata getPlaylistMetadata() {
        verifyApplicationThreadAndInitState();
        return this.state.playlistMetadata;
    }

    @Override // androidx.media3.common.Player
    public final int getRepeatMode() {
        verifyApplicationThreadAndInitState();
        return this.state.repeatMode;
    }

    @Override // androidx.media3.common.Player
    public final long getSeekBackIncrement() {
        verifyApplicationThreadAndInitState();
        return this.state.seekBackIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public final long getSeekForwardIncrement() {
        verifyApplicationThreadAndInitState();
        return this.state.seekForwardIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public final boolean getShuffleModeEnabled() {
        verifyApplicationThreadAndInitState();
        return this.state.shuffleModeEnabled;
    }

    public abstract State getState();

    @Override // androidx.media3.common.Player
    public final Size getSurfaceSize() {
        verifyApplicationThreadAndInitState();
        return this.state.surfaceSize;
    }

    @Override // androidx.media3.common.Player
    public final long getTotalBufferedDuration() {
        verifyApplicationThreadAndInitState();
        return this.state.totalBufferedDurationMsSupplier.get();
    }

    @Override // androidx.media3.common.Player
    public final TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThreadAndInitState();
        return this.state.trackSelectionParameters;
    }

    @Override // androidx.media3.common.Player
    public final VideoSize getVideoSize() {
        verifyApplicationThreadAndInitState();
        return this.state.videoSize;
    }

    @Override // androidx.media3.common.Player
    public final float getVolume() {
        verifyApplicationThreadAndInitState();
        return this.state.volume;
    }

    public d1<?> handleAddMediaItems(int i10, List<MediaItem> list) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    public d1<?> handleClearVideoOutput(Object obj) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VIDEO_SURFACE");
    }

    public d1<?> handleDecreaseDeviceVolume(int i10) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    public d1<?> handleIncreaseDeviceVolume(int i10) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    public d1<?> handleMoveMediaItems(int i10, int i11, int i12) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    public d1<?> handlePrepare() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_PREPARE");
    }

    public d1<?> handleRelease() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_RELEASE");
    }

    public d1<?> handleRemoveMediaItems(int i10, int i11) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    public d1<?> handleReplaceMediaItems(int i10, int i11, List<MediaItem> list) {
        d1<?> d1VarHandleAddMediaItems = handleAddMediaItems(i11, list);
        return i10 == i11 ? d1VarHandleAddMediaItems : Util.transformFutureAsync(d1VarHandleAddMediaItems, new a3.b(handleRemoveMediaItems(i10, i11), 3));
    }

    public d1<?> handleSeek(int i10, long j10, int i11) {
        throw new IllegalStateException("Missing implementation to handle one of the COMMAND_SEEK_*");
    }

    public d1<?> handleSetAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_AUDIO_ATTRIBUTES");
    }

    public d1<?> handleSetDeviceMuted(boolean z, int i10) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    public d1<?> handleSetDeviceVolume(int i10, int i11) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_DEVICE_VOLUME or COMMAND_SET_DEVICE_VOLUME_WITH_FLAGS");
    }

    public d1<?> handleSetMediaItems(List<MediaItem> list, int i10, long j10) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_MEDIA_ITEM(S)");
    }

    public d1<?> handleSetPlayWhenReady(boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_PLAY_PAUSE");
    }

    public d1<?> handleSetPlaybackParameters(PlaybackParameters playbackParameters) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_SPEED_AND_PITCH");
    }

    public d1<?> handleSetPlaylistMetadata(MediaMetadata mediaMetadata) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_PLAYLIST_METADATA");
    }

    public d1<?> handleSetRepeatMode(int i10) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_REPEAT_MODE");
    }

    public d1<?> handleSetShuffleModeEnabled(boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_SHUFFLE_MODE");
    }

    public d1<?> handleSetTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_TRACK_SELECTION_PARAMETERS");
    }

    public d1<?> handleSetVideoOutput(Object obj) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VIDEO_SURFACE");
    }

    @Deprecated
    public d1<?> handleSetVolume(float f10) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VOLUME");
    }

    public d1<?> handleStop() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_STOP");
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void increaseDeviceVolume() {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(26)) {
            updateStateForPendingOperation(handleIncreaseDeviceVolume(1), new k(state, 7));
        }
    }

    public final void invalidateState() {
        verifyApplicationThreadAndInitState();
        if (!this.pendingOperations.isEmpty() || this.released) {
            return;
        }
        updateStateAndInformListeners(getState(), false, false);
    }

    @Override // androidx.media3.common.Player
    public final boolean isDeviceMuted() {
        verifyApplicationThreadAndInitState();
        return this.state.isDeviceMuted;
    }

    @Override // androidx.media3.common.Player
    public final boolean isLoading() {
        verifyApplicationThreadAndInitState();
        return this.state.isLoading;
    }

    @Override // androidx.media3.common.Player
    public final boolean isPlayingAd() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdGroupIndex != -1;
    }

    @Override // androidx.media3.common.Player
    public final void moveMediaItems(final int i10, int i11, int i12) {
        verifyApplicationThreadAndInitState();
        ac.b.j(i10 >= 0 && i11 >= i10 && i12 >= 0);
        final State state = this.state;
        int windowCount = state.timeline.getWindowCount();
        if (shouldHandleCommand(20) && windowCount != 0 && i10 < windowCount) {
            final int iMin = Math.min(i11, windowCount);
            final int iMin2 = Math.min(i12, windowCount - (iMin - i10));
            if (i10 != iMin && iMin2 != i10) {
                updateStateForPendingOperation(handleMoveMediaItems(i10, iMin, iMin2), new com.google.common.base.c0() { // from class: androidx.media3.common.w
                    @Override // com.google.common.base.c0
                    public final Object get() {
                        return this.f3433i.lambda$moveMediaItems$4(state, i10, iMin, iMin2);
                    }
                });
            }
        }
    }

    @Override // androidx.media3.common.Player
    public final void mute() {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(24) && this.state.volume != 0.0f) {
            updateStateForPendingOperation(handleSetVolume(0.0f, 1), new k(state, 1));
        }
    }

    @Override // androidx.media3.common.Player
    public final void prepare() {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(2)) {
            updateStateForPendingOperation(handlePrepare(), new k(state, 8));
        }
    }

    @Override // androidx.media3.common.Player
    public final void release() {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(32)) {
            updateStateForPendingOperation(handleRelease(), new k(state, 5));
            this.released = true;
            this.listeners.release();
            this.state = this.state.buildUpon().setPlaybackState(1).setTotalBufferedDurationMs(PositionSupplier.ZERO).setContentBufferedPositionMs(b0.a(getContentPositionMsInternal(state, this.window))).setAdBufferedPositionMs(state.adPositionMsSupplier).setIsLoading(false).build();
        }
    }

    @Override // androidx.media3.common.Player
    public final void removeListener(Player.Listener listener) {
        verifyApplicationThreadAndInitState();
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.common.Player
    public final void removeMediaItems(final int i10, int i11) {
        final int iMin;
        verifyApplicationThreadAndInitState();
        ac.b.j(i10 >= 0 && i11 >= i10);
        final State state = this.state;
        int windowCount = state.timeline.getWindowCount();
        if (!shouldHandleCommand(20) || windowCount == 0 || i10 >= windowCount || i10 == (iMin = Math.min(i11, windowCount))) {
            return;
        }
        updateStateForPendingOperation(handleRemoveMediaItems(i10, iMin), new com.google.common.base.c0() { // from class: androidx.media3.common.y
            @Override // com.google.common.base.c0
            public final Object get() {
                return this.f3443i.lambda$removeMediaItems$6(state, i10, iMin);
            }
        });
    }

    @Override // androidx.media3.common.Player
    public final void replaceMediaItems(final int i10, int i11, final List<MediaItem> list) {
        verifyApplicationThreadAndInitState();
        ac.b.j(i10 >= 0 && i10 <= i11);
        final State state = this.state;
        int windowCount = state.timeline.getWindowCount();
        if (!shouldHandleCommand(20) || i10 > windowCount) {
            return;
        }
        final int iMin = Math.min(i11, windowCount);
        updateStateForPendingOperation(handleReplaceMediaItems(i10, iMin, list), new com.google.common.base.c0() { // from class: androidx.media3.common.q
            @Override // com.google.common.base.c0
            public final Object get() {
                return this.f3391i.lambda$replaceMediaItems$5(state, list, iMin, i10);
            }
        });
    }

    @Override // androidx.media3.common.BasePlayer
    public final void seekTo(final int i10, final long j10, int i11, boolean z) {
        verifyApplicationThreadAndInitState();
        ac.b.j(i10 == -1 || i10 >= 0);
        final State state = this.state;
        if (shouldHandleCommand(i11)) {
            final boolean z5 = i10 == -1 || isPlayingAd() || (!state.timeline.isEmpty() && i10 >= state.timeline.getWindowCount());
            updateStateForPendingOperation(handleSeek(i10, j10, i11), new com.google.common.base.c0() { // from class: androidx.media3.common.x
                @Override // com.google.common.base.c0
                public final Object get() {
                    return this.f3438i.lambda$seekTo$10(z5, state, i10, j10);
                }
            }, !z5, z);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(35)) {
            updateStateForPendingOperation(handleSetAudioAttributes(audioAttributes, z), new t(state, audioAttributes, 4));
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void setDeviceMuted(boolean z) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(26)) {
            updateStateForPendingOperation(handleSetDeviceMuted(z, 1), new n(state, z, 2));
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void setDeviceVolume(int i10) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(25)) {
            updateStateForPendingOperation(handleSetDeviceVolume(i10, 1), new p(state, i10, 0));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThreadAndInitState();
        setMediaItemsInternal(list, z ? -1 : this.state.currentMediaItemIndex, z ? C.TIME_UNSET : this.state.contentPositionMsSupplier.get());
    }

    @Override // androidx.media3.common.Player
    public final void setPlayWhenReady(boolean z) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(1)) {
            updateStateForPendingOperation(handleSetPlayWhenReady(z), new n(state, z, 0));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(13)) {
            updateStateForPendingOperation(handleSetPlaybackParameters(playbackParameters), new t(state, playbackParameters, 6));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(19)) {
            updateStateForPendingOperation(handleSetPlaylistMetadata(mediaMetadata), new t(state, mediaMetadata, 1));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setRepeatMode(int i10) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(15)) {
            updateStateForPendingOperation(handleSetRepeatMode(i10), new p(state, i10, 2));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setShuffleModeEnabled(boolean z) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(14)) {
            updateStateForPendingOperation(handleSetShuffleModeEnabled(z), new n(state, z, 1));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(29)) {
            updateStateForPendingOperation(handleSetTrackSelectionParameters(trackSelectionParameters), new t(state, trackSelectionParameters, 7));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurface(Surface surface) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(27)) {
            if (surface == null) {
                clearVideoSurface();
            } else {
                updateStateForPendingOperation(handleSetVideoOutput(surface), new k(state, 2));
            }
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(27)) {
            if (surfaceHolder == null) {
                clearVideoSurface();
            } else {
                updateStateForPendingOperation(handleSetVideoOutput(surfaceHolder), new t(state, surfaceHolder, 2));
            }
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(27)) {
            if (surfaceView == null) {
                clearVideoSurface();
            } else {
                updateStateForPendingOperation(handleSetVideoOutput(surfaceView), new t(state, surfaceView, 3));
            }
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoTextureView(TextureView textureView) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(27)) {
            if (textureView == null) {
                clearVideoSurface();
            } else {
                updateStateForPendingOperation(handleSetVideoOutput(textureView), new t(state, textureView.isAvailable() ? new Size(textureView.getWidth(), textureView.getHeight()) : Size.ZERO, 0));
            }
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVolume(final float f10) {
        verifyApplicationThreadAndInitState();
        final State state = this.state;
        if (shouldHandleCommand(24)) {
            updateStateForPendingOperation(handleSetVolume(f10, 0), new com.google.common.base.c0() { // from class: androidx.media3.common.v
                @Override // com.google.common.base.c0
                public final Object get() {
                    return SimpleBasePlayer.lambda$setVolume$16(state, f10);
                }
            });
        }
    }

    @Override // androidx.media3.common.Player
    public final void stop() {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(3)) {
            updateStateForPendingOperation(handleStop(), new t(this, state));
        }
    }

    @Override // androidx.media3.common.Player
    public final void unmute() {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(24) && this.state.volume == 0.0f) {
            updateStateForPendingOperation(handleSetVolume(state.unmuteVolume, 2), new k(state, 0));
        }
    }

    public final void verifyApplicationThread() {
        if (Thread.currentThread() != this.applicationLooper.getThread()) {
            throw new IllegalStateException(Util.formatInvariant("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\n", Thread.currentThread().getName(), this.applicationLooper.getThread().getName()));
        }
    }

    public SimpleBasePlayer(Looper looper, Clock clock) {
        this.applicationLooper = looper;
        this.applicationHandler = clock.createHandler(looper, null);
        this.pendingOperations = new HashSet<>();
        this.period = new Timeline.Period();
        this.listeners = new ListenerSet<>(looper, clock, new a3.b(this, 2));
    }

    @RequiresNonNull({"state"})
    private void updateStateForPendingOperation(d1<?> d1Var, com.google.common.base.c0<State> c0Var, boolean z, boolean z5) {
        if (d1Var.isDone() && this.pendingOperations.isEmpty()) {
            updateStateAndInformListeners(getState(), z, z5);
            return;
        }
        this.pendingOperations.add(d1Var);
        updateStateAndInformListeners(getPlaceholderState((State) c0Var.get()), z, z5);
        d1Var.addListener(new androidx.activity.s(this, d1Var, 2), new c(this, 1));
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurface(Surface surface) {
        clearVideoOutput(surface);
    }

    public d1<?> handleSetVolume(float f10, int i10) {
        return handleSetVolume(f10);
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list, int i10, long j10) {
        verifyApplicationThreadAndInitState();
        if (i10 == -1) {
            State state = this.state;
            int i11 = state.currentMediaItemIndex;
            long j11 = state.contentPositionMsSupplier.get();
            i10 = i11;
            j10 = j11;
        }
        setMediaItemsInternal(list, i10, j10);
    }

    @Override // androidx.media3.common.Player
    public final void decreaseDeviceVolume(int i10) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(34)) {
            updateStateForPendingOperation(handleDecreaseDeviceVolume(i10), new k(state, 9));
        }
    }

    @Override // androidx.media3.common.Player
    public final void increaseDeviceVolume(int i10) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(34)) {
            updateStateForPendingOperation(handleIncreaseDeviceVolume(i10), new k(state, 3));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setDeviceMuted(boolean z, int i10) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(34)) {
            updateStateForPendingOperation(handleSetDeviceMuted(z, i10), new n(state, z, 3));
        }
    }

    @Override // androidx.media3.common.Player
    public final void setDeviceVolume(int i10, int i11) {
        verifyApplicationThreadAndInitState();
        State state = this.state;
        if (shouldHandleCommand(33)) {
            updateStateForPendingOperation(handleSetDeviceVolume(i10, i11), new p(state, i10, 1));
        }
    }
}
