package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.ScrollableNode$onKeyEvent$1$1", f = "Scrollable.kt", l = {442}, m = "invokeSuspend")
public final class ScrollableNode$onKeyEvent$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ long $scrollAmount;
    final /* synthetic */ ScrollingLogic $this_with;
    int label;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$onKeyEvent$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.ScrollableNode$onKeyEvent$1$1$1", f = "Scrollable.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<ScrollScope, d7.d<? super t0>, Object> {
        final /* synthetic */ long $scrollAmount;
        final /* synthetic */ ScrollingLogic $this_with;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ScrollingLogic scrollingLogic, long j10, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$this_with = scrollingLogic;
            this.$scrollAmount = j10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_with, this.$scrollAmount, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(ScrollScope scrollScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            this.$this_with.m373dispatchScroll3eAAhYA((ScrollScope) this.L$0, this.$scrollAmount, NestedScrollSource.INSTANCE.m4428getWheelWNlRxjI());
            return t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollableNode$onKeyEvent$1$1(ScrollingLogic scrollingLogic, long j10, d7.d<? super ScrollableNode$onKeyEvent$1$1> dVar) {
        super(2, dVar);
        this.$this_with = scrollingLogic;
        this.$scrollAmount = j10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new ScrollableNode$onKeyEvent$1$1(this.$this_with, this.$scrollAmount, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            ScrollableState scrollableState = this.$this_with.getScrollableState();
            MutatePriority mutatePriority = MutatePriority.UserInput;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_with, this.$scrollAmount, null);
            this.label = 1;
            Object objScroll = scrollableState.scroll(mutatePriority, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (objScroll == aVar) {
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
        return ((ScrollableNode$onKeyEvent$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
