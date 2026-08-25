package androidx.media3.exoplayer.hls;

import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class UnexpectedSampleTimestampException extends IOException {
    public final long lastAcceptedSampleTimeUs;
    public final MediaChunk mediaChunk;
    public final long rejectedSampleTimeUs;

    public UnexpectedSampleTimestampException(MediaChunk mediaChunk, long j10, long j11) {
        StringBuilder sb2 = new StringBuilder("Unexpected sample timestamp: ");
        sb2.append(Util.usToMs(j11));
        sb2.append(" in chunk [");
        sb2.append(mediaChunk.startTimeUs);
        sb2.append(", ");
        super(a0.c.j(mediaChunk.endTimeUs, "]", sb2));
        this.mediaChunk = mediaChunk;
        this.lastAcceptedSampleTimeUs = j10;
        this.rejectedSampleTimeUs = j11;
    }
}
