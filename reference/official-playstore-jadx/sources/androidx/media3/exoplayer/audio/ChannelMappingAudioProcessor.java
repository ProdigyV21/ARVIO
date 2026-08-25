package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class ChannelMappingAudioProcessor extends BaseAudioProcessor {
    private int[] outputChannels;
    private int[] pendingOutputChannels;

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int[] iArr = this.pendingOutputChannels;
        if (iArr == null) {
            return AudioProcessor.AudioFormat.NOT_SET;
        }
        if (!Util.isEncodingLinearPcm(audioFormat.encoding)) {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
        boolean z = audioFormat.channelCount != iArr.length;
        int i10 = 0;
        while (i10 < iArr.length) {
            int i11 = iArr[i10];
            if (i11 >= audioFormat.channelCount) {
                throw new AudioProcessor.UnhandledAudioFormatException("Channel map (" + Arrays.toString(iArr) + ") trying to access non-existent input channel.", audioFormat);
            }
            z |= i11 != i10;
            i10++;
        }
        return z ? new AudioProcessor.AudioFormat(audioFormat.sampleRate, iArr.length, audioFormat.encoding) : AudioProcessor.AudioFormat.NOT_SET;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        this.outputChannels = this.pendingOutputChannels;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.outputChannels = null;
        this.pendingOutputChannels = null;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int[] iArr = this.outputChannels;
        iArr.getClass();
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        ByteBuffer byteBufferReplaceOutputBuffer = replaceOutputBuffer(((iLimit - iPosition) / this.inputAudioFormat.bytesPerFrame) * this.outputAudioFormat.bytesPerFrame);
        while (iPosition < iLimit) {
            for (int i10 : iArr) {
                int byteDepth = (Util.getByteDepth(this.inputAudioFormat.encoding) * i10) + iPosition;
                int i11 = this.inputAudioFormat.encoding;
                if (i11 == 2) {
                    byteBufferReplaceOutputBuffer.putShort(byteBuffer.getShort(byteDepth));
                } else if (i11 == 3) {
                    byteBufferReplaceOutputBuffer.put(byteBuffer.get(byteDepth));
                } else if (i11 == 4) {
                    byteBufferReplaceOutputBuffer.putFloat(byteBuffer.getFloat(byteDepth));
                } else if (i11 == 21) {
                    Util.putInt24(byteBufferReplaceOutputBuffer, Util.getInt24(byteBuffer, byteDepth));
                } else {
                    if (i11 != 22) {
                        if (i11 != 268435456) {
                            if (i11 != 1342177280) {
                                if (i11 != 1610612736) {
                                    throw new IllegalStateException("Unexpected encoding: " + this.inputAudioFormat.encoding);
                                }
                            }
                            Util.putInt24(byteBufferReplaceOutputBuffer, Util.getInt24(byteBuffer, byteDepth));
                        }
                        byteBufferReplaceOutputBuffer.putShort(byteBuffer.getShort(byteDepth));
                    }
                    byteBufferReplaceOutputBuffer.putInt(byteBuffer.getInt(byteDepth));
                }
            }
            iPosition += this.inputAudioFormat.bytesPerFrame;
        }
        byteBuffer.position(iLimit);
        byteBufferReplaceOutputBuffer.flip();
    }

    public void setChannelMap(int[] iArr) {
        this.pendingOutputChannels = iArr;
    }
}
