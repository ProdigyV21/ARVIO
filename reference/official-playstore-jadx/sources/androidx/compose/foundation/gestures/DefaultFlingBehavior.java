package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.ui.MotionDurationScale;
import f7.j;
import java.util.concurrent.CancellationException;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\u000b\u001a\u00020\u0003*\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0096@¢\u0006\u0004\b\u000b\u0010\fR(\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0012R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/gestures/DefaultFlingBehavior;", "Landroidx/compose/foundation/gestures/FlingBehavior;", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "flingDecay", "Landroidx/compose/ui/MotionDurationScale;", "motionDurationScale", "<init>", "(Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/ui/MotionDurationScale;)V", "Landroidx/compose/foundation/gestures/ScrollScope;", "initialVelocity", "performFling", "(Landroidx/compose/foundation/gestures/ScrollScope;FLd7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/DecayAnimationSpec;", "getFlingDecay", "()Landroidx/compose/animation/core/DecayAnimationSpec;", "setFlingDecay", "(Landroidx/compose/animation/core/DecayAnimationSpec;)V", "Landroidx/compose/ui/MotionDurationScale;", "", "lastAnimationCycleCount", "I", "getLastAnimationCycleCount", "()I", "setLastAnimationCycleCount", "(I)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultFlingBehavior implements FlingBehavior {
    public static final int $stable = 0;
    private DecayAnimationSpec<Float> flingDecay;
    private int lastAnimationCycleCount;
    private final MotionDurationScale motionDurationScale;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DefaultFlingBehavior$performFling$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)F"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DefaultFlingBehavior$performFling$2", f = "Scrollable.kt", l = {965}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super Float>, Object> {
        final /* synthetic */ float $initialVelocity;
        final /* synthetic */ ScrollScope $this_performFling;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ DefaultFlingBehavior this$0;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DefaultFlingBehavior$performFling$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
            final /* synthetic */ h0 $lastValue;
            final /* synthetic */ ScrollScope $this_performFling;
            final /* synthetic */ h0 $velocityLeft;
            final /* synthetic */ DefaultFlingBehavior this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(h0 h0Var, ScrollScope scrollScope, h0 h0Var2, DefaultFlingBehavior defaultFlingBehavior) {
                super(1);
                this.$lastValue = h0Var;
                this.$this_performFling = scrollScope;
                this.$velocityLeft = h0Var2;
                this.this$0 = defaultFlingBehavior;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((AnimationScope<Float, AnimationVector1D>) obj);
                return t0.f22605a;
            }

            public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                float fFloatValue = animationScope.getValue().floatValue() - this.$lastValue.f19742i;
                float fScrollBy = this.$this_performFling.scrollBy(fFloatValue);
                this.$lastValue.f19742i = animationScope.getValue().floatValue();
                this.$velocityLeft.f19742i = animationScope.getVelocity().floatValue();
                if (Math.abs(fFloatValue - fScrollBy) > 0.5f) {
                    animationScope.cancelAnimation();
                }
                DefaultFlingBehavior defaultFlingBehavior = this.this$0;
                defaultFlingBehavior.setLastAnimationCycleCount(defaultFlingBehavior.getLastAnimationCycleCount() + 1);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f10, DefaultFlingBehavior defaultFlingBehavior, ScrollScope scrollScope, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$initialVelocity = f10;
            this.this$0 = defaultFlingBehavior;
            this.$this_performFling = scrollScope;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$initialVelocity, this.this$0, this.$this_performFling, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            float f10;
            AnimationState animationState;
            h0 h0Var;
            Object objAnimateDecay$default;
            e7.a aVar;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                if (Math.abs(this.$initialVelocity) > 1.0f) {
                    h0 h0Var2 = new h0();
                    float f11 = this.$initialVelocity;
                    h0Var2.f19742i = f11;
                    h0 h0Var3 = new h0();
                    AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(0.0f, f11, 0L, 0L, false, 28, null);
                    try {
                        DecayAnimationSpec<Float> flingDecay = this.this$0.getFlingDecay();
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(h0Var3, this.$this_performFling, h0Var2, this.this$0);
                        this.L$0 = h0Var2;
                        this.L$1 = animationStateAnimationState$default;
                        this.label = 1;
                        animationState = animationStateAnimationState$default;
                        try {
                            objAnimateDecay$default = SuspendAnimationKt.animateDecay$default(animationState, flingDecay, false, anonymousClass1, this, 2, null);
                            aVar = e7.a.f15033i;
                        } catch (CancellationException unused) {
                            h0Var = h0Var2;
                            h0Var.f19742i = ((Number) animationState.getVelocity()).floatValue();
                        }
                    } catch (CancellationException unused2) {
                        animationState = animationStateAnimationState$default;
                    }
                    if (objAnimateDecay$default == aVar) {
                        return aVar;
                    }
                    h0Var = h0Var2;
                    f10 = h0Var.f19742i;
                } else {
                    f10 = this.$initialVelocity;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                animationState = (AnimationState) this.L$1;
                h0Var = (h0) this.L$0;
                try {
                    k2.c.G(obj);
                } catch (CancellationException unused3) {
                    h0Var.f19742i = ((Number) animationState.getVelocity()).floatValue();
                }
                f10 = h0Var.f19742i;
            }
            return new Float(f10);
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super Float> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public DefaultFlingBehavior(DecayAnimationSpec<Float> decayAnimationSpec, MotionDurationScale motionDurationScale) {
        this.flingDecay = decayAnimationSpec;
        this.motionDurationScale = motionDurationScale;
    }

    public final DecayAnimationSpec<Float> getFlingDecay() {
        return this.flingDecay;
    }

    public final int getLastAnimationCycleCount() {
        return this.lastAnimationCycleCount;
    }

    @Override // androidx.compose.foundation.gestures.FlingBehavior
    public Object performFling(ScrollScope scrollScope, float f10, d7.d<? super Float> dVar) {
        this.lastAnimationCycleCount = 0;
        return m0.y(this.motionDurationScale, new AnonymousClass2(f10, this, scrollScope, null), dVar);
    }

    public final void setFlingDecay(DecayAnimationSpec<Float> decayAnimationSpec) {
        this.flingDecay = decayAnimationSpec;
    }

    public final void setLastAnimationCycleCount(int i10) {
        this.lastAnimationCycleCount = i10;
    }

    public /* synthetic */ DefaultFlingBehavior(DecayAnimationSpec decayAnimationSpec, MotionDurationScale motionDurationScale, int i10, kotlin.jvm.internal.h hVar) {
        this(decayAnimationSpec, (i10 & 2) != 0 ? ScrollableKt.getDefaultScrollMotionDurationScale() : motionDurationScale);
    }
}
