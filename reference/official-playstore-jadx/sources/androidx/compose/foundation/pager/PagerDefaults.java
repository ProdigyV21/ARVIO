package androidx.compose.foundation.pager;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jh\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018JY\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0003\u0010\u0016\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/pager/PagerDefaults;", "", "()V", "BeyondBoundsPageCount", "", "LowVelocityAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "getLowVelocityAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "flingBehavior", "Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "state", "Landroidx/compose/foundation/pager/PagerState;", "pagerSnapDistance", "Landroidx/compose/foundation/pager/PagerSnapDistance;", "lowVelocityAnimationSpec", "highVelocityAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "snapAnimationSpec", "snapVelocityThreshold", "Landroidx/compose/ui/unit/Dp;", "snapPositionalThreshold", "flingBehavior-PfoAEA0", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/pager/PagerSnapDistance;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;FFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/pager/PagerSnapDistance;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;FLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PagerDefaults {
    public static final int BeyondBoundsPageCount = 0;
    public static final PagerDefaults INSTANCE = new PagerDefaults();
    private static final AnimationSpec<Float> LowVelocityAnimationSpec = AnimationSpecKt.tween$default(500, 0, EasingKt.getLinearEasing(), 2, null);
    public static final int $stable = 8;

    private PagerDefaults() {
    }

    public final SnapFlingBehavior flingBehavior(PagerState pagerState, PagerSnapDistance pagerSnapDistance, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec2, float f10, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-194065136);
        if ((i11 & 2) != 0) {
            pagerSnapDistance = PagerSnapDistance.INSTANCE.atMost(1);
        }
        if ((i11 & 4) != 0) {
            animationSpec = LowVelocityAnimationSpec;
        }
        if ((i11 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        if ((i11 & 16) != 0) {
            animationSpec2 = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i11 & 32) != 0) {
            f10 = 0.5f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-194065136, i10, -1, "androidx.compose.foundation.pager.PagerDefaults.flingBehavior (Pager.kt:348)");
        }
        if (0.0f > f10 || f10 > 1.0f) {
            throw new IllegalArgumentException(("snapPositionalThreshold should be a number between 0 and 1. You've specified " + f10).toString());
        }
        Object[] objArr = {pagerState, animationSpec, decayAnimationSpec, animationSpec2, pagerSnapDistance, (Density) composer.consume(CompositionLocalsKt.getLocalDensity())};
        composer.startReplaceableGroup(-568225417);
        boolean zChanged = false;
        for (int i12 = 0; i12 < 6; i12++) {
            zChanged |= composer.changed(objArr[i12]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SnapFlingBehavior(PagerKt.SnapLayoutInfoProvider(pagerState, pagerSnapDistance, decayAnimationSpec, f10), animationSpec, decayAnimationSpec, animationSpec2);
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

    @e
    /* JADX INFO: renamed from: flingBehavior-PfoAEA0, reason: not valid java name */
    public final SnapFlingBehavior m754flingBehaviorPfoAEA0(PagerState pagerState, PagerSnapDistance pagerSnapDistance, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec2, float f10, float f11, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-705378306);
        if ((i11 & 2) != 0) {
            pagerSnapDistance = PagerSnapDistance.INSTANCE.atMost(1);
        }
        PagerSnapDistance pagerSnapDistance2 = pagerSnapDistance;
        if ((i11 & 4) != 0) {
            animationSpec = AnimationSpecKt.tween$default(500, 0, EasingKt.getLinearEasing(), 2, null);
        }
        if ((i11 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        if ((i11 & 16) != 0) {
            animationSpec2 = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        AnimationSpec<Float> animationSpec3 = animationSpec2;
        if ((i11 & 32) != 0) {
            SnapFlingBehaviorKt.getMinFlingVelocityDp();
        }
        float f12 = (i11 & 64) != 0 ? 0.5f : f11;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-705378306, i10, -1, "androidx.compose.foundation.pager.PagerDefaults.flingBehavior (Pager.kt:470)");
        }
        SnapFlingBehavior snapFlingBehaviorFlingBehavior = flingBehavior(pagerState, pagerSnapDistance2, animationSpec, decayAnimationSpec, animationSpec3, f12, composer, (i10 & 14) | 2134528 | (i10 & 112) | ((i10 >> 3) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return snapFlingBehaviorFlingBehavior;
    }

    public final AnimationSpec<Float> getLowVelocityAnimationSpec() {
        return LowVelocityAnimationSpec;
    }

    public final NestedScrollConnection pageNestedScrollConnection(PagerState state, Orientation orientation) {
        return new DefaultPagerNestedScrollConnection(state, orientation);
    }
}
