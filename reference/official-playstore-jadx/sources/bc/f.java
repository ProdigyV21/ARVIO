package bc;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends FilterOutputStream {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final byte[] f7357i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7358l;

    public f(FileOutputStream fileOutputStream) {
        super(fileOutputStream);
        this.f7357i = new byte[8192];
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        i();
        ((FilterOutputStream) this).out.flush();
    }

    public final void i() throws IOException {
        int i10 = this.f7358l;
        if (i10 > 0) {
            ((FilterOutputStream) this).out.write(this.f7357i, 0, i10);
            this.f7358l = 0;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int i10) throws IOException {
        int i11 = this.f7358l;
        byte[] bArr = this.f7357i;
        if (i11 >= bArr.length) {
            i();
        }
        int i12 = this.f7358l;
        this.f7358l = i12 + 1;
        bArr[i12] = (byte) i10;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i10, int i11) throws IOException {
        byte[] bArr2 = this.f7357i;
        if (i11 >= bArr2.length) {
            i();
            ((FilterOutputStream) this).out.write(bArr, i10, i11);
        } else {
            if (i11 > bArr2.length - this.f7358l) {
                i();
            }
            System.arraycopy(bArr, i10, bArr2, this.f7358l, i11);
            this.f7358l += i11;
        }
    }
}
