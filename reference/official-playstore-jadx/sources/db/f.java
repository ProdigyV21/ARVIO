package db;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f14892a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e f14893b = e.f14887b;

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        k2.c.b(dVar);
        return new d((List) new cb.d(q.f14921a, 0).e(dVar));
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f14893b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        d dVar = (d) obj;
        k2.c.a(eVar);
        q qVar = q.f14921a;
        cb.c cVar = new cb.c(qVar.getDescriptor(), 1);
        int size = dVar.size();
        bb.c cVarV = eVar.v(cVar);
        Iterator<m> it = dVar.iterator();
        for (int i10 = 0; i10 < size; i10++) {
            cVarV.j(cVar, i10, qVar, it.next());
        }
        cVarV.e();
    }
}
