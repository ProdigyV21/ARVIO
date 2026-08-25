package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.util.Util;
import com.google.common.collect.d3;
import com.google.common.collect.f1;
import com.google.common.collect.g3;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import com.google.common.collect.y;

/* JADX INFO: loaded from: classes3.dex */
final class CuesWithTimingSubtitle implements Subtitle {
    private static final g3 CUES_BY_START_TIME_ASCENDING = new y(new a(0), d3.f13967l);
    private static final String TAG = "CuesWithTimingSubtitle";
    private final h1 eventCues;
    private final long[] eventTimesUs;

    /* JADX WARN: Removed duplicated region for block: B:36:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CuesWithTimingSubtitle(java.util.List<androidx.media3.extractor.text.CuesWithTiming> r15) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.CuesWithTimingSubtitle.<init>(java.util.List):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Comparable lambda$static$0(CuesWithTiming cuesWithTiming) {
        return Long.valueOf(normalizeUnsetStartTimeToZero(cuesWithTiming.startTimeUs));
    }

    private static long normalizeUnsetStartTimeToZero(long j10) {
        if (j10 == C.TIME_UNSET) {
            return 0L;
        }
        return j10;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int i10) {
        ac.b.j(i10 < this.eventCues.size());
        return this.eventTimesUs[i10];
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return this.eventCues.size();
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long j10) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j10, false, false);
        if (iBinarySearchCeil < this.eventCues.size()) {
            return iBinarySearchCeil;
        }
        return -1;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public h1 getCues(long j10) {
        int iBinarySearchFloor = Util.binarySearchFloor(this.eventTimesUs, j10, true, false);
        if (iBinarySearchFloor != -1) {
            return (h1) this.eventCues.get(iBinarySearchFloor);
        }
        f1 f1Var = h1.f14020l;
        return o3.f14078o;
    }
}
