package androidx.tv.foundation.lazy.list;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class v {
    public static final ArrayList a(ArrayList arrayList, r7.l lVar) {
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = arrayList.get(i10);
            if (((Boolean) lVar.invoke(obj)).booleanValue()) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }
}
