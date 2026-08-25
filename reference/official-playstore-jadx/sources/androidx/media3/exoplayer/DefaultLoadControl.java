package androidx.media3.exoplayer;

import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.RawResourceDataSource;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocation;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.exoplayer.upstream.PlayerIdAwareAllocator;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.l1;
import com.google.common.collect.t3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultLoadControl implements LoadControl {
    public static final int DEFAULT_AUDIO_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_BACK_BUFFER_DURATION_MS = 0;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_FOR_LOCAL_PLAYBACK_MS = 1000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 2000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_FOR_LOCAL_PLAYBACK_MS = 1000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_MS = 1000;
    public static final int DEFAULT_CAMERA_MOTION_BUFFER_SIZE = 131072;
    public static final int DEFAULT_IMAGE_BUFFER_SIZE = 26214400;
    public static final int DEFAULT_MAX_BUFFER_FOR_LOCAL_PLAYBACK_MS = 50000;
    public static final int DEFAULT_MAX_BUFFER_MS = 50000;
    public static final int DEFAULT_MAX_BUFFER_SIZE = 210239488;
    public static final int DEFAULT_METADATA_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MIN_BUFFER_FOR_LOCAL_PLAYBACK_MS = 1000;
    public static final int DEFAULT_MIN_BUFFER_MS = 50000;
    public static final int DEFAULT_MIN_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_MUXED_BUFFER_SIZE = 144310272;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS = false;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS_FOR_LOCAL_PLAYBACK = true;
    public static final boolean DEFAULT_RETAIN_BACK_BUFFER_FROM_KEYFRAME = false;
    public static final int DEFAULT_TARGET_BUFFER_BYTES = -1;
    public static final int DEFAULT_TARGET_BUFFER_BYTES_FOR_PRELOAD = 144179200;
    public static final int DEFAULT_TEXT_BUFFER_SIZE = 131072;
    public static final int DEFAULT_VIDEO_BUFFER_SIZE = 131072000;
    public static final int DEFAULT_VIDEO_BUFFER_SIZE_FOR_LOCAL_PLAYBACK = 19660800;
    public static final h1 LOCAL_PLAYBACK_SCHEMES;
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferForLocalPlaybackUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackForLocalPlaybackUs;
    private final long bufferForPlaybackUs;
    private final ConcurrentHashMap<PlayerId, PlayerLoadingState> loadingStates;
    private final long maxBufferForLocalPlaybackUs;
    private final long maxBufferUs;
    private final long minBufferForLocalPlaybackUs;
    private final long minBufferUs;
    private final Timeline.Period period;
    private final l1 playerTargetBufferBytesOverwrites;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean prioritizeTimeOverSizeThresholdsForLocalPlayback;
    private final boolean retainBackBufferFromKeyframe;
    private final int targetBufferBytesOverwrite;
    private long threadId;
    private final Timeline.Window window;

    public static final class Builder {
        private DefaultAllocator allocator;
        private int backBufferDurationMs;
        private int bufferForPlaybackAfterRebufferForLocalPlaybackMs;
        private int bufferForPlaybackAfterRebufferMs;
        private int bufferForPlaybackForLocalPlaybackMs;
        private int bufferForPlaybackMs;
        private boolean buildCalled;
        private int maxBufferForLocalPlaybackMs;
        private int maxBufferMs;
        private int minBufferForLocalPlaybackMs;
        private int minBufferMs;
        private Boolean onlyGenericConfigurationMethodsCalled;
        private final HashMap<String, Integer> playerTargetBufferBytes;
        private boolean prioritizeTimeOverSizeThresholds;
        private boolean prioritizeTimeOverSizeThresholdsForLocalPlayback;
        private boolean retainBackBufferFromKeyframe;
        private int targetBufferBytes;

        public Builder() {
            HashMap<String, Integer> map = new HashMap<>();
            this.playerTargetBufferBytes = map;
            map.put(PlayerId.PRELOAD.name, Integer.valueOf(DefaultLoadControl.DEFAULT_TARGET_BUFFER_BYTES_FOR_PRELOAD));
            this.minBufferMs = 50000;
            this.minBufferForLocalPlaybackMs = 1000;
            this.maxBufferMs = 50000;
            this.maxBufferForLocalPlaybackMs = 50000;
            this.bufferForPlaybackMs = 1000;
            this.bufferForPlaybackForLocalPlaybackMs = 1000;
            this.bufferForPlaybackAfterRebufferMs = 2000;
            this.bufferForPlaybackAfterRebufferForLocalPlaybackMs = 1000;
            this.targetBufferBytes = -1;
            this.prioritizeTimeOverSizeThresholds = false;
            this.prioritizeTimeOverSizeThresholdsForLocalPlayback = true;
            this.backBufferDurationMs = 0;
            this.retainBackBufferFromKeyframe = false;
        }

        public DefaultLoadControl build() {
            ac.b.s(!this.buildCalled);
            this.buildCalled = true;
            if (this.allocator == null) {
                this.allocator = new DefaultAllocator(true, 65536);
            }
            Boolean bool = this.onlyGenericConfigurationMethodsCalled;
            if (bool != null && bool.booleanValue()) {
                this.minBufferForLocalPlaybackMs = this.minBufferMs;
                this.maxBufferForLocalPlaybackMs = this.maxBufferMs;
                this.bufferForPlaybackForLocalPlaybackMs = this.bufferForPlaybackMs;
                this.bufferForPlaybackAfterRebufferForLocalPlaybackMs = this.bufferForPlaybackAfterRebufferMs;
                this.prioritizeTimeOverSizeThresholdsForLocalPlayback = this.prioritizeTimeOverSizeThresholds;
            }
            return new DefaultLoadControl(this.allocator, this.minBufferMs, this.minBufferForLocalPlaybackMs, this.maxBufferMs, this.maxBufferForLocalPlaybackMs, this.bufferForPlaybackMs, this.bufferForPlaybackForLocalPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.bufferForPlaybackAfterRebufferForLocalPlaybackMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.prioritizeTimeOverSizeThresholdsForLocalPlayback, this.backBufferDurationMs, this.retainBackBufferFromKeyframe, this.playerTargetBufferBytes);
        }

        public Builder setAllocator(DefaultAllocator defaultAllocator) {
            ac.b.s(!this.buildCalled);
            this.allocator = defaultAllocator;
            return this;
        }

        public Builder setBackBuffer(int i10, boolean z) {
            ac.b.s(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i10, 0, "backBufferDurationMs", "0");
            this.backBufferDurationMs = i10;
            this.retainBackBufferFromKeyframe = z;
            return this;
        }

        public Builder setBufferDurationsMs(int i10, int i11, int i12, int i13) {
            ac.b.s(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i12, 0, "bufferForPlaybackMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i13, 0, "bufferForPlaybackAfterRebufferMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i10, i12, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.assertGreaterOrEqual(i10, i13, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.assertGreaterOrEqual(i11, i10, "maxBufferMs", "minBufferMs");
            this.minBufferMs = i10;
            this.maxBufferMs = i11;
            this.bufferForPlaybackMs = i12;
            this.bufferForPlaybackAfterRebufferMs = i13;
            this.minBufferForLocalPlaybackMs = i10;
            this.maxBufferForLocalPlaybackMs = i11;
            this.bufferForPlaybackForLocalPlaybackMs = i12;
            this.bufferForPlaybackAfterRebufferForLocalPlaybackMs = i13;
            if (this.onlyGenericConfigurationMethodsCalled == null) {
                this.onlyGenericConfigurationMethodsCalled = Boolean.TRUE;
            }
            return this;
        }

        public Builder setBufferDurationsMsForLocalPlayback(int i10, int i11, int i12, int i13) {
            ac.b.s(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i12, 0, "bufferForPlaybackMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i13, 0, "bufferForPlaybackAfterRebufferMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i10, i12, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.assertGreaterOrEqual(i10, i13, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.assertGreaterOrEqual(i11, i10, "maxBufferMs", "minBufferMs");
            this.minBufferForLocalPlaybackMs = i10;
            this.maxBufferForLocalPlaybackMs = i11;
            this.bufferForPlaybackForLocalPlaybackMs = i12;
            this.bufferForPlaybackAfterRebufferForLocalPlaybackMs = i13;
            this.onlyGenericConfigurationMethodsCalled = Boolean.FALSE;
            return this;
        }

        public Builder setBufferDurationsMsForStreaming(int i10, int i11, int i12, int i13) {
            ac.b.s(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i12, 0, "bufferForPlaybackMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i13, 0, "bufferForPlaybackAfterRebufferMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i10, i12, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.assertGreaterOrEqual(i10, i13, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.assertGreaterOrEqual(i11, i10, "maxBufferMs", "minBufferMs");
            this.minBufferMs = i10;
            this.maxBufferMs = i11;
            this.bufferForPlaybackMs = i12;
            this.bufferForPlaybackAfterRebufferMs = i13;
            this.onlyGenericConfigurationMethodsCalled = Boolean.FALSE;
            return this;
        }

        public Builder setPlayerTargetBufferBytes(String str, int i10) {
            ac.b.s(!this.buildCalled);
            this.playerTargetBufferBytes.put(str, Integer.valueOf(i10));
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholds(boolean z) {
            ac.b.s(!this.buildCalled);
            this.prioritizeTimeOverSizeThresholds = z;
            this.prioritizeTimeOverSizeThresholdsForLocalPlayback = z;
            if (this.onlyGenericConfigurationMethodsCalled == null) {
                this.onlyGenericConfigurationMethodsCalled = Boolean.TRUE;
            }
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholdsForLocalPlayback(boolean z) {
            ac.b.s(!this.buildCalled);
            this.prioritizeTimeOverSizeThresholdsForLocalPlayback = z;
            this.onlyGenericConfigurationMethodsCalled = Boolean.FALSE;
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholdsForStreaming(boolean z) {
            ac.b.s(!this.buildCalled);
            this.prioritizeTimeOverSizeThresholds = z;
            this.onlyGenericConfigurationMethodsCalled = Boolean.FALSE;
            return this;
        }

        public Builder setTargetBufferBytes(int i10) {
            ac.b.s(!this.buildCalled);
            this.targetBufferBytes = i10;
            return this;
        }
    }

    public static class PlayerLoadingState {
        private int allocatedCounts;
        public boolean isLoading;
        public int referenceCount = 1;
        public int targetBufferBytes;

        public synchronized void decreaseAllocatedCounts() {
            this.allocatedCounts--;
        }

        public synchronized int getAllocatedCounts() {
            return this.allocatedCounts;
        }

        public synchronized void increaseAllocatedCounts() {
            this.allocatedCounts++;
        }
    }

    static {
        f1 f1Var = h1.f14020l;
        Object[] objArr = {"file", "content", "data", "android.resource", RawResourceDataSource.RAW_RESOURCE_SCHEME, "asset"};
        com.google.common.collect.a0.a(6, objArr);
        LOCAL_PLAYBACK_SCHEMES = h1.h(6, objArr);
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536), 50000, 1000, 50000, 50000, 1000, 1000, 2000, 1000, -1, false, true, 0, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertGreaterOrEqual(int i10, int i11, String str, String str2) {
        ac.b.m(i10 >= i11, "%s cannot be less than %s", str, str2);
    }

    private long getBufferForPlaybackAfterRebufferUs(boolean z) {
        return z ? this.bufferForPlaybackAfterRebufferForLocalPlaybackUs : this.bufferForPlaybackAfterRebufferUs;
    }

    private long getBufferForPlaybackUs(boolean z) {
        return z ? this.bufferForPlaybackForLocalPlaybackUs : this.bufferForPlaybackUs;
    }

    private static int getDefaultBufferSize(int i10, boolean z) {
        switch (i10) {
            case -2:
                return 0;
            case -1:
                return 13107200;
            case 0:
                return DEFAULT_MUXED_BUFFER_SIZE;
            case 1:
                return 13107200;
            case 2:
                return z ? DEFAULT_VIDEO_BUFFER_SIZE_FOR_LOCAL_PLAYBACK : DEFAULT_VIDEO_BUFFER_SIZE;
            case 3:
                return 131072;
            case 4:
                return DEFAULT_IMAGE_BUFFER_SIZE;
            case 5:
            case 6:
                return 131072;
            default:
                throw new IllegalArgumentException();
        }
    }

    private long getMaxBufferUs(boolean z) {
        return z ? this.maxBufferForLocalPlaybackUs : this.maxBufferUs;
    }

    private long getMinBufferUs(boolean z) {
        return z ? this.minBufferForLocalPlaybackUs : this.minBufferUs;
    }

    private int getTargetBufferBytes(PlayerId playerId) {
        PlayerLoadingState playerLoadingState = this.loadingStates.get(playerId);
        playerLoadingState.getClass();
        return playerLoadingState.targetBufferBytes;
    }

    private int getTargetBufferBytesOverwrite(PlayerId playerId) {
        Integer num = (Integer) this.playerTargetBufferBytesOverwrites.get(playerId.name);
        return (num == null || num.intValue() == -1) ? this.targetBufferBytesOverwrite : num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTotalBufferBytesAllocated(PlayerId playerId) {
        PlayerLoadingState playerLoadingState = this.loadingStates.get(playerId);
        playerLoadingState.getClass();
        return this.allocator.getIndividualAllocationLength() * playerLoadingState.getAllocatedCounts();
    }

    private boolean isLocalPlayback(LoadControl.Parameters parameters) {
        MediaItem.LocalConfiguration localConfiguration = parameters.timeline.getWindow(parameters.timeline.getPeriodByUid(parameters.mediaPeriodId.periodUid, this.period).windowIndex, this.window).mediaItem.localConfiguration;
        if (localConfiguration == null) {
            return false;
        }
        String scheme = localConfiguration.uri.getScheme();
        return TextUtils.isEmpty(scheme) || LOCAL_PLAYBACK_SCHEMES.contains(scheme);
    }

    private boolean prioritizeTimeOverSizeThresholds(boolean z) {
        return z ? this.prioritizeTimeOverSizeThresholdsForLocalPlayback : this.prioritizeTimeOverSizeThresholds;
    }

    private void removePlayer(PlayerId playerId) {
        PlayerLoadingState playerLoadingState = this.loadingStates.get(playerId);
        if (playerLoadingState != null) {
            int i10 = playerLoadingState.referenceCount - 1;
            playerLoadingState.referenceCount = i10;
            if (i10 == 0) {
                this.loadingStates.remove(playerId);
                updateAllocator();
            }
        }
    }

    private void resetPlayerLoadingState(PlayerId playerId) {
        PlayerLoadingState playerLoadingState = this.loadingStates.get(playerId);
        playerLoadingState.getClass();
        int targetBufferBytesOverwrite = getTargetBufferBytesOverwrite(playerId);
        if (targetBufferBytesOverwrite == -1) {
            targetBufferBytesOverwrite = 13107200;
        }
        playerLoadingState.targetBufferBytes = targetBufferBytesOverwrite;
        playerLoadingState.isLoading = false;
    }

    private void updateAllocator() {
        if (this.loadingStates.isEmpty()) {
            this.allocator.reset();
        } else {
            this.allocator.setTargetBufferSize(calculateTotalTargetBufferBytes());
        }
    }

    @Deprecated
    public int calculateTargetBufferBytes(ExoTrackSelection[] exoTrackSelectionArr) {
        return -1;
    }

    public int calculateTotalTargetBufferBytes() {
        Iterator<PlayerLoadingState> it = this.loadingStates.values().iterator();
        int i10 = 0;
        while (it.hasNext()) {
            i10 += it.next().targetBufferBytes;
        }
        return i10;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public Allocator getAllocator(PlayerId playerId) {
        return new PlayerIdFilteringAllocatorImpl(playerId);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ long getBackBufferDurationUs() {
        return h0.a(this);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ void onPrepared() {
        h0.c(this);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ void onReleased() {
        h0.e(this);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ void onStopped() {
        h0.g(this);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ void onTracksSelected(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        h0.i(this, timeline, mediaPeriodId, rendererArr, trackGroupArray, exoTrackSelectionArr);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ boolean retainBackBufferFromKeyframe() {
        return h0.m(this);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ boolean shouldContinueLoading(long j10, long j11, float f10) {
        return h0.o(this, j10, j11, f10);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldContinuePreloading(PlayerId playerId, Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j10) {
        Iterator<PlayerLoadingState> it = this.loadingStates.values().iterator();
        while (it.hasNext()) {
            if (it.next().isLoading) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ boolean shouldStartPlayback(long j10, float f10, boolean z, long j11) {
        return h0.r(this, j10, f10, z, j11);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, boolean z, boolean z5, int i19, boolean z10, Map<String, Integer> map) {
        assertGreaterOrEqual(i14, 0, "bufferForPlaybackMs", "0");
        assertGreaterOrEqual(i15, 0, "bufferForPlaybackForLocalPlaybackMs", "0");
        assertGreaterOrEqual(i16, 0, "bufferForPlaybackAfterRebufferMs", "0");
        assertGreaterOrEqual(i17, 0, "bufferForPlaybackAfterRebufferForLocalPlaybackMs", "0");
        assertGreaterOrEqual(i10, i14, "minBufferMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(i11, i15, "minBufferForLocalPlaybackMs", "bufferForPlaybackForLocalPlaybackMs");
        assertGreaterOrEqual(i10, i16, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(i11, i17, "minBufferForLocalPlaybackMs", "bufferForPlaybackAfterRebufferForLocalPlaybackMs");
        assertGreaterOrEqual(i12, i10, "maxBufferMs", "minBufferMs");
        assertGreaterOrEqual(i13, i11, "maxBufferForLocalPlaybackMs", "minBufferForLocalPlaybackMs");
        assertGreaterOrEqual(i19, 0, "backBufferDurationMs", "0");
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.allocator = defaultAllocator;
        this.minBufferUs = Util.msToUs(i10);
        this.minBufferForLocalPlaybackUs = Util.msToUs(i11);
        this.maxBufferUs = Util.msToUs(i12);
        this.maxBufferForLocalPlaybackUs = Util.msToUs(i13);
        this.bufferForPlaybackUs = Util.msToUs(i14);
        this.bufferForPlaybackForLocalPlaybackUs = Util.msToUs(i15);
        this.bufferForPlaybackAfterRebufferUs = Util.msToUs(i16);
        this.bufferForPlaybackAfterRebufferForLocalPlaybackUs = Util.msToUs(i17);
        this.targetBufferBytesOverwrite = i18;
        this.prioritizeTimeOverSizeThresholds = z;
        this.prioritizeTimeOverSizeThresholdsForLocalPlayback = z5;
        this.backBufferDurationUs = Util.msToUs(i19);
        this.retainBackBufferFromKeyframe = z10;
        this.loadingStates = new ConcurrentHashMap<>();
        this.playerTargetBufferBytesOverwrites = l1.b(map);
        this.threadId = -1L;
    }

    public int calculateTargetBufferBytes(LoadControl.Parameters parameters, ExoTrackSelection[] exoTrackSelectionArr) {
        int iCalculateTargetBufferBytes = calculateTargetBufferBytes(exoTrackSelectionArr);
        if (iCalculateTargetBufferBytes != -1) {
            return iCalculateTargetBufferBytes;
        }
        boolean zIsLocalPlayback = isLocalPlayback(parameters);
        int defaultBufferSize = 0;
        for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
            if (exoTrackSelection != null) {
                defaultBufferSize += getDefaultBufferSize(exoTrackSelection.getTrackGroup().type, zIsLocalPlayback);
            }
        }
        return Util.constrainValue(defaultBufferSize, 13107200, DEFAULT_MAX_BUFFER_SIZE);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public long getBackBufferDurationUs(PlayerId playerId) {
        return this.backBufferDurationUs;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onPrepared(PlayerId playerId) {
        long id = Thread.currentThread().getId();
        long j10 = this.threadId;
        ac.b.t(j10 == -1 || j10 == id, "Players that share the same LoadControl must share the same playback thread. See ExoPlayer.Builder.setPlaybackLooper(Looper).");
        this.threadId = id;
        PlayerLoadingState playerLoadingState = this.loadingStates.get(playerId);
        if (playerLoadingState == null) {
            this.loadingStates.put(playerId, new PlayerLoadingState());
        } else {
            playerLoadingState.referenceCount++;
        }
        resetPlayerLoadingState(playerId);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onReleased(PlayerId playerId) {
        removePlayer(playerId);
        if (this.loadingStates.isEmpty()) {
            this.threadId = -1L;
        }
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onStopped(PlayerId playerId) {
        removePlayer(playerId);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ void onTracksSelected(PlayerId playerId, Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        h0.k(this, playerId, timeline, mediaPeriodId, rendererArr, trackGroupArray, exoTrackSelectionArr);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean retainBackBufferFromKeyframe(PlayerId playerId) {
        return this.retainBackBufferFromKeyframe;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldContinueLoading(LoadControl.Parameters parameters) {
        PlayerId playerId = parameters.playerId;
        PlayerLoadingState playerLoadingState = this.loadingStates.get(playerId);
        playerLoadingState.getClass();
        boolean z = getTotalBufferBytesAllocated(playerId) >= getTargetBufferBytes(playerId);
        if (playerId.equals(PlayerId.PRELOAD)) {
            return !z;
        }
        boolean zIsLocalPlayback = isLocalPlayback(parameters);
        long minBufferUs = getMinBufferUs(zIsLocalPlayback);
        long maxBufferUs = getMaxBufferUs(zIsLocalPlayback);
        float f10 = parameters.playbackSpeed;
        if (f10 > 1.0f) {
            minBufferUs = Math.min(Util.getMediaDurationForPlayoutDuration(minBufferUs, f10), maxBufferUs);
        }
        long jMax = Math.max(minBufferUs, 500000L);
        long j10 = parameters.bufferedDurationUs;
        if (j10 < jMax) {
            boolean z5 = prioritizeTimeOverSizeThresholds(zIsLocalPlayback) || !z;
            playerLoadingState.isLoading = z5;
            if (!z5 && parameters.bufferedDurationUs < 500000) {
                Log.w("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (j10 >= maxBufferUs || z) {
            playerLoadingState.isLoading = false;
        }
        return playerLoadingState.isLoading;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ boolean shouldStartPlayback(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j10, float f10, boolean z, long j11) {
        return h0.s(this, timeline, mediaPeriodId, j10, f10, z, j11);
    }

    public final class PlayerIdFilteringAllocatorImpl implements PlayerIdAwareAllocator {
        private final HashMap<Allocation, PlayerId> allocationPlayerIdMap = new HashMap<>();
        private PlayerId playerId;

        public PlayerIdFilteringAllocatorImpl(PlayerId playerId) {
            this.playerId = playerId;
        }

        private void releaseInternal(Allocation allocation) {
            PlayerId playerIdRemove = this.allocationPlayerIdMap.remove(allocation);
            playerIdRemove.getClass();
            PlayerLoadingState playerLoadingState = (PlayerLoadingState) DefaultLoadControl.this.loadingStates.get(playerIdRemove);
            if (playerLoadingState != null) {
                playerLoadingState.decreaseAllocatedCounts();
            }
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator
        public synchronized Allocation allocate() {
            Allocation allocationAllocate;
            allocationAllocate = DefaultLoadControl.this.allocator.allocate();
            this.allocationPlayerIdMap.put(allocationAllocate, this.playerId);
            PlayerLoadingState playerLoadingState = (PlayerLoadingState) DefaultLoadControl.this.loadingStates.get(this.playerId);
            if (playerLoadingState != null) {
                playerLoadingState.increaseAllocatedCounts();
            }
            return allocationAllocate;
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator
        public synchronized int getIndividualAllocationLength() {
            return DefaultLoadControl.this.allocator.getIndividualAllocationLength();
        }

        @Override // androidx.media3.exoplayer.upstream.PlayerIdAwareAllocator, androidx.media3.exoplayer.upstream.Allocator
        public synchronized int getTotalBytesAllocated() {
            return DefaultLoadControl.this.getTotalBufferBytesAllocated(this.playerId);
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator
        public synchronized void release(Allocation allocation) {
            DefaultLoadControl.this.allocator.release(allocation);
            releaseInternal(allocation);
        }

        @Override // androidx.media3.exoplayer.upstream.PlayerIdAwareAllocator
        public synchronized void setPlayerId(PlayerId playerId) {
            this.playerId = playerId;
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator
        public synchronized void trim() {
            DefaultLoadControl.this.allocator.trim();
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator
        public synchronized void release(Allocator.AllocationNode allocationNode) {
            DefaultLoadControl.this.allocator.release(allocationNode);
            while (allocationNode != null) {
                releaseInternal(allocationNode.getAllocation());
                allocationNode = allocationNode.next();
            }
        }
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public final /* synthetic */ void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        h0.l(this, rendererArr, trackGroupArray, exoTrackSelectionArr);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldStartPlayback(LoadControl.Parameters parameters) {
        long bufferForPlaybackUs;
        boolean zIsLocalPlayback = isLocalPlayback(parameters);
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(parameters.bufferedDurationUs, parameters.playbackSpeed);
        if (parameters.rebuffering) {
            bufferForPlaybackUs = getBufferForPlaybackAfterRebufferUs(zIsLocalPlayback);
        } else {
            bufferForPlaybackUs = getBufferForPlaybackUs(zIsLocalPlayback);
        }
        long j10 = parameters.targetLiveOffsetUs;
        if (j10 != C.TIME_UNSET) {
            bufferForPlaybackUs = Math.min(j10 / 2, bufferForPlaybackUs);
        }
        if (bufferForPlaybackUs <= 0 || playoutDurationForMediaDuration >= bufferForPlaybackUs) {
            return true;
        }
        return !prioritizeTimeOverSizeThresholds(zIsLocalPlayback) && getTotalBufferBytesAllocated(parameters.playerId) >= getTargetBufferBytes(parameters.playerId);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onTracksSelected(LoadControl.Parameters parameters, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        int targetBufferBytesOverwrite = getTargetBufferBytesOverwrite(parameters.playerId);
        PlayerLoadingState playerLoadingState = this.loadingStates.get(parameters.playerId);
        playerLoadingState.getClass();
        if (targetBufferBytesOverwrite == -1) {
            targetBufferBytesOverwrite = calculateTargetBufferBytes(parameters, exoTrackSelectionArr);
        }
        playerLoadingState.targetBufferBytes = targetBufferBytesOverwrite;
        updateAllocator();
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, boolean z, boolean z5, int i19, boolean z10) {
        this(defaultAllocator, i10, i11, i12, i13, i14, i15, i16, i17, i18, z, z5, i19, z10, t3.f14114q);
    }
}
