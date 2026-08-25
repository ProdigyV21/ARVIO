package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: classes3.dex */
public final class w1 implements androidx.lifecycle.k, b2.g, androidx.lifecycle.j1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c0 f2855i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.lifecycle.i1 f2856l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public androidx.lifecycle.g1 f2857m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public androidx.lifecycle.a0 f2858n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public b2.f f2859o = null;

    public w1(c0 c0Var, androidx.lifecycle.i1 i1Var) {
        this.f2855i = c0Var;
        this.f2856l = i1Var;
    }

    public final void a(androidx.lifecycle.p pVar) {
        this.f2858n.c(pVar);
    }

    public final void b() {
        if (this.f2858n == null) {
            this.f2858n = new androidx.lifecycle.a0(this);
            b2.f fVar = new b2.f(this);
            this.f2859o = fVar;
            fVar.a();
        }
    }

    @Override // androidx.lifecycle.k
    public final o1.c getDefaultViewModelCreationExtras() {
        Application application;
        c0 c0Var = this.f2855i;
        Context applicationContext = c0Var.requireContext().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            }
            if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            }
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        o1.d dVar = new o1.d(0);
        if (application != null) {
            dVar.b(androidx.lifecycle.e1.f3225a, application);
        }
        dVar.b(androidx.lifecycle.z0.f3316a, c0Var);
        dVar.b(androidx.lifecycle.z0.f3317b, this);
        if (c0Var.getArguments() != null) {
            dVar.b(androidx.lifecycle.z0.f3318c, c0Var.getArguments());
        }
        return dVar;
    }

    @Override // androidx.lifecycle.k
    public final androidx.lifecycle.g1 getDefaultViewModelProviderFactory() {
        Application application;
        c0 c0Var = this.f2855i;
        androidx.lifecycle.g1 defaultViewModelProviderFactory = c0Var.getDefaultViewModelProviderFactory();
        if (!defaultViewModelProviderFactory.equals(c0Var.mDefaultFactory)) {
            this.f2857m = defaultViewModelProviderFactory;
            return defaultViewModelProviderFactory;
        }
        if (this.f2857m == null) {
            Context applicationContext = c0Var.requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    application = null;
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            this.f2857m = new androidx.lifecycle.b1(application, c0Var, c0Var.getArguments());
        }
        return this.f2857m;
    }

    @Override // androidx.lifecycle.y
    public final androidx.lifecycle.r getLifecycle() {
        b();
        return this.f2858n;
    }

    @Override // b2.g
    public final b2.e getSavedStateRegistry() {
        b();
        return this.f2859o.f7121b;
    }

    @Override // androidx.lifecycle.j1
    public final androidx.lifecycle.i1 getViewModelStore() {
        b();
        return this.f2856l;
    }
}
