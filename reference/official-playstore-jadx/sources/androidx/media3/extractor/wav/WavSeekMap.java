package androidx.media3.extractor.wav;

import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

/* JADX INFO: loaded from: classes3.dex */
final class WavSeekMap implements SeekMap {
    private final long blockCount;
    private final long durationUs;
    private final long firstBlockPosition;
    private final int framesPerBlock;
    private final WavFormat wavFormat;

    public WavSeekMap(WavFormat wavFormat, int i10, long j10, long j11) {
        this.wavFormat = wavFormat;
        this.framesPerBlock = i10;
        this.firstBlockPosition = j10;
        long j12 = (j11 - j10) / ((long) wavFormat.blockSize);
        this.blockCount = j12;
        this.durationUs = blockIndexToTimeUs(j12);
    }

    private long blockIndexToTimeUs(long j10) {
        return Util.scaleLargeTimestamp(j10 * ((long) this.framesPerBlock), 1000000L, this.wavFormat.frameRateHz);
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j10) {
        long jConstrainValue = Util.constrainValue((((long) this.wavFormat.frameRateHz) * j10) / (((long) this.framesPerBlock) * 1000000), 0L, this.blockCount - 1);
        long j11 = (((long) this.wavFormat.blockSize) * jConstrainValue) + this.firstBlockPosition;
        long jBlockIndexToTimeUs = blockIndexToTimeUs(jConstrainValue);
        SeekPoint seekPoint = new SeekPoint(jBlockIndexToTimeUs, j11);
        if (jBlockIndexToTimeUs >= j10 || jConstrainValue == this.blockCount - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        long j12 = jConstrainValue + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(blockIndexToTimeUs(j12), (((long) this.wavFormat.blockSize) * j12) + this.firstBlockPosition));
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
