package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.core.view.x;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.internal.cast.w3;
import com.google.android.gms.internal.cast.z3;

/* JADX INFO: loaded from: classes4.dex */
public final class zzh extends ViewGroup {
    Animator zza;
    private final int[] zzb;
    private final Rect zzc;
    private final Rect zzd;
    private final OuterHighlightDrawable zze;
    private final InnerZoneDrawable zzf;
    private View zzg;
    private final zzi zzh;
    private final x zzi;
    private x zzj;
    private zzg zzk;
    private boolean zzl;
    private HelpTextView zzm;

    public zzh(Context context) {
        super(context);
        this.zzb = new int[2];
        this.zzc = new Rect();
        this.zzd = new Rect();
        setId(R.id.cast_featurehighlight_view);
        setWillNotDraw(false);
        InnerZoneDrawable innerZoneDrawable = new InnerZoneDrawable(context);
        this.zzf = innerZoneDrawable;
        innerZoneDrawable.setCallback(this);
        OuterHighlightDrawable outerHighlightDrawable = new OuterHighlightDrawable(context);
        this.zze = outerHighlightDrawable;
        outerHighlightDrawable.setCallback(this);
        this.zzh = new zzi(this);
        x xVar = new x(context, new zza(this));
        this.zzi = xVar;
        xVar.f2332a.setIsLongpressEnabled(false);
        setVisibility(8);
    }

