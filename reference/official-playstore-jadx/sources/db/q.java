package db;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f14921a = new q();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ab.i f14922b = xc.d.J("kotlinx.serialization.json.JsonElement", ab.c.f788c, new ab.g[0], p.f14918l);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return k2.c.b(dVar).e();
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f14922b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        m mVar = (m) obj;
        k2.c.a(eVar);
        if (mVar instanceof g0) {
            eVar.z(h0.f14899a, mVar);
        } else if (mVar instanceof b0) {
            eVar.z(e0.f14890a, mVar);
        } else if (mVar instanceof d) {
            eVar.z(f.f14892a, mVar);
        }
    }
}
