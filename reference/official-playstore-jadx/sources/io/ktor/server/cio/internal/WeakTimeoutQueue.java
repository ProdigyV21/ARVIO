package io.ktor.server.cio.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import d7.j;
import ga.m;
import io.ktor.http.ContentDisposition;
import io.ktor.util.InternalAPI;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.internal.LockFreeLinkedListHead;
import io.ktor.util.internal.LockFreeLinkedListNode;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.k0;
import ka.m0;
import ka.o;
import ka.q;
import ka.u1;
import ka.v1;
import ka.x1;
import ka.z0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.q0;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import sa.b;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001:\u0004,-./B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u0011\u001a\u00020\u0010\"\u0004\b\u0000\u0010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0010¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u0018\u001a\u00020\u0010¢\u0006\u0004\b\u0018\u0010\u001bJ\u000f\u0010\u001f\u001a\u00020\u001cH\u0000¢\u0006\u0004\b\u001d\u0010\u001eJB\u0010$\u001a\u00028\u0000\"\u0004\b\u0000\u0010\r2'\u0010#\u001a#\b\u0001\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010 ¢\u0006\u0002\b\"H\u0086@ø\u0001\u0000¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010(R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010)R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010*R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010+\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lio/ktor/server/cio/internal/WeakTimeoutQueue;", "", "", "timeoutMillis", "Lkotlin/Function0;", "clock", "<init>", "(JLr7/a;)V", "Lka/v1;", "job", "Lio/ktor/server/cio/internal/WeakTimeoutQueue$Registration;", "register", "(Lka/v1;)Lio/ktor/server/cio/internal/WeakTimeoutQueue$Registration;", "T", "Ld7/d;", "continuation", "Lx6/t0;", "checkCancellation", "(Ld7/d;)V", "now", "Lio/ktor/util/internal/LockFreeLinkedListHead;", TtmlNode.TAG_HEAD, "", "cancelled", "process", "(JLio/ktor/util/internal/LockFreeLinkedListHead;Z)V", "cancel", "()V", "", "count$ktor_server_cio", "()I", "count", "Lkotlin/Function2;", "Lka/k0;", "Lx6/n;", "block", "withTimeout", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "J", "getTimeoutMillis", "()J", "Lr7/a;", "Lio/ktor/util/internal/LockFreeLinkedListHead;", "Z", "Cancellable", "JobTask", "Registration", "WeakTimeoutCoroutine", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WeakTimeoutQueue {
    private volatile boolean cancelled;
    private final a<Long> clock;
    private final LockFreeLinkedListHead head;
    private final long timeoutMillis;

    /* JADX INFO: renamed from: io.ktor.server.cio.internal.WeakTimeoutQueue$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Long> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Long invoke() {
            return Long.valueOf(DateJvmKt.getTimeMillis());
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\"\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/server/cio/internal/WeakTimeoutQueue$Cancellable;", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/server/cio/internal/WeakTimeoutQueue$Registration;", "", "deadline", "<init>", "(J)V", "Lx6/t0;", "cancel", "()V", "dispose", "J", "getDeadline", "()J", "", "isActive", "()Z", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class Cancellable extends LockFreeLinkedListNode implements Registration {
        private final long deadline;

        public Cancellable(long j10) {
            this.deadline = j10;
        }

        public abstract void cancel();

        @Override // io.ktor.server.cio.internal.WeakTimeoutQueue.Registration, ka.z0
        public void dispose() {
            remove();
        }

        public final long getDeadline() {
            return this.deadline;
        }

        @Override // io.ktor.server.cio.internal.WeakTimeoutQueue.Registration
        public void invoke(Throwable th) {
            Registration.DefaultImpls.invoke(this, th);
        }

        public boolean isActive() {
            return !isRemoved();
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/cio/internal/WeakTimeoutQueue$JobTask;", "Lio/ktor/server/cio/internal/WeakTimeoutQueue$Cancellable;", "", "deadline", "Lka/v1;", "job", "<init>", "(JLka/v1;)V", "Lx6/t0;", "cancel", "()V", "Lka/v1;", "", "isActive", "()Z", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class JobTask extends Cancellable {
        private final v1 job;

        public JobTask(long j10, v1 v1Var) {
            super(j10);
            this.job = v1Var;
        }

        @Override // io.ktor.server.cio.internal.WeakTimeoutQueue.Cancellable
        public void cancel() {
            this.job.cancel((CancellationException) null);
        }

        @Override // io.ktor.server.cio.internal.WeakTimeoutQueue.Cancellable
        public boolean isActive() {
            return super.isActive() && this.job.isActive();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bb\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/server/cio/internal/WeakTimeoutQueue$Registration;", "Lka/z0;", "", "cause", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Registration extends z0 {

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class DefaultImpls {
            public static void invoke(Registration registration, Throwable th) {
                registration.dispose();
            }
        }

        @Override // ka.z0
        /* synthetic */ void dispose();

        void invoke(Throwable cause);
    }

    @Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u00022\u00020\u00032\u00020\u0004B'\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0097\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001c\u0010\u0011\u001a\u00020\u00152\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0016J\"\u0010\u0011\u001a\u00020\u00102\u0010\b\u0002\u0010\u0014\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018H\u0096\u0001¢\u0006\u0004\b\u0011\u0010\u0019J8\u0010\u001f\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u001a2\u0006\u0010\u001b\u001a\u00028\u00012\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00028\u00010\u001cH\u0096\u0001¢\u0006\u0004\b\u001f\u0010 J*\u0010$\u001a\u0004\u0018\u00018\u0001\"\b\b\u0001\u0010!*\u00020\u001d2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\"H\u0096\u0003¢\u0006\u0004\b$\u0010%J\u0014\u0010&\u001a\u00060\u0017j\u0002`\u0018H\u0097\u0001¢\u0006\u0004\b&\u0010'JM\u00100\u001a\u00020/2\b\b\u0002\u0010(\u001a\u00020\u00152\b\b\u0002\u0010)\u001a\u00020\u00152'\u0010.\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00100*j\u0002`-H\u0097\u0001¢\u0006\u0004\b0\u00101J9\u00100\u001a\u00020/2'\u0010.\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00100*j\u0002`-H\u0096\u0001¢\u0006\u0004\b0\u00102J\u0013\u00103\u001a\u00020\u0010H\u0096Aø\u0001\u0000¢\u0006\u0004\b3\u00104J\u001c\u00105\u001a\u00020\u00052\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\"H\u0096\u0001¢\u0006\u0004\b5\u00106J\u0018\u00107\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0096\u0003¢\u0006\u0004\b7\u00108J\u0018\u00107\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u0003H\u0097\u0003¢\u0006\u0004\b7\u0010:J\u0010\u0010;\u001a\u00020\u0015H\u0096\u0001¢\u0006\u0004\b;\u0010<J \u0010?\u001a\u00020\u00102\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000=H\u0016ø\u0001\u0000¢\u0006\u0004\b?\u0010@J\r\u0010A\u001a\u00020\u0015¢\u0006\u0004\bA\u0010<R\u0014\u0010\b\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010BR\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010C\u001a\u0004\bD\u0010ER\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030F8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0014\u0010J\u001a\u00020\u00158\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bJ\u0010<R\u0014\u0010K\u001a\u00020\u00158\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bK\u0010<R\u0014\u0010L\u001a\u00020\u00158\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bL\u0010<R\u0018\u0010#\u001a\u0006\u0012\u0002\b\u00030\"8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0014\u0010R\u001a\u00020O8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0016\u0010U\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0014\u0010W\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bV\u0010E\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006X"}, d2 = {"Lio/ktor/server/cio/internal/WeakTimeoutQueue$WeakTimeoutCoroutine;", "T", "Ld7/d;", "Lka/v1;", "Lka/k0;", "Ld7/j;", "context", "delegate", "job", "<init>", "(Ld7/j;Ld7/d;Lka/v1;)V", "Lka/q;", "child", "Lka/o;", "attachChild", "(Lka/q;)Lka/o;", "Lx6/t0;", "cancel", "()V", "", "cause", "", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "R", "initial", "Lkotlin/Function2;", "Ld7/j$a;", "operation", "fold", "(Ljava/lang/Object;Lr7/p;)Ljava/lang/Object;", "E", "Ld7/j$b;", "key", "get", "(Ld7/j$b;)Ld7/j$a;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "onCancelling", "invokeImmediately", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "Lkotlinx/coroutines/CompletionHandler;", "handler", "Lka/z0;", "invokeOnCompletion", "(ZZLr7/l;)Lka/z0;", "(Lr7/l;)Lka/z0;", "join", "(Ld7/d;)Ljava/lang/Object;", "minusKey", "(Ld7/j$b;)Ld7/j;", "plus", "(Ld7/j;)Ld7/j;", "other", "(Lka/v1;)Lka/v1;", TtmlNode.START, "()Z", "Lx6/d0;", "result", "resumeWith", "(Ljava/lang/Object;)V", "tryComplete", "Lka/v1;", "Ld7/j;", "getContext", "()Ld7/j;", "Lga/m;", "getChildren", "()Lga/m;", "children", "isActive", "isCancelled", "isCompleted", "getKey", "()Ld7/j$b;", "Lsa/b;", "getOnJoin", "()Lsa/b;", "onJoin", "getParent", "()Lka/v1;", "parent", "getCoroutineContext", "coroutineContext", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WeakTimeoutCoroutine<T> implements d<T>, v1, k0 {
        private static final /* synthetic */ AtomicReferenceFieldUpdater state$FU = AtomicReferenceFieldUpdater.newUpdater(WeakTimeoutCoroutine.class, Object.class, "state");
        private final j context;
        private final v1 job;
        private volatile /* synthetic */ Object state;

        public WeakTimeoutCoroutine(j jVar, d<? super T> dVar, v1 v1Var) {
            this.job = v1Var;
            this.context = jVar.plus(v1Var);
            this.state = dVar;
        }

        @Override // ka.v1
        public o attachChild(q child) {
            return this.job.attachChild(child);
        }

        @Override // ka.v1
        @e
        public /* synthetic */ void cancel() {
            this.job.cancel();
        }

        @Override // d7.j
        public <R> R fold(R initial, p<? super R, ? super j.a, ? extends R> operation) {
            return (R) this.job.fold(initial, operation);
        }

        @Override // d7.j
        public <E extends j.a> E get(j.b<E> key) {
            return (E) this.job.get(key);
        }

        @Override // ka.v1
        public CancellationException getCancellationException() {
            return this.job.getCancellationException();
        }

        @Override // ka.v1
        public m<v1> getChildren() {
            return this.job.getChildren();
        }

        @Override // d7.d
        public j getContext() {
            return this.context;
        }

        @Override // ka.k0
        public j getCoroutineContext() {
            return getContext();
        }

        @Override // d7.j.a
        public j.b<?> getKey() {
            return this.job.getKey();
        }

        @Override // ka.v1
        public b getOnJoin() {
            return this.job.getOnJoin();
        }

        @Override // ka.v1
        public v1 getParent() {
            return this.job.getParent();
        }

        @Override // ka.v1
        public z0 invokeOnCompletion(l<? super Throwable, t0> handler) {
            return this.job.invokeOnCompletion(handler);
        }

        @Override // ka.v1
        public boolean isActive() {
            return this.job.isActive();
        }

        @Override // ka.v1
        public boolean isCancelled() {
            return this.job.isCancelled();
        }

        @Override // ka.v1
        public boolean isCompleted() {
            return this.job.isCompleted();
        }

        @Override // ka.v1
        public Object join(d<? super t0> dVar) {
            return this.job.join(dVar);
        }

        @Override // d7.j
        public j minusKey(j.b<?> key) {
            return this.job.minusKey(key);
        }

        @Override // d7.j
        public j plus(j context) {
            return this.job.plus(context);
        }

        @Override // d7.d
        public void resumeWith(Object result) {
            while (true) {
                Object obj = this.state;
                d dVar = (d) obj;
                if (dVar == null) {
                    return;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$FU;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                dVar.resumeWith(result);
                this.job.cancel((CancellationException) null);
                return;
            }
        }

        @Override // ka.v1
        public boolean start() {
            return this.job.start();
        }

        public final boolean tryComplete() {
            while (true) {
                Object obj = this.state;
                if (((d) obj) == null) {
                    return false;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$FU;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                this.job.cancel((CancellationException) null);
                return true;
            }
        }

        @Override // ka.v1
        public void cancel(CancellationException cause) {
            this.job.cancel(cause);
        }

        @Override // ka.v1
        public z0 invokeOnCompletion(boolean onCancelling, boolean invokeImmediately, l<? super Throwable, t0> handler) {
            return this.job.invokeOnCompletion(onCancelling, invokeImmediately, handler);
        }

        @Override // ka.v1
        @e
        public v1 plus(v1 other) {
            return this.job.plus(other);
        }

        @Override // ka.v1
        @e
        public /* synthetic */ boolean cancel(Throwable cause) {
            return this.job.cancel(cause);
        }

        public WeakTimeoutCoroutine(j jVar, d dVar, v1 v1Var, int i10, h hVar) {
            this(jVar, dVar, (i10 & 4) != 0 ? new x1((v1) jVar.get(u1.f19642i)) : v1Var);
        }
    }

    public /* synthetic */ WeakTimeoutQueue(long j10, a aVar, int i10, h hVar) {
        this(j10, (i10 & 2) != 0 ? AnonymousClass1.INSTANCE : aVar);
    }

    private final <T> void checkCancellation(d<? super T> continuation) {
        v1 v1Var = (v1) continuation.getContext().get(u1.f19642i);
        if (v1Var != null && v1Var.isCancelled()) {
            throw v1Var.getCancellationException();
        }
    }

    private final Registration register(v1 job) {
        long jLongValue = ((Number) this.clock.invoke()).longValue();
        LockFreeLinkedListHead lockFreeLinkedListHead = this.head;
        if (this.cancelled) {
            throw new CancellationException("Queue is cancelled");
        }
        JobTask jobTask = new JobTask(this.timeoutMillis + jLongValue, job);
        lockFreeLinkedListHead.addLast(jobTask);
        process(jLongValue, lockFreeLinkedListHead, this.cancelled);
        if (!this.cancelled) {
            return jobTask;
        }
        jobTask.cancel();
        throw new CancellationException("Queue is cancelled");
    }

    public final void cancel() {
        this.cancelled = true;
        process();
    }

    public final int count$ktor_server_cio() {
        LockFreeLinkedListHead lockFreeLinkedListHead = this.head;
        int i10 = 0;
        for (LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) lockFreeLinkedListHead.getNext(); !kotlin.jvm.internal.p.a(nextNode, lockFreeLinkedListHead); nextNode = nextNode.getNextNode()) {
            if (nextNode instanceof Cancellable) {
                i10++;
            }
        }
        return i10;
    }

    public final long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    public final void process() {
        process(((Number) this.clock.invoke()).longValue(), this.head, this.cancelled);
    }

    public final <T> Object withTimeout(p<? super k0, ? super d<? super T>, ? extends Object> pVar, d<? super T> dVar) {
        Object objInvoke;
        e7.a aVar = e7.a.f15033i;
        if (!m0.m(dVar.getContext())) {
            checkCancellation(dVar);
        }
        d dVarA = t7.a.A(dVar);
        WeakTimeoutCoroutine weakTimeoutCoroutine = new WeakTimeoutCoroutine(dVarA.getContext(), dVarA, null, 4, null);
        Registration registrationRegister = register(weakTimeoutCoroutine);
        weakTimeoutCoroutine.invokeOnCompletion(new WeakTimeoutQueue$withTimeout$2$1(registrationRegister));
        try {
        } catch (Throwable th) {
            if (weakTimeoutCoroutine.tryComplete()) {
                registrationRegister.dispose();
                throw th;
            }
            objInvoke = aVar;
        }
        if (weakTimeoutCoroutine.isCancelled()) {
            throw weakTimeoutCoroutine.getCancellationException();
        }
        q0.e(2, pVar);
        objInvoke = pVar.invoke(weakTimeoutCoroutine, weakTimeoutCoroutine);
        if (objInvoke != aVar && weakTimeoutCoroutine.tryComplete()) {
            registrationRegister.dispose();
        }
        return objInvoke;
    }

    private final void process(long now, LockFreeLinkedListHead head, boolean cancelled) {
        while (true) {
            Object next = head.getNext();
            Cancellable cancellable = next instanceof Cancellable ? (Cancellable) next : null;
            if (cancellable == null) {
                return;
            }
            if (!cancelled && cancellable.getDeadline() > now) {
                return;
            }
            if (cancellable.isActive() && cancellable.remove()) {
                cancellable.cancel();
            }
        }
    }

    public WeakTimeoutQueue(long j10, a<Long> aVar) {
        this.timeoutMillis = j10;
        this.clock = aVar;
        this.head = new LockFreeLinkedListHead();
    }
}
