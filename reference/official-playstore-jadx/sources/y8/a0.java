package y8;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22913a;

    public a0(String str) {
        this.f22913a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a0) && kotlin.jvm.internal.p.a(this.f22913a, ((a0) obj).f22913a);
    }

    public final int hashCode() {
        return this.f22913a.hashCode();
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("MemberSignature(signature="), this.f22913a, ')');
    }
}
