package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001R\u0011\u0010\u0005\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0011\u0010\t\u001a\u00020\u00068G¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\r\u001a\u00020\n8G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/tv/material3/MaterialTheme;", "", "Landroidx/tv/material3/h1;", "getColorScheme", "(Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/h1;", "colorScheme", "Landroidx/tv/material3/p4;", "getTypography", "(Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/p4;", "typography", "Landroidx/tv/material3/d3;", "getShapes", "(Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/d3;", "shapes", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MaterialTheme {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final MaterialTheme f5670a = new MaterialTheme();

    public final h1 getColorScheme(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1726685380, i10, -1, "androidx.tv.material3.MaterialTheme.<get-colorScheme> (MaterialTheme.kt:82)");
        }
        h1 h1Var = (h1) composer.consume(ColorSchemeKt.f5609a);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return h1Var;
    }

    public final d3 getShapes(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1811626840, i10, -1, "androidx.tv.material3.MaterialTheme.<get-shapes> (MaterialTheme.kt:90)");
        }
        d3 d3Var = (d3) composer.consume(ShapesKt.f5710a);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return d3Var;
    }

    public final p4 getTypography(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(898663541, i10, -1, "androidx.tv.material3.MaterialTheme.<get-typography> (MaterialTheme.kt:86)");
        }
        p4 p4Var = (p4) composer.consume(q4.f6337a);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return p4Var;
    }
}
