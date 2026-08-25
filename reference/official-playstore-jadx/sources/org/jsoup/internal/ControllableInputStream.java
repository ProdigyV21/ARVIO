package org.jsoup.internal;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import org.jsoup.helper.Validate;

/* JADX INFO: loaded from: classes5.dex */
public class ControllableInputStream extends FilterInputStream {
    private final BufferedInputStream buff;
    private final boolean capped;
    private boolean interrupted;
    private int markPos;
    private final int maxSize;
    private int remaining;
    private long startTime;
    private long timeout;

    private ControllableInputStream(BufferedInputStream bufferedInputStream, int i10) {
        super(bufferedInputStream);
        this.timeout = 0L;
        Validate.isTrue(i10 >= 0);
        this.buff = bufferedInputStream;
        this.capped = i10 != 0;
        this.maxSize = i10;
        this.remaining = i10;
        this.markPos = -1;
        this.startTime = System.nanoTime();
    }

    private boolean expired() {
        return this.timeout != 0 && System.nanoTime() - this.startTime > this.timeout;
    }

    public static ByteBuffer readToByteBuffer(InputStream inputStream, int i10) throws IOException {
        Validate.isTrue(i10 >= 0, "maxSize must be 0 (unlimited) or larger");
        Validate.notNull(inputStream);
        boolean z = i10 > 0;
        int i11 = 32768;
        if (z && i10 < 32768) {
            i11 = i10;
        }
        byte[] bArr = new byte[i11];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i11);
        while (true) {
            int i12 = inputStream.read(bArr, 0, z ? Math.min(i10, i11) : i11);
            if (i12 == -1) {
                break;
            }
            if (z) {
                if (i12 >= i10) {
                    byteArrayOutputStream.write(bArr, 0, i10);
                    break;
                }
                i10 -= i12;
            }
            byteArrayOutputStream.write(bArr, 0, i12);
        }
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    public static ControllableInputStream wrap(InputStream inputStream, int i10, int i11) {
        return inputStream instanceof ControllableInputStream ? (ControllableInputStream) inputStream : inputStream instanceof BufferedInputStream ? new ControllableInputStream((BufferedInputStream) inputStream, i11) : new ControllableInputStream(new BufferedInputStream(inputStream, i10), i11);
    }

    public BufferedInputStream inputStream() {
        return this.buff;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i10) {
        super.mark(i10);
        this.markPos = this.maxSize - this.remaining;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
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

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        super.reset();
        this.remaining = this.maxSize - this.markPos;
    }

    public ControllableInputStream timeout(long j10, long j11) {
        this.startTime = j10;
        this.timeout = j11 * 1000000;
        return this;
    }
}
