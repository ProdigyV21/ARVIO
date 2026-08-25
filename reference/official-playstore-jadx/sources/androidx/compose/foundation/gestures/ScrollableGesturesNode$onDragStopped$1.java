package androidx.compose.foundation.gestures;

import androidx.compose.ui.unit.Velocity;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/ui/unit/Velocity;", "velocity", "Lx6/t0;", "<anonymous>", "(Lka/k0;Landroidx/compose/ui/unit/Velocity;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.ScrollableGesturesNode$onDragStopped$1", f = "Scrollable.kt", l = {}, m = "invokeSuspend")
public final class ScrollableGesturesNode$onDragStopped$1 extends j implements q<k0, Velocity, d7.d<? super t0>, Object> {
    /* synthetic */ long J$0;
    int label;
    final /* synthetic */ ScrollableGesturesNode this$0;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableGesturesNode$onDragStopped$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.ScrollableGesturesNode$onDragStopped$1$1", f = "Scrollable.kt", l = {612}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ long $velocity;
        int label;
        final /* synthetic */ ScrollableGesturesNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ScrollableGesturesNode scrollableGesturesNode, long j10, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = scrollableGesturesNode;
            this.$velocity = j10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.this$0, this.$velocity, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                ScrollingLogic scrollLogic = this.this$0.getScrollLogic();
                long j10 = this.$velocity;
                this.label = 1;
                Object objM375onDragStoppedsFctU = scrollLogic.m375onDragStoppedsFctU(j10, this);
                e7.a aVar = e7.a.f15033i;
                if (objM375onDragStoppedsFctU == aVar) {
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
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollableGesturesNode$onDragStopped$1(ScrollableGesturesNode scrollableGesturesNode, d7.d<? super ScrollableGesturesNode$onDragStopped$1> dVar) {
        super(3, dVar);
        this.this$0 = scrollableGesturesNode;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return m366invokeLuvzFrg((k0) obj, ((Velocity) obj2).getPackedValue(), (d7.d) obj3);
    }

    /* JADX INFO: renamed from: invoke-LuvzFrg, reason: not valid java name */
    public final Object m366invokeLuvzFrg(k0 k0Var, long j10, d7.d<? super t0> dVar) {
        ScrollableGesturesNode$onDragStopped$1 scrollableGesturesNode$onDragStopped$1 = new ScrollableGesturesNode$onDragStopped$1(this.this$0, dVar);
        scrollableGesturesNode$onDragStopped$1.J$0 = j10;
        return scrollableGesturesNode$onDragStopped$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        m0.p(this.this$0.getNestedScrollDispatcher().getCoroutineScope(), null, 0, new AnonymousClass1(this.this$0, this.J$0, null), 3);
        return t0.f22605a;
    }
}
