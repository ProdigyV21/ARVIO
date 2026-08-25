package androidx.appcompat.widget;

import android.widget.AbsListView;

/* JADX INFO: loaded from: classes.dex */
public final class u2 implements AbsListView.OnScrollListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ w2 f1772a;

    public u2(w2 w2Var) {
        this.f1772a = w2Var;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i10, int i11, int i12) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i10) {
        w2 w2Var = this.f1772a;
        s2 s2Var = w2Var.A;
        i0 i0Var = w2Var.I;
        if (i10 != 1 || i0Var.getInputMethodMode() == 2 || i0Var.getContentView() == null) {
            return;
        }
        w2Var.E.removeCallbacks(s2Var);
        s2Var.run();
    }
}
