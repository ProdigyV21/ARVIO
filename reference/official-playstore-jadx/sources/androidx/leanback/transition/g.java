package androidx.leanback.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Property;
import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2920a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2921b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f2922c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f2923d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f2924e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2925f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Property f2926g;

    public g(View view, Property property, float f10, float f11, int i10) {
        this.f2926g = property;
        this.f2922c = view;
        this.f2924e = f10;
        this.f2923d = f11;
        this.f2925f = i10;
        view.setVisibility(0);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        View view = this.f2922c;
        view.setTag(R.id.lb_slide_transition_value, new float[]{view.getTranslationX(), view.getTranslationY()});
        this.f2926g.set(view, Float.valueOf(this.f2924e));
        this.f2920a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        boolean z = this.f2920a;
        View view = this.f2922c;
        if (!z) {
            this.f2926g.set(view, Float.valueOf(this.f2924e));
        }
        view.setVisibility(this.f2925f);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        Property property = this.f2926g;
        View view = this.f2922c;
        this.f2921b = ((Float) property.get(view)).floatValue();
        property.set(view, Float.valueOf(this.f2923d));
        view.setVisibility(this.f2925f);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        Float fValueOf = Float.valueOf(this.f2921b);
        Property property = this.f2926g;
        View view = this.f2922c;
        property.set(view, fValueOf);
        view.setVisibility(0);
    }
}
