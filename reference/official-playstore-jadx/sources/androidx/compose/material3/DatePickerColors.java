package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b \b\u0007\u0018\u00002\u00020\u0001BÏ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u008c\u0002\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001bø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J#\u0010&\u001a\u00020\u001b*\u0004\u0018\u00010\u001b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001b0\"H\u0000¢\u0006\u0004\b$\u0010%J5\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020,2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'H\u0001¢\u0006\u0004\b-\u0010.J-\u00102\u001a\b\u0012\u0004\u0012\u00020\u00020,2\u0006\u0010)\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010/\u001a\u00020'H\u0001¢\u0006\u0004\b0\u00101J-\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020,2\u0006\u00103\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010+\u001a\u00020'H\u0001¢\u0006\u0004\b4\u00101J%\u00107\u001a\b\u0012\u0004\u0012\u00020\u00020,2\u0006\u0010)\u001a\u00020'2\u0006\u0010+\u001a\u00020'H\u0001¢\u0006\u0004\b5\u00106J\u001a\u00109\u001a\u00020'2\b\u00108\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b9\u0010:J\u000f\u0010<\u001a\u00020;H\u0016¢\u0006\u0004\b<\u0010=R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010>\u001a\u0004\b?\u0010@R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010>\u001a\u0004\bA\u0010@R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010>\u001a\u0004\bB\u0010@R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010>\u001a\u0004\bC\u0010@R\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010>\u001a\u0004\bD\u0010@R\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010>\u001a\u0004\bE\u0010@R\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010>\u001a\u0004\bF\u0010@R\u001d\u0010\n\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010>\u001a\u0004\bG\u0010@R\u001d\u0010\u000b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010>\u001a\u0004\bH\u0010@R\u001d\u0010\f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\f\u0010>\u001a\u0004\bI\u0010@R\u001d\u0010\r\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\r\u0010>\u001a\u0004\bJ\u0010@R\u001d\u0010\u000e\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000e\u0010>\u001a\u0004\bK\u0010@R\u001d\u0010\u000f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000f\u0010>\u001a\u0004\bL\u0010@R\u001d\u0010\u0010\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0010\u0010>\u001a\u0004\bM\u0010@R\u001d\u0010\u0011\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0011\u0010>\u001a\u0004\bN\u0010@R\u001d\u0010\u0012\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0012\u0010>\u001a\u0004\bO\u0010@R\u001d\u0010\u0013\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0013\u0010>\u001a\u0004\bP\u0010@R\u001d\u0010\u0014\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0014\u0010>\u001a\u0004\bQ\u0010@R\u001d\u0010\u0015\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0015\u0010>\u001a\u0004\bR\u0010@R\u001d\u0010\u0016\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0016\u0010>\u001a\u0004\bS\u0010@R\u001d\u0010\u0017\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0017\u0010>\u001a\u0004\bT\u0010@R\u001d\u0010\u0018\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0018\u0010>\u001a\u0004\bU\u0010@R\u001d\u0010\u0019\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0019\u0010>\u001a\u0004\bV\u0010@R\u001d\u0010\u001a\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001a\u0010>\u001a\u0004\bW\u0010@R\u0017\u0010\u001c\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010X\u001a\u0004\bY\u0010Z\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006["}, d2 = {"Landroidx/compose/material3/DatePickerColors;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "titleContentColor", "headlineContentColor", "weekdayContentColor", "subheadContentColor", "navigationContentColor", "yearContentColor", "disabledYearContentColor", "currentYearContentColor", "selectedYearContentColor", "disabledSelectedYearContentColor", "selectedYearContainerColor", "disabledSelectedYearContainerColor", "dayContentColor", "disabledDayContentColor", "selectedDayContentColor", "disabledSelectedDayContentColor", "selectedDayContainerColor", "disabledSelectedDayContainerColor", "todayContentColor", "todayDateBorderColor", "dayInSelectionRangeContainerColor", "dayInSelectionRangeContentColor", "dividerColor", "Landroidx/compose/material3/TextFieldColors;", "dateTextFieldColors", "<init>", "(JJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/material3/TextFieldColors;Lkotlin/jvm/internal/h;)V", "copy-tNwlRmA", "(JJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/material3/TextFieldColors;)Landroidx/compose/material3/DatePickerColors;", "copy", "Lkotlin/Function0;", "block", "takeOrElse$material3_release", "(Landroidx/compose/material3/TextFieldColors;Lr7/a;)Landroidx/compose/material3/TextFieldColors;", "takeOrElse", "", "isToday", "selected", "inRange", "enabled", "Landroidx/compose/runtime/State;", "dayContentColor$material3_release", "(ZZZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "animate", "dayContainerColor$material3_release", "(ZZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "dayContainerColor", "currentYear", "yearContentColor$material3_release", "yearContainerColor$material3_release", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "yearContainerColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getContainerColor-0d7_KjU", "()J", "getTitleContentColor-0d7_KjU", "getHeadlineContentColor-0d7_KjU", "getWeekdayContentColor-0d7_KjU", "getSubheadContentColor-0d7_KjU", "getNavigationContentColor-0d7_KjU", "getYearContentColor-0d7_KjU", "getDisabledYearContentColor-0d7_KjU", "getCurrentYearContentColor-0d7_KjU", "getSelectedYearContentColor-0d7_KjU", "getDisabledSelectedYearContentColor-0d7_KjU", "getSelectedYearContainerColor-0d7_KjU", "getDisabledSelectedYearContainerColor-0d7_KjU", "getDayContentColor-0d7_KjU", "getDisabledDayContentColor-0d7_KjU", "getSelectedDayContentColor-0d7_KjU", "getDisabledSelectedDayContentColor-0d7_KjU", "getSelectedDayContainerColor-0d7_KjU", "getDisabledSelectedDayContainerColor-0d7_KjU", "getTodayContentColor-0d7_KjU", "getTodayDateBorderColor-0d7_KjU", "getDayInSelectionRangeContainerColor-0d7_KjU", "getDayInSelectionRangeContentColor-0d7_KjU", "getDividerColor-0d7_KjU", "Landroidx/compose/material3/TextFieldColors;", "getDateTextFieldColors", "()Landroidx/compose/material3/TextFieldColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DatePickerColors {
    public static final int $stable = 0;
    private final long containerColor;
    private final long currentYearContentColor;
    private final TextFieldColors dateTextFieldColors;
    private final long dayContentColor;
    private final long dayInSelectionRangeContainerColor;
    private final long dayInSelectionRangeContentColor;
    private final long disabledDayContentColor;
    private final long disabledSelectedDayContainerColor;
    private final long disabledSelectedDayContentColor;
    private final long disabledSelectedYearContainerColor;
    private final long disabledSelectedYearContentColor;
    private final long disabledYearContentColor;
    private final long dividerColor;
    private final long headlineContentColor;
    private final long navigationContentColor;
    private final long selectedDayContainerColor;
    private final long selectedDayContentColor;
    private final long selectedYearContainerColor;
    private final long selectedYearContentColor;
    private final long subheadContentColor;
    private final long titleContentColor;
    private final long todayContentColor;
    private final long todayDateBorderColor;
    private final long weekdayContentColor;
    private final long yearContentColor;

    public /* synthetic */ DatePickerColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, TextFieldColors textFieldColors, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, textFieldColors);
    }

    /* JADX INFO: renamed from: copy-tNwlRmA, reason: not valid java name */
    public final DatePickerColors m1450copytNwlRmA(long containerColor, long titleContentColor, long headlineContentColor, long weekdayContentColor, long subheadContentColor, long navigationContentColor, long yearContentColor, long disabledYearContentColor, long currentYearContentColor, long selectedYearContentColor, long disabledSelectedYearContentColor, long selectedYearContainerColor, long disabledSelectedYearContainerColor, long dayContentColor, long disabledDayContentColor, long selectedDayContentColor, long disabledSelectedDayContentColor, long selectedDayContainerColor, long disabledSelectedDayContainerColor, long todayContentColor, long todayDateBorderColor, long dayInSelectionRangeContainerColor, long dayInSelectionRangeContentColor, long dividerColor, TextFieldColors dateTextFieldColors) {
        Color.Companion companion = Color.INSTANCE;
        return new DatePickerColors(containerColor != companion.m3508getUnspecified0d7_KjU() ? containerColor : this.containerColor, titleContentColor != companion.m3508getUnspecified0d7_KjU() ? titleContentColor : this.titleContentColor, headlineContentColor != companion.m3508getUnspecified0d7_KjU() ? headlineContentColor : this.headlineContentColor, weekdayContentColor != companion.m3508getUnspecified0d7_KjU() ? weekdayContentColor : this.weekdayContentColor, subheadContentColor != companion.m3508getUnspecified0d7_KjU() ? subheadContentColor : this.subheadContentColor, navigationContentColor != companion.m3508getUnspecified0d7_KjU() ? navigationContentColor : this.navigationContentColor, yearContentColor != companion.m3508getUnspecified0d7_KjU() ? yearContentColor : this.yearContentColor, disabledYearContentColor != companion.m3508getUnspecified0d7_KjU() ? disabledYearContentColor : this.disabledYearContentColor, currentYearContentColor != companion.m3508getUnspecified0d7_KjU() ? currentYearContentColor : this.currentYearContentColor, selectedYearContentColor != companion.m3508getUnspecified0d7_KjU() ? selectedYearContentColor : this.selectedYearContentColor, disabledSelectedYearContentColor != companion.m3508getUnspecified0d7_KjU() ? disabledSelectedYearContentColor : this.disabledSelectedYearContentColor, selectedYearContainerColor != companion.m3508getUnspecified0d7_KjU() ? selectedYearContainerColor : this.selectedYearContainerColor, disabledSelectedYearContainerColor != companion.m3508getUnspecified0d7_KjU() ? disabledSelectedYearContainerColor : this.disabledSelectedYearContainerColor, dayContentColor != companion.m3508getUnspecified0d7_KjU() ? dayContentColor : this.dayContentColor, disabledDayContentColor != companion.m3508getUnspecified0d7_KjU() ? disabledDayContentColor : this.disabledDayContentColor, selectedDayContentColor != companion.m3508getUnspecified0d7_KjU() ? selectedDayContentColor : this.selectedDayContentColor, disabledSelectedDayContentColor != companion.m3508getUnspecified0d7_KjU() ? disabledSelectedDayContentColor : this.disabledSelectedDayContentColor, selectedDayContainerColor != companion.m3508getUnspecified0d7_KjU() ? selectedDayContainerColor : this.selectedDayContainerColor, disabledSelectedDayContainerColor != companion.m3508getUnspecified0d7_KjU() ? disabledSelectedDayContainerColor : this.disabledSelectedDayContainerColor, todayContentColor != companion.m3508getUnspecified0d7_KjU() ? todayContentColor : this.todayContentColor, todayDateBorderColor != companion.m3508getUnspecified0d7_KjU() ? todayDateBorderColor : this.todayDateBorderColor, dayInSelectionRangeContainerColor != companion.m3508getUnspecified0d7_KjU() ? dayInSelectionRangeContainerColor : this.dayInSelectionRangeContainerColor, dayInSelectionRangeContentColor != companion.m3508getUnspecified0d7_KjU() ? dayInSelectionRangeContentColor : this.dayInSelectionRangeContentColor, dividerColor != companion.m3508getUnspecified0d7_KjU() ? dividerColor : this.dividerColor, takeOrElse$material3_release(dateTextFieldColors, new DatePickerColors$copy$25(this)), null);
    }

    public final State<Color> dayContainerColor$material3_release(boolean z, boolean z5, boolean z10, Composer composer, int i10) {
        Composer composer2;
        State<Color> stateRememberUpdatedState;
        composer.startReplaceableGroup(-1240482658);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1240482658, i10, -1, "androidx.compose.material3.DatePickerColors.dayContainerColor (DatePicker.kt:924)");
        }
        long jM3507getTransparent0d7_KjU = z ? z5 ? this.selectedDayContainerColor : this.disabledSelectedDayContainerColor : Color.INSTANCE.m3507getTransparent0d7_KjU();
        if (z10) {
            composer.startReplaceableGroup(1577421952);
            composer2 = composer;
            stateRememberUpdatedState = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(jM3507getTransparent0d7_KjU, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer2, 0, 12);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composer;
            composer2.startReplaceableGroup(1577422116);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(jM3507getTransparent0d7_KjU), composer2, 0);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> dayContentColor$material3_release(boolean z, boolean z5, boolean z10, boolean z11, Composer composer, int i10) {
        Composer composer2;
        State<Color> stateM69animateColorAsStateeuL9pac;
        composer.startReplaceableGroup(-1233694918);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1233694918, i10, -1, "androidx.compose.material3.DatePickerColors.dayContentColor (DatePicker.kt:890)");
        }
        long j10 = (z5 && z11) ? this.selectedDayContentColor : (!z5 || z11) ? (z10 && z11) ? this.dayInSelectionRangeContentColor : (!z10 || z11) ? z ? this.todayContentColor : z11 ? this.dayContentColor : this.disabledDayContentColor : this.disabledDayContentColor : this.disabledSelectedDayContentColor;
        if (z10) {
            composer.startReplaceableGroup(379022200);
            stateM69animateColorAsStateeuL9pac = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(j10), composer, 0);
            composer.endReplaceableGroup();
            composer2 = composer;
        } else {
            composer.startReplaceableGroup(379022258);
            composer2 = composer;
            stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(j10, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer2, 0, 12);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    public boolean equals(Object other) {
        if (!(other instanceof DatePickerColors)) {
            return false;
        }
        DatePickerColors datePickerColors = (DatePickerColors) other;
        return Color.m3473equalsimpl0(this.containerColor, datePickerColors.containerColor) && Color.m3473equalsimpl0(this.titleContentColor, datePickerColors.titleContentColor) && Color.m3473equalsimpl0(this.headlineContentColor, datePickerColors.headlineContentColor) && Color.m3473equalsimpl0(this.weekdayContentColor, datePickerColors.weekdayContentColor) && Color.m3473equalsimpl0(this.subheadContentColor, datePickerColors.subheadContentColor) && Color.m3473equalsimpl0(this.yearContentColor, datePickerColors.yearContentColor) && Color.m3473equalsimpl0(this.disabledYearContentColor, datePickerColors.disabledYearContentColor) && Color.m3473equalsimpl0(this.currentYearContentColor, datePickerColors.currentYearContentColor) && Color.m3473equalsimpl0(this.selectedYearContentColor, datePickerColors.selectedYearContentColor) && Color.m3473equalsimpl0(this.disabledSelectedYearContentColor, datePickerColors.disabledSelectedYearContentColor) && Color.m3473equalsimpl0(this.selectedYearContainerColor, datePickerColors.selectedYearContainerColor) && Color.m3473equalsimpl0(this.disabledSelectedYearContainerColor, datePickerColors.disabledSelectedYearContainerColor) && Color.m3473equalsimpl0(this.dayContentColor, datePickerColors.dayContentColor) && Color.m3473equalsimpl0(this.disabledDayContentColor, datePickerColors.disabledDayContentColor) && Color.m3473equalsimpl0(this.selectedDayContentColor, datePickerColors.selectedDayContentColor) && Color.m3473equalsimpl0(this.disabledSelectedDayContentColor, datePickerColors.disabledSelectedDayContentColor) && Color.m3473equalsimpl0(this.selectedDayContainerColor, datePickerColors.selectedDayContainerColor) && Color.m3473equalsimpl0(this.disabledSelectedDayContainerColor, datePickerColors.disabledSelectedDayContainerColor) && Color.m3473equalsimpl0(this.todayContentColor, datePickerColors.todayContentColor) && Color.m3473equalsimpl0(this.todayDateBorderColor, datePickerColors.todayDateBorderColor) && Color.m3473equalsimpl0(this.dayInSelectionRangeContainerColor, datePickerColors.dayInSelectionRangeContainerColor) && Color.m3473equalsimpl0(this.dayInSelectionRangeContentColor, datePickerColors.dayInSelectionRangeContentColor);
    }

    /* JADX INFO: renamed from: getContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContainerColor() {
        return this.containerColor;
    }

    /* JADX INFO: renamed from: getCurrentYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCurrentYearContentColor() {
        return this.currentYearContentColor;
    }

    public final TextFieldColors getDateTextFieldColors() {
        return this.dateTextFieldColors;
    }

    /* JADX INFO: renamed from: getDayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDayContentColor() {
        return this.dayContentColor;
    }

    /* JADX INFO: renamed from: getDayInSelectionRangeContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDayInSelectionRangeContainerColor() {
        return this.dayInSelectionRangeContainerColor;
    }

    /* JADX INFO: renamed from: getDayInSelectionRangeContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDayInSelectionRangeContentColor() {
        return this.dayInSelectionRangeContentColor;
    }

    /* JADX INFO: renamed from: getDisabledDayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledDayContentColor() {
        return this.disabledDayContentColor;
    }

    /* JADX INFO: renamed from: getDisabledSelectedDayContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedDayContainerColor() {
        return this.disabledSelectedDayContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledSelectedDayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedDayContentColor() {
        return this.disabledSelectedDayContentColor;
    }

    /* JADX INFO: renamed from: getDisabledSelectedYearContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedYearContainerColor() {
        return this.disabledSelectedYearContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledSelectedYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedYearContentColor() {
        return this.disabledSelectedYearContentColor;
    }

    /* JADX INFO: renamed from: getDisabledYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledYearContentColor() {
        return this.disabledYearContentColor;
    }

    /* JADX INFO: renamed from: getDividerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDividerColor() {
        return this.dividerColor;
    }

    /* JADX INFO: renamed from: getHeadlineContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getHeadlineContentColor() {
        return this.headlineContentColor;
    }

    /* JADX INFO: renamed from: getNavigationContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getNavigationContentColor() {
        return this.navigationContentColor;
    }

    /* JADX INFO: renamed from: getSelectedDayContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedDayContainerColor() {
        return this.selectedDayContainerColor;
    }

    /* JADX INFO: renamed from: getSelectedDayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedDayContentColor() {
        return this.selectedDayContentColor;
    }

    /* JADX INFO: renamed from: getSelectedYearContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedYearContainerColor() {
        return this.selectedYearContainerColor;
    }

    /* JADX INFO: renamed from: getSelectedYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedYearContentColor() {
        return this.selectedYearContentColor;
    }

    /* JADX INFO: renamed from: getSubheadContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSubheadContentColor() {
        return this.subheadContentColor;
    }

    /* JADX INFO: renamed from: getTitleContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTitleContentColor() {
        return this.titleContentColor;
    }

    /* JADX INFO: renamed from: getTodayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTodayContentColor() {
        return this.todayContentColor;
    }

    /* JADX INFO: renamed from: getTodayDateBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTodayDateBorderColor() {
        return this.todayDateBorderColor;
    }

    /* JADX INFO: renamed from: getWeekdayContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getWeekdayContentColor() {
        return this.weekdayContentColor;
    }

    /* JADX INFO: renamed from: getYearContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getYearContentColor() {
        return this.yearContentColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.dayInSelectionRangeContentColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.containerColor) * 31, 31, this.titleContentColor), 31, this.headlineContentColor), 31, this.weekdayContentColor), 31, this.subheadContentColor), 31, this.yearContentColor), 31, this.disabledYearContentColor), 31, this.currentYearContentColor), 31, this.selectedYearContentColor), 31, this.disabledSelectedYearContentColor), 31, this.selectedYearContainerColor), 31, this.disabledSelectedYearContainerColor), 31, this.dayContentColor), 31, this.disabledDayContentColor), 31, this.selectedDayContentColor), 31, this.disabledSelectedDayContentColor), 31, this.selectedDayContainerColor), 31, this.disabledSelectedDayContainerColor), 31, this.todayContentColor), 31, this.todayDateBorderColor), 31, this.dayInSelectionRangeContainerColor);
    }

    public final TextFieldColors takeOrElse$material3_release(TextFieldColors textFieldColors, r7.a<TextFieldColors> aVar) {
        return textFieldColors == null ? (TextFieldColors) aVar.invoke() : textFieldColors;
    }

    public final State<Color> yearContainerColor$material3_release(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(-1306331107);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1306331107, i10, -1, "androidx.compose.material3.DatePickerColors.yearContainerColor (DatePicker.kt:974)");
        }
        State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(z ? z5 ? this.selectedYearContainerColor : this.disabledSelectedYearContainerColor : Color.INSTANCE.m3507getTransparent0d7_KjU(), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer, 0, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    public final State<Color> yearContentColor$material3_release(boolean z, boolean z5, boolean z10, Composer composer, int i10) {
        composer.startReplaceableGroup(874111097);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(874111097, i10, -1, "androidx.compose.material3.DatePickerColors.yearContentColor (DatePicker.kt:952)");
        }
        State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac((z5 && z10) ? this.selectedYearContentColor : (!z5 || z10) ? z ? this.currentYearContentColor : z10 ? this.yearContentColor : this.disabledYearContentColor : this.disabledSelectedYearContentColor, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer, 0, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    private DatePickerColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, TextFieldColors textFieldColors) {
        this.containerColor = j10;
        this.titleContentColor = j11;
        this.headlineContentColor = j12;
        this.weekdayContentColor = j13;
        this.subheadContentColor = j14;
        this.navigationContentColor = j15;
        this.yearContentColor = j16;
        this.disabledYearContentColor = j17;
        this.currentYearContentColor = j18;
        this.selectedYearContentColor = j19;
        this.disabledSelectedYearContentColor = j20;
        this.selectedYearContainerColor = j21;
        this.disabledSelectedYearContainerColor = j22;
        this.dayContentColor = j23;
        this.disabledDayContentColor = j24;
        this.selectedDayContentColor = j25;
        this.disabledSelectedDayContentColor = j26;
        this.selectedDayContainerColor = j27;
        this.disabledSelectedDayContainerColor = j28;
        this.todayContentColor = j29;
        this.todayDateBorderColor = j30;
        this.dayInSelectionRangeContainerColor = j31;
        this.dayInSelectionRangeContentColor = j32;
        this.dividerColor = j33;
        this.dateTextFieldColors = textFieldColors;
    }
}
