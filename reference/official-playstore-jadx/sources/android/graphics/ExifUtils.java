package android.graphics;

import android.content.Bitmaps;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import f1.c;
import f1.i;
import kotlin.Metadata;
import xb.h;
import xb.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000b\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcoil/decode/ExifUtils;", "", "<init>", "()V", "", "mimeType", "Lxb/l;", "source", "Lcoil/decode/ExifOrientationPolicy;", "policy", "Lcoil/decode/ExifData;", "getExifData", "(Ljava/lang/String;Lxb/l;Lcoil/decode/ExifOrientationPolicy;)Lcoil/decode/ExifData;", "Landroid/graphics/Bitmap;", "inBitmap", "exifData", "reverseTransformations", "(Landroid/graphics/Bitmap;Lcoil/decode/ExifData;)Landroid/graphics/Bitmap;", "Landroid/graphics/Paint;", "PAINT", "Landroid/graphics/Paint;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ExifUtils {
    public static final ExifUtils INSTANCE = new ExifUtils();
    private static final Paint PAINT = new Paint(3);

    private ExifUtils() {
    }

    public final ExifData getExifData(String mimeType, l source, ExifOrientationPolicy policy) {
        int iE;
        if (!ExifUtilsKt.supports(policy, mimeType)) {
            return ExifData.NONE;
        }
        int iE2 = 1;
        i iVar = new i(new ExifInterfaceInputStream(new h(source.peek(), 1)));
        c cVarC = iVar.c("Orientation");
        if (cVarC == null) {
            iE = 1;
        } else {
            try {
                iE = cVarC.e(iVar.f15241f);
            } catch (NumberFormatException unused) {
                iE = 1;
            }
        }
        int i10 = 0;
        boolean z = iE == 2 || iE == 7 || iE == 4 || iE == 5;
        c cVarC2 = iVar.c("Orientation");
        if (cVarC2 != null) {
            try {
                iE2 = cVarC2.e(iVar.f15241f);
            } catch (NumberFormatException unused2) {
            }
        }
        switch (iE2) {
            case 3:
            case 4:
                i10 = 180;
                break;
            case 5:
            case 8:
                i10 = 270;
                break;
            case 6:
            case 7:
                i10 = 90;
                break;
        }
        return new ExifData(z, i10);
    }

    public final Bitmap reverseTransformations(Bitmap inBitmap, ExifData exifData) {
        if (!exifData.getIsFlipped() && !ExifUtilsKt.isRotated(exifData)) {
            return inBitmap;
        }
        Matrix matrix = new Matrix();
        float width = inBitmap.getWidth() / 2.0f;
        float height = inBitmap.getHeight() / 2.0f;
        if (exifData.getIsFlipped()) {
            matrix.postScale(-1.0f, 1.0f, width, height);
        }
        if (ExifUtilsKt.isRotated(exifData)) {
            matrix.postRotate(exifData.getRotationDegrees(), width, height);
        }
        RectF rectF = new RectF(0.0f, 0.0f, inBitmap.getWidth(), inBitmap.getHeight());
        matrix.mapRect(rectF);
        float f10 = rectF.left;
        if (f10 != 0.0f || rectF.top != 0.0f) {
            matrix.postTranslate(-f10, -rectF.top);
        }
        Bitmap bitmapCreateBitmap = ExifUtilsKt.isSwapped(exifData) ? Bitmap.createBitmap(inBitmap.getHeight(), inBitmap.getWidth(), Bitmaps.getSafeConfig(inBitmap)) : Bitmap.createBitmap(inBitmap.getWidth(), inBitmap.getHeight(), Bitmaps.getSafeConfig(inBitmap));
        new Canvas(bitmapCreateBitmap).drawBitmap(inBitmap, matrix, PAINT);
        inBitmap.recycle();
        return bitmapCreateBitmap;
    }
}
