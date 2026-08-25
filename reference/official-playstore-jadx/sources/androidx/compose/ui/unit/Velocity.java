package androidx.compose.ui.unit;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.g;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087@\u0018\u0000 22\u00020\u0001:\u00012B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b\u0010\u0010\u000bJ\u0010\u0010\u0011\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b\u0012\u0010\u000bJ'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0007H\u0087\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020 HÖ\u0001¢\u0006\u0004\b!\u0010\"J\u001b\u0010#\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0000H\u0087\u0002ø\u0001\u0001¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0000H\u0087\u0002ø\u0001\u0001¢\u0006\u0004\b'\u0010%J\u001e\u0010(\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0007H\u0087\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010\u0019J\u001e\u0010*\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0007H\u0087\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010\u0019J\u000f\u0010,\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/J\u0016\u00100\u001a\u00020\u0000H\u0087\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u0010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000b\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00063"}, d2 = {"Landroidx/compose/ui/unit/Velocity;", "", "packedValue", "", "constructor-impl", "(J)J", "x", "", "getX$annotations", "()V", "getX-impl", "(J)F", "y", "getY$annotations", "getY-impl", "component1", "component1-impl", "component2", "component2-impl", "copy", "copy-OhffZ5M", "(JFF)J", TtmlNode.TAG_DIV, "operand", "div-adjELrA", "(JF)J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "minus", "minus-AH228Gc", "(JJ)J", "plus", "plus-AH228Gc", "rem", "rem-adjELrA", "times", "times-adjELrA", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "unaryMinus", "unaryMinus-9UxMQ8M", "Companion", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
public final class Velocity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Zero = VelocityKt.Velocity(0.0f, 0.0f);
    private final long packedValue;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\t"}, d2 = {"Landroidx/compose/ui/unit/Velocity$Companion;", "", "()V", "Zero", "Landroidx/compose/ui/unit/Velocity;", "getZero-9UxMQ8M$annotations", "getZero-9UxMQ8M", "()J", "J", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getZero-9UxMQ8M$annotations, reason: not valid java name */
        public static /* synthetic */ void m5919getZero9UxMQ8M$annotations() {
        }

        /* JADX INFO: renamed from: getZero-9UxMQ8M, reason: not valid java name */
        public final long m5920getZero9UxMQ8M() {
            return Velocity.Zero;
        }

        private Companion() {
        }
    }

    private /* synthetic */ Velocity(long j10) {
        this.packedValue = j10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Velocity m5900boximpl(long j10) {
        return new Velocity(j10);
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final float m5901component1impl(long j10) {
        return m5909getXimpl(j10);
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final float m5902component2impl(long j10) {
        return m5910getYimpl(j10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m5903constructorimpl(long j10) {
        return j10;
    }

    /* JADX INFO: renamed from: copy-OhffZ5M, reason: not valid java name */
    public static final long m5904copyOhffZ5M(long j10, float f10, float f11) {
        return VelocityKt.Velocity(f10, f11);
    }

    /* JADX INFO: renamed from: copy-OhffZ5M$default, reason: not valid java name */
    public static /* synthetic */ long m5905copyOhffZ5M$default(long j10, float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = m5909getXimpl(j10);
        }
        if ((i10 & 2) != 0) {
            f11 = m5910getYimpl(j10);
        }
        return m5904copyOhffZ5M(j10, f10, f11);
    }

    /* JADX INFO: renamed from: div-adjELrA, reason: not valid java name */
    public static final long m5906divadjELrA(long j10, float f10) {
        return VelocityKt.Velocity(m5909getXimpl(j10) / f10, m5910getYimpl(j10) / f10);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5907equalsimpl(long j10, Object obj) {
        return (obj instanceof Velocity) && j10 == ((Velocity) obj).getPackedValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5908equalsimpl0(long j10, long j11) {
        return j10 == j11;
    }

    public static /* synthetic */ void getX$annotations() {
    }

    /* JADX INFO: renamed from: getX-impl, reason: not valid java name */
    public static final float m5909getXimpl(long j10) {
        return Float.intBitsToFloat((int) (j10 >> 32));
    }

    public static /* synthetic */ void getY$annotations() {
    }

    /* JADX INFO: renamed from: getY-impl, reason: not valid java name */
    public static final float m5910getYimpl(long j10) {
        return Float.intBitsToFloat((int) (j10 & 4294967295L));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5911hashCodeimpl(long j10) {
        return (int) (j10 ^ (j10 >>> 32));
    }

    /* JADX INFO: renamed from: minus-AH228Gc, reason: not valid java name */
    public static final long m5912minusAH228Gc(long j10, long j11) {
        return VelocityKt.Velocity(m5909getXimpl(j10) - m5909getXimpl(j11), m5910getYimpl(j10) - m5910getYimpl(j11));
    }

    /* JADX INFO: renamed from: plus-AH228Gc, reason: not valid java name */
    public static final long m5913plusAH228Gc(long j10, long j11) {
        return VelocityKt.Velocity(m5909getXimpl(j11) + m5909getXimpl(j10), m5910getYimpl(j11) + m5910getYimpl(j10));
    }

    /* JADX INFO: renamed from: rem-adjELrA, reason: not valid java name */
    public static final long m5914remadjELrA(long j10, float f10) {
        return VelocityKt.Velocity(m5909getXimpl(j10) % f10, m5910getYimpl(j10) % f10);
    }

    /* JADX INFO: renamed from: times-adjELrA, reason: not valid java name */
    public static final long m5915timesadjELrA(long j10, float f10) {
        return VelocityKt.Velocity(m5909getXimpl(j10) * f10, m5910getYimpl(j10) * f10);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5916toStringimpl(long j10) {
        return "(" + m5909getXimpl(j10) + ", " + m5910getYimpl(j10) + ") px/sec";
    }

    /* JADX INFO: renamed from: unaryMinus-9UxMQ8M, reason: not valid java name */
    public static final long m5917unaryMinus9UxMQ8M(long j10) {
        return VelocityKt.Velocity(-m5909getXimpl(j10), -m5910getYimpl(j10));
    }

    public boolean equals(Object obj) {
        return m5907equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m5911hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m5916toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getPackedValue() {
        return this.packedValue;
    }
}
