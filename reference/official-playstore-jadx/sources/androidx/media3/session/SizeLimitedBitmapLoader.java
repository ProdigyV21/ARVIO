package androidx.media3.session;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import androidx.media3.datasource.BitmapUtil;

/* JADX INFO: loaded from: classes3.dex */
public final class SizeLimitedBitmapLoader implements androidx.media3.common.util.BitmapLoader {
    private final androidx.media3.common.util.BitmapLoader bitmapLoader;
    private final boolean makeShared;
    private final int maxBitmapSize;

    public SizeLimitedBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader, int i10, boolean z) {
        this.bitmapLoader = bitmapLoader;
        this.maxBitmapSize = i10;
        this.makeShared = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap scaleIfNecessary(Bitmap bitmap) {
        if (bitmap.getWidth() > this.maxBitmapSize || bitmap.getHeight() > this.maxBitmapSize) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i10 = this.maxBitmapSize;
            float f10 = width;
            float f11 = height;
            float fMin = Math.min(i10 / f10, i10 / f11);
            bitmap = Bitmap.createScaledBitmap(bitmap, (int) (f10 * fMin), (int) (f11 * fMin), true);
        }
        return this.makeShared ? BitmapUtil.makeShared(bitmap) : bitmap;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public com.google.common.util.concurrent.d1<Bitmap> decodeBitmap(byte[] bArr) {
        return xc.d.K0(this.bitmapLoader.decodeBitmap(bArr), new l3(this));
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public com.google.common.util.concurrent.d1<Bitmap> loadBitmap(Uri uri) {
        return xc.d.K0(this.bitmapLoader.loadBitmap(uri), new l3(this));
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public com.google.common.util.concurrent.d1<Bitmap> loadBitmapFromMetadata(MediaMetadata mediaMetadata) {
        com.google.common.util.concurrent.d1<Bitmap> d1VarLoadBitmapFromMetadata = this.bitmapLoader.loadBitmapFromMetadata(mediaMetadata);
        if (d1VarLoadBitmapFromMetadata == null) {
            return null;
        }
        return xc.d.K0(d1VarLoadBitmapFromMetadata, new l3(this));
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public boolean supportsMimeType(String str) {
        return this.bitmapLoader.supportsMimeType(str);
    }
}
