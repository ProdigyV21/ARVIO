package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001Jb\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJb\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0011"}, d2 = {"Landroidx/tv/material3/TabDefaults;", "", "Landroidx/compose/ui/graphics/Color;", "contentColor", "inactiveContentColor", "selectedContentColor", "focusedContentColor", "focusedSelectedContentColor", "disabledContentColor", "disabledInactiveContentColor", "disabledSelectedContentColor", "Landroidx/tv/material3/c4;", "underlinedIndicatorTabColors-oq7We08", "(JJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/c4;", "underlinedIndicatorTabColors", "pillIndicatorTabColors-oq7We08", "pillIndicatorTabColors", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TabDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TabDefaults f5763a = new TabDefaults();

    /* JADX INFO: renamed from: pillIndicatorTabColors-oq7We08, reason: not valid java name */
    public final c4 m6014pillIndicatorTabColorsoq7We08(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(623339441);
        long jM3482unboximpl = (i11 & 1) != 0 ? ((Color) composer.consume(o1.f6258a)).m3482unboximpl() : j10;
        long jM3471copywmQWz5c$default = (i11 & 2) != 0 ? Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j11;
        int i12 = i11 & 4;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jK = i12 != 0 ? materialTheme.getColorScheme(composer, 6).k() : j12;
        long jY = (i11 & 8) != 0 ? materialTheme.getColorScheme(composer, 6).y() : j13;
        long j18 = (i11 & 16) != 0 ? jY : j14;
        long j19 = (i11 & 32) != 0 ? jM3482unboximpl : j15;
        long jM3471copywmQWz5c$default2 = (i11 & 64) != 0 ? Color.m3471copywmQWz5c$default(j19, 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long j20 = (i11 & 128) != 0 ? jK : j17;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(623339441, i10, -1, "androidx.tv.material3.TabDefaults.pillIndicatorTabColors (Tab.kt:211)");
        }
        c4 c4Var = new c4(jM3482unboximpl, jM3471copywmQWz5c$default, jK, jY, j18, j19, jM3471copywmQWz5c$default2, j20);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return c4Var;
    }

    /* JADX INFO: renamed from: underlinedIndicatorTabColors-oq7We08, reason: not valid java name */
    public final c4 m6015underlinedIndicatorTabColorsoq7We08(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(788062096);
        long jM3482unboximpl = (i11 & 1) != 0 ? ((Color) composer.consume(o1.f6258a)).m3482unboximpl() : j10;
        long jM3471copywmQWz5c$default = (i11 & 2) != 0 ? Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j11;
        int i12 = i11 & 4;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jK = i12 != 0 ? materialTheme.getColorScheme(composer, 6).k() : j12;
        long jR = (i11 & 8) != 0 ? materialTheme.getColorScheme(composer, 6).r() : j13;
        long j18 = (i11 & 16) != 0 ? jR : j14;
        long j19 = (i11 & 32) != 0 ? jM3482unboximpl : j15;
        long jM3471copywmQWz5c$default2 = (i11 & 64) != 0 ? Color.m3471copywmQWz5c$default(j19, 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long j20 = (i11 & 128) != 0 ? jK : j17;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(788062096, i10, -1, "androidx.tv.material3.TabDefaults.underlinedIndicatorTabColors (Tab.kt:174)");
        }
        c4 c4Var = new c4(jM3482unboximpl, jM3471copywmQWz5c$default, jK, jR, j18, j19, jM3471copywmQWz5c$default2, j20);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return c4Var;
    }
}
