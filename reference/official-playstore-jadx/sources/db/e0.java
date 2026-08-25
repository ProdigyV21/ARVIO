package db;

import cb.r1;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e0 f14890a = new e0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d0 f14891b = d0.f14884b;

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        k2.c.b(dVar);
        return new b0((Map) new cb.g0(r1.f7553a, q.f14921a, 1).e(dVar));
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f14891b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        k2.c.a(eVar);
        new cb.g0(r1.f7553a, q.f14921a, 1).serialize(eVar, (b0) obj);
    }
}
