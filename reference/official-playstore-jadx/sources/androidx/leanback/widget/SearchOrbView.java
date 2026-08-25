package androidx.leanback.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.view.b2;
import com.arvio.tv.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class SearchOrbView extends FrameLayout implements View.OnClickListener {
    public ValueAnimator A;
    public final e1 B;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View.OnClickListener f3018i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final View f3019l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final View f3020m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ImageView f3021n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Drawable f3022o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public f1 f3023p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float f3024q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f3025r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f3026s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final float f3027t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final float f3028u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ValueAnimator f3029v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f3030w;
    public boolean x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final ArgbEvaluator f3031y;
    public final e1 z;

    public SearchOrbView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchOrbViewStyle);
    }

    public final void a(boolean z) {
        float f10 = z ? this.f3024q : 1.0f;
        ViewPropertyAnimator viewPropertyAnimatorScaleY = this.f3019l.animate().scaleX(f10).scaleY(f10);
        long j10 = this.f3026s;
        viewPropertyAnimatorScaleY.setDuration(j10).start();
        if (this.A == null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.A = valueAnimatorOfFloat;
            valueAnimatorOfFloat.addUpdateListener(this.B);
        }
        if (z) {
            this.A.start();
        } else {
            this.A.reverse();
        }
        this.A.setDuration(j10);
        this.f3030w = z;
        b();
    }

    public final void b() {
        ValueAnimator valueAnimator = this.f3029v;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f3029v = null;
        }
        if (this.f3030w && this.x) {
            ValueAnimator valueAnimatorOfObject = ValueAnimator.ofObject(this.f3031y, Integer.valueOf(this.f3023p.f3081a), Integer.valueOf(this.f3023p.f3082b), Integer.valueOf(this.f3023p.f3081a));
            this.f3029v = valueAnimatorOfObject;
            valueAnimatorOfObject.setRepeatCount(-1);
            this.f3029v.setDuration(this.f3025r * 2);
            this.f3029v.addUpdateListener(this.z);
            this.f3029v.start();
        }
    }

    public float getFocusedZoom() {
        return this.f3024q;
    }

    public int getLayoutResourceId() {
        return R.layout.lb_search_orb;
    }

    public int getOrbColor() {
        return this.f3023p.f3081a;
    }

    public f1 getOrbColors() {
        return this.f3023p;
    }

    public Drawable getOrbIcon() {
        return this.f3022o;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.x = true;
        b();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        View.OnClickListener onClickListener = this.f3018i;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.x = false;
        b();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i10, Rect rect) {
        super.onFocusChanged(z, i10, rect);
        a(z);
    }

    public void setOnOrbClickedListener(View.OnClickListener onClickListener) {
        this.f3018i = onClickListener;
    }

    public void setOrbColor(int i10) {
        setOrbColors(new f1(i10, i10, 0));
    }

    public void setOrbColors(f1 f1Var) {
        this.f3023p = f1Var;
        this.f3021n.setColorFilter(f1Var.f3083c);
        if (this.f3029v == null) {
            setOrbViewColor(this.f3023p.f3081a);
        } else {
            this.f3030w = true;
            b();
        }
    }

    public void setOrbIcon(Drawable drawable) {
        this.f3022o = drawable;
        this.f3021n.setImageDrawable(drawable);
    }

    public void setOrbViewColor(int i10) {
        View view = this.f3020m;
        if (view.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) view.getBackground()).setColor(i10);
        }
    }

    public void setSearchOrbZ(float f10) {
        float f11 = this.f3028u;
        float f12 = this.f3027t;
        float f13 = ((f11 - f12) * f10) + f12;
        WeakHashMap weakHashMap = b2.f2200a;
        androidx.core.view.k1.x(this.f3020m, f13);
    }

    public SearchOrbView(Context context, AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        this.f3031y = new ArgbEvaluator();
        this.z = new e1(this, 0);
        this.B = new e1(this, 1);
        Resources resources = context.getResources();
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(getLayoutResourceId(), (ViewGroup) this, true);
        this.f3019l = viewInflate;
        this.f3020m = viewInflate.findViewById(R.id.search_orb);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.icon);
        this.f3021n = imageView;
        this.f3024q = context.getResources().getFraction(R.fraction.lb_search_orb_focused_zoom, 1, 1);
        this.f3025r = context.getResources().getInteger(R.integer.lb_search_orb_pulse_duration_ms);
        this.f3026s = context.getResources().getInteger(R.integer.lb_search_orb_scale_duration_ms);
        float dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.lb_search_orb_focused_z);
        this.f3028u = dimensionPixelSize;
        this.f3027t = context.getResources().getDimensionPixelSize(R.dimen.lb_search_orb_unfocused_z);
        int[] iArr = k1.a.f19458h;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i10, 0);
        b2.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i10);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(2);
        setOrbIcon(drawable == null ? resources.getDrawable(2131231268) : drawable);
        int color = typedArrayObtainStyledAttributes.getColor(1, resources.getColor(R.color.lb_default_search_color));
        setOrbColors(new f1(color, typedArrayObtainStyledAttributes.getColor(0, color), typedArrayObtainStyledAttributes.getColor(3, 0)));
        typedArrayObtainStyledAttributes.recycle();
        setFocusable(true);
        setClipChildren(false);
        setOnClickListener(this);
        setSoundEffectsEnabled(false);
        setSearchOrbZ(0.0f);
        androidx.core.view.k1.x(imageView, dimensionPixelSize);
    }
}
