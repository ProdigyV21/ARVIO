package androidx.media3.exoplayer.audio;

import android.util.SparseArray;
import androidx.media3.common.audio.AudioMixingUtil;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.ChannelMixingMatrix;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.TeeAudioProcessor;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class WaveformAudioBufferSink implements TeeAudioProcessor.AudioBufferSink {
    private final int barsPerSecond;
    private ChannelMixingMatrix channelMixingMatrix;
    private AudioProcessor.AudioFormat inputAudioFormat;
    private final Listener listener;
    private AudioProcessor.AudioFormat mixingAudioFormat;
    private final ByteBuffer mixingBuffer;
    private final SparseArray<WaveformBar> outputChannels;
    private int samplesPerBar;

    public interface Listener {
        void onNewWaveformBar(int i10, WaveformBar waveformBar);
    }

    public static class WaveformBar {
        private int sampleCount;
        private double squareSum;
        private float minSampleValue = 1.0f;
        private float maxSampleValue = -1.0f;

        public void addSample(float f10) {
            ac.b.j(f10 >= -1.0f && f10 <= 1.0f);
            this.minSampleValue = Math.min(this.minSampleValue, f10);
            this.maxSampleValue = Math.max(this.maxSampleValue, f10);
            double d4 = f10;
            this.squareSum = (d4 * d4) + this.squareSum;
            this.sampleCount++;
        }

        public double getMaxSampleValue() {
            return this.maxSampleValue;
        }

        public double getMinSampleValue() {
            return this.minSampleValue;
        }

        public double getRootMeanSquare() {
            return Math.sqrt(this.squareSum / ((double) this.sampleCount));
        }

        public int getSampleCount() {
            return this.sampleCount;
        }
    }

    public WaveformAudioBufferSink(int i10, int i11, Listener listener) {
        this.barsPerSecond = i10;
        this.listener = listener;
        this.mixingBuffer = ByteBuffer.allocate(Util.getPcmFrameSize(4, i11));
        this.outputChannels = new SparseArray<>(i11);
        for (int i12 = 0; i12 < i11; i12++) {
            this.outputChannels.append(i12, new WaveformBar());
        }
    }

    @Override // androidx.media3.exoplayer.audio.TeeAudioProcessor.AudioBufferSink
    public void flush(int i10, int i11, int i12) {
        this.samplesPerBar = i10 / this.barsPerSecond;
        this.inputAudioFormat = new AudioProcessor.AudioFormat(i10, i11, i12);
        this.mixingAudioFormat = new AudioProcessor.AudioFormat(i10, this.outputChannels.size(), 4);
        this.channelMixingMatrix = ChannelMixingMatrix.createForConstantGain(i11, this.outputChannels.size());
    }

    @Override // androidx.media3.exoplayer.audio.TeeAudioProcessor.AudioBufferSink
    public void handleBuffer(ByteBuffer byteBuffer) {
        this.inputAudioFormat.getClass();
        this.mixingAudioFormat.getClass();
        this.channelMixingMatrix.getClass();
        while (byteBuffer.hasRemaining()) {
            this.mixingBuffer.rewind();
            ByteBuffer byteBuffer2 = byteBuffer;
            AudioMixingUtil.mix(byteBuffer2, this.inputAudioFormat, this.mixingBuffer, this.mixingAudioFormat, this.channelMixingMatrix, 1, false, true);
            this.mixingBuffer.rewind();
            for (int i10 = 0; i10 < this.outputChannels.size(); i10++) {
                WaveformBar waveformBar = this.outputChannels.get(i10);
                waveformBar.addSample(this.mixingBuffer.getFloat());
                if (waveformBar.getSampleCount() >= this.samplesPerBar) {
                    this.listener.onNewWaveformBar(i10, waveformBar);
                    this.outputChannels.put(i10, new WaveformBar());
                }
            }
            byteBuffer = byteBuffer2;
        }
    }
}
