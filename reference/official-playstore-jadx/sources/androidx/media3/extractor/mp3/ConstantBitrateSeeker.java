package androidx.media3.extractor.mp3;

import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.MpegAudioUtil;

/* JADX INFO: loaded from: classes3.dex */
final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    private final boolean allowSeeksIfLengthUnknown;
    private final int bitrate;
    private final long dataEndPosition;
    private final long firstFramePosition;
    private final int frameSize;

    public ConstantBitrateSeeker(long j10, long j11, MpegAudioUtil.Header header, boolean z) {
        this(j10, j11, header.bitrate, header.frameSize, z);
    }

    public ConstantBitrateSeeker copyWithNewDataEndPosition(long j10) {
        return new ConstantBitrateSeeker(j10, this.firstFramePosition, this.bitrate, this.frameSize, this.allowSeeksIfLengthUnknown);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.bitrate;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataStartPosition() {
        return this.firstFramePosition;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long j10) {
        return getTimeUsAtPosition(j10);
    }

    public ConstantBitrateSeeker(long j10, long j11, int i10, int i11, boolean z) {
        super(j10, j11, i10, i11, z);
        long j12 = j10;
        this.firstFramePosition = j11;
        this.bitrate = i10;
        this.frameSize = i11;
        this.allowSeeksIfLengthUnknown = z;
        this.dataEndPosition = j12 == -1 ? -1L : j12;
    }
}
