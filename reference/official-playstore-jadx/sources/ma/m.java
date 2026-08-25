package ma;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f20345a;

    public m(Throwable th) {
        this.f20345a = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m) {
            return kotlin.jvm.internal.p.a(this.f20345a, ((m) obj).f20345a);
        }
        return false;
    }

    public final int hashCode() {
        Throwable th = this.f20345a;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    @Override // ma.n
    public final String toString() {
        return "Closed(" + this.f20345a + ')';
    }
}
