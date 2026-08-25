package xb;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends OutputStream {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22753i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f22754l;

    public i(j jVar) {
        this.f22754l = jVar;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        int i10 = this.f22753i;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        switch (this.f22753i) {
            case 0:
                break;
            default:
                ((FileOutputStream) this.f22754l).flush();
                break;
        }
    }

    public String toString() {
        switch (this.f22753i) {
            case 0:
                return ((j) this.f22754l) + ".outputStream()";
            default:
                return super.toString();
        }
    }

    @Override // java.io.OutputStream
    public final void write(int i10) throws IOException {
        switch (this.f22753i) {
            case 0:
                ((j) this.f22754l).M0(i10);
                break;
            default:
                ((FileOutputStream) this.f22754l).write(i10);
                break;
        }
    }

    public i(FileOutputStream fileOutputStream) {
        this.f22754l = fileOutputStream;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        switch (this.f22753i) {
            case 1:
                ((FileOutputStream) this.f22754l).write(bArr);
                break;
            default:
                super.write(bArr);
                break;
        }
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i10, int i11) throws IOException {
        switch (this.f22753i) {
            case 0:
                ((j) this.f22754l).write(bArr, i10, i11);
                break;
            default:
                ((FileOutputStream) this.f22754l).write(bArr, i10, i11);
                break;
        }
    }

    private final void i() {
    }

    private final void j() {
    }

    private final void k() {
    }
}
