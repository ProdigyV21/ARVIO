package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.input.key.KeyEvent;
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
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\u001a/\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001aG\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\t2,\u0010\u0010\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u001b²\u0006\u000e\u0010\u0014\u001a\u00020\u00138\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0015\u001a\u00020\u00138\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0016\u001a\u00020\u00138\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0017\u001a\u00020\u00138\n@\nX\u008a\u008e\u0002²\u0006\f\u0010\u0018\u001a\u00020\u00138\nX\u008a\u0084\u0002²\u0006\u000e\u0010\u0019\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u001a\u001a\u00020\t8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "isVisible", "Lkotlin/Function0;", "Lx6/t0;", "onDismiss", "Landroidx/compose/ui/Modifier;", "modifier", "Screensaver", "(ZLr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "", "idleTimeoutMs", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "resetIdleTimer", "Landroidx/compose/runtime/Composable;", "content", "ScreensaverHost", "(JLr7/q;Landroidx/compose/runtime/Composer;II)V", "", "positionX", "positionY", "directionX", "directionY", "alpha", "isScreensaverActive", "lastActivityTime", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ScreensaverKt {
    /* JADX WARN: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Screensaver(boolean r26, final r7.a<x6.t0> r27, androidx.compose.ui.Modifier r28, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ScreensaverKt.Screensaver(boolean, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Screensaver$lambda$1(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Screensaver$lambda$10(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Screensaver$lambda$11(MutableState<Float> mutableState, float f10) {
        mutableState.setValue(Float.valueOf(f10));
    }

    private static final float Screensaver$lambda$13(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 Screensaver$lambda$14(Modifier modifier, final r7.a aVar, float f10, float f11, MutableState mutableState, MutableState mutableState2, State state, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(345002088, i10, -1, "com.arflix.tv.ui.components.Screensaver.<anonymous> (Screensaver.kt:103)");
        }
        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(modifier, 0.0f, 1, null);
        Color.Companion companion = Color.INSTANCE;
        Modifier modifierFocusable$default = FocusableKt.focusable$default(BackgroundKt.m179backgroundbw27NRU$default(modifierFillMaxSize$default, companion.m3498getBlack0d7_KjU(), null, 2, null), false, null, 3, null);
        boolean zChanged = composer.changed(aVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.ScreensaverKt$Screensaver$2$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6183invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6183invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    aVar.invoke();
                    return Boolean.TRUE;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(modifierFocusable$default, (r7.l) objRememberedValue);
        composer.startReplaceableGroup(733328855);
        Alignment.Companion companion2 = Alignment.INSTANCE;
        MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion3.getConstructor();
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
        r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        Modifier.Companion companion4 = Modifier.INSTANCE;
        float f12 = 200;
        float f13 = 100;
        Modifier modifierM580sizeVpY3zN4 = SizeKt.m580sizeVpY3zN4(OffsetKt.m489offsetVpY3zN4(companion4, Dp.m5678constructorimpl(Dp.m5678constructorimpl(f10 - Dp.m5678constructorimpl(f12)) * Screensaver$lambda$1(mutableState)), Dp.m5678constructorimpl(Dp.m5678constructorimpl(f11 - Dp.m5678constructorimpl(f13)) * Screensaver$lambda$4(mutableState2))), Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(f13));
        Alignment center = companion2.getCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM580sizeVpY3zN4);
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
        r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
        TextKt.m6020Text4IGK_g("ARFLIX", null, Color.m3471copywmQWz5c$default(ColorKt.getPink(), Screensaver$lambda$13(state), 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getHeroTitle(), composer, 6, 0, 65530);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        Modifier modifierM490offsetVpY3zN4$default = OffsetKt.m490offsetVpY3zN4$default(boxScopeInstance.align(companion4, companion2.getBottomCenter()), 0.0f, Dp.m5678constructorimpl(-20), 1, null);
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD2 = a0.c.d(companion2, false, composer, 0, -1323940314);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM490offsetVpY3zN4$default);
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
        r7.p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyD2, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
        }
        a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
        TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.component_screensaver_hint, composer, 0), null, Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getCaption(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        if (androidx.compose.foundation.c.A(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 Screensaver$lambda$15(boolean z, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        Screensaver(z, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Screensaver$lambda$2(MutableState<Float> mutableState, float f10) {
        mutableState.setValue(Float.valueOf(f10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Screensaver$lambda$4(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Screensaver$lambda$5(MutableState<Float> mutableState, float f10) {
        mutableState.setValue(Float.valueOf(f10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Screensaver$lambda$7(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Screensaver$lambda$8(MutableState<Float> mutableState, float f10) {
        mutableState.setValue(Float.valueOf(f10));
    }

    public static final void ScreensaverHost(long j10, r7.q<? super r7.a<x6.t0>, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10, int i11) {
        long j11;
        int i12;
        MutableState mutableState;
        long j12;
        long j13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1871157589);
        if ((i10 & 6) == 0) {
            j11 = j10;
            i12 = i10 | (((i11 & 1) == 0 && composerStartRestartGroup.changed(j11)) ? 4 : 2);
        } else {
            j11 = j10;
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(qVar) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            j13 = j11;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i10 & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i11 & 1) != 0) {
                    i12 &= -15;
                }
            } else if ((i11 & 1) != 0) {
                i12 &= -15;
                j11 = 300000;
            }
            long j14 = j11;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1871157589, i12, -1, "com.arflix.tv.ui.components.ScreensaverHost (Screensaver.kt:153)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState2 = (MutableState) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Long.valueOf(System.currentTimeMillis()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableState mutableState3 = (MutableState) objRememberedValue2;
            boolean z = (((i12 & 14) ^ 6) > 4 && composerStartRestartGroup.changed(j14)) || (i12 & 6) == 4;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue3 == companion.getEmpty()) {
                ScreensaverKt$ScreensaverHost$1$1 screensaverKt$ScreensaverHost$1$1 = new ScreensaverKt$ScreensaverHost$1$1(j14, mutableState3, mutableState2, null);
                mutableState = mutableState3;
                j12 = j14;
                composerStartRestartGroup.updateRememberedValue(screensaverKt$ScreensaverHost$1$1);
                objRememberedValue3 = screensaverKt$ScreensaverHost$1$1;
            } else {
                mutableState = mutableState3;
                j12 = j14;
            }
            EffectsKt.LaunchedEffect(x6.t0.f22605a, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue3, composerStartRestartGroup, 6);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new j3(mutableState, mutableState2, 3);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            r7.a aVar = (r7.a) objRememberedValue4;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            qVar.invoke(aVar, composerStartRestartGroup, Integer.valueOf((i12 & 112) | 6));
            Screensaver(ScreensaverHost$lambda$1(mutableState2), aVar, null, composerStartRestartGroup, 48, 4);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
            j13 = j12;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new a2(j13, qVar, i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ScreensaverHost$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScreensaverHost$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ScreensaverHost$lambda$4(MutableState<Long> mutableState) {
        return mutableState.getValue().longValue();
    }

    private static final void ScreensaverHost$lambda$5(MutableState<Long> mutableState, long j10) {
        mutableState.setValue(Long.valueOf(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ScreensaverHost$lambda$7$0(MutableState mutableState, MutableState mutableState2) {
        ScreensaverHost$lambda$5(mutableState, System.currentTimeMillis());
        if (ScreensaverHost$lambda$1(mutableState2)) {
            ScreensaverHost$lambda$2(mutableState2, false);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ScreensaverHost$lambda$9(long j10, r7.q qVar, int i10, int i11, Composer composer, int i12) {
        ScreensaverHost(j10, qVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }
}
