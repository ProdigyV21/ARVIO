package io.ktor.utils.io.jvm.javaio;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import d7.j;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.c1;
import ka.r2;
import ka.v1;
import ka.z0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.q0;
import t7.a;
import x6.c0;
import x6.d0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\b\b\"\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\bH¤@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0001¢\u0006\u0004\b\u0017\u0010\u001aJ\u001b\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0014H\u0084Hø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0014H\u0004¢\u0006\u0004\b\u001e\u0010\u001fR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010&\u001a\u0004\u0018\u00010%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u00148\u0004@BX\u0084\u000e¢\u0006\f\n\u0004\b\u0015\u0010)\u001a\u0004\b*\u0010+R$\u0010\u0016\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u00148\u0004@BX\u0084\u000e¢\u0006\f\n\u0004\b\u0016\u0010)\u001a\u0004\b,\u0010+\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lio/ktor/utils/io/jvm/javaio/BlockingAdapter;", "", "Lka/v1;", "parent", "<init>", "(Lka/v1;)V", "Ljava/lang/Thread;", "thread", "Lx6/t0;", "parkingLoop", "(Ljava/lang/Thread;)V", "Ld7/d;", "ucont", "rendezvousBlock", "(Ld7/d;)Ljava/lang/Object;", "loop", "shutdown", "()V", "", "buffer", "", "offset", "length", "submitAndAwait", "([BII)I", "jobToken", "(Ljava/lang/Object;)I", "rc", "rendezvous", "(ILd7/d;)Ljava/lang/Object;", "finish", "(I)V", "Lka/v1;", "getParent", "()Lka/v1;", TtmlNode.END, "Ld7/d;", "Lka/z0;", "disposable", "Lka/z0;", "<set-?>", "I", "getOffset", "()I", "getLength", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
abstract class BlockingAdapter {
    static final /* synthetic */ AtomicReferenceFieldUpdater state$FU = AtomicReferenceFieldUpdater.newUpdater(BlockingAdapter.class, Object.class, "state");
    private final z0 disposable;
    private final d<t0> end;
    private int length;
    private int offset;
    private final v1 parent;
    volatile /* synthetic */ int result;
    volatile /* synthetic */ Object state;

    /* JADX WARN: Multi-variable type inference failed */
    public BlockingAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private static /* synthetic */ void getState$annotations() {
    }

    private final void parkingLoop(Thread thread) {
        if (this.state != thread) {
            return;
        }
        if (!PollersKt.isParkingAllowed()) {
            BlockingKt.getADAPTER_LOGGER().g("Blocking network thread detected. \nIt can possible lead to a performance decline or even a deadlock.\nPlease make sure you're using blocking IO primitives like InputStream and OutputStream only in \nthe context of Dispatchers.IO:\n```\nwithContext(Dispatchers.IO) {\n    myInputStream.read()\n}\n```");
        }
        while (true) {
            c1 c1Var = (c1) r2.f19629a.get();
            long jD0 = c1Var != null ? c1Var.D0() : Long.MAX_VALUE;
            if (this.state != thread) {
                return;
            }
            if (jD0 > 0) {
                PollersKt.getParkingImpl().park(jD0);
            }
        }
    }

