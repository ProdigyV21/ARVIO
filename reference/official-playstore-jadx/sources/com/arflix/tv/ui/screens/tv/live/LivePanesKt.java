package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.WavUtil;
import androidx.tv.material3.TextKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a!\u0010\u0005\u001a\u00020\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a_\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"", "message", "", "percent", "Lx6/t0;", "LoadingPane", "(Ljava/lang/String;ILandroidx/compose/runtime/Composer;I)V", "actionLabel", "Lkotlin/Function0;", "onAction", "Landroidx/compose/ui/Modifier;", "modifier", "", "isFocused", "onMoveUp", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "EmptyStatePane", "(Ljava/lang/String;Ljava/lang/String;Lr7/a;Landroidx/compose/ui/Modifier;ZLr7/a;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LivePanesKt {
    /* JADX WARN: Removed duplicated region for block: B:102:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:179:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0172  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void EmptyStatePane(java.lang.String r102, final java.lang.String r103, r7.a<x6.t0> r104, androidx.compose.ui.Modifier r105, boolean r106, r7.a<x6.t0> r107, androidx.compose.ui.focus.FocusRequester r108, androidx.compose.runtime.Composer r109, int r110, int r111) {
        /*
            Method dump skipped, instruction units count: 1343
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LivePanesKt.EmptyStatePane(java.lang.String, java.lang.String, r7.a, androidx.compose.ui.Modifier, boolean, r7.a, androidx.compose.ui.focus.FocusRequester, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EmptyStatePane$lambda$0$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EmptyStatePane$lambda$0$2$1(String str, long j10, RowScope rowScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1272340068, i10, -1, "com.arflix.tv.ui.screens.tv.live.EmptyStatePane.<anonymous>.<anonymous>.<anonymous> (LivePanes.kt:139)");
            }
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getCatLabel(), j10, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EmptyStatePane$lambda$1(String str, String str2, r7.a aVar, Modifier modifier, boolean z, r7.a aVar2, FocusRequester focusRequester, int i10, int i11, Composer composer, int i12) {
        EmptyStatePane(str, str2, aVar, modifier, z, aVar2, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final void LoadingPane(String str, int i10, Composer composer, int i11) {
        int i12;
        boolean z;
        Modifier.Companion companion;
        int i13;
        String str2 = str;
        int i14 = i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(1735052491);
        if ((i11 & 6) == 0) {
            i12 = i11 | (composerStartRestartGroup.changed(str2) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i14) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i13 = 1;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1735052491, i12, -1, "com.arflix.tv.ui.screens.tv.live.LoadingPane (LivePanes.kt:36)");
            }
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion2, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment.Horizontal centerHorizontally = companion3.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyColumnMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            LiveColors liveColors = LiveColors.INSTANCE;
            int i15 = i12;
            ProgressIndicatorKt.m1762CircularProgressIndicatorLxG7B9w(null, liveColors.m6397getAccent0d7_KjU(), 0.0f, 0L, 0, composerStartRestartGroup, 48, 29);
            if (str == null || kotlin.text.o.h0(str)) {
                str2 = str;
                z = false;
                companion = companion2;
                composerStartRestartGroup.startReplaceGroup(207586253);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(207429610);
                Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion2, 0.0f, Dp.m5678constructorimpl(20), 0.0f, 0.0f, 13, null);
                companion = companion2;
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
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
                r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                z = false;
                str2 = str;
                TextKt.m6020Text4IGK_g(str2, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getCellTitle(), liveColors.m6403getFgDim0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composerStartRestartGroup, i15 & 14, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
                composerStartRestartGroup = composerStartRestartGroup;
                com.arflix.tv.data.repository.g.q(composerStartRestartGroup);
            }
            i14 = i10;
            if (i14 > 0) {
                composerStartRestartGroup.startReplaceGroup(207621097);
                boolean z5 = (i15 & 112) == 32 ? true : z;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    i13 = 1;
                    objRememberedValue = new com.arflix.tv.ui.screens.home.x(i14, i13);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    i13 = 1;
                }
                ProgressIndicatorKt.m1768LinearProgressIndicator_5eSRE((r7.a<Float>) objRememberedValue, SizeKt.m583width3ABfNKs(PaddingKt.m533paddingqDBjuR0$default(companion, 0.0f, Dp.m5678constructorimpl(12), 0.0f, 0.0f, 13, null), Dp.m5678constructorimpl(260)), liveColors.m6397getAccent0d7_KjU(), liveColors.m6400getDivider0d7_KjU(), 0, composerStartRestartGroup, 3504, 16);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                i13 = 1;
                composerStartRestartGroup.startReplaceGroup(207901709);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.settings.telegram.f(i14, i11, i13, str2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float LoadingPane$lambda$0$1$0(int i10) {
        return qb.d.m(i10 / 100.0f, 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LoadingPane$lambda$1(String str, int i10, int i11, Composer composer, int i12) {
        LoadingPane(str, i10, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }
}
