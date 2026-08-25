package com.arflix.tv.ui.skin;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import com.arflix.tv.ui.screens.player.d0;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0017\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0015\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\b\u001a,\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\n\u001a\u00020\t2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\"\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u001f\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u00118\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/graphics/Color;", "fallback", "resolveAccentColor-ek8zF_U", "(JLandroidx/compose/runtime/Composer;I)J", "resolveAccentColor", "", ContentDisposition.Parameters.Name, "accentColorFromName", "(Ljava/lang/String;)J", "Lcom/arflix/tv/ui/skin/ArvioSkinTokens;", "tokens", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "ProvideArvioSkin", "(Lcom/arflix/tv/ui/skin/ArvioSkinTokens;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalArvioSkinTokens", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalArvioSkinTokens", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalAccentColorOverride", "getLocalAccentColorOverride", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ArvioSkinKt {
    private static final ProvidableCompositionLocal<ArvioSkinTokens> LocalArvioSkinTokens = CompositionLocalKt.staticCompositionLocalOf(new d0(18));
    private static final ProvidableCompositionLocal<Color> LocalAccentColorOverride = CompositionLocalKt.staticCompositionLocalOf(new r7.a<Color>() { // from class: com.arflix.tv.ui.skin.ArvioSkinKt$LocalAccentColorOverride$1
        @Override // r7.a
        /* JADX INFO: renamed from: invoke-QN2ZGVo, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Color invoke() {
            return null;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArvioSkinTokens LocalArvioSkinTokens$lambda$0() {
        return ArvioSkinTokens.INSTANCE.defaults();
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ProvideArvioSkin(com.arflix.tv.ui.skin.ArvioSkinTokens r7, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r8, androidx.compose.runtime.Composer r9, int r10, int r11) {
        /*
            r0 = 1898395046(0x712735a6, float:8.2798166E29)
            androidx.compose.runtime.Composer r9 = r9.startRestartGroup(r0)
            r1 = r10 & 6
            if (r1 != 0) goto L1a
            r1 = r11 & 1
            if (r1 != 0) goto L17
            boolean r1 = r9.changed(r7)
            if (r1 == 0) goto L17
            r1 = 4
            goto L18
        L17:
            r1 = 2
        L18:
            r1 = r1 | r10
            goto L1b
        L1a:
            r1 = r10
        L1b:
            r2 = r10 & 48
            if (r2 != 0) goto L2b
            boolean r2 = r9.changedInstance(r8)
            if (r2 == 0) goto L28
            r2 = 32
            goto L2a
        L28:
            r2 = 16
        L2a:
            r1 = r1 | r2
        L2b:
            r2 = r1 & 19
            r3 = 18
            if (r2 != r3) goto L3d
            boolean r2 = r9.getSkipping()
            if (r2 != 0) goto L38
            goto L3d
        L38:
            r9.skipToGroupEnd()
        L3b:
            r2 = r7
            goto L87
        L3d:
            r9.startDefaults()
            r2 = r10 & 1
            if (r2 == 0) goto L55
            boolean r2 = r9.getDefaultsInvalid()
            if (r2 == 0) goto L4b
            goto L55
        L4b:
            r9.skipToGroupEnd()
            r2 = r11 & 1
            if (r2 == 0) goto L60
        L52:
            r1 = r1 & (-15)
            goto L60
        L55:
            r2 = r11 & 1
            if (r2 == 0) goto L60
            com.arflix.tv.ui.skin.ArvioSkinTokens$Companion r7 = com.arflix.tv.ui.skin.ArvioSkinTokens.INSTANCE
            com.arflix.tv.ui.skin.ArvioSkinTokens r7 = r7.defaults()
            goto L52
        L60:
            r9.endDefaults()
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto L6f
            r2 = -1
            java.lang.String r3 = "com.arflix.tv.ui.skin.ProvideArvioSkin (ArvioSkin.kt:49)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r1, r2, r3)
        L6f:
            androidx.compose.runtime.ProvidableCompositionLocal<com.arflix.tv.ui.skin.ArvioSkinTokens> r0 = com.arflix.tv.ui.skin.ArvioSkinKt.LocalArvioSkinTokens
            androidx.compose.runtime.ProvidedValue r0 = r0.provides(r7)
            int r2 = androidx.compose.runtime.ProvidedValue.$stable
            r1 = r1 & 112(0x70, float:1.57E-43)
            r1 = r1 | r2
            androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider(r0, r8, r9, r1)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto L3b
            androidx.compose.runtime.ComposerKt.traceEventEnd()
            goto L3b
        L87:
            androidx.compose.runtime.ScopeUpdateScope r7 = r9.endRestartGroup()
            if (r7 == 0) goto L9a
            com.arflix.tv.ui.components.p1 r1 = new com.arflix.tv.ui.components.p1
            r6 = 9
            r3 = r8
            r4 = r10
            r5 = r11
            r1.<init>(r2, r3, r4, r5, r6)
            r7.updateScope(r1)
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.skin.ArvioSkinKt.ProvideArvioSkin(com.arflix.tv.ui.skin.ArvioSkinTokens, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProvideArvioSkin$lambda$0(ArvioSkinTokens arvioSkinTokens, p pVar, int i10, int i11, Composer composer, int i12) {
        ProvideArvioSkin(arvioSkinTokens, pVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final long accentColorFromName(String str) {
        switch (str.hashCode()) {
            case -2100368654:
                if (str.equals("Indigo")) {
                    return ColorKt.Color(4284892364L);
                }
                break;
            case -1924984242:
                if (str.equals("Orange")) {
                    return ColorKt.Color(4294936576L);
                }
                break;
            case -1732476769:
                if (str.equals("Violet")) {
                    return ColorKt.Color(4290462924L);
                }
                break;
            case -1650372460:
                if (str.equals("Yellow")) {
                    return ColorKt.Color(4294958404L);
                }
                break;
            case 82033:
                if (str.equals("Red")) {
                    return ColorKt.Color(4294919236L);
                }
                break;
            case 2073722:
                if (str.equals("Blue")) {
                    return ColorKt.Color(4282681599L);
                }
                break;
            case 69066467:
                if (str.equals("Green")) {
                    return ColorKt.Color(4282698820L);
                }
                break;
        }
        return ColorKt.Color(4294967295L);
    }

    public static final ProvidableCompositionLocal<Color> getLocalAccentColorOverride() {
        return LocalAccentColorOverride;
    }

    public static final ProvidableCompositionLocal<ArvioSkinTokens> getLocalArvioSkinTokens() {
        return LocalArvioSkinTokens;
    }

    /* JADX INFO: renamed from: resolveAccentColor-ek8zF_U, reason: not valid java name */
    public static final long m6519resolveAccentColorek8zF_U(long j10, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(205471287, i10, -1, "com.arflix.tv.ui.skin.resolveAccentColor (ArvioSkin.kt:26)");
        }
        Color color = (Color) composer.consume(LocalAccentColorOverride);
        if (color != null) {
            j10 = color.m3482unboximpl();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return j10;
    }
}
