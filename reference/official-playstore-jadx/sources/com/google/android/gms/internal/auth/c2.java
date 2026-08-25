package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class c2 implements u2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c2 f13013b = new c2(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13014a;

    public /* synthetic */ c2(int i10) {
        this.f13014a = i10;
    }

    @Override // com.google.android.gms.internal.auth.u2
    public final t2 a(Class cls) {
        switch (this.f13014a) {
            case 0:
                if (!d2.class.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
                }
                try {
                    return (t2) d2.a(cls.asSubclass(d2.class)).h(3);
                } catch (Exception e5) {
                    throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e5);
                }
            default:
                throw new IllegalStateException("This should never be called.");
        }
    }

    @Override // com.google.android.gms.internal.auth.u2
    public final boolean b(Class cls) {
        switch (this.f13014a) {
            case 0:
                return d2.class.isAssignableFrom(cls);
            default:
                return false;
        }
    }
}
