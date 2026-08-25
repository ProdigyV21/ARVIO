package j5;

import java.io.OutputStream;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends OutputStream {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f19211i;

    @Override // java.io.OutputStream
    public final void write(int i10) {
        this.f19211i++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.f19211i += (long) bArr.length;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i10, int i11) {
        int i12;
        if (i10 >= 0 && i10 <= bArr.length && i11 >= 0 && (i12 = i10 + i11) <= bArr.length && i12 >= 0) {
            this.f19211i += (long) i11;
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
