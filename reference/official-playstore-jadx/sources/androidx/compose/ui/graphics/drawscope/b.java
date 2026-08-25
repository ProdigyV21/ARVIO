package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    public static Canvas a(DrawContext drawContext) {
        return new EmptyCanvas();
    }

    public static Density b(DrawContext drawContext) {
        return DrawContextKt.getDefaultDensity();
    }

    public static LayoutDirection c(DrawContext drawContext) {
        return LayoutDirection.Ltr;
    }

    public static void d(DrawContext drawContext, Canvas canvas) {
    }

    public static void e(DrawContext drawContext, Density density) {
    }

    public static void f(DrawContext drawContext, LayoutDirection layoutDirection) {
    }
}
