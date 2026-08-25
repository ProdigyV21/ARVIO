package io.ktor.utils.io.internal;

import d7.d;
import d7.j;
import d7.k;
import e7.a;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import k2.c;
import ka.u1;
import ka.v1;
import ka.z0;
import kotlin.Metadata;
import r7.l;
import x6.c0;
import x6.d0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001\"B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ!\u0010\r\u001a\u00020\b2\u0010\u0010\f\u001a\f0\u000bR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0019J\u001b\u0010\u001b\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ \u0010\u001f\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0017R\u0014\u0010\u0007\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lio/ktor/utils/io/internal/CancellableReusableContinuation;", "", "T", "Ld7/d;", "<init>", "()V", "Ld7/j;", "context", "Lx6/t0;", "parent", "(Ld7/j;)V", "Lio/ktor/utils/io/internal/CancellableReusableContinuation$JobRelation;", "relation", "notParent", "(Lio/ktor/utils/io/internal/CancellableReusableContinuation$JobRelation;)V", "Lka/v1;", "job", "", "exception", "resumeWithExceptionContinuationOnly", "(Lka/v1;Ljava/lang/Throwable;)V", "value", "close", "(Ljava/lang/Object;)V", "cause", "(Ljava/lang/Throwable;)V", "actual", "completeSuspendBlock", "(Ld7/d;)Ljava/lang/Object;", "Lx6/d0;", "result", "resumeWith", "getContext", "()Ld7/j;", "JobRelation", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CancellableReusableContinuation<T> implements d<T> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableReusableContinuation.class, Object.class, "state");
    private static final /* synthetic */ AtomicReferenceFieldUpdater jobCancellationHandler$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableReusableContinuation.class, Object.class, "jobCancellationHandler");
    private volatile /* synthetic */ Object state = null;
    private volatile /* synthetic */ Object jobCancellationHandler = null;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007B\u000f\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\f\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/utils/io/internal/CancellableReusableContinuation$JobRelation;", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "cause", "Lx6/t0;", "Lkotlinx/coroutines/CompletionHandler;", "Lka/v1;", "job", "<init>", "(Lio/ktor/utils/io/internal/CancellableReusableContinuation;Lka/v1;)V", "invoke", "(Ljava/lang/Throwable;)V", "dispose", "()V", "Lka/v1;", "getJob", "()Lka/v1;", "Lka/z0;", "handler", "Lka/z0;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class JobRelation implements l<Throwable, t0> {
        private z0 handler;
        private final v1 job;

        public JobRelation(v1 v1Var) {
            this.job = v1Var;
            z0 z0VarInvokeOnCompletion = v1Var.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, this);
            if (v1Var.isActive()) {
                this.handler = z0VarInvokeOnCompletion;
            }
        }

        public final void dispose() {
            z0 z0Var = this.handler;
            if (z0Var != null) {
                this.handler = null;
                z0Var.dispose();
            }
        }

        public final v1 getJob() {
            return this.job;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public void invoke(Throwable cause) {
            CancellableReusableContinuation.this.notParent(this);
            dispose();
            if (cause != null) {
                CancellableReusableContinuation.this.resumeWithExceptionContinuationOnly(this.job, cause);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notParent(CancellableReusableContinuation<T>.JobRelation relation) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = jobCancellationHandler$FU;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, relation, null) && atomicReferenceFieldUpdater.get(this) == relation) {
        }
    }

    private final void parent(j context) {
        v1 v1Var = (v1) context.get(u1.f19642i);
        JobRelation jobRelation = (JobRelation) this.jobCancellationHandler;
        if ((jobRelation != null ? jobRelation.getJob() : null) == v1Var) {
            return;
        }
        if (v1Var == null) {
            JobRelation jobRelation2 = (JobRelation) jobCancellationHandler$FU.getAndSet(this, null);
            if (jobRelation2 != null) {
                jobRelation2.dispose();
                return;
            }
            return;
        }
        JobRelation jobRelation3 = new JobRelation(v1Var);
        while (true) {
            Object obj = this.jobCancellationHandler;
            JobRelation jobRelation4 = (JobRelation) obj;
            if (jobRelation4 != null && jobRelation4.getJob() == v1Var) {
                jobRelation3.dispose();
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = jobCancellationHandler$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, jobRelation3)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            if (jobRelation4 != null) {
                jobRelation4.dispose();
                return;
            }
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeWithExceptionContinuationOnly(v1 job, Throwable exception) {
        while (true) {
            Object obj = this.state;
            if (obj instanceof d) {
                d dVar = (d) obj;
                if (dVar.getContext().get(u1.f19642i) != job) {
                    return;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$FU;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                dVar.resumeWith(new c0(exception));
                return;
            }
            return;
        }
    }

    public final void close(T value) {
        resumeWith(value);
        JobRelation jobRelation = (JobRelation) jobCancellationHandler$FU.getAndSet(this, null);
        if (jobRelation != null) {
            jobRelation.dispose();
        }
    }

    public final Object completeSuspendBlock(d<? super T> actual) {
        while (true) {
            Object obj = this.state;
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$FU;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, actual)) {
                    if (atomicReferenceFieldUpdater.get(this) != null) {
                        break;
                    }
                }
                parent(actual.getContext());
                return a.f15033i;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = state$FU;
            while (!atomicReferenceFieldUpdater2.compareAndSet(this, obj, null)) {
                if (atomicReferenceFieldUpdater2.get(this) != obj) {
                    break;
                }
            }
            if (obj instanceof Throwable) {
                throw ((Throwable) obj);
            }
            return obj;
        }
    }

    @Override // d7.d
    public j getContext() {
        j context;
        Object obj = this.state;
        d dVar = obj instanceof d ? (d) obj : null;
        return (dVar == null || (context = dVar.getContext()) == null) ? k.f14688i : context;
    }

    @Override // d7.d
    public void resumeWith(Object result) {
        Object objA;
        while (true) {
            Object obj = this.state;
            if (obj == null) {
                objA = d0.a(result);
                if (objA == null) {
                    c.G(result);
                    objA = result;
                }
            } else if (!(obj instanceof d)) {
                return;
            } else {
                objA = null;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, objA)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            if (obj instanceof d) {
                ((d) obj).resumeWith(result);
                return;
            }
            return;
        }
    }

    public final void close(Throwable cause) {
        resumeWith(new c0(cause));
        JobRelation jobRelation = (JobRelation) jobCancellationHandler$FU.getAndSet(this, null);
        if (jobRelation != null) {
            jobRelation.dispose();
        }
    }
}
