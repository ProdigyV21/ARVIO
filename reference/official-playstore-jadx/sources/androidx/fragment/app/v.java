package androidx.fragment.app;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class v implements androidx.lifecycle.w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c0 f2846i;

    public v(c0 c0Var) {
        this.f2846i = c0Var;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        View view;
        if (pVar != androidx.lifecycle.p.ON_STOP || (view = this.f2846i.mView) == null) {
            return;
        }
        view.cancelPendingInputEvents();
    }
}
