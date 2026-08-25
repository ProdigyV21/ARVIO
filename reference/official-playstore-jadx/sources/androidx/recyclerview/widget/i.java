package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: classes3.dex */
public final class i extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ p1 f4940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f4941b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f4942c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4943d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f4944e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ m f4945f;

    public i(m mVar, p1 p1Var, int i10, View view, int i11, ViewPropertyAnimator viewPropertyAnimator) {
        this.f4945f = mVar;
        this.f4940a = p1Var;
        this.f4941b = i10;
        this.f4942c = view;
        this.f4943d = i11;
        this.f4944e = viewPropertyAnimator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i10 = this.f4941b;
        View view = this.f4942c;
        if (i10 != 0) {
            view.setTranslationX(0.0f);
        }
        if (this.f4943d != 0) {
            view.setTranslationY(0.0f);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f4944e.setListener(null);
        m mVar = this.f4945f;
        p1 p1Var = this.f4940a;
        mVar.c(p1Var);
        mVar.f4997p.remove(p1Var);
        mVar.i();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f4945f.getClass();
    }
}
