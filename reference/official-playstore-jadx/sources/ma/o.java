package ma;

/* JADX INFO: loaded from: classes5.dex */
public final class o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n f20346b = new n();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f20347a;

    public static final Object a(Object obj) {
        if (obj instanceof n) {
            return null;
        }
        return obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o) {
            return kotlin.jvm.internal.p.a(this.f20347a, ((o) obj).f20347a);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f20347a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.f20347a;
        if (obj instanceof m) {
            return ((m) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
