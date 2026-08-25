package io.ktor.utils.io.nio;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001a,\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a\u0019\u0010\u0007\u001a\u00020\n*\u00020\u00002\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\u000b\u001a\u0019\u0010\u000f\u001a\u00020\u0006*\u00020\f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0019\u0010\u0011\u001a\u00020\u0006*\u00020\f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u0010\u001a\u0019\u0010\u0012\u001a\u00020\u0006*\u00020\f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0010\u001a#\u0010\u0015\u001a\u00020\u0006*\u00020\f2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001b\u0010\u001a\u001a\u00020\u0019*\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a3\u0010\u001a\u001a\u00020\u0019*\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00192\b\b\u0002\u0010\u001f\u001a\u00020\u0019ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010!\u001a\u001b\u0010\"\u001a\u00020\u0019*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\"\u0010#\u001a3\u0010\"\u001a\u00020\u0019*\u00020\u00002\u0006\u0010$\u001a\u00020\u001c2\b\b\u0002\u0010%\u001a\u00020\u00192\b\b\u0002\u0010\u001f\u001a\u00020\u0019ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006("}, d2 = {"Ljava/nio/channels/WritableByteChannel;", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "Lio/ktor/utils/io/core/ByteReadPacket;", "writePacket", "(Ljava/nio/channels/WritableByteChannel;Lr7/l;)Lio/ktor/utils/io/core/ByteReadPacket;", TtmlNode.TAG_P, "", "(Ljava/nio/channels/WritableByteChannel;Lio/ktor/utils/io/core/ByteReadPacket;)Z", "Ljava/nio/channels/ReadableByteChannel;", "", "n", "readPacketExact", "(Ljava/nio/channels/ReadableByteChannel;J)Lio/ktor/utils/io/core/ByteReadPacket;", "readPacketAtLeast", "readPacketAtMost", "min", "max", "readPacketImpl", "(Ljava/nio/channels/ReadableByteChannel;JJ)Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/core/Buffer;", "buffer", "", "read", "(Ljava/nio/channels/ReadableByteChannel;Lio/ktor/utils/io/core/Buffer;)I", "Lio/ktor/utils/io/bits/Memory;", "destination", "destinationOffset", "maxLength", "read-UAd2zVI", "(Ljava/nio/channels/ReadableByteChannel;Ljava/nio/ByteBuffer;II)I", "write", "(Ljava/nio/channels/WritableByteChannel;Lio/ktor/utils/io/core/Buffer;)I", "source", "sourceOffset", "write-UAd2zVI", "(Ljava/nio/channels/WritableByteChannel;Ljava/nio/ByteBuffer;II)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChannelsKt {
    @e
    public static final int read(ReadableByteChannel readableByteChannel, Buffer buffer) throws IOException {
        if (buffer.getLimit() - buffer.getWritePosition() == 0) {
            return 0;
        }
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int i10 = readableByteChannel.read(MemoryJvmKt.sliceSafe(memory, writePosition, buffer.getLimit() - writePosition));
        if (i10 == -1) {
            return -1;
        }
        buffer.commitWritten(i10);
        return i10;
    }

    /* JADX INFO: renamed from: read-UAd2zVI, reason: not valid java name */
    public static final int m7012readUAd2zVI(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, int i10, int i11) {
        return readableByteChannel.read(MemoryJvmKt.sliceSafe(byteBuffer, i10, i11));
    }

    /* JADX INFO: renamed from: read-UAd2zVI$default, reason: not valid java name */
    public static /* synthetic */ int m7013readUAd2zVI$default(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = byteBuffer.limit() - i10;
        }
        return m7012readUAd2zVI(readableByteChannel, byteBuffer, i10, i11);
    }

    public static final ByteReadPacket readPacketAtLeast(ReadableByteChannel readableByteChannel, long j10) {
        return readPacketImpl(readableByteChannel, j10, Long.MAX_VALUE);
    }

    public static final ByteReadPacket readPacketAtMost(ReadableByteChannel readableByteChannel, long j10) {
        return readPacketImpl(readableByteChannel, 1L, j10);
    }

    public static final ByteReadPacket readPacketExact(ReadableByteChannel readableByteChannel, long j10) {
        return readPacketImpl(readableByteChannel, j10, j10);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        return new io.ktor.utils.io.core.ByteReadPacket(r9, r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final io.ktor.utils.io.core.ByteReadPacket readPacketImpl(java.nio.channels.ReadableByteChannel r18, long r19, long r21) {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.nio.ChannelsKt.readPacketImpl(java.nio.channels.ReadableByteChannel, long, long):io.ktor.utils.io.core.ByteReadPacket");
    }

    @e
    public static final int write(WritableByteChannel writableByteChannel, Buffer buffer) throws IOException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        int iWrite = writableByteChannel.write(MemoryJvmKt.sliceSafe(memory, readPosition, buffer.getWritePosition() - readPosition));
        buffer.discardExact(iWrite);
        return iWrite;
    }

    /* JADX INFO: renamed from: write-UAd2zVI, reason: not valid java name */
    public static final int m7014writeUAd2zVI(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer, int i10, int i11) {
        return writableByteChannel.write(MemoryJvmKt.sliceSafe(byteBuffer, i10, i11));
    }

    /* JADX INFO: renamed from: write-UAd2zVI$default, reason: not valid java name */
    public static /* synthetic */ int m7015writeUAd2zVI$default(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = byteBuffer.limit() - i10;
        }
        return m7014writeUAd2zVI(writableByteChannel, byteBuffer, i10, i11);
    }

    public static final ByteReadPacket writePacket(WritableByteChannel writableByteChannel, l<? super BytePacketBuilder, t0> lVar) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            lVar.invoke(bytePacketBuilder);
            ByteReadPacket byteReadPacketBuild = bytePacketBuilder.build();
            try {
                if (writePacket(writableByteChannel, byteReadPacketBuild)) {
                    return null;
                }
                return byteReadPacketBuild;
            } catch (Throwable th) {
                byteReadPacketBuild.release();
                throw th;
            }
        } catch (Throwable th2) {
            bytePacketBuilder.release();
            throw th2;
        }
    }

    public static final boolean writePacket(WritableByteChannel writableByteChannel, ByteReadPacket byteReadPacket) {
        int iWrite;
        do {
            try {
                ChunkBuffer chunkBufferPrepareRead = byteReadPacket.prepareRead(1);
                if (chunkBufferPrepareRead != null) {
                    int readPosition = chunkBufferPrepareRead.getReadPosition();
                    try {
                        ByteBuffer memory = chunkBufferPrepareRead.getMemory();
                        int readPosition2 = chunkBufferPrepareRead.getReadPosition();
                        int writePosition = chunkBufferPrepareRead.getWritePosition() - readPosition2;
                        ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, readPosition2, writePosition);
                        iWrite = writableByteChannel.write(byteBufferM6756slice87lwejk);
                        if (byteBufferM6756slice87lwejk.limit() == writePosition) {
                            chunkBufferPrepareRead.discardExact(byteBufferM6756slice87lwejk.position());
                            int readPosition3 = chunkBufferPrepareRead.getReadPosition();
                            if (readPosition3 >= readPosition) {
                                if (readPosition3 == chunkBufferPrepareRead.getWritePosition()) {
                                    byteReadPacket.ensureNext(chunkBufferPrepareRead);
                                } else {
                                    byteReadPacket.setHeadPosition(readPosition3);
                                }
                                if (byteReadPacket.getEndOfInput()) {
                                    return true;
                                }
                            } else {
                                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                            }
                        } else {
                            throw new IllegalStateException("Buffer's limit change is not allowed");
                        }
                    } catch (Throwable th) {
                        int readPosition4 = chunkBufferPrepareRead.getReadPosition();
                        if (readPosition4 >= readPosition) {
                            if (readPosition4 == chunkBufferPrepareRead.getWritePosition()) {
                                byteReadPacket.ensureNext(chunkBufferPrepareRead);
                            } else {
                                byteReadPacket.setHeadPosition(readPosition4);
                            }
                            throw th;
                        }
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    }
                } else {
                    StringsKt.prematureEndOfStream(1);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th2) {
                byteReadPacket.release();
                throw th2;
            }
        } while (iWrite != 0);
        return false;
    }
}
