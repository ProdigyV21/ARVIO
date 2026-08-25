package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f7499a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7500b = new h1("kotlin.Boolean", ab.e.f789b);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return Boolean.valueOf(dVar.r());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7500b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.t(((Boolean) obj).booleanValue());
    }
}
