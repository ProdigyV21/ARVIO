package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.audio.AudioProcessingPipeline;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.SonicAudioProcessor;
import androidx.media3.common.audio.ToInt16PcmAudioProcessor;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioOffloadSupport;
import androidx.media3.exoplayer.audio.AudioOutput;
import androidx.media3.exoplayer.audio.AudioOutputProvider;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.AudioTrackAudioOutputProvider;
import androidx.media3.exoplayer.audio.DefaultAudioTrackBufferSizeProvider;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.OpusUtil;
import com.google.common.collect.a0;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultAudioSink implements AudioSink {
    private static final int AUDIO_OUTPUT_SMALLER_BUFFER_RETRY_SIZE = 1000000;
    private static final int AUDIO_OUTPUT_VOLUME_RAMP_TIME_MS = 20;
    public static final float DEFAULT_PLAYBACK_SPEED = 1.0f;
    private static final boolean DEFAULT_SKIP_SILENCE = false;
    public static final float MAX_PITCH = 8.0f;
    public static final float MAX_PLAYBACK_SPEED = 8.0f;
    private static final int MINIMUM_REPORT_SKIPPED_SILENCE_DURATION_US = 300000;
    public static final float MIN_PITCH = 0.1f;
    public static final float MIN_PLAYBACK_SPEED = 0.1f;
    public static final int OUTPUT_MODE_OFFLOAD = 1;
    public static final int OUTPUT_MODE_PASSTHROUGH = 2;
    public static final int OUTPUT_MODE_PCM = 0;
    private static final int REPORT_SKIPPED_SILENCE_DELAY_MS = 100;
    private static final String TAG = "DefaultAudioSink";
    private static final AtomicInteger pendingReleaseCount = new AtomicInteger();
    private long accumulatedSkippedSilenceDurationUs;
    private MediaPositionParameters afterDrainParameters;
    private AudioAttributes audioAttributes;
    private final ExoPlayer.AudioOffloadListener audioOffloadListener;
    private AudioOutput audioOutput;
    private AudioOutputListener audioOutputListener;
    private AudioOutputProvider audioOutputProvider;
    private AudioOutputProvider.Listener audioOutputProviderListener;
    private AudioProcessingPipeline audioProcessingPipeline;
    private final androidx.media3.common.audio.AudioProcessorChain audioProcessorChain;
    private int audioSessionId;
    private AuxEffectInfo auxEffectInfo;
    private final h1 availableAudioProcessors;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private Configuration configuration;
    private final Context context;
    private final boolean enableFloatOutput;
    private boolean externalAudioSessionIdProvided;
    private int framesPerEncodedSample;
    private boolean handledEndOfStream;
    private boolean handledOffloadOnPresentationEnded;
    private final PendingExceptionHolder<AudioSink.InitializationException> initializationExceptionPendingExceptionHolder;
    private ByteBuffer inputBuffer;
    private int inputBufferAccessUnitCount;
    private boolean isWaitingForOffloadEndOfStreamHandled;
    private long lastFeedElapsedRealtimeMs;
    private AudioSink.Listener listener;
    private MediaPositionParameters mediaPositionParameters;
    private final ArrayDeque<MediaPositionParameters> mediaPositionParametersCheckpoints;
    private boolean offloadDisabledUntilNextConfiguration;
    private int offloadMode;
    private ByteBuffer outputBuffer;
    private boolean pendingAudioSessionIdChangeConfirmation;
    private Configuration pendingConfiguration;
    private PlaybackParameters playbackParameters;
    private PlayerId playerId;
    private boolean playing;
    private final boolean preferAudioOutputPlaybackParameters;
    private AudioDeviceInfo preferredDevice;
    private Handler reportSkippedSilenceHandler;
    private boolean skipSilenceEnabled;
    private long skippedOutputFrameCountAtLastPosition;
    private long startMediaTimeUs;
    private boolean startMediaTimeUsNeedsInit;
    private boolean startMediaTimeUsNeedsSync;
    private boolean stoppedAudioOutput;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private final ToFloatPcmAudioProcessor toFloatPcmAudioProcessor;
    private final ToInt16PcmAudioProcessor toInt16PcmAudioProcessor;
    private final TrimmingAudioProcessor trimmingAudioProcessor;
    private boolean tunneling;
    private int virtualDeviceId;
    private float volume;
    private final PendingExceptionHolder<AudioSink.WriteException> writeExceptionPendingExceptionHolder;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    public interface AudioOffloadSupportProvider {
        AudioOffloadSupport getAudioOffloadSupport(Format format, AudioAttributes audioAttributes);
    }

    public final class AudioOutputListener implements AudioOutput.Listener {
        private final AudioOutputProvider.OutputConfig outputConfig;

        @Override // androidx.media3.exoplayer.audio.AudioOutput.Listener
        public void onOffloadDataRequest() {
            if (equals(DefaultAudioSink.this.audioOutputListener) && DefaultAudioSink.this.listener != null && DefaultAudioSink.this.playing) {
                DefaultAudioSink.this.listener.onOffloadBufferEmptying();
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioOutput.Listener
        public void onOffloadPresentationEnded() {
            if (equals(DefaultAudioSink.this.audioOutputListener)) {
                DefaultAudioSink.this.handledOffloadOnPresentationEnded = true;
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioOutput.Listener
        public void onPositionAdvancing(long j10) {
            if (equals(DefaultAudioSink.this.audioOutputListener) && DefaultAudioSink.this.listener != null) {
                DefaultAudioSink.this.listener.onPositionAdvancing(j10);
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioOutput.Listener
        public void onReleased() {
            DefaultAudioSink.pendingReleaseCount.getAndDecrement();
            if (DefaultAudioSink.this.listener != null) {
                AudioSink.Listener listener = DefaultAudioSink.this.listener;
                AudioOutputProvider.OutputConfig outputConfig = this.outputConfig;
                listener.onAudioTrackReleased(new AudioSink.AudioTrackConfig(outputConfig.encoding, outputConfig.sampleRate, outputConfig.channelMask, outputConfig.isTunneling, outputConfig.isOffload, outputConfig.bufferSize));
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioOutput.Listener
        public void onUnderrun() {
            long jSampleCountToDurationUs;
            if (equals(DefaultAudioSink.this.audioOutputListener) && DefaultAudioSink.this.listener != null) {
                if (DefaultAudioSink.this.configuration.outputPcmFrameSize != -1) {
                    long j10 = DefaultAudioSink.this.configuration.outputConfig.bufferSize / DefaultAudioSink.this.configuration.outputPcmFrameSize;
                    AudioOutput audioOutput = DefaultAudioSink.this.audioOutput;
                    audioOutput.getClass();
                    jSampleCountToDurationUs = Util.sampleCountToDurationUs(j10, audioOutput.getSampleRate());
                } else {
                    jSampleCountToDurationUs = C.TIME_UNSET;
                }
                DefaultAudioSink.this.listener.onUnderrun(DefaultAudioSink.this.configuration.outputConfig.bufferSize, Util.usToMs(jSampleCountToDurationUs), SystemClock.elapsedRealtime() - DefaultAudioSink.this.lastFeedElapsedRealtimeMs);
            }
        }

        private AudioOutputListener(AudioOutputProvider.OutputConfig outputConfig) {
            this.outputConfig = outputConfig;
        }
    }

    @Deprecated
    public interface AudioProcessorChain extends androidx.media3.common.audio.AudioProcessorChain {
    }

    public interface AudioTrackBufferSizeProvider {
        public static final AudioTrackBufferSizeProvider DEFAULT = new DefaultAudioTrackBufferSizeProvider.Builder().build();

        int getBufferSizeInBytes(int i10, int i11, int i12, int i13, int i14, int i15, double d4);
    }

    @Deprecated
    public interface AudioTrackProvider {
        public static final AudioTrackProvider DEFAULT = new DefaultAudioTrackProvider();

        AudioTrack getAudioTrack(AudioSink.AudioTrackConfig audioTrackConfig, AudioAttributes audioAttributes, int i10, Context context);

        int getAudioTrackChannelConfig(int i10);
    }

    public static final class Configuration {
        private final Format afterProcessingInputFormat;
        private final AudioProcessingPipeline audioProcessingPipeline;
        private final Format inputFormat;
        private final int inputPcmFrameSize;
        private final AudioOutputProvider.OutputConfig outputConfig;
        private final int outputPcmFrameSize;

        /* JADX INFO: Access modifiers changed from: private */
        public AudioSink.AudioTrackConfig buildAudioTrackConfig() {
            AudioOutputProvider.OutputConfig outputConfig = this.outputConfig;
            return new AudioSink.AudioTrackConfig(outputConfig.encoding, outputConfig.sampleRate, outputConfig.channelMask, outputConfig.isTunneling, outputConfig.isOffload, outputConfig.bufferSize);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean canReuseAudioOutput(Configuration configuration) {
            return configuration.outputConfig.equals(this.outputConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Configuration copyWithOutputConfig(AudioOutputProvider.OutputConfig outputConfig) {
            return new Configuration(this.inputFormat, this.afterProcessingInputFormat, this.inputPcmFrameSize, this.outputPcmFrameSize, outputConfig, this.audioProcessingPipeline);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long framesToDurationUs(long j10) {
            return Util.sampleCountToDurationUs(j10, this.outputConfig.sampleRate);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long inputFramesToDurationUs(long j10) {
            return Util.sampleCountToDurationUs(j10, this.inputFormat.sampleRate);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isPcm() {
            return Objects.equals(this.inputFormat.sampleMimeType, MimeTypes.AUDIO_RAW);
        }

        private Configuration(Format format, Format format2, int i10, int i11, AudioOutputProvider.OutputConfig outputConfig, AudioProcessingPipeline audioProcessingPipeline) {
            this.inputFormat = format;
            this.afterProcessingInputFormat = format2;
            this.inputPcmFrameSize = i10;
            this.outputPcmFrameSize = i11;
            this.outputConfig = outputConfig;
            this.audioProcessingPipeline = audioProcessingPipeline;
        }
    }

    public static class DefaultAudioProcessorChain implements AudioProcessorChain {
        private final AudioProcessor[] audioProcessors;
        private final SilenceSkippingAudioProcessor silenceSkippingAudioProcessor;
        private final SonicAudioProcessor sonicAudioProcessor;

        public DefaultAudioProcessorChain(AudioProcessor... audioProcessorArr) {
            this(audioProcessorArr, new SilenceSkippingAudioProcessor(), new SonicAudioProcessor());
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public PlaybackParameters applyPlaybackParameters(PlaybackParameters playbackParameters) {
            this.sonicAudioProcessor.setSpeed(playbackParameters.speed);
            this.sonicAudioProcessor.setPitch(playbackParameters.pitch);
            return playbackParameters;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public boolean applySkipSilenceEnabled(boolean z) {
            this.silenceSkippingAudioProcessor.setEnabled(z);
            return z;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public AudioProcessor[] getAudioProcessors() {
            return this.audioProcessors;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public long getMediaDuration(long j10) {
            return this.sonicAudioProcessor.isActive() ? this.sonicAudioProcessor.getMediaDuration(j10) : j10;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public long getSkippedOutputFrameCount() {
            return this.silenceSkippingAudioProcessor.getSkippedFrames();
        }

        public DefaultAudioProcessorChain(AudioProcessor[] audioProcessorArr, SilenceSkippingAudioProcessor silenceSkippingAudioProcessor, SonicAudioProcessor sonicAudioProcessor) {
            AudioProcessor[] audioProcessorArr2 = new AudioProcessor[audioProcessorArr.length + 2];
            this.audioProcessors = audioProcessorArr2;
            System.arraycopy(audioProcessorArr, 0, audioProcessorArr2, 0, audioProcessorArr.length);
            this.silenceSkippingAudioProcessor = silenceSkippingAudioProcessor;
            this.sonicAudioProcessor = sonicAudioProcessor;
            audioProcessorArr2[audioProcessorArr.length] = silenceSkippingAudioProcessor;
            audioProcessorArr2[audioProcessorArr.length + 1] = sonicAudioProcessor;
        }
    }

    public static final class MediaPositionParameters {
        public final long audioOutputPositionUs;
        public long mediaPositionDriftUs;
        public final long mediaTimeUs;
        public final PlaybackParameters playbackParameters;

        private MediaPositionParameters(PlaybackParameters playbackParameters, long j10, long j11) {
            this.playbackParameters = playbackParameters;
            this.mediaTimeUs = j10;
            this.audioOutputPositionUs = j11;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface OutputMode {
    }

    public static final class PendingExceptionHolder<T extends Exception> {
        private static final int RETRY_DELAY_MS = 50;
        private static final int RETRY_DURATION_MS = 200;
        private T pendingException;
        private long throwDeadlineMs = C.TIME_UNSET;
        private long earliestNextRetryTimeMs = C.TIME_UNSET;

        public void clear() {
            this.pendingException = null;
            this.throwDeadlineMs = C.TIME_UNSET;
            this.earliestNextRetryTimeMs = C.TIME_UNSET;
        }

        public boolean shouldWaitBeforeRetry() {
            if (this.pendingException == null) {
                return false;
            }
            return DefaultAudioSink.hasPendingAudioOutputReleases() || SystemClock.elapsedRealtime() < this.earliestNextRetryTimeMs;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: T extends java.lang.Exception */
        public void throwExceptionIfDeadlineIsReached(T t2) throws Exception {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (this.pendingException == null) {
                this.pendingException = t2;
            }
            if (this.throwDeadlineMs == C.TIME_UNSET && !DefaultAudioSink.hasPendingAudioOutputReleases()) {
                this.throwDeadlineMs = 200 + jElapsedRealtime;
            }
            long j10 = this.throwDeadlineMs;
            if (j10 == C.TIME_UNSET || jElapsedRealtime < j10) {
                this.earliestNextRetryTimeMs = jElapsedRealtime + 50;
                return;
            }
            T t10 = this.pendingException;
            if (t10 != t2) {
                t10.addSuppressed(t2);
            }
            T t11 = this.pendingException;
            clear();
            throw t11;
        }
    }

    private void applyAudioProcessorPlaybackParametersAndSkipSilence(long j10) {
        PlaybackParameters playbackParametersApplyPlaybackParameters;
        if (useAudioOutputPlaybackParams()) {
            playbackParametersApplyPlaybackParameters = PlaybackParameters.DEFAULT;
        } else {
            playbackParametersApplyPlaybackParameters = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applyPlaybackParameters(this.playbackParameters) : PlaybackParameters.DEFAULT;
            this.playbackParameters = playbackParametersApplyPlaybackParameters;
        }
        PlaybackParameters playbackParameters = playbackParametersApplyPlaybackParameters;
        this.skipSilenceEnabled = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applySkipSilenceEnabled(this.skipSilenceEnabled) : false;
        this.mediaPositionParametersCheckpoints.add(new MediaPositionParameters(playbackParameters, Math.max(0L, j10), this.configuration.framesToDurationUs(getWrittenFrames())));
        setupAudioProcessors();
        AudioSink.Listener listener = this.listener;
        if (listener != null) {
            listener.onSkipSilenceEnabledChanged(this.skipSilenceEnabled);
        }
    }

    private long applyMediaPositionParameters(long j10) {
        while (!this.mediaPositionParametersCheckpoints.isEmpty() && j10 >= this.mediaPositionParametersCheckpoints.getFirst().audioOutputPositionUs) {
            this.mediaPositionParameters = this.mediaPositionParametersCheckpoints.remove();
        }
        MediaPositionParameters mediaPositionParameters = this.mediaPositionParameters;
        long j11 = j10 - mediaPositionParameters.audioOutputPositionUs;
        long mediaDurationForPlayoutDuration = Util.getMediaDurationForPlayoutDuration(j11, mediaPositionParameters.playbackParameters.speed);
        if (!this.mediaPositionParametersCheckpoints.isEmpty()) {
            MediaPositionParameters mediaPositionParameters2 = this.mediaPositionParameters;
            return mediaPositionParameters2.mediaTimeUs + mediaDurationForPlayoutDuration + mediaPositionParameters2.mediaPositionDriftUs;
        }
        long mediaDuration = this.audioProcessorChain.getMediaDuration(j11);
        MediaPositionParameters mediaPositionParameters3 = this.mediaPositionParameters;
        long j12 = mediaPositionParameters3.mediaTimeUs + mediaDuration;
        mediaPositionParameters3.mediaPositionDriftUs = mediaDuration - mediaDurationForPlayoutDuration;
        return j12;
    }

    private long applySkipping(long j10) {
        long skippedOutputFrameCount = this.audioProcessorChain.getSkippedOutputFrameCount();
        long jFramesToDurationUs = j10 + this.configuration.framesToDurationUs(skippedOutputFrameCount);
        long j11 = this.skippedOutputFrameCountAtLastPosition;
        if (skippedOutputFrameCount > j11) {
            long jFramesToDurationUs2 = this.configuration.framesToDurationUs(skippedOutputFrameCount - j11);
            this.skippedOutputFrameCountAtLastPosition = skippedOutputFrameCount;
            handleSkippedSilence(jFramesToDurationUs2);
        }
        return jFramesToDurationUs;
    }

    private AudioOutput buildAudioOutput(AudioOutputProvider.OutputConfig outputConfig) throws AudioSink.InitializationException {
        try {
            return this.audioOutputProvider.getAudioOutput(outputConfig);
        } catch (AudioOutputProvider.InitializationException e5) {
            AudioSink.InitializationException initializationException = new AudioSink.InitializationException(0, outputConfig.sampleRate, outputConfig.channelMask, outputConfig.encoding, outputConfig.bufferSize, this.configuration.inputFormat, outputConfig.isOffload, e5);
            AudioSink.Listener listener = this.listener;
            if (listener == null) {
                throw initializationException;
            }
            listener.onAudioSinkError(initializationException);
            throw initializationException;
        }
    }

    private AudioOutput buildAudioOutputWithRetry() throws AudioSink.InitializationException {
        try {
            return buildAudioOutput(this.configuration.outputConfig);
        } catch (AudioSink.InitializationException e5) {
            if (this.configuration.outputConfig.bufferSize > 1000000) {
                AudioOutputProvider.OutputConfig outputConfigBuild = this.configuration.outputConfig.buildUpon().setBufferSize(1000000).build();
                try {
                    AudioOutput audioOutputBuildAudioOutput = buildAudioOutput(outputConfigBuild);
                    this.configuration = this.configuration.copyWithOutputConfig(outputConfigBuild);
                    return audioOutputBuildAudioOutput;
                } catch (AudioSink.InitializationException e6) {
                    e5.addSuppressed(e6);
                    maybeDisableOffload();
                    throw e5;
                }
            }
            maybeDisableOffload();
            throw e5;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void drainOutputBuffer(long r9) throws java.lang.Exception {
        /*
            r8 = this;
            java.nio.ByteBuffer r0 = r8.outputBuffer
            if (r0 != 0) goto L6
            goto L89
        L6:
            androidx.media3.exoplayer.audio.DefaultAudioSink$PendingExceptionHolder<androidx.media3.exoplayer.audio.AudioSink$WriteException> r0 = r8.writeExceptionPendingExceptionHolder
            boolean r0 = r0.shouldWaitBeforeRetry()
            if (r0 == 0) goto L10
            goto L89
        L10:
            java.nio.ByteBuffer r0 = r8.outputBuffer
            int r0 = r0.remaining()
            r1 = 0
            r3 = 1
            r4 = 0
            androidx.media3.exoplayer.audio.AudioOutput r5 = r8.audioOutput     // Catch: androidx.media3.exoplayer.audio.AudioOutput.WriteException -> L8a
            java.nio.ByteBuffer r6 = r8.outputBuffer     // Catch: androidx.media3.exoplayer.audio.AudioOutput.WriteException -> L8a
            int r7 = r8.inputBufferAccessUnitCount     // Catch: androidx.media3.exoplayer.audio.AudioOutput.WriteException -> L8a
            boolean r9 = r5.write(r6, r7, r9)     // Catch: androidx.media3.exoplayer.audio.AudioOutput.WriteException -> L8a
            long r5 = android.os.SystemClock.elapsedRealtime()
            r8.lastFeedElapsedRealtimeMs = r5
            androidx.media3.exoplayer.audio.DefaultAudioSink$PendingExceptionHolder<androidx.media3.exoplayer.audio.AudioSink$WriteException> r10 = r8.writeExceptionPendingExceptionHolder
            r10.clear()
            androidx.media3.exoplayer.audio.AudioOutput r10 = r8.audioOutput
            boolean r10 = r10.isOffloadedPlayback()
            if (r10 == 0) goto L50
            long r5 = r8.writtenEncodedFrames
            int r10 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r10 <= 0) goto L3f
            r8.isWaitingForOffloadEndOfStreamHandled = r4
        L3f:
            boolean r10 = r8.playing
            if (r10 == 0) goto L50
            androidx.media3.exoplayer.audio.AudioSink$Listener r10 = r8.listener
            if (r10 == 0) goto L50
            if (r9 != 0) goto L50
            boolean r1 = r8.isWaitingForOffloadEndOfStreamHandled
            if (r1 != 0) goto L50
            r10.onOffloadBufferFull()
        L50:
            androidx.media3.exoplayer.audio.DefaultAudioSink$Configuration r10 = r8.configuration
            boolean r10 = androidx.media3.exoplayer.audio.DefaultAudioSink.Configuration.access$1700(r10)
            if (r10 == 0) goto L65
            long r1 = r8.writtenPcmBytes
            java.nio.ByteBuffer r10 = r8.outputBuffer
            int r10 = r10.remaining()
            int r0 = r0 - r10
            long r5 = (long) r0
            long r1 = r1 + r5
            r8.writtenPcmBytes = r1
        L65:
            if (r9 == 0) goto L89
            androidx.media3.exoplayer.audio.DefaultAudioSink$Configuration r9 = r8.configuration
            boolean r9 = androidx.media3.exoplayer.audio.DefaultAudioSink.Configuration.access$1700(r9)
            if (r9 != 0) goto L86
            java.nio.ByteBuffer r9 = r8.outputBuffer
            java.nio.ByteBuffer r10 = r8.inputBuffer
            if (r9 != r10) goto L76
            goto L77
        L76:
            r3 = r4
        L77:
            ac.b.s(r3)
            long r9 = r8.writtenEncodedFrames
            int r0 = r8.framesPerEncodedSample
            long r0 = (long) r0
            int r2 = r8.inputBufferAccessUnitCount
            long r2 = (long) r2
            long r0 = r0 * r2
            long r0 = r0 + r9
            r8.writtenEncodedFrames = r0
        L86:
            r9 = 0
            r8.outputBuffer = r9
        L89:
            return
        L8a:
            r9 = move-exception
            boolean r10 = r9.isRecoverable
            if (r10 == 0) goto La4
            long r5 = r8.getWrittenFrames()
            int r10 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r10 <= 0) goto L98
            goto La5
        L98:
            androidx.media3.exoplayer.audio.AudioOutput r10 = r8.audioOutput
            boolean r10 = r10.isOffloadedPlayback()
            if (r10 == 0) goto La4
            r8.maybeDisableOffload()
            goto La5
        La4:
            r3 = r4
        La5:
            androidx.media3.exoplayer.audio.AudioSink$WriteException r10 = new androidx.media3.exoplayer.audio.AudioSink$WriteException
            int r0 = r9.errorCode
            androidx.media3.exoplayer.audio.DefaultAudioSink$Configuration r1 = r8.configuration
            androidx.media3.common.Format r1 = androidx.media3.exoplayer.audio.DefaultAudioSink.Configuration.access$1300(r1)
            r10.<init>(r0, r1, r3)
            androidx.media3.exoplayer.audio.AudioSink$Listener r0 = r8.listener
            if (r0 == 0) goto Lb9
            r0.onAudioSinkError(r10)
        Lb9:
            boolean r9 = r9.isRecoverable
            if (r9 != 0) goto Lc3
            androidx.media3.exoplayer.audio.DefaultAudioSink$PendingExceptionHolder<androidx.media3.exoplayer.audio.AudioSink$WriteException> r9 = r8.writeExceptionPendingExceptionHolder
            r9.throwExceptionIfDeadlineIsReached(r10)
            return
        Lc3:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DefaultAudioSink.drainOutputBuffer(long):void");
    }

    private boolean drainToEndOfStream() throws Exception {
        ByteBuffer byteBuffer;
        if (!this.audioProcessingPipeline.isOperational()) {
            drainOutputBuffer(Long.MIN_VALUE);
            return this.outputBuffer == null;
        }
        this.audioProcessingPipeline.queueEndOfStream();
        processBuffers(Long.MIN_VALUE);
        return this.audioProcessingPipeline.isEnded() && ((byteBuffer = this.outputBuffer) == null || !byteBuffer.hasRemaining());
    }

    private static int getDeviceIdFromContext(Context context) {
        return resolveDefaultVirtualDeviceIds(context.getDeviceId());
    }

    private AudioOutputProvider.FormatConfig getFormatConfig(Format format) {
        return getFormatConfig(format, -1);
    }

    public static int getFramesPerEncodedSample(int i10, ByteBuffer byteBuffer) {
        if (i10 == 20) {
            return OpusUtil.parseOggPacketAudioSampleCount(byteBuffer);
        }
        if (i10 != 30) {
            switch (i10) {
                case 5:
                case 6:
                    break;
                case 7:
                case 8:
                    break;
                case 9:
                    int mpegAudioFrameSampleCount = MpegAudioUtil.parseMpegAudioFrameSampleCount(Util.getBigEndianInt(byteBuffer, byteBuffer.position()));
                    if (mpegAudioFrameSampleCount != -1) {
                        return mpegAudioFrameSampleCount;
                    }
                    throw new IllegalArgumentException();
                case 10:
                    return 1024;
                case 11:
                case 12:
                    return 2048;
                default:
                    switch (i10) {
                        case 14:
                            int iFindTrueHdSyncframeOffset = Ac3Util.findTrueHdSyncframeOffset(byteBuffer);
                            if (iFindTrueHdSyncframeOffset == -1) {
                                return 0;
                            }
                            return Ac3Util.parseTrueHdSyncframeAudioSampleCount(byteBuffer, iFindTrueHdSyncframeOffset) * 16;
                        case 15:
                            return 512;
                        case 16:
                            return 1024;
                        case 17:
                            return Ac4Util.parseAc4SyncframeAudioSampleCount(byteBuffer);
                        case 18:
                            break;
                        default:
                            throw new IllegalStateException(a0.c.i(i10, "Unexpected audio encoding: "));
                    }
                    break;
            }
            return Ac3Util.parseAc3SyncframeAudioSampleCount(byteBuffer);
        }
        return DtsUtil.parseDtsAudioSampleCount(byteBuffer);
    }

    private static int getNonPcmMaximumEncodedRateBytesPerSecond(int i10) {
        int maximumEncodedRateBytesPerSecond = ExtractorUtil.getMaximumEncodedRateBytesPerSecond(i10);
        ac.b.s(maximumEncodedRateBytesPerSecond != -2147483647);
        return maximumEncodedRateBytesPerSecond;
    }

    private long getSubmittedFrames() {
        return this.configuration.isPcm() ? this.submittedPcmBytes / ((long) this.configuration.inputPcmFrameSize) : this.submittedEncodedFrames;
    }

    private long getWrittenFrames() {
        return this.configuration.isPcm() ? Util.ceilDivide(this.writtenPcmBytes, this.configuration.outputPcmFrameSize) : this.writtenEncodedFrames;
    }

    private void handleSkippedSilence(long j10) {
        this.accumulatedSkippedSilenceDurationUs += j10;
        if (this.reportSkippedSilenceHandler == null) {
            this.reportSkippedSilenceHandler = new Handler(Looper.myLooper());
        }
        this.reportSkippedSilenceHandler.removeCallbacksAndMessages(null);
        this.reportSkippedSilenceHandler.postDelayed(new n(this, 1), 100L);
    }

    private boolean hasAudioOutputPendingData(long j10) {
        long positionUs = this.audioOutput.getPositionUs();
        AudioOutput audioOutput = this.audioOutput;
        audioOutput.getClass();
        return j10 > Util.durationUsToSampleCount(positionUs, audioOutput.getSampleRate());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean hasPendingAudioOutputReleases() {
        return pendingReleaseCount.get() > 0;
    }

    private boolean initializeAudioOutput() throws AudioSink.InitializationException {
        if (this.initializationExceptionPendingExceptionHolder.shouldWaitBeforeRetry()) {
            return false;
        }
        this.audioOutput = buildAudioOutputWithRetry();
        AudioOutputListener audioOutputListener = new AudioOutputListener(this.configuration.outputConfig);
        this.audioOutputListener = audioOutputListener;
        this.audioOutput.addListener(audioOutputListener);
        ExoPlayer.AudioOffloadListener audioOffloadListener = this.audioOffloadListener;
        if (audioOffloadListener != null) {
            audioOffloadListener.onOffloadedPlayback(this.audioOutput.isOffloadedPlayback());
        }
        if (this.audioOutput.isOffloadedPlayback() && this.configuration.outputConfig.useOffloadGapless) {
            this.audioOutput.setOffloadDelayPadding(this.configuration.inputFormat.encoderDelay, this.configuration.inputFormat.encoderPadding);
        }
        PlayerId playerId = this.playerId;
        if (playerId != null) {
            this.audioOutput.setPlayerId(playerId);
        }
        setVolumeInternal();
        int i10 = this.auxEffectInfo.effectId;
        if (i10 != 0) {
            this.audioOutput.attachAuxEffect(i10);
            this.audioOutput.setAuxEffectSendLevel(this.auxEffectInfo.sendLevel);
        }
        AudioDeviceInfo audioDeviceInfo = this.preferredDevice;
        if (audioDeviceInfo != null) {
            this.audioOutput.setPreferredDevice(audioDeviceInfo);
        }
        this.startMediaTimeUsNeedsInit = true;
        int audioSessionId = this.audioOutput.getAudioSessionId();
        boolean z = audioSessionId != this.audioSessionId;
        this.audioSessionId = audioSessionId;
        AudioSink.Listener listener = this.listener;
        if (listener != null) {
            listener.onAudioTrackInitialized(this.configuration.buildAudioTrackConfig());
            if (z) {
                this.pendingAudioSessionIdChangeConfirmation = true;
                Configuration configuration = this.configuration;
                this.configuration = configuration.copyWithOutputConfig(configuration.outputConfig.buildUpon().setAudioSessionId(this.audioSessionId).build());
                Configuration configuration2 = this.pendingConfiguration;
                if (configuration2 != null) {
                    this.pendingConfiguration = configuration2.copyWithOutputConfig(configuration2.outputConfig.buildUpon().setAudioSessionId(this.audioSessionId).build());
                }
                this.listener.onAudioSessionIdChanged(this.audioSessionId);
            }
        }
        return true;
    }

    private boolean isAudioOutputInitialized() {
        return this.audioOutput != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$maybeAddAudioOutputProviderListener$0() {
        AudioSink.Listener listener = this.listener;
        if (listener != null) {
            listener.onAudioCapabilitiesChanged();
        }
    }

    private void maybeAddAudioOutputProviderListener() {
        if (this.audioOutputProviderListener != null || this.context == null) {
            return;
        }
        AudioOutputProvider.Listener listener = new AudioOutputProvider.Listener() { // from class: androidx.media3.exoplayer.audio.s
            @Override // androidx.media3.exoplayer.audio.AudioOutputProvider.Listener
            public final void onFormatSupportChanged() {
                this.f3578a.lambda$maybeAddAudioOutputProviderListener$0();
            }
        };
        this.audioOutputProviderListener = listener;
        this.audioOutputProvider.addListener(listener);
    }

    private void maybeDisableOffload() {
        if (this.configuration.outputConfig.isOffload) {
            this.offloadDisabledUntilNextConfiguration = true;
        }
    }

    private ByteBuffer maybeRampUpVolume(ByteBuffer byteBuffer) {
        if (this.configuration.isPcm()) {
            int iDurationUsToSampleCount = (int) Util.durationUsToSampleCount(Util.msToUs(20L), this.configuration.outputConfig.sampleRate);
            long writtenFrames = getWrittenFrames();
            if (writtenFrames < iDurationUsToSampleCount) {
                return PcmAudioUtil.rampUpVolume(byteBuffer, this.configuration.outputConfig.encoding, this.configuration.outputPcmFrameSize, (int) writtenFrames, iDurationUsToSampleCount);
            }
        }
        return byteBuffer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeReportSkippedSilence() {
        if (this.accumulatedSkippedSilenceDurationUs >= 300000) {
            this.listener.onSilenceSkipped();
            this.accumulatedSkippedSilenceDurationUs = 0L;
        }
    }

    private void playPendingData() {
        if (this.stoppedAudioOutput) {
            return;
        }
        this.stoppedAudioOutput = true;
        if (this.audioOutput.isOffloadedPlayback()) {
            this.handledOffloadOnPresentationEnded = false;
        }
        this.audioOutput.stop();
    }

    private void processBuffers(long j10) throws Exception {
        drainOutputBuffer(j10);
        if (this.outputBuffer != null) {
            return;
        }
        if (!this.audioProcessingPipeline.isOperational()) {
            ByteBuffer byteBuffer = this.inputBuffer;
            if (byteBuffer != null) {
                setOutputBuffer(byteBuffer);
                drainOutputBuffer(j10);
                return;
            }
            return;
        }
        while (!this.audioProcessingPipeline.isEnded()) {
            do {
                ByteBuffer output = this.audioProcessingPipeline.getOutput();
                if (output.hasRemaining()) {
                    setOutputBuffer(output);
                    drainOutputBuffer(j10);
                } else {
                    ByteBuffer byteBuffer2 = this.inputBuffer;
                    if (byteBuffer2 == null || !byteBuffer2.hasRemaining()) {
                        return;
                    } else {
                        this.audioProcessingPipeline.queueInput(this.inputBuffer);
                    }
                }
            } while (this.outputBuffer == null);
            return;
        }
    }

    private void reconfigureAndFlush() {
        if (this.configuration != null) {
            Configuration configuration = this.pendingConfiguration;
            if (configuration != null) {
                this.configuration = configuration;
                this.pendingConfiguration = null;
            }
            try {
                this.configuration = new Configuration(this.configuration.inputFormat, this.configuration.afterProcessingInputFormat, this.configuration.inputPcmFrameSize, this.configuration.outputPcmFrameSize, this.audioOutputProvider.getOutputConfig(getFormatConfig(this.configuration.afterProcessingInputFormat)), this.configuration.audioProcessingPipeline);
            } catch (AudioOutputProvider.ConfigurationException e5) {
                throw new IllegalStateException(new AudioSink.ConfigurationException(e5, this.configuration.inputFormat));
            }
        }
        flush();
    }

    private void resetSinkStateForFlush() {
        this.submittedPcmBytes = 0L;
        this.submittedEncodedFrames = 0L;
        this.writtenPcmBytes = 0L;
        this.writtenEncodedFrames = 0L;
        this.isWaitingForOffloadEndOfStreamHandled = false;
        this.framesPerEncodedSample = 0;
        this.mediaPositionParameters = new MediaPositionParameters(this.playbackParameters, 0L, 0L);
        this.startMediaTimeUs = 0L;
        this.afterDrainParameters = null;
        this.mediaPositionParametersCheckpoints.clear();
        this.inputBuffer = null;
        this.inputBufferAccessUnitCount = 0;
        this.outputBuffer = null;
        this.stoppedAudioOutput = false;
        this.handledEndOfStream = false;
        this.handledOffloadOnPresentationEnded = false;
        this.trimmingAudioProcessor.resetTrimmedFrameCount();
        setupAudioProcessors();
    }

    private static int resolveDefaultVirtualDeviceIds(int i10) {
        if (i10 == 0 || i10 == -1) {
            return -1;
        }
        return i10;
    }

    private void setAudioOutputPlaybackParameters() {
        if (isAudioOutputInitialized()) {
            this.audioOutput.setPlaybackParameters(this.playbackParameters);
            this.playbackParameters = this.audioOutput.getPlaybackParameters();
        }
    }

    private void setAudioProcessorPlaybackParameters(PlaybackParameters playbackParameters) {
        MediaPositionParameters mediaPositionParameters = new MediaPositionParameters(playbackParameters, C.TIME_UNSET, C.TIME_UNSET);
        if (isAudioOutputInitialized()) {
            this.afterDrainParameters = mediaPositionParameters;
        } else {
            this.mediaPositionParameters = mediaPositionParameters;
        }
    }

    private void setOutputBuffer(ByteBuffer byteBuffer) {
        ac.b.s(this.outputBuffer == null);
        if (byteBuffer.hasRemaining()) {
            this.outputBuffer = maybeRampUpVolume(byteBuffer);
        }
    }

    private void setVolumeInternal() {
        if (isAudioOutputInitialized()) {
            this.audioOutput.setVolume(this.volume);
        }
    }

    private void setupAudioProcessors() {
        AudioProcessingPipeline audioProcessingPipeline = this.configuration.audioProcessingPipeline;
        this.audioProcessingPipeline = audioProcessingPipeline;
        audioProcessingPipeline.flush();
    }

    private boolean shouldApplyAudioProcessorPlaybackParameters() {
        return (this.tunneling || !this.configuration.isPcm() || shouldUseFloatOutput(this.configuration.inputFormat.pcmEncoding)) ? false : true;
    }

    private boolean shouldUseFloatOutput(int i10) {
        return this.enableFloatOutput && Util.isEncodingHighResolutionPcm(i10);
    }

    private boolean useAudioOutputPlaybackParams() {
        Configuration configuration = this.configuration;
        return configuration != null && configuration.outputConfig.usePlaybackParameters;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void configure(Format format, int i10, int[] iArr) throws AudioSink.ConfigurationException {
        Format formatBuild;
        int i11;
        AudioProcessingPipeline audioProcessingPipeline;
        int pcmFrameSize;
        maybeAddAudioOutputProviderListener();
        if (MimeTypes.AUDIO_RAW.equals(format.sampleMimeType)) {
            ac.b.j(Util.isEncodingLinearPcm(format.pcmEncoding));
            int pcmFrameSize2 = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
            e1 e1Var = new e1(4);
            e1Var.d(this.availableAudioProcessors);
            if (shouldUseFloatOutput(format.pcmEncoding)) {
                e1Var.c(this.toFloatPcmAudioProcessor);
            } else {
                e1Var.c(this.toInt16PcmAudioProcessor);
                AudioProcessor[] audioProcessors = this.audioProcessorChain.getAudioProcessors();
                int length = audioProcessors.length;
                a0.a(length, audioProcessors);
                e1Var.e(length);
                System.arraycopy(audioProcessors, 0, e1Var.f13927a, e1Var.f13928b, length);
                e1Var.f13928b += length;
            }
            AudioProcessingPipeline audioProcessingPipeline2 = new AudioProcessingPipeline(e1Var.f());
            if (audioProcessingPipeline2.equals(this.audioProcessingPipeline)) {
                audioProcessingPipeline2 = this.audioProcessingPipeline;
            }
            this.trimmingAudioProcessor.setTrimFrameCount(format.encoderDelay, format.encoderPadding);
            this.channelMappingAudioProcessor.setChannelMap(iArr);
            try {
                AudioProcessor.AudioFormat audioFormatConfigure = audioProcessingPipeline2.configure(new AudioProcessor.AudioFormat(format));
                formatBuild = format.buildUpon().setPcmEncoding(audioFormatConfigure.encoding).setSampleRate(audioFormatConfigure.sampleRate).setChannelCount(audioFormatConfigure.channelCount).build();
                audioProcessingPipeline = audioProcessingPipeline2;
                pcmFrameSize = Util.getPcmFrameSize(audioFormatConfigure.encoding, audioFormatConfigure.channelCount);
                i11 = pcmFrameSize2;
            } catch (AudioProcessor.UnhandledAudioFormatException e5) {
                throw new AudioSink.ConfigurationException(e5, format);
            }
        } else {
            f1 f1Var = h1.f14020l;
            formatBuild = format;
            i11 = -1;
            audioProcessingPipeline = new AudioProcessingPipeline(o3.f14078o);
            pcmFrameSize = -1;
        }
        AudioOutputProvider.FormatConfig formatConfig = getFormatConfig(formatBuild, i10 != 0 ? i10 : -1);
        try {
            AudioOutputProvider.OutputConfig outputConfig = this.audioOutputProvider.getOutputConfig(formatConfig);
            if (outputConfig.encoding == 0) {
                throw new AudioSink.ConfigurationException(a0.c.m(")", outputConfig.isOffload, new StringBuilder("Invalid output encoding (isOffload=")), formatConfig.format);
            }
            if (outputConfig.channelMask == 0) {
                throw new AudioSink.ConfigurationException(a0.c.m(")", outputConfig.isOffload, new StringBuilder("Invalid output channel config (isOffload=")), formatConfig.format);
            }
            this.offloadDisabledUntilNextConfiguration = false;
            Configuration configuration = new Configuration(format, formatBuild, i11, pcmFrameSize, outputConfig, audioProcessingPipeline);
            if (isAudioOutputInitialized()) {
                this.pendingConfiguration = configuration;
            } else {
                this.configuration = configuration;
            }
        } catch (AudioOutputProvider.ConfigurationException e6) {
            throw new AudioSink.ConfigurationException(e6, format);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void disableTunneling() {
        if (this.tunneling) {
            this.tunneling = false;
            reconfigureAndFlush();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void enableTunnelingV21() {
        ac.b.s(this.externalAudioSessionIdProvided);
        if (this.tunneling) {
            return;
        }
        this.tunneling = true;
        reconfigureAndFlush();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void flush() {
        if (isAudioOutputInitialized()) {
            resetSinkStateForFlush();
            this.audioOutputListener = null;
            Configuration configuration = this.pendingConfiguration;
            if (configuration != null) {
                this.configuration = configuration;
                this.pendingConfiguration = null;
            }
            pendingReleaseCount.incrementAndGet();
            this.audioOutput.release();
            this.audioOutput = null;
        }
        this.writeExceptionPendingExceptionHolder.clear();
        this.initializationExceptionPendingExceptionHolder.clear();
        this.skippedOutputFrameCountAtLastPosition = 0L;
        this.accumulatedSkippedSilenceDurationUs = 0L;
        Handler handler = this.reportSkippedSilenceHandler;
        if (handler != null) {
            handler.getClass();
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public long getAudioTrackBufferSizeUs() {
        return !isAudioOutputInitialized() ? C.TIME_UNSET : this.configuration.isPcm() ? this.configuration.framesToDurationUs(this.audioOutput.getBufferSizeInFrames()) : Util.scaleLargeValue(this.audioOutput.getBufferSizeInFrames(), 1000000L, getNonPcmMaximumEncodedRateBytesPerSecond(this.configuration.outputConfig.encoding), RoundingMode.DOWN);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public long getCurrentPositionUs(boolean z) {
        if (!isAudioOutputInitialized() || this.startMediaTimeUsNeedsInit) {
            return Long.MIN_VALUE;
        }
        return applySkipping(applyMediaPositionParameters(Math.min(this.audioOutput.getPositionUs(), this.configuration.framesToDurationUs(getWrittenFrames()))));
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public AudioOffloadSupport getFormatOffloadSupport(Format format) {
        if (this.offloadDisabledUntilNextConfiguration) {
            return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        }
        AudioOutputProvider.FormatSupport formatSupport = this.audioOutputProvider.getFormatSupport(getFormatConfig(format));
        return new AudioOffloadSupport.Builder().setIsFormatSupported(formatSupport.isFormatSupportedForOffload).setIsGaplessSupported(formatSupport.isGaplessSupportedForOffload).setIsSpeedChangeSupported(formatSupport.isSpeedChangeSupportedForOffload).build();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public int getFormatSupport(Format format) {
        int i10 = this.audioOutputProvider.getFormatSupport(getFormatConfig(format)).supportLevel;
        int i11 = 1;
        if (i10 != 1) {
            i11 = 2;
            if (i10 != 2) {
                return 0;
            }
        }
        return i11;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean getSkipSilenceEnabled() {
        return this.skipSilenceEnabled;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean handleBuffer(ByteBuffer byteBuffer, long j10, int i10) throws Exception {
        ByteBuffer byteBuffer2 = this.inputBuffer;
        ac.b.j(byteBuffer2 == null || byteBuffer == byteBuffer2);
        if (this.pendingConfiguration != null) {
            if (!drainToEndOfStream()) {
                return false;
            }
            if (this.pendingConfiguration.canReuseAudioOutput(this.configuration)) {
                this.configuration = this.pendingConfiguration;
                this.pendingConfiguration = null;
                AudioOutput audioOutput = this.audioOutput;
                if (audioOutput != null && audioOutput.isOffloadedPlayback() && this.configuration.outputConfig.useOffloadGapless) {
                    this.audioOutput.setOffloadEndOfStream();
                    this.audioOutput.setOffloadDelayPadding(this.configuration.inputFormat.encoderDelay, this.configuration.inputFormat.encoderPadding);
                    this.isWaitingForOffloadEndOfStreamHandled = true;
                }
            } else {
                playPendingData();
                if (hasPendingData()) {
                    return false;
                }
                flush();
            }
            applyAudioProcessorPlaybackParametersAndSkipSilence(j10);
        }
        if (!isAudioOutputInitialized()) {
            try {
                if (!initializeAudioOutput()) {
                    return false;
                }
            } catch (AudioSink.InitializationException e5) {
                if (e5.isRecoverable) {
                    throw e5;
                }
                this.initializationExceptionPendingExceptionHolder.throwExceptionIfDeadlineIsReached(e5);
                return false;
            }
        }
        this.initializationExceptionPendingExceptionHolder.clear();
        if (this.startMediaTimeUsNeedsInit) {
            this.startMediaTimeUs = Math.max(0L, j10);
            this.startMediaTimeUsNeedsSync = false;
            this.startMediaTimeUsNeedsInit = false;
            if (useAudioOutputPlaybackParams()) {
                setAudioOutputPlaybackParameters();
            }
            applyAudioProcessorPlaybackParametersAndSkipSilence(j10);
            if (this.playing) {
                play();
            }
        }
        if (this.inputBuffer == null) {
            ac.b.j(byteBuffer.order() == ByteOrder.LITTLE_ENDIAN);
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (!this.configuration.isPcm() && this.framesPerEncodedSample == 0) {
                int framesPerEncodedSample = getFramesPerEncodedSample(this.configuration.outputConfig.encoding, byteBuffer);
                this.framesPerEncodedSample = framesPerEncodedSample;
                if (framesPerEncodedSample == 0) {
                    return true;
                }
            }
            if (this.afterDrainParameters != null) {
                if (!drainToEndOfStream()) {
                    return false;
                }
                applyAudioProcessorPlaybackParametersAndSkipSilence(j10);
                this.afterDrainParameters = null;
            }
            long jInputFramesToDurationUs = this.startMediaTimeUs + this.configuration.inputFramesToDurationUs(getSubmittedFrames() - this.trimmingAudioProcessor.getTrimmedFrameCount());
            if (!this.startMediaTimeUsNeedsSync && Math.abs(jInputFramesToDurationUs - j10) > 200000) {
                AudioSink.Listener listener = this.listener;
                if (listener != null) {
                    listener.onAudioSinkError(new AudioSink.UnexpectedDiscontinuityException(j10, jInputFramesToDurationUs));
                }
                this.startMediaTimeUsNeedsSync = true;
            }
            if (this.startMediaTimeUsNeedsSync) {
                if (!drainToEndOfStream()) {
                    return false;
                }
                long j11 = j10 - jInputFramesToDurationUs;
                this.startMediaTimeUs += j11;
                this.startMediaTimeUsNeedsSync = false;
                applyAudioProcessorPlaybackParametersAndSkipSilence(j10);
                AudioSink.Listener listener2 = this.listener;
                if (listener2 != null && j11 != 0) {
                    listener2.onPositionDiscontinuity();
                }
            }
            if (this.configuration.isPcm()) {
                this.submittedPcmBytes += (long) byteBuffer.remaining();
            } else {
                this.submittedEncodedFrames = (((long) this.framesPerEncodedSample) * ((long) i10)) + this.submittedEncodedFrames;
            }
            this.inputBuffer = byteBuffer;
            this.inputBufferAccessUnitCount = i10;
        }
        processBuffers(j10);
        if (!this.inputBuffer.hasRemaining()) {
            this.inputBuffer = null;
            this.inputBufferAccessUnitCount = 0;
            return true;
        }
        if (!this.audioOutput.isStalled()) {
            return false;
        }
        Log.w(TAG, "Resetting stalled audio output");
        flush();
        return true;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void handleDiscontinuity() {
        this.startMediaTimeUsNeedsSync = true;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean hasPendingData() {
        if (isAudioOutputInitialized()) {
            return !(Build.VERSION.SDK_INT >= 29 && this.audioOutput.isOffloadedPlayback() && this.handledOffloadOnPresentationEnded) && hasAudioOutputPendingData(getWrittenFrames());
        }
        return false;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean isEnded() {
        if (isAudioOutputInitialized()) {
            return this.handledEndOfStream && !hasPendingData();
        }
        return true;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void pause() {
        this.playing = false;
        if (isAudioOutputInitialized()) {
            this.audioOutput.pause();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void play() {
        this.playing = true;
        if (isAudioOutputInitialized()) {
            this.audioOutput.play();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void playToEndOfStream() throws AudioSink.WriteException {
        if (!this.handledEndOfStream && isAudioOutputInitialized() && drainToEndOfStream()) {
            playPendingData();
            this.handledEndOfStream = true;
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void release() {
        this.audioOutputProvider.release();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void reset() {
        flush();
        f1 f1VarListIterator = this.availableAudioProcessors.listIterator(0);
        while (f1VarListIterator.hasNext()) {
            ((AudioProcessor) f1VarListIterator.next()).reset();
        }
        this.toInt16PcmAudioProcessor.reset();
        this.toFloatPcmAudioProcessor.reset();
        AudioProcessingPipeline audioProcessingPipeline = this.audioProcessingPipeline;
        if (audioProcessingPipeline != null) {
            audioProcessingPipeline.reset();
        }
        this.playing = false;
        this.offloadDisabledUntilNextConfiguration = false;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioAttributes(AudioAttributes audioAttributes) {
        if (this.audioAttributes.equals(audioAttributes)) {
            return;
        }
        this.audioAttributes = audioAttributes;
        if (this.tunneling) {
            return;
        }
        reconfigureAndFlush();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioOutputProvider(AudioOutputProvider audioOutputProvider) {
        if (audioOutputProvider.equals(this.audioOutputProvider)) {
            return;
        }
        this.audioOutputProvider.release();
        this.audioOutputProvider = audioOutputProvider;
        AudioOutputProvider.Listener listener = this.audioOutputProviderListener;
        if (listener != null) {
            audioOutputProvider.addListener(listener);
        }
        reconfigureAndFlush();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioSessionId(int i10) {
        if (this.pendingAudioSessionIdChangeConfirmation) {
            if (this.audioSessionId != i10) {
                return;
            } else {
                this.pendingAudioSessionIdChangeConfirmation = false;
            }
        }
        if (this.audioSessionId != i10) {
            this.audioSessionId = i10;
            this.externalAudioSessionIdProvided = i10 != 0;
            reconfigureAndFlush();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        if (this.auxEffectInfo.equals(auxEffectInfo)) {
            return;
        }
        int i10 = auxEffectInfo.effectId;
        float f10 = auxEffectInfo.sendLevel;
        AudioOutput audioOutput = this.audioOutput;
        if (audioOutput != null) {
            if (this.auxEffectInfo.effectId != i10) {
                audioOutput.attachAuxEffect(i10);
            }
            if (i10 != 0) {
                this.audioOutput.setAuxEffectSendLevel(f10);
            }
        }
        this.auxEffectInfo = auxEffectInfo;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setClock(Clock clock) {
        this.audioOutputProvider.setClock(clock);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setListener(AudioSink.Listener listener) {
        this.listener = listener;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setOffloadDelayPadding(int i10, int i11) {
        Configuration configuration;
        AudioOutput audioOutput = this.audioOutput;
        if (audioOutput == null || !audioOutput.isOffloadedPlayback() || (configuration = this.configuration) == null || !configuration.outputConfig.useOffloadGapless) {
            return;
        }
        this.audioOutput.setOffloadDelayPadding(i10, i11);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setOffloadMode(int i10) {
        ac.b.s(Build.VERSION.SDK_INT >= 29);
        this.offloadMode = i10;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public final /* synthetic */ void setOutputStreamOffsetUs(long j10) {
        l.g(this, j10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.playbackParameters = new PlaybackParameters(Util.constrainValue(playbackParameters.speed, 0.1f, 8.0f), Util.constrainValue(playbackParameters.pitch, 0.1f, 8.0f));
        if (useAudioOutputPlaybackParams()) {
            setAudioOutputPlaybackParameters();
        } else {
            setAudioProcessorPlaybackParameters(playbackParameters);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
        this.preferredDevice = audioDeviceInfo;
        AudioOutput audioOutput = this.audioOutput;
        if (audioOutput != null) {
            audioOutput.setPreferredDevice(audioDeviceInfo);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setSkipSilenceEnabled(boolean z) {
        this.skipSilenceEnabled = z;
        setAudioProcessorPlaybackParameters(useAudioOutputPlaybackParams() ? PlaybackParameters.DEFAULT : this.playbackParameters);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setVirtualDeviceId(int i10) {
        int iResolveDefaultVirtualDeviceIds = resolveDefaultVirtualDeviceIds(i10);
        if (this.virtualDeviceId == iResolveDefaultVirtualDeviceIds) {
            return;
        }
        this.virtualDeviceId = iResolveDefaultVirtualDeviceIds;
        reconfigureAndFlush();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setVolume(float f10) {
        if (this.volume != f10) {
            this.volume = f10;
            setVolumeInternal();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean supportsFormat(Format format) {
        return getFormatSupport(format) != 0;
    }

    @RequiresNonNull({"#1.audioProcessorChain"})
    private DefaultAudioSink(Builder builder) {
        this.context = builder.context == null ? null : builder.context.getApplicationContext();
        this.audioAttributes = AudioAttributes.DEFAULT;
        this.audioProcessorChain = builder.audioProcessorChain;
        this.enableFloatOutput = builder.enableFloatOutput;
        this.preferAudioOutputPlaybackParameters = builder.enableAudioOutputPlaybackParameters;
        this.offloadMode = 0;
        this.audioOutputProvider = builder.audioOutputProvider;
        ChannelMappingAudioProcessor channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.channelMappingAudioProcessor = channelMappingAudioProcessor;
        TrimmingAudioProcessor trimmingAudioProcessor = new TrimmingAudioProcessor();
        this.trimmingAudioProcessor = trimmingAudioProcessor;
        this.toInt16PcmAudioProcessor = new ToInt16PcmAudioProcessor();
        this.toFloatPcmAudioProcessor = new ToFloatPcmAudioProcessor();
        this.availableAudioProcessors = h1.t(trimmingAudioProcessor, channelMappingAudioProcessor);
        this.volume = 1.0f;
        this.audioSessionId = 0;
        this.auxEffectInfo = new AuxEffectInfo(0, 0.0f);
        PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
        this.mediaPositionParameters = new MediaPositionParameters(playbackParameters, 0L, 0L);
        this.playbackParameters = playbackParameters;
        this.skipSilenceEnabled = false;
        this.mediaPositionParametersCheckpoints = new ArrayDeque<>();
        this.initializationExceptionPendingExceptionHolder = new PendingExceptionHolder<>();
        this.writeExceptionPendingExceptionHolder = new PendingExceptionHolder<>();
        this.audioOffloadListener = builder.audioOffloadListener;
        this.virtualDeviceId = (Build.VERSION.SDK_INT < 34 || builder.context == null) ? -1 : getDeviceIdFromContext(builder.context);
    }

    private AudioOutputProvider.FormatConfig getFormatConfig(Format format, int i10) {
        return new AudioOutputProvider.FormatConfig.Builder(format).setAudioAttributes(this.audioAttributes).setEnableHighResolutionPcmOutput(this.enableFloatOutput).setEnablePlaybackParameters(this.preferAudioOutputPlaybackParameters).setEnableOffload(this.offloadMode != 0).setPreferredDevice(this.preferredDevice).setAudioSessionId(this.audioSessionId).setEnableTunneling(this.tunneling).setPreferredBufferSize(i10).setVirtualDeviceId(this.virtualDeviceId).build();
    }

    public static final class Builder {
        private AudioCapabilities audioCapabilities;
        private ExoPlayer.AudioOffloadListener audioOffloadListener;
        private AudioOffloadSupportProvider audioOffloadSupportProvider;
        private AudioOutputProvider audioOutputProvider;
        private androidx.media3.common.audio.AudioProcessorChain audioProcessorChain;
        private AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
        private AudioTrackProvider audioTrackProvider;
        private boolean buildCalled;
        private final Context context;
        private boolean enableAudioOutputPlaybackParameters;
        private boolean enableFloatOutput;

        @Deprecated
        public Builder() {
            this.context = null;
            this.audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
        }

        public DefaultAudioSink build() {
            ac.b.s(!this.buildCalled);
            this.buildCalled = true;
            if (this.audioProcessorChain == null) {
                this.audioProcessorChain = new DefaultAudioProcessorChain(new AudioProcessor[0]);
            }
            if (this.audioOutputProvider == null) {
                if (this.audioOffloadSupportProvider == null) {
                    this.audioOffloadSupportProvider = new DefaultAudioOffloadSupportProvider(this.context);
                }
                if (this.audioTrackBufferSizeProvider == null) {
                    this.audioTrackBufferSizeProvider = AudioTrackBufferSizeProvider.DEFAULT;
                }
                this.audioOutputProvider = new AudioTrackAudioOutputProvider.Builder(this.context).setAudioCapabilities(this.context != null ? null : this.audioCapabilities).setAudioOffloadSupportProvider(this.audioOffloadSupportProvider).setAudioTrackBufferSizeProvider(this.audioTrackBufferSizeProvider).setAudioTrackProvider(this.audioTrackProvider).build();
            } else {
                ac.b.s(this.audioOffloadSupportProvider == null);
                ac.b.s(this.audioTrackBufferSizeProvider == null);
                ac.b.s(this.audioTrackProvider == null);
            }
            return new DefaultAudioSink(this);
        }

        @Deprecated
        public Builder setAudioCapabilities(AudioCapabilities audioCapabilities) {
            audioCapabilities.getClass();
            this.audioCapabilities = audioCapabilities;
            return this;
        }

        @Deprecated
        public Builder setAudioOffloadSupportProvider(AudioOffloadSupportProvider audioOffloadSupportProvider) {
            this.audioOffloadSupportProvider = audioOffloadSupportProvider;
            return this;
        }

        public Builder setAudioOutputProvider(AudioOutputProvider audioOutputProvider) {
            ac.b.t(this.context != null, "Cannot set AudioOutputProvider without a Context");
            this.audioOutputProvider = audioOutputProvider;
            return this;
        }

        public Builder setAudioProcessorChain(androidx.media3.common.audio.AudioProcessorChain audioProcessorChain) {
            audioProcessorChain.getClass();
            this.audioProcessorChain = audioProcessorChain;
            return this;
        }

        public Builder setAudioProcessors(AudioProcessor[] audioProcessorArr) {
            audioProcessorArr.getClass();
            return setAudioProcessorChain(new DefaultAudioProcessorChain(audioProcessorArr));
        }

        @Deprecated
        public Builder setAudioTrackBufferSizeProvider(AudioTrackBufferSizeProvider audioTrackBufferSizeProvider) {
            this.audioTrackBufferSizeProvider = audioTrackBufferSizeProvider;
            return this;
        }

        @Deprecated
        public Builder setAudioTrackProvider(AudioTrackProvider audioTrackProvider) {
            this.audioTrackProvider = audioTrackProvider;
            return this;
        }

        public Builder setEnableAudioOutputPlaybackParameters(boolean z) {
            this.enableAudioOutputPlaybackParameters = z;
            return this;
        }

        @Deprecated
        public Builder setEnableAudioTrackPlaybackParams(boolean z) {
            return setEnableAudioOutputPlaybackParameters(z);
        }

        public Builder setEnableFloatOutput(boolean z) {
            this.enableFloatOutput = z;
            return this;
        }

        public Builder setExperimentalAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
            this.audioOffloadListener = audioOffloadListener;
            return this;
        }

        public Builder(Context context) {
            this.context = context;
            this.audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
        }
    }
}
