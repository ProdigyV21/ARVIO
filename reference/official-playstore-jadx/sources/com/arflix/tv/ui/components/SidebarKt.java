package com.arflix.tv.ui.components;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.skin.ArvioSkin;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001as\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\u00022\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\n0\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001f\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u0013\u0010\u0014\u001a1\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006 ²\u0006\f\u0010\u001b\u001a\u00020\u001a8\nX\u008a\u0084\u0002²\u0006\f\u0010\u001c\u001a\u00020\u001a8\nX\u008a\u0084\u0002²\u0006\f\u0010\u001e\u001a\u00020\u001d8\nX\u008a\u0084\u0002²\u0006\f\u0010\u001b\u001a\u00020\u001a8\nX\u008a\u0084\u0002²\u0006\f\u0010\u001f\u001a\u00020\u001d8\nX\u008a\u0084\u0002²\u0006\f\u0010\u001c\u001a\u00020\u001a8\nX\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/ui/components/SidebarItem;", "selectedItem", "", "isSidebarFocused", "", "focusedIndex", "Lcom/arflix/tv/data/model/Profile;", "profile", "hasUpdateBadge", "Lkotlin/Function0;", "Lx6/t0;", "onProfileClick", "Lkotlin/Function1;", "onItemSelected", "Landroidx/compose/ui/Modifier;", "modifier", "Sidebar", "(Lcom/arflix/tv/ui/components/SidebarItem;ZILcom/arflix/tv/data/model/Profile;ZLr7/a;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "isFocused", "SidebarProfileAvatar", "(Lcom/arflix/tv/data/model/Profile;ZLandroidx/compose/runtime/Composer;I)V", "item", "isSelected", "hasBadge", "SidebarIcon", "(Lcom/arflix/tv/ui/components/SidebarItem;ZZZLandroidx/compose/runtime/Composer;II)V", "", "scale", "indicatorAlpha", "Landroidx/compose/ui/graphics/Color;", "iconColor", "chipBackground", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SidebarKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:215:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0126  */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r30v2 */
    /* JADX WARN: Type inference failed for: r36v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r41v10 */
    /* JADX WARN: Type inference failed for: r41v3 */
    /* JADX WARN: Type inference failed for: r41v4 */
    /* JADX WARN: Type inference failed for: r41v5 */
    /* JADX WARN: Type inference failed for: r41v6 */
    /* JADX WARN: Type inference failed for: r41v8 */
    /* JADX WARN: Type inference failed for: r41v9 */
    /* JADX WARN: Type inference failed for: r8v24, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Sidebar(com.arflix.tv.ui.components.SidebarItem r33, boolean r34, int r35, com.arflix.tv.data.model.Profile r36, boolean r37, r7.a<x6.t0> r38, r7.l<? super com.arflix.tv.ui.components.SidebarItem, x6.t0> r39, androidx.compose.ui.Modifier r40, androidx.compose.runtime.Composer r41, final int r42, final int r43) {
        /*
            Method dump skipped, instruction units count: 1155
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SidebarKt.Sidebar(com.arflix.tv.ui.components.SidebarItem, boolean, int, com.arflix.tv.data.model.Profile, boolean, r7.a, r7.l, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 Sidebar$lambda$1$0(SidebarItem sidebarItem) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 Sidebar$lambda$3(SidebarItem sidebarItem, boolean z, int i10, Profile profile, boolean z5, r7.a aVar, r7.l lVar, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        Sidebar(sidebarItem, z, i10, profile, z5, aVar, lVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SidebarIcon(com.arflix.tv.ui.components.SidebarItem r33, boolean r34, boolean r35, boolean r36, androidx.compose.runtime.Composer r37, int r38, int r39) {
        /*
            Method dump skipped, instruction units count: 898
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SidebarKt.SidebarIcon(com.arflix.tv.ui.components.SidebarItem, boolean, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final long SidebarIcon$lambda$0(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final float SidebarIcon$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final long SidebarIcon$lambda$2(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final float SidebarIcon$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SidebarIcon$lambda$4$0$0$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(SidebarIcon$lambda$1(state));
        graphicsLayerScope.setScaleY(SidebarIcon$lambda$1(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SidebarIcon$lambda$5(SidebarItem sidebarItem, boolean z, boolean z5, boolean z10, int i10, int i11, Composer composer, int i12) {
        SidebarIcon(sidebarItem, z, z5, z10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final void SidebarProfileAvatar(Profile profile, boolean z, Composer composer, int i10) {
        int i11;
        Profile profile2 = profile;
        Composer composerStartRestartGroup = composer.startRestartGroup(-274213528);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(profile2) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-274213528, i11, -1, "com.arflix.tv.ui.components.SidebarProfileAvatar (Sidebar.kt:152)");
            }
            float f10 = z ? 1.12f : 1.0f;
            AnimationConstants animationConstants = AnimationConstants.INSTANCE;
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f10, AnimationSpecKt.tween$default(150, 0, animationConstants.getEaseOut(), 2, null), 0.0f, "profile_scale", null, composerStartRestartGroup, 3072, 20);
            State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, animationConstants.getEaseOut(), 2, null), 0.0f, "profile_indicator", null, composerStartRestartGroup, 3072, 20);
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(42)), Dp.m5678constructorimpl(34));
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment center = companion2.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM564height3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (SidebarProfileAvatar$lambda$1(stateAnimateFloatAsState2) > 0.01f) {
                composerStartRestartGroup.startReplaceGroup(-972161410);
                BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(boxScopeInstance.align(companion, companion2.getCenterStart()), Dp.m5678constructorimpl(3)), Dp.m5678constructorimpl(22)), MediaError.DetailedErrorCode.GENERIC), Color.m3471copywmQWz5c$default(ArvioSkin.INSTANCE.getColors(composerStartRestartGroup, 6).m6491getFocusOutline0d7_KjU(), SidebarProfileAvatar$lambda$1(stateAnimateFloatAsState2), 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-971832748);
                composerStartRestartGroup.endReplaceGroup();
            }
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(ClipKt.clip(SizeKt.m578size3ABfNKs(boxScopeInstance.align(companion, companion2.getCenter()), Dp.m5678constructorimpl(30)), RoundedCornerShapeKt.getCircleShape()), Color.INSTANCE.m3507getTransparent0d7_KjU(), null, 2, null);
            boolean zChanged = composerStartRestartGroup.changed(stateAnimateFloatAsState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new c(stateAnimateFloatAsState, 5);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierM179backgroundbw27NRU$default, (r7.l) objRememberedValue);
            Alignment center2 = companion2.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            profile2 = profile;
            ProfileAvatarVisualKt.m6181ProfileAvatarVisualxjFF98(profile2, null, TextUnitKt.getSp(12), Dp.m5678constructorimpl(3), composerStartRestartGroup, (i11 & 14) | 3456, 2);
            if (androidx.compose.foundation.c.A(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new d(profile2, z, i10, 1));
        }
    }

    private static final float SidebarProfileAvatar$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float SidebarProfileAvatar$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SidebarProfileAvatar$lambda$2$0$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(SidebarProfileAvatar$lambda$0(state));
        graphicsLayerScope.setScaleY(SidebarProfileAvatar$lambda$0(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SidebarProfileAvatar$lambda$3(Profile profile, boolean z, int i10, Composer composer, int i11) {
        SidebarProfileAvatar(profile, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }
}
