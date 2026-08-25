package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.util.Clock;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioOutputProvider {
    public static final int FORMAT_SUPPORTED_DIRECTLY = 2;
    public static final int FORMAT_SUPPORTED_WITH_TRANSCODING = 1;
    public static final int FORMAT_UNSUPPORTED = 0;

    public static final class ConfigurationException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ConfigurationException(String str) {
            super(str);
            str.getClass();
        }
    }

    public static final class FormatConfig {
        public final AudioAttributes audioAttributes;
        public final int audioSessionId;
        public final boolean enableHighResolutionPcmOutput;
        public final boolean enableOffload;
        public final boolean enablePlaybackParameters;
        public final boolean enableTunneling;
        public final Format format;
        public final int preferredBufferSize;
        public final AudioDeviceInfo preferredDevice;
        public final int virtualDeviceId;

        public static final class Builder {
            private AudioAttributes audioAttributes;
            private int audioSessionId;
            private boolean enableHighResolutionPcmOutput;
            private boolean enableOffload;
            private boolean enablePlaybackParameters;
            private boolean enableTunneling;
            private final Format format;
            private int preferredBufferSize;
            private AudioDeviceInfo preferredDevice;
            private int virtualDeviceId;

            public FormatConfig build() {
                return new FormatConfig(this);
            }

            public Builder setAudioAttributes(AudioAttributes audioAttributes) {
                this.audioAttributes = audioAttributes;
                return this;
            }

            public Builder setAudioSessionId(int i10) {
                this.audioSessionId = i10;
                return this;
            }

            public Builder setEnableHighResolutionPcmOutput(boolean z) {
                this.enableHighResolutionPcmOutput = z;
                return this;
            }

            public Builder setEnableOffload(boolean z) {
                this.enableOffload = z;
                return this;
            }

            public Builder setEnablePlaybackParameters(boolean z) {
                this.enablePlaybackParameters = z;
                return this;
            }

            public Builder setEnableTunneling(boolean z) {
                this.enableTunneling = z;
                return this;
            }

            public Builder setPreferredBufferSize(int i10) {
                this.preferredBufferSize = i10;
                return this;
            }

            public Builder setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
                this.preferredDevice = audioDeviceInfo;
                return this;
            }

            public Builder setVirtualDeviceId(int i10) {
                this.virtualDeviceId = i10;
                return this;
            }

            public Builder(Format format) {
                this.format = format;
                this.audioAttributes = AudioAttributes.DEFAULT;
                this.audioSessionId = 0;
                this.virtualDeviceId = -1;
                this.preferredBufferSize = -1;
            }

            private Builder(FormatConfig formatConfig) {
                this.format = formatConfig.format;
                this.audioAttributes = formatConfig.audioAttributes;
                this.preferredDevice = formatConfig.preferredDevice;
                this.enableHighResolutionPcmOutput = formatConfig.enableHighResolutionPcmOutput;
                this.enablePlaybackParameters = formatConfig.enablePlaybackParameters;
                this.enableOffload = formatConfig.enableOffload;
                this.audioSessionId = formatConfig.audioSessionId;
                this.virtualDeviceId = formatConfig.virtualDeviceId;
                this.enableTunneling = formatConfig.enableTunneling;
                this.preferredBufferSize = formatConfig.preferredBufferSize;
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        private FormatConfig(Builder builder) {
            this.format = builder.format;
            this.audioAttributes = builder.audioAttributes;
            this.preferredDevice = builder.preferredDevice;
            this.enableHighResolutionPcmOutput = builder.enableHighResolutionPcmOutput;
            this.enablePlaybackParameters = builder.enablePlaybackParameters;
            this.enableOffload = builder.enableOffload;
            this.audioSessionId = builder.audioSessionId;
            this.virtualDeviceId = builder.virtualDeviceId;
            this.enableTunneling = builder.enableTunneling;
            this.preferredBufferSize = builder.preferredBufferSize;
        }
    }

    public static final class FormatSupport {
        public static final FormatSupport UNSUPPORTED = new Builder().build();
        public final boolean isFormatSupportedForOffload;
        public final boolean isGaplessSupportedForOffload;
        public final boolean isSpeedChangeSupportedForOffload;
        public final int supportLevel;

        public static final class Builder {
            private boolean isFormatSupportedForOffload;
            private boolean isGaplessSupportedForOffload;
            private boolean isSpeedChangeSupportedForOffload;
            private int supportLevel;

            public FormatSupport build() {
                if (this.isFormatSupportedForOffload || !(this.isGaplessSupportedForOffload || this.isSpeedChangeSupportedForOffload)) {
                    return new FormatSupport(this);
                }
                throw new IllegalStateException("Secondary offload attribute fields are true but primary isFormatSupportedForOffload is false");
            }

            public Builder setFormatSupportLevel(int i10) {
                this.supportLevel = i10;
                return this;
            }

            public Builder setIsFormatSupportedForOffload(boolean z) {
                this.isFormatSupportedForOffload = z;
                return this;
            }

            public Builder setIsGaplessSupportedForOffload(boolean z) {
                this.isGaplessSupportedForOffload = z;
                return this;
            }

            public Builder setIsSpeedChangeSupportedForOffload(boolean z) {
                this.isSpeedChangeSupportedForOffload = z;
                return this;
            }

            public Builder() {
                this.supportLevel = 0;
            }

            private Builder(FormatSupport formatSupport) {
                this.isFormatSupportedForOffload = formatSupport.isFormatSupportedForOffload;
                this.isGaplessSupportedForOffload = formatSupport.isGaplessSupportedForOffload;
                this.isSpeedChangeSupportedForOffload = formatSupport.isSpeedChangeSupportedForOffload;
                this.supportLevel = formatSupport.supportLevel;
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        private FormatSupport(Builder builder) {
            this.isFormatSupportedForOffload = builder.isFormatSupportedForOffload;
            this.isGaplessSupportedForOffload = builder.isGaplessSupportedForOffload;
            this.isSpeedChangeSupportedForOffload = builder.isSpeedChangeSupportedForOffload;
            this.supportLevel = builder.supportLevel;
        }
    }

    public static final class InitializationException extends Exception {
        public InitializationException() {
        }

        public InitializationException(Throwable th) {
            super(th);
        }
    }

    public interface Listener {
        void onFormatSupportChanged();
    }

    public static final class OutputConfig {
        public final AudioAttributes audioAttributes;
        public final int audioSessionId;
        public final int bufferSize;
        public final int channelMask;
        public final int encoding;
        public final boolean isOffload;
        public final boolean isTunneling;
        public final int sampleRate;
        public final boolean useOffloadGapless;
        public final boolean usePlaybackParameters;
        public final int virtualDeviceId;

        public static final class Builder {
            private AudioAttributes audioAttributes;
            private int audioSessionId;
            private int bufferSize;
            private int channelMask;
            private int encoding;
            private boolean isOffload;
            private boolean isTunneling;
            private int sampleRate;
            private boolean useOffloadGapless;
            private boolean usePlaybackParameters;
            private int virtualDeviceId;

            public OutputConfig build() {
                return new OutputConfig(this);
            }

            public Builder setAudioAttributes(AudioAttributes audioAttributes) {
                this.audioAttributes = audioAttributes;
                return this;
            }

            public Builder setAudioSessionId(int i10) {
                this.audioSessionId = i10;
                return this;
            }

            public Builder setBufferSize(int i10) {
                this.bufferSize = i10;
                return this;
            }

            public Builder setChannelMask(int i10) {
                this.channelMask = i10;
                return this;
            }

            public Builder setEncoding(int i10) {
                this.encoding = i10;
                return this;
            }

            public Builder setIsOffload(boolean z) {
                this.isOffload = z;
                return this;
            }

            public Builder setIsTunneling(boolean z) {
                this.isTunneling = z;
                return this;
            }

            public Builder setSampleRate(int i10) {
                this.sampleRate = i10;
                return this;
            }

            public Builder setUseOffloadGapless(boolean z) {
                this.useOffloadGapless = z;
                return this;
            }

            public Builder setUsePlaybackParameters(boolean z) {
                this.usePlaybackParameters = z;
                return this;
            }

            public Builder setVirtualDeviceId(int i10) {
                this.virtualDeviceId = i10;
                return this;
            }

            public Builder() {
                this.audioAttributes = AudioAttributes.DEFAULT;
                this.audioSessionId = 0;
                this.virtualDeviceId = -1;
            }

            private Builder(OutputConfig outputConfig) {
                this.encoding = outputConfig.encoding;
                this.sampleRate = outputConfig.sampleRate;
                this.channelMask = outputConfig.channelMask;
                this.isTunneling = outputConfig.isTunneling;
                this.isOffload = outputConfig.isOffload;
                this.bufferSize = outputConfig.bufferSize;
                this.audioAttributes = outputConfig.audioAttributes;
                this.audioSessionId = outputConfig.audioSessionId;
                this.virtualDeviceId = outputConfig.virtualDeviceId;
                this.usePlaybackParameters = outputConfig.usePlaybackParameters;
                this.useOffloadGapless = outputConfig.useOffloadGapless;
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && OutputConfig.class == obj.getClass()) {
                OutputConfig outputConfig = (OutputConfig) obj;
                if (this.encoding == outputConfig.encoding && this.sampleRate == outputConfig.sampleRate && this.channelMask == outputConfig.channelMask && this.isTunneling == outputConfig.isTunneling && this.isOffload == outputConfig.isOffload && this.bufferSize == outputConfig.bufferSize && this.audioSessionId == outputConfig.audioSessionId && this.virtualDeviceId == outputConfig.virtualDeviceId && this.usePlaybackParameters == outputConfig.usePlaybackParameters && this.useOffloadGapless == outputConfig.useOffloadGapless && this.audioAttributes.equals(outputConfig.audioAttributes)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.encoding), Integer.valueOf(this.sampleRate), Integer.valueOf(this.channelMask), Boolean.valueOf(this.isTunneling), Boolean.valueOf(this.isOffload), Integer.valueOf(this.bufferSize), this.audioAttributes, Integer.valueOf(this.audioSessionId), Integer.valueOf(this.virtualDeviceId), Boolean.valueOf(this.useOffloadGapless), Boolean.valueOf(this.usePlaybackParameters));
        }

        private OutputConfig(Builder builder) {
            this.encoding = builder.encoding;
            this.sampleRate = builder.sampleRate;
            this.channelMask = builder.channelMask;
            this.isTunneling = builder.isTunneling;
            this.isOffload = builder.isOffload;
            this.bufferSize = builder.bufferSize;
            this.audioAttributes = builder.audioAttributes;
            this.audioSessionId = builder.audioSessionId;
            this.virtualDeviceId = builder.virtualDeviceId;
            this.usePlaybackParameters = builder.usePlaybackParameters;
            this.useOffloadGapless = builder.useOffloadGapless;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SupportLevel {
    }

    void addListener(Listener listener);

    AudioOutput getAudioOutput(OutputConfig outputConfig) throws InitializationException;

    FormatSupport getFormatSupport(FormatConfig formatConfig);

    OutputConfig getOutputConfig(FormatConfig formatConfig) throws ConfigurationException;

    void release();

    void removeListener(Listener listener);

    void setClock(Clock clock);
}
