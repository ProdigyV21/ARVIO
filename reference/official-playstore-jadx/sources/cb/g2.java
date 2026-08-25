package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class g2 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g2 f7506a = new g2();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h0 f7507b = b1.a("kotlin.UShort", q1.f7549a);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        return new x6.r0(dVar.h(f7507b).m());
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7507b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        eVar.k(f7507b).s(((x6.r0) obj).f22603i);
    }
}
