package com.arflix.tv.ui.screens.player;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.tv.material3.TextKt;
import com.arflix.tv.data.repository.SkipInterval;
import com.arflix.tv.ui.theme.ArflixTypography;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\u001aI\u0010\f\u001a\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u0019\u0010\u0010\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0016²\u0006\u000e\u0010\u0012\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0013\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\f\u0010\u0015\u001a\u00020\u00148\nX\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/data/repository/SkipInterval;", "interval", "", "dismissed", "controlsVisible", "Lkotlin/Function0;", "Lx6/t0;", "onSkip", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "Landroidx/compose/ui/Modifier;", "modifier", "SkipIntroButton", "(Lcom/arflix/tv/data/repository/SkipInterval;ZZLr7/a;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "", LinkHeader.Parameters.Type, "skipLabel", "(Ljava/lang/String;)Ljava/lang/String;", "autoHidden", "isFocused", "", "scale", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SkipIntroButtonKt {
    /* JADX WARN: Removed duplicated region for block: B:102:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SkipIntroButton(com.arflix.tv.data.repository.SkipInterval r26, boolean r27, boolean r28, r7.a<x6.t0> r29, androidx.compose.ui.focus.FocusRequester r30, androidx.compose.ui.Modifier r31, androidx.compose.runtime.Composer r32, int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 685
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SkipIntroButtonKt.SkipIntroButton(com.arflix.tv.data.repository.SkipInterval, boolean, boolean, r7.a, androidx.compose.ui.focus.FocusRequester, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SkipIntroButton$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final float SkipIntroButton$lambda$10(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkipIntroButton$lambda$11(FocusRequester focusRequester, final r7.a aVar, State state, RoundedCornerShape roundedCornerShape, MutableState mutableState, SkipInterval skipInterval, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        MutableState mutableState2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-813420882, i10, -1, "com.arflix.tv.ui.screens.player.SkipIntroButton.<anonymous> (SkipIntroButton.kt:101)");
        }
        Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        int i11 = 1;
        if (objRememberedValue == companion.getEmpty()) {
            mutableState2 = mutableState;
            objRememberedValue = new b(mutableState2, i11);
            composer.updateRememberedValue(objRememberedValue);
        } else {
            mutableState2 = mutableState;
        }
        Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (r7.l) objRememberedValue);
        boolean zChanged = composer.changed(aVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.player.SkipIntroButtonKt$SkipIntroButton$5$2$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6272invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6272invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z = false;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion2 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4165getDirectionCenterEK5gGoQ())) {
                            aVar.invoke();
                            z = true;
                        }
                    }
                    return Boolean.valueOf(z);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Modifier modifierFocusable$default = FocusableKt.focusable$default(KeyInputModifierKt.onKeyEvent(modifierOnFocusChanged, (r7.l) objRememberedValue2), false, null, 3, null);
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = InteractionSourceKt.MutableInteractionSource();
            composer.updateRememberedValue(objRememberedValue3);
        }
        MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue3;
        boolean zChanged2 = composer.changed(aVar);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new k(11, aVar);
            composer.updateRememberedValue(objRememberedValue4);
        }
        Modifier modifierM212clickableO2vRcR0$default = ClickableKt.m212clickableO2vRcR0$default(modifierFocusable$default, mutableInteractionSource, null, false, null, null, (r7.a) objRememberedValue4, 28, null);
        boolean zChanged3 = composer.changed(state);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue5 == companion.getEmpty()) {
            objRememberedValue5 = new l(state, 4);
            composer.updateRememberedValue(objRememberedValue5);
        }
        Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(modifierM212clickableO2vRcR0$default, (r7.l) objRememberedValue5), SkipIntroButton$lambda$4(mutableState2) ? Color.INSTANCE.m3509getWhite0d7_KjU() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), roundedCornerShape);
        float fM5678constructorimpl = Dp.m5678constructorimpl(1);
        Color.Companion companion2 = Color.INSTANCE;
        Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(BorderKt.m191borderxT4_qwU(modifierM178backgroundbw27NRU, fM5678constructorimpl, Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null), roundedCornerShape), Dp.m5678constructorimpl(20), Dp.m5678constructorimpl(10));
        Alignment center = Alignment.INSTANCE.getCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        TextKt.m6020Text4IGK_g(skipLabel(skipInterval != null ? skipInterval.getType() : null), null, SkipIntroButton$lambda$4(mutableState2) ? companion2.m3498getBlack0d7_KjU() : companion2.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), 0L, TextUnitKt.getSp(14), SkipIntroButton$lambda$4(mutableState2) ? FontWeight.INSTANCE.getSemiBold() : FontWeight.INSTANCE.getNormal(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, 0, 0, 65530);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkipIntroButton$lambda$11$0$0(MutableState mutableState, FocusState focusState) {
        SkipIntroButton$lambda$5(mutableState, focusState.isFocused());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkipIntroButton$lambda$11$3$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkipIntroButton$lambda$11$4$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(SkipIntroButton$lambda$10(state));
        graphicsLayerScope.setScaleY(SkipIntroButton$lambda$10(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkipIntroButton$lambda$12(SkipInterval skipInterval, boolean z, boolean z5, r7.a aVar, FocusRequester focusRequester, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SkipIntroButton(skipInterval, z, z5, aVar, focusRequester, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SkipIntroButton$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean SkipIntroButton$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SkipIntroButton$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static final String skipLabel(String str) {
        if (str == null) {
            return "Skip";
        }
        switch (str.hashCode()) {
            case -1076796495:
                return !str.equals("mixed-ed") ? "Skip" : "Skip Ending";
            case -1076796173:
                return !str.equals("mixed-op") ? "Skip" : "Skip Intro";
            case 3231:
                return !str.equals("ed") ? "Skip" : "Skip Ending";
            case 3553:
                return !str.equals("op") ? "Skip" : "Skip Intro";
            case 100361836:
                return !str.equals("intro") ? "Skip" : "Skip Intro";
            case 106111499:
                return !str.equals("outro") ? "Skip" : "Skip Ending";
            case 108388543:
                return !str.equals("recap") ? "Skip" : "Skip Recap";
            default:
                return "Skip";
        }
    }
}
