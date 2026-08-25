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
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ8\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ%\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001c\u0010\u001bR\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001d\u0010\u001bR\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001e\u0010\u001b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001f"}, d2 = {"Landroidx/compose/material3/RadioButtonColors;", "", "Landroidx/compose/ui/graphics/Color;", "selectedColor", "unselectedColor", "disabledSelectedColor", "disabledUnselectedColor", "<init>", "(JJJJLkotlin/jvm/internal/h;)V", "copy-jRlVdoo", "(JJJJ)Landroidx/compose/material3/RadioButtonColors;", "copy", "", "enabled", "selected", "Landroidx/compose/runtime/State;", "radioColor$material3_release", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "radioColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getSelectedColor-0d7_KjU", "()J", "getUnselectedColor-0d7_KjU", "getDisabledSelectedColor-0d7_KjU", "getDisabledUnselectedColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RadioButtonColors {
    public static final int $stable = 0;
    private final long disabledSelectedColor;
    private final long disabledUnselectedColor;
    private final long selectedColor;
    private final long unselectedColor;

    public /* synthetic */ RadioButtonColors(long j10, long j11, long j12, long j13, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13);
    }

    /* JADX INFO: renamed from: copy-jRlVdoo$default, reason: not valid java name */
    public static /* synthetic */ RadioButtonColors m1783copyjRlVdoo$default(RadioButtonColors radioButtonColors, long j10, long j11, long j12, long j13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = radioButtonColors.selectedColor;
        }
        long j14 = j10;
        if ((i10 & 2) != 0) {
            j11 = radioButtonColors.unselectedColor;
        }
        long j15 = j11;
        if ((i10 & 4) != 0) {
            j12 = radioButtonColors.disabledSelectedColor;
        }
        return radioButtonColors.m1784copyjRlVdoo(j14, j15, j12, (i10 & 8) != 0 ? radioButtonColors.disabledUnselectedColor : j13);
    }

    /* JADX INFO: renamed from: copy-jRlVdoo, reason: not valid java name */
    public final RadioButtonColors m1784copyjRlVdoo(long selectedColor, long unselectedColor, long disabledSelectedColor, long disabledUnselectedColor) {
        Color.Companion companion = Color.INSTANCE;
        if (selectedColor == companion.m3508getUnspecified0d7_KjU()) {
            selectedColor = this.selectedColor;
        }
        return new RadioButtonColors(selectedColor, unselectedColor != companion.m3508getUnspecified0d7_KjU() ? unselectedColor : this.unselectedColor, disabledSelectedColor != companion.m3508getUnspecified0d7_KjU() ? disabledSelectedColor : this.disabledSelectedColor, disabledUnselectedColor != companion.m3508getUnspecified0d7_KjU() ? disabledUnselectedColor : this.disabledUnselectedColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof RadioButtonColors)) {
            return false;
        }
        RadioButtonColors radioButtonColors = (RadioButtonColors) other;
        return Color.m3473equalsimpl0(this.selectedColor, radioButtonColors.selectedColor) && Color.m3473equalsimpl0(this.unselectedColor, radioButtonColors.unselectedColor) && Color.m3473equalsimpl0(this.disabledSelectedColor, radioButtonColors.disabledSelectedColor) && Color.m3473equalsimpl0(this.disabledUnselectedColor, radioButtonColors.disabledUnselectedColor);
    }

    /* JADX INFO: renamed from: getDisabledSelectedColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSelectedColor() {
        return this.disabledSelectedColor;
    }

    /* JADX INFO: renamed from: getDisabledUnselectedColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUnselectedColor() {
        return this.disabledUnselectedColor;
    }

    /* JADX INFO: renamed from: getSelectedColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedColor() {
        return this.selectedColor;
    }

    /* JADX INFO: renamed from: getUnselectedColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnselectedColor() {
        return this.unselectedColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.disabledUnselectedColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.selectedColor) * 31, 31, this.unselectedColor), 31, this.disabledSelectedColor);
    }

    public final State<Color> radioColor$material3_release(boolean z, boolean z5, Composer composer, int i10) {
        Composer composer2;
        State<Color> stateRememberUpdatedState;
        composer.startReplaceableGroup(-1840145292);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1840145292, i10, -1, "androidx.compose.material3.RadioButtonColors.radioColor (RadioButton.kt:224)");
        }
        long j10 = (z && z5) ? this.selectedColor : (!z || z5) ? (z || !z5) ? this.disabledUnselectedColor : this.disabledSelectedColor : this.unselectedColor;
        if (z) {
            composer.startReplaceableGroup(-1943768162);
            composer2 = composer;
            stateRememberUpdatedState = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(j10, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer2, 48, 12);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composer;
            composer2.startReplaceableGroup(-1943768057);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(j10), composer2, 0);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    private RadioButtonColors(long j10, long j11, long j12, long j13) {
        this.selectedColor = j10;
        this.unselectedColor = j11;
        this.disabledSelectedColor = j12;
        this.disabledUnselectedColor = j13;
    }
}
