package androidx.leanback.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.transition.Transition;
import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends AnimatorListenerAdapter implements Transition.TransitionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f2927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f2928b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2929c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2930d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f2931e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f2932f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2933g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f2934h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f2935i;

    public h(View view, View view2, int i10, int i11, float f10, float f11) {
        this.f2928b = view;
        this.f2927a = view2;
        this.f2929c = i10 - Math.round(view.getTranslationX());
        this.f2930d = i11 - Math.round(view.getTranslationY());
        this.f2934h = f10;
        this.f2935i = f11;
        int[] iArr = (int[]) view2.getTag(R.id.transitionPosition);
        this.f2931e = iArr;
        if (iArr != null) {
            view2.setTag(R.id.transitionPosition, null);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        if (this.f2931e == null) {
            this.f2931e = new int[2];
        }
        int[] iArr = this.f2931e;
        float f10 = this.f2929c;
        View view = this.f2928b;
        iArr[0] = Math.round(view.getTranslationX() + f10);
        this.f2931e[1] = Math.round(view.getTranslationY() + this.f2930d);
        this.f2927a.setTag(R.id.transitionPosition, this.f2931e);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        View view = this.f2928b;
        this.f2932f = view.getTranslationX();
        this.f2933g = view.getTranslationY();
        view.setTranslationX(this.f2934h);
        view.setTranslationY(this.f2935i);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        float f10 = this.f2932f;
        View view = this.f2928b;
        view.setTranslationX(f10);
        view.setTranslationY(this.f2933g);
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionEnd(Transition transition) {
        float f10 = this.f2934h;
        View view = this.f2928b;
        view.setTranslationX(f10);
        view.setTranslationY(this.f2935i);
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionStart(Transition transition) {
    }
}
