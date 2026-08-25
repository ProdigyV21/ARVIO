package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public class n0 extends SeekBar {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o0 f1668i;

    public n0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarStyle);
        d4.a(getContext(), this);
        o0 o0Var = new o0(this);
        this.f1668i = o0Var;
        o0Var.b(attributeSet, R.attr.seekBarStyle);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        o0 o0Var = this.f1668i;
        n0 n0Var = o0Var.f1679e;
        Drawable drawable = o0Var.f1680f;
        if (drawable != null && drawable.isStateful() && drawable.setState(n0Var.getDrawableState())) {
            n0Var.invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1668i.f1680f;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f1668i.g(canvas);
    }
}
