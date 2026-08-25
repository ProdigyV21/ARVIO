package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.SpeedProviderUtil;
import androidx.media3.common.util.TimestampConsumer;
import androidx.media3.common.util.Util;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: loaded from: classes3.dex */
public final class SpeedChangingAudioProcessor implements AudioProcessor {
    private float currentSpeed;
    private boolean endOfStreamQueuedToSonic;
    private long framesRead;
    private AudioProcessor.AudioFormat inputAudioFormat;
    private boolean inputEnded;
    private final Object lock;
    private final LongArrayQueue pendingCallbackInputTimesUs;
    private final Queue<TimestampConsumer> pendingCallbacks;
    private AudioProcessor.AudioFormat pendingInputAudioFormat;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;
    private final SynchronizedSonicAudioProcessor sonicAudioProcessor;
    private final SpeedProvider speedProvider;

    public SpeedChangingAudioProcessor(SpeedProvider speedProvider) {
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.speedProvider = speedProvider;
        Object obj = new Object();
        this.lock = obj;
        this.sonicAudioProcessor = new SynchronizedSonicAudioProcessor(obj, true);
        this.pendingCallbackInputTimesUs = new LongArrayQueue();
        this.pendingCallbacks = new ArrayDeque();
        this.currentSpeed = 1.0f;
    }

    private static long getDurationUsAfterProcessorApplied(SpeedProvider speedProvider, int i10, long j10) {
        return Util.sampleCountToDurationUs(getSampleCountAfterProcessorApplied(speedProvider, i10, Util.scaleLargeValue(j10, i10, 1000000L, RoundingMode.HALF_EVEN)), i10);
    }

    public static long getInputFrameCountForOutput(SpeedProvider speedProvider, int i10, long j10) {
        int i11 = i10;
        ac.b.j(i11 > 0);
        ac.b.j(j10 >= 0);
        long j11 = j10;
        long expectedInputFrameCountForOutputFrameCount = 0;
        while (j11 > 0) {
            long nextSpeedChangeSamplePosition = SpeedProviderUtil.getNextSpeedChangeSamplePosition(speedProvider, expectedInputFrameCountForOutputFrameCount, i10);
            float sampleAlignedSpeed = SpeedProviderUtil.getSampleAlignedSpeed(speedProvider, expectedInputFrameCountForOutputFrameCount, i10);
            long expectedFrameCountAfterProcessorApplied = Sonic.getExpectedFrameCountAfterProcessorApplied(i11, i10, sampleAlignedSpeed, sampleAlignedSpeed, nextSpeedChangeSamplePosition - expectedInputFrameCountForOutputFrameCount);
            if (nextSpeedChangeSamplePosition == -1 || expectedFrameCountAfterProcessorApplied > j11) {
                expectedInputFrameCountForOutputFrameCount += Sonic.getExpectedInputFrameCountForOutputFrameCount(i10, i10, sampleAlignedSpeed, sampleAlignedSpeed, j11);
                j11 = 0;
            } else {
                j11 -= expectedFrameCountAfterProcessorApplied;
                expectedInputFrameCountForOutputFrameCount = nextSpeedChangeSamplePosition;
            }
            i11 = i10;
        }
        return expectedInputFrameCountForOutputFrameCount;
    }

    public static long getSampleCountAfterProcessorApplied(SpeedProvider speedProvider, int i10, long j10) {
        ac.b.j(speedProvider != null);
        ac.b.j(i10 > 0);
        long j11 = 0;
        ac.b.j(j10 >= 0);
        long expectedFrameCountAfterProcessorApplied = 0;
        while (j11 < j10) {
            long nextSpeedChangeSamplePosition = SpeedProviderUtil.getNextSpeedChangeSamplePosition(speedProvider, j11, i10);
            if (nextSpeedChangeSamplePosition == -1 || nextSpeedChangeSamplePosition > j10) {
                nextSpeedChangeSamplePosition = j10;
            }
            float sampleAlignedSpeed = SpeedProviderUtil.getSampleAlignedSpeed(speedProvider, j11, i10);
            expectedFrameCountAfterProcessorApplied += Sonic.getExpectedFrameCountAfterProcessorApplied(i10, i10, sampleAlignedSpeed, sampleAlignedSpeed, nextSpeedChangeSamplePosition - j11);
            j11 = nextSpeedChangeSamplePosition;
        }
        return expectedFrameCountAfterProcessorApplied;
    }

