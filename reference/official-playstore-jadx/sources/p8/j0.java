package p8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.collections.q0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ArrayList f21157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ArrayList f21158b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f21159c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final LinkedHashMap f21160d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Set f21161e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Set f21162f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g0 f21163g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f21164h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final LinkedHashMap f21165i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final ArrayList f21166j;
    public static final LinkedHashMap k;

    /* JADX WARN: Type inference failed for: r0v34, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v37, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v41, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r1v17, types: [java.lang.Object, java.util.Map] */
    static {
        Set setP0 = kotlin.collections.r.p0(new String[]{"containsAll", "removeAll", "retainAll"});
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(setP0, 10));
        Iterator it = setP0.iterator();
        while (it.hasNext()) {
            arrayList.add(o.a("java/util/Collection", (String) it.next(), "Ljava/util/Collection;", n9.c.BOOLEAN.f()));
        }
        f21157a = arrayList;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((g0) it2.next()).f21148e);
        }
        f21158b = arrayList2;
        ArrayList arrayList3 = f21157a;
        ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(arrayList3, 10));
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((g0) it3.next()).f21145b.d());
        }
        String strConcat = "java/util/".concat("Collection");
        n9.c cVar = n9.c.BOOLEAN;
        g0 g0VarA = o.a(strConcat, "contains", "Ljava/lang/Object;", cVar.f());
        i0 i0Var = i0.f21153n;
        x6.x xVar = new x6.x(g0VarA, i0Var);
        x6.x xVar2 = new x6.x(o.a("java/util/".concat("Collection"), "remove", "Ljava/lang/Object;", cVar.f()), i0Var);
        x6.x xVar3 = new x6.x(o.a("java/util/".concat("Map"), "containsKey", "Ljava/lang/Object;", cVar.f()), i0Var);
        x6.x xVar4 = new x6.x(o.a("java/util/".concat("Map"), "containsValue", "Ljava/lang/Object;", cVar.f()), i0Var);
        x6.x xVar5 = new x6.x(o.a("java/util/".concat("Map"), "remove", "Ljava/lang/Object;Ljava/lang/Object;", cVar.f()), i0Var);
        x6.x xVar6 = new x6.x(o.a("java/util/".concat("Map"), "getOrDefault", "Ljava/lang/Object;Ljava/lang/Object;", "Ljava/lang/Object;"), i0.f21154o);
        g0 g0VarA2 = o.a("java/util/".concat("Map"), "get", "Ljava/lang/Object;", "Ljava/lang/Object;");
        i0 i0Var2 = i0.f21151l;
        x6.x xVar7 = new x6.x(g0VarA2, i0Var2);
        x6.x xVar8 = new x6.x(o.a("java/util/".concat("Map"), "remove", "Ljava/lang/Object;", "Ljava/lang/Object;"), i0Var2);
        String strConcat2 = "java/util/".concat("List");
        n9.c cVar2 = n9.c.INT;
        g0 g0VarA3 = o.a(strConcat2, "indexOf", "Ljava/lang/Object;", cVar2.f());
        i0 i0Var3 = i0.f21152m;
        Map mapT0 = kotlin.collections.h0.t0(xVar, xVar2, xVar3, xVar4, xVar5, xVar6, xVar7, xVar8, new x6.x(g0VarA3, i0Var3), new x6.x(o.a("java/util/".concat("List"), "lastIndexOf", "Ljava/lang/Object;", cVar2.f()), i0Var3));
        f21159c = mapT0;
        LinkedHashMap linkedHashMap = new LinkedHashMap(kotlin.collections.i0.q0(mapT0.size()));
        for (Map.Entry entry : mapT0.entrySet()) {
            linkedHashMap.put(((g0) entry.getKey()).f21148e, entry.getValue());
        }
        f21160d = linkedHashMap;
        LinkedHashSet linkedHashSetB0 = q0.b0(f21159c.keySet(), f21157a);
        ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(linkedHashSetB0, 10));
        Iterator it4 = linkedHashSetB0.iterator();
        while (it4.hasNext()) {
            arrayList5.add(((g0) it4.next()).f21145b);
        }
        f21161e = kotlin.collections.x.g1(arrayList5);
        ArrayList arrayList6 = new ArrayList(kotlin.collections.s.U(linkedHashSetB0, 10));
        Iterator it5 = linkedHashSetB0.iterator();
        while (it5.hasNext()) {
            arrayList6.add(((g0) it5.next()).f21148e);
        }
        f21162f = kotlin.collections.x.g1(arrayList6);
        n9.c cVar3 = n9.c.INT;
        g0 g0VarA4 = o.a("java/util/List", "removeAt", cVar3.f(), "Ljava/lang/Object;");
        f21163g = g0VarA4;
        Map mapT02 = kotlin.collections.h0.t0(new x6.x(o.a("java/lang/".concat("Number"), "toByte", "", n9.c.BYTE.f()), f9.f.j("byteValue")), new x6.x(o.a("java/lang/".concat("Number"), "toShort", "", n9.c.SHORT.f()), f9.f.j("shortValue")), new x6.x(o.a("java/lang/".concat("Number"), "toInt", "", cVar3.f()), f9.f.j("intValue")), new x6.x(o.a("java/lang/".concat("Number"), "toLong", "", n9.c.LONG.f()), f9.f.j("longValue")), new x6.x(o.a("java/lang/".concat("Number"), "toFloat", "", n9.c.FLOAT.f()), f9.f.j("floatValue")), new x6.x(o.a("java/lang/".concat("Number"), "toDouble", "", n9.c.DOUBLE.f()), f9.f.j("doubleValue")), new x6.x(g0VarA4, f9.f.j("remove")), new x6.x(o.a("java/lang/".concat("CharSequence"), "get", cVar3.f(), n9.c.CHAR.f()), f9.f.j("charAt")));
        f21164h = mapT02;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(kotlin.collections.i0.q0(mapT02.size()));
        for (Map.Entry entry2 : mapT02.entrySet()) {
            linkedHashMap2.put(((g0) entry2.getKey()).f21148e, entry2.getValue());
        }
        f21165i = linkedHashMap2;
        ?? r02 = f21164h;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Map.Entry entry3 : r02.entrySet()) {
            g0 g0Var = (g0) entry3.getKey();
            f9.f fVar = (f9.f) entry3.getValue();
            linkedHashSet.add(g0Var.f21144a + '.' + (fVar + '(' + g0Var.f21146c + ')' + g0Var.f21147d));
        }
        Set setKeySet = f21164h.keySet();
        ArrayList arrayList7 = new ArrayList(kotlin.collections.s.U(setKeySet, 10));
        Iterator it6 = setKeySet.iterator();
        while (it6.hasNext()) {
            arrayList7.add(((g0) it6.next()).f21145b);
        }
        f21166j = arrayList7;
        Set<Map.Entry> setEntrySet = f21164h.entrySet();
        ArrayList<x6.x> arrayList8 = new ArrayList(kotlin.collections.s.U(setEntrySet, 10));
        for (Map.Entry entry4 : setEntrySet) {
            arrayList8.add(new x6.x(((g0) entry4.getKey()).f21145b, entry4.getValue()));
        }
        int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(arrayList8, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(iQ0);
        for (x6.x xVar9 : arrayList8) {
            linkedHashMap3.put((f9.f) xVar9.f22609l, (f9.f) xVar9.f22608i);
        }
        k = linkedHashMap3;
    }
}
