package p9;

import g8.p0;
import g8.u0;
import j8.t0;
import java.util.Collection;
import java.util.List;
import kotlin.collections.z;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class h extends o {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21223d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j8.b f21224b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u9.s f21225c;

    static {
        m0 m0Var = l0.f19747a;
        f21223d = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(h.class), "allDescriptors", "getAllDescriptors()Ljava/util/List;"))};
    }

    public h(u9.p pVar, j8.b bVar) {
        this.f21224b = bVar;
        t0 t0Var = new t0(this, 2);
        pVar.getClass();
        this.f21225c = new u9.l(pVar, t0Var);
    }

    @Override // p9.o, p9.n
    public final Collection c(f9.f fVar, o8.b bVar) {
        kotlin.reflect.m mVar = f21223d[0];
        List list = (List) this.f21225c.invoke();
        if (list.isEmpty()) {
            return z.f19728i;
        }
        ea.i iVar = new ea.i();
        for (Object obj : list) {
            if ((obj instanceof u0) && kotlin.jvm.internal.p.a(((u0) obj).getName(), fVar)) {
                iVar.add(obj);
            }
        }
        return iVar;
    }

    @Override // p9.o, p9.p
    public final Collection f(f fVar, r7.l lVar) {
        if (!fVar.a(f.f21213n.f21220b)) {
            return z.f19728i;
        }
        kotlin.reflect.m mVar = f21223d[0];
        return (List) this.f21225c.invoke();
    }

    @Override // p9.o, p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        kotlin.reflect.m mVar = f21223d[0];
        List list = (List) this.f21225c.invoke();
        if (list.isEmpty()) {
            return z.f19728i;
        }
        ea.i iVar = new ea.i();
        for (Object obj : list) {
            if ((obj instanceof p0) && kotlin.jvm.internal.p.a(((p0) obj).getName(), fVar)) {
                iVar.add(obj);
            }
        }
        return iVar;
    }

    public abstract List h();
}
