package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0088\u0001\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J%\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0014H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0018J%\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0018J\u001a\u0010 \u001a\u00020\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010%\u001a\u0004\b&\u0010'R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b(\u0010'R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b)\u0010'R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010%\u001a\u0004\b*\u0010'R\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010%\u001a\u0004\b+\u0010'R\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010%\u001a\u0004\b,\u0010'R\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010%\u001a\u0004\b-\u0010'R\u001d\u0010\n\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010%\u001a\u0004\b.\u0010'R\u001d\u0010\u000b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b/\u0010'R\u001d\u0010\f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\f\u0010%\u001a\u0004\b0\u0010'R\u001d\u0010\r\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\r\u0010%\u001a\u0004\b1\u0010'R\u001d\u0010\u000e\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000e\u0010%\u001a\u0004\b2\u0010'\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00063"}, d2 = {"Landroidx/compose/material3/SegmentedButtonColors;", "", "Landroidx/compose/ui/graphics/Color;", "activeContainerColor", "activeContentColor", "activeBorderColor", "inactiveContainerColor", "inactiveContentColor", "inactiveBorderColor", "disabledActiveContainerColor", "disabledActiveContentColor", "disabledActiveBorderColor", "disabledInactiveContainerColor", "disabledInactiveContentColor", "disabledInactiveBorderColor", "<init>", "(JJJJJJJJJJJJLkotlin/jvm/internal/h;)V", "copy-2qZNXz8", "(JJJJJJJJJJJJ)Landroidx/compose/material3/SegmentedButtonColors;", "copy", "", "enabled", "active", "borderColor-WaAFU9c$material3_release", "(ZZ)J", "borderColor", "checked", "contentColor-WaAFU9c$material3_release", "contentColor", "containerColor-WaAFU9c$material3_release", "containerColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getActiveContainerColor-0d7_KjU", "()J", "getActiveContentColor-0d7_KjU", "getActiveBorderColor-0d7_KjU", "getInactiveContainerColor-0d7_KjU", "getInactiveContentColor-0d7_KjU", "getInactiveBorderColor-0d7_KjU", "getDisabledActiveContainerColor-0d7_KjU", "getDisabledActiveContentColor-0d7_KjU", "getDisabledActiveBorderColor-0d7_KjU", "getDisabledInactiveContainerColor-0d7_KjU", "getDisabledInactiveContentColor-0d7_KjU", "getDisabledInactiveBorderColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SegmentedButtonColors {
    public static final int $stable = 0;
    private final long activeBorderColor;
    private final long activeContainerColor;
    private final long activeContentColor;
    private final long disabledActiveBorderColor;
    private final long disabledActiveContainerColor;
    private final long disabledActiveContentColor;
    private final long disabledInactiveBorderColor;
    private final long disabledInactiveContainerColor;
    private final long disabledInactiveContentColor;
    private final long inactiveBorderColor;
    private final long inactiveContainerColor;
    private final long inactiveContentColor;

    public /* synthetic */ SegmentedButtonColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21);
    }

    /* JADX INFO: renamed from: copy-2qZNXz8$default, reason: not valid java name */
    public static /* synthetic */ SegmentedButtonColors m1823copy2qZNXz8$default(SegmentedButtonColors segmentedButtonColors, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, int i10, Object obj) {
        long j22;
        long j23;
        long j24 = (i10 & 1) != 0 ? segmentedButtonColors.activeContainerColor : j10;
        long j25 = (i10 & 2) != 0 ? segmentedButtonColors.activeContentColor : j11;
        long j26 = (i10 & 4) != 0 ? segmentedButtonColors.activeBorderColor : j12;
        long j27 = (i10 & 8) != 0 ? segmentedButtonColors.inactiveContainerColor : j13;
        long j28 = (i10 & 16) != 0 ? segmentedButtonColors.inactiveContentColor : j14;
        long j29 = (i10 & 32) != 0 ? segmentedButtonColors.inactiveBorderColor : j15;
        long j30 = (i10 & 64) != 0 ? segmentedButtonColors.disabledActiveContainerColor : j16;
        long j31 = j24;
        long j32 = (i10 & 128) != 0 ? segmentedButtonColors.disabledActiveContentColor : j17;
        long j33 = (i10 & 256) != 0 ? segmentedButtonColors.disabledActiveBorderColor : j18;
        long j34 = (i10 & 512) != 0 ? segmentedButtonColors.disabledInactiveContainerColor : j19;
        long j35 = (i10 & 1024) != 0 ? segmentedButtonColors.disabledInactiveContentColor : j20;
        if ((i10 & 2048) != 0) {
            j23 = j35;
            j22 = segmentedButtonColors.disabledInactiveBorderColor;
        } else {
            j22 = j21;
            j23 = j35;
        }
        return segmentedButtonColors.m1827copy2qZNXz8(j31, j25, j26, j27, j28, j29, j30, j32, j33, j34, j23, j22);
    }

    /* JADX INFO: renamed from: borderColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1824borderColorWaAFU9c$material3_release(boolean enabled, boolean active) {
        return (enabled && active) ? this.activeBorderColor : (!enabled || active) ? (enabled || !active) ? this.disabledInactiveBorderColor : this.disabledActiveBorderColor : this.inactiveBorderColor;
    }

    /* JADX INFO: renamed from: containerColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1825containerColorWaAFU9c$material3_release(boolean enabled, boolean active) {
        return (enabled && active) ? this.activeContainerColor : (!enabled || active) ? (enabled || !active) ? this.disabledInactiveContainerColor : this.disabledActiveContainerColor : this.inactiveContainerColor;
    }

    /* JADX INFO: renamed from: contentColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1826contentColorWaAFU9c$material3_release(boolean enabled, boolean checked) {
        return (enabled && checked) ? this.activeContentColor : (!enabled || checked) ? (enabled || !checked) ? this.disabledInactiveContentColor : this.disabledActiveContentColor : this.inactiveContentColor;
    }

    /* JADX INFO: renamed from: copy-2qZNXz8, reason: not valid java name */
    public final SegmentedButtonColors m1827copy2qZNXz8(long activeContainerColor, long activeContentColor, long activeBorderColor, long inactiveContainerColor, long inactiveContentColor, long inactiveBorderColor, long disabledActiveContainerColor, long disabledActiveContentColor, long disabledActiveBorderColor, long disabledInactiveContainerColor, long disabledInactiveContentColor, long disabledInactiveBorderColor) {
        Color.Companion companion = Color.INSTANCE;
        return new SegmentedButtonColors(activeContainerColor != companion.m3508getUnspecified0d7_KjU() ? activeContainerColor : this.activeContainerColor, activeContentColor != companion.m3508getUnspecified0d7_KjU() ? activeContentColor : this.activeContentColor, activeBorderColor != companion.m3508getUnspecified0d7_KjU() ? activeBorderColor : this.activeBorderColor, inactiveContainerColor != companion.m3508getUnspecified0d7_KjU() ? inactiveContainerColor : this.inactiveContainerColor, inactiveContentColor != companion.m3508getUnspecified0d7_KjU() ? inactiveContentColor : this.inactiveContentColor, inactiveBorderColor != companion.m3508getUnspecified0d7_KjU() ? inactiveBorderColor : this.inactiveBorderColor, disabledActiveContainerColor != companion.m3508getUnspecified0d7_KjU() ? disabledActiveContainerColor : this.disabledActiveContainerColor, disabledActiveContentColor != companion.m3508getUnspecified0d7_KjU() ? disabledActiveContentColor : this.disabledActiveContentColor, disabledActiveBorderColor != companion.m3508getUnspecified0d7_KjU() ? disabledActiveBorderColor : this.disabledActiveBorderColor, disabledInactiveContainerColor != companion.m3508getUnspecified0d7_KjU() ? disabledInactiveContainerColor : this.disabledInactiveContainerColor, disabledInactiveContentColor != companion.m3508getUnspecified0d7_KjU() ? disabledInactiveContentColor : this.disabledInactiveContentColor, disabledInactiveBorderColor != companion.m3508getUnspecified0d7_KjU() ? disabledInactiveBorderColor : this.disabledInactiveBorderColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || SegmentedButtonColors.class != other.getClass()) {
            return false;
        }
        SegmentedButtonColors segmentedButtonColors = (SegmentedButtonColors) other;
        return Color.m3473equalsimpl0(this.activeBorderColor, segmentedButtonColors.activeBorderColor) && Color.m3473equalsimpl0(this.activeContentColor, segmentedButtonColors.activeContentColor) && Color.m3473equalsimpl0(this.activeContainerColor, segmentedButtonColors.activeContainerColor) && Color.m3473equalsimpl0(this.inactiveBorderColor, segmentedButtonColors.inactiveBorderColor) && Color.m3473equalsimpl0(this.inactiveContentColor, segmentedButtonColors.inactiveContentColor) && Color.m3473equalsimpl0(this.inactiveContainerColor, segmentedButtonColors.inactiveContainerColor) && Color.m3473equalsimpl0(this.disabledActiveBorderColor, segmentedButtonColors.disabledActiveBorderColor) && Color.m3473equalsimpl0(this.disabledActiveContentColor, segmentedButtonColors.disabledActiveContentColor) && Color.m3473equalsimpl0(this.disabledActiveContainerColor, segmentedButtonColors.disabledActiveContainerColor) && Color.m3473equalsimpl0(this.disabledInactiveBorderColor, segmentedButtonColors.disabledInactiveBorderColor) && Color.m3473equalsimpl0(this.disabledInactiveContentColor, segmentedButtonColors.disabledInactiveContentColor) && Color.m3473equalsimpl0(this.disabledInactiveContainerColor, segmentedButtonColors.disabledInactiveContainerColor);
    }

    /* JADX INFO: renamed from: getActiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveBorderColor() {
        return this.activeBorderColor;
    }

    /* JADX INFO: renamed from: getActiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveContainerColor() {
        return this.activeContainerColor;
    }

    /* JADX INFO: renamed from: getActiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveContentColor() {
        return this.activeContentColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveBorderColor() {
        return this.disabledActiveBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveContainerColor() {
        return this.disabledActiveContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveContentColor() {
        return this.disabledActiveContentColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveBorderColor() {
        return this.disabledInactiveBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveContainerColor() {
        return this.disabledInactiveContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveContentColor() {
        return this.disabledInactiveContentColor;
    }

    /* JADX INFO: renamed from: getInactiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveBorderColor() {
        return this.inactiveBorderColor;
    }

    /* JADX INFO: renamed from: getInactiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveContainerColor() {
        return this.inactiveContainerColor;
    }

    /* JADX INFO: renamed from: getInactiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveContentColor() {
        return this.inactiveContentColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.disabledInactiveContainerColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.activeBorderColor) * 31, 31, this.activeContentColor), 31, this.activeContainerColor), 31, this.inactiveBorderColor), 31, this.inactiveContentColor), 31, this.inactiveContainerColor), 31, this.disabledActiveBorderColor), 31, this.disabledActiveContentColor), 31, this.disabledActiveContainerColor), 31, this.disabledInactiveBorderColor), 31, this.disabledInactiveContentColor);
    }

    private SegmentedButtonColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21) {
        this.activeContainerColor = j10;
        this.activeContentColor = j11;
        this.activeBorderColor = j12;
        this.inactiveContainerColor = j13;
        this.inactiveContentColor = j14;
        this.inactiveBorderColor = j15;
        this.disabledActiveContainerColor = j16;
        this.disabledActiveContentColor = j17;
        this.disabledActiveBorderColor = j18;
        this.disabledInactiveContainerColor = j19;
        this.disabledInactiveContentColor = j20;
        this.disabledInactiveBorderColor = j21;
    }
}
