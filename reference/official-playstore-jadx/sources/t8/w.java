package t8;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21980i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ z f21981l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(z zVar, int i10) {
        super(0);
        this.f21980i = i10;
        this.f21981l = zVar;
    }

    @Override // r7.a
    public final Object invoke() {
        int i10 = this.f21980i;
        z zVar = this.f21981l;
        switch (i10) {
            case 0:
                p9.f fVar = p9.f.f21212m;
                p9.n.f21236a.getClass();
                p9.k kVar = p9.k.f21229l;
                List list = fVar.f21219a;
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                boolean zA = fVar.a(p9.f.f21211l);
                o8.e eVar = o8.e.f20796n;
                if (zA) {
                    Iterator it = zVar.h(fVar, kVar).iterator();
                    while (it.hasNext()) {
                        ea.o.d(linkedHashSet, zVar.e((f9.f) it.next(), eVar));
                    }
                }
                if (fVar.a(p9.f.f21209i) && !list.contains(p9.b.f21198a)) {
                    Iterator it2 = zVar.i(fVar, kVar).iterator();
                    while (it2.hasNext()) {
                        linkedHashSet.addAll(zVar.c((f9.f) it2.next(), eVar));
                    }
                }
                if (fVar.a(p9.f.f21210j) && !list.contains(p9.b.f21198a)) {
                    Iterator it3 = zVar.n().iterator();
                    while (it3.hasNext()) {
                        linkedHashSet.addAll(zVar.g((f9.f) it3.next(), eVar));
                    }
                }
                return kotlin.collections.x.c1(linkedHashSet);
            case 1:
                return zVar.h(p9.f.f21214o, null);
            case 2:
                return zVar.k();
            case 3:
                return zVar.i(p9.f.f21215p, null);
            default:
                p9.l lVar = p9.f.f21203c;
                return zVar.n();
        }
    }
}
