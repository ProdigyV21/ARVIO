package com.arflix.tv.ui.components;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.VisibilityKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MenuKt;
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
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
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
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.ui.skin.ArvioSkin;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\u001aQ\u0010\t\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\t\u0010\n\u001a/\u0010\u0011\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0016²\u0006\u000e\u0010\u0014\u001a\u00020\u00138\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0015\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002"}, d2 = {"", "isVisible", "isWatched", "canRemoveContinueWatching", "Lkotlin/Function0;", "Lx6/t0;", "onMarkWatched", "onRemoveContinueWatching", "onDismiss", "QuickActionMenu", "(ZZZLr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "", "label", "isFocused", "isEnabled", "QuickActionTile", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;ZZLandroidx/compose/runtime/Composer;I)V", "", "focusedIndex", "ignoreNextEnter", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class QuickActionMenuKt {
    public static final void QuickActionMenu(boolean z, boolean z5, boolean z10, r7.a<x6.t0> aVar, r7.a<x6.t0> aVar2, r7.a<x6.t0> aVar3, Composer composer, int i10) {
        int i11;
        MutableState mutableState;
        FocusRequester focusRequester;
        MutableIntState mutableIntState;
        Boolean bool;
        boolean z11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1432122781);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z10) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar2) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar3) ? 131072 : 65536;
        }
        int i12 = i11;
        if ((74899 & i12) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1432122781, i12, -1, "com.arflix.tv.ui.components.QuickActionMenu (QuickActionMenu.kt:60)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableIntState mutableIntState2 = (MutableIntState) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableState mutableState2 = (MutableState) objRememberedValue2;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            FocusRequester focusRequester2 = (FocusRequester) objRememberedValue3;
            Boolean boolValueOf = Boolean.valueOf(z);
            int i13 = i12 & 14;
            boolean z12 = i13 == 4;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue4 == companion.getEmpty()) {
                mutableState = mutableState2;
                focusRequester = focusRequester2;
                mutableIntState = mutableIntState2;
                bool = boolValueOf;
                z11 = true;
                QuickActionMenuKt$QuickActionMenu$1$1 quickActionMenuKt$QuickActionMenu$1$1 = new QuickActionMenuKt$QuickActionMenu$1$1(z, focusRequester, mutableIntState, mutableState, null);
                composerStartRestartGroup.updateRememberedValue(quickActionMenuKt$QuickActionMenu$1$1);
                objRememberedValue4 = quickActionMenuKt$QuickActionMenu$1$1;
            } else {
                mutableState = mutableState2;
                focusRequester = focusRequester2;
                bool = boolValueOf;
                z11 = true;
                mutableIntState = mutableIntState2;
            }
            EffectsKt.LaunchedEffect(bool, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue4, composerStartRestartGroup, i13);
            boolean z13 = (458752 & i12) == 131072 ? z11 : false;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (z13 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new g(10, aVar3);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            BackHandlerKt.BackHandler(z, (r7.a) objRememberedValue5, composerStartRestartGroup, i13, 0);
            composer2 = composerStartRestartGroup;
            AnimatedVisibilityKt.AnimatedVisibility(z, (Modifier) null, EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.m38scaleInL8ZKhE$default(null, 0.92f, 0L, 5, null)), EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.m40scaleOutL8ZKhE$default(null, 0.92f, 0L, 5, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(55815307, z11, new i1(focusRequester, aVar, aVar3, z10, aVar2, mutableIntState, mutableState, z5), composerStartRestartGroup, 54), composer2, i13 | 200064, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new z1(z, z5, z10, aVar, aVar2, aVar3, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 QuickActionMenu$lambda$10(boolean z, boolean z5, boolean z10, r7.a aVar, r7.a aVar2, r7.a aVar3, int i10, Composer composer, int i11) {
        QuickActionMenu(z, z5, z10, aVar, aVar2, aVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean QuickActionMenu$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void QuickActionMenu$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 QuickActionMenu$lambda$8$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 QuickActionMenu$lambda$9(FocusRequester focusRequester, final r7.a aVar, final r7.a aVar2, final boolean z, final r7.a aVar3, final MutableIntState mutableIntState, final MutableState mutableState, boolean z5, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        int i11;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(55815307, i10, -1, "com.arflix.tv.ui.components.QuickActionMenu.<anonymous> (QuickActionMenu.kt:82)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierFocusable$default = FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion, 0.0f, 1, null), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.65f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), focusRequester), false, null, 3, null);
        boolean zChanged = composer.changed(aVar) | composer.changed(aVar2) | composer.changed(z) | composer.changed(aVar3);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            r7.l<KeyEvent, Boolean> lVar = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.QuickActionMenuKt$QuickActionMenu$3$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6182invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6182invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z10 = false;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion2 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4169getDirectionLeftEK5gGoQ())) {
                            if (mutableIntState.getIntValue() > 0) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() - 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4170getDirectionRightEK5gGoQ())) {
                            if (mutableIntState.getIntValue() < 1) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                            }
                        } else if (!Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4171getDirectionUpEK5gGoQ()) && !Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4166getDirectionDownEK5gGoQ())) {
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4165getDirectionCenterEK5gGoQ())) {
                                if (QuickActionMenuKt.QuickActionMenu$lambda$4(mutableState)) {
                                    QuickActionMenuKt.QuickActionMenu$lambda$5(mutableState, false);
                                } else {
                                    int intValue = mutableIntState.getIntValue();
                                    if (intValue == 0) {
                                        aVar.invoke();
                                        aVar2.invoke();
                                    } else if (intValue == 1) {
                                        if (z) {
                                            aVar3.invoke();
                                        }
                                        aVar2.invoke();
                                    }
                                }
                            } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4182getEscapeEK5gGoQ())) {
                                aVar2.invoke();
                            }
                        }
                        z10 = true;
                    }
                    return Boolean.valueOf(z10);
                }
            };
            composer.updateRememberedValue(lVar);
            objRememberedValue = lVar;
        }
        Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(modifierFocusable$default, (r7.l) objRememberedValue);
        Alignment.Companion companion2 = Alignment.INSTANCE;
        Alignment center = companion2.getCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnPreviewKeyEvent);
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
        r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(16));
        Alignment.Vertical centerVertically = companion2.getCenterVertically();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_4, centerVertically, composer, 54);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
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
        r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRowMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        Icons.Filled filled = Icons.INSTANCE.getDefault();
        ImageVector check = z5 ? CheckKt.getCheck(filled) : VisibilityKt.getVisibility(filled);
        if (z5) {
            composer.startReplaceGroup(2139364001);
            i11 = R.string.watched;
        } else {
            composer.startReplaceGroup(2139365232);
            i11 = R.string.component_mark_watched;
        }
        String strStringResource = StringResources_androidKt.stringResource(i11, composer, 0);
        composer.endReplaceGroup();
        QuickActionTile(check, strStringResource, mutableIntState.getIntValue() == 0, true, composer, 3072);
        QuickActionTile(CloseKt.getClose(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.component_remove_continue_watching, composer, 0), mutableIntState.getIntValue() == 1, z, composer, 0);
        if (androidx.compose.foundation.c.A(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    private static final void QuickActionTile(ImageVector imageVector, String str, boolean z, boolean z5, Composer composer, int i10) {
        ImageVector imageVector2;
        int i11;
        long jM3471copywmQWz5c$default;
        long jM3507getTransparent0d7_KjU;
        Composer composerStartRestartGroup = composer.startRestartGroup(-480694953);
        if ((i10 & 6) == 0) {
            imageVector2 = imageVector;
            i11 = (composerStartRestartGroup.changed(imageVector2) ? 4 : 2) | i10;
        } else {
            imageVector2 = imageVector;
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 2048 : 1024;
        }
        int i12 = i11;
        if ((i12 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-480694953, i12, -1, "com.arflix.tv.ui.components.QuickActionTile (QuickActionMenu.kt:160)");
            }
            RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(12));
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-962151766);
                jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ArvioSkin.INSTANCE.getColors(composerStartRestartGroup, 6).m6494getSurfaceRaised0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-962081489);
                jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ArvioSkin.INSTANCE.getColors(composerStartRestartGroup, 6).m6493getSurface0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (z) {
                composerStartRestartGroup.startReplaceGroup(1908631267);
                jM3507getTransparent0d7_KjU = ArvioSkin.INSTANCE.getColors(composerStartRestartGroup, 6).m6491getFocusOutline0d7_KjU();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1908632034);
                composerStartRestartGroup.endReplaceGroup();
                jM3507getTransparent0d7_KjU = Color.INSTANCE.m3507getTransparent0d7_KjU();
            }
            float f10 = z5 ? 1.0f : 0.45f;
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(BorderKt.m191borderxT4_qwU(BackgroundKt.m178backgroundbw27NRU(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(220)), Dp.m5678constructorimpl(MenuKt.InTransitionDuration)), jM3471copywmQWz5c$default, roundedCornerShapeM798RoundedCornerShape0680j_4), Dp.m5678constructorimpl(2), jM3507getTransparent0d7_KjU, roundedCornerShapeM798RoundedCornerShape0680j_4), Dp.m5678constructorimpl(16));
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyColumnMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ArvioSkin arvioSkin = ArvioSkin.INSTANCE;
            IconKt.m1610Iconww6aTOc(imageVector2, (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(26)), Color.m3471copywmQWz5c$default(arvioSkin.getColors(composerStartRestartGroup, 6).m6497getTextPrimary0d7_KjU(), f10, 0.0f, 0.0f, 0.0f, 14, null), composerStartRestartGroup, (i12 & 14) | 432, 0);
            com.arflix.tv.data.repository.g.p(10, companion, composerStartRestartGroup, 6);
            TextKt.m6020Text4IGK_g(str, null, Color.m3471copywmQWz5c$default(arvioSkin.getColors(composerStartRestartGroup, 6).m6497getTextPrimary0d7_KjU(), f10, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arvioSkin.getTypography(composerStartRestartGroup, 6).getCaption(), composerStartRestartGroup, (i12 >> 3) & 14, 0, 65530);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p(imageVector, str, z, z5, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 QuickActionTile$lambda$1(ImageVector imageVector, String str, boolean z, boolean z5, int i10, Composer composer, int i11) {
        QuickActionTile(imageVector, str, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }
}
