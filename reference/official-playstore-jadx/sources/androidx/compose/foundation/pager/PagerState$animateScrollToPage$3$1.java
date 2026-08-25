package androidx.compose.foundation.pager;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope;
import d7.d;
import f7.e;
import f7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1", f = "PagerState.kt", l = {552}, m = "invokeSuspend")
public final class PagerState$animateScrollToPage$3$1 extends j implements p<ScrollScope, d<? super t0>, Object> {
    final /* synthetic */ AnimationSpec<Float> $animationSpec;
    final /* synthetic */ int $targetPage;
    final /* synthetic */ int $targetPageOffsetToSnappedPosition;
    final /* synthetic */ LazyLayoutAnimateScrollScope $this_with;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PagerState this$0;

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "currentValue", "<anonymous parameter 1>", "Lx6/t0;", "invoke", "(FF)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements p<Float, Float, t0> {
        final /* synthetic */ ScrollScope $$this$scroll;
        final /* synthetic */ h0 $previousValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(h0 h0Var, ScrollScope scrollScope) {
            super(2);
            this.$previousValue = h0Var;
            this.$$this$scroll = scrollScope;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue());
            return t0.f22605a;
        }

        public final void invoke(float f10, float f11) {
            this.$previousValue.f19742i += this.$$this$scroll.scrollBy(f10 - this.$previousValue.f19742i);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerState$animateScrollToPage$3$1(PagerState pagerState, int i10, LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i11, AnimationSpec<Float> animationSpec, d<? super PagerState$animateScrollToPage$3$1> dVar) {
        super(2, dVar);
        this.this$0 = pagerState;
        this.$targetPage = i10;
        this.$this_with = lazyLayoutAnimateScrollScope;
        this.$targetPageOffsetToSnappedPosition = i11;
        this.$animationSpec = animationSpec;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        PagerState$animateScrollToPage$3$1 pagerState$animateScrollToPage$3$1 = new PagerState$animateScrollToPage$3$1(this.this$0, this.$targetPage, this.$this_with, this.$targetPageOffsetToSnappedPosition, this.$animationSpec, dVar);
        pagerState$animateScrollToPage$3$1.L$0 = obj;
        return pagerState$animateScrollToPage$3$1;
    }

    @Override // r7.p
    public final Object invoke(ScrollScope scrollScope, d<? super t0> dVar) {
        return ((PagerState$animateScrollToPage$3$1) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0073, code lost:
    
        r0 = r2;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            int r0 = r13.label
            r1 = 1
            if (r0 == 0) goto L14
            if (r0 != r1) goto Lc
            k2.c.G(r14)
            goto Lc1
        Lc:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L14:
            k2.c.G(r14)
            java.lang.Object r14 = r13.L$0
            androidx.compose.foundation.gestures.ScrollScope r14 = (androidx.compose.foundation.gestures.ScrollScope) r14
            androidx.compose.foundation.pager.PagerState r0 = r13.this$0
            int r2 = r13.$targetPage
            r0.updateTargetPage(r14, r2)
            int r0 = r13.$targetPage
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r2 = r13.$this_with
            int r2 = r2.getFirstVisibleItemIndex()
            r3 = 0
            if (r0 <= r2) goto L2f
            r0 = r1
            goto L30
        L2f:
            r0 = r3
        L30:
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r2 = r13.$this_with
            int r2 = r2.getLastVisibleItemIndex()
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r4 = r13.$this_with
            int r4 = r4.getFirstVisibleItemIndex()
            int r2 = r2 - r4
            int r2 = r2 + r1
            if (r0 == 0) goto L4a
            int r4 = r13.$targetPage
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r5 = r13.$this_with
            int r5 = r5.getLastVisibleItemIndex()
            if (r4 > r5) goto L56
        L4a:
            if (r0 != 0) goto L86
            int r4 = r13.$targetPage
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r5 = r13.$this_with
            int r5 = r5.getFirstVisibleItemIndex()
            if (r4 >= r5) goto L86
        L56:
            int r4 = r13.$targetPage
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r5 = r13.$this_with
            int r5 = r5.getFirstVisibleItemIndex()
            int r4 = r4 - r5
            int r4 = java.lang.Math.abs(r4)
            r5 = 3
            if (r4 < r5) goto L86
            if (r0 == 0) goto L75
            int r0 = r13.$targetPage
            int r0 = r0 - r2
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r2 = r13.$this_with
            int r2 = r2.getFirstVisibleItemIndex()
            if (r0 >= r2) goto L81
        L73:
            r0 = r2
            goto L81
        L75:
            int r0 = r13.$targetPage
            int r0 = r0 + r2
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r2 = r13.$this_with
            int r2 = r2.getFirstVisibleItemIndex()
            if (r0 <= r2) goto L81
            goto L73
        L81:
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r2 = r13.$this_with
            r2.snapToItem(r14, r0, r3)
        L86:
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope r0 = r13.$this_with
            int r0 = r0.getVisibleItemsAverageSize()
            androidx.compose.foundation.pager.PagerState r2 = r13.this$0
            int r2 = r2.getCurrentPage()
            int r3 = r13.$targetPage
            int r3 = r3 * r0
            int r2 = r2 * r0
            float r0 = (float) r0
            androidx.compose.foundation.pager.PagerState r4 = r13.this$0
            float r4 = r4.getCurrentPageOffsetFraction()
            float r4 = r4 * r0
            int r3 = r3 - r2
            int r0 = r13.$targetPageOffsetToSnappedPosition
            int r3 = r3 + r0
            float r0 = (float) r3
            float r6 = r0 - r4
            kotlin.jvm.internal.h0 r0 = new kotlin.jvm.internal.h0
            r0.<init>()
            androidx.compose.animation.core.AnimationSpec<java.lang.Float> r8 = r13.$animationSpec
            androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1$3 r9 = new androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1$3
            r9.<init>(r0, r14)
            r13.label = r1
            r5 = 0
            r7 = 0
            r11 = 4
            r12 = 0
            r10 = r13
            java.lang.Object r14 = androidx.compose.animation.core.SuspendAnimationKt.animate$default(r5, r6, r7, r8, r9, r10, r11, r12)
            e7.a r0 = e7.a.f15033i
            if (r14 != r0) goto Lc1
            return r0
        Lc1:
            x6.t0 r14 = x6.t0.f22605a
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
