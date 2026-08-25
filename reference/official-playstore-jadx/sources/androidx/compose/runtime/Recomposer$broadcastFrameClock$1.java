package androidx.compose.runtime;

import androidx.compose.runtime.Recomposer;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Recomposer$broadcastFrameClock$1 extends r implements r7.a<t0> {
    final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$broadcastFrameClock$1(Recomposer recomposer) {
        super(0);
        this.this$0 = recomposer;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m2976invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m2976invoke() {
        ka.k kVarDeriveStateLocked;
        Object obj = this.this$0.stateLock;
        Recomposer recomposer = this.this$0;
        synchronized (obj) {
            kVarDeriveStateLocked = recomposer.deriveStateLocked();
            if (((Recomposer.State) recomposer._state.getValue()).compareTo(Recomposer.State.ShuttingDown) <= 0) {
                throw m0.a("Recomposer shutdown; frame clock awaiter will never resume", recomposer.closeCause);
            }
        }
        if (kVarDeriveStateLocked != null) {
            kVarDeriveStateLocked.resumeWith(t0.f22605a);
        }
    }
}
