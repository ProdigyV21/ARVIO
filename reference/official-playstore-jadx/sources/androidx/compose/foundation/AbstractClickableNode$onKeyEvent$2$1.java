package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.AbstractClickableNode$onKeyEvent$2$1", f = "Clickable.kt", l = {819}, m = "invokeSuspend")
public final class AbstractClickableNode$onKeyEvent$2$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ PressInteraction.Press $it;
    int label;
    final /* synthetic */ AbstractClickableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractClickableNode$onKeyEvent$2$1(AbstractClickableNode abstractClickableNode, PressInteraction.Press press, d<? super AbstractClickableNode$onKeyEvent$2$1> dVar) {
        super(2, dVar);
        this.this$0 = abstractClickableNode;
        this.$it = press;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new AbstractClickableNode$onKeyEvent$2$1(this.this$0, this.$it, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            MutableInteractionSource mutableInteractionSource = this.this$0.interactionSource;
            PressInteraction.Release release = new PressInteraction.Release(this.$it);
            this.label = 1;
            Object objEmit = mutableInteractionSource.emit(release, this);
            e7.a aVar = e7.a.f15033i;
            if (objEmit == aVar) {
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

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((AbstractClickableNode$onKeyEvent$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
