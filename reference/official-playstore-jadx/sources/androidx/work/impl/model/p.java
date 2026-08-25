package androidx.work.impl.model;

import androidx.work.OverwritingInputMerger;
import h.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class p {
    public static final String x = m2.t.f("WorkSpec");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6922a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6923b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f6924c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f6925d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m2.h f6926e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m2.h f6927f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f6928g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f6929h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f6930i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final m2.f f6931j;
    public final int k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6932l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f6933m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f6934n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f6935o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f6936p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6937q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f6938r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f6939s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f6940t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final long f6941u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f6942v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f6943w;

    public p(String str, int i10, String str2, String str3, m2.h hVar, m2.h hVar2, long j10, long j11, long j12, m2.f fVar, int i11, int i12, long j13, long j14, long j15, long j16, boolean z, int i13, int i14, int i15, long j17, int i16, int i17) {
        this.f6922a = str;
        this.f6923b = i10;
        this.f6924c = str2;
        this.f6925d = str3;
        this.f6926e = hVar;
        this.f6927f = hVar2;
        this.f6928g = j10;
        this.f6929h = j11;
        this.f6930i = j12;
        this.f6931j = fVar;
        this.k = i11;
        this.f6932l = i12;
        this.f6933m = j13;
        this.f6934n = j14;
        this.f6935o = j15;
        this.f6936p = j16;
        this.f6937q = z;
        this.f6938r = i13;
        this.f6939s = i14;
        this.f6940t = i15;
        this.f6941u = j17;
        this.f6942v = i16;
        this.f6943w = i17;
    }

    public final long a() {
        int i10 = this.f6923b;
        int i11 = this.k;
        boolean z = i10 == 1 && i11 > 0;
        int i12 = this.f6932l;
        long j10 = this.f6933m;
        long j11 = this.f6934n;
        boolean zC = c();
        long j12 = this.f6928g;
        long j13 = this.f6930i;
        long j14 = this.f6929h;
        long j15 = this.f6941u;
        boolean z5 = z;
        int i13 = this.f6939s;
        if (j15 != Long.MAX_VALUE && zC) {
            if (i13 != 0) {
                long j16 = j11 + 900000;
                if (j15 < j16) {
                    return j16;
                }
            }
            return j15;
        }
        if (z5) {
            long jScalb = i12 == 2 ? j10 * ((long) i11) : (long) Math.scalb(j10, i11 - 1);
            if (jScalb > 18000000) {
                jScalb = 18000000;
            }
            return j11 + jScalb;
        }
        if (zC) {
            long j17 = i13 == 0 ? j11 + j12 : j11 + j14;
            return (j13 == j14 || i13 != 0) ? j17 : (j14 - j13) + j17;
        }
        if (j11 == -1) {
            return Long.MAX_VALUE;
        }
        return j11 + j12;
    }

    public final boolean b() {
        return !m2.f.f20187i.equals(this.f6931j);
    }

    public final boolean c() {
        return this.f6929h != 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return kotlin.jvm.internal.p.a(this.f6922a, pVar.f6922a) && this.f6923b == pVar.f6923b && kotlin.jvm.internal.p.a(this.f6924c, pVar.f6924c) && kotlin.jvm.internal.p.a(this.f6925d, pVar.f6925d) && kotlin.jvm.internal.p.a(this.f6926e, pVar.f6926e) && kotlin.jvm.internal.p.a(this.f6927f, pVar.f6927f) && this.f6928g == pVar.f6928g && this.f6929h == pVar.f6929h && this.f6930i == pVar.f6930i && kotlin.jvm.internal.p.a(this.f6931j, pVar.f6931j) && this.k == pVar.k && this.f6932l == pVar.f6932l && this.f6933m == pVar.f6933m && this.f6934n == pVar.f6934n && this.f6935o == pVar.f6935o && this.f6936p == pVar.f6936p && this.f6937q == pVar.f6937q && this.f6938r == pVar.f6938r && this.f6939s == pVar.f6939s && this.f6940t == pVar.f6940t && this.f6941u == pVar.f6941u && this.f6942v == pVar.f6942v && this.f6943w == pVar.f6943w;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v37, types: [int] */
    /* JADX WARN: Type inference failed for: r2v51 */
    /* JADX WARN: Type inference failed for: r2v52 */
    public final int hashCode() {
        int iHashCode = (this.f6927f.hashCode() + ((this.f6926e.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((f0.c(this.f6923b) + (this.f6922a.hashCode() * 31)) * 31, 31, this.f6924c), 31, this.f6925d)) * 31)) * 31;
        long j10 = this.f6928g;
        int i10 = (iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.f6929h;
        int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
        long j12 = this.f6930i;
        int iC = (f0.c(this.f6932l) + ((((this.f6931j.hashCode() + ((i11 + ((int) (j12 ^ (j12 >>> 32)))) * 31)) * 31) + this.k) * 31)) * 31;
        long j13 = this.f6933m;
        int i12 = (iC + ((int) (j13 ^ (j13 >>> 32)))) * 31;
        long j14 = this.f6934n;
        int i13 = (i12 + ((int) (j14 ^ (j14 >>> 32)))) * 31;
        long j15 = this.f6935o;
        int i14 = (i13 + ((int) (j15 ^ (j15 >>> 32)))) * 31;
        long j16 = this.f6936p;
        int i15 = (i14 + ((int) (j16 ^ (j16 >>> 32)))) * 31;
        boolean z = this.f6937q;
        ?? r22 = z;
        if (z) {
            r22 = 1;
        }
        int iC2 = (((((f0.c(this.f6938r) + ((i15 + r22) * 31)) * 31) + this.f6939s) * 31) + this.f6940t) * 31;
        long j17 = this.f6941u;
        return ((((iC2 + ((int) ((j17 >>> 32) ^ j17))) * 31) + this.f6942v) * 31) + this.f6943w;
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("{WorkSpec: "), this.f6922a, '}');
    }

    public /* synthetic */ p(String str, int i10, String str2, String str3, m2.h hVar, m2.h hVar2, long j10, long j11, long j12, m2.f fVar, int i11, int i12, long j13, long j14, long j15, long j16, boolean z, int i13, int i14, long j17, int i15, int i16, int i17) {
        this(str, (i17 & 2) != 0 ? 1 : i10, str2, (i17 & 8) != 0 ? OverwritingInputMerger.class.getName() : str3, (i17 & 16) != 0 ? m2.h.f20202c : hVar, (i17 & 32) != 0 ? m2.h.f20202c : hVar2, (i17 & 64) != 0 ? 0L : j10, (i17 & 128) != 0 ? 0L : j11, (i17 & 256) != 0 ? 0L : j12, (i17 & 512) != 0 ? m2.f.f20187i : fVar, (i17 & 1024) != 0 ? 0 : i11, (i17 & 2048) != 0 ? 1 : i12, (i17 & 4096) != 0 ? 30000L : j13, (i17 & 8192) != 0 ? -1L : j14, (i17 & 16384) == 0 ? j15 : 0L, (32768 & i17) != 0 ? -1L : j16, (65536 & i17) != 0 ? false : z, (131072 & i17) != 0 ? 1 : i13, (262144 & i17) != 0 ? 0 : i14, 0, (1048576 & i17) != 0 ? Long.MAX_VALUE : j17, (2097152 & i17) != 0 ? 0 : i15, (i17 & 4194304) != 0 ? -256 : i16);
    }
}
