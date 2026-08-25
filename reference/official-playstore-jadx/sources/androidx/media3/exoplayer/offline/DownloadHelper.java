package androidx.media3.exoplayer.offline;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseIntArray;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.DefaultRendererCapabilitiesList;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererCapabilitiesList;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.j;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.offline.DownloadRequest;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.BaseTrackSelection;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectionUtil;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.SeekMap;
import com.google.common.base.c0;
import com.google.common.collect.h1;
import com.google.common.collect.o4;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class DownloadHelper {
    public static final DefaultTrackSelector.Parameters DEFAULT_TRACK_SELECTOR_PARAMETERS;

    @Deprecated
    public static final DefaultTrackSelector.Parameters DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT;
    private static final int MODE_NOT_PREPARE = 0;
    private static final int MODE_PREPARE_NON_PROGRESSIVE_SOURCE_AND_SELECT_TRACKS = 2;
    private static final int MODE_PREPARE_PROGRESSIVE_SOURCE = 1;
    private static final String TAG = "DownloadHelper";
    private boolean areTracksSelected;
    private Callback callback;
    private final Handler callbackHandler;
    private final boolean debugLoggingEnabled;
    private List<ExoTrackSelection>[][] immutableTrackSelectionsByPeriodAndRenderer;
    private boolean isPreparedWithMedia;
    private final MediaItem.LocalConfiguration localConfiguration;
    private MappingTrackSelector.MappedTrackInfo[] mappedTrackInfos;
    private MediaPreparer mediaPreparer;
    private final MediaSource mediaSource;
    private final int mode;
    private final RendererCapabilitiesList rendererCapabilities;
    private final SparseIntArray scratchSet;
    private TrackGroupArray[] trackGroupArrays;
    private List<ExoTrackSelection>[][] trackSelectionsByPeriodAndRenderer;
    private final DefaultTrackSelector trackSelector;
    private final Timeline.Window window;

    public interface Callback {
        void onPrepareError(DownloadHelper downloadHelper, IOException iOException);

        void onPrepared(DownloadHelper downloadHelper, boolean z);
    }

    public static final class DownloadTrackSelection extends BaseTrackSelection {

        public static final class Factory implements ExoTrackSelection.Factory {
            private Factory() {
            }

            @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection.Factory
            public ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
                ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
                for (int i10 = 0; i10 < definitionArr.length; i10++) {
                    ExoTrackSelection.Definition definition = definitionArr[i10];
                    exoTrackSelectionArr[i10] = definition == null ? null : new DownloadTrackSelection(definition.group, definition.tracks);
                }
                return exoTrackSelectionArr;
            }
        }

        public DownloadTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Object getSelectionData() {
            return null;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long j10, long j11, long j12, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        }
    }

    public static final class FakeBandwidthMeter implements BandwidthMeter {
        private FakeBandwidthMeter() {
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public long getBitrateEstimate() {
            return 0L;
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public final /* synthetic */ long getTimeToFirstByteEstimateUs() {
            return androidx.media3.exoplayer.upstream.a.a(this);
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public TransferListener getTransferListener() {
            return null;
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        }
    }

    public static class LiveContentUnsupportedException extends IOException {
    }

    public static final class MediaPreparer implements MediaSource.MediaSourceCaller, ProgressiveMediaSource.Listener, MediaPeriod.Callback, Handler.Callback {
        private static final int DOWNLOAD_HELPER_CALLBACK_MESSAGE_FAILED = 2;
        private static final int DOWNLOAD_HELPER_CALLBACK_MESSAGE_PREPARED = 1;
        private static final int MESSAGE_CHECK_FOR_FAILURE = 2;
        private static final int MESSAGE_CONTINUE_LOADING = 3;
        private static final int MESSAGE_PREPARE_SOURCE = 1;
        private static final int MESSAGE_RELEASE = 4;
        private final DownloadHelper downloadHelper;
        public MediaPeriod[] mediaPeriods;
        private final MediaSource mediaSource;
        private final Handler mediaSourceHandler;
        private final HandlerThread mediaSourceThread;
        private boolean released;
        public SeekMap seekMap;
        public Timeline timeline;
        private final Allocator allocator = new DefaultAllocator(true, 65536);
        private final ArrayList<MediaPeriod> pendingMediaPeriods = new ArrayList<>();
        private final Handler downloadHelperHandler = Util.createHandlerForCurrentOrMainLooper(new d(this, 0));

        public MediaPreparer(MediaSource mediaSource, DownloadHelper downloadHelper) {
            this.mediaSource = mediaSource;
            this.downloadHelper = downloadHelper;
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:DownloadHelper");
            this.mediaSourceThread = handlerThread;
            handlerThread.start();
            Handler handlerCreateHandler = Util.createHandler(handlerThread.getLooper(), this);
            this.mediaSourceHandler = handlerCreateHandler;
            handlerCreateHandler.sendEmptyMessage(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean handleDownloadHelperCallbackMessage(Message message) {
            if (this.released) {
                return false;
            }
            int i10 = message.what;
            if (i10 == 1) {
                try {
                    this.downloadHelper.onMediaPrepared();
                } catch (ExoPlaybackException e5) {
                    this.downloadHelperHandler.obtainMessage(2, new IOException(e5)).sendToTarget();
                }
                return true;
            }
            if (i10 != 2) {
                return false;
            }
            release();
            this.downloadHelper.onMediaPreparationFailed((IOException) Util.castNonNull(message.obj));
            return true;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i10 = message.what;
            if (i10 == 1) {
                MediaSource mediaSource = this.mediaSource;
                if (mediaSource instanceof ProgressiveMediaSource) {
                    ((ProgressiveMediaSource) mediaSource).setListener(this);
                }
                this.mediaSource.prepareSource(this, null, PlayerId.UNSET);
                this.mediaSourceHandler.sendEmptyMessage(2);
                return true;
            }
            int i11 = 0;
            if (i10 == 2) {
                try {
                    if (this.mediaPeriods == null) {
                        this.mediaSource.maybeThrowSourceInfoRefreshError();
                    } else {
                        while (i11 < this.pendingMediaPeriods.size()) {
                            this.pendingMediaPeriods.get(i11).maybeThrowPrepareError();
                            i11++;
                        }
                    }
                    this.mediaSourceHandler.sendEmptyMessageDelayed(2, 100L);
                } catch (IOException e5) {
                    this.downloadHelperHandler.obtainMessage(2, e5).sendToTarget();
                }
                return true;
            }
            if (i10 == 3) {
                MediaPeriod mediaPeriod = (MediaPeriod) message.obj;
                if (this.pendingMediaPeriods.contains(mediaPeriod)) {
                    mediaPeriod.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(0L).build());
                }
                return true;
            }
            if (i10 != 4) {
                return false;
            }
            MediaPeriod[] mediaPeriodArr = this.mediaPeriods;
            if (mediaPeriodArr != null) {
                int length = mediaPeriodArr.length;
                while (i11 < length) {
                    this.mediaSource.releasePeriod(mediaPeriodArr[i11]);
                    i11++;
                }
            }
            MediaSource mediaSource2 = this.mediaSource;
            if (mediaSource2 instanceof ProgressiveMediaSource) {
                ((ProgressiveMediaSource) mediaSource2).clearListener();
            }
            this.mediaSource.releaseSource(this);
            this.mediaSourceHandler.removeCallbacksAndMessages(null);
            this.mediaSourceThread.quit();
            return true;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            this.pendingMediaPeriods.remove(mediaPeriod);
            if (this.pendingMediaPeriods.isEmpty()) {
                this.mediaSourceHandler.removeMessages(2);
                this.downloadHelperHandler.sendEmptyMessage(1);
            }
        }

        @Override // androidx.media3.exoplayer.source.ProgressiveMediaSource.Listener
        public void onSeekMap(MediaSource mediaSource, SeekMap seekMap) {
            this.seekMap = seekMap;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
        public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
            MediaPeriod[] mediaPeriodArr;
            if (this.timeline != null) {
                return;
            }
            if (timeline.getWindow(0, new Timeline.Window()).isLive()) {
                this.downloadHelperHandler.obtainMessage(2, new LiveContentUnsupportedException()).sendToTarget();
                return;
            }
            this.timeline = timeline;
            this.mediaPeriods = new MediaPeriod[timeline.getPeriodCount()];
            int i10 = 0;
            while (true) {
                mediaPeriodArr = this.mediaPeriods;
                if (i10 >= mediaPeriodArr.length) {
                    break;
                }
                MediaPeriod mediaPeriodCreatePeriod = this.mediaSource.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(i10)), this.allocator, 0L);
                this.mediaPeriods[i10] = mediaPeriodCreatePeriod;
                this.pendingMediaPeriods.add(mediaPeriodCreatePeriod);
                i10++;
            }
            for (MediaPeriod mediaPeriod : mediaPeriodArr) {
                mediaPeriod.prepare(this, 0L);
            }
        }

        public void release() {
            if (this.released) {
                return;
            }
            this.released = true;
            this.mediaSourceHandler.sendEmptyMessage(4);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            if (this.pendingMediaPeriods.contains(mediaPeriod)) {
                this.mediaSourceHandler.obtainMessage(3, mediaPeriod).sendToTarget();
            }
        }
    }

    public static final class UnreleaseableRendererCapabilitiesList implements RendererCapabilitiesList {
        private final RendererCapabilities[] rendererCapabilities;

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public RendererCapabilities[] getRendererCapabilities() {
            return this.rendererCapabilities;
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public void release() {
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public int size() {
            return this.rendererCapabilities.length;
        }

        private UnreleaseableRendererCapabilitiesList(RendererCapabilities[] rendererCapabilitiesArr) {
            this.rendererCapabilities = rendererCapabilitiesArr;
        }
    }

    static {
        DefaultTrackSelector.Parameters parametersBuild = DefaultTrackSelector.Parameters.DEFAULT.buildUpon().setForceHighestSupportedBitrate(true).setConstrainAudioChannelCountToDeviceCapabilities(false).build();
        DEFAULT_TRACK_SELECTOR_PARAMETERS = parametersBuild;
        DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT = parametersBuild;
    }

    public DownloadHelper(MediaItem mediaItem, MediaSource mediaSource, TrackSelectionParameters trackSelectionParameters, RendererCapabilitiesList rendererCapabilitiesList) {
        this(mediaItem, mediaSource, trackSelectionParameters, rendererCapabilitiesList, false);
    }

    @RequiresNonNull({"trackGroupArrays", "trackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline"})
    private void addTrackSelectionInternal(int i10, TrackSelectionParameters trackSelectionParameters) throws ExoPlaybackException {
        this.trackSelector.setParameters(trackSelectionParameters);
        runTrackSelection(i10);
        o4 o4VarG = trackSelectionParameters.overrides.values().iterator();
        while (o4VarG.hasNext()) {
            this.trackSelector.setParameters(trackSelectionParameters.buildUpon().setOverrideForType((TrackSelectionOverride) o4VarG.next()).build());
            runTrackSelection(i10);
        }
    }

    @EnsuresNonNull({"mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    private void assertPreparedWithMedia() {
        ac.b.s(this.mode != 0);
        ac.b.s(this.isPreparedWithMedia);
    }

    @EnsuresNonNull({"trackGroupArrays", "mappedTrackInfos", "trackSelectionsByPeriodAndRenderer", "immutableTrackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    private void assertPreparedWithNonProgressiveSourceAndTracksSelected() {
        ac.b.s(this.mode == 2);
        ac.b.s(this.isPreparedWithMedia);
        ac.b.s(this.areTracksSelected);
    }

    @EnsuresNonNull({"mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.seekMap", "mediaPreparer.mediaPeriods"})
    private void assertPreparedWithProgressiveSource() {
        ac.b.s(this.mode == 1);
        ac.b.s(this.isPreparedWithMedia);
    }

    public static MediaSource createMediaSource(DownloadRequest downloadRequest, DataSource.Factory factory) {
        return createMediaSource(downloadRequest, factory, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaSource createMediaSourceInternal(MediaItem mediaItem, DataSource.Factory factory, DrmSessionManager drmSessionManager, c0<ReleasableExecutor> c0Var) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        localConfiguration.getClass();
        MediaSource.Factory factory2 = isProgressive(localConfiguration) ? new ProgressiveMediaSource.Factory(factory) : new DefaultMediaSourceFactory(factory, ExtractorsFactory.EMPTY);
        if (c0Var != null) {
            factory2.setDownloadExecutor(c0Var);
        }
        if (drmSessionManager != null) {
            factory2.setDrmSessionManagerProvider(new b(drmSessionManager));
        }
        return factory2.createMediaSource(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        localConfiguration.getClass();
        ac.b.j(isProgressive(localConfiguration));
        return new Factory().create(mediaItem);
    }

    @Deprecated
    public static DefaultTrackSelector.Parameters getDefaultTrackSelectorParameters(Context context) {
        return DEFAULT_TRACK_SELECTOR_PARAMETERS;
    }

    private DownloadRequest.Builder getDownloadRequestBuilder(String str, byte[] bArr) {
        DownloadRequest.Builder mimeType = new DownloadRequest.Builder(str, this.localConfiguration.uri).setMimeType(this.localConfiguration.mimeType);
        MediaItem.DrmConfiguration drmConfiguration = this.localConfiguration.drmConfiguration;
        DownloadRequest.Builder data = mimeType.setKeySetId(drmConfiguration != null ? drmConfiguration.getKeySetId() : null).setCustomCacheKey(this.localConfiguration.customCacheKey).setData(bArr);
        if (this.mode == 2) {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int length = this.trackSelectionsByPeriodAndRenderer.length;
            for (int i10 = 0; i10 < length; i10++) {
                arrayList2.clear();
                int length2 = this.trackSelectionsByPeriodAndRenderer[i10].length;
                for (int i11 = 0; i11 < length2; i11++) {
                    arrayList2.addAll(this.trackSelectionsByPeriodAndRenderer[i10][i11]);
                }
                arrayList.addAll(this.mediaPreparer.mediaPeriods[i10].getStreamKeys(arrayList2));
            }
            data.setStreamKeys(arrayList);
        }
        return data;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isProgressive(MediaItem.LocalConfiguration localConfiguration) {
        return Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType) == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ DrmSessionManager lambda$createMediaSourceInternal$4(DrmSessionManager drmSessionManager, MediaItem mediaItem) {
        return drmSessionManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onMediaPreparationFailed$3(IOException iOException) {
        Callback callback = this.callback;
        callback.getClass();
        callback.onPrepareError(this, iOException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onMediaPrepared$2(boolean z) {
        Callback callback = this.callback;
        callback.getClass();
        callback.onPrepared(this, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$1(Callback callback) {
        callback.onPrepared(this, false);
    }

    private static void logTrackSelectorResult(int i10, TrackSelectorResult trackSelectorResult) {
        Log.d(TAG, "Track selections changed, period index: " + i10 + ", tracks [");
        h1 groups = trackSelectorResult.tracks.getGroups();
        for (int i11 = 0; i11 < groups.size(); i11++) {
            Tracks.Group group = (Tracks.Group) groups.get(i11);
            Log.d(TAG, "  group [");
            for (int i12 = 0; i12 < group.length; i12++) {
                String str = group.isTrackSelected(i12) ? "[X]" : "[ ]";
                String formatSupportString = Util.getFormatSupportString(group.getTrackSupport(i12));
                StringBuilder sbQ = a2.q("    ", str, " Track:", i12, ", ");
                sbQ.append(Format.toLogString(group.getTrackFormat(i12)));
                sbQ.append(", supported=");
                sbQ.append(formatSupportString);
                Log.d(TAG, sbQ.toString());
            }
            Log.d(TAG, "  ]");
        }
        Log.d(TAG, "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaPreparationFailed(IOException iOException) {
        Handler handler = this.callbackHandler;
        handler.getClass();
        handler.post(new g(this, iOException, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaPrepared() throws ExoPlaybackException {
        this.mediaPreparer.getClass();
        this.mediaPreparer.mediaPeriods.getClass();
        this.mediaPreparer.timeline.getClass();
        int i10 = this.mode;
        boolean z = false;
        if (i10 == 2) {
            int length = this.mediaPreparer.mediaPeriods.length;
            int size = this.rendererCapabilities.size();
            this.trackSelectionsByPeriodAndRenderer = (List[][]) Array.newInstance((Class<?>) List.class, length, size);
            this.immutableTrackSelectionsByPeriodAndRenderer = (List[][]) Array.newInstance((Class<?>) List.class, length, size);
            for (int i11 = 0; i11 < length; i11++) {
                for (int i12 = 0; i12 < size; i12++) {
                    this.trackSelectionsByPeriodAndRenderer[i11][i12] = new ArrayList();
                    this.immutableTrackSelectionsByPeriodAndRenderer[i11][i12] = Collections.unmodifiableList(this.trackSelectionsByPeriodAndRenderer[i11][i12]);
                }
            }
            this.trackGroupArrays = new TrackGroupArray[length];
            this.mappedTrackInfos = new MappingTrackSelector.MappedTrackInfo[length];
            for (int i13 = 0; i13 < length; i13++) {
                this.trackGroupArrays[i13] = this.mediaPreparer.mediaPeriods[i13].getTrackGroups();
                this.trackSelector.onSelectionActivated(runTrackSelection(i13).info);
                MappingTrackSelector.MappedTrackInfo[] mappedTrackInfoArr = this.mappedTrackInfos;
                MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
                currentMappedTrackInfo.getClass();
                mappedTrackInfoArr[i13] = currentMappedTrackInfo;
            }
            setPreparedWithNonProgressiveSourceAndTracksSelected();
            z = true;
        } else {
            ac.b.s(i10 == 1);
            this.mediaPreparer.seekMap.getClass();
            setPreparedWithProgressiveSource();
        }
        Handler handler = this.callbackHandler;
        handler.getClass();
        handler.post(new j(1, this, z));
    }

    private void populateDownloadRequestBuilderWithByteRange(DownloadRequest.Builder builder, long j10, long j11) {
        long jMsToUs;
        assertPreparedWithProgressiveSource();
        Timeline timeline = this.mediaPreparer.timeline;
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        long jLongValue = ((Long) timeline.getPeriodPositionUs(window, period, 0, Util.msToUs(j10)).second).longValue();
        if (j11 != C.TIME_UNSET) {
            jMsToUs = Util.msToUs(j11) + jLongValue;
            long j12 = period.durationUs;
            if (j12 != C.TIME_UNSET) {
                jMsToUs = Math.min(jMsToUs, j12 - 1);
            }
        } else {
            jMsToUs = -9223372036854775807L;
        }
        SeekMap seekMap = this.mediaPreparer.seekMap;
        if (!seekMap.isSeekable()) {
            Log.w(TAG, "Cannot set download byte range for progressive stream that is unseekable");
            return;
        }
        long j13 = seekMap.getSeekPoints(jLongValue).first.position;
        long j14 = -1;
        if (jMsToUs != C.TIME_UNSET) {
            long j15 = seekMap.getSeekPoints(jMsToUs).second.position;
            if (jLongValue == jMsToUs || j13 != j15) {
                j14 = j15 - j13;
            }
        }
        builder.setByteRange(j13, j14);
    }

    private void populateDownloadRequestBuilderWithDownloadRange(DownloadRequest.Builder builder, long j10, long j11) {
        int i10 = this.mode;
        if (i10 == 1) {
            populateDownloadRequestBuilderWithByteRange(builder, j10, j11);
        } else {
            if (i10 != 2) {
                return;
            }
            populateDownloadRequestBuilderWithTimeRange(builder, j10, j11);
        }
    }

    private void populateDownloadRequestBuilderWithTimeRange(DownloadRequest.Builder builder, long j10, long j11) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        Timeline.Window window = this.mediaPreparer.timeline.getWindow(0, new Timeline.Window());
        long defaultPositionUs = j10 == C.TIME_UNSET ? window.getDefaultPositionUs() : Util.msToUs(j10);
        long durationUs = window.getDurationUs();
        long jMsToUs = j11 == C.TIME_UNSET ? durationUs : Util.msToUs(j11);
        if (durationUs != C.TIME_UNSET) {
            defaultPositionUs = Math.min(defaultPositionUs, durationUs);
            jMsToUs = Math.min(jMsToUs, durationUs - defaultPositionUs);
        }
        builder.setTimeRange(defaultPositionUs, jMsToUs);
    }

    @RequiresNonNull({"trackGroupArrays", "trackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline"})
    private TrackSelectorResult runTrackSelection(int i10) throws ExoPlaybackException {
        TrackSelectorResult trackSelectorResultSelectTracks = this.trackSelector.selectTracks(this.rendererCapabilities.getRendererCapabilities(), this.trackGroupArrays[i10], new MediaSource.MediaPeriodId(this.mediaPreparer.timeline.getUidOfPeriod(i10)), this.mediaPreparer.timeline);
        for (int i11 = 0; i11 < trackSelectorResultSelectTracks.length; i11++) {
            ExoTrackSelection exoTrackSelection = trackSelectorResultSelectTracks.selections[i11];
            if (exoTrackSelection != null) {
                List<ExoTrackSelection> list = this.trackSelectionsByPeriodAndRenderer[i10][i11];
                int i12 = 0;
                while (true) {
                    if (i12 >= list.size()) {
                        list.add(exoTrackSelection);
                        break;
                    }
                    ExoTrackSelection exoTrackSelection2 = list.get(i12);
                    if (exoTrackSelection2.getTrackGroup().equals(exoTrackSelection.getTrackGroup())) {
                        this.scratchSet.clear();
                        for (int i13 = 0; i13 < exoTrackSelection2.length(); i13++) {
                            this.scratchSet.put(exoTrackSelection2.getIndexInTrackGroup(i13), 0);
                        }
                        for (int i14 = 0; i14 < exoTrackSelection.length(); i14++) {
                            this.scratchSet.put(exoTrackSelection.getIndexInTrackGroup(i14), 0);
                        }
                        int[] iArr = new int[this.scratchSet.size()];
                        for (int i15 = 0; i15 < this.scratchSet.size(); i15++) {
                            iArr[i15] = this.scratchSet.keyAt(i15);
                        }
                        list.set(i12, new DownloadTrackSelection(exoTrackSelection2.getTrackGroup(), iArr));
                    } else {
                        i12++;
                    }
                }
            }
        }
        if (this.debugLoggingEnabled) {
            logTrackSelectorResult(i10, trackSelectorResultSelectTracks);
        }
        return trackSelectorResultSelectTracks;
    }

    @RequiresNonNull({"trackGroupArrays", "mappedTrackInfos", "trackSelectionsByPeriodAndRenderer", "immutableTrackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    private void setPreparedWithNonProgressiveSourceAndTracksSelected() {
        this.isPreparedWithMedia = true;
        this.areTracksSelected = true;
    }

    @RequiresNonNull({"mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.seekMap", "mediaPreparer.mediaPeriods"})
    private void setPreparedWithProgressiveSource() {
        this.isPreparedWithMedia = true;
    }

    public void addAudioLanguagesToSelection(String... strArr) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            DefaultTrackSelector.Parameters.Builder builderBuildUpon = DEFAULT_TRACK_SELECTOR_PARAMETERS.buildUpon();
            builderBuildUpon.setForceHighestSupportedBitrate(true);
            for (RendererCapabilities rendererCapabilities : this.rendererCapabilities.getRendererCapabilities()) {
                int trackType = rendererCapabilities.getTrackType();
                builderBuildUpon.setTrackTypeDisabled(trackType, trackType != 1);
            }
            int periodCount = getPeriodCount();
            for (String str : strArr) {
                TrackSelectionParameters trackSelectionParametersBuild = builderBuildUpon.setPreferredAudioLanguage(str).build();
                for (int i10 = 0; i10 < periodCount; i10++) {
                    addTrackSelectionInternal(i10, trackSelectionParametersBuild);
                }
            }
        } catch (ExoPlaybackException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public void addTextLanguagesToSelection(boolean z, String... strArr) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            DefaultTrackSelector.Parameters.Builder builderBuildUpon = DEFAULT_TRACK_SELECTOR_PARAMETERS.buildUpon();
            builderBuildUpon.setSelectUndeterminedTextLanguage(z);
            builderBuildUpon.setForceHighestSupportedBitrate(true);
            for (RendererCapabilities rendererCapabilities : this.rendererCapabilities.getRendererCapabilities()) {
                int trackType = rendererCapabilities.getTrackType();
                builderBuildUpon.setTrackTypeDisabled(trackType, trackType != 3);
            }
            int periodCount = getPeriodCount();
            for (String str : strArr) {
                TrackSelectionParameters trackSelectionParametersBuild = builderBuildUpon.setPreferredTextLanguage(str).build();
                for (int i10 = 0; i10 < periodCount; i10++) {
                    addTrackSelectionInternal(i10, trackSelectionParametersBuild);
                }
            }
        } catch (ExoPlaybackException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public void addTrackSelection(int i10, TrackSelectionParameters trackSelectionParameters) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            addTrackSelectionInternal(i10, trackSelectionParameters);
        } catch (ExoPlaybackException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public void addTrackSelectionForSingleRenderer(int i10, int i11, DefaultTrackSelector.Parameters parameters, List<DefaultTrackSelector.SelectionOverride> list) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            DefaultTrackSelector.Parameters.Builder builderBuildUpon = parameters.buildUpon();
            int i12 = 0;
            while (i12 < this.mappedTrackInfos[i10].getRendererCount()) {
                builderBuildUpon.setRendererDisabled(i12, i12 != i11);
                i12++;
            }
            if (list.isEmpty()) {
                addTrackSelectionInternal(i10, builderBuildUpon.build());
                return;
            }
            TrackGroupArray trackGroups = this.mappedTrackInfos[i10].getTrackGroups(i11);
            for (int i13 = 0; i13 < list.size(); i13++) {
                builderBuildUpon.setSelectionOverride(i11, trackGroups, list.get(i13));
                addTrackSelectionInternal(i10, builderBuildUpon.build());
            }
        } catch (ExoPlaybackException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public void clearTrackSelections(int i10) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        for (int i11 = 0; i11 < this.rendererCapabilities.size(); i11++) {
            this.trackSelectionsByPeriodAndRenderer[i10][i11].clear();
        }
    }

    public DownloadRequest getDownloadRequest(byte[] bArr) {
        return getDownloadRequest(this.localConfiguration.uri.toString(), bArr);
    }

    public Object getManifest() {
        if (this.mode == 0) {
            return null;
        }
        assertPreparedWithMedia();
        if (this.mediaPreparer.timeline.getWindowCount() > 0) {
            return this.mediaPreparer.timeline.getWindow(0, this.window).manifest;
        }
        return null;
    }

    public MappingTrackSelector.MappedTrackInfo getMappedTrackInfo(int i10) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        return this.mappedTrackInfos[i10];
    }

    public int getPeriodCount() {
        if (this.mode == 0) {
            return 0;
        }
        assertPreparedWithMedia();
        return this.mediaPreparer.mediaPeriods.length;
    }

    public TrackGroupArray getTrackGroups(int i10) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        return this.trackGroupArrays[i10];
    }

    public List<ExoTrackSelection> getTrackSelections(int i10, int i11) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        return this.immutableTrackSelectionsByPeriodAndRenderer[i10][i11];
    }

    public Tracks getTracks(int i10) {
        assertPreparedWithNonProgressiveSourceAndTracksSelected();
        return TrackSelectionUtil.buildTracks(this.mappedTrackInfos[i10], this.immutableTrackSelectionsByPeriodAndRenderer[i10]);
    }

    public void prepare(Callback callback) {
        ac.b.s(this.callback == null);
        this.callback = callback;
        if (this.mode == 0) {
            this.callbackHandler.post(new g(this, callback, 2));
            return;
        }
        MediaSource mediaSource = this.mediaSource;
        mediaSource.getClass();
        this.mediaPreparer = new MediaPreparer(mediaSource, this);
    }

    public void release() {
        MediaPreparer mediaPreparer = this.mediaPreparer;
        if (mediaPreparer != null) {
            mediaPreparer.release();
        }
        this.trackSelector.release();
        this.rendererCapabilities.release();
    }

    public void replaceTrackSelections(int i10, TrackSelectionParameters trackSelectionParameters) {
        try {
            assertPreparedWithNonProgressiveSourceAndTracksSelected();
            clearTrackSelections(i10);
            addTrackSelectionInternal(i10, trackSelectionParameters);
        } catch (ExoPlaybackException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public DownloadHelper(MediaItem mediaItem, MediaSource mediaSource, TrackSelectionParameters trackSelectionParameters, RendererCapabilitiesList rendererCapabilitiesList, boolean z) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        localConfiguration.getClass();
        this.localConfiguration = localConfiguration;
        this.mediaSource = mediaSource;
        this.mode = mediaSource == null ? 0 : mediaSource instanceof ProgressiveMediaSource ? 1 : 2;
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(trackSelectionParameters, new DownloadTrackSelection.Factory());
        this.trackSelector = defaultTrackSelector;
        this.rendererCapabilities = rendererCapabilitiesList;
        this.debugLoggingEnabled = z;
        this.scratchSet = new SparseIntArray();
        defaultTrackSelector.init(new c(), new FakeBandwidthMeter());
        this.callbackHandler = Util.createHandlerForCurrentOrMainLooper();
        this.window = new Timeline.Window();
    }

    public static MediaSource createMediaSource(DownloadRequest downloadRequest, DataSource.Factory factory, DrmSessionManager drmSessionManager) {
        return createMediaSourceInternal(downloadRequest.toMediaItem(), factory, drmSessionManager, null);
    }

    public DownloadRequest getDownloadRequest(byte[] bArr, long j10, long j11) {
        return getDownloadRequest(this.localConfiguration.uri.toString(), bArr, j10, j11);
    }

    public DownloadRequest getDownloadRequest(String str, byte[] bArr) {
        return getDownloadRequestBuilder(str, bArr).build();
    }

    public DownloadRequest getDownloadRequest(String str, byte[] bArr, long j10, long j11) {
        DownloadRequest.Builder downloadRequestBuilder = getDownloadRequestBuilder(str, bArr);
        assertPreparedWithMedia();
        populateDownloadRequestBuilderWithDownloadRange(downloadRequestBuilder, j10, j11);
        return downloadRequestBuilder.build();
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, DataSource.Factory factory) {
        return new Factory().setDataSourceFactory(factory).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, DataSource.Factory factory, boolean z) {
        return new Factory().setDataSourceFactory(factory).setDebugLoggingEnabled(z).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, RenderersFactory renderersFactory, DataSource.Factory factory) {
        return new Factory().setDataSourceFactory(factory).setRenderersFactory(renderersFactory).create(mediaItem);
    }

    public static final class Factory {
        private DataSource.Factory dataSourceFactory;
        private boolean debugLoggingEnabled;
        private DrmSessionManager drmSessionManager;
        private RenderersFactory renderersFactory;
        private TrackSelectionParameters trackSelectionParameters = DownloadHelper.DEFAULT_TRACK_SELECTOR_PARAMETERS;
        private c0<ReleasableExecutor> loadExecutorSupplier = null;

        public DownloadHelper create(MediaItem mediaItem) {
            MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
            localConfiguration.getClass();
            boolean zIsProgressive = DownloadHelper.isProgressive(localConfiguration);
            ac.b.j(zIsProgressive || this.dataSourceFactory != null);
            MediaSource mediaSourceCreateMediaSourceInternal = (zIsProgressive && this.dataSourceFactory == null) ? null : DownloadHelper.createMediaSourceInternal(mediaItem, (DataSource.Factory) Util.castNonNull(this.dataSourceFactory), this.drmSessionManager, this.loadExecutorSupplier);
            TrackSelectionParameters trackSelectionParameters = this.trackSelectionParameters;
            RenderersFactory renderersFactory = this.renderersFactory;
            return new DownloadHelper(mediaItem, mediaSourceCreateMediaSourceInternal, trackSelectionParameters, renderersFactory != null ? new DefaultRendererCapabilitiesList.Factory(renderersFactory).createRendererCapabilitiesList() : new UnreleaseableRendererCapabilitiesList(new RendererCapabilities[0]), this.debugLoggingEnabled);
        }

        public Factory setDataSourceFactory(DataSource.Factory factory) {
            this.dataSourceFactory = factory;
            return this;
        }

        public Factory setDebugLoggingEnabled(boolean z) {
            this.debugLoggingEnabled = z;
            return this;
        }

        public Factory setDrmSessionManager(DrmSessionManager drmSessionManager) {
            this.drmSessionManager = drmSessionManager;
            return this;
        }

        public Factory setLoadExecutor(c0<ReleasableExecutor> c0Var) {
            this.loadExecutorSupplier = c0Var;
            return this;
        }

        public Factory setRenderersFactory(RenderersFactory renderersFactory) {
            this.renderersFactory = renderersFactory;
            return this;
        }

        public Factory setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
            this.trackSelectionParameters = trackSelectionParameters;
            return this;
        }

        public DownloadHelper create(MediaSource mediaSource) {
            RendererCapabilitiesList unreleaseableRendererCapabilitiesList;
            MediaItem mediaItem = mediaSource.getMediaItem();
            TrackSelectionParameters trackSelectionParameters = this.trackSelectionParameters;
            RenderersFactory renderersFactory = this.renderersFactory;
            if (renderersFactory != null) {
                unreleaseableRendererCapabilitiesList = new DefaultRendererCapabilitiesList.Factory(renderersFactory).createRendererCapabilitiesList();
            } else {
                unreleaseableRendererCapabilitiesList = new UnreleaseableRendererCapabilitiesList(new RendererCapabilities[0]);
            }
            return new DownloadHelper(mediaItem, mediaSource, trackSelectionParameters, unreleaseableRendererCapabilitiesList, this.debugLoggingEnabled);
        }
    }

    @Deprecated
    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, RenderersFactory renderersFactory, DataSource.Factory factory, boolean z) {
        return new Factory().setDataSourceFactory(factory).setRenderersFactory(renderersFactory).setDebugLoggingEnabled(z).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory factory) {
        return new Factory().setDataSourceFactory(factory).setTrackSelectionParameters(trackSelectionParameters).setRenderersFactory(renderersFactory).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory factory, boolean z) {
        return new Factory().setDataSourceFactory(factory).setTrackSelectionParameters(trackSelectionParameters).setRenderersFactory(renderersFactory).setDebugLoggingEnabled(z).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory factory, DrmSessionManager drmSessionManager) {
        return new Factory().setDataSourceFactory(factory).setTrackSelectionParameters(trackSelectionParameters).setRenderersFactory(renderersFactory).setDrmSessionManager(drmSessionManager).create(mediaItem);
    }

    @Deprecated
    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory factory, DrmSessionManager drmSessionManager, boolean z) {
        return new Factory().setDataSourceFactory(factory).setTrackSelectionParameters(trackSelectionParameters).setRenderersFactory(renderersFactory).setDrmSessionManager(drmSessionManager).setDebugLoggingEnabled(z).create(mediaItem);
    }
}
