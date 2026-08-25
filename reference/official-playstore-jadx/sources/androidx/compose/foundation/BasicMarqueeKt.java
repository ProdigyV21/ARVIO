package androidx.compose.foundation;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.StartOffset;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001aH\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0002ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a&\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0001H\u0002\u001aR\u0010&\u001a\u00020'*\u00020'2\b\b\u0002\u0010\u001b\u001a\u00020\u00012\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+\"\u001c\u0010\u0000\u001a\u00020\u00018GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u00018GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\u0005\"\u001c\u0010\t\u001a\u00020\n8GX\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\r\"\u001e\u0010\u000e\u001a\u00020\u000f8GX\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006,"}, d2 = {"DefaultMarqueeDelayMillis", "", "getDefaultMarqueeDelayMillis$annotations", "()V", "getDefaultMarqueeDelayMillis", "()I", "DefaultMarqueeIterations", "getDefaultMarqueeIterations$annotations", "getDefaultMarqueeIterations", "DefaultMarqueeSpacing", "Landroidx/compose/foundation/MarqueeSpacing;", "getDefaultMarqueeSpacing$annotations", "getDefaultMarqueeSpacing", "()Landroidx/compose/foundation/MarqueeSpacing;", "DefaultMarqueeVelocity", "Landroidx/compose/ui/unit/Dp;", "getDefaultMarqueeVelocity$annotations", "getDefaultMarqueeVelocity", "()F", "F", "MarqueeSpacing", "spacing", "MarqueeSpacing-0680j_4", "(F)Landroidx/compose/foundation/MarqueeSpacing;", "createMarqueeAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "iterations", "targetValue", "initialDelayMillis", "delayMillis", "velocity", "density", "Landroidx/compose/ui/unit/Density;", "createMarqueeAnimationSpec-Z4HSEVQ", "(IFIIFLandroidx/compose/ui/unit/Density;)Landroidx/compose/animation/core/AnimationSpec;", "velocityBasedTween", "Landroidx/compose/animation/core/TweenSpec;", "basicMarquee", "Landroidx/compose/ui/Modifier;", "animationMode", "Landroidx/compose/foundation/MarqueeAnimationMode;", "basicMarquee-1Mj1MLw", "(Landroidx/compose/ui/Modifier;IIIILandroidx/compose/foundation/MarqueeSpacing;F)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicMarqueeKt {
    private static final int DefaultMarqueeDelayMillis = 1200;
    private static final int DefaultMarqueeIterations = 3;
    private static final MarqueeSpacing DefaultMarqueeSpacing = MarqueeSpacing.INSTANCE.fractionOfContainer(0.33333334f);
    private static final float DefaultMarqueeVelocity = Dp.m5678constructorimpl(30);

    /* JADX INFO: renamed from: MarqueeSpacing-0680j_4, reason: not valid java name */
    public static final MarqueeSpacing m182MarqueeSpacing0680j_4(float f10) {
        return new a(f10, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int MarqueeSpacing_0680j_4$lambda$1(float f10, Density density, int i10, int i11) {
        return density.mo279roundToPx0680j_4(f10);
    }

    /* JADX INFO: renamed from: basicMarquee-1Mj1MLw, reason: not valid java name */
    public static final Modifier m184basicMarquee1Mj1MLw(Modifier modifier, int i10, int i11, int i12, int i13, MarqueeSpacing marqueeSpacing, float f10) {
        return modifier.then(new MarqueeModifierElement(i10, i11, i12, i13, marqueeSpacing, f10, null));
    }

    /* JADX INFO: renamed from: basicMarquee-1Mj1MLw$default, reason: not valid java name */
    public static /* synthetic */ Modifier m185basicMarquee1Mj1MLw$default(Modifier modifier, int i10, int i11, int i12, int i13, MarqueeSpacing marqueeSpacing, float f10, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = DefaultMarqueeIterations;
        }
        if ((i14 & 2) != 0) {
            i11 = MarqueeAnimationMode.INSTANCE.m263getImmediatelyZbEOnfQ();
        }
        if ((i14 & 4) != 0) {
            i12 = DefaultMarqueeDelayMillis;
        }
        if ((i14 & 8) != 0) {
            i13 = MarqueeAnimationMode.m257equalsimpl0(i11, MarqueeAnimationMode.INSTANCE.m263getImmediatelyZbEOnfQ()) ? i12 : 0;
        }
        if ((i14 & 16) != 0) {
            marqueeSpacing = DefaultMarqueeSpacing;
        }
        if ((i14 & 32) != 0) {
            f10 = DefaultMarqueeVelocity;
        }
        return m184basicMarquee1Mj1MLw(modifier, i10, i11, i12, i13, marqueeSpacing, f10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createMarqueeAnimationSpec-Z4HSEVQ, reason: not valid java name */
    public static final AnimationSpec<Float> m186createMarqueeAnimationSpecZ4HSEVQ(int i10, float f10, int i11, int i12, float f11, Density density) {
        TweenSpec<Float> tweenSpecVelocityBasedTween = velocityBasedTween(Math.abs(density.mo285toPx0680j_4(f11)), f10, i12);
        long jM115constructorimpl$default = StartOffset.m115constructorimpl$default((-i12) + i11, 0, 2, null);
        return i10 == Integer.MAX_VALUE ? AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(tweenSpecVelocityBasedTween, null, jM115constructorimpl$default, 2, null) : AnimationSpecKt.m95repeatable91I0pcU$default(i10, tweenSpecVelocityBasedTween, null, jM115constructorimpl$default, 4, null);
    }

    public static final int getDefaultMarqueeDelayMillis() {
        return DefaultMarqueeDelayMillis;
    }

    public static /* synthetic */ void getDefaultMarqueeDelayMillis$annotations() {
    }

    public static final int getDefaultMarqueeIterations() {
        return DefaultMarqueeIterations;
    }

    public static /* synthetic */ void getDefaultMarqueeIterations$annotations() {
    }

    public static final MarqueeSpacing getDefaultMarqueeSpacing() {
        return DefaultMarqueeSpacing;
    }

    public static /* synthetic */ void getDefaultMarqueeSpacing$annotations() {
    }

    public static final float getDefaultMarqueeVelocity() {
        return DefaultMarqueeVelocity;
    }

    public static /* synthetic */ void getDefaultMarqueeVelocity$annotations() {
    }

    private static final TweenSpec<Float> velocityBasedTween(float f10, float f11, int i10) {
        return AnimationSpecKt.tween((int) Math.ceil(f11 / (f10 / 1000.0f)), i10, EasingKt.getLinearEasing());
    }
}
