package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001Js\u0010\u0003\u001a\u00020\r2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u000eJ\u009e\u0001\u0010!\u001a\u00020\u001e2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u000f2\b\b\u0002\u0010\u0019\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f2\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"Landroidx/tv/material3/SelectableSurfaceDefaults;", "", "Landroidx/compose/ui/graphics/Shape;", "shape", "focusedShape", "pressedShape", "selectedShape", "disabledShape", "focusedSelectedShape", "focusedDisabledShape", "pressedSelectedShape", "selectedDisabledShape", "focusedSelectedDisabledShape", "Landroidx/tv/material3/b3;", "(Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;III)Landroidx/tv/material3/b3;", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "focusedContainerColor", "focusedContentColor", "pressedContainerColor", "pressedContentColor", "selectedContainerColor", "selectedContentColor", "disabledContainerColor", "disabledContentColor", "focusedSelectedContainerColor", "focusedSelectedContentColor", "pressedSelectedContainerColor", "pressedSelectedContentColor", "Landroidx/tv/material3/y2;", "colors-u3YEpmA", "(JJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/tv/material3/y2;", "colors", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SelectableSurfaceDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SelectableSurfaceDefaults f5709a = new SelectableSurfaceDefaults();

    /* JADX INFO: renamed from: colors-u3YEpmA, reason: not valid java name */
    public final y2 m6004colorsu3YEpmA(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, Composer composer, int i10, int i11, int i12) {
        int i13 = i12 & 1;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jW = i13 != 0 ? materialTheme.getColorScheme(composer, 6).w() : j10;
        long jM5987contentColorForek8zF_U = (i12 & 2) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jW, composer, i10 & 14) : j11;
        long jF = (i12 & 4) != 0 ? materialTheme.getColorScheme(composer, 6).f() : j12;
        long jM5987contentColorForek8zF_U2 = (i12 & 8) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jF, composer, (i10 >> 6) & 14) : j13;
        long j24 = (i12 & 16) != 0 ? jF : j14;
        long jM5987contentColorForek8zF_U3 = (i12 & 32) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(j24, composer, (i10 >> 12) & 14) : j15;
        long jM3471copywmQWz5c$default = (i12 & 64) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).f(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long jE = (i12 & 128) != 0 ? materialTheme.getColorScheme(composer, 6).e() : j17;
        long jM3471copywmQWz5c$default2 = (i12 & 256) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).y(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j18;
        long jN = (i12 & 512) != 0 ? materialTheme.getColorScheme(composer, 6).n() : j19;
        long jM3471copywmQWz5c$default3 = (i12 & 1024) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).f(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null) : j20;
        long jE2 = (i12 & 2048) != 0 ? materialTheme.getColorScheme(composer, 6).e() : j21;
        long j25 = (i12 & 4096) != 0 ? jM3471copywmQWz5c$default3 : j22;
        long j26 = (i12 & 8192) != 0 ? jE2 : j23;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-559008714, i10, i11, "androidx.tv.material3.SelectableSurfaceDefaults.colors (SurfaceDefaults.kt:376)");
        }
        y2 y2Var = new y2(jW, jM5987contentColorForek8zF_U, jF, jM5987contentColorForek8zF_U2, j24, jM5987contentColorForek8zF_U3, jM3471copywmQWz5c$default, jE, jM3471copywmQWz5c$default2, jN, jM3471copywmQWz5c$default3, jE2, j25, j26);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return y2Var;
    }

    public final b3 shape(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5, Shape shape6, Shape shape7, Shape shape8, Shape shape9, Shape shape10, Composer composer, int i10, int i11, int i12) {
        if ((i12 & 1) != 0) {
            shape = MaterialTheme.f5670a.getShapes(composer, 6).f5914c;
        }
        if ((i12 & 2) != 0) {
            shape2 = shape;
        }
        if ((i12 & 4) != 0) {
            shape3 = shape;
        }
        if ((i12 & 8) != 0) {
            shape4 = shape;
        }
        if ((i12 & 16) != 0) {
            shape5 = shape;
        }
        if ((i12 & 32) != 0) {
            shape6 = shape;
        }
        if ((i12 & 64) != 0) {
            shape7 = shape5;
        }
        if ((i12 & 128) != 0) {
            shape8 = shape;
        }
        Shape shape11 = (i12 & 256) != 0 ? shape5 : shape9;
        if ((i12 & 512) != 0) {
            shape10 = shape5;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(224690110, i10, i11, "androidx.tv.material3.SelectableSurfaceDefaults.shape (SurfaceDefaults.kt:314)");
        }
        Shape shape12 = shape10;
        Shape shape13 = shape8;
        Shape shape14 = shape7;
        Shape shape15 = shape5;
        Shape shape16 = shape3;
        Shape shape17 = shape;
        b3 b3Var = new b3(shape17, shape2, shape16, shape4, shape15, shape6, shape14, shape13, shape11, shape12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return b3Var;
    }
}
