package androidx.compose.animation.core;

import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a5\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a5\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t¢\u0006\u0004\b\u0007\u0010\n\u001a=\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t¢\u0006\u0004\b\u0007\u0010\u000e\u001a/\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a/\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0015\u0010\u0014\u001a7\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a?\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001a@\u0010 \u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u001dH\u0082\b¢\u0006\u0004\b \u0010!\u001a\u0014\u0010#\u001a\u00020\"*\u00020\tH\u0082\b¢\u0006\u0004\b#\u0010$¨\u0006%"}, d2 = {"", "stiffness", "dampingRatio", "initialVelocity", "initialDisplacement", "delta", "", "estimateAnimationDurationMillis", "(FFFFF)J", "", "(DDDDD)J", "springConstant", "dampingCoefficient", "mass", "(DDDDDD)J", "Landroidx/compose/animation/core/ComplexDouble;", "firstRoot", "p0", "v0", "estimateUnderDamped", "(Landroidx/compose/animation/core/ComplexDouble;DDD)D", "estimateCriticallyDamped", "secondRoot", "estimateOverDamped", "(Landroidx/compose/animation/core/ComplexDouble;Landroidx/compose/animation/core/ComplexDouble;DDD)D", "initialPosition", "estimateDurationInternal", "(Landroidx/compose/animation/core/ComplexDouble;Landroidx/compose/animation/core/ComplexDouble;DDDD)J", "x", "Lkotlin/Function1;", "fn", "fnPrime", "iterateNewtonsMethod", "(DLr7/l;Lr7/l;)D", "", "isNotFinite", "(D)Z", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpringEstimationKt {
    public static final long estimateAnimationDurationMillis(float f10, float f11, float f12, float f13, float f14) {
        return estimateAnimationDurationMillis(f10, f11, f12, f13, f14);
    }

    private static final double estimateCriticallyDamped(ComplexDouble complexDouble, double d4, double d10, double d11) {
        double d12 = d11;
        double real = complexDouble.getReal();
        double d13 = real * d4;
        double d14 = d10 - d13;
        double dLog = Math.log(Math.abs(d12 / d4)) / real;
        double dLog2 = Math.log(Math.abs(d12 / d14));
        double dLog3 = dLog2;
        for (int i10 = 0; i10 < 6; i10++) {
            dLog3 = dLog2 - Math.log(Math.abs(dLog3 / real));
        }
        double d15 = dLog3 / real;
        if (!((Double.isInfinite(dLog) || Double.isNaN(dLog)) ? false : true)) {
            dLog = d15;
        } else if ((Double.isInfinite(d15) || Double.isNaN(d15)) ? false : true) {
            dLog = Math.max(dLog, d15);
        }
        double d16 = (-(d13 + d14)) / (real * d14);
        double d17 = real * d16;
        double dExp = (Math.exp(d17) * d14 * d16) + (Math.exp(d17) * d4);
        if (Double.isNaN(d16) || d16 <= 0.0d) {
            d12 = -d12;
        } else if (d16 <= 0.0d || (-dExp) >= d12) {
            dLog = (-(2.0d / real)) - (d4 / d14);
        } else {
            if (d14 < 0.0d && d4 > 0.0d) {
                dLog = 0.0d;
            }
            d12 = -d12;
        }
        double dAbs = Double.MAX_VALUE;
        int i11 = 0;
        while (dAbs > 0.001d && i11 < 100) {
            i11++;
            double d18 = real * dLog;
            double dExp2 = dLog - (((Math.exp(d18) * ((d14 * dLog) + d4)) + d12) / (Math.exp(d18) * (((((double) 1) + d18) * d14) + d13)));
            dAbs = Math.abs(dLog - dExp2);
            dLog = dExp2;
        }
        return dLog;
    }

    private static final long estimateDurationInternal(ComplexDouble complexDouble, ComplexDouble complexDouble2, double d4, double d10, double d11, double d12) {
        if (d11 == 0.0d && d10 == 0.0d) {
            return 0L;
        }
        if (d11 < 0.0d) {
            d10 = -d10;
        }
        double d13 = d10;
        double dAbs = Math.abs(d11);
        return (long) ((d4 > 1.0d ? estimateOverDamped(complexDouble, complexDouble2, dAbs, d13, d12) : d4 < 1.0d ? estimateUnderDamped(complexDouble, dAbs, d13, d12) : estimateCriticallyDamped(complexDouble, dAbs, d13, d12)) * 1000.0d);
    }

    private static final double estimateOverDamped(ComplexDouble complexDouble, ComplexDouble complexDouble2, double d4, double d10, double d11) {
        double d12 = d11;
        double real = complexDouble.getReal();
        double real2 = complexDouble2.getReal();
        double d13 = real - real2;
        double d14 = ((real * d4) - d10) / d13;
        double d15 = d4 - d14;
        double dLog = Math.log(Math.abs(d12 / d15)) / real;
        double dLog2 = Math.log(Math.abs(d12 / d14)) / real2;
        if ((Double.isInfinite(dLog) || Double.isNaN(dLog)) ? false : true) {
            if ((Double.isInfinite(dLog2) || Double.isNaN(dLog2)) ? false : true) {
                dLog = Math.max(dLog, dLog2);
            }
            dLog2 = dLog;
        }
        double d16 = d15 * real;
        double dLog3 = Math.log(d16 / ((-d14) * real2)) / (real2 - real);
        if (Double.isNaN(dLog3) || dLog3 <= 0.0d) {
            d12 = -d12;
        } else if (dLog3 <= 0.0d || (-estimateOverDamped$xInflection(d15, real, dLog3, d14, real2)) >= d12) {
            dLog2 = Math.log((-((d14 * real2) * real2)) / (d16 * real)) / d13;
        } else {
            if (d14 > 0.0d && d15 < 0.0d) {
                dLog2 = 0.0d;
            }
            d12 = -d12;
        }
        double d17 = d14 * real2;
        if (Math.abs((Math.exp(real2 * dLog2) * d17) + (Math.exp(real * dLog2) * d16)) < 1.0E-4d) {
            return dLog2;
        }
        double d18 = Double.MAX_VALUE;
        double d19 = d12;
        int i10 = 0;
        while (d18 > 0.001d && i10 < 100) {
            i10++;
            double d20 = real * dLog2;
            double d21 = real2 * dLog2;
            double dExp = dLog2 - ((((Math.exp(d21) * d14) + (Math.exp(d20) * d15)) + d19) / ((Math.exp(d21) * d17) + (Math.exp(d20) * d16)));
            double dAbs = Math.abs(dLog2 - dExp);
            dLog2 = dExp;
            d18 = dAbs;
        }
        return dLog2;
    }

    private static final double estimateOverDamped$xInflection(double d4, double d10, double d11, double d12, double d13) {
        return (Math.exp(d13 * d11) * d12) + (Math.exp(d10 * d11) * d4);
    }

    private static final double estimateUnderDamped(ComplexDouble complexDouble, double d4, double d10, double d11) {
        double real = complexDouble.getReal();
        double imaginary = (d10 - (real * d4)) / complexDouble.getImaginary();
        return Math.log(d11 / Math.sqrt((imaginary * imaginary) + (d4 * d4))) / real;
    }

    private static final boolean isNotFinite(double d4) {
        return !((Double.isInfinite(d4) || Double.isNaN(d4)) ? false : true);
    }

    private static final double iterateNewtonsMethod(double d4, l<? super Double, Double> lVar, l<? super Double, Double> lVar2) {
        return d4 - (((Number) lVar.invoke(Double.valueOf(d4))).doubleValue() / ((Number) lVar2.invoke(Double.valueOf(d4))).doubleValue());
    }

    public static final long estimateAnimationDurationMillis(double d4, double d10, double d11, double d12, double d13) {
        double dSqrt = Math.sqrt(d4) * 2.0d * d10;
        double d14 = (dSqrt * dSqrt) - (d4 * 4.0d);
        double d15 = -dSqrt;
        ComplexDouble complexDoubleComplexSqrt = ComplexDoubleKt.complexSqrt(d14);
        complexDoubleComplexSqrt._real += d15;
        complexDoubleComplexSqrt._real *= 0.5d;
        complexDoubleComplexSqrt._imaginary *= 0.5d;
        ComplexDouble complexDoubleComplexSqrt2 = ComplexDoubleKt.complexSqrt(d14);
        double d16 = -1;
        complexDoubleComplexSqrt2._real *= d16;
        complexDoubleComplexSqrt2._imaginary *= d16;
        complexDoubleComplexSqrt2._real += d15;
        complexDoubleComplexSqrt2._real *= 0.5d;
        complexDoubleComplexSqrt2._imaginary *= 0.5d;
        return estimateDurationInternal(complexDoubleComplexSqrt, complexDoubleComplexSqrt2, d10, d11, d12, d13);
    }

    public static final long estimateAnimationDurationMillis(double d4, double d10, double d11, double d12, double d13, double d14) {
        double dSqrt = d10 / (Math.sqrt(d4 * d11) * 2.0d);
        double d15 = (d10 * d10) - ((4.0d * d11) * d4);
        double d16 = 1.0d / (2.0d * d11);
        double d17 = -d10;
        ComplexDouble complexDoubleComplexSqrt = ComplexDoubleKt.complexSqrt(d15);
        complexDoubleComplexSqrt._real += d17;
        complexDoubleComplexSqrt._real *= d16;
        complexDoubleComplexSqrt._imaginary *= d16;
        ComplexDouble complexDoubleComplexSqrt2 = ComplexDoubleKt.complexSqrt(d15);
        double d18 = -1;
        complexDoubleComplexSqrt2._real *= d18;
        complexDoubleComplexSqrt2._imaginary *= d18;
        complexDoubleComplexSqrt2._real += d17;
        complexDoubleComplexSqrt2._real *= d16;
        complexDoubleComplexSqrt2._imaginary *= d16;
        return estimateDurationInternal(complexDoubleComplexSqrt, complexDoubleComplexSqrt2, dSqrt, d12, d13, d14);
    }
}
