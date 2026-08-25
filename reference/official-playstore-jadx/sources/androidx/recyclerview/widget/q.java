package androidx.recyclerview.widget;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes3.dex */
public final class q implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f5012a;

    public q(r rVar) {
        this.f5012a = rVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        r rVar = this.f5012a;
        rVar.f5017c.setAlpha(iFloatValue);
        rVar.f5018d.setAlpha(iFloatValue);
        rVar.f5032s.invalidate();
    }
}
