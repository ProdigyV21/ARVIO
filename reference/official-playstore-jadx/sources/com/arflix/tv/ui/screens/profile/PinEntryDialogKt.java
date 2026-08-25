package com.arflix.tv.ui.screens.profile;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.LockKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.IconKt;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.util.PinUtil;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u001aO\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a7\u0010\u0014\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0003¢\u0006\u0004\b\u0012\u0010\u0013\u001a/\u0010\u0015\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\u00102\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0003¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u001b²\u0006\u000e\u0010\u0017\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0018\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u0019\u001a\u00020\u00078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u001a\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002"}, d2 = {"", LinkHeader.Parameters.Title, "Lkotlin/Function1;", "Lx6/t0;", "onPinConfirmed", "Lkotlin/Function0;", "onDismiss", "", "isSetup", "pinError", "PinEntryDialog", "(Ljava/lang/String;Lr7/l;Lr7/a;ZLjava/lang/String;Landroidx/compose/runtime/Composer;II)V", "label", "onClick", "Landroidx/compose/ui/graphics/Color;", "containerColor", "Landroidx/compose/ui/Modifier;", "modifier", "PinActionButton-cf5BqRc", "(Ljava/lang/String;Lr7/a;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "PinActionButton", "PinKeyButton", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "pinInput", "confirmPin", "isConfirmingSetup", "errorMessage", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PinEntryDialogKt {
    /* JADX WARN: Removed duplicated region for block: B:41:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: PinActionButton-cf5BqRc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6276PinActionButtoncf5BqRc(java.lang.String r29, r7.a<x6.t0> r30, long r31, androidx.compose.ui.Modifier r33, androidx.compose.runtime.Composer r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 507
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.PinEntryDialogKt.m6276PinActionButtoncf5BqRc(java.lang.String, r7.a, long, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinActionButton_cf5BqRc$lambda$0(String str, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1457915747, i10, -1, "com.arflix.tv.ui.screens.profile.PinActionButton.<anonymous> (PinEntryDialog.kt:312)");
            }
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            TextKt.m6020Text4IGK_g(str, null, Color.INSTANCE.m3509getWhite0d7_KjU(), 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 196992, 0, 131034);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinActionButton_cf5BqRc$lambda$1$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinActionButton_cf5BqRc$lambda$3(r7.p pVar, BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(15788537, i10, -1, "com.arflix.tv.ui.screens.profile.PinActionButton.<anonymous> (PinEntryDialog.kt:335)");
            }
            if (a0.c.A(composer, 6, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinActionButton_cf5BqRc$lambda$4(String str, r7.a aVar, long j10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        m6276PinActionButtoncf5BqRc(str, aVar, j10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x019d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PinEntryDialog(java.lang.String r29, r7.l<? super java.lang.String, x6.t0> r30, final r7.a<x6.t0> r31, boolean r32, java.lang.String r33, androidx.compose.runtime.Composer r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.PinEntryDialogKt.PinEntryDialog(java.lang.String, r7.l, r7.a, boolean, java.lang.String, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final String PinEntryDialog$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PinEntryDialog$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v41 */
    public static final t0 PinEntryDialog$lambda$13(final boolean z, String str, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, r7.a aVar, final String str2, final String str3, final r7.l lVar, Composer composer, int i10) {
        String strStringResource;
        String strStringResource2;
        Arrangement arrangement;
        ComposeUiNode.Companion companion;
        int i11;
        Modifier.Companion companion2;
        Composer composer2;
        Integer num;
        Alignment.Companion companion3;
        float f10;
        int i12;
        int i13;
        boolean z5;
        int i14;
        float f11;
        Integer num2;
        Composer composer3;
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-655306935, i10, -1, "com.arflix.tv.ui.screens.profile.PinEntryDialog.<anonymous> (PinEntryDialog.kt:72)");
            }
            Modifier.Companion companion4 = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion4, 0.0f, 1, null);
            Color.Companion companion5 = Color.INSTANCE;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(modifierFillMaxSize$default, Color.m3471copywmQWz5c$default(companion5.m3498getBlack0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
            Alignment.Companion companion6 = Alignment.INSTANCE;
            Alignment center = companion6.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion7.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
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
            r7.p pVarU = a0.c.u(companion7, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            float f12 = 16;
            float f13 = 40;
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(PaddingKt.m529padding3ABfNKs(BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(f12, companion4), ColorKt.Color(4279505940L), null, 2, null), Dp.m5678constructorimpl(f13)), Dp.m5678constructorimpl(300));
            Alignment.Horizontal centerHorizontally = companion6.getCenterHorizontally();
            Arrangement arrangement2 = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(androidx.compose.foundation.c.g(f12, arrangement2, composer, -483455358), centerHorizontally, composer, 54);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion7.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM583width3ABfNKs);
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
            r7.p pVarU2 = a0.c.u(companion7, composerM2991constructorimpl2, measurePolicyColumnMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            IconKt.m5992Iconww6aTOc(LockKt.getLock(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.profile_pin_entry_cd, composer, 0), SizeKt.m578size3ABfNKs(companion4, Dp.m5678constructorimpl(32)), companion5.m3509getWhite0d7_KjU(), composer, 3456, 0);
            if (z && !PinEntryDialog$lambda$7(mutableState)) {
                composer.startReplaceGroup(770880749);
                strStringResource = StringResources_androidKt.stringResource(R.string.set_profile_pin, composer, 0);
                composer.endReplaceGroup();
            } else if (z && PinEntryDialog$lambda$7(mutableState)) {
                composer.startReplaceGroup(770883857);
                strStringResource = StringResources_androidKt.stringResource(R.string.profile_confirm_pin, composer, 0);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(770886282);
                composer.endReplaceGroup();
                strStringResource = str;
            }
            long sp = TextUnitKt.getSp(18);
            FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
            long jM3509getWhite0d7_KjU = companion5.m3509getWhite0d7_KjU();
            TextAlign.Companion companion8 = TextAlign.INSTANCE;
            ?? r1 = 0;
            TextKt.m6020Text4IGK_g(strStringResource, null, jM3509getWhite0d7_KjU, sp, null, semiBold, null, 0L, null, TextAlign.m5575boximpl(companion8.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 200064, 0, 130514);
            if (z && !PinEntryDialog$lambda$7(mutableState)) {
                composer.startReplaceGroup(770897300);
                strStringResource2 = StringResources_androidKt.stringResource(R.string.profile_pin_setup_hint, composer, 0);
                composer.endReplaceGroup();
            } else if (z && PinEntryDialog$lambda$7(mutableState)) {
                composer.startReplaceGroup(770900625);
                strStringResource2 = StringResources_androidKt.stringResource(R.string.profile_pin_reenter, composer, 0);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(770903089);
                strStringResource2 = StringResources_androidKt.stringResource(R.string.enter_pin_to_unlock, composer, 0);
                composer.endReplaceGroup();
            }
            TextKt.m6020Text4IGK_g(strStringResource2, null, ColorKt.Color(4289769648L), TextUnitKt.getSp(12), null, null, null, 0L, null, TextAlign.m5575boximpl(companion8.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 3456, 0, 130546);
            Composer composer4 = composer;
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(companion4, 0.0f, 1, null), 0.0f, Dp.m5678constructorimpl(f12), 1, null);
            float f14 = 8;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement2.m441spacedBy0680j_4(Dp.m5678constructorimpl(f14));
            Alignment.Vertical centerVertically = companion6.getCenterVertically();
            composer4.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_4, centerVertically, composer4, 54);
            int i15 = -1323940314;
            composer4.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer4.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion7.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
            if (composer4.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
                composer4.createNode(constructor3);
            } else {
                composer4.useNode();
            }
            Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer4);
            r7.p pVarU3 = a0.c.u(companion7, composerM2991constructorimpl3, measurePolicyRowMeasurePolicy, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            Integer num3 = 0;
            int i16 = 2058660585;
            a0.c.y(composer4, qVarModifierMaterializerOf3, composer4, null, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composer4.startReplaceGroup(326268903);
            int i17 = 0;
            while (i17 < 5) {
                String strPinEntryDialog$lambda$4 = (z && PinEntryDialog$lambda$7(mutableState)) ? PinEntryDialog$lambda$4(mutableState2) : PinEntryDialog$lambda$1(mutableState3);
                Modifier modifierK = com.arflix.tv.data.repository.g.k(f14, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(f13)), f14), ColorKt.Color(4280953386L), null, 2, null), Dp.m5678constructorimpl(2), i17 < strPinEntryDialog$lambda$4.length() ? ColorKt.Color(4283215696L) : ColorKt.Color(4282664004L));
                Alignment center2 = Alignment.INSTANCE.getCenter();
                composer4.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, r1, composer4, 6);
                composer4.startReplaceableGroup(i15);
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer4, r1);
                CompositionLocalMap currentCompositionLocalMap4 = composer4.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion9 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor4 = companion9.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierK);
                if (composer4.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                    composer4.createNode(constructor4);
                } else {
                    composer4.useNode();
                }
                Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer4);
                r7.p pVarU4 = a0.c.u(companion9, composerM2991constructorimpl4, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl4, currentCompositionLocalMap4);
                if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
                }
                a0.c.y(composer4, qVarModifierMaterializerOf4, composer4, num3, i16);
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                if (i17 < strPinEntryDialog$lambda$4.length()) {
                    composer4.startReplaceGroup(-336651454);
                    i14 = i17;
                    f11 = f14;
                    num2 = num3;
                    TextKt.m6020Text4IGK_g("•", null, ColorKt.Color(4283215696L), TextUnitKt.getSp(24), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 200070, 0, 131026);
                    composer3 = composer;
                    composer3.endReplaceGroup();
                } else {
                    i14 = i17;
                    f11 = f14;
                    num2 = num3;
                    composer3 = composer4;
                    composer3.startReplaceGroup(-336363185);
                    composer3.endReplaceGroup();
                }
                composer3.endReplaceableGroup();
                composer3.endNode();
                composer3.endReplaceableGroup();
                composer3.endReplaceableGroup();
                f14 = f11;
                i17 = i14 + 1;
                composer4 = composer3;
                num3 = num2;
                r1 = 0;
                i15 = -1323940314;
                i16 = 2058660585;
            }
            float f15 = f14;
            Integer num4 = num3;
            int i18 = 6;
            Composer composer5 = composer4;
            composer5.endReplaceGroup();
            composer5.endReplaceableGroup();
            composer5.endNode();
            composer5.endReplaceableGroup();
            composer5.endReplaceableGroup();
            Arrangement.HorizontalOrVertical horizontalOrVerticalG = androidx.compose.foundation.c.g(f15, Arrangement.INSTANCE, composer5, -483455358);
            Modifier.Companion companion10 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(Alignment.INSTANCE, horizontalOrVerticalG, composer5, 6, -1323940314);
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer5.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion11 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor5 = companion11.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(companion10);
            if (composer5.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer5.startReusableNode();
            if (composer5.getInserting()) {
                composer5.createNode(constructor5);
            } else {
                composer5.useNode();
            }
            Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer5);
            r7.p pVarU5 = a0.c.u(companion11, composerM2991constructorimpl5, measurePolicyJ, composerM2991constructorimpl5, currentCompositionLocalMap5);
            if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
            }
            int i19 = 2058660585;
            a0.c.y(composer5, qVarModifierMaterializerOf5, composer5, num4, 2058660585);
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            composer5.startReplaceGroup(1617538554);
            int i20 = 0;
            while (true) {
                int i21 = 48;
                if (i20 >= 3) {
                    break;
                }
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(Alignment.INSTANCE, androidx.compose.foundation.c.g(f15, Arrangement.INSTANCE, composer5, 693286680), composer5, i18, -1323940314);
                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                boolean z10 = true;
                CompositionLocalMap currentCompositionLocalMap6 = composer5.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion12 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor6 = companion12.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
                if (composer5.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer5.startReusableNode();
                if (composer5.getInserting()) {
                    composer5.createNode(constructor6);
                } else {
                    composer5.useNode();
                }
                Composer composerM2991constructorimpl6 = Updater.m2991constructorimpl(composer5);
                r7.p pVarU6 = a0.c.u(companion12, composerM2991constructorimpl6, measurePolicyC, composerM2991constructorimpl6, currentCompositionLocalMap6);
                if (composerM2991constructorimpl6.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                    a0.c.w(currentCompositeKeyHash6, composerM2991constructorimpl6, currentCompositeKeyHash6, pVarU6);
                }
                a0.c.y(composer5, qVarModifierMaterializerOf6, composer5, num4, i19);
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                composer5.startReplaceGroup(-1366615912);
                int i22 = 0;
                for (int i23 = 3; i22 < i23; i23 = 3) {
                    final int i24 = (i20 * 3) + i22 + 1;
                    String strValueOf = String.valueOf(i24);
                    Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.e.a(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m5678constructorimpl(i21));
                    boolean zChanged = composer5.changed(z) | composer5.changed(i24);
                    Object objRememberedValue = composer5.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        f10 = f15;
                        i12 = i22;
                        i13 = i20;
                        z5 = z10;
                        r7.a aVar2 = new r7.a() { // from class: com.arflix.tv.ui.screens.profile.a
                            @Override // r7.a
                            public final Object invoke() {
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$0$0$0(z, i24, mutableState, mutableState2, mutableState3, mutableState4);
                            }
                        };
                        composer5.updateRememberedValue(aVar2);
                        objRememberedValue = aVar2;
                    } else {
                        i12 = i22;
                        f10 = f15;
                        i13 = i20;
                        z5 = z10;
                    }
                    PinKeyButton(strValueOf, modifierM564height3ABfNKs, (r7.a) objRememberedValue, composer5, 0, 0);
                    i22 = i12 + 1;
                    z10 = z5;
                    f15 = f10;
                    i20 = i13;
                    i21 = 48;
                }
                composer5.endReplaceGroup();
                composer5.endReplaceableGroup();
                composer5.endNode();
                composer5.endReplaceableGroup();
                composer5.endReplaceableGroup();
                i20++;
                i18 = 6;
                f15 = f15;
                i19 = 2058660585;
            }
            int i25 = i18;
            float f16 = f15;
            composer5.endReplaceGroup();
            Modifier.Companion companion13 = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(companion13, 0.0f, 1, null);
            Arrangement arrangement3 = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical horizontalOrVerticalG2 = androidx.compose.foundation.c.g(f16, arrangement3, composer5, 693286680);
            Alignment.Companion companion14 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyC2 = androidx.compose.foundation.c.C(companion14, horizontalOrVerticalG2, composer5, i25, -1323940314);
            int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
            CompositionLocalMap currentCompositionLocalMap7 = composer5.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion15 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor7 = companion15.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf7 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default2);
            if (composer5.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer5.startReusableNode();
            if (composer5.getInserting()) {
                composer5.createNode(constructor7);
            } else {
                composer5.useNode();
            }
            Composer composerM2991constructorimpl7 = Updater.m2991constructorimpl(composer5);
            r7.p pVarU7 = a0.c.u(companion15, composerM2991constructorimpl7, measurePolicyC2, composerM2991constructorimpl7, currentCompositionLocalMap7);
            if (composerM2991constructorimpl7.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                a0.c.w(currentCompositeKeyHash7, composerM2991constructorimpl7, currentCompositeKeyHash7, pVarU7);
            }
            a0.c.y(composer5, qVarModifierMaterializerOf7, composer5, num4, 2058660585);
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            float f17 = 48;
            Modifier modifierM564height3ABfNKs2 = SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.e.a(rowScopeInstance3, companion13, 1.0f, false, 2, null), Dp.m5678constructorimpl(f17));
            boolean zChanged2 = composer5.changed(z);
            Object objRememberedValue2 = composer5.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                final int i26 = 0;
                arrangement = arrangement3;
                companion = companion15;
                i11 = 0;
                r7.a aVar3 = new r7.a() { // from class: com.arflix.tv.ui.screens.profile.b
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i26) {
                            case 0:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$0$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                            case 1:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$1$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                            default:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$2$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                        }
                    }
                };
                composer5.updateRememberedValue(aVar3);
                objRememberedValue2 = aVar3;
            } else {
                arrangement = arrangement3;
                companion = companion15;
                i11 = 0;
            }
            PinKeyButton("0", modifierM564height3ABfNKs2, (r7.a) objRememberedValue2, composer5, 6, 0);
            String strStringResource3 = StringResources_androidKt.stringResource(R.string.profile_clear, composer5, i11);
            Modifier modifierM564height3ABfNKs3 = SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.e.a(rowScopeInstance3, companion13, 1.0f, false, 2, null), Dp.m5678constructorimpl(f17));
            boolean zChanged3 = composer5.changed(z);
            Object objRememberedValue3 = composer5.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                final int i27 = 1;
                r7.a aVar4 = new r7.a() { // from class: com.arflix.tv.ui.screens.profile.b
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i27) {
                            case 0:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$0$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                            case 1:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$1$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                            default:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$2$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                        }
                    }
                };
                composer5.updateRememberedValue(aVar4);
                objRememberedValue3 = aVar4;
            }
            PinKeyButton(strStringResource3, modifierM564height3ABfNKs3, (r7.a) objRememberedValue3, composer5, 0, 0);
            Modifier modifierM564height3ABfNKs4 = SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.e.a(rowScopeInstance3, companion13, 1.0f, false, 2, null), Dp.m5678constructorimpl(f17));
            boolean zChanged4 = composer5.changed(z);
            Object objRememberedValue4 = composer5.rememberedValue();
            if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                final int i28 = 2;
                companion2 = companion13;
                r7.a aVar5 = new r7.a() { // from class: com.arflix.tv.ui.screens.profile.b
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i28) {
                            case 0:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$0$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                            case 1:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$1$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                            default:
                                return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$1$1$2$0(z, mutableState, mutableState2, mutableState3, mutableState4);
                        }
                    }
                };
                composer5.updateRememberedValue(aVar5);
                objRememberedValue4 = aVar5;
            } else {
                companion2 = companion13;
            }
            PinKeyButton("←", modifierM564height3ABfNKs4, (r7.a) objRememberedValue4, composer5, 6, 0);
            com.arflix.tv.data.repository.g.w(composer5);
            if (PinEntryDialog$lambda$10(mutableState4).length() > 0) {
                composer5.startReplaceGroup(-1865534173);
                companion3 = companion14;
                num = num4;
                TextKt.m6020Text4IGK_g(PinEntryDialog$lambda$10(mutableState4), null, ColorKt.Color(4294929259L), TextUnitKt.getSp(12), null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 3456, 0, 130546);
                composer2 = composer;
                composer2.endReplaceGroup();
            } else {
                composer2 = composer5;
                num = num4;
                companion3 = companion14;
                composer2.startReplaceGroup(-1865306819);
                composer2.endReplaceGroup();
            }
            Modifier.Companion companion16 = companion2;
            Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(companion16, 0.0f, 1, null);
            MeasurePolicy measurePolicyC3 = androidx.compose.foundation.c.C(companion3, androidx.compose.foundation.c.g(12, arrangement, composer2, 693286680), composer2, 6, -1323940314);
            int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap8 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor8 = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf8 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default3);
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
            r7.p pVarU8 = a0.c.u(companion, composerM2991constructorimpl8, measurePolicyC3, composerM2991constructorimpl8, currentCompositionLocalMap8);
            if (composerM2991constructorimpl8.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                a0.c.w(currentCompositeKeyHash8, composerM2991constructorimpl8, currentCompositeKeyHash8, pVarU8);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf8, composer2, num, 2058660585);
            float f18 = 44;
            m6276PinActionButtoncf5BqRc(StringResources_androidKt.stringResource(R.string.cancel, composer2, 0), aVar, ColorKt.Color(4280953386L), SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.e.a(rowScopeInstance3, companion16, 1.0f, false, 2, null), Dp.m5678constructorimpl(f18)), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
            String strStringResource4 = StringResources_androidKt.stringResource(R.string.confirm, composer, 0);
            boolean zChanged5 = composer.changed(z) | composer.changed(str2) | composer.changed(str3) | composer.changed(lVar);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                r7.a aVar6 = new r7.a() { // from class: com.arflix.tv.ui.screens.profile.c
                    @Override // r7.a
                    public final Object invoke() {
                        return PinEntryDialogKt.PinEntryDialog$lambda$13$0$0$2$0$0(z, str2, str3, lVar, mutableState, mutableState2, mutableState3, mutableState4);
                    }
                };
                composer.updateRememberedValue(aVar6);
                objRememberedValue5 = aVar6;
            }
            m6276PinActionButtoncf5BqRc(strStringResource4, (r7.a) objRememberedValue5, ColorKt.Color(4283215696L), SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.e.a(rowScopeInstance3, companion16, 1.0f, false, 2, null), Dp.m5678constructorimpl(f18)), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
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
    public static final t0 PinEntryDialog$lambda$13$0$0$1$0$0$0(boolean z, int i10, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        String strPinEntryDialog$lambda$4 = (z && PinEntryDialog$lambda$7(mutableState)) ? PinEntryDialog$lambda$4(mutableState2) : PinEntryDialog$lambda$1(mutableState3);
        if (strPinEntryDialog$lambda$4.length() < 5) {
            String str = strPinEntryDialog$lambda$4 + i10;
            if (z && PinEntryDialog$lambda$7(mutableState)) {
                mutableState2.setValue(str);
            } else {
                mutableState3.setValue(str);
            }
            mutableState4.setValue("");
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinEntryDialog$lambda$13$0$0$1$1$0$0(boolean z, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        String strPinEntryDialog$lambda$4 = (z && PinEntryDialog$lambda$7(mutableState)) ? PinEntryDialog$lambda$4(mutableState2) : PinEntryDialog$lambda$1(mutableState3);
        if (strPinEntryDialog$lambda$4.length() < 5) {
            String strConcat = strPinEntryDialog$lambda$4.concat("0");
            if (z && PinEntryDialog$lambda$7(mutableState)) {
                mutableState2.setValue(strConcat);
            } else {
                mutableState3.setValue(strConcat);
            }
            mutableState4.setValue("");
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinEntryDialog$lambda$13$0$0$1$1$1$0(boolean z, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        if (z && PinEntryDialog$lambda$7(mutableState)) {
            mutableState2.setValue("");
        } else {
            mutableState3.setValue("");
        }
        mutableState4.setValue("");
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinEntryDialog$lambda$13$0$0$1$1$2$0(boolean z, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        String strPinEntryDialog$lambda$4 = (z && PinEntryDialog$lambda$7(mutableState)) ? PinEntryDialog$lambda$4(mutableState2) : PinEntryDialog$lambda$1(mutableState3);
        if (strPinEntryDialog$lambda$4.length() > 0) {
            String strW = kotlin.text.o.W(1, strPinEntryDialog$lambda$4);
            if (z && PinEntryDialog$lambda$7(mutableState)) {
                mutableState2.setValue(strW);
            } else {
                mutableState3.setValue(strW);
            }
        }
        mutableState4.setValue("");
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinEntryDialog$lambda$13$0$0$2$0$0(boolean z, String str, String str2, r7.l lVar, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        String strPinEntryDialog$lambda$4 = (z && PinEntryDialog$lambda$7(mutableState)) ? PinEntryDialog$lambda$4(mutableState2) : PinEntryDialog$lambda$1(mutableState3);
        if (!PinUtil.INSTANCE.isValidPin(strPinEntryDialog$lambda$4)) {
            mutableState4.setValue(str);
        } else if (!z) {
            lVar.invoke(strPinEntryDialog$lambda$4);
        } else if (!PinEntryDialog$lambda$7(mutableState)) {
            PinEntryDialog$lambda$8(mutableState, true);
        } else if (kotlin.jvm.internal.p.a(PinEntryDialog$lambda$1(mutableState3), PinEntryDialog$lambda$4(mutableState2))) {
            lVar.invoke(PinEntryDialog$lambda$1(mutableState3));
        } else {
            mutableState4.setValue(str2);
            mutableState2.setValue("");
            PinEntryDialog$lambda$8(mutableState, false);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinEntryDialog$lambda$14(String str, r7.l lVar, r7.a aVar, boolean z, String str2, int i10, int i11, Composer composer, int i12) {
        PinEntryDialog(str, lVar, aVar, z, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    private static final String PinEntryDialog$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean PinEntryDialog$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PinEntryDialog$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void PinKeyButton(java.lang.String r28, androidx.compose.ui.Modifier r29, r7.a<x6.t0> r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.PinEntryDialogKt.PinKeyButton(java.lang.String, androidx.compose.ui.Modifier, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinKeyButton$lambda$0(String str, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(246035381, i10, -1, "com.arflix.tv.ui.screens.profile.PinKeyButton.<anonymous> (PinEntryDialog.kt:349)");
            }
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            TextKt.m6020Text4IGK_g(str, null, Color.INSTANCE.m3509getWhite0d7_KjU(), TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 200064, 0, 131026);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinKeyButton$lambda$1$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinKeyButton$lambda$3(r7.p pVar, BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-454489383, i10, -1, "com.arflix.tv.ui.screens.profile.PinKeyButton.<anonymous> (PinEntryDialog.kt:378)");
            }
            if (a0.c.A(composer, 6, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PinKeyButton$lambda$4(String str, Modifier modifier, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        PinKeyButton(str, modifier, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }
}
