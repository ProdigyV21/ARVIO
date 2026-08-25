package androidx.media3.exoplayer.source;

import android.net.Uri;
import android.os.Handler;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.source.IcyDataSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ForwardingSeekMap;
import androidx.media3.extractor.ForwardingTrackOutput;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.google.common.collect.k1;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: loaded from: classes3.dex */
final class ProgressiveMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback, SampleQueue.UpstreamFormatChangedListener {
    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    private static final String TAG = "ProgressiveMediaPeriod";
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final long continueLoadingCheckIntervalBytes;
    private ControlledTrackOutput[] controlledTrackOutputs;
    private final String customCacheKey;
    private final DataSource dataSource;
    private int dataType;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private long durationUs;
    private int enabledTrackCount;
    private int extractedSamplesCountAtStartOfLoad;
    private final Handler handler;
    private boolean haveAudioVideoTracks;
    private IcyHeaders icyHeaders;
    private boolean isLengthKnown;
    private boolean isLive;
    private boolean isSingleSample;
    private long lastSeekPositionUs;
    private final Listener listener;
    private final ConditionVariable loadCondition;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final boolean loadOnlySelectedTracks;
    private final Loader loader;
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private boolean notifyDiscontinuity;
    private final Runnable onContinueLoadingRequestedRunnable;
    private boolean pendingDeferredRetry;
    private boolean pendingInitialDiscontinuity;
    private long pendingResetPositionUs;
    private boolean prepared;
    private final ProgressiveMediaExtractor progressiveMediaExtractor;
    private boolean released;
    private TrackId[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private final long singleSampleDurationUs;
    private final Format singleTrackFormat;
    private final int singleTrackId;
    private TrackState trackState;
    private final Uri uri;
    private static final Map<String, String> ICY_METADATA_HEADERS = createIcyMetadataHeaders();
    private static final Format ICY_FORMAT = new Format.Builder().setId("icy").setSampleMimeType(MimeTypes.APPLICATION_ICY).build();

    public static class ControlledTrackOutput extends ForwardingTrackOutput {
        private final DiscardingTrackOutput discardingTrackOutput;
        private final AtomicReference<OutputMode> outputMode;
        private final SampleQueue sampleQueue;

        public enum OutputMode {
            PASS_THROUGH,
            DISCARD_AFTER_NEXT_SAMPLE_METADATA,
            DISCARDING
        }

        public ControlledTrackOutput(SampleQueue sampleQueue) {
            super(sampleQueue);
            this.sampleQueue = sampleQueue;
            this.discardingTrackOutput = new DiscardingTrackOutput();
            this.outputMode = new AtomicReference<>(OutputMode.PASS_THROUGH);
        }

        private TrackOutput getCurrentOutput() {
            return this.outputMode.get() == OutputMode.DISCARDING ? this.discardingTrackOutput : this.sampleQueue;
        }

        public boolean isSelected() {
            return this.outputMode.get() == OutputMode.PASS_THROUGH;
        }

        @Override // androidx.media3.extractor.ForwardingTrackOutput, androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader dataReader, int i10, boolean z) throws IOException {
            return getCurrentOutput().sampleData(dataReader, i10, z);
        }

        @Override // androidx.media3.extractor.ForwardingTrackOutput, androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long j10, int i10, int i11, int i12, TrackOutput.CryptoData cryptoData) {
            getCurrentOutput().sampleMetadata(j10, i10, i11, i12, cryptoData);
            if (this.outputMode.get() == OutputMode.DISCARD_AFTER_NEXT_SAMPLE_METADATA) {
                this.sampleQueue.reset();
                this.outputMode.set(OutputMode.DISCARDING);
            }
        }

        public void updateSelectionState(boolean z) {
            this.outputMode.set(z ? OutputMode.PASS_THROUGH : OutputMode.DISCARD_AFTER_NEXT_SAMPLE_METADATA);
            if (z) {
                return;
            }
            this.sampleQueue.discardToEnd();
        }

