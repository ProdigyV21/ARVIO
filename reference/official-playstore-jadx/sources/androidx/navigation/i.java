package androidx.navigation;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.e1;
import androidx.lifecycle.g1;
import androidx.lifecycle.i1;
import androidx.lifecycle.j1;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements androidx.lifecycle.y, j1, androidx.lifecycle.k, b2.g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f4680i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public d0 f4681l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Bundle f4682m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public androidx.lifecycle.q f4683n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final x0 f4684o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f4685p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Bundle f4686q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final androidx.lifecycle.a0 f4687r = new androidx.lifecycle.a0(this);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final b2.f f4688s = new b2.f(this);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f4689t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public androidx.lifecycle.q f4690u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final androidx.lifecycle.b1 f4691v;

    public i(Context context, d0 d0Var, Bundle bundle, androidx.lifecycle.q qVar, x0 x0Var, String str, Bundle bundle2) {
        this.f4680i = context;
        this.f4681l = d0Var;
        this.f4682m = bundle;
        this.f4683n = qVar;
        this.f4684o = x0Var;
        this.f4685p = str;
        this.f4686q = bundle2;
        x6.i0 i0Var = new x6.i0(new a8.g0(this, 7));
        this.f4690u = androidx.lifecycle.q.f3267l;
        this.f4691v = (androidx.lifecycle.b1) i0Var.getValue();
    }

    public final Bundle a() {
        Bundle bundle = this.f4682m;
        if (bundle == null) {
            return null;
        }
        return new Bundle(bundle);
    }

    public final void b(androidx.lifecycle.q qVar) {
        this.f4690u = qVar;
        c();
    }

    public final void c() {
        if (!this.f4689t) {
            b2.f fVar = this.f4688s;
            fVar.a();
            this.f4689t = true;
            if (this.f4684o != null) {
                androidx.lifecycle.z0.d(this);
            }
            fVar.b(this.f4686q);
        }
        int iOrdinal = this.f4683n.ordinal();
        int iOrdinal2 = this.f4690u.ordinal();
        androidx.lifecycle.a0 a0Var = this.f4687r;
        if (iOrdinal < iOrdinal2) {
            a0Var.e(this.f4683n);
        } else {
            a0Var.e(this.f4690u);
        }
    }

    public final boolean equals(Object obj) {
        Set<String> setKeySet;
        if (obj != null && (obj instanceof i)) {
            i iVar = (i) obj;
            Bundle bundle = iVar.f4682m;
            if (kotlin.jvm.internal.p.a(this.f4685p, iVar.f4685p) && kotlin.jvm.internal.p.a(this.f4681l, iVar.f4681l) && kotlin.jvm.internal.p.a(this.f4687r, iVar.f4687r) && kotlin.jvm.internal.p.a(this.f4688s.f7121b, iVar.f4688s.f7121b)) {
                Bundle bundle2 = this.f4682m;
                if (kotlin.jvm.internal.p.a(bundle2, bundle)) {
                    return true;
                }
                if (bundle2 != null && (setKeySet = bundle2.keySet()) != null) {
                    Set<String> set = setKeySet;
                    if ((set instanceof Collection) && set.isEmpty()) {
                        return true;
                    }
                    for (String str : set) {
                        if (!kotlin.jvm.internal.p.a(bundle2.get(str), bundle != null ? bundle.get(str) : null)) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.lifecycle.k
    public final o1.c getDefaultViewModelCreationExtras() {
        o1.d dVar = new o1.d(0);
        Context context = this.f4680i;
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        Application application = applicationContext instanceof Application ? (Application) applicationContext : null;
        if (application != null) {
            dVar.b(e1.f3225a, application);
        }
        dVar.b(androidx.lifecycle.z0.f3316a, this);
        dVar.b(androidx.lifecycle.z0.f3317b, this);
        Bundle bundleA = a();
        if (bundleA != null) {
            dVar.b(androidx.lifecycle.z0.f3318c, bundleA);
        }
        return dVar;
    }

    @Override // androidx.lifecycle.k
    public final g1 getDefaultViewModelProviderFactory() {
        return this.f4691v;
    }

    @Override // androidx.lifecycle.y
    public final androidx.lifecycle.r getLifecycle() {
        return this.f4687r;
    }

    @Override // b2.g
    public final b2.e getSavedStateRegistry() {
        return this.f4688s.f7121b;
    }

    @Override // androidx.lifecycle.j1
    public final i1 getViewModelStore() {
        if (!this.f4689t) {
            throw new IllegalStateException("You cannot access the NavBackStackEntry's ViewModels until it is added to the NavController's back stack (i.e., the Lifecycle of the NavBackStackEntry reaches the CREATED state).");
        }
        if (this.f4687r.f3198c == androidx.lifecycle.q.f3266i) {
            throw new IllegalStateException("You cannot access the NavBackStackEntry's ViewModels after the NavBackStackEntry is destroyed.");
        }
        x0 x0Var = this.f4684o;
        if (x0Var != null) {
            return x0Var.getViewModelStore(this.f4685p);
        }
        throw new IllegalStateException("You must call setViewModelStore() on your NavHostController before accessing the ViewModelStore of a navigation graph.");
    }

    public final int hashCode() {
        Set<String> setKeySet;
        int iHashCode = this.f4681l.hashCode() + (this.f4685p.hashCode() * 31);
        Bundle bundle = this.f4682m;
        if (bundle != null && (setKeySet = bundle.keySet()) != null) {
            Iterator<T> it = setKeySet.iterator();
            while (it.hasNext()) {
                int i10 = iHashCode * 31;
                Object obj = bundle.get((String) it.next());
                iHashCode = i10 + (obj != null ? obj.hashCode() : 0);
            }
        }
        return this.f4688s.f7121b.hashCode() + ((this.f4687r.hashCode() + (iHashCode * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i.class.getSimpleName());
        sb2.append("(" + this.f4685p + ')');
        sb2.append(" destination=");
        sb2.append(this.f4681l);
        return sb2.toString();
    }
}
