package androidx.compose.foundation;

import d7.d;
import f7.e;
import f7.j;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.BasicTooltipStateImpl$show$cancellableShow$1", f = "BasicTooltip.kt", l = {247}, m = "invokeSuspend")
public final class BasicTooltipStateImpl$show$cancellableShow$1 extends j implements l<d<? super t0>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ BasicTooltipStateImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicTooltipStateImpl$show$cancellableShow$1(BasicTooltipStateImpl basicTooltipStateImpl, d<? super BasicTooltipStateImpl$show$cancellableShow$1> dVar) {
        super(1, dVar);
        this.this$0 = basicTooltipStateImpl;
    }

    @Override // f7.a
    public final d<t0> create(d<?> dVar) {
        return new BasicTooltipStateImpl$show$cancellableShow$1(this.this$0, dVar);
    }

    @Override // r7.l
    public final Object invoke(d<? super t0> dVar) {
        return ((BasicTooltipStateImpl$show$cancellableShow$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            BasicTooltipStateImpl basicTooltipStateImpl = this.this$0;
            this.L$0 = basicTooltipStateImpl;
            this.label = 1;
            ka.l lVar = new ka.l(1, t7.a.A(this));
            lVar.s();
            basicTooltipStateImpl.setVisible(true);
            basicTooltipStateImpl.job = lVar;
            Object objR = lVar.r();
            e7.a aVar = e7.a.f15033i;
            if (objR == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }
}