        @Override // androidx.media3.extractor.ForwardingTrackOutput, androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader dataReader, int i10, boolean z, int i11) throws IOException {
            return getCurrentOutput().sampleData(dataReader, i10, z, i11);
        }

        @Override // androidx.media3.extractor.ForwardingTrackOutput, androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i10) {
            getCurrentOutput().sampleData(parsableByteArray, i10);
        }

        @Override // androidx.media3.extractor.ForwardingTrackOutput, androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i10, int i11) {
            getCurrentOutput().sampleData(parsableByteArray, i10, i11);
        }
    }

    public final class ExtractingLoadable implements Loader.Loadable, IcyDataSource.Listener {
        private final StatsDataSource dataSource;
        private final ExtractorOutput extractorOutput;
        private TrackOutput icyTrackOutput;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private final ProgressiveMediaExtractor progressiveMediaExtractor;
        private long seekTimeUs;
        private boolean seenIcyMetadata;
        private final Uri uri;
        private final PositionHolder positionHolder = new PositionHolder();
        private boolean pendingExtractorSeek = true;
        private final long loadTaskId = LoadEventInfo.getNewId();
        private DataSpec dataSpec = buildDataSpec(0, null);

        public ExtractingLoadable(Uri uri, DataSource dataSource, ProgressiveMediaExtractor progressiveMediaExtractor, ExtractorOutput extractorOutput, ConditionVariable conditionVariable) {
            this.uri = uri;
            this.dataSource = new StatsDataSource(dataSource);
            this.progressiveMediaExtractor = progressiveMediaExtractor;
            this.extractorOutput = extractorOutput;
            this.loadCondition = conditionVariable;
        }

        private DataSpec buildDataSpec(long j10, String str) {
            Map<String, String> mapB = ProgressiveMediaPeriod.ICY_METADATA_HEADERS;
            if (str != null && !str.startsWith("W/")) {
                k1 k1Var = new k1(4);
                k1Var.e(mapB.entrySet());
                k1Var.c("If-Range", str);
                mapB = k1Var.b();
            }
            return new DataSpec.Builder().setUri(this.uri).setPosition(j10).setKey(ProgressiveMediaPeriod.this.customCacheKey).setFlags(6).setHttpRequestHeaders(mapB).build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLoadPosition(long j10, long j11) {
            this.positionHolder.position = j10;
            this.seekTimeUs = j11;
            this.pendingExtractorSeek = true;
            this.seenIcyMetadata = false;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void cancelLoad() {
            this.loadCanceled = true;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void load() throws IOException {
            int i10 = 0;
            String str = null;
            while (i10 == 0 && !this.loadCanceled) {
                try {
                    long j10 = this.positionHolder.position;
                    DataSpec dataSpecBuildDataSpec = buildDataSpec(j10, str);
                    this.dataSpec = dataSpecBuildDataSpec;
                    long jOpen = this.dataSource.open(dataSpecBuildDataSpec);
                    if (this.loadCanceled) {
                        if (i10 != 1 && this.progressiveMediaExtractor.getCurrentInputPosition() != -1) {
                            this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                        }
                        DataSourceUtil.closeQuietly(this.dataSource);
                        return;
                    }
                    List<String> list = this.dataSource.getResponseHeaders().get("ETag");
                    str = (list == null || list.isEmpty()) ? null : list.get(0);
                    if (jOpen != -1) {
                        jOpen += j10;
                        ProgressiveMediaPeriod.this.onLengthKnown();
                    }
                    long j11 = jOpen;
                    ProgressiveMediaPeriod.this.icyHeaders = IcyHeaders.parse(this.dataSource.getResponseHeaders());
                    DataReader icyDataSource = this.dataSource;
                    if (ProgressiveMediaPeriod.this.icyHeaders != null && ProgressiveMediaPeriod.this.icyHeaders.metadataInterval != -1) {
                        icyDataSource = new IcyDataSource(this.dataSource, ProgressiveMediaPeriod.this.icyHeaders.metadataInterval, this);
                        TrackOutput trackOutputIcyTrack = ProgressiveMediaPeriod.this.icyTrack();
                        this.icyTrackOutput = trackOutputIcyTrack;
                        trackOutputIcyTrack.format(ProgressiveMediaPeriod.ICY_FORMAT);
                    }
                    this.progressiveMediaExtractor.init(icyDataSource, this.uri, this.dataSource.getResponseHeaders(), j10, j11, this.extractorOutput);
                    if (ProgressiveMediaPeriod.this.icyHeaders != null) {
                        this.progressiveMediaExtractor.disableSeekingOnMp3Streams();
                    }
                    if (this.pendingExtractorSeek) {
                        this.progressiveMediaExtractor.seek(j10, this.seekTimeUs);
                        this.pendingExtractorSeek = false;
                    }
                    while (i10 == 0 && !this.loadCanceled) {
                        try {
                            this.loadCondition.block();
                            i10 = this.progressiveMediaExtractor.read(this.positionHolder);
                            long currentInputPosition = this.progressiveMediaExtractor.getCurrentInputPosition();
                            if (currentInputPosition > ProgressiveMediaPeriod.this.continueLoadingCheckIntervalBytes + j10) {
                                this.loadCondition.close();
                                ProgressiveMediaPeriod.this.handler.post(ProgressiveMediaPeriod.this.onContinueLoadingRequestedRunnable);
                                j10 = currentInputPosition;
                            }
                        } catch (InterruptedException unused) {
                            throw new InterruptedIOException();
                        }
                    }
                    if (i10 == 1) {
                        i10 = 0;
                    } else if (this.progressiveMediaExtractor.getCurrentInputPosition() != -1) {
                        this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                    }
                    DataSourceUtil.closeQuietly(this.dataSource);
                } catch (Throwable th) {
                    if (i10 != 1 && this.progressiveMediaExtractor.getCurrentInputPosition() != -1) {
                        this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                    }
                    DataSourceUtil.closeQuietly(this.dataSource);
                    throw th;
                }
            }
        }

        @Override // androidx.media3.exoplayer.source.IcyDataSource.Listener
        public void onIcyMetadata(ParsableByteArray parsableByteArray) {
            long jMax = !this.seenIcyMetadata ? this.seekTimeUs : Math.max(ProgressiveMediaPeriod.this.getLargestQueuedTimestampUs(true), this.seekTimeUs);
            int iBytesLeft = parsableByteArray.bytesLeft();
            TrackOutput trackOutput = this.icyTrackOutput;
            trackOutput.getClass();
            trackOutput.sampleData(parsableByteArray, iBytesLeft);
            trackOutput.sampleMetadata(jMax, 1, iBytesLeft, 0, null);
            this.seenIcyMetadata = true;
        }
    }

    public interface Listener {
        void onSourceInfoRefreshed(long j10, SeekMap seekMap, boolean z);
    }

    public final class SampleStreamImpl implements SampleStream {
        private final int track;

        public SampleStreamImpl(int i10) {
            this.track = i10;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return ProgressiveMediaPeriod.this.isReady(this.track);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            ProgressiveMediaPeriod.this.maybeThrowError(this.track);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i10) {
            return ProgressiveMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, i10);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j10) {
            return ProgressiveMediaPeriod.this.skipData(this.track, j10);
        }
    }

    public static final class TrackId {
        public final int id;
        public final boolean isIcyTrack;

        public TrackId(int i10, boolean z) {
            this.id = i10;
            this.isIcyTrack = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && TrackId.class == obj.getClass()) {
                TrackId trackId = (TrackId) obj;
                if (this.id == trackId.id && this.isIcyTrack == trackId.isIcyTrack) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return (this.id * 31) + (this.isIcyTrack ? 1 : 0);
        }
    }

    public static final class TrackState {
        public final boolean[] trackEnabledStates;
        public final boolean[] trackIsAudioVideoFlags;
        public final boolean[] trackNotifiedDownstreamFormats;
        public final TrackGroupArray tracks;

        public TrackState(TrackGroupArray trackGroupArray, boolean[] zArr) {
            this.tracks = trackGroupArray;
            this.trackIsAudioVideoFlags = zArr;
            int i10 = trackGroupArray.length;
            this.trackEnabledStates = new boolean[i10];
            this.trackNotifiedDownstreamFormats = new boolean[i10];
        }
    }

    public ProgressiveMediaPeriod(Uri uri, DataSource dataSource, ProgressiveMediaExtractor progressiveMediaExtractor, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, Listener listener, Allocator allocator, String str, int i10, boolean z, int i11, Format format, long j10, ReleasableExecutor releasableExecutor) {
        this.uri = uri;
        this.dataSource = dataSource;
        this.drmSessionManager = drmSessionManager;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.listener = listener;
        this.allocator = allocator;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = i10;
        this.loadOnlySelectedTracks = z;
        this.singleTrackId = i11;
        this.singleTrackFormat = format;
        this.loader = releasableExecutor != null ? new Loader(releasableExecutor) : new Loader(TAG);
        this.progressiveMediaExtractor = progressiveMediaExtractor;
        this.singleSampleDurationUs = j10;
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new l(this, 1);
        this.onContinueLoadingRequestedRunnable = new l(this, 2);
        this.handler = Util.createHandlerForCurrentLooper();
        this.sampleQueueTrackIds = new TrackId[0];
        this.sampleQueues = new SampleQueue[0];
        this.controlledTrackOutputs = new ControlledTrackOutput[0];
        this.pendingResetPositionUs = C.TIME_UNSET;
        this.dataType = 1;
    }

    @EnsuresNonNull({"trackState", "seekMap"})
    private void assertPrepared() {
        ac.b.s(this.prepared);
        this.trackState.getClass();
        this.seekMap.getClass();
    }

    private boolean configureRetry(ExtractingLoadable extractingLoadable, int i10) {
        SeekMap seekMap;
        if (this.isLengthKnown || !((seekMap = this.seekMap) == null || seekMap.getDurationUs() == C.TIME_UNSET)) {
            this.extractedSamplesCountAtStartOfLoad = i10;
            return true;
        }
        if (this.prepared && !suppressRead()) {
            this.pendingDeferredRetry = true;
            return false;
        }
        this.notifyDiscontinuity = this.prepared;
        this.lastSeekPositionUs = 0L;
        this.extractedSamplesCountAtStartOfLoad = 0;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset();
        }
        extractingLoadable.setLoadPosition(0L, 0L);
        return true;
    }

    private static Map<String, String> createIcyMetadataHeaders() {
        HashMap map = new HashMap();
        map.put(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_NAME, "1");
        return Collections.unmodifiableMap(map);
    }

    private int getExtractedSamplesCount() {
        int writeIndex = 0;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            writeIndex += sampleQueue.getWriteIndex();
        }
        return writeIndex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long getLargestQueuedTimestampUs(boolean r6) {
        /*
            r5 = this;
            r0 = -9223372036854775808
            r2 = 0
        L3:
            androidx.media3.exoplayer.source.SampleQueue[] r3 = r5.sampleQueues
            int r3 = r3.length
            if (r2 >= r3) goto L24
            if (r6 != 0) goto L15
            androidx.media3.exoplayer.source.ProgressiveMediaPeriod$TrackState r3 = r5.trackState
            r3.getClass()
            boolean[] r3 = r3.trackEnabledStates
            boolean r3 = r3[r2]
            if (r3 == 0) goto L21
        L15:
            androidx.media3.exoplayer.source.SampleQueue[] r3 = r5.sampleQueues
            r3 = r3[r2]
            long r3 = r3.getLargestQueuedTimestampUs()
            long r0 = java.lang.Math.max(r0, r3)
        L21:
            int r2 = r2 + 1
            goto L3
        L24:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.ProgressiveMediaPeriod.getLargestQueuedTimestampUs(boolean):long");
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$new$0() {
        if (this.released) {
            return;
        }
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onLengthKnown$2() {
        this.isLengthKnown = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (this.released || this.prepared || !this.sampleQueuesBuilt || this.seekMap == null) {
            return;
        }
        for (SampleQueue sampleQueue : this.sampleQueues) {
            if (sampleQueue.getUpstreamFormat() == null) {
                return;
            }
        }
        this.loadCondition.close();
        int length = this.sampleQueues.length;
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        boolean[] zArr = new boolean[length];
        for (int i10 = 0; i10 < length; i10++) {
            Format upstreamFormat = this.sampleQueues[i10].getUpstreamFormat();
            upstreamFormat.getClass();
            String str = upstreamFormat.sampleMimeType;
            boolean zIsAudio = MimeTypes.isAudio(str);
            boolean z = zIsAudio || MimeTypes.isVideo(str);
            zArr[i10] = z;
            this.haveAudioVideoTracks = z | this.haveAudioVideoTracks;
            this.isSingleSample = this.singleSampleDurationUs != C.TIME_UNSET && length == 1 && MimeTypes.isImage(str);
            IcyHeaders icyHeaders = this.icyHeaders;
            if (icyHeaders != null) {
                if (zIsAudio || this.sampleQueueTrackIds[i10].isIcyTrack) {
                    Metadata metadata = upstreamFormat.metadata;
                    upstreamFormat = upstreamFormat.buildUpon().setMetadata(metadata == null ? new Metadata(icyHeaders) : metadata.copyWithAppendedEntries(icyHeaders)).build();
                }
                if (zIsAudio && upstreamFormat.averageBitrate == -1 && upstreamFormat.peakBitrate == -1 && icyHeaders.bitrate != -1) {
                    upstreamFormat = upstreamFormat.buildUpon().setAverageBitrate(icyHeaders.bitrate).build();
                }
            }
            Format formatCopyWithCryptoType = upstreamFormat.copyWithCryptoType(this.drmSessionManager.getCryptoType(upstreamFormat));
            trackGroupArr[i10] = new TrackGroup(Integer.toString(i10), formatCopyWithCryptoType);
            this.pendingInitialDiscontinuity = formatCopyWithCryptoType.hasPrerollSamples | this.pendingInitialDiscontinuity;
        }
        this.trackState = new TrackState(new TrackGroupArray(trackGroupArr), zArr);
        if (this.isSingleSample && this.durationUs == C.TIME_UNSET) {
            this.durationUs = this.singleSampleDurationUs;
            this.seekMap = new ForwardingSeekMap(this.seekMap) { // from class: androidx.media3.exoplayer.source.ProgressiveMediaPeriod.1
                @Override // androidx.media3.extractor.ForwardingSeekMap, androidx.media3.extractor.SeekMap
                public long getDurationUs() {
                    return ProgressiveMediaPeriod.this.durationUs;
                }
            };
        }
        this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap, this.isLive);
        this.prepared = true;
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onPrepared(this);
    }

    private void maybeNotifyDownstreamFormat(int i10) {
        assertPrepared();
        TrackState trackState = this.trackState;
        boolean[] zArr = trackState.trackNotifiedDownstreamFormats;
        if (zArr[i10]) {
            return;
        }
        Format format = trackState.tracks.get(i10).getFormat(0);
        this.mediaSourceEventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(format.sampleMimeType), format, 0, null, this.lastSeekPositionUs);
        zArr[i10] = true;
    }

    private void maybeStartDeferredRetry(int i10) {
        assertPrepared();
        if (this.pendingDeferredRetry) {
            if (!this.haveAudioVideoTracks || this.trackState.trackIsAudioVideoFlags[i10]) {
                if (this.sampleQueues[i10].isReady(false)) {
                    return;
                }
                this.pendingResetPositionUs = 0L;
                this.pendingDeferredRetry = false;
                this.notifyDiscontinuity = true;
                this.lastSeekPositionUs = 0L;
                this.extractedSamplesCountAtStartOfLoad = 0;
                for (SampleQueue sampleQueue : this.sampleQueues) {
                    sampleQueue.reset();
                }
                MediaPeriod.Callback callback = this.callback;
                callback.getClass();
                callback.onContinueLoadingRequested(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLengthKnown() {
        this.handler.post(new l(this, 0));
    }

    private TrackOutput prepareTrackOutput(TrackId trackId) {
        int length = this.sampleQueues.length;
        for (int i10 = 0; i10 < length; i10++) {
            if (trackId.equals(this.sampleQueueTrackIds[i10])) {
                return this.sampleQueues[i10];
            }
        }
        if (this.sampleQueuesBuilt) {
            Log.w(TAG, "Extractor added new track (id=" + trackId.id + ") after finishing tracks.");
            return new DiscardingTrackOutput();
        }
        SampleQueue sampleQueueCreateWithDrm = SampleQueue.createWithDrm(this.allocator, this.drmSessionManager, this.drmEventDispatcher);
        ControlledTrackOutput controlledTrackOutput = new ControlledTrackOutput(sampleQueueCreateWithDrm);
        sampleQueueCreateWithDrm.setUpstreamFormatChangeListener(this);
        int i11 = length + 1;
        TrackId[] trackIdArr = (TrackId[]) Arrays.copyOf(this.sampleQueueTrackIds, i11);
        trackIdArr[length] = trackId;
        this.sampleQueueTrackIds = (TrackId[]) Util.castNonNullTypeArray(trackIdArr);
        SampleQueue[] sampleQueueArr = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i11);
        sampleQueueArr[length] = sampleQueueCreateWithDrm;
        this.sampleQueues = (SampleQueue[]) Util.castNonNullTypeArray(sampleQueueArr);
        ControlledTrackOutput[] controlledTrackOutputArr = (ControlledTrackOutput[]) Arrays.copyOf(this.controlledTrackOutputs, i11);
        controlledTrackOutputArr[length] = controlledTrackOutput;
        this.controlledTrackOutputs = (ControlledTrackOutput[]) Util.castNonNullTypeArray(controlledTrackOutputArr);
        return controlledTrackOutput;
    }

    private boolean seekInsideBufferUs(boolean[] zArr, long j10, boolean z) {
        int length = this.sampleQueues.length;
        for (int i10 = 0; i10 < length; i10++) {
            SampleQueue sampleQueue = this.sampleQueues[i10];
            if (this.controlledTrackOutputs[i10].isSelected() && (sampleQueue.getReadIndex() != 0 || !z)) {
                if (!(this.isSingleSample ? sampleQueue.seekTo(sampleQueue.getFirstIndex()) : sampleQueue.seekTo(j10, this.loadingFinished)) && (zArr[i10] || !this.haveAudioVideoTracks)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setSeekMap, reason: merged with bridge method [inline-methods] */
    public void lambda$seekMap$1(SeekMap seekMap) {
        this.seekMap = this.icyHeaders == null ? seekMap : new SeekMap.Unseekable(C.TIME_UNSET);
        this.durationUs = seekMap.getDurationUs();
        boolean z = !this.isLengthKnown && seekMap.getDurationUs() == C.TIME_UNSET;
        this.isLive = z;
        this.dataType = z ? 7 : 1;
        if (this.prepared) {
            this.listener.onSourceInfoRefreshed(this.durationUs, seekMap, z);
        } else {
            maybeFinishPrepare();
        }
    }

    private void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.progressiveMediaExtractor, this, this.loadCondition);
        if (this.prepared) {
            ac.b.s(isPendingReset());
            long j10 = this.durationUs;
            if (j10 != C.TIME_UNSET && this.pendingResetPositionUs > j10) {
                this.loadingFinished = true;
                this.pendingResetPositionUs = C.TIME_UNSET;
                return;
            }
            SeekMap seekMap = this.seekMap;
            seekMap.getClass();
            extractingLoadable.setLoadPosition(seekMap.getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.setStartTimeUs(this.pendingResetPositionUs);
            }
            this.pendingResetPositionUs = C.TIME_UNSET;
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.loader.startLoading(extractingLoadable, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }

    private boolean suppressRead() {
        return this.notifyDiscontinuity || isPendingReset();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.loadingFinished || this.loader.hasFatalError() || this.pendingDeferredRetry) {
            return false;
        }
        if ((this.prepared || this.singleTrackFormat != null) && this.enabledTrackCount == 0) {
            return false;
        }
        boolean zOpen = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return zOpen;
        }
        startLoading();
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j10, boolean z) {
        if (this.isSingleSample) {
            return;
        }
        assertPrepared();
        if (isPendingReset()) {
            return;
        }
        boolean[] zArr = this.trackState.trackEnabledStates;
        int length = this.sampleQueues.length;
        for (int i10 = 0; i10 < length; i10++) {
            this.sampleQueues[i10].discardTo(j10, z, zArr[i10]);
        }
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
        assertPrepared();
        if (!this.seekMap.isSeekable()) {
            return 0L;
        }
        SeekMap.SeekPoints seekPoints = this.seekMap.getSeekPoints(j10);
        return seekParameters.resolveSeekPositionUs(j10, seekPoints.first.timeUs, seekPoints.second.timeUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long largestQueuedTimestampUs;
        assertPrepared();
        if (this.loadingFinished || this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            int length = this.sampleQueues.length;
            largestQueuedTimestampUs = Long.MAX_VALUE;
            for (int i10 = 0; i10 < length; i10++) {
                TrackState trackState = this.trackState;
                if (trackState.trackIsAudioVideoFlags[i10] && trackState.trackEnabledStates[i10] && !this.sampleQueues[i10].isLastSampleQueued()) {
                    largestQueuedTimestampUs = Math.min(largestQueuedTimestampUs, this.sampleQueues[i10].getLargestQueuedTimestampUs());
                }
            }
        } else {
            largestQueuedTimestampUs = Long.MAX_VALUE;
        }
        if (largestQueuedTimestampUs == Long.MAX_VALUE) {
            largestQueuedTimestampUs = getLargestQueuedTimestampUs(false);
        }
        return largestQueuedTimestampUs == Long.MIN_VALUE ? this.lastSeekPositionUs : largestQueuedTimestampUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public final /* synthetic */ List getStreamKeys(List list) {
        return f.a(this, list);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        assertPrepared();
        return this.trackState.tracks;
    }

    public TrackOutput icyTrack() {
        return prepareTrackOutput(new TrackId(0, true));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.loader.isLoading() && this.loadCondition.isOpen();
    }

    public boolean isReady(int i10) {
        return !suppressRead() && this.sampleQueues[i10].isReady(this.loadingFinished);
    }

    public void maybeThrowError(int i10) throws IOException {
        this.sampleQueues[i10].maybeThrowError();
        maybeThrowError();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
        if (this.loadingFinished && !this.prepared) {
            throw ParserException.createForMalformedContainer("Loading finished before preparation is complete.", null);
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.release();
        }
        this.progressiveMediaExtractor.release();
    }

    @Override // androidx.media3.exoplayer.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j10) {
        this.callback = callback;
        if (this.singleTrackFormat == null) {
            this.loadCondition.open();
            startLoading();
        } else {
            track(this.singleTrackId, 3).format(this.singleTrackFormat);
            lambda$seekMap$1(new IndexSeekMap(new long[]{0}, new long[]{0}, C.TIME_UNSET));
            endTracks();
            this.pendingResetPositionUs = j10;
        }
    }

    public int readData(int i10, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i11) {
        if (suppressRead()) {
            return -3;
        }
        maybeNotifyDownstreamFormat(i10);
        int i12 = this.sampleQueues[i10].read(formatHolder, decoderInputBuffer, i11, this.loadingFinished);
        if (i12 == -3) {
            maybeStartDeferredRetry(i10);
        }
        return i12;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        if (this.pendingInitialDiscontinuity) {
            this.pendingInitialDiscontinuity = false;
            return this.lastSeekPositionUs;
        }
        if (!this.notifyDiscontinuity) {
            return C.TIME_UNSET;
        }
        if (!this.loadingFinished && getExtractedSamplesCount() <= this.extractedSamplesCountAtStartOfLoad) {
            return C.TIME_UNSET;
        }
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j10) {
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.preRelease();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages(null);
        this.callback = null;
        this.released = true;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.handler.post(new m(this, seekMap, 0));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j10) {
        assertPrepared();
        boolean[] zArr = this.trackState.trackIsAudioVideoFlags;
        if (!this.seekMap.isSeekable()) {
            j10 = 0;
        }
        int i10 = 0;
        this.notifyDiscontinuity = false;
        boolean z = this.lastSeekPositionUs == j10;
        this.lastSeekPositionUs = j10;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j10;
            return j10;
        }
        if (this.dataType == 7 || ((!this.loadingFinished && !this.loader.isLoading()) || !seekInsideBufferUs(zArr, j10, z))) {
            this.pendingDeferredRetry = false;
            this.pendingResetPositionUs = j10;
            this.loadingFinished = false;
            this.pendingInitialDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length = sampleQueueArr.length;
                while (i10 < length) {
                    sampleQueueArr[i10].discardToEnd();
                    i10++;
                }
                this.loader.cancelLoading();
                return j10;
            }
            this.loader.clearFatalError();
            SampleQueue[] sampleQueueArr2 = this.sampleQueues;
            int length2 = sampleQueueArr2.length;
            while (i10 < length2) {
                sampleQueueArr2[i10].reset();
                i10++;
            }
        }
        return j10;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j10) {
        ExoTrackSelection exoTrackSelection;
        assertPrepared();
        TrackState trackState = this.trackState;
        TrackGroupArray trackGroupArray = trackState.tracks;
        boolean[] zArr3 = trackState.trackEnabledStates;
        int i10 = this.enabledTrackCount;
        int i11 = 0;
        for (int i12 = 0; i12 < exoTrackSelectionArr.length; i12++) {
            SampleStream sampleStream = sampleStreamArr[i12];
            if (sampleStream != null && (exoTrackSelectionArr[i12] == null || !zArr[i12])) {
                int i13 = ((SampleStreamImpl) sampleStream).track;
                ac.b.s(zArr3[i13]);
                this.enabledTrackCount--;
                zArr3[i13] = false;
                sampleStreamArr[i12] = null;
            }
        }
        boolean z = !this.seenFirstTrackSelection ? j10 == 0 || this.isSingleSample : i10 != 0;
        for (int i14 = 0; i14 < exoTrackSelectionArr.length; i14++) {
            if (sampleStreamArr[i14] == null && (exoTrackSelection = exoTrackSelectionArr[i14]) != null) {
                ac.b.s(exoTrackSelection.length() == 1);
                ac.b.s(exoTrackSelection.getIndexInTrackGroup(0) == 0);
                int iIndexOf = trackGroupArray.indexOf(exoTrackSelection.getTrackGroup());
                ac.b.s(!zArr3[iIndexOf]);
                this.enabledTrackCount++;
                zArr3[iIndexOf] = true;
                this.pendingInitialDiscontinuity = exoTrackSelection.getSelectedFormat().hasPrerollSamples | this.pendingInitialDiscontinuity;
                sampleStreamArr[i14] = new SampleStreamImpl(iIndexOf);
                zArr2[i14] = true;
                if (this.loadOnlySelectedTracks) {
                    z |= this.seenFirstTrackSelection;
                } else if (!z) {
                    SampleQueue sampleQueue = this.sampleQueues[iIndexOf];
                    z = (sampleQueue.getReadIndex() == 0 || sampleQueue.seekTo(j10, true)) ? false : true;
                }
            }
        }
        if (this.loadOnlySelectedTracks) {
            int i15 = 0;
            while (true) {
                ControlledTrackOutput[] controlledTrackOutputArr = this.controlledTrackOutputs;
                if (i15 >= controlledTrackOutputArr.length) {
                    break;
                }
                controlledTrackOutputArr[i15].updateSelectionState(zArr3[i15]);
                i15++;
            }
        }
        if (this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            this.pendingInitialDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length = sampleQueueArr.length;
                while (i11 < length) {
                    sampleQueueArr[i11].discardToEnd();
                    i11++;
                }
                this.loader.cancelLoading();
            } else {
                this.loadingFinished = false;
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length2 = sampleQueueArr2.length;
                while (i11 < length2) {
                    sampleQueueArr2[i11].reset();
                    i11++;
                }
            }
        } else if (z) {
            j10 = seekToUs(j10);
            while (i11 < sampleStreamArr.length) {
                if (sampleStreamArr[i11] != null) {
                    zArr2[i11] = true;
                }
                i11++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j10;
    }

    public int skipData(int i10, long j10) {
        if (suppressRead()) {
            return 0;
        }
        maybeNotifyDownstreamFormat(i10);
        SampleQueue sampleQueue = this.sampleQueues[i10];
        int skipCount = sampleQueue.getSkipCount(j10, this.loadingFinished);
        sampleQueue.skip(skipCount);
        if (skipCount == 0) {
            maybeStartDeferredRetry(i10);
        }
        return skipCount;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int i10, int i11) {
        return prepareTrackOutput(new TrackId(i10, false));
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j10, long j11, boolean z) {
        StatsDataSource statsDataSource = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, statsDataSource.getLastOpenedUri(), statsDataSource.getLastResponseHeaders(), j10, j11, statsDataSource.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs);
        if (z) {
            return;
        }
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset();
        }
        if (this.enabledTrackCount > 0) {
            MediaPeriod.Callback callback = this.callback;
            callback.getClass();
            callback.onContinueLoadingRequested(this);
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j10, long j11) {
        if (this.durationUs == C.TIME_UNSET && this.seekMap != null) {
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs(true);
            long j12 = largestQueuedTimestampUs == Long.MIN_VALUE ? 0L : largestQueuedTimestampUs + 10000;
            this.durationUs = j12;
            this.listener.onSourceInfoRefreshed(j12, this.seekMap, this.isLive);
        }
        StatsDataSource statsDataSource = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, statsDataSource.getLastOpenedUri(), statsDataSource.getLastResponseHeaders(), j10, j11, statsDataSource.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs);
        this.loadingFinished = true;
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public Loader.LoadErrorAction onLoadError(ExtractingLoadable extractingLoadable, long j10, long j11, IOException iOException, int i10) {
        ExtractingLoadable extractingLoadable2;
        Loader.LoadErrorAction loadErrorActionCreateRetryAction;
        StatsDataSource statsDataSource = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, statsDataSource.getLastOpenedUri(), statsDataSource.getLastResponseHeaders(), j10, j11, statsDataSource.getBytesRead());
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(1, -1, null, 0, null, Util.usToMs(extractingLoadable.seekTimeUs), Util.usToMs(this.durationUs)), iOException, i10));
        if (retryDelayMsFor == C.TIME_UNSET) {
            loadErrorActionCreateRetryAction = Loader.DONT_RETRY_FATAL;
            extractingLoadable2 = extractingLoadable;
        } else {
            int extractedSamplesCount = getExtractedSamplesCount();
            extractingLoadable2 = extractingLoadable;
            loadErrorActionCreateRetryAction = configureRetry(extractingLoadable2, extractedSamplesCount) ? Loader.createRetryAction(extractedSamplesCount > this.extractedSamplesCountAtStartOfLoad, retryDelayMsFor) : Loader.DONT_RETRY;
        }
        boolean zIsRetry = loadErrorActionCreateRetryAction.isRetry();
        this.mediaSourceEventDispatcher.loadError(loadEventInfo, 1, -1, null, 0, null, extractingLoadable2.seekTimeUs, this.durationUs, iOException, !zIsRetry);
        if (!zIsRetry) {
            this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable2.loadTaskId);
        }
        return loadErrorActionCreateRetryAction;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadStarted(ExtractingLoadable extractingLoadable, long j10, long j11, int i10) {
        StatsDataSource statsDataSource = extractingLoadable.dataSource;
        this.mediaSourceEventDispatcher.loadStarted(i10 == 0 ? new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, j10) : new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, statsDataSource.getLastOpenedUri(), statsDataSource.getLastResponseHeaders(), j10, j11, statsDataSource.getBytesRead()), 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, i10);
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError(this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }
}
