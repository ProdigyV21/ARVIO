package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class s extends t7.a {
    public static int U(Iterable iterable, int i10) {
        return iterable instanceof Collection ? ((Collection) iterable).size() : i10;
    }

    public static ArrayList V(Iterable iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            x.b0(arrayList, (Iterable) it.next());
        }
        return arrayList;
    }
}
