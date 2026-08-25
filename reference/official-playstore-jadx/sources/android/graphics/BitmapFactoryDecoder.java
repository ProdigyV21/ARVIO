package android.graphics;

import android.content.Bitmaps;
import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Decoder;
import android.graphics.ImageSource;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Size;
import android.view.Sizes;
import coil.ImageLoader;
import coil.fetch.SourceResult;
import coil.request.Options;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import t7.a;
import ua.f;
import xb.i0;
import xb.j;
import xb.o0;
import xb.v;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001d\u001e\u001fB+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bB\u0019\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\fB#\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\rJ\u0013\u0010\u0010\u001a\u00020\u000f*\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0015\u001a\u00020\u0014*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0017\u001a\u00020\u0014*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0017\u0010\u0016J\u0013\u0010\u0010\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0018R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0019R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001aR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001bR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lcoil/decode/BitmapFactoryDecoder;", "Lcoil/decode/Decoder;", "Lcoil/decode/ImageSource;", "source", "Lcoil/request/Options;", "options", "Lua/f;", "parallelismLock", "Lcoil/decode/ExifOrientationPolicy;", "exifOrientationPolicy", "<init>", "(Lcoil/decode/ImageSource;Lcoil/request/Options;Lua/f;Lcoil/decode/ExifOrientationPolicy;)V", "(Lcoil/decode/ImageSource;Lcoil/request/Options;)V", "(Lcoil/decode/ImageSource;Lcoil/request/Options;Lua/f;)V", "Landroid/graphics/BitmapFactory$Options;", "Lcoil/decode/DecodeResult;", "decode", "(Landroid/graphics/BitmapFactory$Options;)Lcoil/decode/DecodeResult;", "Lcoil/decode/ExifData;", "exifData", "Lx6/t0;", "configureConfig", "(Landroid/graphics/BitmapFactory$Options;Lcoil/decode/ExifData;)V", "configureScale", "(Ld7/d;)Ljava/lang/Object;", "Lcoil/decode/ImageSource;", "Lcoil/request/Options;", "Lua/f;", "Lcoil/decode/ExifOrientationPolicy;", "Companion", "ExceptionCatchingSource", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BitmapFactoryDecoder implements Decoder {
    public static final int DEFAULT_MAX_PARALLELISM = 4;
    private final ExifOrientationPolicy exifOrientationPolicy;
    private final Options options;
    private final f parallelismLock;
    private final ImageSource source;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\n\u0010\u000bR4\u0010\u000f\u001a\n\u0018\u00010\fj\u0004\u0018\u0001`\r2\u000e\u0010\u000e\u001a\n\u0018\u00010\fj\u0004\u0018\u0001`\r8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder$ExceptionCatchingSource;", "Lxb/v;", "Lxb/o0;", "delegate", "<init>", "(Lxb/o0;)V", "Lxb/j;", "sink", "", "byteCount", "read", "(Lxb/j;J)J", "Ljava/lang/Exception;", "Lkotlin/Exception;", "<set-?>", "exception", "Ljava/lang/Exception;", "getException", "()Ljava/lang/Exception;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ExceptionCatchingSource extends v {
        private Exception exception;

        public ExceptionCatchingSource(o0 o0Var) {
            super(o0Var);
        }

        public final Exception getException() {
            return this.exception;
        }

        @Override // xb.v, xb.o0
        public long read(j sink, long byteCount) throws Exception {
            try {
                return super.read(sink, byteCount);
            } catch (Exception e5) {
                this.exception = e5;
                throw e5;
            }
        }
    }

    /* JADX INFO: renamed from: coil.decode.BitmapFactoryDecoder$decode$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.decode.BitmapFactoryDecoder", f = "BitmapFactoryDecoder.kt", l = {232, 46}, m = "decode")
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
            return BitmapFactoryDecoder.this.decode(this);
        }
    }

    public BitmapFactoryDecoder(ImageSource imageSource, Options options, f fVar, ExifOrientationPolicy exifOrientationPolicy) {
        this.source = imageSource;
        this.options = options;
        this.parallelismLock = fVar;
        this.exifOrientationPolicy = exifOrientationPolicy;
    }

    private final void configureConfig(BitmapFactory.Options options, ExifData exifData) {
        Bitmap.Config config = this.options.getConfig();
        if (exifData.getIsFlipped() || ExifUtilsKt.isRotated(exifData)) {
            config = Bitmaps.toSoftware(config);
        }
        if (this.options.getAllowRgb565() && config == Bitmap.Config.ARGB_8888 && p.a(options.outMimeType, "image/jpeg")) {
            config = Bitmap.Config.RGB_565;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            Bitmap.Config config2 = options.outConfig;
            Bitmap.Config config3 = Bitmap.Config.RGBA_F16;
            if (config2 == config3 && config != Bitmap.Config.HARDWARE) {
                config = config3;
            }
        }
        options.inPreferredConfig = config;
    }

    private final void configureScale(BitmapFactory.Options options, ExifData exifData) {
        ImageSource.Metadata metadata = this.source.getMetadata();
        if ((metadata instanceof ResourceMetadata) && Sizes.isOriginal(this.options.getSize())) {
            options.inSampleSize = 1;
            options.inScaled = true;
            options.inDensity = ((ResourceMetadata) metadata).getDensity();
            options.inTargetDensity = this.options.getContext().getResources().getDisplayMetrics().densityDpi;
            return;
        }
        if (options.outWidth <= 0 || options.outHeight <= 0) {
            options.inSampleSize = 1;
            options.inScaled = false;
            return;
        }
        int i10 = ExifUtilsKt.isSwapped(exifData) ? options.outHeight : options.outWidth;
        int i11 = ExifUtilsKt.isSwapped(exifData) ? options.outWidth : options.outHeight;
        Size size = this.options.getSize();
        int px = Sizes.isOriginal(size) ? i10 : Utils.toPx(size.getWidth(), this.options.getScale());
        Size size2 = this.options.getSize();
        int px2 = Sizes.isOriginal(size2) ? i11 : Utils.toPx(size2.getHeight(), this.options.getScale());
        int iCalculateInSampleSize = DecodeUtils.calculateInSampleSize(i10, i11, px, px2, this.options.getScale());
        options.inSampleSize = iCalculateInSampleSize;
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(((double) i10) / ((double) iCalculateInSampleSize), ((double) i11) / ((double) iCalculateInSampleSize), px, px2, this.options.getScale());
        if (this.options.getAllowInexactSize() && dComputeSizeMultiplier > 1.0d) {
            dComputeSizeMultiplier = 1.0d;
        }
        boolean z = dComputeSizeMultiplier == 1.0d;
        options.inScaled = !z;
        if (z) {
            return;
        }
        if (dComputeSizeMultiplier > 1.0d) {
            options.inDensity = a.L(((double) Integer.MAX_VALUE) / dComputeSizeMultiplier);
            options.inTargetDensity = Integer.MAX_VALUE;
        } else {
            options.inDensity = Integer.MAX_VALUE;
            options.inTargetDensity = a.L(((double) Integer.MAX_VALUE) * dComputeSizeMultiplier);
        }
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
            boolean r0 = r7 instanceof android.graphics.BitmapFactoryDecoder.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            coil.decode.BitmapFactoryDecoder$decode$1 r0 = (android.graphics.BitmapFactoryDecoder.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.decode.BitmapFactoryDecoder$decode$1 r0 = new coil.decode.BitmapFactoryDecoder$decode$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L45
            if (r1 == r3) goto L38
            if (r1 != r2) goto L30
            java.lang.Object r0 = r0.L$0
            ua.f r0 = (ua.f) r0
            k2.c.G(r7)     // Catch: java.lang.Throwable -> L2e
            goto L6e
        L2e:
            r7 = move-exception
            goto L78
        L30:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L38:
            java.lang.Object r1 = r0.L$1
            ua.f r1 = (ua.f) r1
            java.lang.Object r3 = r0.L$0
            coil.decode.BitmapFactoryDecoder r3 = (android.graphics.BitmapFactoryDecoder) r3
            k2.c.G(r7)
            r7 = r1
            goto L58
        L45:
            k2.c.G(r7)
            ua.f r7 = r6.parallelismLock
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r1 = r7.a(r0)
            if (r1 != r4) goto L57
            goto L6a
        L57:
            r3 = r6
        L58:
            coil.decode.BitmapFactoryDecoder$decode$2$1 r1 = new coil.decode.BitmapFactoryDecoder$decode$2$1     // Catch: java.lang.Throwable -> L74
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L74
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L74
            r3 = 0
            r0.L$1 = r3     // Catch: java.lang.Throwable -> L74
            r0.label = r2     // Catch: java.lang.Throwable -> L74
            java.lang.Object r0 = ka.m0.u(r1, r0)     // Catch: java.lang.Throwable -> L74
            if (r0 != r4) goto L6b
        L6a:
            return r4
        L6b:
            r5 = r0
            r0 = r7
            r7 = r5
        L6e:
            coil.decode.DecodeResult r7 = (android.graphics.DecodeResult) r7     // Catch: java.lang.Throwable -> L2e
            r0.release()
            return r7
        L74:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
        L78:
            r0.release()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.graphics.BitmapFactoryDecoder.decode(d7.d):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\t\b\u0017¢\u0006\u0004\b\u0006\u0010\bB\u0013\b\u0017\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\tJ'\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder$Factory;", "Lcoil/decode/Decoder$Factory;", "", "maxParallelism", "Lcoil/decode/ExifOrientationPolicy;", "exifOrientationPolicy", "<init>", "(ILcoil/decode/ExifOrientationPolicy;)V", "()V", "(I)V", "Lcoil/fetch/SourceResult;", "result", "Lcoil/request/Options;", "options", "Lcoil/ImageLoader;", "imageLoader", "Lcoil/decode/Decoder;", "create", "(Lcoil/fetch/SourceResult;Lcoil/request/Options;Lcoil/ImageLoader;)Lcoil/decode/Decoder;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Lcoil/decode/ExifOrientationPolicy;", "Lua/f;", "parallelismLock", "Lua/f;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Decoder.Factory {
        private final ExifOrientationPolicy exifOrientationPolicy;
        private final f parallelismLock;

        public Factory(int i10, ExifOrientationPolicy exifOrientationPolicy) {
            this.exifOrientationPolicy = exifOrientationPolicy;
            this.parallelismLock = ua.j.a(i10);
        }

        @Override // coil.decode.Decoder.Factory
        public Decoder create(SourceResult result, Options options, ImageLoader imageLoader) {
            return new BitmapFactoryDecoder(result.getSource(), options, this.parallelismLock, this.exifOrientationPolicy);
        }

        public boolean equals(Object other) {
            return other instanceof Factory;
        }

        public int hashCode() {
            return Factory.class.hashCode();
        }

        public /* synthetic */ Factory(int i10, ExifOrientationPolicy exifOrientationPolicy, int i11, h hVar) {
            this((i11 & 1) != 0 ? 4 : i10, (i11 & 2) != 0 ? ExifOrientationPolicy.RESPECT_PERFORMANCE : exifOrientationPolicy);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Factory() {
            this(0, null, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @x6.e
        public /* synthetic */ Factory(int i10) {
            this(i10, null, 2, 0 == true ? 1 : 0);
        }

        public /* synthetic */ Factory(int i10, int i11, h hVar) {
            this((i11 & 1) != 0 ? 4 : i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DecodeResult decode(BitmapFactory.Options options) throws Exception {
        ExceptionCatchingSource exceptionCatchingSource = new ExceptionCatchingSource(this.source.source());
        i0 i0Var = new i0(exceptionCatchingSource);
        boolean z = true;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new xb.h(i0Var.peek(), 1), null, options);
        Exception exception = exceptionCatchingSource.getException();
        if (exception == null) {
            options.inJustDecodeBounds = false;
            ExifUtils exifUtils = ExifUtils.INSTANCE;
            ExifData exifData = exifUtils.getExifData(options.outMimeType, i0Var, this.exifOrientationPolicy);
            Exception exception2 = exceptionCatchingSource.getException();
            if (exception2 == null) {
                options.inMutable = false;
                if (Build.VERSION.SDK_INT >= 26 && this.options.getColorSpace() != null) {
                    options.inPreferredColorSpace = this.options.getColorSpace();
                }
                options.inPremultiplied = this.options.getPremultipliedAlpha();
                configureConfig(options, exifData);
                configureScale(options, exifData);
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(new xb.h(i0Var, 1), null, options);
                    i0Var.close();
                    Exception exception3 = exceptionCatchingSource.getException();
                    if (exception3 != null) {
                        throw exception3;
                    }
                    if (bitmapDecodeStream != null) {
                        bitmapDecodeStream.setDensity(this.options.getContext().getResources().getDisplayMetrics().densityDpi);
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.options.getContext().getResources(), exifUtils.reverseTransformations(bitmapDecodeStream, exifData));
                        if (options.inSampleSize <= 1 && !options.inScaled) {
                            z = false;
                        }
                        return new DecodeResult(bitmapDrawable, z);
                    }
                    throw new IllegalStateException("BitmapFactory returned a null bitmap. Often this means BitmapFactory could not decode the image data read from the input source (e.g. network, disk, or memory) as it's not encoded as a valid image format.");
                } finally {
                }
            } else {
                throw exception2;
            }
        } else {
            throw exception;
        }
    }

    public /* synthetic */ BitmapFactoryDecoder(ImageSource imageSource, Options options, f fVar, ExifOrientationPolicy exifOrientationPolicy, int i10, h hVar) {
        this(imageSource, options, (i10 & 4) != 0 ? ua.j.a(Integer.MAX_VALUE) : fVar, (i10 & 8) != 0 ? ExifOrientationPolicy.RESPECT_PERFORMANCE : exifOrientationPolicy);
    }

    @x6.e
    public /* synthetic */ BitmapFactoryDecoder(ImageSource imageSource, Options options) {
        this(imageSource, options, null, null, 12, null);
    }

    public /* synthetic */ BitmapFactoryDecoder(ImageSource imageSource, Options options, f fVar, int i10, h hVar) {
        this(imageSource, options, (i10 & 4) != 0 ? ua.j.a(Integer.MAX_VALUE) : fVar);
    }

    @x6.e
    public /* synthetic */ BitmapFactoryDecoder(ImageSource imageSource, Options options, f fVar) {
        this(imageSource, options, fVar, null, 8, null);
    }
}
