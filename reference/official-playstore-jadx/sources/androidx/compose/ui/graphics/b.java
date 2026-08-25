package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    public static void a(Canvas canvas, Rect rect, int i10) {
        canvas.mo3331clipRectN_I0leg(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), i10);
    }

    public static void b(Canvas canvas, Rect rect, float f10, float f11, boolean z, Paint paint) {
        canvas.drawArc(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), f10, f11, z, paint);
    }

    public static void c(Canvas canvas, Rect rect, float f10, float f11, boolean z, Paint paint) {
        canvas.drawArc(rect, DegreesKt.degrees(f10), DegreesKt.degrees(f11), z, paint);
    }

    public static void d(Canvas canvas, Rect rect, Paint paint) {
        canvas.drawOval(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint);
    }

    public static void e(Canvas canvas, Rect rect, Paint paint) {
        canvas.drawRect(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint);
    }

    public static void f(Canvas canvas, float f10, float f11) {
        canvas.skew(DegreesKt.degrees(f10), DegreesKt.degrees(f11));
    }

    public static /* synthetic */ void m(Canvas canvas, Path path, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipPath-mtrdD-E");
        }
        if ((i11 & 2) != 0) {
            i10 = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        }
        canvas.mo3330clipPathmtrdDE(path, i10);
    }

    public static /* synthetic */ void n(Canvas canvas, float f10, float f11, float f12, float f13, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipRect-N_I0leg");
        }
        if ((i11 & 16) != 0) {
            i10 = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        }
        canvas.mo3331clipRectN_I0leg(f10, f11, f12, f13, i10);
    }

    public static /* synthetic */ void o(Canvas canvas, Rect rect, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipRect-mtrdD-E");
        }
        if ((i11 & 2) != 0) {
            i10 = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        }
        canvas.mo3332clipRectmtrdDE(rect, i10);
    }

    public static /* synthetic */ void p(Canvas canvas, ImageBitmap imageBitmap, long j10, long j11, long j12, long j13, Paint paint, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImageRect-HPBpro0");
        }
        long jM5812getZeronOccac = (i10 & 2) != 0 ? IntOffset.INSTANCE.m5812getZeronOccac() : j10;
        long jIntSize = (i10 & 4) != 0 ? IntSizeKt.IntSize(imageBitmap.getWidth(), imageBitmap.getHeight()) : j11;
        canvas.mo3336drawImageRectHPBpro0(imageBitmap, jM5812getZeronOccac, jIntSize, (i10 & 8) != 0 ? IntOffset.INSTANCE.m5812getZeronOccac() : j12, (i10 & 16) != 0 ? jIntSize : j13, paint);
    }

    public static /* synthetic */ void q(Canvas canvas, float f10, float f11, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scale");
        }
        if ((i10 & 2) != 0) {
            f11 = f10;
        }
        canvas.scale(f10, f11);
    }
}
