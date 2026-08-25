package androidx.datastore.preferences.protobuf;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c implements v1 {
    protected int memoizedHashCode;

    public static void f(Iterable iterable, List list) {
        Charset charset = f1.f2421a;
        if (iterable instanceof i1) {
            List listI = ((i1) iterable).i();
            i1 i1Var = (i1) list;
            int size = list.size();
            for (Object obj : listI) {
                if (obj == null) {
                    String str = "Element at index " + (i1Var.size() - size) + " is null.";
                    for (int size2 = i1Var.size() - 1; size2 >= size; size2--) {
                        i1Var.remove(size2);
                    }
                    throw new NullPointerException(str);
                }
                if (obj instanceof m) {
                    i1Var.b0((m) obj);
                } else {
                    i1Var.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof i2) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
        }
        int size3 = list.size();
        for (Object obj2 : iterable) {
            if (obj2 == null) {
                String str2 = "Element at index " + (list.size() - size3) + " is null.";
                for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                    list.remove(size4);
                }
                throw new NullPointerException(str2);
            }
            list.add(obj2);
        }
    }

    public final int g(o2 o2Var) {
        r0 r0Var = (r0) this;
        int i10 = r0Var.memoizedSerializedSize;
        if (i10 != -1) {
            return i10;
        }
        int iC = o2Var.c(this);
        r0Var.memoizedSerializedSize = iC;
        return iC;
    }
}
