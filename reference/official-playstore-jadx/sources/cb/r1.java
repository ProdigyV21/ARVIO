package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class r1 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r1 f7553a = new r1();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7554b = new h1("kotlin.String", ab.e.f797j);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return dVar.v();
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7554b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.E((String) obj);
    }
}
