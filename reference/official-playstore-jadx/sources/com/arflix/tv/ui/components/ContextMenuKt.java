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
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.CloseKt;
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
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.Color;
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
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\r\u001a_\u0010\r\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\u0011\u0010\u0012\u001ag\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001aI\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0007¢\u0006\u0004\b\u001f\u0010 \u001a\u008f\u0001\u0010%\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0007¢\u0006\u0004\b%\u0010&¨\u0006(²\u0006\u000e\u0010'\u001a\u00020\u001b8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "isVisible", "", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, "", "Lcom/arflix/tv/ui/components/ContextAction;", "actions", "Lkotlin/Function1;", "Lx6/t0;", "onAction", "Lkotlin/Function0;", "onDismiss", "ContextMenu", "(ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "action", "isFocused", "ContextMenuItem", "(Lcom/arflix/tv/ui/components/ContextAction;ZLandroidx/compose/runtime/Composer;I)V", "episodeName", "seasonEpisode", "isWatched", "onPlay", "onSelectSource", "onToggleWatched", "EpisodeContextMenu", "(ZLjava/lang/String;Ljava/lang/String;ZLr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "", "seasonNumber", "onMarkSeasonWatched", "onMarkSeasonUnwatched", "SeasonContextMenu", "(ZILr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "year", "isInWatchlist", "onToggleWatchlist", "onViewDetails", "MediaContextMenu", "(ZLjava/lang/String;Ljava/lang/String;ZZLr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "focusedIndex", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ContextMenuKt {
    /* JADX WARN: Removed duplicated region for block: B:100:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContextMenu(boolean r24, java.lang.String r25, java.lang.String r26, java.util.List<com.arflix.tv.ui.components.ContextAction> r27, r7.l<? super com.arflix.tv.ui.components.ContextAction, x6.t0> r28, r7.a<x6.t0> r29, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 588
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ContextMenuKt.ContextMenu(boolean, java.lang.String, java.lang.String, java.util.List, r7.l, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenu$lambda$0$0(ContextAction contextAction) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenu$lambda$10(boolean z, String str, String str2, List list, r7.l lVar, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        ContextMenu(z, str, str2, list, lVar, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenu$lambda$7$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenu$lambda$8(FocusRequester focusRequester, final r7.a aVar, final List list, final r7.l lVar, final MutableIntState mutableIntState, String str, String str2, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1681209751, i10, -1, "com.arflix.tv.ui.components.ContextMenu.<anonymous> (ContextMenu.kt:131)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion, 0.0f, 1, null);
        Color.Companion companion2 = Color.INSTANCE;
        Modifier modifierFocusable$default = FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(BackgroundKt.m179backgroundbw27NRU$default(modifierFillMaxSize$default, Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.56f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), focusRequester), false, null, 3, null);
        boolean zChanged = composer.changed(aVar) | composer.changed(list) | composer.changed(lVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.ContextMenuKt$ContextMenu$5$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6159invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6159invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z = false;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion3 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4182getEscapeEK5gGoQ())) {
                            aVar.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4171getDirectionUpEK5gGoQ())) {
                            if (mutableIntState.getIntValue() > 0) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() - 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4166getDirectionDownEK5gGoQ())) {
                            if (mutableIntState.getIntValue() < list.size() - 1) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4165getDirectionCenterEK5gGoQ())) {
                            ContextAction contextAction = (ContextAction) kotlin.collections.x.p0(mutableIntState.getIntValue(), list);
                            if (contextAction != null) {
                                lVar.invoke(contextAction);
                            }
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
        Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(com.arflix.tv.data.repository.g.l(18, SizeKt.m583width3ABfNKs(PaddingKt.m533paddingqDBjuR0$default(companion, 0.0f, Dp.m5678constructorimpl(MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE), 0.0f, 0.0f, 13, null), Dp.m5678constructorimpl(360)), ColorKt.getBackgroundElevated()), Dp.m5678constructorimpl(20));
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
        ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
        TextKt.m6020Text4IGK_g(str, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getSectionTitle(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        Composer composer2 = composer;
        if (str2 != null) {
            composer2.startReplaceGroup(134685253);
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(8)), composer2, 6);
            TextKt.m6020Text4IGK_g(str2, null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getBody(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            composer2 = composer;
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(134971631);
            composer2.endReplaceGroup();
        }
        float f10 = 16;
        com.arflix.tv.data.repository.g.p(f10, companion, composer2, 6);
        BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(1)), Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer2, 6);
        float f11 = 12;
        com.arflix.tv.data.repository.g.p(f11, companion, composer2, 6);
        float f12 = 8;
        MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion3, androidx.compose.foundation.c.g(f12, arrangement, composer2, -483455358), composer2, 6, -1323940314);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion);
        if (composer2.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer2.startReusableNode();
        if (composer2.getInserting()) {
            composer2.createNode(constructor3);
        } else {
            composer2.useNode();
        }
        Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer2);
        r7.p pVarU3 = a0.c.u(companion4, composerM2991constructorimpl3, measurePolicyJ, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
        }
        a0.c.y(composer2, qVarModifierMaterializerOf3, composer2, 0, 2058660585);
        composer2.startReplaceGroup(112975746);
        int i11 = 0;
        for (Object obj : list) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                t7.a.Q();
                throw null;
            }
            ContextMenuItem((ContextAction) obj, i11 == mutableIntState.getIntValue(), composer2, 0);
            i11 = i12;
        }
        composer2.endReplaceGroup();
        composer2.endReplaceableGroup();
        composer2.endNode();
        composer2.endReplaceableGroup();
        composer2.endReplaceableGroup();
        Modifier.Companion companion5 = Modifier.INSTANCE;
        com.arflix.tv.data.repository.g.p(14, companion5, composer2, 6);
        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
        Modifier modifierH = androidx.fragment.app.a2.h(6, com.arflix.tv.data.repository.g.j(f12, companion5, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.05f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(f11), composer2, 693286680);
        MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composer2, 48, -1323940314);
        int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
        CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion6 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor4 = companion6.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierH);
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
        r7.p pVarU4 = a0.c.u(companion6, composerM2991constructorimpl4, measurePolicyG, composerM2991constructorimpl4, currentCompositionLocalMap4);
        if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
            a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
        }
        a0.c.y(composer2, qVarModifierMaterializerOf4, composer2, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        IconKt.m1610Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion5, Dp.m5678constructorimpl(f10)), ColorKt.getTextSecondary(), composer2, 3504, 0);
        androidx.compose.material3.d.w(f12, companion5, composer2, 6);
        TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.context_press_back_cancel, composer2, 0), null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getCaption(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenu$lambda$9(final r7.a aVar, boolean z, final String str, final String str2, final List list, final r7.l lVar, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(574879136, i10, -1, "com.arflix.tv.ui.components.ContextMenu.<anonymous> (ContextMenu.kt:245)");
        }
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
        boolean zChanged = composer.changed(aVar);
        Object objRememberedValue2 = composer.rememberedValue();
        int i11 = 3;
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new g(i11, aVar);
            composer.updateRememberedValue(objRememberedValue2);
        }
        Modifier modifierM212clickableO2vRcR0$default = ClickableKt.m212clickableO2vRcR0$default(modifierM179backgroundbw27NRU$default, mutableInteractionSource, null, false, null, null, (r7.a) objRememberedValue2, 28, null);
        boolean zChanged2 = composer.changed(aVar);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.ContextMenuKt$ContextMenu$6$3$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6160invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX WARN: Removed duplicated region for block: B:9:0x0035  */
                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Boolean m6160invokeZmokQxo(android.view.KeyEvent r6) {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ContextMenuKt$ContextMenu$6$3$1.m6160invokeZmokQxo(android.view.KeyEvent):java.lang.Boolean");
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(modifierM212clickableO2vRcR0$default, (r7.l) objRememberedValue3);
        Alignment bottomCenter = Alignment.INSTANCE.getBottomCenter();
        composer.startReplaceableGroup(733328855);
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
            objRememberedValue4 = new com.arflix.tv.data.api.c(2);
            composer.updateRememberedValue(objRememberedValue4);
        }
        EnterTransition enterTransitionSlideInVertically$default = EnterExitTransitionKt.slideInVertically$default(null, (r7.l) objRememberedValue4, 1, null);
        Object objRememberedValue5 = composer.rememberedValue();
        if (objRememberedValue5 == companion.getEmpty()) {
            objRememberedValue5 = new com.arflix.tv.data.api.c(i11);
            composer.updateRememberedValue(objRememberedValue5);
        }
        AnimatedVisibilityKt.AnimatedVisibility(z, (Modifier) null, enterTransitionSlideInVertically$default, EnterExitTransitionKt.slideOutVertically$default(null, (r7.l) objRememberedValue5, 1, null), (String) null, ComposableLambdaKt.rememberComposableLambda(1283633662, true, new r7.q() { // from class: com.arflix.tv.ui.components.w
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                int iIntValue = ((Integer) obj3).intValue();
                return ContextMenuKt.ContextMenu$lambda$9$3$2(str, str2, list, lVar, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue);
            }
        }, composer, 54), composer, 200064, 18);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenu$lambda$9$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ContextMenu$lambda$9$3$0$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ContextMenu$lambda$9$3$1$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenu$lambda$9$3$2(String str, String str2, List list, r7.l lVar, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) throws Throwable {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1283633662, i10, -1, "com.arflix.tv.ui.components.ContextMenu.<anonymous>.<anonymous>.<anonymous> (ContextMenu.kt:266)");
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
            objRememberedValue2 = new com.arflix.tv.data.repository.simkl.a(10);
            composer.updateRememberedValue(objRememberedValue2);
        }
        float f11 = 24;
        Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(ClickableKt.m212clickableO2vRcR0$default(modifierM178backgroundbw27NRU, mutableInteractionSource, null, false, null, null, (r7.a) objRememberedValue2, 28, null), 0.0f, Dp.m5678constructorimpl(f10), 0.0f, Dp.m5678constructorimpl(f11), 5, null);
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
        float f12 = 4;
        Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(ColumnScopeInstance.INSTANCE.align(companion, companion3.getCenterHorizontally()), Dp.m5678constructorimpl(36)), Dp.m5678constructorimpl(f12));
        Color.Companion companion5 = Color.INSTANCE;
        BoxKt.Box(com.arflix.tv.data.repository.g.l(2, modifierM564height3ABfNKs, Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null)), composer, 0);
        float f13 = 12;
        com.arflix.tv.data.repository.g.p(f13, companion, composer, 6);
        ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
        Integer num = 0;
        TextKt.m6020Text4IGK_g(str, PaddingKt.m531paddingVpY3zN4$default(companion, Dp.m5678constructorimpl(f10), 0.0f, 2, null), ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getSectionTitle(), composer, 432, 0, 65528);
        Composer composer2 = composer;
        if (str2 != null) {
            composer2.startReplaceGroup(1042706871);
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f12)), composer2, 6);
            TextKt.m6020Text4IGK_g(str2, PaddingKt.m531paddingVpY3zN4$default(companion, Dp.m5678constructorimpl(f10), 0.0f, 2, null), ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getBody(), composer, 432, 0, 65528);
            composer2 = composer;
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(1043097998);
            composer2.endReplaceGroup();
        }
        float f14 = f13;
        com.arflix.tv.data.repository.g.p(f14, companion, composer2, 6);
        int i11 = 1;
        float f15 = 0.0f;
        Throwable th = null;
        float f16 = 1;
        BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(f16)), Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer2, 6);
        composer2.startReplaceGroup(1696232506);
        Iterator it = list.iterator();
        int i12 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            int i13 = i12 + 1;
            if (i12 < 0) {
                t7.a.Q();
                throw th;
            }
            ContextAction contextAction = (ContextAction) next;
            Modifier.Companion companion6 = Modifier.INSTANCE;
            int i14 = 2;
            Modifier modifierM566heightInVpY3zN4$default = SizeKt.m566heightInVpY3zN4$default(SizeKt.fillMaxWidth$default(companion6, f15, i11, th), Dp.m5678constructorimpl(48), f15, 2, th);
            boolean zChanged = composer2.changed(lVar) | composer2.changed(contextAction);
            Object objRememberedValue3 = composer2.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new j3(lVar, contextAction, i14);
                composer2.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(ClickableKt.m214clickableXHw0xAI$default(modifierM566heightInVpY3zN4$default, false, null, null, (r7.a) objRememberedValue3, 7, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f14));
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f14, Arrangement.INSTANCE, composer2, 693286680), Alignment.INSTANCE.getCenterVertically(), composer2, 54);
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
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
            IconKt.m1610Iconww6aTOc(contextAction.getIcon(), (String) null, SizeKt.m578size3ABfNKs(companion6, Dp.m5678constructorimpl(f11)), contextAction.m6158getColor0d7_KjU(), composer2, 432, 0);
            float f17 = f14;
            float f18 = f16;
            int i15 = i12;
            Iterator it2 = it;
            TextKt.m6020Text4IGK_g(contextAction.getLabel(), null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getBody(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            composer2 = composer;
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (i15 < list.size() - 1) {
                composer2.startReplaceGroup(580108006);
                f15 = 0.0f;
                th = null;
                BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(companion6, 0.0f, 1, null), Dp.m5678constructorimpl(f10), 0.0f, 2, null), Dp.m5678constructorimpl(f18)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.05f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer2, 6);
                composer2.endReplaceGroup();
            } else {
                f15 = 0.0f;
                th = null;
                composer2.startReplaceGroup(580513548);
                composer2.endReplaceGroup();
            }
            i11 = 1;
            i12 = i13;
            f16 = f18;
            num = num2;
            f14 = f17;
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
    public static final x6.t0 ContextMenu$lambda$9$3$2$2$0$0$0(r7.l lVar, ContextAction contextAction) {
        lVar.invoke(contextAction);
        return x6.t0.f22605a;
    }

    private static final void ContextMenuItem(ContextAction contextAction, boolean z, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(1742647163);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(contextAction) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1742647163, i11, -1, "com.arflix.tv.ui.components.ContextMenuItem (ContextMenu.kt:368)");
            }
            Color.Companion companion = Color.INSTANCE;
            long jM3471copywmQWz5c$default = z ? Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null) : companion.m3507getTransparent0d7_KjU();
            long pink = z ? ColorKt.getPink() : Color.INSTANCE.m3507getTransparent0d7_KjU();
            Modifier.Companion companion2 = Modifier.INSTANCE;
            float f10 = 12;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), jM3471copywmQWz5c$default), Dp.m5678constructorimpl(z ? 2 : 0), pink), Dp.m5678constructorimpl(f10));
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion3.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
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
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            float f11 = 24;
            IconKt.m1610Iconww6aTOc(contextAction.getIcon(), (String) null, SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(f11)), z ? ColorKt.getPink() : contextAction.m6158getColor0d7_KjU(), composerStartRestartGroup, 432, 0);
            float f12 = 16;
            SpacerKt.Spacer(SizeKt.m583width3ABfNKs(companion2, Dp.m5678constructorimpl(f12)), composerStartRestartGroup, 6);
            TextKt.m6020Text4IGK_g(contextAction.getLabel(), null, z ? ColorKt.getTextPrimary() : contextAction.m6158getColor0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getBody(), composerStartRestartGroup, 0, 0, 65530);
            composerStartRestartGroup = composerStartRestartGroup;
            SpacerKt.Spacer(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion2, 1.0f, false, 2, null), composerStartRestartGroup, 0);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-18144979);
                Modifier modifierL = com.arflix.tv.data.repository.g.l(6, SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(f11)), ColorKt.getPink());
                Alignment center = companion3.getCenter();
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
                composerStartRestartGroup.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierL);
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
                r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                IconKt.m1610Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(f12)), Color.INSTANCE.m3498getBlack0d7_KjU(), composerStartRestartGroup, 3504, 0);
                com.arflix.tv.data.repository.g.q(composerStartRestartGroup);
            } else {
                composerStartRestartGroup.startReplaceGroup(-17685621);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new b3(i10, z, 1, contextAction));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ContextMenuItem$lambda$1(ContextAction contextAction, boolean z, int i10, Composer composer, int i11) {
        ContextMenuItem(contextAction, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    public static final void EpisodeContextMenu(final boolean z, final String str, final String str2, final boolean z5, final r7.a<x6.t0> aVar, final r7.a<x6.t0> aVar2, final r7.a<x6.t0> aVar3, final r7.a<x6.t0> aVar4, Composer composer, final int i10) {
        int i11;
        Object obj;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1269216442);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(str2) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar2) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            obj = aVar3;
            i11 |= composerStartRestartGroup.changedInstance(obj) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            obj = aVar3;
        }
        if ((12582912 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar4) ? 8388608 : 4194304;
        }
        if ((i11 & 4793491) == 4793490 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1269216442, i11, -1, "com.arflix.tv.ui.components.EpisodeContextMenu (ContextMenu.kt:432)");
            }
            ContextActions contextActions = ContextActions.INSTANCE;
            List listE = t7.a.E(contextActions.getPlay(), contextActions.getSelectSource(), z5 ? contextActions.getMarkUnwatched() : contextActions.getMarkWatched());
            boolean z10 = ((i11 & 458752) == 131072) | ((57344 & i11) == 16384) | ((3670016 & i11) == 1048576) | ((29360128 & i11) == 8388608);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                com.arflix.tv.data.repository.b0 b0Var = new com.arflix.tv.data.repository.b0(aVar, aVar2, obj, aVar4, 2);
                composerStartRestartGroup.updateRememberedValue(b0Var);
                objRememberedValue = b0Var;
            }
            composer2 = composerStartRestartGroup;
            ContextMenu(z, str, str2, listE, (r7.l) objRememberedValue, aVar4, composer2, (i11 & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED) | ((i11 >> 6) & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.z
                @Override // r7.p
                public final Object invoke(Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return ContextMenuKt.EpisodeContextMenu$lambda$1(z, str, str2, z5, aVar, aVar2, aVar3, aVar4, i10, (Composer) obj2, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 EpisodeContextMenu$lambda$0$0(r7.a r1, r7.a r2, r7.a r3, r7.a r4, com.arflix.tv.ui.components.ContextAction r5) {
        /*
            java.lang.String r5 = r5.getId()
            int r0 = r5.hashCode()
            switch(r0) {
                case -2077457917: goto L30;
                case -2021876808: goto L22;
                case 3443508: goto L15;
                case 1111910972: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L3c
        Lc:
            java.lang.String r1 = "mark_watched"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L39
            goto L3c
        L15:
            java.lang.String r2 = "play"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L1e
            goto L3c
        L1e:
            r1.invoke()
            goto L3c
        L22:
            java.lang.String r1 = "sources"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L2c
            goto L3c
        L2c:
            r2.invoke()
            goto L3c
        L30:
            java.lang.String r1 = "mark_unwatched"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L39
            goto L3c
        L39:
            r3.invoke()
        L3c:
            r4.invoke()
            x6.t0 r1 = x6.t0.f22605a
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ContextMenuKt.EpisodeContextMenu$lambda$0$0(r7.a, r7.a, r7.a, r7.a, com.arflix.tv.ui.components.ContextAction):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpisodeContextMenu$lambda$1(boolean z, String str, String str2, boolean z5, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, int i10, Composer composer, int i11) {
        EpisodeContextMenu(z, str, str2, z5, aVar, aVar2, aVar3, aVar4, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:143:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MediaContextMenu(final boolean r26, final java.lang.String r27, java.lang.String r28, final boolean r29, final boolean r30, final r7.a<x6.t0> r31, final r7.a<x6.t0> r32, final r7.a<x6.t0> r33, final r7.a<x6.t0> r34, final r7.a<x6.t0> r35, final r7.a<x6.t0> r36, androidx.compose.runtime.Composer r37, final int r38, final int r39, final int r40) {
        /*
            Method dump skipped, instruction units count: 535
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ContextMenuKt.MediaContextMenu(boolean, java.lang.String, java.lang.String, boolean, boolean, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 MediaContextMenu$lambda$0$0(r7.a r1, r7.a r2, r7.a r3, r7.a r4, r7.a r5, r7.a r6, com.arflix.tv.ui.components.ContextAction r7) {
        /*
            java.lang.String r7 = r7.getId()
            int r0 = r7.hashCode()
            switch(r0) {
                case -2077457917: goto L54;
                case -2021876808: goto L46;
                case -740204888: goto L38;
                case 3443508: goto L2b;
                case 552686543: goto L1e;
                case 1111910972: goto L15;
                case 1567790066: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L60
        Lc:
            java.lang.String r1 = "remove_watchlist"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L27
            goto L60
        L15:
            java.lang.String r1 = "mark_watched"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L5d
            goto L60
        L1e:
            java.lang.String r1 = "add_watchlist"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L27
            goto L60
        L27:
            r4.invoke()
            goto L60
        L2b:
            java.lang.String r2 = "play"
            boolean r2 = r7.equals(r2)
            if (r2 != 0) goto L34
            goto L60
        L34:
            r1.invoke()
            goto L60
        L38:
            java.lang.String r1 = "view_details"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L42
            goto L60
        L42:
            r5.invoke()
            goto L60
        L46:
            java.lang.String r1 = "sources"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L50
            goto L60
        L50:
            r2.invoke()
            goto L60
        L54:
            java.lang.String r1 = "mark_unwatched"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L5d
            goto L60
        L5d:
            r3.invoke()
        L60:
            r6.invoke()
            x6.t0 r1 = x6.t0.f22605a
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ContextMenuKt.MediaContextMenu$lambda$0$0(r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, com.arflix.tv.ui.components.ContextAction):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaContextMenu$lambda$1(boolean z, String str, String str2, boolean z5, boolean z10, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i10, int i11, int i12, Composer composer, int i13) {
        MediaContextMenu(z, str, str2, z5, z10, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return x6.t0.f22605a;
    }

    public static final void SeasonContextMenu(boolean z, int i10, r7.a<x6.t0> aVar, r7.a<x6.t0> aVar2, r7.a<x6.t0> aVar3, Composer composer, int i11) {
        boolean z5;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(292567171);
        if ((i11 & 6) == 0) {
            z5 = z;
            i12 = (composerStartRestartGroup.changed(z5) ? 4 : 2) | i11;
        } else {
            z5 = z;
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar2) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar3) ? 16384 : 8192;
        }
        if ((i12 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(292567171, i12, -1, "com.arflix.tv.ui.components.SeasonContextMenu (ContextMenu.kt:463)");
            }
            String strI = a0.c.i(i10, "Season ");
            ContextActions contextActions = ContextActions.INSTANCE;
            int i13 = 0;
            List listE = t7.a.E(contextActions.getMarkSeasonWatched(), contextActions.getMarkSeasonUnwatched());
            boolean z10 = ((i12 & 896) == 256) | ((i12 & 7168) == 2048) | ((57344 & i12) == 16384);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new a0(aVar, aVar2, aVar3, i13);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ContextMenu(z5, strI, "Quick Actions", listE, (r7.l) objRememberedValue, aVar3, composerStartRestartGroup, (i12 & 14) | 3456 | ((i12 << 3) & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new b0(z, i10, aVar, aVar2, aVar3, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SeasonContextMenu$lambda$0$0(r7.a aVar, r7.a aVar2, r7.a aVar3, ContextAction contextAction) {
        String id = contextAction.getId();
        if (kotlin.jvm.internal.p.a(id, "mark_season_watched")) {
            aVar.invoke();
        } else if (kotlin.jvm.internal.p.a(id, "mark_season_unwatched")) {
            aVar2.invoke();
        }
        aVar3.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SeasonContextMenu$lambda$1(boolean z, int i10, r7.a aVar, r7.a aVar2, r7.a aVar3, int i11, Composer composer, int i12) {
        SeasonContextMenu(z, i10, aVar, aVar2, aVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }
}
