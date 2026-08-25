package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import androidx.fragment.app.a2;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.analytics.PlayerId;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioSink {
    public static final long CURRENT_POSITION_NOT_SET = Long.MIN_VALUE;
    public static final int OFFLOAD_MODE_DISABLED = 0;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_NOT_REQUIRED = 2;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_REQUIRED = 1;
    public static final int SINK_FORMAT_SUPPORTED_DIRECTLY = 2;
    public static final int SINK_FORMAT_SUPPORTED_WITH_TRANSCODING = 1;
    public static final int SINK_FORMAT_UNSUPPORTED = 0;

    public static final class AudioTrackConfig {
        public final int bufferSize;
        public final int channelConfig;
        public final int encoding;
        public final boolean offload;
        public final int sampleRate;
        public final boolean tunneling;

        public AudioTrackConfig(int i10, int i11, int i12, boolean z, boolean z5, int i13) {
            this.encoding = i10;
            this.sampleRate = i11;
            this.channelConfig = i12;
            this.tunneling = z;
            this.offload = z5;
            this.bufferSize = i13;
        }
    }

    public interface Listener {
        void onAudioCapabilitiesChanged();

        void onAudioSessionIdChanged(int i10);

        void onAudioSinkError(Exception exc);

        void onAudioTrackInitialized(AudioTrackConfig audioTrackConfig);

        void onAudioTrackReleased(AudioTrackConfig audioTrackConfig);

        void onOffloadBufferEmptying();

        void onOffloadBufferFull();

        void onPositionAdvancing(long j10);

        void onPositionDiscontinuity();

        void onSilenceSkipped();

        void onSkipSilenceEnabledChanged(boolean z);

        void onUnderrun(int i10, long j10, long j11);
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface OffloadMode {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SinkFormatSupport {
    }

    public static final class UnexpectedDiscontinuityException extends Exception {
        public final long actualPresentationTimeUs;
        public final long expectedPresentationTimeUs;

        /* JADX WARN: Illegal instructions before constructor call */
        public UnexpectedDiscontinuityException(long j10, long j11) {
            StringBuilder sbR = androidx.compose.material3.d.r(j11, "Unexpected audio track timestamp discontinuity: expected ", ", got ");
            sbR.append(j10);
            super(sbR.toString());
            this.actualPresentationTimeUs = j10;
            this.expectedPresentationTimeUs = j11;
        }
    }

    public static final class WriteException extends Exception {
        public final int errorCode;
        public final Format format;
        public final boolean isRecoverable;

        public WriteException(int i10, Format format, boolean z) {
            super(a0.c.i(i10, "AudioTrack write failed: "));
            this.isRecoverable = z;
            this.errorCode = i10;
            this.format = format;
        }
    }

    void configure(Format format, int i10, int[] iArr) throws ConfigurationException;

    void disableTunneling();

    void enableTunnelingV21();

    void flush();

    AudioAttributes getAudioAttributes();

    long getAudioTrackBufferSizeUs();

    long getCurrentPositionUs(boolean z);

    AudioOffloadSupport getFormatOffloadSupport(Format format);

    int getFormatSupport(Format format);

    PlaybackParameters getPlaybackParameters();

    boolean getSkipSilenceEnabled();

    boolean handleBuffer(ByteBuffer byteBuffer, long j10, int i10) throws WriteException, InitializationException;

    void handleDiscontinuity();

    boolean hasPendingData();

    boolean isEnded();

    void pause();

    void play();

    void playToEndOfStream() throws WriteException;

    void release();

    void reset();

    void setAudioAttributes(AudioAttributes audioAttributes);

    void setAudioOutputProvider(AudioOutputProvider audioOutputProvider);

    void setAudioSessionId(int i10);

    void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

    void setClock(Clock clock);

    void setListener(Listener listener);

    void setOffloadDelayPadding(int i10, int i11);

    void setOffloadMode(int i10);

    void setOutputStreamOffsetUs(long j10);

    void setPlaybackParameters(PlaybackParameters playbackParameters);

    void setPlayerId(PlayerId playerId);

    void setPreferredDevice(AudioDeviceInfo audioDeviceInfo);

    void setSkipSilenceEnabled(boolean z);

    void setVirtualDeviceId(int i10);

    void setVolume(float f10);

    boolean supportsFormat(Format format);

    public static final class ConfigurationException extends Exception {
        public final Format format;

        public ConfigurationException(Throwable th, Format format) {
            super(th);
            this.format = format;
        }

        public ConfigurationException(String str, Format format) {
            super(str);
            this.format = format;
        }
    }

    public static final class InitializationException extends Exception {
        public final int audioTrackState;
        public final Format format;
        public final boolean isRecoverable;

        /* JADX WARN: Illegal instructions before constructor call */
        public InitializationException(int i10, int i11, int i12, int i13, int i14, Format format, boolean z, Exception exc) {
            StringBuilder sbV = androidx.compose.foundation.c.v("AudioTrack init failed ", i10, " Config(", i11, ", ");
            a2.y(sbV, i12, ", ", i13, ", ");
            sbV.append(i14);
            sbV.append(") ");
            sbV.append(format);
            sbV.append(z ? " (recoverable)" : "");
            this(sbV.toString(), i10, format, z, exc);
        }

        public InitializationException(String str, int i10, Format format, boolean z, Throwable th) {
            super(str, th);
            this.audioTrackState = i10;
            this.isRecoverable = z;
            this.format = format;
        }
    }
}
