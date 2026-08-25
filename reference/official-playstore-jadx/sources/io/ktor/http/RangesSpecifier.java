package io.ktor.http;

import androidx.compose.foundation.c;
import io.ktor.http.ContentRange;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\t\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\nJ!\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u000b*\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0011\u001a\u00020\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00042\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00042\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u001aJ\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b \u0010!J*\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010'\u001a\u00020\u000f2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010\u001eR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010+\u001a\u0004\b,\u0010!¨\u0006-"}, d2 = {"Lio/ktor/http/RangesSpecifier;", "", "", "unit", "", "Lio/ktor/http/ContentRange;", "ranges", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "Lio/ktor/http/RangeUnits;", "(Lio/ktor/http/RangeUnits;Ljava/util/List;)V", "T", "toList", "(Ljava/lang/Object;)Ljava/util/List;", "Lkotlin/Function1;", "", "rangeUnitPredicate", "isValid", "(Lr7/l;)Z", "", "length", "", "maxRangeCount", "Lx7/l;", "merge", "(JI)Ljava/util/List;", "(J)Ljava/util/List;", "mergeToSingle", "(J)Lx7/l;", "toString", "()Ljava/lang/String;", "component1", "component2", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/util/List;)Lio/ktor/http/RangesSpecifier;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getUnit", "Ljava/util/List;", "getRanges", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class RangesSpecifier {
    private final List<ContentRange> ranges;
    private final String unit;

    /* JADX INFO: renamed from: io.ktor.http.RangesSpecifier$isValid$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/String;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<String, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(String str) {
            return Boolean.valueOf(str.equals(RangeUnits.Bytes.getUnitToken()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RangesSpecifier(String str, List<? extends ContentRange> list) {
        this.unit = str;
        this.ranges = list;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("It should be at least one range");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RangesSpecifier copy$default(RangesSpecifier rangesSpecifier, String str, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = rangesSpecifier.unit;
        }
        if ((i10 & 2) != 0) {
            list = rangesSpecifier.ranges;
        }
        return rangesSpecifier.copy(str, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean isValid$default(RangesSpecifier rangesSpecifier, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return rangesSpecifier.isValid(lVar);
    }

    public static /* synthetic */ List merge$default(RangesSpecifier rangesSpecifier, long j10, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 50;
        }
        return rangesSpecifier.merge(j10, i10);
    }

    private final <T> List<T> toList(T t2) {
        return t2 == null ? z.f19728i : Collections.singletonList(t2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUnit() {
        return this.unit;
    }

    public final List<ContentRange> component2() {
        return this.ranges;
    }

    public final RangesSpecifier copy(String unit, List<? extends ContentRange> ranges) {
        return new RangesSpecifier(unit, ranges);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RangesSpecifier)) {
            return false;
        }
        RangesSpecifier rangesSpecifier = (RangesSpecifier) other;
        return p.a(this.unit, rangesSpecifier.unit) && p.a(this.ranges, rangesSpecifier.ranges);
    }

    public final List<ContentRange> getRanges() {
        return this.ranges;
    }

    public final String getUnit() {
        return this.unit;
    }

    public int hashCode() {
        return this.ranges.hashCode() + (this.unit.hashCode() * 31);
    }

    public final boolean isValid(l<? super String, Boolean> rangeUnitPredicate) {
        if (!((Boolean) rangeUnitPredicate.invoke(this.unit)).booleanValue()) {
            return false;
        }
        List<ContentRange> list = this.ranges;
        if (list != null && list.isEmpty()) {
            return true;
        }
        for (ContentRange contentRange : list) {
            if (contentRange instanceof ContentRange.Bounded) {
                ContentRange.Bounded bounded = (ContentRange.Bounded) contentRange;
                if (bounded.getFrom() < 0 || bounded.getTo() < bounded.getFrom()) {
                    return false;
                }
            } else if (contentRange instanceof ContentRange.TailFrom) {
                if (((ContentRange.TailFrom) contentRange).getFrom() < 0) {
                    return false;
                }
            } else {
                if (!(contentRange instanceof ContentRange.Suffix)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (((ContentRange.Suffix) contentRange).getLastCount() < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public final List<x7.l> merge(long length, int maxRangeCount) {
        return this.ranges.size() > maxRangeCount ? toList(mergeToSingle(length)) : merge(length);
    }

    public final x7.l mergeToSingle(long length) {
        Object next;
        List<x7.l> longRanges = RangesKt.toLongRanges(this.ranges, length);
        Object next2 = null;
        if (longRanges.isEmpty()) {
            return null;
        }
        Iterator<T> it = longRanges.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                long j10 = ((x7.l) next).f22627i;
                do {
                    Object next3 = it.next();
                    long j11 = ((x7.l) next3).f22627i;
                    if (j10 > j11) {
                        next = next3;
                        j10 = j11;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        long j12 = ((x7.l) next).f22627i;
        Iterator<T> it2 = longRanges.iterator();
        if (it2.hasNext()) {
            next2 = it2.next();
            if (it2.hasNext()) {
                long j13 = ((x7.l) next2).f22628l;
                do {
                    Object next4 = it2.next();
                    long j14 = ((x7.l) next4).f22628l;
                    if (j13 < j14) {
                        next2 = next4;
                        j13 = j14;
                    }
                } while (it2.hasNext());
            }
        }
        long j15 = ((x7.l) next2).f22628l;
        long j16 = length - 1;
        if (j15 > j16) {
            j15 = j16;
        }
        return new x7.l(j12, j15);
    }

    public String toString() {
        return x.u0(this.ranges, ",", c.u(new StringBuilder(), this.unit, '='), null, null, 60);
    }

    public /* synthetic */ RangesSpecifier(String str, List list, int i10, h hVar) {
        this((i10 & 1) != 0 ? RangeUnits.Bytes.getUnitToken() : str, (List<? extends ContentRange>) list);
    }

    public RangesSpecifier(RangeUnits rangeUnits, List<? extends ContentRange> list) {
        this(rangeUnits.getUnitToken(), list);
    }

    public final List<x7.l> merge(long length) {
        return RangesKt.mergeRangesKeepOrder(RangesKt.toLongRanges(this.ranges, length));
    }
}
