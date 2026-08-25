package androidx.media3.exoplayer.text;

import androidx.media3.common.C;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.a0;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
final class ReplacingCuesResolver implements CuesResolver {
    private final ArrayList<CuesWithTiming> cuesWithTimingList = new ArrayList<>();

    private int getIndexOfCuesStartingAfter(long j10) {
        for (int i10 = 0; i10 < this.cuesWithTimingList.size(); i10++) {
            if (j10 < this.cuesWithTimingList.get(i10).startTimeUs) {
                return i10;
            }
        }
        return this.cuesWithTimingList.size();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    @Override // androidx.media3.exoplayer.text.CuesResolver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean addCues(androidx.media3.extractor.text.CuesWithTiming r10, long r11) {
        /*
            r9 = this;
            long r0 = r10.startTimeUs
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 0
            r4 = 1
            if (r0 == 0) goto Lf
            r0 = r4
            goto L10
        Lf:
            r0 = r1
        L10:
            ac.b.j(r0)
            long r5 = r10.startTimeUs
            int r0 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r0 > 0) goto L25
            long r5 = r10.endTimeUs
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 == 0) goto L23
            int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r0 >= 0) goto L25
        L23:
            r0 = r4
            goto L26
        L25:
            r0 = r1
        L26:
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r2 = r9.cuesWithTimingList
            int r2 = r2.size()
            int r2 = r2 - r4
        L2d:
            if (r2 < 0) goto L58
            long r5 = r10.startTimeUs
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r3 = r9.cuesWithTimingList
            java.lang.Object r3 = r3.get(r2)
            androidx.media3.extractor.text.CuesWithTiming r3 = (androidx.media3.extractor.text.CuesWithTiming) r3
            long r7 = r3.startTimeUs
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 < 0) goto L46
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r11 = r9.cuesWithTimingList
            int r2 = r2 + r4
            r11.add(r2, r10)
            return r0
        L46:
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r3 = r9.cuesWithTimingList
            java.lang.Object r3 = r3.get(r2)
            androidx.media3.extractor.text.CuesWithTiming r3 = (androidx.media3.extractor.text.CuesWithTiming) r3
            long r5 = r3.startTimeUs
            int r3 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r3 > 0) goto L55
            r0 = r1
        L55:
            int r2 = r2 + (-1)
            goto L2d
        L58:
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r11 = r9.cuesWithTimingList
            r11.add(r1, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.text.ReplacingCuesResolver.addCues(androidx.media3.extractor.text.CuesWithTiming, long):boolean");
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void clear() {
        this.cuesWithTimingList.clear();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void discardCuesBeforeTimeUs(long j10) {
        int indexOfCuesStartingAfter = getIndexOfCuesStartingAfter(j10);
        if (indexOfCuesStartingAfter == 0) {
            return;
        }
        long j11 = this.cuesWithTimingList.get(indexOfCuesStartingAfter - 1).endTimeUs;
        if (j11 == C.TIME_UNSET || j11 >= j10) {
            indexOfCuesStartingAfter--;
        }
        this.cuesWithTimingList.subList(0, indexOfCuesStartingAfter).clear();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public h1 getCuesAtTimeUs(long j10) {
        int indexOfCuesStartingAfter = getIndexOfCuesStartingAfter(j10);
        if (indexOfCuesStartingAfter == 0) {
            f1 f1Var = h1.f14020l;
            return o3.f14078o;
        }
        CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(indexOfCuesStartingAfter - 1);
        long j11 = cuesWithTiming.endTimeUs;
        if (j11 == C.TIME_UNSET || j10 < j11) {
            return cuesWithTiming.cues;
        }
        f1 f1Var2 = h1.f14020l;
        return o3.f14078o;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getNextCueChangeTimeUs(long j10) {
        if (this.cuesWithTimingList.isEmpty()) {
            return Long.MIN_VALUE;
        }
        if (j10 < this.cuesWithTimingList.get(0).startTimeUs) {
            return this.cuesWithTimingList.get(0).startTimeUs;
        }
        for (int i10 = 1; i10 < this.cuesWithTimingList.size(); i10++) {
            CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i10);
            if (j10 < cuesWithTiming.startTimeUs) {
                long j11 = this.cuesWithTimingList.get(i10 - 1).endTimeUs;
                return (j11 == C.TIME_UNSET || j11 <= j10 || j11 >= cuesWithTiming.startTimeUs) ? cuesWithTiming.startTimeUs : j11;
            }
        }
        long j12 = ((CuesWithTiming) a0.j(this.cuesWithTimingList)).endTimeUs;
        if (j12 == C.TIME_UNSET || j10 >= j12) {
            return Long.MIN_VALUE;
        }
        return j12;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getPreviousCueChangeTimeUs(long j10) {
        if (this.cuesWithTimingList.isEmpty() || j10 < this.cuesWithTimingList.get(0).startTimeUs) {
            return C.TIME_UNSET;
        }
        for (int i10 = 1; i10 < this.cuesWithTimingList.size(); i10++) {
            long j11 = this.cuesWithTimingList.get(i10).startTimeUs;
            if (j10 == j11) {
                return j11;
            }
            if (j10 < j11) {
                CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i10 - 1);
                long j12 = cuesWithTiming.endTimeUs;
                return (j12 == C.TIME_UNSET || j12 > j10) ? cuesWithTiming.startTimeUs : j12;
            }
        }
        CuesWithTiming cuesWithTiming2 = (CuesWithTiming) a0.j(this.cuesWithTimingList);
        long j13 = cuesWithTiming2.endTimeUs;
        return (j13 == C.TIME_UNSET || j10 < j13) ? cuesWithTiming2.startTimeUs : j13;
    }
}
