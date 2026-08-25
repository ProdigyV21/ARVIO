package androidx.leanback.widget;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes3.dex */
public final class e1 implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3079a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SearchOrbView f3080b;

    public /* synthetic */ e1(SearchOrbView searchOrbView, int i10) {
        this.f3079a = i10;
        this.f3080b = searchOrbView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f3079a) {
            case 0:
                this.f3080b.setOrbViewColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                break;
            default:
                this.f3080b.setSearchOrbZ(valueAnimator.getAnimatedFraction());
                break;
        }
    }
}
