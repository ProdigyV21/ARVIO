package com.arflix.tv.ui.components;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
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
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
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
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ai\u0010\r\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00002\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00002\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001d\u0010\u0011\u001a\u00020\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003H\u0003¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u000f\u0010\u0013\u001a\u00020\tH\u0003¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u000f\u0010\u0015\u001a\u00020\tH\u0003¢\u0006\u0004\b\u0015\u0010\u0014\"\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018\"\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b\"\u0014\u0010\u001c\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001b\"\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"", "imageUrl", LinkHeader.Parameters.Title, "", "genres", "year", "rating", "logoUrl", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "MobileHeroBanner", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "BannerMeta", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "BannerGenres", "(Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "MobileHeroBannerSeriesPreview", "(Landroidx/compose/runtime/Composer;I)V", "MobileHeroBannerFilmPreview", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "BannerShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "Landroidx/compose/ui/graphics/Color;", "CardBorder", "J", "ImdbYellow", "Landroidx/compose/ui/graphics/Brush;", "BottomScrim", "Landroidx/compose/ui/graphics/Brush;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MobileHeroBannerKt {
    private static final Brush BottomScrim;
    private static final RoundedCornerShape BannerShape = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(24));
    private static final long CardBorder = ColorKt.Color(4281019179L);
    private static final long ImdbYellow = ColorKt.Color(4294296856L);

    static {
        Brush.Companion companion = Brush.INSTANCE;
        Float fValueOf = Float.valueOf(0.0f);
        Color.Companion companion2 = Color.INSTANCE;
        BottomScrim = Brush.Companion.m3436verticalGradient8A3gB4$default(companion, new x6.x[]{new x6.x(fValueOf, Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU())), new x6.x(Float.valueOf(0.25f), Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU())), new x6.x(Float.valueOf(0.48f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null))), new x6.x(Float.valueOf(0.66f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null))), new x6.x(Float.valueOf(0.82f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.94f, 0.0f, 0.0f, 0.0f, 14, null))), new x6.x(Float.valueOf(1.0f), Color.m3462boximpl(ColorKt.Color(3422552064L)))}, 0.0f, 0.0f, 0, 14, (Object) null);
    }

    private static final void BannerGenres(final List<String> list, Composer composer, final int i10) {
        int i11;
        Composer composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(-675513267);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-675513267, i11, -1, "com.arflix.tv.ui.components.BannerGenres (MobileHeroBanner.kt:209)");
            }
            if (list.isEmpty()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final int i12 = 0;
                    pVar = new r7.p() { // from class: com.arflix.tv.ui.components.e1
                        @Override // r7.p
                        public final Object invoke(Object obj, Object obj2) {
                            int i13 = i12;
                            Composer composer3 = (Composer) obj;
                            int iIntValue = ((Integer) obj2).intValue();
                            switch (i13) {
                                case 0:
                                    return MobileHeroBannerKt.BannerGenres$lambda$0(list, i10, composer3, iIntValue);
                                default:
                                    return MobileHeroBannerKt.BannerGenres$lambda$1(list, i10, composer3, iIntValue);
                            }
                        }
                    };
                    scopeUpdateScopeEndRestartGroup.updateScope(pVar);
                }
                return;
            }
            composer2 = composerStartRestartGroup;
            TextKt.m2153Text4IGK_g(kotlin.collections.x.u0(list, "  •  ", null, null, null, 62), (Modifier) null, Color.INSTANCE.m3504getLightGray0d7_KjU(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getNormal(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), TextUnitKt.getSp(17), TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 2, 0, (r7.l<? super TextLayoutResult, x6.t0>) null, (TextStyle) null, composer2, 200064, 3126, 119250);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i13 = 1;
            pVar = new r7.p() { // from class: com.arflix.tv.ui.components.e1
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int i132 = i13;
                    Composer composer3 = (Composer) obj;
                    int iIntValue = ((Integer) obj2).intValue();
                    switch (i132) {
                        case 0:
                            return MobileHeroBannerKt.BannerGenres$lambda$0(list, i10, composer3, iIntValue);
                        default:
                            return MobileHeroBannerKt.BannerGenres$lambda$1(list, i10, composer3, iIntValue);
                    }
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BannerGenres$lambda$0(List list, int i10, Composer composer, int i11) {
        BannerGenres(list, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BannerGenres$lambda$1(List list, int i10, Composer composer, int i11) {
        BannerGenres(list, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void BannerMeta(String str, String str2, Composer composer, int i10) {
        int i11;
        Integer num;
        Composer composer2;
        int i12;
        Modifier.Companion companion;
        ComposeUiNode.Companion companion2;
        Composer composer3;
        String str3;
        int i13 = 0;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1416731820);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            str3 = str2;
            composer3 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1416731820, i11, -1, "com.arflix.tv.ui.components.BannerMeta (MobileHeroBanner.kt:163)");
            }
            boolean z = str.length() > 0;
            boolean z5 = str2.length() > 0;
            if (!z && !z5) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new c1(str, str2, i10, i13));
                    return;
                }
                return;
            }
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion3.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalG = androidx.compose.foundation.c.g(8, Arrangement.INSTANCE, composerStartRestartGroup, 693286680);
            Modifier.Companion companion4 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalG, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion4);
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
            r7.p pVarU = a0.c.u(companion5, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-412052192);
                companion2 = companion5;
                num = 0;
                i12 = i11;
                companion = companion4;
                TextKt.m2153Text4IGK_g(str, (Modifier) null, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.55f, 0.0f, 0.0f, 0.0f, 14, null), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (r7.l<? super TextLayoutResult, x6.t0>) null, (TextStyle) null, composerStartRestartGroup, (i11 & 14) | 200064, 0, 131026);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
            } else {
                num = 0;
                composer2 = composerStartRestartGroup;
                i12 = i11;
                companion = companion4;
                companion2 = companion5;
                composer2.startReplaceGroup(-411850382);
                composer2.endReplaceGroup();
            }
            if (z5) {
                composer2.startReplaceGroup(-411802022);
                Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(3, companion), ImdbYellow, null, 2, null), Dp.m5678constructorimpl(5), Dp.m5678constructorimpl(2));
                Alignment center = companion3.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer2, 6);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion2.getConstructor();
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
                r7.p pVarU2 = a0.c.u(companion2, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf2, composer2, num, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                Color.Companion companion6 = Color.INSTANCE;
                long jM3498getBlack0d7_KjU = companion6.m3498getBlack0d7_KjU();
                long sp = TextUnitKt.getSp(9);
                FontWeight.Companion companion7 = FontWeight.INSTANCE;
                Composer composer4 = composer2;
                TextKt.m2153Text4IGK_g("IMDb", (Modifier) null, jM3498getBlack0d7_KjU, sp, (FontStyle) null, companion7.getExtraBold(), (FontFamily) null, TextUnitKt.getSp(0.3d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (r7.l<? super TextLayoutResult, x6.t0>) null, (TextStyle) null, composer4, 12782982, 0, 130898);
                composer4.endReplaceableGroup();
                composer4.endNode();
                composer4.endReplaceableGroup();
                composer4.endReplaceableGroup();
                str3 = str2;
                TextKt.m2153Text4IGK_g(str3, (Modifier) null, Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), TextUnitKt.getSp(11), (FontStyle) null, companion7.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (r7.l<? super TextLayoutResult, x6.t0>) null, (TextStyle) null, composer4, ((i12 >> 3) & 14) | 200064, 0, 131026);
                composer3 = composer4;
                composer3.endReplaceGroup();
            } else {
                composer3 = composer2;
                str3 = str2;
                composer3.startReplaceGroup(-411040910);
                composer3.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composer3)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new c1(str, str3, i10, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BannerMeta$lambda$0(String str, String str2, int i10, Composer composer, int i11) {
        BannerMeta(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BannerMeta$lambda$2(String str, String str2, int i10, Composer composer, int i11) {
        BannerMeta(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:156:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MobileHeroBanner(final java.lang.String r40, final java.lang.String r41, final java.util.List<java.lang.String> r42, java.lang.String r43, java.lang.String r44, java.lang.String r45, r7.a<x6.t0> r46, androidx.compose.ui.Modifier r47, androidx.compose.runtime.Composer r48, final int r49, final int r50) {
        /*
            Method dump skipped, instruction units count: 1076
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.MobileHeroBannerKt.MobileHeroBanner(java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.String, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileHeroBanner$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileHeroBanner$lambda$2(String str, String str2, List list, String str3, String str4, String str5, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        MobileHeroBanner(str, str2, list, str3, str4, str5, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final void MobileHeroBannerFilmPreview(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1871740213);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1871740213, i10, -1, "com.arflix.tv.ui.components.MobileHeroBannerFilmPreview (MobileHeroBanner.kt:248)");
            }
            Modifier modifierM = com.arflix.tv.data.repository.g.m(16, BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m3498getBlack0d7_KjU(), null, 2, null), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            MobileHeroBanner("", "Oppenheimer", t7.a.E("History", "Drama", "Biography"), "2023", "8.3", null, null, null, composerStartRestartGroup, 28086, 224);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.j(i10, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileHeroBannerFilmPreview$lambda$1(int i10, Composer composer, int i11) {
        MobileHeroBannerFilmPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void MobileHeroBannerSeriesPreview(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-113200130);
        int i11 = 1;
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-113200130, i10, -1, "com.arflix.tv.ui.components.MobileHeroBannerSeriesPreview (MobileHeroBanner.kt:229)");
            }
            Modifier modifierM = com.arflix.tv.data.repository.g.m(16, BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m3498getBlack0d7_KjU(), null, 2, null), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            MobileHeroBanner("", "Stranger Things", t7.a.E("Slick", "Psychological", "Thriller"), "2022", "8.7", null, null, null, composerStartRestartGroup, 28086, 224);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.j(i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileHeroBannerSeriesPreview$lambda$1(int i10, Composer composer, int i11) {
        MobileHeroBannerSeriesPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }
}
