package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0013\u0010\u0006\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a%\u0010\f\u001a\u00020\u0003*\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\u0011\u001a\u00020\r*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001f\u0010\u0011\u001a\u00020\r*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\rH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0010\u001a\u001f\u0010\u0015\u001a\u00020\r*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0010\u001a*\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\"\u001f\u0010!\u001a\u00020\u001c*\u00020\u00038Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001e\"\u001f\u0010$\u001a\u00020\u001c*\u00020\u00038Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b#\u0010 \u001a\u0004\b\"\u0010\u001e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006%"}, d2 = {"", "scaleX", "scaleY", "Landroidx/compose/ui/layout/ScaleFactor;", "ScaleFactor", "(FF)J", "roundToTenths", "(F)F", "Lkotlin/Function0;", "block", "takeOrElse-oyDd2qo", "(JLr7/a;)J", "takeOrElse", "Landroidx/compose/ui/geometry/Size;", "scaleFactor", "times-UQTWf7w", "(JJ)J", "times", ContentDisposition.Parameters.Size, "times-m-w2e94", "div-UQTWf7w", TtmlNode.TAG_DIV, TtmlNode.START, "stop", "fraction", "lerp--bDIf60", "(JJF)J", "lerp", "", "isSpecified-FK8aYYs", "(J)Z", "isSpecified-FK8aYYs$annotations", "(J)V", "isSpecified", "isUnspecified-FK8aYYs", "isUnspecified-FK8aYYs$annotations", "isUnspecified", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScaleFactorKt {
    public static final long ScaleFactor(float f10, float f11) {
        return ScaleFactor.m4682constructorimpl((((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32));
    }

    /* JADX INFO: renamed from: div-UQTWf7w, reason: not valid java name */
    public static final long m4696divUQTWf7w(long j10, long j11) {
        return SizeKt.Size(Size.m3305getWidthimpl(j10) / ScaleFactor.m4688getScaleXimpl(j11), Size.m3302getHeightimpl(j10) / ScaleFactor.m4689getScaleYimpl(j11));
    }

    /* JADX INFO: renamed from: isSpecified-FK8aYYs, reason: not valid java name */
    public static final boolean m4697isSpecifiedFK8aYYs(long j10) {
        return j10 != ScaleFactor.Companion.m4695getUnspecified_hLwfpc();
    }

    /* JADX INFO: renamed from: isSpecified-FK8aYYs$annotations, reason: not valid java name */
    public static /* synthetic */ void m4698isSpecifiedFK8aYYs$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isUnspecified-FK8aYYs, reason: not valid java name */
    public static final boolean m4699isUnspecifiedFK8aYYs(long j10) {
        return j10 == ScaleFactor.Companion.m4695getUnspecified_hLwfpc();
    }

    /* JADX INFO: renamed from: isUnspecified-FK8aYYs$annotations, reason: not valid java name */
    public static /* synthetic */ void m4700isUnspecifiedFK8aYYs$annotations(long j10) {
    }

    /* JADX INFO: renamed from: lerp--bDIf60, reason: not valid java name */
    public static final long m4701lerpbDIf60(long j10, long j11, float f10) {
        return ScaleFactor(MathHelpersKt.lerp(ScaleFactor.m4688getScaleXimpl(j10), ScaleFactor.m4688getScaleXimpl(j11), f10), MathHelpersKt.lerp(ScaleFactor.m4689getScaleYimpl(j10), ScaleFactor.m4689getScaleYimpl(j11), f10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float roundToTenths(float f10) {
        float f11 = 10;
        float f12 = f10 * f11;
        int i10 = (int) f12;
        if (f12 - i10 >= 0.5f) {
            i10++;
        }
        return i10 / f11;
    }

    /* JADX INFO: renamed from: takeOrElse-oyDd2qo, reason: not valid java name */
    public static final long m4702takeOrElseoyDd2qo(long j10, r7.a<ScaleFactor> aVar) {
        return j10 != ScaleFactor.Companion.m4695getUnspecified_hLwfpc() ? j10 : ((ScaleFactor) aVar.invoke()).m4693unboximpl();
    }

    /* JADX INFO: renamed from: times-UQTWf7w, reason: not valid java name */
    public static final long m4703timesUQTWf7w(long j10, long j11) {
        return SizeKt.Size(ScaleFactor.m4688getScaleXimpl(j11) * Size.m3305getWidthimpl(j10), ScaleFactor.m4689getScaleYimpl(j11) * Size.m3302getHeightimpl(j10));
    }

    /* JADX INFO: renamed from: times-m-w2e94, reason: not valid java name */
    public static final long m4704timesmw2e94(long j10, long j11) {
        return m4703timesUQTWf7w(j11, j10);
    }
}
