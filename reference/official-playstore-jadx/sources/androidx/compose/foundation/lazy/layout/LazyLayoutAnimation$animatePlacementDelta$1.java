package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animatePlacementDelta$1", f = "LazyLayoutAnimation.kt", l = {127, 133}, m = "invokeSuspend")
public final class LazyLayoutAnimation$animatePlacementDelta$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ FiniteAnimationSpec<IntOffset> $spec;
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutAnimation this$0;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animatePlacementDelta$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/ui/unit/IntOffset;", "Landroidx/compose/animation/core/AnimationVector2D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/Animatable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Animatable<IntOffset, AnimationVector2D>, t0> {
        final /* synthetic */ long $animationTarget;
        final /* synthetic */ LazyLayoutAnimation this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LazyLayoutAnimation lazyLayoutAnimation, long j10) {
            super(1);
            this.this$0 = lazyLayoutAnimation;
            this.$animationTarget = j10;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animatable<IntOffset, AnimationVector2D>) obj);
            return t0.f22605a;
        }

        public final void invoke(Animatable<IntOffset, AnimationVector2D> animatable) {
            LazyLayoutAnimation lazyLayoutAnimation = this.this$0;
            long packedValue = animatable.getValue().getPackedValue();
            long j10 = this.$animationTarget;
            lazyLayoutAnimation.m674setPlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(packedValue) - IntOffset.m5802getXimpl(j10), IntOffset.m5803getYimpl(packedValue) - IntOffset.m5803getYimpl(j10)));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutAnimation$animatePlacementDelta$1(LazyLayoutAnimation lazyLayoutAnimation, FiniteAnimationSpec<IntOffset> finiteAnimationSpec, long j10, d7.d<? super LazyLayoutAnimation$animatePlacementDelta$1> dVar) {
        super(2, dVar);
        this.this$0 = lazyLayoutAnimation;
        this.$spec = finiteAnimationSpec;
        this.$totalDelta = j10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new LazyLayoutAnimation$animatePlacementDelta$1(this.this$0, this.$spec, this.$totalDelta, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|37|(1:(1:(5:6|7|33|34|35)(2:9|10))(2:11|12))(8:14|15|(2:17|(1:19)(1:20))(1:22)|21|23|(3:25|(1:27)|32)|34|35)|28|29|38|30) */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b4, code lost:
    
        if (androidx.compose.animation.core.Animatable.animateTo$default(r13, r5, r6, null, r8, r9, 4, null) == r3) goto L32;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            int r0 = r12.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L25
            if (r0 == r2) goto L1d
            if (r0 != r1) goto L15
            k2.c.G(r13)     // Catch: java.util.concurrent.CancellationException -> L12
            r9 = r12
            goto Lb7
        L12:
            r9 = r12
            goto Lbd
        L15:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L1d:
            java.lang.Object r0 = r12.L$0
            androidx.compose.animation.core.FiniteAnimationSpec r0 = (androidx.compose.animation.core.FiniteAnimationSpec) r0
            k2.c.G(r13)     // Catch: java.util.concurrent.CancellationException -> L12
            goto L6a
        L25:
            k2.c.G(r13)
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r13 = r12.this$0     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.animation.core.Animatable r13 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$getPlacementDeltaAnimation$p(r13)     // Catch: java.util.concurrent.CancellationException -> L12
            boolean r13 = r13.isRunning()     // Catch: java.util.concurrent.CancellationException -> L12
            if (r13 == 0) goto L43
            androidx.compose.animation.core.FiniteAnimationSpec<androidx.compose.ui.unit.IntOffset> r13 = r12.$spec     // Catch: java.util.concurrent.CancellationException -> L12
            boolean r0 = r13 instanceof androidx.compose.animation.core.SpringSpec     // Catch: java.util.concurrent.CancellationException -> L12
            if (r0 == 0) goto L3d
            androidx.compose.animation.core.SpringSpec r13 = (androidx.compose.animation.core.SpringSpec) r13     // Catch: java.util.concurrent.CancellationException -> L12
            goto L41
        L3d:
            androidx.compose.animation.core.SpringSpec r13 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimationKt.access$getInterruptionSpec$p()     // Catch: java.util.concurrent.CancellationException -> L12
        L41:
            r0 = r13
            goto L46
        L43:
            androidx.compose.animation.core.FiniteAnimationSpec<androidx.compose.ui.unit.IntOffset> r13 = r12.$spec     // Catch: java.util.concurrent.CancellationException -> L12
            goto L41
        L46:
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r13 = r12.this$0     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.animation.core.Animatable r13 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$getPlacementDeltaAnimation$p(r13)     // Catch: java.util.concurrent.CancellationException -> L12
            boolean r13 = r13.isRunning()     // Catch: java.util.concurrent.CancellationException -> L12
            if (r13 != 0) goto L6a
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r13 = r12.this$0     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.animation.core.Animatable r13 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$getPlacementDeltaAnimation$p(r13)     // Catch: java.util.concurrent.CancellationException -> L12
            long r4 = r12.$totalDelta     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.ui.unit.IntOffset r4 = androidx.compose.ui.unit.IntOffset.m5793boximpl(r4)     // Catch: java.util.concurrent.CancellationException -> L12
            r12.L$0 = r0     // Catch: java.util.concurrent.CancellationException -> L12
            r12.label = r2     // Catch: java.util.concurrent.CancellationException -> L12
            java.lang.Object r13 = r13.snapTo(r4, r12)     // Catch: java.util.concurrent.CancellationException -> L12
            if (r13 != r3) goto L6a
            r9 = r12
            goto Lb6
        L6a:
            r6 = r0
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r13 = r12.this$0     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.animation.core.Animatable r13 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$getPlacementDeltaAnimation$p(r13)     // Catch: java.util.concurrent.CancellationException -> L12
            java.lang.Object r13 = r13.getValue()     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.ui.unit.IntOffset r13 = (androidx.compose.ui.unit.IntOffset) r13     // Catch: java.util.concurrent.CancellationException -> L12
            long r4 = r13.getPackedValue()     // Catch: java.util.concurrent.CancellationException -> L12
            long r7 = r12.$totalDelta     // Catch: java.util.concurrent.CancellationException -> L12
            int r13 = androidx.compose.ui.unit.IntOffset.m5802getXimpl(r4)     // Catch: java.util.concurrent.CancellationException -> L12
            int r0 = androidx.compose.ui.unit.IntOffset.m5802getXimpl(r7)     // Catch: java.util.concurrent.CancellationException -> L12
            int r13 = r13 - r0
            int r0 = androidx.compose.ui.unit.IntOffset.m5803getYimpl(r4)     // Catch: java.util.concurrent.CancellationException -> L12
            int r2 = androidx.compose.ui.unit.IntOffset.m5803getYimpl(r7)     // Catch: java.util.concurrent.CancellationException -> L12
            int r0 = r0 - r2
            long r4 = androidx.compose.ui.unit.IntOffsetKt.IntOffset(r13, r0)     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r13 = r12.this$0     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.animation.core.Animatable r13 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$getPlacementDeltaAnimation$p(r13)     // Catch: java.util.concurrent.CancellationException -> L12
            r7 = r4
            androidx.compose.ui.unit.IntOffset r5 = androidx.compose.ui.unit.IntOffset.m5793boximpl(r7)     // Catch: java.util.concurrent.CancellationException -> L12
            r9 = r7
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animatePlacementDelta$1$1 r8 = new androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animatePlacementDelta$1$1     // Catch: java.util.concurrent.CancellationException -> L12
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r0 = r12.this$0     // Catch: java.util.concurrent.CancellationException -> L12
            r8.<init>(r0, r9)     // Catch: java.util.concurrent.CancellationException -> L12
            r0 = 0
            r12.L$0 = r0     // Catch: java.util.concurrent.CancellationException -> L12
            r12.label = r1     // Catch: java.util.concurrent.CancellationException -> L12
            r7 = 0
            r10 = 4
            r11 = 0
            r9 = r12
            r4 = r13
            java.lang.Object r13 = androidx.compose.animation.core.Animatable.animateTo$default(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: java.util.concurrent.CancellationException -> Lbd
            if (r13 != r3) goto Lb7
        Lb6:
            return r3
        Lb7:
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r13 = r9.this$0     // Catch: java.util.concurrent.CancellationException -> Lbd
            r0 = 0
            androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$setPlacementAnimationInProgress(r13, r0)     // Catch: java.util.concurrent.CancellationException -> Lbd
        Lbd:
            x6.t0 r13 = x6.t0.f22605a
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animatePlacementDelta$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((LazyLayoutAnimation$animatePlacementDelta$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
