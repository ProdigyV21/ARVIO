package androidx.compose.animation.core;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"ExponentialDecayFriction", "", "createAnimation", "Landroidx/compose/animation/core/Animation;", "Landroidx/compose/animation/core/AnimationVector1D;", "Landroidx/compose/animation/core/FloatDecayAnimationSpec;", "startValue", "startVelocity", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FloatDecayAnimationSpecKt {
    private static final float ExponentialDecayFriction = -4.2f;

    public static final Animation<Float, AnimationVector1D> createAnimation(FloatDecayAnimationSpec floatDecayAnimationSpec, float f10, float f11) {
        return AnimationKt.DecayAnimation(floatDecayAnimationSpec, f10, f11);
    }

    public static /* synthetic */ Animation createAnimation$default(FloatDecayAnimationSpec floatDecayAnimationSpec, float f10, float f11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f11 = 0.0f;
        }
        return createAnimation(floatDecayAnimationSpec, f10, f11);
    }
}
