package androidx.media3.common.audio;

/* JADX INFO: loaded from: classes3.dex */
public final class ChannelMixingMatrix {
    private final float[] coefficients;
    private final int inputChannelCount;
    private final boolean isDiagonal;
    private final boolean isIdentity;
    private final boolean isZero;
    private final int outputChannelCount;

    public ChannelMixingMatrix(int i10, int i11, float[] fArr) {
        boolean z = false;
        ac.b.k(i10 > 0, "Input channel count must be positive.");
        ac.b.k(i11 > 0, "Output channel count must be positive.");
        ac.b.k(fArr.length == i10 * i11, "Coefficient array length is invalid.");
        this.inputChannelCount = i10;
        this.outputChannelCount = i11;
        this.coefficients = checkCoefficientsValid(fArr);
        int i12 = 0;
        boolean z5 = true;
        boolean z10 = true;
        boolean z11 = true;
        while (i12 < i10) {
            int i13 = 0;
            while (i13 < i11) {
                float mixingCoefficient = getMixingCoefficient(i12, i13);
                boolean z12 = i12 == i13;
                if (mixingCoefficient != 1.0f && z12) {
                    z11 = false;
                }
                if (mixingCoefficient != 0.0f) {
                    z5 = false;
                    if (!z12) {
                        z10 = false;
                    }
                }
                i13++;
            }
            i12++;
        }
        this.isZero = z5;
        boolean z13 = isSquare() && z10;
        this.isDiagonal = z13;
        if (z13 && z11) {
            z = true;
        }
        this.isIdentity = z;
    }

    private static float[] checkCoefficientsValid(float[] fArr) {
        for (int i10 = 0; i10 < fArr.length; i10++) {
            if (fArr[i10] < 0.0f) {
                throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Coefficient at index ", " is negative."));
            }
        }
        return fArr;
    }

    @Deprecated
    public static ChannelMixingMatrix create(int i10, int i11) {
        return createForConstantGain(i10, i11);
    }

    private static float[] createConstantGainMixingCoefficients(int i10, int i11) {
        if (i10 == i11) {
            return initializeIdentityMatrix(i11);
        }
        if (i10 == 1 && i11 == 2) {
            return new float[]{1.0f, 1.0f};
        }
        if (i10 == 2 && i11 == 1) {
            return new float[]{0.5f, 0.5f};
        }
        throw new UnsupportedOperationException(androidx.compose.foundation.c.s("Default channel mixing coefficients for ", i10, "->", i11, " are not yet implemented."));
    }

    private static float[] createConstantPowerMixingCoefficients(int i10, int i11) {
        if (i11 == 1) {
            return getConstantPowerCoefficientsToMono(i10);
        }
        if (i11 == 2) {
            return getConstantPowerCoefficientsToStereo(i10);
        }
        if (i10 == i11) {
            return initializeIdentityMatrix(i11);
        }
        throw new UnsupportedOperationException(androidx.compose.foundation.c.s("Default constant power channel mixing coefficients for ", i10, "->", i11, " are not implemented."));
    }

    public static ChannelMixingMatrix createForConstantGain(int i10, int i11) {
        return new ChannelMixingMatrix(i10, i11, createConstantGainMixingCoefficients(i10, i11));
    }

    public static ChannelMixingMatrix createForConstantPower(int i10, int i11) {
        return new ChannelMixingMatrix(i10, i11, createConstantPowerMixingCoefficients(i10, i11));
    }

    private static float[] getConstantPowerCoefficientsToMono(int i10) {
        switch (i10) {
            case 1:
                return new float[]{1.0f};
            case 2:
                return new float[]{0.7071f, 0.7071f};
            case 3:
                return new float[]{0.7071f, 0.7071f, 1.0f};
            case 4:
                return new float[]{0.7071f, 0.7071f, 0.5f, 0.5f};
            case 5:
                return new float[]{0.7071f, 0.7071f, 1.0f, 0.5f, 0.5f};
            case 6:
                return new float[]{0.7071f, 0.7071f, 1.0f, 0.7071f, 0.5f, 0.5f};
            default:
                throw new UnsupportedOperationException(androidx.compose.foundation.c.o(i10, "Default constant power channel mixing coefficients for ", "->1 are not implemented."));
        }
    }

    private static float[] getConstantPowerCoefficientsToStereo(int i10) {
        switch (i10) {
            case 1:
                return new float[]{0.7071f, 0.7071f};
            case 2:
                return new float[]{1.0f, 0.0f, 0.0f, 1.0f};
            case 3:
                return new float[]{1.0f, 0.0f, 0.7071f, 0.0f, 1.0f, 0.7071f};
            case 4:
                return new float[]{1.0f, 0.0f, 0.7071f, 0.0f, 0.0f, 1.0f, 0.0f, 0.7071f};
            case 5:
                return new float[]{1.0f, 0.0f, 0.7071f, 0.7071f, 0.0f, 0.0f, 1.0f, 0.7071f, 0.0f, 0.7071f};
            case 6:
                return new float[]{1.0f, 0.0f, 0.7071f, 0.5f, 0.7071f, 0.0f, 0.0f, 1.0f, 0.7071f, 0.5f, 0.0f, 0.7071f};
            default:
                throw new UnsupportedOperationException(androidx.compose.foundation.c.o(i10, "Default constant power channel mixing coefficients for ", "->2 are not implemented."));
        }
    }

    private static float[] initializeIdentityMatrix(int i10) {
        float[] fArr = new float[i10 * i10];
        for (int i11 = 0; i11 < i10; i11++) {
            fArr[(i10 * i11) + i11] = 1.0f;
        }
        return fArr;
    }

    public int getInputChannelCount() {
        return this.inputChannelCount;
    }

    public float getMixingCoefficient(int i10, int i11) {
        return this.coefficients[(i10 * this.outputChannelCount) + i11];
    }

    public int getOutputChannelCount() {
        return this.outputChannelCount;
    }

    public boolean isDiagonal() {
        return this.isDiagonal;
    }

    public boolean isIdentity() {
        return this.isIdentity;
    }

    public boolean isSquare() {
        return this.inputChannelCount == this.outputChannelCount;
    }

    public boolean isZero() {
        return this.isZero;
    }

    public ChannelMixingMatrix scaleBy(float f10) {
        float[] fArr = new float[this.coefficients.length];
        int i10 = 0;
        while (true) {
            float[] fArr2 = this.coefficients;
            if (i10 >= fArr2.length) {
                return new ChannelMixingMatrix(this.inputChannelCount, this.outputChannelCount, fArr);
            }
            fArr[i10] = fArr2[i10] * f10;
            i10++;
        }
    }
}
