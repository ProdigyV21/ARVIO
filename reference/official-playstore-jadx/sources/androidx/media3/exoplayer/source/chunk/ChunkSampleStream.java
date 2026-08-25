package androidx.media3.exoplayer.source.chunk;

import a0.c;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.chunk.ChunkSource;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ChunkSampleStream<T extends ChunkSource> implements SampleStream, SequenceableLoader, Loader.Callback<Chunk>, Loader.ReleaseCallback {
    private static final String TAG = "ChunkSampleStream";
    private final SequenceableLoader.Callback<ChunkSampleStream<T>> callback;
    private boolean canReportInitialDiscontinuity;
    private BaseMediaChunk canceledMediaChunk;
    private final BaseMediaChunkOutput chunkOutput;
    private final T chunkSource;
    private final SampleQueue[] embeddedSampleQueues;
    private final Format[] embeddedTrackFormats;
    private final int[] embeddedTrackTypes;
    private final boolean[] embeddedTracksSelected;
    private boolean hasInitialDiscontinuity;
    private long lastSeekPositionUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader;
    private Chunk loadingChunk;
    boolean loadingFinished;
    private final ArrayList<BaseMediaChunk> mediaChunks;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private final ChunkHolder nextChunkHolder;
    private int nextNotifyPrimaryFormatMediaChunkIndex;
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final SampleQueue primarySampleQueue;
    public final int primaryTrackType;
    private final List<BaseMediaChunk> readOnlyMediaChunks;
    private ReleaseCallback<T> releaseCallback;

    public final class EmbeddedSampleStream implements SampleStream {
        private final int index;
        private boolean notifiedDownstreamFormat;
        public final ChunkSampleStream<T> parent;
        private final SampleQueue sampleQueue;

        public EmbeddedSampleStream(ChunkSampleStream<T> chunkSampleStream, SampleQueue sampleQueue, int i10) {
            this.parent = chunkSampleStream;
            this.sampleQueue = sampleQueue;
            this.index = i10;
        }

        private void maybeNotifyDownstreamFormat() {
            if (this.notifiedDownstreamFormat) {
                return;
            }
            ChunkSampleStream.this.mediaSourceEventDispatcher.downstreamFormatChanged(ChunkSampleStream.this.embeddedTrackTypes[this.index], ChunkSampleStream.this.embeddedTrackFormats[this.index], 0, null, ChunkSampleStream.this.lastSeekPositionUs);
            this.notifiedDownstreamFormat = true;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return !ChunkSampleStream.this.isPendingReset() && this.sampleQueue.isReady(ChunkSampleStream.this.loadingFinished);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() {
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i10) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return -3;
            }
            if (ChunkSampleStream.this.canceledMediaChunk != null && ChunkSampleStream.this.canceledMediaChunk.getFirstSampleIndex(this.index + 1) <= this.sampleQueue.getReadIndex()) {
                return -3;
            }
            maybeNotifyDownstreamFormat();
            return this.sampleQueue.read(formatHolder, decoderInputBuffer, i10, ChunkSampleStream.this.loadingFinished);
        }

        public void release() {
            ac.b.s(ChunkSampleStream.this.embeddedTracksSelected[this.index]);
            ChunkSampleStream.this.embeddedTracksSelected[this.index] = false;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j10) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return 0;
            }
            int skipCount = this.sampleQueue.getSkipCount(j10, ChunkSampleStream.this.loadingFinished);
            if (ChunkSampleStream.this.canceledMediaChunk != null) {
                skipCount = Math.min(skipCount, ChunkSampleStream.this.canceledMediaChunk.getFirstSampleIndex(this.index + 1) - this.sampleQueue.getReadIndex());
            }
            this.sampleQueue.skip(skipCount);
            if (skipCount > 0) {
                maybeNotifyDownstreamFormat();
            }
            return skipCount;
        }
    }

    public interface ReleaseCallback<T extends ChunkSource> {
        void onSampleStreamReleased(ChunkSampleStream<T> chunkSampleStream);
    }

    public ChunkSampleStream(int i10, int[] iArr, Format[] formatArr, T t2, SequenceableLoader.Callback<ChunkSampleStream<T>> callback, Allocator allocator, long j10, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, boolean z, ReleasableExecutor releasableExecutor) {
        this.primaryTrackType = i10;
        int i11 = 0;
        iArr = iArr == null ? new int[0] : iArr;
        this.embeddedTrackTypes = iArr;
        this.embeddedTrackFormats = formatArr == null ? new Format[0] : formatArr;
        this.chunkSource = t2;
        this.callback = callback;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.canReportInitialDiscontinuity = z;
        this.loader = releasableExecutor != null ? new Loader(releasableExecutor) : new Loader(TAG);
        this.nextChunkHolder = new ChunkHolder();
        ArrayList<BaseMediaChunk> arrayList = new ArrayList<>();
        this.mediaChunks = arrayList;
        this.readOnlyMediaChunks = Collections.unmodifiableList(arrayList);
        int length = iArr.length;
        this.embeddedSampleQueues = new SampleQueue[length];
        this.embeddedTracksSelected = new boolean[length];
        int i12 = length + 1;
        int[] iArr2 = new int[i12];
        SampleQueue[] sampleQueueArr = new SampleQueue[i12];
        SampleQueue sampleQueueCreateWithDrm = SampleQueue.createWithDrm(allocator, drmSessionManager, eventDispatcher);
        this.primarySampleQueue = sampleQueueCreateWithDrm;
        iArr2[0] = i10;
        sampleQueueArr[0] = sampleQueueCreateWithDrm;
        while (i11 < length) {
            SampleQueue sampleQueueCreateWithoutDrm = SampleQueue.createWithoutDrm(allocator);
            this.embeddedSampleQueues[i11] = sampleQueueCreateWithoutDrm;
            int i13 = i11 + 1;
            sampleQueueArr[i13] = sampleQueueCreateWithoutDrm;
            iArr2[i13] = this.embeddedTrackTypes[i11];
            i11 = i13;
        }
        this.chunkOutput = new BaseMediaChunkOutput(iArr2, sampleQueueArr);
        this.pendingResetPositionUs = j10;
        this.lastSeekPositionUs = j10;
    }

    private void discardDownstreamMediaChunks(int i10) {
        int iMin = Math.min(primarySampleIndexToMediaChunkIndex(i10, 0), this.nextNotifyPrimaryFormatMediaChunkIndex);
        if (iMin > 0) {
            Util.removeRange(this.mediaChunks, 0, iMin);
            this.nextNotifyPrimaryFormatMediaChunkIndex -= iMin;
        }
    }

    private void discardUpstream(int i10) {
        ac.b.s(!this.loader.isLoading());
        int size = this.mediaChunks.size();
        while (true) {
            if (i10 >= size) {
                i10 = -1;
                break;
            } else if (!haveReadFromMediaChunk(i10)) {
                break;
            } else {
                i10++;
            }
        }
        if (i10 == -1) {
            return;
        }
        long j10 = getLastMediaChunk().endTimeUs;
        BaseMediaChunk baseMediaChunkDiscardUpstreamMediaChunksFromIndex = discardUpstreamMediaChunksFromIndex(i10);
        if (this.mediaChunks.isEmpty()) {
            this.pendingResetPositionUs = this.lastSeekPositionUs;
        }
        this.loadingFinished = false;
        this.mediaSourceEventDispatcher.upstreamDiscarded(this.primaryTrackType, baseMediaChunkDiscardUpstreamMediaChunksFromIndex.startTimeUs, j10);
    }

    private BaseMediaChunk discardUpstreamMediaChunksFromIndex(int i10) {
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i10);
        ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
        Util.removeRange(arrayList, i10, arrayList.size());
        this.nextNotifyPrimaryFormatMediaChunkIndex = Math.max(this.nextNotifyPrimaryFormatMediaChunkIndex, this.mediaChunks.size());
        int i11 = 0;
        this.primarySampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(0));
        while (true) {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i11 >= sampleQueueArr.length) {
                return baseMediaChunk;
            }
            SampleQueue sampleQueue = sampleQueueArr[i11];
            i11++;
            sampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(i11));
        }
    }

    private BaseMediaChunk getLastMediaChunk() {
        return (BaseMediaChunk) c.h(1, this.mediaChunks);
    }

    private boolean haveReadFromMediaChunk(int i10) {
        int readIndex;
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i10);
        if (this.primarySampleQueue.getReadIndex() > baseMediaChunk.getFirstSampleIndex(0)) {
            return true;
        }
        int i11 = 0;
        do {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i11 >= sampleQueueArr.length) {
                return false;
            }
            readIndex = sampleQueueArr[i11].getReadIndex();
            i11++;
        } while (readIndex <= baseMediaChunk.getFirstSampleIndex(i11));
        return true;
    }

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }

    private void maybeNotifyPrimaryTrackFormatChanged() {
        int iPrimarySampleIndexToMediaChunkIndex = primarySampleIndexToMediaChunkIndex(this.primarySampleQueue.getReadIndex(), this.nextNotifyPrimaryFormatMediaChunkIndex - 1);
        while (true) {
            int i10 = this.nextNotifyPrimaryFormatMediaChunkIndex;
            if (i10 > iPrimarySampleIndexToMediaChunkIndex) {
                return;
            }
            this.nextNotifyPrimaryFormatMediaChunkIndex = i10 + 1;
            maybeNotifyPrimaryTrackFormatChanged(i10);
        }
    }

    private int primarySampleIndexToMediaChunkIndex(int i10, int i11) {
        do {
            i11++;
            if (i11 >= this.mediaChunks.size()) {
                return this.mediaChunks.size() - 1;
            }
        } while (this.mediaChunks.get(i11).getFirstSampleIndex(0) <= i10);
        return i11 - 1;
    }

    private void resetSampleQueues() {
        this.primarySampleQueue.reset();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.reset();
        }
    }

    public boolean consumeInitialDiscontinuity() {
        try {
            return this.hasInitialDiscontinuity;
        } finally {
            this.hasInitialDiscontinuity = false;
        }
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        List<BaseMediaChunk> list;
        long j10;
        if (this.loadingFinished || this.loader.isLoading() || this.loader.hasFatalError()) {
            return false;
        }
        boolean zIsPendingReset = isPendingReset();
        if (zIsPendingReset) {
            list = Collections.EMPTY_LIST;
            j10 = this.pendingResetPositionUs;
        } else {
            list = this.readOnlyMediaChunks;
            j10 = getLastMediaChunk().endTimeUs;
        }
        this.chunkSource.getNextChunk(loadingInfo, j10, list, this.nextChunkHolder);
        ChunkHolder chunkHolder = this.nextChunkHolder;
        boolean z = chunkHolder.endOfStream;
        Chunk chunk = chunkHolder.chunk;
        chunkHolder.clear();
        if (z) {
            this.pendingResetPositionUs = C.TIME_UNSET;
            this.loadingFinished = true;
            return true;
        }
        if (chunk == null) {
            return false;
        }
        this.loadingChunk = chunk;
        if (isMediaChunk(chunk)) {
            BaseMediaChunk baseMediaChunk = (BaseMediaChunk) chunk;
            if (zIsPendingReset) {
                long j11 = baseMediaChunk.startTimeUs;
                long j12 = this.pendingResetPositionUs;
                if (j11 < j12) {
                    this.primarySampleQueue.setStartTimeUs(j12);
                    for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
                        sampleQueue.setStartTimeUs(this.pendingResetPositionUs);
                    }
                    if (this.canReportInitialDiscontinuity) {
                        Format format = baseMediaChunk.trackFormat;
                        this.hasInitialDiscontinuity = !MimeTypes.allSamplesAreSyncSamples(format.sampleMimeType, format.codecs);
                    }
                }
                this.canReportInitialDiscontinuity = false;
                this.pendingResetPositionUs = C.TIME_UNSET;
            }
            baseMediaChunk.init(this.chunkOutput);
            this.mediaChunks.add(baseMediaChunk);
        } else if (chunk instanceof InitializationChunk) {
            ((InitializationChunk) chunk).init(this.chunkOutput);
        }
        this.loader.startLoading(chunk, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(chunk.type));
        return true;
    }

    public void discardBuffer(long j10, boolean z) {
        if (isPendingReset()) {
            return;
        }
        int firstIndex = this.primarySampleQueue.getFirstIndex();
        this.primarySampleQueue.discardTo(j10, z, true);
        int firstIndex2 = this.primarySampleQueue.getFirstIndex();
        if (firstIndex2 > firstIndex) {
            long firstTimestampUs = this.primarySampleQueue.getFirstTimestampUs();
            int i10 = 0;
            while (true) {
                SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
                if (i10 >= sampleQueueArr.length) {
                    break;
                }
                sampleQueueArr[i10].discardTo(firstTimestampUs, z, this.embeddedTracksSelected[i10]);
                i10++;
            }
        }
        discardDownstreamMediaChunks(firstIndex2);
    }

    public void discardUpstreamSamplesForClippedDuration(long j10) {
        ac.b.s(!this.loader.isLoading());
        if (isPendingReset() || j10 == C.TIME_UNSET || this.mediaChunks.isEmpty()) {
            return;
        }
        BaseMediaChunk lastMediaChunk = getLastMediaChunk();
        long j11 = lastMediaChunk.clippedEndTimeUs;
        if (j11 == C.TIME_UNSET) {
            j11 = lastMediaChunk.endTimeUs;
        }
        if (j11 <= j10) {
            return;
        }
        long largestQueuedTimestampUs = this.primarySampleQueue.getLargestQueuedTimestampUs();
        if (largestQueuedTimestampUs <= j10) {
            return;
        }
        this.primarySampleQueue.discardUpstreamFrom(Math.max(j10, this.primarySampleQueue.getLargestReadTimestampUs() + 1));
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.discardUpstreamFrom(Math.max(j10, sampleQueue.getLargestReadTimestampUs() + 1));
        }
        this.mediaSourceEventDispatcher.upstreamDiscarded(this.primaryTrackType, j10, largestQueuedTimestampUs);
    }

    public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
        return this.chunkSource.getAdjustedSeekPositionUs(j10, seekParameters);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long jMax = this.lastSeekPositionUs;
        BaseMediaChunk lastMediaChunk = getLastMediaChunk();
        if (!lastMediaChunk.isLoadCompleted()) {
            lastMediaChunk = this.mediaChunks.size() > 1 ? (BaseMediaChunk) c.h(2, this.mediaChunks) : null;
        }
        if (lastMediaChunk != null) {
            jMax = Math.max(jMax, lastMediaChunk.endTimeUs);
        }
        return Math.max(jMax, this.primarySampleQueue.getLargestQueuedTimestampUs());
    }

    public T getChunkSource() {
        return this.chunkSource;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return getLastMediaChunk().endTimeUs;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.loader.isLoading();
    }

    public boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public boolean isReady() {
        return !isPendingReset() && this.primarySampleQueue.isReady(this.loadingFinished);
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        this.primarySampleQueue.maybeThrowError();
        if (this.loader.isLoading()) {
            return;
        }
        this.chunkSource.maybeThrowError();
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        this.primarySampleQueue.release();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.release();
        }
        this.chunkSource.release();
        ReleaseCallback<T> releaseCallback = this.releaseCallback;
        if (releaseCallback != null) {
            releaseCallback.onSampleStreamReleased(this);
        }
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i10) {
        if (isPendingReset()) {
            return -3;
        }
        BaseMediaChunk baseMediaChunk = this.canceledMediaChunk;
        if (baseMediaChunk != null && baseMediaChunk.getFirstSampleIndex(0) <= this.primarySampleQueue.getReadIndex()) {
            return -3;
        }
        maybeNotifyPrimaryTrackFormatChanged();
        return this.primarySampleQueue.read(formatHolder, decoderInputBuffer, i10, this.loadingFinished);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j10) {
        if (this.loader.hasFatalError() || isPendingReset()) {
            return;
        }
        if (!this.loader.isLoading()) {
            int preferredQueueSize = this.chunkSource.getPreferredQueueSize(j10, this.readOnlyMediaChunks);
            if (preferredQueueSize < this.mediaChunks.size()) {
                discardUpstream(preferredQueueSize);
                return;
            }
            return;
        }
        Chunk chunk = this.loadingChunk;
        chunk.getClass();
        if (!(isMediaChunk(chunk) && haveReadFromMediaChunk(this.mediaChunks.size() - 1)) && this.chunkSource.shouldCancelLoad(j10, chunk, this.readOnlyMediaChunks)) {
            this.loader.cancelLoading();
            if (isMediaChunk(chunk)) {
                this.canceledMediaChunk = (BaseMediaChunk) chunk;
            }
        }
    }

    public void release() {
        release(null);
    }

    public void seekToUs(long j10) {
        BaseMediaChunk baseMediaChunk;
        boolean zSeekTo;
        this.lastSeekPositionUs = j10;
        int i10 = 0;
        this.canReportInitialDiscontinuity = false;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j10;
            return;
        }
        for (int i11 = 0; i11 < this.mediaChunks.size(); i11++) {
            baseMediaChunk = this.mediaChunks.get(i11);
            long j11 = baseMediaChunk.startTimeUs;
            if (j11 == j10 && baseMediaChunk.clippedStartTimeUs == C.TIME_UNSET) {
                break;
            } else {
                if (j11 > j10) {
                    break;
                }
            }
        }
        baseMediaChunk = null;
        if (baseMediaChunk != null) {
            zSeekTo = this.primarySampleQueue.seekTo(baseMediaChunk.getFirstSampleIndex(0));
        } else {
            long nextLoadPositionUs = getNextLoadPositionUs();
            zSeekTo = this.primarySampleQueue.seekTo(j10, nextLoadPositionUs == Long.MIN_VALUE || j10 < nextLoadPositionUs);
        }
        if (zSeekTo) {
            this.nextNotifyPrimaryFormatMediaChunkIndex = primarySampleIndexToMediaChunkIndex(this.primarySampleQueue.getReadIndex(), 0);
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            int length = sampleQueueArr.length;
            while (i10 < length) {
                sampleQueueArr[i10].seekTo(j10, true);
                i10++;
            }
            return;
        }
        this.pendingResetPositionUs = j10;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        this.nextNotifyPrimaryFormatMediaChunkIndex = 0;
        if (!this.loader.isLoading()) {
            this.loader.clearFatalError();
            resetSampleQueues();
            return;
        }
        this.primarySampleQueue.discardToEnd();
        SampleQueue[] sampleQueueArr2 = this.embeddedSampleQueues;
        int length2 = sampleQueueArr2.length;
        while (i10 < length2) {
            sampleQueueArr2[i10].discardToEnd();
            i10++;
        }
        this.loader.cancelLoading();
    }

    public ChunkSampleStream<T>.EmbeddedSampleStream selectEmbeddedTrack(long j10, int i10) {
        for (int i11 = 0; i11 < this.embeddedSampleQueues.length; i11++) {
            if (this.embeddedTrackTypes[i11] == i10) {
                ac.b.s(!this.embeddedTracksSelected[i11]);
                this.embeddedTracksSelected[i11] = true;
                this.embeddedSampleQueues[i11].seekTo(j10, true);
                return new EmbeddedSampleStream(this, this.embeddedSampleQueues[i11], i11);
            }
        }
        throw new IllegalStateException();
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int skipData(long j10) {
        if (isPendingReset()) {
            return 0;
        }
        int skipCount = this.primarySampleQueue.getSkipCount(j10, this.loadingFinished);
        BaseMediaChunk baseMediaChunk = this.canceledMediaChunk;
        if (baseMediaChunk != null) {
            skipCount = Math.min(skipCount, baseMediaChunk.getFirstSampleIndex(0) - this.primarySampleQueue.getReadIndex());
        }
        this.primarySampleQueue.skip(skipCount);
        maybeNotifyPrimaryTrackFormatChanged();
        return skipCount;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(Chunk chunk, long j10, long j11, boolean z) {
        this.loadingChunk = null;
        this.canceledMediaChunk = null;
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j10, j11, chunk.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs);
        if (z) {
            return;
        }
        if (isPendingReset()) {
            resetSampleQueues();
        } else if (isMediaChunk(chunk)) {
            discardUpstreamMediaChunksFromIndex(this.mediaChunks.size() - 1);
            if (this.mediaChunks.isEmpty()) {
                this.pendingResetPositionUs = this.lastSeekPositionUs;
            }
        }
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(Chunk chunk, long j10, long j11) {
        this.loadingChunk = null;
        this.chunkSource.onChunkLoadCompleted(chunk);
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j10, j11, chunk.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs);
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public Loader.LoadErrorAction onLoadError(Chunk chunk, long j10, long j11, IOException iOException, int i10) {
        Loader.LoadErrorAction loadErrorActionCreateRetryAction;
        long jBytesLoaded = chunk.bytesLoaded();
        boolean zIsMediaChunk = isMediaChunk(chunk);
        int size = this.mediaChunks.size() - 1;
        boolean z = (jBytesLoaded != 0 && zIsMediaChunk && haveReadFromMediaChunk(size)) ? false : true;
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j10, j11, jBytesLoaded);
        LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo = new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, Util.usToMs(chunk.startTimeUs), Util.usToMs(chunk.endTimeUs)), iOException, i10);
        if (!this.chunkSource.onChunkLoadError(chunk, z, loadErrorInfo, this.loadErrorHandlingPolicy)) {
            loadErrorActionCreateRetryAction = null;
        } else if (z) {
            loadErrorActionCreateRetryAction = Loader.DONT_RETRY;
            if (zIsMediaChunk) {
                ac.b.s(discardUpstreamMediaChunksFromIndex(size) == chunk);
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }
            }
        } else {
            Log.w(TAG, "Ignoring attempt to cancel non-cancelable load.");
            loadErrorActionCreateRetryAction = null;
        }
        if (loadErrorActionCreateRetryAction == null) {
            long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(loadErrorInfo);
            loadErrorActionCreateRetryAction = retryDelayMsFor != C.TIME_UNSET ? Loader.createRetryAction(false, retryDelayMsFor) : Loader.DONT_RETRY_FATAL;
        }
        boolean zIsRetry = loadErrorActionCreateRetryAction.isRetry();
        this.mediaSourceEventDispatcher.loadError(loadEventInfo, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, iOException, !zIsRetry);
        if (!zIsRetry) {
            this.loadingChunk = null;
            this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk.loadTaskId);
            this.callback.onContinueLoadingRequested(this);
        }
        return loadErrorActionCreateRetryAction;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadStarted(Chunk chunk, long j10, long j11, int i10) {
        this.mediaSourceEventDispatcher.loadStarted(i10 == 0 ? new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, j10) : new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j10, j11, chunk.bytesLoaded()), chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, i10);
    }

    public void release(ReleaseCallback<T> releaseCallback) {
        this.releaseCallback = releaseCallback;
        this.primarySampleQueue.preRelease();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.preRelease();
        }
        this.loader.release(this);
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int i10) {
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i10);
        Format format = baseMediaChunk.trackFormat;
        if (!format.equals(this.primaryDownstreamTrackFormat)) {
            this.mediaSourceEventDispatcher.downstreamFormatChanged(this.primaryTrackType, format, baseMediaChunk.trackSelectionReason, baseMediaChunk.trackSelectionData, baseMediaChunk.startTimeUs);
        }
        this.primaryDownstreamTrackFormat = format;
    }
}
