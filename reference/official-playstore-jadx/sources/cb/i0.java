package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 implements e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ya.h f7516a;

    public i0(ya.h hVar) {
        this.f7516a = hVar;
    }

    @Override // cb.e0
    public final ya.h[] childSerializers() {
        return new ya.h[]{this.f7516a};
    }

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        throw new IllegalStateException("unsupported");
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        throw new IllegalStateException("unsupported");
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        throw new IllegalStateException("unsupported");
    }

    @Override // cb.e0
    public final ya.h[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
