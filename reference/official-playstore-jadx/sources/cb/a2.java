package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class a2 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a2 f7459a = new a2();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h0 f7460b = b1.a("kotlin.UInt", l0.f7529a);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return new x6.m0(dVar.h(f7460b).g());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7460b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.k(f7460b).A(((x6.m0) obj).f22592i);
    }
}
