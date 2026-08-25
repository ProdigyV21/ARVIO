package androidx.media3.exoplayer;

import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
public final class DecoderCounters {
    public int decoderInitCount;
    public int decoderReleaseCount;
    public int droppedBufferCount;
    public int droppedInputBufferCount;
    public int droppedToKeyframeCount;
    public int maxConsecutiveDroppedBufferCount;
    public int queuedInputBufferCount;
    public int renderedOutputBufferCount;
    public int skippedInputBufferCount;
    public int skippedOutputBufferCount;
    public long totalVideoFrameProcessingOffsetUs;
    public int videoFrameProcessingOffsetCount;

    private void addVideoFrameProcessingOffsets(long j10, int i10) {
        this.totalVideoFrameProcessingOffsetUs += j10;
        this.videoFrameProcessingOffsetCount += i10;
    }

    public void addVideoFrameProcessingOffset(long j10) {
        addVideoFrameProcessingOffsets(j10, 1);
    }

    public synchronized void ensureUpdated() {
    }

    public void merge(DecoderCounters decoderCounters) {
        this.decoderInitCount += decoderCounters.decoderInitCount;
        this.decoderReleaseCount += decoderCounters.decoderReleaseCount;
        this.queuedInputBufferCount += decoderCounters.queuedInputBufferCount;
        this.skippedInputBufferCount += decoderCounters.skippedInputBufferCount;
        this.renderedOutputBufferCount += decoderCounters.renderedOutputBufferCount;
        this.skippedOutputBufferCount += decoderCounters.skippedOutputBufferCount;
        this.droppedBufferCount += decoderCounters.droppedBufferCount;
        this.droppedInputBufferCount += decoderCounters.droppedInputBufferCount;
        this.maxConsecutiveDroppedBufferCount = Math.max(this.maxConsecutiveDroppedBufferCount, decoderCounters.maxConsecutiveDroppedBufferCount);
        this.droppedToKeyframeCount += decoderCounters.droppedToKeyframeCount;
        addVideoFrameProcessingOffsets(decoderCounters.totalVideoFrameProcessingOffsetUs, decoderCounters.videoFrameProcessingOffsetCount);
    }

    public String toString() {
        return Util.formatInvariant("DecoderCounters {\n decoderInits=%s,\n decoderReleases=%s\n queuedInputBuffers=%s\n skippedInputBuffers=%s\n renderedOutputBuffers=%s\n skippedOutputBuffers=%s\n droppedBuffers=%s\n droppedInputBuffers=%s\n maxConsecutiveDroppedBuffers=%s\n droppedToKeyframeEvents=%s\n totalVideoFrameProcessingOffsetUs=%s\n videoFrameProcessingOffsetCount=%s\n}", Integer.valueOf(this.decoderInitCount), Integer.valueOf(this.decoderReleaseCount), Integer.valueOf(this.queuedInputBufferCount), Integer.valueOf(this.skippedInputBufferCount), Integer.valueOf(this.renderedOutputBufferCount), Integer.valueOf(this.skippedOutputBufferCount), Integer.valueOf(this.droppedBufferCount), Integer.valueOf(this.droppedInputBufferCount), Integer.valueOf(this.maxConsecutiveDroppedBufferCount), Integer.valueOf(this.droppedToKeyframeCount), Long.valueOf(this.totalVideoFrameProcessingOffsetUs), Integer.valueOf(this.videoFrameProcessingOffsetCount));
    }
}
