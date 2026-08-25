package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class l0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l0 f7529a = new l0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7530b = new h1("kotlin.Int", ab.e.f794g);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return Integer.valueOf(dVar.g());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7530b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.A(((Number) obj).intValue());
    }
}
