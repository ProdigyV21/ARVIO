package m8;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends e implements w8.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Enum f20293b;

    public t(f9.f fVar, Enum r22) {
        super(fVar);
        this.f20293b = r22;
    }

    @Override // w8.m
    public final f9.b d() {
        Class<?> enclosingClass = this.f20293b.getClass();
        if (!enclosingClass.isEnum()) {
            enclosingClass = enclosingClass.getEnclosingClass();
        }
        return c.a(enclosingClass);
    }

    @Override // w8.m
    public final f9.f e() {
        return f9.f.j(this.f20293b.name());
    }
}
