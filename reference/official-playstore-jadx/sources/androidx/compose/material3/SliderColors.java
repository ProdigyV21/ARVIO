package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJt\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0012H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J%\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J%\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0018J\u001a\u0010\u001d\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010$R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b%\u0010$R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b&\u0010$R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b'\u0010$R\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b(\u0010$R\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b)\u0010$R\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b*\u0010$R\u001d\u0010\n\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010\"\u001a\u0004\b+\u0010$R\u001d\u0010\u000b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010\"\u001a\u0004\b,\u0010$R\u001d\u0010\f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\f\u0010\"\u001a\u0004\b-\u0010$\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006."}, d2 = {"Landroidx/compose/material3/SliderColors;", "", "Landroidx/compose/ui/graphics/Color;", "thumbColor", "activeTrackColor", "activeTickColor", "inactiveTrackColor", "inactiveTickColor", "disabledThumbColor", "disabledActiveTrackColor", "disabledActiveTickColor", "disabledInactiveTrackColor", "disabledInactiveTickColor", "<init>", "(JJJJJJJJJJLkotlin/jvm/internal/h;)V", "copy--K518z4", "(JJJJJJJJJJ)Landroidx/compose/material3/SliderColors;", "copy", "", "enabled", "thumbColor-vNxB06k$material3_release", "(Z)J", "active", "trackColor-WaAFU9c$material3_release", "(ZZ)J", "trackColor", "tickColor-WaAFU9c$material3_release", "tickColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getThumbColor-0d7_KjU", "()J", "getActiveTrackColor-0d7_KjU", "getActiveTickColor-0d7_KjU", "getInactiveTrackColor-0d7_KjU", "getInactiveTickColor-0d7_KjU", "getDisabledThumbColor-0d7_KjU", "getDisabledActiveTrackColor-0d7_KjU", "getDisabledActiveTickColor-0d7_KjU", "getDisabledInactiveTrackColor-0d7_KjU", "getDisabledInactiveTickColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SliderColors {
    public static final int $stable = 0;
    private final long activeTickColor;
    private final long activeTrackColor;
    private final long disabledActiveTickColor;
    private final long disabledActiveTrackColor;
    private final long disabledInactiveTickColor;
    private final long disabledInactiveTrackColor;
    private final long disabledThumbColor;
    private final long inactiveTickColor;
    private final long inactiveTrackColor;
    private final long thumbColor;

    public /* synthetic */ SliderColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19);
    }

    /* JADX INFO: renamed from: copy--K518z4$default, reason: not valid java name */
    public static /* synthetic */ SliderColors m1871copyK518z4$default(SliderColors sliderColors, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, int i10, Object obj) {
        long j20;
        long j21;
        long j22 = (i10 & 1) != 0 ? sliderColors.thumbColor : j10;
        long j23 = (i10 & 2) != 0 ? sliderColors.activeTrackColor : j11;
        long j24 = (i10 & 4) != 0 ? sliderColors.activeTickColor : j12;
        long j25 = (i10 & 8) != 0 ? sliderColors.inactiveTrackColor : j13;
        long j26 = (i10 & 16) != 0 ? sliderColors.inactiveTickColor : j14;
        long j27 = (i10 & 32) != 0 ? sliderColors.disabledThumbColor : j15;
        long j28 = (i10 & 64) != 0 ? sliderColors.disabledActiveTrackColor : j16;
        long j29 = j22;
        long j30 = (i10 & 128) != 0 ? sliderColors.disabledActiveTickColor : j17;
        long j31 = (i10 & 256) != 0 ? sliderColors.disabledInactiveTrackColor : j18;
        if ((i10 & 512) != 0) {
            j21 = j31;
            j20 = sliderColors.disabledInactiveTickColor;
        } else {
            j20 = j19;
            j21 = j31;
        }
        return sliderColors.m1872copyK518z4(j29, j23, j24, j25, j26, j27, j28, j30, j21, j20);
    }

    /* JADX INFO: renamed from: copy--K518z4, reason: not valid java name */
    public final SliderColors m1872copyK518z4(long thumbColor, long activeTrackColor, long activeTickColor, long inactiveTrackColor, long inactiveTickColor, long disabledThumbColor, long disabledActiveTrackColor, long disabledActiveTickColor, long disabledInactiveTrackColor, long disabledInactiveTickColor) {
        Color.Companion companion = Color.INSTANCE;
        return new SliderColors(thumbColor != companion.m3508getUnspecified0d7_KjU() ? thumbColor : this.thumbColor, activeTrackColor != companion.m3508getUnspecified0d7_KjU() ? activeTrackColor : this.activeTrackColor, activeTickColor != companion.m3508getUnspecified0d7_KjU() ? activeTickColor : this.activeTickColor, inactiveTrackColor != companion.m3508getUnspecified0d7_KjU() ? inactiveTrackColor : this.inactiveTrackColor, inactiveTickColor != companion.m3508getUnspecified0d7_KjU() ? inactiveTickColor : this.inactiveTickColor, disabledThumbColor != companion.m3508getUnspecified0d7_KjU() ? disabledThumbColor : this.disabledThumbColor, disabledActiveTrackColor != companion.m3508getUnspecified0d7_KjU() ? disabledActiveTrackColor : this.disabledActiveTrackColor, disabledActiveTickColor != companion.m3508getUnspecified0d7_KjU() ? disabledActiveTickColor : this.disabledActiveTickColor, disabledInactiveTrackColor != companion.m3508getUnspecified0d7_KjU() ? disabledInactiveTrackColor : this.disabledInactiveTrackColor, disabledInactiveTickColor != companion.m3508getUnspecified0d7_KjU() ? disabledInactiveTickColor : this.disabledInactiveTickColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof SliderColors)) {
            return false;
        }
        SliderColors sliderColors = (SliderColors) other;
        return Color.m3473equalsimpl0(this.thumbColor, sliderColors.thumbColor) && Color.m3473equalsimpl0(this.activeTrackColor, sliderColors.activeTrackColor) && Color.m3473equalsimpl0(this.activeTickColor, sliderColors.activeTickColor) && Color.m3473equalsimpl0(this.inactiveTrackColor, sliderColors.inactiveTrackColor) && Color.m3473equalsimpl0(this.inactiveTickColor, sliderColors.inactiveTickColor) && Color.m3473equalsimpl0(this.disabledThumbColor, sliderColors.disabledThumbColor) && Color.m3473equalsimpl0(this.disabledActiveTrackColor, sliderColors.disabledActiveTrackColor) && Color.m3473equalsimpl0(this.disabledActiveTickColor, sliderColors.disabledActiveTickColor) && Color.m3473equalsimpl0(this.disabledInactiveTrackColor, sliderColors.disabledInactiveTrackColor) && Color.m3473equalsimpl0(this.disabledInactiveTickColor, sliderColors.disabledInactiveTickColor);
    }

    /* JADX INFO: renamed from: getActiveTickColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveTickColor() {
        return this.activeTickColor;
    }

    /* JADX INFO: renamed from: getActiveTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveTrackColor() {
        return this.activeTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveTickColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveTickColor() {
        return this.disabledActiveTickColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveTrackColor() {
        return this.disabledActiveTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveTickColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveTickColor() {
        return this.disabledInactiveTickColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveTrackColor() {
        return this.disabledInactiveTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledThumbColor() {
        return this.disabledThumbColor;
    }

    /* JADX INFO: renamed from: getInactiveTickColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveTickColor() {
        return this.inactiveTickColor;
    }

    /* JADX INFO: renamed from: getInactiveTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveTrackColor() {
        return this.inactiveTrackColor;
    }

    /* JADX INFO: renamed from: getThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getThumbColor() {
        return this.thumbColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.disabledInactiveTickColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.thumbColor) * 31, 31, this.activeTrackColor), 31, this.activeTickColor), 31, this.inactiveTrackColor), 31, this.inactiveTickColor), 31, this.disabledThumbColor), 31, this.disabledActiveTrackColor), 31, this.disabledActiveTickColor), 31, this.disabledInactiveTrackColor);
    }

    /* JADX INFO: renamed from: thumbColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m1883thumbColorvNxB06k$material3_release(boolean enabled) {
        return enabled ? this.thumbColor : this.disabledThumbColor;
    }

    /* JADX INFO: renamed from: tickColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1884tickColorWaAFU9c$material3_release(boolean enabled, boolean active) {
        return enabled ? active ? this.activeTickColor : this.inactiveTickColor : active ? this.disabledActiveTickColor : this.disabledInactiveTickColor;
    }

    /* JADX INFO: renamed from: trackColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1885trackColorWaAFU9c$material3_release(boolean enabled, boolean active) {
        return enabled ? active ? this.activeTrackColor : this.inactiveTrackColor : active ? this.disabledActiveTrackColor : this.disabledInactiveTrackColor;
    }

    private SliderColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19) {
        this.thumbColor = j10;
        this.activeTrackColor = j11;
        this.activeTickColor = j12;
        this.inactiveTrackColor = j13;
        this.inactiveTickColor = j14;
        this.disabledThumbColor = j15;
        this.disabledActiveTrackColor = j16;
        this.disabledActiveTickColor = j17;
        this.disabledInactiveTrackColor = j18;
        this.disabledInactiveTickColor = j19;
    }
}
