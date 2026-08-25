package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes3.dex */
public final class b1 implements g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Application f3211a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f1 f3212b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Bundle f3213c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r f3214d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b2.e f3215e;

    public b1(Application application, b2.g gVar, Bundle bundle) {
        f1 f1Var;
        this.f3215e = gVar.getSavedStateRegistry();
        this.f3214d = gVar.getLifecycle();
        this.f3213c = bundle;
        this.f3211a = application;
        if (application != null) {
            if (f1.f3230d == null) {
                f1.f3230d = new f1(application);
            }
            f1Var = f1.f3230d;
        } else {
            f1Var = new f1(null);
        }
        this.f3212b = f1Var;
    }

    public final d1 a(Class cls, String str) {
        r rVar = this.f3214d;
        if (rVar == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean zIsAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
        Application application = this.f3211a;
        Constructor constructorA = (!zIsAssignableFrom || application == null) ? c1.a(c1.f3221b, cls) : c1.a(c1.f3220a, cls);
        if (constructorA == null) {
            if (application != null) {
                return this.f3212b.create(cls);
            }
            if (y0.f3312b == null) {
                y0.f3312b = new y0(1);
            }
            return y0.f3312b.create(cls);
        }
        b2.e eVar = this.f3215e;
        Bundle bundleA = eVar.a(str);
        Class[] clsArr = w0.f3303f;
        w0 w0VarB = z0.b(bundleA, this.f3213c);
        x0 x0Var = new x0(str, w0VarB);
        if (x0Var.f3311m) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        x0Var.f3311m = true;
        rVar.addObserver(x0Var);
        eVar.c(str, w0VarB.f3308e);
        z0.k(eVar, rVar);
        d1 d1VarB = (!zIsAssignableFrom || application == null) ? c1.b(cls, constructorA, w0VarB) : c1.b(cls, constructorA, application, w0VarB);
        d1VarB.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", x0Var);
        return d1VarB;
    }

    public final void b(d1 d1Var) {
        r rVar = this.f3214d;
        if (rVar != null) {
            z0.a(d1Var, this.f3215e, rVar);
        }
    }

    @Override // androidx.lifecycle.g1
    public final d1 create(Class cls, o1.c cVar) {
        String str = (String) cVar.a(e1.f3226b);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if (cVar.a(z0.f3316a) == null || cVar.a(z0.f3317b) == null) {
            if (this.f3214d != null) {
                return a(cls, str);
            }
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
        Application application = (Application) cVar.a(e1.f3225a);
        boolean zIsAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
        Constructor constructorA = (!zIsAssignableFrom || application == null) ? c1.a(c1.f3221b, cls) : c1.a(c1.f3220a, cls);
        return constructorA == null ? this.f3212b.create(cls, cVar) : (!zIsAssignableFrom || application == null) ? c1.b(cls, constructorA, z0.c(cVar)) : c1.b(cls, constructorA, application, z0.c(cVar));
    }

    @Override // androidx.lifecycle.g1
    public final d1 create(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return a(cls, canonicalName);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
