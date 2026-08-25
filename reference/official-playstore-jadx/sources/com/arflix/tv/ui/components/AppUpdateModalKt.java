package com.arflix.tv.ui.components;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.media3.exoplayer.RendererCapabilities;
import com.arflix.tv.R;
import com.arflix.tv.updater.UpdateStatus;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\u001a]\u0010\t\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\t\u0010\n\u001a;\u0010\u0010\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0003¢\u0006\u0004\b\u0010\u0010\u0011\u001aA\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00142\b\b\u0002\u0010\u0018\u001a\u00020\u0014H\u0003¢\u0006\u0004\b\u0019\u0010\u001a¨\u0006\u001d²\u0006\u000e\u0010\u001c\u001a\u00020\u001b8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus;", "status", "Lkotlin/Function0;", "Lx6/t0;", "onDownload", "onCancelDownload", "onInstall", "onDismiss", "onIgnore", "AppUpdateModal", "(Lcom/arflix/tv/updater/UpdateStatus;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "ModalScrim", "(Lr7/a;Lr7/q;Landroidx/compose/runtime/Composer;I)V", "", "label", "", "isFocused", "onClick", "highlighted", "enabled", "UpdateActionButton", "(Ljava/lang/String;ZLr7/a;ZZLandroidx/compose/runtime/Composer;II)V", "", "focusedIndex", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AppUpdateModalKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v6, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v9, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    public static final void AppUpdateModal(UpdateStatus updateStatus, r7.a<x6.t0> aVar, r7.a<x6.t0> aVar2, r7.a<x6.t0> aVar3, r7.a<x6.t0> aVar4, r7.a<x6.t0> aVar5, Composer composer, int i10) {
        int i11;
        int i12;
        boolean z;
        int i13;
        r7.a<x6.t0> aVar6;
        int i14;
        List listE;
        boolean z5;
        boolean z10;
        ?? r11;
        ?? r02;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-168806364);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(updateStatus) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar2) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar3) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar4) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar5) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-168806364, i11, -1, "com.arflix.tv.ui.components.AppUpdateModal (AppUpdateModal.kt:72)");
            }
            String strStringResource = StringResources_androidKt.stringResource(R.string.close, composerStartRestartGroup, 0);
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.update_btn_ignore, composerStartRestartGroup, 0);
            String strStringResource3 = StringResources_androidKt.stringResource(R.string.update_btn_download, composerStartRestartGroup, 0);
            String strStringResource4 = StringResources_androidKt.stringResource(R.string.update_btn_install, composerStartRestartGroup, 0);
            String strStringResource5 = StringResources_androidKt.stringResource(R.string.update_btn_hide, composerStartRestartGroup, 0);
            String strStringResource6 = StringResources_androidKt.stringResource(R.string.update_btn_retry_install, composerStartRestartGroup, 0);
            int i15 = i11;
            String strStringResource7 = StringResources_androidKt.stringResource(R.string.cancel, composerStartRestartGroup, 0);
            String strStringResource8 = StringResources_androidKt.stringResource(R.string.retry, composerStartRestartGroup, 0);
            boolean zChanged = ((i15 & 14) == 4) | composerStartRestartGroup.changed(strStringResource) | composerStartRestartGroup.changed(strStringResource2) | composerStartRestartGroup.changed(strStringResource3) | composerStartRestartGroup.changed(strStringResource4) | composerStartRestartGroup.changed(strStringResource5) | composerStartRestartGroup.changed(strStringResource6) | composerStartRestartGroup.changed(strStringResource7) | composerStartRestartGroup.changed(strStringResource8);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                if (updateStatus instanceof UpdateStatus.UpdateAvailable) {
                    i12 = i15;
                    z10 = true;
                    z = false;
                    aVar6 = aVar4;
                    objRememberedValue = t7.a.E(new ActionButtonConfig(strStringResource, aVar4, false, false, 12, null), new ActionButtonConfig(strStringResource2, aVar5, false, false, 12, null), new ActionButtonConfig(strStringResource3, aVar, true, false, 8, null));
                    i13 = 16384;
                } else {
                    i12 = i15;
                    z = false;
                    z = false;
                    z = false;
                    if (updateStatus instanceof UpdateStatus.ReadyToInstall) {
                        aVar6 = aVar4;
                        objRememberedValue = t7.a.E(new ActionButtonConfig(strStringResource, aVar6, false, false, 12, null), new ActionButtonConfig(strStringResource4, aVar3, true, false, 8, null));
                        i13 = 16384;
                        z10 = true;
                    } else {
                        if (updateStatus instanceof UpdateStatus.Installing) {
                            z5 = true;
                            i14 = 16384;
                            listE = t7.a.E(new ActionButtonConfig(strStringResource5, aVar4, false, false, 12, null), new ActionButtonConfig(strStringResource6, aVar3, true, false, 8, null));
                        } else {
                            boolean z11 = true;
                            if (updateStatus instanceof UpdateStatus.Downloading) {
                                i14 = 16384;
                                listE = t7.a.E(new ActionButtonConfig(strStringResource5, aVar4, false, false, 12, null), new ActionButtonConfig(strStringResource7, aVar2, false, false, 12, null));
                                z5 = z11;
                            } else if (updateStatus instanceof UpdateStatus.Failure) {
                                i14 = 16384;
                                listE = t7.a.E(new ActionButtonConfig(strStringResource, aVar4, false, false, 12, null), new ActionButtonConfig(strStringResource8, aVar, true, false, 8, null));
                                z5 = z11;
                            } else {
                                i13 = 16384;
                                aVar6 = aVar4;
                                objRememberedValue = Collections.singletonList(new ActionButtonConfig(strStringResource, aVar6, false, false, 12, null));
                                z10 = z11;
                            }
                        }
                        aVar6 = aVar4;
                        objRememberedValue = listE;
                        i13 = i14;
                        z10 = z5;
                    }
                }
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                r02 = z;
                r11 = z10;
            } else {
                aVar6 = aVar4;
                i12 = i15;
                r02 = 0;
                r11 = 1;
                i13 = 16384;
            }
            List list = (List) objRememberedValue;
            boolean zChanged2 = composerStartRestartGroup.changed(list);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotIntStateKt.mutableIntStateOf(t7.a.w(list));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableIntState mutableIntState = (MutableIntState) objRememberedValue2;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            FocusRequester focusRequester = (FocusRequester) objRememberedValue3;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new AppUpdateModalKt$AppUpdateModal$1$1(focusRequester, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            EffectsKt.LaunchedEffect(x6.t0.f22605a, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue4, composerStartRestartGroup, 6);
            ?? r72 = (i12 & 57344) == i13 ? r11 : r02;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (r72 != 0 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new g(r02, aVar6);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            BackHandlerKt.BackHandler(r02, (r7.a) objRememberedValue5, composerStartRestartGroup, r02, r11);
            composer2 = composerStartRestartGroup;
            AndroidDialog_androidKt.Dialog(aVar4, new DialogProperties(true, true, null, false, false, 20, null), ComposableLambdaKt.rememberComposableLambda(-576077637, r11, new h(mutableIntState, focusRequester, updateStatus, list, aVar6), composerStartRestartGroup, 54), composer2, ((i12 >> 12) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new i(updateStatus, aVar, aVar2, aVar3, aVar4, aVar5, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AppUpdateModal$lambda$6$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AppUpdateModal$lambda$7(r7.a aVar, FocusRequester focusRequester, MutableIntState mutableIntState, List list, UpdateStatus updateStatus, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-576077637, i10, -1, "com.arflix.tv.ui.components.AppUpdateModal.<anonymous> (AppUpdateModal.kt:134)");
            }
            ModalScrim(aVar, ComposableLambdaKt.rememberComposableLambda(2060351033, true, new j(mutableIntState, focusRequester, updateStatus, list, aVar), composer, 54), composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:107:0x064d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0659  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x065d  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x069f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x06f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 AppUpdateModal$lambda$7$0(androidx.compose.ui.focus.FocusRequester r73, final r7.a r74, final androidx.compose.runtime.MutableIntState r75, final java.util.List r76, com.arflix.tv.updater.UpdateStatus r77, androidx.compose.foundation.layout.BoxScope r78, androidx.compose.runtime.Composer r79, int r80) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1803
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.AppUpdateModalKt.AppUpdateModal$lambda$7$0(androidx.compose.ui.focus.FocusRequester, r7.a, androidx.compose.runtime.MutableIntState, java.util.List, com.arflix.tv.updater.UpdateStatus, androidx.compose.foundation.layout.BoxScope, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AppUpdateModal$lambda$8(UpdateStatus updateStatus, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, int i10, Composer composer, int i11) {
        AppUpdateModal(updateStatus, aVar, aVar2, aVar3, aVar4, aVar5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void ModalScrim(r7.a<x6.t0> aVar, r7.q<? super BoxScope, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-731552467);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(aVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(qVar) ? 32 : 16;
        }
        int i12 = i11;
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-731552467, i12, -1, "com.arflix.tv.ui.components.ModalScrim (AppUpdateModal.kt:263)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = InteractionSourceKt.MutableInteractionSource();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue2;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierM212clickableO2vRcR0$default = ClickableKt.m212clickableO2vRcR0$default(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion2, 0.0f, 1, null), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.72f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), mutableInteractionSource, null, false, null, null, aVar, 28, null);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment center = companion3.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM212clickableO2vRcR0$default);
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
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new com.arflix.tv.data.repository.simkl.a(9);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierM212clickableO2vRcR0$default2 = ClickableKt.m212clickableO2vRcR0$default(companion2, mutableInteractionSource2, null, false, null, null, (r7.a) objRememberedValue3, 28, null);
            int i13 = (i12 << 6) & 7168;
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM212clickableO2vRcR0$default2);
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
            qVar.invoke(boxScopeInstance, composerStartRestartGroup, Integer.valueOf(((i13 >> 6) & 112) | 6));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new x2(aVar, qVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ModalScrim$lambda$3(r7.a aVar, r7.q qVar, int i10, Composer composer, int i11) {
        ModalScrim(aVar, qVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0222  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void UpdateActionButton(java.lang.String r33, boolean r34, r7.a<x6.t0> r35, boolean r36, boolean r37, androidx.compose.runtime.Composer r38, int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 574
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.AppUpdateModalKt.UpdateActionButton(java.lang.String, boolean, r7.a, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 UpdateActionButton$lambda$1(String str, boolean z, r7.a aVar, boolean z5, boolean z10, int i10, int i11, Composer composer, int i12) {
        UpdateActionButton(str, z, aVar, z5, z10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }
}
