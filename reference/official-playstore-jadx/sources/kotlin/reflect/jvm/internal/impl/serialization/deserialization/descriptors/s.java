package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class s extends p9.o {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f19893f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s9.m f19894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final n f19895c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u9.s f19896d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u9.t f19897e;

    static {
        m0 m0Var = l0.f19747a;
        f19893f = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(s.class), "classNames", "getClassNames$deserialization()Ljava/util/Set;")), m0Var.g(new b0(m0Var.b(s.class), "classifierNamesLazy", "getClassifierNamesLazy()Ljava/util/Set;"))};
    }

    public s(s9.m mVar, List list, List list2, List list3, r7.a aVar) {
        this.f19894b = mVar;
        s9.j jVar = mVar.f21731a;
        jVar.f21708c.getClass();
        this.f19895c = new q(this, list, list2, list3);
        u9.p pVar = jVar.f21706a;
        r rVar = new r(aVar);
        pVar.getClass();
        this.f19896d = new u9.l(pVar, rVar);
        r rVar2 = new r(this, 0);
        pVar.getClass();
        this.f19897e = new u9.k(pVar, rVar2);
    }

    @Override // p9.o, p9.n
    public final Set a() {
        return this.f19895c.a();
    }

    @Override // p9.o, p9.n
    public final Set b() {
        return this.f19895c.b();
    }

    @Override // p9.o, p9.n
    public Collection c(f9.f fVar, o8.b bVar) {
        return this.f19895c.e(fVar);
    }

    @Override // p9.o, p9.n
    public final Set d() {
        kotlin.reflect.m mVar = f19893f[1];
        return (Set) this.f19897e.invoke();
    }

    @Override // p9.o, p9.n
    public Collection g(f9.f fVar, o8.e eVar) {
        return this.f19895c.f(fVar);
    }

    public abstract void h(ArrayList arrayList);

    public final List i(p9.f fVar, r7.l lVar) {
        ArrayList arrayList = new ArrayList(0);
        if (fVar.a(p9.f.f21206f)) {
            h(arrayList);
        }
        n nVar = this.f19895c;
        nVar.c(arrayList, fVar, lVar);
        if (fVar.a(p9.f.f21211l)) {
            for (f9.f fVar2 : m()) {
                if (((Boolean) lVar.invoke(fVar2)).booleanValue()) {
                    ea.o.d(arrayList, (g8.f) this.f19894b.f21731a.f21724t.f21696b.invoke(new s9.f(l(fVar2), null)));
                }
            }
        }
        if (fVar.a(p9.f.f21207g)) {
            for (f9.f fVar3 : nVar.d()) {
                if (((Boolean) lVar.invoke(fVar3)).booleanValue()) {
                    ea.o.d(arrayList, nVar.g(fVar3));
                }
            }
        }
        return ea.o.e(arrayList);
    }

    public abstract f9.b l(f9.f fVar);

    public final Set m() {
        kotlin.reflect.m mVar = f19893f[0];
        return (Set) this.f19896d.invoke();
    }

    public abstract Set n();

    public abstract Set o();

    public abstract Set p();

    public boolean q(f9.f fVar) {
        return m().contains(fVar);
    }

    public boolean r(v vVar) {
        return true;
    }

    public void j(f9.f fVar, ArrayList arrayList) {
    }

    public void k(f9.f fVar, ArrayList arrayList) {
    }
}
