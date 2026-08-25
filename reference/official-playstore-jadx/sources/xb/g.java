package xb;

import java.io.Closeable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public j f22735i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f22736l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public j0 f22737m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public byte[] f22739o;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f22738n = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22740p = -1;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f22741q = -1;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f22735i == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        this.f22735i = null;
        this.f22737m = null;
        this.f22738n = -1L;
        this.f22739o = null;
        this.f22740p = -1;
        this.f22741q = -1;
    }

    public final void i(long j10) {
        j jVar = this.f22735i;
        if (jVar == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        if (!this.f22736l) {
            throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
        }
        long j11 = jVar.f22759l;
        if (j10 <= j11) {
            if (j10 < 0) {
                throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "newSize < 0: ").toString());
            }
            long j12 = j11 - j10;
            while (true) {
                if (j12 <= 0) {
                    break;
                }
                j0 j0Var = jVar.f22758i.f22766g;
                int i10 = j0Var.f22762c;
                long j13 = i10 - j0Var.f22761b;
                if (j13 > j12) {
                    j0Var.f22762c = i10 - ((int) j12);
                    break;
                } else {
                    jVar.f22758i = j0Var.a();
                    k0.a(j0Var);
                    j12 -= j13;
                }
            }
            this.f22737m = null;
            this.f22738n = j10;
            this.f22739o = null;
            this.f22740p = -1;
            this.f22741q = -1;
        } else if (j10 > j11) {
            long j14 = j10 - j11;
            int i11 = 1;
            boolean z = true;
            for (long j15 = 0; j14 > j15; j15 = 0) {
                j0 j0VarD0 = jVar.D0(i11);
                int iMin = (int) Math.min(j14, 8192 - j0VarD0.f22762c);
                int i12 = j0VarD0.f22762c + iMin;
                j0VarD0.f22762c = i12;
                j14 -= (long) iMin;
                if (z) {
                    this.f22737m = j0VarD0;
                    this.f22738n = j11;
                    this.f22739o = j0VarD0.f22760a;
                    this.f22740p = i12 - iMin;
                    this.f22741q = i12;
                    z = false;
                }
                i11 = 1;
            }
        }
        jVar.f22759l = j10;
    }

    public final int j(long j10) {
        j jVar = this.f22735i;
        if (jVar == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        if (j10 >= -1) {
            long j11 = jVar.f22759l;
            if (j10 <= j11) {
                if (j10 == -1 || j10 == j11) {
                    this.f22737m = null;
                    this.f22738n = j10;
                    this.f22739o = null;
                    this.f22740p = -1;
                    this.f22741q = -1;
                    return -1;
                }
                j0 j0Var = jVar.f22758i;
                j0 j0Var2 = this.f22737m;
                long j12 = 0;
                if (j0Var2 != null) {
                    long j13 = this.f22738n - ((long) (this.f22740p - j0Var2.f22761b));
                    if (j13 > j10) {
                        j0Var2 = j0Var;
                        j0Var = j0Var2;
                        j11 = j13;
                    } else {
                        j12 = j13;
                    }
                } else {
                    j0Var2 = j0Var;
                }
                if (j11 - j10 > j10 - j12) {
                    while (true) {
                        long j14 = ((long) (j0Var2.f22762c - j0Var2.f22761b)) + j12;
                        if (j10 < j14) {
                            break;
                        }
                        j0Var2 = j0Var2.f22765f;
                        j12 = j14;
                    }
                } else {
                    while (j11 > j10) {
                        j0Var = j0Var.f22766g;
                        j11 -= (long) (j0Var.f22762c - j0Var.f22761b);
                    }
                    j0Var2 = j0Var;
                    j12 = j11;
                }
                if (this.f22736l && j0Var2.f22763d) {
                    byte[] bArr = j0Var2.f22760a;
                    j0 j0Var3 = new j0(Arrays.copyOf(bArr, bArr.length), j0Var2.f22761b, j0Var2.f22762c, false, true);
                    if (jVar.f22758i == j0Var2) {
                        jVar.f22758i = j0Var3;
                    }
                    j0Var2.b(j0Var3);
                    j0Var3.f22766g.a();
                    j0Var2 = j0Var3;
                }
                this.f22737m = j0Var2;
                this.f22738n = j10;
                this.f22739o = j0Var2.f22760a;
                int i10 = j0Var2.f22761b + ((int) (j10 - j12));
                this.f22740p = i10;
                int i11 = j0Var2.f22762c;
                this.f22741q = i11;
                return i11 - i10;
            }
        }
        StringBuilder sbR = androidx.compose.material3.d.r(j10, "offset=", " > size=");
        sbR.append(jVar.f22759l);
        throw new ArrayIndexOutOfBoundsException(sbR.toString());
    }
}
