package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class TrimmingAudioProcessor extends BaseAudioProcessor {
    private byte[] endBuffer = Util.EMPTY_BYTE_ARRAY;
    private int endBufferSize;
    private int pendingTrimStartBytes;
    private boolean reconfigurationPending;
    private int trimEndFrames;
    private int trimStartFrames;
    private long trimmedFrameCount;

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public long getDurationAfterProcessorApplied(long j10) {
        return Math.max(0L, j10 - Util.sampleCountToDurationUs(this.trimEndFrames + this.trimStartFrames, this.inputAudioFormat.sampleRate));
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public ByteBuffer getOutput() {
        int i10;
        if (super.isEnded() && (i10 = this.endBufferSize) > 0) {
            replaceOutputBuffer(i10).put(this.endBuffer, 0, this.endBufferSize).flip();
            this.endBufferSize = 0;
        }
        return super.getOutput();
    }

    public long getTrimmedFrameCount() {
        return this.trimmedFrameCount;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public boolean isEnded() {
        return super.isEnded() && this.endBufferSize == 0;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (!Util.isEncodingLinearPcm(audioFormat.encoding)) {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
        this.reconfigurationPending = true;
        return (this.trimStartFrames == 0 && this.trimEndFrames == 0) ? AudioProcessor.AudioFormat.NOT_SET : audioFormat;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        if (this.reconfigurationPending) {
            this.reconfigurationPending = false;
            int i10 = this.trimEndFrames;
            int i11 = this.inputAudioFormat.bytesPerFrame;
            this.endBuffer = new byte[i10 * i11];
            this.pendingTrimStartBytes = this.trimStartFrames * i11;
        }
        this.endBufferSize = 0;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        if (this.reconfigurationPending) {
            int i10 = this.endBufferSize;
            if (i10 > 0) {
                this.trimmedFrameCount += (long) (i10 / this.inputAudioFormat.bytesPerFrame);
            }
            this.endBufferSize = 0;
        }
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.endBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i10 = iLimit - iPosition;
        if (i10 == 0) {
            return;
        }
        int iMin = Math.min(i10, this.pendingTrimStartBytes);
        this.trimmedFrameCount += (long) (iMin / this.inputAudioFormat.bytesPerFrame);
        this.pendingTrimStartBytes -= iMin;
        byteBuffer.position(iPosition + iMin);
        if (this.pendingTrimStartBytes > 0) {
            return;
        }
        int i11 = i10 - iMin;
        int length = (this.endBufferSize + i11) - this.endBuffer.length;
        ByteBuffer byteBufferReplaceOutputBuffer = replaceOutputBuffer(length);
        int iConstrainValue = Util.constrainValue(length, 0, this.endBufferSize);
        byteBufferReplaceOutputBuffer.put(this.endBuffer, 0, iConstrainValue);
        int iConstrainValue2 = Util.constrainValue(length - iConstrainValue, 0, i11);
        byteBuffer.limit(byteBuffer.position() + iConstrainValue2);
        byteBufferReplaceOutputBuffer.put(byteBuffer);
        byteBuffer.limit(iLimit);
        int i12 = i11 - iConstrainValue2;
        int i13 = this.endBufferSize - iConstrainValue;
        this.endBufferSize = i13;
        byte[] bArr = this.endBuffer;
        System.arraycopy(bArr, iConstrainValue, bArr, 0, i13);
        byteBuffer.get(this.endBuffer, this.endBufferSize, i12);
        this.endBufferSize += i12;
        byteBufferReplaceOutputBuffer.flip();
    }

    public void resetTrimmedFrameCount() {
        this.trimmedFrameCount = 0L;
    }

    public void setTrimFrameCount(int i10, int i11) {
        this.trimStartFrames = i10;
        this.trimEndFrames = i11;
    }
}
