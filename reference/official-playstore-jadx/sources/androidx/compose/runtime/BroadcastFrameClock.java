package androidx.compose.runtime;

import androidx.compose.runtime.MonotonicFrameClock;
import d7.j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import r7.p;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001+B\u0019\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ*\u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\u0010H\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0017\u001a\u00020\u00032\f\b\u0002\u0010\u0016\u001a\u00060\u0014j\u0002`\u0015¢\u0006\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR \u0010!\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030 0\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R \u0010#\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030 0\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010\"R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0011\u0010*\u001a\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006,"}, d2 = {"Landroidx/compose/runtime/BroadcastFrameClock;", "Landroidx/compose/runtime/MonotonicFrameClock;", "Lkotlin/Function0;", "Lx6/t0;", "onNewAwaiters", "<init>", "(Lr7/a;)V", "", "cause", "fail", "(Ljava/lang/Throwable;)V", "", "timeNanos", "sendFrame", "(J)V", "R", "Lkotlin/Function1;", "onFrame", "withFrameNanos", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancellationException", "cancel", "(Ljava/util/concurrent/CancellationException;)V", "Lr7/a;", "", "lock", "Ljava/lang/Object;", "failureCause", "Ljava/lang/Throwable;", "", "Landroidx/compose/runtime/BroadcastFrameClock$FrameAwaiter;", "awaiters", "Ljava/util/List;", "spareList", "Landroidx/compose/runtime/AtomicInt;", "hasAwaitersUnlocked", "Landroidx/compose/runtime/AtomicInt;", "", "getHasAwaiters", "()Z", "hasAwaiters", "FrameAwaiter", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BroadcastFrameClock implements MonotonicFrameClock {
    public static final int $stable = 8;
    private List<FrameAwaiter<?>> awaiters;
    private Throwable failureCause;
    private final AtomicInt hasAwaitersUnlocked;
    private final Object lock;
    private final r7.a<t0> onNewAwaiters;
    private List<FrameAwaiter<?>> spareList;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B)\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\rR#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/runtime/BroadcastFrameClock$FrameAwaiter;", "R", "", "Lkotlin/Function1;", "", "onFrame", "Ld7/d;", "continuation", "<init>", "(Lr7/l;Ld7/d;)V", "timeNanos", "Lx6/t0;", "resume", "(J)V", "Lr7/l;", "getOnFrame", "()Lr7/l;", "Ld7/d;", "getContinuation", "()Ld7/d;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FrameAwaiter<R> {
        private final d7.d<R> continuation;
        private final r7.l<Long, R> onFrame;

        /* JADX WARN: Multi-variable type inference failed */
        public FrameAwaiter(r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
            this.onFrame = lVar;
            this.continuation = dVar;
        }

        public final d7.d<R> getContinuation() {
            return this.continuation;
        }

        public final r7.l<Long, R> getOnFrame() {
            return this.onFrame;
        }

        public final void resume(long timeNanos) {
            Object c0Var;
            d7.d<R> dVar = this.continuation;
            try {
                c0Var = this.onFrame.invoke(Long.valueOf(timeNanos));
            } catch (Throwable th) {
                c0Var = new c0(th);
            }
            dVar.resumeWith(c0Var);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BroadcastFrameClock() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ void cancel$default(BroadcastFrameClock broadcastFrameClock, CancellationException cancellationException, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            cancellationException = new CancellationException("clock cancelled");
        }
        broadcastFrameClock.cancel(cancellationException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fail(Throwable cause) {
        synchronized (this.lock) {
            try {
                if (this.failureCause != null) {
                    return;
                }
                this.failureCause = cause;
                List<FrameAwaiter<?>> list = this.awaiters;
                int size = list.size();
                for (int i10 = 0; i10 < size; i10++) {
                    list.get(i10).getContinuation().resumeWith(new c0(cause));
                }
                this.awaiters.clear();
                this.hasAwaitersUnlocked.set(0);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void cancel(CancellationException cancellationException) {
        fail(cancellationException);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) MonotonicFrameClock.DefaultImpls.fold(this, r4, pVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) MonotonicFrameClock.DefaultImpls.get(this, bVar);
    }

    public final boolean getHasAwaiters() {
        return this.hasAwaitersUnlocked.get() != 0;
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j.a
    public final /* synthetic */ j.b getKey() {
        return h.a(this);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public d7.j minusKey(j.b<?> bVar) {
        return MonotonicFrameClock.DefaultImpls.minusKey(this, bVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public d7.j plus(d7.j jVar) {
        return MonotonicFrameClock.DefaultImpls.plus(this, jVar);
    }

    public final void sendFrame(long timeNanos) {
        synchronized (this.lock) {
            try {
                List<FrameAwaiter<?>> list = this.awaiters;
                this.awaiters = this.spareList;
                this.spareList = list;
                this.hasAwaitersUnlocked.set(0);
                int size = list.size();
                for (int i10 = 0; i10 < size; i10++) {
                    list.get(i10).resume(timeNanos);
                }
                list.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public <R> Object withFrameNanos(r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        ka.l lVar2 = new ka.l(1, t7.a.A(dVar));
        lVar2.s();
        FrameAwaiter frameAwaiter = new FrameAwaiter(lVar, lVar2);
        synchronized (this.lock) {
            Throwable th = this.failureCause;
            if (th != null) {
                lVar2.resumeWith(new c0(th));
            } else {
                boolean zIsEmpty = this.awaiters.isEmpty();
                this.awaiters.add(frameAwaiter);
                if (zIsEmpty) {
                    this.hasAwaitersUnlocked.set(1);
                }
                lVar2.c(new BroadcastFrameClock$withFrameNanos$2$1(this, frameAwaiter));
                if (zIsEmpty && this.onNewAwaiters != null) {
                    try {
                        this.onNewAwaiters.invoke();
                    } catch (Throwable th2) {
                        fail(th2);
                    }
                }
            }
        }
        return lVar2.r();
    }

    public BroadcastFrameClock(r7.a<t0> aVar) {
        this.onNewAwaiters = aVar;
        this.lock = new Object();
        this.awaiters = new ArrayList();
        this.spareList = new ArrayList();
        this.hasAwaitersUnlocked = new AtomicInt(0);
    }

    public /* synthetic */ BroadcastFrameClock(r7.a aVar, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : aVar);
    }
}
