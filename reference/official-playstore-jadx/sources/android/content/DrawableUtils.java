package android.content;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DecodeUtils;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Scale;
import android.view.Size;
import android.view.Sizes;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002J(\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcoil/util/DrawableUtils;", "", "()V", "DEFAULT_SIZE", "", "convertToBitmap", "Landroid/graphics/Bitmap;", "drawable", "Landroid/graphics/drawable/Drawable;", "config", "Landroid/graphics/Bitmap$Config;", ContentDisposition.Parameters.Size, "Lcoil/size/Size;", "scale", "Lcoil/size/Scale;", "allowInexactSize", "", "isConfigValid", "bitmap", "isSizeValid", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DrawableUtils {
    private static final int DEFAULT_SIZE = 512;
    public static final DrawableUtils INSTANCE = new DrawableUtils();

    private DrawableUtils() {
    }

    private final boolean isConfigValid(Bitmap bitmap, Bitmap.Config config) {
        return bitmap.getConfig() == Bitmaps.toSoftware(config);
    }

    private final boolean isSizeValid(boolean allowInexactSize, Bitmap bitmap, Size size, Scale scale) {
        if (allowInexactSize) {
            return true;
        }
        return DecodeUtils.computeSizeMultiplier(bitmap.getWidth(), bitmap.getHeight(), Sizes.isOriginal(size) ? bitmap.getWidth() : Utils.toPx(size.getWidth(), scale), Sizes.isOriginal(size) ? bitmap.getHeight() : Utils.toPx(size.getHeight(), scale), scale) == 1.0d;
    }

    public final Bitmap convertToBitmap(Drawable drawable, Bitmap.Config config, Size size, Scale scale, boolean allowInexactSize) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (isConfigValid(bitmap, config) && isSizeValid(allowInexactSize, bitmap, size, scale)) {
                return bitmap;
            }
        }
        Drawable drawableMutate = drawable.mutate();
        int width = Utils.getWidth(drawableMutate);
        if (width <= 0) {
            width = 512;
        }
        int height = Utils.getHeight(drawableMutate);
        int i10 = height > 0 ? height : 512;
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(width, i10, Sizes.isOriginal(size) ? width : Utils.toPx(size.getWidth(), scale), Sizes.isOriginal(size) ? i10 : Utils.toPx(size.getHeight(), scale), scale);
        int iL = a.L(((double) width) * dComputeSizeMultiplier);
        int iL2 = a.L(dComputeSizeMultiplier * ((double) i10));
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iL, iL2, Bitmaps.toSoftware(config));
        Rect bounds = drawableMutate.getBounds();
        int i11 = bounds.left;
        int i12 = bounds.top;
        int i13 = bounds.right;
        int i14 = bounds.bottom;
        drawableMutate.setBounds(0, 0, iL, iL2);
        drawableMutate.draw(new Canvas(bitmapCreateBitmap));
        drawableMutate.setBounds(i11, i12, i13, i14);
        return bitmapCreateBitmap;
    }
}
