package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class x1 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x1 f7586a = new x1();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h0 f7587b = b1.a("kotlin.UByte", j.f7518a);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return new x6.k0(dVar.h(f7587b).D());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7587b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.k(f7587b).h(((x6.k0) obj).f22590i);
    }
}
