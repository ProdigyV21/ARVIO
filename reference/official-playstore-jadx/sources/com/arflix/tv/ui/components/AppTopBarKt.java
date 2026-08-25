package com.arflix.tv.ui.components;

import android.content.Context;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.IconKt;
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
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.repository.CloudSyncStatus;
import com.arflix.tv.ui.skin.ArvioSkinKt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u001f\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\n\u0010\u000b\u001ae\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a'\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\u001c\u0010\u001d\u001a)\u0010\u001f\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u001e\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\u001f\u0010 \u001a\u001f\u0010!\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0000H\u0003¢\u0006\u0004\b!\u0010\"\u001a\u0017\u0010#\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0003¢\u0006\u0004\b#\u0010$\u001a\u0017\u0010%\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b%\u0010&\"\u0017\u0010(\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0017\u0010,\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b,\u0010)\u001a\u0004\b-\u0010+\"\u0017\u0010.\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b.\u0010)\u001a\u0004\b/\u0010+\"\u0017\u00100\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b0\u0010)\u001a\u0004\b1\u0010+\"\u0017\u00102\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b2\u0010)\u001a\u0004\b3\u0010+\"\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\u0005048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106¨\u0006?²\u0006\f\u00108\u001a\u0002078\nX\u008a\u0084\u0002²\u0006\f\u00109\u001a\u0002078\nX\u008a\u0084\u0002²\u0006\f\u0010:\u001a\u0002078\nX\u008a\u0084\u0002²\u0006\f\u0010<\u001a\u00020;8\nX\u008a\u0084\u0002²\u0006\f\u00109\u001a\u0002078\nX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u0002078\nX\u008a\u0084\u0002²\u0006\f\u0010<\u001a\u00020;8\nX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u0002078\nX\u008a\u0084\u0002²\u0006\f\u0010<\u001a\u00020;8\nX\u008a\u0084\u0002²\u0006\u000e\u0010=\u001a\u00020\u00108\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010>\u001a\u00020\u00108\n@\nX\u008a\u008e\u0002"}, d2 = {"", "hasProfile", "", "topBarMaxIndex", "(Z)I", "Lcom/arflix/tv/ui/components/SidebarItem;", "selectedItem", "topBarSelectedIndex", "(Lcom/arflix/tv/ui/components/SidebarItem;Z)I", "focusedIndex", "topBarFocusedItem", "(IZ)Lcom/arflix/tv/ui/components/SidebarItem;", "isFocused", "Lcom/arflix/tv/data/model/Profile;", "profile", "profileCount", "", "clockFormat", "Lcom/arflix/tv/data/repository/CloudSyncStatus;", "syncStatus", "hasUpdateBadge", "Landroidx/compose/ui/Modifier;", "modifier", "Lx6/t0;", "AppTopBar", "(Lcom/arflix/tv/ui/components/SidebarItem;ZILcom/arflix/tv/data/model/Profile;ILjava/lang/String;Lcom/arflix/tv/data/repository/CloudSyncStatus;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "item", "isSelected", "TopBarNavChip", "(Lcom/arflix/tv/ui/components/SidebarItem;ZZLandroidx/compose/runtime/Composer;I)V", "hasBadge", "TopBarSettingsGear", "(ZZZLandroidx/compose/runtime/Composer;II)V", "TopBarProfileAvatar", "(Lcom/arflix/tv/data/model/Profile;ZLandroidx/compose/runtime/Composer;I)V", "rememberTopBarTime", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "topBarCurrentTime", "(Ljava/lang/String;)Ljava/lang/String;", "Landroidx/compose/ui/unit/Dp;", "AppTopBarHeight", "F", "getAppTopBarHeight", "()F", "AppTopBarTopPadding", "getAppTopBarTopPadding", "AppTopBarContentTopInset", "getAppTopBarContentTopInset", "MobileContentTopInset", "getMobileContentTopInset", "AppTopBarHorizontalPadding", "getAppTopBarHorizontalPadding", "", "NAV_ITEMS", "Ljava/util/List;", "Landroidx/compose/ui/graphics/Color;", "containerColor", "iconColor", "textColor", "", "scale", "resolvedFormat", "currentTime", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AppTopBarKt {
    private static final List<SidebarItem> NAV_ITEMS;
    private static final float AppTopBarHeight = Dp.m5678constructorimpl(82);
    private static final float AppTopBarTopPadding = Dp.m5678constructorimpl(0);
    private static final float AppTopBarContentTopInset = Dp.m5678constructorimpl(98);
    private static final float MobileContentTopInset = Dp.m5678constructorimpl(16);
    private static final float AppTopBarHorizontalPadding = Dp.m5678constructorimpl(28);

    static {
        g7.a<SidebarItem> entries = SidebarItem.getEntries();
        ArrayList arrayList = new ArrayList();
        for (SidebarItem sidebarItem : entries) {
            if (sidebarItem != SidebarItem.SETTINGS) {
                arrayList.add(sidebarItem);
            }
        }
        NAV_ITEMS = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02e4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0497  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x055a  */
    /* JADX WARN: Removed duplicated region for block: B:228:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0136  */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r20v5 */
    /* JADX WARN: Type inference failed for: r27v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r33v0, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r33v1, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r33v2 */
    /* JADX WARN: Type inference failed for: r33v3 */
    /* JADX WARN: Type inference failed for: r43v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r44v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v1, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v40 */
    /* JADX WARN: Type inference failed for: r9v6, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppTopBar(com.arflix.tv.ui.components.SidebarItem r40, boolean r41, int r42, com.arflix.tv.data.model.Profile r43, int r44, java.lang.String r45, com.arflix.tv.data.repository.CloudSyncStatus r46, boolean r47, androidx.compose.ui.Modifier r48, androidx.compose.runtime.Composer r49, int r50, int r51) {
        /*
            Method dump skipped, instruction units count: 1387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.AppTopBarKt.AppTopBar(com.arflix.tv.ui.components.SidebarItem, boolean, int, com.arflix.tv.data.model.Profile, int, java.lang.String, com.arflix.tv.data.repository.CloudSyncStatus, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AppTopBar$lambda$2(SidebarItem sidebarItem, boolean z, int i10, Profile profile, int i11, String str, CloudSyncStatus cloudSyncStatus, boolean z5, Modifier modifier, int i12, int i13, Composer composer, int i14) {
        AppTopBar(sidebarItem, z, i10, profile, i11, str, cloudSyncStatus, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), i13);
        return x6.t0.f22605a;
    }

    private static final void TopBarNavChip(final SidebarItem sidebarItem, final boolean z, final boolean z5, Composer composer, final int i10) {
        int i11;
        String strStringResource;
        Composer composerStartRestartGroup = composer.startRestartGroup(15058964);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(sidebarItem.ordinal()) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(15058964, i11, -1, "com.arflix.tv.ui.components.TopBarNavChip (AppTopBar.kt:195)");
            }
            Color.Companion companion = Color.INSTANCE;
            long jM6519resolveAccentColorek8zF_U = ArvioSkinKt.m6519resolveAccentColorek8zF_U(companion.m3509getWhite0d7_KjU(), composerStartRestartGroup, 6);
            State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(z ? Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null) : z5 ? Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null) : companion.m3507getTransparent0d7_KjU(), AnimationSpecKt.tween$default(150, 0, null, 6, null), "topbar_chip_bg", null, composerStartRestartGroup, 432, 8);
            State<Color> stateM69animateColorAsStateeuL9pac2 = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(z ? companion.m3509getWhite0d7_KjU() : z5 ? jM6519resolveAccentColorek8zF_U : Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.62f, 0.0f, 0.0f, 0.0f, 14, null), AnimationSpecKt.tween$default(150, 0, null, 6, null), "topbar_icon_color", null, composerStartRestartGroup, 432, 8);
            if (z) {
                jM6519resolveAccentColorek8zF_U = companion.m3509getWhite0d7_KjU();
            } else if (!z5) {
                jM6519resolveAccentColorek8zF_U = Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.68f, 0.0f, 0.0f, 0.0f, 14, null);
            }
            State<Color> stateM69animateColorAsStateeuL9pac3 = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(jM6519resolveAccentColorek8zF_U, AnimationSpecKt.tween$default(150, 0, null, 6, null), "topbar_text_color", null, composerStartRestartGroup, 432, 8);
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.05f : 1.0f, AnimationSpecKt.spring$default(0.75f, 400.0f, null, 4, null), 0.0f, "topbar_scale", null, composerStartRestartGroup, 3120, 20);
            if (sidebarItem == SidebarItem.TV) {
                composerStartRestartGroup.startReplaceGroup(1946509116);
                strStringResource = StringResources_androidKt.stringResource(R.string.topbar_tv, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1946564513);
                strStringResource = StringResources_androidKt.stringResource(sidebarItem.getLabelRes(), composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            }
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(16, companion2), TopBarNavChip$lambda$0(stateM69animateColorAsStateeuL9pac), null, 2, null);
            boolean zChanged = composerStartRestartGroup.changed(stateAnimateFloatAsState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new c(stateAnimateFloatAsState, 2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(GraphicsLayerModifierKt.graphicsLayer(modifierM179backgroundbw27NRU$default, (r7.l) objRememberedValue), Dp.m5678constructorimpl(14), Dp.m5678constructorimpl(9));
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(8, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 54);
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
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(sidebarItem.getIcon(), strStringResource, SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(18)), TopBarNavChip$lambda$1(stateM69animateColorAsStateeuL9pac2), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
            TextKt.m6020Text4IGK_g(strStringResource, null, TopBarNavChip$lambda$2(stateM69animateColorAsStateeuL9pac3), TextUnitKt.getSp(14), null, (z || z5) ? FontWeight.INSTANCE.getSemiBold() : FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, null, composerStartRestartGroup, 3072, 3120, 120786);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.f
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AppTopBarKt.TopBarNavChip$lambda$6(sidebarItem, z, z5, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    private static final long TopBarNavChip$lambda$0(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final long TopBarNavChip$lambda$1(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final long TopBarNavChip$lambda$2(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final float TopBarNavChip$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TopBarNavChip$lambda$4$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(TopBarNavChip$lambda$3(state));
        graphicsLayerScope.setScaleY(TopBarNavChip$lambda$3(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TopBarNavChip$lambda$6(SidebarItem sidebarItem, boolean z, boolean z5, int i10, Composer composer, int i11) {
        TopBarNavChip(sidebarItem, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void TopBarProfileAvatar(Profile profile, boolean z, Composer composer, int i10) {
        int i11;
        Composer composer2;
        int i12 = 0;
        Composer composerStartRestartGroup = composer.startRestartGroup(470459747);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(profile) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(470459747, i11, -1, "com.arflix.tv.ui.components.TopBarProfileAvatar (AppTopBar.kt:342)");
            }
            Color.Companion companion = Color.INSTANCE;
            State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(z ? Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null) : companion.m3507getTransparent0d7_KjU(), AnimationSpecKt.tween$default(150, 0, null, 6, null), "topbar_profile_bg", null, composerStartRestartGroup, 432, 8);
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.08f : 1.0f, AnimationSpecKt.spring$default(0.75f, 400.0f, null, 4, null), 0.0f, "topbar_profile_scale", null, composerStartRestartGroup, 3120, 20);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(ClipKt.clip(SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(40)), RoundedCornerShapeKt.getCircleShape()), TopBarProfileAvatar$lambda$0(stateM69animateColorAsStateeuL9pac), null, 2, null);
            boolean zChanged = composerStartRestartGroup.changed(stateAnimateFloatAsState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new c(stateAnimateFloatAsState, i12);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierM179backgroundbw27NRU$default, (r7.l) objRememberedValue);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment center = companion3.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
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
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierM179backgroundbw27NRU$default2 = BackgroundKt.m179backgroundbw27NRU$default(ClipKt.clip(SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(34)), RoundedCornerShapeKt.getCircleShape()), Color.INSTANCE.m3507getTransparent0d7_KjU(), null, 2, null);
            Alignment center2 = companion3.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default2);
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
            r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            composer2 = composerStartRestartGroup;
            ProfileAvatarVisualKt.m6181ProfileAvatarVisualxjFF98(profile, null, TextUnitKt.getSp(13), Dp.m5678constructorimpl(4), composer2, (i11 & 14) | 3456, 2);
            if (androidx.compose.foundation.c.A(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new d(profile, z, i10, 0));
        }
    }

    private static final long TopBarProfileAvatar$lambda$0(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final float TopBarProfileAvatar$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TopBarProfileAvatar$lambda$2$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(TopBarProfileAvatar$lambda$1(state));
        graphicsLayerScope.setScaleY(TopBarProfileAvatar$lambda$1(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TopBarProfileAvatar$lambda$4(Profile profile, boolean z, int i10, Composer composer, int i11) {
        TopBarProfileAvatar(profile, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void TopBarSettingsGear(final boolean r27, final boolean r28, boolean r29, androidx.compose.runtime.Composer r30, final int r31, final int r32) {
        /*
            Method dump skipped, instruction units count: 603
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.AppTopBarKt.TopBarSettingsGear(boolean, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final long TopBarSettingsGear$lambda$0(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final long TopBarSettingsGear$lambda$1(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final float TopBarSettingsGear$lambda$2(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TopBarSettingsGear$lambda$3$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(TopBarSettingsGear$lambda$2(state));
        graphicsLayerScope.setScaleY(TopBarSettingsGear$lambda$2(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TopBarSettingsGear$lambda$5(boolean z, boolean z5, boolean z10, int i10, int i11, Composer composer, int i12) {
        TopBarSettingsGear(z, z5, z10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final float getAppTopBarContentTopInset() {
        return AppTopBarContentTopInset;
    }

    public static final float getAppTopBarHeight() {
        return AppTopBarHeight;
    }

    public static final float getAppTopBarHorizontalPadding() {
        return AppTopBarHorizontalPadding;
    }

    public static final float getAppTopBarTopPadding() {
        return AppTopBarTopPadding;
    }

    public static final float getMobileContentTopInset() {
        return MobileContentTopInset;
    }

    private static final String rememberTopBarTime(String str, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1395148880, i10, -1, "com.arflix.tv.ui.components.rememberTopBarTime (AppTopBar.kt:382)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        int i11 = (i10 & 14) ^ 6;
        boolean z = true;
        boolean z5 = (i11 > 4 && composer.changed(str)) || (i10 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        boolean zChanged = composer.changed(rememberTopBarTime$lambda$1(mutableState));
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(topBarCurrentTime(rememberTopBarTime$lambda$1(mutableState)), null, 2, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        MutableState mutableState2 = (MutableState) objRememberedValue2;
        boolean zChangedInstance = composer.changedInstance(context) | composer.changed(mutableState);
        if ((i11 <= 4 || !composer.changed(str)) && (i10 & 6) != 4) {
            z = false;
        }
        boolean z10 = zChangedInstance | z;
        Object objRememberedValue3 = composer.rememberedValue();
        if (z10 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new AppTopBarKt$rememberTopBarTime$1$1(context, str, mutableState, null);
            composer.updateRememberedValue(objRememberedValue3);
        }
        EffectsKt.LaunchedEffect(context, str, (r7.p) objRememberedValue3, composer, (i10 << 3) & 112);
        String strRememberTopBarTime$lambda$1 = rememberTopBarTime$lambda$1(mutableState);
        boolean zChanged2 = composer.changed(mutableState2) | composer.changed(mutableState);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new AppTopBarKt$rememberTopBarTime$2$1(mutableState, mutableState2, null);
            composer.updateRememberedValue(objRememberedValue4);
        }
        EffectsKt.LaunchedEffect(strRememberTopBarTime$lambda$1, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue4, composer, 0);
        String strRememberTopBarTime$lambda$4 = rememberTopBarTime$lambda$4(mutableState2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strRememberTopBarTime$lambda$4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String rememberTopBarTime$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String rememberTopBarTime$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String topBarCurrentTime(String str) {
        return new SimpleDateFormat(kotlin.jvm.internal.p.a(str, "12h") ? "h:mm a" : "HH:mm", Locale.getDefault()).format(new Date());
    }

    public static final SidebarItem topBarFocusedItem(int i10, boolean z) {
        if (z && i10 == 0) {
            return null;
        }
        if (z) {
            i10--;
        }
        List<SidebarItem> list = NAV_ITEMS;
        return i10 == list.size() ? SidebarItem.SETTINGS : (SidebarItem) kotlin.collections.x.p0(i10, list);
    }

    public static final int topBarMaxIndex(boolean z) {
        int size = NAV_ITEMS.size();
        return z ? size + 1 : size;
    }

    public static final int topBarSelectedIndex(SidebarItem sidebarItem, boolean z) {
        if (sidebarItem == SidebarItem.SETTINGS) {
            return topBarMaxIndex(z);
        }
        int iIndexOf = NAV_ITEMS.indexOf(sidebarItem);
        if (iIndexOf < 0) {
            return -1;
        }
        return z ? iIndexOf + 1 : iIndexOf;
    }
}
