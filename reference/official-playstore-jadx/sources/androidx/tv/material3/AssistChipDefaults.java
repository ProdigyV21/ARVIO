package androidx.tv.material3;

import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001Jb\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJA\u0010\u0010\u001a\u00020\u00152\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/tv/material3/AssistChipDefaults;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "focusedContainerColor", "focusedContentColor", "pressedContainerColor", "pressedContentColor", "disabledContainerColor", "disabledContentColor", "Landroidx/tv/material3/y0;", "colors-oq7We08", "(JJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/y0;", "colors", "Landroidx/tv/material3/d;", "border", "focusedBorder", "pressedBorder", "disabledBorder", "focusedDisabledBorder", "Landroidx/tv/material3/x0;", "(Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/x0;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AssistChipDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AssistChipDefaults f5527a = new AssistChipDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5528b = Dp.m5678constructorimpl(36);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final RoundedCornerShape f5529c;

    static {
        Dp.m5678constructorimpl(18);
        f5529c = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(8));
    }

    public final x0 border(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, Composer composer, int i10, int i11) {
        int i12 = i11 & 1;
        RoundedCornerShape roundedCornerShape = f5529c;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        if (i12 != 0) {
            dVar = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), materialTheme.getColorScheme(composer, 6).b()), roundedCornerShape, 2);
        }
        if ((i11 & 2) != 0) {
            dVar2 = d.f5889d;
        }
        if ((i11 & 4) != 0) {
            dVar3 = dVar2;
        }
        if ((i11 & 8) != 0) {
            dVar4 = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), materialTheme.getColorScheme(composer, 6).y()), roundedCornerShape, 2);
        }
        if ((i11 & 16) != 0) {
            dVar5 = dVar;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-404995052, i10, -1, "androidx.tv.material3.AssistChipDefaults.border (Chip.kt:618)");
        }
        d dVar6 = dVar5;
        d dVar7 = dVar3;
        x0 x0Var = new x0(dVar, dVar2, dVar7, dVar4, dVar6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return x0Var;
    }

    /* JADX INFO: renamed from: colors-oq7We08, reason: not valid java name */
    public final y0 m5972colorsoq7We08(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, Composer composer, int i10, int i11) {
        long j18;
        long jM3507getTransparent0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j10;
        int i12 = i11 & 2;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jO = i12 != 0 ? materialTheme.getColorScheme(composer, 6).o() : j11;
        long jN = (i11 & 4) != 0 ? materialTheme.getColorScheme(composer, 6).n() : j12;
        long jE = (i11 & 8) != 0 ? materialTheme.getColorScheme(composer, 6).e() : j13;
        long jO2 = (i11 & 16) != 0 ? materialTheme.getColorScheme(composer, 6).o() : j14;
        long jW = (i11 & 32) != 0 ? materialTheme.getColorScheme(composer, 6).w() : j15;
        long jM3471copywmQWz5c$default = (i11 & 64) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).y(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long jM3471copywmQWz5c$default2 = (i11 & 128) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).b(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null) : j17;
        if (ComposerKt.isTraceInProgress()) {
            j18 = jM3471copywmQWz5c$default2;
            ComposerKt.traceEventStart(-1576836840, i10, -1, "androidx.tv.material3.AssistChipDefaults.colors (Chip.kt:551)");
        } else {
            j18 = jM3471copywmQWz5c$default2;
        }
        y0 y0Var = new y0(jM3507getTransparent0d7_KjU, jO, jN, jE, jO2, jW, jM3471copywmQWz5c$default, j18);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return y0Var;
    }
}
