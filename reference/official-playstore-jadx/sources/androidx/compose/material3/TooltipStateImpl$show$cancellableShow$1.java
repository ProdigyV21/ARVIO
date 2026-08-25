package androidx.compose.material3;

import f7.j;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.TooltipStateImpl$show$cancellableShow$1", f = "Tooltip.kt", l = {720}, m = "invokeSuspend")
public final class TooltipStateImpl$show$cancellableShow$1 extends j implements l<d7.d<? super t0>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ TooltipStateImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TooltipStateImpl$show$cancellableShow$1(TooltipStateImpl tooltipStateImpl, d7.d<? super TooltipStateImpl$show$cancellableShow$1> dVar) {
        super(1, dVar);
        this.this$0 = tooltipStateImpl;
    }

    @Override // f7.a
    public final d7.d<t0> create(d7.d<?> dVar) {
        return new TooltipStateImpl$show$cancellableShow$1(this.this$0, dVar);
    }

    @Override // r7.l
    public final Object invoke(d7.d<? super t0> dVar) {
        return ((TooltipStateImpl$show$cancellableShow$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            TooltipStateImpl tooltipStateImpl = this.this$0;
            this.L$0 = tooltipStateImpl;
            this.label = 1;
            ka.l lVar = new ka.l(1, t7.a.A(this));
            lVar.s();
            tooltipStateImpl.getTransition().setTargetState(Boolean.TRUE);
            tooltipStateImpl.job = lVar;
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
