package com.arflix.tv.ui.components;

import android.content.Context;
import android.view.Precision;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.AspectRatioKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.PlayArrowKt;
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
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import coil.compose.SingletonAsyncImageKt;
import coil.request.ImageRequest;
import com.arflix.tv.R;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.skin.ArvioFocusKt;
import com.arflix.tv.ui.skin.ArvioSkin;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a]\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001aG\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\n\u001a\u00020\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/model/MediaItem;", "item", "", "progress", "", "episodeInfo", "timeRemaining", "Landroidx/compose/ui/unit/Dp;", "width", "", "isFocused", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "ContinueWatchingCard-iHT-50w", "(Lcom/arflix/tv/data/model/MediaItem;FLjava/lang/String;Ljava/lang/String;FZLr7/a;Landroidx/compose/runtime/Composer;II)V", "ContinueWatchingCard", "ContinueWatchingCardCompact", "(Lcom/arflix/tv/data/model/MediaItem;FLjava/lang/String;ZLr7/a;Landroidx/compose/runtime/Composer;II)V", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ContinueWatchingCardKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:141:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0101  */
    /* JADX INFO: renamed from: ContinueWatchingCard-iHT-50w, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6161ContinueWatchingCardiHT50w(final com.arflix.tv.data.model.MediaItem r77, float r78, java.lang.String r79, java.lang.String r80, float r81, boolean r82, r7.a<x6.t0> r83, androidx.compose.runtime.Composer r84, final int r85, final int r86) {
        /*
            Method dump skipped, instruction units count: 1031
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ContinueWatchingCardKt.m6161ContinueWatchingCardiHT50w(com.arflix.tv.data.model.MediaItem, float, java.lang.String, java.lang.String, float, boolean, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContinueWatchingCardCompact(final com.arflix.tv.data.model.MediaItem r39, float r40, java.lang.String r41, boolean r42, r7.a<x6.t0> r43, androidx.compose.runtime.Composer r44, final int r45, final int r46) {
        /*
            Method dump skipped, instruction units count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ContinueWatchingCardKt.ContinueWatchingCardCompact(com.arflix.tv.data.model.MediaItem, float, java.lang.String, boolean, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContinueWatchingCardCompact$lambda$1(boolean z, MediaItem mediaItem, float f10, String str, BoxScope boxScope, boolean z5, Composer composer, int i10) {
        int i11;
        ComposeUiNode.Companion companion;
        ArvioSkin arvioSkin;
        Alignment.Companion companion2;
        Arrangement arrangement;
        Integer num;
        RowScopeInstance rowScopeInstance;
        float f11;
        Modifier.Companion companion3;
        ArvioSkin arvioSkin2;
        if ((i10 & 48) == 0) {
            i11 = i10 | (composer.changed(z5) ? 32 : 16);
        } else {
            i11 = i10;
        }
        if ((i11 & 145) == 144 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(272322960, i11, -1, "com.arflix.tv.ui.components.ContinueWatchingCardCompact.<anonymous> (ContinueWatchingCard.kt:241)");
            }
            boolean z10 = z || z5;
            Modifier.Companion companion4 = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion4, 0.0f, 1, null);
            ArvioSkin arvioSkin3 = ArvioSkin.INSTANCE;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(modifierFillMaxWidth$default, arvioSkin3.getSpacing(composer, 6).m6534getX2D9Ej5fM());
            Alignment.Companion companion5 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion5.getCenterVertically();
            composer.startReplaceableGroup(693286680);
            Arrangement arrangement2 = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement2, centerVertically, composer, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion6 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion6.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion6, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(AspectRatioKt.aspectRatio$default(SizeKt.m583width3ABfNKs(companion4, Dp.m5678constructorimpl(100)), 1.7777778f, false, 2, null), arvioSkin3.getColors(composer, 6).m6494getSurfaceRaised0d7_KjU(), ArvioFocusKt.m6502rememberArvioCardShapekHDZbjc(arvioSkin3.getRadius(composer, 6).m6518getSmD9Ej5fM(), composer, 0, 0));
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion5, false, composer, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion6.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU);
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
            r7.p pVarU2 = a0.c.u(companion6, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            String backdrop = mediaItem.getBackdrop();
            if (backdrop == null) {
                backdrop = mediaItem.getImage();
            }
            if (backdrop == null || kotlin.text.o.h0(backdrop)) {
                backdrop = null;
            }
            if (backdrop != null) {
                composer.startReplaceGroup(-1788659913);
                arvioSkin = arvioSkin3;
                arrangement = arrangement2;
                num = 0;
                rowScopeInstance = rowScopeInstance2;
                f11 = 0.0f;
                companion = companion6;
                companion3 = companion4;
                companion2 = companion5;
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(new ImageRequest.Builder((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).data(backdrop).size(200, 112).precision(Precision.INEXACT).allowHardware(true).build(), mediaItem.getTitle(), SizeKt.fillMaxSize$default(companion4, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer, 1573248, 952);
                composer.endReplaceGroup();
            } else {
                companion = companion6;
                arvioSkin = arvioSkin3;
                companion2 = companion5;
                arrangement = arrangement2;
                num = 0;
                rowScopeInstance = rowScopeInstance2;
                f11 = 0.0f;
                companion3 = companion4;
                composer.startReplaceGroup(-1788145220);
                composer.endReplaceGroup();
            }
            ArvioSkin arvioSkin4 = arvioSkin;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(boxScopeInstance.align(companion3, companion2.getBottomCenter()), f11, 1, null), Dp.m5678constructorimpl(3)), Color.m3471copywmQWz5c$default(arvioSkin4.getColors(composer, 6).m6491getFocusOutline0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
            composer.startReplaceableGroup(733328855);
            Alignment.Companion companion7 = companion2;
            MeasurePolicy measurePolicyD2 = a0.c.d(companion7, false, composer, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
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
            ComposeUiNode.Companion companion8 = companion;
            r7.p pVarU3 = a0.c.u(companion8, composerM2991constructorimpl3, measurePolicyD2, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            Integer num2 = num;
            a0.c.y(composer, qVarModifierMaterializerOf3, composer, num2, 2058660585);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(SizeKt.fillMaxWidth(companion3, qb.d.m(f10, f11, 1.0f)), f11, 1, null), arvioSkin4.getColors(composer, 6).m6487getAccent0d7_KjU(), null, 2, null), composer, 0);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            SpacerKt.Spacer(SizeKt.m583width3ABfNKs(companion3, arvioSkin4.getSpacing(composer, 6).m6535getX3D9Ej5fM()), composer, 0);
            Modifier.Companion companion9 = companion3;
            Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance, companion9, 1.0f, false, 2, null);
            composer.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion7, arrangement.getTop(), composer, 0, -1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion8.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierA);
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
            r7.p pVarU4 = a0.c.u(companion8, composerM2991constructorimpl4, measurePolicyK, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composer, qVarModifierMaterializerOf4, composer, num2, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            String title = mediaItem.getTitle();
            TextStyle cardTitle = arvioSkin4.getTypography(composer, 6).getCardTitle();
            Color.Companion companion10 = Color.INSTANCE;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(cardTitle, 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, new Shadow(companion10.m3498getBlack0d7_KjU(), OffsetKt.Offset(2.0f, 2.0f), 6.0f, null), null, 0, 0, 0L, null, null, null, 0, 0, null, 16769023, null);
            long jM6497getTextPrimary0d7_KjU = arvioSkin4.getColors(composer, 6).m6497getTextPrimary0d7_KjU();
            TextOverflow.Companion companion11 = TextOverflow.INSTANCE;
            TextKt.m6020Text4IGK_g(title, null, jM6497getTextPrimary0d7_KjU, 0L, null, null, null, 0L, null, null, 0L, companion11.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composer, 0, 3120, 55290);
            Composer composer2 = composer;
            if (str != null) {
                composer2.startReplaceGroup(1620354475);
                arvioSkin2 = arvioSkin4;
                TextKt.m6020Text4IGK_g(str, null, arvioSkin4.getColors(composer2, 6).m6496getTextMuted0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, companion11.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(arvioSkin4.getTypography(composer2, 6).getCaption(), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, new Shadow(companion10.m3498getBlack0d7_KjU(), OffsetKt.Offset(2.0f, 2.0f), 4.0f, null), null, 0, 0, 0L, null, null, null, 0, 0, null, 16769023, null), composer, 0, 3120, 55290);
                composer2 = composer;
                composer2.endReplaceGroup();
            } else {
                arvioSkin2 = arvioSkin4;
                composer2.startReplaceGroup(1620988332);
                composer2.endReplaceGroup();
            }
            androidx.compose.foundation.c.y(composer2);
            if (z10) {
                composer2.startReplaceGroup(-764240790);
                ArvioSkin arvioSkin5 = arvioSkin2;
                Modifier modifierM178backgroundbw27NRU2 = BackgroundKt.m178backgroundbw27NRU(SizeKt.m578size3ABfNKs(companion9, Dp.m5678constructorimpl(40)), arvioSkin5.getColors(composer2, 6).m6487getAccent0d7_KjU(), RoundedCornerShapeKt.getCircleShape());
                Alignment center = companion7.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer2, 6);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor5 = companion8.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU2);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor5);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU5 = a0.c.u(companion8, composerM2991constructorimpl5, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl5, currentCompositionLocalMap5);
                if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf5, composer2, num2, 2058660585);
                IconKt.m1610Iconww6aTOc(PlayArrowKt.getPlayArrow(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.play, composer2, 0), SizeKt.m578size3ABfNKs(companion9, Dp.m5678constructorimpl(24)), arvioSkin5.getColors(composer2, 6).m6497getTextPrimary0d7_KjU(), composer2, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                com.arflix.tv.data.repository.g.q(composer2);
            } else {
                composer2.startReplaceGroup(-763675722);
                composer2.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContinueWatchingCardCompact$lambda$2(MediaItem mediaItem, float f10, String str, boolean z, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        ContinueWatchingCardCompact(mediaItem, f10, str, z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v9 */
    /* JADX WARN: Type inference failed for: r1v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v20 */
    public static final x6.t0 ContinueWatchingCard_iHT_50w$lambda$1$0(boolean z, MediaItem mediaItem, String str, float f10, BoxScope boxScope, boolean z5, Composer composer, int i10) {
        int i11;
        Modifier.Companion companion;
        BoxScopeInstance boxScopeInstance;
        Alignment.Companion companion2;
        ComposeUiNode.Companion companion3;
        ?? r17;
        Integer num;
        float f11;
        Object obj;
        ComposeUiNode.Companion companion4;
        Composer composer2;
        Integer num2;
        BoxScopeInstance boxScopeInstance2;
        Alignment.Companion companion5;
        ArvioSkin arvioSkin;
        Integer num3;
        ComposeUiNode.Companion companion6;
        Modifier.Companion companion7;
        Composer composer3;
        ?? r4;
        int i12;
        if ((i10 & 48) == 0) {
            i11 = i10 | (composer.changed(z5) ? 32 : 16);
        } else {
            i11 = i10;
        }
        if ((i11 & 145) == 144 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1597131704, i11, -1, "com.arflix.tv.ui.components.ContinueWatchingCard.<anonymous>.<anonymous> (ContinueWatchingCard.kt:70)");
            }
            ?? r16 = z || z5;
            Modifier.Companion companion8 = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion8, 0.0f, 1, null);
            composer.startReplaceableGroup(733328855);
            Alignment.Companion companion9 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion9, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion10 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion10.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
            r7.p pVarU = a0.c.u(companion10, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            String backdrop = mediaItem.getBackdrop();
            if (backdrop == null) {
                backdrop = mediaItem.getImage();
            }
            if (backdrop == null || kotlin.text.o.h0(backdrop)) {
                backdrop = null;
            }
            if (backdrop != null) {
                composer.startReplaceGroup(1301705233);
                companion3 = companion10;
                boxScopeInstance = boxScopeInstance3;
                companion = companion8;
                companion2 = companion9;
                num = 0;
                f11 = 0.0f;
                r17 = 0;
                obj = null;
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(new ImageRequest.Builder((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).data(backdrop).size(640, 360).precision(Precision.INEXACT).allowHardware(true).build(), mediaItem.getTitle(), SizeKt.fillMaxSize$default(companion8, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer, 1573248, 952);
                composer.endReplaceGroup();
            } else {
                companion = companion8;
                boxScopeInstance = boxScopeInstance3;
                companion2 = companion9;
                companion3 = companion10;
                r17 = 0;
                num = 0;
                f11 = 0.0f;
                obj = null;
                composer.startReplaceGroup(1302218004);
                composer.endReplaceGroup();
            }
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(companion, f11, 1, obj);
            Brush.Companion companion11 = Brush.INSTANCE;
            Color colorM3462boximpl = Color.m3462boximpl(Color.INSTANCE.m3507getTransparent0d7_KjU());
            ArvioSkin arvioSkin2 = ArvioSkin.INSTANCE;
            Color colorM3462boximpl2 = Color.m3462boximpl(Color.m3471copywmQWz5c$default(arvioSkin2.getColors(composer, 6).m6488getBackground0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null));
            Color[] colorArr = new Color[2];
            colorArr[r17] = colorM3462boximpl;
            colorArr[1] = colorM3462boximpl2;
            Modifier modifierBackground$default = BackgroundKt.background$default(modifierFillMaxSize$default2, Brush.Companion.m3435verticalGradient8A3gB4$default(companion11, t7.a.E(colorArr), 120.0f, 0.0f, 0, 12, (Object) null), null, 0.0f, 6, null);
            ?? r1 = r17;
            BoxKt.Box(modifierBackground$default, composer, r1 == true ? 1 : 0);
            if (r16 == true) {
                composer.startReplaceGroup(1302814909);
                Modifier modifierFillMaxSize$default3 = SizeKt.fillMaxSize$default(companion, f11, 1, obj);
                Alignment center = companion2.getCenter();
                composer.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, r1, composer, 6);
                composer.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, r1 == true ? 1 : 0);
                CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default3);
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
                ComposeUiNode.Companion companion12 = companion3;
                r7.p pVarU2 = a0.c.u(companion12, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                Integer num4 = num;
                a0.c.y(composer, qVarModifierMaterializerOf2, composer, num4, 2058660585);
                Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(56)), arvioSkin2.getColors(composer, 6).m6487getAccent0d7_KjU(), RoundedCornerShapeKt.getCircleShape());
                Alignment center2 = companion2.getCenter();
                composer.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer, 6);
                composer.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion12.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU);
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
                r7.p pVarU3 = a0.c.u(companion12, composerM2991constructorimpl3, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
                }
                a0.c.y(composer, qVarModifierMaterializerOf3, composer, num4, 2058660585);
                num2 = num4;
                composer2 = composer;
                companion4 = companion12;
                IconKt.m1610Iconww6aTOc(PlayArrowKt.getPlayArrow(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.play, composer, 0), SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(32)), arvioSkin2.getColors(composer, 6).m6497getTextPrimary0d7_KjU(), composer2, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                com.arflix.tv.data.repository.g.v(composer2);
            } else {
                companion4 = companion3;
                composer2 = composer;
                num2 = num;
                composer2.startReplaceGroup(1303667316);
                composer2.endReplaceGroup();
            }
            BoxScopeInstance boxScopeInstance4 = boxScopeInstance;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(SizeKt.fillMaxWidth$default(boxScopeInstance4.align(companion, companion2.getBottomCenter()), f11, 1, obj), arvioSkin2.getSpacing(composer2, 6).m6534getX2D9Ej5fM(), arvioSkin2.getSpacing(composer2, 6).m6534getX2D9Ej5fM());
            composer2.startReplaceableGroup(733328855);
            Alignment.Companion companion13 = companion2;
            MeasurePolicy measurePolicyD2 = a0.c.d(companion13, false, composer2, 0, -1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor4);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer2);
            r7.p pVarU4 = a0.c.u(companion4, composerM2991constructorimpl4, measurePolicyD2, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf4, composer2, num2, 2058660585);
            float f12 = 3;
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, f11, 1, obj), Dp.m5678constructorimpl(f12)), Color.m3471copywmQWz5c$default(arvioSkin2.getColors(composer2, 6).m6491getFocusOutline0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer2, 0);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth(companion, qb.d.m(f10, f11, 1.0f)), Dp.m5678constructorimpl(f12)), arvioSkin2.getColors(composer2, 6).m6487getAccent0d7_KjU(), null, 2, null), composer2, 0);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (str != null) {
                composer2.startReplaceGroup(1304565200);
                Modifier modifierM530paddingVpY3zN42 = PaddingKt.m530paddingVpY3zN4(BackgroundKt.m178backgroundbw27NRU(PaddingKt.m529padding3ABfNKs(boxScopeInstance4.align(companion, companion13.getTopEnd()), arvioSkin2.getSpacing(composer2, 6).m6534getX2D9Ej5fM()), Color.m3471copywmQWz5c$default(arvioSkin2.getColors(composer2, 6).m6494getSurfaceRaised0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), ArvioFocusKt.m6502rememberArvioCardShapekHDZbjc(arvioSkin2.getRadius(composer2, 6).m6518getSmD9Ej5fM(), composer2, 0, 0)), arvioSkin2.getSpacing(composer2, 6).m6534getX2D9Ej5fM(), arvioSkin2.getSpacing(composer2, 6).m6531getX1D9Ej5fM());
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD3 = a0.c.d(companion13, false, composer2, 0, -1323940314);
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor5 = companion4.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN42);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor5);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU5 = a0.c.u(companion4, composerM2991constructorimpl5, measurePolicyD3, composerM2991constructorimpl5, currentCompositionLocalMap5);
                if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf5, composer2, num2, 2058660585);
                arvioSkin = arvioSkin2;
                companion6 = companion4;
                boxScopeInstance2 = boxScopeInstance4;
                num3 = num2;
                companion5 = companion13;
                companion7 = companion;
                TextKt.m6020Text4IGK_g(str, null, arvioSkin2.getColors(composer2, 6).m6497getTextPrimary0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arvioSkin2.getTypography(composer2, 6).getBadge(), composer, 0, 0, 65530);
                composer3 = composer;
                com.arflix.tv.data.repository.g.q(composer3);
            } else {
                boxScopeInstance2 = boxScopeInstance4;
                companion5 = companion13;
                arvioSkin = arvioSkin2;
                num3 = num2;
                companion6 = companion4;
                companion7 = companion;
                composer3 = composer2;
                composer3.startReplaceGroup(1305368596);
                composer3.endReplaceGroup();
            }
            if (mediaItem.getMediaType() == MediaType.TV) {
                composer3.startReplaceGroup(1843226265);
                i12 = R.string.component_badge_tv;
                r4 = 0;
            } else {
                r4 = 0;
                composer3.startReplaceGroup(1843227836);
                i12 = R.string.component_badge_movie;
            }
            String strStringResource = StringResources_androidKt.stringResource(i12, composer3, r4);
            composer3.endReplaceGroup();
            ArvioSkin arvioSkin3 = arvioSkin;
            Modifier modifierM530paddingVpY3zN43 = PaddingKt.m530paddingVpY3zN4(BackgroundKt.m178backgroundbw27NRU(PaddingKt.m529padding3ABfNKs(boxScopeInstance2.align(companion7, companion5.getTopStart()), arvioSkin3.getSpacing(composer3, 6).m6534getX2D9Ej5fM()), Color.m3471copywmQWz5c$default(arvioSkin3.getColors(composer3, 6).m6494getSurfaceRaised0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), ArvioFocusKt.m6502rememberArvioCardShapekHDZbjc(arvioSkin3.getRadius(composer3, 6).m6518getSmD9Ej5fM(), composer3, r4, r4)), arvioSkin3.getSpacing(composer3, 6).m6534getX2D9Ej5fM(), arvioSkin3.getSpacing(composer3, 6).m6531getX1D9Ej5fM());
            composer3.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD4 = a0.c.d(companion5, r4, composer3, r4, -1323940314);
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer3, r4);
            CompositionLocalMap currentCompositionLocalMap6 = composer3.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor6 = companion6.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN43);
            if (composer3.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
                composer3.createNode(constructor6);
            } else {
                composer3.useNode();
            }
            Composer composerM2991constructorimpl6 = Updater.m2991constructorimpl(composer3);
            r7.p pVarU6 = a0.c.u(companion6, composerM2991constructorimpl6, measurePolicyD4, composerM2991constructorimpl6, currentCompositionLocalMap6);
            if (composerM2991constructorimpl6.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                a0.c.w(currentCompositeKeyHash6, composerM2991constructorimpl6, currentCompositeKeyHash6, pVarU6);
            }
            a0.c.y(composer3, qVarModifierMaterializerOf6, composer3, num3, 2058660585);
            TextKt.m6020Text4IGK_g(strStringResource, null, arvioSkin3.getColors(composer3, 6).m6497getTextPrimary0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arvioSkin3.getTypography(composer3, 6).getBadge(), composer, 0, 0, 65530);
            if (androidx.compose.foundation.c.A(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContinueWatchingCard_iHT_50w$lambda$2(MediaItem mediaItem, float f10, String str, String str2, float f11, boolean z, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        m6161ContinueWatchingCardiHT50w(mediaItem, f10, str, str2, f11, z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }
}
