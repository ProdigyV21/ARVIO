package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteWriteChannel;
import java.io.OutputStream;
import ka.v1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\b\u0006*\u0001\u0015\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/utils/io/jvm/javaio/OutputAdapter;", "Ljava/io/OutputStream;", "Lka/v1;", "parent", "Lio/ktor/utils/io/ByteWriteChannel;", "channel", "<init>", "(Lka/v1;Lio/ktor/utils/io/ByteWriteChannel;)V", "", "b", "Lx6/t0;", "write", "(I)V", "", "off", "len", "([BII)V", "flush", "()V", "close", "Lio/ktor/utils/io/ByteWriteChannel;", "io/ktor/utils/io/jvm/javaio/OutputAdapter$loop$1", "loop", "Lio/ktor/utils/io/jvm/javaio/OutputAdapter$loop$1;", "single", "[B", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class OutputAdapter extends OutputStream {
    private final ByteWriteChannel channel;
    private final OutputAdapter$loop$1 loop;
    private byte[] single;

    public OutputAdapter(v1 v1Var, ByteWriteChannel byteWriteChannel) {
        this.channel = byteWriteChannel;
        this.loop = new OutputAdapter$loop$1(v1Var, this);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            this.loop.submitAndAwait(BlockingKt.CloseToken);
            this.loop.shutdown();
        } finally {
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() {
        this.loop.submitAndAwait(BlockingKt.FlushToken);
    }

    @Override // java.io.OutputStream
    public synchronized void write(int b10) {
        try {
            byte[] bArr = this.single;
            if (bArr == null) {
                bArr = new byte[1];
                this.single = bArr;
            }
            bArr[0] = (byte) b10;
            this.loop.submitAndAwait(bArr, 0, 1);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] b10, int off, int len) {
        this.loop.submitAndAwait(b10, off, len);
    }
}
