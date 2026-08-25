package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class q1 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q1 f7549a = new q1();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7550b = new h1("kotlin.Short", ab.e.f796i);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return Short.valueOf(dVar.m());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7550b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.s(((Number) obj).shortValue());
    }
}
