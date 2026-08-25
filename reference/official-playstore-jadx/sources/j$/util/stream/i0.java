package j$.util.stream;

import j$.util.Optional;

/* JADX INFO: loaded from: classes2.dex */
public final class i0 extends j0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e0 f18720c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e0 f18721d;

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.f18737a) {
            return Optional.of(this.f18738b);
        }
        return null;
    }

    static {
        z6 z6Var = z6.REFERENCE;
        f18720c = new e0(true, z6Var, Optional.empty(), new o(16), new o(17));
        f18721d = new e0(false, z6Var, Optional.empty(), new o(16), new o(17));
    }
}
