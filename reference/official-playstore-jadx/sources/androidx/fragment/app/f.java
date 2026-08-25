package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f2723a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f2724b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f2725c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ z1 f2726d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ k f2727e;

    public f(ViewGroup viewGroup, View view, boolean z, z1 z1Var, k kVar) {
        this.f2723a = viewGroup;
        this.f2724b = view;
        this.f2725c = z;
        this.f2726d = z1Var;
        this.f2727e = kVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ViewGroup viewGroup = this.f2723a;
        View view = this.f2724b;
        viewGroup.endViewTransition(view);
        boolean z = this.f2725c;
        z1 z1Var = this.f2726d;
        if (z) {
            a2.a(z1Var.f2890a, view);
        }
        this.f2727e.a();
        if (b1.G(2)) {
            Log.v("FragmentManager", "Animator from operation " + z1Var + " has ended.");
        }
    }
}
