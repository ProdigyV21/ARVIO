package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f3194a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f3195b;

    public a(HashMap map) {
        this.f3195b = map;
        for (Map.Entry entry : map.entrySet()) {
            p pVar = (p) entry.getValue();
            List arrayList = (List) this.f3194a.get(pVar);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.f3194a.put(pVar, arrayList);
            }
            arrayList.add((b) entry.getKey());
        }
    }

    public static void a(List list, y yVar, p pVar, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                b bVar = (b) list.get(size);
                Method method = bVar.f3210b;
                try {
                    int i10 = bVar.f3209a;
                    if (i10 == 0) {
                        method.invoke(obj, null);
                    } else if (i10 == 1) {
                        method.invoke(obj, yVar);
                    } else if (i10 == 2) {
                        method.invoke(obj, yVar, pVar);
                    }
                } catch (IllegalAccessException e5) {
                    throw new RuntimeException(e5);
                } catch (InvocationTargetException e6) {
                    throw new RuntimeException("Failed to call observer method", e6.getCause());
                }
            }
        }
    }
}
