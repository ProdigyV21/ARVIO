package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
class SynchronizedSonicAudioProcessor implements AudioProcessor {
    private final Object lock;
    private final SonicAudioProcessor sonicAudioProcessor;

    public SynchronizedSonicAudioProcessor(Object obj, boolean z) {
        this.lock = obj;
        this.sonicAudioProcessor = new SonicAudioProcessor(z);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        AudioProcessor.AudioFormat audioFormatConfigure;
        synchronized (this.lock) {
            audioFormatConfigure = this.sonicAudioProcessor.configure(audioFormat);
        }
        return audioFormatConfigure;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final /* synthetic */ void flush() {
        e.a(this);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public long getDurationAfterProcessorApplied(long j10) {
        return getPlayoutDuration(j10);
    }

    public final long getMediaDuration(long j10) {
        long mediaDuration;
        synchronized (this.lock) {
            mediaDuration = this.sonicAudioProcessor.getMediaDuration(j10);
        }
        return mediaDuration;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final ByteBuffer getOutput() {
        ByteBuffer output;
        synchronized (this.lock) {
            output = this.sonicAudioProcessor.getOutput();
        }
        return output;
    }

    public final long getPlayoutDuration(long j10) {
        long playoutDuration;
        synchronized (this.lock) {
            playoutDuration = this.sonicAudioProcessor.getPlayoutDuration(j10);
        }
        return playoutDuration;
    }

    public final long getProcessedInputBytes() {
        long processedInputBytes;
        synchronized (this.lock) {
            processedInputBytes = this.sonicAudioProcessor.getProcessedInputBytes();
        }
        return processedInputBytes;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final boolean isActive() {
        boolean zIsActive;
        synchronized (this.lock) {
            zIsActive = this.sonicAudioProcessor.isActive();
        }
        return zIsActive;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final boolean isEnded() {
        boolean zIsEnded;
        synchronized (this.lock) {
            zIsEnded = this.sonicAudioProcessor.isEnded();
        }
        return zIsEnded;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void queueEndOfStream() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.queueEndOfStream();
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void queueInput(ByteBuffer byteBuffer) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.queueInput(byteBuffer);
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void reset() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.reset();
        }
    }

    public final void setOutputSampleRateHz(int i10) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setOutputSampleRateHz(i10);
        }
    }

    public final void setPitch(float f10) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setPitch(f10);
        }
    }

    public final void setSpeed(float f10) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setSpeed(f10);
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void flush(AudioProcessor.StreamMetadata streamMetadata) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.flush(streamMetadata);
        }
    }
}
