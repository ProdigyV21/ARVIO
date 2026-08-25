package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Latch$await$2$2 extends r implements r7.l<Throwable, t0> {
    final /* synthetic */ ka.k<t0> $co;
    final /* synthetic */ Latch this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Latch$await$2$2(Latch latch, ka.k<? super t0> kVar) {
        super(1);
        this.this$0 = latch;
        this.$co = kVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    public final void invoke(Throwable th) {
        Object obj = this.this$0.lock;
        Latch latch = this.this$0;
        ka.k<t0> kVar = this.$co;
        synchronized (obj) {
            latch.awaiters.remove(kVar);
        }
    }
}
