package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0013\b\u0007\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u009c\u0001\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0019J\u001d\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0019J\u001d\u0010 \u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0019J\u001d\u0010\"\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u0019J\u001a\u0010$\u001a\u00020\u00162\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b$\u0010%J\u000f\u0010'\u001a\u00020&H\u0016¢\u0006\u0004\b'\u0010(R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010+R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b,\u0010+R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010)\u001a\u0004\b-\u0010+R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b.\u0010+R\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010)\u001a\u0004\b/\u0010+R\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010)\u001a\u0004\b0\u0010+R\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010)\u001a\u0004\b1\u0010+R\u001d\u0010\n\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010)\u001a\u0004\b2\u0010+R\u001d\u0010\u000b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010)\u001a\u0004\b3\u0010+R\u001d\u0010\f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\f\u0010)\u001a\u0004\b4\u0010+R\u001d\u0010\r\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\r\u0010)\u001a\u0004\b5\u0010+R\u001d\u0010\u000e\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000e\u0010)\u001a\u0004\b6\u0010+R\u001d\u0010\u000f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000f\u0010)\u001a\u0004\b7\u0010+R\u001d\u0010\u0010\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0010\u0010)\u001a\u0004\b8\u0010+\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00069"}, d2 = {"Landroidx/compose/material3/TimePickerColors;", "", "Landroidx/compose/ui/graphics/Color;", "clockDialColor", "selectorColor", "containerColor", "periodSelectorBorderColor", "clockDialSelectedContentColor", "clockDialUnselectedContentColor", "periodSelectorSelectedContainerColor", "periodSelectorUnselectedContainerColor", "periodSelectorSelectedContentColor", "periodSelectorUnselectedContentColor", "timeSelectorSelectedContainerColor", "timeSelectorUnselectedContainerColor", "timeSelectorSelectedContentColor", "timeSelectorUnselectedContentColor", "<init>", "(JJJJJJJJJJJJJJLkotlin/jvm/internal/h;)V", "copy-dVHXu7A", "(JJJJJJJJJJJJJJ)Landroidx/compose/material3/TimePickerColors;", "copy", "", "selected", "periodSelectorContainerColor-vNxB06k$material3_release", "(Z)J", "periodSelectorContainerColor", "periodSelectorContentColor-vNxB06k$material3_release", "periodSelectorContentColor", "timeSelectorContainerColor-vNxB06k$material3_release", "timeSelectorContainerColor", "timeSelectorContentColor-vNxB06k$material3_release", "timeSelectorContentColor", "clockDialContentColor-vNxB06k$material3_release", "clockDialContentColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getClockDialColor-0d7_KjU", "()J", "getSelectorColor-0d7_KjU", "getContainerColor-0d7_KjU", "getPeriodSelectorBorderColor-0d7_KjU", "getClockDialSelectedContentColor-0d7_KjU", "getClockDialUnselectedContentColor-0d7_KjU", "getPeriodSelectorSelectedContainerColor-0d7_KjU", "getPeriodSelectorUnselectedContainerColor-0d7_KjU", "getPeriodSelectorSelectedContentColor-0d7_KjU", "getPeriodSelectorUnselectedContentColor-0d7_KjU", "getTimeSelectorSelectedContainerColor-0d7_KjU", "getTimeSelectorUnselectedContainerColor-0d7_KjU", "getTimeSelectorSelectedContentColor-0d7_KjU", "getTimeSelectorUnselectedContentColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TimePickerColors {
    public static final int $stable = 0;
    private final long clockDialColor;
    private final long clockDialSelectedContentColor;
    private final long clockDialUnselectedContentColor;
    private final long containerColor;
    private final long periodSelectorBorderColor;
    private final long periodSelectorSelectedContainerColor;
    private final long periodSelectorSelectedContentColor;
    private final long periodSelectorUnselectedContainerColor;
    private final long periodSelectorUnselectedContentColor;
    private final long selectorColor;
    private final long timeSelectorSelectedContainerColor;
    private final long timeSelectorSelectedContentColor;
    private final long timeSelectorUnselectedContainerColor;
    private final long timeSelectorUnselectedContentColor;

    public /* synthetic */ TimePickerColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23);
    }

    /* JADX INFO: renamed from: copy-dVHXu7A$default, reason: not valid java name */
    public static /* synthetic */ TimePickerColors m2156copydVHXu7A$default(TimePickerColors timePickerColors, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, int i10, Object obj) {
        long j24;
        long j25;
        long j26 = (i10 & 1) != 0 ? timePickerColors.containerColor : j10;
        long j27 = (i10 & 2) != 0 ? timePickerColors.selectorColor : j11;
        long j28 = (i10 & 4) != 0 ? timePickerColors.containerColor : j12;
        long j29 = (i10 & 8) != 0 ? timePickerColors.periodSelectorBorderColor : j13;
        long j30 = (i10 & 16) != 0 ? timePickerColors.clockDialSelectedContentColor : j14;
        long j31 = (i10 & 32) != 0 ? timePickerColors.clockDialUnselectedContentColor : j15;
        long j32 = (i10 & 64) != 0 ? timePickerColors.periodSelectorSelectedContainerColor : j16;
        long j33 = j26;
        long j34 = (i10 & 128) != 0 ? timePickerColors.periodSelectorUnselectedContainerColor : j17;
        long j35 = (i10 & 256) != 0 ? timePickerColors.periodSelectorSelectedContentColor : j18;
        long j36 = (i10 & 512) != 0 ? timePickerColors.periodSelectorUnselectedContentColor : j19;
        long j37 = (i10 & 1024) != 0 ? timePickerColors.timeSelectorSelectedContainerColor : j20;
        long j38 = (i10 & 2048) != 0 ? timePickerColors.timeSelectorUnselectedContainerColor : j21;
        long j39 = (i10 & 4096) != 0 ? timePickerColors.timeSelectorSelectedContentColor : j22;
        if ((i10 & 8192) != 0) {
            j25 = j39;
            j24 = timePickerColors.timeSelectorUnselectedContentColor;
        } else {
            j24 = j23;
            j25 = j39;
        }
        return timePickerColors.m2158copydVHXu7A(j33, j27, j28, j29, j30, j31, j32, j34, j35, j36, j37, j38, j25, j24);
    }

    /* JADX INFO: renamed from: clockDialContentColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m2157clockDialContentColorvNxB06k$material3_release(boolean selected) {
        return selected ? this.clockDialSelectedContentColor : this.clockDialUnselectedContentColor;
    }

    /* JADX INFO: renamed from: copy-dVHXu7A, reason: not valid java name */
    public final TimePickerColors m2158copydVHXu7A(long clockDialColor, long selectorColor, long containerColor, long periodSelectorBorderColor, long clockDialSelectedContentColor, long clockDialUnselectedContentColor, long periodSelectorSelectedContainerColor, long periodSelectorUnselectedContainerColor, long periodSelectorSelectedContentColor, long periodSelectorUnselectedContentColor, long timeSelectorSelectedContainerColor, long timeSelectorUnselectedContainerColor, long timeSelectorSelectedContentColor, long timeSelectorUnselectedContentColor) {
        Color.Companion companion = Color.INSTANCE;
        return new TimePickerColors(clockDialColor != companion.m3508getUnspecified0d7_KjU() ? clockDialColor : this.clockDialColor, selectorColor != companion.m3508getUnspecified0d7_KjU() ? selectorColor : this.selectorColor, containerColor != companion.m3508getUnspecified0d7_KjU() ? containerColor : this.containerColor, periodSelectorBorderColor != companion.m3508getUnspecified0d7_KjU() ? periodSelectorBorderColor : this.periodSelectorBorderColor, clockDialSelectedContentColor != companion.m3508getUnspecified0d7_KjU() ? clockDialSelectedContentColor : this.clockDialSelectedContentColor, clockDialUnselectedContentColor != companion.m3508getUnspecified0d7_KjU() ? clockDialUnselectedContentColor : this.clockDialUnselectedContentColor, periodSelectorSelectedContainerColor != companion.m3508getUnspecified0d7_KjU() ? periodSelectorSelectedContainerColor : this.periodSelectorSelectedContainerColor, periodSelectorUnselectedContainerColor != companion.m3508getUnspecified0d7_KjU() ? periodSelectorUnselectedContainerColor : this.periodSelectorUnselectedContainerColor, periodSelectorSelectedContentColor != companion.m3508getUnspecified0d7_KjU() ? periodSelectorSelectedContentColor : this.periodSelectorSelectedContentColor, periodSelectorUnselectedContentColor != companion.m3508getUnspecified0d7_KjU() ? periodSelectorUnselectedContentColor : this.periodSelectorUnselectedContentColor, timeSelectorSelectedContainerColor != companion.m3508getUnspecified0d7_KjU() ? timeSelectorSelectedContainerColor : this.timeSelectorSelectedContainerColor, timeSelectorUnselectedContainerColor != companion.m3508getUnspecified0d7_KjU() ? timeSelectorUnselectedContainerColor : this.timeSelectorUnselectedContainerColor, timeSelectorSelectedContentColor != companion.m3508getUnspecified0d7_KjU() ? timeSelectorSelectedContentColor : this.timeSelectorSelectedContentColor, timeSelectorUnselectedContentColor != companion.m3508getUnspecified0d7_KjU() ? timeSelectorUnselectedContentColor : this.timeSelectorUnselectedContentColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || TimePickerColors.class != other.getClass()) {
            return false;
        }
        TimePickerColors timePickerColors = (TimePickerColors) other;
        return Color.m3473equalsimpl0(this.clockDialColor, timePickerColors.clockDialColor) && Color.m3473equalsimpl0(this.selectorColor, timePickerColors.selectorColor) && Color.m3473equalsimpl0(this.containerColor, timePickerColors.containerColor) && Color.m3473equalsimpl0(this.periodSelectorBorderColor, timePickerColors.periodSelectorBorderColor) && Color.m3473equalsimpl0(this.periodSelectorSelectedContainerColor, timePickerColors.periodSelectorSelectedContainerColor) && Color.m3473equalsimpl0(this.periodSelectorUnselectedContainerColor, timePickerColors.periodSelectorUnselectedContainerColor) && Color.m3473equalsimpl0(this.periodSelectorSelectedContentColor, timePickerColors.periodSelectorSelectedContentColor) && Color.m3473equalsimpl0(this.periodSelectorUnselectedContentColor, timePickerColors.periodSelectorUnselectedContentColor) && Color.m3473equalsimpl0(this.timeSelectorSelectedContainerColor, timePickerColors.timeSelectorSelectedContainerColor) && Color.m3473equalsimpl0(this.timeSelectorUnselectedContainerColor, timePickerColors.timeSelectorUnselectedContainerColor) && Color.m3473equalsimpl0(this.timeSelectorSelectedContentColor, timePickerColors.timeSelectorSelectedContentColor) && Color.m3473equalsimpl0(this.timeSelectorUnselectedContentColor, timePickerColors.timeSelectorUnselectedContentColor);
    }

    /* JADX INFO: renamed from: getClockDialColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getClockDialColor() {
        return this.clockDialColor;
    }

    /* JADX INFO: renamed from: getClockDialSelectedContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getClockDialSelectedContentColor() {
        return this.clockDialSelectedContentColor;
    }

    /* JADX INFO: renamed from: getClockDialUnselectedContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getClockDialUnselectedContentColor() {
        return this.clockDialUnselectedContentColor;
    }

    /* JADX INFO: renamed from: getContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContainerColor() {
        return this.containerColor;
    }

    /* JADX INFO: renamed from: getPeriodSelectorBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getPeriodSelectorBorderColor() {
        return this.periodSelectorBorderColor;
    }

    /* JADX INFO: renamed from: getPeriodSelectorSelectedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getPeriodSelectorSelectedContainerColor() {
        return this.periodSelectorSelectedContainerColor;
    }

    /* JADX INFO: renamed from: getPeriodSelectorSelectedContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getPeriodSelectorSelectedContentColor() {
        return this.periodSelectorSelectedContentColor;
    }

    /* JADX INFO: renamed from: getPeriodSelectorUnselectedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getPeriodSelectorUnselectedContainerColor() {
        return this.periodSelectorUnselectedContainerColor;
    }

    /* JADX INFO: renamed from: getPeriodSelectorUnselectedContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getPeriodSelectorUnselectedContentColor() {
        return this.periodSelectorUnselectedContentColor;
    }

    /* JADX INFO: renamed from: getSelectorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectorColor() {
        return this.selectorColor;
    }

    /* JADX INFO: renamed from: getTimeSelectorSelectedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTimeSelectorSelectedContainerColor() {
        return this.timeSelectorSelectedContainerColor;
    }

    /* JADX INFO: renamed from: getTimeSelectorSelectedContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTimeSelectorSelectedContentColor() {
        return this.timeSelectorSelectedContentColor;
    }

    /* JADX INFO: renamed from: getTimeSelectorUnselectedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTimeSelectorUnselectedContainerColor() {
        return this.timeSelectorUnselectedContainerColor;
    }

    /* JADX INFO: renamed from: getTimeSelectorUnselectedContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTimeSelectorUnselectedContentColor() {
        return this.timeSelectorUnselectedContentColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.timeSelectorUnselectedContentColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.clockDialColor) * 31, 31, this.selectorColor), 31, this.containerColor), 31, this.periodSelectorBorderColor), 31, this.periodSelectorSelectedContainerColor), 31, this.periodSelectorUnselectedContainerColor), 31, this.periodSelectorSelectedContentColor), 31, this.periodSelectorUnselectedContentColor), 31, this.timeSelectorSelectedContainerColor), 31, this.timeSelectorUnselectedContainerColor), 31, this.timeSelectorSelectedContentColor);
    }

    /* JADX INFO: renamed from: periodSelectorContainerColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m2173periodSelectorContainerColorvNxB06k$material3_release(boolean selected) {
        return selected ? this.periodSelectorSelectedContainerColor : this.periodSelectorUnselectedContainerColor;
    }

    /* JADX INFO: renamed from: periodSelectorContentColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m2174periodSelectorContentColorvNxB06k$material3_release(boolean selected) {
        return selected ? this.periodSelectorSelectedContentColor : this.periodSelectorUnselectedContentColor;
    }

    /* JADX INFO: renamed from: timeSelectorContainerColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m2175timeSelectorContainerColorvNxB06k$material3_release(boolean selected) {
        return selected ? this.timeSelectorSelectedContainerColor : this.timeSelectorUnselectedContainerColor;
    }

    /* JADX INFO: renamed from: timeSelectorContentColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m2176timeSelectorContentColorvNxB06k$material3_release(boolean selected) {
        return selected ? this.timeSelectorSelectedContentColor : this.timeSelectorUnselectedContentColor;
    }

    private TimePickerColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23) {
        this.clockDialColor = j10;
        this.selectorColor = j11;
        this.containerColor = j12;
        this.periodSelectorBorderColor = j13;
        this.clockDialSelectedContentColor = j14;
        this.clockDialUnselectedContentColor = j15;
        this.periodSelectorSelectedContainerColor = j16;
        this.periodSelectorUnselectedContainerColor = j17;
        this.periodSelectorSelectedContentColor = j18;
        this.periodSelectorUnselectedContentColor = j19;
        this.timeSelectorSelectedContainerColor = j20;
        this.timeSelectorUnselectedContainerColor = j21;
        this.timeSelectorSelectedContentColor = j22;
        this.timeSelectorUnselectedContentColor = j23;
    }
}
