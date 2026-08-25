package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b2\u0006\u0010\n\u001a\u00020\tH\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0004\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/material3/ChipBorder;", "", "Landroidx/compose/ui/graphics/Color;", "borderColor", "disabledBorderColor", "Landroidx/compose/ui/unit/Dp;", "borderWidth", "<init>", "(JJFLkotlin/jvm/internal/h;)V", "", "enabled", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/BorderStroke;", "borderStroke$material3_release", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "borderStroke", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "F", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@x6.e
public final class ChipBorder {
    public static final int $stable = 0;
    private final long borderColor;
    private final float borderWidth;
    private final long disabledBorderColor;

    public /* synthetic */ ChipBorder(long j10, long j11, float f10, kotlin.jvm.internal.h hVar) {
        this(j10, j11, f10);
    }

    public final State<BorderStroke> borderStroke$material3_release(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(1899621712);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1899621712, i10, -1, "androidx.compose.material3.ChipBorder.borderStroke (Chip.kt:2677)");
        }
        State<BorderStroke> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(BorderStrokeKt.m207BorderStrokecXLIe8U(this.borderWidth, z ? this.borderColor : this.disabledBorderColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof ChipBorder)) {
            return false;
        }
        ChipBorder chipBorder = (ChipBorder) other;
        return Color.m3473equalsimpl0(this.borderColor, chipBorder.borderColor) && Color.m3473equalsimpl0(this.disabledBorderColor, chipBorder.disabledBorderColor) && Dp.m5683equalsimpl0(this.borderWidth, chipBorder.borderWidth);
    }

    public int hashCode() {
        return Dp.m5684hashCodeimpl(this.borderWidth) + androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.borderColor) * 31, 31, this.disabledBorderColor);
    }

    private ChipBorder(long j10, long j11, float f10) {
        this.borderColor = j10;
        this.disabledBorderColor = j11;
        this.borderWidth = f10;
    }
}
