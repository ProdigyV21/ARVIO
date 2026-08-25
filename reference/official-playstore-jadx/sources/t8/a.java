package t8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w8.g f21895a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.jvm.internal.r f21896b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s8.d f21897c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashMap f21898d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f21899e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f21900f;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6, types: [kotlin.jvm.internal.r, r7.l] */
    /* JADX WARN: Type inference failed for: r5v6, types: [kotlin.jvm.internal.r, r7.l] */
    public a(w8.g gVar, r7.l lVar) {
        this.f21895a = gVar;
        this.f21896b = (kotlin.jvm.internal.r) lVar;
        s8.d dVar = new s8.d(this, 5);
        this.f21897c = dVar;
        ga.i iVarI = ga.r.I(new ga.p(gVar.q(), 3), dVar);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ga.h hVar = new ga.h(iVarI);
        while (hVar.hasNext()) {
            Object next = hVar.next();
            f9.f name = ((w8.q) next).getName();
            Object arrayList = linkedHashMap.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(name, arrayList);
            }
            ((List) arrayList).add(next);
        }
        this.f21898d = linkedHashMap;
        ga.i iVarI2 = ga.r.I(new ga.p(this.f21895a.A(), 3), this.f21896b);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        ga.h hVar2 = new ga.h(iVarI2);
        while (hVar2.hasNext()) {
            Object next2 = hVar2.next();
            linkedHashMap2.put(((w8.n) next2).getName(), next2);
        }
        this.f21899e = linkedHashMap2;
        ArrayList arrayListK = this.f21895a.k();
        ?? r52 = this.f21896b;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayListK) {
            if (((Boolean) r52.invoke(obj)).booleanValue()) {
                arrayList2.add(obj);
            }
        }
        int iQ0 = i0.q0(kotlin.collections.s.U(arrayList2, 10));
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(iQ0 < 16 ? 16 : iQ0);
        for (Object obj2 : arrayList2) {
            linkedHashMap3.put(((w8.v) obj2).getName(), obj2);
        }
        this.f21900f = linkedHashMap3;
    }

    @Override // t8.c
    public final Set a() {
        ga.i iVarI = ga.r.I(new ga.p(this.f21895a.q(), 3), this.f21897c);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ga.h hVar = new ga.h(iVarI);
        while (hVar.hasNext()) {
            linkedHashSet.add(((w8.q) hVar.next()).getName());
        }
        return linkedHashSet;
    }

    @Override // t8.c
    public final Set b() {
        return this.f21900f.keySet();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // t8.c
    public final Set c() {
        ga.i iVarI = ga.r.I(new ga.p(this.f21895a.A(), 3), this.f21896b);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ga.h hVar = new ga.h(iVarI);
        while (hVar.hasNext()) {
            linkedHashSet.add(((w8.n) hVar.next()).getName());
        }
        return linkedHashSet;
    }

    @Override // t8.c
    public final w8.n d(f9.f fVar) {
        return (w8.n) this.f21899e.get(fVar);
    }

    @Override // t8.c
    public final Collection e(f9.f fVar) {
        List list = (List) this.f21898d.get(fVar);
        return list != null ? list : kotlin.collections.z.f19728i;
    }

    @Override // t8.c
    public final w8.v f(f9.f fVar) {
        return (w8.v) this.f21900f.get(fVar);
    }
}
