package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.ImageBitmap;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class g {
    static {
        ImageBitmap.Companion companion = ImageBitmap.Companion;
    }

    public static /* synthetic */ void a(ImageBitmap imageBitmap, int[] iArr, int i10, int i11, int i12, int i13, int i14, int i15, int i16, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readPixels");
        }
        if ((i16 & 2) != 0) {
            i10 = 0;
        }
        if ((i16 & 4) != 0) {
            i11 = 0;
        }
        if ((i16 & 8) != 0) {
            i12 = imageBitmap.getWidth();
        }
        if ((i16 & 16) != 0) {
            i13 = imageBitmap.getHeight();
        }
        if ((i16 & 32) != 0) {
            i14 = 0;
        }
        if ((i16 & 64) != 0) {
            i15 = i12;
        }
        imageBitmap.readPixels(iArr, i10, i11, i12, i13, i14, i15);
    }
}
