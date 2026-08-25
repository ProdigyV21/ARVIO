package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import f7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.j0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2", f = "Scrollable.kt", l = {844}, m = "invokeSuspend")
public final class ScrollingLogic$doFlingAnimation$2 extends j implements p<ScrollScope, d7.d<? super t0>, Object> {
    final /* synthetic */ long $available;
    final /* synthetic */ j0 $result;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLogic$doFlingAnimation$2(ScrollingLogic scrollingLogic, j0 j0Var, long j10, d7.d<? super ScrollingLogic$doFlingAnimation$2> dVar) {
        super(2, dVar);
        this.this$0 = scrollingLogic;
        this.$result = j0Var;
        this.$available = j10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        ScrollingLogic$doFlingAnimation$2 scrollingLogic$doFlingAnimation$2 = new ScrollingLogic$doFlingAnimation$2(this.this$0, this.$result, this.$available, dVar);
        scrollingLogic$doFlingAnimation$2.L$0 = obj;
        return scrollingLogic$doFlingAnimation$2;
    }

    @Override // r7.p
    public final Object invoke(ScrollScope scrollScope, d7.d<? super t0> dVar) {
        return ((ScrollingLogic$doFlingAnimation$2) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ScrollingLogic scrollingLogic;
        j0 j0Var;
        ScrollingLogic scrollingLogic2;
        long j10;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            final ScrollingLogic$doFlingAnimation$2$outerScopeScroll$1 scrollingLogic$doFlingAnimation$2$outerScopeScroll$1 = new ScrollingLogic$doFlingAnimation$2$outerScopeScroll$1(this.this$0, (ScrollScope) this.L$0);
            final ScrollingLogic scrollingLogic3 = this.this$0;
            ScrollScope scrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$scope$1
                @Override // androidx.compose.foundation.gestures.ScrollScope
                public float scrollBy(float pixels) {
                    ScrollingLogic scrollingLogic4 = scrollingLogic3;
                    return scrollingLogic4.m381toFloatk4lQ0M(((Offset) scrollingLogic$doFlingAnimation$2$outerScopeScroll$1.invoke(Offset.m3225boximpl(scrollingLogic4.m382toOffsettuRUvjQ(pixels)))).getPackedValue());
                }
            };
            scrollingLogic = this.this$0;
            j0Var = this.$result;
            long j11 = this.$available;
            FlingBehavior flingBehavior = scrollingLogic.flingBehavior;
            long j12 = j0Var.f19745i;
            float fReverseIfNeeded = scrollingLogic.reverseIfNeeded(scrollingLogic.m380toFloatTH1AsA0(j11));
            this.L$0 = scrollingLogic;
            this.L$1 = scrollingLogic;
            this.L$2 = j0Var;
            this.J$0 = j12;
            this.label = 1;
            obj = flingBehavior.performFling(scrollScope, fReverseIfNeeded, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
            scrollingLogic2 = scrollingLogic;
            j10 = j12;
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j10 = this.J$0;
            j0Var = (j0) this.L$2;
            scrollingLogic = (ScrollingLogic) this.L$1;
            scrollingLogic2 = (ScrollingLogic) this.L$0;
            k2.c.G(obj);
        }
        j0Var.f19745i = scrollingLogic.m383updateQWom1Mo(j10, scrollingLogic2.reverseIfNeeded(((Number) obj).floatValue()));
        return t0.f22605a;
    }
}
