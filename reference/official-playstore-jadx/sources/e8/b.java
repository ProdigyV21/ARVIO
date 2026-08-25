package e8;

import d8.q;
import g8.b0;
import g8.b1;
import g8.y;
import g8.y0;
import g8.z0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import v9.k0;
import v9.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends v9.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ c f15045c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar) {
        super(cVar.f15048o);
        this.f15045c = cVar;
    }

    @Override // v9.g
    public final Collection c() {
        List<f9.b> listE;
        c cVar = this.f15045c;
        int i10 = cVar.f15051r;
        j jVar = cVar.f15050q;
        f fVar = f.f15055c;
        if (p.a(jVar, fVar)) {
            listE = Collections.singletonList(c.f15046v);
        } else if (p.a(jVar, g.f15056c)) {
            listE = t7.a.E(c.f15047w, new f9.b(q.f14766l, fVar.a(i10)));
        } else {
            i iVar = i.f15058c;
            if (p.a(jVar, iVar)) {
                listE = Collections.singletonList(c.f15046v);
            } else {
                if (!p.a(jVar, h.f15057c)) {
                    int i11 = fa.a.f15399a;
                    throw new IllegalStateException("should not be called");
                }
                listE = t7.a.E(c.f15047w, new f9.b(q.f14761f, iVar.a(i10)));
            }
        }
        b0 b0VarD = cVar.f15049p.d();
        ArrayList arrayList = new ArrayList(s.U(listE, 10));
        for (f9.b bVar : listE) {
            g8.f fVarD = y.d(b0VarD, bVar);
            if (fVarD == null) {
                throw new IllegalStateException(("Built-in class " + bVar + " not found").toString());
            }
            List listY0 = x.Y0(fVarD.g().getParameters().size(), cVar.f15054u);
            ArrayList arrayList2 = new ArrayList(s.U(listY0, 10));
            Iterator it = listY0.iterator();
            while (it.hasNext()) {
                arrayList2.add(new t0(((b1) it.next()).m()));
            }
            k0.f22220l.getClass();
            arrayList.add(v9.e.r(arrayList2, k0.f22221m, fVarD.g(), false));
        }
        return x.c1(arrayList);
    }

    @Override // v9.b, v9.o0
    public final g8.h e() {
        return this.f15045c;
    }

    @Override // v9.o0
    public final boolean f() {
        return true;
    }

    @Override // v9.o0
    public final List getParameters() {
        return this.f15045c.f15054u;
    }

    @Override // v9.g
    public final z0 h() {
        return y0.f15559l;
    }

    @Override // v9.b
    /* JADX INFO: renamed from: n */
    public final g8.f e() {
        return this.f15045c;
    }

    public final String toString() {
        return this.f15045c.toString();
    }
}
