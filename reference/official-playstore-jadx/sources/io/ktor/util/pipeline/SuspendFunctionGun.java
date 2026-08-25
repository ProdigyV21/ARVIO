package io.ktor.util.pipeline;

import d7.d;
import d7.j;
import e7.a;
import java.util.List;
import kotlin.Metadata;
import r7.q;
import x6.c0;
import x6.d0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004Ba\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0001\u0012H\u0010\f\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u000b0\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0015\u001a\u00020\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001a\u001a\u00020\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001c\u0010\u0018J\u0013\u0010\u001d\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010 \u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u001b\u0010#\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u0000H\u0090@ø\u0001\u0000¢\u0006\u0004\b\"\u0010!RV\u0010\f\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u000b0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010$R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010%R\"\u0010\u001f\u001a\u00028\u00008\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010\u0016R\"\u0010+\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010/R\u0014\u00104\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u00103\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, d2 = {"Lio/ktor/util/pipeline/SuspendFunctionGun;", "", "TSubject", "TContext", "Lio/ktor/util/pipeline/PipelineContext;", "initial", "context", "", "Lkotlin/Function3;", "Ld7/d;", "Lx6/t0;", "Lio/ktor/util/pipeline/PipelineInterceptorFunction;", "blocks", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/List;)V", "", "direct", "loop", "(Z)Z", "Lx6/d0;", "result", "resumeRootWith", "(Ljava/lang/Object;)V", "discardLastRootContinuation", "()V", "continuation", "addContinuation", "(Ld7/d;)V", "finish", "proceed", "(Ld7/d;)Ljava/lang/Object;", "subject", "proceedWith", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "execute$ktor_utils", "execute", "Ljava/util/List;", "Ld7/d;", "Ljava/lang/Object;", "getSubject", "()Ljava/lang/Object;", "setSubject", "", "suspensions", "[Ld7/d;", "", "lastSuspensionIndex", "I", "index", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SuspendFunctionGun<TSubject, TContext> extends PipelineContext<TSubject, TContext> {
    private final List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> blocks;
    private final d<t0> continuation;
    private int index;
    private int lastSuspensionIndex;
    private TSubject subject;
    private final d<TSubject>[] suspensions;

    /* JADX WARN: Multi-variable type inference failed */
    public SuspendFunctionGun(TSubject tsubject, TContext tcontext, List<? extends q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object>> list) {
        super(tcontext);
        this.blocks = list;
        this.continuation = new SuspendFunctionGun$continuation$1(this);
        this.subject = tsubject;
        this.suspensions = new d[list.size()];
        this.lastSuspensionIndex = -1;
    }

    private final void addContinuation(d<? super TSubject> continuation) {
        d<TSubject>[] dVarArr = this.suspensions;
        int i10 = this.lastSuspensionIndex + 1;
        this.lastSuspensionIndex = i10;
        dVarArr[i10] = continuation;
    }

    private final void discardLastRootContinuation() {
        int i10 = this.lastSuspensionIndex;
        if (i10 < 0) {
            throw new IllegalStateException("No more continuations to resume");
        }
        d<TSubject>[] dVarArr = this.suspensions;
        this.lastSuspensionIndex = i10 - 1;
        dVarArr[i10] = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loop(boolean direct) {
        int i10;
        do {
            i10 = this.index;
            if (i10 == this.blocks.size()) {
                if (direct) {
                    return true;
                }
                resumeRootWith(getSubject());
                return false;
            }
            this.index = i10 + 1;
            try {
            } catch (Throwable th) {
                resumeRootWith(new c0(th));
                return false;
            }
        } while (this.blocks.get(i10).invoke(this, getSubject(), this.continuation) != a.f15033i);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeRootWith(Object result) {
        int i10 = this.lastSuspensionIndex;
        if (i10 < 0) {
            throw new IllegalStateException("No more continuations to resume");
        }
        d<TSubject>[] dVarArr = this.suspensions;
        d<TSubject> dVar = dVarArr[i10];
        this.lastSuspensionIndex = i10 - 1;
        dVarArr[i10] = null;
        if (result instanceof c0) {
            dVar.resumeWith(new c0(StackTraceRecoverKt.recoverStackTraceBridge(d0.a(result), dVar)));
        } else {
            dVar.resumeWith(result);
        }
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object execute$ktor_utils(TSubject tsubject, d<? super TSubject> dVar) {
        this.index = 0;
        if (this.blocks.size() == 0) {
            return tsubject;
        }
        setSubject(tsubject);
        if (this.lastSuspensionIndex < 0) {
            return proceed(dVar);
        }
        throw new IllegalStateException("Already started");
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public void finish() {
        this.index = this.blocks.size();
    }

    @Override // io.ktor.util.pipeline.PipelineContext, ka.k0
    public j getCoroutineContext() {
        return this.continuation.getContext();
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public TSubject getSubject() {
        return this.subject;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceed(d<? super TSubject> dVar) {
        if (this.index == this.blocks.size()) {
            return getSubject();
        }
        addContinuation(t7.a.A(dVar));
        if (!loop(true)) {
            return a.f15033i;
        }
        discardLastRootContinuation();
        return getSubject();
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceedWith(TSubject tsubject, d<? super TSubject> dVar) {
        setSubject(tsubject);
        return proceed(dVar);
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public void setSubject(TSubject tsubject) {
        this.subject = tsubject;
    }
}
