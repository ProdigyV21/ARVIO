package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22472a;

    public n(String str) {
        this.f22472a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof n) && kotlin.jvm.internal.p.a(this.f22472a, ((n) obj).f22472a);
    }

    public final int hashCode() {
        String str = this.f22472a;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("FirebaseSessionsData(sessionId="), this.f22472a, ')');
    }
}
