package io.ktor.utils.io;

import d7.d;
import io.ktor.utils.io.internal.ClosedElement;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import t7.a;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Ld7/d;", "Lx6/t0;", "ucont", "", "invoke", "(Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ByteBufferChannel$writeSuspension$1 extends r implements l<d<? super t0>, Object> {
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$writeSuspension$1(ByteBufferChannel byteBufferChannel) {
        super(1);
        this.this$0 = byteBufferChannel;
    }

    @Override // r7.l
    public final Object invoke(d<? super t0> dVar) throws Throwable {
        Throwable sendException;
        int i10 = this.this$0.writeSuspensionSize;
        loop0: while (true) {
            ClosedElement closed = this.this$0.getClosed();
            if (closed != null && (sendException = closed.getSendException()) != null) {
                ByteBufferChannelKt.rethrowClosed(sendException);
                throw new KotlinNothingValueException();
            }
            if (!this.this$0.writeSuspendPredicate(i10)) {
                dVar.resumeWith(t0.f22605a);
                break;
            }
            ByteBufferChannel byteBufferChannel = this.this$0;
            d dVarA = a.A(dVar);
            ByteBufferChannel byteBufferChannel2 = this.this$0;
            while (byteBufferChannel.getWriteOp() == null) {
                if (!byteBufferChannel2.writeSuspendPredicate(i10)) {
                    break;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = ByteBufferChannel._writeOp$FU;
                while (!atomicReferenceFieldUpdater.compareAndSet(byteBufferChannel, null, dVarA)) {
                    if (atomicReferenceFieldUpdater.get(byteBufferChannel) != null) {
                        break;
                    }
                }
                if (byteBufferChannel2.writeSuspendPredicate(i10)) {
                    break;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = ByteBufferChannel._writeOp$FU;
                while (!atomicReferenceFieldUpdater2.compareAndSet(byteBufferChannel, dVarA, null)) {
                    if (atomicReferenceFieldUpdater2.get(byteBufferChannel) != dVarA) {
                        break loop0;
                    }
                }
            }
            throw new IllegalStateException("Operation is already in progress");
        }
        this.this$0.flushImpl(i10);
        if (this.this$0.shouldResumeReadOp()) {
            this.this$0.resumeReadOp();
        }
        return e7.a.f15033i;
    }
}
