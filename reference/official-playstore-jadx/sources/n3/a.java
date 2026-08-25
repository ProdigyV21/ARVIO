package n3;

import android.util.SparseArray;
import java.util.HashMap;
import z2.c;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseArray f20482a = new SparseArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap f20483b;

    static {
        HashMap map = new HashMap();
        f20483b = map;
        map.put(c.f23164i, 0);
        map.put(c.f23165l, 1);
        map.put(c.f23166m, 2);
        for (c cVar : map.keySet()) {
            f20482a.append(((Integer) f20483b.get(cVar)).intValue(), cVar);
        }
    }

    public static int a(c cVar) {
        Integer num = (Integer) f20483b.get(cVar);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + cVar);
    }

    public static c b(int i10) {
        c cVar = (c) f20482a.get(i10);
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalArgumentException(a0.c.i(i10, "Unknown Priority for value "));
    }
}
