package androidx.compose.ui.unit;

import androidx.compose.ui.unit.fontscaling.FontScaleConverter;
import androidx.compose.ui.unit.fontscaling.FontScaleConverterFactory;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class b {
    public static float a(FontScaling fontScaling, long j10) {
        if (!TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(j10), TextUnitType.INSTANCE.m5898getSpUIouoOA())) {
            throw new IllegalStateException("Only Sp can convert to Px");
        }
        FontScaleConverterFactory fontScaleConverterFactory = FontScaleConverterFactory.INSTANCE;
        if (!fontScaleConverterFactory.isNonLinearFontScalingActive(fontScaling.getFontScale()) || FontScalingKt.getDisableNonLinearFontScalingInCompose()) {
            return Dp.m5678constructorimpl(fontScaling.getFontScale() * TextUnit.m5865getValueimpl(j10));
        }
        FontScaleConverter fontScaleConverterForScale = fontScaleConverterFactory.forScale(fontScaling.getFontScale());
        if (fontScaleConverterForScale != null) {
            return Dp.m5678constructorimpl(fontScaleConverterForScale.convertSpToDp(TextUnit.m5865getValueimpl(j10)));
        }
        return Dp.m5678constructorimpl(fontScaling.getFontScale() * TextUnit.m5865getValueimpl(j10));
    }

    public static long b(FontScaling fontScaling, float f10) {
        FontScaleConverterFactory fontScaleConverterFactory = FontScaleConverterFactory.INSTANCE;
        if (!fontScaleConverterFactory.isNonLinearFontScalingActive(fontScaling.getFontScale()) || FontScalingKt.getDisableNonLinearFontScalingInCompose()) {
            return TextUnitKt.getSp(f10 / fontScaling.getFontScale());
        }
        FontScaleConverter fontScaleConverterForScale = fontScaleConverterFactory.forScale(fontScaling.getFontScale());
        return TextUnitKt.getSp(fontScaleConverterForScale != null ? fontScaleConverterForScale.convertDpToSp(f10) : f10 / fontScaling.getFontScale());
    }
}
