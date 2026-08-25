package n;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.o;
import androidx.appcompat.view.menu.q;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class f extends b implements o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Context f20380m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ActionBarContextView f20381n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public io.sentry.internal.debugmeta.c f20382o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public WeakReference f20383p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f20384q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public q f20385r;

    @Override // n.b
    public final void a() {
        if (this.f20384q) {
            return;
        }
        this.f20384q = true;
        this.f20382o.t(this);
    }

    @Override // androidx.appcompat.view.menu.o
    public final boolean b(q qVar, MenuItem menuItem) {
        return ((a) this.f20382o.f17367l).i(this, menuItem);
    }

    @Override // n.b
    public final View c() {
        WeakReference weakReference = this.f20383p;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // n.b
    public final q d() {
        return this.f20385r;
    }

    @Override // n.b
    public final MenuInflater e() {
        return new j(this.f20381n.getContext());
    }

    @Override // androidx.appcompat.view.menu.o
    public final void f(q qVar) {
        i();
        androidx.appcompat.widget.m mVar = this.f20381n.f1413n;
        if (mVar != null) {
            mVar.l();
        }
    }

    @Override // n.b
    public final CharSequence g() {
        return this.f20381n.getSubtitle();
    }

    @Override // n.b
    public final CharSequence h() {
        return this.f20381n.getTitle();
    }

    @Override // n.b
    public final void i() {
        this.f20382o.e(this, this.f20385r);
    }

    @Override // n.b
    public final boolean j() {
        return this.f20381n.C;
    }

    @Override // n.b
    public final void k(View view) {
        this.f20381n.setCustomView(view);
        this.f20383p = view != null ? new WeakReference(view) : null;
    }

    @Override // n.b
    public final void l(int i10) {
        m(this.f20380m.getString(i10));
    }

    @Override // n.b
    public final void m(CharSequence charSequence) {
        this.f20381n.setSubtitle(charSequence);
    }

    @Override // n.b
    public final void n(int i10) {
        o(this.f20380m.getString(i10));
    }

    @Override // n.b
    public final void o(CharSequence charSequence) {
        this.f20381n.setTitle(charSequence);
    }

    @Override // n.b
    public final void p(boolean z) {
        this.f20373l = z;
        this.f20381n.setTitleOptional(z);
    }
}
