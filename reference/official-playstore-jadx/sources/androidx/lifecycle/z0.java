package androidx.lifecycle;

import android.os.Bundle;
import android.view.View;
import com.arflix.tv.MainActivity;
import com.arvio.tv.R;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicReference;
import ka.p2;

/* JADX INFO: loaded from: classes3.dex */
public abstract class z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e1 f3316a = new e1();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e1 f3317b = new e1();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e1 f3318c = new e1();

    public static final void a(d1 d1Var, b2.e eVar, r rVar) {
        boolean z;
        x0 x0Var = (x0) d1Var.getTag("androidx.lifecycle.savedstate.vm.tag");
        if (x0Var == null || (z = x0Var.f3311m)) {
            return;
        }
        if (z) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        x0Var.f3311m = true;
        rVar.addObserver(x0Var);
        eVar.c(x0Var.f3309i, x0Var.f3310l.f3308e);
        k(eVar, rVar);
    }

    public static w0 b(Bundle bundle, Bundle bundle2) {
        if (bundle == null) {
            if (bundle2 == null) {
                return new w0();
            }
            HashMap map = new HashMap();
            for (String str : bundle2.keySet()) {
                map.put(str, bundle2.get(str));
            }
            return new w0(map);
        }
        bundle.setClassLoader(w0.class.getClassLoader());
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(UserMetadata.KEYDATA_FILENAME);
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = parcelableArrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            linkedHashMap.put((String) parcelableArrayList.get(i10), parcelableArrayList2.get(i10));
        }
        return new w0(linkedHashMap);
    }

    public static final w0 c(o1.c cVar) {
        b2.g gVar = (b2.g) cVar.a(f3316a);
        if (gVar == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        }
        j1 j1Var = (j1) cVar.a(f3317b);
        if (j1Var == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        Bundle bundle = (Bundle) cVar.a(f3318c);
        e1 e1Var = e1.f3226b;
        String str = (String) cVar.a(e1Var);
        if (str == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
        }
        b2.d dVarB = gVar.getSavedStateRegistry().b();
        a1 a1Var = dVarB instanceof a1 ? (a1) dVarB : null;
        if (a1Var == null) {
            throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
        }
        y0 y0Var = new y0(0);
        i1 viewModelStore = j1Var.getViewModelStore();
        o1.c defaultViewModelCreationExtras = j1Var instanceof k ? ((k) j1Var).getDefaultViewModelCreationExtras() : o1.a.f20721b;
        d1 d1VarB = viewModelStore.b("androidx.lifecycle.internal.SavedStateHandlesVM");
        if (!SavedStateHandlesVM.class.isInstance(d1VarB)) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(defaultViewModelCreationExtras.f20722a);
            linkedHashMap.put(e1Var, "androidx.lifecycle.internal.SavedStateHandlesVM");
            try {
                d1VarB = new SavedStateHandlesVM();
                d1 d1Var = (d1) viewModelStore.f3252a.put("androidx.lifecycle.internal.SavedStateHandlesVM", d1VarB);
                if (d1Var != null) {
                    d1Var.onCleared();
                }
            } catch (AbstractMethodError unused) {
                y0Var.create(SavedStateHandlesVM.class);
                throw null;
            }
        }
        SavedStateHandlesVM savedStateHandlesVM = (SavedStateHandlesVM) d1VarB;
        w0 w0Var = savedStateHandlesVM.getHandles().get(str);
        if (w0Var != null) {
            return w0Var;
        }
        Class[] clsArr = w0.f3303f;
        a1Var.b();
        Bundle bundle2 = a1Var.f3207c;
        Bundle bundle3 = bundle2 != null ? bundle2.getBundle(str) : null;
        Bundle bundle4 = a1Var.f3207c;
        if (bundle4 != null) {
            bundle4.remove(str);
        }
        Bundle bundle5 = a1Var.f3207c;
        if (bundle5 != null && bundle5.isEmpty()) {
            a1Var.f3207c = null;
        }
        w0 w0VarB = b(bundle3, bundle);
        savedStateHandlesVM.getHandles().put(str, w0VarB);
        return w0VarB;
    }

    public static final void d(b2.g gVar) {
        q currentState = gVar.getLifecycle().getCurrentState();
        if (currentState != q.f3267l && currentState != q.f3268m) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (gVar.getSavedStateRegistry().b() == null) {
            a1 a1Var = new a1(gVar.getSavedStateRegistry(), (j1) gVar);
            gVar.getSavedStateRegistry().c("androidx.lifecycle.internal.SavedStateHandlesProvider", a1Var);
            gVar.getLifecycle().addObserver(new b2.b(a1Var, 3));
        }
    }

    public static final y e(View view) {
        return (y) ga.r.K(ga.r.Q(ga.r.M(view, k1.f3253l), k1.f3254m));
    }

    public static final j1 f(View view) {
        return (j1) ga.r.K(ga.r.Q(ga.r.M(view, k1.f3255n), k1.f3256o));
    }

    public static final t g(MainActivity mainActivity) {
        r lifecycle = mainActivity.getLifecycle();
        while (true) {
            t tVar = (t) lifecycle.getInternalScopeRef().get();
            if (tVar != null) {
                return tVar;
            }
            p2 p2VarD = ka.m0.d();
            ka.x0 x0Var = ka.x0.f19652a;
            t tVar2 = new t(lifecycle, d7.h.a(p2VarD, ((la.d) pa.q.f21294a).f20077n));
            AtomicReference<Object> internalScopeRef = lifecycle.getInternalScopeRef();
            do {
                d7.d dVar = null;
                if (internalScopeRef.compareAndSet(null, tVar2)) {
                    ka.x0 x0Var2 = ka.x0.f19652a;
                    ka.m0.p(tVar2, ((la.d) pa.q.f21294a).f20077n, 0, new s(tVar2, dVar, 0), 2);
                    return tVar2;
                }
            } while (internalScopeRef.get() == null);
        }
    }

    public static final ka.k0 h(d1 d1Var) {
        ka.k0 k0Var = (ka.k0) d1Var.getTag("androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY");
        if (k0Var != null) {
            return k0Var;
        }
        p2 p2VarD = ka.m0.d();
        ka.x0 x0Var = ka.x0.f19652a;
        return (ka.k0) d1Var.setTagIfAbsent("androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY", new d(d7.h.a(p2VarD, ((la.d) pa.q.f21294a).f20077n)));
    }

    public static final void i(View view, y yVar) {
        view.setTag(R.id.view_tree_lifecycle_owner, yVar);
    }

    public static final void j(View view, j1 j1Var) {
        view.setTag(R.id.view_tree_view_model_store_owner, j1Var);
    }

    public static void k(b2.e eVar, r rVar) {
        q currentState = rVar.getCurrentState();
        if (currentState == q.f3267l || currentState.a(q.f3269n)) {
            eVar.d();
        } else {
            rVar.addObserver(new g(rVar, eVar, 1));
        }
    }
}
