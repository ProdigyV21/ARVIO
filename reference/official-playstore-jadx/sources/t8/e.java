package t8;

import java.util.ArrayList;
import kotlin.collections.h0;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21914i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f21915l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(f fVar, int i10) {
        super(0);
        this.f21914i = i10;
        this.f21915l = fVar;
    }

    @Override // r7.a
    public final Object invoke() throws Throwable {
        switch (this.f21914i) {
            case 0:
                f fVar = this.f21915l;
                ArrayList<w8.b> arrayListG = fVar.f21920b.g();
                ArrayList arrayList = new ArrayList();
                for (w8.b bVar : arrayListG) {
                    f9.f name = bVar.getName();
                    if (name == null) {
                        name = p8.b0.f21089b;
                    }
                    k9.g gVarD = fVar.d(bVar);
                    x6.x xVar = gVarD != null ? new x6.x(name, gVarD) : null;
                    if (xVar != null) {
                        arrayList.add(xVar);
                    }
                }
                return h0.A0(arrayList);
            case 1:
                return this.f21915l.f21920b.h().b();
            default:
                f fVar2 = this.f21915l;
                f9.c cVarC = fVar2.c();
                w8.a aVar = fVar2.f21920b;
                androidx.core.provider.e eVar = fVar2.f21919a;
                if (cVarC == null) {
                    return x9.k.b(x9.j.O, aVar.toString());
                }
                s8.a aVar2 = (s8.a) eVar.f2140l;
                s8.a aVar3 = (s8.a) eVar.f2140l;
                g8.f fVarA = f8.e.a(cVarC, aVar2.f21658o.j());
                if (fVarA == null) {
                    fVarA = aVar3.k.a(aVar.t());
                    if (fVarA == null) {
                        fVarA = g8.y.f(aVar3.f21658o, f9.b.j(cVarC), aVar3.f21648d.c().f21716l);
                    }
                }
                return fVarA.m();
        }
    }
}
