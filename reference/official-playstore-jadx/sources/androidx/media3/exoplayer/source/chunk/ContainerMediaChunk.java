package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class ContainerMediaChunk extends BaseMediaChunk {
    private final int chunkCount;
    private final ChunkExtractor chunkExtractor;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private long nextLoadPosition;
    private final long sampleOffsetUs;

    public ContainerMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i10, Object obj, long j10, long j11, long j12, long j13, long j14, int i11, long j15, ChunkExtractor chunkExtractor) {
        super(dataSource, dataSpec, format, i10, obj, j10, j11, j12, j13, j14);
        this.chunkCount = i11;
        this.sampleOffsetUs = j15;
        this.chunkExtractor = chunkExtractor;
    }

    private void maybeWriteEmptySamples(BaseMediaChunkOutput baseMediaChunkOutput) {
        if (MimeTypes.isImage(this.trackFormat.containerMimeType)) {
            Format format = this.trackFormat;
            int i10 = format.tileCountHorizontal;
            if ((i10 <= 1 && format.tileCountVertical <= 1) || i10 == -1 || format.tileCountVertical == -1) {
                return;
            }
            TrackOutput trackOutputTrack = baseMediaChunkOutput.track(0, 4);
            Format format2 = this.trackFormat;
            int i11 = format2.tileCountHorizontal * format2.tileCountVertical;
            long j10 = (this.endTimeUs - this.startTimeUs) / ((long) i11);
            for (int i12 = 1; i12 < i11; i12++) {
                trackOutputTrack.sampleData(new ParsableByteArray(), 0);
                trackOutputTrack.sampleMetadata(((long) i12) * j10, 0, 0, 0, null);
            }
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // androidx.media3.exoplayer.source.chunk.MediaChunk
    public long getNextChunkIndex() {
        return this.chunkIndex + ((long) this.chunkCount);
    }

    public final long getNextLoadPosition() {
        return this.nextLoadPosition;
    }

    public ChunkExtractor.TrackOutputProvider getTrackOutputProvider(BaseMediaChunkOutput baseMediaChunkOutput) {
        return baseMediaChunkOutput;
    }

    public final boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // androidx.media3.exoplayer.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void load() throws IOException {
        BaseMediaChunkOutput output = getOutput();
        if (this.nextLoadPosition == 0) {
            output.setSampleOffsetUs(this.sampleOffsetUs);
            ChunkExtractor chunkExtractor = this.chunkExtractor;
            ChunkExtractor.TrackOutputProvider trackOutputProvider = getTrackOutputProvider(output);
            long j10 = this.clippedStartTimeUs;
            long j11 = C.TIME_UNSET;
            long j12 = j10 == C.TIME_UNSET ? -9223372036854775807L : j10 - this.sampleOffsetUs;
            long j13 = this.clippedEndTimeUs;
            if (j13 != C.TIME_UNSET) {
                j11 = j13 - this.sampleOffsetUs;
            }
            chunkExtractor.init(trackOutputProvider, j12, j11);
        }
        try {
            DataSpec dataSpecSubrange = this.dataSpec.subrange(this.nextLoadPosition);
            StatsDataSource statsDataSource = this.dataSource;
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(statsDataSource, dataSpecSubrange.position, statsDataSource.open(dataSpecSubrange));
            do {
                try {
                    if (this.loadCanceled) {
                        break;
                    }
                } finally {
                    this.nextLoadPosition = defaultExtractorInput.getPosition() - this.dataSpec.position;
                }
            } while (this.chunkExtractor.read(defaultExtractorInput));
            maybeWriteEmptySamples(output);
            this.nextLoadPosition = defaultExtractorInput.getPosition() - this.dataSpec.position;
            onLoadEnded();
            DataSourceUtil.closeQuietly(this.dataSource);
            this.loadCompleted = !this.loadCanceled;
        } catch (Throwable th) {
            onLoadEnded();
            DataSourceUtil.closeQuietly(this.dataSource);
            throw th;
        }
    }

    public void onLoadEnded() {
    }
}
