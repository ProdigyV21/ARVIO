package com.arflix.tv.ui.components;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.AspectRatioKt;
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
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.PlayArrowKt;
import androidx.compose.material.icons.filled.SkipNextKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.tv.material3.TextKt;
import coil.compose.SingletonAsyncImageKt;
import com.arflix.tv.R;
import com.arflix.tv.ui.theme.ArflixTypography;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\u008b\u0001\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0007¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0018²\u0006\u000e\u0010\u0013\u001a\u00020\u00058\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0014\u001a\u00020\u00058\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0016\u001a\u00020\u00158\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0017\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002"}, d2 = {"", "isVisible", "", "showTitle", "episodeTitle", "", "seasonNumber", "episodeNumber", "episodeImage", "countdownSeconds", "focusedButtonOverride", "Lkotlin/Function1;", "Lx6/t0;", "onFocusedButtonChange", "Lkotlin/Function0;", "onPlayNext", "onCancel", "NextEpisodeOverlay", "(ZLjava/lang/String;Ljava/lang/String;IILjava/lang/String;ILjava/lang/Integer;Lr7/l;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "internalFocusedButton", "countdown", "", "progress", "actionTaken", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NextEpisodeOverlayKt {
    public static final void NextEpisodeOverlay(final boolean z, final String str, final String str2, final int i10, final int i11, final String str3, int i12, Integer num, r7.l<? super Integer, x6.t0> lVar, final r7.a<x6.t0> aVar, final r7.a<x6.t0> aVar2, Composer composer, final int i13, final int i14, final int i15) {
        int i16;
        int i17;
        Integer num2;
        int i18;
        FocusRequester focusRequester;
        int i19;
        FiniteAnimationSpec finiteAnimationSpec;
        boolean z5;
        int i20;
        MutableIntState mutableIntState;
        MutableState mutableState;
        Integer num3;
        final MutableFloatState mutableFloatState;
        final r7.l<? super Integer, x6.t0> lVar2;
        Composer composer2;
        final int i21;
        final Integer num4;
        Composer composerStartRestartGroup = composer.startRestartGroup(1376540029);
        if ((i13 & 6) == 0) {
            i16 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i13;
        } else {
            i16 = i13;
        }
        if ((i13 & 48) == 0) {
            i16 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i13 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i16 |= composerStartRestartGroup.changed(str2) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i16 |= composerStartRestartGroup.changed(i10) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i16 |= composerStartRestartGroup.changed(i11) ? 16384 : 8192;
        }
        if ((196608 & i13) == 0) {
            i16 |= composerStartRestartGroup.changed(str3) ? 131072 : 65536;
        }
        int i22 = i15 & 64;
        if (i22 != 0) {
            i16 |= 1572864;
            i17 = i12;
        } else {
            i17 = i12;
            if ((i13 & 1572864) == 0) {
                i16 |= composerStartRestartGroup.changed(i17) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
            }
        }
        int i23 = i15 & 128;
        if (i23 != 0) {
            i16 |= 12582912;
            num2 = num;
        } else {
            num2 = num;
            if ((i13 & 12582912) == 0) {
                i16 |= composerStartRestartGroup.changed(num2) ? 8388608 : 4194304;
            }
        }
        int i24 = i15 & 256;
        if (i24 != 0) {
            i16 |= 100663296;
        } else if ((i13 & 100663296) == 0) {
            i16 |= composerStartRestartGroup.changedInstance(lVar) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i13 & 805306368) == 0) {
            i16 |= composerStartRestartGroup.changedInstance(aVar) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i14 & 6) == 0) {
            i18 = i14 | (composerStartRestartGroup.changedInstance(aVar2) ? 4 : 2);
        } else {
            i18 = i14;
        }
        if ((i16 & 306783379) == 306783378 && (i18 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            lVar2 = lVar;
            i21 = i17;
            composer2 = composerStartRestartGroup;
            num4 = num2;
        } else {
            if (i22 != 0) {
                i17 = 10;
            }
            if (i23 != 0) {
                num2 = null;
            }
            r7.l<? super Integer, x6.t0> lVar3 = i24 != 0 ? null : lVar;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1376540029, i16, i18, "com.arflix.tv.ui.components.NextEpisodeOverlay (NextEpisodeOverlay.kt:83)");
            }
            int i25 = i16 & 14;
            int i26 = i18;
            boolean z10 = i25 == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableIntState mutableIntState2 = (MutableIntState) objRememberedValue;
            boolean z11 = i25 == 4;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z11 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotIntStateKt.mutableIntStateOf(i17);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableIntState mutableIntState3 = (MutableIntState) objRememberedValue2;
            boolean z12 = i25 == 4;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MutableFloatState mutableFloatState2 = (MutableFloatState) objRememberedValue3;
            int i27 = i16;
            boolean z13 = ((i16 & 7168) == 2048) | ((i27 & 57344) == 16384);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z13 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            MutableState mutableState2 = (MutableState) objRememberedValue4;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            FocusRequester focusRequester2 = (FocusRequester) objRememberedValue5;
            final int iIntValue = num2 != null ? num2.intValue() : mutableIntState2.getIntValue();
            Boolean boolValueOf = Boolean.valueOf(z);
            int i28 = i17;
            boolean z14 = i25 == 4;
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (z14 || objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = new NextEpisodeOverlayKt$NextEpisodeOverlay$1$1(z, focusRequester2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            EffectsKt.LaunchedEffect(boolValueOf, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue6, composerStartRestartGroup, i25);
            Boolean boolValueOf2 = Boolean.valueOf(z);
            Integer numValueOf = Integer.valueOf(i10);
            Integer numValueOf2 = Integer.valueOf(i11);
            boolean zChanged = (i25 == 4) | composerStartRestartGroup.changed(mutableIntState3) | ((i27 & 3670016) == 1048576) | composerStartRestartGroup.changed(mutableState2) | composerStartRestartGroup.changed(mutableFloatState2) | ((i27 & 1879048192) == 536870912);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue7 == companion.getEmpty()) {
                focusRequester = focusRequester2;
                i19 = i25;
                finiteAnimationSpec = null;
                NextEpisodeOverlayKt$NextEpisodeOverlay$2$1 nextEpisodeOverlayKt$NextEpisodeOverlay$2$1 = new NextEpisodeOverlayKt$NextEpisodeOverlay$2$1(z, i28, mutableIntState3, mutableState2, mutableFloatState2, aVar, null);
                z5 = z;
                i20 = i28;
                mutableIntState = mutableIntState3;
                mutableState = mutableState2;
                num3 = num2;
                mutableFloatState = mutableFloatState2;
                composerStartRestartGroup.updateRememberedValue(nextEpisodeOverlayKt$NextEpisodeOverlay$2$1);
                objRememberedValue7 = nextEpisodeOverlayKt$NextEpisodeOverlay$2$1;
            } else {
                i20 = i28;
                focusRequester = focusRequester2;
                mutableState = mutableState2;
                i19 = i25;
                mutableIntState = mutableIntState3;
                num3 = num2;
                finiteAnimationSpec = null;
                z5 = z;
                mutableFloatState = mutableFloatState2;
            }
            int i29 = i27 >> 6;
            EffectsKt.LaunchedEffect(boolValueOf2, numValueOf, numValueOf2, (r7.p) objRememberedValue7, composerStartRestartGroup, (i29 & 112) | i19 | (i29 & 896));
            boolean z15 = (i26 & 14) == 4;
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (z15 || objRememberedValue8 == companion.getEmpty()) {
                objRememberedValue8 = new g(7, aVar2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            BackHandlerKt.BackHandler(z5, (r7.a) objRememberedValue8, composerStartRestartGroup, i19, 0);
            EnterTransition enterTransitionFadeIn$default = EnterExitTransitionKt.fadeIn$default(finiteAnimationSpec, 0.0f, 3, finiteAnimationSpec);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == companion.getEmpty()) {
                objRememberedValue9 = new com.arflix.tv.data.api.c(8);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            EnterTransition enterTransitionPlus = enterTransitionFadeIn$default.plus(EnterExitTransitionKt.slideInHorizontally$default(finiteAnimationSpec, (r7.l) objRememberedValue9, 1, finiteAnimationSpec));
            ExitTransition exitTransitionFadeOut$default = EnterExitTransitionKt.fadeOut$default(finiteAnimationSpec, 0.0f, 3, finiteAnimationSpec);
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue10 == companion.getEmpty()) {
                objRememberedValue10 = new com.arflix.tv.data.api.c(9);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            ExitTransition exitTransitionPlus = exitTransitionFadeOut$default.plus(EnterExitTransitionKt.slideOutHorizontally$default(finiteAnimationSpec, (r7.l) objRememberedValue10, 1, finiteAnimationSpec));
            final r7.l<? super Integer, x6.t0> lVar4 = lVar3;
            final FocusRequester focusRequester3 = focusRequester;
            final MutableIntState mutableIntState4 = mutableIntState;
            final Integer num5 = num3;
            final MutableState mutableState3 = mutableState;
            r7.q qVar = new r7.q() { // from class: com.arflix.tv.ui.components.g1
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue2 = ((Integer) obj3).intValue();
                    return NextEpisodeOverlayKt.NextEpisodeOverlay$lambda$18(focusRequester3, mutableState3, aVar2, num5, mutableIntState2, lVar4, iIntValue, aVar, mutableIntState4, str3, str2, mutableFloatState, str, i10, i11, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue2);
                }
            };
            lVar2 = lVar4;
            composer2 = composerStartRestartGroup;
            AnimatedVisibilityKt.AnimatedVisibility(z, (Modifier) null, enterTransitionPlus, exitTransitionPlus, (String) null, ComposableLambdaKt.rememberComposableLambda(1862277717, true, qVar, composer2, 54), composer2, i19 | 200064, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            i21 = i20;
            num4 = num3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.h1
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue2 = ((Integer) obj2).intValue();
                    return NextEpisodeOverlayKt.NextEpisodeOverlay$lambda$19(z, str, str2, i10, i11, str3, i21, num4, lVar2, aVar, aVar2, i13, i14, i15, (Composer) obj, iIntValue2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NextEpisodeOverlay$cancelOnce(r7.a<x6.t0> aVar, MutableState<Boolean> mutableState) {
        if (NextEpisodeOverlay$lambda$10(mutableState)) {
            return;
        }
        NextEpisodeOverlay$lambda$11(mutableState, true);
        aVar.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean NextEpisodeOverlay$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void NextEpisodeOverlay$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NextEpisodeOverlay$lambda$15$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int NextEpisodeOverlay$lambda$16$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int NextEpisodeOverlay$lambda$17$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v40 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v26 */
    public static final x6.t0 NextEpisodeOverlay$lambda$18(FocusRequester focusRequester, final MutableState mutableState, final r7.a aVar, final Integer num, final MutableIntState mutableIntState, final r7.l lVar, final int i10, final r7.a aVar2, MutableIntState mutableIntState2, String str, String str2, MutableFloatState mutableFloatState, String str3, int i11, int i12, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i13) {
        Arrangement arrangement;
        float f10;
        float f11;
        Alignment.Companion companion;
        float f12;
        Object obj;
        int i14;
        int i15;
        ?? r10;
        int i16;
        ComposeUiNode.Companion companion2;
        Integer num2;
        ?? r72;
        float fM5678constructorimpl;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1862277717, i13, -1, "com.arflix.tv.ui.components.NextEpisodeOverlay.<anonymous> (NextEpisodeOverlay.kt:138)");
        }
        Modifier.Companion companion3 = Modifier.INSTANCE;
        Modifier modifierFocusable$default = FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null), focusRequester), false, null, 3, null);
        boolean zChanged = composer.changed(mutableState) | composer.changed(aVar) | composer.changed(num) | composer.changed(mutableIntState) | composer.changed(lVar) | composer.changed(i10) | composer.changed(aVar2);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            r7.l<KeyEvent, Boolean> lVar2 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.NextEpisodeOverlayKt$NextEpisodeOverlay$6$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return m6170invokeZmokQxo(((KeyEvent) obj2).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6170invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z = false;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion4 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4182getEscapeEK5gGoQ())) {
                            NextEpisodeOverlayKt.NextEpisodeOverlay$cancelOnce(aVar, mutableState);
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4169getDirectionLeftEK5gGoQ())) {
                            NextEpisodeOverlayKt.NextEpisodeOverlay$updateFocusedButton(num, lVar, mutableIntState, i10 - 1);
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4170getDirectionRightEK5gGoQ())) {
                            NextEpisodeOverlayKt.NextEpisodeOverlay$updateFocusedButton(num, lVar, mutableIntState, i10 + 1);
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4165getDirectionCenterEK5gGoQ())) {
                            int i17 = i10;
                            if (i17 == 0) {
                                NextEpisodeOverlayKt.NextEpisodeOverlay$playNextOnce(aVar2, mutableState);
                            } else if (i17 == 1) {
                                NextEpisodeOverlayKt.NextEpisodeOverlay$cancelOnce(aVar, mutableState);
                            }
                        }
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            };
            composer.updateRememberedValue(lVar2);
            objRememberedValue = lVar2;
        }
        Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(modifierFocusable$default, (r7.l) objRememberedValue);
        Alignment.Companion companion4 = Alignment.INSTANCE;
        Alignment bottomEnd = companion4.getBottomEnd();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(bottomEnd, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion5.getConstructor();
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
        r7.p pVarU = a0.c.u(companion5, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        float f13 = 48;
        Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(PaddingKt.m529padding3ABfNKs(companion3, Dp.m5678constructorimpl(f13)), Dp.m5678constructorimpl(500));
        Brush.Companion companion6 = Brush.INSTANCE;
        Color.Companion companion7 = Color.INSTANCE;
        float f14 = 20;
        float f15 = 1;
        float f16 = 24;
        Modifier modifierM = com.arflix.tv.data.repository.g.m(f16, com.arflix.tv.data.repository.g.k(f14, BackgroundKt.background$default(modifierM583width3ABfNKs, Brush.Companion.m3427horizontalGradient8A3gB4$default(companion6, t7.a.E(Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion7.m3498getBlack0d7_KjU(), 0.95f, 0.0f, 0.0f, 0.0f, 14, null)), Color.m3462boximpl(Color.m3471copywmQWz5c$default(ColorKt.Color(4279900698L), 0.98f, 0.0f, 0.0f, 0.0f, 14, null))), 0.0f, 0.0f, 0, 14, (Object) null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f14)), 0.0f, 4, null), Dp.m5678constructorimpl(f15), Color.m3471copywmQWz5c$default(companion7.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null)), composer, 733328855);
        MeasurePolicy measurePolicyD = a0.c.d(companion4, false, composer, 0, -1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM);
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
        r7.p pVarU2 = a0.c.u(companion5, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        composer.startReplaceableGroup(-483455358);
        Arrangement arrangement2 = Arrangement.INSTANCE;
        MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion4, arrangement2.getTop(), composer, 0, -1323940314);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion3);
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
        r7.p pVarU3 = a0.c.u(companion5, composerM2991constructorimpl3, measurePolicyK, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
        }
        a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        Alignment.Vertical centerVertically = companion4.getCenterVertically();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement2, centerVertically, composer, 48, -1323940314);
        int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor4 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(companion3);
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
        r7.p pVarU4 = a0.c.u(companion5, composerM2991constructorimpl4, measurePolicyG, composerM2991constructorimpl4, currentCompositionLocalMap4);
        if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
            a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
        }
        a0.c.y(composer, qVarModifierMaterializerOf4, composer, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        Icons icons = Icons.INSTANCE;
        IconKt.m1610Iconww6aTOc(SkipNextKt.getSkipNext(icons.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion3, Dp.m5678constructorimpl(f16)), com.arflix.tv.ui.theme.ColorKt.getPink(), composer, 3504, 0);
        float f17 = 8;
        androidx.compose.material3.d.w(f17, companion3, composer, 6);
        String strStringResource = StringResources_androidKt.stringResource(R.string.next, composer, 0);
        Locale locale = Locale.ROOT;
        String upperCase = strStringResource.toUpperCase(locale);
        ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
        TextKt.m6020Text4IGK_g(upperCase, null, com.arflix.tv.ui.theme.ColorKt.getPink(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getLabel(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        SpacerKt.Spacer(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion3, 1.0f, false, 2, null), composer, 0);
        TextKt.m6020Text4IGK_g(androidx.compose.foundation.c.o(mutableIntState2.getIntValue(), "in ", CmcdData.STREAMING_FORMAT_SS), null, com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getBody(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        Composer composer2 = composer;
        androidx.compose.foundation.c.y(composer2);
        float f18 = 16;
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion3, Dp.m5678constructorimpl(f18)), composer2, 6);
        Alignment.Vertical centerVertically2 = companion4.getCenterVertically();
        composer2.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyG2 = androidx.compose.material3.d.g(arrangement2, centerVertically2, composer2, 48, -1323940314);
        int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
        CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor5 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(companion3);
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
        r7.p pVarU5 = a0.c.u(companion5, composerM2991constructorimpl5, measurePolicyG2, composerM2991constructorimpl5, currentCompositionLocalMap5);
        if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
            a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
        }
        a0.c.y(composer2, qVarModifierMaterializerOf5, composer2, 0, 2058660585);
        float f19 = 160;
        float f20 = 12;
        Modifier modifierClip = ClipKt.clip(AspectRatioKt.aspectRatio$default(SizeKt.m583width3ABfNKs(companion3, Dp.m5678constructorimpl(f19)), 1.7777778f, false, 2, null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f20)));
        composer2.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD2 = a0.c.d(companion4, false, composer2, 0, -1323940314);
        int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
        CompositionLocalMap currentCompositionLocalMap6 = composer2.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor6 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierClip);
        if (composer2.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer2.startReusableNode();
        if (composer2.getInserting()) {
            composer2.createNode(constructor6);
        } else {
            composer2.useNode();
        }
        Composer composerM2991constructorimpl6 = Updater.m2991constructorimpl(composer2);
        r7.p pVarU6 = a0.c.u(companion5, composerM2991constructorimpl6, measurePolicyD2, composerM2991constructorimpl6, currentCompositionLocalMap6);
        if (composerM2991constructorimpl6.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
            a0.c.w(currentCompositeKeyHash6, composerM2991constructorimpl6, currentCompositeKeyHash6, pVarU6);
        }
        a0.c.y(composer2, qVarModifierMaterializerOf6, composer2, 0, 2058660585);
        if (str != null) {
            composer2.startReplaceGroup(-1965186608);
            f12 = 0.0f;
            f10 = f19;
            companion = companion4;
            f11 = f20;
            arrangement = arrangement2;
            obj = null;
            SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(str, str2, SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer, 1573248, 952);
            composer2 = composer;
            composer2.endReplaceGroup();
            companion2 = companion5;
            num2 = 0;
            i14 = 1;
            i15 = 733328855;
            r10 = 0;
            i16 = -1323940314;
        } else {
            arrangement = arrangement2;
            f10 = f19;
            f11 = f20;
            companion = companion4;
            f12 = 0.0f;
            obj = null;
            composer2.startReplaceGroup(-1964825799);
            i14 = 1;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null), ColorKt.Color(4280427042L), null, 2, null);
            Alignment center = companion.getCenter();
            i15 = 733328855;
            composer2.startReplaceableGroup(733328855);
            r10 = 0;
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center, false, composer2, 6);
            i16 = -1323940314;
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap7 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor7 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf7 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor7);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl7 = Updater.m2991constructorimpl(composer2);
            companion2 = companion5;
            r7.p pVarU7 = a0.c.u(companion2, composerM2991constructorimpl7, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl7, currentCompositionLocalMap7);
            if (composerM2991constructorimpl7.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                a0.c.w(currentCompositeKeyHash7, composerM2991constructorimpl7, currentCompositeKeyHash7, pVarU7);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf7, composer2, 0, 2058660585);
            num2 = 0;
            IconKt.m1610Iconww6aTOc(PlayArrowKt.getPlayArrow(icons.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion3, Dp.m5678constructorimpl(32)), com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), composer2, 3504, 0);
            com.arflix.tv.data.repository.g.q(composer2);
        }
        float f21 = 4;
        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(SizeKt.m564height3ABfNKs(boxScopeInstance.align(companion3, companion.getBottomCenter()), Dp.m5678constructorimpl(f21)), f12, i14, obj);
        composer2.startReplaceableGroup(i15);
        Alignment.Companion companion8 = companion;
        MeasurePolicy measurePolicyD3 = a0.c.d(companion8, r10, composer2, r10, i16);
        int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composer2, r10);
        CompositionLocalMap currentCompositionLocalMap8 = composer2.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor8 = companion2.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf8 = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
        if (composer2.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer2.startReusableNode();
        if (composer2.getInserting()) {
            composer2.createNode(constructor8);
        } else {
            composer2.useNode();
        }
        Composer composerM2991constructorimpl8 = Updater.m2991constructorimpl(composer2);
        r7.p pVarU8 = a0.c.u(companion2, composerM2991constructorimpl8, measurePolicyD3, composerM2991constructorimpl8, currentCompositionLocalMap8);
        if (composerM2991constructorimpl8.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
            a0.c.w(currentCompositeKeyHash8, composerM2991constructorimpl8, currentCompositeKeyHash8, pVarU8);
        }
        Integer num3 = num2;
        a0.c.y(composer2, qVarModifierMaterializerOf8, composer2, num3, 2058660585);
        BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(SizeKt.fillMaxHeight$default(companion3, f12, i14, obj), f12, i14, obj), Color.m3471copywmQWz5c$default(companion7.m3509getWhite0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer2, 6);
        BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m583width3ABfNKs(SizeKt.fillMaxHeight$default(companion3, f12, i14, obj), Dp.m5678constructorimpl(f10 * mutableFloatState.getFloatValue())), com.arflix.tv.ui.theme.ColorKt.getPink(), null, 2, null), composer2, r10);
        composer2.endReplaceableGroup();
        composer2.endNode();
        composer2.endReplaceableGroup();
        composer2.endReplaceableGroup();
        composer2.endReplaceableGroup();
        composer2.endNode();
        composer2.endReplaceableGroup();
        composer2.endReplaceableGroup();
        SpacerKt.Spacer(SizeKt.m583width3ABfNKs(companion3, Dp.m5678constructorimpl(f18)), composer2, 6);
        ComposeUiNode.Companion companion9 = companion2;
        ?? r73 = r10;
        Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance, companion3, 1.0f, false, 2, null);
        composer2.startReplaceableGroup(-483455358);
        MeasurePolicy measurePolicyK2 = androidx.compose.foundation.c.k(companion8, arrangement.getTop(), composer2, r73 == true ? 1 : 0, i16);
        int currentCompositeKeyHash9 = ComposablesKt.getCurrentCompositeKeyHash(composer2, r73 == true ? 1 : 0);
        CompositionLocalMap currentCompositionLocalMap9 = composer2.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor9 = companion9.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf9 = LayoutKt.modifierMaterializerOf(modifierA);
        if (composer2.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer2.startReusableNode();
        if (composer2.getInserting()) {
            composer2.createNode(constructor9);
        } else {
            composer2.useNode();
        }
        Composer composerM2991constructorimpl9 = Updater.m2991constructorimpl(composer2);
        r7.p pVarU9 = a0.c.u(companion9, composerM2991constructorimpl9, measurePolicyK2, composerM2991constructorimpl9, currentCompositionLocalMap9);
        if (composerM2991constructorimpl9.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl9.rememberedValue(), Integer.valueOf(currentCompositeKeyHash9))) {
            a0.c.w(currentCompositeKeyHash9, composerM2991constructorimpl9, currentCompositeKeyHash9, pVarU9);
        }
        a0.c.y(composer2, qVarModifierMaterializerOf9, composer2, num3, 2058660585);
        TextKt.m6020Text4IGK_g(str3, null, com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getCaption(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        TextKt.m6020Text4IGK_g(str2, null, com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 2, 0, null, arflixTypography.getCardTitle(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion3, Dp.m5678constructorimpl(f21)), composer, 6);
        TextKt.m6020Text4IGK_g("S" + i11 + " E" + i12, null, com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getBadge(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion3, Dp.m5678constructorimpl(f14)), composer, 6);
        Arrangement arrangement3 = arrangement;
        float f22 = f11;
        MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(companion8, androidx.compose.foundation.c.g(f22, arrangement3, composer, 693286680), composer, 6, -1323940314);
        final int i17 = 0;
        int currentCompositeKeyHash10 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap10 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor10 = companion9.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf10 = LayoutKt.modifierMaterializerOf(companion3);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor10);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl10 = Updater.m2991constructorimpl(composer);
        r7.p pVarU10 = a0.c.u(companion9, composerM2991constructorimpl10, measurePolicyC, composerM2991constructorimpl10, currentCompositionLocalMap10);
        if (composerM2991constructorimpl10.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl10.rememberedValue(), Integer.valueOf(currentCompositeKeyHash10))) {
            a0.c.w(currentCompositeKeyHash10, composerM2991constructorimpl10, currentCompositeKeyHash10, pVarU10);
        }
        a0.c.y(composer, qVarModifierMaterializerOf10, composer, num3, 2058660585);
        Modifier modifierK = com.arflix.tv.data.repository.g.k(f22, com.arflix.tv.data.repository.g.l(f22, SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion3, 1.0f, false, 2, null), Dp.m5678constructorimpl(f13)), i10 == 0 ? com.arflix.tv.ui.theme.ColorKt.getPink() : Color.m3471copywmQWz5c$default(companion7.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null)), i10 == 0 ? Dp.m5678constructorimpl(0) : Dp.m5678constructorimpl(f15), Color.m3471copywmQWz5c$default(companion7.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null));
        boolean zChanged2 = composer.changed(mutableState) | composer.changed(aVar2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new r7.a() { // from class: com.arflix.tv.ui.components.f1
                @Override // r7.a
                public final Object invoke() {
                    switch (i17) {
                        case 0:
                            return NextEpisodeOverlayKt.NextEpisodeOverlay$lambda$18$1$0$0$2$0$0(aVar2, mutableState);
                        default:
                            return NextEpisodeOverlayKt.NextEpisodeOverlay$lambda$18$1$0$0$2$2$0(aVar2, mutableState);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Modifier modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue2, 7, null);
        Alignment center2 = companion8.getCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy3 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash11 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap11 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor11 = companion9.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf11 = LayoutKt.modifierMaterializerOf(modifierM214clickableXHw0xAI$default);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor11);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl11 = Updater.m2991constructorimpl(composer);
        r7.p pVarU11 = a0.c.u(companion9, composerM2991constructorimpl11, measurePolicyRememberBoxMeasurePolicy3, composerM2991constructorimpl11, currentCompositionLocalMap11);
        if (composerM2991constructorimpl11.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl11.rememberedValue(), Integer.valueOf(currentCompositeKeyHash11))) {
            a0.c.w(currentCompositeKeyHash11, composerM2991constructorimpl11, currentCompositeKeyHash11, pVarU11);
        }
        a0.c.y(composer, qVarModifierMaterializerOf11, composer, num3, 2058660585);
        Alignment.Vertical centerVertically3 = companion8.getCenterVertically();
        Arrangement.HorizontalOrVertical center3 = arrangement3.getCenter();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center3, centerVertically3, composer, 54);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash12 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap12 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor12 = companion9.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf12 = LayoutKt.modifierMaterializerOf(companion3);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor12);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl12 = Updater.m2991constructorimpl(composer);
        r7.p pVarU12 = a0.c.u(companion9, composerM2991constructorimpl12, measurePolicyRowMeasurePolicy, composerM2991constructorimpl12, currentCompositionLocalMap12);
        if (composerM2991constructorimpl12.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl12.rememberedValue(), Integer.valueOf(currentCompositeKeyHash12))) {
            a0.c.w(currentCompositeKeyHash12, composerM2991constructorimpl12, currentCompositeKeyHash12, pVarU12);
        }
        a0.c.y(composer, qVarModifierMaterializerOf12, composer, num3, 2058660585);
        IconKt.m1610Iconww6aTOc(PlayArrowKt.getPlayArrow(icons.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion3, Dp.m5678constructorimpl(f16)), i10 == 0 ? companion7.m3498getBlack0d7_KjU() : com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), composer, 432, 0);
        androidx.compose.material3.d.w(f17, companion3, composer, 6);
        TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.play, composer, 0).toUpperCase(locale), null, i10 == 0 ? companion7.m3498getBlack0d7_KjU() : com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getButton(), composer, 0, 0, 65530);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(companion3, Dp.m5678constructorimpl(f13));
        final int i18 = 1;
        long jM3509getWhite0d7_KjU = companion7.m3509getWhite0d7_KjU();
        if (i10 != 1) {
            jM3509getWhite0d7_KjU = Color.m3471copywmQWz5c$default(jM3509getWhite0d7_KjU, 0.1f, 0.0f, 0.0f, 0.0f, 14, null);
        }
        Modifier modifierL = com.arflix.tv.data.repository.g.l(f22, modifierM578size3ABfNKs, jM3509getWhite0d7_KjU);
        if (i10 == 1) {
            r72 = 0;
            fM5678constructorimpl = Dp.m5678constructorimpl(0);
        } else {
            r72 = 0;
            fM5678constructorimpl = Dp.m5678constructorimpl(f15);
        }
        Modifier modifierK2 = com.arflix.tv.data.repository.g.k(f22, modifierL, fM5678constructorimpl, Color.m3471copywmQWz5c$default(companion7.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null));
        boolean zChanged3 = composer.changed(mutableState) | composer.changed(aVar);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new r7.a() { // from class: com.arflix.tv.ui.components.f1
                @Override // r7.a
                public final Object invoke() {
                    switch (i18) {
                        case 0:
                            return NextEpisodeOverlayKt.NextEpisodeOverlay$lambda$18$1$0$0$2$0$0(aVar, mutableState);
                        default:
                            return NextEpisodeOverlayKt.NextEpisodeOverlay$lambda$18$1$0$0$2$2$0(aVar, mutableState);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        Modifier modifierM214clickableXHw0xAI$default2 = ClickableKt.m214clickableXHw0xAI$default(modifierK2, false, null, null, (r7.a) objRememberedValue3, 7, null);
        Alignment center4 = companion8.getCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy4 = BoxKt.rememberBoxMeasurePolicy(center4, r72, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash13 = ComposablesKt.getCurrentCompositeKeyHash(composer, r72);
        CompositionLocalMap currentCompositionLocalMap13 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor13 = companion9.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf13 = LayoutKt.modifierMaterializerOf(modifierM214clickableXHw0xAI$default2);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor13);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl13 = Updater.m2991constructorimpl(composer);
        r7.p pVarU13 = a0.c.u(companion9, composerM2991constructorimpl13, measurePolicyRememberBoxMeasurePolicy4, composerM2991constructorimpl13, currentCompositionLocalMap13);
        if (composerM2991constructorimpl13.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl13.rememberedValue(), Integer.valueOf(currentCompositeKeyHash13))) {
            a0.c.w(currentCompositeKeyHash13, composerM2991constructorimpl13, currentCompositeKeyHash13, pVarU13);
        }
        a0.c.y(composer, qVarModifierMaterializerOf13, composer, num3, 2058660585);
        IconKt.m1610Iconww6aTOc(CloseKt.getClose(icons.getDefault()), StringResources_androidKt.stringResource(R.string.cancel, composer, r72), SizeKt.m578size3ABfNKs(companion3, Dp.m5678constructorimpl(f16)), i10 == 1 ? companion7.m3498getBlack0d7_KjU() : com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
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
    public static final x6.t0 NextEpisodeOverlay$lambda$18$1$0$0$2$0$0(r7.a aVar, MutableState mutableState) {
        NextEpisodeOverlay$playNextOnce(aVar, mutableState);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NextEpisodeOverlay$lambda$18$1$0$0$2$2$0(r7.a aVar, MutableState mutableState) {
        NextEpisodeOverlay$cancelOnce(aVar, mutableState);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NextEpisodeOverlay$lambda$19(boolean z, String str, String str2, int i10, int i11, String str3, int i12, Integer num, r7.l lVar, r7.a aVar, r7.a aVar2, int i13, int i14, int i15, Composer composer, int i16) {
        NextEpisodeOverlay(z, str, str2, i10, i11, str3, i12, num, lVar, aVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i13 | 1), RecomposeScopeImplKt.updateChangedFlags(i14), i15);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NextEpisodeOverlay$playNextOnce(r7.a<x6.t0> aVar, MutableState<Boolean> mutableState) {
        if (NextEpisodeOverlay$lambda$10(mutableState)) {
            return;
        }
        NextEpisodeOverlay$lambda$11(mutableState, true);
        aVar.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NextEpisodeOverlay$updateFocusedButton(Integer num, r7.l<? super Integer, x6.t0> lVar, MutableIntState mutableIntState, int i10) {
        int iN = qb.d.n(i10, 0, 1);
        if (num == null) {
            mutableIntState.setIntValue(iN);
        }
        if (lVar != null) {
            lVar.invoke(Integer.valueOf(iN));
        }
    }
}
