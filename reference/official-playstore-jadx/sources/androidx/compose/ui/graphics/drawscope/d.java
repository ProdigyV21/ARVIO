package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Path;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class d {
    public static long a(DrawTransform drawTransform) {
        float f10 = 2;
        return OffsetKt.Offset(Size.m3305getWidthimpl(drawTransform.mo3927getSizeNHjbRc()) / f10, Size.m3302getHeightimpl(drawTransform.mo3927getSizeNHjbRc()) / f10);
    }

    public static /* synthetic */ void c(DrawTransform drawTransform, Path path, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipPath-mtrdD-E");
        }
        if ((i11 & 2) != 0) {
            i10 = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        }
        drawTransform.mo3924clipPathmtrdDE(path, i10);
    }

    public static /* synthetic */ void d(DrawTransform drawTransform, float f10, float f11, float f12, float f13, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipRect-N_I0leg");
        }
        if ((i11 & 1) != 0) {
            f10 = 0.0f;
        }
        if ((i11 & 2) != 0) {
            f11 = 0.0f;
        }
        if ((i11 & 4) != 0) {
            f12 = Size.m3305getWidthimpl(drawTransform.mo3927getSizeNHjbRc());
        }
        if ((i11 & 8) != 0) {
            f13 = Size.m3302getHeightimpl(drawTransform.mo3927getSizeNHjbRc());
        }
        if ((i11 & 16) != 0) {
            i10 = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        }
        drawTransform.mo3925clipRectN_I0leg(f10, f11, f12, f13, i10);
    }

    public static /* synthetic */ void e(DrawTransform drawTransform, float f10, long j10, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: rotate-Uv8p0NA");
        }
        if ((i10 & 2) != 0) {
            j10 = drawTransform.mo3926getCenterF1C5BW0();
        }
        drawTransform.mo3928rotateUv8p0NA(f10, j10);
    }

    public static /* synthetic */ void f(DrawTransform drawTransform, float f10, float f11, long j10, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scale-0AR0LA0");
        }
        if ((i10 & 4) != 0) {
            j10 = drawTransform.mo3926getCenterF1C5BW0();
        }
        drawTransform.mo3929scale0AR0LA0(f10, f11, j10);
    }

    public static /* synthetic */ void g(DrawTransform drawTransform, float f10, float f11, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: translate");
        }
        if ((i10 & 1) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 2) != 0) {
            f11 = 0.0f;
        }
        drawTransform.translate(f10, f11);
    }
}
