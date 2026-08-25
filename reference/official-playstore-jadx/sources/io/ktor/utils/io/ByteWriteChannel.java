package io.ktor.utils.io;

import androidx.media3.exoplayer.upstream.CmcdData;
import d7.d;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\bf\u0018\u00002\u00020\u0001J+\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\tH¦@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\nJ\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u000bH¦@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\fJ+\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\bJ\u001b\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u000bH¦@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\fJ-\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r0\u0010H&¢\u0006\u0004\b\u0007\u0010\u0012J1\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r0\u0010H¦@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0016\u001a\u00020\r2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00150\u0010H¦@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J<\u0010\u001d\u001a\u00020\r2'\u0010\u001c\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018¢\u0006\u0002\b\u001bH§@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010!\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u001fH¦@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u001b\u0010%\u001a\u00020\r2\u0006\u0010$\u001a\u00020#H¦@ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u001b\u0010(\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b(\u0010)J\u001b\u0010,\u001a\u00020\r2\u0006\u0010+\u001a\u00020*H¦@ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u001b\u00100\u001a\u00020\r2\u0006\u0010/\u001a\u00020.H¦@ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u001b\u00104\u001a\u00020\r2\u0006\u00103\u001a\u000202H¦@ø\u0001\u0000¢\u0006\u0004\b4\u00105J\u001b\u00108\u001a\u00020\r2\u0006\u00107\u001a\u000206H¦@ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u0019\u0010<\u001a\u00020\u00152\b\u0010;\u001a\u0004\u0018\u00010:H&¢\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\rH&¢\u0006\u0004\b>\u0010?J\u0013\u0010@\u001a\u00020\rH¦@ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ\u001b\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020BH¦@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010CJ1\u0010\u000e\u001a\u00020\r2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u0004H¦@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bH\u0010IR\u0014\u0010L\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0014\u0010M\u001a\u00020\u00158&X¦\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0014\u0010P\u001a\u00020\u00158&X¦\u0004¢\u0006\u0006\u001a\u0004\bO\u0010NR\u0014\u0010S\u001a\u00020#8&X¦\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0016\u0010V\u001a\u0004\u0018\u00010:8&X¦\u0004¢\u0006\u0006\u001a\u0004\bT\u0010U\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006W"}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "", "", "src", "", "offset", "length", "writeAvailable", "([BIILd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Ld7/d;)Ljava/lang/Object;", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "writeFully", "min", "Lkotlin/Function1;", "block", "(ILr7/l;)I", "write", "(ILr7/l;Ld7/d;)Ljava/lang/Object;", "", "writeWhile", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lio/ktor/utils/io/WriterSuspendSession;", "Ld7/d;", "Lx6/n;", "visitor", "writeSuspendSession", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/ByteReadPacket;", "packet", "writePacket", "(Lio/ktor/utils/io/core/ByteReadPacket;Ld7/d;)Ljava/lang/Object;", "", CmcdData.STREAM_TYPE_LIVE, "writeLong", "(JLd7/d;)Ljava/lang/Object;", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "writeInt", "(ILd7/d;)Ljava/lang/Object;", "", CmcdData.STREAMING_FORMAT_SS, "writeShort", "(SLd7/d;)Ljava/lang/Object;", "", "b", "writeByte", "(BLd7/d;)Ljava/lang/Object;", "", "d", "writeDouble", "(DLd7/d;)Ljava/lang/Object;", "", "f", "writeFloat", "(FLd7/d;)Ljava/lang/Object;", "", "cause", "close", "(Ljava/lang/Throwable;)Z", "flush", "()V", "awaitFreeSpace", "(Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Buffer;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/bits/Memory;", "memory", "startIndex", "endIndex", "writeFully-JT6ljtQ", "(Ljava/nio/ByteBuffer;IILd7/d;)Ljava/lang/Object;", "getAvailableForWrite", "()I", "availableForWrite", "isClosedForWrite", "()Z", "getAutoFlush", "autoFlush", "getTotalBytesWritten", "()J", "totalBytesWritten", "getClosedCause", "()Ljava/lang/Throwable;", "closedCause", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ByteWriteChannel {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object write$default(ByteWriteChannel byteWriteChannel, int i10, l lVar, d dVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: write");
            }
            if ((i11 & 1) != 0) {
                i10 = 1;
            }
            return byteWriteChannel.write(i10, lVar, dVar);
        }

        public static /* synthetic */ int writeAvailable$default(ByteWriteChannel byteWriteChannel, int i10, l lVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeAvailable");
            }
            if ((i11 & 1) != 0) {
                i10 = 1;
            }
            return byteWriteChannel.writeAvailable(i10, (l<? super ByteBuffer, t0>) lVar);
        }
    }

    Object awaitFreeSpace(d<? super t0> dVar);

    boolean close(Throwable cause);

    void flush();

    boolean getAutoFlush();

    int getAvailableForWrite();

    Throwable getClosedCause();

    long getTotalBytesWritten();

    boolean isClosedForWrite();

    Object write(int i10, l<? super ByteBuffer, t0> lVar, d<? super t0> dVar);

    int writeAvailable(int min, l<? super ByteBuffer, t0> block);

    Object writeAvailable(ChunkBuffer chunkBuffer, d<? super Integer> dVar);

    Object writeAvailable(ByteBuffer byteBuffer, d<? super Integer> dVar);

    Object writeAvailable(byte[] bArr, int i10, int i11, d<? super Integer> dVar);

    Object writeByte(byte b10, d<? super t0> dVar);

    Object writeDouble(double d4, d<? super t0> dVar);

    Object writeFloat(float f10, d<? super t0> dVar);

    Object writeFully(Buffer buffer, d<? super t0> dVar);

    Object writeFully(ByteBuffer byteBuffer, d<? super t0> dVar);

    Object writeFully(byte[] bArr, int i10, int i11, d<? super t0> dVar);

    /* JADX INFO: renamed from: writeFully-JT6ljtQ */
    Object mo6736writeFullyJT6ljtQ(ByteBuffer byteBuffer, int i10, int i11, d<? super t0> dVar);

    Object writeInt(int i10, d<? super t0> dVar);

    Object writeLong(long j10, d<? super t0> dVar);

    Object writePacket(ByteReadPacket byteReadPacket, d<? super t0> dVar);

    Object writeShort(short s10, d<? super t0> dVar);

    @e
    Object writeSuspendSession(p<? super WriterSuspendSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar);

    Object writeWhile(l<? super ByteBuffer, Boolean> lVar, d<? super t0> dVar);
}
