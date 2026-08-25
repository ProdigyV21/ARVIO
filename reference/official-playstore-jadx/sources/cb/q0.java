package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class q0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q0 f7547a = new q0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7548b = new h1("kotlin.Long", ab.e.f795h);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return Long.valueOf(dVar.i());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7548b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.p(((Number) obj).longValue());
    }
}
