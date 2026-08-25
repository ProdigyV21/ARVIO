package androidx.compose.foundation;

import androidx.compose.ui.input.pointer.PointerInputScope;
import d7.d;
import f7.e;
import f7.j;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.AbstractClickablePointerInputNode$pointerInputNode$1", f = "Clickable.kt", l = {938}, m = "invokeSuspend")
public final class AbstractClickablePointerInputNode$pointerInputNode$1 extends j implements p<PointerInputScope, d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AbstractClickablePointerInputNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractClickablePointerInputNode$pointerInputNode$1(AbstractClickablePointerInputNode abstractClickablePointerInputNode, d<? super AbstractClickablePointerInputNode$pointerInputNode$1> dVar) {
        super(2, dVar);
        this.this$0 = abstractClickablePointerInputNode;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        AbstractClickablePointerInputNode$pointerInputNode$1 abstractClickablePointerInputNode$pointerInputNode$1 = new AbstractClickablePointerInputNode$pointerInputNode$1(this.this$0, dVar);
        abstractClickablePointerInputNode$pointerInputNode$1.L$0 = obj;
        return abstractClickablePointerInputNode$pointerInputNode$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
        return ((AbstractClickablePointerInputNode$pointerInputNode$1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            AbstractClickablePointerInputNode abstractClickablePointerInputNode = this.this$0;
            this.label = 1;
            Object objPointerInput = abstractClickablePointerInputNode.pointerInput(pointerInputScope, this);
            e7.a aVar = e7.a.f15033i;
            if (objPointerInput == aVar) {
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
