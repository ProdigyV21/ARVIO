package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: classes3.dex */
public final class i0 extends AnimationSet implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ViewGroup f2738i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final View f2739l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2740m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2741n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2742o;

    public i0(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.f2742o = true;
        this.f2738i = viewGroup;
        this.f2739l = view;
        addAnimation(animation);
        viewGroup.post(this);
    }

    @Override // android.view.animation.AnimationSet, android.view.animation.Animation
    public final boolean getTransformation(long j10, Transformation transformation) {
        this.f2742o = true;
        if (this.f2740m) {
            return !this.f2741n;
        }
        if (!super.getTransformation(j10, transformation)) {
            this.f2740m = true;
            androidx.core.view.u0.a(this.f2738i, this);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.f2740m;
        ViewGroup viewGroup = this.f2738i;
        if (z || !this.f2742o) {
            viewGroup.endViewTransition(this.f2739l);
            this.f2741n = true;
        } else {
            this.f2742o = false;
            viewGroup.post(this);
        }
    }

    @Override // android.view.animation.Animation
    public final boolean getTransformation(long j10, Transformation transformation, float f10) {
        this.f2742o = true;
        if (this.f2740m) {
            return !this.f2741n;
        }
        if (!super.getTransformation(j10, transformation, f10)) {
            this.f2740m = true;
            androidx.core.view.u0.a(this.f2738i, this);
        }
        return true;
    }
}
