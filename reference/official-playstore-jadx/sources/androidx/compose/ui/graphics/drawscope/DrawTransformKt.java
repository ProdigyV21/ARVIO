package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.graphics.DegreesKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a(\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001c\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0005\u0010\u0007\u001a)\u0010\r\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\tH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a)\u0010\u000e\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\tH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "", "horizontal", "vertical", "Lx6/t0;", "inset", "(Landroidx/compose/ui/graphics/drawscope/DrawTransform;FF)V", "(Landroidx/compose/ui/graphics/drawscope/DrawTransform;F)V", "radians", "Landroidx/compose/ui/geometry/Offset;", "pivot", "rotateRad-0AR0LA0", "(Landroidx/compose/ui/graphics/drawscope/DrawTransform;FJ)V", "rotateRad", "scale", "scale-0AR0LA0", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DrawTransformKt {
    public static final void inset(DrawTransform drawTransform, float f10, float f11) {
        drawTransform.inset(f10, f11, f10, f11);
    }

    public static /* synthetic */ void inset$default(DrawTransform drawTransform, float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 2) != 0) {
            f11 = 0.0f;
        }
        drawTransform.inset(f10, f11, f10, f11);
    }

    /* JADX INFO: renamed from: rotateRad-0AR0LA0, reason: not valid java name */
    public static final void m4000rotateRad0AR0LA0(DrawTransform drawTransform, float f10, long j10) {
        drawTransform.mo3928rotateUv8p0NA(DegreesKt.degrees(f10), j10);
    }

    /* JADX INFO: renamed from: rotateRad-0AR0LA0$default, reason: not valid java name */
    public static /* synthetic */ void m4001rotateRad0AR0LA0$default(DrawTransform drawTransform, float f10, long j10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = drawTransform.mo3926getCenterF1C5BW0();
        }
        drawTransform.mo3928rotateUv8p0NA(DegreesKt.degrees(f10), j10);
    }

    /* JADX INFO: renamed from: scale-0AR0LA0, reason: not valid java name */
    public static final void m4002scale0AR0LA0(DrawTransform drawTransform, float f10, long j10) {
        drawTransform.mo3929scale0AR0LA0(f10, f10, j10);
    }

    /* JADX INFO: renamed from: scale-0AR0LA0$default, reason: not valid java name */
    public static /* synthetic */ void m4003scale0AR0LA0$default(DrawTransform drawTransform, float f10, long j10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = drawTransform.mo3926getCenterF1C5BW0();
        }
        drawTransform.mo3929scale0AR0LA0(f10, f10, j10);
    }

    public static final void inset(DrawTransform drawTransform, float f10) {
        drawTransform.inset(f10, f10, f10, f10);
    }
}
