package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aE\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a+\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\t\u0010\u000e\u001a1\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"", "array", "", "offset", "length", "Lkotlin/Function1;", "Lx6/t0;", "block", "Lio/ktor/utils/io/core/ByteReadPacket;", "ByteReadPacket", "([BIILr7/l;)Lio/ktor/utils/io/core/ByteReadPacket;", "Ljava/nio/ByteBuffer;", "bb", "release", "(Ljava/nio/ByteBuffer;Lr7/l;)Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "poolFor", "(Ljava/nio/ByteBuffer;Lr7/l;)Lio/ktor/utils/io/pool/ObjectPool;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteReadPacketExtensionsKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.core.ByteReadPacketExtensionsKt$ByteReadPacket$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "it", "Lx6/t0;", "invoke", "(Ljava/nio/ByteBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<ByteBuffer, t0> {
        final /* synthetic */ byte[] $array;
        final /* synthetic */ l<byte[], t0> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super byte[], t0> lVar, byte[] bArr) {
            super(1);
            this.$block = lVar;
            this.$array = bArr;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ByteBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(ByteBuffer byteBuffer) {
            this.$block.invoke(this.$array);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.core.ByteReadPacketExtensionsKt$ByteReadPacket$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "it", "Lx6/t0;", "invoke", "(Ljava/nio/ByteBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<ByteBuffer, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(ByteBuffer byteBuffer) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ByteBuffer) obj);
            return t0.f22605a;
        }
    }

    public static final ByteReadPacket ByteReadPacket(byte[] bArr, int i10, int i11, l<? super byte[], t0> lVar) {
        return ByteReadPacket(ByteBuffer.wrap(bArr, i10, i11), new AnonymousClass1(lVar, bArr));
    }

    public static /* synthetic */ ByteReadPacket ByteReadPacket$default(ByteBuffer byteBuffer, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return ByteReadPacket(byteBuffer, lVar);
    }

    private static final ObjectPool<ChunkBuffer> poolFor(ByteBuffer byteBuffer, l<? super ByteBuffer, t0> lVar) {
        return new SingleByteBufferPool(byteBuffer, lVar);
    }

    public static final ByteReadPacket ByteReadPacket(ByteBuffer byteBuffer, l<? super ByteBuffer, t0> lVar) {
        ObjectPool<ChunkBuffer> objectPoolPoolFor = poolFor(byteBuffer, lVar);
        ChunkBuffer chunkBufferBorrow = objectPoolPoolFor.borrow();
        chunkBufferBorrow.resetForRead();
        return new ByteReadPacket(chunkBufferBorrow, objectPoolPoolFor);
    }

    public static /* synthetic */ ByteReadPacket ByteReadPacket$default(byte[] bArr, int i10, int i11, l lVar, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length;
        }
        return ByteReadPacket(ByteBuffer.wrap(bArr, i10, i11), new AnonymousClass1(lVar, bArr));
    }
}
