package androidx.media3.exoplayer.text;

import androidx.media3.common.C;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.base.m;
import com.google.common.collect.d3;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.g3;
import com.google.common.collect.h1;
import com.google.common.collect.m0;
import com.google.common.collect.o3;
import com.google.common.collect.y;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class MergingCuesResolver implements CuesResolver {
    private static final g3 CUES_DISPLAY_PRIORITY_COMPARATOR;
    private final List<CuesWithTiming> cuesWithTimingList = new ArrayList();

    static {
        final int i10 = 0;
        final int i11 = 1;
        CUES_DISPLAY_PRIORITY_COMPARATOR = new m0(new y(new m() { // from class: androidx.media3.exoplayer.text.a
            @Override // com.google.common.base.m
            public final Object apply(Object obj) {
                CuesWithTiming cuesWithTiming = (CuesWithTiming) obj;
                switch (i10) {
                    case 0:
                        return MergingCuesResolver.lambda$static$0(cuesWithTiming);
                    default:
                        return MergingCuesResolver.lambda$static$1(cuesWithTiming);
                }
            }
        }, d3.f13967l), new y(new m() { // from class: androidx.media3.exoplayer.text.a
            @Override // com.google.common.base.m
            public final Object apply(Object obj) {
                CuesWithTiming cuesWithTiming = (CuesWithTiming) obj;
                switch (i11) {
                    case 0:
                        return MergingCuesResolver.lambda$static$0(cuesWithTiming);
                    default:
                        return MergingCuesResolver.lambda$static$1(cuesWithTiming);
                }
            }
        }, d3.f13968m));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Long lambda$static$0(CuesWithTiming cuesWithTiming) {
        return Long.valueOf(cuesWithTiming.startTimeUs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Long lambda$static$1(CuesWithTiming cuesWithTiming) {
        return Long.valueOf(cuesWithTiming.durationUs);
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public boolean addCues(CuesWithTiming cuesWithTiming, long j10) {
        ac.b.j(cuesWithTiming.startTimeUs != C.TIME_UNSET);
        ac.b.j(cuesWithTiming.durationUs != C.TIME_UNSET);
        boolean z = cuesWithTiming.startTimeUs <= j10 && j10 < cuesWithTiming.endTimeUs;
        for (int size = this.cuesWithTimingList.size() - 1; size >= 0; size--) {
            if (cuesWithTiming.startTimeUs >= this.cuesWithTimingList.get(size).startTimeUs) {
                this.cuesWithTimingList.add(size + 1, cuesWithTiming);
                return z;
            }
        }
        this.cuesWithTimingList.add(0, cuesWithTiming);
        return z;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void clear() {
        this.cuesWithTimingList.clear();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void discardCuesBeforeTimeUs(long j10) {
        int i10 = 0;
        while (i10 < this.cuesWithTimingList.size()) {
            long j11 = this.cuesWithTimingList.get(i10).startTimeUs;
            if (j10 > j11 && j10 > this.cuesWithTimingList.get(i10).endTimeUs) {
                this.cuesWithTimingList.remove(i10);
                i10--;
            } else if (j10 < j11) {
                return;
            }
            i10++;
        }
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public h1 getCuesAtTimeUs(long j10) {
        if (!this.cuesWithTimingList.isEmpty()) {
            if (j10 >= this.cuesWithTimingList.get(0).startTimeUs) {
                ArrayList arrayList = new ArrayList();
                for (int i10 = 0; i10 < this.cuesWithTimingList.size(); i10++) {
                    CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i10);
                    if (j10 >= cuesWithTiming.startTimeUs && j10 < cuesWithTiming.endTimeUs) {
                        arrayList.add(cuesWithTiming);
                    }
                    if (j10 < cuesWithTiming.startTimeUs) {
                        break;
                    }
                }
                o3 o3VarU = h1.u(CUES_DISPLAY_PRIORITY_COMPARATOR, arrayList);
                e1 e1VarK = h1.k();
                for (int i11 = 0; i11 < o3VarU.f14080n; i11++) {
                    e1VarK.d(((CuesWithTiming) o3VarU.get(i11)).cues);
                }
                return e1VarK.f();
            }
        }
        f1 f1Var = h1.f14020l;
        return o3.f14078o;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getNextCueChangeTimeUs(long j10) {
        int i10 = 0;
        long jMin = -9223372036854775807L;
        while (true) {
            if (i10 >= this.cuesWithTimingList.size()) {
                break;
            }
            long j11 = this.cuesWithTimingList.get(i10).startTimeUs;
            long j12 = this.cuesWithTimingList.get(i10).endTimeUs;
            if (j10 < j11) {
                jMin = jMin == C.TIME_UNSET ? j11 : Math.min(jMin, j11);
            } else {
                if (j10 < j12) {
                    jMin = jMin == C.TIME_UNSET ? j12 : Math.min(jMin, j12);
                }
                i10++;
            }
        }
        if (jMin != C.TIME_UNSET) {
            return jMin;
        }
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getPreviousCueChangeTimeUs(long j10) {
        if (this.cuesWithTimingList.isEmpty()) {
            return C.TIME_UNSET;
        }
        if (j10 < this.cuesWithTimingList.get(0).startTimeUs) {
            return C.TIME_UNSET;
        }
        long jMax = this.cuesWithTimingList.get(0).startTimeUs;
        for (int i10 = 0; i10 < this.cuesWithTimingList.size(); i10++) {
            long j11 = this.cuesWithTimingList.get(i10).startTimeUs;
            long j12 = this.cuesWithTimingList.get(i10).endTimeUs;
            if (j12 > j10) {
                if (j11 > j10) {
                    break;
                }
                jMax = Math.max(jMax, j11);
            } else {
                jMax = Math.max(jMax, j12);
            }
        }
        return jMax;
    }
}
