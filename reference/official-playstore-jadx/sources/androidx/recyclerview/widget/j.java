package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4947a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ k f4948b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f4949c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ View f4950d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ m f4951e;

    public /* synthetic */ j(m mVar, k kVar, ViewPropertyAnimator viewPropertyAnimator, View view, int i10) {
        this.f4947a = i10;
        this.f4951e = mVar;
        this.f4948b = kVar;
        this.f4949c = viewPropertyAnimator;
        this.f4950d = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f4947a) {
            case 0:
                this.f4949c.setListener(null);
                View view = this.f4950d;
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                k kVar = this.f4948b;
                p1 p1Var = kVar.f4960a;
                m mVar = this.f4951e;
                mVar.c(p1Var);
                mVar.f4999r.remove(kVar.f4960a);
                mVar.i();
                break;
            default:
                this.f4949c.setListener(null);
                View view2 = this.f4950d;
                view2.setAlpha(1.0f);
                view2.setTranslationX(0.0f);
                view2.setTranslationY(0.0f);
                k kVar2 = this.f4948b;
                p1 p1Var2 = kVar2.f4961b;
                m mVar2 = this.f4951e;
                mVar2.c(p1Var2);
                mVar2.f4999r.remove(kVar2.f4961b);
                mVar2.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f4947a) {
            case 0:
                p1 p1Var = this.f4948b.f4960a;
                this.f4951e.getClass();
                break;
            default:
                p1 p1Var2 = this.f4948b.f4961b;
                this.f4951e.getClass();
                break;
        }
    }
}
