package com.arflix.tv.ui.theme;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.tv.material3.MaterialThemeKt;
import androidx.tv.material3.h1;
import com.arflix.tv.ui.screens.player.d0;
import com.arflix.tv.ui.skin.ArvioSkinKt;
import kotlin.Metadata;
import r7.p;
import x2.a;
import x2.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0001\u0010\u0002\u001a8\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a8\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tH\u0007¢\u0006\u0004\b\r\u0010\f\"\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013\"\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013*\n\u0010\u0018\"\u00020\u000f2\u00020\u000f¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/graphics/Color;", "appBackgroundDark", "(Landroidx/compose/runtime/Composer;I)J", "", "oledBlackBackground", "", "accentColorName", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "ArvioTvTheme", "(ZLjava/lang/String;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "ArflixTvTheme", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lcom/arflix/tv/ui/theme/ArvioColors;", "LocalArvioColors", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalArvioColors", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalOledBlackBackground", "getLocalOledBlackBackground", "LocalArflixColors", "getLocalArflixColors", "ArflixColors", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ThemeKt {
    private static final ProvidableCompositionLocal<ArvioColors> LocalArflixColors;
    private static final ProvidableCompositionLocal<ArvioColors> LocalArvioColors;
    private static final ProvidableCompositionLocal<Boolean> LocalOledBlackBackground;

    static {
        ProvidableCompositionLocal<ArvioColors> providableCompositionLocalStaticCompositionLocalOf = CompositionLocalKt.staticCompositionLocalOf(new d0(25));
        LocalArvioColors = providableCompositionLocalStaticCompositionLocalOf;
        LocalOledBlackBackground = CompositionLocalKt.staticCompositionLocalOf(new d0(26));
        LocalArflixColors = providableCompositionLocalStaticCompositionLocalOf;
    }

    public static final void ArflixTvTheme(boolean z, String str, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11) {
        int i12;
        boolean z5;
        String str2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-409647716);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i14 = i11 & 2;
        if (i14 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            z5 = z;
            str2 = str;
        } else {
            boolean z10 = i13 != 0 ? false : z;
            String str3 = i14 != 0 ? null : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-409647716, i12, -1, "com.arflix.tv.ui.theme.ArflixTvTheme (Theme.kt:143)");
            }
            boolean z11 = z10;
            ArvioTvTheme(z11, str3, pVar, composerStartRestartGroup, i12 & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z5 = z11;
            str2 = str3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new a(z5, str2, pVar, i10, i11, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArflixTvTheme$lambda$0(boolean z, String str, p pVar, int i10, int i11, Composer composer, int i12) {
        ArflixTvTheme(z, str, pVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ArvioTvTheme(boolean r85, java.lang.String r86, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r87, androidx.compose.runtime.Composer r88, int r89, int r90) {
        /*
            Method dump skipped, instruction units count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.theme.ThemeKt.ArvioTvTheme(boolean, java.lang.String, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioTvTheme$lambda$1(h1 h1Var, p pVar, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1609021595, i10, -1, "com.arflix.tv.ui.theme.ArvioTvTheme.<anonymous> (Theme.kt:127)");
            }
            ArvioSkinKt.ProvideArvioSkin(null, ComposableLambdaKt.rememberComposableLambda(-1363238022, true, new b(h1Var, pVar, 0), composer, 54), composer, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioTvTheme$lambda$1$0(h1 h1Var, p pVar, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1363238022, i10, -1, "com.arflix.tv.ui.theme.ArvioTvTheme.<anonymous>.<anonymous> (Theme.kt:128)");
            }
            MaterialThemeKt.MaterialTheme(h1Var, null, null, pVar, composer, 0, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioTvTheme$lambda$2(boolean z, String str, p pVar, int i10, int i11, Composer composer, int i12) {
        ArvioTvTheme(z, str, pVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArvioColors LocalArvioColors$lambda$0() {
        return new ArvioColors(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -1, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LocalOledBlackBackground$lambda$0() {
        return false;
    }

    public static final long appBackgroundDark(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1938697163, i10, -1, "com.arflix.tv.ui.theme.appBackgroundDark (Theme.kt:76)");
        }
        long jM3498getBlack0d7_KjU = ((Boolean) composer.consume(LocalOledBlackBackground)).booleanValue() ? Color.INSTANCE.m3498getBlack0d7_KjU() : ColorKt.getBackgroundDark();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jM3498getBlack0d7_KjU;
    }

    public static final ProvidableCompositionLocal<ArvioColors> getLocalArflixColors() {
        return LocalArflixColors;
    }

    public static final ProvidableCompositionLocal<ArvioColors> getLocalArvioColors() {
        return LocalArvioColors;
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalOledBlackBackground() {
        return LocalOledBlackBackground;
    }
}
