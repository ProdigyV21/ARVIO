package androidx.work.impl.constraints;

/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f6801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f6802b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f6803c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f6804d;

    public e(boolean z, boolean z5, boolean z10, boolean z11) {
        this.f6801a = z;
        this.f6802b = z5;
        this.f6803c = z10;
        this.f6804d = z11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.f6801a == eVar.f6801a && this.f6802b == eVar.f6802b && this.f6803c == eVar.f6803c && this.f6804d == eVar.f6804d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public final int hashCode() {
        boolean z = this.f6801a;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i10 = r1 * 31;
        boolean z5 = this.f6802b;
        ?? r22 = z5;
        if (z5) {
            r22 = 1;
        }
        int i11 = (i10 + r22) * 31;
        boolean z10 = this.f6803c;
        ?? r23 = z10;
        if (z10) {
            r23 = 1;
        }
        int i12 = (i11 + r23) * 31;
        boolean z11 = this.f6804d;
        return i12 + (z11 ? 1 : z11);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("NetworkState(isConnected=");
        sb2.append(this.f6801a);
        sb2.append(", isValidated=");
        sb2.append(this.f6802b);
        sb2.append(", isMetered=");
        sb2.append(this.f6803c);
        sb2.append(", isNotRoaming=");
        return a0.c.r(sb2, this.f6804d, ')');
    }
}
