package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f7518a = new j();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7519b = new h1("kotlin.Byte", ab.e.f790c);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return Byte.valueOf(dVar.D());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7519b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.h(((Number) obj).byteValue());
    }
}
