package androidx.lifecycle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class l implements b2.c {
    @Override // b2.c
    public final void a(b2.g gVar) {
        if (!(gVar instanceof j1)) {
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
        }
        i1 viewModelStore = ((j1) gVar).getViewModelStore();
        b2.e savedStateRegistry = gVar.getSavedStateRegistry();
        viewModelStore.getClass();
        LinkedHashMap linkedHashMap = viewModelStore.f3252a;
        Iterator it = new HashSet(linkedHashMap.keySet()).iterator();
        while (it.hasNext()) {
            z0.a((d1) linkedHashMap.get((String) it.next()), savedStateRegistry, gVar.getLifecycle());
        }
        if (new HashSet(linkedHashMap.keySet()).isEmpty()) {
            return;
        }
        savedStateRegistry.d();
    }
}
