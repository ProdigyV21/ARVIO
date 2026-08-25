package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class p implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f7541a = new p();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7542b = new h1("kotlin.Char", ab.e.f791d);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return Character.valueOf(dVar.s());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7542b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.y(((Character) obj).charValue());
    }
}
