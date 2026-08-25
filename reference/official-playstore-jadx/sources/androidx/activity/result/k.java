package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.lifecycle.w;
import androidx.lifecycle.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f1005a = new LinkedHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f1006b = new LinkedHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f1007c = new LinkedHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f1008d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final transient LinkedHashMap f1009e = new LinkedHashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f1010f = new LinkedHashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Bundle f1011g = new Bundle();

    public final boolean a(int i10, int i11, Intent intent) {
        String str = (String) this.f1005a.get(Integer.valueOf(i10));
        if (str == null) {
            return false;
        }
        f fVar = (f) this.f1009e.get(str);
        if ((fVar != null ? fVar.f997a : null) != null) {
            ArrayList arrayList = this.f1008d;
            if (arrayList.contains(str)) {
                fVar.f997a.a(fVar.f998b.c(intent, i11));
                arrayList.remove(str);
                return true;
            }
        }
        this.f1010f.remove(str);
        this.f1011g.putParcelable(str, new a(intent, i11));
        return true;
    }

    public abstract void b(int i10, g.a aVar, Object obj);

    public final i c(String str, y yVar, g.a aVar, b bVar) {
        r lifecycle = yVar.getLifecycle();
        if (lifecycle.getCurrentState().a(q.f3269n)) {
            throw new IllegalStateException(("LifecycleOwner " + yVar + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.").toString());
        }
        e(str);
        LinkedHashMap linkedHashMap = this.f1007c;
        g gVar = (g) linkedHashMap.get(str);
        if (gVar == null) {
            gVar = new g(lifecycle);
        }
        e eVar = new e(this, str, bVar, aVar, 0);
        gVar.f999a.addObserver(eVar);
        gVar.f1000b.add(eVar);
        linkedHashMap.put(str, gVar);
        return new i();
    }

    public final j d(String str, g.a aVar, b bVar) {
        e(str);
        this.f1009e.put(str, new f(aVar, bVar));
        LinkedHashMap linkedHashMap = this.f1010f;
        if (linkedHashMap.containsKey(str)) {
            Object obj = linkedHashMap.get(str);
            linkedHashMap.remove(str);
            bVar.a(obj);
        }
        Bundle bundle = this.f1011g;
        a aVar2 = (a) t7.a.z(bundle, str);
        if (aVar2 != null) {
            bundle.remove(str);
            bVar.a(aVar.c(aVar2.f991l, aVar2.f990i));
        }
        return new j(this, str, aVar);
    }

    public final void e(String str) {
        LinkedHashMap linkedHashMap = this.f1006b;
        if (((Integer) linkedHashMap.get(str)) != null) {
            return;
        }
        for (Number number : (ga.a) ga.r.N(h.f1001i)) {
            Integer numValueOf = Integer.valueOf(number.intValue());
            LinkedHashMap linkedHashMap2 = this.f1005a;
            if (!linkedHashMap2.containsKey(numValueOf)) {
                int iIntValue = number.intValue();
                linkedHashMap2.put(Integer.valueOf(iIntValue), str);
                linkedHashMap.put(str, Integer.valueOf(iIntValue));
                return;
            }
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    public final void f(String str) {
        Integer num;
        if (!this.f1008d.contains(str) && (num = (Integer) this.f1006b.remove(str)) != null) {
            this.f1005a.remove(num);
        }
        this.f1009e.remove(str);
        LinkedHashMap linkedHashMap = this.f1010f;
        if (linkedHashMap.containsKey(str)) {
            StringBuilder sbT = a0.c.t("Dropping pending result for request ", str, ": ");
            sbT.append(linkedHashMap.get(str));
            Log.w("ActivityResultRegistry", sbT.toString());
            linkedHashMap.remove(str);
        }
        Bundle bundle = this.f1011g;
        if (bundle.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + ((a) t7.a.z(bundle, str)));
            bundle.remove(str);
        }
        LinkedHashMap linkedHashMap2 = this.f1007c;
        g gVar = (g) linkedHashMap2.get(str);
        if (gVar != null) {
            ArrayList arrayList = gVar.f1000b;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                gVar.f999a.removeObserver((w) it.next());
            }
            arrayList.clear();
            linkedHashMap2.remove(str);
        }
    }
}
