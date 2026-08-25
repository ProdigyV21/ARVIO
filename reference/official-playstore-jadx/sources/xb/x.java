package xb;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes5.dex */
public final class x implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte f22800i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i0 f22801l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Inflater f22802m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final y f22803n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final CRC32 f22804o;

    public x(o0 o0Var) {
        i0 i0Var = new i0(o0Var);
        this.f22801l = i0Var;
        Inflater inflater = new Inflater(true);
        this.f22802m = inflater;
        this.f22803n = new y(i0Var, inflater);
        this.f22804o = new CRC32();
    }

    public static void i(int i10, int i11, String str) throws IOException {
        if (i11 != i10) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i11), Integer.valueOf(i10)}, 3)));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f22803n.close();
    }

    public final void j(j jVar, long j10, long j11) {
        j0 j0Var = jVar.f22758i;
        while (true) {
            int i10 = j0Var.f22762c;
            int i11 = j0Var.f22761b;
            if (j10 < i10 - i11) {
                break;
            }
            j10 -= (long) (i10 - i11);
            j0Var = j0Var.f22765f;
        }
        while (j11 > 0) {
            int i12 = (int) (((long) j0Var.f22761b) + j10);
            int iMin = (int) Math.min(j0Var.f22762c - i12, j11);
            this.f22804o.update(j0Var.f22760a, i12, iMin);
            j11 -= (long) iMin;
            j0Var = j0Var.f22765f;
            j10 = 0;
        }
    }

    @Override // xb.o0
    public final long read(j jVar, long j10) throws IOException {
        x xVar = this;
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
        }
        if (j10 == 0) {
            return 0L;
        }
        byte b10 = xVar.f22800i;
        CRC32 crc32 = xVar.f22804o;
        i0 i0Var = xVar.f22801l;
        if (b10 == 0) {
            i0Var.X(10L);
            j jVar2 = i0Var.f22756l;
            byte bX = jVar2.x(3L);
            boolean z = ((bX >> 1) & 1) == 1;
            if (z) {
                xVar.j(jVar2, 0L, 10L);
            }
            i(8075, i0Var.readShort(), "ID1ID2");
            i0Var.skip(8L);
            if (((bX >> 2) & 1) == 1) {
                i0Var.X(2L);
                if (z) {
                    j(jVar2, 0L, 2L);
                }
                long jB0 = jVar2.b0() & 65535;
                i0Var.X(jB0);
                if (z) {
                    j(jVar2, 0L, jB0);
                }
                i0Var.skip(jB0);
            }
            if (((bX >> 3) & 1) == 1) {
                long jA = i0Var.A((byte) 0, 0L, Long.MAX_VALUE);
                if (jA == -1) {
                    throw new EOFException();
                }
                if (z) {
                    j(jVar2, 0L, jA + 1);
                }
                i0Var.skip(jA + 1);
            }
            if (((bX >> 4) & 1) == 1) {
                long jA2 = i0Var.A((byte) 0, 0L, Long.MAX_VALUE);
                if (jA2 == -1) {
                    throw new EOFException();
                }
                if (z) {
                    xVar = this;
                    xVar.j(jVar2, 0L, jA2 + 1);
                } else {
                    xVar = this;
                }
                i0Var.skip(jA2 + 1);
            } else {
                xVar = this;
            }
            if (z) {
                i(i0Var.l(), (short) crc32.getValue(), "FHCRC");
                crc32.reset();
            }
            xVar.f22800i = (byte) 1;
        }
        if (xVar.f22800i == 1) {
            long j11 = jVar.f22759l;
            long j12 = xVar.f22803n.read(jVar, j10);
            if (j12 != -1) {
                xVar.j(jVar, j11, j12);
                return j12;
            }
            xVar.f22800i = (byte) 2;
        }
        if (xVar.f22800i == 2) {
            i(i0Var.j(), (int) crc32.getValue(), "CRC");
            i(i0Var.j(), (int) xVar.f22802m.getBytesWritten(), "ISIZE");
            xVar.f22800i = (byte) 3;
            if (!i0Var.i()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // xb.o0
    public final q0 timeout() {
        return this.f22801l.f22755i.timeout();
    }
}
