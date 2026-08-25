package androidx.tv.material3;

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

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u000e\u0010\f¨\u0006\u0010"}, d2 = {"Landroidx/tv/material3/CheckboxColors;", "", "Landroidx/compose/ui/state/ToggleableState;", "state", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/graphics/Color;", "checkmarkColor$tv_material_release", "(Landroidx/compose/ui/state/ToggleableState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "checkmarkColor", "", "enabled", "boxColor$tv_material_release", "(ZLandroidx/compose/ui/state/ToggleableState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "boxColor", "borderColor$tv_material_release", "borderColor", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CheckboxColors {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5574a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5575b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5576c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f5577d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f5578e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f5579f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f5580g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f5581h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f5582i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f5583j;
    public final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f5584l;

    public CheckboxColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21) {
        this.f5574a = j10;
        this.f5575b = j11;
        this.f5576c = j12;
        this.f5577d = j13;
        this.f5578e = j14;
        this.f5579f = j15;
        this.f5580g = j16;
        this.f5581h = j17;
        this.f5582i = j18;
        this.f5583j = j19;
        this.k = j20;
        this.f5584l = j21;
    }

    public final State<Color> borderColor$tv_material_release(boolean z, ToggleableState toggleableState, Composer composer, int i10) {
        long j10;
        Composer composer2;
        State<Color> stateRememberUpdatedState;
        composer.startReplaceableGroup(-1198140658);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1198140658, i10, -1, "androidx.tv.material3.CheckboxColors.borderColor (Checkbox.kt:513)");
        }
        if (z) {
            int i11 = n0.f6229a[toggleableState.ordinal()];
            if (i11 == 1 || i11 == 2) {
                j10 = this.f5581h;
            } else {
                if (i11 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                j10 = this.f5582i;
            }
        } else {
            int i12 = n0.f6229a[toggleableState.ordinal()];
            if (i12 == 1) {
                j10 = this.f5583j;
            } else if (i12 == 2) {
                j10 = this.f5584l;
            } else {
                if (i12 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                j10 = this.k;
            }
        }
        long j11 = j10;
        if (z) {
            composer.startReplaceableGroup(434784713);
            composer2 = composer;
            stateRememberUpdatedState = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(j11, AnimationSpecKt.tween$default(toggleableState == ToggleableState.Off ? 100 : 50, 0, null, 6, null), null, null, composer2, 0, 12);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composer;
            composer2.startReplaceableGroup(434965257);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(j11), composer2, 0);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> boxColor$tv_material_release(boolean z, ToggleableState toggleableState, Composer composer, int i10) {
        long j10;
        Composer composer2;
        State<Color> stateRememberUpdatedState;
        composer.startReplaceableGroup(-457639957);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-457639957, i10, -1, "androidx.tv.material3.CheckboxColors.boxColor (Checkbox.kt:480)");
        }
        if (z) {
            int i11 = n0.f6229a[toggleableState.ordinal()];
            if (i11 == 1 || i11 == 2) {
                j10 = this.f5576c;
            } else {
                if (i11 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                j10 = this.f5577d;
            }
        } else {
            int i12 = n0.f6229a[toggleableState.ordinal()];
            if (i12 == 1) {
                j10 = this.f5578e;
            } else if (i12 == 2) {
                j10 = this.f5580g;
            } else {
                if (i12 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                j10 = this.f5579f;
            }
        }
        long j11 = j10;
        if (z) {
            composer.startReplaceableGroup(1248372932);
            composer2 = composer;
            stateRememberUpdatedState = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(j11, AnimationSpecKt.tween$default(toggleableState == ToggleableState.Off ? 100 : 50, 0, null, 6, null), null, null, composer2, 0, 12);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composer;
            composer2.startReplaceableGroup(1248553476);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(j11), composer2, 0);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> checkmarkColor$tv_material_release(ToggleableState toggleableState, Composer composer, int i10) {
        composer.startReplaceableGroup(-1513586235);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1513586235, i10, -1, "androidx.tv.material3.CheckboxColors.checkmarkColor (Checkbox.kt:460)");
        }
        ToggleableState toggleableState2 = ToggleableState.Off;
        State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(toggleableState == toggleableState2 ? this.f5575b : this.f5574a, AnimationSpecKt.tween$default(toggleableState == toggleableState2 ? 100 : 50, 0, null, 6, null), null, null, composer, 0, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CheckboxColors)) {
            return false;
        }
        CheckboxColors checkboxColors = (CheckboxColors) obj;
        return Color.m3473equalsimpl0(this.f5574a, checkboxColors.f5574a) && Color.m3473equalsimpl0(this.f5575b, checkboxColors.f5575b) && Color.m3473equalsimpl0(this.f5576c, checkboxColors.f5576c) && Color.m3473equalsimpl0(this.f5577d, checkboxColors.f5577d) && Color.m3473equalsimpl0(this.f5578e, checkboxColors.f5578e) && Color.m3473equalsimpl0(this.f5579f, checkboxColors.f5579f) && Color.m3473equalsimpl0(this.f5580g, checkboxColors.f5580g) && Color.m3473equalsimpl0(this.f5581h, checkboxColors.f5581h) && Color.m3473equalsimpl0(this.f5582i, checkboxColors.f5582i) && Color.m3473equalsimpl0(this.f5583j, checkboxColors.f5583j) && Color.m3473equalsimpl0(this.k, checkboxColors.k) && Color.m3473equalsimpl0(this.f5584l, checkboxColors.f5584l);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f5584l) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f5574a) * 31, 31, this.f5575b), 31, this.f5576c), 31, this.f5577d), 31, this.f5578e), 31, this.f5579f), 31, this.f5580g), 31, this.f5581h), 31, this.f5582i), 31, this.f5583j), 31, this.k);
    }
}
