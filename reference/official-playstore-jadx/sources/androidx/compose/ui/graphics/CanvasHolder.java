package androidx.compose.ui.graphics;

import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086\b¢\u0006\u0004\b\u000b\u0010\fR \u0010\u000e\u001a\u00020\r8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/graphics/CanvasHolder;", "", "<init>", "()V", "Landroid/graphics/Canvas;", "targetCanvas", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Canvas;", "Lx6/t0;", "Lx6/n;", "block", "drawInto", "(Landroid/graphics/Canvas;Lr7/l;)V", "Landroidx/compose/ui/graphics/AndroidCanvas;", "androidCanvas", "Landroidx/compose/ui/graphics/AndroidCanvas;", "getAndroidCanvas", "()Landroidx/compose/ui/graphics/AndroidCanvas;", "getAndroidCanvas$annotations", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CanvasHolder {
    private final AndroidCanvas androidCanvas = new AndroidCanvas();

    public static /* synthetic */ void getAndroidCanvas$annotations() {
    }

    public final void drawInto(android.graphics.Canvas targetCanvas, l<? super Canvas, t0> block) {
        android.graphics.Canvas internalCanvas = getAndroidCanvas().getInternalCanvas();
        getAndroidCanvas().setInternalCanvas(targetCanvas);
        block.invoke(getAndroidCanvas());
        getAndroidCanvas().setInternalCanvas(internalCanvas);
    }

    public final AndroidCanvas getAndroidCanvas() {
        return this.androidCanvas;
    }
}
