package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import f7.j;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJA\u0010\u0013\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2'\u0010\u0012\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000e¢\u0006\u0002\b\u0011H\u0096@¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0016\u0010\u000bR\"\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u0006R\"\u0010\u001c\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006\""}, d2 = {"Landroidx/compose/foundation/gestures/ScrollDraggableState;", "Landroidx/compose/foundation/gestures/DraggableState;", "Landroidx/compose/foundation/gestures/DragScope;", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "scrollLogic", "<init>", "(Landroidx/compose/foundation/gestures/ScrollingLogic;)V", "", "pixels", "Lx6/t0;", "dragBy", "(F)V", "Landroidx/compose/foundation/MutatePriority;", "dragPriority", "Lkotlin/Function2;", "Ld7/d;", "", "Lx6/n;", "block", "drag", "(Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "delta", "dispatchRawDelta", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "getScrollLogic", "()Landroidx/compose/foundation/gestures/ScrollingLogic;", "setScrollLogic", "Landroidx/compose/foundation/gestures/ScrollScope;", "latestScrollScope", "Landroidx/compose/foundation/gestures/ScrollScope;", "getLatestScrollScope", "()Landroidx/compose/foundation/gestures/ScrollScope;", "setLatestScrollScope", "(Landroidx/compose/foundation/gestures/ScrollScope;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ScrollDraggableState implements DraggableState, DragScope {
    private ScrollScope latestScrollScope = ScrollableKt.NoOpScrollScope;
    private ScrollingLogic scrollLogic;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollDraggableState$drag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.ScrollDraggableState$drag$2", f = "Scrollable.kt", l = {894}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ScrollScope, d7.d<? super t0>, Object> {
        final /* synthetic */ p<DragScope, d7.d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(p<? super DragScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$block = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = ScrollDraggableState.this.new AnonymousClass2(this.$block, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ScrollScope scrollScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                ScrollDraggableState.this.setLatestScrollScope((ScrollScope) this.L$0);
                p<DragScope, d7.d<? super t0>, Object> pVar = this.$block;
                ScrollDraggableState scrollDraggableState = ScrollDraggableState.this;
                this.label = 1;
                Object objInvoke = pVar.invoke(scrollDraggableState, this);
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

    public ScrollDraggableState(ScrollingLogic scrollingLogic) {
        this.scrollLogic = scrollingLogic;
    }

    @Override // androidx.compose.foundation.gestures.DraggableState
    public void dispatchRawDelta(float delta) {
        ScrollingLogic scrollingLogic = this.scrollLogic;
        scrollingLogic.m376performRawScrollMKHz9U(scrollingLogic.m382toOffsettuRUvjQ(delta));
    }

    @Override // androidx.compose.foundation.gestures.DraggableState
    public Object drag(MutatePriority mutatePriority, p<? super DragScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super t0> dVar) {
        Object objScroll = this.scrollLogic.getScrollableState().scroll(mutatePriority, new AnonymousClass2(pVar, null), dVar);
        return objScroll == e7.a.f15033i ? objScroll : t0.f22605a;
    }

    @Override // androidx.compose.foundation.gestures.DragScope
    public void dragBy(float pixels) {
        ScrollingLogic scrollingLogic = this.scrollLogic;
        scrollingLogic.m373dispatchScroll3eAAhYA(this.latestScrollScope, scrollingLogic.m382toOffsettuRUvjQ(pixels), NestedScrollSource.INSTANCE.m4425getDragWNlRxjI());
    }

    public final ScrollScope getLatestScrollScope() {
        return this.latestScrollScope;
    }

    public final ScrollingLogic getScrollLogic() {
        return this.scrollLogic;
    }

    public final void setLatestScrollScope(ScrollScope scrollScope) {
        this.latestScrollScope = scrollScope;
    }

    public final void setScrollLogic(ScrollingLogic scrollingLogic) {
        this.scrollLogic = scrollingLogic;
    }
}
