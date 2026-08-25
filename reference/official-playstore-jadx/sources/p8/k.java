package p8;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashMap f21167a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f21168b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        f21167a = linkedHashMap;
        b(f9.i.f15395r, a("java.util.ArrayList", "java.util.LinkedList"));
        b(f9.i.f15396s, a("java.util.HashSet", "java.util.TreeSet", "java.util.LinkedHashSet"));
        b(f9.i.f15397t, a("java.util.HashMap", "java.util.TreeMap", "java.util.LinkedHashMap", "java.util.concurrent.ConcurrentHashMap", "java.util.concurrent.ConcurrentSkipListMap"));
        b(f9.b.j(new f9.c("java.util.function.Function")), a("java.util.function.UnaryOperator"));
        b(f9.b.j(new f9.c("java.util.function.BiFunction")), a("java.util.function.BinaryOperator"));
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new x6.x(((f9.b) entry.getKey()).b(), ((f9.b) entry.getValue()).b()));
        }
        f21168b = kotlin.collections.h0.A0(arrayList);
    }

    public static ArrayList a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(f9.b.j(new f9.c(str)));
        }
        return arrayList;
    }

    public static void b(f9.b bVar, ArrayList arrayList) {
        for (Object obj : arrayList) {
            f21167a.put(obj, bVar);
        }
    }
}
