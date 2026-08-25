package d8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.h0;
import kotlin.collections.i0;
import kotlin.collections.x;
import v9.b1;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set f14783a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set f14784b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final HashMap f14785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final HashMap f14786d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final LinkedHashSet f14787e;

    static {
        t[] tVarArrValues = t.values();
        ArrayList arrayList = new ArrayList(tVarArrValues.length);
        for (t tVar : tVarArrValues) {
            arrayList.add(tVar.f14781l);
        }
        f14783a = x.g1(arrayList);
        s[] sVarArrValues = s.values();
        ArrayList arrayList2 = new ArrayList(sVarArrValues.length);
        for (s sVar : sVarArrValues) {
            arrayList2.add(sVar.f14778i);
        }
        f14784b = x.g1(arrayList2);
        f14785c = new HashMap();
        f14786d = new HashMap();
        h0.y0(new HashMap(i0.q0(4)), new x6.x[]{new x6.x(s.UBYTEARRAY, f9.f.j("ubyteArrayOf")), new x6.x(s.USHORTARRAY, f9.f.j("ushortArrayOf")), new x6.x(s.UINTARRAY, f9.f.j("uintArrayOf")), new x6.x(s.ULONGARRAY, f9.f.j("ulongArrayOf"))});
        t[] tVarArrValues2 = t.values();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (t tVar2 : tVarArrValues2) {
            linkedHashSet.add(tVar2.f14782m.i());
        }
        f14787e = linkedHashSet;
        for (t tVar3 : t.values()) {
            HashMap map = f14785c;
            f9.b bVar = tVar3.f14782m;
            f9.b bVar2 = tVar3.f14780i;
            map.put(bVar, bVar2);
            f14786d.put(bVar2, tVar3.f14782m);
        }
    }

    public static final boolean a(w wVar) {
        g8.h hVarE;
        if (b1.l(wVar) || (hVarE = wVar.I0().e()) == null) {
            return false;
        }
        g8.k kVarD = hVarE.d();
        return (kVarD instanceof g8.h0) && kotlin.jvm.internal.p.a(((g8.h0) kVarD).c(), q.f14766l) && f14783a.contains(hVarE.getName());
    }
}
