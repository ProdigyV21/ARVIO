package androidx.media3.exoplayer.image;

import ac.b;
import android.content.Context;
import android.graphics.Point;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.BitmapUtil;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.exoplayer.image.ImageDecoder;
import androidx.media3.exoplayer.t0;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class BitmapFactoryImageDecoder extends SimpleDecoder<DecoderInputBuffer, ImageOutputBuffer, ImageDecoderException> implements ImageDecoder {
    private final Context context;
    private final int maxOutputSize;

    public static final class Factory implements ImageDecoder.Factory {
        private final Context context;
        private int maxOutputSize;

        @Deprecated
        public Factory() {
            this.context = null;
            this.maxOutputSize = -1;
        }

        public Factory setMaxOutputSize(int i10) {
            b.j(i10 == -1 || i10 > 0);
            this.maxOutputSize = i10;
            return this;
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public int supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return (str == null || !MimeTypes.isImage(str)) ? t0.c(0) : Util.isBitmapFactorySupportedMimeType(format.sampleMimeType) ? t0.c(4) : t0.c(1);
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public BitmapFactoryImageDecoder createImageDecoder() {
            return new BitmapFactoryImageDecoder(this.context, this.maxOutputSize);
        }

        public Factory(Context context) {
            context.getClass();
            this.context = context;
            this.maxOutputSize = -1;
        }
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(1);
    }

    @Override // androidx.media3.decoder.SimpleDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ ImageOutputBuffer dequeueOutputBuffer() throws ImageDecoderException {
        return dequeueOutputBuffer();
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "BitmapFactoryImageDecoder";
    }

    private BitmapFactoryImageDecoder(Context context, int i10) {
        super(new DecoderInputBuffer[1], new ImageOutputBuffer[1]);
        this.context = context;
        this.maxOutputSize = i10;
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageOutputBuffer createOutputBuffer() {
        return new ImageOutputBuffer() { // from class: androidx.media3.exoplayer.image.BitmapFactoryImageDecoder.1
            @Override // androidx.media3.decoder.DecoderOutputBuffer
            public void release() {
                BitmapFactoryImageDecoder.this.releaseOutputBuffer(this);
            }
        };
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageDecoderException createUnexpectedDecodeException(Throwable th) {
        return new ImageDecoderException("Unexpected decode error", th);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageDecoderException decode(DecoderInputBuffer decoderInputBuffer, ImageOutputBuffer imageOutputBuffer, boolean z) {
        ByteBuffer byteBuffer = decoderInputBuffer.data;
        byteBuffer.getClass();
        b.s(byteBuffer.hasArray());
        b.j(byteBuffer.arrayOffset() == 0);
        try {
            int iMax = this.maxOutputSize;
            if (iMax == -1) {
                Context context = this.context;
                if (context != null) {
                    Point currentDisplayModeSize = Util.getCurrentDisplayModeSize(context);
                    int i10 = currentDisplayModeSize.x;
                    int i11 = currentDisplayModeSize.y;
                    Format format = decoderInputBuffer.format;
                    if (format != null) {
                        int i12 = format.tileCountHorizontal;
                        if (i12 != -1) {
                            i10 *= i12;
                        }
                        int i13 = format.tileCountVertical;
                        if (i13 != -1) {
                            i11 *= i13;
                        }
                    }
                    iMax = (Math.max(i10, i11) * 2) - 1;
                } else {
                    iMax = 4096;
                }
            }
            imageOutputBuffer.bitmap = BitmapUtil.decode(byteBuffer.array(), byteBuffer.remaining(), null, iMax);
            imageOutputBuffer.timeUs = decoderInputBuffer.timeUs;
            return null;
        } catch (ParserException e5) {
            return new ImageDecoderException("Could not decode image data with BitmapFactory.", e5);
        } catch (IOException e6) {
            return new ImageDecoderException(e6);
        }
    }
}
