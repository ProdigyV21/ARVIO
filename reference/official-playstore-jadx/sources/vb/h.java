package vb;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f22300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f22301b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f22302c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Integer f22303d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f22304e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f22305f;

    public h(boolean z, Integer num, boolean z5, Integer num2, boolean z10, boolean z11) {
        this.f22300a = z;
        this.f22301b = num;
        this.f22302c = z5;
        this.f22303d = num2;
        this.f22304e = z10;
        this.f22305f = z11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f22300a == hVar.f22300a && p.a(this.f22301b, hVar.f22301b) && this.f22302c == hVar.f22302c && p.a(this.f22303d, hVar.f22303d) && this.f22304e == hVar.f22304e && this.f22305f == hVar.f22305f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r3v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    public final int hashCode() {
        boolean z = this.f22300a;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i10 = r1 * 31;
        Integer num = this.f22301b;
        int iHashCode = (i10 + (num == null ? 0 : num.hashCode())) * 31;
        boolean z5 = this.f22302c;
        ?? r32 = z5;
        if (z5) {
            r32 = 1;
        }
        int i11 = (iHashCode + r32) * 31;
        Integer num2 = this.f22303d;
        int iHashCode2 = (i11 + (num2 != null ? num2.hashCode() : 0)) * 31;
        boolean z10 = this.f22304e;
        ?? r22 = z10;
        if (z10) {
            r22 = 1;
        }
        int i12 = (iHashCode2 + r22) * 31;
        boolean z11 = this.f22305f;
        return i12 + (z11 ? 1 : z11);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("WebSocketExtensions(perMessageDeflate=");
        sb2.append(this.f22300a);
        sb2.append(", clientMaxWindowBits=");
        sb2.append(this.f22301b);
        sb2.append(", clientNoContextTakeover=");
        sb2.append(this.f22302c);
        sb2.append(", serverMaxWindowBits=");
        sb2.append(this.f22303d);
        sb2.append(", serverNoContextTakeover=");
        sb2.append(this.f22304e);
        sb2.append(", unknownValues=");
        return a0.c.r(sb2, this.f22305f, ')');
    }
}
