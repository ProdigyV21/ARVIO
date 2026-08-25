package androidx.compose.animation;

import a0.c;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\u001a'\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a!\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\r\"\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\"\u0014\u0010\u0011\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0011\u0010\u0010\"\u0014\u0010\u0012\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0010\"\u0014\u0010\u0013\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0010\"\u0014\u0010\u0014\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0010¨\u0006\u0015"}, d2 = {"", "splinePositions", "splineTimes", "", "nbSamples", "Lx6/t0;", "computeSplineInfo", "([F[FI)V", "T", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/animation/core/DecayAnimationSpec;", "splineBasedDecay", "(Landroidx/compose/ui/unit/Density;)Landroidx/compose/animation/core/DecayAnimationSpec;", "", "Inflection", "F", "StartTension", "EndTension", "P1", "P2", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SplineBasedDecayKt {
    private static final float EndTension = 1.0f;
    private static final float Inflection = 0.35f;
    private static final float P1 = 0.175f;
    private static final float P2 = 0.35000002f;
    private static final float StartTension = 0.5f;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void computeSplineInfo(float[] fArr, float[] fArr2, int i10) {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19 = 0.0f;
        int i11 = 0;
        float f20 = 0.0f;
        while (true) {
            float f21 = 1.0f;
            if (i11 >= i10) {
                fArr2[i10] = 1.0f;
                fArr[i10] = 1.0f;
                return;
            }
            float f22 = i11 / i10;
            float f23 = 1.0f;
            while (true) {
                f10 = ((f23 - f19) / 2.0f) + f19;
                f11 = f21 - f10;
                f12 = f10 * 3.0f * f11;
                f13 = f10 * f10 * f10;
                float fA = c.a(f10, P2, f11 * P1, f12) + f13;
                f14 = f21;
                if (Math.abs(fA - f22) < 1.0E-5d) {
                    break;
                }
                if (fA > f22) {
                    f23 = f10;
                } else {
                    f19 = f10;
                }
                f21 = f14;
            }
            float f24 = 0.5f;
            fArr[i11] = (((f11 * 0.5f) + f10) * f12) + f13;
            float f25 = f14;
            while (true) {
                f15 = ((f25 - f20) / 2.0f) + f20;
                f16 = f14 - f15;
                f17 = f15 * 3.0f * f16;
                f18 = f15 * f15 * f15;
                float fA2 = c.a(f16, f24, f15, f17) + f18;
                float f26 = f22;
                if (Math.abs(fA2 - f22) >= 1.0E-5d) {
                    if (fA2 > f26) {
                        f25 = f15;
                    } else {
                        f20 = f15;
                    }
                    f22 = f26;
                    f24 = 0.5f;
                }
            }
            fArr2[i11] = (((f15 * P2) + (f16 * P1)) * f17) + f18;
            i11++;
        }
    }

    public static final <T> DecayAnimationSpec<T> splineBasedDecay(Density density) {
        return DecayAnimationSpecKt.generateDecayAnimationSpec(new SplineBasedFloatDecayAnimationSpec(density));
    }
}
