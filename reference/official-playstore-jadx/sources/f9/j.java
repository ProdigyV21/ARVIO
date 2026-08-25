package f9;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.i0;
import kotlin.collections.s;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j {
    static {
        new c("java.lang").c(f.j("annotation"));
    }

    public static final b a(String str) {
        c cVar = i.f15379a;
        return new b(i.f15379a, f.j(str));
    }

    public static final b b(String str) {
        c cVar = i.f15379a;
        return new b(i.f15381c, f.j(str));
    }

    public static final LinkedHashMap c(LinkedHashMap linkedHashMap) {
        Set<Map.Entry> setEntrySet = linkedHashMap.entrySet();
        int iQ0 = i0.q0(s.U(setEntrySet, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(iQ0);
        for (Map.Entry entry : setEntrySet) {
            linkedHashMap2.put(entry.getValue(), entry.getKey());
        }
        return linkedHashMap2;
    }

    public static final b d(f fVar) {
        c cVar = i.f15379a;
        b bVar = i.f15387i;
        return new b(bVar.g(), f.j(fVar.f().concat(bVar.i().f())));
    }

    public static final b e(String str) {
        c cVar = i.f15379a;
        return new b(i.f15380b, f.j(str));
    }

    public static final b f(b bVar) {
        c cVar = i.f15379a;
        return new b(i.f15379a, f.j("U".concat(bVar.i().f())));
    }
}
