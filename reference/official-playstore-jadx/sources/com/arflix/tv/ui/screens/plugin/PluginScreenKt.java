package com.arflix.tv.ui.screens.plugin;

import android.app.UiModeManager;
import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AddKt;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.ExtensionKt;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
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
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.compose.ui.window.DialogWindowProvider;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.domain.model.ScraperInfo;
import com.arflix.tv.j;
import com.arflix.tv.m;
import com.arflix.tv.ui.components.SettingsRowsKt;
import com.arflix.tv.ui.screens.player.d0;
import com.arflix.tv.ui.screens.plugin.PluginUiEvent;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u009f\u0001\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00042\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00022\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u000f\u0010\u0011\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a]\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001aW\u0010!\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u000b2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0007¢\u0006\u0004\b!\u0010\"\u001a1\u0010%\u001a\u00020\u00052\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0007¢\u0006\u0004\b%\u0010&\u001aK\u0010+\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00162\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0007¢\u0006\u0004\b+\u0010,¨\u00066²\u0006\f\u0010.\u001a\u00020-8\nX\u008a\u0084\u0002²\u0006\u000e\u0010/\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00100\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u00102\u001a\u0004\u0018\u0001018\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0019\u001a\u00020\u00168\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00103\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00104\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00103\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00105\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginViewModel;", "viewModel", "", "focusedIndex", "Lkotlin/Function1;", "Lx6/t0;", "onFocusedIndexChanged", "onMaxIndexChanged", "enterTrigger", "Lkotlin/Function0;", "onEnterTriggerHandled", "", "onModalStateChanged", "onBackPressed", "onNavigateToSection", "PluginScreen", "(Lcom/arflix/tv/ui/screens/plugin/PluginViewModel;ILr7/l;Lr7/l;ILr7/a;Lr7/l;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "HideDialogSystemBars", "(Landroidx/compose/runtime/Composer;I)V", "index", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, "value", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "FocusableSettingsRow", "(IILandroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "isEnabled", "onToggle", "FocusableSettingsToggleRow", "(IILjava/lang/String;Ljava/lang/String;ZLr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "onSave", "onDismiss", "AddRepoDialog", "(Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "message", "cancelText", "confirmText", "onConfirm", "WarningDialog", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "Lcom/arflix/tv/ui/screens/plugin/PluginUiState;", "uiState", "showAddDialog", "showResetDialog", "Lcom/arflix/tv/domain/model/PluginRepository;", "repoToDelete", "isCancelFocused", "isSaveFocused", "isConfirmFocused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PluginScreenKt {
    public static final void AddRepoDialog(l<? super String, t0> lVar, r7.a<t0> aVar, Composer composer, int i10) {
        int i11;
        Composer composer2;
        r7.a<t0> aVar2 = aVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(267603712);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(lVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar2) ? 32 : 16;
        }
        int i12 = i11;
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(267603712, i12, -1, "com.arflix.tv.ui.screens.plugin.AddRepoDialog (PluginScreen.kt:473)");
            }
            HideDialogSystemBars(composerStartRestartGroup, 0);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            FocusRequester focusRequester = (FocusRequester) objRememberedValue2;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            FocusRequester focusRequester2 = (FocusRequester) objRememberedValue3;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            FocusRequester focusRequester3 = (FocusRequester) objRememberedValue4;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new PluginScreenKt$AddRepoDialog$1$1(focusRequester, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            EffectsKt.LaunchedEffect(t0.f22605a, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue5, composerStartRestartGroup, 6);
            aVar2 = aVar;
            composer2 = composerStartRestartGroup;
            AndroidDialog_androidKt.Dialog(aVar2, new DialogProperties(false, true, null, false, false, 21, null), ComposableLambdaKt.rememberComposableLambda(-751930281, true, new c(aVar, focusRequester, mutableState, focusRequester3, focusRequester2, lVar), composerStartRestartGroup, 54), composer2, ((i12 >> 3) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new d(i10, 0, lVar, aVar2));
        }
    }

    private static final String AddRepoDialog$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v26, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v27 */
    public static final t0 AddRepoDialog$lambda$7(final r7.a aVar, FocusRequester focusRequester, MutableState mutableState, FocusRequester focusRequester2, FocusRequester focusRequester3, l lVar, Composer composer, int i10) {
        int i11;
        long textSecondary;
        int i12;
        Object obj;
        float f10;
        ?? r14;
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-751930281, i10, -1, "com.arflix.tv.ui.screens.plugin.AddRepoDialog.<anonymous> (PluginScreen.kt:492)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierImePadding = WindowInsetsPadding_androidKt.imePadding(SizeKt.fillMaxSize$default(companion, 0.0f, 1, null));
            boolean zChanged = composer.changed(aVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.plugin.PluginScreenKt$AddRepoDialog$2$1$1
                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        return m6273invokeZmokQxo(((KeyEvent) obj2).m4390unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m6273invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                            long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                            Key.Companion companion2 = Key.INSTANCE;
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), companion2.m4182getEscapeEK5gGoQ())) {
                                aVar.invoke();
                                return Boolean.TRUE;
                            }
                        }
                        return Boolean.FALSE;
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(modifierImePadding, (l) objRememberedValue);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment center = companion2.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnPreviewKeyEvent);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            float f11 = 16;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(520)), f11), ColorKt.getBackgroundElevated(), null, 2, null);
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composer, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
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
            p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            Modifier modifierM = com.arflix.tv.data.repository.g.m(24, SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), composer, -483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, arrangement.getTop(), composer, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM);
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
            p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyK, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            String strStringResource = StringResources_androidKt.stringResource(R.string.plugin_screen_add_repo_dialog_title, composer, 0);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getSectionTitle(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            com.arflix.tv.data.repository.g.p(f11, companion, composer, 6);
            String strAddRepoDialog$lambda$1 = AddRepoDialog$lambda$1(mutableState);
            Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), focusRequester);
            TextFieldColors textFieldColorsM1745colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m1745colors0hiis_0(ColorKt.getTextPrimary(), ColorKt.getTextPrimary(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, ColorKt.getPink(), Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.getPink(), ColorKt.getTextSecondary(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 54, 432, 27648, 0, 3072, 2122311676, 4095);
            Object objRememberedValue2 = composer.rememberedValue();
            Composer.Companion companion4 = Composer.INSTANCE;
            if (objRememberedValue2 == companion4.getEmpty()) {
                objRememberedValue2 = new m(mutableState, 11);
                composer.updateRememberedValue(objRememberedValue2);
            }
            OutlinedTextFieldKt.OutlinedTextField(strAddRepoDialog$lambda$1, (l<? super String, t0>) objRememberedValue2, modifierFocusRequester, false, false, (TextStyle) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) ComposableSingletons$PluginScreenKt.INSTANCE.getLambda$891478482$app_playRelease(), (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColorsM1745colors0hiis_0, composer, 12582960, 12582912, 0, 4063096);
            int i13 = 20;
            com.arflix.tv.data.repository.g.p(20, companion, composer, 6);
            int i14 = 12;
            float f12 = 12;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f12));
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            composer.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(companion2, horizontalOrVerticalM441spacedBy0680j_4, composer, 6, -1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU4 = a0.c.u(companion3, composerM2991constructorimpl4, measurePolicyC, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composer, qVarModifierMaterializerOf4, composer, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == companion4.getEmpty()) {
                i11 = 2;
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composer.updateRememberedValue(objRememberedValue3);
            } else {
                i11 = 2;
            }
            MutableState mutableState2 = (MutableState) objRememberedValue3;
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == companion4.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, i11, null);
                composer.updateRememberedValue(objRememberedValue4);
            }
            MutableState mutableState3 = (MutableState) objRememberedValue4;
            int i15 = i11;
            Modifier modifierFocusRequester2 = FocusRequesterModifierKt.focusRequester(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), focusRequester2);
            Object objRememberedValue5 = composer.rememberedValue();
            if (objRememberedValue5 == companion4.getEmpty()) {
                objRememberedValue5 = new m(mutableState2, i14);
                composer.updateRememberedValue(objRememberedValue5);
            }
            Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester2, (l) objRememberedValue5);
            float f13 = 8;
            Modifier modifierM179backgroundbw27NRU$default2 = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(modifierOnFocusChanged, f13), AddRepoDialog$lambda$7$1$0$0$1$1(mutableState2) ? Color.m3471copywmQWz5c$default(ColorKt.getBackgroundElevated(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null) : ColorKt.getBackgroundElevated(), null, 2, null);
            float fM5678constructorimpl = Dp.m5678constructorimpl(AddRepoDialog$lambda$7$1$0$0$1$1(mutableState2) ? i15 : 1);
            if (AddRepoDialog$lambda$7$1$0$0$1$1(mutableState2)) {
                textSecondary = Color.INSTANCE.m3509getWhite0d7_KjU();
                i12 = 14;
                obj = null;
                f10 = 0.8f;
            } else {
                textSecondary = ColorKt.getTextSecondary();
                i12 = 14;
                obj = null;
                f10 = 0.3f;
            }
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f13, modifierM179backgroundbw27NRU$default2, fM5678constructorimpl, Color.m3471copywmQWz5c$default(textSecondary, f10, 0.0f, 0.0f, 0.0f, i12, obj));
            boolean zChanged2 = composer.changed(aVar);
            Object objRememberedValue6 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue6 == companion4.getEmpty()) {
                objRememberedValue6 = new com.arflix.tv.ui.components.g(i13, aVar);
                composer.updateRememberedValue(objRememberedValue6);
            }
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue6, 7, null), 0.0f, Dp.m5678constructorimpl(f12), 1, null);
            Alignment center2 = companion2.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor5 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor5);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer);
            p pVarU5 = a0.c.u(companion3, composerM2991constructorimpl5, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl5, currentCompositionLocalMap5);
            if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
            }
            a0.c.y(composer, qVarModifierMaterializerOf5, composer, 0, 2058660585);
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.cancel, composer, 0);
            TextAlign.Companion companion5 = TextAlign.INSTANCE;
            TextKt.m6020Text4IGK_g(strStringResource2, null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion5.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, arflixTypography.getButton(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65018);
            androidx.compose.foundation.c.y(composer);
            Modifier modifierFocusRequester3 = FocusRequesterModifierKt.focusRequester(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), focusRequester3);
            Object objRememberedValue7 = composer.rememberedValue();
            if (objRememberedValue7 == companion4.getEmpty()) {
                objRememberedValue7 = new m(mutableState3, 13);
                composer.updateRememberedValue(objRememberedValue7);
            }
            Modifier modifierK2 = com.arflix.tv.data.repository.g.k(f13, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(FocusChangedModifierKt.onFocusChanged(modifierFocusRequester3, (l) objRememberedValue7), f13), AddRepoDialog$lambda$7$1$0$0$1$4(mutableState3) ? Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.35f, 0.0f, 0.0f, 0.0f, 14, null) : Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), Dp.m5678constructorimpl(AddRepoDialog$lambda$7$1$0$0$1$4(mutableState3) ? 2 : 1), AddRepoDialog$lambda$7$1$0$0$1$4(mutableState3) ? ColorKt.getPink() : Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null));
            boolean zChanged3 = composer.changed(lVar);
            Object objRememberedValue8 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue8 == companion4.getEmpty()) {
                r14 = 0;
                objRememberedValue8 = new a(null == true ? 1 : 0, mutableState, lVar);
                composer.updateRememberedValue(objRememberedValue8);
            } else {
                r14 = 0;
            }
            Modifier modifierM531paddingVpY3zN4$default2 = PaddingKt.m531paddingVpY3zN4$default(ClickableKt.m214clickableXHw0xAI$default(modifierK2, false, null, null, (r7.a) objRememberedValue8, 7, null), 0.0f, Dp.m5678constructorimpl(f12), 1, null);
            Alignment center3 = companion2.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy3 = BoxKt.rememberBoxMeasurePolicy(center3, r14, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer, r14);
            CompositionLocalMap currentCompositionLocalMap6 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor6 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default2);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor6);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl6 = Updater.m2991constructorimpl(composer);
            p pVarU6 = a0.c.u(companion3, composerM2991constructorimpl6, measurePolicyRememberBoxMeasurePolicy3, composerM2991constructorimpl6, currentCompositionLocalMap6);
            if (composerM2991constructorimpl6.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                a0.c.w(currentCompositeKeyHash6, composerM2991constructorimpl6, currentCompositeKeyHash6, pVarU6);
            }
            a0.c.y(composer, qVarModifierMaterializerOf6, composer, 0, 2058660585);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.add, composer, r14), null, ColorKt.getPink(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion5.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, arflixTypography.getButton(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65018);
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
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddRepoDialog$lambda$7$1$0$0$0$0(MutableState mutableState, String str) {
        mutableState.setValue(str);
        return t0.f22605a;
    }

    private static final boolean AddRepoDialog$lambda$7$1$0$0$1$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddRepoDialog$lambda$7$1$0$0$1$10$0(l lVar, MutableState mutableState) {
        lVar.invoke(AddRepoDialog$lambda$1(mutableState));
        return t0.f22605a;
    }

    private static final void AddRepoDialog$lambda$7$1$0$0$1$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AddRepoDialog$lambda$7$1$0$0$1$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AddRepoDialog$lambda$7$1$0$0$1$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddRepoDialog$lambda$7$1$0$0$1$6$0(MutableState mutableState, FocusState focusState) {
        AddRepoDialog$lambda$7$1$0$0$1$2(mutableState, focusState.isFocused());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddRepoDialog$lambda$7$1$0$0$1$7$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddRepoDialog$lambda$7$1$0$0$1$9$0(MutableState mutableState, FocusState focusState) {
        AddRepoDialog$lambda$7$1$0$0$1$5(mutableState, focusState.isFocused());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddRepoDialog$lambda$8(l lVar, r7.a aVar, int i10, Composer composer, int i11) {
        AddRepoDialog(lVar, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0144  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FocusableSettingsRow(final int r21, final int r22, final androidx.compose.ui.graphics.vector.ImageVector r23, final java.lang.String r24, java.lang.String r25, java.lang.String r26, final r7.a<x6.t0> r27, androidx.compose.ui.Modifier r28, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instruction units count: 351
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.plugin.PluginScreenKt.FocusableSettingsRow(int, int, androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, java.lang.String, java.lang.String, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FocusableSettingsRow$lambda$0$0(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FocusableSettingsRow$lambda$1(int i10, int i11, ImageVector imageVector, String str, String str2, String str3, r7.a aVar, Modifier modifier, int i12, int i13, Composer composer, int i14) {
        FocusableSettingsRow(i10, i11, imageVector, str, str2, str3, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), i13);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FocusableSettingsToggleRow(final int r18, final int r19, final java.lang.String r20, java.lang.String r21, final boolean r22, final r7.l<? super java.lang.Boolean, x6.t0> r23, androidx.compose.ui.Modifier r24, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.plugin.PluginScreenKt.FocusableSettingsToggleRow(int, int, java.lang.String, java.lang.String, boolean, r7.l, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FocusableSettingsToggleRow$lambda$0$0(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FocusableSettingsToggleRow$lambda$1(int i10, int i11, String str, String str2, boolean z, l lVar, Modifier modifier, int i12, int i13, Composer composer, int i14) {
        FocusableSettingsToggleRow(i10, i11, str, str2, z, lVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), i13);
        return t0.f22605a;
    }

    public static final void HideDialogSystemBars(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(412802551);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(412802551, i10, -1, "com.arflix.tv.ui.screens.plugin.HideDialogSystemBars (PluginScreen.kt:405)");
            }
            View view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            ViewParent parent = view.getParent();
            DialogWindowProvider dialogWindowProvider = parent instanceof DialogWindowProvider ? (DialogWindowProvider) parent : null;
            Window window = dialogWindowProvider != null ? dialogWindowProvider.getWindow() : null;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                Object systemService = context.getSystemService("uimode");
                UiModeManager uiModeManager = systemService instanceof UiModeManager ? (UiModeManager) systemService : null;
                objRememberedValue = Boolean.valueOf(uiModeManager != null && uiModeManager.getCurrentModeType() == 4);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            boolean zBooleanValue = ((Boolean) objRememberedValue).booleanValue();
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(window);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new PluginScreenKt$HideDialogSystemBars$1$1(window, zBooleanValue, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(window, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new j(i10, 5));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HideDialogSystemBars$lambda$2(int i10, Composer composer, int i11) {
        HideDialogSystemBars(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x05fc  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x06db  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x06e9  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x073a  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0772  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0799  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x07ab  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x07b6  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x07bd  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x07c5  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x07c7  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x081d  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0829  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0851  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x08d7  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0a0d  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0ac3  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0b0d  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x0b90  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0b94  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0baa  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0bc4  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0bea  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0bf8  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0c02  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0c09  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x0c0e  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0c15  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0c63  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0c6f  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0c73  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0c97  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x0d08  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x0d4b  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0d5a  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x0dc7  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x0dd8  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0de4  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0e5a  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0e70  */
    /* JADX WARN: Removed duplicated region for block: B:474:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0116  */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v44 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PluginScreen(com.arflix.tv.ui.screens.plugin.PluginViewModel r91, int r92, r7.l<? super java.lang.Integer, x6.t0> r93, r7.l<? super java.lang.Integer, x6.t0> r94, int r95, r7.a<x6.t0> r96, r7.l<? super java.lang.Boolean, x6.t0> r97, final r7.a<x6.t0> r98, r7.a<x6.t0> r99, androidx.compose.runtime.Composer r100, final int r101, final int r102) {
        /*
            Method dump skipped, instruction units count: 3711
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.plugin.PluginScreenKt.PluginScreen(com.arflix.tv.ui.screens.plugin.PluginViewModel, int, r7.l, r7.l, int, r7.a, r7.l, r7.a, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$0$0(int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$1$0(int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PluginScreen$lambda$10(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final PluginRepository PluginScreen$lambda$12(MutableState<PluginRepository> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$17$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$18$1(MutableState mutableState, ColumnScope columnScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1591738229, i10, -1, "com.arflix.tv.ui.screens.plugin.PluginScreen.<anonymous>.<anonymous> (PluginScreen.kt:142)");
            }
            ImageVector add = AddKt.getAdd(Icons.INSTANCE.getDefault());
            String strStringResource = StringResources_androidKt.stringResource(R.string.plugin_screen_add_repo, composer, 0);
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.plugin_screen_repo_url, composer, 0);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new com.arflix.tv.ui.screens.login.f(mutableState, 7);
                composer.updateRememberedValue(objRememberedValue);
            }
            SettingsRowsKt.m6184MobileSettingsRowUt8lOTo(add, null, 0L, strStringResource, strStringResource2, "", false, false, false, false, null, false, (r7.a) objRememberedValue, composer, 12779520, 432, 1862);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$18$1$0$0(MutableState mutableState) {
        PluginScreen$lambda$7(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final t0 PluginScreen$lambda$18$2(List list, MutableState mutableState, ColumnScope columnScope, Composer composer, int i10) {
        Composer composer2 = composer;
        if ((i10 & 17) == 16 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1992514192, i10, -1, "com.arflix.tv.ui.screens.plugin.PluginScreen.<anonymous>.<anonymous> (PluginScreen.kt:155)");
            }
            int i11 = 0;
            int i12 = 0;
            for (Object obj : list) {
                int i13 = i12 + 1;
                if (i12 < 0) {
                    t7.a.Q();
                    throw null;
                }
                PluginRepository pluginRepository = (PluginRepository) obj;
                ImageVector extension = ExtensionKt.getExtension(Icons.INSTANCE.getDefault());
                String name = pluginRepository.getName();
                String url = pluginRepository.getUrl();
                String strStringResource = StringResources_androidKt.stringResource(R.string.delete, composer2, i11);
                boolean z = i12 < t7.a.w(list) ? 1 : i11;
                boolean zChanged = composer2.changed(pluginRepository);
                Object objRememberedValue = composer2.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new com.arflix.tv.ui.screens.collections.e(pluginRepository, mutableState, 6);
                    composer2.updateRememberedValue(objRememberedValue);
                }
                SettingsRowsKt.m6184MobileSettingsRowUt8lOTo(extension, null, 0L, name, url, strStringResource, false, false, false, false, null, z, (r7.a) objRememberedValue, composer2, 12582912, 0, 1862);
                composer2 = composer;
                i12 = i13;
                i11 = i11;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$18$2$0$0$0(PluginRepository pluginRepository, MutableState mutableState) {
        mutableState.setValue(pluginRepository);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$18$3(List list, PluginViewModel pluginViewModel, ColumnScope columnScope, Composer composer, int i10) {
        Composer composer2 = composer;
        if ((i10 & 17) == 16 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(483348532, i10, -1, "com.arflix.tv.ui.screens.plugin.PluginScreen.<anonymous>.<anonymous> (PluginScreen.kt:170)");
            }
            boolean z = false;
            if (list.isEmpty()) {
                composer2.startReplaceGroup(-1589173199);
                ImageVector extension = ExtensionKt.getExtension(Icons.INSTANCE.getDefault());
                String strStringResource = StringResources_androidKt.stringResource(R.string.plugin_screen_no_scrapers, composer2, 0);
                Object objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new d0(8);
                    composer2.updateRememberedValue(objRememberedValue);
                }
                SettingsRowsKt.m6184MobileSettingsRowUt8lOTo(extension, null, 0L, strStringResource, null, "", false, false, false, false, null, false, (r7.a) objRememberedValue, composer2, 12779520, 432, 1878);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-1588781235);
                int i11 = 0;
                for (Object obj : list) {
                    int i12 = i11 + 1;
                    if (i11 < 0) {
                        t7.a.Q();
                        throw null;
                    }
                    ScraperInfo scraperInfo = (ScraperInfo) obj;
                    ImageVector extension2 = ExtensionKt.getExtension(Icons.INSTANCE.getDefault());
                    String name = scraperInfo.getName();
                    String id = scraperInfo.getId();
                    String str = scraperInfo.getEnabled() ? "On" : "Off";
                    boolean z5 = i11 < t7.a.w(list) ? true : z;
                    boolean zChangedInstance = composer2.changedInstance(pluginViewModel) | composer2.changed(scraperInfo);
                    Object objRememberedValue2 = composer2.rememberedValue();
                    if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new com.arflix.tv.ui.screens.collections.e(pluginViewModel, scraperInfo, 4);
                        composer2.updateRememberedValue(objRememberedValue2);
                    }
                    SettingsRowsKt.m6184MobileSettingsRowUt8lOTo(extension2, null, 0L, name, id, str, false, false, false, false, null, z5, (r7.a) objRememberedValue2, composer, 12582912, 0, 1862);
                    composer2 = composer;
                    i11 = i12;
                    z = z;
                }
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$18$3$1$0$0(PluginViewModel pluginViewModel, ScraperInfo scraperInfo) {
        pluginViewModel.onEvent(new PluginUiEvent.ToggleScraper(scraperInfo.getId(), !scraperInfo.getEnabled()));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$18$4(MutableState mutableState, ColumnScope columnScope, Composer composer, int i10) {
        Composer composer2;
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(928885843, i10, -1, "com.arflix.tv.ui.screens.plugin.PluginScreen.<anonymous>.<anonymous> (PluginScreen.kt:195)");
            }
            ImageVector delete = DeleteKt.getDelete(Icons.INSTANCE.getDefault());
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new com.arflix.tv.ui.screens.login.f(mutableState, 1);
                composer2 = composer;
                composer2.updateRememberedValue(objRememberedValue);
            } else {
                composer2 = composer;
            }
            SettingsRowsKt.m6184MobileSettingsRowUt8lOTo(delete, null, 0L, "Reset Plugins & Extensions", "Deletes all repositories, scrapers, and local data", "", false, false, false, false, null, false, (r7.a) objRememberedValue, composer2, 12807168, 432, 1862);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$18$4$0$0(MutableState mutableState) {
        PluginScreen$lambda$10(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$20$1$0(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$20$2$0(MutableState mutableState) {
        PluginScreen$lambda$7(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$20$4$0$0(PluginViewModel pluginViewModel, PluginRepository pluginRepository) {
        pluginViewModel.onEvent(new PluginUiEvent.RemoveRepository(pluginRepository.getId()));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$20$5$0$0(PluginViewModel pluginViewModel, ScraperInfo scraperInfo, boolean z) {
        pluginViewModel.onEvent(new PluginUiEvent.ToggleScraper(scraperInfo.getId(), z));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$20$6$0(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$20$7$0(MutableState mutableState) {
        PluginScreen$lambda$10(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$21$0(PluginViewModel pluginViewModel, MutableState mutableState, String str) {
        pluginViewModel.onEvent(new PluginUiEvent.AddRepository(str));
        PluginScreen$lambda$7(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$22$0(MutableState mutableState) {
        PluginScreen$lambda$7(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$23$0(PluginViewModel pluginViewModel, l lVar, MutableState mutableState) {
        pluginViewModel.onEvent(PluginUiEvent.ResetAllPlugins.INSTANCE);
        lVar.invoke(0);
        PluginScreen$lambda$10(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$24$0(MutableState mutableState) {
        PluginScreen$lambda$10(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$25$0$0(PluginViewModel pluginViewModel, PluginRepository pluginRepository, MutableState mutableState) {
        pluginViewModel.onEvent(new PluginUiEvent.RemoveRepository(pluginRepository.getId()));
        mutableState.setValue(null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$25$1$0(MutableState mutableState) {
        mutableState.setValue(null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$26(PluginViewModel pluginViewModel, int i10, l lVar, l lVar2, int i11, r7.a aVar, l lVar3, r7.a aVar2, r7.a aVar3, int i12, int i13, Composer composer, int i14) {
        PluginScreen(pluginViewModel, i10, lVar, lVar2, i11, aVar, lVar3, aVar2, aVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), i13);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PluginScreen$lambda$3$0(boolean z) {
        return t0.f22605a;
    }

    private static final PluginUiState PluginScreen$lambda$4(State<PluginUiState> state) {
        return state.getValue();
    }

    private static final boolean PluginScreen$lambda$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PluginScreen$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean PluginScreen$lambda$9(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    public static final void WarningDialog(final String str, final String str2, final String str3, String str4, final r7.a<t0> aVar, final r7.a<t0> aVar2, Composer composer, int i10) {
        int i11;
        String str5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2102178648);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(str3) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            str5 = str4;
            i11 |= composerStartRestartGroup.changed(str5) ? 2048 : 1024;
        } else {
            str5 = str4;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar2) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2102178648, i11, -1, "com.arflix.tv.ui.screens.plugin.WarningDialog (PluginScreen.kt:603)");
            }
            HideDialogSystemBars(composerStartRestartGroup, 0);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            final FocusRequester focusRequester = (FocusRequester) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            final FocusRequester focusRequester2 = (FocusRequester) objRememberedValue2;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new PluginScreenKt$WarningDialog$1$1(focusRequester, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.LaunchedEffect(t0.f22605a, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue3, composerStartRestartGroup, 6);
            final String str6 = str5;
            AndroidDialog_androidKt.Dialog(aVar2, new DialogProperties(false, true, null, false, false, 21, null), ComposableLambdaKt.rememberComposableLambda(94083455, true, new p() { // from class: com.arflix.tv.ui.screens.plugin.i
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return PluginScreenKt.WarningDialog$lambda$3(aVar2, str, str2, focusRequester, focusRequester2, aVar, str3, str6, (Composer) obj, iIntValue);
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i11 >> 15) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.components.i(str, str2, str3, str4, aVar, aVar2, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WarningDialog$lambda$3(final r7.a aVar, String str, String str2, FocusRequester focusRequester, FocusRequester focusRequester2, r7.a aVar2, String str3, String str4, Composer composer, int i10) {
        int i11;
        long textSecondary;
        int i12;
        Object obj;
        float f10;
        MutableState mutableState;
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(94083455, i10, -1, "com.arflix.tv.ui.screens.plugin.WarningDialog.<anonymous> (PluginScreen.kt:617)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion, 0.0f, 1, null);
            boolean zChanged = composer.changed(aVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.plugin.PluginScreenKt$WarningDialog$2$1$1
                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        return m6275invokeZmokQxo(((KeyEvent) obj2).m4390unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m6275invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                            long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                            Key.Companion companion2 = Key.INSTANCE;
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), companion2.m4182getEscapeEK5gGoQ())) {
                                aVar.invoke();
                                return Boolean.TRUE;
                            }
                        }
                        return Boolean.FALSE;
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(modifierFillMaxSize$default, (l) objRememberedValue);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment center = companion2.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnPreviewKeyEvent);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            float f11 = 24;
            Modifier modifierM = com.arflix.tv.data.repository.g.m(f11, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN)), 16), ColorKt.getBackgroundElevated(), null, 2, null), composer, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composer, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM);
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
            p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            Alignment.Horizontal centerHorizontally = companion2.getCenterHorizontally();
            composer.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(arrangement, centerHorizontally, composer, 48, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion);
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
            p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyF, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle sectionTitle = arflixTypography.getSectionTitle();
            Color.Companion companion4 = Color.INSTANCE;
            long jM3506getRed0d7_KjU = companion4.m3506getRed0d7_KjU();
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            TextAlign.Companion companion5 = TextAlign.INSTANCE;
            TextKt.m6020Text4IGK_g(str, modifierFillMaxWidth$default, jM3506getRed0d7_KjU, 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion5.m5587getStarte0LSkKk()), 0L, 0, false, 0, 0, null, sectionTitle, composer, 432, 0, 65016);
            float f12 = 12;
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f12)), composer, 6);
            TextKt.m6020Text4IGK_g(str2, SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion5.m5587getStarte0LSkKk()), 0L, 0, false, 0, 0, null, arflixTypography.getBody(), composer, 432, 0, 65016);
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f11)), composer, 6);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f12));
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            composer.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(companion2, horizontalOrVerticalM441spacedBy0680j_4, composer, 6, -1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default2);
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
            p pVarU4 = a0.c.u(companion3, composerM2991constructorimpl4, measurePolicyC, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composer, qVarModifierMaterializerOf4, composer, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Object objRememberedValue2 = composer.rememberedValue();
            Composer.Companion companion6 = Composer.INSTANCE;
            if (objRememberedValue2 == companion6.getEmpty()) {
                i11 = 2;
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composer.updateRememberedValue(objRememberedValue2);
            } else {
                i11 = 2;
            }
            MutableState mutableState2 = (MutableState) objRememberedValue2;
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == companion6.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, i11, null);
                composer.updateRememberedValue(objRememberedValue3);
            }
            MutableState mutableState3 = (MutableState) objRememberedValue3;
            int i13 = i11;
            Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), focusRequester);
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == companion6.getEmpty()) {
                objRememberedValue4 = new m(mutableState2, 10);
                composer.updateRememberedValue(objRememberedValue4);
            }
            Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (l) objRememberedValue4);
            float f13 = 8;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(modifierOnFocusChanged, f13), WarningDialog$lambda$3$1$0$0$0$1(mutableState2) ? Color.m3471copywmQWz5c$default(ColorKt.getBackgroundElevated(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null) : ColorKt.getBackgroundElevated(), null, 2, null);
            float fM5678constructorimpl = Dp.m5678constructorimpl(WarningDialog$lambda$3$1$0$0$0$1(mutableState2) ? i13 : 1);
            if (WarningDialog$lambda$3$1$0$0$0$1(mutableState2)) {
                textSecondary = companion4.m3509getWhite0d7_KjU();
                i12 = 14;
                obj = null;
                f10 = 0.8f;
            } else {
                textSecondary = ColorKt.getTextSecondary();
                i12 = 14;
                obj = null;
                f10 = 0.3f;
            }
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f13, modifierM179backgroundbw27NRU$default, fM5678constructorimpl, Color.m3471copywmQWz5c$default(textSecondary, f10, 0.0f, 0.0f, 0.0f, i12, obj));
            boolean zChanged2 = composer.changed(aVar);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue5 == companion6.getEmpty()) {
                objRememberedValue5 = new com.arflix.tv.ui.components.g(21, aVar);
                composer.updateRememberedValue(objRememberedValue5);
            }
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue5, 7, null), 0.0f, Dp.m5678constructorimpl(f12), 1, null);
            Alignment center2 = companion2.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor5 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor5);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer);
            p pVarU5 = a0.c.u(companion3, composerM2991constructorimpl5, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl5, currentCompositionLocalMap5);
            if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
            }
            a0.c.y(composer, qVarModifierMaterializerOf5, composer, 0, 2058660585);
            TextKt.m6020Text4IGK_g(str3, null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion5.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, arflixTypography.getButton(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65018);
            androidx.compose.foundation.c.y(composer);
            Modifier modifierFocusRequester2 = FocusRequesterModifierKt.focusRequester(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), focusRequester2);
            Object objRememberedValue6 = composer.rememberedValue();
            if (objRememberedValue6 == companion6.getEmpty()) {
                mutableState = mutableState3;
                objRememberedValue6 = new m(mutableState, 14);
                composer.updateRememberedValue(objRememberedValue6);
            } else {
                mutableState = mutableState3;
            }
            Modifier modifierK2 = com.arflix.tv.data.repository.g.k(f13, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(FocusChangedModifierKt.onFocusChanged(modifierFocusRequester2, (l) objRememberedValue6), f13), WarningDialog$lambda$3$1$0$0$0$4(mutableState) ? Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.35f, 0.0f, 0.0f, 0.0f, 14, null) : Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), Dp.m5678constructorimpl(WarningDialog$lambda$3$1$0$0$0$4(mutableState) ? i13 : 1), WarningDialog$lambda$3$1$0$0$0$4(mutableState) ? ColorKt.getPink() : Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null));
            boolean zChanged3 = composer.changed(aVar2);
            Object objRememberedValue7 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue7 == companion6.getEmpty()) {
                objRememberedValue7 = new com.arflix.tv.ui.components.g(23, aVar2);
                composer.updateRememberedValue(objRememberedValue7);
            }
            Modifier modifierM531paddingVpY3zN4$default2 = PaddingKt.m531paddingVpY3zN4$default(ClickableKt.m214clickableXHw0xAI$default(modifierK2, false, null, null, (r7.a) objRememberedValue7, 7, null), 0.0f, Dp.m5678constructorimpl(f12), 1, null);
            Alignment center3 = companion2.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy3 = BoxKt.rememberBoxMeasurePolicy(center3, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap6 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor6 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default2);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor6);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl6 = Updater.m2991constructorimpl(composer);
            p pVarU6 = a0.c.u(companion3, composerM2991constructorimpl6, measurePolicyRememberBoxMeasurePolicy3, composerM2991constructorimpl6, currentCompositionLocalMap6);
            if (composerM2991constructorimpl6.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                a0.c.w(currentCompositeKeyHash6, composerM2991constructorimpl6, currentCompositeKeyHash6, pVarU6);
            }
            a0.c.y(composer, qVarModifierMaterializerOf6, composer, 0, 2058660585);
            TextKt.m6020Text4IGK_g(str4, null, ColorKt.getPink(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion5.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, arflixTypography.getButton(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65018);
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
        }
        return t0.f22605a;
    }

    private static final boolean WarningDialog$lambda$3$1$0$0$0$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WarningDialog$lambda$3$1$0$0$0$10$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    private static final void WarningDialog$lambda$3$1$0$0$0$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean WarningDialog$lambda$3$1$0$0$0$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void WarningDialog$lambda$3$1$0$0$0$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WarningDialog$lambda$3$1$0$0$0$6$0(MutableState mutableState, FocusState focusState) {
        WarningDialog$lambda$3$1$0$0$0$2(mutableState, focusState.isFocused());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WarningDialog$lambda$3$1$0$0$0$7$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WarningDialog$lambda$3$1$0$0$0$9$0(MutableState mutableState, FocusState focusState) {
        WarningDialog$lambda$3$1$0$0$0$5(mutableState, focusState.isFocused());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WarningDialog$lambda$4(String str, String str2, String str3, String str4, r7.a aVar, r7.a aVar2, int i10, Composer composer, int i11) {
        WarningDialog(str, str2, str3, str4, aVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }
}
