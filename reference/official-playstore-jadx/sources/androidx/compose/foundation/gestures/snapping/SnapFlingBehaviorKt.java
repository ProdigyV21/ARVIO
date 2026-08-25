package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001ag\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0012*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\fH\u0082@¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0018\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u0015*\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0082\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a*\u0010\u001a\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u0015*\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0082\u0002¢\u0006\u0004\b\u001a\u0010\u0019\u001am\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0012*\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00062\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u001e2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\fH\u0082@¢\u0006\u0004\b \u0010!\u001au\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0012*\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060#2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\fH\u0082@¢\u0006\u0004\b%\u0010&\u001a\u001b\u0010(\u001a\u00020\u0006*\u00020\u00062\u0006\u0010'\u001a\u00020\u0006H\u0002¢\u0006\u0004\b(\u0010)\u001a*\u00100\u001a\u00020\u00062\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a\u001e\u00104\u001a\u00020\u00102\f\u00103\u001a\b\u0012\u0004\u0012\u00020201H\u0082\b¢\u0006\u0004\b4\u00105\"\u001a\u00107\u001a\u0002068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0014\u0010;\u001a\u00020\u00068\u0000X\u0080T¢\u0006\u0006\n\u0004\b;\u00108\"\u0014\u0010<\u001a\u00020\u00068\u0000X\u0080T¢\u0006\u0006\n\u0004\b<\u00108\"\u0014\u0010>\u001a\u00020=8\u0002X\u0082T¢\u0006\u0006\n\u0004\b>\u0010?\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006@"}, d2 = {"Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "snapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "rememberSnapFlingBehavior", "(Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "Landroidx/compose/foundation/gestures/ScrollScope;", "", "initialTargetOffset", "initialVelocity", "Landroidx/compose/foundation/gestures/snapping/ApproachAnimation;", "Landroidx/compose/animation/core/AnimationVector1D;", "animation", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "delta", "Lx6/t0;", "onAnimationStep", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "approach", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLandroidx/compose/foundation/gestures/snapping/ApproachAnimation;Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "T", "Lx7/e;", "component1", "(Lx7/e;)Ljava/lang/Comparable;", "component2", "targetOffset", "Landroidx/compose/animation/core/AnimationState;", "animationState", "Landroidx/compose/animation/core/DecayAnimationSpec;", "decayAnimationSpec", "animateDecay", "(Landroidx/compose/foundation/gestures/ScrollScope;FLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/DecayAnimationSpec;Lr7/l;Ld7/d;)Ljava/lang/Object;", "cancelOffset", "Landroidx/compose/animation/core/AnimationSpec;", "animationSpec", "animateWithTarget", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/AnimationSpec;Lr7/l;Ld7/d;)Ljava/lang/Object;", "target", "coerceToTarget", "(FF)F", "Landroidx/compose/foundation/gestures/snapping/FinalSnappingItem;", "snappingOffset", "lowerBound", "upperBound", "calculateFinalOffset-Fhqu1e0", "(IFF)F", "calculateFinalOffset", "Lkotlin/Function0;", "", "generateMsg", "debugLog", "(Lr7/a;)V", "Landroidx/compose/ui/unit/Dp;", "MinFlingVelocityDp", "F", "getMinFlingVelocityDp", "()F", "NoDistance", "NoVelocity", "", "DEBUG", "Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnapFlingBehaviorKt {
    private static final boolean DEBUG = false;
    private static final float MinFlingVelocityDp = Dp.m5678constructorimpl(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
    public static final float NoDistance = 0.0f;
    public static final float NoVelocity = 0.0f;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt", f = "SnapFlingBehavior.kt", l = {364}, m = "animateDecay")
    public static final class AnonymousClass1 extends c {
        float F$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehaviorKt.animateDecay(null, 0.0f, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ l<Float, t0> $onAnimationStep;
        final /* synthetic */ h0 $previousValue;
        final /* synthetic */ float $targetOffset;
        final /* synthetic */ ScrollScope $this_animateDecay;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(float f10, h0 h0Var, ScrollScope scrollScope, l<? super Float, t0> lVar) {
            super(1);
            this.$targetOffset = f10;
            this.$previousValue = h0Var;
            this.$this_animateDecay = scrollScope;
            this.$onAnimationStep = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            if (Math.abs(animationScope.getValue().floatValue()) < Math.abs(this.$targetOffset)) {
                SnapFlingBehaviorKt.animateDecay$consumeDelta(animationScope, this.$this_animateDecay, this.$onAnimationStep, animationScope.getValue().floatValue() - this.$previousValue.f19742i);
                this.$previousValue.f19742i = animationScope.getValue().floatValue();
                return;
            }
            float fCoerceToTarget = SnapFlingBehaviorKt.coerceToTarget(animationScope.getValue().floatValue(), this.$targetOffset);
            SnapFlingBehaviorKt.animateDecay$consumeDelta(animationScope, this.$this_animateDecay, this.$onAnimationStep, fCoerceToTarget - this.$previousValue.f19742i);
            animationScope.cancelAnimation();
            this.$previousValue.f19742i = fCoerceToTarget;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt", f = "SnapFlingBehavior.kt", l = {409}, m = "animateWithTarget")
    public static final class C03321 extends c {
        float F$0;
        float F$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C03321(d<? super C03321> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehaviorKt.animateWithTarget(null, 0.0f, 0.0f, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03332 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ float $cancelOffset;
        final /* synthetic */ h0 $consumedUpToNow;
        final /* synthetic */ l<Float, t0> $onAnimationStep;
        final /* synthetic */ ScrollScope $this_animateWithTarget;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03332(float f10, h0 h0Var, ScrollScope scrollScope, l<? super Float, t0> lVar) {
            super(1);
            this.$cancelOffset = f10;
            this.$consumedUpToNow = h0Var;
            this.$this_animateWithTarget = scrollScope;
            this.$onAnimationStep = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            float fCoerceToTarget = SnapFlingBehaviorKt.coerceToTarget(animationScope.getValue().floatValue(), this.$cancelOffset);
            float f10 = fCoerceToTarget - this.$consumedUpToNow.f19742i;
            float fScrollBy = this.$this_animateWithTarget.scrollBy(f10);
            this.$onAnimationStep.invoke(Float.valueOf(fScrollBy));
            if (Math.abs(f10 - fScrollBy) > 0.5f || fCoerceToTarget != animationScope.getValue().floatValue()) {
                animationScope.cancelAnimation();
            }
            this.$consumedUpToNow.f19742i += fScrollBy;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animateDecay(androidx.compose.foundation.gestures.ScrollScope r4, float r5, androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r6, androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r7, r7.l<? super java.lang.Float, x6.t0> r8, d7.d<? super androidx.compose.foundation.gestures.snapping.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r9) {
        /*
            boolean r0 = r9 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1 r0 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1 r0 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            float r5 = r0.F$0
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.internal.h0 r4 = (kotlin.jvm.internal.h0) r4
            java.lang.Object r6 = r0.L$0
            androidx.compose.animation.core.AnimationState r6 = (androidx.compose.animation.core.AnimationState) r6
            k2.c.G(r9)
            goto L69
        L2f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L37:
            k2.c.G(r9)
            kotlin.jvm.internal.h0 r9 = new kotlin.jvm.internal.h0
            r9.<init>()
            java.lang.Object r1 = r6.getVelocity()
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L50
            r1 = r2
            goto L51
        L50:
            r1 = 0
        L51:
            r1 = r1 ^ r2
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$2 r3 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$2
            r3.<init>(r5, r9, r4, r8)
            r0.L$0 = r6
            r0.L$1 = r9
            r0.F$0 = r5
            r0.label = r2
            java.lang.Object r4 = androidx.compose.animation.core.SuspendAnimationKt.animateDecay(r6, r7, r1, r3, r0)
            e7.a r7 = e7.a.f15033i
            if (r4 != r7) goto L68
            return r7
        L68:
            r4 = r9
        L69:
            androidx.compose.foundation.gestures.snapping.AnimationResult r7 = new androidx.compose.foundation.gestures.snapping.AnimationResult
            float r4 = r4.f19742i
            float r5 = r5 - r4
            java.lang.Float r4 = new java.lang.Float
            r4.<init>(r5)
            r7.<init>(r4, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.animateDecay(androidx.compose.foundation.gestures.ScrollScope, float, androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.DecayAnimationSpec, r7.l, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animateDecay$consumeDelta(AnimationScope<Float, AnimationVector1D> animationScope, ScrollScope scrollScope, l<? super Float, t0> lVar, float f10) {
        float fScrollBy = scrollScope.scrollBy(f10);
        lVar.invoke(Float.valueOf(fScrollBy));
        if (Math.abs(f10 - fScrollBy) > 0.5f) {
            animationScope.cancelAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animateWithTarget(androidx.compose.foundation.gestures.ScrollScope r10, float r11, float r12, androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r13, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r14, r7.l<? super java.lang.Float, x6.t0> r15, d7.d<? super androidx.compose.foundation.gestures.snapping.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r16) {
        /*
            r0 = r16
            boolean r1 = r0 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.C03321
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1 r1 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.C03321) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L16
            int r2 = r2 - r3
            r1.label = r2
        L14:
            r7 = r1
            goto L1c
        L16:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1 r1 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1
            r1.<init>(r0)
            goto L14
        L1c:
            java.lang.Object r0 = r7.result
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L3e
            if (r1 != r2) goto L36
            float r10 = r7.F$1
            float r11 = r7.F$0
            java.lang.Object r12 = r7.L$1
            kotlin.jvm.internal.h0 r12 = (kotlin.jvm.internal.h0) r12
            java.lang.Object r1 = r7.L$0
            androidx.compose.animation.core.AnimationState r1 = (androidx.compose.animation.core.AnimationState) r1
            k2.c.G(r0)
            r0 = r1
            goto L86
        L36:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3e:
            k2.c.G(r0)
            kotlin.jvm.internal.h0 r0 = new kotlin.jvm.internal.h0
            r0.<init>()
            java.lang.Object r1 = r13.getVelocity()
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            java.lang.Float r3 = new java.lang.Float
            r3.<init>(r11)
            java.lang.Object r4 = r13.getVelocity()
            java.lang.Number r4 = (java.lang.Number) r4
            float r4 = r4.floatValue()
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L66
            r4 = r2
            goto L67
        L66:
            r4 = 0
        L67:
            r5 = r4 ^ 1
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$2 r6 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$2
            r6.<init>(r12, r0, r10, r15)
            r7.L$0 = r13
            r7.L$1 = r0
            r7.F$0 = r11
            r7.F$1 = r1
            r7.label = r2
            r2 = r13
            r4 = r14
            java.lang.Object r10 = androidx.compose.animation.core.SuspendAnimationKt.animateTo(r2, r3, r4, r5, r6, r7)
            e7.a r12 = e7.a.f15033i
            if (r10 != r12) goto L83
            return r12
        L83:
            r12 = r0
            r10 = r1
            r0 = r13
        L86:
            java.lang.Object r1 = r0.getVelocity()
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            float r2 = coerceToTarget(r1, r10)
            androidx.compose.foundation.gestures.snapping.AnimationResult r10 = new androidx.compose.foundation.gestures.snapping.AnimationResult
            float r12 = r12.f19742i
            float r11 = r11 - r12
            java.lang.Float r12 = new java.lang.Float
            r12.<init>(r11)
            r8 = 29
            r9 = 0
            r1 = 0
            r3 = 0
            r5 = 0
            r7 = 0
            androidx.compose.animation.core.AnimationState r11 = androidx.compose.animation.core.AnimationStateKt.copy$default(r0, r1, r2, r3, r5, r7, r8, r9)
            r10.<init>(r12, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.animateWithTarget(androidx.compose.foundation.gestures.ScrollScope, float, float, androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.AnimationSpec, r7.l, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object approach(ScrollScope scrollScope, float f10, float f11, ApproachAnimation<Float, AnimationVector1D> approachAnimation, l<? super Float, t0> lVar, d<? super AnimationResult<Float, AnimationVector1D>> dVar) {
        return approachAnimation.approachAnimation(scrollScope, new Float(f10), new Float(f11), lVar, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX INFO: renamed from: calculateFinalOffset-Fhqu1e0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final float m407calculateFinalOffsetFhqu1e0(int r3, float r4, float r5) {
        /*
            androidx.compose.foundation.gestures.snapping.FinalSnappingItem$Companion r0 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.INSTANCE
            int r1 = r0.m404getClosestItembbeMdSM()
            boolean r1 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m400equalsimpl0(r3, r1)
            r2 = 0
            if (r1 == 0) goto L1a
            float r3 = java.lang.Math.abs(r5)
            float r0 = java.lang.Math.abs(r4)
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r3 > 0) goto L32
            goto L24
        L1a:
            int r1 = r0.m405getNextItembbeMdSM()
            boolean r1 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m400equalsimpl0(r3, r1)
            if (r1 == 0) goto L26
        L24:
            r4 = r5
            goto L32
        L26:
            int r5 = r0.m406getPreviousItembbeMdSM()
            boolean r3 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m400equalsimpl0(r3, r5)
            if (r3 == 0) goto L31
            goto L32
        L31:
            r4 = r2
        L32:
            boolean r3 = calculateFinalOffset_Fhqu1e0$isValidDistance(r4)
            if (r3 == 0) goto L39
            return r4
        L39:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.m407calculateFinalOffsetFhqu1e0(int, float, float):float");
    }

    private static final boolean calculateFinalOffset_Fhqu1e0$isValidDistance(float f10) {
        return (f10 == Float.POSITIVE_INFINITY || f10 == Float.NEGATIVE_INFINITY) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float coerceToTarget(float f10, float f11) {
        if (f11 == 0.0f) {
            return 0.0f;
        }
        return (f11 <= 0.0f ? f10 >= f11 : f10 <= f11) ? f10 : f11;
    }

    private static final <T extends Comparable<? super T>> T component1(x7.e<T> eVar) {
        return (T) eVar.getStart();
    }

    private static final <T extends Comparable<? super T>> T component2(x7.e<T> eVar) {
        return (T) eVar.b();
    }

    public static final float getMinFlingVelocityDp() {
        return MinFlingVelocityDp;
    }

    public static final SnapFlingBehavior rememberSnapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, Composer composer, int i10) {
        composer.startReplaceableGroup(-473984552);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-473984552, i10, -1, "androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior (SnapFlingBehavior.kt:284)");
        }
        Object obj = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        DecayAnimationSpec decayAnimationSpecRememberSplineBasedDecay = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        composer.startReplaceableGroup(1618982084);
        boolean zChanged = composer.changed(obj) | composer.changed(snapLayoutInfoProvider) | composer.changed(decayAnimationSpecRememberSplineBasedDecay);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SnapFlingBehavior(snapLayoutInfoProvider, AnimationSpecKt.tween$default(0, 0, EasingKt.getLinearEasing(), 3, null), decayAnimationSpecRememberSplineBasedDecay, AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null));
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        SnapFlingBehavior snapFlingBehavior = (SnapFlingBehavior) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return snapFlingBehavior;
    }

    private static final void debugLog(a<String> aVar) {
    }
}
