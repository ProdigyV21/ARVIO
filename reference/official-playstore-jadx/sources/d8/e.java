package d8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashSet f14697a;

    static {
        Set set = m.f14712o;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(set, 10));
        Iterator it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(q.f14766l.c(((m) it.next()).f14721i));
        }
        ArrayList arrayListH0 = x.H0(p.f14742j.g(), x.H0(p.f14740h.g(), x.H0(p.f14738f.g(), arrayList)));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it2 = arrayListH0.iterator();
        while (it2.hasNext()) {
            linkedHashSet.add(f9.b.j((f9.c) it2.next()));
        }
        f14697a = linkedHashSet;
    }
}
