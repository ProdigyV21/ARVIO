package com.google.firebase.components;

import android.util.Log;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class j implements c, f5.a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final h f14268h = new h(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f14269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f14270b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f14271c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet f14272d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n f14273e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicReference f14274f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final v1.i f14275g;

    public j(ArrayList arrayList, ArrayList arrayList2, v1.i iVar) {
        com.google.firebase.concurrent.p pVar = com.google.firebase.concurrent.p.f14343i;
        this.f14269a = new HashMap();
        this.f14270b = new HashMap();
        this.f14271c = new HashMap();
        this.f14272d = new HashSet();
        this.f14274f = new AtomicReference();
        com.google.firebase.concurrent.p pVar2 = com.google.firebase.concurrent.p.f14343i;
        n nVar = new n();
        new HashMap();
        nVar.f14284a = new ArrayDeque();
        this.f14273e = nVar;
        this.f14275g = iVar;
        ArrayList<b> arrayList3 = new ArrayList();
        arrayList3.add(b.c(nVar, n.class, k5.c.class, k5.b.class));
        arrayList3.add(b.c(this, f5.a.class, new Class[0]));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                arrayList3.add(bVar);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList4.add(it2.next());
        }
        ArrayList arrayList5 = new ArrayList();
        synchronized (this) {
            Iterator it3 = arrayList4.iterator();
            while (it3.hasNext()) {
                try {
                    ComponentRegistrar componentRegistrar = (ComponentRegistrar) ((m5.b) it3.next()).get();
                    if (componentRegistrar != null) {
                        arrayList3.addAll(this.f14275g.l(componentRegistrar));
                        it3.remove();
                    }
                } catch (InvalidRegistrarException e5) {
                    it3.remove();
                    Log.w("ComponentDiscovery", "Invalid component registrar.", e5);
                }
            }
            Iterator it4 = arrayList3.iterator();
            while (it4.hasNext()) {
                Object[] array = ((b) it4.next()).f14256b.toArray();
                int length = array.length;
                int i10 = 0;
                while (true) {
                    if (i10 < length) {
                        Object obj = array[i10];
                        if (obj.toString().contains("kotlinx.coroutines.CoroutineDispatcher")) {
                            if (this.f14272d.contains(obj.toString())) {
                                it4.remove();
                                break;
                            }
                            this.f14272d.add(obj.toString());
                        }
                        i10++;
                    }
                }
            }
            if (this.f14269a.isEmpty()) {
                ac.b.A(arrayList3);
            } else {
                ArrayList arrayList6 = new ArrayList(this.f14269a.keySet());
                arrayList6.addAll(arrayList3);
                ac.b.A(arrayList6);
            }
            for (b bVar2 : arrayList3) {
                this.f14269a.put(bVar2, new o(new i(this, bVar2, 0)));
            }
            arrayList5.addAll(j(arrayList3));
            arrayList5.addAll(k());
            i();
        }
        Iterator it5 = arrayList5.iterator();
        while (it5.hasNext()) {
            ((Runnable) it5.next()).run();
        }
        Boolean bool = (Boolean) this.f14274f.get();
        if (bool != null) {
            h(this.f14269a, bool.booleanValue());
        }
    }

    @Override // com.google.firebase.components.c
    public final Object a(Class cls) {
        return d(s.a(cls));
    }

    @Override // com.google.firebase.components.c
    public final synchronized m5.b b(s sVar) {
        androidx.work.impl.t.f(sVar, "Null interface requested.");
        return (m5.b) this.f14270b.get(sVar);
    }

    @Override // com.google.firebase.components.c
    public final Set c(s sVar) {
        m5.b bVar;
        synchronized (this) {
            bVar = (p) this.f14271c.get(sVar);
            if (bVar == null) {
                bVar = f14268h;
            }
        }
        return (Set) bVar.get();
    }

    @Override // com.google.firebase.components.c
    public final Object d(s sVar) {
        m5.b bVarB = b(sVar);
        if (bVarB == null) {
            return null;
        }
        return bVarB.get();
    }

    @Override // com.google.firebase.components.c
    public final m5.b e(Class cls) {
        return b(s.a(cls));
    }

    @Override // com.google.firebase.components.c
    public final m5.a f(s sVar) {
        m5.b bVarB = b(sVar);
        return bVarB == null ? new q(q.f14290c, q.f14291d) : bVarB instanceof q ? (q) bVarB : new q(null, bVarB);
    }

    @Override // com.google.firebase.components.c
    public final m5.a g(Class cls) {
        return f(s.a(cls));
    }

    public final void h(HashMap map, boolean z) {
        ArrayDeque arrayDeque;
        for (Map.Entry entry : map.entrySet()) {
            b bVar = (b) entry.getKey();
            m5.b bVar2 = (m5.b) entry.getValue();
            int i10 = bVar.f14258d;
            if (i10 == 1 || (i10 == 2 && z)) {
                bVar2.get();
            }
        }
        n nVar = this.f14273e;
        synchronized (nVar) {
            try {
                arrayDeque = nVar.f14284a;
                if (arrayDeque != null) {
                    nVar.f14284a = null;
                } else {
                    arrayDeque = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (arrayDeque != null) {
            Iterator it = arrayDeque.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
        }
    }

    public final void i() {
        HashMap map = this.f14270b;
        HashMap map2 = this.f14271c;
        for (b bVar : this.f14269a.keySet()) {
            for (m mVar : bVar.f14257c) {
                boolean z = mVar.f14282b == 2;
                s sVar = mVar.f14281a;
                if (z && !map2.containsKey(sVar)) {
                    Set set = Collections.EMPTY_SET;
                    p pVar = new p();
                    pVar.f14289b = null;
                    pVar.f14288a = Collections.newSetFromMap(new ConcurrentHashMap());
                    pVar.f14288a.addAll(set);
                    map2.put(sVar, pVar);
                } else if (map.containsKey(sVar)) {
                    continue;
                } else {
                    int i10 = mVar.f14282b;
                    if (i10 == 1) {
                        throw new MissingDependencyException("Unsatisfied dependency for component " + bVar + ": " + sVar);
                    }
                    if (i10 != 2) {
                        map.put(sVar, new q(q.f14290c, q.f14291d));
                    }
                }
            }
        }
    }

    public final ArrayList j(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.f14259e == 0) {
                m5.b bVar2 = (m5.b) this.f14269a.get(bVar);
                for (s sVar : bVar.f14256b) {
                    HashMap map = this.f14270b;
                    if (map.containsKey(sVar)) {
                        arrayList2.add(new androidx.activity.s((q) ((m5.b) map.get(sVar)), bVar2, 18));
                    } else {
                        map.put(sVar, bVar2);
                    }
                }
            }
        }
        return arrayList2;
    }

    public final ArrayList k() {
        HashMap map = this.f14271c;
        ArrayList arrayList = new ArrayList();
        HashMap map2 = new HashMap();
        for (Map.Entry entry : this.f14269a.entrySet()) {
            b bVar = (b) entry.getKey();
            if (bVar.f14259e != 0) {
                m5.b bVar2 = (m5.b) entry.getValue();
                for (s sVar : bVar.f14256b) {
                    if (!map2.containsKey(sVar)) {
                        map2.put(sVar, new HashSet());
                    }
                    ((Set) map2.get(sVar)).add(bVar2);
                }
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            if (map.containsKey(entry2.getKey())) {
                p pVar = (p) map.get(entry2.getKey());
                Iterator it = ((Set) entry2.getValue()).iterator();
                while (it.hasNext()) {
                    arrayList.add(new androidx.activity.s(pVar, (m5.b) it.next(), 19));
                }
            } else {
                s sVar2 = (s) entry2.getKey();
                Set set = (Set) ((Collection) entry2.getValue());
                p pVar2 = new p();
                pVar2.f14289b = null;
                pVar2.f14288a = Collections.newSetFromMap(new ConcurrentHashMap());
                pVar2.f14288a.addAll(set);
                map.put(sVar2, pVar2);
            }
        }
        return arrayList;
    }
}
