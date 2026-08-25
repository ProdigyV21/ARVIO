package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0001H\u0086@¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u0003J\u0015\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/ProduceFrameSignal;", "", "<init>", "()V", "lock", "Lx6/t0;", "awaitFrameRequest", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "takeFrameRequestLocked", "Ld7/d;", "requestFrameLocked", "()Ld7/d;", "pendingFrameContinuation", "Ljava/lang/Object;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ProduceFrameSignal {
    private Object pendingFrameContinuation;

    public final Object awaitFrameRequest(Object obj, d7.d<? super t0> dVar) {
        ka.l lVar;
        synchronized (obj) {
            if (this.pendingFrameContinuation == RecomposerKt.ProduceAnotherFrame) {
                this.pendingFrameContinuation = RecomposerKt.FramePending;
                return t0.f22605a;
            }
            ka.l lVar2 = new ka.l(1, t7.a.A(dVar));
            lVar2.s();
            synchronized (obj) {
                try {
                    if (this.pendingFrameContinuation == RecomposerKt.ProduceAnotherFrame) {
                        this.pendingFrameContinuation = RecomposerKt.FramePending;
                        lVar = lVar2;
                    } else {
                        this.pendingFrameContinuation = lVar2;
                        lVar = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (lVar != null) {
                lVar.resumeWith(t0.f22605a);
            }
            Object objR = lVar2.r();
            return objR == e7.a.f15033i ? objR : t0.f22605a;
        }
    }

    public final d7.d<t0> requestFrameLocked() {
        Object obj = this.pendingFrameContinuation;
        if (obj instanceof d7.d) {
            this.pendingFrameContinuation = RecomposerKt.FramePending;
            return (d7.d) obj;
        }
        if (p.a(obj, RecomposerKt.ProduceAnotherFrame) ? true : p.a(obj, RecomposerKt.FramePending)) {
            return null;
        }
        if (obj == null) {
            this.pendingFrameContinuation = RecomposerKt.ProduceAnotherFrame;
            return null;
        }
        throw new IllegalStateException(("invalid pendingFrameContinuation " + obj).toString());
    }

    public final void takeFrameRequestLocked() {
        if (!(this.pendingFrameContinuation == RecomposerKt.FramePending)) {
            PreconditionsKt.throwIllegalStateException("frame not pending");
        }
        this.pendingFrameContinuation = null;
    }
}
