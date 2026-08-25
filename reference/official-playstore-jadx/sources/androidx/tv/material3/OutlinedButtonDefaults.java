package androidx.tv.material3;

import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001Jb\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJA\u0010\u0010\u001a\u00020\u00152\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/tv/material3/OutlinedButtonDefaults;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "focusedContainerColor", "focusedContentColor", "pressedContainerColor", "pressedContentColor", "disabledContainerColor", "disabledContentColor", "Landroidx/tv/material3/f;", "colors-oq7We08", "(JJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/f;", "colors", "Landroidx/tv/material3/d;", "border", "focusedBorder", "pressedBorder", "disabledBorder", "focusedDisabledBorder", "Landroidx/tv/material3/e;", "(Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/e;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OutlinedButtonDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final OutlinedButtonDefaults f5694a = new OutlinedButtonDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final RoundedCornerShape f5695b = RoundedCornerShapeKt.getCircleShape();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final PaddingValues f5696c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final PaddingValues f5697d;

    static {
        float fM5678constructorimpl = Dp.m5678constructorimpl(16);
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(10);
        float fM5678constructorimpl3 = Dp.m5678constructorimpl(12);
        f5696c = PaddingKt.m525PaddingValuesa9UjIt4(fM5678constructorimpl, fM5678constructorimpl2, fM5678constructorimpl, fM5678constructorimpl2);
        Dp.m5678constructorimpl(20);
        Dp.m5678constructorimpl(8);
        f5697d = PaddingKt.m525PaddingValuesa9UjIt4(fM5678constructorimpl3, fM5678constructorimpl2, fM5678constructorimpl, fM5678constructorimpl2);
    }

    public final e border(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, Composer composer, int i10, int i11) {
        int i12 = i11 & 1;
        RoundedCornerShape roundedCornerShape = f5695b;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        d dVar6 = i12 != 0 ? new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl((float) 1.5d), Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).o(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null)), roundedCornerShape, 2) : dVar;
        d dVar7 = (i11 & 2) != 0 ? new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl((float) 1.65d), materialTheme.getColorScheme(composer, 6).o()), roundedCornerShape, 2) : dVar2;
        d dVar8 = (i11 & 4) != 0 ? new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl((float) 1.5d), materialTheme.getColorScheme(composer, 6).o()), roundedCornerShape, 2) : dVar3;
        d dVar9 = (i11 & 8) != 0 ? new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl((float) 1.5d), Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).o(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null)), roundedCornerShape, 2) : dVar4;
        d dVar10 = (i11 & 16) != 0 ? dVar9 : dVar5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1370841249, i10, -1, "androidx.tv.material3.OutlinedButtonDefaults.border (ButtonDefaults.kt:367)");
        }
        e eVar = new e(dVar6, dVar7, dVar8, dVar9, dVar10);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return eVar;
    }

    /* JADX INFO: renamed from: colors-oq7We08, reason: not valid java name */
    public final f m6001colorsoq7We08(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, Composer composer, int i10, int i11) {
        long j18;
        long jM3507getTransparent0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j10;
        int i12 = i11 & 2;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jM3471copywmQWz5c$default = i12 != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).n(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null) : j11;
        long jN = (i11 & 4) != 0 ? materialTheme.getColorScheme(composer, 6).n() : j12;
        long jE = (i11 & 8) != 0 ? materialTheme.getColorScheme(composer, 6).e() : j13;
        long j19 = (i11 & 16) != 0 ? jN : j14;
        long j20 = (i11 & 32) != 0 ? jE : j15;
        long j21 = (i11 & 64) != 0 ? jM3507getTransparent0d7_KjU : j16;
        long jM3471copywmQWz5c$default2 = (i11 & 128) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).n(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j17;
        if (ComposerKt.isTraceInProgress()) {
            j18 = jM3471copywmQWz5c$default2;
            ComposerKt.traceEventStart(1106679267, i10, -1, "androidx.tv.material3.OutlinedButtonDefaults.colors (ButtonDefaults.kt:277)");
        } else {
            j18 = jM3471copywmQWz5c$default2;
        }
        f fVar = new f(jM3507getTransparent0d7_KjU, jM3471copywmQWz5c$default, jN, jE, j19, j20, j21, j18);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return fVar;
    }
}
