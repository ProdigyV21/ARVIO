package androidx.compose.ui.geometry;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0019\u001a \u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a@\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010 \u001a\u00020\u001c2\b\b\u0002\u0010!\u001a\u00020\u001c2\b\b\u0002\u0010\"\u001a\u00020\u001cø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u001e\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u0012\u001a8\u0010\u0019\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a6\u0010\u0019\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u0012\u001a\u001e\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u00020\u0012\u001a\u001c\u00101\u001a\u00020\u0002*\u00020\u00022\u0006\u00102\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b3\u00104\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b\"\u0015\u0010\f\u001a\u00020\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000b\"\u0015\u0010\r\u001a\u00020\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b\"\u0015\u0010\u000e\u001a\u00020\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000b\"\u0015\u0010\u000f\u001a\u00020\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000b\"\u0015\u0010\u0010\u001a\u00020\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000b\"\u0015\u0010\u0011\u001a\u00020\u0012*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0015\u0010\u0015\u001a\u00020\u0012*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014\"\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00065"}, d2 = {"boundingRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/geometry/RoundRect;", "getBoundingRect", "(Landroidx/compose/ui/geometry/RoundRect;)Landroidx/compose/ui/geometry/Rect;", TtmlNode.CENTER, "Landroidx/compose/ui/geometry/Offset;", "getCenter", "(Landroidx/compose/ui/geometry/RoundRect;)J", "isCircle", "", "(Landroidx/compose/ui/geometry/RoundRect;)Z", "isEllipse", "isEmpty", "isFinite", "isRect", "isSimple", "maxDimension", "", "getMaxDimension", "(Landroidx/compose/ui/geometry/RoundRect;)F", "minDimension", "getMinDimension", "safeInnerRect", "getSafeInnerRect", "RoundRect", "rect", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "RoundRect-sniSvfs", "(Landroidx/compose/ui/geometry/Rect;J)Landroidx/compose/ui/geometry/RoundRect;", "topLeft", "topRight", "bottomRight", "bottomLeft", "RoundRect-ZAM2FJo", "(Landroidx/compose/ui/geometry/Rect;JJJJ)Landroidx/compose/ui/geometry/RoundRect;", "radiusX", "radiusY", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "RoundRect-gG7oq9Y", "(FFFFJ)Landroidx/compose/ui/geometry/RoundRect;", "lerp", TtmlNode.START, "stop", "fraction", "translate", "offset", "translate-Uv8p0NA", "(Landroidx/compose/ui/geometry/RoundRect;J)Landroidx/compose/ui/geometry/RoundRect;", "ui-geometry_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RoundRectKt {
    public static final RoundRect RoundRect(float f10, float f11, float f12, float f13, float f14, float f15) {
        long jCornerRadius = CornerRadiusKt.CornerRadius(f14, f15);
        return new RoundRect(f10, f11, f12, f13, jCornerRadius, jCornerRadius, jCornerRadius, jCornerRadius, null);
    }

    /* JADX INFO: renamed from: RoundRect-ZAM2FJo, reason: not valid java name */
    public static final RoundRect m3288RoundRectZAM2FJo(Rect rect, long j10, long j11, long j12, long j13) {
        return new RoundRect(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), j10, j11, j12, j13, null);
    }

    /* JADX INFO: renamed from: RoundRect-gG7oq9Y, reason: not valid java name */
    public static final RoundRect m3290RoundRectgG7oq9Y(float f10, float f11, float f12, float f13, long j10) {
        return RoundRect(f10, f11, f12, f13, CornerRadius.m3211getXimpl(j10), CornerRadius.m3212getYimpl(j10));
    }

    /* JADX INFO: renamed from: RoundRect-sniSvfs, reason: not valid java name */
    public static final RoundRect m3291RoundRectsniSvfs(Rect rect, long j10) {
        return RoundRect(rect, CornerRadius.m3211getXimpl(j10), CornerRadius.m3212getYimpl(j10));
    }

    public static final Rect getBoundingRect(RoundRect roundRect) {
        return new Rect(roundRect.getLeft(), roundRect.getTop(), roundRect.getRight(), roundRect.getBottom());
    }

    public static final long getCenter(RoundRect roundRect) {
        return OffsetKt.Offset((roundRect.getWidth() / 2.0f) + roundRect.getLeft(), (roundRect.getHeight() / 2.0f) + roundRect.getTop());
    }

    public static final float getMaxDimension(RoundRect roundRect) {
        return Math.max(Math.abs(roundRect.getWidth()), Math.abs(roundRect.getHeight()));
    }

    public static final float getMinDimension(RoundRect roundRect) {
        return Math.min(Math.abs(roundRect.getWidth()), Math.abs(roundRect.getHeight()));
    }

    public static final Rect getSafeInnerRect(RoundRect roundRect) {
        float fMax = Math.max(CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()), CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()));
        float fMax2 = Math.max(CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()), CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()));
        return new Rect((fMax * 0.29289323f) + roundRect.getLeft(), (fMax2 * 0.29289323f) + roundRect.getTop(), roundRect.getRight() - (Math.max(CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()), CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs())) * 0.29289323f), roundRect.getBottom() - (Math.max(CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()), CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs())) * 0.29289323f));
    }

    public static final boolean isCircle(RoundRect roundRect) {
        return roundRect.getWidth() == roundRect.getHeight() && isEllipse(roundRect);
    }

    public static final boolean isEllipse(RoundRect roundRect) {
        return CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) && CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) && CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) && CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) && CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) && CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) && ((double) roundRect.getWidth()) <= ((double) CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs())) * 2.0d && ((double) roundRect.getHeight()) <= ((double) CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs())) * 2.0d;
    }

    public static final boolean isEmpty(RoundRect roundRect) {
        return roundRect.getLeft() >= roundRect.getRight() || roundRect.getTop() >= roundRect.getBottom();
    }

    public static final boolean isFinite(RoundRect roundRect) {
        float left = roundRect.getLeft();
        if (Float.isInfinite(left) || Float.isNaN(left)) {
            return false;
        }
        float top = roundRect.getTop();
        if (Float.isInfinite(top) || Float.isNaN(top)) {
            return false;
        }
        float right = roundRect.getRight();
        if (Float.isInfinite(right) || Float.isNaN(right)) {
            return false;
        }
        float bottom = roundRect.getBottom();
        return (Float.isInfinite(bottom) || Float.isNaN(bottom)) ? false : true;
    }

    public static final boolean isRect(RoundRect roundRect) {
        if (CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) != 0.0f && CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) != 0.0f) {
            return false;
        }
        if (CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) != 0.0f && CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) != 0.0f) {
            return false;
        }
        if (CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) == 0.0f || CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) == 0.0f) {
            return CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) == 0.0f || CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) == 0.0f;
        }
        return false;
    }

    public static final boolean isSimple(RoundRect roundRect) {
        return CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) && CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) && CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) && CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) && CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) && CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) && CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs());
    }

    public static final RoundRect lerp(RoundRect roundRect, RoundRect roundRect2, float f10) {
        return new RoundRect(MathHelpersKt.lerp(roundRect.getLeft(), roundRect2.getLeft(), f10), MathHelpersKt.lerp(roundRect.getTop(), roundRect2.getTop(), f10), MathHelpersKt.lerp(roundRect.getRight(), roundRect2.getRight(), f10), MathHelpersKt.lerp(roundRect.getBottom(), roundRect2.getBottom(), f10), CornerRadiusKt.m3222lerp3Ry4LBc(roundRect.m3286getTopLeftCornerRadiuskKHJgLs(), roundRect2.m3286getTopLeftCornerRadiuskKHJgLs(), f10), CornerRadiusKt.m3222lerp3Ry4LBc(roundRect.m3287getTopRightCornerRadiuskKHJgLs(), roundRect2.m3287getTopRightCornerRadiuskKHJgLs(), f10), CornerRadiusKt.m3222lerp3Ry4LBc(roundRect.m3285getBottomRightCornerRadiuskKHJgLs(), roundRect2.m3285getBottomRightCornerRadiuskKHJgLs(), f10), CornerRadiusKt.m3222lerp3Ry4LBc(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs(), roundRect2.m3284getBottomLeftCornerRadiuskKHJgLs(), f10), null);
    }

    /* JADX INFO: renamed from: translate-Uv8p0NA, reason: not valid java name */
    public static final RoundRect m3292translateUv8p0NA(RoundRect roundRect, long j10) {
        return new RoundRect(roundRect.getLeft() + Offset.m3236getXimpl(j10), roundRect.getTop() + Offset.m3237getYimpl(j10), roundRect.getRight() + Offset.m3236getXimpl(j10), roundRect.getBottom() + Offset.m3237getYimpl(j10), roundRect.m3286getTopLeftCornerRadiuskKHJgLs(), roundRect.m3287getTopRightCornerRadiuskKHJgLs(), roundRect.m3285getBottomRightCornerRadiuskKHJgLs(), roundRect.m3284getBottomLeftCornerRadiuskKHJgLs(), null);
    }

    public static final RoundRect RoundRect(Rect rect, float f10, float f11) {
        return RoundRect(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), f10, f11);
    }
}