    private void processPendingCallbacks() {
        synchronized (this.lock) {
            try {
                if (this.inputAudioFormat.sampleRate == -1) {
                    return;
                }
                while (!this.pendingCallbacks.isEmpty()) {
                    this.pendingCallbacks.remove().onTimestamp(getDurationUsAfterProcessorApplied(this.speedProvider, this.inputAudioFormat.sampleRate, this.pendingCallbackInputTimesUs.remove()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void resetInternalState(boolean z) {
        if (z) {
            this.currentSpeed = 1.0f;
        }
        this.framesRead = 0L;
        this.endOfStreamQueuedToSonic = false;
    }

    private void updateSpeed(float f10) {
        if (f10 != this.currentSpeed) {
            this.currentSpeed = f10;
            this.sonicAudioProcessor.setSpeed(f10);
            this.sonicAudioProcessor.setPitch(f10);
            this.sonicAudioProcessor.flush(AudioProcessor.StreamMetadata.DEFAULT);
            this.endOfStreamQueuedToSonic = false;
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        this.pendingInputAudioFormat = audioFormat;
        AudioProcessor.AudioFormat audioFormatConfigure = this.sonicAudioProcessor.configure(audioFormat);
        this.pendingOutputAudioFormat = audioFormatConfigure;
        return audioFormatConfigure;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final /* synthetic */ void flush() {
        e.a(this);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public long getDurationAfterProcessorApplied(long j10) {
        return SpeedProviderUtil.getDurationAfterSpeedProviderApplied(this.speedProvider, j10);
    }

    public long getMediaDurationUs(long j10) {
        int i10;
        synchronized (this.lock) {
            i10 = this.inputAudioFormat.sampleRate;
        }
        if (i10 == -1) {
            return j10;
        }
        return Util.sampleCountToDurationUs(getInputFrameCountForOutput(this.speedProvider, i10, Util.scaleLargeValue(j10, i10, 1000000L, RoundingMode.HALF_EVEN)), i10);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public ByteBuffer getOutput() {
        return this.sonicAudioProcessor.getOutput();
    }

    public void getSpeedAdjustedTimeAsync(long j10, TimestampConsumer timestampConsumer) {
        synchronized (this.lock) {
            try {
                int i10 = this.inputAudioFormat.sampleRate;
                if (i10 != -1) {
                    timestampConsumer.onTimestamp(getDurationUsAfterProcessorApplied(this.speedProvider, i10, j10));
                } else {
                    this.pendingCallbackInputTimesUs.add(j10);
                    this.pendingCallbacks.add(timestampConsumer);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public SpeedProvider getSpeedProvider() {
        return this.speedProvider;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        return !this.pendingOutputAudioFormat.equals(AudioProcessor.AudioFormat.NOT_SET);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isEnded() {
        return this.inputEnded && this.sonicAudioProcessor.isEnded();
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueEndOfStream() {
        this.inputEnded = true;
        if (this.endOfStreamQueuedToSonic) {
            return;
        }
        this.sonicAudioProcessor.queueEndOfStream();
        this.endOfStreamQueuedToSonic = true;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        AudioProcessor.AudioFormat audioFormat;
        int i10;
        synchronized (this.lock) {
            audioFormat = this.inputAudioFormat;
        }
        float sampleAlignedSpeed = SpeedProviderUtil.getSampleAlignedSpeed(this.speedProvider, this.framesRead, audioFormat.sampleRate);
        long nextSpeedChangeSamplePosition = SpeedProviderUtil.getNextSpeedChangeSamplePosition(this.speedProvider, this.framesRead, audioFormat.sampleRate);
        updateSpeed(sampleAlignedSpeed);
        int iLimit = byteBuffer.limit();
        if (nextSpeedChangeSamplePosition != -1) {
            i10 = (int) ((nextSpeedChangeSamplePosition - this.framesRead) * ((long) audioFormat.bytesPerFrame));
            byteBuffer.limit(Math.min(iLimit, byteBuffer.position() + i10));
        } else {
            i10 = -1;
        }
        long jPosition = byteBuffer.position();
        this.sonicAudioProcessor.queueInput(byteBuffer);
        if (i10 != -1 && ((long) byteBuffer.position()) - jPosition == i10) {
            this.sonicAudioProcessor.queueEndOfStream();
            this.endOfStreamQueuedToSonic = true;
        }
        long jPosition2 = ((long) byteBuffer.position()) - jPosition;
        ac.b.t(jPosition2 % ((long) audioFormat.bytesPerFrame) == 0, "A frame was not queued completely.");
        this.framesRead = (jPosition2 / ((long) audioFormat.bytesPerFrame)) + this.framesRead;
        byteBuffer.limit(iLimit);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void reset() {
        flush(AudioProcessor.StreamMetadata.DEFAULT);
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        synchronized (this.lock) {
            this.inputAudioFormat = audioFormat;
            this.pendingCallbackInputTimesUs.clear();
            this.pendingCallbacks.clear();
        }
        resetInternalState(true);
        this.sonicAudioProcessor.reset();
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void flush(AudioProcessor.StreamMetadata streamMetadata) {
        this.inputEnded = false;
        resetInternalState(false);
        synchronized (this.lock) {
            this.inputAudioFormat = this.pendingInputAudioFormat;
            this.sonicAudioProcessor.flush(streamMetadata);
            processPendingCallbacks();
            this.framesRead = Util.durationUsToSampleCount(streamMetadata.positionOffsetUs, this.inputAudioFormat.sampleRate);
        }
    }
}
