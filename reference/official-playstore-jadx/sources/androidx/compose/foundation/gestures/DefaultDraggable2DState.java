package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.ui.geometry.Offset;
import f7.j;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007JA\u0010\u0010\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2'\u0010\u000f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\n¢\u0006\u0002\b\u000eH\u0096@¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0003H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/gestures/DefaultDraggable2DState;", "Landroidx/compose/foundation/gestures/Draggable2DState;", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/t0;", "onDelta", "<init>", "(Lr7/l;)V", "Landroidx/compose/foundation/MutatePriority;", "dragPriority", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/Drag2DScope;", "Ld7/d;", "", "Lx6/n;", "block", "drag", "(Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "delta", "dispatchRawDelta-k-4lQ0M", "(J)V", "dispatchRawDelta", "Lr7/l;", "getOnDelta", "()Lr7/l;", "drag2DScope", "Landroidx/compose/foundation/gestures/Drag2DScope;", "Landroidx/compose/foundation/MutatorMutex;", "drag2DMutex", "Landroidx/compose/foundation/MutatorMutex;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DefaultDraggable2DState implements Draggable2DState {
    private final l<Offset, t0> onDelta;
    private final Drag2DScope drag2DScope = new Drag2DScope() { // from class: androidx.compose.foundation.gestures.DefaultDraggable2DState$drag2DScope$1
        @Override // androidx.compose.foundation.gestures.Drag2DScope
        /* JADX INFO: renamed from: dragBy-k-4lQ0M, reason: not valid java name */
        public void mo301dragByk4lQ0M(long pixels) {
            this.this$0.getOnDelta().invoke(Offset.m3225boximpl(pixels));
        }
    };
    private final MutatorMutex drag2DMutex = new MutatorMutex();

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DefaultDraggable2DState$drag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DefaultDraggable2DState$drag$2", f = "Draggable2D.kt", l = {355}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ p<Drag2DScope, d7.d<? super t0>, Object> $block;
        final /* synthetic */ MutatePriority $dragPriority;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(MutatePriority mutatePriority, p<? super Drag2DScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$dragPriority = mutatePriority;
            this.$block = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return DefaultDraggable2DState.this.new AnonymousClass2(this.$dragPriority, this.$block, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MutatorMutex mutatorMutex = DefaultDraggable2DState.this.drag2DMutex;
                Drag2DScope drag2DScope = DefaultDraggable2DState.this.drag2DScope;
                MutatePriority mutatePriority = this.$dragPriority;
                p<Drag2DScope, d7.d<? super t0>, Object> pVar = this.$block;
                this.label = 1;
                Object objMutateWith = mutatorMutex.mutateWith(drag2DScope, mutatePriority, pVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objMutateWith == aVar) {
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
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DefaultDraggable2DState(l<? super Offset, t0> lVar) {
        this.onDelta = lVar;
    }

    @Override // androidx.compose.foundation.gestures.Draggable2DState
    /* JADX INFO: renamed from: dispatchRawDelta-k-4lQ0M, reason: not valid java name */
    public void mo300dispatchRawDeltak4lQ0M(long delta) {
        this.onDelta.invoke(Offset.m3225boximpl(delta));
    }

    @Override // androidx.compose.foundation.gestures.Draggable2DState
    public Object drag(MutatePriority mutatePriority, p<? super Drag2DScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super t0> dVar) {
        Object objC = l0.c(new AnonymousClass2(mutatePriority, pVar, null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }

    public final l<Offset, t0> getOnDelta() {
        return this.onDelta;
    }
}
