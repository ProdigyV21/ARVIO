package nb;

import androidx.appcompat.widget.u;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import lb.k;
import xb.j;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f20708n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ u f20709o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(u uVar, long j10) {
        super(uVar);
        this.f20709o = uVar;
        this.f20708n = j10;
        if (j10 == 0) {
            i();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        boolean zU;
        if (this.f20699l) {
            return;
        }
        if (this.f20708n != 0) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            try {
                zU = ib.c.u(this, 100);
            } catch (IOException unused) {
                zU = false;
            }
            if (!zU) {
                ((k) this.f20709o.f1764c).l();
                i();
            }
        }
        this.f20699l = true;
    }

    @Override // nb.a, xb.o0
    public final long read(j jVar, long j10) throws IOException {
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
        }
        if (this.f20699l) {
            throw new IllegalStateException("closed");
        }
        long j11 = this.f20708n;
        if (j11 == 0) {
            return -1L;
        }
        long j12 = super.read(jVar, Math.min(j11, j10));
        if (j12 == -1) {
            ((k) this.f20709o.f1764c).l();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            i();
            throw protocolException;
        }
        long j13 = this.f20708n - j12;
        this.f20708n = j13;
        if (j13 == 0) {
            i();
        }
        return j12;
    }
}
