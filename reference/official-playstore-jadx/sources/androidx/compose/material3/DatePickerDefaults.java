package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.material3.tokens.DividerTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u008e\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00072\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J+\u0010)\u001a\u00020(2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$¢\u0006\u0004\b)\u0010*J$\u00102\u001a\u00020/2\u0006\u0010,\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020-H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u00101J6\u00107\u001a\u00020/2\b\u00104\u001a\u0004\u0018\u0001032\u0006\u0010,\u001a\u00020+2\u0006\u0010)\u001a\u00020(2\b\b\u0002\u0010.\u001a\u00020-H\u0007ø\u0001\u0000¢\u0006\u0004\b5\u00106J'\u0010@\u001a\u00020=2\u0006\u00109\u001a\u0002082\u000e\b\u0002\u0010<\u001a\b\u0012\u0004\u0012\u00020;0:H\u0001¢\u0006\u0004\b>\u0010?R\u0017\u0010B\u001a\u00020A8\u0006¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER\u001d\u0010G\u001a\u00020F8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u0017\u0010L\u001a\u00020K8\u0006¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010OR\u0014\u0010P\u001a\u00020$8\u0006X\u0086T¢\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010R\u001a\u00020$8\u0006X\u0086T¢\u0006\u0006\n\u0004\bR\u0010QR\u0014\u0010S\u001a\u00020$8\u0006X\u0086T¢\u0006\u0006\n\u0004\bS\u0010QR\u0018\u0010W\u001a\u00020\u0004*\u00020T8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0011\u0010[\u001a\u00020X8G¢\u0006\u0006\u001a\u0004\bY\u0010Z\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\\"}, d2 = {"Landroidx/compose/material3/DatePickerDefaults;", "", "<init>", "()V", "Landroidx/compose/material3/DatePickerColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/DatePickerColors;", "Landroidx/compose/ui/graphics/Color;", "containerColor", "titleContentColor", "headlineContentColor", "weekdayContentColor", "subheadContentColor", "navigationContentColor", "yearContentColor", "disabledYearContentColor", "currentYearContentColor", "selectedYearContentColor", "disabledSelectedYearContentColor", "selectedYearContainerColor", "disabledSelectedYearContainerColor", "dayContentColor", "disabledDayContentColor", "selectedDayContentColor", "disabledSelectedDayContentColor", "selectedDayContainerColor", "disabledSelectedDayContainerColor", "todayContentColor", "todayDateBorderColor", "dayInSelectionRangeContentColor", "dayInSelectionRangeContainerColor", "dividerColor", "Landroidx/compose/material3/TextFieldColors;", "dateTextFieldColors", "colors-bSRYm20", "(JJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)Landroidx/compose/material3/DatePickerColors;", "", "yearSelectionSkeleton", "selectedDateSkeleton", "selectedDateDescriptionSkeleton", "Landroidx/compose/material3/DatePickerFormatter;", "dateFormatter", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroidx/compose/material3/DatePickerFormatter;", "Landroidx/compose/material3/DisplayMode;", "displayMode", "Landroidx/compose/ui/Modifier;", "modifier", "Lx6/t0;", "DatePickerTitle-hOD91z4", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "DatePickerTitle", "", "selectedDateMillis", "DatePickerHeadline-3kbWawI", "(Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "DatePickerHeadline", "Landroidx/compose/foundation/lazy/LazyListState;", "lazyListState", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "decayAnimationSpec", "Landroidx/compose/foundation/gestures/FlingBehavior;", "rememberSnapFlingBehavior$material3_release", "(Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/FlingBehavior;", "rememberSnapFlingBehavior", "Lx7/i;", "YearRange", "Lx7/i;", "getYearRange", "()Lx7/i;", "Landroidx/compose/ui/unit/Dp;", "TonalElevation", "F", "getTonalElevation-D9Ej5fM", "()F", "Landroidx/compose/material3/SelectableDates;", "AllDates", "Landroidx/compose/material3/SelectableDates;", "getAllDates", "()Landroidx/compose/material3/SelectableDates;", "YearMonthSkeleton", "Ljava/lang/String;", "YearAbbrMonthDaySkeleton", "YearMonthWeekdayDaySkeleton", "Landroidx/compose/material3/ColorScheme;", "getDefaultDatePickerColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/DatePickerColors;", "defaultDatePickerColors", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "shape", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DatePickerDefaults {
    public static final int $stable = 0;
    public static final String YearAbbrMonthDaySkeleton = "yMMMd";
    public static final String YearMonthSkeleton = "yMMMM";
    public static final String YearMonthWeekdayDaySkeleton = "yMMMMEEEEd";
    public static final DatePickerDefaults INSTANCE = new DatePickerDefaults();
    private static final i YearRange = new i(1900, 2100, 1);
    private static final float TonalElevation = DatePickerModalTokens.INSTANCE.m2472getContainerElevationD9Ej5fM();
    private static final SelectableDates AllDates = new SelectableDates() { // from class: androidx.compose.material3.DatePickerDefaults$AllDates$1
        @Override // androidx.compose.material3.SelectableDates
        public final /* synthetic */ boolean isSelectableDate(long j10) {
            return f.a(this, j10);
        }

        @Override // androidx.compose.material3.SelectableDates
        public final /* synthetic */ boolean isSelectableYear(int i10) {
            return f.b(this, i10);
        }
    };

    private DatePickerDefaults() {
    }

    public static /* synthetic */ DatePickerFormatter dateFormatter$default(DatePickerDefaults datePickerDefaults, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = YearMonthSkeleton;
        }
        if ((i10 & 2) != 0) {
            str2 = YearAbbrMonthDaySkeleton;
        }
        if ((i10 & 4) != 0) {
            str3 = YearMonthWeekdayDaySkeleton;
        }
        return datePickerDefaults.dateFormatter(str, str2, str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DatePickerHeadline-3kbWawI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m1475DatePickerHeadline3kbWawI(java.lang.Long r34, int r35, androidx.compose.material3.DatePickerFormatter r36, androidx.compose.ui.Modifier r37, androidx.compose.runtime.Composer r38, int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 560
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerDefaults.m1475DatePickerHeadline3kbWawI(java.lang.Long, int, androidx.compose.material3.DatePickerFormatter, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DatePickerTitle-hOD91z4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m1476DatePickerTitlehOD91z4(int r29, androidx.compose.ui.Modifier r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerDefaults.m1476DatePickerTitlehOD91z4(int, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    public final DatePickerColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(-275219611);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-275219611, i10, -1, "androidx.compose.material3.DatePickerDefaults.colors (DatePicker.kt:433)");
        }
        DatePickerColors defaultDatePickerColors = getDefaultDatePickerColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i10 << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultDatePickerColors;
    }

    /* JADX INFO: renamed from: colors-bSRYm20, reason: not valid java name */
    public final DatePickerColors m1477colorsbSRYm20(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, TextFieldColors textFieldColors, Composer composer, int i10, int i11, int i12, int i13) {
        composer.startReplaceableGroup(1991626358);
        long jM3508getUnspecified0d7_KjU = (i13 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i13 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i13 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i13 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i13 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        long jM3508getUnspecified0d7_KjU6 = (i13 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j15;
        long jM3508getUnspecified0d7_KjU7 = (i13 & 64) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j16;
        long jM3508getUnspecified0d7_KjU8 = (i13 & 128) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j17;
        long jM3508getUnspecified0d7_KjU9 = (i13 & 256) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j18;
        long jM3508getUnspecified0d7_KjU10 = (i13 & 512) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j19;
        long jM3508getUnspecified0d7_KjU11 = (i13 & 1024) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j20;
        long jM3508getUnspecified0d7_KjU12 = (i13 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j21;
        long jM3508getUnspecified0d7_KjU13 = (i13 & 4096) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j22;
        long jM3508getUnspecified0d7_KjU14 = (i13 & 8192) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j23;
        long jM3508getUnspecified0d7_KjU15 = (i13 & 16384) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j24;
        long jM3508getUnspecified0d7_KjU16 = (32768 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j25;
        long jM3508getUnspecified0d7_KjU17 = (65536 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j26;
        long jM3508getUnspecified0d7_KjU18 = (131072 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j27;
        long jM3508getUnspecified0d7_KjU19 = (262144 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j28;
        long jM3508getUnspecified0d7_KjU20 = (524288 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j29;
        long jM3508getUnspecified0d7_KjU21 = (1048576 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j30;
        long jM3508getUnspecified0d7_KjU22 = (2097152 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j31;
        long jM3508getUnspecified0d7_KjU23 = (4194304 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j32;
        long jM3508getUnspecified0d7_KjU24 = (8388608 & i13) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j33;
        TextFieldColors textFieldColors2 = (i13 & 16777216) != 0 ? null : textFieldColors;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1991626358, i10, i11, "androidx.compose.material3.DatePickerDefaults.colors (DatePicker.kt:502)");
        }
        DatePickerColors datePickerColorsM1450copytNwlRmA = getDefaultDatePickerColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i12 >> 12) & 112).m1450copytNwlRmA(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5, jM3508getUnspecified0d7_KjU6, jM3508getUnspecified0d7_KjU7, jM3508getUnspecified0d7_KjU8, jM3508getUnspecified0d7_KjU9, jM3508getUnspecified0d7_KjU10, jM3508getUnspecified0d7_KjU11, jM3508getUnspecified0d7_KjU12, jM3508getUnspecified0d7_KjU13, jM3508getUnspecified0d7_KjU14, jM3508getUnspecified0d7_KjU15, jM3508getUnspecified0d7_KjU16, jM3508getUnspecified0d7_KjU17, jM3508getUnspecified0d7_KjU18, jM3508getUnspecified0d7_KjU19, jM3508getUnspecified0d7_KjU20, jM3508getUnspecified0d7_KjU21, jM3508getUnspecified0d7_KjU23, jM3508getUnspecified0d7_KjU22, jM3508getUnspecified0d7_KjU24, textFieldColors2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return datePickerColorsM1450copytNwlRmA;
    }

    public final DatePickerFormatter dateFormatter(String yearSelectionSkeleton, String selectedDateSkeleton, String selectedDateDescriptionSkeleton) {
        return new DatePickerFormatterImpl(yearSelectionSkeleton, selectedDateSkeleton, selectedDateDescriptionSkeleton);
    }

    public final SelectableDates getAllDates() {
        return AllDates;
    }

    public final DatePickerColors getDefaultDatePickerColors(ColorScheme colorScheme, Composer composer, int i10) {
        composer.startReplaceableGroup(1180555308);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1180555308, i10, -1, "androidx.compose.material3.DatePickerDefaults.<get-defaultDatePickerColors> (DatePicker.kt:532)");
        }
        DatePickerColors defaultDatePickerColorsCached = colorScheme.getDefaultDatePickerColorsCached();
        if (defaultDatePickerColorsCached == null) {
            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
            DatePickerColors datePickerColors = new DatePickerColors(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getHeaderSupportingTextColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getHeaderHeadlineColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getWeekdaysLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getRangeSelectionMonthSubheadColor()), colorScheme.getOnSurfaceVariant(), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearUnselectedLabelTextColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearUnselectedLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateTodayLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearSelectedLabelTextColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearSelectedLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearSelectedContainerColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionYearSelectedContainerColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateUnselectedLabelTextColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateUnselectedLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateSelectedLabelTextColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateSelectedLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateSelectedContainerColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateSelectedContainerColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateTodayLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getDateTodayContainerOutlineColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getRangeSelectionActiveIndicatorContainerColor()), ColorSchemeKt.fromToken(colorScheme, datePickerModalTokens.getSelectionDateInRangeLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, DividerTokens.INSTANCE.getColor()), OutlinedTextFieldDefaults.INSTANCE.getDefaultOutlinedTextFieldColors(colorScheme, composer, (i10 & 14) | 48), null);
            colorScheme.setDefaultDatePickerColorsCached$material3_release(datePickerColors);
            defaultDatePickerColorsCached = datePickerColors;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultDatePickerColorsCached;
    }

    public final Shape getShape(Composer composer, int i10) {
        composer.startReplaceableGroup(700927667);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(700927667, i10, -1, "androidx.compose.material3.DatePickerDefaults.<get-shape> (DatePicker.kt:719)");
        }
        Shape value = ShapesKt.getValue(DatePickerModalTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getTonalElevation-D9Ej5fM, reason: not valid java name */
    public final float m1478getTonalElevationD9Ej5fM() {
        return TonalElevation;
    }

    public final i getYearRange() {
        return YearRange;
    }

    public final FlingBehavior rememberSnapFlingBehavior$material3_release(LazyListState lazyListState, DecayAnimationSpec<Float> decayAnimationSpec, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-2036003494);
        if ((i11 & 2) != 0) {
            decayAnimationSpec = DecayAnimationSpecKt.exponentialDecay$default(0.0f, 0.0f, 3, null);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2036003494, i10, -1, "androidx.compose.material3.DatePickerDefaults.rememberSnapFlingBehavior (DatePicker.kt:700)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        composer.startReplaceableGroup(-1872611444);
        boolean zChanged = composer.changed(density);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SnapFlingBehavior(lazyListState, decayAnimationSpec, AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), density);
            composer.updateRememberedValue(objRememberedValue);
        }
        SnapFlingBehavior snapFlingBehavior = (SnapFlingBehavior) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return snapFlingBehavior;
    }
}
