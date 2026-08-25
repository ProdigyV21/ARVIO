package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.extractor.ExtractorUtil;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultAudioTrackBufferSizeProvider implements DefaultAudioSink.AudioTrackBufferSizeProvider {
    private static final int AC3_BUFFER_MULTIPLICATION_FACTOR = 2;
    private static final int DTSHD_BUFFER_MULTIPLICATION_FACTOR = 4;
    private static final int MAX_PCM_BUFFER_DURATION_US = 750000;
    private static final int MIN_PCM_BUFFER_DURATION_US = 250000;
    private static final int OFFLOAD_BUFFER_DURATION_US = 50000000;
    private static final int PASSTHROUGH_BUFFER_DURATION_US = 250000;
    private static final int PCM_BUFFER_MULTIPLICATION_FACTOR = 4;
    public final int ac3BufferMultiplicationFactor;
    public final int dtshdBufferMultiplicationFactor;
    protected final int maxPcmBufferDurationUs;
    protected final int minPcmBufferDurationUs;
    protected final int offloadBufferDurationUs;
    protected final int passthroughBufferDurationUs;
    protected final int pcmBufferMultiplicationFactor;

    public static class Builder {
        private int minPcmBufferDurationUs = 250000;
        private int maxPcmBufferDurationUs = DefaultAudioTrackBufferSizeProvider.MAX_PCM_BUFFER_DURATION_US;
        private int pcmBufferMultiplicationFactor = 4;
        private int passthroughBufferDurationUs = 250000;
        private int offloadBufferDurationUs = DefaultAudioTrackBufferSizeProvider.OFFLOAD_BUFFER_DURATION_US;
        private int ac3BufferMultiplicationFactor = 2;
        private int dtshdBufferMultiplicationFactor = 4;

        public DefaultAudioTrackBufferSizeProvider build() {
            return new DefaultAudioTrackBufferSizeProvider(this);
        }

        public Builder setAc3BufferMultiplicationFactor(int i10) {
            this.ac3BufferMultiplicationFactor = i10;
            return this;
        }

        public Builder setDtshdBufferMultiplicationFactor(int i10) {
            this.dtshdBufferMultiplicationFactor = i10;
            return this;
        }

        public Builder setMaxPcmBufferDurationUs(int i10) {
            this.maxPcmBufferDurationUs = i10;
            return this;
        }

        public Builder setMinPcmBufferDurationUs(int i10) {
            this.minPcmBufferDurationUs = i10;
            return this;
        }

        public Builder setOffloadBufferDurationUs(int i10) {
            this.offloadBufferDurationUs = i10;
            return this;
        }

        public Builder setPassthroughBufferDurationUs(int i10) {
            this.passthroughBufferDurationUs = i10;
            return this;
        }

        public Builder setPcmBufferMultiplicationFactor(int i10) {
            this.pcmBufferMultiplicationFactor = i10;
            return this;
        }
    }

    public DefaultAudioTrackBufferSizeProvider(Builder builder) {
        this.minPcmBufferDurationUs = builder.minPcmBufferDurationUs;
        this.maxPcmBufferDurationUs = builder.maxPcmBufferDurationUs;
        this.pcmBufferMultiplicationFactor = builder.pcmBufferMultiplicationFactor;
        this.passthroughBufferDurationUs = builder.passthroughBufferDurationUs;
        this.offloadBufferDurationUs = builder.offloadBufferDurationUs;
        this.ac3BufferMultiplicationFactor = builder.ac3BufferMultiplicationFactor;
        this.dtshdBufferMultiplicationFactor = builder.dtshdBufferMultiplicationFactor;
    }

    public static int durationUsToBytes(int i10, int i11, int i12) {
        return f0.f(((((long) i10) * ((long) i11)) * ((long) i12)) / 1000000);
    }

    private static int getNonPcmMaximumEncodedRateBytesPerSecond(int i10) {
        int maximumEncodedRateBytesPerSecond = ExtractorUtil.getMaximumEncodedRateBytesPerSecond(i10);
        ac.b.s(maximumEncodedRateBytesPerSecond != -2147483647);
        return maximumEncodedRateBytesPerSecond;
    }

    public int get1xBufferSizeInBytes(int i10, int i11, int i12, int i13, int i14, int i15) {
        if (i12 == 0) {
            return getPcmBufferSizeInBytes(i10, i14, i13);
        }
        if (i12 == 1) {
            return getOffloadBufferSizeInBytes(i11);
        }
        if (i12 == 2) {
            return getPassthroughBufferSizeInBytes(i11, i15);
        }
        throw new IllegalArgumentException();
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioTrackBufferSizeProvider
    public int getBufferSizeInBytes(int i10, int i11, int i12, int i13, int i14, int i15, double d4) {
        return (((Math.max(i10, (int) (((double) get1xBufferSizeInBytes(i10, i11, i12, i13, i14, i15)) * d4)) + i13) - 1) / i13) * i13;
    }

    public int getOffloadBufferSizeInBytes(int i10) {
        return f0.f((((long) this.offloadBufferDurationUs) * ((long) getNonPcmMaximumEncodedRateBytesPerSecond(i10))) / 1000000);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getPassthroughBufferSizeInBytes(int r4, int r5) {
        /*
            r3 = this;
            int r0 = r3.passthroughBufferDurationUs
            r1 = 5
            r2 = 8
            if (r4 != r1) goto Lb
            int r1 = r3.ac3BufferMultiplicationFactor
        L9:
            int r0 = r0 * r1
            goto L10
        Lb:
            if (r4 != r2) goto L10
            int r1 = r3.dtshdBufferMultiplicationFactor
            goto L9
        L10:
            r1 = -1
            if (r5 == r1) goto L1a
            java.math.RoundingMode r4 = java.math.RoundingMode.CEILING
            int r4 = f4.f.i(r5, r2)
            goto L1e
        L1a:
            int r4 = getNonPcmMaximumEncodedRateBytesPerSecond(r4)
        L1e:
            long r0 = (long) r0
            long r4 = (long) r4
            long r0 = r0 * r4
            r4 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r0 / r4
            int r4 = m2.f0.f(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DefaultAudioTrackBufferSizeProvider.getPassthroughBufferSizeInBytes(int, int):int");
    }

    public int getPcmBufferSizeInBytes(int i10, int i11, int i12) {
        return Util.constrainValue(i10 * this.pcmBufferMultiplicationFactor, durationUsToBytes(this.minPcmBufferDurationUs, i11, i12), durationUsToBytes(this.maxPcmBufferDurationUs, i11, i12));
    }
}
