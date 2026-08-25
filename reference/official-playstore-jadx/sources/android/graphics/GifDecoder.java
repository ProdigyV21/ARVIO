package android.graphics;

import android.content.GifUtils;
import android.graphics.Bitmap;
import android.graphics.Decoder;
import android.graphics.Movie;
import coil.ImageLoader;
import coil.drawable.MovieDrawable;
import coil.fetch.SourceResult;
import coil.request.Gifs;
import coil.request.Options;
import d7.d;
import java.io.IOException;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.a;
import x6.t0;
import xb.i0;
import xb.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00102\u00020\u0001:\u0002\u0010\u0011B#\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lcoil/decode/GifDecoder;", "Lcoil/decode/Decoder;", "Lcoil/decode/ImageSource;", "source", "Lcoil/request/Options;", "options", "", "enforceMinimumFrameDelay", "<init>", "(Lcoil/decode/ImageSource;Lcoil/request/Options;Z)V", "Lcoil/decode/DecodeResult;", "decode", "(Ld7/d;)Ljava/lang/Object;", "Lcoil/decode/ImageSource;", "Lcoil/request/Options;", "Z", "Companion", "Factory", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GifDecoder implements Decoder {
    public static final String ANIMATED_TRANSFORMATION_KEY = "coil#animated_transformation";
    public static final String ANIMATION_END_CALLBACK_KEY = "coil#animation_end_callback";
    public static final String ANIMATION_START_CALLBACK_KEY = "coil#animation_start_callback";
    public static final String REPEAT_COUNT_KEY = "coil#repeat_count";
    private final boolean enforceMinimumFrameDelay;
    private final Options options;
    private final ImageSource source;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcoil/decode/GifDecoder$Factory;", "Lcoil/decode/Decoder$Factory;", "enforceMinimumFrameDelay", "", "(Z)V", "create", "Lcoil/decode/Decoder;", "result", "Lcoil/fetch/SourceResult;", "options", "Lcoil/request/Options;", "imageLoader", "Lcoil/ImageLoader;", "equals", "other", "", "hashCode", "", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Decoder.Factory {
        private final boolean enforceMinimumFrameDelay;

        public Factory() {
            this(false, 1, null);
        }

        @Override // coil.decode.Decoder.Factory
        public Decoder create(SourceResult result, Options options, ImageLoader imageLoader) {
            if (GifDecodeUtils.isGif(DecodeUtils.INSTANCE, result.getSource().source())) {
                return new GifDecoder(result.getSource(), options, this.enforceMinimumFrameDelay);
            }
            return null;
        }

        public boolean equals(Object other) {
            return other instanceof Factory;
        }

        public int hashCode() {
            return Factory.class.hashCode();
        }

        public Factory(boolean z) {
            this.enforceMinimumFrameDelay = z;
        }

        public /* synthetic */ Factory(boolean z, int i10, h hVar) {
            this((i10 & 1) != 0 ? true : z);
        }
    }

    /* JADX INFO: renamed from: coil.decode.GifDecoder$decode$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcoil/decode/DecodeResult;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<DecodeResult> {
        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final DecodeResult invoke() throws IOException {
            l i0Var = GifDecoder.this.enforceMinimumFrameDelay ? new i0(new FrameDelayRewritingSource(GifDecoder.this.source.source())) : GifDecoder.this.source.source();
            try {
                Movie movieDecodeStream = Movie.decodeStream(i0Var.inputStream());
                i0Var.close();
                if (movieDecodeStream == null || movieDecodeStream.width() <= 0 || movieDecodeStream.height() <= 0) {
                    throw new IllegalStateException("Failed to decode GIF.");
                }
                MovieDrawable movieDrawable = new MovieDrawable(movieDecodeStream, (movieDecodeStream.isOpaque() && GifDecoder.this.options.getAllowRgb565()) ? Bitmap.Config.RGB_565 : GifUtils.isHardware(GifDecoder.this.options.getConfig()) ? Bitmap.Config.ARGB_8888 : GifDecoder.this.options.getConfig(), GifDecoder.this.options.getScale());
                Integer numRepeatCount = Gifs.repeatCount(GifDecoder.this.options.getParameters());
                movieDrawable.setRepeatCount(numRepeatCount != null ? numRepeatCount.intValue() : -1);
                a<t0> aVarAnimationStartCallback = Gifs.animationStartCallback(GifDecoder.this.options.getParameters());
                a<t0> aVarAnimationEndCallback = Gifs.animationEndCallback(GifDecoder.this.options.getParameters());
                if (aVarAnimationStartCallback != null || aVarAnimationEndCallback != null) {
                    movieDrawable.registerAnimationCallback(GifUtils.animatable2CompatCallbackOf(aVarAnimationStartCallback, aVarAnimationEndCallback));
                }
                movieDrawable.setAnimatedTransformation(Gifs.animatedTransformation(GifDecoder.this.options.getParameters()));
                return new DecodeResult(movieDrawable, false);
            } finally {
            }
        }
    }

    public GifDecoder(ImageSource imageSource, Options options) {
        this(imageSource, options, false, 4, null);
    }

    @Override // android.graphics.Decoder
    public Object decode(d<? super DecodeResult> dVar) {
        return m0.u(new AnonymousClass2(), dVar);
    }

    public GifDecoder(ImageSource imageSource, Options options, boolean z) {
        this.source = imageSource;
        this.options = options;
        this.enforceMinimumFrameDelay = z;
    }

    public /* synthetic */ GifDecoder(ImageSource imageSource, Options options, boolean z, int i10, h hVar) {
        this(imageSource, options, (i10 & 4) != 0 ? true : z);
    }
}
