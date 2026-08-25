package io.ktor.utils.io;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.j0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/SuspendableReadSession;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/SuspendableReadSession;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.utils.io.ByteChannelSequentialBase$peekTo$2", f = "ByteChannelSequential.kt", l = {823}, m = "invokeSuspend")
public final class ByteChannelSequentialBase$peekTo$2 extends j implements p<SuspendableReadSession, d<? super t0>, Object> {
    final /* synthetic */ j0 $bytesCopied;
    final /* synthetic */ ByteBuffer $destination;
    final /* synthetic */ long $destinationOffset;
    final /* synthetic */ long $max;
    final /* synthetic */ long $min;
    final /* synthetic */ long $offset;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$peekTo$2(long j10, long j11, j0 j0Var, long j12, ByteBuffer byteBuffer, long j13, d<? super ByteChannelSequentialBase$peekTo$2> dVar) {
        super(2, dVar);
        this.$min = j10;
        this.$offset = j11;
        this.$bytesCopied = j0Var;
        this.$max = j12;
        this.$destination = byteBuffer;
        this.$destinationOffset = j13;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        ByteChannelSequentialBase$peekTo$2 byteChannelSequentialBase$peekTo$2 = new ByteChannelSequentialBase$peekTo$2(this.$min, this.$offset, this.$bytesCopied, this.$max, this.$destination, this.$destinationOffset, dVar);
        byteChannelSequentialBase$peekTo$2.L$0 = obj;
        return byteChannelSequentialBase$peekTo$2;
    }

    @Override // r7.p
    public final Object invoke(SuspendableReadSession suspendableReadSession, d<? super t0> dVar) {
        return ((ByteChannelSequentialBase$peekTo$2) create(suspendableReadSession, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        SuspendableReadSession suspendableReadSession;
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            suspendableReadSession = (SuspendableReadSession) this.L$0;
            long j10 = this.$min + this.$offset;
            if (j10 > 4088) {
                j10 = 4088;
            }
            this.L$0 = suspendableReadSession;
            this.label = 1;
            Object objAwait = suspendableReadSession.await((int) j10, this);
            a aVar = a.f15033i;
            if (objAwait == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            suspendableReadSession = (SuspendableReadSession) this.L$0;
            c.G(obj);
        }
        ChunkBuffer chunkBufferRequest = suspendableReadSession.request(1);
        if (chunkBufferRequest == null) {
            chunkBufferRequest = ChunkBuffer.INSTANCE.getEmpty();
        }
        if (chunkBufferRequest.getWritePosition() - chunkBufferRequest.getReadPosition() > this.$offset) {
            this.$bytesCopied.f19745i = Math.min(((long) (chunkBufferRequest.getWritePosition() - chunkBufferRequest.getReadPosition())) - this.$offset, Math.min(this.$max, ((long) this.$destination.limit()) - this.$destinationOffset));
            Memory.m6748copyToJT6ljtQ(chunkBufferRequest.getMemory(), this.$destination, this.$offset, this.$bytesCopied.f19745i, this.$destinationOffset);
        }
        return t0.f22605a;
    }
}
