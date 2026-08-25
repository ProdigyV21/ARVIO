package androidx.compose.material3;

import android.content.Context;
import android.os.Build;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0001\u001a\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001\u001a\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0001\u001a\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001\u001a\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0001\u001a\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0002\u001a \u0010\u0012\u001a\u00020\u0013*\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"delinearized", "", "rgbComponent", "", "dynamicDarkColorScheme", "Landroidx/compose/material3/ColorScheme;", "context", "Landroid/content/Context;", "dynamicDarkColorScheme31", "tonalPalette", "Landroidx/compose/material3/TonalPalette;", "dynamicDarkColorScheme34", "dynamicLightColorScheme", "dynamicLightColorScheme31", "dynamicLightColorScheme34", "dynamicTonalPalette", "labInvf", "ft", "setLuminance", "Landroidx/compose/ui/graphics/Color;", "newLuminance", "setLuminance-DxMtmZc", "(JF)J", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DynamicTonalPaletteKt {
    private static final int delinearized(float f10) {
        double d4 = f10 / 100;
        int iL = t7.a.L((d4 <= 0.0031308d ? d4 * 12.92d : (Math.pow(d4, 0.4166666666666667d) * 1.055d) - 0.055d) * 255.0d);
        if (iL < 0) {
            return 0;
        }
        if (iL > 255) {
            return 255;
        }
        return iL;
    }

    public static final ColorScheme dynamicDarkColorScheme(Context context) {
        return Build.VERSION.SDK_INT >= 34 ? dynamicDarkColorScheme34(context) : dynamicDarkColorScheme31(dynamicTonalPalette(context));
    }

    public static final ColorScheme dynamicDarkColorScheme31(TonalPalette tonalPalette) {
        long primary80 = tonalPalette.getPrimary80();
        long primary20 = tonalPalette.getPrimary20();
        long primary30 = tonalPalette.getPrimary30();
        long primary90 = tonalPalette.getPrimary90();
        long primary40 = tonalPalette.getPrimary40();
        long secondary80 = tonalPalette.getSecondary80();
        long secondary20 = tonalPalette.getSecondary20();
        long secondary30 = tonalPalette.getSecondary30();
        long secondary90 = tonalPalette.getSecondary90();
        long tertiary80 = tonalPalette.getTertiary80();
        long tertiary20 = tonalPalette.getTertiary20();
        long tertiary30 = tonalPalette.getTertiary30();
        long tertiary90 = tonalPalette.getTertiary90();
        long neutralVariant6 = tonalPalette.getNeutralVariant6();
        long neutralVariant90 = tonalPalette.getNeutralVariant90();
        long neutralVariant62 = tonalPalette.getNeutralVariant6();
        long neutralVariant902 = tonalPalette.getNeutralVariant90();
        long neutralVariant30 = tonalPalette.getNeutralVariant30();
        long neutralVariant80 = tonalPalette.getNeutralVariant80();
        long neutralVariant903 = tonalPalette.getNeutralVariant90();
        long neutralVariant20 = tonalPalette.getNeutralVariant20();
        long neutralVariant60 = tonalPalette.getNeutralVariant60();
        long neutralVariant302 = tonalPalette.getNeutralVariant30();
        long neutralVariant0 = tonalPalette.getNeutralVariant0();
        long neutralVariant24 = tonalPalette.getNeutralVariant24();
        long neutralVariant63 = tonalPalette.getNeutralVariant6();
        return ColorSchemeKt.m1396darkColorSchemeCXl9yA$default(primary80, primary20, primary30, primary90, primary40, secondary80, secondary20, secondary30, secondary90, tertiary80, tertiary20, tertiary30, tertiary90, neutralVariant6, neutralVariant90, neutralVariant62, neutralVariant902, neutralVariant30, neutralVariant80, tonalPalette.getPrimary80(), neutralVariant903, neutralVariant20, 0L, 0L, 0L, 0L, neutralVariant60, neutralVariant302, neutralVariant0, neutralVariant24, tonalPalette.getNeutralVariant12(), tonalPalette.getNeutralVariant17(), tonalPalette.getNeutralVariant22(), tonalPalette.getNeutralVariant10(), tonalPalette.getNeutralVariant4(), neutralVariant63, 62914560, 0, null);
    }

    public static final ColorScheme dynamicDarkColorScheme34(Context context) {
        ColorResourceHelper colorResourceHelper = ColorResourceHelper.INSTANCE;
        long jM1351getColorWaAFU9c = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_primary_dark);
        long jM1351getColorWaAFU9c2 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_primary_dark);
        long jM1351getColorWaAFU9c3 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_primary_container_dark);
        long jM1351getColorWaAFU9c4 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_primary_container_dark);
        long jM1351getColorWaAFU9c5 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_primary_light);
        long jM1351getColorWaAFU9c6 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_secondary_dark);
        long jM1351getColorWaAFU9c7 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_secondary_dark);
        long jM1351getColorWaAFU9c8 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_secondary_container_dark);
        long jM1351getColorWaAFU9c9 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_secondary_container_dark);
        long jM1351getColorWaAFU9c10 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_tertiary_dark);
        long jM1351getColorWaAFU9c11 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_tertiary_dark);
        long jM1351getColorWaAFU9c12 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_tertiary_container_dark);
        long jM1351getColorWaAFU9c13 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_tertiary_container_dark);
        long jM1351getColorWaAFU9c14 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_background_dark);
        long jM1351getColorWaAFU9c15 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_background_dark);
        long jM1351getColorWaAFU9c16 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_dark);
        long jM1351getColorWaAFU9c17 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_surface_dark);
        long jM1351getColorWaAFU9c18 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_variant_dark);
        long jM1351getColorWaAFU9c19 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_surface_variant_dark);
        long jM1351getColorWaAFU9c20 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_light);
        long jM1351getColorWaAFU9c21 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_surface_light);
        long jM1351getColorWaAFU9c22 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_outline_dark);
        long jM1351getColorWaAFU9c23 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_outline_variant_dark);
        long jM1351getColorWaAFU9c24 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_bright_dark);
        long jM1351getColorWaAFU9c25 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_dim_dark);
        return ColorSchemeKt.m1396darkColorSchemeCXl9yA$default(jM1351getColorWaAFU9c, jM1351getColorWaAFU9c2, jM1351getColorWaAFU9c3, jM1351getColorWaAFU9c4, jM1351getColorWaAFU9c5, jM1351getColorWaAFU9c6, jM1351getColorWaAFU9c7, jM1351getColorWaAFU9c8, jM1351getColorWaAFU9c9, jM1351getColorWaAFU9c10, jM1351getColorWaAFU9c11, jM1351getColorWaAFU9c12, jM1351getColorWaAFU9c13, jM1351getColorWaAFU9c14, jM1351getColorWaAFU9c15, jM1351getColorWaAFU9c16, jM1351getColorWaAFU9c17, jM1351getColorWaAFU9c18, jM1351getColorWaAFU9c19, colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_primary_dark), jM1351getColorWaAFU9c20, jM1351getColorWaAFU9c21, 0L, 0L, 0L, 0L, jM1351getColorWaAFU9c22, jM1351getColorWaAFU9c23, 0L, jM1351getColorWaAFU9c24, colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_dark), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_high_dark), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_highest_dark), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_low_dark), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_lowest_dark), jM1351getColorWaAFU9c25, 331350016, 0, null);
    }

    public static final ColorScheme dynamicLightColorScheme(Context context) {
        return Build.VERSION.SDK_INT >= 34 ? dynamicLightColorScheme34(context) : dynamicLightColorScheme31(dynamicTonalPalette(context));
    }

    public static final ColorScheme dynamicLightColorScheme31(TonalPalette tonalPalette) {
        long primary40 = tonalPalette.getPrimary40();
        long primary100 = tonalPalette.getPrimary100();
        long primary90 = tonalPalette.getPrimary90();
        long primary10 = tonalPalette.getPrimary10();
        long primary80 = tonalPalette.getPrimary80();
        long secondary40 = tonalPalette.getSecondary40();
        long secondary100 = tonalPalette.getSecondary100();
        long secondary90 = tonalPalette.getSecondary90();
        long secondary10 = tonalPalette.getSecondary10();
        long tertiary40 = tonalPalette.getTertiary40();
        long tertiary100 = tonalPalette.getTertiary100();
        long tertiary90 = tonalPalette.getTertiary90();
        long tertiary10 = tonalPalette.getTertiary10();
        long neutralVariant98 = tonalPalette.getNeutralVariant98();
        long neutralVariant10 = tonalPalette.getNeutralVariant10();
        long neutralVariant982 = tonalPalette.getNeutralVariant98();
        long neutralVariant102 = tonalPalette.getNeutralVariant10();
        long neutralVariant90 = tonalPalette.getNeutralVariant90();
        long neutralVariant30 = tonalPalette.getNeutralVariant30();
        long neutralVariant20 = tonalPalette.getNeutralVariant20();
        long neutralVariant95 = tonalPalette.getNeutralVariant95();
        long neutralVariant50 = tonalPalette.getNeutralVariant50();
        long neutralVariant80 = tonalPalette.getNeutralVariant80();
        long neutralVariant0 = tonalPalette.getNeutralVariant0();
        long neutralVariant983 = tonalPalette.getNeutralVariant98();
        long neutralVariant87 = tonalPalette.getNeutralVariant87();
        return ColorSchemeKt.m1400lightColorSchemeCXl9yA$default(primary40, primary100, primary90, primary10, primary80, secondary40, secondary100, secondary90, secondary10, tertiary40, tertiary100, tertiary90, tertiary10, neutralVariant98, neutralVariant10, neutralVariant982, neutralVariant102, neutralVariant90, neutralVariant30, tonalPalette.getPrimary40(), neutralVariant20, neutralVariant95, 0L, 0L, 0L, 0L, neutralVariant50, neutralVariant80, neutralVariant0, neutralVariant983, tonalPalette.getNeutralVariant94(), tonalPalette.getNeutralVariant92(), tonalPalette.getNeutralVariant90(), tonalPalette.getNeutralVariant96(), tonalPalette.getNeutralVariant100(), neutralVariant87, 62914560, 0, null);
    }

    public static final ColorScheme dynamicLightColorScheme34(Context context) {
        ColorResourceHelper colorResourceHelper = ColorResourceHelper.INSTANCE;
        long jM1351getColorWaAFU9c = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_primary_light);
        long jM1351getColorWaAFU9c2 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_primary_light);
        long jM1351getColorWaAFU9c3 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_primary_container_light);
        long jM1351getColorWaAFU9c4 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_primary_container_light);
        long jM1351getColorWaAFU9c5 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_primary_dark);
        long jM1351getColorWaAFU9c6 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_secondary_light);
        long jM1351getColorWaAFU9c7 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_secondary_light);
        long jM1351getColorWaAFU9c8 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_secondary_container_light);
        long jM1351getColorWaAFU9c9 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_secondary_container_light);
        long jM1351getColorWaAFU9c10 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_tertiary_light);
        long jM1351getColorWaAFU9c11 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_tertiary_light);
        long jM1351getColorWaAFU9c12 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_tertiary_container_light);
        long jM1351getColorWaAFU9c13 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_tertiary_container_light);
        long jM1351getColorWaAFU9c14 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_background_light);
        long jM1351getColorWaAFU9c15 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_background_light);
        long jM1351getColorWaAFU9c16 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_light);
        long jM1351getColorWaAFU9c17 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_surface_light);
        long jM1351getColorWaAFU9c18 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_variant_light);
        long jM1351getColorWaAFU9c19 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_surface_variant_light);
        long jM1351getColorWaAFU9c20 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_dark);
        long jM1351getColorWaAFU9c21 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_on_surface_dark);
        long jM1351getColorWaAFU9c22 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_outline_light);
        long jM1351getColorWaAFU9c23 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_outline_variant_light);
        long jM1351getColorWaAFU9c24 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_bright_light);
        long jM1351getColorWaAFU9c25 = colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_dim_light);
        return ColorSchemeKt.m1400lightColorSchemeCXl9yA$default(jM1351getColorWaAFU9c, jM1351getColorWaAFU9c2, jM1351getColorWaAFU9c3, jM1351getColorWaAFU9c4, jM1351getColorWaAFU9c5, jM1351getColorWaAFU9c6, jM1351getColorWaAFU9c7, jM1351getColorWaAFU9c8, jM1351getColorWaAFU9c9, jM1351getColorWaAFU9c10, jM1351getColorWaAFU9c11, jM1351getColorWaAFU9c12, jM1351getColorWaAFU9c13, jM1351getColorWaAFU9c14, jM1351getColorWaAFU9c15, jM1351getColorWaAFU9c16, jM1351getColorWaAFU9c17, jM1351getColorWaAFU9c18, jM1351getColorWaAFU9c19, colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_primary_light), jM1351getColorWaAFU9c20, jM1351getColorWaAFU9c21, 0L, 0L, 0L, 0L, jM1351getColorWaAFU9c22, jM1351getColorWaAFU9c23, 0L, jM1351getColorWaAFU9c24, colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_light), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_high_light), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_highest_light), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_low_light), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_surface_container_lowest_light), jM1351getColorWaAFU9c25, 331350016, 0, null);
    }

    public static final TonalPalette dynamicTonalPalette(Context context) {
        ColorResourceHelper colorResourceHelper = ColorResourceHelper.INSTANCE;
        return new TonalPalette(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_0), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_10), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 98.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 96.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_50), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 94.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 92.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_100), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 87.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_200), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_300), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_400), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_500), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_700), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 24.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 22.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_800), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 17.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 12.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_900), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 6.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_600), 4.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral1_1000), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_0), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_10), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 98.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 96.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_50), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 94.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 92.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_100), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 87.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_200), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_300), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_400), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_500), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_700), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 24.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 22.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_800), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 17.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 12.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_900), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 6.0f), m1548setLuminanceDxMtmZc(colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_600), 4.0f), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_neutral2_1000), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_0), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_10), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_50), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_100), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_200), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_300), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_400), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_500), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_600), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_700), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_800), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_900), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent1_1000), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_0), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_10), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_50), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_100), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_200), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_300), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_400), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_500), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_600), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_700), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_800), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_900), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent2_1000), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_0), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_10), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_50), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_100), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_200), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_300), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_400), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_500), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_600), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_700), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_800), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_900), colorResourceHelper.m1351getColorWaAFU9c(context, android.R.color.system_accent3_1000), null);
    }

    private static final float labInvf(float f10) {
        float f11 = f10 * f10 * f10;
        return f11 > 0.008856452f ? f11 : ((AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID * f10) - 16) / 903.2963f;
    }

    /* JADX INFO: renamed from: setLuminance-DxMtmZc, reason: not valid java name */
    public static final long m1548setLuminanceDxMtmZc(long j10, float f10) {
        double d4 = f10;
        if ((d4 < 1.0E-4d) || (d4 > 99.9999d)) {
            int iDelinearized = delinearized(100 * labInvf((f10 + 16) / AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID));
            return ColorKt.Color$default(iDelinearized, iDelinearized, iDelinearized, 0, 8, null);
        }
        ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
        long jM3469convertvNxB06k = Color.m3469convertvNxB06k(j10, colorSpaces.getCieLab());
        return Color.m3469convertvNxB06k(ColorKt.Color$default(f10, Color.m3464component2impl(jM3469convertvNxB06k), Color.m3465component3impl(jM3469convertvNxB06k), 0.0f, colorSpaces.getCieLab(), 8, null), colorSpaces.getSrgb());
    }
}
