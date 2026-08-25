package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes3.dex */
public final class SonicAudioProcessor implements AudioProcessor {
    private static final float CLOSE_THRESHOLD = 1.0E-4f;
    private static final int MIN_BYTES_FOR_DURATION_SCALING_CALCULATION = 1024;
    public static final int SAMPLE_RATE_NO_CHANGE = -1;
    private ByteBuffer buffer;
    private AudioProcessor.AudioFormat inputAudioFormat;
    private long inputBytes;
    private boolean inputEnded;
    private AudioProcessor.AudioFormat outputAudioFormat;
    private ByteBuffer outputBuffer;
    private long outputBytes;
    private AudioProcessor.AudioFormat pendingInputAudioFormat;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;
    private int pendingOutputSampleRate;
    private boolean pendingSonicRecreation;
    private float pitch;
    private final boolean shouldBeActiveWithDefaultParameters;
    private Sonic sonic;
    private float speed;

    public SonicAudioProcessor() {
        this(false);
    }

    private boolean areParametersSetToDefaultValues() {
        return Math.abs(this.speed - 1.0f) < CLOSE_THRESHOLD && Math.abs(this.pitch - 1.0f) < CLOSE_THRESHOLD && this.pendingOutputAudioFormat.sampleRate == this.pendingInputAudioFormat.sampleRate;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int i10 = audioFormat.encoding;
        if (i10 != 2 && i10 != 4) {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
        int i11 = this.pendingOutputSampleRate;
        if (i11 == -1) {
            i11 = audioFormat.sampleRate;
        }
        this.pendingInputAudioFormat = audioFormat;
        AudioProcessor.AudioFormat audioFormat2 = new AudioProcessor.AudioFormat(i11, audioFormat.channelCount, audioFormat.encoding);
        this.pendingOutputAudioFormat = audioFormat2;
        this.pendingSonicRecreation = true;
        return audioFormat2;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final /* synthetic */ void flush() {
        e.a(this);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public long getDurationAfterProcessorApplied(long j10) {
        return getPlayoutDuration(j10);
    }

    public long getMediaDuration(long j10) {
        if (this.outputBytes < 1024) {
            return (long) (((double) this.speed) * j10);
        }
        long j11 = this.inputBytes;
        Sonic sonic = this.sonic;
        sonic.getClass();
        long pendingInputBytes = j11 - ((long) sonic.getPendingInputBytes());
        int i10 = this.outputAudioFormat.sampleRate;
        int i11 = this.inputAudioFormat.sampleRate;
        return i10 == i11 ? Util.scaleLargeTimestamp(j10, pendingInputBytes, this.outputBytes) : Util.scaleLargeTimestamp(j10, pendingInputBytes * ((long) i10), this.outputBytes * ((long) i11));
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public ByteBuffer getOutput() {
        int outputSize;
        Sonic sonic = this.sonic;
        if (sonic != null && (outputSize = sonic.getOutputSize()) > 0) {
            if (this.buffer.capacity() < outputSize) {
                this.buffer = ByteBuffer.allocateDirect(outputSize).order(ByteOrder.nativeOrder());
            } else {
                this.buffer.clear();
            }
            sonic.getOutput(this.buffer);
            this.buffer.flip();
            this.outputBytes += (long) outputSize;
            this.outputBuffer = this.buffer;
        }
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return byteBuffer;
    }

    public long getPlayoutDuration(long j10) {
        if (this.outputBytes < 1024) {
            return (long) (j10 / ((double) this.speed));
        }
        long j11 = this.inputBytes;
        Sonic sonic = this.sonic;
        sonic.getClass();
        long pendingInputBytes = j11 - ((long) sonic.getPendingInputBytes());
        int i10 = this.outputAudioFormat.sampleRate;
        int i11 = this.inputAudioFormat.sampleRate;
        return i10 == i11 ? Util.scaleLargeTimestamp(j10, this.outputBytes, pendingInputBytes) : Util.scaleLargeTimestamp(j10, this.outputBytes * ((long) i11), pendingInputBytes * ((long) i10));
    }

    public long getProcessedInputBytes() {
        long j10 = this.inputBytes;
        Sonic sonic = this.sonic;
        sonic.getClass();
        return j10 - ((long) sonic.getPendingInputBytes());
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        if (this.pendingOutputAudioFormat.sampleRate != -1) {
            return this.shouldBeActiveWithDefaultParameters || !areParametersSetToDefaultValues();
        }
        return false;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isEnded() {
        if (!this.inputEnded) {
            return false;
        }
        Sonic sonic = this.sonic;
        return sonic == null || sonic.getOutputSize() == 0;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueEndOfStream() {
        Sonic sonic = this.sonic;
        if (sonic != null) {
            sonic.queueEndOfStream();
        }
        this.inputEnded = true;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            Sonic sonic = this.sonic;
            sonic.getClass();
            this.inputBytes += (long) byteBuffer.remaining();
            sonic.queueInput(byteBuffer);
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void reset() {
        this.speed = 1.0f;
        this.pitch = 1.0f;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.outputAudioFormat = audioFormat;
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.outputBuffer = byteBuffer;
        this.pendingOutputSampleRate = -1;
        this.pendingSonicRecreation = false;
        this.sonic = null;
        this.inputBytes = 0L;
        this.outputBytes = 0L;
        this.inputEnded = false;
    }

    public void setOutputSampleRateHz(int i10) {
        ac.b.j(i10 == -1 || i10 > 0);
        this.pendingOutputSampleRate = i10;
    }

    public void setPitch(float f10) {
        ac.b.j(f10 > 0.0f);
        if (this.pitch != f10) {
            this.pitch = f10;
            this.pendingSonicRecreation = true;
        }
    }

    public void setSpeed(float f10) {
        ac.b.j(f10 > 0.0f);
        if (this.speed != f10) {
            this.speed = f10;
            this.pendingSonicRecreation = true;
        }
    }

    public SonicAudioProcessor(boolean z) {
        this.speed = 1.0f;
        this.pitch = 1.0f;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.outputAudioFormat = audioFormat;
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.outputBuffer = byteBuffer;
        this.pendingOutputSampleRate = -1;
        this.shouldBeActiveWithDefaultParameters = z;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void flush(AudioProcessor.StreamMetadata streamMetadata) {
        if (isActive()) {
            AudioProcessor.AudioFormat audioFormat = this.pendingInputAudioFormat;
            this.inputAudioFormat = audioFormat;
            AudioProcessor.AudioFormat audioFormat2 = this.pendingOutputAudioFormat;
            this.outputAudioFormat = audioFormat2;
            if (this.pendingSonicRecreation) {
                this.sonic = new Sonic(audioFormat.sampleRate, audioFormat.channelCount, this.speed, this.pitch, audioFormat2.sampleRate, audioFormat.encoding == 4);
            } else {
                Sonic sonic = this.sonic;
                if (sonic != null) {
                    sonic.flush();
                }
            }
        }
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputBytes = 0L;
        this.outputBytes = 0L;
        this.inputEnded = false;
    }
}
