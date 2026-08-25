package androidx.compose.ui.unit;

import androidx.compose.ui.unit.fontscaling.FontScaleConverter;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/unit/LinearFontScaleConverter;", "Landroidx/compose/ui/unit/fontscaling/FontScaleConverter;", "fontScale", "", "(F)V", "component1", "convertDpToSp", "dp", "convertSpToDp", "sp", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class LinearFontScaleConverter implements FontScaleConverter {
    private final float fontScale;

    public LinearFontScaleConverter(float f10) {
        this.fontScale = f10;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final float getFontScale() {
        return this.fontScale;
    }

    public static /* synthetic */ LinearFontScaleConverter copy$default(LinearFontScaleConverter linearFontScaleConverter, float f10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = linearFontScaleConverter.fontScale;
        }
        return linearFontScaleConverter.copy(f10);
    }

    @Override // androidx.compose.ui.unit.fontscaling.FontScaleConverter
    public float convertDpToSp(float dp) {
        return dp / this.fontScale;
    }

    @Override // androidx.compose.ui.unit.fontscaling.FontScaleConverter
    public float convertSpToDp(float sp) {
        return sp * this.fontScale;
    }

    public final LinearFontScaleConverter copy(float fontScale) {
        return new LinearFontScaleConverter(fontScale);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LinearFontScaleConverter) && Float.compare(this.fontScale, ((LinearFontScaleConverter) other).fontScale) == 0;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.fontScale);
    }

    public String toString() {
        return a0.c.n(new StringBuilder("LinearFontScaleConverter(fontScale="), this.fontScale, ')');
    }
}
