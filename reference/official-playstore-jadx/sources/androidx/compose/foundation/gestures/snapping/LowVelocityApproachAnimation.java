package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.gestures.ScrollScope;
import d7.d;
import e7.a;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007JW\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\fH\u0096@¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/gestures/snapping/LowVelocityApproachAnimation;", "Landroidx/compose/foundation/gestures/snapping/ApproachAnimation;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Landroidx/compose/animation/core/AnimationSpec;", "lowVelocityAnimationSpec", "<init>", "(Landroidx/compose/animation/core/AnimationSpec;)V", "Landroidx/compose/foundation/gestures/ScrollScope;", "scope", "offset", "velocity", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "delta", "Lx6/t0;", "onAnimationStep", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "approachAnimation", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLr7/l;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/animation/core/AnimationSpec;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class LowVelocityApproachAnimation implements ApproachAnimation<Float, AnimationVector1D> {
    private final AnimationSpec<Float> lowVelocityAnimationSpec;

    public LowVelocityApproachAnimation(AnimationSpec<Float> animationSpec) {
        this.lowVelocityAnimationSpec = animationSpec;
    }

    @Override // androidx.compose.foundation.gestures.snapping.ApproachAnimation
    public /* bridge */ /* synthetic */ Object approachAnimation(ScrollScope scrollScope, Float f10, Float f11, l<? super Float, t0> lVar, d dVar) {
        return approachAnimation(scrollScope, f10.floatValue(), f11.floatValue(), lVar, (d<? super AnimationResult<Float, AnimationVector1D>>) dVar);
    }

    public Object approachAnimation(ScrollScope scrollScope, float f10, float f11, l<? super Float, t0> lVar, d<? super AnimationResult<Float, AnimationVector1D>> dVar) {
        Object objAnimateWithTarget = SnapFlingBehaviorKt.animateWithTarget(scrollScope, Math.signum(f11) * Math.abs(f10), f10, AnimationStateKt.AnimationState$default(0.0f, f11, 0L, 0L, false, 28, null), this.lowVelocityAnimationSpec, lVar, dVar);
        return objAnimateWithTarget == a.f15033i ? objAnimateWithTarget : (AnimationResult) objAnimateWithTarget;
    }
}
