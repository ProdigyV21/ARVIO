package androidx.compose.material3;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0081@\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0006\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\t\u001a\u0004\b\u0010\u0010\u000b\u0088\u0001\u0002¨\u0006\u001f"}, d2 = {"Landroidx/compose/material3/SliderRange;", "", "packedValue", "", "constructor-impl", "(J)J", "endInclusive", "", "getEndInclusive$annotations", "()V", "getEndInclusive-impl", "(J)F", "getPackedValue", "()J", TtmlNode.START, "getStart$annotations", "getStart-impl", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
public final class SliderRange {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Unspecified = SliderKt.SliderRange(Float.NaN, Float.NaN);
    private final long packedValue;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\t"}, d2 = {"Landroidx/compose/material3/SliderRange$Companion;", "", "()V", "Unspecified", "Landroidx/compose/material3/SliderRange;", "getUnspecified-FYbKRX4$annotations", "getUnspecified-FYbKRX4", "()J", "J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getUnspecified-FYbKRX4$annotations, reason: not valid java name */
        public static /* synthetic */ void m1910getUnspecifiedFYbKRX4$annotations() {
        }

        /* JADX INFO: renamed from: getUnspecified-FYbKRX4, reason: not valid java name */
        public final long m1911getUnspecifiedFYbKRX4() {
            return SliderRange.Unspecified;
        }

        private Companion() {
        }
    }

    private /* synthetic */ SliderRange(long j10) {
        this.packedValue = j10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SliderRange m1901boximpl(long j10) {
        return new SliderRange(j10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1902constructorimpl(long j10) {
        return j10;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1903equalsimpl(long j10, Object obj) {
        return (obj instanceof SliderRange) && j10 == ((SliderRange) obj).m1909unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1904equalsimpl0(long j10, long j11) {
        return j10 == j11;
    }

    public static /* synthetic */ void getEndInclusive$annotations() {
    }

    /* JADX INFO: renamed from: getEndInclusive-impl, reason: not valid java name */
    public static final float m1905getEndInclusiveimpl(long j10) {
        if (j10 != Unspecified) {
            return Float.intBitsToFloat((int) (j10 & 4294967295L));
        }
        throw new IllegalStateException("SliderRange is unspecified");
    }

    public static /* synthetic */ void getStart$annotations() {
    }

    /* JADX INFO: renamed from: getStart-impl, reason: not valid java name */
    public static final float m1906getStartimpl(long j10) {
        if (j10 != Unspecified) {
            return Float.intBitsToFloat((int) (j10 >> 32));
        }
        throw new IllegalStateException("SliderRange is unspecified");
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1907hashCodeimpl(long j10) {
        return (int) (j10 ^ (j10 >>> 32));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1908toStringimpl(long j10) {
        if (!SliderKt.m1893isSpecifiedIf1S1O4(j10)) {
            return "FloatRange.Unspecified";
        }
        return m1906getStartimpl(j10) + ".." + m1905getEndInclusiveimpl(j10);
    }

    public boolean equals(Object obj) {
        return m1903equalsimpl(this.packedValue, obj);
    }

    public final long getPackedValue() {
        return this.packedValue;
    }

    public int hashCode() {
        return m1907hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m1908toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m1909unboximpl() {
        return this.packedValue;
    }
}
