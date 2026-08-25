package androidx.media3.common.audio;

import android.util.SparseArray;
import androidx.media3.common.audio.AudioProcessor;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class ChannelMixingAudioProcessor extends BaseAudioProcessor {
    private final SparseArray<ChannelMixingMatrix> matrixByInputChannelCount = new SparseArray<>();

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (!AudioMixingUtil.canMix(audioFormat)) {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
        ChannelMixingMatrix channelMixingMatrix = this.matrixByInputChannelCount.get(audioFormat.channelCount);
        if (channelMixingMatrix != null) {
            return channelMixingMatrix.isIdentity() ? AudioProcessor.AudioFormat.NOT_SET : new AudioProcessor.AudioFormat(audioFormat.sampleRate, channelMixingMatrix.getOutputChannelCount(), audioFormat.encoding);
        }
        throw new AudioProcessor.UnhandledAudioFormatException("No mixing matrix for input channel count", audioFormat);
    }

    public void putChannelMixingMatrix(ChannelMixingMatrix channelMixingMatrix) {
        this.matrixByInputChannelCount.put(channelMixingMatrix.getInputChannelCount(), channelMixingMatrix);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        ChannelMixingMatrix channelMixingMatrix = this.matrixByInputChannelCount.get(this.inputAudioFormat.channelCount);
        channelMixingMatrix.getClass();
        int iRemaining = byteBuffer.remaining() / this.inputAudioFormat.bytesPerFrame;
        ByteBuffer byteBufferReplaceOutputBuffer = replaceOutputBuffer(this.outputAudioFormat.bytesPerFrame * iRemaining);
        AudioMixingUtil.mix(byteBuffer, this.inputAudioFormat, byteBufferReplaceOutputBuffer, this.outputAudioFormat, channelMixingMatrix, iRemaining, false, true);
        byteBufferReplaceOutputBuffer.flip();
    }
}
