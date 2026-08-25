package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class m2 extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f2289b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2290c;

    public /* synthetic */ m2(Object obj, View view, int i10) {
        this.f2288a = i10;
        this.f2290c = obj;
        this.f2289b = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.f2288a) {
            case 0:
                ((o2) this.f2290c).a();
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f2288a) {
            case 0:
                ((o2) this.f2290c).c();
                break;
            default:
                b3 b3Var = (b3) this.f2290c;
                b3Var.f2205a.c(1.0f);
                w2.d(this.f2289b, b3Var);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.f2288a) {
            case 0:
                ((o2) this.f2290c).b();
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
