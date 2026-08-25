package io.ktor.util.pipeline;

import d7.d;
import d7.j;
import kotlin.Metadata;
import x6.c0;
import x6.d0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000=\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0015\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bH\u0016¢\u0006\u0004\b\t\u0010\nJ \u0010\r\u001a\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0018\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001c\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"io/ktor/util/pipeline/SuspendFunctionGun$continuation$1", "Ld7/d;", "Lx6/t0;", "Lf7/d;", "Lio/ktor/util/CoroutineStackFrame;", "peekContinuation", "()Ld7/d;", "Ljava/lang/StackTraceElement;", "Lio/ktor/util/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "Lx6/d0;", "result", "resumeWith", "(Ljava/lang/Object;)V", "", "currentIndex", "I", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "getCallerFrame", "()Lf7/d;", "callerFrame", "Ld7/j;", "getContext", "()Ld7/j;", "context", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SuspendFunctionGun$continuation$1 implements d<t0>, f7.d {
    private int currentIndex = Integer.MIN_VALUE;
    final /* synthetic */ SuspendFunctionGun<TSubject, TContext> this$0;

    public SuspendFunctionGun$continuation$1(SuspendFunctionGun<TSubject, TContext> suspendFunctionGun) {
        this.this$0 = suspendFunctionGun;
    }

    private final d<?> peekContinuation() {
        if (this.currentIndex == Integer.MIN_VALUE) {
            this.currentIndex = ((SuspendFunctionGun) this.this$0).lastSuspensionIndex;
        }
        if (this.currentIndex < 0) {
            this.currentIndex = Integer.MIN_VALUE;
            return null;
        }
        try {
            d<?>[] dVarArr = ((SuspendFunctionGun) this.this$0).suspensions;
            int i10 = this.currentIndex;
            d<?> dVar = dVarArr[i10];
            if (dVar == null) {
                return StackWalkingFailedFrame.INSTANCE;
            }
            this.currentIndex = i10 - 1;
            return dVar;
        } catch (Throwable unused) {
            return StackWalkingFailedFrame.INSTANCE;
        }
    }

    @Override // f7.d
    public f7.d getCallerFrame() {
        d<?> dVarPeekContinuation = peekContinuation();
        if (dVarPeekContinuation instanceof f7.d) {
            return (f7.d) dVarPeekContinuation;
        }
        return null;
    }

    @Override // d7.d
    public j getContext() {
        j context;
        d dVar = ((SuspendFunctionGun) this.this$0).suspensions[((SuspendFunctionGun) this.this$0).lastSuspensionIndex];
        if (dVar == null || (context = dVar.getContext()) == null) {
            throw new IllegalStateException("Not started");
        }
        return context;
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // d7.d
    public void resumeWith(Object result) {
        if (result instanceof c0) {
            this.this$0.resumeRootWith(new c0(d0.a(result)));
        } else {
            this.this$0.loop(false);
        }
    }

    public final void setCurrentIndex(int i10) {
        this.currentIndex = i10;
    }
}
