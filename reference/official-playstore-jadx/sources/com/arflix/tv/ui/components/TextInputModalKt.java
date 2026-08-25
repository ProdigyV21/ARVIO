package com.arflix.tv.ui.components;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
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
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.ui.theme.ArflixTypography;
import io.ktor.http.LinkHeader;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ai\u0010\r\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00002\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a7\u0010\u0013\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0003¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u001b²\u0006\u000e\u0010\u0015\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0017\u001a\u00020\u00168\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0018\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00198\n@\nX\u008a\u008e\u0002"}, d2 = {"", "isVisible", "", LinkHeader.Parameters.Title, "hint", "initialValue", "isPassword", "isNumeric", "Lkotlin/Function1;", "Lx6/t0;", "onConfirm", "Lkotlin/Function0;", "onCancel", "TextInputModal", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLr7/l;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "text", "isFocused", "isPrimary", "onClick", "ActionButton", "(Ljava/lang/String;ZZLr7/a;Landroidx/compose/runtime/Composer;II)V", "inputText", "", "focusedButton", "isInputFocused", "Landroid/widget/EditText;", "editTextRef", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TextInputModalKt {
    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void ActionButton(java.lang.String r31, boolean r32, boolean r33, r7.a<x6.t0> r34, androidx.compose.runtime.Composer r35, int r36, int r37) {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.TextInputModalKt.ActionButton(java.lang.String, boolean, boolean, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ActionButton$lambda$1(String str, boolean z, boolean z5, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        ActionButton(str, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextInputModal(final boolean r22, final java.lang.String r23, java.lang.String r24, java.lang.String r25, boolean r26, boolean r27, final r7.l<? super java.lang.String, x6.t0> r28, final r7.a<x6.t0> r29, androidx.compose.runtime.Composer r30, final int r31, final int r32) {
        /*
            Method dump skipped, instruction units count: 635
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.TextInputModalKt.TextInputModal(boolean, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, r7.l, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextInputModal$hideKeyboard(Context context, MutableState<EditText> mutableState) {
        EditText editTextTextInputModal$lambda$11 = TextInputModal$lambda$11(mutableState);
        if (editTextTextInputModal$lambda$11 == null) {
            return;
        }
        Object systemService = context.getSystemService("input_method");
        editTextTextInputModal$lambda$11.post(new m3(systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null, editTextTextInputModal$lambda$11));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextInputModal$hideKeyboard$lambda$14(InputMethodManager inputMethodManager, EditText editText) {
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        editText.clearFocus();
        if (inputMethodManager != null) {
            try {
                inputMethodManager.restartInput(editText);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TextInputModal$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final EditText TextInputModal$lambda$11(MutableState<EditText> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TextInputModal$lambda$16$0(r7.a aVar, Context context, MutableState mutableState) {
        TextInputModal$hideKeyboard(context, mutableState);
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final x6.t0 TextInputModal$lambda$17(Context context, final r7.a aVar, final MutableIntState mutableIntState, final r7.l lVar, MutableState mutableState, final FocusRequester focusRequester, final MutableState mutableState2, String str, final String str2, final boolean z, final boolean z5, boolean z10, final MutableState mutableState3, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        Modifier modifier;
        ComposeUiNode.Companion companion;
        float f10;
        Integer num;
        float f11;
        Alignment.Companion companion2;
        Arrangement arrangement;
        int i11;
        Object obj;
        MutableState mutableState4;
        final Context context2 = context;
        final MutableState mutableState5 = mutableState;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-257696399, i10, -1, "com.arflix.tv.ui.components.TextInputModal.<anonymous> (TextInputModal.kt:140)");
        }
        Modifier.Companion companion3 = Modifier.INSTANCE;
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.95f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
        boolean zChangedInstance = composer.changedInstance(context2) | composer.changed(aVar) | composer.changed(mutableIntState) | composer.changed(lVar) | composer.changed(mutableState5);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            modifier = modifierM179backgroundbw27NRU$default;
            objRememberedValue = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.TextInputModalKt$TextInputModal$3$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return m6194invokeZmokQxo(((KeyEvent) obj2).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6194invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z11 = false;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion4 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4182getEscapeEK5gGoQ())) {
                            TextInputModalKt.TextInputModal$hideKeyboard(context2, mutableState2);
                            aVar.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4166getDirectionDownEK5gGoQ())) {
                            if (mutableIntState.getIntValue() == -1) {
                                mutableIntState.setIntValue(0);
                                TextInputModalKt.TextInputModal$hideKeyboard(context2, mutableState2);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4171getDirectionUpEK5gGoQ())) {
                            if (mutableIntState.getIntValue() >= 0) {
                                mutableIntState.setIntValue(-1);
                                focusRequester.requestFocus();
                                TextInputModalKt.TextInputModal$showKeyboard(context2, mutableState2);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4169getDirectionLeftEK5gGoQ())) {
                            if (mutableIntState.getIntValue() > 0) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() - 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4170getDirectionRightEK5gGoQ())) {
                            int intValue = mutableIntState.getIntValue();
                            if (intValue >= 0 && intValue < 1) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4165getDirectionCenterEK5gGoQ())) {
                            int intValue2 = mutableIntState.getIntValue();
                            if (intValue2 == 0) {
                                TextInputModalKt.TextInputModal$hideKeyboard(context2, mutableState2);
                                aVar.invoke();
                            } else if (intValue2 != 1) {
                                TextInputModalKt.TextInputModal$showKeyboard(context2, mutableState2);
                            } else {
                                TextInputModalKt.TextInputModal$hideKeyboard(context2, mutableState2);
                                lVar.invoke(TextInputModalKt.TextInputModal$lambda$1(mutableState5));
                            }
                        }
                        z11 = true;
                    }
                    return Boolean.valueOf(z11);
                }
            };
            mutableState5 = mutableState5;
            composer.updateRememberedValue(objRememberedValue);
        } else {
            modifier = modifierM179backgroundbw27NRU$default;
        }
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(modifier, (r7.l) objRememberedValue);
        Alignment.Companion companion4 = Alignment.INSTANCE;
        Alignment center = companion4.getCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
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
        r7.p pVarU = a0.c.u(companion5, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(com.arflix.tv.data.repository.g.l(20, SizeKt.m583width3ABfNKs(companion3, Dp.m5678constructorimpl(500)), ColorKt.Color(4279900698L)), Dp.m5678constructorimpl(32));
        Alignment.Horizontal centerHorizontally = companion4.getCenterHorizontally();
        composer.startReplaceableGroup(-483455358);
        Arrangement arrangement2 = Arrangement.INSTANCE;
        MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(arrangement2, centerHorizontally, composer, 48, -1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion5.getConstructor();
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
        r7.p pVarU2 = a0.c.u(companion5, composerM2991constructorimpl2, measurePolicyF, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        float f12 = 24;
        TextKt.m6020Text4IGK_g(str, PaddingKt.m533paddingqDBjuR0$default(companion3, 0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(f12), 7, null), com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getSectionTitle(), composer, 432, 0, 65528);
        float f13 = 12;
        Modifier modifierM = com.arflix.tv.data.repository.g.m(4, com.arflix.tv.data.repository.g.k(f13, com.arflix.tv.data.repository.g.l(f13, SizeKt.fillMaxWidth$default(companion3, 0.0f, 1, null), com.arflix.tv.ui.theme.ColorKt.getBackgroundElevated()), Dp.m5678constructorimpl(2), mutableIntState.getIntValue() == -1 ? com.arflix.tv.ui.theme.ColorKt.getPink() : Color.m3471copywmQWz5c$default(com.arflix.tv.ui.theme.ColorKt.getPink(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null)), composer, 733328855);
        MeasurePolicy measurePolicyD = a0.c.d(companion4, false, composer, 0, -1323940314);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM);
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
        r7.p pVarU3 = a0.c.u(companion5, composerM2991constructorimpl3, measurePolicyD, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
        }
        a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
        boolean zChanged = composer.changed(mutableState5) | composer.changed(str2) | composer.changed(z) | composer.changed(z5) | composer.changedInstance(context2) | composer.changed(lVar) | composer.changed(mutableIntState);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            companion = companion5;
            f10 = f12;
            num = 0;
            f11 = 0.0f;
            companion2 = companion4;
            arrangement = arrangement2;
            i11 = 1;
            r7.l lVar2 = new r7.l() { // from class: com.arflix.tv.ui.components.s3
                @Override // r7.l
                public final Object invoke(Object obj2) {
                    return TextInputModalKt.TextInputModal$lambda$17$1$0$0$0$0(str2, z, z5, mutableState2, mutableState5, lVar, context2, mutableState3, mutableIntState, (Context) obj2);
                }
            };
            obj = lVar;
            context2 = context2;
            composer.updateRememberedValue(lVar2);
            objRememberedValue2 = lVar2;
        } else {
            f10 = f12;
            companion = companion5;
            arrangement = arrangement2;
            num = 0;
            obj = lVar;
            f11 = 0.0f;
            i11 = 1;
            companion2 = companion4;
        }
        r7.l lVar3 = (r7.l) objRememberedValue2;
        Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(SizeKt.fillMaxWidth$default(companion3, f11, i11, null), focusRequester);
        boolean zChanged2 = composer.changed(z10) | composer.changedInstance(context2);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new com.arflix.tv.data.repository.c0(i11, context2, z10);
            composer.updateRememberedValue(objRememberedValue3);
        }
        AndroidView_androidKt.AndroidView(lVar3, modifierFocusRequester, (r7.l) objRememberedValue3, composer, 0, 0);
        com.arflix.tv.data.repository.g.r(composer, f10, companion3, composer, 6);
        MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(companion2, androidx.compose.foundation.c.g(16, arrangement, composer, 693286680), composer, 6, -1323940314);
        int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor4 = companion.getConstructor();
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
        r7.p pVarU4 = a0.c.u(companion, composerM2991constructorimpl4, measurePolicyC, composerM2991constructorimpl4, currentCompositionLocalMap4);
        if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
            a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
        }
        a0.c.y(composer, qVarModifierMaterializerOf4, composer, num, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        String strStringResource = StringResources_androidKt.stringResource(R.string.cancel, composer, 0);
        Locale locale = Locale.ROOT;
        String upperCase = strStringResource.toUpperCase(locale);
        int i12 = mutableIntState.getIntValue() == 0 ? i11 : 0;
        boolean zChangedInstance2 = composer.changedInstance(context2) | composer.changed(aVar);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            mutableState4 = mutableState2;
            objRememberedValue4 = new n3(aVar, context2, mutableState4, i11);
            composer.updateRememberedValue(objRememberedValue4);
        } else {
            mutableState4 = mutableState2;
        }
        ActionButton(upperCase, i12, false, (r7.a) objRememberedValue4, composer, 0, 4);
        String upperCase2 = StringResources_androidKt.stringResource(R.string.confirm, composer, 0).toUpperCase(locale);
        int i13 = mutableIntState.getIntValue() == i11 ? i11 : 0;
        boolean zChangedInstance3 = composer.changedInstance(context2) | composer.changed(obj) | composer.changed(mutableState5);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChangedInstance3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new t3(0, mutableState4, obj, context2, mutableState5);
            composer.updateRememberedValue(objRememberedValue5);
        }
        ActionButton(upperCase2, i13, true, (r7.a) objRememberedValue5, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
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
    public static final EditText TextInputModal$lambda$17$1$0$0$0$0(String str, boolean z, boolean z5, final MutableState mutableState, final MutableState mutableState2, final r7.l lVar, final Context context, final MutableState mutableState3, final MutableIntState mutableIntState, final Context context2) {
        EditText editText = new EditText(context2);
        mutableState.setValue(editText);
        editText.setText(TextInputModal$lambda$1(mutableState2));
        editText.setTextColor(-1);
        editText.setHintTextColor(-7829368);
        editText.setHint(str);
        editText.setTextSize(18.0f);
        editText.setBackground(null);
        editText.setPadding(32, 32, 32, 32);
        int i10 = 1;
        editText.setSingleLine(true);
        if (z && z5) {
            i10 = 18;
        } else if (z) {
            i10 = 2;
        } else if (z5) {
            i10 = TsExtractor.TS_STREAM_TYPE_AC3;
        }
        editText.setInputType(i10);
        editText.setImeOptions(6);
        editText.addTextChangedListener(new TextWatcher() { // from class: com.arflix.tv.ui.components.TextInputModalKt$TextInputModal$lambda$17$1$0$0$0$0$0$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s10) {
                String string;
                MutableState mutableState4 = mutableState2;
                if (s10 == null || (string = s10.toString()) == null) {
                    string = "";
                }
                mutableState4.setValue(string);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.arflix.tv.ui.components.q3
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i11, android.view.KeyEvent keyEvent) {
                return TextInputModalKt.TextInputModal$lambda$17$1$0$0$0$0$0$1(lVar, context, mutableState, mutableState2, textView, i11, keyEvent);
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.arflix.tv.ui.components.r3
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z10) {
                TextInputModalKt.TextInputModal$lambda$17$1$0$0$0$0$0$2(mutableState3, mutableIntState, context2, view, z10);
            }
        });
        return editText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TextInputModal$lambda$17$1$0$0$0$0$0$1(r7.l lVar, Context context, MutableState mutableState, MutableState mutableState2, TextView textView, int i10, android.view.KeyEvent keyEvent) {
        if (i10 != 6) {
            return false;
        }
        TextInputModal$hideKeyboard(context, mutableState);
        lVar.invoke(TextInputModal$lambda$1(mutableState2));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextInputModal$lambda$17$1$0$0$0$0$0$2(MutableState mutableState, MutableIntState mutableIntState, Context context, View view, boolean z) {
        TextInputModal$lambda$8(mutableState, z);
        if (z) {
            mutableIntState.setIntValue(-1);
            view.postDelayed(new u3(context, view, 0), 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextInputModal$lambda$17$1$0$0$0$0$0$2$0(Context context, View view) {
        Object systemService = context.getSystemService("input_method");
        InputMethodManager inputMethodManager = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if ((inputMethodManager != null ? inputMethodManager.showSoftInput(view, 1) : false) || inputMethodManager == null) {
            return;
        }
        inputMethodManager.showSoftInput(view, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TextInputModal$lambda$17$1$0$0$1$0(boolean z, Context context, EditText editText) {
        if (z && !editText.hasFocus()) {
            editText.requestFocus();
            editText.postDelayed(new u3(context, editText, 1), 100L);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextInputModal$lambda$17$1$0$0$1$0$0(Context context, EditText editText) {
        Object systemService = context.getSystemService("input_method");
        InputMethodManager inputMethodManager = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if ((inputMethodManager != null ? inputMethodManager.showSoftInput(editText, 1) : false) || inputMethodManager == null) {
            return;
        }
        inputMethodManager.showSoftInput(editText, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TextInputModal$lambda$17$1$0$1$0$0(r7.a aVar, Context context, MutableState mutableState) {
        TextInputModal$hideKeyboard(context, mutableState);
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TextInputModal$lambda$17$1$0$1$1$0(r7.l lVar, Context context, MutableState mutableState, MutableState mutableState2) {
        TextInputModal$hideKeyboard(context, mutableState);
        lVar.invoke(TextInputModal$lambda$1(mutableState2));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TextInputModal$lambda$18(boolean z, String str, String str2, String str3, boolean z5, boolean z10, r7.l lVar, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        TextInputModal(z, str, str2, str3, z5, z10, lVar, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final void TextInputModal$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextInputModal$showKeyboard(Context context, MutableState<EditText> mutableState) {
        EditText editTextTextInputModal$lambda$11 = TextInputModal$lambda$11(mutableState);
        if (editTextTextInputModal$lambda$11 == null) {
            return;
        }
        Object systemService = context.getSystemService("input_method");
        editTextTextInputModal$lambda$11.post(new m3(editTextTextInputModal$lambda$11, systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextInputModal$showKeyboard$lambda$13(EditText editText, InputMethodManager inputMethodManager) {
        editText.requestFocus();
        if ((inputMethodManager != null ? inputMethodManager.showSoftInput(editText, 1) : false) || inputMethodManager == null) {
            return;
        }
        inputMethodManager.showSoftInput(editText, 2);
    }
}
