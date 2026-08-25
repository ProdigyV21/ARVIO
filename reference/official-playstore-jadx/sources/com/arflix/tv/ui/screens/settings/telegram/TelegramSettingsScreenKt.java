package com.arflix.tv.ui.screens.settings.telegram;

import android.graphics.Bitmap;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ImageKt;
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
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldDefaults;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
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
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.repository.p0;
import com.arflix.tv.data.telegram.TelegramAuthState;
import com.arflix.tv.m;
import com.arflix.tv.ui.components.LoadingIndicatorKt;
import com.arflix.tv.ui.screens.profile.e0;
import com.arflix.tv.ui.screens.profile.f0;
import com.arflix.tv.ui.screens.search.t;
import com.arflix.tv.ui.screens.settings.f3;
import com.arflix.tv.ui.screens.settings.o1;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.text.o;
import kotlin.text.u;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u001a'\u0010\u0005\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001d\u0010\b\u001a\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0003¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\nH\u0003¢\u0006\u0004\b\f\u0010\r\u001a\u0017\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\nH\u0003¢\u0006\u0004\b\u000f\u0010\r\u001a!\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0018\u001a\u00020\u00012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0016H\u0003¢\u0006\u0004\b\u0018\u0010\u0019\u001a+\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00112\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0016H\u0003¢\u0006\u0004\b\u001b\u0010\u001c\u001a#\u0010\u001d\u001a\u00020\u00012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0016H\u0003¢\u0006\u0004\b\u001d\u0010\u0019\u001a;\u0010#\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0003¢\u0006\u0004\b#\u0010$\u001a%\u0010&\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\n2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0003¢\u0006\u0004\b&\u0010'\u001a%\u0010*\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\n2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0003¢\u0006\u0004\b*\u0010'\u001a+\u0010-\u001a\u00020\u00012\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0003¢\u0006\u0004\b-\u0010.\u001a\u0017\u00100\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u001fH\u0002¢\u0006\u0004\b0\u00101\u001a\u000f\u00103\u001a\u000202H\u0003¢\u0006\u0004\b3\u00104¨\u0006@²\u0006\f\u00106\u001a\u0002058\nX\u008a\u0084\u0002²\u0006\f\u0010 \u001a\u00020\u001f8\nX\u008a\u0084\u0002²\u0006\u000e\u00108\u001a\u0002078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00109\u001a\u00020\n8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010:\u001a\u0002078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010;\u001a\u0002078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010<\u001a\u00020\n8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010=\u001a\u00020\n8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010>\u001a\u0002078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010?\u001a\u00020\u00118\n@\nX\u008a\u008e\u0002"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onBack", "Lcom/arflix/tv/ui/screens/settings/telegram/TelegramSettingsViewModel;", "viewModel", "TelegramSettingsScreen", "(Lr7/a;Lcom/arflix/tv/ui/screens/settings/telegram/TelegramSettingsViewModel;Landroidx/compose/runtime/Composer;II)V", "onConnect", "IdleContent", "(Lr7/a;Landroidx/compose/runtime/Composer;I)V", "", "message", "LoadingContent", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "link", "QrContent", "content", "", ContentDisposition.Parameters.Size, "Landroid/graphics/Bitmap;", "generateQrBitmap", "(Ljava/lang/String;I)Landroid/graphics/Bitmap;", "Lkotlin/Function1;", "onSubmit", "PhoneContent", "(Lr7/l;Landroidx/compose/runtime/Composer;I)V", "codeLength", "CodeContent", "(ILr7/l;Landroidx/compose/runtime/Composer;I)V", "PasswordContent", "firstName", "", "cacheSizeBytes", "onDisconnect", "onClearCache", "ConnectedContent", "(Ljava/lang/String;JLr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "onRetry", "ErrorContent", "(Ljava/lang/String;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "label", "onClick", "ActionButton", "onConfirm", "onDismiss", "DisconnectConfirmDialog", "(Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "bytes", "formatCacheSize", "(J)Ljava/lang/String;", "Landroidx/compose/material3/TextFieldColors;", "inputColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "authState", "", "showDisconnectConfirm", "phone", "isSubmitting", "showValidation", "code", "password", "cacheFocused", "focusedButton", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TelegramSettingsScreenKt {
    private static final void ActionButton(String str, r7.a<t0> aVar, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1515015337);
        int i12 = 4;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1515015337, i11, -1, "com.arflix.tv.ui.screens.settings.telegram.ActionButton (TelegramSettingsScreen.kt:569)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null);
            float f10 = MediaError.DetailedErrorCode.GENERIC;
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, companion, jM3471copywmQWz5c$default), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null));
            boolean z = (i11 & 112) == 32;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new f0(i12, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue, 7, null), Dp.m5678constructorimpl(24), Dp.m5678constructorimpl(12));
            Alignment center = Alignment.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, ColorKt.getPink(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getLabel(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, TextUnitKt.getSp(0.8d), null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777085, null), composer2, (i11 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new a(str, aVar, i10, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ActionButton$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ActionButton$lambda$2(String str, r7.a aVar, int i10, Composer composer, int i11) {
        ActionButton(str, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void CodeContent(int i10, l<? super String, t0> lVar, Composer composer, int i11) {
        int i12;
        int i13;
        int i14;
        Composer composerStartRestartGroup = composer.startRestartGroup(-492378668);
        if ((i11 & 6) == 0) {
            i12 = i11 | (composerStartRestartGroup.changed(i10) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i14 = 0;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-492378668, i12, -1, "com.arflix.tv.ui.screens.settings.telegram.CodeContent (TelegramSettingsScreen.kt:362)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment.Horizontal centerHorizontally = companion3.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(arrangement, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion2, Dp.m5678constructorimpl(40)), composerStartRestartGroup, 6);
            float f10 = 10;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, companion2, Color.m3471copywmQWz5c$default(ColorKt.getSuccessGreen(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(ColorKt.getSuccessGreen(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(16), Dp.m5678constructorimpl(f10));
            Alignment.Vertical centerVertically = companion3.getCenterVertically();
            Arrangement.HorizontalOrVertical center = arrangement.getCenter();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyRowMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            int i15 = i12;
            TextKt.m6020Text4IGK_g("✓  ", null, ColorKt.getSuccessGreen(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(14), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, 390, 0, 65530);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_code_sent, composerStartRestartGroup, 0), null, ColorKt.getSuccessGreen(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            androidx.compose.foundation.c.y(composerStartRestartGroup);
            float f11 = 20;
            com.arflix.tv.data.repository.g.p(f11, companion2, composerStartRestartGroup, 6);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_code_title, composerStartRestartGroup, 0), null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCardTitle(), 0L, TextUnitKt.getSp(22), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            com.arflix.tv.data.repository.g.p(8, companion2, composerStartRestartGroup, 6);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_code_desc, new Object[]{Integer.valueOf(i10)}, composerStartRestartGroup, 0), PaddingKt.m531paddingVpY3zN4$default(companion2, Dp.m5678constructorimpl(32), 0.0f, 2, null), ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, 432, 0, 65016);
            com.arflix.tv.data.repository.g.p(24, companion2, composerStartRestartGroup, 6);
            String strCodeContent$lambda$1 = CodeContent$lambda$1(mutableState);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m5418getNumberPjHm6EE(), ImeAction.INSTANCE.m5368getDoneeUduSuo(), null, 19, null);
            int i16 = i15 & 14;
            int i17 = i15 & 112;
            boolean z = (i17 == 32) | (i16 == 4);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == companion.getEmpty()) {
                i13 = i10;
                objRememberedValue2 = new f3(i13, mutableState, lVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                i13 = i10;
            }
            KeyboardActions keyboardActions = new KeyboardActions((l) objRememberedValue2, null, null, null, null, null, 62, null);
            TextFieldColors textFieldColorsInputColors = inputColors(composerStartRestartGroup, 0);
            Modifier modifierFillMaxWidth = SizeKt.fillMaxWidth(companion2, 0.55f);
            boolean z5 = i16 == 4;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new p0(i13, mutableState, 2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            TextFieldKt.TextField(strCodeContent$lambda$1, (l<? super String, t0>) objRememberedValue3, modifierFillMaxWidth, false, false, (TextStyle) null, (p<? super Composer, ? super Integer, t0>) ComposableSingletons$TelegramSettingsScreenKt.INSTANCE.m6327getLambda$941610430$app_playRelease(), (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, false, (VisualTransformation) null, keyboardOptions, keyboardActions, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColorsInputColors, composerStartRestartGroup, 1573248, 12779520, 0, 3964856);
            composerStartRestartGroup = composerStartRestartGroup;
            com.arflix.tv.data.repository.g.p(f11, companion2, composerStartRestartGroup, 6);
            String upperCase = StringResources_androidKt.stringResource(R.string.confirm, composerStartRestartGroup, 0).toUpperCase(Locale.ROOT);
            boolean z10 = i17 == 32;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new com.arflix.tv.ui.screens.plugin.a(2, mutableState, lVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            i14 = 0;
            ActionButton(upperCase, (r7.a) objRememberedValue4, composerStartRestartGroup, 0);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f(i10, lVar, i11, i14));
        }
    }

    private static final String CodeContent$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CodeContent$lambda$3$1$0(int i10, l lVar, MutableState mutableState, KeyboardActionScope keyboardActionScope) {
        if (CodeContent$lambda$1(mutableState).length() == i10) {
            lVar.invoke(CodeContent$lambda$1(mutableState));
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CodeContent$lambda$3$2$0(int i10, MutableState mutableState, String str) {
        if (str.length() <= i10) {
            mutableState.setValue(str);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CodeContent$lambda$3$3$0(l lVar, MutableState mutableState) {
        if (!o.h0(CodeContent$lambda$1(mutableState))) {
            lVar.invoke(CodeContent$lambda$1(mutableState));
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CodeContent$lambda$4(int i10, l lVar, int i11, Composer composer, int i12) {
        CodeContent(i10, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x04b2  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x04b4  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x05a3  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x05af  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x05d9  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0608  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0614  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0618  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x063c  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x068f  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0695  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x071a  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x078f  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x079e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x03a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void ConnectedContent(final java.lang.String r111, final long r112, final r7.a<x6.t0> r114, final r7.a<x6.t0> r115, androidx.compose.runtime.Composer r116, final int r117) {
        /*
            Method dump skipped, instruction units count: 1974
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt.ConnectedContent(java.lang.String, long, r7.a, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ConnectedContent$lambda$0$0$1$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    private static final boolean ConnectedContent$lambda$0$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ConnectedContent$lambda$0$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ConnectedContent$lambda$0$4$0(long j10, r7.a aVar) {
        if (j10 > 0) {
            aVar.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ConnectedContent$lambda$0$5$0(MutableState mutableState, FocusState focusState) {
        ConnectedContent$lambda$0$3(mutableState, focusState.isFocused());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ConnectedContent$lambda$1(String str, long j10, r7.a aVar, r7.a aVar2, int i10, Composer composer, int i11) {
        ConnectedContent(str, j10, aVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x05a6  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x05db  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x05e2  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x05ea  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0672  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x067e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0682  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x06a6  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0757  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void DisconnectConfirmDialog(final r7.a<x6.t0> r78, final r7.a<x6.t0> r79, androidx.compose.runtime.Composer r80, int r81) {
        /*
            Method dump skipped, instruction units count: 1900
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt.DisconnectConfirmDialog(r7.a, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DisconnectConfirmDialog$lambda$5$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DisconnectConfirmDialog$lambda$6$2$0$0$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DisconnectConfirmDialog$lambda$6$2$0$2$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DisconnectConfirmDialog$lambda$7(r7.a aVar, r7.a aVar2, int i10, Composer composer, int i11) {
        DisconnectConfirmDialog(aVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void ErrorContent(String str, r7.a<t0> aVar, Composer composer, int i10) {
        int i11;
        r7.a<t0> aVar2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-914961632);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        int i12 = i11;
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            aVar2 = aVar;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-914961632, i12, -1, "com.arflix.tv.ui.screens.settings.telegram.ErrorContent (TelegramSettingsScreen.kt:551)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f10 = 40;
            com.arflix.tv.data.repository.g.p(f10, companion, composerStartRestartGroup, 6);
            IconKt.m1610Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(f10)), ColorKt.getPink(), composerStartRestartGroup, 3504, 0);
            com.arflix.tv.data.repository.g.p(12, companion, composerStartRestartGroup, 6);
            String strStringResource = StringResources_androidKt.stringResource(R.string.telegram_connection_failed, composerStartRestartGroup, 0);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCardTitle(), 0L, TextUnitKt.getSp(18), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(8)), composerStartRestartGroup, 6);
            TextKt.m6020Text4IGK_g(str, null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            composerStartRestartGroup = composerStartRestartGroup;
            com.arflix.tv.data.repository.g.p(24, companion, composerStartRestartGroup, 6);
            aVar2 = aVar;
            ActionButton(StringResources_androidKt.stringResource(R.string.telegram_try_again, composerStartRestartGroup, 0), aVar2, composerStartRestartGroup, i12 & 112);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new a(str, aVar2, i10, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ErrorContent$lambda$1(String str, r7.a aVar, int i10, Composer composer, int i11) {
        ErrorContent(str, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void IdleContent(r7.a<t0> aVar, Composer composer, int i10) {
        int i11;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2100811233);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(aVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i12 = 1;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2100811233, i11, -1, "com.arflix.tv.ui.screens.settings.telegram.IdleContent (TelegramSettingsScreen.kt:169)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Horizontal centerHorizontally = companion2.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            int i13 = i11;
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            com.arflix.tv.data.repository.g.p(40, companion, composerStartRestartGroup, 6);
            String strStringResource = StringResources_androidKt.stringResource(R.string.telegram_connect_title, composerStartRestartGroup, 0);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCardTitle(), 0L, TextUnitKt.getSp(22), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            float f10 = 10;
            com.arflix.tv.data.repository.g.p(f10, companion, composerStartRestartGroup, 6);
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.telegram_connect_desc, composerStartRestartGroup, 0);
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(14), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null);
            long textSecondary = ColorKt.getTextSecondary();
            TextAlign.Companion companion4 = TextAlign.INSTANCE;
            TextKt.m6020Text4IGK_g(strStringResource2, PaddingKt.m531paddingVpY3zN4$default(companion, Dp.m5678constructorimpl(32), 0.0f, 2, null), textSecondary, 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion4.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, textStyleM5205copyp1EtxEg$default, composerStartRestartGroup, 432, 0, 65016);
            float f11 = 24;
            com.arflix.tv.data.repository.g.p(f11, companion, composerStartRestartGroup, 6);
            Modifier modifierFillMaxWidth = SizeKt.fillMaxWidth(companion, 0.72f);
            Color.Companion companion5 = Color.INSTANCE;
            Modifier modifierH = a2.h(12, com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, modifierFillMaxWidth, Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.04f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(16), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierH);
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
            p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            i12 = 1;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_disclaimer, composerStartRestartGroup, 0), null, Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion4.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(11), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65018);
            composerStartRestartGroup = composerStartRestartGroup;
            com.arflix.tv.data.repository.g.r(composerStartRestartGroup, f11, companion, composerStartRestartGroup, 6);
            ActionButton(StringResources_androidKt.stringResource(R.string.connect, composerStartRestartGroup, 0).toUpperCase(Locale.ROOT), aVar, composerStartRestartGroup, (i13 << 3) & 112);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new e0(i10, i12, aVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 IdleContent$lambda$1(r7.a aVar, int i10, Composer composer, int i11) {
        IdleContent(aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void LoadingContent(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(341613176);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(341613176, i11, -1, "com.arflix.tv.ui.screens.settings.telegram.LoadingContent (TelegramSettingsScreen.kt:210)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(56)), composerStartRestartGroup, 6);
            LoadingIndicatorKt.m6162LoadingIndicator5_Ve2oQ(null, Dp.m5678constructorimpl(40), ColorKt.getPink(), Dp.m5678constructorimpl(3), composerStartRestartGroup, 3504, 1);
            com.arflix.tv.data.repository.g.p(16, companion, composerStartRestartGroup, 6);
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(14), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer2, (14 & i11) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.collections.b(str, i10, 5));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoadingContent$lambda$1(String str, int i10, Composer composer, int i11) {
        LoadingContent(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void PasswordContent(l<? super String, t0> lVar, Composer composer, int i10) {
        int i11;
        MutableState mutableState;
        l<? super String, t0> lVar2;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(188656837);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changedInstance(lVar) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            lVar2 = lVar;
            i12 = 0;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(188656837, i11, -1, "com.arflix.tv.ui.screens.settings.telegram.PasswordContent (TelegramSettingsScreen.kt:419)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            kotlin.jvm.internal.h hVar = null;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState2 = (MutableState) objRememberedValue;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            int i13 = 1;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            com.arflix.tv.data.repository.g.p(40, companion2, composerStartRestartGroup, 6);
            String strStringResource = StringResources_androidKt.stringResource(R.string.telegram_2fa_title, composerStartRestartGroup, 0);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            int i14 = i11;
            TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCardTitle(), 0L, TextUnitKt.getSp(20), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            com.arflix.tv.data.repository.g.p(8, companion2, composerStartRestartGroup, 6);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_2fa_desc, composerStartRestartGroup, 0), null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            com.arflix.tv.data.repository.g.p(24, companion2, composerStartRestartGroup, 6);
            String strPasswordContent$lambda$1 = PasswordContent$lambda$1(mutableState2);
            PasswordVisualTransformation passwordVisualTransformation = new PasswordVisualTransformation((char) 0, i13, hVar);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m5420getPasswordPjHm6EE(), ImeAction.INSTANCE.m5368getDoneeUduSuo(), null, 19, null);
            int i15 = i14 & 14;
            boolean z = i15 == 4;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == companion.getEmpty()) {
                mutableState = mutableState2;
                objRememberedValue2 = new b(0, mutableState, lVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                mutableState = mutableState2;
            }
            KeyboardActions keyboardActions = new KeyboardActions((l) objRememberedValue2, null, null, null, null, null, 62, null);
            TextFieldColors textFieldColorsInputColors = inputColors(composerStartRestartGroup, 0);
            Modifier modifierFillMaxWidth = SizeKt.fillMaxWidth(companion2, 0.55f);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new m(mutableState, 15);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MutableState mutableState3 = mutableState;
            TextFieldKt.TextField(strPasswordContent$lambda$1, (l<? super String, t0>) objRememberedValue3, modifierFillMaxWidth, false, false, (TextStyle) null, (p<? super Composer, ? super Integer, t0>) ComposableSingletons$TelegramSettingsScreenKt.INSTANCE.m6326getLambda$260574925$app_playRelease(), (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, false, (VisualTransformation) passwordVisualTransformation, keyboardOptions, keyboardActions, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColorsInputColors, composerStartRestartGroup, 1573296, 12779520, 0, 3948472);
            composerStartRestartGroup = composerStartRestartGroup;
            com.arflix.tv.data.repository.g.p(20, companion2, composerStartRestartGroup, 6);
            String upperCase = StringResources_androidKt.stringResource(R.string.confirm, composerStartRestartGroup, 0).toUpperCase(Locale.ROOT);
            boolean z5 = i15 == 4;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue4 == companion.getEmpty()) {
                lVar2 = lVar;
                objRememberedValue4 = new com.arflix.tv.ui.screens.plugin.a(i13, mutableState3, lVar2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                lVar2 = lVar;
            }
            i12 = 0;
            ActionButton(upperCase, (r7.a) objRememberedValue4, composerStartRestartGroup, 0);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new c(i10, i12, lVar2));
        }
    }

    private static final String PasswordContent$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PasswordContent$lambda$3$0$0(l lVar, MutableState mutableState, KeyboardActionScope keyboardActionScope) {
        lVar.invoke(PasswordContent$lambda$1(mutableState));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PasswordContent$lambda$3$1$0(MutableState mutableState, String str) {
        mutableState.setValue(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PasswordContent$lambda$3$2$0(l lVar, MutableState mutableState) {
        lVar.invoke(PasswordContent$lambda$1(mutableState));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PasswordContent$lambda$4(l lVar, int i10, Composer composer, int i11) {
        PasswordContent(lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void PhoneContent(l<? super String, t0> lVar, Composer composer, int i10) {
        int i11;
        boolean z;
        Object o1Var;
        float f10;
        Modifier.Companion companion;
        MutableState mutableState;
        MutableState mutableState2;
        MutableState mutableState3;
        int i12;
        Object telegramSettingsScreenKt$PhoneContent$1$4$1;
        int i13;
        l<? super String, t0> lVar2 = lVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(1862378876);
        int i14 = (i10 & 6) == 0 ? (composerStartRestartGroup.changedInstance(lVar2) ? 4 : 2) | i10 : i10;
        if ((i14 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1862378876, i14, -1, "com.arflix.tv.ui.screens.settings.telegram.PhoneContent (TelegramSettingsScreen.kt:287)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue == companion2.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("+", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState4 = (MutableState) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion2.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableState mutableState5 = (MutableState) objRememberedValue2;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion2.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MutableState mutableState6 = (MutableState) objRememberedValue3;
            String strPhoneContent$lambda$1 = PhoneContent$lambda$1(mutableState4);
            StringBuilder sb2 = new StringBuilder();
            int length = strPhoneContent$lambda$1.length();
            for (int i15 = 0; i15 < length; i15++) {
                char cCharAt = strPhoneContent$lambda$1.charAt(i15);
                if (Character.isDigit(cCharAt)) {
                    sb2.append(cCharAt);
                }
            }
            String string = sb2.toString();
            if (!u.P(PhoneContent$lambda$1(mutableState4), "+", false) || string.length() < 7) {
                i11 = i14;
                z = false;
            } else {
                i11 = i14;
                z = true;
            }
            boolean z5 = PhoneContent$lambda$7(mutableState6) && !z;
            Modifier.Companion companion3 = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion3, 0.0f, 1, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            com.arflix.tv.data.repository.g.p(40, companion3, composerStartRestartGroup, 6);
            String strStringResource = StringResources_androidKt.stringResource(R.string.telegram_phone_title, composerStartRestartGroup, 0);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            boolean z10 = z5;
            TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCardTitle(), 0L, TextUnitKt.getSp(22), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            float f11 = 8;
            com.arflix.tv.data.repository.g.p(f11, companion3, composerStartRestartGroup, 6);
            float f12 = 32;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_phone_desc, composerStartRestartGroup, 0), PaddingKt.m531paddingVpY3zN4$default(companion3, Dp.m5678constructorimpl(f12), 0.0f, 2, null), ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, 432, 0, 65016);
            com.arflix.tv.data.repository.g.p(24, companion3, composerStartRestartGroup, 6);
            String strPhoneContent$lambda$12 = PhoneContent$lambda$1(mutableState4);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m5421getPhonePjHm6EE(), ImeAction.INSTANCE.m5368getDoneeUduSuo(), null, 19, null);
            int i16 = i11 & 14;
            boolean zChanged = composerStartRestartGroup.changed(z) | (i16 == 4);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                f10 = f11;
                companion = companion3;
                mutableState = mutableState6;
                mutableState2 = mutableState5;
                mutableState3 = mutableState4;
                i12 = 0;
                o1Var = new o1(z, lVar, mutableState, mutableState2, mutableState3);
                composerStartRestartGroup.updateRememberedValue(o1Var);
            } else {
                f10 = f11;
                mutableState = mutableState6;
                o1Var = objRememberedValue4;
                mutableState3 = mutableState4;
                i12 = 0;
                companion = companion3;
                mutableState2 = mutableState5;
            }
            KeyboardActions keyboardActions = new KeyboardActions((l) o1Var, null, null, null, null, null, 62, null);
            TextFieldColors textFieldColorsInputColors = inputColors(composerStartRestartGroup, i12);
            Modifier modifierFillMaxWidth = SizeKt.fillMaxWidth(companion, 0.75f);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion5 = Composer.INSTANCE;
            if (objRememberedValue5 == companion5.getEmpty()) {
                objRememberedValue5 = new m(mutableState3, 17);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            ComposableSingletons$TelegramSettingsScreenKt composableSingletons$TelegramSettingsScreenKt = ComposableSingletons$TelegramSettingsScreenKt.INSTANCE;
            float f13 = f10;
            Modifier.Companion companion6 = companion;
            MutableState mutableState7 = mutableState;
            MutableState mutableState8 = mutableState2;
            TextFieldKt.TextField(strPhoneContent$lambda$12, (l<? super String, t0>) objRememberedValue5, modifierFillMaxWidth, false, false, (TextStyle) null, (p<? super Composer, ? super Integer, t0>) composableSingletons$TelegramSettingsScreenKt.m6325getLambda$1398904882$app_playRelease(), (p<? super Composer, ? super Integer, t0>) composableSingletons$TelegramSettingsScreenKt.getLambda$1913069037$app_playRelease(), (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) null, (p<? super Composer, ? super Integer, t0>) ComposableLambdaKt.rememberComposableLambda(-1635224613, true, new t(z10, 1), composerStartRestartGroup, 54), z10, (VisualTransformation) null, keyboardOptions, keyboardActions, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColorsInputColors, composerStartRestartGroup, 14156208, 12779904, 0, 3952440);
            composerStartRestartGroup = composerStartRestartGroup;
            com.arflix.tv.data.repository.g.p(20, companion6, composerStartRestartGroup, 6);
            if (PhoneContent$lambda$4(mutableState8)) {
                composerStartRestartGroup.startReplaceGroup(760388171);
                LoadingIndicatorKt.m6162LoadingIndicator5_Ve2oQ(null, Dp.m5678constructorimpl(f12), ColorKt.getPink(), Dp.m5678constructorimpl((float) 2.5d), composerStartRestartGroup, 3504, 1);
                com.arflix.tv.data.repository.g.p(f13, companion6, composerStartRestartGroup, 6);
                TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_sending_code, composerStartRestartGroup, 0), null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
                lVar2 = lVar;
            } else {
                composerStartRestartGroup.startReplaceGroup(760740672);
                String strStringResource2 = StringResources_androidKt.stringResource(R.string.telegram_send_code, composerStartRestartGroup, 0);
                boolean zChanged2 = composerStartRestartGroup.changed(z) | (i16 == 4);
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue6 == companion5.getEmpty()) {
                    lVar2 = lVar;
                    i13 = 0;
                    telegramSettingsScreenKt$PhoneContent$1$4$1 = new TelegramSettingsScreenKt$PhoneContent$1$4$1(z, lVar2, mutableState7, mutableState8, mutableState3);
                    composerStartRestartGroup.updateRememberedValue(telegramSettingsScreenKt$PhoneContent$1$4$1);
                } else {
                    telegramSettingsScreenKt$PhoneContent$1$4$1 = objRememberedValue6;
                    i13 = 0;
                    lVar2 = lVar;
                }
                ActionButton(strStringResource2, (r7.a) ((kotlin.reflect.g) telegramSettingsScreenKt$PhoneContent$1$4$1), composerStartRestartGroup, i13);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new c(i10, 1, lVar2));
        }
    }

    private static final String PhoneContent$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PhoneContent$lambda$10$0$0(boolean z, l lVar, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, KeyboardActionScope keyboardActionScope) {
        PhoneContent$trySubmit(z, lVar, mutableState, mutableState2, mutableState3);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PhoneContent$lambda$10$1$0(MutableState mutableState, String str) {
        mutableState.setValue(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PhoneContent$lambda$10$2(boolean z, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1635224613, i10, -1, "com.arflix.tv.ui.screens.settings.telegram.PhoneContent.<anonymous>.<anonymous> (TelegramSettingsScreen.kt:329)");
            }
            if (z) {
                composer.startReplaceGroup(-1457338658);
                TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_phone_error, composer, 0), null, ColorKt.getPink(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 131066);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(-1457227647);
                TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_phone_format, composer, 0), null, Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 131066);
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PhoneContent$lambda$11(l lVar, int i10, Composer composer, int i11) {
        PhoneContent(lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final boolean PhoneContent$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PhoneContent$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean PhoneContent$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PhoneContent$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PhoneContent$trySubmit(boolean z, l<? super String, t0> lVar, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3) {
        PhoneContent$lambda$8(mutableState, true);
        if (!z || PhoneContent$lambda$4(mutableState2)) {
            return;
        }
        PhoneContent$lambda$5(mutableState2, true);
        lVar.invoke(PhoneContent$lambda$1(mutableState3));
    }

    private static final void QrContent(String str, Composer composer, int i10) throws Exception {
        int i11;
        int i12;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-692654057);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i12 = 6;
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-692654057, i11, -1, "com.arflix.tv.ui.screens.settings.telegram.QrContent (TelegramSettingsScreen.kt:224)");
            }
            boolean z = (i11 & 14) == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = generateQrBitmap(str, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Bitmap bitmap = (Bitmap) objRememberedValue;
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Horizontal centerHorizontally = companion2.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f10 = 24;
            com.arflix.tv.data.repository.g.p(f10, companion, composerStartRestartGroup, 6);
            String strStringResource = StringResources_androidKt.stringResource(R.string.telegram_scan_title, composerStartRestartGroup, 0);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            i12 = 6;
            TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCardTitle(), 0L, TextUnitKt.getSp(20), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            com.arflix.tv.data.repository.g.p(6, companion, composerStartRestartGroup, 6);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_scan_instructions, composerStartRestartGroup, 0), SizeKt.fillMaxWidth(companion, 0.6f), ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, 432, 0, 65016);
            com.arflix.tv.data.repository.g.p(f10, companion, composerStartRestartGroup, 6);
            if (bitmap != null) {
                composerStartRestartGroup.startReplaceGroup(279684122);
                float f11 = 12;
                Modifier modifierM = com.arflix.tv.data.repository.g.m(f11, com.arflix.tv.data.repository.g.l(f11, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(200)), Color.INSTANCE.m3509getWhite0d7_KjU()), composerStartRestartGroup, 733328855);
                MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM);
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
                p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ImageKt.m238Image5hnEew(AndroidImageBitmap_androidKt.asImageBitmap(bitmap), StringResources_androidKt.stringResource(R.string.telegram_qr_code_desc, composerStartRestartGroup, 0), SizeKt.fillMaxSize$default(companion, 0.0f, 1, null), null, null, 0.0f, null, 0, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 248);
                com.arflix.tv.data.repository.g.q(composerStartRestartGroup);
            } else {
                composerStartRestartGroup.startReplaceGroup(280162421);
                LoadingIndicatorKt.m6162LoadingIndicator5_Ve2oQ(null, Dp.m5678constructorimpl(40), ColorKt.getPink(), Dp.m5678constructorimpl(3), composerStartRestartGroup, 3504, 1);
                composerStartRestartGroup.endReplaceGroup();
            }
            com.arflix.tv.data.repository.g.p(16, companion, composerStartRestartGroup, 6);
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.telegram_qr_expires, composerStartRestartGroup, 0), null, Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(11), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer2, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.collections.b(str, i10, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 QrContent$lambda$2(String str, int i10, Composer composer, int i11) throws Exception {
        QrContent(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x06a4  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x06f6  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0705  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x024a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TelegramSettingsScreen(r7.a<x6.t0> r42, com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel r43, androidx.compose.runtime.Composer r44, int r45, int r46) {
        /*
            Method dump skipped, instruction units count: 1836
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt.TelegramSettingsScreen(r7.a, com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final TelegramAuthState TelegramSettingsScreen$lambda$0(State<? extends TelegramAuthState> state) {
        return state.getValue();
    }

    private static final long TelegramSettingsScreen$lambda$1(State<Long> state) {
        return state.getValue().longValue();
    }

    private static final boolean TelegramSettingsScreen$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void TelegramSettingsScreen$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$0$0$0$0$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$0$0$1$0(TelegramSettingsViewModel telegramSettingsViewModel) {
        telegramSettingsViewModel.startAuth();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$0$0$2$0(TelegramSettingsViewModel telegramSettingsViewModel, String str) {
        telegramSettingsViewModel.submitPhone(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$0$0$4$0(TelegramSettingsViewModel telegramSettingsViewModel, String str) {
        telegramSettingsViewModel.submitCode(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$0$0$5$0(TelegramSettingsViewModel telegramSettingsViewModel, String str) {
        telegramSettingsViewModel.submitPassword(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$0$0$6$0(MutableState mutableState) {
        TelegramSettingsScreen$lambda$4(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$0$0$7$0(TelegramSettingsViewModel telegramSettingsViewModel) {
        telegramSettingsViewModel.clearCache();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$0$0$8$0(TelegramSettingsViewModel telegramSettingsViewModel) {
        telegramSettingsViewModel.startAuth();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$1$0(TelegramSettingsViewModel telegramSettingsViewModel, MutableState mutableState) {
        TelegramSettingsScreen$lambda$4(mutableState, false);
        telegramSettingsViewModel.disconnect();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$5$2$0(MutableState mutableState) {
        TelegramSettingsScreen$lambda$4(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TelegramSettingsScreen$lambda$6(r7.a aVar, TelegramSettingsViewModel telegramSettingsViewModel, int i10, int i11, Composer composer, int i12) {
        TelegramSettingsScreen(aVar, telegramSettingsViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    private static final String formatCacheSize(long j10) {
        if (j10 <= 0) {
            return "Empty";
        }
        if (j10 >= C.NANOS_PER_SECOND) {
            return String.format("%.1f GB", Arrays.copyOf(new Object[]{Double.valueOf(j10 / 1.0E9d)}, 1));
        }
        if (j10 >= 1000000) {
            return String.format("%.1f MB", Arrays.copyOf(new Object[]{Double.valueOf(j10 / 1000000.0d)}, 1));
        }
        if (j10 >= 1000) {
            return String.format("%.0f KB", Arrays.copyOf(new Object[]{Double.valueOf(j10 / 1000.0d)}, 1));
        }
        return j10 + " B";
    }

    private static final Bitmap generateQrBitmap(String str, int i10) throws Exception {
        try {
            b6.b bVarL = qb.l.l(i10, i10, str);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i10, i10, Bitmap.Config.RGB_565);
            for (int i11 = 0; i11 < i10; i11++) {
                for (int i12 = 0; i12 < i10; i12++) {
                    bitmapCreateBitmap.setPixel(i11, i12, bVarL.a(i11, i12) ? -16777216 : -1);
                }
            }
            return bitmapCreateBitmap;
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            return null;
        }
    }

    private static final TextFieldColors inputColors(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-193999831, i10, -1, "com.arflix.tv.ui.screens.settings.telegram.inputColors (TelegramSettingsScreen.kt:701)");
        }
        TextFieldColors textFieldColorsM2123colors0hiis_0 = TextFieldDefaults.INSTANCE.m2123colors0hiis_0(ColorKt.getTextPrimary(), ColorKt.getTextPrimary(), 0L, 0L, ColorKt.getBackgroundElevated(), ColorKt.getBackgroundElevated(), 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.getPink(), ColorKt.getTextSecondary(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 221238, 0, 27648, 0, 3072, 2122317772, 4095);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return textFieldColorsM2123colors0hiis_0;
    }
}
