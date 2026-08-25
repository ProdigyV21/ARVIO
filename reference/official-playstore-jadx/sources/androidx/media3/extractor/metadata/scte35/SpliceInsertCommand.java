package androidx.media3.extractor.metadata.scte35;

import a0.c;
import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class SpliceInsertCommand extends SpliceCommand {
    public final boolean autoReturn;
    public final int availNum;
    public final int availsExpected;
    public final long breakDurationUs;
    public final List<ComponentSplice> componentSpliceList;
    public final boolean outOfNetworkIndicator;
    public final boolean programSpliceFlag;
    public final long programSplicePlaybackPositionUs;
    public final long programSplicePts;
    public final boolean spliceEventCancelIndicator;
    public final long spliceEventId;
    public final boolean spliceImmediateFlag;
    public final int uniqueProgramId;

    public static final class ComponentSplice {
        public final long componentSplicePlaybackPositionUs;
        public final long componentSplicePts;
        public final int componentTag;

        private ComponentSplice(int i10, long j10, long j11) {
            this.componentTag = i10;
            this.componentSplicePts = j10;
            this.componentSplicePlaybackPositionUs = j11;
        }
    }

    private SpliceInsertCommand(long j10, boolean z, boolean z5, boolean z10, boolean z11, long j11, long j12, List<ComponentSplice> list, boolean z12, long j13, int i10, int i11, int i12) {
        this.spliceEventId = j10;
        this.spliceEventCancelIndicator = z;
        this.outOfNetworkIndicator = z5;
        this.programSpliceFlag = z10;
        this.spliceImmediateFlag = z11;
        this.programSplicePts = j11;
        this.programSplicePlaybackPositionUs = j12;
        this.componentSpliceList = Collections.unmodifiableList(list);
        this.autoReturn = z12;
        this.breakDurationUs = j13;
        this.uniqueProgramId = i10;
        this.availNum = i11;
        this.availsExpected = i12;
    }

    public static SpliceInsertCommand parseFromSection(ParsableByteArray parsableByteArray, long j10, TimestampAdjuster timestampAdjuster) {
        List list;
        long j11;
        boolean z;
        boolean z5;
        boolean z10;
        boolean z11;
        int i10;
        int unsignedByte;
        int unsignedByte2;
        boolean z12;
        int i11;
        long spliceTime;
        long unsignedInt = parsableByteArray.readUnsignedInt();
        boolean z13 = (parsableByteArray.readUnsignedByte() & 128) != 0;
        List list2 = Collections.EMPTY_LIST;
        long unsignedInt2 = C.TIME_UNSET;
        if (z13) {
            list = list2;
            j11 = -9223372036854775807L;
            z = false;
            z5 = false;
            z10 = false;
            z11 = false;
            i10 = 0;
            unsignedByte = 0;
            unsignedByte2 = 0;
        } else {
            int unsignedByte3 = parsableByteArray.readUnsignedByte();
            boolean z14 = (unsignedByte3 & 128) != 0;
            boolean z15 = (unsignedByte3 & 64) != 0;
            boolean z16 = (unsignedByte3 & 32) != 0;
            boolean z17 = (unsignedByte3 & 16) != 0;
            long spliceTime2 = (!z15 || z17) ? -9223372036854775807L : TimeSignalCommand.parseSpliceTime(parsableByteArray, j10);
            if (!z15) {
                int unsignedByte4 = parsableByteArray.readUnsignedByte();
                ArrayList arrayList = new ArrayList(unsignedByte4);
                int i12 = 0;
                while (i12 < unsignedByte4) {
                    int unsignedByte5 = parsableByteArray.readUnsignedByte();
                    if (z17) {
                        i11 = unsignedByte4;
                        spliceTime = -9223372036854775807L;
                    } else {
                        i11 = unsignedByte4;
                        spliceTime = TimeSignalCommand.parseSpliceTime(parsableByteArray, j10);
                    }
                    arrayList.add(new ComponentSplice(unsignedByte5, spliceTime, timestampAdjuster.adjustTsTimestamp(spliceTime)));
                    i12++;
                    unsignedByte4 = i11;
                }
                list2 = arrayList;
            }
            if (z16) {
                long unsignedByte6 = parsableByteArray.readUnsignedByte();
                boolean z18 = (128 & unsignedByte6) != 0;
                unsignedInt2 = ((((unsignedByte6 & 1) << 32) | parsableByteArray.readUnsignedInt()) * 1000) / 90;
                z12 = z18;
            } else {
                z12 = false;
            }
            int unsignedShort = parsableByteArray.readUnsignedShort();
            long j12 = spliceTime2;
            j11 = unsignedInt2;
            unsignedInt2 = j12;
            unsignedByte = parsableByteArray.readUnsignedByte();
            unsignedByte2 = parsableByteArray.readUnsignedByte();
            i10 = unsignedShort;
            z11 = z12;
            z = z14;
            z5 = z15;
            list = list2;
            z10 = z17;
        }
        return new SpliceInsertCommand(unsignedInt, z13, z, z5, z10, unsignedInt2, timestampAdjuster.adjustTsTimestamp(unsignedInt2), list, z11, j11, i10, unsignedByte, unsignedByte2);
    }

    @Override // androidx.media3.extractor.metadata.scte35.SpliceCommand
    public String toString() {
        StringBuilder sb2 = new StringBuilder("SCTE-35 SpliceInsertCommand { programSplicePts=");
        sb2.append(this.programSplicePts);
        sb2.append(", programSplicePlaybackPositionUs= ");
        return c.j(this.programSplicePlaybackPositionUs, " }", sb2);
    }
}
