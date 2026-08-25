package b2;

import android.os.Bundle;
import androidx.activity.t;
import androidx.lifecycle.a1;
import androidx.lifecycle.i;
import androidx.lifecycle.p;
import androidx.lifecycle.w;
import androidx.lifecycle.y;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7112i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f7113l;

    public /* synthetic */ b(Object obj, int i10) {
        this.f7112i = i10;
        this.f7113l = obj;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(y yVar, p pVar) {
        switch (this.f7112i) {
            case 0:
                g gVar = (g) this.f7113l;
                if (pVar != p.ON_CREATE) {
                    throw new AssertionError("Next event must be ON_CREATE");
                }
                yVar.getLifecycle().removeObserver(this);
                Bundle bundleA = gVar.getSavedStateRegistry().a("androidx.savedstate.Restarter");
                if (bundleA == null) {
                    return;
                }
                ArrayList<String> stringArrayList = bundleA.getStringArrayList("classes_to_restore");
                if (stringArrayList == null) {
                    throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
                }
                for (String str : stringArrayList) {
                    try {
                        Class<? extends U> clsAsSubclass = Class.forName(str, false, b.class.getClassLoader()).asSubclass(c.class);
                        try {
                            Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(null);
                            declaredConstructor.setAccessible(true);
                            try {
                                ((c) declaredConstructor.newInstance(null)).a(gVar);
                            } catch (Exception e5) {
                                throw new RuntimeException(androidx.compose.material3.d.C("Failed to instantiate ", str), e5);
                            }
                        } catch (NoSuchMethodException e6) {
                            throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e6);
                        }
                    } catch (ClassNotFoundException e10) {
                        throw new RuntimeException(a0.c.l("Class ", str, " wasn't found"), e10);
                    }
                }
                return;
            case 1:
                t tVar = (t) this.f7113l;
                t.access$ensureViewModelStore(tVar);
                tVar.getLifecycle().removeObserver(this);
                return;
            case 2:
                new HashMap();
                i[] iVarArr = (i[]) this.f7113l;
                for (i iVar : iVarArr) {
                    iVar.a();
                }
                for (i iVar2 : iVarArr) {
                    iVar2.a();
                }
                return;
            case 3:
                if (pVar != p.ON_CREATE) {
                    throw new IllegalStateException(("Next event must be ON_CREATE, it was " + pVar).toString());
                }
                yVar.getLifecycle().removeObserver(this);
                ((a1) this.f7113l).b();
                return;
            default:
                i iVar3 = (i) this.f7113l;
                iVar3.a();
                iVar3.a();
                return;
        }
    }
}
