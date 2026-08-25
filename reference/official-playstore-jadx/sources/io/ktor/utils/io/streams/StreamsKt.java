package io.ktor.utils.io.streams;

import androidx.compose.material3.d;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputArraysKt;
import io.ktor.utils.io.core.InsufficientSpaceException;
import io.ktor.utils.io.core.Output;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\u001a*\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0019\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\n\u001a\u0019\u0010\u000e\u001a\u00020\b*\u00020\u000b2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\u0010\u001a\u00020\b*\u00020\u000b2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u000f\u001a\u0019\u0010\u0011\u001a\u00020\b*\u00020\u000b2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u000f\u001a#\u0010\u0014\u001a\u00020\b*\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0011\u0010\u0016\u001a\u00020\u000b*\u00020\b¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0011\u0010\u0019\u001a\u00020\u0018*\u00020\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u00020\u0000*\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0011\u0010\u001e\u001a\u00020\u001d*\u00020\u0002¢\u0006\u0004\b\u001e\u0010\u001f\"\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Ljava/io/OutputStream;", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "writePacket", "(Ljava/io/OutputStream;Lr7/l;)V", "Lio/ktor/utils/io/core/ByteReadPacket;", "packet", "(Ljava/io/OutputStream;Lio/ktor/utils/io/core/ByteReadPacket;)V", "Ljava/io/InputStream;", "", "n", "readPacketExact", "(Ljava/io/InputStream;J)Lio/ktor/utils/io/core/ByteReadPacket;", "readPacketAtLeast", "readPacketAtMost", "min", "max", "readPacketImpl", "(Ljava/io/InputStream;JJ)Lio/ktor/utils/io/core/ByteReadPacket;", "inputStream", "(Lio/ktor/utils/io/core/ByteReadPacket;)Ljava/io/InputStream;", "Ljava/io/Reader;", "readerUTF8", "(Lio/ktor/utils/io/core/ByteReadPacket;)Ljava/io/Reader;", "outputStream", "(Lio/ktor/utils/io/core/BytePacketBuilder;)Ljava/io/OutputStream;", "Ljava/io/Writer;", "writerUTF8", "(Lio/ktor/utils/io/core/BytePacketBuilder;)Ljava/io/Writer;", "", "SkipBuffer", "[C", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StreamsKt {
    private static final char[] SkipBuffer = new char[8192];

    public static final InputStream inputStream(final ByteReadPacket byteReadPacket) {
        return new InputStream() { // from class: io.ktor.utils.io.streams.StreamsKt.inputStream.1
            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(byteReadPacket.getRemaining(), 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                byteReadPacket.release();
            }

            @Override // java.io.InputStream
            public int read() {
                if (byteReadPacket.getEndOfInput()) {
                    return -1;
                }
                return byteReadPacket.readByte() & 255;
            }
        };
    }

    public static final OutputStream outputStream(final BytePacketBuilder bytePacketBuilder) {
        return new OutputStream() { // from class: io.ktor.utils.io.streams.StreamsKt.outputStream.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream
            public void write(int b10) throws InsufficientSpaceException {
                bytePacketBuilder.writeByte((byte) b10);
            }

            @Override // java.io.OutputStream
            public void write(byte[] b10, int off, int len) {
                io.ktor.utils.io.core.OutputKt.writeFully((Output) bytePacketBuilder, b10, off, len);
            }
        };
    }

    public static final ByteReadPacket readPacketAtLeast(InputStream inputStream, long j10) {
        return readPacketImpl(inputStream, j10, Long.MAX_VALUE);
    }

    public static final ByteReadPacket readPacketAtMost(InputStream inputStream, long j10) {
        return readPacketImpl(inputStream, 1L, j10);
    }

    public static final ByteReadPacket readPacketExact(InputStream inputStream, long j10) {
        return readPacketImpl(inputStream, j10, j10);
    }

    private static final ByteReadPacket readPacketImpl(InputStream inputStream, long j10, long j11) {
        long j12 = 0;
        if (j10 < 0) {
            throw new IllegalArgumentException("min shouldn't be negative");
        }
        if (j10 > j11) {
            StringBuilder sbR = d.r(j10, "min shouldn't be greater than max: ", " > ");
            sbR.append(j11);
            throw new IllegalArgumentException(sbR.toString().toString());
        }
        int i10 = (int) (j11 <= 4096 ? j11 : 4096L);
        byte[] bArr = new byte[i10];
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        while (true) {
            if (j12 >= j10 && (j12 != j10 || j10 != 0)) {
                break;
            }
            try {
                int i11 = inputStream.read(bArr, 0, Math.min((int) Math.min(j11 - j12, 2147483647L), i10));
                if (i11 == -1) {
                    throw new EOFException("Premature end of stream: was read " + j12 + " bytes of " + j10);
                }
                j12 += (long) i11;
                io.ktor.utils.io.core.OutputKt.writeFully((Output) bytePacketBuilder, bArr, 0, i11);
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        }
        return bytePacketBuilder.build();
    }

    public static final Reader readerUTF8(final ByteReadPacket byteReadPacket) {
        return new Reader() { // from class: io.ktor.utils.io.streams.StreamsKt.readerUTF8.1
            @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                byteReadPacket.release();
            }

            @Override // java.io.Reader
            public int read(char[] cbuf, int off, int len) {
                return byteReadPacket.readAvailableCharacters$ktor_io(cbuf, off, len);
            }

            @Override // java.io.Reader
            public long skip(long n6) {
                int i10;
                char[] cArr = StreamsKt.SkipBuffer;
                int length = cArr.length;
                long j10 = 0;
                while (j10 < n6 && (i10 = read(cArr, 0, (int) Math.min(length, n6 - j10))) != -1) {
                    j10 += (long) i10;
                }
                return j10;
            }
        };
    }

    public static final void writePacket(OutputStream outputStream, ByteReadPacket byteReadPacket) throws Throwable {
        ByteReadPacket byteReadPacket2;
        long remaining = byteReadPacket.getRemaining();
        if (remaining == 0) {
            return;
        }
        if (remaining > 4096) {
            remaining = 4096;
        }
        byte[] bArr = new byte[(int) remaining];
        while (!byteReadPacket.getEndOfInput()) {
            try {
                byteReadPacket2 = byteReadPacket;
            } catch (Throwable th) {
                th = th;
                byteReadPacket2 = byteReadPacket;
            }
            try {
                outputStream.write(bArr, 0, InputArraysKt.readAvailable$default((Input) byteReadPacket2, bArr, 0, 0, 6, (Object) null));
                byteReadPacket = byteReadPacket2;
            } catch (Throwable th2) {
                th = th2;
                Throwable th3 = th;
                byteReadPacket2.release();
                throw th3;
            }
        }
        byteReadPacket.release();
    }

    public static final Writer writerUTF8(final BytePacketBuilder bytePacketBuilder) {
        return new Writer() { // from class: io.ktor.utils.io.streams.StreamsKt.writerUTF8.1
            @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.Writer, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.Writer
            public void write(char[] cbuf, int off, int len) {
                bytePacketBuilder.append(cbuf, off, len + off);
            }
        };
    }

    public static final void writePacket(OutputStream outputStream, l<? super BytePacketBuilder, t0> lVar) throws Throwable {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            lVar.invoke(bytePacketBuilder);
            writePacket(outputStream, bytePacketBuilder.build());
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }
}
