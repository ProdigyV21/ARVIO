package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d0 f7475a = new d0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7476b = new h1("kotlin.Float", ab.e.f793f);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return Float.valueOf(dVar.n());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7476b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.w(((Number) obj).floatValue());
    }
}
