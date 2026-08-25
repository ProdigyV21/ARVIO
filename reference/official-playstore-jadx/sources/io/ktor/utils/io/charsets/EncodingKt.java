package io.ktor.utils.io.charsets;

import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.internal.CharArraySequence;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u001a7\u0010\n\u001a\u00020\t*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a3\u0010\r\u001a\u00020\f*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a1\u0010\n\u001a\u00020\u000f*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u0010\u001a\u001d\u0010\u0011\u001a\u00020\u000f*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012\u001a5\u0010\n\u001a\u00020\t*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u0014\u001a'\u0010\u001a\u001a\u00020\u0019*\u00060\u0015j\u0002`\u00162\u0006\u0010\u0003\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0004¢\u0006\u0004\b\u001a\u0010\u001b\u001a7\u0010\u001d\u001a\u00020\u0004*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u001cH\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0013\u0010 \u001a\u00020\u001f*\u00020\u0017H\u0000¢\u0006\u0004\b \u0010!\u001a\u001f\u0010\"\u001a\u00020\u0004*\u00060\u0000j\u0002`\u00012\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\"\u0010#\u001a7\u0010%\u001a\u00020\u0004*\u00060\u0000j\u0002`\u00012\u0006\u0010$\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b%\u0010&¨\u0006'"}, d2 = {"Ljava/nio/charset/CharsetEncoder;", "Lio/ktor/utils/io/charsets/CharsetEncoder;", "", "input", "", "fromIndex", "toIndex", "Lio/ktor/utils/io/core/Output;", "dst", "Lx6/t0;", "encode", "(Ljava/nio/charset/CharsetEncoder;Ljava/lang/CharSequence;IILio/ktor/utils/io/core/Output;)V", "", "encodeToByteArrayImpl", "(Ljava/nio/charset/CharsetEncoder;Ljava/lang/CharSequence;II)[B", "Lio/ktor/utils/io/core/ByteReadPacket;", "(Ljava/nio/charset/CharsetEncoder;Ljava/lang/CharSequence;II)Lio/ktor/utils/io/core/ByteReadPacket;", "encodeUTF8", "(Ljava/nio/charset/CharsetEncoder;Lio/ktor/utils/io/core/ByteReadPacket;)Lio/ktor/utils/io/core/ByteReadPacket;", "", "(Ljava/nio/charset/CharsetEncoder;[CIILio/ktor/utils/io/core/Output;)V", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "Lio/ktor/utils/io/core/Input;", "max", "", "decode", "(Ljava/nio/charset/CharsetDecoder;Lio/ktor/utils/io/core/Input;I)Ljava/lang/String;", "Lio/ktor/utils/io/core/Buffer;", "encodeArrayImpl", "(Ljava/nio/charset/CharsetEncoder;[CIILio/ktor/utils/io/core/Buffer;)I", "", "sizeEstimate", "(Lio/ktor/utils/io/core/Input;)J", "encodeCompleteImpl", "(Ljava/nio/charset/CharsetEncoder;Lio/ktor/utils/io/core/Output;)I", "destination", "encodeToImpl", "(Ljava/nio/charset/CharsetEncoder;Lio/ktor/utils/io/core/Output;Ljava/lang/CharSequence;II)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EncodingKt {
    public static final String decode(CharsetDecoder charsetDecoder, Input input, int i10) {
        StringBuilder sb2 = new StringBuilder((int) Math.min(i10, sizeEstimate(input)));
        CharsetJVMKt.decode(charsetDecoder, input, sb2, i10);
        return sb2.toString();
    }

    public static /* synthetic */ String decode$default(CharsetDecoder charsetDecoder, Input input, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        return decode(charsetDecoder, input, i10);
    }

    @e
    public static final void encode(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11, Output output) {
        encodeToImpl(charsetEncoder, output, charSequence, i10, i11);
    }

    public static /* synthetic */ ByteReadPacket encode$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = charSequence.length();
        }
        return encode(charsetEncoder, charSequence, i10, i11);
    }

    public static final int encodeArrayImpl(CharsetEncoder charsetEncoder, char[] cArr, int i10, int i11, Buffer buffer) {
        int i12 = i11 - i10;
        return CharsetJVMKt.encodeImpl(charsetEncoder, new CharArraySequence(cArr, i10, i12), 0, i12, buffer);
    }

    private static final int encodeCompleteImpl(CharsetEncoder charsetEncoder, Output output) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        int i10 = 1;
        int limit = 0;
        while (true) {
            try {
                int limit2 = chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition();
                i10 = CharsetJVMKt.encodeComplete(charsetEncoder, chunkBufferPrepareWriteHead) ? 0 : i10 + 1;
                limit += limit2 - (chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                if (i10 <= 0) {
                    return limit;
                }
                chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    @e
    public static final byte[] encodeToByteArrayImpl(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11) {
        return CharsetJVMKt.encodeToByteArray(charsetEncoder, charSequence, i10, i11);
    }

    public static /* synthetic */ byte[] encodeToByteArrayImpl$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = charSequence.length();
        }
        return encodeToByteArrayImpl(charsetEncoder, charSequence, i10, i11);
    }

    public static final int encodeToImpl(CharsetEncoder charsetEncoder, Output output, CharSequence charSequence, int i10, int i11) {
        if (i10 >= i11) {
            return 0;
        }
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        int limit = 0;
        while (true) {
            try {
                int limit2 = chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition();
                int iEncodeImpl = CharsetJVMKt.encodeImpl(charsetEncoder, charSequence, i10, i11, chunkBufferPrepareWriteHead);
                if (iEncodeImpl < 0) {
                    throw new IllegalStateException("Check failed.");
                }
                i10 += iEncodeImpl;
                limit += limit2 - (chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                int i12 = i10 >= i11 ? 0 : iEncodeImpl == 0 ? 8 : 1;
                if (i12 <= 0) {
                    output.afterHeadWrite();
                    return limit + encodeCompleteImpl(charsetEncoder, output);
                }
                chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i12, chunkBufferPrepareWriteHead);
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ByteReadPacket encodeUTF8(CharsetEncoder charsetEncoder, ByteReadPacket byteReadPacket) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            CharsetJVMKt.encodeUTF8(charsetEncoder, byteReadPacket, bytePacketBuilder);
            return bytePacketBuilder.build();
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final long sizeEstimate(Input input) {
        return input instanceof ByteReadPacket ? input.getRemaining() : Math.max(input.getRemaining(), 16L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ByteReadPacket encode(CharsetEncoder charsetEncoder, CharSequence charSequence, int i10, int i11) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            encodeToImpl(charsetEncoder, bytePacketBuilder, charSequence, i10, i11);
            return bytePacketBuilder.build();
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final void encode(CharsetEncoder charsetEncoder, char[] cArr, int i10, int i11, Output output) {
        if (i10 >= i11) {
            return;
        }
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        while (true) {
            try {
                int iEncodeArrayImpl = encodeArrayImpl(charsetEncoder, cArr, i10, i11, chunkBufferPrepareWriteHead);
                if (iEncodeArrayImpl < 0) {
                    throw new IllegalStateException("Check failed.");
                }
                i10 += iEncodeArrayImpl;
                int i12 = i10 >= i11 ? 0 : iEncodeArrayImpl == 0 ? 8 : 1;
                if (i12 > 0) {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i12, chunkBufferPrepareWriteHead);
                } else {
                    output.afterHeadWrite();
                    encodeCompleteImpl(charsetEncoder, output);
                    return;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
    }
}
