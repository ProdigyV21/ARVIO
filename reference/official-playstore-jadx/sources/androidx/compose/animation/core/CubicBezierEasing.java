package androidx.compose.animation.core;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J \u0010\f\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/animation/core/CubicBezierEasing;", "Landroidx/compose/animation/core/Easing;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "", "b", "c", "d", "(FFFF)V", "equals", "", "other", "", "evaluateCubic", CmcdData.OBJECT_TYPE_MANIFEST, "hashCode", "", "transform", "fraction", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CubicBezierEasing implements Easing {
    public static final int $stable = 0;
    private final float a;
    private final float b;
    private final float c;
    private final float d;

    public CubicBezierEasing(float f10, float f11, float f12, float f13) {
        this.a = f10;
        this.b = f11;
        this.c = f12;
        this.d = f13;
        if (Float.isNaN(f10) || Float.isNaN(f11) || Float.isNaN(f12) || Float.isNaN(f13)) {
            throw new IllegalArgumentException(("Parameters to CubicBezierEasing cannot be NaN. Actual parameters are: " + f10 + ", " + f11 + ", " + f12 + ", " + f13 + '.').toString());
        }
    }

    private final float evaluateCubic(float a10, float b10, float m10) {
        float f10 = 3;
        float f11 = 1 - m10;
        return (m10 * m10 * m10) + (f10 * b10 * f11 * m10 * m10) + (a10 * f10 * f11 * f11 * m10);
    }

    public boolean equals(Object other) {
        if (!(other instanceof CubicBezierEasing)) {
            return false;
        }
        CubicBezierEasing cubicBezierEasing = (CubicBezierEasing) other;
        return this.a == cubicBezierEasing.a && this.b == cubicBezierEasing.b && this.c == cubicBezierEasing.c && this.d == cubicBezierEasing.d;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.d) + a0.c.b(this.c, a0.c.b(this.b, Float.floatToIntBits(this.a) * 31, 31), 31);
    }

    @Override // androidx.compose.animation.core.Easing
    public float transform(float fraction) {
        float f10 = 0.0f;
        if (fraction > 0.0f) {
            float f11 = 1.0f;
            if (fraction < 1.0f) {
                while (true) {
                    float f12 = (f10 + f11) / 2;
                    float fEvaluateCubic = evaluateCubic(this.a, this.c, f12);
                    if (Math.abs(fraction - fEvaluateCubic) < 0.001f) {
                        return evaluateCubic(this.b, this.d, f12);
                    }
                    if (fEvaluateCubic < fraction) {
                        f10 = f12;
                    } else {
                        f11 = f12;
                    }
                }
            }
        }
        return fraction;
    }
}
