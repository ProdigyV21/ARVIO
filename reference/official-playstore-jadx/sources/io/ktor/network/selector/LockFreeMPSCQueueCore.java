package io.ktor.network.selector;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000 &*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0002&'B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ'\u0010\r\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J3\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\f2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ3\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\f2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u001c\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010#R\u0011\u0010$\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b$\u0010\u0012R\u0014\u0010%\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010#¨\u0006("}, d2 = {"Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "", "E", "", "capacity", "<init>", "(I)V", "element", "addLast", "(Ljava/lang/Object;)I", "", "state", "Lio/ktor/network/selector/Core;", "allocateNextCopy", "(J)Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "allocateOrGetNextCopy", "", "close", "()Z", "index", "fillPlaceholder", "(ILjava/lang/Object;)Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "markFrozen", "()J", LinkHeader.Rel.Next, "()Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "removeFirstOrNull", "()Ljava/lang/Object;", "oldHead", "newHead", "removeSlowPath", "(II)Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "array", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "I", "isEmpty", "mask", "Companion", "Placeholder", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class LockFreeMPSCQueueCore<E> {
    public static final int ADD_CLOSED = 2;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_SUCCESS = 0;
    private static final int CAPACITY_BITS = 30;
    private static final long CLOSED_MASK = 2305843009213693952L;
    private static final int CLOSED_SHIFT = 61;
    private static final long FROZEN_MASK = 1152921504606846976L;
    private static final int FROZEN_SHIFT = 60;
    private static final long HEAD_MASK = 1073741823;
    private static final int HEAD_SHIFT = 0;
    public static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY_MASK = 1073741823;
    private static final long TAIL_MASK = 1152921503533105152L;
    private static final int TAIL_SHIFT = 30;
    private volatile /* synthetic */ Object _next = null;
    private volatile /* synthetic */ long _state = 0;
    private final AtomicReferenceArray<Object> array;
    private final int capacity;
    private final int mask;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Object REMOVE_FROZEN = new Object() { // from class: io.ktor.network.selector.LockFreeMPSCQueueCore$Companion$REMOVE_FROZEN$1
        public String toString() {
            return "REMOVE_FROZEN";
        }
    };
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeMPSCQueueCore.class, Object.class, "_next");
    private static final /* synthetic */ AtomicLongFieldUpdater _state$FU = AtomicLongFieldUpdater.newUpdater(LockFreeMPSCQueueCore.class, "_state");

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0082\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\r\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000bJR\u0010\u0015\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u000e*\u00020\u000426\u0010\u0014\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00028\u00010\u000fH\u0082\b¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\b*\u00020\u0004H\u0002¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\b8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\b8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\b8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001d\u0010\u001aR\u0014\u0010\u001e\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b \u0010\u001aR\u0014\u0010!\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010\u001fR\u0014\u0010\"\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\"\u0010\u001aR\u0014\u0010#\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b#\u0010\u001fR\u0014\u0010$\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b$\u0010\u001aR\u0014\u0010%\u001a\u00020\b8\u0000X\u0080T¢\u0006\u0006\n\u0004\b%\u0010\u001aR\u0014\u0010&\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b&\u0010\u001aR\u0014\u0010'\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b)\u0010\u001fR\u0014\u0010*\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b*\u0010\u001a¨\u0006+"}, d2 = {"Lio/ktor/network/selector/LockFreeMPSCQueueCore$Companion;", "", "<init>", "()V", "", "other", "wo", "(JJ)J", "", "newHead", "updateHead", "(JI)J", "newTail", "updateTail", "T", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, TtmlNode.TAG_HEAD, "tail", "block", "withState", "(JLr7/p;)Ljava/lang/Object;", "addFailReason", "(J)I", "ADD_CLOSED", "I", "ADD_FROZEN", "ADD_SUCCESS", "CAPACITY_BITS", "CLOSED_MASK", "J", "CLOSED_SHIFT", "FROZEN_MASK", "FROZEN_SHIFT", "HEAD_MASK", "HEAD_SHIFT", "INITIAL_CAPACITY", "MAX_CAPACITY_MASK", "REMOVE_FROZEN", "Ljava/lang/Object;", "TAIL_MASK", "TAIL_SHIFT", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int addFailReason(long j10) {
            return (j10 & LockFreeMPSCQueueCore.CLOSED_MASK) != 0 ? 2 : 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long updateHead(long j10, int i10) {
            return wo(j10, LockFreeMPSCQueueCore.HEAD_MASK) | ((long) i10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long updateTail(long j10, int i10) {
            return wo(j10, LockFreeMPSCQueueCore.TAIL_MASK) | (((long) i10) << 30);
        }

        private final <T> T withState(long j10, p<? super Integer, ? super Integer, ? extends T> pVar) {
            return (T) pVar.invoke(Integer.valueOf((int) (LockFreeMPSCQueueCore.HEAD_MASK & j10)), Integer.valueOf((int) ((j10 & LockFreeMPSCQueueCore.TAIL_MASK) >> 30)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long wo(long j10, long j11) {
            return j10 & (~j11);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/ktor/network/selector/LockFreeMPSCQueueCore$Placeholder;", "", "index", "", "(I)V", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Placeholder {
        public final int index;

        public Placeholder(int i10) {
            this.index = i10;
        }
    }

    public LockFreeMPSCQueueCore(int i10) {
        this.capacity = i10;
        int i11 = i10 - 1;
        this.mask = i11;
        this.array = new AtomicReferenceArray<>(i10);
        if (i11 > MAX_CAPACITY_MASK) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i10 & i11) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    private final LockFreeMPSCQueueCore<E> allocateNextCopy(long state) {
        LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCore = new LockFreeMPSCQueueCore<>(this.capacity * 2);
        int i10 = (int) (HEAD_MASK & state);
        int i11 = (int) ((TAIL_MASK & state) >> 30);
        while (true) {
            int i12 = this.mask;
            if ((i10 & i12) == (i11 & i12)) {
                lockFreeMPSCQueueCore._state = INSTANCE.wo(state, FROZEN_MASK);
                return lockFreeMPSCQueueCore;
            }
            AtomicReferenceArray<Object> atomicReferenceArray = lockFreeMPSCQueueCore.array;
            int i13 = lockFreeMPSCQueueCore.mask & i10;
            Object placeholder = this.array.get(i12 & i10);
            if (placeholder == null) {
                placeholder = new Placeholder(i10);
            }
            atomicReferenceArray.set(i13, placeholder);
            i10++;
        }
    }

    private final LockFreeMPSCQueueCore<E> allocateOrGetNextCopy(long state) {
        while (true) {
            LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._next;
            if (lockFreeMPSCQueueCore != null) {
                return lockFreeMPSCQueueCore;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
            LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCoreAllocateNextCopy = allocateNextCopy(state);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, lockFreeMPSCQueueCoreAllocateNextCopy) && atomicReferenceFieldUpdater.get(this) == null) {
            }
        }
    }

    private final LockFreeMPSCQueueCore<E> fillPlaceholder(int index, E element) {
        Object obj = this.array.get(this.mask & index);
        if (!(obj instanceof Placeholder) || ((Placeholder) obj).index != index) {
            return null;
        }
        this.array.set(index & this.mask, element);
        return this;
    }

    private final long markFrozen() {
        long j10;
        long j11;
        do {
            j10 = this._state;
            if ((j10 & FROZEN_MASK) != 0) {
                return j10;
            }
            j11 = j10 | FROZEN_MASK;
        } while (!_state$FU.compareAndSet(this, j10, j11));
        return j11;
    }

    private final LockFreeMPSCQueueCore<E> removeSlowPath(int oldHead, int newHead) {
        long j10;
        int i10;
        do {
            j10 = this._state;
            i10 = (int) (HEAD_MASK & j10);
            if (i10 != oldHead) {
                throw new IllegalStateException("This queue can have only one consumer");
            }
            if ((FROZEN_MASK & j10) != 0) {
                return next();
            }
        } while (!_state$FU.compareAndSet(this, j10, INSTANCE.updateHead(j10, newHead)));
        this.array.set(this.mask & i10, null);
        return null;
    }

    public final int addLast(E element) {
        long j10;
        int i10;
        do {
            j10 = this._state;
            if ((3458764513820540928L & j10) != 0) {
                return INSTANCE.addFailReason(j10);
            }
            int i11 = (int) (HEAD_MASK & j10);
            i10 = (int) ((TAIL_MASK & j10) >> 30);
            int i12 = this.mask;
            if (((i10 + 2) & i12) == (i11 & i12)) {
                return 1;
            }
        } while (!_state$FU.compareAndSet(this, j10, INSTANCE.updateTail(j10, (i10 + 1) & MAX_CAPACITY_MASK)));
        this.array.set(this.mask & i10, element);
        LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCoreFillPlaceholder = this;
        while ((lockFreeMPSCQueueCoreFillPlaceholder._state & FROZEN_MASK) != 0 && (lockFreeMPSCQueueCoreFillPlaceholder = lockFreeMPSCQueueCoreFillPlaceholder.next().fillPlaceholder(i10, element)) != null) {
        }
        return 0;
    }

    public final boolean close() {
        long j10;
        do {
            j10 = this._state;
            if ((j10 & CLOSED_MASK) != 0) {
                return true;
            }
            if ((FROZEN_MASK & j10) != 0) {
                return false;
            }
        } while (!_state$FU.compareAndSet(this, j10, j10 | CLOSED_MASK));
        return true;
    }

    public final boolean isEmpty() {
        long j10 = this._state;
        return ((int) (HEAD_MASK & j10)) == ((int) ((j10 & TAIL_MASK) >> 30));
    }

    public final LockFreeMPSCQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    public final Object removeFirstOrNull() {
        Object obj;
        long j10 = this._state;
        if ((FROZEN_MASK & j10) != 0) {
            return REMOVE_FROZEN;
        }
        int i10 = (int) (HEAD_MASK & j10);
        int i11 = (int) ((TAIL_MASK & j10) >> 30);
        int i12 = this.mask;
        if ((i11 & i12) == (i10 & i12) || (obj = this.array.get(i12 & i10)) == null || (obj instanceof Placeholder)) {
            return null;
        }
        int i13 = (i10 + 1) & MAX_CAPACITY_MASK;
        if (_state$FU.compareAndSet(this, j10, INSTANCE.updateHead(j10, i13))) {
            this.array.set(this.mask & i10, null);
            return obj;
        }
        LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCoreRemoveSlowPath = this;
        do {
            lockFreeMPSCQueueCoreRemoveSlowPath = lockFreeMPSCQueueCoreRemoveSlowPath.removeSlowPath(i10, i13);
        } while (lockFreeMPSCQueueCoreRemoveSlowPath != null);
        return obj;
    }
}
