package xb;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public final class h0 implements k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m0 f22750i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j f22751l = new j();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22752m;

    public h0(m0 m0Var) {
        this.f22750i = m0Var;
    }

    @Override // xb.k
    public final k H(String str) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        this.f22751l.S0(str);
        i();
        return this;
    }

    @Override // xb.k
    public final k U(m mVar) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        this.f22751l.J0(mVar);
        i();
        return this;
    }

    @Override // xb.k
    public final k Y(long j10) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        this.f22751l.N0(j10);
        i();
        return this;
    }

    @Override // xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        m0 m0Var = this.f22750i;
        if (this.f22752m) {
            return;
        }
        try {
            j jVar = this.f22751l;
            long j10 = jVar.f22759l;
            if (j10 > 0) {
                m0Var.write(jVar, j10);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            m0Var.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.f22752m = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // xb.k, xb.m0, java.io.Flushable
    public final void flush() {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        j jVar = this.f22751l;
        long j10 = jVar.f22759l;
        m0 m0Var = this.f22750i;
        if (j10 > 0) {
            m0Var.write(jVar, j10);
        }
        m0Var.flush();
    }

    @Override // xb.k
    public final j h() {
        return this.f22751l;
    }

    public final k i() {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        j jVar = this.f22751l;
        long j10 = jVar.j();
        if (j10 > 0) {
            this.f22750i.write(jVar, j10);
        }
        return this;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f22752m;
    }

    @Override // xb.k
    public final k p0(int i10, byte[] bArr) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        this.f22751l.write(bArr, 0, i10);
        i();
        return this;
    }

    @Override // xb.k
    public final k q() {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        j jVar = this.f22751l;
        long j10 = jVar.f22759l;
        if (j10 > 0) {
            this.f22750i.write(jVar, j10);
        }
        return this;
    }

    @Override // xb.m0
    public final q0 timeout() {
        return this.f22750i.timeout();
    }

    public final String toString() {
        return "buffer(" + this.f22750i + ')';
    }

    @Override // xb.k
    public final k v0(long j10) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        this.f22751l.O0(j10);
        i();
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.f22751l.write(byteBuffer);
        i();
        return iWrite;
    }

    @Override // xb.k
    public final k writeByte(int i10) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        this.f22751l.M0(i10);
        i();
        return this;
    }

    @Override // xb.k
    public final k writeInt(int i10) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        this.f22751l.P0(i10);
        i();
        return this;
    }

    @Override // xb.k
    public final k writeShort(int i10) {
        if (this.f22752m) {
            throw new IllegalStateException("closed");
        }
        this.f22751l.Q0(i10);
        i();
        return this;
    }

    @Override // xb.k
    public final long z0(o0 o0Var) {
        long j10 = 0;
        while (true) {
            long j11 = o0Var.read(this.f22751l, 8192L);
            if (j11 == -1) {
                return j10;
            }
            j10 += j11;
            i();
        }
    }

    @Override // xb.m0
    public final void write(j jVar, long j10) {
        if (!this.f22752m) {
            this.f22751l.write(jVar, j10);
            i();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // xb.k
    public final k write(byte[] bArr) {
        if (!this.f22752m) {
            this.f22751l.write(bArr, 0, bArr.length);
            i();
            return this;
        }
        throw new IllegalStateException("closed");
    }
}
