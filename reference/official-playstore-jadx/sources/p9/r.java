package p9;

import g8.p0;
import g8.u0;
import java.util.Collection;
import java.util.List;
import kotlin.collections.x;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends o {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21239f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k f21240b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f21241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u9.s f21242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u9.s f21243e;

    static {
        m0 m0Var = l0.f19747a;
        f21239f = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(r.class), "functions", "getFunctions()Ljava/util/List;")), m0Var.g(new b0(m0Var.b(r.class), "properties", "getProperties()Ljava/util/List;"))};
    }

    public r(u9.p pVar, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k kVar, boolean z) {
        this.f21240b = kVar;
        this.f21241c = z;
        q qVar = new q(this, 0);
        pVar.getClass();
        this.f21242d = new u9.l(pVar, qVar);
        this.f21243e = new u9.l(pVar, new q(this, 1));
    }

    @Override // p9.o, p9.n
    public final Collection c(f9.f fVar, o8.b bVar) {
        kotlin.reflect.m mVar = f21239f[0];
        List list = (List) this.f21242d.invoke();
        ea.i iVar = new ea.i();
        for (Object obj : list) {
            if (kotlin.jvm.internal.p.a(((u0) obj).getName(), fVar)) {
                iVar.add(obj);
            }
        }
        return iVar;
    }

    @Override // p9.o, p9.p
    public final /* bridge */ /* synthetic */ g8.h e(f9.f fVar, o8.b bVar) {
        return null;
    }

    @Override // p9.o, p9.p
    public final Collection f(f fVar, r7.l lVar) {
        kotlin.reflect.m[] mVarArr = f21239f;
        kotlin.reflect.m mVar = mVarArr[0];
        List list = (List) this.f21242d.invoke();
        kotlin.reflect.m mVar2 = mVarArr[1];
        return x.I0(list, (List) this.f21243e.invoke());
    }

    @Override // p9.o, p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        kotlin.reflect.m mVar = f21239f[1];
        List list = (List) this.f21243e.invoke();
        ea.i iVar = new ea.i();
        for (Object obj : list) {
            if (kotlin.jvm.internal.p.a(((p0) obj).getName(), fVar)) {
                iVar.add(obj);
            }
        }
        return iVar;
    }
}
