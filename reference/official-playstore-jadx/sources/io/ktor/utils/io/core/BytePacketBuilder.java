package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0004¢\u0006\u0004\b\b\u0010\tJ-\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0014\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0004\b\u0014\u0010\u0017J)\u0010\u0014\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0014\u0010\u001aJ\r\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 R \u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028@X\u0081\u0004¢\u0006\f\u0012\u0004\b#\u0010\t\u001a\u0004\b!\u0010\"R\u0011\u0010'\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0011\u0010)\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b+\u0010*\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lio/ktor/utils/io/core/BytePacketBuilder;", "Lio/ktor/utils/io/core/Output;", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "pool", "<init>", "(Lio/ktor/utils/io/pool/ObjectPool;)V", "Lx6/t0;", "closeDestination", "()V", "Lio/ktor/utils/io/bits/Memory;", "source", "", "offset", "length", "flush-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "flush", "", "value", "append", "(C)Lio/ktor/utils/io/core/BytePacketBuilder;", "", "(Ljava/lang/CharSequence;)Lio/ktor/utils/io/core/BytePacketBuilder;", "startIndex", "endIndex", "(Ljava/lang/CharSequence;II)Lio/ktor/utils/io/core/BytePacketBuilder;", "Lio/ktor/utils/io/core/ByteReadPacket;", "build", "()Lio/ktor/utils/io/core/ByteReadPacket;", "", "toString", "()Ljava/lang/String;", "get_pool", "()Lio/ktor/utils/io/pool/ObjectPool;", "get_pool$annotations", "_pool", "getSize", "()I", ContentDisposition.Parameters.Size, "", "isEmpty", "()Z", "isNotEmpty", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BytePacketBuilder extends Output {
    /* JADX WARN: Multi-variable type inference failed */
    public BytePacketBuilder() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ void get_pool$annotations() {
    }

    public final ByteReadPacket build() {
        int size = getSize();
        ChunkBuffer chunkBufferStealAll$ktor_io = stealAll$ktor_io();
        return chunkBufferStealAll$ktor_io == null ? ByteReadPacket.INSTANCE.getEmpty() : new ByteReadPacket(chunkBufferStealAll$ktor_io, size, getPool());
    }

    @Override // io.ktor.utils.io.core.Output
    public final void closeDestination() {
    }

    @Override // io.ktor.utils.io.core.Output
    /* JADX INFO: renamed from: flush-62zg_DM, reason: not valid java name */
    public final void mo6925flush62zg_DM(ByteBuffer source, int offset, int length) {
    }

    public final int getSize() {
        return get_size();
    }

    public final ObjectPool<ChunkBuffer> get_pool() {
        return getPool();
    }

    public final boolean isEmpty() {
        return get_size() == 0;
    }

    public final boolean isNotEmpty() {
        return get_size() > 0;
    }

    public String toString() {
        return "BytePacketBuilder[0x" + hashCode() + ']';
    }

    public /* synthetic */ BytePacketBuilder(ObjectPool objectPool, int i10, h hVar) {
        this((i10 & 1) != 0 ? ChunkBuffer.INSTANCE.getPool() : objectPool);
    }

    public BytePacketBuilder(ObjectPool<ChunkBuffer> objectPool) {
        super(objectPool);
    }

    @Override // io.ktor.utils.io.core.Output, java.lang.Appendable
    public BytePacketBuilder append(char value) {
        return (BytePacketBuilder) super.append(value);
    }

    @Override // io.ktor.utils.io.core.Output, java.lang.Appendable
    public BytePacketBuilder append(CharSequence value) {
        return (BytePacketBuilder) super.append(value);
    }

    @Override // io.ktor.utils.io.core.Output, java.lang.Appendable
    public BytePacketBuilder append(CharSequence value, int startIndex, int endIndex) {
        return (BytePacketBuilder) super.append(value, startIndex, endIndex);
    }
}
