package io.ktor.util;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.core.ByteReadPacket;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.util.ByteChannelsKt$split$1$1$2", f = "ByteChannels.kt", l = {28}, m = "invokeSuspend")
public final class ByteChannelsKt$split$1$1$2 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ ByteReadPacket $chunk;
    final /* synthetic */ ByteChannel $second;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelsKt$split$1$1$2(ByteChannel byteChannel, ByteReadPacket byteReadPacket, d<? super ByteChannelsKt$split$1$1$2> dVar) {
        super(2, dVar);
        this.$second = byteChannel;
        this.$chunk = byteReadPacket;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new ByteChannelsKt$split$1$1$2(this.$second, this.$chunk, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            ByteChannel byteChannel = this.$second;
            ByteReadPacket byteReadPacketCopy = this.$chunk.copy();
            this.label = 1;
            Object objWritePacket = byteChannel.writePacket(byteReadPacketCopy, this);
            a aVar = a.f15033i;
            if (objWritePacket == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((ByteChannelsKt$split$1$1$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
