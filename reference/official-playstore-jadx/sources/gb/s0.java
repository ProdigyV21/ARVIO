package gb;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public abstract class s0 implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public q0 f15814i;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ib.c.d(o());
    }

    public final InputStream i() {
        return o().inputStream();
    }

    public final byte[] j() throws IOException {
        long jK = k();
        if (jK > 2147483647L) {
            throw new IOException(androidx.compose.foundation.c.q(jK, "Cannot buffer entire body for content length: "));
        }
        xb.l lVarO = o();
        try {
            byte[] bArrI0 = lVarO.i0();
            lVarO.close();
            int length = bArrI0.length;
            if (jK == -1 || jK == length) {
                return bArrI0;
            }
            throw new IOException("Content-Length (" + jK + ") and stream length (" + length + ") disagree");
        } finally {
        }
    }

    public abstract long k();

    public abstract d0 l();

    public abstract xb.l o();

    public final String x() {
        Charset charsetA;
        xb.l lVarO = o();
        try {
            d0 d0VarL = l();
            if (d0VarL == null || (charsetA = d0VarL.a(kotlin.text.a.f19924a)) == null) {
                charsetA = kotlin.text.a.f19924a;
            }
            String strR0 = lVarO.r0(ib.c.s(lVarO, charsetA));
            lVarO.close();
            return strR0;
        } finally {
        }
    }
}
