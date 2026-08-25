package androidx.media3.common.audio;

import androidx.media3.common.Format;
import androidx.media3.common.util.Util;
import j$.util.Objects;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioProcessor {
    public static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    public static final class AudioFormat {
        public static final AudioFormat NOT_SET = new AudioFormat(-1, -1, -1);
        public final int bytesPerFrame;
        public final int channelCount;
        public final int encoding;
        public final int sampleRate;

        public AudioFormat(Format format) {
            this(format.sampleRate, format.channelCount, format.pcmEncoding);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AudioFormat)) {
                return false;
            }
            AudioFormat audioFormat = (AudioFormat) obj;
            return this.sampleRate == audioFormat.sampleRate && this.channelCount == audioFormat.channelCount && this.encoding == audioFormat.encoding;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.sampleRate), Integer.valueOf(this.channelCount), Integer.valueOf(this.encoding));
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("AudioFormat[sampleRate=");
            sb2.append(this.sampleRate);
            sb2.append(", channelCount=");
            sb2.append(this.channelCount);
            sb2.append(", encoding=");
            return a0.c.o(sb2, this.encoding, ']');
        }

        public AudioFormat(int i10, int i11, int i12) {
            this.sampleRate = i10;
            this.channelCount = i11;
            this.encoding = i12;
            this.bytesPerFrame = Util.isEncodingLinearPcm(i12) ? Util.getPcmFrameSize(i12, i11) : -1;
        }
    }

    public static final class StreamMetadata {
        public static final StreamMetadata DEFAULT = new StreamMetadata(0);
        public final long positionOffsetUs;

        public StreamMetadata(long j10) {
            ac.b.j(j10 >= 0);
            this.positionOffsetUs = j10;
        }
    }

    public static final class UnhandledAudioFormatException extends Exception {
        public final AudioFormat inputAudioFormat;

        public UnhandledAudioFormatException(AudioFormat audioFormat) {
            this("Unhandled input format:", audioFormat);
        }

        public UnhandledAudioFormatException(String str, AudioFormat audioFormat) {
            super(str + " " + audioFormat);
            this.inputAudioFormat = audioFormat;
        }
    }

    AudioFormat configure(AudioFormat audioFormat) throws UnhandledAudioFormatException;

    @Deprecated
    void flush();

    void flush(StreamMetadata streamMetadata);

    long getDurationAfterProcessorApplied(long j10);

    ByteBuffer getOutput();

    boolean isActive();

    boolean isEnded();

    void queueEndOfStream();

    void queueInput(ByteBuffer byteBuffer);

    void reset();
}
