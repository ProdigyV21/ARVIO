package db;

import cb.d2;
import cb.h1;
import eb.j0;
import kotlin.jvm.internal.l0;
import x6.o0;

/* JADX INFO: loaded from: classes5.dex */
public final class v implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final v f14927a = new v();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f14928b = xc.d.F("kotlinx.serialization.json.JsonLiteral");

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        m mVarE = k2.c.b(dVar).e();
        if (mVarE instanceof u) {
            return (u) mVarE;
        }
        StringBuilder sb2 = new StringBuilder("Unexpected JSON element, expected JsonLiteral, had ");
        throw eb.r.d(g8.b.h(l0.f19747a, mVarE.getClass(), sb2), mVarE.toString(), -1);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f14928b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        u uVar = (u) obj;
        String str = uVar.f14926m;
        String str2 = uVar.f14926m;
        k2.c.a(eVar);
        if (uVar.f14924i) {
            eVar.E(str);
            return;
        }
        ab.g gVar = uVar.f14925l;
        if (gVar != null) {
            eVar.k(gVar).E(str);
            return;
        }
        cb.h0 h0Var = n.f14910a;
        Long lS = kotlin.text.u.S(str2);
        if (lS != null) {
            eVar.p(lS.longValue());
            return;
        }
        o0 o0VarI = k2.c.I(str);
        if (o0VarI != null) {
            eVar.k(d2.f7488b).p(o0VarI.f22594i);
            return;
        }
        Double dG = kotlin.text.t.G(str2);
        if (dG != null) {
            eVar.f(dG.doubleValue());
            return;
        }
        String strB = uVar.b();
        String[] strArr = j0.f15157a;
        Boolean bool = strB.equalsIgnoreCase("true") ? Boolean.TRUE : strB.equalsIgnoreCase("false") ? Boolean.FALSE : null;
        if (bool != null) {
            eVar.t(bool.booleanValue());
        } else {
            eVar.E(str);
        }
    }
}
