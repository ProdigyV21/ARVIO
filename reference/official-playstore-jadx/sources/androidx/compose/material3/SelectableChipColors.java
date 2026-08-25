package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0092\u0001\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ%\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ%\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001cJ%\u0010 \u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001cJ\u001a\u0010\"\u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&R\u001a\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0003\u0010'R\u001a\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0004\u0010'R\u001a\u0010\u0005\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0005\u0010'R\u001a\u0010\u0006\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0006\u0010'R\u001a\u0010\u0007\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0007\u0010'R\u001a\u0010\b\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\b\u0010'R\u001a\u0010\t\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\t\u0010'R\u001a\u0010\n\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\n\u0010'R\u001a\u0010\u000b\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000b\u0010'R\u001a\u0010\f\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\f\u0010'R\u001a\u0010\r\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\r\u0010'R\u001a\u0010\u000e\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000e\u0010'R\u001a\u0010\u000f\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000f\u0010'\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006("}, d2 = {"Landroidx/compose/material3/SelectableChipColors;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "labelColor", "leadingIconColor", "trailingIconColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "selectedContainerColor", "disabledSelectedContainerColor", "selectedLabelColor", "selectedLeadingIconColor", "selectedTrailingIconColor", "<init>", "(JJJJJJJJJJJJJLkotlin/jvm/internal/h;)V", "copy-daRQuJA", "(JJJJJJJJJJJJJ)Landroidx/compose/material3/SelectableChipColors;", "copy", "", "enabled", "selected", "Landroidx/compose/runtime/State;", "containerColor$material3_release", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "labelColor-WaAFU9c$material3_release", "(ZZ)J", "leadingIconContentColor-WaAFU9c$material3_release", "leadingIconContentColor", "trailingIconContentColor-WaAFU9c$material3_release", "trailingIconContentColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SelectableChipColors {
    public static final int $stable = 0;
    private final long containerColor;
    private final long disabledContainerColor;
    private final long disabledLabelColor;
    private final long disabledLeadingIconColor;
    private final long disabledSelectedContainerColor;
    private final long disabledTrailingIconColor;
    private final long labelColor;
    private final long leadingIconColor;
    private final long selectedContainerColor;
    private final long selectedLabelColor;
    private final long selectedLeadingIconColor;
    private final long selectedTrailingIconColor;
    private final long trailingIconColor;

    public /* synthetic */ SelectableChipColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22);
    }

    /* JADX INFO: renamed from: copy-daRQuJA$default, reason: not valid java name */
    public static /* synthetic */ SelectableChipColors m1848copydaRQuJA$default(SelectableChipColors selectableChipColors, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, int i10, Object obj) {
        long j23;
        long j24;
        long j25 = (i10 & 1) != 0 ? selectableChipColors.containerColor : j10;
        long j26 = (i10 & 2) != 0 ? selectableChipColors.labelColor : j11;
        long j27 = (i10 & 4) != 0 ? selectableChipColors.leadingIconColor : j12;
        long j28 = (i10 & 8) != 0 ? selectableChipColors.trailingIconColor : j13;
        long j29 = (i10 & 16) != 0 ? selectableChipColors.disabledContainerColor : j14;
        long j30 = (i10 & 32) != 0 ? selectableChipColors.disabledLabelColor : j15;
        long j31 = (i10 & 64) != 0 ? selectableChipColors.disabledLeadingIconColor : j16;
        long j32 = j25;
        long j33 = (i10 & 128) != 0 ? selectableChipColors.disabledTrailingIconColor : j17;
        long j34 = (i10 & 256) != 0 ? selectableChipColors.selectedContainerColor : j18;
        long j35 = (i10 & 512) != 0 ? selectableChipColors.disabledSelectedContainerColor : j19;
        long j36 = (i10 & 1024) != 0 ? selectableChipColors.selectedLabelColor : j20;
        long j37 = (i10 & 2048) != 0 ? selectableChipColors.selectedLeadingIconColor : j21;
        if ((i10 & 4096) != 0) {
            j24 = j37;
            j23 = selectableChipColors.selectedTrailingIconColor;
        } else {
            j23 = j22;
            j24 = j37;
        }
        return selectableChipColors.m1849copydaRQuJA(j32, j26, j27, j28, j29, j30, j31, j33, j34, j35, j36, j24, j23);
    }

    public final State<Color> containerColor$material3_release(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(-2126903408);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2126903408, i10, -1, "androidx.compose.material3.SelectableChipColors.containerColor (Chip.kt:2566)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? z5 ? this.disabledSelectedContainerColor : this.disabledContainerColor : !z5 ? this.containerColor : this.selectedContainerColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    /* JADX INFO: renamed from: copy-daRQuJA, reason: not valid java name */
    public final SelectableChipColors m1849copydaRQuJA(long containerColor, long labelColor, long leadingIconColor, long trailingIconColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconColor, long disabledTrailingIconColor, long selectedContainerColor, long disabledSelectedContainerColor, long selectedLabelColor, long selectedLeadingIconColor, long selectedTrailingIconColor) {
        Color.Companion companion = Color.INSTANCE;
        return new SelectableChipColors(containerColor != companion.m3508getUnspecified0d7_KjU() ? containerColor : this.containerColor, labelColor != companion.m3508getUnspecified0d7_KjU() ? labelColor : this.labelColor, leadingIconColor != companion.m3508getUnspecified0d7_KjU() ? leadingIconColor : this.leadingIconColor, trailingIconColor != companion.m3508getUnspecified0d7_KjU() ? trailingIconColor : this.trailingIconColor, disabledContainerColor != companion.m3508getUnspecified0d7_KjU() ? disabledContainerColor : this.disabledContainerColor, disabledLabelColor != companion.m3508getUnspecified0d7_KjU() ? disabledLabelColor : this.disabledLabelColor, disabledLeadingIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledLeadingIconColor : this.disabledLeadingIconColor, disabledTrailingIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledTrailingIconColor : this.disabledTrailingIconColor, selectedContainerColor != companion.m3508getUnspecified0d7_KjU() ? selectedContainerColor : this.selectedContainerColor, disabledSelectedContainerColor != companion.m3508getUnspecified0d7_KjU() ? disabledSelectedContainerColor : this.disabledSelectedContainerColor, selectedLabelColor != companion.m3508getUnspecified0d7_KjU() ? selectedLabelColor : this.selectedLabelColor, selectedLeadingIconColor != companion.m3508getUnspecified0d7_KjU() ? selectedLeadingIconColor : this.selectedLeadingIconColor, selectedTrailingIconColor != companion.m3508getUnspecified0d7_KjU() ? selectedTrailingIconColor : this.selectedTrailingIconColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof SelectableChipColors)) {
            return false;
        }
        SelectableChipColors selectableChipColors = (SelectableChipColors) other;
        return Color.m3473equalsimpl0(this.containerColor, selectableChipColors.containerColor) && Color.m3473equalsimpl0(this.labelColor, selectableChipColors.labelColor) && Color.m3473equalsimpl0(this.leadingIconColor, selectableChipColors.leadingIconColor) && Color.m3473equalsimpl0(this.trailingIconColor, selectableChipColors.trailingIconColor) && Color.m3473equalsimpl0(this.disabledContainerColor, selectableChipColors.disabledContainerColor) && Color.m3473equalsimpl0(this.disabledLabelColor, selectableChipColors.disabledLabelColor) && Color.m3473equalsimpl0(this.disabledLeadingIconColor, selectableChipColors.disabledLeadingIconColor) && Color.m3473equalsimpl0(this.disabledTrailingIconColor, selectableChipColors.disabledTrailingIconColor) && Color.m3473equalsimpl0(this.selectedContainerColor, selectableChipColors.selectedContainerColor) && Color.m3473equalsimpl0(this.disabledSelectedContainerColor, selectableChipColors.disabledSelectedContainerColor) && Color.m3473equalsimpl0(this.selectedLabelColor, selectableChipColors.selectedLabelColor) && Color.m3473equalsimpl0(this.selectedLeadingIconColor, selectableChipColors.selectedLeadingIconColor) && Color.m3473equalsimpl0(this.selectedTrailingIconColor, selectableChipColors.selectedTrailingIconColor);
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.selectedTrailingIconColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.containerColor) * 31, 31, this.labelColor), 31, this.leadingIconColor), 31, this.trailingIconColor), 31, this.disabledContainerColor), 31, this.disabledLabelColor), 31, this.disabledLeadingIconColor), 31, this.disabledTrailingIconColor), 31, this.selectedContainerColor), 31, this.disabledSelectedContainerColor), 31, this.selectedLabelColor), 31, this.selectedLeadingIconColor);
    }

    /* JADX INFO: renamed from: labelColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1850labelColorWaAFU9c$material3_release(boolean enabled, boolean selected) {
        return !enabled ? this.disabledLabelColor : !selected ? this.labelColor : this.selectedLabelColor;
    }

    /* JADX INFO: renamed from: leadingIconContentColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1851leadingIconContentColorWaAFU9c$material3_release(boolean enabled, boolean selected) {
        return !enabled ? this.disabledLeadingIconColor : !selected ? this.leadingIconColor : this.selectedLeadingIconColor;
    }

    /* JADX INFO: renamed from: trailingIconContentColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1852trailingIconContentColorWaAFU9c$material3_release(boolean enabled, boolean selected) {
        return !enabled ? this.disabledTrailingIconColor : !selected ? this.trailingIconColor : this.selectedTrailingIconColor;
    }

    private SelectableChipColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22) {
        this.containerColor = j10;
        this.labelColor = j11;
        this.leadingIconColor = j12;
        this.trailingIconColor = j13;
        this.disabledContainerColor = j14;
        this.disabledLabelColor = j15;
        this.disabledLeadingIconColor = j16;
        this.disabledTrailingIconColor = j17;
        this.selectedContainerColor = j18;
        this.disabledSelectedContainerColor = j19;
        this.selectedLabelColor = j20;
        this.selectedLeadingIconColor = j21;
        this.selectedTrailingIconColor = j22;
    }
}
