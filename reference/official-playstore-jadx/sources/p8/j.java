package p8;

import g8.s0;
import g8.u0;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements i9.g {
    @Override // i9.g
    public final int a(g8.a aVar, g8.a aVar2, g8.f fVar) {
        ga.j jVar;
        ga.j jVar2;
        if (!(aVar2 instanceof r8.g)) {
            return 4;
        }
        r8.g gVar = (r8.g) aVar2;
        if (!gVar.getTypeParameters().isEmpty()) {
            return 4;
        }
        i9.m mVarI = i9.n.i(aVar, aVar2);
        if ((mVarI != null ? mVarI.c() : 0) != 0) {
            return 4;
        }
        ga.m mVarG = kotlin.collections.r.G(new ga.m[]{new ga.t(new ga.p(gVar.e(), 3), e.f21129o), new eb.v(gVar.f19379q, 1)});
        com.arflix.tv.util.a aVar3 = new com.arflix.tv.util.a(1);
        if (mVarG instanceof ga.t) {
            ga.t tVar = (ga.t) mVarG;
            jVar = new ga.j(tVar.f15603a, tVar.f15604b, aVar3);
        } else {
            jVar = new ga.j(mVarG, new com.arflix.tv.util.a(3), aVar3);
        }
        s0 s0Var = gVar.f19381s;
        ga.m mVarG2 = kotlin.collections.r.G(new ga.m[]{jVar, new ga.p(t7.a.F(s0Var != null ? s0Var.getType() : null), 3)});
        com.arflix.tv.util.a aVar4 = new com.arflix.tv.util.a(1);
        if (mVarG2 instanceof ga.t) {
            ga.t tVar2 = (ga.t) mVarG2;
            jVar2 = new ga.j(tVar2.f15603a, tVar2.f15604b, aVar4);
        } else {
            jVar2 = new ga.j(mVarG2, new com.arflix.tv.util.a(3), aVar4);
        }
        ga.h hVar = new ga.h(jVar2);
        while (hVar.hasNext()) {
            v9.w wVar = (v9.w) hVar.next();
            if (!wVar.G0().isEmpty() && !(wVar.L0() instanceof u8.f)) {
                return 4;
            }
        }
        g8.a aVarBuild = (g8.a) aVar.b(new y0(new u8.d()));
        if (aVarBuild == null) {
            return 4;
        }
        if (aVarBuild instanceof u0) {
            u0 u0Var = (u0) aVarBuild;
            if (!u0Var.getTypeParameters().isEmpty()) {
                aVarBuild = u0Var.C0().g().build();
            }
        }
        return i.f21150a[h.f0.c(i9.n.f16259c.n(aVarBuild, aVar2, false).c())] == 1 ? 1 : 4;
    }

    @Override // i9.g
    public final int b() {
        return 2;
    }
}
