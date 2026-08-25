package v7;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends d implements Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22179m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22180n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f22181o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22182p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f22183q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f22184r;

    @Override // v7.d
    public final int a(int i10) {
        return ((-i10) >> 31) & (d() >>> (32 - i10));
    }

    @Override // v7.d
    public final int d() {
        int i10 = this.f22179m;
        int i11 = i10 ^ (i10 >>> 2);
        this.f22179m = this.f22180n;
        this.f22180n = this.f22181o;
        this.f22181o = this.f22182p;
        int i12 = this.f22183q;
        this.f22182p = i12;
        int i13 = ((i11 ^ (i11 << 1)) ^ i12) ^ (i12 << 4);
        this.f22183q = i13;
        int i14 = this.f22184r + 362437;
        this.f22184r = i14;
        return i13 + i14;
    }
}
