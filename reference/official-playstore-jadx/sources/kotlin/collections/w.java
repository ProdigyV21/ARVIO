package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class w extends v {
    public static void W(List list) {
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }

    public static void X(Comparator comparator, List list) {
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }
}
