package com.google.accompanist.drawablepainter;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidColorFilter_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.unit.LayoutDirection;
import j8.t0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import qb.d;
import t7.a;
import x6.i0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/accompanist/drawablepainter/DrawablePainter;", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/runtime/RememberObserver;", "drawablepainter_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DrawablePainter extends Painter implements RememberObserver {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Drawable f12557i;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final MutableState f12559m;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final MutableState f12558l = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i0 f12560n = new i0(new t0(this, 13));

    public DrawablePainter(Drawable drawable) {
        this.f12557i = drawable;
        this.f12559m = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m3293boximpl(DrawablePainterKt.a(drawable)), null, 2, null);
        if (drawable.getIntrinsicWidth() < 0 || drawable.getIntrinsicHeight() < 0) {
            return;
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final boolean applyAlpha(float f10) {
        this.f12557i.setAlpha(d.n(a.M(f10 * 255), 0, 255));
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final boolean applyColorFilter(ColorFilter colorFilter) {
        this.f12557i.setColorFilter(colorFilter != null ? AndroidColorFilter_androidKt.asAndroidColorFilter(colorFilter) : null);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final boolean applyLayoutDirection(LayoutDirection layoutDirection) {
        int i10 = y2.a.f22873a[layoutDirection.ordinal()];
        int i11 = 1;
        if (i10 == 1) {
            i11 = 0;
        } else if (i10 != 2) {
            throw new NoWhenBranchMatchedException();
        }
        return this.f12557i.setLayoutDirection(i11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public final long getIntrinsicSize() {
        return ((Size) this.f12559m.getValue()).getPackedValue();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onAbandoned() {
        onForgotten();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.graphics.painter.Painter
    public final void onDraw(DrawScope drawScope) {
        Canvas canvas = drawScope.getDrawContext().getCanvas();
        ((Number) this.f12558l.getValue()).intValue();
        int iM = a.M(Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()));
        int iM2 = a.M(Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc()));
        Drawable drawable = this.f12557i;
        drawable.setBounds(0, 0, iM, iM2);
        try {
            canvas.save();
            drawable.draw(AndroidCanvas_androidKt.getNativeCanvas(canvas));
        } finally {
            canvas.restore();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.RememberObserver
    public final void onForgotten() {
        Drawable drawable = this.f12557i;
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
        }
        drawable.setVisible(false, false);
        drawable.setCallback(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.RememberObserver
    public final void onRemembered() {
        Drawable.Callback callback = (Drawable.Callback) this.f12560n.getValue();
        Drawable drawable = this.f12557i;
        drawable.setCallback(callback);
        drawable.setVisible(true, true);
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}
