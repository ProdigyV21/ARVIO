package t8;

import androidx.appcompat.app.i1;
import g8.b1;
import g8.e1;
import g8.v0;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import v9.r0;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements h8.b, r8.i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21918i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.core.provider.e f21919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w8.a f21920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u9.t f21921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u9.s f21922d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final v8.a f21923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u9.s f21924f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f21925g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f21926h;

    static {
        m0 m0Var = l0.f19747a;
        f21918i = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(f.class), "fqName", "getFqName()Lorg/jetbrains/kotlin/name/FqName;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(f.class), LinkHeader.Parameters.Type, "getType()Lorg/jetbrains/kotlin/types/SimpleType;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(f.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    public f(androidx.core.provider.e eVar, w8.a aVar, boolean z) {
        this.f21919a = eVar;
        this.f21920b = aVar;
        s8.a aVar2 = (s8.a) eVar.f2140l;
        u9.v vVar = aVar2.f21645a;
        this.f21921c = vVar.c(new e(this, 1));
        this.f21922d = vVar.d(new e(this, 2));
        this.f21923e = aVar2.f21654j.c(aVar);
        this.f21924f = vVar.d(new e(this, 0));
        this.f21925g = false;
        this.f21926h = z;
    }

    @Override // h8.b
    public final Map a() {
        kotlin.reflect.m mVar = f21918i[2];
        return (Map) this.f21924f.invoke();
    }

    @Override // r8.i
    public final boolean b() {
        return this.f21925g;
    }

    @Override // h8.b
    public final f9.c c() {
        kotlin.reflect.m mVar = f21918i[0];
        return (f9.c) this.f21921c.invoke();
    }

    public final k9.g d(w8.b bVar) throws Throwable {
        v9.w wVarH;
        if (bVar instanceof w8.o) {
            return k9.h.b(((w8.o) bVar).getValue(), null);
        }
        if (bVar instanceof w8.m) {
            w8.m mVar = (w8.m) bVar;
            return new k9.i(mVar.d(), mVar.e());
        }
        boolean z = bVar instanceof w8.e;
        androidx.core.provider.e eVar = this.f21919a;
        if (z) {
            w8.e eVar2 = (w8.e) bVar;
            f9.f name = eVar2.getName();
            if (name == null) {
                name = p8.b0.f21089b;
            }
            ArrayList arrayListC = eVar2.c();
            kotlin.reflect.m mVar2 = f21918i[1];
            if (!v9.c.g((v9.b0) this.f21922d.invoke())) {
                e1 e1VarL = f4.f.l(name, m9.d.d(this));
                if (e1VarL == null || (wVarH = e1VarL.getType()) == null) {
                    wVarH = ((s8.a) eVar.f2140l).f21658o.j().h(x9.k.b(x9.j.N, new String[0]));
                }
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(arrayListC, 10));
                Iterator it = arrayListC.iterator();
                while (it.hasNext()) {
                    k9.g gVarD = d((w8.b) it.next());
                    if (gVarD == null) {
                        gVarD = new k9.u(null);
                    }
                    arrayList.add(gVarD);
                }
                return new k9.x(arrayList, wVarH);
            }
        } else {
            if (bVar instanceof w8.c) {
                return new k9.a(new f(eVar, ((w8.c) bVar).a(), false));
            }
            if (bVar instanceof w8.h) {
                v9.w wVarD0 = ((i1) eVar.f2144p).d0(((w8.h) bVar).b(), qb.l.C(2, false, null, 7));
                if (!v9.c.g(wVarD0)) {
                    v9.w type = wVarD0;
                    int i10 = 0;
                    while (d8.k.y(type)) {
                        type = ((r0) kotlin.collections.x.R0(type.G0())).getType();
                        i10++;
                    }
                    g8.h hVarE = type.I0().e();
                    if (hVarE instanceof g8.f) {
                        f9.b bVarF = m9.d.f(hVarE);
                        return bVarF == null ? new k9.s(new k9.p(wVarD0)) : new k9.s(bVarF, i10);
                    }
                    if (hVarE instanceof b1) {
                        return new k9.s(f9.b.j(d8.p.f14732a.g()), 0);
                    }
                }
            }
        }
        return null;
    }

    @Override // h8.b
    public final v0 f() {
        return this.f21923e;
    }

    @Override // h8.b
    public final v9.w getType() {
        kotlin.reflect.m mVar = f21918i[1];
        return (v9.b0) this.f21922d.invoke();
    }

    public final String toString() {
        return h9.h.f15907c.w(this, null);
    }
}
