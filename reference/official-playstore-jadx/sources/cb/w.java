package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class w implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w f7577a = new w();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7578b = new h1("kotlin.Double", ab.e.f792e);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return Double.valueOf(dVar.p());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7578b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.f(((Number) obj).doubleValue());
    }
}