    private final void zzo(Animator animator) {
        Animator animator2 = this.zza;
        if (animator2 != null) {
            animator2.cancel();
        }
        this.zza = animator;
        animator.start();
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.save();
        OuterHighlightDrawable outerHighlightDrawable = this.zze;
        outerHighlightDrawable.draw(canvas);
        this.zzf.draw(canvas);
        View view = this.zzg;
        if (view == null) {
            throw new IllegalStateException("Neither target view nor drawable was set");
        }
        if (view.getParent() != null) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.zzg.getWidth(), this.zzg.getHeight(), Bitmap.Config.ARGB_8888);
            this.zzg.draw(new Canvas(bitmapCreateBitmap));
            int iZza = outerHighlightDrawable.zza();
            int iRed = Color.red(iZza);
            int iGreen = Color.green(iZza);
            int iBlue = Color.blue(iZza);
            for (int i10 = 0; i10 < bitmapCreateBitmap.getHeight(); i10++) {
                for (int i11 = 0; i11 < bitmapCreateBitmap.getWidth(); i11++) {
                    int pixel = bitmapCreateBitmap.getPixel(i11, i10);
                    if (Color.alpha(pixel) != 0) {
                        bitmapCreateBitmap.setPixel(i11, i10, Color.argb(Color.alpha(pixel), iRed, iGreen, iBlue));
                    }
                }
            }
            Rect rect = this.zzc;
            canvas.drawBitmap(bitmapCreateBitmap, rect.left, rect.top, (Paint) null);
        }
        canvas.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        View view = this.zzg;
        if (view == null) {
            throw new IllegalStateException("Target view must be set before layout");
        }
        if (view.getParent() != null) {
            int[] iArr = this.zzb;
            View view2 = this.zzg;
            getLocationInWindow(iArr);
            int i14 = iArr[0];
            int i15 = iArr[1];
            view2.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i14;
            iArr[1] = iArr[1] - i15;
        }
        Rect rect = this.zzc;
        int[] iArr2 = this.zzb;
        int i16 = iArr2[0];
        rect.set(i16, iArr2[1], this.zzg.getWidth() + i16, this.zzg.getHeight() + iArr2[1]);
        Rect rect2 = this.zzd;
        rect2.set(i10, i11, i12, i13);
        this.zze.setBounds(rect2);
        this.zzf.setBounds(rect2);
        this.zzh.zza(rect, rect2);
    }

    @Override // android.view.View
    public final void onMeasure(int i10, int i11) {
        setMeasuredDimension(View.resolveSize(View.MeasureSpec.getSize(i10), i10), View.resolveSize(View.MeasureSpec.getSize(i11), i11));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.zzl = this.zzc.contains((int) motionEvent.getX(), (int) motionEvent.getY());
            actionMasked = 0;
        }
        if (!this.zzl) {
            this.zzi.f2332a.onTouchEvent(motionEvent);
            return true;
        }
        x xVar = this.zzj;
        if (xVar != null) {
            xVar.f2332a.onTouchEvent(motionEvent);
            if (actionMasked == 1) {
                motionEvent = MotionEvent.obtain(motionEvent);
                motionEvent.setAction(3);
            }
        }
        if (this.zzg.getParent() != null) {
            this.zzg.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.zze || drawable == this.zzf || drawable == null;
    }

    public final void zza(View view, View view2, boolean z, zzg zzgVar) {
        this.zzg = view;
        this.zzk = zzgVar;
        x xVar = new x(getContext(), new zzb(this, view, true, zzgVar));
        this.zzj = xVar;
        xVar.f2332a.setIsLongpressEnabled(false);
        setVisibility(4);
    }

    public final void zzb(Runnable runnable) {
        addOnLayoutChangeListener(new zzc(this, null));
    }

    public final void zzc() {
        if (this.zzg == null) {
            throw new IllegalStateException("Target view must be set before animation");
        }
        setVisibility(0);
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", 0.0f, 1.0f).setDuration(350L);
        Interpolator interpolator = z3.f13705a;
        duration.setInterpolator(interpolator);
        Rect rect = this.zzc;
        OuterHighlightDrawable outerHighlightDrawable = this.zze;
        float fExactCenterX = rect.exactCenterX() - outerHighlightDrawable.zzd();
        float fExactCenterY = rect.exactCenterY() - outerHighlightDrawable.zze();
        InnerZoneDrawable innerZoneDrawable = this.zzf;
        Animator animatorZzg = outerHighlightDrawable.zzg(fExactCenterX, fExactCenterY);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(innerZoneDrawable, PropertyValuesHolder.ofFloat("scale", 0.0f, 1.0f), PropertyValuesHolder.ofInt("alpha", 0, 255));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(interpolator);
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(350L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, animatorZzg, duration2);
        animatorSet.addListener(new zzd(this));
        zzo(animatorSet);
    }

    public final void zzd(Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", 0.0f).setDuration(200L);
        Interpolator interpolator = z3.f13706b;
        duration.setInterpolator(interpolator);
        OuterHighlightDrawable outerHighlightDrawable = this.zze;
        Rect rect = this.zzc;
        float fExactCenterX = rect.exactCenterX() - outerHighlightDrawable.zzd();
        float fExactCenterY = rect.exactCenterY() - outerHighlightDrawable.zze();
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(outerHighlightDrawable, PropertyValuesHolder.ofFloat("scale", 0.0f), PropertyValuesHolder.ofFloat("translationX", 0.0f, fExactCenterX), PropertyValuesHolder.ofFloat("translationY", 0.0f, fExactCenterY), PropertyValuesHolder.ofInt("alpha", 0));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(interpolator);
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(200L);
        Animator animatorZzb = this.zzf.zzb();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, duration2, animatorZzb);
        animatorSet.addListener(new zzf(this, runnable));
        zzo(animatorSet);
    }

    public final void zze(Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", 0.0f).setDuration(200L);
        Interpolator interpolator = z3.f13706b;
        duration.setInterpolator(interpolator);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zze, PropertyValuesHolder.ofFloat("scale", 1.125f), PropertyValuesHolder.ofInt("alpha", 0));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(interpolator);
        Animator duration2 = objectAnimatorOfPropertyValuesHolder.setDuration(200L);
        Animator animatorZzb = this.zzf.zzb();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, duration2, animatorZzb);
        animatorSet.addListener(new zze(this, runnable));
        zzo(animatorSet);
    }

    public final void zzf(int i10) {
        this.zze.zzb(i10);
    }

    public final View zzg() {
        return this.zzm.asView();
    }

    public final OuterHighlightDrawable zzh() {
        return this.zze;
    }

    public final InnerZoneDrawable zzi() {
        return this.zzf;
    }

    public final /* synthetic */ boolean zzj(float f10, float f11) {
        return this.zzd.contains(Math.round(f10), Math.round(f11));
    }

    public final Animator zzk() {
        AnimatorSet animatorSet = new AnimatorSet();
        InnerZoneDrawable innerZoneDrawable = this.zzf;
        ObjectAnimator duration = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", 1.0f, 1.1f).setDuration(500L);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", 1.1f, 1.0f).setDuration(500L);
        ObjectAnimator duration3 = ObjectAnimator.ofPropertyValuesHolder(innerZoneDrawable, PropertyValuesHolder.ofFloat("pulseScale", 1.1f, 2.0f), PropertyValuesHolder.ofFloat("pulseAlpha", 1.0f, 0.0f)).setDuration(500L);
        animatorSet.play(duration);
        animatorSet.play(duration2).with(duration3).after(duration);
        animatorSet.setInterpolator(z3.f13707c);
        animatorSet.setStartDelay(500L);
        animatorSet.addListener(new w3(animatorSet));
        return animatorSet;
    }

    public final /* synthetic */ OuterHighlightDrawable zzl() {
        return this.zze;
    }

    public final /* synthetic */ zzg zzm() {
        return this.zzk;
    }

    public final void zzn(HelpTextView helpTextView) {
        helpTextView.getClass();
        this.zzm = helpTextView;
        addView(helpTextView.asView(), 0);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }
}
