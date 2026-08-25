package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public final class n0 implements u1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n0 f2479b = new n0(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2480a;

    public /* synthetic */ n0(int i10) {
        this.f2480a = i10;
    }

    @Override // androidx.datastore.preferences.protobuf.u1
    public final t1 a(Class cls) {
        switch (this.f2480a) {
            case 0:
                if (!r0.class.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
                }
                try {
                    return (t1) r0.i(cls.asSubclass(r0.class)).h(3);
                } catch (Exception e5) {
                    throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e5);
                }
            default:
                throw new IllegalStateException("This should never be called.");
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u1
    public final boolean b(Class cls) {
        switch (this.f2480a) {
            case 0:
                return r0.class.isAssignableFrom(cls);
            default:
                return false;
        }
    }
}
