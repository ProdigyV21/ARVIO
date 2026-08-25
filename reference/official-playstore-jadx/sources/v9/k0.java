package v9;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 extends ba.d {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final fi.iki.elonen.f f22220l = new fi.iki.elonen.f(28);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k0 f22221m = new k0(kotlin.collections.z.f19728i);

    public k0(List list) {
        this.f7274i = ba.k.f7293i;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            h hVar = (h) it.next();
            hVar.getClass();
            int iQ = f22220l.Q(kotlin.jvm.internal.l0.f19747a.b(h.class).o());
            int iA = this.f7274i.a();
            if (iA != 0) {
                if (iA == 1) {
                    ba.r rVar = (ba.r) this.f7274i;
                    int i10 = rVar.f7307l;
                    if (i10 == iQ) {
                        this.f7274i = new ba.r(iQ, hVar);
                    } else {
                        ba.c cVar = new ba.c();
                        cVar.f7272i = new Object[20];
                        cVar.f7273l = 0;
                        this.f7274i = cVar;
                        cVar.b(i10, rVar.f7306i);
                    }
                }
                this.f7274i.b(iQ, hVar);
            } else {
                this.f7274i = new ba.r(iQ, hVar);
            }
        }
    }
}
