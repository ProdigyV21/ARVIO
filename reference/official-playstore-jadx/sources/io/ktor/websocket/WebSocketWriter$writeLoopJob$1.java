package io.ktor.websocket;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.websocket.WebSocketWriter$writeLoopJob$1", f = "WebSocketWriter.kt", l = {40}, m = "invokeSuspend")
public final class WebSocketWriter$writeLoopJob$1 extends j implements p<k0, d<? super t0>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ WebSocketWriter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketWriter$writeLoopJob$1(WebSocketWriter webSocketWriter, d<? super WebSocketWriter$writeLoopJob$1> dVar) {
        super(2, dVar);
        this.this$0 = webSocketWriter;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new WebSocketWriter$writeLoopJob$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        ObjectPool objectPool;
        Throwable th;
        Object obj2;
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            obj2 = this.L$1;
            objectPool = (ObjectPool) this.L$0;
            try {
                c.G(obj);
                objectPool.recycle(obj2);
                return t0.f22605a;
            } catch (Throwable th2) {
                th = th2;
                objectPool.recycle(obj2);
                throw th;
            }
        }
        c.G(obj);
        ObjectPool<ByteBuffer> pool = this.this$0.getPool();
        WebSocketWriter webSocketWriter = this.this$0;
        ByteBuffer byteBufferBorrow = pool.borrow();
        try {
            this.L$0 = pool;
            this.L$1 = byteBufferBorrow;
            this.label = 1;
            Object objWriteLoop = webSocketWriter.writeLoop(byteBufferBorrow, this);
            a aVar = a.f15033i;
            if (objWriteLoop == aVar) {
                return aVar;
            }
            objectPool = pool;
            obj2 = byteBufferBorrow;
            objectPool.recycle(obj2);
            return t0.f22605a;
        } catch (Throwable th3) {
            objectPool = pool;
            th = th3;
            obj2 = byteBufferBorrow;
            objectPool.recycle(obj2);
            throw th;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((WebSocketWriter$writeLoopJob$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
