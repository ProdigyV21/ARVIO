package io.ktor.client.plugins.websocket;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BuildersKt$webSocketSession$2$1$1 extends r implements l<Throwable, t0> {
    final /* synthetic */ ka.r<t0> $sessionCompleted;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BuildersKt$webSocketSession$2$1$1(ka.r<t0> rVar) {
        super(1);
        this.$sessionCompleted = rVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    public final void invoke(Throwable th) {
        if (th != null) {
            this.$sessionCompleted.i(th);
        } else {
            this.$sessionCompleted.R(t0.f22605a);
        }
    }
}
