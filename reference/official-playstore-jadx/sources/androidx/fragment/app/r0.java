package androidx.fragment.app;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/* JADX INFO: loaded from: classes3.dex */
public final class r0 implements androidx.core.view.i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b1 f2830a;

    public r0(b1 b1Var) {
        this.f2830a = b1Var;
    }

    @Override // androidx.core.view.i0
    public final void a(Menu menu) {
        this.f2830a.p(menu);
    }

    @Override // androidx.core.view.i0
    public final void b(Menu menu) {
        this.f2830a.s(menu);
    }

    @Override // androidx.core.view.i0
    public final boolean c(MenuItem menuItem) {
        return this.f2830a.o(menuItem);
    }

    @Override // androidx.core.view.i0
    public final void d(Menu menu, MenuInflater menuInflater) {
        this.f2830a.j(menu, menuInflater);
    }
}
