package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
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
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.LinkHeader;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0091\u0001\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a5\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00002\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0003¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u001a²\u0006\u000e\u0010\u0019\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002"}, d2 = {"", "isVisible", "", LinkHeader.Parameters.Title, "isInWatchlist", "isWatched", "isContinueWatching", "Lkotlin/Function0;", "Lx6/t0;", "onPlay", "onViewDetails", "onToggleWatchlist", "onToggleWatched", "onRemoveFromContinueWatching", "onDismiss", "MediaContextMenu", "(ZLjava/lang/String;ZZZLr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "label", "isFocused", "onClick", "ContextMenuItem", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "", "focusedIndex", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MediaContextMenuKt {
    private static final void ContextMenuItem(ImageVector imageVector, String str, boolean z, r7.a<x6.t0> aVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(372546571);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(imageVector) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(372546571, i11, -1, "com.arflix.tv.ui.components.ContextMenuItem (MediaContextMenu.kt:349)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 8;
            Modifier modifierL = com.arflix.tv.data.repository.g.l(f10, SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), z ? ColorKt.getPink() : Color.INSTANCE.m3507getTransparent0d7_KjU());
            float fM5678constructorimpl = Dp.m5678constructorimpl(z ? 0 : 1);
            Color.Companion companion2 = Color.INSTANCE;
            float f11 = 12;
            Modifier modifierH = androidx.fragment.app.a2.h(f11, com.arflix.tv.data.repository.g.k(f10, modifierL, fM5678constructorimpl, z ? companion2.m3507getTransparent0d7_KjU() : Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(16), composerStartRestartGroup, 733328855);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f11, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), companion3.getCenterVertically(), composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
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
            r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyRowMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(imageVector, (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(22)), z ? Color.INSTANCE.m3498getBlack0d7_KjU() : ColorKt.getTextSecondary(), composerStartRestartGroup, (i11 & 14) | 432, 0);
            TextKt.m6020Text4IGK_g(str, null, z ? Color.INSTANCE.m3498getBlack0d7_KjU() : ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getBody(), composerStartRestartGroup, (i11 >> 3) & 14, 0, 65530);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.A(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new w0(imageVector, str, z, aVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenuItem$lambda$1(ImageVector imageVector, String str, boolean z, r7.a aVar, int i10, Composer composer, int i11) {
        ContextMenuItem(imageVector, str, z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MediaContextMenu(final boolean r30, final java.lang.String r31, final boolean r32, final boolean r33, boolean r34, final r7.a<x6.t0> r35, final r7.a<x6.t0> r36, final r7.a<x6.t0> r37, final r7.a<x6.t0> r38, r7.a<x6.t0> r39, r7.a<x6.t0> r40, androidx.compose.runtime.Composer r41, final int r42, final int r43, final int r44) {
        /*
            Method dump skipped, instruction units count: 823
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.MediaContextMenuKt.MediaContextMenu(boolean, java.lang.String, boolean, boolean, boolean, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$6$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$7(FocusRequester focusRequester, final List list, final r7.a aVar, final MutableIntState mutableIntState, String str, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(487568131, i10, -1, "com.arflix.tv.ui.components.MediaContextMenu.<anonymous> (MediaContextMenu.kt:142)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierZIndex = ZIndexModifierKt.zIndex(SizeKt.fillMaxSize$default(companion, 0.0f, 1, null), 50.0f);
        Color.Companion companion2 = Color.INSTANCE;
        Modifier modifierFocusable$default = FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(BackgroundKt.m179backgroundbw27NRU$default(modifierZIndex, Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), focusRequester), false, null, 3, null);
        boolean zChanged = composer.changed(list) | composer.changed(aVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.MediaContextMenuKt$MediaContextMenu$3$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6168invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6168invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z = false;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion3 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4171getDirectionUpEK5gGoQ())) {
                            if (mutableIntState.getIntValue() > 0) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() - 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4166getDirectionDownEK5gGoQ())) {
                            if (mutableIntState.getIntValue() < list.size() - 1) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4165getDirectionCenterEK5gGoQ())) {
                            list.get(mutableIntState.getIntValue()).getAction().invoke();
                            aVar.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4182getEscapeEK5gGoQ())) {
                            aVar.invoke();
                        }
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(modifierFocusable$default, (r7.l) objRememberedValue);
        Alignment.Companion companion3 = Alignment.INSTANCE;
        Alignment topCenter = companion3.getTopCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(topCenter, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion4.getConstructor();
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
        r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        float f10 = 14;
        Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, SizeKt.m583width3ABfNKs(PaddingKt.m533paddingqDBjuR0$default(companion, 0.0f, Dp.m5678constructorimpl(MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE), 0.0f, 0.0f, 13, null), Dp.m5678constructorimpl(320)), ColorKt.getBackgroundCard()), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(16));
        Alignment.Horizontal centerHorizontally = companion3.getCenterHorizontally();
        composer.startReplaceableGroup(-483455358);
        Arrangement arrangement = Arrangement.INSTANCE;
        MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(arrangement, centerHorizontally, composer, 48, -1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
        r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyF, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        TextKt.m6020Text4IGK_g(str, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 2, 0, null, ArflixTypography.INSTANCE.getSectionTitle(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 3072, 57338);
        com.arflix.tv.data.repository.g.p(f10, companion, composer, 6);
        int i11 = 1;
        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
        MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion3, androidx.compose.foundation.c.g(8, arrangement, composer, -483455358), composer, 6, -1323940314);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
        r7.p pVarU3 = a0.c.u(companion4, composerM2991constructorimpl3, measurePolicyJ, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
        }
        a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
        composer.startReplaceGroup(706286501);
        int i12 = 0;
        for (Object obj : list) {
            int i13 = i12 + 1;
            if (i12 < 0) {
                t7.a.Q();
                throw null;
            }
            MenuItem menuItem = (MenuItem) obj;
            ImageVector icon = menuItem.getIcon();
            String strStringResource = StringResources_androidKt.stringResource(menuItem.getLabelRes(), composer, 0);
            boolean z = i12 == mutableIntState.getIntValue();
            boolean zChanged2 = composer.changed(menuItem) | composer.changed(aVar);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new x0(menuItem, aVar, i11);
                composer.updateRememberedValue(objRememberedValue2);
            }
            ContextMenuItem(icon, strStringResource, z, (r7.a) objRememberedValue2, composer, 0);
            i12 = i13;
        }
        composer.endReplaceGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        com.arflix.tv.data.repository.g.p(12, Modifier.INSTANCE, composer, 6);
        TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.press_back_to_close, composer, 0), null, Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getCaption(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        if (androidx.compose.foundation.c.A(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$7$1$0$0$0$0$0(MenuItem menuItem, r7.a aVar) {
        menuItem.getAction().invoke();
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$8(final r7.a aVar, boolean z, String str, List list, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1949756084, i10, -1, "com.arflix.tv.ui.components.MediaContextMenu.<anonymous> (MediaContextMenu.kt:230)");
        }
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(ZIndexModifierKt.zIndex(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 50.0f), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
        boolean zChanged = composer.changed(aVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new g(4, aVar);
            composer.updateRememberedValue(objRememberedValue2);
        }
        Modifier modifierM212clickableO2vRcR0$default = ClickableKt.m212clickableO2vRcR0$default(modifierM179backgroundbw27NRU$default, mutableInteractionSource, null, false, null, null, (r7.a) objRememberedValue2, 28, null);
        boolean zChanged2 = composer.changed(aVar);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.MediaContextMenuKt$MediaContextMenu$4$3$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6169invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX WARN: Removed duplicated region for block: B:9:0x0035  */
                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Boolean m6169invokeZmokQxo(android.view.KeyEvent r6) {
                    /*
                        r5 = this;
                        int r0 = androidx.compose.ui.input.key.KeyEvent_androidKt.m4402getTypeZmokQxo(r6)
                        androidx.compose.ui.input.key.KeyEventType$Companion r1 = androidx.compose.ui.input.key.KeyEventType.INSTANCE
                        int r1 = r1.m4398getKeyDownCS__XNY()
                        boolean r0 = androidx.compose.ui.input.key.KeyEventType.m4394equalsimpl0(r0, r1)
                        if (r0 == 0) goto L35
                        long r0 = androidx.compose.ui.input.key.KeyEvent_androidKt.m4401getKeyZmokQxo(r6)
                        androidx.compose.ui.input.key.Key$Companion r2 = androidx.compose.ui.input.key.Key.INSTANCE
                        long r3 = r2.m4108getBackEK5gGoQ()
                        boolean r0 = androidx.compose.ui.input.key.Key.m4093equalsimpl0(r0, r3)
                        if (r0 != 0) goto L2e
                        long r0 = androidx.compose.ui.input.key.KeyEvent_androidKt.m4401getKeyZmokQxo(r6)
                        long r2 = r2.m4182getEscapeEK5gGoQ()
                        boolean r6 = androidx.compose.ui.input.key.Key.m4093equalsimpl0(r0, r2)
                        if (r6 == 0) goto L35
                    L2e:
                        r7.a<x6.t0> r6 = r1
                        r6.invoke()
                        r6 = 1
                        goto L36
                    L35:
                        r6 = 0
                    L36:
                        java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.MediaContextMenuKt$MediaContextMenu$4$3$1.m6169invokeZmokQxo(android.view.KeyEvent):java.lang.Boolean");
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(modifierM212clickableO2vRcR0$default, (r7.l) objRememberedValue3);
        Alignment bottomCenter = Alignment.INSTANCE.getBottomCenter();
        composer.startReplaceableGroup(733328855);
        int i11 = 0;
        int i12 = 6;
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(bottomCenter, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion2.getConstructor();
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
        r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        Object objRememberedValue4 = composer.rememberedValue();
        if (objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new com.arflix.tv.data.api.c(i12);
            composer.updateRememberedValue(objRememberedValue4);
        }
        EnterTransition enterTransitionSlideInVertically$default = EnterExitTransitionKt.slideInVertically$default(null, (r7.l) objRememberedValue4, 1, null);
        Object objRememberedValue5 = composer.rememberedValue();
        if (objRememberedValue5 == companion.getEmpty()) {
            objRememberedValue5 = new com.arflix.tv.data.api.c(7);
            composer.updateRememberedValue(objRememberedValue5);
        }
        AnimatedVisibilityKt.AnimatedVisibility(z, (Modifier) null, enterTransitionSlideInVertically$default, EnterExitTransitionKt.slideOutVertically$default(null, (r7.l) objRememberedValue5, 1, null), (String) null, ComposableLambdaKt.rememberComposableLambda(291070250, true, new y0(str, list, aVar, i11), composer, 54), composer, 200064, 18);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$8$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int MediaContextMenu$lambda$8$3$0$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int MediaContextMenu$lambda$8$3$1$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$8$3$2(String str, List list, r7.a aVar, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) throws Throwable {
        int i11;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(291070250, i10, -1, "com.arflix.tv.ui.components.MediaContextMenu.<anonymous>.<anonymous>.<anonymous> (MediaContextMenu.kt:252)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        float f10 = 16;
        Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), ColorKt.getBackgroundElevated(), RoundedCornerShapeKt.m800RoundedCornerShapea9UjIt4$default(Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f10), 0.0f, 0.0f, 12, null));
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion2 = Composer.INSTANCE;
        if (objRememberedValue == companion2.getEmpty()) {
            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion2.getEmpty()) {
            objRememberedValue2 = new com.arflix.tv.data.repository.simkl.a(18);
            composer.updateRememberedValue(objRememberedValue2);
        }
        Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(ClickableKt.m212clickableO2vRcR0$default(modifierM178backgroundbw27NRU, mutableInteractionSource, null, false, null, null, (r7.a) objRememberedValue2, 28, null), 0.0f, Dp.m5678constructorimpl(f10), 0.0f, Dp.m5678constructorimpl(24), 5, null);
        composer.startReplaceableGroup(-483455358);
        Arrangement.Vertical top = Arrangement.INSTANCE.getTop();
        Alignment.Companion companion3 = Alignment.INSTANCE;
        MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion3, top, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
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
        r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(ColumnScopeInstance.INSTANCE.align(companion, companion3.getCenterHorizontally()), Dp.m5678constructorimpl(36)), Dp.m5678constructorimpl(4));
        Color.Companion companion5 = Color.INSTANCE;
        BoxKt.Box(com.arflix.tv.data.repository.g.l(2, modifierM564height3ABfNKs, Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null)), composer, 0);
        float f11 = 12;
        com.arflix.tv.data.repository.g.p(f11, companion, composer, 6);
        Integer num = 0;
        TextKt.m6020Text4IGK_g(str, PaddingKt.m531paddingVpY3zN4$default(companion, Dp.m5678constructorimpl(f10), 0.0f, 2, null), ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 2, 0, null, ArflixTypography.INSTANCE.getSectionTitle(), composer, 432, 3072, 57336);
        Composer composer2 = composer;
        float f12 = f11;
        com.arflix.tv.data.repository.g.p(f12, companion, composer2, 6);
        int i12 = 1;
        float f13 = 0.0f;
        Throwable th = null;
        float f14 = 1;
        BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(f14)), Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer2, 6);
        composer2.startReplaceGroup(-634821037);
        Iterator it = list.iterator();
        int i13 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            int i14 = i13 + 1;
            if (i13 < 0) {
                t7.a.Q();
                throw th;
            }
            MenuItem menuItem = (MenuItem) next;
            Modifier.Companion companion6 = Modifier.INSTANCE;
            Modifier modifierM566heightInVpY3zN4$default = SizeKt.m566heightInVpY3zN4$default(SizeKt.fillMaxWidth$default(companion6, f13, i12, th), Dp.m5678constructorimpl(48), f13, 2, th);
            boolean zChanged = composer2.changed(menuItem) | composer2.changed(aVar);
            Object objRememberedValue3 = composer2.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                i11 = 0;
                objRememberedValue3 = new x0(menuItem, aVar, i11);
                composer2.updateRememberedValue(objRememberedValue3);
            } else {
                i11 = 0;
            }
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(ClickableKt.m214clickableXHw0xAI$default(modifierM566heightInVpY3zN4$default, false, null, null, (r7.a) objRememberedValue3, 7, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f12));
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f12, Arrangement.INSTANCE, composer2, 693286680), Alignment.INSTANCE.getCenterVertically(), composer2, 54);
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, i11);
            CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor2 = companion7.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor2);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer2);
            r7.p pVarU2 = a0.c.u(companion7, composerM2991constructorimpl2, measurePolicyRowMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            Integer num2 = num;
            a0.c.y(composer2, qVarModifierMaterializerOf2, composer2, num2, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            float f15 = f12;
            IconKt.m1610Iconww6aTOc(menuItem.getIcon(), (String) null, SizeKt.m578size3ABfNKs(companion6, Dp.m5678constructorimpl(22)), ColorKt.getTextSecondary(), composer2, 3504, 0);
            num = num2;
            float f16 = f14;
            int i15 = i13;
            Iterator it2 = it;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(menuItem.getLabelRes(), composer2, 0), null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getBody(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            composer2 = composer;
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (i15 < list.size() - 1) {
                composer2.startReplaceGroup(1535379095);
                f13 = 0.0f;
                th = null;
                BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(companion6, 0.0f, 1, null), Dp.m5678constructorimpl(f10), 0.0f, 2, null), Dp.m5678constructorimpl(f16)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.05f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer2, 6);
                composer2.endReplaceGroup();
            } else {
                f13 = 0.0f;
                th = null;
                composer2.startReplaceGroup(1535784637);
                composer2.endReplaceGroup();
            }
            i12 = 1;
            i13 = i14;
            f14 = f16;
            f12 = f15;
            it = it2;
        }
        composer2.endReplaceGroup();
        composer2.endReplaceableGroup();
        composer2.endNode();
        composer2.endReplaceableGroup();
        composer2.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$8$3$2$2$0$0$0(MenuItem menuItem, r7.a aVar) {
        menuItem.getAction().invoke();
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$9(boolean z, String str, boolean z5, boolean z10, boolean z11, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i10, int i11, int i12, Composer composer, int i13) {
        MediaContextMenu(z, str, z5, z10, z11, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return x6.t0.f22605a;
    }
}
