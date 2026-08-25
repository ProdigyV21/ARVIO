package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\u001a>\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001aP\u0010\r\u001a\u00020\u000e*\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0015"}, d2 = {"ImageBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "width", "", "height", "config", "Landroidx/compose/ui/graphics/ImageBitmapConfig;", "hasAlpha", "", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "ImageBitmap-x__-hDU", "(IIIZLandroidx/compose/ui/graphics/colorspace/ColorSpace;)Landroidx/compose/ui/graphics/ImageBitmap;", "toPixelMap", "Landroidx/compose/ui/graphics/PixelMap;", "startX", "startY", "buffer", "", "bufferOffset", "stride", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ImageBitmapKt {
    /* JADX INFO: renamed from: ImageBitmap-x__-hDU, reason: not valid java name */
    public static final ImageBitmap m3678ImageBitmapx__hDU(int i10, int i11, int i12, boolean z, ColorSpace colorSpace) {
        return AndroidImageBitmap_androidKt.m3346ActualImageBitmapx__hDU(i10, i11, i12, z, colorSpace);
    }

    /* JADX INFO: renamed from: ImageBitmap-x__-hDU$default, reason: not valid java name */
    public static /* synthetic */ ImageBitmap m3679ImageBitmapx__hDU$default(int i10, int i11, int i12, boolean z, ColorSpace colorSpace, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i12 = ImageBitmapConfig.INSTANCE.m3674getArgb8888_sVssgQ();
        }
        if ((i13 & 8) != 0) {
            z = true;
        }
        if ((i13 & 16) != 0) {
            colorSpace = ColorSpaces.INSTANCE.getSrgb();
        }
        return m3678ImageBitmapx__hDU(i10, i11, i12, z, colorSpace);
    }

    public static final PixelMap toPixelMap(ImageBitmap imageBitmap, int i10, int i11, int i12, int i13, int[] iArr, int i14, int i15) {
        imageBitmap.readPixels(iArr, i10, i11, i12, i13, i14, i15);
        return new PixelMap(iArr, i12, i13, i14, i15);
    }

    public static /* synthetic */ PixelMap toPixelMap$default(ImageBitmap imageBitmap, int i10, int i11, int i12, int i13, int[] iArr, int i14, int i15, int i16, Object obj) {
        if ((i16 & 1) != 0) {
            i10 = 0;
        }
        if ((i16 & 2) != 0) {
            i11 = 0;
        }
        if ((i16 & 4) != 0) {
            i12 = imageBitmap.getWidth();
        }
        if ((i16 & 8) != 0) {
            i13 = imageBitmap.getHeight();
        }
        if ((i16 & 16) != 0) {
            iArr = new int[i12 * i13];
        }
        if ((i16 & 32) != 0) {
            i14 = 0;
        }
        if ((i16 & 64) != 0) {
            i15 = i12;
        }
        return toPixelMap(imageBitmap, i10, i11, i12, i13, iArr, i14, i15);
    }
}
