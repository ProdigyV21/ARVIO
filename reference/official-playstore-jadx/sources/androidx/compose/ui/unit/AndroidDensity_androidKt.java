package androidx.compose.ui.unit;

import android.content.Context;
import androidx.compose.ui.unit.fontscaling.FontScaleConverter;
import androidx.compose.ui.unit.fontscaling.FontScaleConverterFactory;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"Density", "Landroidx/compose/ui/unit/Density;", "context", "Landroid/content/Context;", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidDensity_androidKt {
    public static final Density Density(Context context) {
        FontScaleConverter fontScaleConverterForScale;
        float f10 = context.getResources().getConfiguration().fontScale;
        if (FontScalingKt.getDisableNonLinearFontScalingInCompose() || (fontScaleConverterForScale = FontScaleConverterFactory.INSTANCE.forScale(f10)) == null) {
            fontScaleConverterForScale = new LinearFontScaleConverter(f10);
        }
        return new DensityWithConverter(context.getResources().getDisplayMetrics().density, f10, fontScaleConverterForScale);
    }
}
