package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class x0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ya.h f7584a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k1 f7585b;

    public x0(ya.h hVar) {
        this.f7584a = hVar;
        this.f7585b = new k1(hVar.getDescriptor());
    }

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        if (dVar.x()) {
            return dVar.q(this.f7584a);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && x0.class == obj.getClass() && kotlin.jvm.internal.p.a(this.f7584a, ((x0) obj).f7584a);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return this.f7585b;
    }

    public final int hashCode() {
        return this.f7584a.hashCode();
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        if (obj != null) {
            eVar.z(this.f7584a, obj);
        } else {
            eVar.q();
        }
    }
}
