package lb;

import com.google.android.gms.common.api.internal.g0;
import java.io.IOException;
import java.net.ProtocolException;
import xb.m0;
import xb.u;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f20084i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f20085l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f20086m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f20087n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ g0 f20088o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(g0 g0Var, m0 m0Var, long j10) {
        super(m0Var);
        this.f20088o = g0Var;
        this.f20084i = j10;
    }

    @Override // xb.u, xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f20087n) {
            return;
        }
        this.f20087n = true;
        long j10 = this.f20084i;
        if (j10 != -1 && this.f20086m != j10) {
            throw new ProtocolException("unexpected end of stream");
        }
        try {
            super.close();
            i(null);
        } catch (IOException e5) {
            throw i(e5);
        }
    }

    @Override // xb.u, xb.m0, java.io.Flushable
    public final void flush() throws IOException {
        try {
            super.flush();
        } catch (IOException e5) {
            throw i(e5);
        }
    }

    public final IOException i(IOException iOException) {
        if (this.f20085l) {
            return iOException;
        }
        this.f20085l = true;
        return this.f20088o.n(false, true, iOException);
    }

    @Override // xb.u, xb.m0
    public final void write(xb.j jVar, long j10) throws IOException {
        if (this.f20087n) {
            throw new IllegalStateException("closed");
        }
        long j11 = this.f20084i;
        if (j11 != -1 && this.f20086m + j10 > j11) {
            StringBuilder sbR = androidx.compose.material3.d.r(j11, "expected ", " bytes but received ");
            sbR.append(this.f20086m + j10);
            throw new ProtocolException(sbR.toString());
        }
        try {
            super.write(jVar, j10);
            this.f20086m += j10;
        } catch (IOException e5) {
            throw i(e5);
        }
    }
}
