package androidx.tv.material3;

import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001JA\u0010\u0003\u001a\u00020\b2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\tJb\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015JA\u0010\u0018\u001a\u00020\u001d2\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u0018\u0010\u001e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"Landroidx/tv/material3/ClickableSurfaceDefaults;", "", "Landroidx/compose/ui/graphics/Shape;", "shape", "focusedShape", "pressedShape", "disabledShape", "focusedDisabledShape", "Landroidx/tv/material3/g1;", "(Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/g1;", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "focusedContainerColor", "focusedContentColor", "pressedContainerColor", "pressedContentColor", "disabledContainerColor", "disabledContentColor", "Landroidx/tv/material3/d1;", "colors-oq7We08", "(JJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/d1;", "colors", "Landroidx/tv/material3/d;", "border", "focusedBorder", "pressedBorder", "disabledBorder", "focusedDisabledBorder", "Landroidx/tv/material3/c1;", "(Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/c1;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ClickableSurfaceDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ClickableSurfaceDefaults f5608a = new ClickableSurfaceDefaults();

    public final c1 border(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            dVar = d.f5889d;
        }
        if ((i11 & 2) != 0) {
            dVar2 = dVar;
        }
        if ((i11 & 4) != 0) {
            dVar3 = dVar2;
        }
        if ((i11 & 8) != 0) {
            dVar4 = dVar;
        }
        if ((i11 & 16) != 0) {
            dVar5 = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(2), MaterialTheme.f5670a.getColorScheme(composer, 6).b()), Dp.m5678constructorimpl(0), c3.f5877b);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1724208330, i10, -1, "androidx.tv.material3.ClickableSurfaceDefaults.border (SurfaceDefaults.kt:246)");
        }
        d dVar6 = dVar5;
        d dVar7 = dVar3;
        c1 c1Var = new c1(dVar, dVar2, dVar7, dVar4, dVar6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return c1Var;
    }

    /* JADX INFO: renamed from: colors-oq7We08, reason: not valid java name */
    public final d1 m5986colorsoq7We08(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, Composer composer, int i10, int i11) {
        int i12 = i11 & 1;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jW = i12 != 0 ? materialTheme.getColorScheme(composer, 6).w() : j10;
        long jM5987contentColorForek8zF_U = (i11 & 2) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jW, composer, i10 & 14) : j11;
        long jF = (i11 & 4) != 0 ? materialTheme.getColorScheme(composer, 6).f() : j12;
        long jM5987contentColorForek8zF_U2 = (i11 & 8) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jF, composer, (i10 >> 6) & 14) : j13;
        long j18 = (i11 & 16) != 0 ? jF : j14;
        long jM5987contentColorForek8zF_U3 = (i11 & 32) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(j18, composer, (i10 >> 12) & 14) : j15;
        long jM3471copywmQWz5c$default = (i11 & 64) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).y(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long jN = (i11 & 128) != 0 ? materialTheme.getColorScheme(composer, 6).n() : j17;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(165146566, i10, -1, "androidx.tv.material3.ClickableSurfaceDefaults.colors (SurfaceDefaults.kt:153)");
        }
        d1 d1Var = new d1(jW, jM5987contentColorForek8zF_U, jF, jM5987contentColorForek8zF_U2, j18, jM5987contentColorForek8zF_U3, jM3471copywmQWz5c$default, jN);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return d1Var;
    }

    public final g1 shape(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            shape = MaterialTheme.f5670a.getShapes(composer, 6).f5914c;
        }
        if ((i11 & 2) != 0) {
            shape2 = shape;
        }
        if ((i11 & 4) != 0) {
            shape3 = shape;
        }
        Shape shape6 = (i11 & 8) != 0 ? shape : shape4;
        if ((i11 & 16) != 0) {
            shape5 = shape6;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1870960029, i10, -1, "androidx.tv.material3.ClickableSurfaceDefaults.shape (SurfaceDefaults.kt:91)");
        }
        Shape shape7 = shape5;
        g1 g1Var = new g1(shape, shape2, shape3, shape6, shape7);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return g1Var;
    }
}
