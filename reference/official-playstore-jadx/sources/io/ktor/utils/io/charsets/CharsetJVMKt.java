package io.ktor.utils.io.charsets;

import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferPrimitivesKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\b\u001a\u00020\u0007*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\t\u001a/\u0010\n\u001a\u00020\u0007*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\t\u001a7\u0010\r\u001a\u00020\u0004*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a%\u0010\u0012\u001a\u00020\u0011*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001f\u0010\u0015\u001a\u00020\u0014*\u00060\u0000j\u0002`\u00012\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a=\u0010\u001e\u001a\u00020\u0004*\u00060\u0017j\u0002`\u00182\u0006\u0010\u0003\u001a\u00020\u000b2\n\u0010\u001b\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u001c\u001a\u00020\u00142\b\b\u0002\u0010\u001d\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a3\u0010 \u001a\u00020\u0007*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b \u0010\t\u001a1\u0010\"\u001a\u00020\u0004*\u00060\u0017j\u0002`\u00182\u0006\u0010\u0003\u001a\u00020!2\n\u0010\f\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u001d\u001a\u00020\u0004¢\u0006\u0004\b\"\u0010#\u001a%\u0010&\u001a\u00020%*\u00060\u0017j\u0002`\u00182\u0006\u0010\u0003\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0004¢\u0006\u0004\b&\u0010'\u001a'\u0010(\u001a\u00020%*\u00060\u0017j\u0002`\u00182\u0006\u0010\u0003\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0004H\u0002¢\u0006\u0004\b(\u0010'\u001a'\u0010)\u001a\u00020%*\u00060\u0017j\u0002`\u00182\u0006\u0010\u0003\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0004H\u0002¢\u0006\u0004\b)\u0010'\u001a\u0013\u0010+\u001a\u00020\u0011*\u00020*H\u0002¢\u0006\u0004\b+\u0010,\"\u0014\u0010-\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b-\u0010.\"\u001c\u00101\u001a\n 0*\u0004\u0018\u00010/0/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102\"\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105\"\u0019\u0010:\u001a\u00020%*\u000606j\u0002`78F¢\u0006\u0006\u001a\u0004\b8\u00109\"\u001d\u0010=\u001a\u000606j\u0002`7*\u00060\u0000j\u0002`\u00018F¢\u0006\u0006\u001a\u0004\b;\u0010<\"\u001d\u0010=\u001a\u000606j\u0002`7*\u00060\u0017j\u0002`\u00188F¢\u0006\u0006\u001a\u0004\b;\u0010>*\n\u0010?\"\u0002062\u000206*\n\u0010@\"\u00020\u00172\u00020\u0017*\n\u0010A\"\u00020\u00002\u00020\u0000*\n\u0010C\"\u00020B2\u00020B¨\u0006D"}, d2 = {"Ljava/nio/charset/CharsetEncoder;", "Lio/ktor/utils/io/charsets/CharsetEncoder;", "", "input", "", "fromIndex", "toIndex", "", "encodeToByteArray", "(Ljava/nio/charset/CharsetEncoder;Ljava/lang/CharSequence;II)[B", "encodeToByteArraySlow", "Lio/ktor/utils/io/core/Buffer;", "dst", "encodeImpl", "(Ljava/nio/charset/CharsetEncoder;Ljava/lang/CharSequence;IILio/ktor/utils/io/core/Buffer;)I", "Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/core/Output;", "Lx6/t0;", "encodeUTF8", "(Ljava/nio/charset/CharsetEncoder;Lio/ktor/utils/io/core/ByteReadPacket;Lio/ktor/utils/io/core/Output;)V", "", "encodeComplete", "(Ljava/nio/charset/CharsetEncoder;Lio/ktor/utils/io/core/Buffer;)Z", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "lastBuffer", "max", "decodeBuffer", "(Ljava/nio/charset/CharsetDecoder;Lio/ktor/utils/io/core/Buffer;Ljava/lang/Appendable;ZI)I", "encodeToByteArrayImpl1", "Lio/ktor/utils/io/core/Input;", "decode", "(Ljava/nio/charset/CharsetDecoder;Lio/ktor/utils/io/core/Input;Ljava/lang/Appendable;I)I", "inputLength", "", "decodeExactBytes", "(Ljava/nio/charset/CharsetDecoder;Lio/ktor/utils/io/core/Input;I)Ljava/lang/String;", "decodeImplByteBuffer", "decodeImplSlow", "Ljava/nio/charset/CoderResult;", "throwExceptionWrapped", "(Ljava/nio/charset/CoderResult;)V", "DECODE_CHAR_BUFFER_SIZE", "I", "Ljava/nio/CharBuffer;", "kotlin.jvm.PlatformType", "EmptyCharBuffer", "Ljava/nio/CharBuffer;", "Ljava/nio/ByteBuffer;", "EmptyByteBuffer", "Ljava/nio/ByteBuffer;", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "getName", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", ContentDisposition.Parameters.Name, "getCharset", "(Ljava/nio/charset/CharsetEncoder;)Ljava/nio/charset/Charset;", HttpAuthHeader.Parameters.Charset, "(Ljava/nio/charset/CharsetDecoder;)Ljava/nio/charset/Charset;", "Charset", "CharsetDecoder", "CharsetEncoder", "Lkotlin/text/a;", "Charsets", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CharsetJVMKt {
    private static final int DECODE_CHAR_BUFFER_SIZE = 8192;
    private static final CharBuffer EmptyCharBuffer = CharBuffer.allocate(0);
    private static final ByteBuffer EmptyByteBuffer = ByteBuffer.allocate(0);

    public static /* synthetic */ void Charset$annotations() {
    }

    public static final int decode(CharsetDecoder charsetDecoder, Input input, Appendable appendable, int i10) {
        CoderResult coderResultDecode;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        CharBuffer charBufferAllocate = CharBuffer.allocate(8192);
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        int iRemaining = 0;
        if (chunkBufferPrepareReadFirstHead != null) {
            int i11 = 1;
            int i12 = 1;
            int iRemaining2 = 0;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i11) {
                        int i13 = i10 - iRemaining2;
                        if (i13 == 0) {
                            i11 = 0;
                        } else {
                            try {
                                ByteBuffer memory = chunkBufferPrepareReadFirstHead.getMemory();
                                int readPosition = chunkBufferPrepareReadFirstHead.getReadPosition();
                                int writePosition2 = chunkBufferPrepareReadFirstHead.getWritePosition() - readPosition;
                                ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, readPosition, writePosition2);
                                charBufferAllocate.clear();
                                if (i13 < 8192) {
                                    charBufferAllocate.limit(i13);
                                }
                                CoderResult coderResultDecode2 = charsetDecoder.decode(byteBufferM6756slice87lwejk, charBufferAllocate, false);
                                charBufferAllocate.flip();
                                iRemaining2 += charBufferAllocate.remaining();
                                appendable.append(charBufferAllocate);
                                if (coderResultDecode2.isMalformed() || coderResultDecode2.isUnmappable()) {
                                    throwExceptionWrapped(coderResultDecode2);
                                }
                                i12 = (coderResultDecode2.isUnderflow() && byteBufferM6756slice87lwejk.hasRemaining()) ? i12 + 1 : 1;
                                if (byteBufferM6756slice87lwejk.limit() != writePosition2) {
                                    throw new IllegalStateException("Buffer's limit change is not allowed");
                                }
                                chunkBufferPrepareReadFirstHead.discardExact(byteBufferM6756slice87lwejk.position());
                                i11 = i12;
                            } finally {
                                chunkBufferPrepareReadFirstHead.getWritePosition();
                                chunkBufferPrepareReadFirstHead.getReadPosition();
                            }
                        }
                        writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i11 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i11);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        break;
                    }
                    if (i11 <= 0) {
                        iRemaining = 1;
                        chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (iRemaining != 0) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
            iRemaining = iRemaining2;
        }
        do {
            charBufferAllocate.clear();
            int i14 = i10 - iRemaining;
            if (i14 == 0) {
                break;
            }
            if (i14 < 8192) {
                charBufferAllocate.limit(i14);
            }
            coderResultDecode = charsetDecoder.decode(EmptyByteBuffer, charBufferAllocate, true);
            charBufferAllocate.flip();
            iRemaining += charBufferAllocate.remaining();
            appendable.append(charBufferAllocate);
            if (coderResultDecode.isUnmappable() || coderResultDecode.isMalformed()) {
                throwExceptionWrapped(coderResultDecode);
            }
        } while (coderResultDecode.isOverflow());
        return iRemaining;
    }

    public static final int decodeBuffer(CharsetDecoder charsetDecoder, Buffer buffer, Appendable appendable, boolean z, int i10) {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition() - readPosition;
        ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, readPosition, writePosition);
        ChunkBuffer chunkBufferBorrow = ChunkBuffer.INSTANCE.getPool().borrow();
        CharBuffer charBufferAsCharBuffer = chunkBufferBorrow.getMemory().asCharBuffer();
        int i11 = 0;
        while (byteBufferM6756slice87lwejk.hasRemaining() && i11 < i10) {
            try {
                int iMin = Math.min(charBufferAsCharBuffer.capacity(), i10 - i11);
                charBufferAsCharBuffer.clear();
                charBufferAsCharBuffer.limit(iMin);
                CoderResult coderResultDecode = charsetDecoder.decode(byteBufferM6756slice87lwejk, charBufferAsCharBuffer, z);
                if (coderResultDecode.isMalformed() || coderResultDecode.isUnmappable()) {
                    throwExceptionWrapped(coderResultDecode);
                }
                i11 += iMin;
            } catch (Throwable th) {
                chunkBufferBorrow.release(ChunkBuffer.INSTANCE.getPool());
                throw th;
            }
        }
        chunkBufferBorrow.release(ChunkBuffer.INSTANCE.getPool());
        if (byteBufferM6756slice87lwejk.limit() != writePosition) {
            throw new IllegalStateException("Buffer's limit change is not allowed");
        }
        buffer.discardExact(byteBufferM6756slice87lwejk.position());
        return i11;
    }

    public static /* synthetic */ int decodeBuffer$default(CharsetDecoder charsetDecoder, Buffer buffer, Appendable appendable, boolean z, int i10, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        return decodeBuffer(charsetDecoder, buffer, appendable, z, i10);
    }

    public static final String decodeExactBytes(CharsetDecoder charsetDecoder, Input input, int i10) {
        if (i10 == 0) {
            return "";
        }
        if (input.getHeadEndExclusive() - input.getHeadPosition() < i10) {
            return decodeImplSlow(charsetDecoder, input, i10);
        }
        if (!input.getHeadMemory().hasArray()) {
            return decodeImplByteBuffer(charsetDecoder, input, i10);
        }
        ByteBuffer headMemory = input.getHeadMemory();
        String str = new String(headMemory.array(), input.getHead().getReadPosition() + headMemory.position() + headMemory.arrayOffset(), i10, charsetDecoder.charset());
        input.discardExact(i10);
        return str;
    }

    private static final String decodeImplByteBuffer(CharsetDecoder charsetDecoder, Input input, int i10) throws CharacterCodingException, EOFException {
        CharBuffer charBufferAllocate = CharBuffer.allocate(i10);
        ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(input.getHeadMemory(), input.getHead().getReadPosition(), i10);
        CoderResult coderResultDecode = charsetDecoder.decode(byteBufferM6756slice87lwejk, charBufferAllocate, true);
        if (coderResultDecode.isMalformed() || coderResultDecode.isUnmappable()) {
            throwExceptionWrapped(coderResultDecode);
        }
        charBufferAllocate.flip();
        input.discardExact(byteBufferM6756slice87lwejk.position());
        return charBufferAllocate.toString();
    }

    /* JADX WARN: Finally extract failed */
    private static final String decodeImplSlow(CharsetDecoder charsetDecoder, Input input, int i10) throws Throwable {
        int iPosition;
        int writePosition;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        CharBuffer charBufferAllocate = CharBuffer.allocate(i10);
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        boolean z5 = false;
        if (chunkBufferPrepareReadFirstHead == null) {
            iPosition = i10;
        } else {
            iPosition = i10;
            int i11 = 1;
            int i12 = 1;
            boolean z10 = false;
            while (true) {
                try {
                    writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                } catch (Throwable th) {
                    th = th;
                }
                if (writePosition >= i11) {
                    try {
                        if (charBufferAllocate.hasRemaining() && iPosition != 0) {
                            ByteBuffer memory = chunkBufferPrepareReadFirstHead.getMemory();
                            int readPosition = chunkBufferPrepareReadFirstHead.getReadPosition();
                            int writePosition2 = chunkBufferPrepareReadFirstHead.getWritePosition() - readPosition;
                            ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, readPosition, writePosition2);
                            int iLimit = byteBufferM6756slice87lwejk.limit();
                            int iPosition2 = byteBufferM6756slice87lwejk.position();
                            boolean z11 = iLimit - iPosition2 >= iPosition;
                            if (z11) {
                                byteBufferM6756slice87lwejk.limit(iPosition2 + iPosition);
                            }
                            CoderResult coderResultDecode = charsetDecoder.decode(byteBufferM6756slice87lwejk, charBufferAllocate, z11);
                            if (coderResultDecode.isMalformed() || coderResultDecode.isUnmappable()) {
                                throwExceptionWrapped(coderResultDecode);
                            }
                            i12 = (coderResultDecode.isUnderflow() && byteBufferM6756slice87lwejk.hasRemaining()) ? i12 + 1 : 1;
                            byteBufferM6756slice87lwejk.limit(iLimit);
                            iPosition -= byteBufferM6756slice87lwejk.position() - iPosition2;
                            if (byteBufferM6756slice87lwejk.limit() != writePosition2) {
                                throw new IllegalStateException("Buffer's limit change is not allowed");
                            }
                            chunkBufferPrepareReadFirstHead.discardExact(byteBufferM6756slice87lwejk.position());
                            i11 = i12;
                            z10 = z11;
                            th = th;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                        i11 = 0;
                        writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    } catch (Throwable th2) {
                        chunkBufferPrepareReadFirstHead.getWritePosition();
                        chunkBufferPrepareReadFirstHead.getReadPosition();
                        throw th2;
                    }
                }
                if (writePosition == 0) {
                    try {
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                    } catch (Throwable th3) {
                        th = th3;
                        z = false;
                    }
                } else if (writePosition < i11 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                    UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i11);
                } else {
                    chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                }
                if (chunkBufferPrepareReadNextHead == null) {
                    break;
                }
                if (i11 <= 0) {
                    z5 = true;
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    break;
                }
                chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
            }
            if (z5) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
            z5 = z10;
        }
        if (charBufferAllocate.hasRemaining() && !z5) {
            CoderResult coderResultDecode2 = charsetDecoder.decode(EmptyByteBuffer, charBufferAllocate, true);
            if (coderResultDecode2.isMalformed() || coderResultDecode2.isUnmappable()) {
                throwExceptionWrapped(coderResultDecode2);
            }
        }
        if (iPosition <= 0) {
            if (iPosition < 0) {
                throw new AssertionError("remainingInputBytes < 0");
            }
            charBufferAllocate.flip();
            return charBufferAllocate.toString();
        }
        throw new EOFException("Not enough bytes available: had only " + (i10 - iPosition) + " instead of " + i10);
    }

    public static final boolean encodeComplete(CharsetEncoder charsetEncoder, Buffer buffer) throws CharacterCodingException {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, writePosition, limit);
        CoderResult coderResultEncode = charsetEncoder.encode(EmptyCharBuffer, byteBufferM6756slice87lwejk, true);
        if (coderResultEncode.isMalformed() || coderResultEncode.isUnmappable()) {
            throwExceptionWrapped(coderResultEncode);
        }
        boolean zIsUnderflow = coderResultEncode.isUnderflow();
        if (byteBufferM6756slice87lwejk.limit() != limit) {
            throw new IllegalStateException("Buffer's limit change is not allowed");
        }
        buffer.commitWritten(byteBufferM6756slice87lwejk.position());
        return zIsUnderflow;
    }

    public static final int encodeImpl(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11, Buffer buffer) throws CharacterCodingException {
        CharBuffer charBufferWrap = CharBuffer.wrap(charSequence, i10, i11);
        int iRemaining = charBufferWrap.remaining();
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, writePosition, limit);
        CoderResult coderResultEncode = charsetEncoder.encode(charBufferWrap, byteBufferM6756slice87lwejk, false);
        if (coderResultEncode.isMalformed() || coderResultEncode.isUnmappable()) {
            throwExceptionWrapped(coderResultEncode);
        }
        if (byteBufferM6756slice87lwejk.limit() != limit) {
            throw new IllegalStateException("Buffer's limit change is not allowed");
        }
        buffer.commitWritten(byteBufferM6756slice87lwejk.position());
        return iRemaining - charBufferWrap.remaining();
    }

    public static final byte[] encodeToByteArray(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11) {
        return charSequence instanceof String ? (i10 == 0 && i11 == charSequence.length()) ? ((String) charSequence).getBytes(charsetEncoder.charset()) : ((String) charSequence).substring(i10, i11).getBytes(charsetEncoder.charset()) : encodeToByteArraySlow(charsetEncoder, charSequence, i10, i11);
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = charSequence.length();
        }
        return encodeToByteArray(charsetEncoder, charSequence, i10, i11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final byte[] encodeToByteArrayImpl1(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11) {
        if (i10 >= i11) {
            return UnsafeKt.EmptyByteArray;
        }
        ChunkBuffer.Companion companion = ChunkBuffer.INSTANCE;
        ChunkBuffer chunkBufferBorrow = companion.getPool().borrow();
        try {
            int iEncodeImpl = i10 + encodeImpl(charsetEncoder, charSequence, i10, i11, chunkBufferBorrow);
            if (iEncodeImpl == i11) {
                int writePosition = chunkBufferBorrow.getWritePosition() - chunkBufferBorrow.getReadPosition();
                byte[] bArr = new byte[writePosition];
                BufferPrimitivesKt.readFully((Buffer) chunkBufferBorrow, bArr, 0, writePosition);
                chunkBufferBorrow.release(companion.getPool());
                return bArr;
            }
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
            try {
                bytePacketBuilder.appendSingleChunk$ktor_io(chunkBufferBorrow.duplicate());
                EncodingKt.encodeToImpl(charsetEncoder, bytePacketBuilder, charSequence, iEncodeImpl, i11);
                byte[] bytes$default = io.ktor.utils.io.core.StringsKt.readBytes$default(bytePacketBuilder.build(), 0, 1, null);
                chunkBufferBorrow.release(companion.getPool());
                return bytes$default;
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        } catch (Throwable th2) {
            chunkBufferBorrow.release(ChunkBuffer.INSTANCE.getPool());
            throw th2;
        }
    }

    public static /* synthetic */ byte[] encodeToByteArrayImpl1$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = charSequence.length();
        }
        return encodeToByteArrayImpl1(charsetEncoder, charSequence, i10, i11);
    }

    private static final byte[] encodeToByteArraySlow(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11) throws CharacterCodingException {
        ByteBuffer byteBufferEncode = charsetEncoder.encode(CharBuffer.wrap(charSequence, i10, i11));
        byte[] bArr = null;
        if (byteBufferEncode.hasArray() && byteBufferEncode.arrayOffset() == 0) {
            byte[] bArrArray = byteBufferEncode.array();
            if (bArrArray.length == byteBufferEncode.remaining()) {
                bArr = bArrArray;
            }
        }
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[byteBufferEncode.remaining()];
        byteBufferEncode.get(bArr2);
        return bArr2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x005c, code lost:
    
        r20 = r3;
        r22 = r6;
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void encodeUTF8(java.nio.charset.CharsetEncoder r24, io.ktor.utils.io.core.ByteReadPacket r25, io.ktor.utils.io.core.Output r26) {
        /*
            Method dump skipped, instruction units count: 639
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.CharsetJVMKt.encodeUTF8(java.nio.charset.CharsetEncoder, io.ktor.utils.io.core.ByteReadPacket, io.ktor.utils.io.core.Output):void");
    }

    public static final Charset getCharset(CharsetEncoder charsetEncoder) {
        return charsetEncoder.charset();
    }

    public static final String getName(Charset charset) {
        return charset.name();
    }

    private static final void throwExceptionWrapped(CoderResult coderResult) throws CharacterCodingException {
        try {
            coderResult.throwException();
        } catch (java.nio.charset.MalformedInputException e5) {
            String message = e5.getMessage();
            if (message == null) {
                message = "Failed to decode bytes";
            }
            throw new MalformedInputException(message);
        }
    }

    public static final Charset getCharset(CharsetDecoder charsetDecoder) {
        return charsetDecoder.charset();
    }
}
