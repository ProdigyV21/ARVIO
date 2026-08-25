package androidx.media3.common.audio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
final class Sonic {
    private static final int AMDF_FREQUENCY = 4000;
    private static final int MAXIMUM_PITCH = 400;
    private static final int MINIMUM_PITCH = 65;
    private static final float MINIMUM_SLOWDOWN_RATE = 0.99999f;
    private static final float MINIMUM_SPEEDUP_RATE = 1.00001f;
    private double accumulatedSpeedAdjustmentError;
    private final int channelCount;
    private final SonicImpl<?> impl;
    private int inputFrameCount;
    private final int inputSampleRateHz;
    private final int maxPeriod;
    private final int maxRequiredFrameCount;
    private final int minPeriod;
    private int newRatePosition;
    private int oldRatePosition;
    private int outputFrameCount;
    private final float pitch;
    private int pitchFrameCount;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopyFrameCount;
    private final float speed;

    public final class SonicFloatImpl implements SonicImpl<float[]> {
        private final float[] downSampleBuffer;
        private float[] inputBuffer;
        private double maxDiff;
        private double minDiff;
        private float[] outputBuffer;
        private float[] pitchBuffer;
        private double prevMinDiff;

        public SonicFloatImpl() {
            this.downSampleBuffer = new float[Sonic.this.maxRequiredFrameCount];
            this.inputBuffer = new float[Sonic.this.maxRequiredFrameCount * Sonic.this.channelCount];
            this.outputBuffer = new float[Sonic.this.maxRequiredFrameCount * Sonic.this.channelCount];
            this.pitchBuffer = new float[Sonic.this.maxRequiredFrameCount * Sonic.this.channelCount];
        }

        private float[] ensureSpaceForAdditionalFrames(float[] fArr, int i10, int i11) {
            int length = fArr.length / Sonic.this.channelCount;
            return i10 + i11 <= length ? fArr : Arrays.copyOf(fArr, (((length * 3) / 2) + i11) * Sonic.this.channelCount);
        }

        private int findPitchPeriodInRange(float[] fArr, int i10, int i11, int i12) {
            int i13 = Sonic.this.channelCount * i10;
            double d4 = 1.0d;
            int i14 = 0;
            double d10 = 0.0d;
            int i15 = 255;
            int i16 = i11;
            while (i16 <= i12) {
                double dAbs = 0.0d;
                for (int i17 = 0; i17 < i16; i17++) {
                    dAbs += (double) Math.abs(fArr[i13 + i17] - fArr[(i13 + i16) + i17]);
                }
                int i18 = i13;
                double d11 = i16;
                if (((double) i14) * dAbs < d4 * d11) {
                    i14 = i16;
                    d4 = dAbs;
                }
                if (((double) i15) * dAbs > d11 * d10) {
                    i15 = i16;
                    d10 = dAbs;
                }
                i16++;
                i13 = i18;
            }
            this.minDiff = d4 / ((double) i14);
            this.maxDiff = d10 / ((double) i15);
            return i14;
        }

