package androidx.compose.ui.geometry;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"toStringAsFixed", "", "", "digits", "", "ui-geometry_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GeometryUtilsKt {
    public static final String toStringAsFixed(float f10, int i10) {
        int iMax = Math.max(i10, 0);
        float fPow = (float) Math.pow(10.0f, iMax);
        float f11 = f10 * fPow;
        int i11 = (int) f11;
        if (f11 - i11 >= 0.5f) {
            i11++;
        }
        float f12 = i11 / fPow;
        return iMax > 0 ? String.valueOf(f12) : String.valueOf((int) f12);
    }
}
