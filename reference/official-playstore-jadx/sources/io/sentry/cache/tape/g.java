package io.sentry.cache.tape;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g implements Iterable, Closeable {
    public abstract void G(int i10);

    public void clear() {
        G(size());
    }

    public abstract void k(Object obj);

    public abstract int size();

    public final List y() {
        int iMin = Math.min(size(), size());
        ArrayList arrayList = new ArrayList(iMin);
        Iterator it = iterator();
        for (int i10 = 0; i10 < iMin; i10++) {
            arrayList.add(it.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
