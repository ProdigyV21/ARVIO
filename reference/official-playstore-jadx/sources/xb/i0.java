package xb;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o0 f22755i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j f22756l = new j();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22757m;

    public i0(o0 o0Var) {
        this.f22755i = o0Var;
    }

    @Override // xb.l
    public final long A(byte b10, long j10, long j11) {
        if (this.f22757m) {
            throw new IllegalStateException("closed");
        }
        if (0 > j10 || j10 > j11) {
            StringBuilder sbR = androidx.compose.material3.d.r(j10, "fromIndex=", " toIndex=");
            sbR.append(j11);
            throw new IllegalArgumentException(sbR.toString().toString());
        }
        long jMax = j10;
        while (jMax < j11) {
            j jVar = this.f22756l;
            byte b11 = b10;
            long j12 = j11;
            long jA = jVar.A(b11, jMax, j12);
            if (jA != -1) {
                return jA;
            }
            long j13 = jVar.f22759l;
            if (j13 >= j12 || this.f22755i.read(jVar, 8192L) == -1) {
                break;
            }
            jMax = Math.max(jMax, j13);
            b10 = b11;
            j11 = j12;
        }
        return -1L;
    }

    @Override // xb.l
    public final String B(long j10) {
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "limit < 0: ").toString());
        }
        long j11 = j10 == Long.MAX_VALUE ? Long.MAX_VALUE : j10 + 1;
        long jA = A((byte) 10, 0L, j11);
        j jVar = this.f22756l;
        if (jA != -1) {
            return yb.a.a(jVar, jA);
        }
        if (j11 < Long.MAX_VALUE && request(j11) && jVar.x(j11 - 1) == 13 && request(j11 + 1) && jVar.x(j11) == 10) {
            return yb.a.a(jVar, j11);
        }
        j jVar2 = new j();
        jVar.l(jVar2, 0L, Math.min(32, jVar.f22759l));
        throw new EOFException("\\n not found: limit=" + Math.min(jVar.f22759l, j10) + " content=" + jVar2.c0(jVar2.f22759l).i() + (char) 8230);
    }

    @Override // xb.l
    public final long D(k kVar) {
        j jVar;
        long j10 = 0;
        while (true) {
            o0 o0Var = this.f22755i;
            jVar = this.f22756l;
            if (o0Var.read(jVar, 8192L) == -1) {
                break;
            }
            long j11 = jVar.j();
            if (j11 > 0) {
                j10 += j11;
                kVar.write(jVar, j11);
            }
        }
        long j12 = jVar.f22759l;
        if (j12 <= 0) {
            return j10;
        }
        long j13 = j10 + j12;
        kVar.write(jVar, j12);
        return j13;
    }

    @Override // xb.l
    public final long L0() {
        j jVar;
        byte bX;
        X(1L);
        int i10 = 0;
        while (true) {
            int i11 = i10 + 1;
            boolean zRequest = request(i11);
            jVar = this.f22756l;
            if (!zRequest) {
                break;
            }
            bX = jVar.x(i10);
            if ((bX < 48 || bX > 57) && ((bX < 97 || bX > 102) && (bX < 65 || bX > 70))) {
                break;
            }
            i10 = i11;
        }
        if (i10 == 0) {
            com.google.common.util.concurrent.r0.c(16);
            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x".concat(Integer.toString(bX, 16)));
        }
        return jVar.L0();
    }

    @Override // xb.l
    public final String Q() {
        return B(Long.MAX_VALUE);
    }

    @Override // xb.l
    public final void S(j jVar, long j10) throws EOFException {
        j jVar2 = this.f22756l;
        try {
            X(j10);
            jVar2.S(jVar, j10);
        } catch (EOFException e5) {
            jVar.z0(jVar2);
            throw e5;
        }
    }

    @Override // xb.l
    public final void X(long j10) {
        if (!request(j10)) {
            throw new EOFException();
        }
    }

    @Override // xb.l
    public final m c0(long j10) {
        X(j10);
        return this.f22756l.c0(j10);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() throws IOException {
        if (this.f22757m) {
            return;
        }
        this.f22757m = true;
        this.f22755i.close();
        this.f22756l.i();
    }

    @Override // xb.l
    public final int d0(e0 e0Var) {
        if (this.f22757m) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            j jVar = this.f22756l;
            int iB = yb.a.b(jVar, e0Var, true);
            if (iB != -2) {
                if (iB != -1) {
                    jVar.skip(e0Var.f22731i[iB].f());
                    return iB;
                }
            } else if (this.f22755i.read(jVar, 8192L) == -1) {
                break;
            }
        }
        return -1;
    }

    @Override // xb.l
    public final j h() {
        return this.f22756l;
    }

    public final boolean i() {
        if (this.f22757m) {
            throw new IllegalStateException("closed");
        }
        j jVar = this.f22756l;
        return jVar.o() && this.f22755i.read(jVar, 8192L) == -1;
    }

    @Override // xb.l
    public final byte[] i0() {
        o0 o0Var = this.f22755i;
        j jVar = this.f22756l;
        jVar.z0(o0Var);
        return jVar.T(jVar.f22759l);
    }

    @Override // xb.l
    public final InputStream inputStream() {
        return new h(this, 1);
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f22757m;
    }

    public final int j() {
        X(4L);
        int i10 = this.f22756l.readInt();
        return ((i10 & 255) << 24) | (((-16777216) & i10) >>> 24) | ((16711680 & i10) >>> 8) | ((65280 & i10) << 8);
    }

    public final long k() throws EOFException {
        X(8L);
        long j10 = this.f22756l.readLong();
        return ((j10 & 255) << 56) | (((-72057594037927936L) & j10) >>> 56) | ((71776119061217280L & j10) >>> 40) | ((280375465082880L & j10) >>> 24) | ((1095216660480L & j10) >>> 8) | ((4278190080L & j10) << 8) | ((16711680 & j10) << 24) | ((65280 & j10) << 40);
    }

    public final short l() {
        X(2L);
        return this.f22756l.b0();
    }

    public final String o(long j10) {
        X(j10);
        j jVar = this.f22756l;
        jVar.getClass();
        return jVar.q0(j10, kotlin.text.a.f19924a);
    }

    @Override // xb.l
    public final i0 peek() {
        return new i0(new g0(this));
    }

    @Override // xb.l
    public final String r0(Charset charset) {
        o0 o0Var = this.f22755i;
        j jVar = this.f22756l;
        jVar.z0(o0Var);
        return jVar.q0(jVar.f22759l, charset);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        j jVar = this.f22756l;
        if (jVar.f22759l == 0 && this.f22755i.read(jVar, 8192L) == -1) {
            return -1;
        }
        return jVar.read(byteBuffer);
    }

    @Override // xb.l
    public final byte readByte() {
        X(1L);
        return this.f22756l.readByte();
    }

    @Override // xb.l
    public final void readFully(byte[] bArr) throws EOFException {
        j jVar = this.f22756l;
        try {
            X(bArr.length);
            jVar.readFully(bArr);
        } catch (EOFException e5) {
            int i10 = 0;
            while (true) {
                long j10 = jVar.f22759l;
                if (j10 <= 0) {
                    throw e5;
                }
                int i11 = jVar.read(bArr, i10, (int) j10);
                if (i11 == -1) {
                    throw new AssertionError();
                }
                i10 += i11;
            }
        }
    }

    @Override // xb.l
    public final int readInt() {
        X(4L);
        return this.f22756l.readInt();
    }

    @Override // xb.l
    public final long readLong() {
        X(8L);
        return this.f22756l.readLong();
    }

    @Override // xb.l
    public final short readShort() {
        X(2L);
        return this.f22756l.readShort();
    }

    @Override // xb.l
    public final boolean request(long j10) {
        j jVar;
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
        }
        if (this.f22757m) {
            throw new IllegalStateException("closed");
        }
        do {
            jVar = this.f22756l;
            if (jVar.f22759l >= j10) {
                return true;
            }
        } while (this.f22755i.read(jVar, 8192L) != -1);
        return false;
    }

    @Override // xb.l
    public final boolean s0(long j10, m mVar) {
        int iF = mVar.f();
        if (this.f22757m) {
            throw new IllegalStateException("closed");
        }
        if (j10 >= 0 && iF >= 0 && mVar.f() >= iF) {
            for (int i10 = 0; i10 < iF; i10++) {
                long j11 = ((long) i10) + j10;
                if (request(1 + j11) && this.f22756l.x(j11) == mVar.m(i10)) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // xb.l
    public final void skip(long j10) {
        if (this.f22757m) {
            throw new IllegalStateException("closed");
        }
        while (j10 > 0) {
            j jVar = this.f22756l;
            if (jVar.f22759l == 0 && this.f22755i.read(jVar, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j10, jVar.f22759l);
            jVar.skip(jMin);
            j10 -= jMin;
        }
    }

    @Override // xb.o0
    public final q0 timeout() {
        return this.f22755i.timeout();
    }

    public final String toString() {
        return "buffer(" + this.f22755i + ')';
    }

    @Override // xb.l
    public final m x0() {
        o0 o0Var = this.f22755i;
        j jVar = this.f22756l;
        jVar.z0(o0Var);
        return jVar.c0(jVar.f22759l);
    }

    @Override // xb.o0
    public final long read(j jVar, long j10) {
        if (j10 >= 0) {
            if (!this.f22757m) {
                j jVar2 = this.f22756l;
                if (jVar2.f22759l == 0 && this.f22755i.read(jVar2, 8192L) == -1) {
                    return -1L;
                }
                return jVar2.read(jVar, Math.min(j10, jVar2.f22759l));
            }
            throw new IllegalStateException("closed");
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
    }
}
