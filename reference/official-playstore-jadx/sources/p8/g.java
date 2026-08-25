package p8;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f21140a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashMap f21141b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Set f21142c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Set f21143d;

    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Object, java.util.Map] */
    static {
        f9.e eVar = d8.p.f14742j;
        x6.x xVar = new x6.x(eVar.b(f9.f.j(ContentDisposition.Parameters.Name)).g(), d8.q.f14759d);
        x6.x xVar2 = new x6.x(eVar.b(f9.f.j("ordinal")).g(), f9.f.j("ordinal"));
        x6.x xVar3 = new x6.x(d8.p.B.c(f9.f.j(ContentDisposition.Parameters.Size)), f9.f.j(ContentDisposition.Parameters.Size));
        f9.c cVar = d8.p.F;
        Map mapT0 = kotlin.collections.h0.t0(xVar, xVar2, xVar3, new x6.x(cVar.c(f9.f.j(ContentDisposition.Parameters.Size)), f9.f.j(ContentDisposition.Parameters.Size)), new x6.x(d8.p.f14737e.b(f9.f.j("length")).g(), f9.f.j("length")), new x6.x(cVar.c(f9.f.j(UserMetadata.KEYDATA_FILENAME)), f9.f.j("keySet")), new x6.x(cVar.c(f9.f.j("values")), f9.f.j("values")), new x6.x(cVar.c(f9.f.j("entries")), f9.f.j("entrySet")));
        f21140a = mapT0;
        Set<Map.Entry> setEntrySet = mapT0.entrySet();
        ArrayList<x6.x> arrayList = new ArrayList(kotlin.collections.s.U(setEntrySet, 10));
        for (Map.Entry entry : setEntrySet) {
            arrayList.add(new x6.x(((f9.c) entry.getKey()).f(), entry.getValue()));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (x6.x xVar4 : arrayList) {
            f9.f fVar = (f9.f) xVar4.f22609l;
            Object arrayList2 = linkedHashMap.get(fVar);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(fVar, arrayList2);
            }
            ((List) arrayList2).add((f9.f) xVar4.f22608i);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry2.getKey(), kotlin.collections.x.g0((Iterable) entry2.getValue()));
        }
        f21141b = linkedHashMap2;
        ?? r02 = f21140a;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Map.Entry entry3 : r02.entrySet()) {
            String str = f8.d.f15297a;
            linkedHashSet.add(f8.d.e(((f9.c) entry3.getKey()).e().i()).b().c((f9.f) entry3.getValue()));
        }
        Set setKeySet = f21140a.keySet();
        f21142c = setKeySet;
        Set set = setKeySet;
        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(set, 10));
        Iterator it = set.iterator();
        while (it.hasNext()) {
            arrayList3.add(((f9.c) it.next()).f());
        }
        f21143d = kotlin.collections.x.g1(arrayList3);
    }
}
