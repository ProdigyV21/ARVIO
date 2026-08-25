package io.ktor.utils.io;

import d7.d;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.e;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\bf\u0018\u0000 ^2\u00020\u0001:\u0001^J-\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H&¢\u0006\u0004\b\b\u0010\tJ+\u0010\b\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u000eJ\u001b\u0010\b\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u000fH¦@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0010J\u001b\u0010\b\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0011J+\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000eJ#\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0014J\u001b\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0011J\u001b\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u0019H¦@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u0019H¦@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001eJ\u0013\u0010!\u001a\u00020 H¦@ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001eJ\u0013\u0010#\u001a\u00020\"H¦@ø\u0001\u0000¢\u0006\u0004\b#\u0010\u001eJ\u0013\u0010%\u001a\u00020$H¦@ø\u0001\u0000¢\u0006\u0004\b%\u0010\u001eJ\u0013\u0010'\u001a\u00020&H¦@ø\u0001\u0000¢\u0006\u0004\b'\u0010\u001eJ\u0013\u0010)\u001a\u00020(H¦@ø\u0001\u0000¢\u0006\u0004\b)\u0010\u001eJ(\u0010-\u001a\u00020\u00062\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b+H'¢\u0006\u0004\b-\u0010.J<\u00102\u001a\u00020\u00062'\u0010,\u001a#\b\u0001\u0012\u0004\u0012\u000200\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000601\u0012\u0006\u0012\u0004\u0018\u00010\u00010/¢\u0006\u0002\b+H§@ø\u0001\u0000¢\u0006\u0004\b2\u00103J.\u00107\u001a\u00028\u0000\"\u0004\b\u0000\u001042\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\b+H'¢\u0006\u0004\b7\u00108JB\u0010:\u001a\u00028\u0000\"\u0004\b\u0000\u001042'\u00106\u001a#\b\u0001\u0012\u0004\u0012\u000209\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000001\u0012\u0006\u0012\u0004\u0018\u00010\u00010/¢\u0006\u0002\b+H§@ø\u0001\u0000¢\u0006\u0004\b:\u00103J1\u0010?\u001a\u00020$\"\f\b\u0000\u0010=*\u00060;j\u0002`<2\u0006\u0010>\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b?\u0010@J\u001d\u0010B\u001a\u0004\u0018\u00010A2\u0006\u0010\u001a\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J1\u0010C\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H¦@ø\u0001\u0000¢\u0006\u0004\bC\u0010DJ\u0019\u0010G\u001a\u00020$2\b\u0010F\u001a\u0004\u0018\u00010EH&¢\u0006\u0004\bG\u0010HJ\u001b\u0010J\u001a\u00020\u00192\u0006\u0010I\u001a\u00020\u0019H¦@ø\u0001\u0000¢\u0006\u0004\bJ\u0010\u001cJG\u0010P\u001a\u00020\u00192\u0006\u0010L\u001a\u00020K2\u0006\u0010M\u001a\u00020\u00192\b\b\u0002\u0010\f\u001a\u00020\u00192\b\b\u0002\u0010\u0003\u001a\u00020\u00192\b\b\u0002\u0010I\u001a\u00020\u0019H¦@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bN\u0010OJ\u0013\u0010Q\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0004\bQ\u0010\u001eR\u0014\u0010T\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0014\u0010U\u001a\u00020$8&X¦\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0014\u0010W\u001a\u00020$8&X¦\u0004¢\u0006\u0006\u001a\u0004\bW\u0010VR\u0016\u0010Z\u001a\u0004\u0018\u00010E8&X¦\u0004¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0014\u0010]\u001a\u00020\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006_"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "", "", "min", "Lkotlin/Function1;", "Ljava/nio/ByteBuffer;", "Lx6/t0;", "block", "readAvailable", "(ILr7/l;)I", "", "dst", "offset", "length", "([BIILd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Ld7/d;)Ljava/lang/Object;", "(Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "readFully", "n", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ILd7/d;)Ljava/lang/Object;", ContentDisposition.Parameters.Size, "Lio/ktor/utils/io/core/ByteReadPacket;", "readPacket", "(ILd7/d;)Ljava/lang/Object;", "", "limit", "readRemaining", "(JLd7/d;)Ljava/lang/Object;", "readLong", "(Ld7/d;)Ljava/lang/Object;", "readInt", "", "readShort", "", "readByte", "", "readBoolean", "", "readDouble", "", "readFloat", "Lio/ktor/utils/io/ReadSession;", "Lx6/n;", "consumer", "readSession", "(Lr7/l;)V", "Lkotlin/Function2;", "Lio/ktor/utils/io/SuspendableReadSession;", "Ld7/d;", "readSuspendableSession", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "R", "Lio/ktor/utils/io/LookAheadSession;", "visitor", "lookAhead", "(Lr7/l;)Ljava/lang/Object;", "Lio/ktor/utils/io/LookAheadSuspendSession;", "lookAheadSuspend", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "out", "readUTF8LineTo", "(Ljava/lang/Appendable;ILd7/d;)Ljava/lang/Object;", "", "readUTF8Line", "read", "(ILr7/l;Ld7/d;)Ljava/lang/Object;", "", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "max", "discard", "Lio/ktor/utils/io/bits/Memory;", "destination", "destinationOffset", "peekTo-lBXzO7A", "(Ljava/nio/ByteBuffer;JJJJLd7/d;)Ljava/lang/Object;", "peekTo", "awaitContent", "getAvailableForRead", "()I", "availableForRead", "isClosedForRead", "()Z", "isClosedForWrite", "getClosedCause", "()Ljava/lang/Throwable;", "closedCause", "getTotalBytesRead", "()J", "totalBytesRead", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ByteReadChannel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\t\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel$Companion;", "", "<init>", "()V", "Lio/ktor/utils/io/ByteReadChannel;", "Empty$delegate", "Lx6/s;", "getEmpty", "()Lio/ktor/utils/io/ByteReadChannel;", "Empty", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: renamed from: Empty$delegate, reason: from kotlin metadata */
        private static final s<ByteChannel> Empty = new i0(ByteReadChannel$Companion$Empty$2.INSTANCE);

        private Companion() {
        }

        public final ByteReadChannel getEmpty() {
            return (ByteReadChannel) Empty.getValue();
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX INFO: renamed from: peekTo-lBXzO7A$default, reason: not valid java name */
        public static /* synthetic */ Object m6738peekTolBXzO7A$default(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, long j10, long j11, long j12, long j13, d dVar, int i10, Object obj) {
            if (obj == null) {
                return byteReadChannel.mo6735peekTolBXzO7A(byteBuffer, j10, (i10 & 4) != 0 ? 0L : j11, (i10 & 8) != 0 ? 1L : j12, (i10 & 16) != 0 ? Long.MAX_VALUE : j13, dVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: peekTo-lBXzO7A");
        }

        public static /* synthetic */ Object read$default(ByteReadChannel byteReadChannel, int i10, l lVar, d dVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: read");
            }
            if ((i11 & 1) != 0) {
                i10 = 1;
            }
            return byteReadChannel.read(i10, lVar, dVar);
        }

        public static /* synthetic */ int readAvailable$default(ByteReadChannel byteReadChannel, int i10, l lVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readAvailable");
            }
            if ((i11 & 1) != 0) {
                i10 = 1;
            }
            return byteReadChannel.readAvailable(i10, (l<? super ByteBuffer, t0>) lVar);
        }

        public static /* synthetic */ Object readRemaining$default(ByteReadChannel byteReadChannel, long j10, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readRemaining");
            }
            if ((i10 & 1) != 0) {
                j10 = Long.MAX_VALUE;
            }
            return byteReadChannel.readRemaining(j10, dVar);
        }
    }

    Object awaitContent(d<? super t0> dVar);

    boolean cancel(Throwable cause);

    Object discard(long j10, d<? super Long> dVar);

    int getAvailableForRead();

    Throwable getClosedCause();

    long getTotalBytesRead();

    boolean isClosedForRead();

    boolean isClosedForWrite();

    @e
    <R> R lookAhead(l<? super LookAheadSession, ? extends R> visitor);

    @e
    <R> Object lookAheadSuspend(p<? super LookAheadSuspendSession, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar);

    /* JADX INFO: renamed from: peekTo-lBXzO7A */
    Object mo6735peekTolBXzO7A(ByteBuffer byteBuffer, long j10, long j11, long j12, long j13, d<? super Long> dVar);

    Object read(int i10, l<? super ByteBuffer, t0> lVar, d<? super t0> dVar);

    int readAvailable(int min, l<? super ByteBuffer, t0> block);

    Object readAvailable(ChunkBuffer chunkBuffer, d<? super Integer> dVar);

    Object readAvailable(ByteBuffer byteBuffer, d<? super Integer> dVar);

    Object readAvailable(byte[] bArr, int i10, int i11, d<? super Integer> dVar);

    Object readBoolean(d<? super Boolean> dVar);

    Object readByte(d<? super Byte> dVar);

    Object readDouble(d<? super Double> dVar);

    Object readFloat(d<? super Float> dVar);

    Object readFully(ChunkBuffer chunkBuffer, int i10, d<? super t0> dVar);

    Object readFully(ByteBuffer byteBuffer, d<? super Integer> dVar);

    Object readFully(byte[] bArr, int i10, int i11, d<? super t0> dVar);

    Object readInt(d<? super Integer> dVar);

    Object readLong(d<? super Long> dVar);

    Object readPacket(int i10, d<? super ByteReadPacket> dVar);

    Object readRemaining(long j10, d<? super ByteReadPacket> dVar);

    @e
    void readSession(l<? super ReadSession, t0> consumer);

    Object readShort(d<? super Short> dVar);

    @e
    Object readSuspendableSession(p<? super SuspendableReadSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar);

    Object readUTF8Line(int i10, d<? super String> dVar);

    <A extends Appendable> Object readUTF8LineTo(A a10, int i10, d<? super Boolean> dVar);
}
