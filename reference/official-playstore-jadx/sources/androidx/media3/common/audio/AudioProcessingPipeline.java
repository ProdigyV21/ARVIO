package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import com.google.common.collect.h1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioProcessingPipeline {
    private final h1 audioProcessors;
    private boolean inputEnded;
    private AudioProcessor.AudioFormat outputAudioFormat;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;
    private final List<AudioProcessor> activeAudioProcessors = new ArrayList();
    private ByteBuffer[] outputBuffers = new ByteBuffer[0];

    public AudioProcessingPipeline(h1 h1Var) {
        this.audioProcessors = h1Var;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.outputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputEnded = false;
    }

    private int getFinalOutputBufferIndex() {
        return this.outputBuffers.length - 1;
    }

    private void processData(ByteBuffer byteBuffer) {
        boolean z;
        for (boolean z5 = true; z5; z5 = z) {
            z = false;
            int i10 = 0;
            while (i10 <= getFinalOutputBufferIndex()) {
                if (!this.outputBuffers[i10].hasRemaining()) {
                    AudioProcessor audioProcessor = this.activeAudioProcessors.get(i10);
                    if (!audioProcessor.isEnded()) {
                        ByteBuffer byteBuffer2 = i10 > 0 ? this.outputBuffers[i10 - 1] : byteBuffer.hasRemaining() ? byteBuffer : AudioProcessor.EMPTY_BUFFER;
                        long jRemaining = byteBuffer2.remaining();
                        audioProcessor.queueInput(byteBuffer2);
                        this.outputBuffers[i10] = audioProcessor.getOutput();
                        z |= jRemaining - ((long) byteBuffer2.remaining()) > 0 || this.outputBuffers[i10].hasRemaining();
                    } else if (!this.outputBuffers[i10].hasRemaining() && i10 < getFinalOutputBufferIndex()) {
                        this.activeAudioProcessors.get(i10 + 1).queueEndOfStream();
                    }
                }
                i10++;
            }
        }
    }

    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.equals(AudioProcessor.AudioFormat.NOT_SET)) {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
        for (int i10 = 0; i10 < this.audioProcessors.size(); i10++) {
            AudioProcessor audioProcessor = (AudioProcessor) this.audioProcessors.get(i10);
            AudioProcessor.AudioFormat audioFormatConfigure = audioProcessor.configure(audioFormat);
            if (audioProcessor.isActive()) {
                ac.b.s(!audioFormatConfigure.equals(AudioProcessor.AudioFormat.NOT_SET));
                audioFormat = audioFormatConfigure;
            }
        }
        this.pendingOutputAudioFormat = audioFormat;
        return audioFormat;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioProcessingPipeline)) {
            return false;
        }
        AudioProcessingPipeline audioProcessingPipeline = (AudioProcessingPipeline) obj;
        if (this.audioProcessors.size() != audioProcessingPipeline.audioProcessors.size()) {
            return false;
        }
        for (int i10 = 0; i10 < this.audioProcessors.size(); i10++) {
            if (this.audioProcessors.get(i10) != audioProcessingPipeline.audioProcessors.get(i10)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public void flush() {
        flush(AudioProcessor.StreamMetadata.DEFAULT);
    }

    public ByteBuffer getOutput() {
        if (!isOperational()) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        ByteBuffer byteBuffer = this.outputBuffers[getFinalOutputBufferIndex()];
        if (byteBuffer.hasRemaining()) {
            return byteBuffer;
        }
        processData(AudioProcessor.EMPTY_BUFFER);
        return this.outputBuffers[getFinalOutputBufferIndex()];
    }

    public AudioProcessor.AudioFormat getOutputAudioFormat() {
        return this.outputAudioFormat;
    }

    public int hashCode() {
        return this.audioProcessors.hashCode();
    }

    public boolean isEnded() {
        return this.inputEnded && this.activeAudioProcessors.get(getFinalOutputBufferIndex()).isEnded() && !this.outputBuffers[getFinalOutputBufferIndex()].hasRemaining();
    }

    public boolean isOperational() {
        return !this.activeAudioProcessors.isEmpty();
    }

    public void queueEndOfStream() {
        if (!isOperational() || this.inputEnded) {
            return;
        }
        this.inputEnded = true;
        this.activeAudioProcessors.get(0).queueEndOfStream();
    }

    public void queueInput(ByteBuffer byteBuffer) {
        if (!isOperational() || this.inputEnded) {
            return;
        }
        processData(byteBuffer);
    }

    public void reset() {
        for (int i10 = 0; i10 < this.audioProcessors.size(); i10++) {
            AudioProcessor audioProcessor = (AudioProcessor) this.audioProcessors.get(i10);
            audioProcessor.flush(AudioProcessor.StreamMetadata.DEFAULT);
            audioProcessor.reset();
        }
        this.outputBuffers = new ByteBuffer[0];
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.outputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputEnded = false;
    }

    public void flush(AudioProcessor.StreamMetadata streamMetadata) {
        this.activeAudioProcessors.clear();
        this.outputAudioFormat = this.pendingOutputAudioFormat;
        this.inputEnded = false;
        long durationAfterProcessorApplied = streamMetadata.positionOffsetUs;
        for (int i10 = 0; i10 < this.audioProcessors.size(); i10++) {
            AudioProcessor audioProcessor = (AudioProcessor) this.audioProcessors.get(i10);
            audioProcessor.flush(new AudioProcessor.StreamMetadata(durationAfterProcessorApplied));
            if (audioProcessor.isActive()) {
                durationAfterProcessorApplied = audioProcessor.getDurationAfterProcessorApplied(durationAfterProcessorApplied);
                ac.b.s(durationAfterProcessorApplied >= 0);
                this.activeAudioProcessors.add(audioProcessor);
            }
        }
        this.outputBuffers = new ByteBuffer[this.activeAudioProcessors.size()];
        for (int i11 = 0; i11 <= getFinalOutputBufferIndex(); i11++) {
            this.outputBuffers[i11] = this.activeAudioProcessors.get(i11).getOutput();
        }
    }
}
