package androidx.compose.runtime;

import androidx.compose.runtime.Recomposer;
import java.util.concurrent.CancellationException;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "throwable", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Recomposer$effectJob$1$1 extends r implements r7.l<Throwable, t0> {
    final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$effectJob$1$1(Recomposer recomposer) {
        super(1);
        this.this$0 = recomposer;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    public final void invoke(Throwable th) {
        ka.k kVar;
        ka.k kVar2;
        CancellationException cancellationExceptionA = m0.a("Recomposer effect job completed", th);
        Object obj = this.this$0.stateLock;
        Recomposer recomposer = this.this$0;
        synchronized (obj) {
            try {
                v1 v1Var = recomposer.runnerJob;
                kVar = null;
                if (v1Var != null) {
                    recomposer._state.setValue(Recomposer.State.ShuttingDown);
                    if (recomposer.isClosed) {
                        if (recomposer.workContinuation != null) {
                            kVar2 = recomposer.workContinuation;
                        }
                        recomposer.workContinuation = null;
                        v1Var.invokeOnCompletion(new Recomposer$effectJob$1$1$1$1(recomposer, th));
                        kVar = kVar2;
                    } else {
                        v1Var.cancel(cancellationExceptionA);
                    }
                    kVar2 = null;
                    recomposer.workContinuation = null;
                    v1Var.invokeOnCompletion(new Recomposer$effectJob$1$1$1$1(recomposer, th));
                    kVar = kVar2;
                } else {
                    recomposer.closeCause = cancellationExceptionA;
                    recomposer._state.setValue(Recomposer.State.ShutDown);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        if (kVar != null) {
            kVar.resumeWith(t0.f22605a);
        }
    }
}
