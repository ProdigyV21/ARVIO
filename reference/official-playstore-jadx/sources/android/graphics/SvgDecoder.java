package android.graphics;

import android.content.SvgUtils;
import android.graphics.Decoder;
import android.view.Scale;
import android.view.Size;
import android.view.Sizes;
import coil.ImageLoader;
import coil.fetch.SourceResult;
import coil.request.Options;
import d7.d;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB#\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ3\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u000f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0013\u001a\u00020\u0012H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lcoil/decode/SvgDecoder;", "Lcoil/decode/Decoder;", "Lcoil/decode/ImageSource;", "source", "Lcoil/request/Options;", "options", "", "useViewBoundsAsIntrinsicSize", "<init>", "(Lcoil/decode/ImageSource;Lcoil/request/Options;Z)V", "", "srcWidth", "srcHeight", "Lcoil/size/Scale;", "scale", "Lx6/x;", "getDstSize", "(FFLcoil/size/Scale;)Lx6/x;", "Lcoil/decode/DecodeResult;", "decode", "(Ld7/d;)Ljava/lang/Object;", "Lcoil/decode/ImageSource;", "Lcoil/request/Options;", "Z", "getUseViewBoundsAsIntrinsicSize", "()Z", "Companion", "Factory", "coil-svg_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SvgDecoder implements Decoder {
    public static final String CSS_KEY = "coil#css";
    private static final float DEFAULT_SIZE = 512.0f;
    private static final String MIME_TYPE_SVG = "image/svg+xml";
    private final Options options;
    private final ImageSource source;
    private final boolean useViewBoundsAsIntrinsicSize;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcoil/decode/SvgDecoder$Factory;", "Lcoil/decode/Decoder$Factory;", "useViewBoundsAsIntrinsicSize", "", "(Z)V", "getUseViewBoundsAsIntrinsicSize", "()Z", "create", "Lcoil/decode/Decoder;", "result", "Lcoil/fetch/SourceResult;", "options", "Lcoil/request/Options;", "imageLoader", "Lcoil/ImageLoader;", "equals", "other", "", "hashCode", "", "isApplicable", "coil-svg_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Decoder.Factory {
        private final boolean useViewBoundsAsIntrinsicSize;

        public Factory() {
            this(false, 1, null);
        }

        private final boolean isApplicable(SourceResult result) {
            return p.a(result.getMimeType(), SvgDecoder.MIME_TYPE_SVG) || SvgDecodeUtils.isSvg(DecodeUtils.INSTANCE, result.getSource().source());
        }

        @Override // coil.decode.Decoder.Factory
        public Decoder create(SourceResult result, Options options, ImageLoader imageLoader) {
            if (isApplicable(result)) {
                return new SvgDecoder(result.getSource(), options, this.useViewBoundsAsIntrinsicSize);
            }
            return null;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Factory) && this.useViewBoundsAsIntrinsicSize == ((Factory) other).useViewBoundsAsIntrinsicSize;
        }

        public final boolean getUseViewBoundsAsIntrinsicSize() {
            return this.useViewBoundsAsIntrinsicSize;
        }

        public int hashCode() {
            return this.useViewBoundsAsIntrinsicSize ? 1231 : 1237;
        }

        public Factory(boolean z) {
            this.useViewBoundsAsIntrinsicSize = z;
        }

        public /* synthetic */ Factory(boolean z, int i10, h hVar) {
            this((i10 & 1) != 0 ? true : z);
        }
    }

    /* JADX INFO: renamed from: coil.decode.SvgDecoder$decode$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcoil/decode/DecodeResult;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<DecodeResult> {
        public AnonymousClass2() {
            super(0);
        }

        /* JADX WARN: Removed duplicated region for block: B:60:0x0164  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0167  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x01a7  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x01bf  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x01cb  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x01df  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x01fd  */
        @Override // r7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final android.graphics.DecodeResult invoke() throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 585
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.graphics.SvgDecoder.AnonymousClass2.invoke():coil.decode.DecodeResult");
        }
    }

    public SvgDecoder(ImageSource imageSource, Options options) {
        this(imageSource, options, false, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final x getDstSize(float srcWidth, float srcHeight, Scale scale) {
        if (!Sizes.isOriginal(this.options.getSize())) {
            Size size = this.options.getSize();
            return new x(Float.valueOf(SvgUtils.toPx(size.getWidth(), scale)), Float.valueOf(SvgUtils.toPx(size.getHeight(), scale)));
        }
        if (srcWidth <= 0.0f) {
            srcWidth = 512.0f;
        }
        if (srcHeight <= 0.0f) {
            srcHeight = 512.0f;
        }
        return new x(Float.valueOf(srcWidth), Float.valueOf(srcHeight));
    }

    @Override // android.graphics.Decoder
    public Object decode(d<? super DecodeResult> dVar) {
        return m0.u(new AnonymousClass2(), dVar);
    }

    public final boolean getUseViewBoundsAsIntrinsicSize() {
        return this.useViewBoundsAsIntrinsicSize;
    }

    public SvgDecoder(ImageSource imageSource, Options options, boolean z) {
        this.source = imageSource;
        this.options = options;
        this.useViewBoundsAsIntrinsicSize = z;
    }

    public /* synthetic */ SvgDecoder(ImageSource imageSource, Options options, boolean z, int i10, h hVar) {
        this(imageSource, options, (i10 & 4) != 0 ? true : z);
    }
}
