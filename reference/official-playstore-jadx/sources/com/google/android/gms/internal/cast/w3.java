package com.google.android.gms.internal.cast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.Choreographer;

/* JADX INFO: loaded from: classes4.dex */
public final class w3 extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t.l0 f13663a = new t.l0(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AnimatorSet f13664b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f13665c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f13666d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final y0 f13667e;

    public w3(AnimatorSet animatorSet) {
        y0 y0Var = new y0();
        y0Var.f13692b = this;
        this.f13667e = y0Var;
        this.f13664b = animatorSet;
        this.f13665c = -1;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f13663a.put(animator, Boolean.TRUE);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        t.l0 l0Var = this.f13663a;
        if (l0Var.containsKey(animator) && ((Boolean) l0Var.get(animator)).booleanValue()) {
            return;
        }
        Choreographer choreographer = ((y3) y3.f13695b.get()).f13696a;
        final y0 y0Var = this.f13667e;
        if (((x3) y0Var.f13691a) == null) {
            y0Var.f13691a = new Choreographer.FrameCallback() { // from class: com.google.android.gms.internal.cast.x3
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j10) {
                    w3 w3Var = (w3) y0Var.f13692b;
                    w3Var.f13666d++;
                    AnimatorSet animatorSet = w3Var.f13664b;
                    t.l0 l0Var2 = w3Var.f13663a;
                    if ((l0Var2.containsKey(animatorSet) && ((Boolean) l0Var2.get(animatorSet)).booleanValue()) || animatorSet.isStarted()) {
                        return;
                    }
                    if (w3Var.f13665c != -1 && w3Var.f13666d >= 0) {
                        return;
                    }
                    animatorSet.start();
                }
            };
        }
        choreographer.postFrameCallback((x3) y0Var.f13691a);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f13663a.put(animator, Boolean.FALSE);
    }
}
