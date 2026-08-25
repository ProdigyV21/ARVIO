package io.sentry.android.core.anr;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StackTraceElement[] f16427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16428b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f16429c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f16430d;

    public b(StackTraceElement[] stackTraceElementArr, int i10, int i11) {
        this.f16427a = stackTraceElementArr;
        this.f16428b = i10;
        this.f16429c = i11;
        int iHashCode = 1;
        while (i10 <= this.f16429c) {
            iHashCode = (iHashCode * 31) + this.f16427a[i10].hashCode();
            i10++;
        }
        this.f16430d = iHashCode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        int i10 = bVar.f16428b;
        if (this.f16430d != bVar.f16430d) {
            return false;
        }
        int i11 = this.f16429c;
        int i12 = this.f16428b;
        int i13 = (i11 - i12) + 1;
        if (i13 != (bVar.f16429c - i10) + 1) {
            return false;
        }
        for (int i14 = 0; i14 < i13; i14++) {
            if (!this.f16427a[i12 + i14].equals(bVar.f16427a[i10 + i14])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return this.f16430d;
    }
}
