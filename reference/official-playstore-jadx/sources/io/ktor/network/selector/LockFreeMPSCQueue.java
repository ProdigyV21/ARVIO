package io.ktor.network.selector;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0004J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/network/selector/LockFreeMPSCQueue;", "", "E", "<init>", "()V", "Lx6/t0;", "close", "element", "", "addLast", "(Ljava/lang/Object;)Z", "removeFirstOrNull", "()Ljava/lang/Object;", "isEmpty", "()Z", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LockFreeMPSCQueue<E> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _cur$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeMPSCQueue.class, Object.class, "_cur");
    private volatile /* synthetic */ Object _cur = new LockFreeMPSCQueueCore(8);

    public final boolean addLast(E element) {
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            int iAddLast = lockFreeMPSCQueueCore.addLast(element);
            if (iAddLast == 0) {
                return true;
            }
            if (iAddLast == 1) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _cur$FU;
                LockFreeMPSCQueueCore<E> next = lockFreeMPSCQueueCore.next();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, lockFreeMPSCQueueCore, next) && atomicReferenceFieldUpdater.get(this) == lockFreeMPSCQueueCore) {
                }
            } else if (iAddLast == 2) {
                return false;
            }
        }
    }

    public final void close() {
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            if (lockFreeMPSCQueueCore.close()) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _cur$FU;
            LockFreeMPSCQueueCore<E> next = lockFreeMPSCQueueCore.next();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, lockFreeMPSCQueueCore, next) && atomicReferenceFieldUpdater.get(this) == lockFreeMPSCQueueCore) {
            }
        }
    }

    public final boolean isEmpty() {
        return ((LockFreeMPSCQueueCore) this._cur).isEmpty();
    }

    public final E removeFirstOrNull() {
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            E e5 = (E) lockFreeMPSCQueueCore.removeFirstOrNull();
            if (e5 != LockFreeMPSCQueueCore.REMOVE_FROZEN) {
                return e5;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _cur$FU;
            LockFreeMPSCQueueCore<E> next = lockFreeMPSCQueueCore.next();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, lockFreeMPSCQueueCore, next) && atomicReferenceFieldUpdater.get(this) == lockFreeMPSCQueueCore) {
            }
        }
    }
}
