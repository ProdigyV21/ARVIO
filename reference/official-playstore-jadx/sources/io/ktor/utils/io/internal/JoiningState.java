package io.ktor.utils.io.internal;

import d7.d;
import e7.a;
import io.ktor.utils.io.ByteBufferChannel;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.m0;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/utils/io/internal/JoiningState;", "", "Lio/ktor/utils/io/ByteBufferChannel;", "delegatedTo", "", "delegateClose", "<init>", "(Lio/ktor/utils/io/ByteBufferChannel;Z)V", "Lx6/t0;", "complete", "()V", "awaitClose", "(Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteBufferChannel;", "getDelegatedTo", "()Lio/ktor/utils/io/ByteBufferChannel;", "Z", "getDelegateClose", "()Z", "Lka/v1;", "getCloseWaitJob", "()Lka/v1;", "closeWaitJob", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class JoiningState {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closeWaitJob$FU = AtomicReferenceFieldUpdater.newUpdater(JoiningState.class, Object.class, "_closeWaitJob");
    private volatile /* synthetic */ Object _closeWaitJob = null;
    private volatile /* synthetic */ int closed = 0;
    private final boolean delegateClose;
    private final ByteBufferChannel delegatedTo;

    public JoiningState(ByteBufferChannel byteBufferChannel, boolean z) {
        this.delegatedTo = byteBufferChannel;
        this.delegateClose = z;
    }

    private final v1 getCloseWaitJob() throws IllegalAccessException, InvocationTargetException {
        while (true) {
            v1 v1Var = (v1) this._closeWaitJob;
            if (v1Var != null) {
                return v1Var;
            }
            x1 x1VarC = m0.c();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _closeWaitJob$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, x1VarC)) {
                if (atomicReferenceFieldUpdater.get(this) != null) {
                    break;
                }
            }
            if (this.closed == 1) {
                x1VarC.cancel((CancellationException) null);
            }
            return x1VarC;
        }
    }

    public final Object awaitClose(d<? super t0> dVar) {
        Object objJoin;
        t0 t0Var = t0.f22605a;
        return (this.closed != 1 && (objJoin = getCloseWaitJob().join(dVar)) == a.f15033i) ? objJoin : t0Var;
    }

    public final void complete() {
        this.closed = 1;
        v1 v1Var = (v1) _closeWaitJob$FU.getAndSet(this, null);
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
    }

    public final boolean getDelegateClose() {
        return this.delegateClose;
    }

    public final ByteBufferChannel getDelegatedTo() {
        return this.delegatedTo;
    }
}
