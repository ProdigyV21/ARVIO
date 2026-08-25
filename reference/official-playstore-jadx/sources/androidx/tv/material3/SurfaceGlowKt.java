package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/tv/material3/s1;", "glow", "tvSurfaceGlow", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/tv/material3/s1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SurfaceGlowKt {
    public static final Modifier tvSurfaceGlow(Modifier modifier, Shape shape, s1 s1Var, Composer composer, int i10) {
        composer.startReplaceableGroup(-463445277);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-463445277, i10, -1, "androidx.tv.material3.tvSurfaceGlow (SurfaceGlow.kt:38)");
        }
        long j10 = s1Var.f6377a;
        float f10 = s1Var.f6378b;
        Modifier modifierThen = modifier.then(new SurfaceGlowElement(shape, ((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo285toPx0680j_4(f10), SurfaceKt.m6011surfaceColorAtElevationCLU3JFs(j10, f10, composer, 0), InspectableValueKt.isDebugInspectorInfoEnabled() ? new androidx.navigation.p(shape, s1Var, 5) : InspectableValueKt.getNoInspectorInfo()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }
}
