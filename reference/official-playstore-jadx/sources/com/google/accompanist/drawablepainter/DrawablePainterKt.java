package com.google.accompanist.drawablepainter;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.painter.ColorPainter;
import androidx.compose.ui.graphics.painter.Painter;
import f4.f;
import kotlin.Metadata;
import y2.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroid/graphics/drawable/Drawable;", "drawable", "Landroidx/compose/ui/graphics/painter/Painter;", "rememberDrawablePainter", "(Landroid/graphics/drawable/Drawable;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/painter/Painter;", "drawablepainter_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class DrawablePainterKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f12561a = f.o(3, b.f22874i);

    public static final long a(Drawable drawable) {
        return (drawable.getIntrinsicWidth() < 0 || drawable.getIntrinsicHeight() < 0) ? Size.INSTANCE.m3313getUnspecifiedNHjbRc() : SizeKt.Size(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public static final Painter rememberDrawablePainter(Drawable drawable, Composer composer, int i10) {
        composer.startReplaceableGroup(1756822313);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1756822313, i10, -1, "com.google.accompanist.drawablepainter.rememberDrawablePainter (DrawablePainter.kt:151)");
        }
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(drawable);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            if (drawable == null) {
                objRememberedValue = EmptyPainter.f12562i;
            } else {
                objRememberedValue = drawable instanceof ColorDrawable ? new ColorPainter(ColorKt.Color(((ColorDrawable) drawable).getColor()), null) : new DrawablePainter(drawable.mutate());
            }
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Painter painter = (Painter) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return painter;
    }
}
