package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseMediaChunk extends MediaChunk {
    public final long clippedEndTimeUs;
    public final long clippedStartTimeUs;
    private int[] firstSampleIndices;
    private BaseMediaChunkOutput output;

    public BaseMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i10, Object obj, long j10, long j11, long j12, long j13, long j14) {
        super(dataSource, dataSpec, format, i10, obj, j10, j11, j14);
        this.clippedStartTimeUs = j12;
        this.clippedEndTimeUs = j13;
    }

    public final int getFirstSampleIndex(int i10) {
        int[] iArr = this.firstSampleIndices;
        iArr.getClass();
        return iArr[i10];
    }

    public final BaseMediaChunkOutput getOutput() {
        BaseMediaChunkOutput baseMediaChunkOutput = this.output;
        baseMediaChunkOutput.getClass();
        return baseMediaChunkOutput;
    }

    public void init(BaseMediaChunkOutput baseMediaChunkOutput) {
        this.output = baseMediaChunkOutput;
        this.firstSampleIndices = baseMediaChunkOutput.getWriteIndices();
    }
}
