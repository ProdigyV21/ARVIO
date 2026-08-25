package androidx.compose.foundation.gestures;

import f7.j;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "it", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/AnchoredDragScope;Landroidx/compose/foundation/gestures/DraggableAnchors;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.AnchoredDraggableState$draggableState$1$drag$2", f = "AnchoredDraggable.kt", l = {282}, m = "invokeSuspend")
public final class AnchoredDraggableState$draggableState$1$drag$2<T> extends j implements q<AnchoredDragScope, DraggableAnchors<T>, d7.d<? super t0>, Object> {
    final /* synthetic */ p<DragScope, d7.d<? super t0>, Object> $block;
    int label;
    final /* synthetic */ AnchoredDraggableState$draggableState$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnchoredDraggableState$draggableState$1$drag$2(AnchoredDraggableState$draggableState$1 anchoredDraggableState$draggableState$1, p pVar, d7.d dVar) {
        super(3, dVar);
        this.this$0 = anchoredDraggableState$draggableState$1;
        this.$block = pVar;
    }

    @Override // r7.q
    public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, d7.d<? super t0> dVar) {
        return new AnchoredDraggableState$draggableState$1$drag$2(this.this$0, this.$block, dVar).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            AnchoredDraggableState$draggableState$1$dragScope$1 anchoredDraggableState$draggableState$1$dragScope$1 = this.this$0.dragScope;
            p<DragScope, d7.d<? super t0>, Object> pVar = this.$block;
            this.label = 1;
            Object objInvoke = pVar.invoke(anchoredDraggableState$draggableState$1$dragScope$1, this);
            e7.a aVar = e7.a.f15033i;
            if (objInvoke == aVar) {
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
