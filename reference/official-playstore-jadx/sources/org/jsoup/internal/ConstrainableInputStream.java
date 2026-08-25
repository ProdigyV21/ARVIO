package org.jsoup.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public final class ConstrainableInputStream extends BufferedInputStream {
    private final boolean capped;
    private boolean interrupted;
    private final int maxSize;
    private int remaining;
    private long startTime;
    private long timeout;

    private ConstrainableInputStream(InputStream inputStream, int i10, int i11) {
        super(inputStream, i10);
        this.timeout = 0L;
        Validate.isTrue(i11 >= 0);
        this.maxSize = i11;
        this.remaining = i11;
        this.capped = i11 != 0;
        this.startTime = System.nanoTime();
    }

    private boolean expired() {
        return this.timeout != 0 && System.nanoTime() - this.startTime > this.timeout;
    }

    public static ConstrainableInputStream wrap(InputStream inputStream, int i10, int i11) {
        return inputStream instanceof ConstrainableInputStream ? (ConstrainableInputStream) inputStream : new ConstrainableInputStream(inputStream, i10, i11);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i10, int i11) throws IOException {
        int i12;
        if (this.interrupted || (this.capped && this.remaining <= 0)) {
            return -1;
        }
        if (Thread.currentThread().isInterrupted()) {
            this.interrupted = true;
            return -1;
        }
        if (expired()) {
            throw new SocketTimeoutException("Read timeout");
        }
        if (this.capped && i11 > (i12 = this.remaining)) {
            i11 = i12;
        }
        try {
            int i13 = super.read(bArr, i10, i11);
            this.remaining -= i13;
            return i13;
        } catch (SocketTimeoutException unused) {
            return 0;
        }
    }

    public ByteBuffer readToByteBuffer(int i10) throws IOException {
        return DataUtil.readToByteBuffer(this, i10);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        super.reset();
        this.remaining = this.maxSize - ((BufferedInputStream) this).markpos;
    }

    public ConstrainableInputStream timeout(long j10, long j11) {
        this.startTime = j10;
        this.timeout = j11 * 1000000;
        return this;
    }
}
