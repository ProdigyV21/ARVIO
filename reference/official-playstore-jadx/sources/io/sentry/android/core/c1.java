package io.sentry.android.core;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class c1 extends InputStream {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final BufferedInputStream f16455i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f16456l;

    public c1(BufferedInputStream bufferedInputStream, int i10) {
        this.f16455i = bufferedInputStream;
        this.f16456l = i10;
    }

    @Override // java.io.InputStream
    public final int available() {
        return Math.min(this.f16455i.available(), (int) this.f16456l);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        androidx.loader.app.d.g(this.f16455i, this.f16456l);
        this.f16456l = 0L;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.f16456l <= 0) {
            return -1;
        }
        int i10 = this.f16455i.read();
        if (i10 != -1) {
            this.f16456l--;
        }
        return i10;
    }

    @Override // java.io.InputStream
    public final long skip(long j10) throws IOException {
        long jSkip = this.f16455i.skip(Math.min(j10, this.f16456l));
        this.f16456l -= jSkip;
        return jSkip;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i10, int i11) throws IOException {
        long j10 = this.f16456l;
        if (j10 <= 0) {
            return -1;
        }
        int i12 = this.f16455i.read(bArr, i10, Math.min(i11, (int) j10));
        if (i12 > 0) {
            this.f16456l -= (long) i12;
        }
        return i12;
    }
}
