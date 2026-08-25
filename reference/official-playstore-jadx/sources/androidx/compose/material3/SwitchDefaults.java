package androidx.compose.material3;

import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\r\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u000eJ²\u0001\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00102\b\b\u0002\u0010\u0017\u001a\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u00102\b\b\u0002\u0010\u001b\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\b\u001a\u00020\t*\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\""}, d2 = {"Landroidx/compose/material3/SwitchDefaults;", "", "()V", "IconSize", "Landroidx/compose/ui/unit/Dp;", "getIconSize-D9Ej5fM", "()F", "F", "defaultSwitchColors", "Landroidx/compose/material3/SwitchColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultSwitchColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SwitchColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SwitchColors;", "checkedThumbColor", "Landroidx/compose/ui/graphics/Color;", "checkedTrackColor", "checkedBorderColor", "checkedIconColor", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedBorderColor", "uncheckedIconColor", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledCheckedBorderColor", "disabledCheckedIconColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "disabledUncheckedBorderColor", "disabledUncheckedIconColor", "colors-V1nXRL4", "(JJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SwitchColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SwitchDefaults {
    public static final int $stable = 0;
    public static final SwitchDefaults INSTANCE = new SwitchDefaults();
    private static final float IconSize = Dp.m5678constructorimpl(16);

    private SwitchDefaults() {
    }

    public final SwitchColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(435552781);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(435552781, i10, -1, "androidx.compose.material3.SwitchDefaults.colors (Switch.kt:269)");
        }
        SwitchColors defaultSwitchColors$material3_release = getDefaultSwitchColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultSwitchColors$material3_release;
    }

    /* JADX INFO: renamed from: colors-V1nXRL4, reason: not valid java name */
    public final SwitchColors m2035colorsV1nXRL4(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, Composer composer, int i10, int i11, int i12) {
        composer.startReplaceableGroup(1937926421);
        long value = (i12 & 1) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getSelectedHandleColor(), composer, 6) : j10;
        long value2 = (i12 & 2) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getSelectedTrackColor(), composer, 6) : j11;
        long jM3507getTransparent0d7_KjU = (i12 & 4) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j12;
        long value3 = (i12 & 8) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getSelectedIconColor(), composer, 6) : j13;
        long value4 = (i12 & 16) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getUnselectedHandleColor(), composer, 6) : j14;
        long value5 = (i12 & 32) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getUnselectedTrackColor(), composer, 6) : j15;
        long value6 = (i12 & 64) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getUnselectedFocusTrackOutlineColor(), composer, 6) : j16;
        long value7 = (i12 & 128) != 0 ? ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getUnselectedIconColor(), composer, 6) : j17;
        long jM3517compositeOverOWjLjI = (i12 & 256) != 0 ? ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledSelectedHandleColor(), composer, 6), 1.0f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface()) : j18;
        long jM3517compositeOverOWjLjI2 = (i12 & 512) != 0 ? ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledSelectedTrackColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface()) : j19;
        long jM3507getTransparent0d7_KjU2 = (i12 & 1024) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j20;
        long jM3517compositeOverOWjLjI3 = (i12 & 2048) != 0 ? ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledSelectedIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface()) : j21;
        long jM3517compositeOverOWjLjI4 = (i12 & 4096) != 0 ? ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledUnselectedHandleColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface()) : j22;
        long jM3517compositeOverOWjLjI5 = (i12 & 8192) != 0 ? ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledUnselectedTrackColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface()) : j23;
        long jM3517compositeOverOWjLjI6 = (i12 & 16384) != 0 ? ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledUnselectedTrackOutlineColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface()) : j24;
        long jM3517compositeOverOWjLjI7 = (i12 & 32768) != 0 ? ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(SwitchTokens.INSTANCE.getDisabledUnselectedIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface()) : j25;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1937926421, i10, i11, "androidx.compose.material3.SwitchDefaults.colors (Switch.kt:325)");
        }
        SwitchColors switchColors = new SwitchColors(value, value2, jM3507getTransparent0d7_KjU, value3, value4, value5, value6, value7, jM3517compositeOverOWjLjI, jM3517compositeOverOWjLjI2, jM3507getTransparent0d7_KjU2, jM3517compositeOverOWjLjI3, jM3517compositeOverOWjLjI4, jM3517compositeOverOWjLjI5, jM3517compositeOverOWjLjI6, jM3517compositeOverOWjLjI7, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return switchColors;
    }

    public final SwitchColors getDefaultSwitchColors$material3_release(ColorScheme colorScheme) {
        SwitchColors defaultSwitchColorsCached = colorScheme.getDefaultSwitchColorsCached();
        if (defaultSwitchColorsCached != null) {
            return defaultSwitchColorsCached;
        }
        SwitchTokens switchTokens = SwitchTokens.INSTANCE;
        long jFromToken = ColorSchemeKt.fromToken(colorScheme, switchTokens.getSelectedHandleColor());
        long jFromToken2 = ColorSchemeKt.fromToken(colorScheme, switchTokens.getSelectedTrackColor());
        Color.Companion companion = Color.INSTANCE;
        SwitchColors switchColors = new SwitchColors(jFromToken, jFromToken2, companion.m3507getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, switchTokens.getSelectedIconColor()), ColorSchemeKt.fromToken(colorScheme, switchTokens.getUnselectedHandleColor()), ColorSchemeKt.fromToken(colorScheme, switchTokens.getUnselectedTrackColor()), ColorSchemeKt.fromToken(colorScheme, switchTokens.getUnselectedFocusTrackOutlineColor()), ColorSchemeKt.fromToken(colorScheme, switchTokens.getUnselectedIconColor()), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledSelectedHandleColor()), 1.0f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledSelectedTrackColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), companion.m3507getTransparent0d7_KjU(), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledSelectedIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledUnselectedHandleColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledUnselectedTrackColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledUnselectedTrackOutlineColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledUnselectedIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), null);
        colorScheme.setDefaultSwitchColorsCached$material3_release(switchColors);
        return switchColors;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m2036getIconSizeD9Ej5fM() {
        return IconSize;
    }
}
