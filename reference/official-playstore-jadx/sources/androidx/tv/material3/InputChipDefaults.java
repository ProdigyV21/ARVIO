package androidx.tv.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001J\u009e\u0001\u0010\u0014\u001a\u00020\u00112\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J{\u0010\u0018\u001a\u00020\"2\u0006\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u00172\b\b\u0002\u0010\u001d\u001a\u00020\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u00172\b\b\u0002\u0010\u001f\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020\u00172\b\b\u0002\u0010!\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u0018\u0010#\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/tv/material3/InputChipDefaults;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "focusedContainerColor", "focusedContentColor", "pressedContainerColor", "pressedContentColor", "selectedContainerColor", "selectedContentColor", "disabledContainerColor", "disabledContentColor", "focusedSelectedContainerColor", "focusedSelectedContentColor", "pressedSelectedContainerColor", "pressedSelectedContentColor", "Landroidx/tv/material3/t2;", "colors-u3YEpmA", "(JJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/tv/material3/t2;", "colors", "", "hasAvatar", "Landroidx/tv/material3/d;", "border", "focusedBorder", "pressedBorder", "selectedBorder", "disabledBorder", "focusedSelectedBorder", "focusedDisabledBorder", "pressedSelectedBorder", "selectedDisabledBorder", "focusedSelectedDisabledBorder", "Landroidx/tv/material3/s2;", "(ZLandroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/compose/runtime/Composer;III)Landroidx/tv/material3/s2;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InputChipDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final InputChipDefaults f5636a = new InputChipDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5637b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final RoundedCornerShape f5638c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final RoundedCornerShape f5639d;

    static {
        float f10 = 36;
        f5637b = Dp.m5678constructorimpl(f10);
        Dp.m5678constructorimpl(18);
        Dp.m5678constructorimpl(28);
        f5638c = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(8));
        f5639d = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10));
    }

    public final s2 border(boolean z, d dVar, d dVar2, d dVar3, d dVar4, d dVar5, d dVar6, d dVar7, d dVar8, d dVar9, d dVar10, Composer composer, int i10, int i11, int i12) {
        d dVar11;
        d dVar12;
        d dVar13;
        d dVar14;
        int i13 = i12 & 2;
        RoundedCornerShape roundedCornerShape = f5638c;
        RoundedCornerShape roundedCornerShape2 = f5639d;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        if (i13 != 0) {
            dVar11 = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), materialTheme.getColorScheme(composer, 6).b()), z ? roundedCornerShape2 : roundedCornerShape, 2);
        } else {
            dVar11 = dVar;
        }
        d dVar15 = (i12 & 4) != 0 ? d.f5889d : dVar2;
        d dVar16 = (i12 & 8) != 0 ? dVar15 : dVar3;
        if ((i12 & 16) != 0) {
            dVar12 = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), materialTheme.getColorScheme(composer, 6).u()), z ? roundedCornerShape2 : roundedCornerShape, 2);
        } else {
            dVar12 = dVar4;
        }
        if ((i12 & 32) != 0) {
            dVar13 = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), materialTheme.getColorScheme(composer, 6).y()), z ? roundedCornerShape2 : roundedCornerShape, 2);
        } else {
            dVar13 = dVar5;
        }
        if ((i12 & 64) != 0) {
            BorderStroke borderStrokeM207BorderStrokecXLIe8U = BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl((float) 1.1d), materialTheme.getColorScheme(composer, 6).k());
            if (z) {
                roundedCornerShape = roundedCornerShape2;
            }
            dVar14 = new d(borderStrokeM207BorderStrokecXLIe8U, roundedCornerShape, 2);
        } else {
            dVar14 = dVar6;
        }
        d dVar17 = (i12 & 128) != 0 ? dVar11 : dVar7;
        d dVar18 = (i12 & 256) != 0 ? d.f5889d : dVar8;
        d dVar19 = (i12 & 512) != 0 ? d.f5889d : dVar9;
        d dVar20 = (i12 & 1024) != 0 ? dVar11 : dVar10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1228382637, i10, i11, "androidx.tv.material3.InputChipDefaults.border (Chip.kt:1132)");
        }
        s2 s2Var = new s2(dVar11, dVar15, dVar16, dVar12, dVar13, dVar14, dVar17, dVar18, dVar19, dVar20);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return s2Var;
    }

    /* JADX INFO: renamed from: colors-u3YEpmA, reason: not valid java name */
    public final t2 m5993colorsu3YEpmA(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, Composer composer, int i10, int i11, int i12) {
        long j24;
        long jM3507getTransparent0d7_KjU = (i12 & 1) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j10;
        int i13 = i12 & 2;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jO = i13 != 0 ? materialTheme.getColorScheme(composer, 6).o() : j11;
        long jN = (i12 & 4) != 0 ? materialTheme.getColorScheme(composer, 6).n() : j12;
        long jE = (i12 & 8) != 0 ? materialTheme.getColorScheme(composer, 6).e() : j13;
        long jO2 = (i12 & 16) != 0 ? materialTheme.getColorScheme(composer, 6).o() : j14;
        long jW = (i12 & 32) != 0 ? materialTheme.getColorScheme(composer, 6).w() : j15;
        long jM3471copywmQWz5c$default = (i12 & 64) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).v(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long jM = (i12 & 128) != 0 ? materialTheme.getColorScheme(composer, 6).m() : j17;
        long jM3471copywmQWz5c$default2 = (i12 & 256) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).y(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null) : j18;
        long jM3471copywmQWz5c$default3 = (i12 & 512) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).b(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null) : j19;
        long jK = (i12 & 1024) != 0 ? materialTheme.getColorScheme(composer, 6).k() : j20;
        long j25 = (i12 & 2048) != 0 ? materialTheme.getColorScheme(composer, 6).j() : j21;
        long jU = (i12 & 4096) != 0 ? materialTheme.getColorScheme(composer, 6).u() : j22;
        long jL = (i12 & 8192) != 0 ? materialTheme.getColorScheme(composer, 6).l() : j23;
        if (ComposerKt.isTraceInProgress()) {
            j24 = jL;
            ComposerKt.traceEventStart(570366073, i10, i11, "androidx.tv.material3.InputChipDefaults.colors (Chip.kt:1016)");
        } else {
            j24 = jL;
        }
        t2 t2Var = new t2(jM3507getTransparent0d7_KjU, jO, jN, jE, jO2, jW, jM3471copywmQWz5c$default, jM, jM3471copywmQWz5c$default2, jM3471copywmQWz5c$default3, jK, j25, jU, j24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t2Var;
    }
}
