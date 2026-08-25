package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes3.dex */
public final class p extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5010a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ r f5011b;

    public p(r rVar) {
        this.f5011b = rVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f5010a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.f5010a) {
            this.f5010a = false;
            return;
        }
        r rVar = this.f5011b;
        if (((Float) rVar.z.getAnimatedValue()).floatValue() == 0.0f) {
            rVar.A = 0;
            rVar.f(0);
        } else {
            rVar.A = 2;
            rVar.f5032s.invalidate();
        }
    }
}
