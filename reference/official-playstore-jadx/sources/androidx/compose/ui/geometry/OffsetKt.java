package androidx.compose.ui.geometry;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a*\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a%\u0010\u0010\u001a\u00020\u0003*\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\"\u001e\u0010\u0016\u001a\u00020\u0011*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\"\u001e\u0010\u0019\u001a\u00020\u0011*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0017\u0010\u0013\"\u001e\u0010\u001c\u001a\u00020\u0011*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u0015\u001a\u0004\b\u001a\u0010\u0013\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"", "x", "y", "Landroidx/compose/ui/geometry/Offset;", "Offset", "(FF)J", TtmlNode.START, "stop", "fraction", "lerp-Wko1d7g", "(JJF)J", "lerp", "Lkotlin/Function0;", "block", "takeOrElse-3MmeM6k", "(JLr7/a;)J", "takeOrElse", "", "isFinite-k-4lQ0M", "(J)Z", "isFinite-k-4lQ0M$annotations", "(J)V", "isFinite", "isSpecified-k-4lQ0M", "isSpecified-k-4lQ0M$annotations", "isSpecified", "isUnspecified-k-4lQ0M", "isUnspecified-k-4lQ0M$annotations", "isUnspecified", "ui-geometry_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OffsetKt {
    public static final long Offset(float f10, float f11) {
        return Offset.m3228constructorimpl((((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32));
    }

    /* JADX INFO: renamed from: isFinite-k-4lQ0M, reason: not valid java name */
    public static final boolean m3253isFinitek4lQ0M(long j10) {
        float fM3236getXimpl = Offset.m3236getXimpl(j10);
        if (Float.isInfinite(fM3236getXimpl) || Float.isNaN(fM3236getXimpl)) {
            return false;
        }
        float fM3237getYimpl = Offset.m3237getYimpl(j10);
        return (Float.isInfinite(fM3237getYimpl) || Float.isNaN(fM3237getYimpl)) ? false : true;
    }

    /* JADX INFO: renamed from: isFinite-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m3254isFinitek4lQ0M$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isSpecified-k-4lQ0M, reason: not valid java name */
    public static final boolean m3255isSpecifiedk4lQ0M(long j10) {
        return j10 != Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: isSpecified-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m3256isSpecifiedk4lQ0M$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isUnspecified-k-4lQ0M, reason: not valid java name */
    public static final boolean m3257isUnspecifiedk4lQ0M(long j10) {
        return j10 == Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: isUnspecified-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m3258isUnspecifiedk4lQ0M$annotations(long j10) {
    }

    /* JADX INFO: renamed from: lerp-Wko1d7g, reason: not valid java name */
    public static final long m3259lerpWko1d7g(long j10, long j11, float f10) {
        return Offset(MathHelpersKt.lerp(Offset.m3236getXimpl(j10), Offset.m3236getXimpl(j11), f10), MathHelpersKt.lerp(Offset.m3237getYimpl(j10), Offset.m3237getYimpl(j11), f10));
    }

    /* JADX INFO: renamed from: takeOrElse-3MmeM6k, reason: not valid java name */
    public static final long m3260takeOrElse3MmeM6k(long j10, a<Offset> aVar) {
        return m3255isSpecifiedk4lQ0M(j10) ? j10 : ((Offset) aVar.invoke()).getPackedValue();
    }
}
