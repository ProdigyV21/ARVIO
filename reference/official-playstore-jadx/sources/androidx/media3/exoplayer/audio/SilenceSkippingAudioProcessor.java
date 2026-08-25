package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class SilenceSkippingAudioProcessor extends BaseAudioProcessor {
    private static final int AVOID_TRUNCATION_FACTOR = 1000;
    public static final long DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US = 2000000;
    public static final long DEFAULT_MINIMUM_SILENCE_DURATION_US = 100000;
    public static final int DEFAULT_MIN_VOLUME_TO_KEEP_PERCENTAGE = 10;

    @Deprecated
    public static final long DEFAULT_PADDING_SILENCE_US = 20000;
    public static final float DEFAULT_SILENCE_RETENTION_RATIO = 0.2f;
    public static final short DEFAULT_SILENCE_THRESHOLD_LEVEL = 1024;
    private static final int DO_NOT_CHANGE_VOLUME = 3;
    private static final int FADE_IN = 2;
    private static final int FADE_OUT = 0;
    private static final int MUTE = 1;
    private static final int STATE_NOISY = 0;
    private static final int STATE_SHORTENING_SILENCE = 1;
    private int bytesPerFrame;
    private byte[] contiguousOutputBuffer;
    private boolean enabled;
    private final long maxSilenceToKeepDurationUs;
    private byte[] maybeSilenceBuffer;
    private int maybeSilenceBufferContentsSize;
    private int maybeSilenceBufferStartIndex;
    private final int minVolumeToKeepPercentageWhenMuting;
    private final long minimumSilenceDurationUs;
    private int outputSilenceFramesSinceNoise;
    private final float silenceRetentionRatio;
    private final short silenceThresholdLevel;
    private long skippedFrames;
    private int state;

    public SilenceSkippingAudioProcessor() {
        this(DEFAULT_MINIMUM_SILENCE_DURATION_US, 0.2f, DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US, 10, DEFAULT_SILENCE_THRESHOLD_LEVEL);
    }

    private int alignToBytePerFrameBoundary(int i10) {
        int i11 = this.bytesPerFrame;
        return (i10 / i11) * i11;
    }

    private int calculateFadeInPercentage(int i10, int i11) {
        int i12 = this.minVolumeToKeepPercentageWhenMuting;
        return ((((i10 * 1000) * (100 - i12)) / i11) / 1000) + i12;
    }

    private int calculateFadeOutPercentage(int i10, int i11) {
        return ((((i10 * 1000) / i11) * (this.minVolumeToKeepPercentageWhenMuting - 100)) / 1000) + 100;
    }

    private int calculateShortenedSilenceLength(int i10) {
        int iDurationUsToFrames = ((durationUsToFrames(this.maxSilenceToKeepDurationUs) - this.outputSilenceFramesSinceNoise) * this.bytesPerFrame) - (this.maybeSilenceBuffer.length / 2);
        ac.b.s(iDurationUsToFrames >= 0);
        return alignToBytePerFrameBoundary(Math.min((i10 * this.silenceRetentionRatio) + 0.5f, iDurationUsToFrames));
    }

    private int durationUsToFrames(long j10) {
        return (int) ((j10 * ((long) this.inputAudioFormat.sampleRate)) / 1000000);
    }

    private int findNoiseLimit(ByteBuffer byteBuffer) {
        for (int iLimit = byteBuffer.limit() - 1; iLimit >= byteBuffer.position(); iLimit -= 2) {
            if (isNoise(byteBuffer.get(iLimit), byteBuffer.get(iLimit - 1))) {
                int i10 = this.bytesPerFrame;
                return ((iLimit / i10) * i10) + i10;
            }
        }
        return byteBuffer.position();
    }

    private int findNoisePosition(ByteBuffer byteBuffer) {
        for (int iPosition = byteBuffer.position() + 1; iPosition < byteBuffer.limit(); iPosition += 2) {
            if (isNoise(byteBuffer.get(iPosition), byteBuffer.get(iPosition - 1))) {
                int i10 = this.bytesPerFrame;
                return (iPosition / i10) * i10;
            }
        }
        return byteBuffer.limit();
    }

    private boolean isNoise(byte b10, byte b11) {
        return Math.abs(twoByteSampleToInt(b10, b11)) > this.silenceThresholdLevel;
    }

    private void modifyVolume(byte[] bArr, int i10, int i11) {
        if (i11 == 3) {
            return;
        }
        for (int i12 = 0; i12 < i10; i12 += 2) {
            sampleIntToTwoBigEndianBytes(bArr, i12, (twoByteSampleToInt(bArr[i12 + 1], bArr[i12]) * (i11 == 0 ? calculateFadeOutPercentage(i12, i10 - 1) : i11 == 2 ? calculateFadeInPercentage(i12, i10 - 1) : this.minVolumeToKeepPercentageWhenMuting)) / 100);
        }
    }

    private void output(ByteBuffer byteBuffer) {
        replaceOutputBuffer(byteBuffer.remaining()).put(byteBuffer).flip();
    }

    private void outputRange(byte[] bArr, int i10, int i11) {
        ac.b.f(i10, "byteOutput size is not aligned to frame size %s", i10 % this.bytesPerFrame == 0);
        modifyVolume(bArr, i10, i11);
        replaceOutputBuffer(i10).put(bArr, 0, i10).flip();
    }

    private void outputShortenedSilenceBuffer(boolean z) {
        int length;
        int iCalculateShortenedSilenceLength;
        int i10 = this.maybeSilenceBufferContentsSize;
        byte[] bArr = this.maybeSilenceBuffer;
        if (i10 == bArr.length || z) {
            if (this.outputSilenceFramesSinceNoise == 0) {
                if (z) {
                    outputSilence(i10, 3);
                    length = i10;
                } else {
                    ac.b.s(i10 >= bArr.length / 2);
                    length = this.maybeSilenceBuffer.length / 2;
                    outputSilence(length, 0);
                }
                iCalculateShortenedSilenceLength = length;
            } else if (z) {
                int length2 = i10 - (bArr.length / 2);
                int length3 = (bArr.length / 2) + length2;
                int iCalculateShortenedSilenceLength2 = calculateShortenedSilenceLength(length2) + (this.maybeSilenceBuffer.length / 2);
                outputSilence(iCalculateShortenedSilenceLength2, 2);
                iCalculateShortenedSilenceLength = iCalculateShortenedSilenceLength2;
                length = length3;
            } else {
                length = i10 - (bArr.length / 2);
                iCalculateShortenedSilenceLength = calculateShortenedSilenceLength(length);
                outputSilence(iCalculateShortenedSilenceLength, 1);
            }
            if (!(length % this.bytesPerFrame == 0)) {
                throw new IllegalStateException(androidx.work.impl.t.y("bytesConsumed is not aligned to frame size: %s", Integer.valueOf(length)));
            }
            ac.b.s(i10 >= iCalculateShortenedSilenceLength);
            this.maybeSilenceBufferContentsSize -= length;
            int i11 = this.maybeSilenceBufferStartIndex + length;
            this.maybeSilenceBufferStartIndex = i11;
            this.maybeSilenceBufferStartIndex = i11 % this.maybeSilenceBuffer.length;
            int i12 = this.outputSilenceFramesSinceNoise;
            int i13 = this.bytesPerFrame;
            this.outputSilenceFramesSinceNoise = (iCalculateShortenedSilenceLength / i13) + i12;
            this.skippedFrames += (long) ((length - iCalculateShortenedSilenceLength) / i13);
        }
    }

    private void outputSilence(int i10, int i11) {
        if (i10 == 0) {
            return;
        }
        ac.b.j(this.maybeSilenceBufferContentsSize >= i10);
        if (i11 == 2) {
            int i12 = this.maybeSilenceBufferStartIndex;
            int i13 = this.maybeSilenceBufferContentsSize;
            int i14 = i12 + i13;
            byte[] bArr = this.maybeSilenceBuffer;
            if (i14 <= bArr.length) {
                System.arraycopy(bArr, (i12 + i13) - i10, this.contiguousOutputBuffer, 0, i10);
            } else {
                int length = i13 - (bArr.length - i12);
                if (length >= i10) {
                    System.arraycopy(bArr, length - i10, this.contiguousOutputBuffer, 0, i10);
                } else {
                    int i15 = i10 - length;
                    System.arraycopy(bArr, bArr.length - i15, this.contiguousOutputBuffer, 0, i15);
                    System.arraycopy(this.maybeSilenceBuffer, 0, this.contiguousOutputBuffer, i15, length);
                }
            }
        } else {
            int i16 = this.maybeSilenceBufferStartIndex;
            int i17 = i16 + i10;
            byte[] bArr2 = this.maybeSilenceBuffer;
            if (i17 <= bArr2.length) {
                System.arraycopy(bArr2, i16, this.contiguousOutputBuffer, 0, i10);
            } else {
                int length2 = bArr2.length - i16;
                System.arraycopy(bArr2, i16, this.contiguousOutputBuffer, 0, length2);
                System.arraycopy(this.maybeSilenceBuffer, 0, this.contiguousOutputBuffer, length2, i10 - length2);
            }
        }
        ac.b.f(i10, "sizeToOutput is not aligned to frame size: %s", i10 % this.bytesPerFrame == 0);
        ac.b.s(this.maybeSilenceBufferStartIndex < this.maybeSilenceBuffer.length);
        outputRange(this.contiguousOutputBuffer, i10, i11);
    }

    private void processNoisy(ByteBuffer byteBuffer) {
        int iLimit = byteBuffer.limit();
        byteBuffer.limit(Math.min(iLimit, byteBuffer.position() + this.maybeSilenceBuffer.length));
        int iFindNoiseLimit = findNoiseLimit(byteBuffer);
        if (iFindNoiseLimit == byteBuffer.position()) {
            this.state = 1;
        } else {
            byteBuffer.limit(Math.min(iFindNoiseLimit, byteBuffer.capacity()));
            output(byteBuffer);
        }
        byteBuffer.limit(iLimit);
    }

    private static void sampleIntToTwoBigEndianBytes(byte[] bArr, int i10, int i11) {
        if (i11 >= 32767) {
            bArr[i10] = -1;
            bArr[i10 + 1] = 127;
        } else if (i11 <= -32768) {
            bArr[i10] = 0;
            bArr[i10 + 1] = -128;
        } else {
            bArr[i10] = (byte) (i11 & 255);
            bArr[i10 + 1] = (byte) (i11 >> 8);
        }
    }

    private void shortenSilenceSilenceUntilNoise(ByteBuffer byteBuffer) {
        int length;
        int i10;
        ac.b.s(this.maybeSilenceBufferStartIndex < this.maybeSilenceBuffer.length);
        int iLimit = byteBuffer.limit();
        int iFindNoisePosition = findNoisePosition(byteBuffer);
        int iPosition = iFindNoisePosition - byteBuffer.position();
        int i11 = this.maybeSilenceBufferStartIndex;
        int i12 = this.maybeSilenceBufferContentsSize;
        int i13 = i11 + i12;
        byte[] bArr = this.maybeSilenceBuffer;
        if (i13 < bArr.length) {
            length = bArr.length - (i12 + i11);
            i10 = i11 + i12;
        } else {
            int length2 = i12 - (bArr.length - i11);
            length = i11 - length2;
            i10 = length2;
        }
        boolean z = iFindNoisePosition < iLimit;
        int iMin = Math.min(iPosition, length);
        byteBuffer.limit(byteBuffer.position() + iMin);
        byteBuffer.get(this.maybeSilenceBuffer, i10, iMin);
        int i14 = this.maybeSilenceBufferContentsSize + iMin;
        this.maybeSilenceBufferContentsSize = i14;
        ac.b.s(i14 <= this.maybeSilenceBuffer.length);
        boolean z5 = z && iPosition < length;
        outputShortenedSilenceBuffer(z5);
        if (z5) {
            this.state = 0;
            this.outputSilenceFramesSinceNoise = 0;
        }
        byteBuffer.limit(iLimit);
    }

    private static int twoByteSampleToInt(byte b10, byte b11) {
        return (b10 << 8) | (b11 & 255);
    }

    public long getSkippedFrames() {
        return this.skippedFrames;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        return super.isActive() && this.enabled;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.encoding == 2) {
            return audioFormat.sampleRate == -1 ? AudioProcessor.AudioFormat.NOT_SET : audioFormat;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        if (isActive()) {
            this.bytesPerFrame = this.inputAudioFormat.channelCount * 2;
            int iAlignToBytePerFrameBoundary = alignToBytePerFrameBoundary(durationUsToFrames(this.minimumSilenceDurationUs) / 2) * 2;
            if (this.maybeSilenceBuffer.length != iAlignToBytePerFrameBoundary) {
                this.maybeSilenceBuffer = new byte[iAlignToBytePerFrameBoundary];
                this.contiguousOutputBuffer = new byte[iAlignToBytePerFrameBoundary];
            }
        }
        this.state = 0;
        this.skippedFrames = 0L;
        this.outputSilenceFramesSinceNoise = 0;
        this.maybeSilenceBufferStartIndex = 0;
        this.maybeSilenceBufferContentsSize = 0;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        if (this.maybeSilenceBufferContentsSize > 0) {
            outputShortenedSilenceBuffer(true);
            this.outputSilenceFramesSinceNoise = 0;
        }
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.enabled = false;
        byte[] bArr = Util.EMPTY_BYTE_ARRAY;
        this.maybeSilenceBuffer = bArr;
        this.contiguousOutputBuffer = bArr;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining() && !hasPendingOutput()) {
            int i10 = this.state;
            if (i10 == 0) {
                processNoisy(byteBuffer);
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException();
                }
                shortenSilenceSilenceUntilNoise(byteBuffer);
            }
        }
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    @Deprecated
    public SilenceSkippingAudioProcessor(long j10, long j11, short s10) {
        this(j10, j11 / j10, j10, 0, s10);
    }

    private int alignToBytePerFrameBoundary(float f10) {
        return alignToBytePerFrameBoundary((int) f10);
    }

    public SilenceSkippingAudioProcessor(long j10, float f10, long j11, int i10, short s10) {
        boolean z = false;
        this.outputSilenceFramesSinceNoise = 0;
        this.maybeSilenceBufferStartIndex = 0;
        this.maybeSilenceBufferContentsSize = 0;
        if (f10 >= 0.0f && f10 <= 1.0f) {
            z = true;
        }
        ac.b.j(z);
        this.minimumSilenceDurationUs = j10;
        this.silenceRetentionRatio = f10;
        this.maxSilenceToKeepDurationUs = j11;
        this.minVolumeToKeepPercentageWhenMuting = i10;
        this.silenceThresholdLevel = s10;
        byte[] bArr = Util.EMPTY_BYTE_ARRAY;
        this.maybeSilenceBuffer = bArr;
        this.contiguousOutputBuffer = bArr;
    }
}
