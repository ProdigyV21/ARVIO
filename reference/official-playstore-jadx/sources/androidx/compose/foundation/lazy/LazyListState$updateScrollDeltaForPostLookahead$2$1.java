package androidx.compose.foundation.lazy;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.lazy.LazyListState$updateScrollDeltaForPostLookahead$2$1", f = "LazyListState.kt", l = {495}, m = "invokeSuspend")
public final class LazyListState$updateScrollDeltaForPostLookahead$2$1 extends j implements p<k0, d<? super t0>, Object> {
    int label;
    final /* synthetic */ LazyListState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyListState$updateScrollDeltaForPostLookahead$2$1(LazyListState lazyListState, d<? super LazyListState$updateScrollDeltaForPostLookahead$2$1> dVar) {
        super(2, dVar);
        this.this$0 = lazyListState;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new LazyListState$updateScrollDeltaForPostLookahead$2$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            AnimationState animationState = this.this$0._scrollDeltaBetweenPasses;
            Float f10 = new Float(0.0f);
            SpringSpec springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 400.0f, new Float(0.5f), 1, null);
            this.label = 1;
            Object objAnimateTo$default = SuspendAnimationKt.animateTo$default(animationState, f10, springSpecSpring$default, true, null, this, 8, null);
            e7.a aVar = e7.a.f15033i;
            if (objAnimateTo$default == aVar) {
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
        return ((LazyListState$updateScrollDeltaForPostLookahead$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
