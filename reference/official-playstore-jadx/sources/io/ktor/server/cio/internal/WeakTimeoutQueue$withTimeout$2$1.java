package io.ktor.server.cio.internal;

import io.ktor.server.cio.internal.WeakTimeoutQueue;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class WeakTimeoutQueue$withTimeout$2$1 extends r implements l<Throwable, t0> {
    final /* synthetic */ WeakTimeoutQueue.Registration $handle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WeakTimeoutQueue$withTimeout$2$1(WeakTimeoutQueue.Registration registration) {
        super(1);
        this.$handle = registration;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    public final void invoke(Throwable th) {
        this.$handle.invoke(th);
    }
}
