package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0088\u0001\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u0017\u0010\u0018J%\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ%\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u001f\u0010\u001dJ\u001a\u0010\"\u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010)R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010'\u001a\u0004\b*\u0010)R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010'\u001a\u0004\b+\u0010)R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010'\u001a\u0004\b,\u0010)R\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010'\u001a\u0004\b-\u0010)R\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b.\u0010)R\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b/\u0010)R\u001d\u0010\n\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010'\u001a\u0004\b0\u0010)R\u001d\u0010\u000b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010'\u001a\u0004\b1\u0010)R\u001d\u0010\f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\f\u0010'\u001a\u0004\b2\u0010)R\u001d\u0010\r\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\r\u0010'\u001a\u0004\b3\u0010)R\u001d\u0010\u000e\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000e\u0010'\u001a\u0004\b4\u0010)\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00065"}, d2 = {"Landroidx/compose/material3/CheckboxColors;", "", "Landroidx/compose/ui/graphics/Color;", "checkedCheckmarkColor", "uncheckedCheckmarkColor", "checkedBoxColor", "uncheckedBoxColor", "disabledCheckedBoxColor", "disabledUncheckedBoxColor", "disabledIndeterminateBoxColor", "checkedBorderColor", "uncheckedBorderColor", "disabledBorderColor", "disabledUncheckedBorderColor", "disabledIndeterminateBorderColor", "<init>", "(JJJJJJJJJJJJLkotlin/jvm/internal/h;)V", "copy-2qZNXz8", "(JJJJJJJJJJJJ)Landroidx/compose/material3/CheckboxColors;", "copy", "Landroidx/compose/ui/state/ToggleableState;", "state", "Landroidx/compose/runtime/State;", "checkmarkColor$material3_release", "(Landroidx/compose/ui/state/ToggleableState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "checkmarkColor", "", "enabled", "boxColor$material3_release", "(ZLandroidx/compose/ui/state/ToggleableState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "boxColor", "borderColor$material3_release", "borderColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getCheckedCheckmarkColor-0d7_KjU", "()J", "getUncheckedCheckmarkColor-0d7_KjU", "getCheckedBoxColor-0d7_KjU", "getUncheckedBoxColor-0d7_KjU", "getDisabledCheckedBoxColor-0d7_KjU", "getDisabledUncheckedBoxColor-0d7_KjU", "getDisabledIndeterminateBoxColor-0d7_KjU", "getCheckedBorderColor-0d7_KjU", "getUncheckedBorderColor-0d7_KjU", "getDisabledBorderColor-0d7_KjU", "getDisabledUncheckedBorderColor-0d7_KjU", "getDisabledIndeterminateBorderColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CheckboxColors {
    public static final int $stable = 0;
    private final long checkedBorderColor;
    private final long checkedBoxColor;
    private final long checkedCheckmarkColor;
    private final long disabledBorderColor;
    private final long disabledCheckedBoxColor;
    private final long disabledIndeterminateBorderColor;
    private final long disabledIndeterminateBoxColor;
    private final long disabledUncheckedBorderColor;
    private final long disabledUncheckedBoxColor;
    private final long uncheckedBorderColor;
    private final long uncheckedBoxColor;
    private final long uncheckedCheckmarkColor;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ CheckboxColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21);
    }

    /* JADX INFO: renamed from: copy-2qZNXz8$default, reason: not valid java name */
    public static /* synthetic */ CheckboxColors m1300copy2qZNXz8$default(CheckboxColors checkboxColors, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, int i10, Object obj) {
        long j22;
        long j23;
        long j24 = (i10 & 1) != 0 ? checkboxColors.checkedCheckmarkColor : j10;
        long j25 = (i10 & 2) != 0 ? checkboxColors.uncheckedCheckmarkColor : j11;
        long j26 = (i10 & 4) != 0 ? checkboxColors.checkedBoxColor : j12;
        long j27 = (i10 & 8) != 0 ? checkboxColors.uncheckedBoxColor : j13;
        long j28 = (i10 & 16) != 0 ? checkboxColors.disabledCheckedBoxColor : j14;
        long j29 = (i10 & 32) != 0 ? checkboxColors.disabledUncheckedBoxColor : j15;
        long j30 = (i10 & 64) != 0 ? checkboxColors.disabledIndeterminateBoxColor : j16;
        long j31 = j24;
        long j32 = (i10 & 128) != 0 ? checkboxColors.checkedBorderColor : j17;
        long j33 = (i10 & 256) != 0 ? checkboxColors.uncheckedBorderColor : j18;
        long j34 = (i10 & 512) != 0 ? checkboxColors.disabledBorderColor : j19;
        long j35 = (i10 & 1024) != 0 ? checkboxColors.disabledUncheckedBorderColor : j20;
        if ((i10 & 2048) != 0) {
            j23 = j35;
            j22 = checkboxColors.disabledIndeterminateBorderColor;
        } else {
            j22 = j21;
            j23 = j35;
        }
        return checkboxColors.m1301copy2qZNXz8(j31, j25, j26, j27, j28, j29, j30, j32, j33, j34, j23, j22);
    }

    public final State<Color> borderColor$material3_release(boolean z, ToggleableState toggleableState, Composer composer, int i10) {
        long j10;
        Composer composer2;
        State<Color> stateRememberUpdatedState;
        composer.startReplaceableGroup(1009643462);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1009643462, i10, -1, "androidx.compose.material3.CheckboxColors.borderColor (Checkbox.kt:526)");
        }
        if (z) {
            int i11 = WhenMappings.$EnumSwitchMapping$0[toggleableState.ordinal()];
            if (i11 == 1 || i11 == 2) {
                j10 = this.checkedBorderColor;
            } else {
                if (i11 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                j10 = this.uncheckedBorderColor;
            }
        } else {
            int i12 = WhenMappings.$EnumSwitchMapping$0[toggleableState.ordinal()];
            if (i12 == 1) {
                j10 = this.disabledBorderColor;
            } else if (i12 == 2) {
                j10 = this.disabledIndeterminateBorderColor;
            } else {
                if (i12 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                j10 = this.disabledUncheckedBorderColor;
            }
        }
        long j11 = j10;
        if (z) {
            composer.startReplaceableGroup(1209374481);
            composer2 = composer;
            stateRememberUpdatedState = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(j11, AnimationSpecKt.tween$default(toggleableState == ToggleableState.Off ? 100 : 50, 0, null, 6, null), null, null, composer2, 0, 12);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composer;
            composer2.startReplaceableGroup(1209374667);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(j11), composer2, 0);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> boxColor$material3_release(boolean z, ToggleableState toggleableState, Composer composer, int i10) {
        long j10;
        Composer composer2;
        State<Color> stateRememberUpdatedState;
        composer.startReplaceableGroup(360729865);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(360729865, i10, -1, "androidx.compose.material3.CheckboxColors.boxColor (Checkbox.kt:495)");
        }
        if (z) {
            int i11 = WhenMappings.$EnumSwitchMapping$0[toggleableState.ordinal()];
            if (i11 == 1 || i11 == 2) {
                j10 = this.checkedBoxColor;
            } else {
                if (i11 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                j10 = this.uncheckedBoxColor;
            }
        } else {
            int i12 = WhenMappings.$EnumSwitchMapping$0[toggleableState.ordinal()];
            if (i12 == 1) {
                j10 = this.disabledCheckedBoxColor;
            } else if (i12 == 2) {
                j10 = this.disabledIndeterminateBoxColor;
            } else {
                if (i12 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                j10 = this.disabledUncheckedBoxColor;
            }
        }
        long j11 = j10;
        if (z) {
            composer.startReplaceableGroup(1143723294);
            composer2 = composer;
            stateRememberUpdatedState = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(j11, AnimationSpecKt.tween$default(toggleableState == ToggleableState.Off ? 100 : 50, 0, null, 6, null), null, null, composer2, 0, 12);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composer;
            composer2.startReplaceableGroup(1143723480);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(j11), composer2, 0);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> checkmarkColor$material3_release(ToggleableState toggleableState, Composer composer, int i10) {
        composer.startReplaceableGroup(-507585681);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-507585681, i10, -1, "androidx.compose.material3.CheckboxColors.checkmarkColor (Checkbox.kt:476)");
        }
        ToggleableState toggleableState2 = ToggleableState.Off;
        State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(toggleableState == toggleableState2 ? this.uncheckedCheckmarkColor : this.checkedCheckmarkColor, AnimationSpecKt.tween$default(toggleableState == toggleableState2 ? 100 : 50, 0, null, 6, null), null, null, composer, 0, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    /* JADX INFO: renamed from: copy-2qZNXz8, reason: not valid java name */
    public final CheckboxColors m1301copy2qZNXz8(long checkedCheckmarkColor, long uncheckedCheckmarkColor, long checkedBoxColor, long uncheckedBoxColor, long disabledCheckedBoxColor, long disabledUncheckedBoxColor, long disabledIndeterminateBoxColor, long checkedBorderColor, long uncheckedBorderColor, long disabledBorderColor, long disabledUncheckedBorderColor, long disabledIndeterminateBorderColor) {
        Color.Companion companion = Color.INSTANCE;
        return new CheckboxColors(checkedCheckmarkColor != companion.m3508getUnspecified0d7_KjU() ? checkedCheckmarkColor : this.checkedCheckmarkColor, uncheckedCheckmarkColor != companion.m3508getUnspecified0d7_KjU() ? uncheckedCheckmarkColor : this.uncheckedCheckmarkColor, checkedBoxColor != companion.m3508getUnspecified0d7_KjU() ? checkedBoxColor : this.checkedBoxColor, uncheckedBoxColor != companion.m3508getUnspecified0d7_KjU() ? uncheckedBoxColor : this.uncheckedBoxColor, disabledCheckedBoxColor != companion.m3508getUnspecified0d7_KjU() ? disabledCheckedBoxColor : this.disabledCheckedBoxColor, disabledUncheckedBoxColor != companion.m3508getUnspecified0d7_KjU() ? disabledUncheckedBoxColor : this.disabledUncheckedBoxColor, disabledIndeterminateBoxColor != companion.m3508getUnspecified0d7_KjU() ? disabledIndeterminateBoxColor : this.disabledIndeterminateBoxColor, checkedBorderColor != companion.m3508getUnspecified0d7_KjU() ? checkedBorderColor : this.checkedBorderColor, uncheckedBorderColor != companion.m3508getUnspecified0d7_KjU() ? uncheckedBorderColor : this.uncheckedBorderColor, disabledBorderColor != companion.m3508getUnspecified0d7_KjU() ? disabledBorderColor : this.disabledBorderColor, disabledUncheckedBorderColor != companion.m3508getUnspecified0d7_KjU() ? disabledUncheckedBorderColor : this.disabledUncheckedBorderColor, disabledIndeterminateBorderColor != companion.m3508getUnspecified0d7_KjU() ? disabledIndeterminateBorderColor : this.disabledIndeterminateBorderColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof CheckboxColors)) {
            return false;
        }
        CheckboxColors checkboxColors = (CheckboxColors) other;
        return Color.m3473equalsimpl0(this.checkedCheckmarkColor, checkboxColors.checkedCheckmarkColor) && Color.m3473equalsimpl0(this.uncheckedCheckmarkColor, checkboxColors.uncheckedCheckmarkColor) && Color.m3473equalsimpl0(this.checkedBoxColor, checkboxColors.checkedBoxColor) && Color.m3473equalsimpl0(this.uncheckedBoxColor, checkboxColors.uncheckedBoxColor) && Color.m3473equalsimpl0(this.disabledCheckedBoxColor, checkboxColors.disabledCheckedBoxColor) && Color.m3473equalsimpl0(this.disabledUncheckedBoxColor, checkboxColors.disabledUncheckedBoxColor) && Color.m3473equalsimpl0(this.disabledIndeterminateBoxColor, checkboxColors.disabledIndeterminateBoxColor) && Color.m3473equalsimpl0(this.checkedBorderColor, checkboxColors.checkedBorderColor) && Color.m3473equalsimpl0(this.uncheckedBorderColor, checkboxColors.uncheckedBorderColor) && Color.m3473equalsimpl0(this.disabledBorderColor, checkboxColors.disabledBorderColor) && Color.m3473equalsimpl0(this.disabledUncheckedBorderColor, checkboxColors.disabledUncheckedBorderColor) && Color.m3473equalsimpl0(this.disabledIndeterminateBorderColor, checkboxColors.disabledIndeterminateBorderColor);
    }

    /* JADX INFO: renamed from: getCheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedBorderColor() {
        return this.checkedBorderColor;
    }

    /* JADX INFO: renamed from: getCheckedBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedBoxColor() {
        return this.checkedBoxColor;
    }

    /* JADX INFO: renamed from: getCheckedCheckmarkColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedCheckmarkColor() {
        return this.checkedCheckmarkColor;
    }

    /* JADX INFO: renamed from: getDisabledBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledBorderColor() {
        return this.disabledBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedBoxColor() {
        return this.disabledCheckedBoxColor;
    }

    /* JADX INFO: renamed from: getDisabledIndeterminateBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledIndeterminateBorderColor() {
        return this.disabledIndeterminateBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledIndeterminateBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledIndeterminateBoxColor() {
        return this.disabledIndeterminateBoxColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedBorderColor() {
        return this.disabledUncheckedBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedBoxColor() {
        return this.disabledUncheckedBoxColor;
    }

    /* JADX INFO: renamed from: getUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedBorderColor() {
        return this.uncheckedBorderColor;
    }

    /* JADX INFO: renamed from: getUncheckedBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedBoxColor() {
        return this.uncheckedBoxColor;
    }

    /* JADX INFO: renamed from: getUncheckedCheckmarkColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedCheckmarkColor() {
        return this.uncheckedCheckmarkColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.disabledIndeterminateBorderColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.checkedCheckmarkColor) * 31, 31, this.uncheckedCheckmarkColor), 31, this.checkedBoxColor), 31, this.uncheckedBoxColor), 31, this.disabledCheckedBoxColor), 31, this.disabledUncheckedBoxColor), 31, this.disabledIndeterminateBoxColor), 31, this.checkedBorderColor), 31, this.uncheckedBorderColor), 31, this.disabledBorderColor), 31, this.disabledUncheckedBorderColor);
    }

    private CheckboxColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21) {
        this.checkedCheckmarkColor = j10;
        this.uncheckedCheckmarkColor = j11;
        this.checkedBoxColor = j12;
        this.uncheckedBoxColor = j13;
        this.disabledCheckedBoxColor = j14;
        this.disabledUncheckedBoxColor = j15;
        this.disabledIndeterminateBoxColor = j16;
        this.checkedBorderColor = j17;
        this.uncheckedBorderColor = j18;
        this.disabledBorderColor = j19;
        this.disabledUncheckedBorderColor = j20;
        this.disabledIndeterminateBorderColor = j21;
    }
}
