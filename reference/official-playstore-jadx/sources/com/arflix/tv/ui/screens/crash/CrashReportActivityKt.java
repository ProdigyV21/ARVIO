package com.arflix.tv.ui.screens.crash;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.RendererCapabilities;
import com.arflix.tv.e;
import com.arflix.tv.m;
import com.arflix.tv.ui.components.QrCodeImageKt;
import com.arflix.tv.util.DeviceType;
import com.arflix.tv.util.DeviceTypeKt;
import f4.f;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import ka.k0;
import kotlin.Metadata;
import kotlin.text.o;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u001aK\u0010\n\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\u000e²\u0006\u000e\u0010\r\u001a\u00020\f8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "crashId", "crashMsg", "", "crashTime", "crashVersion", "Lkotlin/Function0;", "Lx6/t0;", "onRestartApp", "onClose", "CrashReportScreen", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "", "isRestartFocused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CrashReportActivityKt {
    public static final void CrashReportScreen(final String str, final String str2, final long j10, final String str3, final r7.a<t0> aVar, final r7.a<t0> aVar2, Composer composer, final int i10) {
        int i11;
        char c10;
        MutableState mutableState;
        int i12;
        RowScopeInstance rowScopeInstance;
        Composer composer2;
        MutableState mutableState2;
        int i13;
        Composer composer3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-294381357);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(str3) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer3 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-294381357, i11, -1, "com.arflix.tv.ui.screens.crash.CrashReportScreen (CrashReportActivity.kt:87)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            boolean z = DeviceTypeKt.detectDeviceType(context) == DeviceType.TV || !DeviceTypeKt.deviceHasTouchScreen(context);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = androidx.compose.foundation.c.h(composerStartRestartGroup);
            }
            FocusRequester focusRequester = (FocusRequester) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                c10 = 2;
                objRememberedValue2 = mutableStateMutableStateOf$default;
            } else {
                c10 = 2;
            }
            MutableState mutableState3 = (MutableState) objRememberedValue2;
            Boolean boolValueOf = Boolean.valueOf(z);
            boolean zChanged = composerStartRestartGroup.changed(z);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new CrashReportActivityKt$CrashReportScreen$1$1(z, focusRequester, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.LaunchedEffect(boolValueOf, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue3, composerStartRestartGroup, 0);
            int i14 = i11 & 896;
            boolean z5 = i14 == 256;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j10));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            String str4 = (String) objRememberedValue4;
            int i15 = i11 & 14;
            boolean z10 = i15 == 4;
            boolean z11 = z;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = str.equals("N/A") ? "N/A" : "https://sentry.io/issues/?query=id%3A".concat(str);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            String str5 = (String) objRememberedValue5;
            int i16 = i11 & 112;
            int i17 = i11 & 7168;
            int i18 = i11;
            boolean zChanged2 = (i17 == 2048) | (i16 == 32) | (i15 == 4) | composerStartRestartGroup.changed(str5) | composerStartRestartGroup.changed(str4);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue6 == companion.getEmpty()) {
                mutableState = mutableState3;
                StringBuilder sbR = a2.r("\n        **🚨 ARVIO Crash Report**\n        **Crash ID:** `", str, "`\n        **Sentry Link:** ", str5, "\n        **Version:** ");
                y.a.i(sbR, str3, "\n        **Time:** ", str4, "\n        **Error:** ");
                sbR.append(str2);
                sbR.append("\n        ");
                objRememberedValue6 = f.y(sbR.toString());
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            } else {
                mutableState = mutableState3;
            }
            String str6 = (String) objRememberedValue6;
            boolean z12 = (i14 == 256) | (i15 == 4) | (i17 == 2048) | (i16 == 32);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue7 == companion.getEmpty()) {
                String strEncode = Uri.encode(o.I0(300, str2));
                StringBuilder sbR2 = a2.r("https://arvio.tv/report?id=", str, "&v=", Uri.encode(str3), "&err=");
                sbR2.append(strEncode);
                sbR2.append("&t=");
                sbR2.append(j10);
                objRememberedValue7 = sbR2.toString();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            String str7 = (String) objRememberedValue7;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion2, 0.0f, 1, null), ColorKt.Color(4279177493L), null, 2, null), Dp.m5678constructorimpl(24));
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment center = companion3.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.m585widthInVpY3zN4$default(companion2, 0.0f, Dp.m5678constructorimpl(z11 ? 760 : 480), 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null);
            Alignment.Horizontal centerHorizontally = companion3.getCenterHorizontally();
            Arrangement arrangement = Arrangement.INSTANCE;
            float f10 = 16;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(androidx.compose.foundation.c.g(f10, arrangement, composerStartRestartGroup, -483455358), centerHorizontally, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierVerticalScroll$default);
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
            p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyColumnMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Color.Companion companion5 = Color.INSTANCE;
            long jM3509getWhite0d7_KjU = companion5.m3509getWhite0d7_KjU();
            long sp = TextUnitKt.getSp(z11 ? 26 : 22);
            FontWeight.Companion companion6 = FontWeight.INSTANCE;
            FontWeight bold = companion6.getBold();
            TextAlign.Companion companion7 = TextAlign.INSTANCE;
            TextKt.m2153Text4IGK_g("⚠️ ARVIO Encountered an Error", (Modifier) null, jM3509getWhite0d7_KjU, sp, (FontStyle) null, bold, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5575boximpl(companion7.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composerStartRestartGroup, 196998, 0, 130514);
            TextKt.m2153Text4IGK_g(z11 ? "Scan the QR code below with your phone camera to automatically copy the crash report & open our Discord bug channel." : "We apologize for the interruption. You can report this crash directly to our Discord channel to help us fix it.", (Modifier) null, ColorKt.Color(4288718514L), TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5575boximpl(companion7.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composerStartRestartGroup, 3456, 0, 130546);
            Composer composer4 = composerStartRestartGroup;
            if (z11) {
                composer4.startReplaceGroup(-116004614);
                i12 = 54;
                SurfaceKt.m2004SurfaceT9BRK9s(PaddingKt.m531paddingVpY3zN4$default(companion2, 0.0f, Dp.m5678constructorimpl(8), 1, null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)), companion5.m3509getWhite0d7_KjU(), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1632147963, true, new c(str7, 0), composer4, 54), composer4, 12583302, MenuKt.InTransitionDuration);
                TextKt.m2153Text4IGK_g("Scan to open arvio.tv — 1 tap to copy report & jump into Discord.", (Modifier) null, ColorKt.Color(4278251728L), TextUnitKt.getSp(14), (FontStyle) null, companion6.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5575boximpl(companion7.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer4, 200070, 0, 130514);
                composer4 = composer4;
                composer4.endReplaceGroup();
            } else {
                i12 = 54;
                composer4.startReplaceGroup(-115119533);
                composer4.endReplaceGroup();
            }
            float f11 = 12;
            Composer composer5 = composer4;
            SurfaceKt.m2004SurfaceT9BRK9s(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11)), ColorKt.Color(4279770148L), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-905254166, true, new e(str, str4, str3, str2, 2), composer4, i12), composer5, 12583302, MenuKt.InTransitionDuration);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null);
            float f12 = 8;
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(modifierFillMaxWidth$default, 0.0f, Dp.m5678constructorimpl(f12), 0.0f, 0.0f, 13, null);
            Arrangement.Horizontal horizontalM442spacedByD5KLDUw = arrangement.m442spacedByD5KLDUw(Dp.m5678constructorimpl(f11), companion3.getCenterHorizontally());
            composer5.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyI = androidx.compose.foundation.c.i(companion3, horizontalM442spacedByD5KLDUw, composer5, 6, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer5.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
            if (composer5.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer5.startReusableNode();
            if (composer5.getInserting()) {
                composer5.createNode(constructor3);
            } else {
                composer5.useNode();
            }
            Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer5);
            p pVarU3 = a0.c.u(companion4, composerM2991constructorimpl3, measurePolicyI, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer5, qVarModifierMaterializerOf3, composer5, 0, 2058660585);
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            if (z11) {
                rowScopeInstance = rowScopeInstance2;
                composer5.startReplaceGroup(-369209841);
                composer5.endReplaceGroup();
                composer2 = composer5;
            } else {
                composer5.startReplaceGroup(-370305939);
                ButtonColors buttonColorsM1266buttonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1266buttonColorsro_MJ88(ColorKt.Color(4283983346L), 0L, 0L, 0L, composer5, (ButtonDefaults.$stable << 12) | 6, 14);
                RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f12));
                Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance2, companion2, 1.0f, false, 2, null);
                rowScopeInstance = rowScopeInstance2;
                boolean zChangedInstance = composer5.changedInstance(context) | composer5.changed(str6);
                Object objRememberedValue8 = composer5.rememberedValue();
                if (zChangedInstance || objRememberedValue8 == companion.getEmpty()) {
                    objRememberedValue8 = new com.arflix.tv.ui.screens.collections.e(context, str6, 3);
                    composer5.updateRememberedValue(objRememberedValue8);
                }
                composer2 = composer5;
                ButtonKt.Button((r7.a) objRememberedValue8, modifierA, false, roundedCornerShapeM798RoundedCornerShape0680j_4, buttonColorsM1266buttonColorsro_MJ88, null, null, null, null, ComposableSingletons$CrashReportActivityKt.INSTANCE.m6204getLambda$138077762$app_playRelease(), composer2, 805306368, 484);
                composer2.endReplaceGroup();
            }
            Composer composer6 = composer2;
            ButtonColors buttonColorsM1266buttonColorsro_MJ882 = ButtonDefaults.INSTANCE.m1266buttonColorsro_MJ88(CrashReportScreen$lambda$2(mutableState) ? companion5.m3509getWhite0d7_KjU() : ColorKt.Color(4278251728L), 0L, 0L, 0L, composer6, ButtonDefaults.$stable << 12, 14);
            RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_42 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f12));
            Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion2, z11 ? 0.6f : 1.0f, false, 2, null), focusRequester);
            Object objRememberedValue9 = composer6.rememberedValue();
            if (objRememberedValue9 == companion.getEmpty()) {
                mutableState2 = mutableState;
                i13 = 2;
                objRememberedValue9 = new m(mutableState2, i13);
                composer6.updateRememberedValue(objRememberedValue9);
            } else {
                mutableState2 = mutableState;
                i13 = 2;
            }
            composer3 = composer6;
            ButtonKt.Button(aVar, FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (l) objRememberedValue9).then(CrashReportScreen$lambda$2(mutableState2) ? BorderKt.m191borderxT4_qwU(companion2, Dp.m5678constructorimpl(i13), ColorKt.Color(4278251728L), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f12))) : companion2), false, roundedCornerShapeM798RoundedCornerShape0680j_42, buttonColorsM1266buttonColorsro_MJ882, null, null, null, null, ComposableSingletons$CrashReportActivityKt.INSTANCE.getLambda$694758435$app_playRelease(), composer3, ((i18 >> 12) & 14) | 805306368, 484);
            composer3.endReplaceableGroup();
            composer3.endNode();
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            composer3.endNode();
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            composer3.endNode();
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p() { // from class: com.arflix.tv.ui.screens.crash.d
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return CrashReportActivityKt.CrashReportScreen$lambda$10(str, str2, j10, str3, aVar, aVar2, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CrashReportScreen$lambda$10(String str, String str2, long j10, String str3, r7.a aVar, r7.a aVar2, int i10, Composer composer, int i11) {
        CrashReportScreen(str, str2, j10, str3, aVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final boolean CrashReportScreen$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CrashReportScreen$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CrashReportScreen$lambda$9$0$0(String str, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1632147963, i10, -1, "com.arflix.tv.ui.screens.crash.CrashReportScreen.<anonymous>.<anonymous>.<anonymous> (CrashReportActivity.kt:168)");
            }
            QrCodeImageKt.QrCodeImage(str, 320, PaddingKt.m529padding3ABfNKs(SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(220)), Dp.m5678constructorimpl(16)), 0, 0, composer, 432, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CrashReportScreen$lambda$9$0$1(String str, String str2, String str3, String str4, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-905254166, i10, -1, "com.arflix.tv.ui.screens.crash.CrashReportScreen.<anonymous>.<anonymous>.<anonymous> (CrashReportActivity.kt:192)");
            }
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(16));
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(Alignment.INSTANCE, androidx.compose.foundation.c.g(8, Arrangement.INSTANCE, composer, -483455358), composer, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyJ, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            TextKt.m2153Text4IGK_g("Crash Reference Details", (Modifier) null, Color.INSTANCE.m3509getWhite0d7_KjU(), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 200070, 0, 131026);
            TextKt.m2153Text4IGK_g(androidx.compose.material3.d.q(a2.r("ID: ", str, "\nTime: ", str2, "\nVersion: "), str3, "\nError: ", str4), (Modifier) null, ColorKt.Color(4291876317L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 3456, 0, 130994);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CrashReportScreen$lambda$9$0$2$0$0(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("ARVIO Crash Report", str));
        Toast.makeText(context, "Crash details copied! Opening Discord...", 1).show();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(CrashReportActivity.DISCORD_BUG_CHANNEL_URL));
        intent.addFlags(268435456);
        context.startActivity(intent);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CrashReportScreen$lambda$9$0$2$1$0(MutableState mutableState, FocusState focusState) {
        CrashReportScreen$lambda$3(mutableState, focusState.isFocused());
        return t0.f22605a;
    }
}
