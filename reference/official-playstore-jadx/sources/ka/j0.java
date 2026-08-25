package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class j0 extends d7.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i0 f19600l = new i0();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f19601i;

    public j0(String str) {
        super(f19600l);
        this.f19601i = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof j0) && kotlin.jvm.internal.p.a(this.f19601i, ((j0) obj).f19601i);
    }

    public final int hashCode() {
        return this.f19601i.hashCode();
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("CoroutineName("), this.f19601i, ')');
    }
}
