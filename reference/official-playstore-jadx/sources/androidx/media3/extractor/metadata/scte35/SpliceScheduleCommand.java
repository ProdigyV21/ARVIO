package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class SpliceScheduleCommand extends SpliceCommand {
    public final List<Event> events;

    public static final class ComponentSplice {
        public final int componentTag;
        public final long utcSpliceTime;

        private ComponentSplice(int i10, long j10) {
            this.componentTag = i10;
            this.utcSpliceTime = j10;
        }
    }

    public static final class Event {
        public final boolean autoReturn;
        public final int availNum;
        public final int availsExpected;
        public final long breakDurationUs;
        public final List<ComponentSplice> componentSpliceList;
        public final boolean outOfNetworkIndicator;
        public final boolean programSpliceFlag;
        public final boolean spliceEventCancelIndicator;
        public final long spliceEventId;
        public final int uniqueProgramId;
        public final long utcSpliceTime;

        private Event(long j10, boolean z, boolean z5, boolean z10, List<ComponentSplice> list, long j11, boolean z11, long j12, int i10, int i11, int i12) {
            this.spliceEventId = j10;
            this.spliceEventCancelIndicator = z;
            this.outOfNetworkIndicator = z5;
            this.programSpliceFlag = z10;
            this.componentSpliceList = Collections.unmodifiableList(list);
            this.utcSpliceTime = j11;
            this.autoReturn = z11;
            this.breakDurationUs = j12;
            this.uniqueProgramId = i10;
            this.availNum = i11;
            this.availsExpected = i12;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Event parseFromSection(ParsableByteArray parsableByteArray) {
            boolean z;
            ArrayList arrayList;
            boolean z5;
            boolean z10;
            long j10;
            boolean z11;
            long j11;
            int i10;
            int i11;
            int unsignedByte;
            boolean z12;
            long unsignedInt;
            long unsignedInt2 = parsableByteArray.readUnsignedInt();
            boolean z13 = true;
            if ((parsableByteArray.readUnsignedByte() & 128) != 0) {
                z = true;
            } else {
                z = true;
                z13 = false;
            }
            ArrayList arrayList2 = new ArrayList();
            if (z13) {
                arrayList = arrayList2;
                z5 = false;
                z10 = false;
                j10 = C.TIME_UNSET;
                z11 = false;
                j11 = C.TIME_UNSET;
                i10 = 0;
                i11 = 0;
                unsignedByte = 0;
            } else {
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                boolean z14 = (unsignedByte2 & 128) != 0 ? z : false;
                boolean z15 = (unsignedByte2 & 64) != 0 ? z : false;
                boolean z16 = (unsignedByte2 & 32) != 0 ? z : false;
                long unsignedInt3 = z15 ? parsableByteArray.readUnsignedInt() : C.TIME_UNSET;
                if (!z15) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    ArrayList arrayList3 = new ArrayList(unsignedByte3);
                    int i12 = 0;
                    while (i12 < unsignedByte3) {
                        arrayList3.add(new ComponentSplice(parsableByteArray.readUnsignedByte(), parsableByteArray.readUnsignedInt()));
                        i12++;
                        unsignedByte3 = unsignedByte3;
                    }
                    arrayList2 = arrayList3;
                }
                if (z16) {
                    long unsignedByte4 = parsableByteArray.readUnsignedByte();
                    boolean z17 = (128 & unsignedByte4) != 0;
                    unsignedInt = ((((unsignedByte4 & 1) << 32) | parsableByteArray.readUnsignedInt()) * 1000) / 90;
                    z12 = z17;
                } else {
                    z12 = false;
                    unsignedInt = C.TIME_UNSET;
                }
                int unsignedShort = parsableByteArray.readUnsignedShort();
                int unsignedByte5 = parsableByteArray.readUnsignedByte();
                boolean z18 = z14;
                z11 = z12;
                z5 = z18;
                unsignedByte = parsableByteArray.readUnsignedByte();
                long j12 = unsignedInt3;
                i10 = unsignedShort;
                i11 = unsignedByte5;
                long j13 = unsignedInt;
                arrayList = arrayList2;
                z10 = z15;
                j10 = j12;
                j11 = j13;
            }
            return new Event(unsignedInt2, z13, z5, z10, arrayList, j10, z11, j11, i10, i11, unsignedByte);
        }
    }

    private SpliceScheduleCommand(List<Event> list) {
        this.events = Collections.unmodifiableList(list);
    }

    public static SpliceScheduleCommand parseFromSection(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        ArrayList arrayList = new ArrayList(unsignedByte);
        for (int i10 = 0; i10 < unsignedByte; i10++) {
            arrayList.add(Event.parseFromSection(parsableByteArray));
        }
        return new SpliceScheduleCommand(arrayList);
    }
}
