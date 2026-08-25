package android.graphics;

import android.content.GifUtils;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder$OnHeaderDecodedListener;
import android.graphics.drawable.Drawable;
import android.util.Size;
import android.view.Sizes;
import androidx.media3.extractor.mp4.b;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/drawable/Drawable;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class ImageDecoderDecoder$decode$drawable$1 extends r implements a<Drawable> {
    final /* synthetic */ f0 $isSampled;
    final /* synthetic */ ImageDecoderDecoder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageDecoderDecoder$decode$drawable$1(ImageDecoderDecoder imageDecoderDecoder, f0 f0Var) {
        super(0);
        this.this$0 = imageDecoderDecoder;
        this.$isSampled = f0Var;
    }

    @Override // r7.a
    public final Drawable invoke() throws IOException {
        final k0 k0Var = new k0();
        ImageDecoderDecoder imageDecoderDecoder = this.this$0;
        ImageSource imageSourceWrapImageSource = imageDecoderDecoder.wrapImageSource(imageDecoderDecoder.source);
        try {
            ImageDecoder.Source imageDecoderSource = this.this$0.toImageDecoderSource(imageSourceWrapImageSource);
            final ImageDecoderDecoder imageDecoderDecoder2 = this.this$0;
            final f0 f0Var = this.$isSampled;
            return ImageDecoder.decodeDrawable(imageDecoderSource, new ImageDecoder$OnHeaderDecodedListener() { // from class: coil.decode.ImageDecoderDecoder$decode$drawable$1$invoke$$inlined$decodeDrawable$1
                public final void onHeaderDecoded(android.graphics.ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                    k0Var.f19746i = imageDecoder;
                    Size size = imageInfo.getSize();
                    int width = size.getWidth();
                    int height = size.getHeight();
                    android.view.Size size2 = imageDecoderDecoder2.options.getSize();
                    int px = Sizes.isOriginal(size2) ? width : GifUtils.toPx(size2.getWidth(), imageDecoderDecoder2.options.getScale());
                    android.view.Size size3 = imageDecoderDecoder2.options.getSize();
                    int px2 = Sizes.isOriginal(size3) ? height : GifUtils.toPx(size3.getHeight(), imageDecoderDecoder2.options.getScale());
                    if (width > 0 && height > 0 && (width != px || height != px2)) {
                        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(width, height, px, px2, imageDecoderDecoder2.options.getScale());
                        f0 f0Var2 = f0Var;
                        boolean z = dComputeSizeMultiplier < 1.0d;
                        f0Var2.f19738i = z;
                        if (z || !imageDecoderDecoder2.options.getAllowInexactSize()) {
                            imageDecoder.setTargetSize(t7.a.L(((double) width) * dComputeSizeMultiplier), t7.a.L(dComputeSizeMultiplier * ((double) height)));
                        }
                    }
                    imageDecoderDecoder2.configureImageDecoderProperties(imageDecoder);
                }
            });
        } finally {
            ImageDecoder imageDecoderD = b.d(k0Var.f19746i);
            if (imageDecoderD != null) {
                imageDecoderD.close();
            }
            imageSourceWrapImageSource.close();
        }
    }
}
