package androidx.media3.exoplayer;

import com.google.common.collect.w1;
import j$.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class ScrubbingModeParameters {
    public static final ScrubbingModeParameters DEFAULT = new Builder().build();
    public final boolean allowSkippingKeyFrameReset;
    public final boolean allowSkippingMediaCodecFlush;
    public final w1 disabledTrackTypes;
    public final Double fractionalSeekToleranceAfter;
    public final Double fractionalSeekToleranceBefore;

    @Deprecated
    public final boolean isMediaCodecFlushEnabled;
    public final boolean shouldEnableDynamicScheduling;
    public final boolean shouldIncreaseCodecOperatingRate;
    public final boolean useDecodeOnlyFlag;

    public static final class Builder {
        private boolean allowSkippingKeyFrameReset;
        private boolean allowSkippingMediaCodecFlush;
        private w1 disabledTrackTypes;
        private Double fractionalSeekToleranceAfter;
        private Double fractionalSeekToleranceBefore;
        private boolean shouldEnableDynamicScheduling;
        private boolean shouldIncreaseCodecOperatingRate;
        private boolean useDecodeOnlyFlag;

        public ScrubbingModeParameters build() {
            return new ScrubbingModeParameters(this);
        }

        public Builder setAllowSkippingKeyFrameReset(boolean z) {
            this.allowSkippingKeyFrameReset = z;
            return this;
        }

        public Builder setAllowSkippingMediaCodecFlush(boolean z) {
            this.allowSkippingMediaCodecFlush = z;
            return this;
        }

        public Builder setDisabledTrackTypes(Set<Integer> set) {
            this.disabledTrackTypes = w1.m(set);
            return this;
        }

        public Builder setFractionalSeekTolerance(Double d4, Double d10) {
            boolean z = false;
            ac.b.j((d4 == null) == (d10 == null));
            ac.b.j(d4 == null || (d4.doubleValue() >= 0.0d && d4.doubleValue() <= 1.0d));
            if (d10 == null || (d10.doubleValue() >= 0.0d && d10.doubleValue() <= 1.0d)) {
                z = true;
            }
            ac.b.j(z);
            this.fractionalSeekToleranceBefore = d4;
            this.fractionalSeekToleranceAfter = d10;
            return this;
        }

        @Deprecated
        public Builder setIsMediaCodecFlushEnabled(boolean z) {
            this.allowSkippingMediaCodecFlush = !z;
            return this;
        }

        public Builder setShouldEnableDynamicScheduling(boolean z) {
            this.shouldEnableDynamicScheduling = z;
            return this;
        }

        public Builder setShouldIncreaseCodecOperatingRate(boolean z) {
            this.shouldIncreaseCodecOperatingRate = z;
            return this;
        }

        public Builder setUseDecodeOnlyFlag(boolean z) {
            this.useDecodeOnlyFlag = z;
            return this;
        }

        public Builder() {
            this.disabledTrackTypes = w1.k(2, 1, 5);
            this.shouldIncreaseCodecOperatingRate = true;
            this.allowSkippingMediaCodecFlush = true;
            this.allowSkippingKeyFrameReset = true;
            this.shouldEnableDynamicScheduling = true;
            this.useDecodeOnlyFlag = true;
        }

        private Builder(ScrubbingModeParameters scrubbingModeParameters) {
            this.disabledTrackTypes = scrubbingModeParameters.disabledTrackTypes;
            this.fractionalSeekToleranceBefore = scrubbingModeParameters.fractionalSeekToleranceBefore;
            this.fractionalSeekToleranceAfter = scrubbingModeParameters.fractionalSeekToleranceAfter;
            this.shouldIncreaseCodecOperatingRate = scrubbingModeParameters.shouldIncreaseCodecOperatingRate;
            this.allowSkippingMediaCodecFlush = scrubbingModeParameters.allowSkippingMediaCodecFlush;
            this.allowSkippingKeyFrameReset = scrubbingModeParameters.allowSkippingKeyFrameReset;
            this.shouldEnableDynamicScheduling = scrubbingModeParameters.shouldEnableDynamicScheduling;
            this.useDecodeOnlyFlag = scrubbingModeParameters.useDecodeOnlyFlag;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ScrubbingModeParameters)) {
            return false;
        }
        ScrubbingModeParameters scrubbingModeParameters = (ScrubbingModeParameters) obj;
        return this.disabledTrackTypes.equals(scrubbingModeParameters.disabledTrackTypes) && this.allowSkippingMediaCodecFlush == scrubbingModeParameters.allowSkippingMediaCodecFlush && this.allowSkippingKeyFrameReset == scrubbingModeParameters.allowSkippingKeyFrameReset && Objects.equals(this.fractionalSeekToleranceBefore, scrubbingModeParameters.fractionalSeekToleranceBefore) && Objects.equals(this.fractionalSeekToleranceAfter, scrubbingModeParameters.fractionalSeekToleranceAfter) && this.shouldIncreaseCodecOperatingRate == scrubbingModeParameters.shouldIncreaseCodecOperatingRate && this.shouldEnableDynamicScheduling == scrubbingModeParameters.shouldEnableDynamicScheduling && this.useDecodeOnlyFlag == scrubbingModeParameters.useDecodeOnlyFlag;
    }

    public int hashCode() {
        return Objects.hash(this.disabledTrackTypes, this.fractionalSeekToleranceBefore, this.fractionalSeekToleranceAfter, Boolean.valueOf(this.shouldIncreaseCodecOperatingRate), Boolean.valueOf(this.allowSkippingMediaCodecFlush), Boolean.valueOf(this.allowSkippingKeyFrameReset), Boolean.valueOf(this.shouldEnableDynamicScheduling), Boolean.valueOf(this.useDecodeOnlyFlag));
    }

    private ScrubbingModeParameters(Builder builder) {
        this.disabledTrackTypes = builder.disabledTrackTypes;
        this.fractionalSeekToleranceBefore = builder.fractionalSeekToleranceBefore;
        this.fractionalSeekToleranceAfter = builder.fractionalSeekToleranceAfter;
        this.shouldIncreaseCodecOperatingRate = builder.shouldIncreaseCodecOperatingRate;
        this.isMediaCodecFlushEnabled = !builder.allowSkippingMediaCodecFlush;
        this.allowSkippingMediaCodecFlush = builder.allowSkippingMediaCodecFlush;
        this.allowSkippingKeyFrameReset = builder.allowSkippingKeyFrameReset;
        this.shouldEnableDynamicScheduling = builder.shouldEnableDynamicScheduling;
        this.useDecodeOnlyFlag = builder.useDecodeOnlyFlag;
    }
}
