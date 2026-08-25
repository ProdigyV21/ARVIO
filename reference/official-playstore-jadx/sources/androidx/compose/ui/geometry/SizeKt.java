package androidx.compose.ui.geometry;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a%\u0010\n\u001a\u00020\u0003*\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u0086\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001f\u0010\u0015\u001a\u00020\u0003*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001f\u0010\u0015\u001a\u00020\u0003*\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0003H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0017\u001a\u0016\u0010\u001b\u001a\u00020\u0018*\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001f\u0010\u0015\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0003H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u001c\"\u001f\u0010\"\u001a\u00020\u001d*\u00020\u00038Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001f\"\u001f\u0010%\u001a\u00020\u001d*\u00020\u00038Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b$\u0010!\u001a\u0004\b#\u0010\u001f\"\u001e\u0010*\u001a\u00020&*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b)\u0010!\u001a\u0004\b'\u0010(\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"", "width", "height", "Landroidx/compose/ui/geometry/Size;", "Size", "(FF)J", "Lkotlin/Function0;", "block", "takeOrElse-TmRCtEA", "(JLr7/a;)J", "takeOrElse", TtmlNode.START, "stop", "fraction", "lerp-VgWVRYQ", "(JJF)J", "lerp", "", ContentDisposition.Parameters.Size, "times-d16Qtg0", "(IJ)J", "times", "", "(DJ)J", "Landroidx/compose/ui/geometry/Rect;", "toRect-uvyYCjk", "(J)Landroidx/compose/ui/geometry/Rect;", "toRect", "(FJ)J", "", "isSpecified-uvyYCjk", "(J)Z", "isSpecified-uvyYCjk$annotations", "(J)V", "isSpecified", "isUnspecified-uvyYCjk", "isUnspecified-uvyYCjk$annotations", "isUnspecified", "Landroidx/compose/ui/geometry/Offset;", "getCenter-uvyYCjk", "(J)J", "getCenter-uvyYCjk$annotations", TtmlNode.CENTER, "ui-geometry_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SizeKt {
    public static final long Size(float f10, float f11) {
        return Size.m3296constructorimpl((((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32));
    }

    /* JADX INFO: renamed from: getCenter-uvyYCjk, reason: not valid java name */
    public static final long m3315getCenteruvyYCjk(long j10) {
        return OffsetKt.Offset(Size.m3305getWidthimpl(j10) / 2.0f, Size.m3302getHeightimpl(j10) / 2.0f);
    }

    /* JADX INFO: renamed from: getCenter-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m3316getCenteruvyYCjk$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isSpecified-uvyYCjk, reason: not valid java name */
    public static final boolean m3317isSpecifieduvyYCjk(long j10) {
        return j10 != Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }

    /* JADX INFO: renamed from: isSpecified-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m3318isSpecifieduvyYCjk$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isUnspecified-uvyYCjk, reason: not valid java name */
    public static final boolean m3319isUnspecifieduvyYCjk(long j10) {
        return j10 == Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }

    /* JADX INFO: renamed from: isUnspecified-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m3320isUnspecifieduvyYCjk$annotations(long j10) {
    }

    /* JADX INFO: renamed from: lerp-VgWVRYQ, reason: not valid java name */
    public static final long m3321lerpVgWVRYQ(long j10, long j11, float f10) {
        return Size(MathHelpersKt.lerp(Size.m3305getWidthimpl(j10), Size.m3305getWidthimpl(j11), f10), MathHelpersKt.lerp(Size.m3302getHeightimpl(j10), Size.m3302getHeightimpl(j11), f10));
    }

    /* JADX INFO: renamed from: takeOrElse-TmRCtEA, reason: not valid java name */
    public static final long m3322takeOrElseTmRCtEA(long j10, a<Size> aVar) {
        return j10 != Size.INSTANCE.m3313getUnspecifiedNHjbRc() ? j10 : ((Size) aVar.invoke()).getPackedValue();
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m3325timesd16Qtg0(int i10, long j10) {
        return Size.m3308times7Ah8Wj8(j10, i10);
    }

    /* JADX INFO: renamed from: toRect-uvyYCjk, reason: not valid java name */
    public static final Rect m3326toRectuvyYCjk(long j10) {
        return RectKt.m3276Recttz77jQw(Offset.INSTANCE.m3252getZeroF1C5BW0(), j10);
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m3323timesd16Qtg0(double d4, long j10) {
        return Size.m3308times7Ah8Wj8(j10, (float) d4);
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m3324timesd16Qtg0(float f10, long j10) {
        return Size.m3308times7Ah8Wj8(j10, f10);
    }
}
