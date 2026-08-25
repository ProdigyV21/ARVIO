package androidx.tv.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001J%\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroidx/tv/material3/RadioButtonColors;", "", "", "enabled", "selected", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/graphics/Color;", "radioColor$tv_material_release", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "radioColor", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RadioButtonColors {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5701a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5702b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5703c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f5704d;

    public RadioButtonColors(long j10, long j11, long j12, long j13) {
        this.f5701a = j10;
        this.f5702b = j11;
        this.f5703c = j12;
        this.f5704d = j13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RadioButtonColors)) {
            return false;
        }
        RadioButtonColors radioButtonColors = (RadioButtonColors) obj;
        return Color.m3473equalsimpl0(this.f5701a, radioButtonColors.f5701a) && Color.m3473equalsimpl0(this.f5702b, radioButtonColors.f5702b) && Color.m3473equalsimpl0(this.f5703c, radioButtonColors.f5703c) && Color.m3473equalsimpl0(this.f5704d, radioButtonColors.f5704d);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f5704d) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f5701a) * 31, 31, this.f5702b), 31, this.f5703c);
    }

    public final State<Color> radioColor$tv_material_release(boolean z, boolean z5, Composer composer, int i10) {
        Composer composer2;
        State<Color> stateRememberUpdatedState;
        composer.startReplaceableGroup(882374260);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(882374260, i10, -1, "androidx.tv.material3.RadioButtonColors.radioColor (RadioButton.kt:213)");
        }
        long j10 = (z && z5) ? this.f5701a : (!z || z5) ? (z || !z5) ? this.f5704d : this.f5703c : this.f5702b;
        if (z) {
            composer.startReplaceableGroup(-1524912483);
            composer2 = composer;
            stateRememberUpdatedState = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(j10, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer2, 48, 12);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composer;
            composer2.startReplaceableGroup(-1524809780);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(j10), composer2, 0);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateRememberUpdatedState;
    }
}
