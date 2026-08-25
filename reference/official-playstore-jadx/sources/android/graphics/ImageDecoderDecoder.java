package android.graphics;

import android.content.GifUtils;
import android.graphics.Decoder;
import android.graphics.ImageDecoder;
import android.graphics.ImageSource;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.media3.extractor.mp4.b;
import coil.ImageLoader;
import coil.fetch.SourceResult;
import coil.request.Gifs;
import coil.request.Options;
import coil.transform.AnimatedTransformation;
import com.arflix.tv.util.Constants;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import java.nio.ByteBuffer;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.a;
import r7.p;
import x6.t0;
import xb.f0;
import xb.i0;
import xb.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001dB#\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\f*\u00020\u0002H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0011\u001a\u00020\u0010*\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0018\u001a\u00020\u0017H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001bR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lcoil/decode/ImageDecoderDecoder;", "Lcoil/decode/Decoder;", "Lcoil/decode/ImageSource;", "source", "Lcoil/request/Options;", "options", "", "enforceMinimumFrameDelay", "<init>", "(Lcoil/decode/ImageSource;Lcoil/request/Options;Z)V", "wrapImageSource", "(Lcoil/decode/ImageSource;)Lcoil/decode/ImageSource;", "Landroid/graphics/ImageDecoder$Source;", "toImageDecoderSource", "(Lcoil/decode/ImageSource;)Landroid/graphics/ImageDecoder$Source;", "Landroid/graphics/ImageDecoder;", "Lx6/t0;", "configureImageDecoderProperties", "(Landroid/graphics/ImageDecoder;)V", "Landroid/graphics/drawable/Drawable;", "baseDrawable", "wrapDrawable", "(Landroid/graphics/drawable/Drawable;Ld7/d;)Ljava/lang/Object;", "Lcoil/decode/DecodeResult;", "decode", "(Ld7/d;)Ljava/lang/Object;", "Lcoil/decode/ImageSource;", "Lcoil/request/Options;", "Z", "Factory", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ImageDecoderDecoder implements Decoder {
    private final boolean enforceMinimumFrameDelay;
    private final Options options;
    private final ImageSource source;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ)\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcoil/decode/ImageDecoderDecoder$Factory;", "Lcoil/decode/Decoder$Factory;", "", "enforceMinimumFrameDelay", "<init>", "(Z)V", "Lxb/l;", "source", "isApplicable", "(Lxb/l;)Z", "Lcoil/fetch/SourceResult;", "result", "Lcoil/request/Options;", "options", "Lcoil/ImageLoader;", "imageLoader", "Lcoil/decode/Decoder;", "create", "(Lcoil/fetch/SourceResult;Lcoil/request/Options;Lcoil/ImageLoader;)Lcoil/decode/Decoder;", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Z", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Decoder.Factory {
        private final boolean enforceMinimumFrameDelay;

        public Factory() {
            this(false, 1, null);
        }

        private final boolean isApplicable(l source) {
            DecodeUtils decodeUtils = DecodeUtils.INSTANCE;
            if (GifDecodeUtils.isGif(decodeUtils, source) || GifDecodeUtils.isAnimatedWebP(decodeUtils, source)) {
                return true;
            }
            return Build.VERSION.SDK_INT >= 30 && GifDecodeUtils.isAnimatedHeif(decodeUtils, source);
        }

        @Override // coil.decode.Decoder.Factory
        public Decoder create(SourceResult result, Options options, ImageLoader imageLoader) {
            if (isApplicable(result.getSource().source())) {
                return new ImageDecoderDecoder(result.getSource(), options, this.enforceMinimumFrameDelay);
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

    /* JADX INFO: renamed from: coil.decode.ImageDecoderDecoder$decode$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.decode.ImageDecoderDecoder", f = "ImageDecoderDecoder.kt", l = {50, Constants.WATCHED_THRESHOLD}, m = "decode")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ImageDecoderDecoder.this.decode(this);
        }
    }

    /* JADX INFO: renamed from: coil.decode.ImageDecoderDecoder$wrapDrawable$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.decode.ImageDecoderDecoder", f = "ImageDecoderDecoder.kt", l = {158}, m = "wrapDrawable")
    public static final class C06441 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06441(d<? super C06441> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ImageDecoderDecoder.this.wrapDrawable(null, this);
        }
    }

    /* JADX INFO: renamed from: coil.decode.ImageDecoderDecoder$wrapDrawable$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "coil.decode.ImageDecoderDecoder$wrapDrawable$2", f = "ImageDecoderDecoder.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ Drawable $baseDrawable;
        final /* synthetic */ a<t0> $onEnd;
        final /* synthetic */ a<t0> $onStart;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Drawable drawable, a<t0> aVar, a<t0> aVar2, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$baseDrawable = drawable;
            this.$onStart = aVar;
            this.$onEnd = aVar2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$baseDrawable, this.$onStart, this.$onEnd, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            b.e(this.$baseDrawable).registerAnimationCallback(GifUtils.animatable2CallbackOf(this.$onStart, this.$onEnd));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public ImageDecoderDecoder(ImageSource imageSource, Options options) {
        this(imageSource, options, false, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void configureImageDecoderProperties(ImageDecoder imageDecoder) {
        imageDecoder.setAllocator(GifUtils.isHardware(this.options.getConfig()) ? 3 : 1);
        imageDecoder.setMemorySizePolicy(!this.options.getAllowRgb565() ? 1 : 0);
        if (this.options.getColorSpace() != null) {
            imageDecoder.setTargetColorSpace(this.options.getColorSpace());
        }
        imageDecoder.setUnpremultipliedRequired(!this.options.getPremultipliedAlpha());
        AnimatedTransformation animatedTransformation = Gifs.animatedTransformation(this.options.getParameters());
        imageDecoder.setPostProcessor(animatedTransformation != null ? GifUtils.asPostProcessor(animatedTransformation) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageDecoder.Source toImageDecoderSource(ImageSource imageSource) {
        f0 f0VarFileOrNull = imageSource.fileOrNull();
        if (f0VarFileOrNull != null) {
            return ImageDecoder.createSource(f0VarFileOrNull.toFile());
        }
        ImageSource.Metadata metadata = imageSource.getMetadata();
        if (metadata instanceof AssetMetadata) {
            return ImageDecoder.createSource(this.options.getContext().getAssets(), ((AssetMetadata) metadata).getFilePath());
        }
        if (metadata instanceof ContentMetadata) {
            return ImageDecoder.createSource(this.options.getContext().getContentResolver(), ((ContentMetadata) metadata).getUri());
        }
        if (metadata instanceof ResourceMetadata) {
            ResourceMetadata resourceMetadata = (ResourceMetadata) metadata;
            if (kotlin.jvm.internal.p.a(resourceMetadata.getPackageName(), this.options.getContext().getPackageName())) {
                return ImageDecoder.createSource(this.options.getContext().getResources(), resourceMetadata.getResId());
            }
        }
        int i10 = Build.VERSION.SDK_INT;
        return i10 >= 31 ? ImageDecoder.createSource(imageSource.source().i0()) : i10 == 30 ? ImageDecoder.createSource(ByteBuffer.wrap(imageSource.source().i0())) : ImageDecoder.createSource(imageSource.file().toFile());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object wrapDrawable(android.graphics.drawable.Drawable r7, d7.d<? super android.graphics.drawable.Drawable> r8) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r8 instanceof android.graphics.ImageDecoderDecoder.C06441
            if (r0 == 0) goto L13
            r0 = r8
            coil.decode.ImageDecoderDecoder$wrapDrawable$1 r0 = (android.graphics.ImageDecoderDecoder.C06441) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.decode.ImageDecoderDecoder$wrapDrawable$1 r0 = new coil.decode.ImageDecoderDecoder$wrapDrawable$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r7 = r0.L$1
            android.graphics.drawable.Drawable r7 = (android.graphics.drawable.Drawable) r7
            java.lang.Object r0 = r0.L$0
            coil.decode.ImageDecoderDecoder r0 = (android.graphics.ImageDecoderDecoder) r0
            k2.c.G(r8)
            goto L8e
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L35:
            k2.c.G(r8)
            boolean r8 = r7 instanceof android.graphics.drawable.AnimatedImageDrawable
            if (r8 != 0) goto L3d
            return r7
        L3d:
            android.graphics.drawable.AnimatedImageDrawable r8 = androidx.media3.extractor.mp4.b.e(r7)
            coil.request.Options r1 = r6.options
            coil.request.Parameters r1 = r1.getParameters()
            java.lang.Integer r1 = coil.request.Gifs.repeatCount(r1)
            if (r1 == 0) goto L52
            int r1 = r1.intValue()
            goto L53
        L52:
            r1 = -1
        L53:
            r8.setRepeatCount(r1)
            coil.request.Options r8 = r6.options
            coil.request.Parameters r8 = r8.getParameters()
            r7.a r8 = coil.request.Gifs.animationStartCallback(r8)
            coil.request.Options r1 = r6.options
            coil.request.Parameters r1 = r1.getParameters()
            r7.a r1 = coil.request.Gifs.animationEndCallback(r1)
            if (r8 != 0) goto L71
            if (r1 == 0) goto L6f
            goto L71
        L6f:
            r0 = r6
            goto L8e
        L71:
            ka.x0 r3 = ka.x0.f19652a
            ka.h2 r3 = pa.q.f21294a
            la.d r3 = (la.d) r3
            la.d r3 = r3.f20077n
            coil.decode.ImageDecoderDecoder$wrapDrawable$2 r4 = new coil.decode.ImageDecoderDecoder$wrapDrawable$2
            r5 = 0
            r4.<init>(r7, r8, r1, r5)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r8 = ka.m0.y(r3, r4, r0)
            e7.a r0 = e7.a.f15033i
            if (r8 != r0) goto L6f
            return r0
        L8e:
            coil.drawable.ScaleDrawable r8 = new coil.drawable.ScaleDrawable
            coil.request.Options r0 = r0.options
            coil.size.Scale r0 = r0.getScale()
            r8.<init>(r7, r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.graphics.ImageDecoderDecoder.wrapDrawable(android.graphics.drawable.Drawable, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageSource wrapImageSource(ImageSource source) {
        return (this.enforceMinimumFrameDelay && GifDecodeUtils.isGif(DecodeUtils.INSTANCE, source.source())) ? ImageSources.create(new i0(new FrameDelayRewritingSource(source.source())), this.options.getContext()) : source;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // android.graphics.Decoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object decode(d7.d<? super android.graphics.DecodeResult> r7) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r7 instanceof android.graphics.ImageDecoderDecoder.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            coil.decode.ImageDecoderDecoder$decode$1 r0 = (android.graphics.ImageDecoderDecoder.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.decode.ImageDecoderDecoder$decode$1 r0 = new coil.decode.ImageDecoderDecoder$decode$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.f0 r0 = (kotlin.jvm.internal.f0) r0
            k2.c.G(r7)
            goto L71
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L36:
            java.lang.Object r1 = r0.L$1
            kotlin.jvm.internal.f0 r1 = (kotlin.jvm.internal.f0) r1
            java.lang.Object r3 = r0.L$0
            coil.decode.ImageDecoderDecoder r3 = (android.graphics.ImageDecoderDecoder) r3
            k2.c.G(r7)
            goto L60
        L42:
            k2.c.G(r7)
            kotlin.jvm.internal.f0 r7 = new kotlin.jvm.internal.f0
            r7.<init>()
            coil.decode.ImageDecoderDecoder$decode$drawable$1 r1 = new coil.decode.ImageDecoderDecoder$decode$drawable$1
            r1.<init>(r6, r7)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r1 = ka.m0.u(r1, r0)
            if (r1 != r4) goto L5c
            goto L6f
        L5c:
            r3 = r1
            r1 = r7
            r7 = r3
            r3 = r6
        L60:
            android.graphics.drawable.Drawable r7 = (android.graphics.drawable.Drawable) r7
            r0.L$0 = r1
            r5 = 0
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r7 = r3.wrapDrawable(r7, r0)
            if (r7 != r4) goto L70
        L6f:
            return r4
        L70:
            r0 = r1
        L71:
            android.graphics.drawable.Drawable r7 = (android.graphics.drawable.Drawable) r7
            boolean r0 = r0.f19738i
            coil.decode.DecodeResult r1 = new coil.decode.DecodeResult
            r1.<init>(r7, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.graphics.ImageDecoderDecoder.decode(d7.d):java.lang.Object");
    }

    public ImageDecoderDecoder(ImageSource imageSource, Options options, boolean z) {
        this.source = imageSource;
        this.options = options;
        this.enforceMinimumFrameDelay = z;
    }

    public /* synthetic */ ImageDecoderDecoder(ImageSource imageSource, Options options, boolean z, int i10, h hVar) {
        this(imageSource, options, (i10 & 4) != 0 ? true : z);
    }
}
