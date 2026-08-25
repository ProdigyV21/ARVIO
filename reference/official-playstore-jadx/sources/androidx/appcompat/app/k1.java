package androidx.appcompat.app;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class k1 extends n.b implements androidx.appcompat.view.menu.o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f1067m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final androidx.appcompat.view.menu.q f1068n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public io.sentry.internal.debugmeta.c f1069o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public WeakReference f1070p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ l1 f1071q;

    public k1(l1 l1Var, Context context, io.sentry.internal.debugmeta.c cVar) {
        this.f1071q = l1Var;
        this.f1067m = context;
        this.f1069o = cVar;
        androidx.appcompat.view.menu.q qVar = new androidx.appcompat.view.menu.q(context);
        qVar.f1352l = 1;
        this.f1068n = qVar;
        qVar.f1346e = this;
    }

    @Override // n.b
    public final void a() {
        l1 l1Var = this.f1071q;
        if (l1Var.f1101i != this) {
            return;
        }
        if (l1Var.f1107p) {
            l1Var.f1102j = this;
            l1Var.k = this.f1069o;
        } else {
            this.f1069o.t(this);
        }
        this.f1069o = null;
        l1Var.x(false);
        ActionBarContextView actionBarContextView = l1Var.f1098f;
        if (actionBarContextView.f1420u == null) {
            actionBarContextView.e();
        }
        l1Var.f1095c.setHideOnContentScrollEnabled(l1Var.f1112u);
        l1Var.f1101i = null;
    }

    @Override // androidx.appcompat.view.menu.o
    public final boolean b(androidx.appcompat.view.menu.q qVar, MenuItem menuItem) {
        io.sentry.internal.debugmeta.c cVar = this.f1069o;
        if (cVar != null) {
            return ((n.a) cVar.f17367l).i(this, menuItem);
        }
        return false;
    }

    @Override // n.b
    public final View c() {
        WeakReference weakReference = this.f1070p;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // n.b
    public final androidx.appcompat.view.menu.q d() {
        return this.f1068n;
    }

    @Override // n.b
    public final MenuInflater e() {
        return new n.j(this.f1067m);
    }

    @Override // androidx.appcompat.view.menu.o
    public final void f(androidx.appcompat.view.menu.q qVar) {
        if (this.f1069o == null) {
            return;
        }
        i();
        androidx.appcompat.widget.m mVar = this.f1071q.f1098f.f1413n;
        if (mVar != null) {
            mVar.l();
        }
    }

    @Override // n.b
    public final CharSequence g() {
        return this.f1071q.f1098f.getSubtitle();
    }

    @Override // n.b
    public final CharSequence h() {
        return this.f1071q.f1098f.getTitle();
    }

    @Override // n.b
    public final void i() {
        if (this.f1071q.f1101i != this) {
            return;
        }
        androidx.appcompat.view.menu.q qVar = this.f1068n;
        qVar.w();
        try {
            this.f1069o.e(this, qVar);
        } finally {
            qVar.v();
        }
    }

    @Override // n.b
    public final boolean j() {
        return this.f1071q.f1098f.C;
    }

    @Override // n.b
    public final void k(View view) {
        this.f1071q.f1098f.setCustomView(view);
        this.f1070p = new WeakReference(view);
    }

    @Override // n.b
    public final void l(int i10) {
        m(this.f1071q.f1093a.getResources().getString(i10));
    }

    @Override // n.b
    public final void m(CharSequence charSequence) {
        this.f1071q.f1098f.setSubtitle(charSequence);
    }

    @Override // n.b
    public final void n(int i10) {
        o(this.f1071q.f1093a.getResources().getString(i10));
    }

    @Override // n.b
    public final void o(CharSequence charSequence) {
        this.f1071q.f1098f.setTitle(charSequence);
    }

    @Override // n.b
    public final void p(boolean z) {
        this.f20373l = z;
        this.f1071q.f1098f.setTitleOptional(z);
    }
}
