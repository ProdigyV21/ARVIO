package androidx.compose.ui.layout;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 *2\u00020\u0001:\u0001*B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\tH\u0087\n¢\u0006\u0004\b\u0011\u0010\fJ\u0010\u0010\u0012\u001a\u00020\tH\u0087\n¢\u0006\u0004\b\u0013\u0010\fJ'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\tH\u0087\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020!HÖ\u0001¢\u0006\u0004\b\"\u0010#J\u001e\u0010$\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\tH\u0087\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u001aJ\u000f\u0010&\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u0007\u001a\u0004\b\u000f\u0010\f\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/compose/ui/layout/ScaleFactor;", "", "packedValue", "", "constructor-impl", "(J)J", "getPackedValue$annotations", "()V", "scaleX", "", "getScaleX$annotations", "getScaleX-impl", "(J)F", "scaleY", "getScaleY$annotations", "getScaleY-impl", "component1", "component1-impl", "component2", "component2-impl", "copy", "copy-8GGzs04", "(JFF)J", TtmlNode.TAG_DIV, "operand", "div-44nBxM0", "(JF)J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "times", "times-44nBxM0", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
public final class ScaleFactor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Unspecified = ScaleFactorKt.ScaleFactor(Float.NaN, Float.NaN);
    private final long packedValue;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\t"}, d2 = {"Landroidx/compose/ui/layout/ScaleFactor$Companion;", "", "()V", "Unspecified", "Landroidx/compose/ui/layout/ScaleFactor;", "getUnspecified-_hLwfpc$annotations", "getUnspecified-_hLwfpc", "()J", "J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getUnspecified-_hLwfpc$annotations, reason: not valid java name */
        public static /* synthetic */ void m4694getUnspecified_hLwfpc$annotations() {
        }

        /* JADX INFO: renamed from: getUnspecified-_hLwfpc, reason: not valid java name */
        public final long m4695getUnspecified_hLwfpc() {
            return ScaleFactor.Unspecified;
        }

        private Companion() {
        }
    }

    private /* synthetic */ ScaleFactor(long j10) {
        this.packedValue = j10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ScaleFactor m4679boximpl(long j10) {
        return new ScaleFactor(j10);
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final float m4680component1impl(long j10) {
        return m4688getScaleXimpl(j10);
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final float m4681component2impl(long j10) {
        return m4689getScaleYimpl(j10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m4682constructorimpl(long j10) {
        return j10;
    }

    /* JADX INFO: renamed from: copy-8GGzs04, reason: not valid java name */
    public static final long m4683copy8GGzs04(long j10, float f10, float f11) {
        return ScaleFactorKt.ScaleFactor(f10, f11);
    }

    /* JADX INFO: renamed from: copy-8GGzs04$default, reason: not valid java name */
    public static /* synthetic */ long m4684copy8GGzs04$default(long j10, float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = m4688getScaleXimpl(j10);
        }
        if ((i10 & 2) != 0) {
            f11 = m4689getScaleYimpl(j10);
        }
        return m4683copy8GGzs04(j10, f10, f11);
    }

    /* JADX INFO: renamed from: div-44nBxM0, reason: not valid java name */
    public static final long m4685div44nBxM0(long j10, float f10) {
        return ScaleFactorKt.ScaleFactor(m4688getScaleXimpl(j10) / f10, m4689getScaleYimpl(j10) / f10);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4686equalsimpl(long j10, Object obj) {
        return (obj instanceof ScaleFactor) && j10 == ((ScaleFactor) obj).getPackedValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4687equalsimpl0(long j10, long j11) {
        return j10 == j11;
    }

    public static /* synthetic */ void getPackedValue$annotations() {
    }

    public static /* synthetic */ void getScaleX$annotations() {
    }

    /* JADX INFO: renamed from: getScaleX-impl, reason: not valid java name */
    public static final float m4688getScaleXimpl(long j10) {
        if (j10 != Unspecified) {
            return Float.intBitsToFloat((int) (j10 >> 32));
        }
        throw new IllegalStateException("ScaleFactor is unspecified");
    }

    public static /* synthetic */ void getScaleY$annotations() {
    }

    /* JADX INFO: renamed from: getScaleY-impl, reason: not valid java name */
    public static final float m4689getScaleYimpl(long j10) {
        if (j10 != Unspecified) {
            return Float.intBitsToFloat((int) (j10 & 4294967295L));
        }
        throw new IllegalStateException("ScaleFactor is unspecified");
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4690hashCodeimpl(long j10) {
        return (int) (j10 ^ (j10 >>> 32));
    }

    /* JADX INFO: renamed from: times-44nBxM0, reason: not valid java name */
    public static final long m4691times44nBxM0(long j10, float f10) {
        return ScaleFactorKt.ScaleFactor(m4688getScaleXimpl(j10) * f10, m4689getScaleYimpl(j10) * f10);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4692toStringimpl(long j10) {
        return "ScaleFactor(" + ScaleFactorKt.roundToTenths(m4688getScaleXimpl(j10)) + ", " + ScaleFactorKt.roundToTenths(m4689getScaleYimpl(j10)) + ')';
    }

    public boolean equals(Object obj) {
        return m4686equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m4690hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m4692toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getPackedValue() {
        return this.packedValue;
    }
}
