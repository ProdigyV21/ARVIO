package androidx.media3.common.audio;

import androidx.compose.ui.layout.LayoutKt;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioMixingUtil {
    private static final float FLOAT_PCM_MAX_VALUE = 1.0f;
    private static final float FLOAT_PCM_MIN_VALUE = -1.0f;

    private AudioMixingUtil() {
    }

    public static boolean canMix(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.sampleRate == -1 || audioFormat.channelCount == -1) {
            return false;
        }
        int i10 = audioFormat.encoding;
        return i10 == 2 || i10 == 4;
    }

    private static float floatSampleToInt16Pcm(float f10) {
        return Util.constrainValue(f10 * (f10 < 0.0f ? 32768 : LayoutKt.LargeDimension), -32768.0f, 32767.0f);
    }

    private static float getPcmSample(ByteBuffer byteBuffer, boolean z, boolean z5) {
        return z5 ? z ? byteBuffer.getShort() : floatSampleToInt16Pcm(byteBuffer.getFloat()) : z ? int16SampleToFloatPcm(byteBuffer.getShort()) : byteBuffer.getFloat();
    }

    private static float int16SampleToFloatPcm(short s10) {
        return s10 / (s10 < 0 ? 32768 : LayoutKt.LargeDimension);
    }

    public static ByteBuffer mix(ByteBuffer byteBuffer, AudioProcessor.AudioFormat audioFormat, ByteBuffer byteBuffer2, AudioProcessor.AudioFormat audioFormat2, ChannelMixingMatrix channelMixingMatrix, int i10, boolean z, boolean z5) {
        boolean z10 = audioFormat.encoding == 2;
        boolean z11 = audioFormat2.encoding == 2;
        int inputChannelCount = channelMixingMatrix.getInputChannelCount();
        int outputChannelCount = channelMixingMatrix.getOutputChannelCount();
        float[] fArr = new float[inputChannelCount];
        float[] fArr2 = new float[outputChannelCount];
        for (int i11 = 0; i11 < i10; i11++) {
            if (z) {
                int iPosition = byteBuffer2.position();
                for (int i12 = 0; i12 < outputChannelCount; i12++) {
                    fArr2[i12] = getPcmSample(byteBuffer2, z11, z11);
                }
                byteBuffer2.position(iPosition);
            }
            for (int i13 = 0; i13 < inputChannelCount; i13++) {
                fArr[i13] = getPcmSample(byteBuffer, z10, z11);
            }
            for (int i14 = 0; i14 < outputChannelCount; i14++) {
                for (int i15 = 0; i15 < inputChannelCount; i15++) {
                    fArr2[i14] = (channelMixingMatrix.getMixingCoefficient(i15, i14) * fArr[i15]) + fArr2[i14];
                }
                if (z11) {
                    byteBuffer2.putShort((short) Util.constrainValue(fArr2[i14], -32768.0f, 32767.0f));
                } else {
                    byteBuffer2.putFloat(z5 ? Util.constrainValue(fArr2[i14], FLOAT_PCM_MIN_VALUE, 1.0f) : fArr2[i14]);
                }
                fArr2[i14] = 0.0f;
            }
        }
        return byteBuffer2;
    }

    public static boolean canMix(AudioProcessor.AudioFormat audioFormat, AudioProcessor.AudioFormat audioFormat2) {
        return audioFormat.sampleRate == audioFormat2.sampleRate && canMix(audioFormat) && canMix(audioFormat2);
    }
}
