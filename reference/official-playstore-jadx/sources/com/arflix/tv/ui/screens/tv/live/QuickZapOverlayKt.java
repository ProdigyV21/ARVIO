package com.arflix.tv.ui.screens.tv.live;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.KeyboardArrowDownKt;
import androidx.compose.material.icons.filled.KeyboardArrowUpKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.WavUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.components.t3;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0017\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a§\u0001\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00022\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00112\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a7\u0010 \u001a\u00020\u00122\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0003¢\u0006\u0004\b \u0010!\u001a\u0017\u0010#\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\fH\u0003¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010%\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u0006H\u0003¢\u0006\u0004\b%\u0010&\u001aK\u0010'\u001a\u00020\u00122\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00022\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00062\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0003¢\u0006\u0004\b'\u0010(\u001a)\u0010,\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\b2\b\u0010+\u001a\u0004\u0018\u00010\rH\u0003¢\u0006\u0004\b,\u0010-\u001a1\u0010.\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\b2\b\u0010+\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001f\u001a\u00020\u0006H\u0003¢\u0006\u0004\b.\u0010/¨\u00064²\u0006\u000e\u00100\u001a\u00020\u001d8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00101\u001a\u00020\u001d8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00102\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00103\u001a\u00020\f8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "tree", "", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategory;", "getAvailableCategories", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;)Ljava/util/List;", "", "visible", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "currentChannel", "channels", "", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNextMap", "categoriesTree", "selectedCategoryId", "Lkotlin/Function1;", "Lx6/t0;", "onCategorySelected", "Lkotlin/Function0;", "onDismiss", "onChannelSelect", "onRightClick", "Landroidx/compose/ui/Modifier;", "modifier", "QuickZapOverlay", "(ZLcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/util/List;Ljava/util/Map;Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;Ljava/lang/String;Lr7/l;Lr7/a;Lr7/l;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", "categories", "", "selectedIndex", "isFocused", "CategorySidebarPanel", "(Ljava/util/List;IZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "label", "NonFocusedCategoryRow", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "FocusedCategoryRow", "(Ljava/lang/String;ZLandroidx/compose/runtime/Composer;I)V", "ChannelColumnPanel", "(Ljava/util/List;IZLjava/util/Map;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "number", "channel", "nowNext", "NonFocusedChannelSlot", "(ILcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Lcom/arflix/tv/data/model/IptvNowNext;Landroidx/compose/runtime/Composer;I)V", "FocusedChannelSlot", "(ILcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Lcom/arflix/tv/data/model/IptvNowNext;ZLandroidx/compose/runtime/Composer;I)V", "selectedCategoryIndex", "selectedChannelIndex", "categoryListFocused", "originalCategoryId", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class QuickZapOverlayKt {
    /* JADX WARN: Removed duplicated region for block: B:41:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void CategorySidebarPanel(java.util.List<com.arflix.tv.ui.screens.tv.live.LiveCategory> r16, int r17, boolean r18, androidx.compose.ui.Modifier r19, androidx.compose.runtime.Composer r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 469
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt.CategorySidebarPanel(java.util.List, int, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebarPanel$lambda$1(List list, int i10, boolean z, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        CategorySidebarPanel(list, i10, z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0271  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void ChannelColumnPanel(final java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r47, final int r48, final boolean r49, final java.util.Map<java.lang.String, com.arflix.tv.data.model.IptvNowNext> r50, androidx.compose.ui.Modifier r51, androidx.compose.runtime.Composer r52, final int r53, final int r54) {
        /*
            Method dump skipped, instruction units count: 638
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt.ChannelColumnPanel(java.util.List, int, boolean, java.util.Map, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelColumnPanel$lambda$1(List list, int i10, boolean z, Map map, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        ChannelColumnPanel(list, i10, z, map, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    private static final void FocusedCategoryRow(String str, boolean z, Composer composer, int i10) {
        int i11;
        char c10;
        Composer composerStartRestartGroup = composer.startRestartGroup(1287990130);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        int i12 = i11;
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1287990130, i12, -1, "com.arflix.tv.ui.screens.tv.live.FocusedCategoryRow (QuickZapOverlay.kt:372)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 8;
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(com.arflix.tv.data.repository.g.k(f10, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(48)), f10), z ? LiveColors.INSTANCE.m6411getPanelRaised0d7_KjU() : Color.INSTANCE.m3507getTransparent0d7_KjU(), null, 2, null), z ? Dp.m5678constructorimpl((float) 1.5d) : Dp.m5678constructorimpl(0), z ? LiveColors.INSTANCE.m6406getFocusRing0d7_KjU() : Color.INSTANCE.m3507getTransparent0d7_KjU()), Dp.m5678constructorimpl(16), 0.0f, 2, null);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion2.getCenterVertically();
            Arrangement arrangement = Arrangement.INSTANCE;
            float f11 = 10;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f11, arrangement, composerStartRestartGroup, 693286680), centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            if (z) {
                composerStartRestartGroup.startReplaceGroup(214893019);
                Alignment.Horizontal centerHorizontally = companion2.getCenterHorizontally();
                Arrangement.HorizontalOrVertical center = arrangement.getCenter();
                composerStartRestartGroup.startReplaceableGroup(-483455358);
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composerStartRestartGroup, 54);
                composerStartRestartGroup.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
                r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyColumnMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                Icons icons = Icons.INSTANCE;
                ImageVector keyboardArrowUp = KeyboardArrowUpKt.getKeyboardArrowUp(icons.getDefault());
                LiveColors liveColors = LiveColors.INSTANCE;
                IconKt.m1610Iconww6aTOc(keyboardArrowUp, (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(f11)), liveColors.m6397getAccent0d7_KjU(), composerStartRestartGroup, 432, 0);
                c10 = 2;
                SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(2)), composerStartRestartGroup, 6);
                IconKt.m1610Iconww6aTOc(KeyboardArrowDownKt.getKeyboardArrowDown(icons.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(f11)), liveColors.m6397getAccent0d7_KjU(), composerStartRestartGroup, 432, 0);
                com.arflix.tv.data.repository.g.q(composerStartRestartGroup);
            } else {
                c10 = 2;
                composerStartRestartGroup.startReplaceGroup(215595820);
                composerStartRestartGroup.endReplaceGroup();
            }
            TextStyle cellTitle = LiveType.INSTANCE.getCellTitle();
            LiveColors liveColors2 = LiveColors.INSTANCE;
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(cellTitle, z ? liveColors2.m6397getAccent0d7_KjU() : liveColors2.m6402getFg0d7_KjU(), TextUnitKt.getSp(14), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777208, null), composerStartRestartGroup, i12 & 14, 3120, 55294);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new i(str, i10, 2, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FocusedCategoryRow$lambda$1(String str, boolean z, int i10, Composer composer, int i11) {
        FocusedCategoryRow(str, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x05eb  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0625  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0362  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void FocusedChannelSlot(int r71, com.arflix.tv.ui.screens.tv.live.EnrichedChannel r72, com.arflix.tv.data.model.IptvNowNext r73, boolean r74, androidx.compose.runtime.Composer r75, int r76) {
        /*
            Method dump skipped, instruction units count: 1597
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt.FocusedChannelSlot(int, com.arflix.tv.ui.screens.tv.live.EnrichedChannel, com.arflix.tv.data.model.IptvNowNext, boolean, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float FocusedChannelSlot$lambda$0$2$1$0(Float f10) {
        if (f10 != null) {
            return f10.floatValue();
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FocusedChannelSlot$lambda$1(int i10, EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, boolean z, int i11, Composer composer, int i12) {
        FocusedChannelSlot(i10, enrichedChannel, iptvNowNext, z, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    private static final void NonFocusedCategoryRow(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1489260379);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1489260379, i11, -1, "com.arflix.tv.ui.screens.tv.live.NonFocusedCategoryRow (QuickZapOverlay.kt:354)");
            }
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, PaddingKt.m530paddingVpY3zN4(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(36)), Dp.m5678constructorimpl(16), Dp.m5678constructorimpl(8)), 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getCellTitle(), Color.m3471copywmQWz5c$default(LiveColors.INSTANCE.m6403getFgDim0d7_KjU(), 0.65f, 0.0f, 0.0f, 0.0f, 14, null), TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), composer2, (i11 & 14) | 48, 3120, 55292);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.collections.b(str, i10, 9));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NonFocusedCategoryRow$lambda$0(String str, int i10, Composer composer, int i11) {
        NonFocusedCategoryRow(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void NonFocusedChannelSlot(int i10, EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, Composer composer, int i11) {
        int i12;
        Composer composer2;
        String name;
        IptvProgram now;
        EnrichedChannel enrichedChannel2 = enrichedChannel;
        Composer composerStartRestartGroup = composer.startRestartGroup(924898375);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(enrichedChannel2) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(iptvNowNext) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(924898375, i12, -1, "com.arflix.tv.ui.screens.tv.live.NonFocusedChannelSlot (QuickZapOverlay.kt:488)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 42;
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(f10)), Dp.m5678constructorimpl(24), 0.0f, 2, null);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(16, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), companion2.getCenterVertically(), composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            String strValueOf = String.valueOf(i10);
            LiveType liveType = LiveType.INSTANCE;
            TextStyle numberMono = liveType.getNumberMono();
            LiveColors liveColors = LiveColors.INSTANCE;
            TextKt.m6020Text4IGK_g(strValueOf, SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(f10)), 0L, 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5583getEnde0LSkKk()), 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(numberMono, liveColors.m6404getFgMute0d7_KjU(), TextUnitKt.getSp(12), FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777208, null), composerStartRestartGroup, 48, 0, 65020);
            float f11 = 28;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(f10)), Dp.m5678constructorimpl(f11)), 4), liveColors.m6409getPanel0d7_KjU(), null, 2, null);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            enrichedChannel2 = enrichedChannel;
            ChannelLogoKt.m6366ChannelLogouFdPcIQ(enrichedChannel2, Dp.m5678constructorimpl(f11), null, composerStartRestartGroup, ((i12 >> 3) & 14) | 48, 4);
            composer2 = composerStartRestartGroup;
            androidx.compose.foundation.c.y(composer2);
            if (iptvNowNext == null || (now = iptvNowNext.getNow()) == null || (name = now.getTitle()) == null) {
                name = enrichedChannel2.getName();
            }
            TextKt.m6020Text4IGK_g(name, androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getCellTitle(), Color.m3471copywmQWz5c$default(liveColors.m6403getFgDim0d7_KjU(), 0.75f, 0.0f, 0.0f, 0.0f, 14, null), TextUnitKt.getSp(12), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), composer2, 0, 3120, 55292);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.components.p1(i10, enrichedChannel2, iptvNowNext, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NonFocusedChannelSlot$lambda$1(int i10, EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, int i11, Composer composer, int i12) {
        NonFocusedChannelSlot(i10, enrichedChannel, iptvNowNext, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    public static final void QuickZapOverlay(boolean z, EnrichedChannel enrichedChannel, final List<EnrichedChannel> list, final Map<String, IptvNowNext> map, LiveCategoryTree liveCategoryTree, final String str, final r7.l<? super String, x6.t0> lVar, final r7.a<x6.t0> aVar, final r7.l<? super EnrichedChannel, x6.t0> lVar2, final r7.l<? super EnrichedChannel, x6.t0> lVar3, Modifier modifier, Composer composer, int i10, int i11, int i12) {
        int i13;
        Modifier modifier2;
        int i14;
        Iterator<EnrichedChannel> it;
        String id;
        SnapshotMutationPolicy snapshotMutationPolicy;
        int i15;
        Boolean bool;
        int i16;
        int i17;
        MutableState mutableState;
        FocusRequester focusRequester;
        boolean z5;
        MutableState mutableState2;
        Composer composer2;
        Modifier modifier3;
        Composer composerStartRestartGroup = composer.startRestartGroup(440589014);
        if ((i10 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i13 = i10;
        }
        if ((i10 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(enrichedChannel) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(list) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(map) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(liveCategoryTree) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i13 |= composerStartRestartGroup.changed(str) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(lVar) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(aVar) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(lVar2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(lVar3) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        int i18 = i12 & 1024;
        if (i18 != 0) {
            modifier2 = modifier;
            i14 = i11 | 6;
        } else {
            modifier2 = modifier;
            if ((i11 & 6) == 0) {
                i14 = i11 | (composerStartRestartGroup.changed(modifier2) ? 4 : 2);
            } else {
                i14 = i11;
            }
        }
        if ((i13 & 306783379) == 306783378 && (i14 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i18 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(440589014, i13, i14, "com.arflix.tv.ui.screens.tv.live.QuickZapOverlay (QuickZapOverlay.kt:112)");
            }
            boolean zChanged = composerStartRestartGroup.changed(liveCategoryTree);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = getAvailableCategories(liveCategoryTree);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final List list2 = (List) objRememberedValue;
            int i19 = 458752 & i13;
            boolean zChanged2 = composerStartRestartGroup.changed(list2) | (i19 == 131072);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Iterator it2 = list2.iterator();
                int i20 = 0;
                while (true) {
                    if (!it2.hasNext()) {
                        i20 = -1;
                        break;
                    }
                    Iterator it3 = it2;
                    if (kotlin.jvm.internal.p.a(((LiveCategory) it2.next()).getId(), str)) {
                        break;
                    }
                    i20++;
                    it2 = it3;
                }
                if (i20 < 0) {
                    i20 = 0;
                }
                objRememberedValue2 = SnapshotIntStateKt.mutableIntStateOf(i20);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableIntState mutableIntState = (MutableIntState) objRememberedValue2;
            boolean z10 = ((i13 & 896) == 256) | ((i13 & 112) == 32);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Iterator<EnrichedChannel> it4 = list.iterator();
                int i21 = 0;
                while (true) {
                    if (!it4.hasNext()) {
                        i21 = -1;
                        break;
                    }
                    String id2 = it4.next().getId();
                    if (enrichedChannel != null) {
                        it = it4;
                        id = enrichedChannel.getId();
                    } else {
                        it = it4;
                        id = null;
                    }
                    if (kotlin.jvm.internal.p.a(id2, id)) {
                        break;
                    }
                    i21++;
                    it4 = it;
                }
                if (i21 < 0) {
                    i21 = 0;
                }
                objRememberedValue3 = SnapshotIntStateKt.mutableIntStateOf(i21);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableIntState mutableIntState2 = (MutableIntState) objRememberedValue3;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue4 == companion.getEmpty()) {
                snapshotMutationPolicy = null;
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                snapshotMutationPolicy = null;
            }
            MutableState mutableState3 = (MutableState) objRememberedValue4;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, snapshotMutationPolicy, 2, snapshotMutationPolicy);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            MutableState mutableState4 = (MutableState) objRememberedValue5;
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            FocusRequester focusRequester2 = (FocusRequester) objRememberedValue6;
            Boolean boolValueOf = Boolean.valueOf(z);
            int i22 = i13 & 14;
            boolean z11 = (i22 == 4) | (i19 == 131072);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (z11 || objRememberedValue7 == companion.getEmpty()) {
                i15 = ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
                bool = boolValueOf;
                i16 = i13;
                i17 = i22;
                mutableState = mutableState4;
                focusRequester = focusRequester2;
                z5 = z;
                QuickZapOverlayKt$QuickZapOverlay$1$1 quickZapOverlayKt$QuickZapOverlay$1$1 = new QuickZapOverlayKt$QuickZapOverlay$1$1(z5, str, mutableState, mutableState3, focusRequester, null);
                mutableState2 = mutableState3;
                composerStartRestartGroup.updateRememberedValue(quickZapOverlayKt$QuickZapOverlay$1$1);
                objRememberedValue7 = quickZapOverlayKt$QuickZapOverlay$1$1;
            } else {
                bool = boolValueOf;
                i16 = i13;
                i17 = i22;
                mutableState2 = mutableState3;
                mutableState = mutableState4;
                focusRequester = focusRequester2;
                i15 = ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
                z5 = z;
            }
            EffectsKt.LaunchedEffect(bool, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue7, composerStartRestartGroup, i17);
            boolean z12 = ((i16 & 3670016) == i15) | ((i16 & 29360128) == 8388608);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue8 == companion.getEmpty()) {
                objRememberedValue8 = new t3(6, mutableState2, lVar, aVar, mutableState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            BackHandlerKt.BackHandler(z5, (r7.a) objRememberedValue8, composerStartRestartGroup, i17, 0);
            EnterTransition enterTransitionFadeIn$default = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, 2, null);
            TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(300, 0, null, 6, null);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == companion.getEmpty()) {
                objRememberedValue9 = new w0(16);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            EnterTransition enterTransitionPlus = enterTransitionFadeIn$default.plus(EnterExitTransitionKt.slideInVertically(tweenSpecTween$default, (r7.l) objRememberedValue9));
            ExitTransition exitTransitionFadeOut$default = EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null);
            TweenSpec tweenSpecTween$default2 = AnimationSpecKt.tween$default(200, 0, null, 6, null);
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue10 == companion.getEmpty()) {
                objRememberedValue10 = new w0(17);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            Modifier modifier5 = modifier4;
            final MutableState mutableState5 = mutableState;
            final MutableState mutableState6 = mutableState2;
            final FocusRequester focusRequester3 = focusRequester;
            composer2 = composerStartRestartGroup;
            AnimatedVisibilityKt.AnimatedVisibility(z, SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null), enterTransitionPlus, exitTransitionFadeOut$default.plus(EnterExitTransitionKt.slideOutVertically(tweenSpecTween$default2, (r7.l) objRememberedValue10)), (String) null, ComposableLambdaKt.rememberComposableLambda(699438510, true, new r7.q() { // from class: com.arflix.tv.ui.screens.tv.live.v2
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return QuickZapOverlayKt.QuickZapOverlay$lambda$18(focusRequester3, list2, mutableIntState, lVar, list, mutableIntState2, str, lVar3, lVar2, aVar, mutableState6, mutableState5, map, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue);
                }
            }, composerStartRestartGroup, 54), composer2, i17 | 200064, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new w2(z, enrichedChannel, list, map, liveCategoryTree, str, lVar, aVar, lVar2, lVar3, modifier3, i10, i11, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String QuickZapOverlay$lambda$11(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 QuickZapOverlay$lambda$15$0(r7.l lVar, r7.a aVar, MutableState mutableState, MutableState mutableState2) {
        if (QuickZapOverlay$lambda$8(mutableState)) {
            lVar.invoke(QuickZapOverlay$lambda$11(mutableState2));
            QuickZapOverlay$lambda$9(mutableState, false);
        } else {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int QuickZapOverlay$lambda$16$0(int i10) {
        return i10 / 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int QuickZapOverlay$lambda$17$0(int i10) {
        return i10 / 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 QuickZapOverlay$lambda$18(FocusRequester focusRequester, final List list, final MutableIntState mutableIntState, final r7.l lVar, final List list2, final MutableIntState mutableIntState2, final String str, final r7.l lVar2, final r7.l lVar3, final r7.a aVar, final MutableState mutableState, final MutableState mutableState2, Map map, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        Modifier modifier;
        boolean z;
        String strConcat;
        int i11;
        String strLiveCategoryLabel;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(699438510, i10, -1, "com.arflix.tv.ui.screens.tv.live.QuickZapOverlay.<anonymous> (QuickZapOverlay.kt:153)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion, 0.0f, 1, null);
        Brush.Companion companion2 = Brush.INSTANCE;
        Color.Companion companion3 = Color.INSTANCE;
        Modifier modifierFocusable$default = FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(BackgroundKt.background$default(modifierFillMaxSize$default, Brush.Companion.m3435verticalGradient8A3gB4$default(companion2, t7.a.E(Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion3.m3498getBlack0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null)), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion3.m3498getBlack0d7_KjU(), 0.78f, 0.0f, 0.0f, 0.0f, 14, null)), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion3.m3498getBlack0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null))), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), focusRequester), false, null, 3, null);
        boolean zChangedInstance = composer.changedInstance(list) | composer.changed(mutableIntState) | composer.changed(lVar) | composer.changed(list2) | composer.changed(mutableIntState2) | composer.changed(str) | composer.changed(lVar2) | composer.changed(lVar3) | composer.changed(aVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            modifier = modifierFocusable$default;
            r7.l<KeyEvent, Boolean> lVar4 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt$QuickZapOverlay$5$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6449invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6449invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    if (!KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        return Boolean.FALSE;
                    }
                    boolean z5 = false;
                    if (QuickZapOverlayKt.QuickZapOverlay$lambda$8(mutableState)) {
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion4 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4171getDirectionUpEK5gGoQ())) {
                            if (!list.isEmpty()) {
                                MutableIntState mutableIntState3 = mutableIntState;
                                mutableIntState3.setIntValue((list.size() + (mutableIntState3.getIntValue() - 1)) % list.size());
                                lVar.invoke(list.get(mutableIntState.getIntValue()).getId());
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4166getDirectionDownEK5gGoQ())) {
                            if (!list.isEmpty()) {
                                MutableIntState mutableIntState4 = mutableIntState;
                                mutableIntState4.setIntValue((mutableIntState4.getIntValue() + 1) % list.size());
                                lVar.invoke(list.get(mutableIntState.getIntValue()).getId());
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4170getDirectionRightEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4165getDirectionCenterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4179getEnterEK5gGoQ())) {
                            QuickZapOverlayKt.QuickZapOverlay$lambda$9(mutableState, false);
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4169getDirectionLeftEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4182getEscapeEK5gGoQ())) {
                            lVar.invoke(QuickZapOverlayKt.QuickZapOverlay$lambda$11(mutableState2));
                            QuickZapOverlayKt.QuickZapOverlay$lambda$9(mutableState, false);
                        }
                        z5 = true;
                    } else {
                        long jM4401getKeyZmokQxo2 = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion5 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion5.m4171getDirectionUpEK5gGoQ())) {
                            if (!list2.isEmpty()) {
                                MutableIntState mutableIntState5 = mutableIntState2;
                                mutableIntState5.setIntValue((list2.size() + (mutableIntState5.getIntValue() - 1)) % list2.size());
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion5.m4166getDirectionDownEK5gGoQ())) {
                            if (!list2.isEmpty()) {
                                MutableIntState mutableIntState6 = mutableIntState2;
                                mutableIntState6.setIntValue((mutableIntState6.getIntValue() + 1) % list2.size());
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion5.m4169getDirectionLeftEK5gGoQ())) {
                            mutableState2.setValue(str);
                            QuickZapOverlayKt.QuickZapOverlay$lambda$9(mutableState, true);
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion5.m4170getDirectionRightEK5gGoQ())) {
                            if (!list2.isEmpty()) {
                                int size = list2.size();
                                int intValue = mutableIntState2.getIntValue();
                                if (intValue >= 0 && intValue < size) {
                                    lVar2.invoke(list2.get(mutableIntState2.getIntValue()));
                                }
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion5.m4165getDirectionCenterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion5.m4179getEnterEK5gGoQ())) {
                            if (!list2.isEmpty()) {
                                int size2 = list2.size();
                                int intValue2 = mutableIntState2.getIntValue();
                                if (intValue2 >= 0 && intValue2 < size2) {
                                    lVar3.invoke(list2.get(mutableIntState2.getIntValue()));
                                }
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion5.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion5.m4182getEscapeEK5gGoQ())) {
                            aVar.invoke();
                        }
                        z5 = true;
                    }
                    return Boolean.valueOf(z5);
                }
            };
            composer.updateRememberedValue(lVar4);
            objRememberedValue = lVar4;
        } else {
            modifier = modifierFocusable$default;
        }
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(modifier, (r7.l) objRememberedValue);
        composer.startReplaceableGroup(733328855);
        Alignment.Companion companion4 = Alignment.INSTANCE;
        MeasurePolicy measurePolicyD = a0.c.d(companion4, false, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnKeyEvent);
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
        Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(BoxScopeInstance.INSTANCE.align(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), companion4.getTopCenter()), Dp.m5678constructorimpl(56), Dp.m5678constructorimpl(28));
        Arrangement arrangement = Arrangement.INSTANCE;
        Arrangement.HorizontalOrVertical spaceBetween = arrangement.getSpaceBetween();
        Alignment.Vertical centerVertically = companion4.getCenterVertically();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composer, 54);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
        r7.p pVarU2 = a0.c.u(companion5, composerM2991constructorimpl2, measurePolicyRowMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        if (QuickZapOverlay$lambda$8(mutableState)) {
            composer.startReplaceGroup(-1329140218);
            z = false;
            strConcat = StringResources_androidKt.stringResource(R.string.select_category, composer, 0).toUpperCase(Locale.ROOT);
            composer.endReplaceGroup();
        } else {
            z = false;
            composer.startReplaceGroup(-1329034756);
            strConcat = "< ".concat(StringResources_androidKt.stringResource(R.string.channel_categories, composer, 0).toUpperCase(Locale.ROOT));
            composer.endReplaceGroup();
        }
        LiveType liveType = LiveType.INSTANCE;
        TextKt.m6020Text4IGK_g(strConcat, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getSectionTag(), QuickZapOverlay$lambda$8(mutableState) ? LiveColors.INSTANCE.m6397getAccent0d7_KjU() : LiveColors.INSTANCE.m6404getFgMute0d7_KjU(), TextUnitKt.getSp(11), QuickZapOverlay$lambda$8(mutableState) ? FontWeight.INSTANCE.getBold() : FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(1), null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777080, null), composer, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
        LiveCategory liveCategory = (LiveCategory) kotlin.collections.x.p0(mutableIntState.getIntValue(), list);
        String label = liveCategory != null ? liveCategory.getLabel() : null;
        if (label == null) {
            composer.startReplaceGroup(-1328446036);
            composer.endReplaceGroup();
            i11 = 0;
            strLiveCategoryLabel = null;
        } else {
            composer.startReplaceGroup(-1328446035);
            i11 = 0;
            strLiveCategoryLabel = LiveCategoryKt.liveCategoryLabel(label, composer, 0);
            composer.endReplaceGroup();
        }
        if (strLiveCategoryLabel == null) {
            composer.startReplaceGroup(-1151230680);
            strLiveCategoryLabel = StringResources_androidKt.stringResource(R.string.live_label_all_channels, composer, i11);
        } else {
            composer.startReplaceGroup(-1151233315);
        }
        composer.endReplaceGroup();
        Locale locale = Locale.ROOT;
        String upperCase = strLiveCategoryLabel.toUpperCase(locale);
        TextStyle channelName = liveType.getChannelName();
        LiveColors liveColors = LiveColors.INSTANCE;
        TextKt.m6020Text4IGK_g(upperCase, null, 0L, 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(channelName, liveColors.m6402getFg0d7_KjU(), TextUnitKt.getSp(16), FontWeight.INSTANCE.getBold(), null, null, null, null, TextUnitKt.getSp(1.5d), null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777080, null), composer, 0, 0, 65022);
        TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.channel_guide, composer, 0).toUpperCase(locale).concat(" >"), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getSectionTag(), liveColors.m6404getFgMute0d7_KjU(), TextUnitKt.getSp(11), null, null, null, null, null, TextUnitKt.getSp(1), null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777084, null), composer, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        float f10 = 48;
        Modifier modifierM532paddingqDBjuR0 = PaddingKt.m532paddingqDBjuR0(SizeKt.fillMaxHeight$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 0.0f, 1, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(96), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f10));
        Arrangement.HorizontalOrVertical center = arrangement.getCenter();
        Alignment.Vertical centerVertically2 = companion4.getCenterVertically();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(center, centerVertically2, composer, 54);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM532paddingqDBjuR0);
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
        r7.p pVarU3 = a0.c.u(companion5, composerM2991constructorimpl3, measurePolicyRowMeasurePolicy2, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
        }
        a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
        CategorySidebarPanel(list, mutableIntState.getIntValue(), QuickZapOverlay$lambda$8(mutableState), AlphaKt.alpha(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(260)), QuickZapOverlay$lambda$8(mutableState) ? 1.0f : 0.45f), composer, 0, 0);
        androidx.compose.material3.d.w(f10, companion, composer, 6);
        ChannelColumnPanel(list2, mutableIntState2.getIntValue(), !QuickZapOverlay$lambda$8(mutableState), map, AlphaKt.alpha(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(640)), QuickZapOverlay$lambda$8(mutableState) ? 0.55f : 1.0f), composer, 0, 0);
        if (androidx.compose.foundation.c.A(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 QuickZapOverlay$lambda$19(boolean z, EnrichedChannel enrichedChannel, List list, Map map, LiveCategoryTree liveCategoryTree, String str, r7.l lVar, r7.a aVar, r7.l lVar2, r7.l lVar3, Modifier modifier, int i10, int i11, int i12, Composer composer, int i13) {
        QuickZapOverlay(z, enrichedChannel, list, map, liveCategoryTree, str, lVar, aVar, lVar2, lVar3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean QuickZapOverlay$lambda$8(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void QuickZapOverlay$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final List<LiveCategory> getAvailableCategories(LiveCategoryTree liveCategoryTree) {
        ArrayList arrayList = new ArrayList();
        for (LiveCategory liveCategory : liveCategoryTree.getTop()) {
            if (liveCategory.getCount() > 0 || kotlin.jvm.internal.p.a(liveCategory.getId(), TtmlNode.COMBINE_ALL)) {
                arrayList.add(liveCategory);
                if (kotlin.jvm.internal.p.a(liveCategory.getId(), TtmlNode.COMBINE_ALL)) {
                    for (LiveCategory liveCategory2 : liveCategory.getChildren()) {
                        if (liveCategory2.getCount() > 0) {
                            arrayList.add(liveCategory2);
                        }
                    }
                }
            }
        }
        for (LiveCategory liveCategory3 : liveCategoryTree.getGlobal().getCategories()) {
            if (liveCategory3.getCount() > 0) {
                arrayList.add(liveCategory3);
            }
        }
        for (LiveCategory liveCategory4 : liveCategoryTree.getCountries().getCategories()) {
            if (liveCategory4.getCount() > 0) {
                arrayList.add(liveCategory4);
                for (LiveCategory liveCategory5 : liveCategory4.getChildren()) {
                    if (liveCategory5.getCount() > 0) {
                        arrayList.add(liveCategory5);
                    }
                }
            }
        }
        for (LiveCategory liveCategory6 : liveCategoryTree.getAdult().getCategories()) {
            if (liveCategory6.getCount() > 0) {
                arrayList.add(liveCategory6);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (hashSet.add(((LiveCategory) obj).getId())) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }
}
