package db;

import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public final class h0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h0 f14899a = new h0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ab.i f14900b = xc.d.K("kotlinx.serialization.json.JsonPrimitive", ab.e.f797j, new ab.g[0]);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        m mVarE = k2.c.b(dVar).e();
        if (mVarE instanceof g0) {
            return (g0) mVarE;
        }
        StringBuilder sb2 = new StringBuilder("Unexpected JSON element, expected JsonPrimitive, had ");
        throw eb.r.d(g8.b.h(l0.f19747a, mVarE.getClass(), sb2), mVarE.toString(), -1);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f14900b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        g0 g0Var = (g0) obj;
        k2.c.a(eVar);
        if (g0Var instanceof y) {
            eVar.z(z.f14930a, y.INSTANCE);
        } else {
            eVar.z(v.f14927a, (u) g0Var);
        }
    }
}
