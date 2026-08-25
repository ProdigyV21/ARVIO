package androidx.compose.animation.core;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a3\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\f\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0005H\u0080\n¢\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\u000e\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0005H\u0080\n¢\u0006\u0004\b\u000e\u0010\r\u001a\u001c\u0010\u000f\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0005H\u0080\n¢\u0006\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "c", "Lx6/x;", "Landroidx/compose/animation/core/ComplexDouble;", "complexQuadraticFormula", "(DDD)Lx6/x;", "num", "complexSqrt", "(D)Landroidx/compose/animation/core/ComplexDouble;", "other", "plus", "(DLandroidx/compose/animation/core/ComplexDouble;)Landroidx/compose/animation/core/ComplexDouble;", "minus", "times", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ComplexDoubleKt {
    public static final x complexQuadraticFormula(double d4, double d10, double d11) {
        double d12 = (d10 * d10) - ((4.0d * d4) * d11);
        double d13 = 1.0d / (d4 * 2.0d);
        double d14 = -d10;
        ComplexDouble complexDoubleComplexSqrt = complexSqrt(d12);
        complexDoubleComplexSqrt._real += d14;
        complexDoubleComplexSqrt._real *= d13;
        complexDoubleComplexSqrt._imaginary *= d13;
        ComplexDouble complexDoubleComplexSqrt2 = complexSqrt(d12);
        double d15 = -1;
        complexDoubleComplexSqrt2._real *= d15;
        complexDoubleComplexSqrt2._imaginary *= d15;
        complexDoubleComplexSqrt2._real += d14;
        complexDoubleComplexSqrt2._real *= d13;
        complexDoubleComplexSqrt2._imaginary *= d13;
        return new x(complexDoubleComplexSqrt, complexDoubleComplexSqrt2);
    }

    public static final ComplexDouble complexSqrt(double d4) {
        return d4 < 0.0d ? new ComplexDouble(0.0d, Math.sqrt(Math.abs(d4))) : new ComplexDouble(Math.sqrt(d4), 0.0d);
    }

    public static final ComplexDouble minus(double d4, ComplexDouble complexDouble) {
        double d10 = -1;
        complexDouble._real *= d10;
        complexDouble._imaginary *= d10;
        complexDouble._real += d4;
        return complexDouble;
    }

    public static final ComplexDouble plus(double d4, ComplexDouble complexDouble) {
        complexDouble._real += d4;
        return complexDouble;
    }

    public static final ComplexDouble times(double d4, ComplexDouble complexDouble) {
        complexDouble._real *= d4;
        complexDouble._imaginary *= d4;
        return complexDouble;
    }
}
