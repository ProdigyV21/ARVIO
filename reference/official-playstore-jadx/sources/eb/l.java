package eb;

import com.google.android.gms.internal.auth.d1;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends d1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f15160d;

    public l(androidx.core.provider.j jVar, boolean z) {
        super(jVar);
        this.f15160d = z;
    }

    @Override // com.google.android.gms.internal.auth.d1
    public final void d(byte b10) {
        if (this.f15160d) {
            j(String.valueOf(b10 & 255));
        } else {
            h(String.valueOf(b10 & 255));
        }
    }

    @Override // com.google.android.gms.internal.auth.d1
    public final void f(int i10) {
        if (this.f15160d) {
            j(Long.toString(4294967295L & ((long) i10), 10));
        } else {
            h(Long.toString(4294967295L & ((long) i10), 10));
        }
    }

    @Override // com.google.android.gms.internal.auth.d1
    public final void g(long j10) {
        int i10 = 63;
        String str = "0";
        if (this.f15160d) {
            if (j10 != 0) {
                if (j10 > 0) {
                    str = Long.toString(j10, 10);
                } else {
                    char[] cArr = new char[64];
                    long j11 = (j10 >>> 1) / ((long) 5);
                    long j12 = 10;
                    cArr[63] = Character.forDigit((int) (j10 - (j11 * j12)), 10);
                    while (j11 > 0) {
                        i10--;
                        cArr[i10] = Character.forDigit((int) (j11 % j12), 10);
                        j11 /= j12;
                    }
                    str = new String(cArr, i10, 64 - i10);
                }
            }
            j(str);
            return;
        }
        if (j10 != 0) {
            if (j10 > 0) {
                str = Long.toString(j10, 10);
            } else {
                char[] cArr2 = new char[64];
                long j13 = (j10 >>> 1) / ((long) 5);
                long j14 = 10;
                cArr2[63] = Character.forDigit((int) (j10 - (j13 * j14)), 10);
                while (j13 > 0) {
                    i10--;
                    cArr2[i10] = Character.forDigit((int) (j13 % j14), 10);
                    j13 /= j14;
                }
                str = new String(cArr2, i10, 64 - i10);
            }
        }
        h(str);
    }

    @Override // com.google.android.gms.internal.auth.d1
    public final void i(short s10) {
        if (this.f15160d) {
            j(String.valueOf(s10 & 65535));
        } else {
            h(String.valueOf(s10 & 65535));
        }
    }
}
