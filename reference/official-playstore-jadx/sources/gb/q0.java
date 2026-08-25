package gb;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public final class q0 extends Reader {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final xb.l f15805i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Charset f15806l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15807m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public InputStreamReader f15808n;

    public q0(xb.l lVar, Charset charset) {
        this.f15805i = lVar;
        this.f15806l = charset;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        x6.t0 t0Var;
        this.f15807m = true;
        InputStreamReader inputStreamReader = this.f15808n;
        if (inputStreamReader != null) {
            inputStreamReader.close();
            t0Var = x6.t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var == null) {
            this.f15805i.close();
        }
    }

    @Override // java.io.Reader
    public final int read(char[] cArr, int i10, int i11) throws IOException {
        if (this.f15807m) {
            throw new IOException("Stream closed");
        }
        InputStreamReader inputStreamReader = this.f15808n;
        if (inputStreamReader == null) {
            xb.l lVar = this.f15805i;
            inputStreamReader = new InputStreamReader(lVar.inputStream(), ib.c.s(lVar, this.f15806l));
            this.f15808n = inputStreamReader;
        }
        return inputStreamReader.read(cArr, i10, i11);
    }
}
