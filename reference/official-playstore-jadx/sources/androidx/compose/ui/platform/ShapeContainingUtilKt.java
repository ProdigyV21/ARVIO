package androidx.compose.ui.platform;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bH\u0000\u001a4\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002\u001a \u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a4\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0002\u001a:\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\f\u0010\u001a\u001a\u00020\u0001*\u00020\u001bH\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"isInOutline", "", "outline", "Landroidx/compose/ui/graphics/Outline;", "x", "", "y", "tmpTouchPointPath", "Landroidx/compose/ui/graphics/Path;", "tmpOpPath", "isInPath", "path", "isInRectangle", "rect", "Landroidx/compose/ui/geometry/Rect;", "isInRoundedRect", "Landroidx/compose/ui/graphics/Outline$Rounded;", "touchPointPath", "opPath", "isWithinEllipse", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "centerX", "centerY", "isWithinEllipse-VE1yxkc", "(FFJFF)Z", "cornersFit", "Landroidx/compose/ui/geometry/RoundRect;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShapeContainingUtilKt {
    private static final boolean cornersFit(RoundRect roundRect) {
        if (CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) + CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) > roundRect.getWidth()) {
            return false;
        }
        if (CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) + CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) > roundRect.getWidth()) {
            return false;
        }
        if (CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) + CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) <= roundRect.getHeight()) {
            return CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) + CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) <= roundRect.getHeight();
        }
        return false;
    }

    public static final boolean isInOutline(Outline outline, float f10, float f11, Path path, Path path2) {
        if (outline instanceof Outline.Rectangle) {
            return isInRectangle(((Outline.Rectangle) outline).getRect(), f10, f11);
        }
        if (outline instanceof Outline.Rounded) {
            return isInRoundedRect((Outline.Rounded) outline, f10, f11, path, path2);
        }
        if (outline instanceof Outline.Generic) {
            return isInPath(((Outline.Generic) outline).getPath(), f10, f11, path, path2);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ boolean isInOutline$default(Outline outline, float f10, float f11, Path path, Path path2, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            path = null;
        }
        if ((i10 & 16) != 0) {
            path2 = null;
        }
        return isInOutline(outline, f10, f11, path, path2);
    }

    private static final boolean isInPath(Path path, float f10, float f11, Path path2, Path path3) {
        Rect rect = new Rect(f10 - 0.005f, f11 - 0.005f, f10 + 0.005f, f11 + 0.005f);
        if (path2 == null) {
            path2 = AndroidPath_androidKt.Path();
        }
        path2.addRect(rect);
        if (path3 == null) {
            path3 = AndroidPath_androidKt.Path();
        }
        path3.mo3370opN5in7k0(path, path2, PathOperation.INSTANCE.m3743getIntersectb3I0S0c());
        boolean zIsEmpty = path3.isEmpty();
        path3.reset();
        path2.reset();
        return !zIsEmpty;
    }

    private static final boolean isInRectangle(Rect rect, float f10, float f11) {
        return rect.getLeft() <= f10 && f10 < rect.getRight() && rect.getTop() <= f11 && f11 < rect.getBottom();
    }

    private static final boolean isInRoundedRect(Outline.Rounded rounded, float f10, float f11, Path path, Path path2) {
        RoundRect roundRect = rounded.getRoundRect();
        if (f10 < roundRect.getLeft() || f10 >= roundRect.getRight() || f11 < roundRect.getTop() || f11 >= roundRect.getBottom()) {
            return false;
        }
        if (!cornersFit(roundRect)) {
            Path Path = path2 == null ? AndroidPath_androidKt.Path() : path2;
            Path.addRoundRect(roundRect);
            return isInPath(Path, f10, f11, path, path2);
        }
        float fM3211getXimpl = CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) + roundRect.getLeft();
        float fM3212getYimpl = CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) + roundRect.getTop();
        float right = roundRect.getRight() - CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs());
        float fM3212getYimpl2 = CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) + roundRect.getTop();
        float right2 = roundRect.getRight() - CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs());
        float bottom = roundRect.getBottom() - CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs());
        float bottom2 = roundRect.getBottom() - CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs());
        float fM3211getXimpl2 = CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) + roundRect.getLeft();
        if (f10 < fM3211getXimpl && f11 < fM3212getYimpl) {
            return m4996isWithinEllipseVE1yxkc(f10, f11, roundRect.m3286getTopLeftCornerRadiuskKHJgLs(), fM3211getXimpl, fM3212getYimpl);
        }
        if (f10 < fM3211getXimpl2 && f11 > bottom2) {
            return m4996isWithinEllipseVE1yxkc(f10, f11, roundRect.m3284getBottomLeftCornerRadiuskKHJgLs(), fM3211getXimpl2, bottom2);
        }
        if (f10 > right && f11 < fM3212getYimpl2) {
            return m4996isWithinEllipseVE1yxkc(f10, f11, roundRect.m3287getTopRightCornerRadiuskKHJgLs(), right, fM3212getYimpl2);
        }
        if (f10 <= right2 || f11 <= bottom) {
            return true;
        }
        return m4996isWithinEllipseVE1yxkc(f10, f11, roundRect.m3285getBottomRightCornerRadiuskKHJgLs(), right2, bottom);
    }

    /* JADX INFO: renamed from: isWithinEllipse-VE1yxkc, reason: not valid java name */
    private static final boolean m4996isWithinEllipseVE1yxkc(float f10, float f11, long j10, float f12, float f13) {
        float f14 = f10 - f12;
        float f15 = f11 - f13;
        float fM3211getXimpl = CornerRadius.m3211getXimpl(j10);
        float fM3212getYimpl = CornerRadius.m3212getYimpl(j10);
        return ((f15 * f15) / (fM3212getYimpl * fM3212getYimpl)) + ((f14 * f14) / (fM3211getXimpl * fM3211getXimpl)) <= 1.0f;
    }
}
