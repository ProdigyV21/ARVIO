package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0015\b\u0007\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J°\u0001\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ%\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001cJ%\u0010!\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u001cJ%\u0010#\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u001cJ\u001a\u0010%\u001a\u00020\u00182\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010*\u001a\u0004\b+\u0010,R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010*\u001a\u0004\b-\u0010,R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010*\u001a\u0004\b.\u0010,R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010*\u001a\u0004\b/\u0010,R\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b0\u0010,R\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010*\u001a\u0004\b1\u0010,R\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010*\u001a\u0004\b2\u0010,R\u001d\u0010\n\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010*\u001a\u0004\b3\u0010,R\u001d\u0010\u000b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010*\u001a\u0004\b4\u0010,R\u001d\u0010\f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\f\u0010*\u001a\u0004\b5\u0010,R\u001d\u0010\r\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\r\u0010*\u001a\u0004\b6\u0010,R\u001d\u0010\u000e\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000e\u0010*\u001a\u0004\b7\u0010,R\u001d\u0010\u000f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000f\u0010*\u001a\u0004\b8\u0010,R\u001d\u0010\u0010\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0010\u0010*\u001a\u0004\b9\u0010,R\u001d\u0010\u0011\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0011\u0010*\u001a\u0004\b:\u0010,R\u001d\u0010\u0012\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0012\u0010*\u001a\u0004\b;\u0010,\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006<"}, d2 = {"Landroidx/compose/material3/SwitchColors;", "", "Landroidx/compose/ui/graphics/Color;", "checkedThumbColor", "checkedTrackColor", "checkedBorderColor", "checkedIconColor", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedBorderColor", "uncheckedIconColor", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledCheckedBorderColor", "disabledCheckedIconColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "disabledUncheckedBorderColor", "disabledUncheckedIconColor", "<init>", "(JJJJJJJJJJJJJJJJLkotlin/jvm/internal/h;)V", "copy-Q_H9qLU", "(JJJJJJJJJJJJJJJJ)Landroidx/compose/material3/SwitchColors;", "copy", "", "enabled", "checked", "thumbColor-WaAFU9c$material3_release", "(ZZ)J", "thumbColor", "trackColor-WaAFU9c$material3_release", "trackColor", "borderColor-WaAFU9c$material3_release", "borderColor", "iconColor-WaAFU9c$material3_release", "iconColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getCheckedThumbColor-0d7_KjU", "()J", "getCheckedTrackColor-0d7_KjU", "getCheckedBorderColor-0d7_KjU", "getCheckedIconColor-0d7_KjU", "getUncheckedThumbColor-0d7_KjU", "getUncheckedTrackColor-0d7_KjU", "getUncheckedBorderColor-0d7_KjU", "getUncheckedIconColor-0d7_KjU", "getDisabledCheckedThumbColor-0d7_KjU", "getDisabledCheckedTrackColor-0d7_KjU", "getDisabledCheckedBorderColor-0d7_KjU", "getDisabledCheckedIconColor-0d7_KjU", "getDisabledUncheckedThumbColor-0d7_KjU", "getDisabledUncheckedTrackColor-0d7_KjU", "getDisabledUncheckedBorderColor-0d7_KjU", "getDisabledUncheckedIconColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SwitchColors {
    public static final int $stable = 0;
    private final long checkedBorderColor;
    private final long checkedIconColor;
    private final long checkedThumbColor;
    private final long checkedTrackColor;
    private final long disabledCheckedBorderColor;
    private final long disabledCheckedIconColor;
    private final long disabledCheckedThumbColor;
    private final long disabledCheckedTrackColor;
    private final long disabledUncheckedBorderColor;
    private final long disabledUncheckedIconColor;
    private final long disabledUncheckedThumbColor;
    private final long disabledUncheckedTrackColor;
    private final long uncheckedBorderColor;
    private final long uncheckedIconColor;
    private final long uncheckedThumbColor;
    private final long uncheckedTrackColor;

    public /* synthetic */ SwitchColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25);
    }

    /* JADX INFO: renamed from: copy-Q_H9qLU$default, reason: not valid java name */
    public static /* synthetic */ SwitchColors m2013copyQ_H9qLU$default(SwitchColors switchColors, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, int i10, Object obj) {
        long j26;
        long j27;
        long j28;
        long j29;
        long j30;
        long j31;
        long j32;
        long j33;
        long j34;
        SwitchColors switchColors2;
        long j35;
        long j36;
        long j37;
        long j38;
        long j39;
        long j40;
        long j41 = (i10 & 1) != 0 ? switchColors.checkedThumbColor : j10;
        long j42 = (i10 & 2) != 0 ? switchColors.checkedTrackColor : j11;
        long j43 = (i10 & 4) != 0 ? switchColors.checkedBorderColor : j12;
        long j44 = (i10 & 8) != 0 ? switchColors.checkedIconColor : j13;
        long j45 = (i10 & 16) != 0 ? switchColors.uncheckedThumbColor : j14;
        long j46 = (i10 & 32) != 0 ? switchColors.uncheckedTrackColor : j15;
        long j47 = (i10 & 64) != 0 ? switchColors.uncheckedBorderColor : j16;
        long j48 = j41;
        long j49 = (i10 & 128) != 0 ? switchColors.uncheckedIconColor : j17;
        long j50 = (i10 & 256) != 0 ? switchColors.disabledCheckedThumbColor : j18;
        long j51 = (i10 & 512) != 0 ? switchColors.disabledCheckedTrackColor : j19;
        long j52 = (i10 & 1024) != 0 ? switchColors.disabledCheckedBorderColor : j20;
        long j53 = (i10 & 2048) != 0 ? switchColors.disabledCheckedIconColor : j21;
        long j54 = (i10 & 4096) != 0 ? switchColors.disabledUncheckedThumbColor : j22;
        long j55 = (i10 & 8192) != 0 ? switchColors.disabledUncheckedTrackColor : j23;
        long j56 = (i10 & 16384) != 0 ? switchColors.disabledUncheckedBorderColor : j24;
        if ((i10 & 32768) != 0) {
            j27 = j56;
            j26 = switchColors.disabledUncheckedIconColor;
            j29 = j50;
            j30 = j51;
            j31 = j52;
            j32 = j53;
            j33 = j54;
            j34 = j55;
            j35 = j42;
            j36 = j43;
            j37 = j44;
            j38 = j45;
            j39 = j46;
            j40 = j47;
            j28 = j49;
            switchColors2 = switchColors;
        } else {
            j26 = j25;
            j27 = j56;
            j28 = j49;
            j29 = j50;
            j30 = j51;
            j31 = j52;
            j32 = j53;
            j33 = j54;
            j34 = j55;
            switchColors2 = switchColors;
            j35 = j42;
            j36 = j43;
            j37 = j44;
            j38 = j45;
            j39 = j46;
            j40 = j47;
        }
        return switchColors2.m2015copyQ_H9qLU(j48, j35, j36, j37, j38, j39, j40, j28, j29, j30, j31, j32, j33, j34, j27, j26);
    }

    /* JADX INFO: renamed from: borderColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m2014borderColorWaAFU9c$material3_release(boolean enabled, boolean checked) {
        return enabled ? checked ? this.checkedBorderColor : this.uncheckedBorderColor : checked ? this.disabledCheckedBorderColor : this.disabledUncheckedBorderColor;
    }

    /* JADX INFO: renamed from: copy-Q_H9qLU, reason: not valid java name */
    public final SwitchColors m2015copyQ_H9qLU(long checkedThumbColor, long checkedTrackColor, long checkedBorderColor, long checkedIconColor, long uncheckedThumbColor, long uncheckedTrackColor, long uncheckedBorderColor, long uncheckedIconColor, long disabledCheckedThumbColor, long disabledCheckedTrackColor, long disabledCheckedBorderColor, long disabledCheckedIconColor, long disabledUncheckedThumbColor, long disabledUncheckedTrackColor, long disabledUncheckedBorderColor, long disabledUncheckedIconColor) {
        Color.Companion companion = Color.INSTANCE;
        return new SwitchColors(checkedThumbColor != companion.m3508getUnspecified0d7_KjU() ? checkedThumbColor : this.checkedThumbColor, checkedTrackColor != companion.m3508getUnspecified0d7_KjU() ? checkedTrackColor : this.checkedTrackColor, checkedBorderColor != companion.m3508getUnspecified0d7_KjU() ? checkedBorderColor : this.checkedBorderColor, checkedIconColor != companion.m3508getUnspecified0d7_KjU() ? checkedIconColor : this.checkedIconColor, uncheckedThumbColor != companion.m3508getUnspecified0d7_KjU() ? uncheckedThumbColor : this.uncheckedThumbColor, uncheckedTrackColor != companion.m3508getUnspecified0d7_KjU() ? uncheckedTrackColor : this.uncheckedTrackColor, uncheckedBorderColor != companion.m3508getUnspecified0d7_KjU() ? uncheckedBorderColor : this.uncheckedBorderColor, uncheckedIconColor != companion.m3508getUnspecified0d7_KjU() ? uncheckedIconColor : this.uncheckedIconColor, disabledCheckedThumbColor != companion.m3508getUnspecified0d7_KjU() ? disabledCheckedThumbColor : this.disabledCheckedThumbColor, disabledCheckedTrackColor != companion.m3508getUnspecified0d7_KjU() ? disabledCheckedTrackColor : this.disabledCheckedTrackColor, disabledCheckedBorderColor != companion.m3508getUnspecified0d7_KjU() ? disabledCheckedBorderColor : this.disabledCheckedBorderColor, disabledCheckedIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledCheckedIconColor : this.disabledCheckedIconColor, disabledUncheckedThumbColor != companion.m3508getUnspecified0d7_KjU() ? disabledUncheckedThumbColor : this.disabledUncheckedThumbColor, disabledUncheckedTrackColor != companion.m3508getUnspecified0d7_KjU() ? disabledUncheckedTrackColor : this.disabledUncheckedTrackColor, disabledUncheckedBorderColor != companion.m3508getUnspecified0d7_KjU() ? disabledUncheckedBorderColor : this.disabledUncheckedBorderColor, disabledUncheckedIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledUncheckedIconColor : this.disabledUncheckedIconColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof SwitchColors)) {
            return false;
        }
        SwitchColors switchColors = (SwitchColors) other;
        return Color.m3473equalsimpl0(this.checkedThumbColor, switchColors.checkedThumbColor) && Color.m3473equalsimpl0(this.checkedTrackColor, switchColors.checkedTrackColor) && Color.m3473equalsimpl0(this.checkedBorderColor, switchColors.checkedBorderColor) && Color.m3473equalsimpl0(this.checkedIconColor, switchColors.checkedIconColor) && Color.m3473equalsimpl0(this.uncheckedThumbColor, switchColors.uncheckedThumbColor) && Color.m3473equalsimpl0(this.uncheckedTrackColor, switchColors.uncheckedTrackColor) && Color.m3473equalsimpl0(this.uncheckedBorderColor, switchColors.uncheckedBorderColor) && Color.m3473equalsimpl0(this.uncheckedIconColor, switchColors.uncheckedIconColor) && Color.m3473equalsimpl0(this.disabledCheckedThumbColor, switchColors.disabledCheckedThumbColor) && Color.m3473equalsimpl0(this.disabledCheckedTrackColor, switchColors.disabledCheckedTrackColor) && Color.m3473equalsimpl0(this.disabledCheckedBorderColor, switchColors.disabledCheckedBorderColor) && Color.m3473equalsimpl0(this.disabledCheckedIconColor, switchColors.disabledCheckedIconColor) && Color.m3473equalsimpl0(this.disabledUncheckedThumbColor, switchColors.disabledUncheckedThumbColor) && Color.m3473equalsimpl0(this.disabledUncheckedTrackColor, switchColors.disabledUncheckedTrackColor) && Color.m3473equalsimpl0(this.disabledUncheckedBorderColor, switchColors.disabledUncheckedBorderColor) && Color.m3473equalsimpl0(this.disabledUncheckedIconColor, switchColors.disabledUncheckedIconColor);
    }

    /* JADX INFO: renamed from: getCheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedBorderColor() {
        return this.checkedBorderColor;
    }

    /* JADX INFO: renamed from: getCheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedIconColor() {
        return this.checkedIconColor;
    }

    /* JADX INFO: renamed from: getCheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedThumbColor() {
        return this.checkedThumbColor;
    }

    /* JADX INFO: renamed from: getCheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedTrackColor() {
        return this.checkedTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedBorderColor() {
        return this.disabledCheckedBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedIconColor() {
        return this.disabledCheckedIconColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedThumbColor() {
        return this.disabledCheckedThumbColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedTrackColor() {
        return this.disabledCheckedTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedBorderColor() {
        return this.disabledUncheckedBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedIconColor() {
        return this.disabledUncheckedIconColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedThumbColor() {
        return this.disabledUncheckedThumbColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedTrackColor() {
        return this.disabledUncheckedTrackColor;
    }

    /* JADX INFO: renamed from: getUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedBorderColor() {
        return this.uncheckedBorderColor;
    }

    /* JADX INFO: renamed from: getUncheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedIconColor() {
        return this.uncheckedIconColor;
    }

    /* JADX INFO: renamed from: getUncheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedThumbColor() {
        return this.uncheckedThumbColor;
    }

    /* JADX INFO: renamed from: getUncheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedTrackColor() {
        return this.uncheckedTrackColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.disabledUncheckedIconColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.checkedThumbColor) * 31, 31, this.checkedTrackColor), 31, this.checkedBorderColor), 31, this.checkedIconColor), 31, this.uncheckedThumbColor), 31, this.uncheckedTrackColor), 31, this.uncheckedBorderColor), 31, this.uncheckedIconColor), 31, this.disabledCheckedThumbColor), 31, this.disabledCheckedTrackColor), 31, this.disabledCheckedBorderColor), 31, this.disabledCheckedIconColor), 31, this.disabledUncheckedThumbColor), 31, this.disabledUncheckedTrackColor), 31, this.disabledUncheckedBorderColor);
    }

    /* JADX INFO: renamed from: iconColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m2032iconColorWaAFU9c$material3_release(boolean enabled, boolean checked) {
        return enabled ? checked ? this.checkedIconColor : this.uncheckedIconColor : checked ? this.disabledCheckedIconColor : this.disabledUncheckedIconColor;
    }

    /* JADX INFO: renamed from: thumbColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m2033thumbColorWaAFU9c$material3_release(boolean enabled, boolean checked) {
        return enabled ? checked ? this.checkedThumbColor : this.uncheckedThumbColor : checked ? this.disabledCheckedThumbColor : this.disabledUncheckedThumbColor;
    }

    /* JADX INFO: renamed from: trackColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m2034trackColorWaAFU9c$material3_release(boolean enabled, boolean checked) {
        return enabled ? checked ? this.checkedTrackColor : this.uncheckedTrackColor : checked ? this.disabledCheckedTrackColor : this.disabledUncheckedTrackColor;
    }

    private SwitchColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25) {
        this.checkedThumbColor = j10;
        this.checkedTrackColor = j11;
        this.checkedBorderColor = j12;
        this.checkedIconColor = j13;
        this.uncheckedThumbColor = j14;
        this.uncheckedTrackColor = j15;
        this.uncheckedBorderColor = j16;
        this.uncheckedIconColor = j17;
        this.disabledCheckedThumbColor = j18;
        this.disabledCheckedTrackColor = j19;
        this.disabledCheckedBorderColor = j20;
        this.disabledCheckedIconColor = j21;
        this.disabledUncheckedThumbColor = j22;
        this.disabledUncheckedTrackColor = j23;
        this.disabledUncheckedBorderColor = j24;
        this.disabledUncheckedIconColor = j25;
    }
}
