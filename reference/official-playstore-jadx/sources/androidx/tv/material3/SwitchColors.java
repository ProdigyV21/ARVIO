package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001J%\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0007\u0010\bJ%\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\n\u0010\bJ%\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\f\u0010\bJ%\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u000e\u0010\b¨\u0006\u0010"}, d2 = {"Landroidx/tv/material3/SwitchColors;", "", "", "enabled", "checked", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/graphics/Color;", "thumbColor$tv_material_release", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "thumbColor", "trackColor$tv_material_release", "trackColor", "borderColor$tv_material_release", "borderColor", "iconColor$tv_material_release", "iconColor", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SwitchColors {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5741a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5742b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5743c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f5744d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f5745e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f5746f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f5747g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f5748h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f5749i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f5750j;
    public final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f5751l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f5752m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f5753n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f5754o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f5755p;

    public SwitchColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25) {
        this.f5741a = j10;
        this.f5742b = j11;
        this.f5743c = j12;
        this.f5744d = j13;
        this.f5745e = j14;
        this.f5746f = j15;
        this.f5747g = j16;
        this.f5748h = j17;
        this.f5749i = j18;
        this.f5750j = j19;
        this.k = j20;
        this.f5751l = j21;
        this.f5752m = j22;
        this.f5753n = j23;
        this.f5754o = j24;
        this.f5755p = j25;
    }

    public final State<Color> borderColor$tv_material_release(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(1371778897);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1371778897, i10, -1, "androidx.tv.material3.SwitchColors.borderColor (Switch.kt:476)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? z5 ? this.f5743c : this.f5747g : z5 ? this.k : this.f5754o), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SwitchColors)) {
            return false;
        }
        SwitchColors switchColors = (SwitchColors) obj;
        return Color.m3473equalsimpl0(this.f5741a, switchColors.f5741a) && Color.m3473equalsimpl0(this.f5742b, switchColors.f5742b) && Color.m3473equalsimpl0(this.f5743c, switchColors.f5743c) && Color.m3473equalsimpl0(this.f5744d, switchColors.f5744d) && Color.m3473equalsimpl0(this.f5745e, switchColors.f5745e) && Color.m3473equalsimpl0(this.f5746f, switchColors.f5746f) && Color.m3473equalsimpl0(this.f5747g, switchColors.f5747g) && Color.m3473equalsimpl0(this.f5748h, switchColors.f5748h) && Color.m3473equalsimpl0(this.f5749i, switchColors.f5749i) && Color.m3473equalsimpl0(this.f5750j, switchColors.f5750j) && Color.m3473equalsimpl0(this.k, switchColors.k) && Color.m3473equalsimpl0(this.f5751l, switchColors.f5751l) && Color.m3473equalsimpl0(this.f5752m, switchColors.f5752m) && Color.m3473equalsimpl0(this.f5753n, switchColors.f5753n) && Color.m3473equalsimpl0(this.f5754o, switchColors.f5754o) && Color.m3473equalsimpl0(this.f5755p, switchColors.f5755p);
    }

    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f5755p) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.f5741a) * 31, 31, this.f5742b), 31, this.f5743c), 31, this.f5744d), 31, this.f5745e), 31, this.f5746f), 31, this.f5747g), 31, this.f5748h), 31, this.f5749i), 31, this.f5750j), 31, this.k), 31, this.f5751l), 31, this.f5752m), 31, this.f5753n), 31, this.f5754o);
    }

    public final State<Color> iconColor$tv_material_release(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(1020931300);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1020931300, i10, -1, "androidx.tv.material3.SwitchColors.iconColor (Switch.kt:493)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? z5 ? this.f5744d : this.f5748h : z5 ? this.f5751l : this.f5755p), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> thumbColor$tv_material_release(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(-1945390141);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1945390141, i10, -1, "androidx.tv.material3.SwitchColors.thumbColor (Switch.kt:442)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? z5 ? this.f5741a : this.f5745e : z5 ? this.f5749i : this.f5752m), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> trackColor$tv_material_release(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(1170868398);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1170868398, i10, -1, "androidx.tv.material3.SwitchColors.trackColor (Switch.kt:459)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? z5 ? this.f5742b : this.f5746f : z5 ? this.f5750j : this.f5753n), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }
}
