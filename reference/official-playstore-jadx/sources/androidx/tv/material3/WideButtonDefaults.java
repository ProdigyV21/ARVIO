package androidx.tv.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ:\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010JA\u0010\u0013\u001a\u00020\u00182\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0013\u0010\u0019\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a"}, d2 = {"Landroidx/tv/material3/WideButtonDefaults;", "", "", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lx6/t0;", "Background", "(ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "focusedColor", "pressedColor", "disabledColor", "Landroidx/tv/material3/r4;", "contentColor-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/r4;", "contentColor", "Landroidx/tv/material3/d;", "border", "focusedBorder", "pressedBorder", "disabledBorder", "focusedDisabledBorder", "Landroidx/tv/material3/e;", "(Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/e;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WideButtonDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final WideButtonDefaults f5780a = new WideButtonDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final PaddingValues f5781b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final RoundedCornerShape f5782c;

    static {
        float fM5678constructorimpl = Dp.m5678constructorimpl(16);
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(10);
        f5781b = PaddingKt.m525PaddingValuesa9UjIt4(fM5678constructorimpl, fM5678constructorimpl2, fM5678constructorimpl, fM5678constructorimpl2);
        f5782c = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(12));
    }

    public static g a() {
        s1 s1Var = s1.f6376c;
        return new g(s1Var, s1Var, s1Var);
    }

    public static j b() {
        return new j(1.1f);
    }

    public static k c() {
        RoundedCornerShape roundedCornerShape = f5782c;
        return new k(roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape);
    }

    public final void Background(boolean z, MutableInteractionSource mutableInteractionSource, Composer composer, int i10) {
        int i11;
        long jM3471copywmQWz5c$default;
        Composer composerStartRestartGroup = composer.startRestartGroup(814364484);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(814364484, i11, -1, "androidx.tv.material3.WideButtonDefaults.Background (WideButtonDefaults.kt:65)");
            }
            int i12 = (i11 >> 3) & 14;
            boolean zBooleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource, composerStartRestartGroup, i12).getValue().booleanValue();
            boolean zBooleanValue2 = PressInteractionKt.collectIsPressedAsState(mutableInteractionSource, composerStartRestartGroup, i12).getValue().booleanValue();
            MaterialTheme materialTheme = MaterialTheme.f5670a;
            if (!z) {
                composerStartRestartGroup.startReplaceableGroup(603058958);
                jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composerStartRestartGroup, 6).y(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null);
                composerStartRestartGroup.endReplaceableGroup();
            } else if (zBooleanValue2) {
                composerStartRestartGroup.startReplaceableGroup(603061317);
                jM3471copywmQWz5c$default = materialTheme.getColorScheme(composerStartRestartGroup, 6).n();
                composerStartRestartGroup.endReplaceableGroup();
            } else if (zBooleanValue) {
                composerStartRestartGroup.startReplaceableGroup(603063397);
                jM3471copywmQWz5c$default = materialTheme.getColorScheme(composerStartRestartGroup, 6).n();
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                composerStartRestartGroup.startReplaceableGroup(603065806);
                jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composerStartRestartGroup, 6).y(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null);
                composerStartRestartGroup.endReplaceableGroup();
            }
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), jM3471copywmQWz5c$default, null, 2, null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new s4(this, z, mutableInteractionSource, i10));
        }
    }

    public final e border(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, Composer composer, int i10, int i11) {
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
            dVar5 = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(2), MaterialTheme.f5670a.getColorScheme(composer, 6).b()), Dp.m5678constructorimpl(0), f5782c);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1191534395, i10, -1, "androidx.tv.material3.WideButtonDefaults.border (WideButtonDefaults.kt:180)");
        }
        d dVar6 = dVar5;
        d dVar7 = dVar3;
        e eVar = new e(dVar, dVar2, dVar7, dVar4, dVar6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return eVar;
    }

    /* JADX INFO: renamed from: contentColor-ro_MJ88, reason: not valid java name */
    public final r4 m6022contentColorro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        int i12 = i11 & 1;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        if (i12 != 0) {
            j10 = materialTheme.getColorScheme(composer, 6).n();
        }
        if ((i11 & 2) != 0) {
            j11 = materialTheme.getColorScheme(composer, 6).e();
        }
        if ((i11 & 4) != 0) {
            j12 = j11;
        }
        if ((i11 & 8) != 0) {
            j13 = j10;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-883083506, i10, -1, "androidx.tv.material3.WideButtonDefaults.contentColor (WideButtonDefaults.kt:121)");
        }
        long j14 = j11;
        long j15 = j10;
        r4 r4Var = new r4(j15, j14, j12, j13);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return r4Var;
    }
}
