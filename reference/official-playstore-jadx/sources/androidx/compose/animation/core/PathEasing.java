package androidx.compose.animation.core;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidPathMeasure_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathMeasure;
import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/compose/animation/core/PathEasing;", "Landroidx/compose/animation/core/Easing;", "path", "Landroidx/compose/ui/graphics/Path;", "(Landroidx/compose/ui/graphics/Path;)V", "offsetX", "", "offsetY", "transform", "", "fraction", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PathEasing implements Easing {
    public static final int $stable = 0;
    private final float[] offsetX;
    private final float[] offsetY;

    public PathEasing(Path path) {
        PathMeasure PathMeasure = AndroidPathMeasure_androidKt.PathMeasure();
        PathMeasure.setPath(path, false);
        float length = PathMeasure.getLength();
        if (length <= 0.0f) {
            throw new IllegalArgumentException("Path cannot be zero in length. Ensure that supplied Path starts at [0,0] and ends at [1,1]");
        }
        int i10 = (int) (length / 0.002f);
        int i11 = i10 + 1;
        float[] fArr = new float[i11];
        for (int i12 = 0; i12 < i11; i12++) {
            fArr[i12] = 0.0f;
        }
        this.offsetX = fArr;
        float[] fArr2 = new float[i11];
        for (int i13 = 0; i13 < i11; i13++) {
            fArr2[i13] = 0.0f;
        }
        this.offsetY = fArr2;
        for (int i14 = 0; i14 < i11; i14++) {
            long jMo3376getPositiontuRUvjQ = PathMeasure.mo3376getPositiontuRUvjQ((i14 * length) / i10);
            this.offsetX[i14] = Offset.m3236getXimpl(jMo3376getPositiontuRUvjQ);
            this.offsetY[i14] = Offset.m3237getYimpl(jMo3376getPositiontuRUvjQ);
            if (i14 > 0) {
                float[] fArr3 = this.offsetX;
                if (fArr3[i14] < fArr3[i14 - 1]) {
                    throw new IllegalArgumentException("Path needs to be continuously increasing");
                }
            }
        }
    }

    @Override // androidx.compose.animation.core.Easing
    public float transform(float fraction) {
        if (fraction <= 0.0f) {
            return 0.0f;
        }
        if (fraction >= 1.0f) {
            return 1.0f;
        }
        float[] fArr = this.offsetX;
        int iBinarySearch = Arrays.binarySearch(fArr, 0, fArr.length, fraction);
        if (iBinarySearch > 0) {
            return this.offsetY[iBinarySearch];
        }
        int iAbs = Math.abs(iBinarySearch);
        float[] fArr2 = this.offsetX;
        if (iAbs >= fArr2.length - 1) {
            float[] fArr3 = this.offsetY;
            if (fArr3.length != 0) {
                return fArr3[fArr3.length - 1];
            }
            throw new NoSuchElementException("Array is empty.");
        }
        int i10 = iAbs + 1;
        float f10 = fArr2[i10];
        float f11 = fArr2[iAbs];
        float f12 = (fraction - f11) / (f10 - f11);
        float[] fArr4 = this.offsetY;
        float f13 = fArr4[iAbs];
        return ((fArr4[i10] - f13) * f12) + f13;
    }
}
