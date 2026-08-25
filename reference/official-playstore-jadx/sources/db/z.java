package db;

import kotlinx.serialization.json.internal.JsonDecodingException;

/* JADX INFO: loaded from: classes5.dex */
public final class z implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z f14930a = new z();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ab.i f14931b = xc.d.K("kotlinx.serialization.json.JsonNull", ab.m.f817b, new ab.g[0]);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        k2.c.b(dVar);
        if (dVar.x()) {
            throw new JsonDecodingException("Expected 'null' literal");
        }
        return y.INSTANCE;
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f14931b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        k2.c.a(eVar);
        eVar.q();
    }
}
