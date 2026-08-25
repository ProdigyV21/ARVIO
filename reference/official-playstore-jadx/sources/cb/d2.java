package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class d2 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d2 f7487a = new d2();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h0 f7488b = b1.a("kotlin.ULong", q0.f7547a);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return new x6.o0(dVar.h(f7488b).i());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7488b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.k(f7488b).p(((x6.o0) obj).f22594i);
    }
}
