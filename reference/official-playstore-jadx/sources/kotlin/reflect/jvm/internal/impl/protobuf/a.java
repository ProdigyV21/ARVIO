package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends FilterInputStream {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f19760i;

    public a(ByteArrayInputStream byteArrayInputStream, int i10) {
        super(byteArrayInputStream);
        this.f19760i = i10;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() {
        return Math.min(super.available(), this.f19760i);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        if (this.f19760i <= 0) {
            return -1;
        }
        int i10 = super.read();
        if (i10 >= 0) {
            this.f19760i--;
        }
        return i10;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j10) throws IOException {
        long jSkip = super.skip(Math.min(j10, this.f19760i));
        if (jSkip >= 0) {
            this.f19760i = (int) (((long) this.f19760i) - jSkip);
        }
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i10, int i11) throws IOException {
        int i12 = this.f19760i;
        if (i12 <= 0) {
            return -1;
        }
        int i13 = super.read(bArr, i10, Math.min(i11, i12));
        if (i13 >= 0) {
            this.f19760i -= i13;
        }
        return i13;
    }
}
