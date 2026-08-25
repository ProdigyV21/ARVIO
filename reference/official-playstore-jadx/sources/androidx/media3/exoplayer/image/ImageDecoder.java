package androidx.media3.exoplayer.image;

import androidx.media3.common.Format;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.image.BitmapFactoryImageDecoder;

/* JADX INFO: loaded from: classes3.dex */
public interface ImageDecoder extends Decoder<DecoderInputBuffer, ImageOutputBuffer, ImageDecoderException> {

    public interface Factory {

        @Deprecated
        public static final Factory DEFAULT = new BitmapFactoryImageDecoder.Factory();

        ImageDecoder createImageDecoder();

        int supportsFormat(Format format);
    }

    @Override // androidx.media3.decoder.Decoder
    ImageOutputBuffer dequeueOutputBuffer() throws ImageDecoderException;

    void queueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ImageDecoderException;
}