        private float interpolate(float[] fArr, int i10, long j10, long j11) {
            float f10 = fArr[i10];
            float f11 = fArr[i10 + Sonic.this.channelCount];
            long j12 = ((long) Sonic.this.newRatePosition) * j10;
            long j13 = ((long) Sonic.this.oldRatePosition) * j11;
            long j14 = ((long) (Sonic.this.oldRatePosition + 1)) * j11;
            long j15 = j14 - j12;
            return (((r3 - j15) * f11) + (j15 * f10)) / (j14 - j13);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public int bytesPerSample() {
            return 4;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void copyBufferToInputBuffer(ByteBuffer byteBuffer, int i10) {
            byteBuffer.asFloatBuffer().get(this.inputBuffer, Sonic.this.inputFrameCount * Sonic.this.channelCount, i10 / bytesPerSample());
            byteBuffer.position(byteBuffer.position() + i10);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void copyOutputToByteBuffer(ByteBuffer byteBuffer, int i10) {
            byteBuffer.asFloatBuffer().put(this.outputBuffer, 0, Sonic.this.channelCount * i10);
            byteBuffer.position((i10 * bytesPerSample() * Sonic.this.channelCount) + byteBuffer.position());
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void downSampleInput(int i10, int i11) {
            int i12 = Sonic.this.maxRequiredFrameCount / i11;
            int i13 = Sonic.this.channelCount * i11;
            int i14 = i10 * Sonic.this.channelCount;
            for (int i15 = 0; i15 < i12; i15++) {
                double d4 = 0.0d;
                for (int i16 = 0; i16 < i13; i16++) {
                    d4 += (double) this.inputBuffer[(i15 * i13) + i14 + i16];
                }
                this.downSampleBuffer[i15] = (float) (d4 / ((double) i13));
            }
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void ensureAdditionalFramesInInputBuffer(int i10) {
            this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, Sonic.this.inputFrameCount, i10);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void ensureAdditionalFramesInOutputBuffer(int i10) {
            this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, Sonic.this.outputFrameCount, i10);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void ensureAdditionalFramesInPitchBuffer(int i10) {
            this.pitchBuffer = ensureSpaceForAdditionalFrames(this.pitchBuffer, Sonic.this.pitchFrameCount, i10);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public int findPitchPeriodInRangeWithDownsampleBuffer(int i10, int i11, int i12) {
            return findPitchPeriodInRange(this.downSampleBuffer, i10, i11, i12);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public int findPitchPeriodInRangeWithInputBuffer(int i10, int i11, int i12) {
            return findPitchPeriodInRange(this.inputBuffer, i10, i11, i12);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void flush() {
            this.prevMinDiff = 0.0d;
            this.minDiff = 0.0d;
            this.maxDiff = 0.0d;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void interpolateFrame(int i10, long j10, long j11) {
            int i11 = 0;
            while (i11 < Sonic.this.channelCount) {
                long j12 = j10;
                this.outputBuffer[(Sonic.this.outputFrameCount * Sonic.this.channelCount) + i11] = interpolate(this.pitchBuffer, (Sonic.this.channelCount * i10) + i11, j12, j11);
                i11++;
                j10 = j12;
            }
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public boolean isPreviousPeriodBetter() {
            if (this.minDiff == 0.0d || Sonic.this.prevPeriod == 0) {
                return false;
            }
            double d4 = this.maxDiff;
            double d10 = this.minDiff;
            return d4 <= d10 * 3.0d && d10 * 2.0d > this.prevMinDiff * 3.0d;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void overlapAdd(int i10, int i11, int i12, int i13, int i14) {
            float[] fArr = this.outputBuffer;
            float[] fArr2 = this.inputBuffer;
            overlapAdd(i10, i11, fArr, i12, fArr2, i13, fArr2, i14);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void updatePreviousMinDiff() {
            this.prevMinDiff = this.minDiff;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void zeroInputBuffer(int i10, int i11) {
            for (int i12 = 0; i12 < Sonic.this.channelCount * i11; i12++) {
                this.inputBuffer[i10 + i12] = 0.0f;
            }
        }

        private void overlapAdd(int i10, int i11, float[] fArr, int i12, float[] fArr2, int i13, float[] fArr3, int i14) {
            for (int i15 = 0; i15 < i11; i15++) {
                int i16 = (i12 * i11) + i15;
                int i17 = (i14 * i11) + i15;
                int i18 = (i13 * i11) + i15;
                for (int i19 = 0; i19 < i10; i19++) {
                    fArr[i16] = ((fArr3[i17] * i19) + (fArr2[i18] * (i10 - i19))) / i10;
                    i16 += i11;
                    i18 += i11;
                    i17 += i11;
                }
            }
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public float[] getInputBuffer() {
            return this.inputBuffer;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public float[] getOutputBuffer() {
            return this.outputBuffer;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public float[] getPitchBuffer() {
            return this.pitchBuffer;
        }
    }

    public interface SonicImpl<T> {
        int bytesPerSample();

        void copyBufferToInputBuffer(ByteBuffer byteBuffer, int i10);

        void copyOutputToByteBuffer(ByteBuffer byteBuffer, int i10);

        void downSampleInput(int i10, int i11);

        void ensureAdditionalFramesInInputBuffer(int i10);

        void ensureAdditionalFramesInOutputBuffer(int i10);

        void ensureAdditionalFramesInPitchBuffer(int i10);

        int findPitchPeriodInRangeWithDownsampleBuffer(int i10, int i11, int i12);

        int findPitchPeriodInRangeWithInputBuffer(int i10, int i11, int i12);

        void flush();

        T getInputBuffer();

        T getOutputBuffer();

        T getPitchBuffer();

        void interpolateFrame(int i10, long j10, long j11);

        boolean isPreviousPeriodBetter();

        void overlapAdd(int i10, int i11, int i12, int i13, int i14);

        void updatePreviousMinDiff();

        void zeroInputBuffer(int i10, int i11);
    }

    public final class SonicShortImpl implements SonicImpl<short[]> {
        private final short[] downSampleBuffer;
        private short[] inputBuffer;
        private int maxDiff;
        private int minDiff;
        private short[] outputBuffer;
        private short[] pitchBuffer;
        private int prevMinDiff;

        public SonicShortImpl() {
            this.downSampleBuffer = new short[Sonic.this.maxRequiredFrameCount];
            this.inputBuffer = new short[Sonic.this.maxRequiredFrameCount * Sonic.this.channelCount];
            this.outputBuffer = new short[Sonic.this.maxRequiredFrameCount * Sonic.this.channelCount];
            this.pitchBuffer = new short[Sonic.this.maxRequiredFrameCount * Sonic.this.channelCount];
        }

        private short[] ensureSpaceForAdditionalFrames(short[] sArr, int i10, int i11) {
            int length = sArr.length / Sonic.this.channelCount;
            return i10 + i11 <= length ? sArr : Arrays.copyOf(sArr, (((length * 3) / 2) + i11) * Sonic.this.channelCount);
        }

        private int findPitchPeriodInRange(short[] sArr, int i10, int i11, int i12) {
            int i13 = i10 * Sonic.this.channelCount;
            int i14 = 255;
            int i15 = 1;
            int i16 = 0;
            int i17 = 0;
            while (i11 <= i12) {
                int iAbs = 0;
                for (int i18 = 0; i18 < i11; i18++) {
                    iAbs += Math.abs(sArr[i13 + i18] - sArr[(i13 + i11) + i18]);
                }
                if (iAbs * i16 < i15 * i11) {
                    i16 = i11;
                    i15 = iAbs;
                }
                if (iAbs * i14 > i17 * i11) {
                    i14 = i11;
                    i17 = iAbs;
                }
                i11++;
            }
            this.minDiff = i15 / i16;
            this.maxDiff = i17 / i14;
            return i16;
        }

        private short interpolate(short[] sArr, int i10, long j10, long j11) {
            short s10 = sArr[i10];
            short s11 = sArr[i10 + Sonic.this.channelCount];
            long j12 = ((long) Sonic.this.newRatePosition) * j10;
            long j13 = ((long) Sonic.this.oldRatePosition) * j11;
            long j14 = ((long) (Sonic.this.oldRatePosition + 1)) * j11;
            long j15 = j14 - j12;
            long j16 = j14 - j13;
            return (short) ((((j16 - j15) * ((long) s11)) + (((long) s10) * j15)) / j16);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public int bytesPerSample() {
            return 2;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void copyBufferToInputBuffer(ByteBuffer byteBuffer, int i10) {
            byteBuffer.asShortBuffer().get(this.inputBuffer, Sonic.this.inputFrameCount * Sonic.this.channelCount, i10 / 2);
            byteBuffer.position(byteBuffer.position() + i10);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void copyOutputToByteBuffer(ByteBuffer byteBuffer, int i10) {
            byteBuffer.asShortBuffer().put(this.outputBuffer, 0, Sonic.this.channelCount * i10);
            byteBuffer.position((i10 * bytesPerSample() * Sonic.this.channelCount) + byteBuffer.position());
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void downSampleInput(int i10, int i11) {
            short[] sArr = this.inputBuffer;
            int i12 = Sonic.this.maxRequiredFrameCount / i11;
            int i13 = Sonic.this.channelCount * i11;
            int i14 = i10 * Sonic.this.channelCount;
            for (int i15 = 0; i15 < i12; i15++) {
                int i16 = 0;
                for (int i17 = 0; i17 < i13; i17++) {
                    i16 += sArr[(i15 * i13) + i14 + i17];
                }
                this.downSampleBuffer[i15] = (short) (i16 / i13);
            }
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void ensureAdditionalFramesInInputBuffer(int i10) {
            this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, Sonic.this.inputFrameCount, i10);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void ensureAdditionalFramesInOutputBuffer(int i10) {
            this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, Sonic.this.outputFrameCount, i10);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void ensureAdditionalFramesInPitchBuffer(int i10) {
            this.pitchBuffer = ensureSpaceForAdditionalFrames(this.pitchBuffer, Sonic.this.pitchFrameCount, i10);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public int findPitchPeriodInRangeWithDownsampleBuffer(int i10, int i11, int i12) {
            return findPitchPeriodInRange(this.downSampleBuffer, i10, i11, i12);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public int findPitchPeriodInRangeWithInputBuffer(int i10, int i11, int i12) {
            return findPitchPeriodInRange(this.inputBuffer, i10, i11, i12);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void flush() {
            this.prevMinDiff = 0;
            this.minDiff = 0;
            this.maxDiff = 0;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void interpolateFrame(int i10, long j10, long j11) {
            int i11 = 0;
            while (i11 < Sonic.this.channelCount) {
                long j12 = j10;
                this.outputBuffer[(Sonic.this.outputFrameCount * Sonic.this.channelCount) + i11] = interpolate(this.pitchBuffer, (Sonic.this.channelCount * i10) + i11, j12, j11);
                i11++;
                j10 = j12;
            }
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public boolean isPreviousPeriodBetter() {
            if (this.minDiff == 0 || Sonic.this.prevPeriod == 0) {
                return false;
            }
            int i10 = this.maxDiff;
            int i11 = this.minDiff;
            return i10 <= i11 * 3 && i11 * 2 > this.prevMinDiff * 3;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void overlapAdd(int i10, int i11, int i12, int i13, int i14) {
            short[] sArr = this.outputBuffer;
            short[] sArr2 = this.inputBuffer;
            overlapAdd(i10, i11, sArr, i12, sArr2, i13, sArr2, i14);
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void updatePreviousMinDiff() {
            this.prevMinDiff = this.minDiff;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public void zeroInputBuffer(int i10, int i11) {
            for (int i12 = 0; i12 < Sonic.this.channelCount * i11; i12++) {
                this.inputBuffer[i10 + i12] = 0;
            }
        }

        private void overlapAdd(int i10, int i11, short[] sArr, int i12, short[] sArr2, int i13, short[] sArr3, int i14) {
            for (int i15 = 0; i15 < i11; i15++) {
                int i16 = (i12 * i11) + i15;
                int i17 = (i14 * i11) + i15;
                int i18 = (i13 * i11) + i15;
                for (int i19 = 0; i19 < i10; i19++) {
                    sArr[i16] = (short) (((sArr3[i17] * i19) + ((i10 - i19) * sArr2[i18])) / i10);
                    i16 += i11;
                    i18 += i11;
                    i17 += i11;
                }
            }
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public short[] getInputBuffer() {
            return this.inputBuffer;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public short[] getOutputBuffer() {
            return this.outputBuffer;
        }

        @Override // androidx.media3.common.audio.Sonic.SonicImpl
        public short[] getPitchBuffer() {
            return this.pitchBuffer;
        }
    }

    public Sonic(int i10, int i11, float f10, float f11, int i12, boolean z) {
        this.inputSampleRateHz = i10;
        this.channelCount = i11;
        this.speed = f10;
        this.pitch = f11;
        this.rate = i10 / i12;
        this.minPeriod = i10 / 400;
        int i13 = i10 / MINIMUM_PITCH;
        this.maxPeriod = i13;
        this.maxRequiredFrameCount = i13 * 2;
        this.impl = z ? new SonicFloatImpl() : new SonicShortImpl();
    }

    private void adjustRate(float f10, int i10) {
        int i11;
        int i12;
        if (this.outputFrameCount == i10) {
            return;
        }
        int i13 = this.inputSampleRateHz;
        long j10 = (long) (i13 / f10);
        long j11 = i13;
        while (j10 != 0 && j11 != 0 && j10 % 2 == 0 && j11 % 2 == 0) {
            j10 /= 2;
            j11 /= 2;
        }
        moveNewSamplesToPitchBuffer(i10);
        int i14 = 0;
        while (true) {
            int i15 = this.pitchFrameCount;
            if (i14 >= i15 - 1) {
                removePitchFrames(i15 - 1);
                return;
            }
            while (true) {
                i11 = this.oldRatePosition;
                long j12 = ((long) (i11 + 1)) * j10;
                i12 = this.newRatePosition;
                if (j12 <= ((long) i12) * j11) {
                    break;
                }
                this.impl.ensureAdditionalFramesInOutputBuffer(1);
                this.impl.interpolateFrame(i14, j11, j10);
                this.newRatePosition++;
                this.outputFrameCount++;
            }
            int i16 = i11 + 1;
            this.oldRatePosition = i16;
            if (i16 == j11) {
                this.oldRatePosition = 0;
                ac.b.s(((long) i12) == j10);
                this.newRatePosition = 0;
            }
            i14++;
        }
    }

    public static long calculateAccumulatedTruncationErrorForResampling(BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3) {
        RoundingMode roundingMode = RoundingMode.HALF_EVEN;
        BigDecimal bigDecimalDivide = bigDecimal.divide(bigDecimal2, 20, roundingMode);
        BigDecimal bigDecimalDivide2 = bigDecimal2.divide(bigDecimal3, 20, roundingMode);
        RoundingMode roundingMode2 = RoundingMode.FLOOR;
        return bigDecimalDivide.multiply(bigDecimalDivide2.subtract(bigDecimalDivide2.setScale(0, roundingMode2))).setScale(0, roundingMode2).longValueExact();
    }

    private void changeSpeed(double d4) {
        int iInsertPitchPeriod;
        int i10 = this.inputFrameCount;
        if (i10 < this.maxRequiredFrameCount) {
            return;
        }
        int iSkipPitchPeriod = 0;
        do {
            if (this.remainingInputToCopyFrameCount > 0) {
                iInsertPitchPeriod = copyInputToOutput(iSkipPitchPeriod);
            } else {
                int iFindPitchPeriod = findPitchPeriod(iSkipPitchPeriod);
                if (d4 > 1.0d) {
                    iSkipPitchPeriod = iFindPitchPeriod + skipPitchPeriod(iSkipPitchPeriod, d4, iFindPitchPeriod) + iSkipPitchPeriod;
                } else {
                    iInsertPitchPeriod = insertPitchPeriod(iSkipPitchPeriod, d4, iFindPitchPeriod);
                }
            }
            iSkipPitchPeriod += iInsertPitchPeriod;
        } while (this.maxRequiredFrameCount + iSkipPitchPeriod <= i10);
        removeProcessedInputFrames(iSkipPitchPeriod);
    }

    private int copyInputToOutput(int i10) {
        int iMin = Math.min(this.maxRequiredFrameCount, this.remainingInputToCopyFrameCount);
        copyToOutput(i10, iMin);
        this.remainingInputToCopyFrameCount -= iMin;
        return iMin;
    }

    private void copyToOutput(int i10, int i11) {
        this.impl.ensureAdditionalFramesInOutputBuffer(i11);
        Object inputBuffer = this.impl.getInputBuffer();
        int i12 = i10 * this.channelCount;
        Object outputBuffer = this.impl.getOutputBuffer();
        int i13 = this.outputFrameCount;
        int i14 = this.channelCount;
        System.arraycopy(inputBuffer, i12, outputBuffer, i13 * i14, i14 * i11);
        this.outputFrameCount += i11;
    }

    private int findPitchPeriod(int i10) {
        int iFindPitchPeriodInRangeWithDownsampleBuffer;
        int i11 = this.inputSampleRateHz;
        int i12 = i11 > AMDF_FREQUENCY ? i11 / AMDF_FREQUENCY : 1;
        if (this.channelCount == 1 && i12 == 1) {
            iFindPitchPeriodInRangeWithDownsampleBuffer = this.impl.findPitchPeriodInRangeWithInputBuffer(i10, this.minPeriod, this.maxPeriod);
        } else {
            this.impl.downSampleInput(i10, i12);
            int iFindPitchPeriodInRangeWithDownsampleBuffer2 = this.impl.findPitchPeriodInRangeWithDownsampleBuffer(0, this.minPeriod / i12, this.maxPeriod / i12);
            if (i12 != 1) {
                int i13 = iFindPitchPeriodInRangeWithDownsampleBuffer2 * i12;
                int i14 = i12 * 4;
                int i15 = i13 - i14;
                int i16 = i13 + i14;
                int i17 = this.minPeriod;
                if (i15 < i17) {
                    i15 = i17;
                }
                int i18 = this.maxPeriod;
                if (i16 > i18) {
                    i16 = i18;
                }
                if (this.channelCount == 1) {
                    iFindPitchPeriodInRangeWithDownsampleBuffer = this.impl.findPitchPeriodInRangeWithInputBuffer(i10, i15, i16);
                } else {
                    this.impl.downSampleInput(i10, 1);
                    iFindPitchPeriodInRangeWithDownsampleBuffer = this.impl.findPitchPeriodInRangeWithDownsampleBuffer(0, i15, i16);
                }
            } else {
                iFindPitchPeriodInRangeWithDownsampleBuffer = iFindPitchPeriodInRangeWithDownsampleBuffer2;
            }
        }
        int i19 = this.impl.isPreviousPeriodBetter() ? this.prevPeriod : iFindPitchPeriodInRangeWithDownsampleBuffer;
        this.impl.updatePreviousMinDiff();
        this.prevPeriod = iFindPitchPeriodInRangeWithDownsampleBuffer;
        return i19;
    }

    public static long getExpectedFrameCountAfterProcessorApplied(int i10, int i11, float f10, float f11, long j10) {
        float f12 = (i10 / i11) * f11;
        double d4 = f10 / f11;
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(f12));
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(j10);
        if (d4 > 1.0000100135803223d || d4 < 0.9999899864196777d) {
            bigDecimalValueOf = bigDecimalValueOf.divide(BigDecimal.valueOf(d4), RoundingMode.HALF_EVEN);
        }
        return f12 == 1.0f ? bigDecimalValueOf.longValueExact() : bigDecimalValueOf.divide(bigDecimal, RoundingMode.HALF_EVEN).longValueExact() - calculateAccumulatedTruncationErrorForResampling(bigDecimalValueOf, BigDecimal.valueOf(i10), bigDecimal);
    }

    public static long getExpectedInputFrameCountForOutputFrameCount(int i10, int i11, float f10, float f11, long j10) {
        long frameCountBeforeResamplingForOutputCount = getFrameCountBeforeResamplingForOutputCount(BigDecimal.valueOf(i10), new BigDecimal(String.valueOf((i10 / i11) * f11)), BigDecimal.valueOf(j10));
        double d4 = f10 / f11;
        return (d4 > 1.0000100135803223d || d4 < 0.9999899864196777d) ? BigDecimal.valueOf(frameCountBeforeResamplingForOutputCount).multiply(BigDecimal.valueOf(d4)).setScale(0, RoundingMode.FLOOR).longValueExact() : frameCountBeforeResamplingForOutputCount;
    }

    private static long getFrameCountBeforeResamplingForOutputCount(BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3) {
        RoundingMode roundingMode = RoundingMode.FLOOR;
        return bigDecimal.multiply(bigDecimal3).divide(bigDecimal.divide(bigDecimal2, 0, roundingMode), 0, roundingMode).longValueExact();
    }

    private int insertPitchPeriod(int i10, double d4, int i11) {
        int iRound;
        if (d4 < 0.5d) {
            double d10 = ((((double) i11) * d4) / (1.0d - d4)) + this.accumulatedSpeedAdjustmentError;
            iRound = (int) Math.round(d10);
            this.accumulatedSpeedAdjustmentError = d10 - ((double) iRound);
        } else {
            double d11 = ((((2.0d * d4) - 1.0d) * ((double) i11)) / (1.0d - d4)) + this.accumulatedSpeedAdjustmentError;
            int iRound2 = (int) Math.round(d11);
            this.remainingInputToCopyFrameCount = iRound2;
            this.accumulatedSpeedAdjustmentError = d11 - ((double) iRound2);
            iRound = i11;
        }
        int i12 = i11 + iRound;
        this.impl.ensureAdditionalFramesInOutputBuffer(i12);
        Object inputBuffer = this.impl.getInputBuffer();
        int i13 = this.channelCount * i10;
        Object outputBuffer = this.impl.getOutputBuffer();
        int i14 = this.outputFrameCount;
        int i15 = this.channelCount;
        System.arraycopy(inputBuffer, i13, outputBuffer, i14 * i15, i15 * i11);
        this.impl.overlapAdd(iRound, this.channelCount, this.outputFrameCount + i11, i10 + i11, i10);
        this.outputFrameCount += i12;
        return iRound;
    }

    private void moveNewSamplesToPitchBuffer(int i10) {
        int i11 = this.outputFrameCount - i10;
        this.impl.ensureAdditionalFramesInPitchBuffer(i11);
        Object outputBuffer = this.impl.getOutputBuffer();
        int i12 = this.channelCount * i10;
        Object pitchBuffer = this.impl.getPitchBuffer();
        int i13 = this.pitchFrameCount;
        int i14 = this.channelCount;
        System.arraycopy(outputBuffer, i12, pitchBuffer, i13 * i14, i14 * i11);
        this.outputFrameCount = i10;
        this.pitchFrameCount += i11;
    }

    private void processStreamInput() {
        int i10 = this.outputFrameCount;
        float f10 = this.speed;
        float f11 = this.pitch;
        double d4 = f10 / f11;
        float f12 = this.rate * f11;
        if (d4 > 1.0000100135803223d || d4 < 0.9999899864196777d) {
            changeSpeed(d4);
        } else {
            copyToOutput(0, this.inputFrameCount);
            this.inputFrameCount = 0;
        }
        if (f12 != 1.0f) {
            adjustRate(f12, i10);
        }
    }

    private void removePitchFrames(int i10) {
        if (i10 == 0) {
            return;
        }
        System.arraycopy(this.impl.getPitchBuffer(), this.channelCount * i10, this.impl.getPitchBuffer(), 0, (this.pitchFrameCount - i10) * this.channelCount);
        this.pitchFrameCount -= i10;
    }

    private void removeProcessedInputFrames(int i10) {
        int i11 = this.inputFrameCount - i10;
        System.arraycopy(this.impl.getInputBuffer(), i10 * this.channelCount, this.impl.getInputBuffer(), 0, this.channelCount * i11);
        this.inputFrameCount = i11;
    }

    private int skipPitchPeriod(int i10, double d4, int i11) {
        int i12;
        if (d4 >= 2.0d) {
            double d10 = (((double) i11) / (d4 - 1.0d)) + this.accumulatedSpeedAdjustmentError;
            int iRound = (int) Math.round(d10);
            this.accumulatedSpeedAdjustmentError = d10 - ((double) iRound);
            i12 = iRound;
        } else {
            double d11 = (((2.0d - d4) * ((double) i11)) / (d4 - 1.0d)) + this.accumulatedSpeedAdjustmentError;
            int iRound2 = (int) Math.round(d11);
            this.remainingInputToCopyFrameCount = iRound2;
            this.accumulatedSpeedAdjustmentError = d11 - ((double) iRound2);
            i12 = i11;
        }
        this.impl.ensureAdditionalFramesInOutputBuffer(i12);
        this.impl.overlapAdd(i12, this.channelCount, this.outputFrameCount, i10, i10 + i11);
        this.outputFrameCount += i12;
        return i12;
    }

    public void flush() {
        this.inputFrameCount = 0;
        this.outputFrameCount = 0;
        this.pitchFrameCount = 0;
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.prevPeriod = 0;
        this.accumulatedSpeedAdjustmentError = 0.0d;
        this.impl.flush();
    }

    public void getOutput(ByteBuffer byteBuffer) {
        ac.b.s(this.outputFrameCount >= 0);
        int iMin = Math.min(byteBuffer.remaining() / (this.channelCount * this.impl.bytesPerSample()), this.outputFrameCount);
        this.impl.copyOutputToByteBuffer(byteBuffer, iMin);
        this.outputFrameCount -= iMin;
        System.arraycopy(this.impl.getOutputBuffer(), iMin * this.channelCount, this.impl.getOutputBuffer(), 0, this.outputFrameCount * this.channelCount);
    }

    public int getOutputSize() {
        ac.b.s(this.outputFrameCount >= 0);
        return this.outputFrameCount * this.channelCount * this.impl.bytesPerSample();
    }

    public int getPendingInputBytes() {
        return this.inputFrameCount * this.channelCount * this.impl.bytesPerSample();
    }

    public void queueEndOfStream() {
        int i10 = this.inputFrameCount;
        float f10 = this.speed;
        float f11 = this.pitch;
        double d4 = f10 / f11;
        double d10 = this.rate * f11;
        int i11 = this.remainingInputToCopyFrameCount;
        int i12 = this.outputFrameCount + ((int) ((((((((double) (i10 - i11)) / d4) + ((double) i11)) + this.accumulatedSpeedAdjustmentError) + ((double) this.pitchFrameCount)) / d10) + 0.5d));
        this.accumulatedSpeedAdjustmentError = 0.0d;
        this.impl.ensureAdditionalFramesInInputBuffer((this.maxRequiredFrameCount * 2) + i10);
        this.impl.zeroInputBuffer(i10 * this.channelCount, this.maxRequiredFrameCount * 2);
        this.inputFrameCount = (this.maxRequiredFrameCount * 2) + this.inputFrameCount;
        processStreamInput();
        if (this.outputFrameCount > i12) {
            this.outputFrameCount = Math.max(i12, 0);
        }
        this.inputFrameCount = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.pitchFrameCount = 0;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int iRemaining = byteBuffer.remaining();
        int iBytesPerSample = iRemaining / (this.channelCount * this.impl.bytesPerSample());
        this.impl.ensureAdditionalFramesInInputBuffer(iBytesPerSample);
        this.impl.copyBufferToInputBuffer(byteBuffer, iRemaining);
        this.inputFrameCount += iBytesPerSample;
        processStreamInput();
    }
}
