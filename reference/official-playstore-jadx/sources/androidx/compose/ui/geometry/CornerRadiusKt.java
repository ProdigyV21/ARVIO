package androidx.compose.ui.geometry;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a*\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"CornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "x", "", "y", "(FF)J", "lerp", TtmlNode.START, "stop", "fraction", "lerp-3Ry4LBc", "(JJF)J", "ui-geometry_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CornerRadiusKt {
    public static final long CornerRadius(float f10, float f11) {
        return CornerRadius.m3205constructorimpl((((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32));
    }

    public static /* synthetic */ long CornerRadius$default(float f10, float f11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f11 = f10;
        }
        return CornerRadius(f10, f11);
    }

    /* JADX INFO: renamed from: lerp-3Ry4LBc, reason: not valid java name */
    public static final long m3222lerp3Ry4LBc(long j10, long j11, float f10) {
        return CornerRadius(MathHelpersKt.lerp(CornerRadius.m3211getXimpl(j10), CornerRadius.m3211getXimpl(j11), f10), MathHelpersKt.lerp(CornerRadius.m3212getYimpl(j10), CornerRadius.m3212getYimpl(j11), f10));
    }
}
