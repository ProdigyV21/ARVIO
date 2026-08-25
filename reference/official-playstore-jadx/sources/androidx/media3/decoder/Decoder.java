package androidx.media3.decoder;

import androidx.media3.decoder.DecoderException;

/* JADX INFO: loaded from: classes3.dex */
public interface Decoder<I, O, E extends DecoderException> {
    I dequeueInputBuffer() throws DecoderException;

    O dequeueOutputBuffer() throws DecoderException;

    void flush();

    String getName();

    void queueInputBuffer(I i10) throws DecoderException;

    void release();

    void setOutputStartTimeUs(long j10);
}
