package ob;

import h.f0;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import xb.h0;

/* JADX INFO: loaded from: classes5.dex */
public final class y implements Closeable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Logger f20978p = Logger.getLogger(e.class.getName());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final xb.k f20979i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final xb.j f20980l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20981m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f20982n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final c f20983o;

    public y(h0 h0Var) {
        this.f20979i = h0Var;
        xb.j jVar = new xb.j();
        this.f20980l = jVar;
        this.f20981m = 16384;
        this.f20983o = new c(jVar);
    }

    public final synchronized void G(b0 b0Var) {
        try {
            if (this.f20982n) {
                throw new IOException("closed");
            }
            k(0, Integer.bitCount(b0Var.f20882a) * 6, 4, 0);
            int i10 = 0;
            while (i10 < 10) {
                boolean z = true;
                if (((1 << i10) & b0Var.f20882a) == 0) {
                    z = false;
                }
                if (z) {
                    this.f20979i.writeShort(i10 != 4 ? i10 != 7 ? i10 : 4 : 3);
                    this.f20979i.writeInt(b0Var.f20883b[i10]);
                }
                i10++;
            }
            this.f20979i.flush();
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void O(int i10, long j10) {
        if (this.f20982n) {
            throw new IOException("closed");
        }
        if (j10 == 0 || j10 > 2147483647L) {
            throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j10).toString());
        }
        k(i10, 4, 8, 0);
        this.f20979i.writeInt((int) j10);
        this.f20979i.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.f20982n = true;
        this.f20979i.close();
    }

    public final synchronized void flush() {
        if (this.f20982n) {
            throw new IOException("closed");
        }
        this.f20979i.flush();
    }

    public final synchronized void i(b0 b0Var) {
        try {
            if (this.f20982n) {
                throw new IOException("closed");
            }
            int i10 = this.f20981m;
            int i11 = b0Var.f20882a;
            if ((i11 & 32) != 0) {
                i10 = b0Var.f20883b[5];
            }
            this.f20981m = i10;
            if (((i11 & 2) != 0 ? b0Var.f20883b[1] : -1) != -1) {
                c cVar = this.f20983o;
                int iMin = Math.min((i11 & 2) != 0 ? b0Var.f20883b[1] : -1, 16384);
                int i12 = cVar.f20887d;
                if (i12 != iMin) {
                    if (iMin < i12) {
                        cVar.f20885b = Math.min(cVar.f20885b, iMin);
                    }
                    cVar.f20886c = true;
                    cVar.f20887d = iMin;
                    int i13 = cVar.f20891h;
                    if (iMin < i13) {
                        if (iMin == 0) {
                            a[] aVarArr = cVar.f20888e;
                            Arrays.fill(aVarArr, 0, aVarArr.length, (Object) null);
                            cVar.f20889f = cVar.f20888e.length - 1;
                            cVar.f20890g = 0;
                            cVar.f20891h = 0;
                        } else {
                            cVar.a(i13 - iMin);
                        }
                    }
                }
            }
            k(0, 0, 4, 1);
            this.f20979i.flush();
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void j(boolean z, int i10, xb.j jVar, int i11) {
        if (this.f20982n) {
            throw new IOException("closed");
        }
        k(i10, i11, 0, z ? 1 : 0);
        if (i11 > 0) {
            this.f20979i.write(jVar, i11);
        }
    }

    public final void k(int i10, int i11, int i12, int i13) {
        Level level = Level.FINE;
        Logger logger = f20978p;
        if (logger.isLoggable(level)) {
            logger.fine(e.a(i10, i11, i12, i13, false));
        }
        if (i11 > this.f20981m) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.f20981m + ": " + i11).toString());
        }
        if ((Integer.MIN_VALUE & i10) != 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "reserved bit set: ").toString());
        }
        byte[] bArr = ib.c.f16268a;
        xb.k kVar = this.f20979i;
        kVar.writeByte((i11 >>> 16) & 255);
        kVar.writeByte((i11 >>> 8) & 255);
        kVar.writeByte(i11 & 255);
        kVar.writeByte(i12 & 255);
        kVar.writeByte(i13 & 255);
        kVar.writeInt(i10 & Integer.MAX_VALUE);
    }

    public final synchronized void l(int i10, int i11, byte[] bArr) {
        if (this.f20982n) {
            throw new IOException("closed");
        }
        if (f0.c(i11) == -1) {
            throw new IllegalArgumentException("errorCode.httpCode == -1");
        }
        k(0, bArr.length + 8, 7, 0);
        this.f20979i.writeInt(i10);
        this.f20979i.writeInt(f0.c(i11));
        if (bArr.length != 0) {
            this.f20979i.write(bArr);
        }
        this.f20979i.flush();
    }

    public final synchronized void o(int i10, ArrayList arrayList, boolean z) {
        if (this.f20982n) {
            throw new IOException("closed");
        }
        this.f20983o.d(arrayList);
        long j10 = this.f20980l.f22759l;
        long jMin = Math.min(this.f20981m, j10);
        int i11 = j10 == jMin ? 4 : 0;
        if (z) {
            i11 |= 1;
        }
        k(i10, (int) jMin, 1, i11);
        this.f20979i.write(this.f20980l, jMin);
        if (j10 > jMin) {
            long j11 = j10 - jMin;
            while (j11 > 0) {
                long jMin2 = Math.min(this.f20981m, j11);
                j11 -= jMin2;
                k(i10, (int) jMin2, 9, j11 == 0 ? 4 : 0);
                this.f20979i.write(this.f20980l, jMin2);
            }
        }
    }

    public final synchronized void x(int i10, int i11, boolean z) {
        if (this.f20982n) {
            throw new IOException("closed");
        }
        k(0, 8, 6, z ? 1 : 0);
        this.f20979i.writeInt(i10);
        this.f20979i.writeInt(i11);
        this.f20979i.flush();
    }

    public final synchronized void y(int i10, int i11) {
        if (this.f20982n) {
            throw new IOException("closed");
        }
        if (f0.c(i11) == -1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        k(i10, 4, 3, 0);
        this.f20979i.writeInt(f0.c(i11));
        this.f20979i.flush();
    }
}
