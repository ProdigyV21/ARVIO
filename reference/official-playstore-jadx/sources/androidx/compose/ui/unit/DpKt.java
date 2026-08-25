package androidx.compose.ui.unit;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import qb.d;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\n\u001a%\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0001H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001f\u0010\n\u001a\u00020\u0000*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\n\u001a\u00020\u0000*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\b\u0010\f\u001a\u001f\u0010\n\u001a\u00020\u0000*\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\b\u0010\u000e\u001a#\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u001a#\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\t\u001a'\u0010\u0019\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001f\u0010\u001b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\t\u001a\u001f\u0010\u001d\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\t\u001a*\u0010\"\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\u0018\u001a\"\u0010(\u001a\u00020%2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a%\u0010\u0005\u001a\u00020%*\u00020%2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020%0\u0001H\u0086\bø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a*\u0010\"\u001a\u00020%2\u0006\u0010\u001e\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020%2\u0006\u0010 \u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\"\u00101\u001a\u00020/2\u0006\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u0010'\u001a%\u0010\u0005\u001a\u00020/*\u00020/2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020/0\u0001H\u0086\bø\u0001\u0000¢\u0006\u0004\b2\u0010*\u001a\u001f\u0010\n\u001a\u00020/*\u00020\r2\u0006\u00103\u001a\u00020/H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a\u001f\u0010\n\u001a\u00020/*\u00020\u00062\u0006\u00103\u001a\u00020/H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u00106\u001a*\u0010\"\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020/2\u0006\u0010\u001f\u001a\u00020/2\u0006\u0010 \u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b7\u0010,\"\u001f\u0010=\u001a\u000208*\u00020\u00008Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b;\u0010<\u001a\u0004\b9\u0010:\"\u001f\u0010@\u001a\u000208*\u00020\u00008Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b?\u0010<\u001a\u0004\b>\u0010:\"\u001f\u0010E\u001a\u00020\u0000*\u00020\r8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bC\u0010D\u001a\u0004\bA\u0010B\"\u001f\u0010E\u001a\u00020\u0000*\u00020\u000b8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bC\u0010G\u001a\u0004\bA\u0010F\"\u001f\u0010E\u001a\u00020\u0000*\u00020\u00068Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bC\u0010<\u001a\u0004\bA\u0010H\"\u001f\u0010K\u001a\u000208*\u00020\u00008Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bJ\u0010<\u001a\u0004\bI\u0010:\"\u001f\u0010=\u001a\u000208*\u00020%8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bN\u0010O\u001a\u0004\bL\u0010M\"\u001f\u0010@\u001a\u000208*\u00020%8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bQ\u0010O\u001a\u0004\bP\u0010M\"\u001f\u0010=\u001a\u000208*\u00020/8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bS\u0010O\u001a\u0004\bR\u0010M\"\u001f\u0010@\u001a\u000208*\u00020/8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bU\u0010O\u001a\u0004\bT\u0010M\"\u001e\u0010Y\u001a\u00020%*\u00020/8FX\u0087\u0004¢\u0006\f\u0012\u0004\bX\u0010O\u001a\u0004\bV\u0010W\"\u001f\u0010-\u001a\u00020\u0000*\u00020Z8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b]\u0010^\u001a\u0004\b[\u0010\\\"\u001f\u0010.\u001a\u00020\u0000*\u00020Z8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b`\u0010^\u001a\u0004\b_\u0010\\\"\u001f\u00103\u001a\u00020/*\u00020Z8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bc\u0010^\u001a\u0004\ba\u0010b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006d"}, d2 = {"Landroidx/compose/ui/unit/Dp;", "Lkotlin/Function0;", "block", "takeOrElse-D5KLDUw", "(FLr7/a;)F", "takeOrElse", "", "other", "times-3ABfNKs", "(FF)F", "times", "", "(DF)F", "", "(IF)F", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "min-YgX7TsA", "min", "max-YgX7TsA", "max", "minimumValue", "maximumValue", "coerceIn-2z7ARbQ", "(FFF)F", "coerceIn", "coerceAtLeast-YgX7TsA", "coerceAtLeast", "coerceAtMost-YgX7TsA", "coerceAtMost", TtmlNode.START, "stop", "fraction", "lerp-Md-fbLM", "lerp", "x", "y", "Landroidx/compose/ui/unit/DpOffset;", "DpOffset-YgX7TsA", "(FF)J", "DpOffset", "takeOrElse-gVKV90s", "(JLr7/a;)J", "lerp-xhh869w", "(JJF)J", "width", "height", "Landroidx/compose/ui/unit/DpSize;", "DpSize-YgX7TsA", "DpSize", "takeOrElse-itqla9I", ContentDisposition.Parameters.Size, "times-6HolHcs", "(IJ)J", "(FJ)J", "lerp-IDex15A", "", "isSpecified-0680j_4", "(F)Z", "isSpecified-0680j_4$annotations", "(F)V", "isSpecified", "isUnspecified-0680j_4", "isUnspecified-0680j_4$annotations", "isUnspecified", "getDp", "(I)F", "getDp$annotations", "(I)V", "dp", "(D)F", "(D)V", "(F)F", "isFinite-0680j_4", "isFinite-0680j_4$annotations", "isFinite", "isSpecified-jo-Fl9I", "(J)Z", "isSpecified-jo-Fl9I$annotations", "(J)V", "isUnspecified-jo-Fl9I", "isUnspecified-jo-Fl9I$annotations", "isSpecified-EaSLcWc", "isSpecified-EaSLcWc$annotations", "isUnspecified-EaSLcWc", "isUnspecified-EaSLcWc$annotations", "getCenter-EaSLcWc", "(J)J", "getCenter-EaSLcWc$annotations", TtmlNode.CENTER, "Landroidx/compose/ui/unit/DpRect;", "getWidth", "(Landroidx/compose/ui/unit/DpRect;)F", "getWidth$annotations", "(Landroidx/compose/ui/unit/DpRect;)V", "getHeight", "getHeight$annotations", "getSize", "(Landroidx/compose/ui/unit/DpRect;)J", "getSize$annotations", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DpKt {
    /* JADX INFO: renamed from: DpOffset-YgX7TsA, reason: not valid java name */
    public static final long m5699DpOffsetYgX7TsA(float f10, float f11) {
        return DpOffset.m5734constructorimpl((((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32));
    }

    /* JADX INFO: renamed from: DpSize-YgX7TsA, reason: not valid java name */
    public static final long m5700DpSizeYgX7TsA(float f10, float f11) {
        return DpSize.m5767constructorimpl((((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32));
    }

    /* JADX INFO: renamed from: coerceAtLeast-YgX7TsA, reason: not valid java name */
    public static final float m5701coerceAtLeastYgX7TsA(float f10, float f11) {
        if (f10 < f11) {
            f10 = f11;
        }
        return Dp.m5678constructorimpl(f10);
    }

    /* JADX INFO: renamed from: coerceAtMost-YgX7TsA, reason: not valid java name */
    public static final float m5702coerceAtMostYgX7TsA(float f10, float f11) {
        if (f10 > f11) {
            f10 = f11;
        }
        return Dp.m5678constructorimpl(f10);
    }

    /* JADX INFO: renamed from: coerceIn-2z7ARbQ, reason: not valid java name */
    public static final float m5703coerceIn2z7ARbQ(float f10, float f11, float f12) {
        return Dp.m5678constructorimpl(d.m(f10, f11, f12));
    }

    /* JADX INFO: renamed from: getCenter-EaSLcWc, reason: not valid java name */
    public static final long m5704getCenterEaSLcWc(long j10) {
        return m5699DpOffsetYgX7TsA(Dp.m5678constructorimpl(DpSize.m5776getWidthD9Ej5fM(j10) / 2.0f), Dp.m5678constructorimpl(DpSize.m5774getHeightD9Ej5fM(j10) / 2.0f));
    }

    /* JADX INFO: renamed from: getCenter-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m5705getCenterEaSLcWc$annotations(long j10) {
    }

    public static final float getDp(int i10) {
        return Dp.m5678constructorimpl(i10);
    }

    public static /* synthetic */ void getDp$annotations(double d4) {
    }

    public static final float getHeight(DpRect dpRect) {
        return Dp.m5678constructorimpl(dpRect.m5760getBottomD9Ej5fM() - dpRect.m5763getTopD9Ej5fM());
    }

    public static /* synthetic */ void getHeight$annotations(DpRect dpRect) {
    }

    public static final long getSize(DpRect dpRect) {
        return m5700DpSizeYgX7TsA(Dp.m5678constructorimpl(dpRect.m5762getRightD9Ej5fM() - dpRect.m5761getLeftD9Ej5fM()), Dp.m5678constructorimpl(dpRect.m5760getBottomD9Ej5fM() - dpRect.m5763getTopD9Ej5fM()));
    }

    public static /* synthetic */ void getSize$annotations(DpRect dpRect) {
    }

    public static final float getWidth(DpRect dpRect) {
        return Dp.m5678constructorimpl(dpRect.m5762getRightD9Ej5fM() - dpRect.m5761getLeftD9Ej5fM());
    }

    public static /* synthetic */ void getWidth$annotations(DpRect dpRect) {
    }

    /* JADX INFO: renamed from: isFinite-0680j_4, reason: not valid java name */
    public static final boolean m5706isFinite0680j_4(float f10) {
        return !(f10 == Float.POSITIVE_INFINITY);
    }

    /* JADX INFO: renamed from: isFinite-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m5707isFinite0680j_4$annotations(float f10) {
    }

    /* JADX INFO: renamed from: isSpecified-0680j_4, reason: not valid java name */
    public static final boolean m5708isSpecified0680j_4(float f10) {
        return !Float.isNaN(f10);
    }

    /* JADX INFO: renamed from: isSpecified-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m5709isSpecified0680j_4$annotations(float f10) {
    }

    /* JADX INFO: renamed from: isSpecified-EaSLcWc, reason: not valid java name */
    public static final boolean m5710isSpecifiedEaSLcWc(long j10) {
        return j10 != DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ();
    }

    /* JADX INFO: renamed from: isSpecified-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m5711isSpecifiedEaSLcWc$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isSpecified-jo-Fl9I, reason: not valid java name */
    public static final boolean m5712isSpecifiedjoFl9I(long j10) {
        return j10 != DpOffset.INSTANCE.m5748getUnspecifiedRKDOV3M();
    }

    /* JADX INFO: renamed from: isSpecified-jo-Fl9I$annotations, reason: not valid java name */
    public static /* synthetic */ void m5713isSpecifiedjoFl9I$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isUnspecified-0680j_4, reason: not valid java name */
    public static final boolean m5714isUnspecified0680j_4(float f10) {
        return Float.isNaN(f10);
    }

    /* JADX INFO: renamed from: isUnspecified-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m5715isUnspecified0680j_4$annotations(float f10) {
    }

    /* JADX INFO: renamed from: isUnspecified-EaSLcWc, reason: not valid java name */
    public static final boolean m5716isUnspecifiedEaSLcWc(long j10) {
        return j10 == DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ();
    }

    /* JADX INFO: renamed from: isUnspecified-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m5717isUnspecifiedEaSLcWc$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isUnspecified-jo-Fl9I, reason: not valid java name */
    public static final boolean m5718isUnspecifiedjoFl9I(long j10) {
        return j10 == DpOffset.INSTANCE.m5748getUnspecifiedRKDOV3M();
    }

    /* JADX INFO: renamed from: isUnspecified-jo-Fl9I$annotations, reason: not valid java name */
    public static /* synthetic */ void m5719isUnspecifiedjoFl9I$annotations(long j10) {
    }

    /* JADX INFO: renamed from: lerp-IDex15A, reason: not valid java name */
    public static final long m5720lerpIDex15A(long j10, long j11, float f10) {
        return m5700DpSizeYgX7TsA(m5721lerpMdfbLM(DpSize.m5776getWidthD9Ej5fM(j10), DpSize.m5776getWidthD9Ej5fM(j11), f10), m5721lerpMdfbLM(DpSize.m5774getHeightD9Ej5fM(j10), DpSize.m5774getHeightD9Ej5fM(j11), f10));
    }

    /* JADX INFO: renamed from: lerp-Md-fbLM, reason: not valid java name */
    public static final float m5721lerpMdfbLM(float f10, float f11, float f12) {
        return Dp.m5678constructorimpl(MathHelpersKt.lerp(f10, f11, f12));
    }

    /* JADX INFO: renamed from: lerp-xhh869w, reason: not valid java name */
    public static final long m5722lerpxhh869w(long j10, long j11, float f10) {
        return m5699DpOffsetYgX7TsA(m5721lerpMdfbLM(DpOffset.m5739getXD9Ej5fM(j10), DpOffset.m5739getXD9Ej5fM(j11), f10), m5721lerpMdfbLM(DpOffset.m5741getYD9Ej5fM(j10), DpOffset.m5741getYD9Ej5fM(j11), f10));
    }

    /* JADX INFO: renamed from: max-YgX7TsA, reason: not valid java name */
    public static final float m5723maxYgX7TsA(float f10, float f11) {
        return Dp.m5678constructorimpl(Math.max(f10, f11));
    }

    /* JADX INFO: renamed from: min-YgX7TsA, reason: not valid java name */
    public static final float m5724minYgX7TsA(float f10, float f11) {
        return Dp.m5678constructorimpl(Math.min(f10, f11));
    }

    /* JADX INFO: renamed from: takeOrElse-D5KLDUw, reason: not valid java name */
    public static final float m5725takeOrElseD5KLDUw(float f10, r7.a<Dp> aVar) {
        return !Float.isNaN(f10) ? f10 : ((Dp) aVar.invoke()).m5692unboximpl();
    }

    /* JADX INFO: renamed from: takeOrElse-gVKV90s, reason: not valid java name */
    public static final long m5726takeOrElsegVKV90s(long j10, r7.a<DpOffset> aVar) {
        return j10 != DpOffset.INSTANCE.m5748getUnspecifiedRKDOV3M() ? j10 : ((DpOffset) aVar.invoke()).getPackedValue();
    }

    /* JADX INFO: renamed from: takeOrElse-itqla9I, reason: not valid java name */
    public static final long m5727takeOrElseitqla9I(long j10, r7.a<DpSize> aVar) {
        return j10 != DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ() ? j10 : ((DpSize) aVar.invoke()).getPackedValue();
    }

    /* JADX INFO: renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m5729times3ABfNKs(float f10, float f11) {
        return Dp.m5678constructorimpl(f10 * f11);
    }

    /* JADX INFO: renamed from: times-6HolHcs, reason: not valid java name */
    public static final long m5732times6HolHcs(int i10, long j10) {
        return DpSize.m5782timesGh9hcWk(j10, i10);
    }

    public static final float getDp(double d4) {
        return Dp.m5678constructorimpl((float) d4);
    }

    public static /* synthetic */ void getDp$annotations(float f10) {
    }

    /* JADX INFO: renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m5728times3ABfNKs(double d4, float f10) {
        return Dp.m5678constructorimpl(((float) d4) * f10);
    }

    /* JADX INFO: renamed from: times-6HolHcs, reason: not valid java name */
    public static final long m5731times6HolHcs(float f10, long j10) {
        return DpSize.m5781timesGh9hcWk(j10, f10);
    }

    public static final float getDp(float f10) {
        return Dp.m5678constructorimpl(f10);
    }

    public static /* synthetic */ void getDp$annotations(int i10) {
    }

    /* JADX INFO: renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m5730times3ABfNKs(int i10, float f10) {
        return Dp.m5678constructorimpl(i10 * f10);
    }
}
