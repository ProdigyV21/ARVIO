package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001aN\u0010\u0013\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001aN\u0010\u0013\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0003\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0013\u0010\u001a\u001a\u00020\u0019*\u00020\u0018H\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0013\u0010\u001d\u001a\u00020\u001c*\u00020\u0018H\u0002¢\u0006\u0004\b\u001d\u0010\u001b\u001a\u0013\u0010\u001a\u001a\u00020\u0019*\u00020\u001eH\u0002¢\u0006\u0004\b\u001a\u0010\u001f\u001a\u0013\u0010\u001d\u001a\u00020\u001c*\u00020\u001eH\u0002¢\u0006\u0004\b\u001d\u0010\u001f\u001a¦\u0001\u0010*\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012,\u0010%\u001a(\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00030 ¢\u0006\u0002\b$2,\u0010'\u001a(\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00030 ¢\u0006\u0002\b$2,\u0010)\u001a(\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00030 ¢\u0006\u0002\b$H\u0082\b¢\u0006\u0004\b*\u0010+\u001a!\u0010\u0013\u001a\u00020\u0003*\u00020,2\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010.\u001a\u00020-¢\u0006\u0004\b\u0013\u0010/\u001a\u0013\u00101\u001a\u000200*\u00020\u001eH\u0002¢\u0006\u0004\b1\u00102\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00063"}, d2 = {"Landroidx/compose/ui/graphics/Path;", "Landroidx/compose/ui/graphics/Outline;", "outline", "Lx6/t0;", "addOutline", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Outline;)V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "", "alpha", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "style", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "drawOutline-wDX37Ww", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Outline;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOutline", "Landroidx/compose/ui/graphics/Brush;", "brush", "drawOutline-hn5TExg", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Outline;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/geometry/Offset;", "topLeft", "(Landroidx/compose/ui/geometry/Rect;)J", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/geometry/RoundRect;", "(Landroidx/compose/ui/geometry/RoundRect;)J", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "rect", "Lx6/n;", "drawRectBlock", "rrect", "drawRoundedRectBlock", "path", "drawPathBlock", "drawOutlineHelper", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Outline;Lr7/p;Lr7/p;Lr7/p;)V", "Landroidx/compose/ui/graphics/Canvas;", "Landroidx/compose/ui/graphics/Paint;", "paint", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Outline;Landroidx/compose/ui/graphics/Paint;)V", "", "hasSameCornerRadius", "(Landroidx/compose/ui/geometry/RoundRect;)Z", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OutlineKt {
    public static final void addOutline(Path path, Outline outline) {
        if (outline instanceof Outline.Rectangle) {
            path.addRect(((Outline.Rectangle) outline).getRect());
        } else if (outline instanceof Outline.Rounded) {
            path.addRoundRect(((Outline.Rounded) outline).getRoundRect());
        } else {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            h.g(path, ((Outline.Generic) outline).getPath(), 0L, 2, null);
        }
    }

    public static final void drawOutline(Canvas canvas, Outline outline, Paint paint) {
        if (outline instanceof Outline.Rectangle) {
            canvas.drawRect(((Outline.Rectangle) outline).getRect(), paint);
            return;
        }
        if (!(outline instanceof Outline.Rounded)) {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            canvas.drawPath(((Outline.Generic) outline).getPath(), paint);
        } else {
            Outline.Rounded rounded = (Outline.Rounded) outline;
            Path roundRectPath = rounded.getRoundRectPath();
            if (roundRectPath != null) {
                canvas.drawPath(roundRectPath, paint);
            } else {
                canvas.drawRoundRect(rounded.getRoundRect().getLeft(), rounded.getRoundRect().getTop(), rounded.getRoundRect().getRight(), rounded.getRoundRect().getBottom(), CornerRadius.m3211getXimpl(rounded.getRoundRect().m3284getBottomLeftCornerRadiuskKHJgLs()), CornerRadius.m3212getYimpl(rounded.getRoundRect().m3284getBottomLeftCornerRadiuskKHJgLs()), paint);
            }
        }
    }

    /* JADX INFO: renamed from: drawOutline-hn5TExg, reason: not valid java name */
    public static final void m3709drawOutlinehn5TExg(DrawScope drawScope, Outline outline, Brush brush, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10) {
        if (outline instanceof Outline.Rectangle) {
            Rect rect = ((Outline.Rectangle) outline).getRect();
            drawScope.mo3911drawRectAsUm42w(brush, topLeft(rect), size(rect), f10, drawStyle, colorFilter, i10);
            return;
        }
        if (!(outline instanceof Outline.Rounded)) {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            drawScope.mo3907drawPathGBMwjPU(((Outline.Generic) outline).getPath(), brush, f10, drawStyle, colorFilter, i10);
            return;
        }
        Outline.Rounded rounded = (Outline.Rounded) outline;
        Path roundRectPath = rounded.getRoundRectPath();
        if (roundRectPath != null) {
            drawScope.mo3907drawPathGBMwjPU(roundRectPath, brush, f10, drawStyle, colorFilter, i10);
            return;
        }
        RoundRect roundRect = rounded.getRoundRect();
        drawScope.mo3913drawRoundRectZuiqVtQ(brush, topLeft(roundRect), size(roundRect), CornerRadiusKt.CornerRadius$default(CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()), 0.0f, 2, null), f10, drawStyle, colorFilter, i10);
    }

    /* JADX INFO: renamed from: drawOutline-hn5TExg$default, reason: not valid java name */
    public static /* synthetic */ void m3710drawOutlinehn5TExg$default(DrawScope drawScope, Outline outline, Brush brush, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            f10 = 1.0f;
        }
        float f11 = f10;
        if ((i11 & 8) != 0) {
            drawStyle = Fill.INSTANCE;
        }
        DrawStyle drawStyle2 = drawStyle;
        if ((i11 & 16) != 0) {
            colorFilter = null;
        }
        ColorFilter colorFilter2 = colorFilter;
        if ((i11 & 32) != 0) {
            i10 = DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU();
        }
        m3709drawOutlinehn5TExg(drawScope, outline, brush, f11, drawStyle2, colorFilter2, i10);
    }

    /* JADX INFO: renamed from: drawOutline-wDX37Ww, reason: not valid java name */
    public static final void m3711drawOutlinewDX37Ww(DrawScope drawScope, Outline outline, long j10, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10) {
        if (outline instanceof Outline.Rectangle) {
            Rect rect = ((Outline.Rectangle) outline).getRect();
            drawScope.mo3912drawRectnJ9OG0(j10, topLeft(rect), size(rect), f10, drawStyle, colorFilter, i10);
            return;
        }
        if (!(outline instanceof Outline.Rounded)) {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            drawScope.mo3908drawPathLG529CI(((Outline.Generic) outline).getPath(), j10, f10, drawStyle, colorFilter, i10);
            return;
        }
        Outline.Rounded rounded = (Outline.Rounded) outline;
        Path roundRectPath = rounded.getRoundRectPath();
        if (roundRectPath != null) {
            drawScope.mo3908drawPathLG529CI(roundRectPath, j10, f10, drawStyle, colorFilter, i10);
            return;
        }
        RoundRect roundRect = rounded.getRoundRect();
        drawScope.mo3914drawRoundRectuAw5IA(j10, topLeft(roundRect), size(roundRect), CornerRadiusKt.CornerRadius$default(CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()), 0.0f, 2, null), drawStyle, f10, colorFilter, i10);
    }

    /* JADX INFO: renamed from: drawOutline-wDX37Ww$default, reason: not valid java name */
    public static /* synthetic */ void m3712drawOutlinewDX37Ww$default(DrawScope drawScope, Outline outline, long j10, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            f10 = 1.0f;
        }
        float f11 = f10;
        if ((i11 & 8) != 0) {
            drawStyle = Fill.INSTANCE;
        }
        DrawStyle drawStyle2 = drawStyle;
        if ((i11 & 16) != 0) {
            colorFilter = null;
        }
        m3711drawOutlinewDX37Ww(drawScope, outline, j10, f11, drawStyle2, colorFilter, (i11 & 32) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    private static final void drawOutlineHelper(DrawScope drawScope, Outline outline, p<? super DrawScope, ? super Rect, t0> pVar, p<? super DrawScope, ? super RoundRect, t0> pVar2, p<? super DrawScope, ? super Path, t0> pVar3) {
        if (outline instanceof Outline.Rectangle) {
            pVar.invoke(drawScope, ((Outline.Rectangle) outline).getRect());
            return;
        }
        if (!(outline instanceof Outline.Rounded)) {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            pVar3.invoke(drawScope, ((Outline.Generic) outline).getPath());
        } else {
            Outline.Rounded rounded = (Outline.Rounded) outline;
            Path roundRectPath = rounded.getRoundRectPath();
            if (roundRectPath != null) {
                pVar3.invoke(drawScope, roundRectPath);
            } else {
                pVar2.invoke(drawScope, rounded.getRoundRect());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasSameCornerRadius(RoundRect roundRect) {
        return ((CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) > CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) ? 1 : (CornerRadius.m3211getXimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) ? 0 : -1)) == 0 && (CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) > CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) ? 1 : (CornerRadius.m3211getXimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) ? 0 : -1)) == 0 && (CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) > CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) ? 1 : (CornerRadius.m3211getXimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) == CornerRadius.m3211getXimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) ? 0 : -1)) == 0) && ((CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) > CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) ? 1 : (CornerRadius.m3212getYimpl(roundRect.m3284getBottomLeftCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) ? 0 : -1)) == 0 && (CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) > CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) ? 1 : (CornerRadius.m3212getYimpl(roundRect.m3285getBottomRightCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) ? 0 : -1)) == 0 && (CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) > CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) ? 1 : (CornerRadius.m3212getYimpl(roundRect.m3287getTopRightCornerRadiuskKHJgLs()) == CornerRadius.m3212getYimpl(roundRect.m3286getTopLeftCornerRadiuskKHJgLs()) ? 0 : -1)) == 0);
    }

    private static final long size(Rect rect) {
        return SizeKt.Size(rect.getWidth(), rect.getHeight());
    }

    private static final long topLeft(Rect rect) {
        return OffsetKt.Offset(rect.getLeft(), rect.getTop());
    }

    private static final long size(RoundRect roundRect) {
        return SizeKt.Size(roundRect.getWidth(), roundRect.getHeight());
    }

    private static final long topLeft(RoundRect roundRect) {
        return OffsetKt.Offset(roundRect.getLeft(), roundRect.getTop());
    }
}
