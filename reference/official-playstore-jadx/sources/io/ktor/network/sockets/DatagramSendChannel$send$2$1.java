package io.ktor.network.sockets;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.util.PoolsKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.network.sockets.DatagramSendChannel$send$2$1", f = "DatagramSendChannel.kt", l = {86}, m = "invokeSuspend")
public final class DatagramSendChannel$send$2$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ Datagram $element;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DatagramSendChannel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatagramSendChannel$send$2$1(Datagram datagram, DatagramSendChannel datagramSendChannel, d<? super DatagramSendChannel$send$2$1> dVar) {
        super(2, dVar);
        this.$element = datagram;
        this.this$0 = datagramSendChannel;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new DatagramSendChannel$send$2$1(this.$element, this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        ObjectPool defaultDatagramByteBufferPool;
        Object objBorrow;
        ObjectPool objectPool;
        Throwable th;
        Object obj2;
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            defaultDatagramByteBufferPool = PoolsKt.getDefaultDatagramByteBufferPool();
            Datagram datagram = this.$element;
            DatagramSendChannel datagramSendChannel = this.this$0;
            objBorrow = defaultDatagramByteBufferPool.borrow();
            try {
                ByteBuffer byteBuffer = (ByteBuffer) objBorrow;
                DatagramSendChannelKt.writeMessageTo(datagram, byteBuffer);
                if (datagramSendChannel.getChannel().send(byteBuffer, JavaSocketAddressUtilsKt.toJavaAddress(datagram.getAddress())) != 0) {
                    datagramSendChannel.getSocket().interestOp(SelectInterest.WRITE, false);
                } else {
                    SocketAddress address = datagram.getAddress();
                    this.L$0 = defaultDatagramByteBufferPool;
                    this.L$1 = objBorrow;
                    this.label = 1;
                    Object objSendSuspend = datagramSendChannel.sendSuspend(byteBuffer, address, this);
                    a aVar = a.f15033i;
                    if (objSendSuspend == aVar) {
                        return aVar;
                    }
                    objectPool = defaultDatagramByteBufferPool;
                    obj2 = objBorrow;
                    objBorrow = obj2;
                    defaultDatagramByteBufferPool = objectPool;
                }
            } catch (Throwable th2) {
                objectPool = defaultDatagramByteBufferPool;
                th = th2;
                obj2 = objBorrow;
                objectPool.recycle(obj2);
                throw th;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            obj2 = this.L$1;
            objectPool = (ObjectPool) this.L$0;
            try {
                c.G(obj);
                objBorrow = obj2;
                defaultDatagramByteBufferPool = objectPool;
            } catch (Throwable th3) {
                th = th3;
                objectPool.recycle(obj2);
                throw th;
            }
        }
        defaultDatagramByteBufferPool.recycle(objBorrow);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((DatagramSendChannel$send$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
