package io.ktor.websocket;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.util.cio.ChannelIOException;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.websocket.WebSocketReader$readerJob$1", f = "WebSocketReader.kt", l = {40}, m = "invokeSuspend")
public final class WebSocketReader$readerJob$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ ObjectPool<ByteBuffer> $pool;
    Object L$0;
    int label;
    final /* synthetic */ WebSocketReader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketReader$readerJob$1(ObjectPool<ByteBuffer> objectPool, WebSocketReader webSocketReader, d<? super WebSocketReader$readerJob$1> dVar) {
        super(2, dVar);
        this.$pool = objectPool;
        this.this$0 = webSocketReader;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new WebSocketReader$readerJob$1(this.$pool, this.this$0, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            try {
                try {
                    try {
                        if (i10 == 0) {
                            c.G(obj);
                            ByteBuffer byteBufferBorrow = this.$pool.borrow();
                            WebSocketReader webSocketReader = this.this$0;
                            this.L$0 = byteBufferBorrow;
                            this.label = 1;
                            Object loop = webSocketReader.readLoop(byteBufferBorrow, this);
                            a aVar = a.f15033i;
                            i10 = byteBufferBorrow;
                            if (loop == aVar) {
                                return aVar;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ByteBuffer byteBuffer = (ByteBuffer) this.L$0;
                            c.G(obj);
                            i10 = byteBuffer;
                        }
                    } catch (ClosedChannelException | CancellationException unused) {
                    }
                } catch (FrameTooBigException e5) {
                    this.this$0.queue.close(e5);
                }
            } catch (ChannelIOException unused2) {
                this.this$0.queue.cancel(null);
            } catch (ProtocolViolationException e6) {
                this.this$0.queue.close(e6);
            }
            this.$pool.recycle(i10);
            this.this$0.queue.close(null);
            return t0.f22605a;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((WebSocketReader$readerJob$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
