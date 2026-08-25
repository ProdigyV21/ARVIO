package androidx.media3.extractor.avi;

import androidx.media3.common.util.ParsableByteArray;

/* JADX INFO: loaded from: classes3.dex */
final class AviMainHeaderChunk implements AviChunk {
    private static final int AVIF_HAS_INDEX = 16;
    public final int flags;
    public final int frameDurationUs;
    public final int streams;
    public final int totalFrames;

    private AviMainHeaderChunk(int i10, int i11, int i12, int i13) {
        this.frameDurationUs = i10;
        this.flags = i11;
        this.totalFrames = i12;
        this.streams = i13;
    }

    public static AviMainHeaderChunk parseFrom(ParsableByteArray parsableByteArray) {
        int littleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(8);
        int littleEndianInt2 = parsableByteArray.readLittleEndianInt();
        int littleEndianInt3 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int littleEndianInt4 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(12);
        return new AviMainHeaderChunk(littleEndianInt, littleEndianInt2, littleEndianInt3, littleEndianInt4);
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return AviExtractor.FOURCC_avih;
    }

    public boolean hasIndex() {
        return (this.flags & 16) == 16;
    }
}
