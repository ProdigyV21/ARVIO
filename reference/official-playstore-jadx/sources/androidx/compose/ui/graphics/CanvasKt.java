package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\"\u0010\b\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\b¢\u0006\u0004\b\b\u0010\t\u001a2\u0010\u000e\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\b¢\u0006\u0004\b\u000e\u0010\u000f\u001a)\u0010\u0014\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010¢\u0006\u0004\b\u0014\u0010\u0015\u001a-\u0010\u0017\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0010¢\u0006\u0004\b\u0017\u0010\u0015\u001a3\u0010\u001a\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/graphics/ImageBitmap;", "image", "Landroidx/compose/ui/graphics/Canvas;", "Canvas", "(Landroidx/compose/ui/graphics/ImageBitmap;)Landroidx/compose/ui/graphics/Canvas;", "Lkotlin/Function0;", "Lx6/t0;", "block", "withSave", "(Landroidx/compose/ui/graphics/Canvas;Lr7/a;)V", "Landroidx/compose/ui/geometry/Rect;", "bounds", "Landroidx/compose/ui/graphics/Paint;", "paint", "withSaveLayer", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/graphics/Paint;Lr7/a;)V", "", "degrees", "pivotX", "pivotY", "rotate", "(Landroidx/compose/ui/graphics/Canvas;FFF)V", "radians", "rotateRad", "sx", "sy", "scale", "(Landroidx/compose/ui/graphics/Canvas;FFFF)V", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CanvasKt {
    public static final Canvas Canvas(ImageBitmap imageBitmap) {
        return AndroidCanvas_androidKt.ActualCanvas(imageBitmap);
    }

    public static final void rotate(Canvas canvas, float f10, float f11, float f12) {
        if (f10 == 0.0f) {
            return;
        }
        canvas.translate(f11, f12);
        canvas.rotate(f10);
        canvas.translate(-f11, -f12);
    }

    public static final void rotateRad(Canvas canvas, float f10, float f11, float f12) {
        rotate(canvas, DegreesKt.degrees(f10), f11, f12);
    }

    public static /* synthetic */ void rotateRad$default(Canvas canvas, float f10, float f11, float f12, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f11 = 0.0f;
        }
        if ((i10 & 4) != 0) {
            f12 = 0.0f;
        }
        rotateRad(canvas, f10, f11, f12);
    }

    public static final void scale(Canvas canvas, float f10, float f11, float f12, float f13) {
        if (f10 == 1.0f && f11 == 1.0f) {
            return;
        }
        canvas.translate(f12, f13);
        canvas.scale(f10, f11);
        canvas.translate(-f12, -f13);
    }

    public static /* synthetic */ void scale$default(Canvas canvas, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f11 = f10;
        }
        scale(canvas, f10, f11, f12, f13);
    }

    public static final void withSave(Canvas canvas, r7.a<t0> aVar) {
        try {
            canvas.save();
            aVar.invoke();
        } finally {
            canvas.restore();
        }
    }

    public static final void withSaveLayer(Canvas canvas, Rect rect, Paint paint, r7.a<t0> aVar) {
        try {
            canvas.saveLayer(rect, paint);
            aVar.invoke();
        } finally {
            canvas.restore();
        }
    }
}