    private final Object rendezvous$$forInline(int i10, d<Object> dVar) {
        this.result = i10;
        return rendezvousBlock(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object rendezvousBlock(d<Object> ucont) {
        Object obj;
        d dVarA;
        Object obj2 = null;
        while (true) {
            Object obj3 = this.state;
            if (obj3 instanceof Thread) {
                dVarA = a.A(ucont);
                obj = obj3;
            } else {
                if (!p.a(obj3, this)) {
                    throw new IllegalStateException("Already suspended or in finished state");
                }
                obj = obj2;
                dVarA = a.A(ucont);
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj3, dVarA)) {
                if (atomicReferenceFieldUpdater.get(this) != obj3) {
                    break;
                }
            }
            if (obj != null) {
                PollersKt.getParkingImpl().unpark(obj);
            }
            return e7.a.f15033i;
            obj2 = obj;
        }
    }

    public final void finish(int rc2) {
        this.result = rc2;
    }

    public final int getLength() {
        return this.length;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final v1 getParent() {
        return this.parent;
    }

    public abstract Object loop(d<? super t0> dVar);

    public final Object rendezvous(int i10, d<Object> dVar) {
        this.result = i10;
        return rendezvousBlock(dVar);
    }

    public final void shutdown() {
        z0 z0Var = this.disposable;
        if (z0Var != null) {
            z0Var.dispose();
        }
        this.end.resumeWith(new c0(new CancellationException("Stream closed")));
    }

    public final int submitAndAwait(byte[] buffer, int offset, int length) {
        this.offset = offset;
        this.length = length;
        return submitAndAwait(buffer);
    }

    public BlockingAdapter(v1 v1Var) {
        this.parent = v1Var;
        d<t0> dVar = new d<t0>() { // from class: io.ktor.utils.io.jvm.javaio.BlockingAdapter$end$1
            private final j context;

            {
                this.context = this.this$0.getParent() != null ? UnsafeBlockingTrampoline.INSTANCE.plus(this.this$0.getParent()) : UnsafeBlockingTrampoline.INSTANCE;
            }

            @Override // d7.d
            public j getContext() {
                return this.context;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // d7.d
            public void resumeWith(Object result) {
                Object obj;
                boolean z;
                boolean z5;
                Throwable thA;
                v1 parent;
                Object objA = d0.a(result);
                if (objA == null) {
                    objA = t0.f22605a;
                }
                BlockingAdapter blockingAdapter = this.this$0;
                do {
                    obj = blockingAdapter.state;
                    z = obj instanceof Thread;
                    z5 = true;
                    if (!(z ? true : obj instanceof d ? true : p.a(obj, this))) {
                        return;
                    }
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = BlockingAdapter.state$FU;
                    while (true) {
                        if (atomicReferenceFieldUpdater.compareAndSet(blockingAdapter, obj, objA)) {
                            break;
                        } else if (atomicReferenceFieldUpdater.get(blockingAdapter) != obj) {
                            z5 = false;
                            break;
                        }
                    }
                } while (!z5);
                if (z) {
                    PollersKt.getParkingImpl().unpark(obj);
                } else if ((obj instanceof d) && (thA = d0.a(result)) != null) {
                    ((d) obj).resumeWith(new c0(thA));
                }
                if ((result instanceof c0) && !(d0.a(result) instanceof CancellationException) && (parent = this.this$0.getParent()) != null) {
                    parent.cancel((CancellationException) null);
                }
                z0 z0Var = this.this$0.disposable;
                if (z0Var != null) {
                    z0Var.dispose();
                }
            }
        };
        this.end = dVar;
        this.state = this;
        this.result = 0;
        this.disposable = v1Var != null ? v1Var.invokeOnCompletion(new BlockingAdapter$disposable$1(this)) : null;
        BlockingAdapter$block$1 blockingAdapter$block$1 = new BlockingAdapter$block$1(this, null);
        q0.e(1, blockingAdapter$block$1);
        blockingAdapter$block$1.invoke((Object) dVar);
        if (this.state == this) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    public final int submitAndAwait(Object jobToken) throws Throwable {
        Object noWhenBranchMatchedException;
        Thread threadCurrentThread = Thread.currentThread();
        d dVar = null;
        while (true) {
            Object obj = this.state;
            if (obj instanceof d) {
                dVar = (d) obj;
                noWhenBranchMatchedException = threadCurrentThread;
            } else {
                if (obj instanceof t0) {
                    return this.result;
                }
                if (!(obj instanceof Throwable)) {
                    if (!(obj instanceof Thread)) {
                        if (!p.a(obj, this)) {
                            noWhenBranchMatchedException = new NoWhenBranchMatchedException();
                        } else {
                            throw new IllegalStateException("Not yet started");
                        }
                    } else {
                        throw new IllegalStateException("There is already thread owning adapter");
                    }
                } else {
                    throw ((Throwable) obj);
                }
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, noWhenBranchMatchedException)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            dVar.resumeWith(jobToken);
            parkingLoop(threadCurrentThread);
            Object obj2 = this.state;
            if (!(obj2 instanceof Throwable)) {
                return this.result;
            }
            throw ((Throwable) obj2);
        }
    }

    public /* synthetic */ BlockingAdapter(v1 v1Var, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : v1Var);
    }
}
