package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4932a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ p1 f4933b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f4934c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f4935d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ m f4936e;

    public h(m mVar, p1 p1Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f4936e = mVar;
        this.f4933b = p1Var;
        this.f4935d = viewPropertyAnimator;
        this.f4934c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.f4932a) {
            case 1:
                this.f4934c.setAlpha(1.0f);
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f4932a) {
            case 0:
                this.f4935d.setListener(null);
                this.f4934c.setAlpha(1.0f);
                m mVar = this.f4936e;
                p1 p1Var = this.f4933b;
                mVar.c(p1Var);
                mVar.f4998q.remove(p1Var);
                mVar.i();
                break;
            default:
                this.f4935d.setListener(null);
                m mVar2 = this.f4936e;
                p1 p1Var2 = this.f4933b;
                mVar2.c(p1Var2);
                mVar2.f4996o.remove(p1Var2);
                mVar2.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f4932a) {
            case 0:
                this.f4936e.getClass();
                break;
            default:
                this.f4936e.getClass();
                break;
        }
    }

    public h(m mVar, p1 p1Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.f4936e = mVar;
        this.f4933b = p1Var;
        this.f4934c = view;
        this.f4935d = viewPropertyAnimator;
    }
}
