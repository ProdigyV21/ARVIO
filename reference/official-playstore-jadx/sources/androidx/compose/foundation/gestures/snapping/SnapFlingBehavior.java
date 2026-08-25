package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.ui.MotionDurationScale;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\n\u0010\u000bBC\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\rJ<\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0013*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0010H\u0082@¢\u0006\u0004\b\u0015\u0010\u0016JD\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u001a*\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0010H\u0082@¢\u0006\u0004\b\u001b\u0010\u001cJS\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0013*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052!\u0010!\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110\u0010H\u0082@¢\u0006\u0004\b\"\u0010\u001cJ\u001f\u0010$\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002¢\u0006\u0004\b$\u0010%J\u001c\u0010&\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0096@¢\u0006\u0004\b&\u0010'J0\u0010&\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0010H\u0086@¢\u0006\u0004\b&\u0010\u0016J\u001a\u0010+\u001a\u00020#2\b\u0010*\u001a\u0004\u0018\u00010)H\u0096\u0002¢\u0006\u0004\b+\u0010,J\u000f\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00100R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u00101R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u00102R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u00101R\"\u00104\u001a\u0002038\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006:"}, d2 = {"Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "Landroidx/compose/foundation/gestures/FlingBehavior;", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "snapLayoutInfoProvider", "Landroidx/compose/animation/core/AnimationSpec;", "", "lowVelocityAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "highVelocityAnimationSpec", "snapAnimationSpec", "<init>", "(Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;)V", "shortSnapVelocityThreshold", "(Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;F)V", "Landroidx/compose/foundation/gestures/ScrollScope;", "initialVelocity", "Lkotlin/Function1;", "Lx6/t0;", "onRemainingScrollOffsetUpdate", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "Landroidx/compose/animation/core/AnimationVector1D;", "fling", "(Landroidx/compose/foundation/gestures/ScrollScope;FLr7/l;Ld7/d;)Ljava/lang/Object;", "offset", "velocity", "updateRemainingScrollOffset", "Landroidx/compose/animation/core/AnimationState;", "tryApproach", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLr7/l;Ld7/d;)Ljava/lang/Object;", "initialTargetOffset", "Lx6/y;", ContentDisposition.Parameters.Name, "delta", "onAnimationStep", "runApproach", "", "isDecayApproachPossible", "(FF)Z", "performFling", "(Landroidx/compose/foundation/gestures/ScrollScope;FLd7/d;)Ljava/lang/Object;", "onSettlingDistanceUpdated", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "Landroidx/compose/animation/core/AnimationSpec;", "Landroidx/compose/animation/core/DecayAnimationSpec;", "Landroidx/compose/ui/MotionDurationScale;", "motionScaleDuration", "Landroidx/compose/ui/MotionDurationScale;", "getMotionScaleDuration$foundation_release", "()Landroidx/compose/ui/MotionDurationScale;", "setMotionScaleDuration$foundation_release", "(Landroidx/compose/ui/MotionDurationScale;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapFlingBehavior implements FlingBehavior {
    public static final int $stable = 0;
    private final DecayAnimationSpec<Float> highVelocityAnimationSpec;
    private final AnimationSpec<Float> lowVelocityAnimationSpec;
    private MotionDurationScale motionScaleDuration;
    private final AnimationSpec<Float> snapAnimationSpec;
    private final SnapLayoutInfoProvider snapLayoutInfoProvider;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {165}, m = "fling")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.fling(null, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$performFling$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(F)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Float, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(float f10) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).floatValue());
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$performFling$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {151}, m = "performFling")
    public static final class AnonymousClass3 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.performFling(null, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$tryApproach$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {213}, m = "tryApproach")
    public static final class C03311 extends c {
        int label;
        /* synthetic */ Object result;

        public C03311(d<? super C03311> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.tryApproach(null, 0.0f, 0.0f, null, this);
        }
    }

    public SnapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec2) {
        this.snapLayoutInfoProvider = snapLayoutInfoProvider;
        this.lowVelocityAnimationSpec = animationSpec;
        this.highVelocityAnimationSpec = decayAnimationSpec;
        this.snapAnimationSpec = animationSpec2;
        this.motionScaleDuration = ScrollableKt.getDefaultScrollMotionDurationScale();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fling(androidx.compose.foundation.gestures.ScrollScope r10, float r11, r7.l<? super java.lang.Float, x6.t0> r12, d7.d<? super androidx.compose.foundation.gestures.snapping.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r13) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r13 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r13
            androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$1 r0 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$1 r0 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$1
            r0.<init>(r13)
        L18:
            java.lang.Object r13 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r10 = r0.L$0
            r12 = r10
            r7.l r12 = (r7.l) r12
            k2.c.G(r13)
            goto L4f
        L2a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L32:
            k2.c.G(r13)
            androidx.compose.ui.MotionDurationScale r13 = r9.motionScaleDuration
            androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1 r3 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1
            r8 = 0
            r4 = r9
            r7 = r10
            r5 = r11
            r6 = r12
            r3.<init>(r4, r5, r6, r7, r8)
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r13 = ka.m0.y(r13, r3, r0)
            e7.a r10 = e7.a.f15033i
            if (r13 != r10) goto L4e
            return r10
        L4e:
            r12 = r6
        L4f:
            androidx.compose.foundation.gestures.snapping.AnimationResult r13 = (androidx.compose.foundation.gestures.snapping.AnimationResult) r13
            java.lang.Float r10 = new java.lang.Float
            r11 = 0
            r10.<init>(r11)
            r12.invoke(r10)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.fling(androidx.compose.foundation.gestures.ScrollScope, float, r7.l, d7.d):java.lang.Object");
    }

    private final boolean isDecayApproachPossible(float offset, float velocity) {
        return Math.abs(DecayAnimationSpecKt.calculateTargetValue(this.highVelocityAnimationSpec, 0.0f, velocity)) >= Math.abs(offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object runApproach(ScrollScope scrollScope, float f10, float f11, l<? super Float, t0> lVar, d<? super AnimationResult<Float, AnimationVector1D>> dVar) {
        return SnapFlingBehaviorKt.approach(scrollScope, f10, f11, isDecayApproachPossible(f10, f11) ? new HighVelocityApproachAnimation(this.highVelocityAnimationSpec) : new LowVelocityApproachAnimation(this.lowVelocityAnimationSpec), lVar, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tryApproach(androidx.compose.foundation.gestures.ScrollScope r18, float r19, float r20, r7.l<? super java.lang.Float, x6.t0> r21, d7.d<? super androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r22) {
        /*
            r17 = this;
            r0 = r22
            boolean r1 = r0 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.C03311
            if (r1 == 0) goto L18
            r1 = r0
            androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$tryApproach$1 r1 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.C03311) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L18
            int r2 = r2 - r3
            r1.label = r2
            r2 = r17
        L16:
            r7 = r1
            goto L20
        L18:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$tryApproach$1 r1 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$tryApproach$1
            r2 = r17
            r1.<init>(r0)
            goto L16
        L20:
            java.lang.Object r0 = r7.result
            int r1 = r7.label
            r3 = 1
            if (r1 == 0) goto L35
            if (r1 != r3) goto L2d
            k2.c.G(r0)
            goto L6f
        L2d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L35:
            k2.c.G(r0)
            float r0 = java.lang.Math.abs(r19)
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L42
            goto L4a
        L42:
            float r0 = java.lang.Math.abs(r20)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L5c
        L4a:
            r15 = 28
            r16 = 0
            r10 = 0
            r12 = 0
            r14 = 0
            r8 = r19
            r9 = r20
            androidx.compose.animation.core.AnimationState r0 = androidx.compose.animation.core.AnimationStateKt.AnimationState$default(r8, r9, r10, r12, r14, r15, r16)
            return r0
        L5c:
            r7.label = r3
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            java.lang.Object r0 = r2.runApproach(r3, r4, r5, r6, r7)
            e7.a r1 = e7.a.f15033i
            if (r0 != r1) goto L6f
            return r1
        L6f:
            androidx.compose.foundation.gestures.snapping.AnimationResult r0 = (androidx.compose.foundation.gestures.snapping.AnimationResult) r0
            androidx.compose.animation.core.AnimationState r0 = r0.getCurrentAnimationState()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.tryApproach(androidx.compose.foundation.gestures.ScrollScope, float, float, r7.l, d7.d):java.lang.Object");
    }

    public boolean equals(Object other) {
        if (other instanceof SnapFlingBehavior) {
            SnapFlingBehavior snapFlingBehavior = (SnapFlingBehavior) other;
            if (p.a(snapFlingBehavior.snapAnimationSpec, this.snapAnimationSpec) && p.a(snapFlingBehavior.highVelocityAnimationSpec, this.highVelocityAnimationSpec) && p.a(snapFlingBehavior.lowVelocityAnimationSpec, this.lowVelocityAnimationSpec) && p.a(snapFlingBehavior.snapLayoutInfoProvider, this.snapLayoutInfoProvider)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: getMotionScaleDuration$foundation_release, reason: from getter */
    public final MotionDurationScale getMotionScaleDuration() {
        return this.motionScaleDuration;
    }

    public int hashCode() {
        return this.snapLayoutInfoProvider.hashCode() + ((this.lowVelocityAnimationSpec.hashCode() + ((this.highVelocityAnimationSpec.hashCode() + (this.snapAnimationSpec.hashCode() * 31)) * 31)) * 31);
    }

    @Override // androidx.compose.foundation.gestures.FlingBehavior
    public Object performFling(ScrollScope scrollScope, float f10, d<? super Float> dVar) {
        return performFling(scrollScope, f10, AnonymousClass2.INSTANCE, dVar);
    }

    public final void setMotionScaleDuration$foundation_release(MotionDurationScale motionDurationScale) {
        this.motionScaleDuration = motionDurationScale;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object performFling(androidx.compose.foundation.gestures.ScrollScope r5, float r6, r7.l<? super java.lang.Float, x6.t0> r7, d7.d<? super java.lang.Float> r8) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$performFling$3 r0 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$performFling$3 r0 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$performFling$3
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r8)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r8)
            r0.label = r2
            java.lang.Object r8 = r4.fling(r5, r6, r7, r0)
            e7.a r5 = e7.a.f15033i
            if (r8 != r5) goto L3b
            return r5
        L3b:
            androidx.compose.foundation.gestures.snapping.AnimationResult r8 = (androidx.compose.foundation.gestures.snapping.AnimationResult) r8
            java.lang.Object r5 = r8.component1()
            java.lang.Number r5 = (java.lang.Number) r5
            float r5 = r5.floatValue()
            androidx.compose.animation.core.AnimationState r6 = r8.component2()
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 != 0) goto L51
            goto L5b
        L51:
            java.lang.Object r5 = r6.getVelocity()
            java.lang.Number r5 = (java.lang.Number) r5
            float r7 = r5.floatValue()
        L5b:
            java.lang.Float r5 = new java.lang.Float
            r5.<init>(r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.performFling(androidx.compose.foundation.gestures.ScrollScope, float, r7.l, d7.d):java.lang.Object");
    }

    @x6.e
    public SnapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec2, float f10) {
        this(snapLayoutInfoProvider, animationSpec, decayAnimationSpec, animationSpec2);
    }
}
