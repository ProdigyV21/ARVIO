package androidx.compose.ui.graphics;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\tJ\u0015\u0010\f\u001a\u00020\u0002H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u0002ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0003\u0010\u001c\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/graphics/ColorMatrixColorFilter;", "Landroidx/compose/ui/graphics/ColorFilter;", "Landroidx/compose/ui/graphics/ColorMatrix;", "colorMatrix", "Landroid/graphics/ColorFilter;", "Landroidx/compose/ui/graphics/NativeColorFilter;", "nativeColorFilter", "<init>", "([FLandroid/graphics/ColorFilter;Lkotlin/jvm/internal/h;)V", "([FLkotlin/jvm/internal/h;)V", "obtainColorMatrix-p10-uLo", "()[F", "obtainColorMatrix", "targetColorMatrix", "copyColorMatrix-gBh15pI", "([F)[F", "copyColorMatrix", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "[F", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ColorMatrixColorFilter extends ColorFilter {
    private float[] colorMatrix;

    public /* synthetic */ ColorMatrixColorFilter(float[] fArr, android.graphics.ColorFilter colorFilter, kotlin.jvm.internal.h hVar) {
        this(fArr, colorFilter);
    }

    /* JADX INFO: renamed from: copyColorMatrix-gBh15pI$default, reason: not valid java name */
    public static /* synthetic */ float[] m3549copyColorMatrixgBh15pI$default(ColorMatrixColorFilter colorMatrixColorFilter, float[] fArr, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            fArr = ColorMatrix.m3529constructorimpl$default(null, 1, null);
        }
        return colorMatrixColorFilter.m3551copyColorMatrixgBh15pI(fArr);
    }

    /* JADX INFO: renamed from: obtainColorMatrix-p10-uLo, reason: not valid java name */
    private final float[] m3550obtainColorMatrixp10uLo() {
        float[] fArr = this.colorMatrix;
        if (fArr != null) {
            return fArr;
        }
        float[] fArrActualColorMatrixFromFilter = AndroidColorFilter_androidKt.actualColorMatrixFromFilter(getNativeColorFilter());
        this.colorMatrix = fArrActualColorMatrixFromFilter;
        return fArrActualColorMatrixFromFilter;
    }

    /* JADX INFO: renamed from: copyColorMatrix-gBh15pI, reason: not valid java name */
    public final float[] m3551copyColorMatrixgBh15pI(float[] targetColorMatrix) {
        r.Q(m3550obtainColorMatrixp10uLo(), targetColorMatrix, 14);
        return targetColorMatrix;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ColorMatrixColorFilter) && Arrays.equals(m3550obtainColorMatrixp10uLo(), ((ColorMatrixColorFilter) other).m3550obtainColorMatrixp10uLo());
    }

    public int hashCode() {
        float[] fArr = this.colorMatrix;
        if (fArr != null) {
            return ColorMatrix.m3536hashCodeimpl(fArr);
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ColorMatrixColorFilter(colorMatrix=");
        float[] fArr = this.colorMatrix;
        sb2.append((Object) (fArr == null ? "null" : ColorMatrix.m3547toStringimpl(fArr)));
        sb2.append(')');
        return sb2.toString();
    }

    public /* synthetic */ ColorMatrixColorFilter(float[] fArr, kotlin.jvm.internal.h hVar) {
        this(fArr);
    }

    private ColorMatrixColorFilter(float[] fArr, android.graphics.ColorFilter colorFilter) {
        super(colorFilter);
        this.colorMatrix = fArr;
    }

    private ColorMatrixColorFilter(float[] fArr) {
        this(fArr, AndroidColorFilter_androidKt.m3342actualColorMatrixColorFilterjHGOpc(fArr), null);
    }
}
