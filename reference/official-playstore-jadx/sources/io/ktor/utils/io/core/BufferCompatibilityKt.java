package io.ktor.utils.io.core;

import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import g8.b;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.EncodeResult;
import io.ktor.utils.io.core.internal.UTF8Kt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0005\u001a!\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\u0007\u001a#\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\r\u001a\u001b\u0010\u000e\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0013\u0010\u0010\u001a\u00020\u0000*\u00020\u0000H\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0013\u0010\u0010\u001a\u00020\u0012*\u00020\u0012H\u0007¢\u0006\u0004\b\u0010\u0010\u0013\u001a\u0013\u0010\u0014\u001a\u00020\u0005*\u00020\u0000H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a/\u0010\u001a\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001b\u0010\u001e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001d\u0010\u001e\u001a\u00020\u0000*\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0004\b\u001e\u0010 \u001a-\u0010\u001e\u001a\u00020\u0000*\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u001e\u0010!\u001a\u0017\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u0001H\u0002¢\u0006\u0004\b$\u0010%\u001a+\u0010\u001e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0017\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u001e\u0010'\u001a=\u00101\u001a\u00020\u0001*\u00020\u00002\n\u0010*\u001a\u00060(j\u0002`)2\n\u0010-\u001a\u00060+j\u0002`,2\u0006\u0010/\u001a\u00020.2\b\b\u0002\u00100\u001a\u00020\u0001H\u0007¢\u0006\u0004\b1\u00102\u001a\u0013\u00103\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b3\u00104\u001a3\u00108\u001a\u00020\u0005*\u00020\u00002\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u0003052\b\b\u0002\u00107\u001a\u00020\u00012\b\b\u0002\u0010\"\u001a\u00020\u0001¢\u0006\u0004\b8\u00109\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006:"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "", "times", "", "value", "Lx6/t0;", "fill", "(Lio/ktor/utils/io/core/Buffer;IB)V", "Lx6/k0;", "fill-sEu17AQ", "", "n", "v", "(Lio/ktor/utils/io/core/Buffer;JB)V", "pushBack", "(Lio/ktor/utils/io/core/Buffer;I)V", "makeView", "(Lio/ktor/utils/io/core/Buffer;)Lio/ktor/utils/io/core/Buffer;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "flush", "(Lio/ktor/utils/io/core/Buffer;)V", "", "csq", TtmlNode.START, TtmlNode.END, "appendChars", "(Lio/ktor/utils/io/core/Buffer;Ljava/lang/CharSequence;II)I", "", "c", "append", "(Lio/ktor/utils/io/core/Buffer;C)Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Buffer;Ljava/lang/CharSequence;)Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Buffer;Ljava/lang/CharSequence;II)Lio/ktor/utils/io/core/Buffer;", "length", "", "appendFailed", "(I)Ljava/lang/Void;", "", "(Lio/ktor/utils/io/core/Buffer;[CII)Lio/ktor/utils/io/core/Buffer;", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "decoder", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "", "lastBuffer", "max", "readText", "(Lio/ktor/utils/io/core/Buffer;Ljava/nio/charset/CharsetDecoder;Ljava/lang/Appendable;ZI)I", "tryPeek", "(Lio/ktor/utils/io/core/Buffer;)I", "", "dst", "offset", "readFully", "(Lio/ktor/utils/io/core/Buffer;[Ljava/lang/Byte;II)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BufferCompatibilityKt {
    @e
    public static final Buffer append(Buffer buffer, CharSequence charSequence) {
        throw new IllegalStateException("This is no longer supported. Use a packet builder to append characters instead.");
    }

    public static final int appendChars(Buffer buffer, CharSequence charSequence, int i10, int i11) {
        int iM7007encodeUTF8lBXzO7A = UTF8Kt.m7007encodeUTF8lBXzO7A(buffer.getMemory(), charSequence, i10, i11, buffer.getWritePosition(), buffer.getLimit());
        int iM7003getCharactersMh2AYeg = EncodeResult.m7003getCharactersMh2AYeg(iM7007encodeUTF8lBXzO7A) & 65535;
        buffer.commitWritten(EncodeResult.m7002getBytesMh2AYeg(iM7007encodeUTF8lBXzO7A) & 65535);
        return iM7003getCharactersMh2AYeg + i10;
    }

    public static /* synthetic */ int appendChars$default(Buffer buffer, CharSequence charSequence, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = charSequence.length();
        }
        return appendChars(buffer, charSequence, i10, i11);
    }

    private static final Void appendFailed(int i10) throws BufferLimitExceededException {
        throw new BufferLimitExceededException(c.o(i10, "Not enough free space available to write ", " character(s)."));
    }

    @e
    public static final void fill(Buffer buffer, long j10, byte b10) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "n");
        }
        fill(buffer, (int) j10, b10);
    }

    /* JADX INFO: renamed from: fill-sEu17AQ, reason: not valid java name */
    public static final void m6891fillsEu17AQ(Buffer buffer, int i10, byte b10) {
        fill(buffer, i10, b10);
    }

    @e
    public static final void flush(Buffer buffer) {
    }

    @e
    public static final Buffer makeView(Buffer buffer) {
        return buffer.duplicate();
    }

    @e
    public static final void pushBack(Buffer buffer, int i10) {
        buffer.rewind(i10);
    }

    public static final void readFully(Buffer buffer, Byte[] bArr, int i10, int i11) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < i11) {
            throw new EOFException(c.o(i11, "Not enough bytes available to read ", " bytes"));
        }
        for (int i12 = 0; i12 < i11; i12++) {
            bArr[i12 + i10] = Byte.valueOf(memory.get(i12 + readPosition));
        }
        buffer.discardExact(i11);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, Byte[] bArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        readFully(buffer, bArr, i10, i11);
    }

    @e
    public static final int readText(Buffer buffer, CharsetDecoder charsetDecoder, Appendable appendable, boolean z, int i10) {
        return CharsetJVMKt.decodeBuffer(charsetDecoder, buffer, appendable, z, i10);
    }

    public static /* synthetic */ int readText$default(Buffer buffer, CharsetDecoder charsetDecoder, Appendable appendable, boolean z, int i10, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        return readText(buffer, charsetDecoder, appendable, z, i10);
    }

    @e
    public static final int tryPeek(Buffer buffer) {
        return buffer.tryPeekByte();
    }

    @e
    public static final Buffer append(Buffer buffer, CharSequence charSequence, int i10, int i11) {
        throw new IllegalStateException("This is no longer supported. Use a packet builder to append characters instead.");
    }

    @e
    public static final ChunkBuffer makeView(ChunkBuffer chunkBuffer) {
        return chunkBuffer.duplicate();
    }

    @e
    public static final Buffer append(Buffer buffer, char[] cArr, int i10, int i11) {
        throw new IllegalStateException("This is no longer supported. Use a packet builder to append characters instead.");
    }

    @e
    public static final Buffer append(Buffer buffer, char c10) throws BufferLimitExceededException {
        int i10;
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit();
        if (c10 >= 0 && c10 < 128) {
            memory.put(writePosition, (byte) c10);
            i10 = 1;
        } else if (128 <= c10 && c10 < 2048) {
            memory.put(writePosition, (byte) (((c10 >> 6) & 31) | PsExtractor.AUDIO_STREAM));
            memory.put(writePosition + 1, (byte) ((c10 & '?') | 128));
            i10 = 2;
        } else if (2048 <= c10 && c10 < 0) {
            memory.put(writePosition, (byte) (((c10 >> '\f') & 15) | 224));
            memory.put(writePosition + 1, (byte) (((c10 >> 6) & 63) | 128));
            memory.put(writePosition + 2, (byte) ((c10 & '?') | 128));
            i10 = 3;
        } else {
            if (0 > c10 || c10 >= 0) {
                UTF8Kt.malformedCodePoint(c10);
                throw new KotlinNothingValueException();
            }
            memory.put(writePosition, (byte) (((c10 >> 18) & 7) | PsExtractor.VIDEO_STREAM_MASK));
            memory.put(writePosition + 1, (byte) (((c10 >> '\f') & 63) | 128));
            memory.put(writePosition + 2, (byte) (((c10 >> 6) & 63) | 128));
            memory.put(writePosition + 3, (byte) ((c10 & '?') | 128));
            i10 = 4;
        }
        if (i10 <= limit - writePosition) {
            buffer.commitWritten(i10);
            return buffer;
        }
        appendFailed(1);
        throw new KotlinNothingValueException();
    }

    public static final void fill(Buffer buffer, int i10, byte b10) {
        if (i10 >= 0) {
            if (i10 <= buffer.getLimit() - buffer.getWritePosition()) {
                MemoryJvmKt.m6768fillJT6ljtQ(buffer.getMemory(), buffer.getWritePosition(), i10, b10);
                buffer.commitWritten(i10);
                return;
            } else {
                StringBuilder sbS = a0.c.s(i10, "times shouldn't be greater than the write remaining space: ", " > ");
                sbS.append(buffer.getLimit() - buffer.getWritePosition());
                throw new IllegalArgumentException(sbS.toString().toString());
            }
        }
        throw new IllegalArgumentException(a0.c.i(i10, "times shouldn't be negative: ").toString());
    }
}
