package coil.transform;

import a0.c;
import android.content.Bitmaps;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DecodeUtils;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.Dimension;
import android.view.Scale;
import android.view.Size;
import android.view.Sizes;
import d7.d;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import t7.a;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bB\u0013\b\u0016\u0012\b\b\u0001\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\nJ+\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001cR\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001cR\u0014\u0010\u0006\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001cR\u001a\u0010\u001e\u001a\u00020\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lcoil/transform/RoundedCornersTransformation;", "Lcoil/transform/Transformation;", "", "topLeft", "topRight", "bottomLeft", "bottomRight", "<init>", "(FFFF)V", "radius", "(F)V", "Landroid/graphics/Bitmap;", "input", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "Lx6/x;", "", "calculateOutputSize", "(Landroid/graphics/Bitmap;Lcoil/size/Size;)Lx6/x;", "transform", "(Landroid/graphics/Bitmap;Lcoil/size/Size;Ld7/d;)Ljava/lang/Object;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "F", "", "cacheKey", "Ljava/lang/String;", "getCacheKey", "()Ljava/lang/String;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RoundedCornersTransformation implements Transformation {
    private final float bottomLeft;
    private final float bottomRight;
    private final String cacheKey;
    private final float topLeft;
    private final float topRight;

    public RoundedCornersTransformation() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 15, null);
    }

    private final x calculateOutputSize(Bitmap input, Size size) {
        if (Sizes.isOriginal(size)) {
            return new x(Integer.valueOf(input.getWidth()), Integer.valueOf(input.getHeight()));
        }
        Dimension width = size.getWidth();
        Dimension height = size.getHeight();
        if ((width instanceof Dimension.Pixels) && (height instanceof Dimension.Pixels)) {
            return new x(Integer.valueOf(((Dimension.Pixels) width).px), Integer.valueOf(((Dimension.Pixels) height).px));
        }
        int width2 = input.getWidth();
        int height2 = input.getHeight();
        Dimension width3 = size.getWidth();
        int i10 = width3 instanceof Dimension.Pixels ? ((Dimension.Pixels) width3).px : Integer.MIN_VALUE;
        Dimension height3 = size.getHeight();
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(width2, height2, i10, height3 instanceof Dimension.Pixels ? ((Dimension.Pixels) height3).px : Integer.MIN_VALUE, Scale.FILL);
        return new x(Integer.valueOf(a.L(((double) input.getWidth()) * dComputeSizeMultiplier)), Integer.valueOf(a.L(dComputeSizeMultiplier * ((double) input.getHeight()))));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoundedCornersTransformation)) {
            return false;
        }
        RoundedCornersTransformation roundedCornersTransformation = (RoundedCornersTransformation) other;
        return this.topLeft == roundedCornersTransformation.topLeft && this.topRight == roundedCornersTransformation.topRight && this.bottomLeft == roundedCornersTransformation.bottomLeft && this.bottomRight == roundedCornersTransformation.bottomRight;
    }

    @Override // coil.transform.Transformation
    public String getCacheKey() {
        return this.cacheKey;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.bottomRight) + c.b(this.bottomLeft, c.b(this.topRight, Float.floatToIntBits(this.topLeft) * 31, 31), 31);
    }

    @Override // coil.transform.Transformation
    public Object transform(Bitmap bitmap, Size size, d<? super Bitmap> dVar) {
        Paint paint = new Paint(3);
        x xVarCalculateOutputSize = calculateOutputSize(bitmap, size);
        int iIntValue = ((Number) xVarCalculateOutputSize.f22608i).intValue();
        int iIntValue2 = ((Number) xVarCalculateOutputSize.f22609l).intValue();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iIntValue, iIntValue2, Bitmaps.getSafeConfig(bitmap));
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Matrix matrix = new Matrix();
        float fComputeSizeMultiplier = (float) DecodeUtils.computeSizeMultiplier(bitmap.getWidth(), bitmap.getHeight(), iIntValue, iIntValue2, Scale.FILL);
        float f10 = 2;
        matrix.setTranslate((iIntValue - (bitmap.getWidth() * fComputeSizeMultiplier)) / f10, (iIntValue2 - (bitmap.getHeight() * fComputeSizeMultiplier)) / f10);
        matrix.preScale(fComputeSizeMultiplier, fComputeSizeMultiplier);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float f11 = this.topLeft;
        float f12 = this.topRight;
        float f13 = this.bottomRight;
        float f14 = this.bottomLeft;
        float[] fArr = {f11, f11, f12, f12, f13, f13, f14, f14};
        RectF rectF = new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.drawPath(path, paint);
        return bitmapCreateBitmap;
    }

    public RoundedCornersTransformation(float f10, float f11, float f12, float f13) {
        this.topLeft = f10;
        this.topRight = f11;
        this.bottomLeft = f12;
        this.bottomRight = f13;
        if (f10 < 0.0f || f11 < 0.0f || f12 < 0.0f || f13 < 0.0f) {
            throw new IllegalArgumentException("All radii must be >= 0.");
        }
        this.cacheKey = RoundedCornersTransformation.class.getName() + '-' + f10 + ',' + f11 + ',' + f12 + ',' + f13;
    }

    public /* synthetic */ RoundedCornersTransformation(float f10, float f11, float f12, float f13, int i10, h hVar) {
        this((i10 & 1) != 0 ? 0.0f : f10, (i10 & 2) != 0 ? 0.0f : f11, (i10 & 4) != 0 ? 0.0f : f12, (i10 & 8) != 0 ? 0.0f : f13);
    }

    public RoundedCornersTransformation(float f10) {
        this(f10, f10, f10, f10);
    }
}
