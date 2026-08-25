package androidx.compose.runtime;

import androidx.compose.runtime.Recomposer;
import com.google.common.util.concurrent.r0;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "runnerJobCause", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Recomposer$effectJob$1$1$1$1 extends r implements r7.l<Throwable, t0> {
    final /* synthetic */ Throwable $throwable;
    final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$effectJob$1$1$1$1(Recomposer recomposer, Throwable th) {
        super(1);
        this.this$0 = recomposer;
        this.$throwable = th;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    public final void invoke(Throwable th) {
        Object obj = this.this$0.stateLock;
        Recomposer recomposer = this.this$0;
        Throwable th2 = this.$throwable;
        synchronized (obj) {
            if (th2 == null) {
                th2 = null;
            } else if (th != null) {
                try {
                    if (th instanceof CancellationException) {
                        th = null;
                    }
                    if (th != null) {
                        r0.a(th2, th);
                    }
                } catch (Throwable th3) {
                    throw th3;
                }
            }
            recomposer.closeCause = th2;
            recomposer._state.setValue(Recomposer.State.ShutDown);
        }
    }
}
