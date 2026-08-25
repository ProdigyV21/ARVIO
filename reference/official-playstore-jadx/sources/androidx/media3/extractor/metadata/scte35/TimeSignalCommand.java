package androidx.media3.extractor.metadata.scte35;

import a0.c;
import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;

/* JADX INFO: loaded from: classes3.dex */
public final class TimeSignalCommand extends SpliceCommand {
    public final long playbackPositionUs;
    public final long ptsTime;

    private TimeSignalCommand(long j10, long j11) {
        this.ptsTime = j10;
        this.playbackPositionUs = j11;
    }

    public static TimeSignalCommand parseFromSection(ParsableByteArray parsableByteArray, long j10, TimestampAdjuster timestampAdjuster) {
        long spliceTime = parseSpliceTime(parsableByteArray, j10);
        return new TimeSignalCommand(spliceTime, timestampAdjuster.adjustTsTimestamp(spliceTime));
    }

    public static long parseSpliceTime(ParsableByteArray parsableByteArray, long j10) {
        long unsignedByte = parsableByteArray.readUnsignedByte();
        return (128 & unsignedByte) != 0 ? 8589934591L & ((((unsignedByte & 1) << 32) | parsableByteArray.readUnsignedInt()) + j10) : C.TIME_UNSET;
    }

    @Override // androidx.media3.extractor.metadata.scte35.SpliceCommand
    public String toString() {
        StringBuilder sb2 = new StringBuilder("SCTE-35 TimeSignalCommand { ptsTime=");
        sb2.append(this.ptsTime);
        sb2.append(", playbackPositionUs= ");
        return c.j(this.playbackPositionUs, " }", sb2);
    }
}
