package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public final class SeekBar extends View {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final RectF f3032i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final RectF f3033l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final RectF f3034m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Paint f3035n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Paint f3036o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Paint f3037p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Paint f3038q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f3039r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3040s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3041t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3042u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3043v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f3044w;
    public int x;

    public SeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3032i = new RectF();
        this.f3033l = new RectF();
        this.f3034m = new RectF();
        Paint paint = new Paint(1);
        this.f3035n = paint;
        Paint paint2 = new Paint(1);
        this.f3036o = paint2;
        Paint paint3 = new Paint(1);
        this.f3037p = paint3;
        Paint paint4 = new Paint(1);
        this.f3038q = paint4;
        setWillNotDraw(false);
        paint3.setColor(-7829368);
        paint.setColor(-3355444);
        paint2.setColor(-65536);
        paint4.setColor(-1);
        this.f3044w = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_progressbar_bar_height);
        this.x = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_progressbar_active_bar_height);
        this.f3043v = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_progressbar_active_radius);
    }

    public final void a() {
        int i10 = isFocused() ? this.x : this.f3044w;
        int width = getWidth();
        int height = getHeight();
        int i11 = (height - i10) / 2;
        int i12 = this.f3044w;
        float f10 = i11;
        float f11 = height - i11;
        this.f3034m.set(i12 / 2, f10, width - (i12 / 2), f11);
        int i13 = isFocused() ? this.f3043v : this.f3044w / 2;
        float f12 = width - (i13 * 2);
        float f13 = (this.f3039r / this.f3041t) * f12;
        int i14 = this.f3044w;
        RectF rectF = this.f3032i;
        rectF.set(i14 / 2, f10, (i14 / 2) + f13, f11);
        this.f3033l.set(rectF.right, f10, (this.f3044w / 2) + ((this.f3040s / this.f3041t) * f12), f11);
        this.f3042u = i13 + ((int) f13);
        invalidate();
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return android.widget.SeekBar.class.getName();
    }

    public int getMax() {
        return this.f3041t;
    }

    public int getProgress() {
        return this.f3039r;
    }

    public int getSecondProgress() {
        return this.f3040s;
    }

    public int getSecondaryProgressColor() {
        return this.f3035n.getColor();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f10 = isFocused() ? this.f3043v : this.f3044w / 2;
        canvas.drawRoundRect(this.f3034m, f10, f10, this.f3037p);
        RectF rectF = this.f3033l;
        if (rectF.right > rectF.left) {
            canvas.drawRoundRect(rectF, f10, f10, this.f3035n);
        }
        canvas.drawRoundRect(this.f3032i, f10, f10, this.f3036o);
        canvas.drawCircle(this.f3042u, getHeight() / 2, f10, this.f3038q);
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i10, Rect rect) {
        super.onFocusChanged(z, i10, rect);
        a();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i10, int i11, int i12, int i13) {
        super.onSizeChanged(i10, i11, i12, i13);
        a();
    }

    public void setAccessibilitySeekListener(g1 g1Var) {
    }

    public void setActiveBarHeight(int i10) {
        this.x = i10;
        a();
    }

    public void setActiveRadius(int i10) {
        this.f3043v = i10;
        a();
    }

    public void setBarHeight(int i10) {
        this.f3044w = i10;
        a();
    }

    public void setMax(int i10) {
        this.f3041t = i10;
        a();
    }

    public void setProgress(int i10) {
        int i11 = this.f3041t;
        if (i10 > i11) {
            i10 = i11;
        } else if (i10 < 0) {
            i10 = 0;
        }
        this.f3039r = i10;
        a();
    }

    public void setProgressColor(int i10) {
        this.f3036o.setColor(i10);
    }

    public void setSecondaryProgress(int i10) {
        int i11 = this.f3041t;
        if (i10 > i11) {
            i10 = i11;
        } else if (i10 < 0) {
            i10 = 0;
        }
        this.f3040s = i10;
        a();
    }

    public void setSecondaryProgressColor(int i10) {
        this.f3035n.setColor(i10);
    }
}
