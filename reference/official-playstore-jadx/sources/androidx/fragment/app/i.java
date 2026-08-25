package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ z1 f2734a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f2735b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f2736c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ k f2737d;

    public i(View view, ViewGroup viewGroup, k kVar, z1 z1Var) {
        this.f2734a = z1Var;
        this.f2735b = viewGroup;
        this.f2736c = view;
        this.f2737d = kVar;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        this.f2735b.post(new h(this, 0));
        if (b1.G(2)) {
            Log.v("FragmentManager", "Animation from operation " + this.f2734a + " has ended.");
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
        if (b1.G(2)) {
            Log.v("FragmentManager", "Animation from operation " + this.f2734a + " has reached onAnimationStart.");
        }
    }
}
