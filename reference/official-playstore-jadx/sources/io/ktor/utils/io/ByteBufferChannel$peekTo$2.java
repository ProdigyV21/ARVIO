package io.ktor.utils.io;

import io.ktor.utils.io.bits.MemoryJvmKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "nioBuffer", "Lx6/t0;", "invoke", "(Ljava/nio/ByteBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ByteBufferChannel$peekTo$2 extends r implements l<ByteBuffer, t0> {
    final /* synthetic */ i0 $bytesCopied;
    final /* synthetic */ ByteBuffer $destination;
    final /* synthetic */ long $destinationOffset;
    final /* synthetic */ long $max;
    final /* synthetic */ long $offset;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$peekTo$2(long j10, long j11, ByteBuffer byteBuffer, long j12, i0 i0Var) {
        super(1);
        this.$offset = j10;
        this.$max = j11;
        this.$destination = byteBuffer;
        this.$destinationOffset = j12;
        this.$bytesCopied = i0Var;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ByteBuffer) obj);
        return t0.f22605a;
    }

    public final void invoke(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() > this.$offset) {
            ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
            byteBufferDuplicate.position(byteBufferDuplicate.position() + ((int) this.$offset));
            int iLimit = byteBufferDuplicate.limit();
            byteBufferDuplicate.limit((int) Math.min(byteBufferDuplicate.limit(), Math.min(this.$max, ((long) this.$destination.limit()) - this.$destinationOffset) + this.$offset));
            this.$bytesCopied.f19744i = byteBufferDuplicate.remaining();
            MemoryJvmKt.m6767copyToSG11BkQ(byteBufferDuplicate, this.$destination, (int) this.$destinationOffset);
            byteBufferDuplicate.limit(iLimit);
        }
    }
}
