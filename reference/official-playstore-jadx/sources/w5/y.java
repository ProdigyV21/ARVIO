package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22498a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f22500c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f22501d;

    public y(String str, String str2, int i10, long j10) {
        this.f22498a = str;
        this.f22499b = str2;
        this.f22500c = i10;
        this.f22501d = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return kotlin.jvm.internal.p.a(this.f22498a, yVar.f22498a) && kotlin.jvm.internal.p.a(this.f22499b, yVar.f22499b) && this.f22500c == yVar.f22500c && this.f22501d == yVar.f22501d;
    }

    public final int hashCode() {
        int iC = (androidx.compose.foundation.c.c(this.f22498a.hashCode() * 31, 31, this.f22499b) + this.f22500c) * 31;
        long j10 = this.f22501d;
        return iC + ((int) (j10 ^ (j10 >>> 32)));
    }

    public final String toString() {
        return "SessionDetails(sessionId=" + this.f22498a + ", firstSessionId=" + this.f22499b + ", sessionIndex=" + this.f22500c + ", sessionStartTimestampUs=" + this.f22501d + ')';
    }
}
