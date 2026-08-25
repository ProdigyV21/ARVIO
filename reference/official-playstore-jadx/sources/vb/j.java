package vb;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import xb.l;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l f22306i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i f22307l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f22308m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f22309n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f22310o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22311p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f22312q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f22313r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f22314s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f22315t;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public a f22318w;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final xb.j f22316u = new xb.j();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final xb.j f22317v = new xb.j();
    public final byte[] x = null;

    public j(l lVar, g gVar, boolean z, boolean z5) {
        this.f22306i = lVar;
        this.f22307l = gVar;
        this.f22308m = z;
        this.f22309n = z5;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        a aVar = this.f22318w;
        if (aVar != null) {
            aVar.close();
        }
    }

    public final void i() throws ProtocolException, EOFException {
        short s10;
        String strT0;
        long j10 = this.f22312q;
        xb.j jVar = this.f22316u;
        if (j10 > 0) {
            this.f22306i.S(jVar, j10);
        }
        int i10 = this.f22311p;
        i iVar = this.f22307l;
        switch (i10) {
            case 8:
                long j11 = jVar.f22759l;
                if (j11 == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (j11 != 0) {
                    s10 = jVar.readShort();
                    strT0 = jVar.t0();
                    String strI = (s10 < 1000 || s10 >= 5000) ? a0.c.i(s10, "Code must be in range [1000,5000): ") : ((1004 > s10 || s10 >= 1007) && (1015 > s10 || s10 >= 3000)) ? null : androidx.compose.foundation.c.o(s10, "Code ", " is reserved and may not be used.");
                    if (strI != null) {
                        throw new ProtocolException(strI);
                    }
                } else {
                    s10 = 1005;
                    strT0 = "";
                }
                iVar.g(s10, strT0);
                this.f22310o = true;
                return;
            case 9:
                iVar.b(jVar.c0(jVar.f22759l));
                return;
            case 10:
                jVar.c0(jVar.f22759l);
                iVar.f();
                return;
            default:
                int i11 = this.f22311p;
                byte[] bArr = ib.c.f16268a;
                throw new ProtocolException("Unknown control opcode: ".concat(Integer.toHexString(i11)));
        }
    }

    public final void j() throws IOException {
        boolean z;
        if (this.f22310o) {
            throw new IOException("closed");
        }
        l lVar = this.f22306i;
        long jH = lVar.timeout().h();
        lVar.timeout().b();
        try {
            byte b10 = lVar.readByte();
            byte[] bArr = ib.c.f16268a;
            lVar.timeout().g(jH, TimeUnit.NANOSECONDS);
            int i10 = b10 & 15;
            this.f22311p = i10;
            boolean z5 = (b10 & 128) != 0;
            this.f22313r = z5;
            boolean z10 = (b10 & 8) != 0;
            this.f22314s = z10;
            if (z10 && !z5) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z11 = (b10 & 64) != 0;
            if (i10 == 1 || i10 == 2) {
                if (!z11) {
                    z = false;
                } else {
                    if (!this.f22308m) {
                        throw new ProtocolException("Unexpected rsv1 flag");
                    }
                    z = true;
                }
                this.f22315t = z;
            } else if (z11) {
                throw new ProtocolException("Unexpected rsv1 flag");
            }
            if ((b10 & 32) != 0) {
                throw new ProtocolException("Unexpected rsv2 flag");
            }
            if ((b10 & 16) != 0) {
                throw new ProtocolException("Unexpected rsv3 flag");
            }
            byte b11 = lVar.readByte();
            boolean z12 = (b11 & 128) != 0;
            if (z12) {
                throw new ProtocolException("Server-sent frames must not be masked.");
            }
            long j10 = b11 & 127;
            this.f22312q = j10;
            if (j10 == 126) {
                this.f22312q = lVar.readShort() & 65535;
            } else if (j10 == 127) {
                long j11 = lVar.readLong();
                this.f22312q = j11;
                if (j11 < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.f22312q) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            if (this.f22314s && this.f22312q > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (z12) {
                lVar.readFully(this.x);
            }
        } catch (Throwable th) {
            lVar.timeout().g(jH, TimeUnit.NANOSECONDS);
            throw th;
        }
    }
}
