package com.arflix.tv.ui.components;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import com.arflix.tv.ui.skin.ArvioSkinKt;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0019\u0010\u0002\u001a\u00020\u00002\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001aS\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00002\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\t\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001aM\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\u00122\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a5\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00002\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000b0\u0012¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0099\u0001\u0010'\u001a\u00020\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00002\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\b2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010$\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0007¢\u0006\u0004\b%\u0010&¨\u0006("}, d2 = {"", "value", "localizeSettingValue", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, "", "isFocused", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "SettingsRow", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "isEnabled", "Lkotlin/Function1;", "onToggle", "SettingsToggleRow", "(Ljava/lang/String;Ljava/lang/String;ZZLr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "MobileSettingsCategory", "(Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;I)V", "", "iconRes", "Landroidx/compose/ui/graphics/Color;", "iconTint", "enabled", "isToggle", "isExternalLink", "actionIcon", "showDivider", "MobileSettingsRow-Ut8lOTo", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/Integer;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZLandroidx/compose/ui/graphics/vector/ImageVector;ZLr7/a;Landroidx/compose/runtime/Composer;III)V", "MobileSettingsRow", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SettingsRowsKt {
    public static final void MobileSettingsCategory(String str, r7.q<? super ColumnScope, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10) {
        int i11;
        Composer composer2;
        r7.q<? super ColumnScope, ? super Composer, ? super Integer, x6.t0> qVar2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1202517093);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(qVar) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            qVar2 = qVar;
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1202517093, i11, -1, "com.arflix.tv.ui.components.MobileSettingsCategory (SettingsRows.kt:253)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, top, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            int i12 = i11;
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            TextKt.m2153Text4IGK_g(str, PaddingKt.m533paddingqDBjuR0$default(companion, Dp.m5678constructorimpl(16), 0.0f, 0.0f, Dp.m5678constructorimpl(8), 6, null), ColorKt.getTextSecondary(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (r7.l<? super TextLayoutResult, x6.t0>) null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(12), null, null, null, null, null, TextUnitKt.getSp(1), null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777085, null), composerStartRestartGroup, (i12 & 14) | 432, 0, 65528);
            composer2 = composerStartRestartGroup;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 12), ColorKt.getBackgroundElevated(), null, 2, null);
            composer2.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK2 = androidx.compose.foundation.c.k(companion2, arrangement.getTop(), composer2, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyK2, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf2, composer2, 0, 2058660585);
            qVar2 = qVar;
            qVar2.invoke(columnScopeInstance, composer2, Integer.valueOf((i12 & 112) | 6));
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new x2(str, qVar2, i10, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileSettingsCategory$lambda$1(String str, r7.q qVar, int i10, Composer composer, int i11) {
        MobileSettingsCategory(str, qVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x049c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:246:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x04fc  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0641  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0652  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x065c  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x067e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:283:0x068e  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0854  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x089c  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x08ab  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x08c9  */
    /* JADX WARN: Removed duplicated region for block: B:335:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0120  */
    /* JADX INFO: renamed from: MobileSettingsRow-Ut8lOTo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6184MobileSettingsRowUt8lOTo(androidx.compose.ui.graphics.vector.ImageVector r84, java.lang.Integer r85, long r86, final java.lang.String r88, java.lang.String r89, final java.lang.String r90, boolean r91, boolean r92, boolean r93, boolean r94, androidx.compose.ui.graphics.vector.ImageVector r95, boolean r96, final r7.a<x6.t0> r97, androidx.compose.runtime.Composer r98, final int r99, final int r100, final int r101) {
        /*
            Method dump skipped, instruction units count: 2273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SettingsRowsKt.m6184MobileSettingsRowUt8lOTo(androidx.compose.ui.graphics.vector.ImageVector, java.lang.Integer, long, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, boolean, boolean, androidx.compose.ui.graphics.vector.ImageVector, boolean, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileSettingsRow_Ut8lOTo$lambda$0$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileSettingsRow_Ut8lOTo$lambda$1(ImageVector imageVector, Integer num, long j10, String str, String str2, String str3, boolean z, boolean z5, boolean z10, boolean z11, ImageVector imageVector2, boolean z12, r7.a aVar, int i10, int i11, int i12, Composer composer, int i13) {
        m6184MobileSettingsRowUt8lOTo(imageVector, num, j10, str, str2, str3, z, z5, z10, z11, imageVector2, z12, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x059a  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:169:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SettingsRow(androidx.compose.ui.graphics.vector.ImageVector r72, java.lang.String r73, java.lang.String r74, java.lang.String r75, boolean r76, r7.a<x6.t0> r77, androidx.compose.ui.Modifier r78, androidx.compose.runtime.Composer r79, int r80, int r81) {
        /*
            Method dump skipped, instruction units count: 1468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SettingsRowsKt.SettingsRow(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, java.lang.String, java.lang.String, boolean, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SettingsRow$lambda$2(ImageVector imageVector, String str, String str2, String str3, boolean z, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SettingsRow(imageVector, str, str2, str3, z, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final void SettingsToggleRow(String str, String str2, boolean z, boolean z5, r7.l<? super Boolean, x6.t0> lVar, Modifier modifier, Composer composer, int i10, int i11) {
        String str3;
        int i12;
        Modifier modifier2;
        Composer composer2;
        int i13;
        int i14;
        Modifier modifier3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1026331586);
        if ((i10 & 6) == 0) {
            str3 = str;
            i12 = (composerStartRestartGroup.changed(str3) ? 4 : 2) | i10;
        } else {
            str3 = str;
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(z5) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 16384 : 8192;
        }
        int i15 = i11 & 32;
        if (i15 != 0) {
            i12 |= 196608;
            modifier2 = modifier;
        } else {
            modifier2 = modifier;
            if ((i10 & 196608) == 0) {
                i12 |= composerStartRestartGroup.changed(modifier2) ? 131072 : 65536;
            }
        }
        if ((i12 & 74899) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i15 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1026331586, i12, -1, "com.arflix.tv.ui.components.SettingsToggleRow (SettingsRows.kt:183)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
            long jM6519resolveAccentColorek8zF_U = ArvioSkinKt.m6519resolveAccentColorek8zF_U(ColorKt.getPink(), composerStartRestartGroup, 6);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null);
            Modifier modifier5 = modifier4;
            boolean z10 = ((i12 & 896) == 256) | ((57344 & i12) == 16384);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new com.arflix.tv.b(1, lVar, z);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierM212clickableO2vRcR0$default = ClickableKt.m212clickableO2vRcR0$default(modifierFillMaxWidth$default, mutableInteractionSource, null, false, null, null, (r7.a) objRememberedValue2, 28, null);
            float f10 = 12;
            Modifier modifierL = com.arflix.tv.data.repository.g.l(f10, modifierM212clickableO2vRcR0$default, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), z5 ? 0.12f : 0.05f, 0.0f, 0.0f, 0.0f, 14, null));
            float fM5678constructorimpl = z5 ? Dp.m5678constructorimpl(2) : Dp.m5678constructorimpl(0);
            if (!z5) {
                jM6519resolveAccentColorek8zF_U = Color.INSTANCE.m3507getTransparent0d7_KjU();
            }
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.k(f10, modifierL, fM5678constructorimpl, jM6519resolveAccentColorek8zF_U), Dp.m5678constructorimpl(16), Dp.m5678constructorimpl(14));
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion2.getCenterVertically();
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical spaceBetween = arrangement.getSpaceBetween();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Modifier.Companion companion4 = Modifier.INSTANCE;
            Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance, companion4, 1.0f, false, 2, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierA);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyK, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCardTitle(), 0L, TextUnitKt.getSp(16), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null);
            int i16 = i12;
            long textPrimary = ColorKt.getTextPrimary();
            TextOverflow.Companion companion5 = TextOverflow.INSTANCE;
            TextKt.m2153Text4IGK_g(str3, (Modifier) null, textPrimary, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion5.m5632getEllipsisgIe3tQ8(), false, 1, 0, (r7.l<? super TextLayoutResult, x6.t0>) null, textStyleM5205copyp1EtxEg$default, composerStartRestartGroup, (i16 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
            composer2 = composerStartRestartGroup;
            if (str2.length() > 0) {
                composer2.startReplaceGroup(1750219675);
                TextStyle textStyleM5205copyp1EtxEg$default2 = TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(17), null, null, null, 0, 0, null, 16646141, null);
                long textSecondary = ColorKt.getTextSecondary();
                int iM5632getEllipsisgIe3tQ8 = companion5.m5632getEllipsisgIe3tQ8();
                int i17 = ((i16 >> 3) & 14) | RendererCapabilities.DECODER_SUPPORT_MASK;
                i14 = 13;
                i13 = 3;
                TextKt.m2153Text4IGK_g(str2, (Modifier) null, textSecondary, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, iM5632getEllipsisgIe3tQ8, false, 3, 0, (r7.l<? super TextLayoutResult, x6.t0>) null, textStyleM5205copyp1EtxEg$default2, composer2, i17, 3120, 55290);
                composer2 = composer2;
                composer2.endReplaceGroup();
            } else {
                i13 = 3;
                i14 = 13;
                composer2.startReplaceGroup(1750509370);
                composer2.endReplaceGroup();
            }
            androidx.compose.foundation.c.y(composer2);
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(com.arflix.tv.data.repository.g.l(i14, SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion4, Dp.m5678constructorimpl(44)), Dp.m5678constructorimpl(24)), z ? ColorKt.getSuccessGreen() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(i13));
            Alignment centerEnd = z ? companion2.getCenterEnd() : companion2.getCenterStart();
            composer2.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(centerEnd, false, composer2, 0);
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            r7.p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf3, composer2, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            BoxKt.Box(com.arflix.tv.data.repository.g.l(10, SizeKt.m578size3ABfNKs(companion4, Dp.m5678constructorimpl(18)), Color.INSTANCE.m3509getWhite0d7_KjU()), composer2, 0);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f2(str, str2, z, z5, lVar, modifier3, i10, i11, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SettingsToggleRow$lambda$1$0(r7.l lVar, boolean z) {
        lVar.invoke(Boolean.valueOf(!z));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SettingsToggleRow$lambda$3(String str, String str2, boolean z, boolean z5, r7.l lVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SettingsToggleRow(str, str2, z, z5, lVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:144:0x038e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String localizeSettingValue(java.lang.String r3, androidx.compose.runtime.Composer r4, int r5) {
        /*
            Method dump skipped, instruction units count: 1082
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SettingsRowsKt.localizeSettingValue(java.lang.String, androidx.compose.runtime.Composer, int):java.lang.String");
    }
}
