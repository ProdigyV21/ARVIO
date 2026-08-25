package y8;

import g8.e1;
import g8.v0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f22948a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ n f22949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f9.f f22950c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l f22951d;

    public k(n nVar, f9.f fVar, l lVar) {
        this.f22949b = nVar;
        this.f22950c = fVar;
        this.f22951d = lVar;
    }

    @Override // y8.y.b
    public final void a() {
        m mVar = (m) this.f22951d;
        g8.f fVar = mVar.f22955d;
        f9.f fVar2 = this.f22950c;
        e1 e1VarL = f4.f.l(fVar2, fVar);
        ArrayList arrayList = this.f22948a;
        if (e1VarL != null) {
            mVar.f22953b.put(fVar2, new k9.x(ea.o.e(arrayList), e1VarL.getType()));
            return;
        }
        if (mVar.f22954c.f(mVar.f22956e) && kotlin.jvm.internal.p.a(fVar2.d(), "value")) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (obj instanceof k9.a) {
                    arrayList2.add(obj);
                }
            }
            List list = mVar.f22957f;
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                list.add((h8.b) ((k9.a) it.next()).f19530a);
            }
        }
    }

    @Override // y8.y.b
    public final void b(f9.b bVar, f9.f fVar) {
        this.f22948a.add(new k9.i(bVar, fVar));
    }

    @Override // y8.y.b
    public final y.a c(f9.b bVar) {
        ArrayList arrayList = new ArrayList();
        n nVar = this.f22949b;
        return new j(new m(nVar, g8.y.f(nVar.f22959m, bVar, nVar.f22960n), bVar, arrayList, v0.f15554b), this, arrayList);
    }

    @Override // y8.y.b
    public final void d(Object obj) {
        k9.g gVarB = k9.h.b(obj, this.f22949b.f22959m);
        if (gVarB == null) {
            gVarB = new k9.j("Unsupported annotation argument: " + this.f22950c);
        }
        this.f22948a.add(gVarB);
    }

    @Override // y8.y.b
    public final void e(k9.f fVar) {
        this.f22948a.add(new k9.s(fVar));
    }
}
