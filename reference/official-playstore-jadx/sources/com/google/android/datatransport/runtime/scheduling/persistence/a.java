package com.google.android.datatransport.runtime.scheduling.persistence;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f12609f = new a(10485760, 200, 10000, 81920, 604800000);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f12610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f12611b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f12612c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f12613d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f12614e;

    public a(long j10, int i10, int i11, int i12, long j11) {
        this.f12610a = j10;
        this.f12611b = i10;
        this.f12612c = i11;
        this.f12613d = j11;
        this.f12614e = i12;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.f12610a == aVar.f12610a && this.f12611b == aVar.f12611b && this.f12612c == aVar.f12612c && this.f12613d == aVar.f12613d && this.f12614e == aVar.f12614e) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f12610a;
        int i10 = (((((((int) (j10 ^ (j10 >>> 32))) ^ 1000003) * 1000003) ^ this.f12611b) * 1000003) ^ this.f12612c) * 1000003;
        long j11 = this.f12613d;
        return ((i10 ^ ((int) (j11 ^ (j11 >>> 32)))) * 1000003) ^ this.f12614e;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("EventStoreConfig{maxStorageSizeInBytes=");
        sb2.append(this.f12610a);
        sb2.append(", loadBatchSize=");
        sb2.append(this.f12611b);
        sb2.append(", criticalSectionEnterTimeoutMs=");
        sb2.append(this.f12612c);
        sb2.append(", eventCleanUpAge=");
        sb2.append(this.f12613d);
        sb2.append(", maxBlobByteSizePerRow=");
        return androidx.compose.material3.d.j(this.f12614e, "}", sb2);
    }
}
