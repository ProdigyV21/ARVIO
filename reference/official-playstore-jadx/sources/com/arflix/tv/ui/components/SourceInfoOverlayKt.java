package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.HighQualityKt;
import androidx.compose.material.icons.filled.StorageKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a7\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"", "isVisible", "Lcom/arflix/tv/data/model/StreamSource;", "stream", "Landroidx/compose/ui/Modifier;", "modifier", "Lx6/t0;", "SourceInfoOverlay", "(ZLcom/arflix/tv/data/model/StreamSource;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, "isLive", "PlayerInfoBar", "(Ljava/lang/String;Ljava/lang/String;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "quality", "Landroidx/compose/ui/graphics/Color;", "getQualityColor", "(Ljava/lang/String;)J", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SourceInfoOverlayKt {
    /* JADX WARN: Removed duplicated region for block: B:110:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x028b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PlayerInfoBar(java.lang.String r40, java.lang.String r41, boolean r42, androidx.compose.ui.Modifier r43, androidx.compose.runtime.Composer r44, int r45, int r46) {
        /*
            Method dump skipped, instruction units count: 925
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SourceInfoOverlayKt.PlayerInfoBar(java.lang.String, java.lang.String, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerInfoBar$lambda$1(String str, String str2, boolean z, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        PlayerInfoBar(str, str2, z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SourceInfoOverlay(boolean r14, com.arflix.tv.data.model.StreamSource r15, androidx.compose.ui.Modifier r16, androidx.compose.runtime.Composer r17, int r18, int r19) {
        /*
            r4 = r18
            r0 = 1829526905(0x6d0c5d79, float:2.7150564E27)
            r1 = r17
            androidx.compose.runtime.Composer r11 = r1.startRestartGroup(r0)
            r1 = r4 & 6
            if (r1 != 0) goto L1a
            boolean r1 = r11.changed(r14)
            if (r1 == 0) goto L17
            r1 = 4
            goto L18
        L17:
            r1 = 2
        L18:
            r1 = r1 | r4
            goto L1b
        L1a:
            r1 = r4
        L1b:
            r2 = r4 & 48
            if (r2 != 0) goto L2b
            boolean r2 = r11.changed(r15)
            if (r2 == 0) goto L28
            r2 = 32
            goto L2a
        L28:
            r2 = 16
        L2a:
            r1 = r1 | r2
        L2b:
            r2 = r19 & 4
            if (r2 == 0) goto L34
            r1 = r1 | 384(0x180, float:5.38E-43)
        L31:
            r3 = r16
            goto L46
        L34:
            r3 = r4 & 384(0x180, float:5.38E-43)
            if (r3 != 0) goto L31
            r3 = r16
            boolean r5 = r11.changed(r3)
            if (r5 == 0) goto L43
            r5 = 256(0x100, float:3.59E-43)
            goto L45
        L43:
            r5 = 128(0x80, float:1.8E-43)
        L45:
            r1 = r1 | r5
        L46:
            r5 = r1 & 147(0x93, float:2.06E-43)
            r6 = 146(0x92, float:2.05E-43)
            if (r5 != r6) goto L57
            boolean r5 = r11.getSkipping()
            if (r5 != 0) goto L53
            goto L57
        L53:
            r11.skipToGroupEnd()
            goto La8
        L57:
            if (r2 == 0) goto L5d
            androidx.compose.ui.Modifier$Companion r2 = androidx.compose.ui.Modifier.INSTANCE
            r6 = r2
            goto L5e
        L5d:
            r6 = r3
        L5e:
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto L6a
            r2 = -1
            java.lang.String r3 = "com.arflix.tv.ui.components.SourceInfoOverlay (SourceInfoOverlay.kt:51)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r1, r2, r3)
        L6a:
            r0 = 1
            if (r14 == 0) goto L71
            if (r15 == 0) goto L71
            r5 = r0
            goto L73
        L71:
            r2 = 0
            r5 = r2
        L73:
            r2 = 0
            r3 = 0
            r7 = 3
            androidx.compose.animation.EnterTransition r8 = androidx.compose.animation.EnterExitTransitionKt.fadeIn$default(r2, r3, r7, r2)
            androidx.compose.animation.ExitTransition r2 = androidx.compose.animation.EnterExitTransitionKt.fadeOut$default(r2, r3, r7, r2)
            com.arflix.tv.ui.components.x1 r3 = new com.arflix.tv.ui.components.x1
            r9 = 1
            r3.<init>(r15, r9)
            r9 = 54
            r10 = 66397089(0x3f523a1, float:1.4407986E-36)
            androidx.compose.runtime.internal.ComposableLambda r10 = androidx.compose.runtime.internal.ComposableLambdaKt.rememberComposableLambda(r10, r0, r3, r11, r9)
            int r0 = r1 >> 3
            r0 = r0 & 112(0x70, float:1.57E-43)
            r1 = 200064(0x30d80, float:2.8035E-40)
            r12 = r0 | r1
            r13 = 16
            r9 = 0
            r7 = r8
            r8 = r2
            androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto La7
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        La7:
            r3 = r6
        La8:
            androidx.compose.runtime.ScopeUpdateScope r7 = r11.endRestartGroup()
            if (r7 == 0) goto Lbb
            com.arflix.tv.ui.components.c2 r0 = new com.arflix.tv.ui.components.c2
            r6 = 1
            r1 = r14
            r2 = r15
            r5 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r7.updateScope(r0)
        Lbb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SourceInfoOverlayKt.SourceInfoOverlay(boolean, com.arflix.tv.data.model.StreamSource, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceInfoOverlay$lambda$0(StreamSource streamSource, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(66397089, i10, -1, "com.arflix.tv.ui.components.SourceInfoOverlay.<anonymous> (SourceInfoOverlay.kt:58)");
        }
        if (streamSource == null) {
            composer.startReplaceGroup(-1944139935);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1944139934);
            Modifier.Companion companion = Modifier.INSTANCE;
            Brush.Companion companion2 = Brush.INSTANCE;
            Color.Companion companion3 = Color.INSTANCE;
            Color[] colorArr = {Color.m3462boximpl(companion3.m3507getTransparent0d7_KjU()), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion3.m3498getBlack0d7_KjU(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null))};
            float f10 = 16;
            Modifier modifierM = com.arflix.tv.data.repository.g.m(f10, BackgroundKt.background$default(companion, Brush.Companion.m3427horizontalGradient8A3gB4$default(companion2, t7.a.E(colorArr), 0.0f, 0.0f, 0, 14, (Object) null), RoundedCornerShapeKt.m800RoundedCornerShapea9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(f10), 7, null), 0.0f, 4, null), composer, 733328855);
            Alignment.Companion companion4 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion4, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
            r7.p pVarU = a0.c.u(companion5, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Alignment.Horizontal end = companion4.getEnd();
            composer.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(arrangement, end, composer, 48, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer);
            r7.p pVarU2 = a0.c.u(companion5, composerM2991constructorimpl2, measurePolicyF, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Alignment.Vertical centerVertically = companion4.getCenterVertically();
            Arrangement.Horizontal end2 = arrangement.getEnd();
            composer.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(end2, centerVertically, composer, 54);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer);
            r7.p pVarU3 = a0.c.u(companion5, composerM2991constructorimpl3, measurePolicyRowMeasurePolicy, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Icons icons = Icons.INSTANCE;
            IconKt.m1610Iconww6aTOc(HighQualityKt.getHighQuality(icons.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(f10)), getQualityColor(streamSource.getQuality()), composer, 432, 0);
            float f11 = 6;
            SpacerKt.Spacer(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(f11)), composer, 6);
            String upperCase = streamSource.getQuality().toUpperCase(Locale.ROOT);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextKt.m6020Text4IGK_g(upperCase, null, getQualityColor(streamSource.getQuality()), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getLabel(), composer, 0, 0, 65530);
            androidx.compose.foundation.c.y(composer);
            float f12 = 4;
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f12)), composer, 6);
            Alignment.Vertical centerVertically2 = companion4.getCenterVertically();
            Arrangement.Horizontal end3 = arrangement.getEnd();
            composer.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(end3, centerVertically2, composer, 54);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(companion);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor4);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer);
            r7.p pVarU4 = a0.c.u(companion5, composerM2991constructorimpl4, measurePolicyRowMeasurePolicy2, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composer, qVarModifierMaterializerOf4, composer, 0, 2058660585);
            IconKt.m1610Iconww6aTOc(StorageKt.getStorage(icons.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(14)), ColorKt.getTextSecondary(), composer, 3504, 0);
            SpacerKt.Spacer(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(f11)), composer, 6);
            TextKt.m6020Text4IGK_g(streamSource.getAddonName(), null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getCaption(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            Composer composer2 = composer;
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (streamSource.getSize().length() > 0) {
                composer2.startReplaceGroup(540599822);
                SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f12)), composer2, 6);
                TextKt.m6020Text4IGK_g(streamSource.getSize(), null, Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getCaption(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                composer2 = composer;
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(540910225);
                composer2.endReplaceGroup();
            }
            com.arflix.tv.data.repository.g.v(composer2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceInfoOverlay$lambda$1(boolean z, StreamSource streamSource, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SourceInfoOverlay(z, streamSource, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final long getQualityColor(String str) {
        return (kotlin.text.o.T(str, "4K", true) || kotlin.text.o.T(str, "2160p", true)) ? androidx.compose.ui.graphics.ColorKt.Color(4294956800L) : kotlin.text.o.T(str, "1080p", true) ? ColorKt.getPink() : kotlin.text.o.T(str, "720p", true) ? androidx.compose.ui.graphics.ColorKt.Color(4282090230L) : ColorKt.getTextSecondary();
    }
}
