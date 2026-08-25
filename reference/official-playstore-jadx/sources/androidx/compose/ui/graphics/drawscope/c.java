package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    static {
        DrawScope.Companion companion = DrawScope.INSTANCE;
    }

    public static /* synthetic */ void A(DrawScope drawScope, ImageBitmap imageBitmap, long j10, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-gbVJVH8");
        }
        drawScope.mo3902drawImagegbVJVH8(imageBitmap, (i11 & 2) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j10, (i11 & 4) != 0 ? 1.0f : f10, (i11 & 8) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 16) != 0 ? null : colorFilter, (i11 & 32) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void B(DrawScope drawScope, Brush brush, long j10, long j11, float f10, int i10, PathEffect pathEffect, float f11, ColorFilter colorFilter, int i11, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-1RTmtNc");
        }
        drawScope.mo3903drawLine1RTmtNc(brush, j10, j11, (i12 & 8) != 0 ? 0.0f : f10, (i12 & 16) != 0 ? Stroke.INSTANCE.m4006getDefaultCapKaPHkGw() : i10, (i12 & 32) != 0 ? null : pathEffect, (i12 & 64) != 0 ? 1.0f : f11, (i12 & 128) != 0 ? null : colorFilter, (i12 & 256) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i11);
    }

    public static /* synthetic */ void C(DrawScope drawScope, long j10, long j11, long j12, float f10, int i10, PathEffect pathEffect, float f11, ColorFilter colorFilter, int i11, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-NGM6Ib0");
        }
        drawScope.mo3904drawLineNGM6Ib0(j10, j11, j12, (i12 & 8) != 0 ? 0.0f : f10, (i12 & 16) != 0 ? Stroke.INSTANCE.m4006getDefaultCapKaPHkGw() : i10, (i12 & 32) != 0 ? null : pathEffect, (i12 & 64) != 0 ? 1.0f : f11, (i12 & 128) != 0 ? null : colorFilter, (i12 & 256) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i11);
    }

    public static /* synthetic */ void D(DrawScope drawScope, Brush brush, long j10, long j11, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawOval-AsUm42w");
        }
        long jM3252getZeroF1C5BW0 = (i11 & 2) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j10;
        drawScope.mo3905drawOvalAsUm42w(brush, jM3252getZeroF1C5BW0, (i11 & 4) != 0 ? d(drawScope, drawScope.mo3916getSizeNHjbRc(), jM3252getZeroF1C5BW0) : j11, (i11 & 8) != 0 ? 1.0f : f10, (i11 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 32) != 0 ? null : colorFilter, (i11 & 64) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void E(DrawScope drawScope, long j10, long j11, long j12, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawOval-n-J9OG0");
        }
        long jM3252getZeroF1C5BW0 = (i11 & 2) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j11;
        drawScope.mo3906drawOvalnJ9OG0(j10, jM3252getZeroF1C5BW0, (i11 & 4) != 0 ? d(drawScope, drawScope.mo3916getSizeNHjbRc(), jM3252getZeroF1C5BW0) : j12, (i11 & 8) != 0 ? 1.0f : f10, (i11 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 32) != 0 ? null : colorFilter, (i11 & 64) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void F(DrawScope drawScope, Path path, Brush brush, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-GBMwjPU");
        }
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
        drawScope.mo3907drawPathGBMwjPU(path, brush, f11, drawStyle2, colorFilter2, i10);
    }

    public static /* synthetic */ void G(DrawScope drawScope, Path path, long j10, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-LG529CI");
        }
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
        drawScope.mo3908drawPathLG529CI(path, j10, f11, drawStyle2, colorFilter, (i11 & 32) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void H(DrawScope drawScope, List list, int i10, long j10, float f10, int i11, PathEffect pathEffect, float f11, ColorFilter colorFilter, int i12, int i13, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPoints-F8ZwMP8");
        }
        drawScope.mo3909drawPointsF8ZwMP8(list, i10, j10, (i13 & 8) != 0 ? 0.0f : f10, (i13 & 16) != 0 ? StrokeCap.INSTANCE.m3803getButtKaPHkGw() : i11, (i13 & 32) != 0 ? null : pathEffect, (i13 & 64) != 0 ? 1.0f : f11, (i13 & 128) != 0 ? null : colorFilter, (i13 & 256) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i12);
    }

    public static /* synthetic */ void I(DrawScope drawScope, List list, int i10, Brush brush, float f10, int i11, PathEffect pathEffect, float f11, ColorFilter colorFilter, int i12, int i13, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPoints-Gsft0Ws");
        }
        drawScope.mo3910drawPointsGsft0Ws(list, i10, brush, (i13 & 8) != 0 ? 0.0f : f10, (i13 & 16) != 0 ? StrokeCap.INSTANCE.m3803getButtKaPHkGw() : i11, (i13 & 32) != 0 ? null : pathEffect, (i13 & 64) != 0 ? 1.0f : f11, (i13 & 128) != 0 ? null : colorFilter, (i13 & 256) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i12);
    }

    public static /* synthetic */ void J(DrawScope drawScope, Brush brush, long j10, long j11, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-AsUm42w");
        }
        long jM3252getZeroF1C5BW0 = (i11 & 2) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j10;
        drawScope.mo3911drawRectAsUm42w(brush, jM3252getZeroF1C5BW0, (i11 & 4) != 0 ? d(drawScope, drawScope.mo3916getSizeNHjbRc(), jM3252getZeroF1C5BW0) : j11, (i11 & 8) != 0 ? 1.0f : f10, (i11 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 32) != 0 ? null : colorFilter, (i11 & 64) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void K(DrawScope drawScope, long j10, long j11, long j12, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-n-J9OG0");
        }
        long jM3252getZeroF1C5BW0 = (i11 & 2) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j11;
        drawScope.mo3912drawRectnJ9OG0(j10, jM3252getZeroF1C5BW0, (i11 & 4) != 0 ? d(drawScope, drawScope.mo3916getSizeNHjbRc(), jM3252getZeroF1C5BW0) : j12, (i11 & 8) != 0 ? 1.0f : f10, (i11 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 32) != 0 ? null : colorFilter, (i11 & 64) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void L(DrawScope drawScope, Brush brush, long j10, long j11, long j12, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-ZuiqVtQ");
        }
        long jM3252getZeroF1C5BW0 = (i11 & 2) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j10;
        drawScope.mo3913drawRoundRectZuiqVtQ(brush, jM3252getZeroF1C5BW0, (i11 & 4) != 0 ? d(drawScope, drawScope.mo3916getSizeNHjbRc(), jM3252getZeroF1C5BW0) : j11, (i11 & 8) != 0 ? CornerRadius.INSTANCE.m3221getZerokKHJgLs() : j12, (i11 & 16) != 0 ? 1.0f : f10, (i11 & 32) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 64) != 0 ? null : colorFilter, (i11 & 128) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void M(DrawScope drawScope, long j10, long j11, long j12, long j13, DrawStyle drawStyle, float f10, ColorFilter colorFilter, int i10, int i11, Object obj) {
        DrawScope drawScope2;
        long jD;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-u-Aw5IA");
        }
        long jM3252getZeroF1C5BW0 = (i11 & 2) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j11;
        if ((i11 & 4) != 0) {
            drawScope2 = drawScope;
            jD = d(drawScope2, drawScope.mo3916getSizeNHjbRc(), jM3252getZeroF1C5BW0);
        } else {
            drawScope2 = drawScope;
            jD = j12;
        }
        drawScope2.mo3914drawRoundRectuAw5IA(j10, jM3252getZeroF1C5BW0, jD, (i11 & 8) != 0 ? CornerRadius.INSTANCE.m3221getZerokKHJgLs() : j13, (i11 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 32) != 0 ? 1.0f : f10, (i11 & 64) != 0 ? null : colorFilter, (i11 & 128) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static void a(DrawScope drawScope, ImageBitmap imageBitmap, long j10, long j11, long j12, long j13, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11) {
        z(drawScope, imageBitmap, j10, j11, j12, j13, f10, drawStyle, colorFilter, i10, 0, 512, null);
    }

    public static long b(DrawScope drawScope) {
        return SizeKt.m3315getCenteruvyYCjk(drawScope.getDrawContext().mo3922getSizeNHjbRc());
    }

    public static long c(DrawScope drawScope) {
        return drawScope.getDrawContext().mo3922getSizeNHjbRc();
    }

    public static long d(DrawScope drawScope, long j10, long j11) {
        return SizeKt.Size(Size.m3305getWidthimpl(j10) - Offset.m3236getXimpl(j11), Size.m3302getHeightimpl(j10) - Offset.m3237getYimpl(j11));
    }

    public static /* synthetic */ void u(DrawScope drawScope, Brush brush, float f10, float f11, boolean z, long j10, long j11, float f12, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        DrawScope drawScope2;
        long jD;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-illE91I");
        }
        long jM3252getZeroF1C5BW0 = (i11 & 16) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j10;
        if ((i11 & 32) != 0) {
            drawScope2 = drawScope;
            jD = d(drawScope2, drawScope.mo3916getSizeNHjbRc(), jM3252getZeroF1C5BW0);
        } else {
            drawScope2 = drawScope;
            jD = j11;
        }
        drawScope2.mo3896drawArcillE91I(brush, f10, f11, z, jM3252getZeroF1C5BW0, jD, (i11 & 64) != 0 ? 1.0f : f12, (i11 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 256) != 0 ? null : colorFilter, (i11 & 512) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void v(DrawScope drawScope, long j10, float f10, float f11, boolean z, long j11, long j12, float f12, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        DrawScope drawScope2;
        long jD;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-yD3GUKo");
        }
        long jM3252getZeroF1C5BW0 = (i11 & 16) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j11;
        if ((i11 & 32) != 0) {
            drawScope2 = drawScope;
            jD = d(drawScope2, drawScope.mo3916getSizeNHjbRc(), jM3252getZeroF1C5BW0);
        } else {
            drawScope2 = drawScope;
            jD = j12;
        }
        drawScope2.mo3897drawArcyD3GUKo(j10, f10, f11, z, jM3252getZeroF1C5BW0, jD, (i11 & 64) != 0 ? 1.0f : f12, (i11 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 256) != 0 ? null : colorFilter, (i11 & 512) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void w(DrawScope drawScope, Brush brush, float f10, long j10, float f11, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-V9BoPsw");
        }
        drawScope.mo3898drawCircleV9BoPsw(brush, (i11 & 2) != 0 ? Size.m3304getMinDimensionimpl(drawScope.mo3916getSizeNHjbRc()) / 2.0f : f10, (i11 & 4) != 0 ? drawScope.mo3915getCenterF1C5BW0() : j10, (i11 & 8) != 0 ? 1.0f : f11, (i11 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 32) != 0 ? null : colorFilter, (i11 & 64) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void x(DrawScope drawScope, long j10, float f10, long j11, float f11, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-VaOC9Bg");
        }
        if ((i11 & 2) != 0) {
            f10 = Size.m3304getMinDimensionimpl(drawScope.mo3916getSizeNHjbRc()) / 2.0f;
        }
        drawScope.mo3899drawCircleVaOC9Bg(j10, f10, (i11 & 4) != 0 ? drawScope.mo3915getCenterF1C5BW0() : j11, (i11 & 8) != 0 ? 1.0f : f11, (i11 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 32) != 0 ? null : colorFilter, (i11 & 64) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void y(DrawScope drawScope, ImageBitmap imageBitmap, long j10, long j11, long j12, long j13, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-9jGpkUE");
        }
        long jM5812getZeronOccac = (i11 & 2) != 0 ? IntOffset.INSTANCE.m5812getZeronOccac() : j10;
        long jIntSize = (i11 & 4) != 0 ? IntSizeKt.IntSize(imageBitmap.getWidth(), imageBitmap.getHeight()) : j11;
        drawScope.mo3900drawImage9jGpkUE(imageBitmap, jM5812getZeronOccac, jIntSize, (i11 & 8) != 0 ? IntOffset.INSTANCE.m5812getZeronOccac() : j12, (i11 & 16) != 0 ? jIntSize : j13, (i11 & 32) != 0 ? 1.0f : f10, (i11 & 64) != 0 ? Fill.INSTANCE : drawStyle, (i11 & 128) != 0 ? null : colorFilter, (i11 & 256) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void z(DrawScope drawScope, ImageBitmap imageBitmap, long j10, long j11, long j12, long j13, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-AZ2fEMs");
        }
        long jM5812getZeronOccac = (i12 & 2) != 0 ? IntOffset.INSTANCE.m5812getZeronOccac() : j10;
        long jIntSize = (i12 & 4) != 0 ? IntSizeKt.IntSize(imageBitmap.getWidth(), imageBitmap.getHeight()) : j11;
        drawScope.mo3901drawImageAZ2fEMs(imageBitmap, jM5812getZeronOccac, jIntSize, (i12 & 8) != 0 ? IntOffset.INSTANCE.m5812getZeronOccac() : j12, (i12 & 16) != 0 ? jIntSize : j13, (i12 & 32) != 0 ? 1.0f : f10, (i12 & 64) != 0 ? Fill.INSTANCE : drawStyle, (i12 & 128) != 0 ? null : colorFilter, (i12 & 256) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10, (i12 & 512) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i11);
    }
}
