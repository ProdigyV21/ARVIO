package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.R;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.core.graphics.a;
import com.google.android.gms.internal.cast.u0;
import com.google.android.gms.internal.cast.z3;

/* JADX INFO: loaded from: classes4.dex */
class OuterHighlightDrawable extends Drawable {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final Rect zzd = new Rect();
    private final Rect zze = new Rect();
    private final Paint zzf;
    private float zzg;
    private float zzh;
    private float zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private int zzm;

    public OuterHighlightDrawable(Context context) {
        Paint paint = new Paint();
        this.zzf = paint;
        this.zzh = 1.0f;
        this.zzk = 0.0f;
        this.zzl = 0.0f;
        this.zzm = 244;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        paint.setColor(a.e(typedValue.data, 244));
        this.zzm = paint.getAlpha();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Resources resources = context.getResources();
        this.zza = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_center_threshold);
        this.zzb = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_center_horizontal_offset);
        this.zzc = resources.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_outer_padding);
    }

    private static final float zzh(float f10, float f11, Rect rect) {
        float f12 = rect.left;
        float f13 = rect.top;
        float f14 = rect.right;
        float f15 = rect.bottom;
        float fA = u0.a(f10, f11, f12, f13);
        float fA2 = u0.a(f10, f11, f14, f13);
        float fA3 = u0.a(f10, f11, f14, f15);
        float fA4 = u0.a(f10, f11, f12, f15);
        if (fA <= fA2 || fA <= fA3 || fA <= fA4) {
            fA = (fA2 <= fA3 || fA2 <= fA4) ? fA3 <= fA4 ? fA4 : fA3 : fA2;
        }
        return (float) Math.ceil(fA);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.drawCircle(this.zzi + this.zzk, this.zzj + this.zzl, this.zzg * this.zzh, this.zzf);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.zzf.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i10) {
        this.zzf.setAlpha(i10);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.zzf.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setScale(float f10) {
        this.zzh = f10;
        invalidateSelf();
    }

    public void setTranslationX(float f10) {
        this.zzk = f10;
        invalidateSelf();
    }

    public void setTranslationY(float f10) {
        this.zzl = f10;
        invalidateSelf();
    }

    public final int zza() {
        return this.zzf.getColor();
    }

    public final void zzb(int i10) {
        Paint paint = this.zzf;
        paint.setColor(i10);
        this.zzm = paint.getAlpha();
        invalidateSelf();
    }

    public final void zzc(Rect rect, Rect rect2) {
        this.zzd.set(rect);
        this.zze.set(rect2);
        float fExactCenterX = rect.exactCenterX();
        float fExactCenterY = rect.exactCenterY();
        Rect bounds = getBounds();
        if (Math.min(fExactCenterY - bounds.top, bounds.bottom - fExactCenterY) < this.zza) {
            this.zzi = fExactCenterX;
            this.zzj = fExactCenterY;
        } else {
            this.zzi = fExactCenterX <= bounds.exactCenterX() ? rect2.exactCenterX() + this.zzb : rect2.exactCenterX() - this.zzb;
            fExactCenterY = rect2.exactCenterY();
            this.zzj = fExactCenterY;
        }
        this.zzg = this.zzc + Math.max(zzh(this.zzi, fExactCenterY, rect), zzh(this.zzi, this.zzj, rect2));
        invalidateSelf();
    }

    public final float zzd() {
        return this.zzi;
    }

    public final float zze() {
        return this.zzj;
    }

    public final boolean zzf(float f10, float f11) {
        return u0.a(f10, f11, this.zzi, this.zzj) < this.zzg;
    }

    public final Animator zzg(float f10, float f11) {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("scale", 0.0f, 1.0f), PropertyValuesHolder.ofFloat("translationX", f10, 0.0f), PropertyValuesHolder.ofFloat("translationY", f11, 0.0f), PropertyValuesHolder.ofInt("alpha", 0, this.zzm));
        objectAnimatorOfPropertyValuesHolder.setInterpolator(z3.f13705a);
        return objectAnimatorOfPropertyValuesHolder.setDuration(350L);
    }
}
