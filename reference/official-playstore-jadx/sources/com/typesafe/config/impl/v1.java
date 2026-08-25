package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class v1 extends c implements Serializable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final v1 f14627p = d0(w1.g("empty config"));

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Map f14628m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f14629n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f14630o;

    public v1(l6.k kVar, Map map, int i10, boolean z) {
        super(kVar);
        if (map == null) {
            throw new ConfigException.BugOrBroken(null, "creating config object with null map");
        }
        this.f14628m = map;
        this.f14629n = i10 == 2;
        this.f14630o = z;
        if (i10 == com.arflix.tv.data.repository.g.a(map.values())) {
            return;
        }
        throw new ConfigException.BugOrBroken(null, "Wrong resolved status on " + this);
    }

    public static final v1 d0(l6.k kVar) {
        return kVar == null ? f14627p : new v1(kVar, Collections.EMPTY_MAP);
    }

    @Override // com.typesafe.config.impl.g
    public final g A(c cVar) {
        I();
        if (!(cVar instanceof v1)) {
            throw new ConfigException.BugOrBroken(null, "should not be reached (merging non-SimpleConfigObject)");
        }
        v1 v1Var = (v1) cVar;
        Map map = v1Var.f14628m;
        HashMap map2 = new HashMap();
        HashSet<String> hashSet = new HashSet();
        Map map3 = this.f14628m;
        hashSet.addAll(map3.keySet());
        hashSet.addAll(map.keySet());
        boolean z = true;
        boolean z5 = false;
        for (String str : hashSet) {
            g gVar = (g) map3.get(str);
            g gVarF = (g) map.get(str);
            if (gVar != null) {
                gVarF = gVarF == null ? gVar : gVar.f(gVarF);
            }
            map2.put(str, gVarF);
            if (gVar != gVarF) {
                z5 = true;
            }
            if (gVarF.J() == 1) {
                z = false;
            }
        }
        int i10 = z ? 2 : 1;
        boolean z10 = v1Var.f14630o;
        if (z5) {
            return new v1(c.W(Arrays.asList(this, v1Var)), map2, i10, z10);
        }
        return (i10 == (this.f14629n ? 2 : 1) && z10 == this.f14630o) ? this : new v1(this.f14537i, map3, i10, z10);
    }

    @Override // com.typesafe.config.impl.g
    public final g E(g1 g1Var) {
        try {
            return e0(new q1(g1Var, 1));
        } catch (RuntimeException e5) {
            throw e5;
        } catch (Exception e6) {
            throw new ConfigException.BugOrBroken(e6, "unexpected checked exception");
        }
    }

    @Override // com.typesafe.config.impl.g
    public final void F(StringBuilder sb2, int i10, boolean z, io.sentry.util.l lVar) {
        Map map = this.f14628m;
        if (map.isEmpty()) {
            sb2.append("{}");
            return;
        }
        int i11 = i10 + 1;
        sb2.append("{");
        String[] strArr = (String[]) map.keySet().toArray(new String[map.size()]);
        Arrays.sort(strArr, new u1());
        int length = strArr.length;
        int i12 = 0;
        int i13 = 0;
        while (i13 < length) {
            String str = strArr[i13];
            StringBuilder sb3 = sb2;
            ((g) map.get(str)).G(sb3, i11, false, str, lVar);
            sb3.append(",");
            i13++;
            i12 = 1;
            sb2 = sb3;
        }
        StringBuilder sb4 = sb2;
        sb4.setLength(sb4.length() - i12);
        sb4.append("}");
    }

    @Override // com.typesafe.config.impl.g
    public final int J() {
        return this.f14629n ? 2 : 1;
    }

    @Override // com.typesafe.config.impl.g
    public final n1 K(m1 m1Var, o1 o1Var) throws f {
        if (this.f14629n) {
            return new n1(m1Var, this);
        }
        try {
            androidx.appcompat.app.i1 i1Var = new androidx.appcompat.app.i1(m1Var, o1Var.d(this));
            v1 v1VarE0 = e0(i1Var);
            n1 n1Var = new n1((m1) i1Var.f1060m, v1VarE0);
            if (v1VarE0 instanceof c) {
                return n1Var;
            }
            throw new ConfigException.BugOrBroken(null, "Expecting a resolve result to be an object, but it was " + v1VarE0);
        } catch (f e5) {
            throw e5;
        } catch (RuntimeException e6) {
            throw e6;
        } catch (Exception e10) {
            throw new ConfigException.BugOrBroken(e10, "unexpected checked exception");
        }
    }

    @Override // com.typesafe.config.impl.g
    public final g P() {
        if (this.f14630o) {
            return this;
        }
        return new v1(this.f14537i, this.f14628m, this.f14629n ? 2 : 1, true);
    }

    @Override // com.typesafe.config.impl.c
    public final g S(String str) {
        return (g) this.f14628m.get(str);
    }

    @Override // com.typesafe.config.impl.c
    /* JADX INFO: renamed from: U */
    public final g get(Object obj) {
        return (g) this.f14628m.get(obj);
    }

    @Override // com.typesafe.config.impl.c
    public final c X(int i10, w1 w1Var) {
        return new v1(w1Var, this.f14628m, i10, this.f14630o);
    }

    @Override // com.typesafe.config.impl.c
    public final c Z(g1 g1Var) {
        try {
            return e0(new q1(g1Var, 1));
        } catch (RuntimeException e5) {
            throw e5;
        } catch (Exception e6) {
            throw new ConfigException.BugOrBroken(e6, "unexpected checked exception");
        }
    }

    @Override // com.typesafe.config.impl.c, l6.o
    public final Map a() {
        HashMap map = new HashMap();
        for (Map.Entry entry : this.f14628m.entrySet()) {
            map.put(entry.getKey(), ((g) entry.getValue()).a());
        }
        return map;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return this.f14628m.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return this.f14628m.containsValue(obj);
    }

    public final v1 e0(d dVar) {
        Map map = this.f14628m;
        HashMap map2 = null;
        for (String str : map.keySet()) {
            g gVar = (g) map.get(str);
            g gVarL = dVar.L(gVar, str);
            if (gVarL != gVar) {
                if (map2 == null) {
                    map2 = new HashMap();
                }
                map2.put(str, gVarL);
            }
        }
        if (map2 == null) {
            return this;
        }
        HashMap map3 = new HashMap();
        Iterator it = map.keySet().iterator();
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String str2 = (String) it.next();
            if (map2.containsKey(str2)) {
                g gVar2 = (g) map2.get(str2);
                if (gVar2 != null) {
                    map3.put(str2, gVar2);
                    if (gVar2.J() == 1) {
                        z = true;
                    }
                }
            } else {
                g gVar3 = (g) map.get(str2);
                map3.put(str2, gVar3);
                if (gVar3.J() == 1) {
                    z = true;
                }
            }
        }
        return new v1(this.f14537i, map3, z ? 1 : 2, this.f14630o);
    }

    @Override // java.util.Map
    public final Set<Map.Entry<String, l6.o>> entrySet() {
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : this.f14628m.entrySet()) {
            hashSet.add(new AbstractMap.SimpleImmutableEntry(entry.getKey(), entry.getValue()));
        }
        return hashSet;
    }

    @Override // com.typesafe.config.impl.g
    public final boolean equals(Object obj) {
        if (!(obj instanceof l6.j)) {
            return false;
        }
        l6.j jVar = (l6.j) obj;
        if (this == jVar) {
            return true;
        }
        Set<String> setKeySet = keySet();
        if (setKeySet.equals(jVar.keySet())) {
            for (String str : setKeySet) {
                if (!((l6.o) get(str)).equals(jVar.get(str))) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.typesafe.config.impl.g
    public final int hashCode() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(keySet());
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            iHashCode += ((l6.o) get((String) it.next())).hashCode();
        }
        return ((arrayList.hashCode() + 41) * 41) + iHashCode;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.f14628m.isEmpty();
    }

    @Override // com.typesafe.config.impl.t0
    public final boolean k(g gVar) {
        Map map = this.f14628m;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            if (((g) it.next()) == gVar) {
                return true;
            }
        }
        for (l6.i iVar : map.values()) {
            if ((iVar instanceof t0) && ((t0) iVar).k(gVar)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public final Set<String> keySet() {
        return this.f14628m.keySet();
    }

    @Override // com.typesafe.config.impl.t0
    public final g r(g gVar, g gVar2) {
        HashMap map = new HashMap(this.f14628m);
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == gVar) {
                if (gVar2 != null) {
                    entry.setValue(gVar2);
                } else {
                    map.remove(entry.getKey());
                }
                return new v1(this.f14537i, map, com.arflix.tv.data.repository.g.a(map.values()), this.f14630o);
            }
        }
        throw new ConfigException.BugOrBroken(null, "SimpleConfigObject.replaceChild did not find " + gVar + " in " + this);
    }

    @Override // java.util.Map
    public final int size() {
        return this.f14628m.size();
    }

    @Override // com.typesafe.config.impl.g
    public final boolean t(l6.o oVar) {
        return oVar instanceof l6.j;
    }

    @Override // java.util.Map
    public final Collection<l6.o> values() {
        return new HashSet(this.f14628m.values());
    }

    @Override // com.typesafe.config.impl.g
    public final boolean w() {
        return this.f14630o;
    }

    public v1(l6.k kVar, Map map) {
        this(kVar, map, com.arflix.tv.data.repository.g.a(map.values()), false);
    }
}
