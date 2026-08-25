package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public final class n0 implements View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ l1 f2792i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ o0 f2793l;

    public n0(o0 o0Var, l1 l1Var) {
        this.f2793l = o0Var;
        this.f2792i = l1Var;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        l1 l1Var = this.f2792i;
        c0 c0Var = l1Var.f2773c;
        l1Var.j();
        n.i((ViewGroup) c0Var.mView.getParent(), this.f2793l.f2804i.E()).g();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
