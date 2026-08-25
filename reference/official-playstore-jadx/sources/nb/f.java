package nb;

import java.io.IOException;
import xb.j;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f20714n;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f20699l) {
            return;
        }
        if (!this.f20714n) {
            i();
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
        if (this.f20714n) {
            return -1L;
        }
        long j11 = super.read(jVar, j10);
        if (j11 != -1) {
            return j11;
        }
        this.f20714n = true;
        i();
        return -1L;
    }
}
