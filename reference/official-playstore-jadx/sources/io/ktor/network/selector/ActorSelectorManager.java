package io.ktor.network.selector;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import d7.j;
import e7.a;
import f7.e;
import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelector;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import k2.c;
import ka.j0;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlinx.coroutines.channels.ClosedSendChannelException;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u00014B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\u000e\u001a\u00020\r2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u000bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\rH\u0082Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u0017\u001a\u00020\r2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u0019\u001a\u0004\u0018\u00010\t*\b\u0012\u0004\u0012\u00020\t0\bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001b\u001a\u0004\u0018\u00010\t*\b\u0012\u0004\u0012\u00020\t0\bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\tH\u0014¢\u0006\u0004\b\u001f\u0010\u001eJ\u000f\u0010 \u001a\u00020\rH\u0016¢\u0006\u0004\b \u0010\u0016R\u0018\u0010!\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R&\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0*0)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010(R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u001a\u00100\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, d2 = {"Lio/ktor/network/selector/ActorSelectorManager;", "Lio/ktor/network/selector/SelectorManagerSupport;", "Ljava/io/Closeable;", "Lka/k0;", "Ld7/j;", "context", "<init>", "(Ld7/j;)V", "Lio/ktor/network/selector/LockFreeMPSCQueue;", "Lio/ktor/network/selector/Selectable;", "mb", "Ljava/nio/channels/Selector;", "selector", "Lx6/t0;", "process", "(Lio/ktor/network/selector/LockFreeMPSCQueue;Ljava/nio/channels/Selector;Ld7/d;)Ljava/lang/Object;", "", "select", "(Ljava/nio/channels/Selector;Ld7/d;)Ljava/lang/Object;", "dispatchIfNeeded", "(Ld7/d;)Ljava/lang/Object;", "selectWakeup", "()V", "processInterests", "(Lio/ktor/network/selector/LockFreeMPSCQueue;Ljava/nio/channels/Selector;)V", "receiveOrNull", "(Lio/ktor/network/selector/LockFreeMPSCQueue;Ld7/d;)Ljava/lang/Object;", "receiveOrNullSuspend", "selectable", "notifyClosed", "(Lio/ktor/network/selector/Selectable;)V", "publishInterest", "close", "selectorRef", "Ljava/nio/channels/Selector;", "Ljava/util/concurrent/atomic/AtomicLong;", "wakeup", "Ljava/util/concurrent/atomic/AtomicLong;", "", "inSelect", "Z", "Lio/ktor/network/selector/ActorSelectorManager$ContinuationHolder;", "Ld7/d;", "continuation", "Lio/ktor/network/selector/ActorSelectorManager$ContinuationHolder;", "closed", "selectionQueue", "Lio/ktor/network/selector/LockFreeMPSCQueue;", "coroutineContext", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "ContinuationHolder", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ActorSelectorManager extends SelectorManagerSupport implements Closeable, k0 {
    private volatile boolean closed;
    private final j coroutineContext;
    private volatile boolean inSelect;
    private volatile Selector selectorRef;
    private final AtomicLong wakeup = new AtomicLong();
    private final ContinuationHolder<t0, d<t0>> continuation = new ContinuationHolder<>();
    private final LockFreeMPSCQueue<Selectable> selectionQueue = new LockFreeMPSCQueue<>();

    /* JADX INFO: renamed from: io.ktor.network.selector.ActorSelectorManager$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.network.selector.ActorSelectorManager$1", f = "ActorSelectorManager.kt", l = {43}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends f7.j implements p<k0, d<? super t0>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return ActorSelectorManager.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws IOException {
            AbstractSelector abstractSelectorOpenSelector;
            ActorSelectorManager actorSelectorManager;
            Closeable closeable;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    c.G(obj);
                    abstractSelectorOpenSelector = ActorSelectorManager.this.getProvider().openSelector();
                    if (abstractSelectorOpenSelector == null) {
                        throw new IllegalStateException("openSelector() = null");
                    }
                    ActorSelectorManager.this.selectorRef = abstractSelectorOpenSelector;
                    actorSelectorManager = ActorSelectorManager.this;
                    try {
                        LockFreeMPSCQueue lockFreeMPSCQueue = actorSelectorManager.selectionQueue;
                        this.L$0 = abstractSelectorOpenSelector;
                        this.L$1 = actorSelectorManager;
                        this.L$2 = abstractSelectorOpenSelector;
                        this.label = 1;
                        Object objProcess = actorSelectorManager.process(lockFreeMPSCQueue, abstractSelectorOpenSelector, this);
                        a aVar = a.f15033i;
                        if (objProcess == aVar) {
                            return aVar;
                        }
                        closeable = abstractSelectorOpenSelector;
                        actorSelectorManager.closed = true;
                        actorSelectorManager.selectionQueue.close();
                    } catch (Throwable th) {
                        th = th;
                        closeable = abstractSelectorOpenSelector;
                        actorSelectorManager.closed = true;
                        actorSelectorManager.selectionQueue.close();
                        actorSelectorManager.cancelAllSuspensions(abstractSelectorOpenSelector, th);
                        actorSelectorManager.closed = true;
                        actorSelectorManager.selectionQueue.close();
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    abstractSelectorOpenSelector = (AbstractSelector) this.L$2;
                    actorSelectorManager = (ActorSelectorManager) this.L$1;
                    closeable = (Closeable) this.L$0;
                    try {
                        c.G(obj);
                        actorSelectorManager.closed = true;
                        actorSelectorManager.selectionQueue.close();
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            actorSelectorManager.closed = true;
                            actorSelectorManager.selectionQueue.close();
                            actorSelectorManager.cancelAllSuspensions(abstractSelectorOpenSelector, th);
                            actorSelectorManager.closed = true;
                            actorSelectorManager.selectionQueue.close();
                        } catch (Throwable th3) {
                            actorSelectorManager.closed = true;
                            actorSelectorManager.selectionQueue.close();
                            actorSelectorManager.selectorRef = null;
                            actorSelectorManager.cancelAllSuspensions(abstractSelectorOpenSelector, (Throwable) null);
                            throw th3;
                        }
                    }
                }
                actorSelectorManager.selectorRef = null;
                actorSelectorManager.cancelAllSuspensions(abstractSelectorOpenSelector, (Throwable) null);
                while (true) {
                    Selectable selectable = (Selectable) actorSelectorManager.selectionQueue.removeFirstOrNull();
                    if (selectable == null) {
                        xc.d.L(closeable, null);
                        return t0.f22605a;
                    }
                    actorSelectorManager.cancelAllSuspensions(selectable, new ClosedSendChannelException("Failed to apply interest: selector closed"));
                }
            } finally {
            }
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00000\u00022\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\nJ+\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00028\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"Lio/ktor/network/selector/ActorSelectorManager$ContinuationHolder;", "R", "Ld7/d;", "C", "", "<init>", "()V", "value", "", "resume", "(Ljava/lang/Object;)Z", "continuation", "Lkotlin/Function0;", "condition", "suspendIf", "(Ld7/d;Lr7/a;)Ljava/lang/Object;", "Ljava/util/concurrent/atomic/AtomicReference;", "ref", "Ljava/util/concurrent/atomic/AtomicReference;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ContinuationHolder<R, C extends d<? super R>> {
        private final AtomicReference<C> ref = new AtomicReference<>(null);

        public final boolean resume(R value) {
            C andSet = this.ref.getAndSet(null);
            if (andSet == null) {
                return false;
            }
            andSet.resumeWith(value);
            return true;
        }

        public final Object suspendIf(C continuation, r7.a<Boolean> condition) {
            if (!((Boolean) condition.invoke()).booleanValue()) {
                return null;
            }
            AtomicReference atomicReference = this.ref;
            while (!atomicReference.compareAndSet(null, continuation)) {
                if (atomicReference.get() != null) {
                    throw new IllegalStateException("Continuation is already set");
                }
            }
            if (!((Boolean) condition.invoke()).booleanValue()) {
                AtomicReference atomicReference2 = this.ref;
                while (!atomicReference2.compareAndSet(continuation, null)) {
                    if (atomicReference2.get() != continuation) {
                    }
                }
                return null;
            }
            return a.f15033i;
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.selector.ActorSelectorManager$process$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.network.selector.ActorSelectorManager", f = "ActorSelectorManager.kt", l = {69, 73, TsExtractor.TS_STREAM_TYPE_DVBSUBS}, m = "process")
    public static final class C18341 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C18341(d<? super C18341> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ActorSelectorManager.this.process(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.network.selector.ActorSelectorManager", f = "ActorSelectorManager.kt", l = {165}, m = "receiveOrNullSuspend")
    public static final class C18351 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18351(d<? super C18351> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ActorSelectorManager.this.receiveOrNullSuspend(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.selector.ActorSelectorManager$select$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.network.selector.ActorSelectorManager", f = "ActorSelectorManager.kt", l = {205}, m = "select")
    public static final class C18361 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18361(d<? super C18361> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ActorSelectorManager.this.select(null, this);
        }
    }

    public ActorSelectorManager(j jVar) {
        this.coroutineContext = jVar.plus(new j0("selector"));
        m0.p(this, null, 0, new AnonymousClass1(null), 3);
    }

    private final Object dispatchIfNeeded(d<? super t0> dVar) {
        m0.B(dVar);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e6, code lost:
    
        if (r11 == r1) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0093 -> B:19:0x006f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00a7 -> B:19:0x006f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00b7 -> B:19:0x006f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00e6 -> B:44:0x00e9). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object process(io.ktor.network.selector.LockFreeMPSCQueue<io.ktor.network.selector.Selectable> r9, java.nio.channels.Selector r10, d7.d<? super x6.t0> r11) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.process(io.ktor.network.selector.LockFreeMPSCQueue, java.nio.channels.Selector, d7.d):java.lang.Object");
    }

    private final void processInterests(LockFreeMPSCQueue<Selectable> mb2, Selector selector) {
        while (true) {
            Selectable selectableRemoveFirstOrNull = mb2.removeFirstOrNull();
            if (selectableRemoveFirstOrNull == null) {
                return;
            } else {
                applyInterest(selector, selectableRemoveFirstOrNull);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object receiveOrNull(LockFreeMPSCQueue<Selectable> lockFreeMPSCQueue, d<? super Selectable> dVar) {
        Selectable selectableRemoveFirstOrNull = lockFreeMPSCQueue.removeFirstOrNull();
        return selectableRemoveFirstOrNull == null ? receiveOrNullSuspend(lockFreeMPSCQueue, dVar) : selectableRemoveFirstOrNull;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object receiveOrNullSuspend(io.ktor.network.selector.LockFreeMPSCQueue<io.ktor.network.selector.Selectable> r8, d7.d<? super io.ktor.network.selector.Selectable> r9) {
        /*
            r7 = this;
            e7.a r0 = e7.a.f15033i
            boolean r1 = r9 instanceof io.ktor.network.selector.ActorSelectorManager.C18351
            if (r1 == 0) goto L15
            r1 = r9
            io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1 r1 = (io.ktor.network.selector.ActorSelectorManager.C18351) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1 r1 = new io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1
            r1.<init>(r9)
        L1a:
            java.lang.Object r9 = r1.result
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r8 = r1.L$1
            io.ktor.network.selector.LockFreeMPSCQueue r8 = (io.ktor.network.selector.LockFreeMPSCQueue) r8
            java.lang.Object r2 = r1.L$0
            io.ktor.network.selector.ActorSelectorManager r2 = (io.ktor.network.selector.ActorSelectorManager) r2
            k2.c.G(r9)
            goto L3b
        L2f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L37:
            k2.c.G(r9)
            r2 = r7
        L3b:
            java.lang.Object r9 = r8.removeFirstOrNull()
            io.ktor.network.selector.Selectable r9 = (io.ktor.network.selector.Selectable) r9
            if (r9 == 0) goto L44
            return r9
        L44:
            boolean r9 = r2.closed
            r4 = 0
            if (r9 == 0) goto L4a
            return r4
        L4a:
            r1.L$0 = r2
            r1.L$1 = r8
            r1.label = r3
            io.ktor.network.selector.ActorSelectorManager$ContinuationHolder<x6.t0, d7.d<x6.t0>> r9 = r2.continuation
            boolean r5 = r8.isEmpty()
            if (r5 == 0) goto L93
            boolean r5 = r2.closed
            if (r5 != 0) goto L93
            java.util.concurrent.atomic.AtomicReference r5 = io.ktor.network.selector.ActorSelectorManager.ContinuationHolder.access$getRef$p(r9)
        L60:
            boolean r6 = r5.compareAndSet(r4, r1)
            if (r6 == 0) goto L84
            boolean r5 = r8.isEmpty()
            if (r5 == 0) goto L71
            boolean r5 = r2.closed
            if (r5 != 0) goto L71
            goto L82
        L71:
            java.util.concurrent.atomic.AtomicReference r6 = io.ktor.network.selector.ActorSelectorManager.ContinuationHolder.access$getRef$p(r9)
        L75:
            boolean r9 = r6.compareAndSet(r1, r4)
            if (r9 == 0) goto L7c
            goto L93
        L7c:
            java.lang.Object r9 = r6.get()
            if (r9 == r1) goto L75
        L82:
            r4 = r0
            goto L93
        L84:
            java.lang.Object r6 = r5.get()
            if (r6 != 0) goto L8b
            goto L60
        L8b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Continuation is already set"
            r8.<init>(r9)
            throw r8
        L93:
            if (r4 != 0) goto L97
            x6.t0 r4 = x6.t0.f22605a
        L97:
            if (r4 != r0) goto L3b
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.receiveOrNullSuspend(io.ktor.network.selector.LockFreeMPSCQueue, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object select(java.nio.channels.Selector r6, d7.d<? super java.lang.Integer> r7) throws java.io.IOException {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.network.selector.ActorSelectorManager.C18361
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.network.selector.ActorSelectorManager$select$1 r0 = (io.ktor.network.selector.ActorSelectorManager.C18361) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.selector.ActorSelectorManager$select$1 r0 = new io.ktor.network.selector.ActorSelectorManager$select$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r6 = r0.L$1
            java.nio.channels.Selector r6 = (java.nio.channels.Selector) r6
            java.lang.Object r0 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r0 = (io.ktor.network.selector.ActorSelectorManager) r0
            k2.c.G(r7)
            goto L4a
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            k2.c.G(r7)
            r5.inSelect = r3
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = ka.m0.B(r0)
            if (r7 != r1) goto L49
            return r1
        L49:
            r0 = r5
        L4a:
            java.util.concurrent.atomic.AtomicLong r7 = r0.wakeup
            long r1 = r7.get()
            r3 = 0
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r1 = 0
            if (r7 != 0) goto L60
            r2 = 500(0x1f4, double:2.47E-321)
            int r6 = r6.select(r2)
            r0.inSelect = r1
            goto L6b
        L60:
            r0.inSelect = r1
            java.util.concurrent.atomic.AtomicLong r7 = r0.wakeup
            r7.set(r3)
            int r6 = r6.selectNow()
        L6b:
            java.lang.Integer r7 = new java.lang.Integer
            r7.<init>(r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.select(java.nio.channels.Selector, d7.d):java.lang.Object");
    }

    private final void selectWakeup() {
        Selector selector;
        if (this.wakeup.incrementAndGet() == 1 && this.inSelect && (selector = this.selectorRef) != null) {
            selector.wakeup();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
        this.selectionQueue.close();
        if (this.continuation.resume(t0.f22605a)) {
            return;
        }
        selectWakeup();
    }

    @Override // io.ktor.network.selector.SelectorManagerSupport, io.ktor.network.selector.SelectorManager, ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.network.selector.SelectorManager
    public void notifyClosed(Selectable selectable) {
        SelectionKey selectionKeyKeyFor;
        cancelAllSuspensions(selectable, new ClosedChannelException());
        Selector selector = this.selectorRef;
        if (selector == null || (selectionKeyKeyFor = selectable.getChannel().keyFor(selector)) == null) {
            return;
        }
        selectionKeyKeyFor.cancel();
        selectWakeup();
    }

    @Override // io.ktor.network.selector.SelectorManagerSupport
    public void publishInterest(Selectable selectable) {
        try {
            if (this.selectionQueue.addLast(selectable)) {
                this.continuation.resume(t0.f22605a);
                selectWakeup();
            } else {
                if (!selectable.getChannel().isOpen()) {
                    throw new ClosedChannelException();
                }
                throw new ClosedSelectorException();
            }
        } catch (Throwable th) {
            cancelAllSuspensions(selectable, th);
        }
    }
}
