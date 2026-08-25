package f1;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends b {
    public h(byte[] bArr) {
        super(bArr);
        this.f15209i.mark(Integer.MAX_VALUE);
    }

    public final void j(long j10) throws IOException {
        int i10 = this.f15211m;
        if (i10 > j10) {
            this.f15211m = 0;
            this.f15209i.reset();
        } else {
            j10 -= (long) i10;
        }
        i((int) j10);
    }

    public h(InputStream inputStream) {
        super(inputStream);
        if (inputStream.markSupported()) {
            this.f15209i.mark(Integer.MAX_VALUE);
            return;
        }
        throw new IllegalArgumentException("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
    }
}
