package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22481b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f22482c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f22483d;

    public q(String str, int i10, int i11, boolean z) {
        this.f22480a = str;
        this.f22481b = i10;
        this.f22482c = i11;
        this.f22483d = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return kotlin.jvm.internal.p.a(this.f22480a, qVar.f22480a) && this.f22481b == qVar.f22481b && this.f22482c == qVar.f22482c && this.f22483d == qVar.f22483d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public final int hashCode() {
        int iHashCode = ((((this.f22480a.hashCode() * 31) + this.f22481b) * 31) + this.f22482c) * 31;
        boolean z = this.f22483d;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ProcessDetails(processName=");
        sb2.append(this.f22480a);
        sb2.append(", pid=");
        sb2.append(this.f22481b);
        sb2.append(", importance=");
        sb2.append(this.f22482c);
        sb2.append(", isDefaultProcess=");
        return a0.c.r(sb2, this.f22483d, ')');
    }
}
