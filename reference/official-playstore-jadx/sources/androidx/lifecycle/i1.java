package androidx.lifecycle;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class i1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f3252a = new LinkedHashMap();

    public final void a() {
        LinkedHashMap linkedHashMap = this.f3252a;
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((d1) it.next()).clear();
        }
        linkedHashMap.clear();
    }

    public final d1 b(String str) {
        return (d1) this.f3252a.get(str);
    }
}
