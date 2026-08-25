package androidx.compose.material3;

import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\b\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\tJ\u009e\u0001\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u001cH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eR\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001f"}, d2 = {"Landroidx/compose/material3/TimePickerDefaults;", "", "()V", "defaultTimePickerColors", "Landroidx/compose/material3/TimePickerColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultTimePickerColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/TimePickerColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TimePickerColors;", "clockDialColor", "Landroidx/compose/ui/graphics/Color;", "clockDialSelectedContentColor", "clockDialUnselectedContentColor", "selectorColor", "containerColor", "periodSelectorBorderColor", "periodSelectorSelectedContainerColor", "periodSelectorUnselectedContainerColor", "periodSelectorSelectedContentColor", "periodSelectorUnselectedContentColor", "timeSelectorSelectedContainerColor", "timeSelectorUnselectedContainerColor", "timeSelectorSelectedContentColor", "timeSelectorUnselectedContentColor", "colors-u3YEpmA", "(JJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TimePickerColors;", "layoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "layoutType-sDNSZnc", "(Landroidx/compose/runtime/Composer;I)I", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TimePickerDefaults {
    public static final int $stable = 0;
    public static final TimePickerDefaults INSTANCE = new TimePickerDefaults();

    private TimePickerDefaults() {
    }

    public final TimePickerColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(-2085808058);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2085808058, i10, -1, "androidx.compose.material3.TimePickerDefaults.colors (TimePicker.kt:263)");
        }
        TimePickerColors defaultTimePickerColors$material3_release = getDefaultTimePickerColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultTimePickerColors$material3_release;
    }

    /* JADX INFO: renamed from: colors-u3YEpmA, reason: not valid java name */
    public final TimePickerColors m2177colorsu3YEpmA(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, Composer composer, int i10, int i11, int i12) {
        composer.startReplaceableGroup(-646352288);
        long jM3508getUnspecified0d7_KjU = (i12 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i12 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i12 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i12 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i12 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        long jM3508getUnspecified0d7_KjU6 = (i12 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j15;
        long jM3508getUnspecified0d7_KjU7 = (i12 & 64) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j16;
        long jM3508getUnspecified0d7_KjU8 = (i12 & 128) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j17;
        long jM3508getUnspecified0d7_KjU9 = (i12 & 256) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j18;
        long jM3508getUnspecified0d7_KjU10 = (i12 & 512) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j19;
        long jM3508getUnspecified0d7_KjU11 = (i12 & 1024) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j20;
        long jM3508getUnspecified0d7_KjU12 = (i12 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j21;
        long jM3508getUnspecified0d7_KjU13 = (i12 & 4096) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j22;
        long jM3508getUnspecified0d7_KjU14 = (i12 & 8192) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j23;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-646352288, i10, i11, "androidx.compose.material3.TimePickerDefaults.colors (TimePicker.kt:309)");
        }
        TimePickerColors timePickerColorsM2158copydVHXu7A = getDefaultTimePickerColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m2158copydVHXu7A(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5, jM3508getUnspecified0d7_KjU6, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU7, jM3508getUnspecified0d7_KjU8, jM3508getUnspecified0d7_KjU9, jM3508getUnspecified0d7_KjU10, jM3508getUnspecified0d7_KjU11, jM3508getUnspecified0d7_KjU12, jM3508getUnspecified0d7_KjU13, jM3508getUnspecified0d7_KjU14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return timePickerColorsM2158copydVHXu7A;
    }

    public final TimePickerColors getDefaultTimePickerColors$material3_release(ColorScheme colorScheme) {
        TimePickerColors defaultTimePickerColorsCached = colorScheme.getDefaultTimePickerColorsCached();
        if (defaultTimePickerColorsCached != null) {
            return defaultTimePickerColorsCached;
        }
        TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
        TimePickerColors timePickerColors = new TimePickerColors(ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getClockDialColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getClockDialSelectorHandleContainerColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getPeriodSelectorOutlineColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getClockDialSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getClockDialUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getPeriodSelectorSelectedContainerColor()), Color.INSTANCE.m3507getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getPeriodSelectorSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getPeriodSelectorUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getTimeSelectorSelectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getTimeSelectorUnselectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getTimeSelectorSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getTimeSelectorUnselectedLabelTextColor()), null);
        colorScheme.setDefaultTimePickerColorsCached$material3_release(timePickerColors);
        return timePickerColors;
    }

    /* JADX INFO: renamed from: layoutType-sDNSZnc, reason: not valid java name */
    public final int m2178layoutTypesDNSZnc(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(517161502, i10, -1, "androidx.compose.material3.TimePickerDefaults.layoutType (TimePicker.kt:358)");
        }
        int defaultTimePickerLayoutType = TimePicker_androidKt.getDefaultTimePickerLayoutType(composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTimePickerLayoutType;
    }
}
