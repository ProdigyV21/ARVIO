package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class h0 extends i0 {
    public static Map A0(Iterable iterable) {
        boolean z = iterable instanceof Collection;
        a0 a0Var = a0.f19683i;
        if (z) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                return a0Var;
            }
            if (size == 1) {
                return i0.r0((x6.x) (iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next()));
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(collection.size()));
            x0(linkedHashMap, iterable);
            return linkedHashMap;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        x0(linkedHashMap2, iterable);
        int size2 = linkedHashMap2.size();
        if (size2 == 0) {
            return a0Var;
        }
        if (size2 != 1) {
            return linkedHashMap2;
        }
        Map.Entry entry = (Map.Entry) linkedHashMap2.entrySet().iterator().next();
        return Collections.singletonMap(entry.getKey(), entry.getValue());
    }

    public static Map B0(Map map) {
        int size = map.size();
        if (size == 0) {
            return a0.f19683i;
        }
        if (size != 1) {
            return new LinkedHashMap(map);
        }
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        return Collections.singletonMap(entry.getKey(), entry.getValue());
    }

    public static Map C0(x6.x[] xVarArr) {
        int length = xVarArr.length;
        if (length == 0) {
            return a0.f19683i;
        }
        if (length == 1) {
            return i0.r0(xVarArr[0]);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(xVarArr.length));
        y0(linkedHashMap, xVarArr);
        return linkedHashMap;
    }

    public static Object s0(Map map, Object obj) {
        if (map instanceof g0) {
            return ((g0) map).n();
        }
        Object obj2 = map.get(obj);
        if (obj2 != null || map.containsKey(obj)) {
            return obj2;
        }
        throw new NoSuchElementException("Key " + obj + " is missing in the map.");
    }

    public static Map t0(x6.x... xVarArr) {
        if (xVarArr.length <= 0) {
            return a0.f19683i;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(xVarArr.length));
        y0(linkedHashMap, xVarArr);
        return linkedHashMap;
    }

    public static LinkedHashMap u0(x6.x... xVarArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(xVarArr.length));
        y0(linkedHashMap, xVarArr);
        return linkedHashMap;
    }

    public static LinkedHashMap v0(Map map, Map map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return linkedHashMap;
    }

    public static Map w0(Map map, x6.x xVar) {
        if (map.isEmpty()) {
            return i0.r0(xVar);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put(xVar.f22608i, xVar.f22609l);
        return linkedHashMap;
    }

    public static void x0(Map map, Iterable iterable) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            x6.x xVar = (x6.x) it.next();
            map.put(xVar.f22608i, xVar.f22609l);
        }
    }

    public static void y0(Map map, x6.x[] xVarArr) {
        for (x6.x xVar : xVarArr) {
            map.put(xVar.f22608i, xVar.f22609l);
        }
    }

    public static List z0(Map map) {
        int size = map.size();
        z zVar = z.f19728i;
        if (size == 0) {
            return zVar;
        }
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return zVar;
        }
        Map.Entry entry = (Map.Entry) it.next();
        if (!it.hasNext()) {
            return Collections.singletonList(new x6.x(entry.getKey(), entry.getValue()));
        }
        ArrayList arrayList = new ArrayList(map.size());
        arrayList.add(new x6.x(entry.getKey(), entry.getValue()));
        do {
            Map.Entry entry2 = (Map.Entry) it.next();
            arrayList.add(new x6.x(entry2.getKey(), entry2.getValue()));
        } while (it.hasNext());
        return arrayList;
    }
}
