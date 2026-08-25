package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001J&\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\f\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/tv/material3/SurfaceDefaults;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "Landroidx/tv/material3/f3;", "colors-dgg9oW8", "(JJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/f3;", "colors", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "shape", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SurfaceDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SurfaceDefaults f5717a = new SurfaceDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f5718b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final s1 f5719c;

    static {
        d dVar = d.f5889d;
        f5718b = d.f5889d;
        s1 s1Var = s1.f6376c;
        f5719c = s1.f6376c;
    }

    /* JADX INFO: renamed from: colors-dgg9oW8, reason: not valid java name */
    public final f3 m6006colorsdgg9oW8(long j10, long j11, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            j10 = MaterialTheme.f5670a.getColorScheme(composer, 6).w();
        }
        if ((i11 & 2) != 0) {
            j11 = ColorSchemeKt.m5987contentColorForek8zF_U(j10, composer, i10 & 14);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-642120114, i10, -1, "androidx.tv.material3.SurfaceDefaults.colors (SurfaceDefaults.kt:46)");
        }
        f3 f3Var = new f3(j10, j11);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return f3Var;
    }

    public final Shape getShape(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(748174471, i10, -1, "androidx.tv.material3.SurfaceDefaults.<get-shape> (SurfaceDefaults.kt:32)");
        }
        Shape rectangleShape = RectangleShapeKt.getRectangleShape();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return rectangleShape;
    }
}
