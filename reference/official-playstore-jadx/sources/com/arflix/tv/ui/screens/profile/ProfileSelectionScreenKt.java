package com.arflix.tv.ui.screens.profile;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CloudKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.tv.material3.ClickableSurfaceDefaults;
import androidx.tv.material3.IconKt;
import androidx.tv.material3.SurfaceKt;
import androidx.tv.material3.TextKt;
import androidx.tv.material3.g1;
import com.arflix.tv.R;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.components.ProfileAvatarVisualKt;
import com.arflix.tv.ui.components.w1;
import com.arflix.tv.ui.components.x1;
import com.arflix.tv.ui.screens.details.p0;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.arflix.tv.util.DeviceType;
import com.arflix.tv.util.DeviceTypeKt;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u001aO\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\t\u0010\n\u001ai\u0010\u0018\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0003¢\u0006\u0004\b\u0016\u0010\u0017\u001a1\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a%\u0010\u001c\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0003¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001d\u0010\u001e\u001a\u00020\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0003¢\u0006\u0004\b\u001e\u0010\u001f¨\u0006(²\u0006\f\u0010!\u001a\u00020 8\nX\u008a\u0084\u0002²\u0006\u000e\u0010\"\u001a\u00020\u00078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010#\u001a\u00020\u00078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010%\u001a\u00020$8\n@\nX\u008a\u008e\u0002²\u0006\f\u0010'\u001a\u00020&8\nX\u008a\u0084\u0002²\u0006\u000e\u0010%\u001a\u00020$8\n@\nX\u008a\u008e\u0002²\u0006\f\u0010'\u001a\u00020&8\nX\u008a\u0084\u0002²\u0006\u000e\u0010%\u001a\u00020$8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010%\u001a\u00020$8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/profile/ProfileViewModel;", "viewModel", "Lkotlin/Function0;", "Lx6/t0;", "onProfileSelected", "onShowAddProfile", "onConnectCloud", "", "isCloudConnected", "ProfileSelectionScreen", "(Lcom/arflix/tv/ui/screens/profile/ProfileViewModel;Lr7/a;Lr7/a;Lr7/a;ZLandroidx/compose/runtime/Composer;II)V", "Lcom/arflix/tv/data/model/Profile;", "profile", "isManageMode", "isActiveProfile", "Landroidx/compose/ui/unit/Dp;", "avatarSize", "Landroidx/compose/ui/Modifier;", "modifier", "onClick", "onFocus", "onDelete", "ProfileAvatar-aA_HZ9I", "(Lcom/arflix/tv/data/model/Profile;ZZFLandroidx/compose/ui/Modifier;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "ProfileAvatar", "AddProfileButton-Kz89ssw", "(FLandroidx/compose/ui/Modifier;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "AddProfileButton", "ManageProfilesButton", "(ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "CloudConnectButton", "(Lr7/a;Landroidx/compose/runtime/Composer;I)V", "Lcom/arflix/tv/ui/screens/profile/ProfileUiState;", "uiState", "navigateTriggered", "isReadyForInput", "", "isFocused", "", "scale", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ProfileSelectionScreenKt {
    /* JADX WARN: Removed duplicated region for block: B:100:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0258  */
    /* JADX INFO: renamed from: AddProfileButton-Kz89ssw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6278AddProfileButtonKz89ssw(float r40, androidx.compose.ui.Modifier r41, r7.a<x6.t0> r42, androidx.compose.runtime.Composer r43, int r44, int r45) {
        /*
            Method dump skipped, instruction units count: 1041
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt.m6278AddProfileButtonKz89ssw(float, androidx.compose.ui.Modifier, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float AddProfileButton_Kz89ssw$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddProfileButton_Kz89ssw$lambda$4$0$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddProfileButton_Kz89ssw$lambda$4$2$0(MutableIntState mutableIntState, FocusState focusState) {
        mutableIntState.setIntValue(focusState.isFocused() ? 1 : 0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddProfileButton_Kz89ssw$lambda$4$3(r7.p pVar, BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1770813982, i10, -1, "com.arflix.tv.ui.screens.profile.AddProfileButton.<anonymous>.<anonymous> (ProfileSelectionScreen.kt:543)");
            }
            if (a0.c.A(composer, 6, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddProfileButton_Kz89ssw$lambda$5(float f10, Modifier modifier, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        m6278AddProfileButtonKz89ssw(f10, modifier, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    private static final void CloudConnectButton(r7.a<t0> aVar, Composer composer, int i10) {
        int i11;
        r7.a<t0> aVar2 = aVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(1763651109);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changedInstance(aVar2) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1763651109, i11, -1, "com.arflix.tv.ui.screens.profile.CloudConnectButton (ProfileSelectionScreen.kt:604)");
            }
            boolean zIsTouchDevice = ((DeviceType) composerStartRestartGroup.consume(DeviceTypeKt.getLocalDeviceType())).isTouchDevice();
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
            if (zIsTouchDevice) {
                composerStartRestartGroup.startReplaceGroup(595497278);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                float f10 = 24;
                Modifier modifierBackground$default = BackgroundKt.background$default(com.arflix.tv.data.repository.g.i(f10, companion2), Brush.Companion.m3427horizontalGradient8A3gB4$default(Brush.INSTANCE, t7.a.E(Color.m3462boximpl(Color.m3471copywmQWz5c$default(ColorKt.Color(4285293567L), 0.25f, 0.0f, 0.0f, 0.0f, 14, null)), Color.m3462boximpl(Color.m3471copywmQWz5c$default(ColorKt.Color(4278244607L), 0.18f, 0.0f, 0.0f, 0.0f, 14, null))), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
                float fM5678constructorimpl = Dp.m5678constructorimpl(1);
                Color.Companion companion3 = Color.INSTANCE;
                Modifier modifierK = com.arflix.tv.data.repository.g.k(f10, modifierBackground$default, fM5678constructorimpl, Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null));
                boolean z = (i11 & 14) == 4;
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z || objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new com.arflix.tv.ui.components.g(29, aVar2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue2, 7, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(12));
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(10, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 54);
                composerStartRestartGroup.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor = companion4.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
                r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
                if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
                }
                a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                IconKt.m5992Iconww6aTOc(CloudKt.getCloud(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(18)), Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), composerStartRestartGroup, 3504, 0);
                TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.connect_to_cloud, composerStartRestartGroup, 0), null, Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, 200064, 0, 131026);
                composerStartRestartGroup = composerStartRestartGroup;
                com.arflix.tv.data.repository.g.q(composerStartRestartGroup);
            } else {
                composerStartRestartGroup.startReplaceGroup(596734364);
                Modifier.Companion companion5 = Modifier.INSTANCE;
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = new w1(mutableIntState, 5);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(companion5, (r7.l) objRememberedValue3);
                float f11 = 24;
                RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11));
                ClickableSurfaceDefaults clickableSurfaceDefaults = ClickableSurfaceDefaults.f5608a;
                g1 g1VarShape = clickableSurfaceDefaults.shape(roundedCornerShapeM798RoundedCornerShape0680j_4, null, null, null, null, composerStartRestartGroup, 0, 30);
                Color.Companion companion6 = Color.INSTANCE;
                aVar2 = aVar;
                SurfaceKt.m6007Surface05tvjtU(aVar2, modifierOnFocusChanged, null, false, 0.0f, g1VarShape, clickableSurfaceDefaults.m5986colorsoq7We08(Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null), 0L, Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.18f, 0.0f, 0.0f, 0.0f, 14, null), 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 390, AnimationConstants.DURATION_IMAGE_CROSSFADE), null, clickableSurfaceDefaults.border(new androidx.tv.material3.d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11)), 2), new androidx.tv.material3.d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(2), Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11)), 2), null, null, null, composerStartRestartGroup, 0, 28), null, null, ComposableLambdaKt.rememberComposableLambda(-952067126, true, new x1(mutableIntState, 2), composerStartRestartGroup, 54), composerStartRestartGroup, i11 & 14, 48, 1692);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new e0(i10, 0, aVar2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CloudConnectButton$lambda$3$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CloudConnectButton$lambda$5$0(MutableIntState mutableIntState, FocusState focusState) {
        mutableIntState.setIntValue(focusState.isFocused() ? 1 : 0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CloudConnectButton$lambda$6(MutableIntState mutableIntState, BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-952067126, i10, -1, "com.arflix.tv.ui.screens.profile.CloudConnectButton.<anonymous> (ProfileSelectionScreen.kt:659)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(companion, Dp.m5678constructorimpl(24), Dp.m5678constructorimpl(12));
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(10, Arrangement.INSTANCE, composer, 693286680), Alignment.INSTANCE.getCenterVertically(), composer, 54);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m5992Iconww6aTOc(CloudKt.getCloud(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(18)), mutableIntState.getIntValue() > 0 ? Color.INSTANCE.m3509getWhite0d7_KjU() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), composer, 432, 0);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.connect_to_cloud, composer, 0), null, mutableIntState.getIntValue() > 0 ? Color.INSTANCE.m3509getWhite0d7_KjU() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 199680, 0, 131026);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CloudConnectButton$lambda$7(r7.a aVar, int i10, Composer composer, int i11) {
        CloudConnectButton(aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void ManageProfilesButton(final boolean z, r7.a<t0> aVar, Composer composer, int i10) {
        int i11;
        r7.a<t0> aVar2;
        Modifier modifierM214clickableXHw0xAI$default;
        Composer composerStartRestartGroup = composer.startRestartGroup(1762473615);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1762473615, i11, -1, "com.arflix.tv.ui.screens.profile.ManageProfilesButton (ProfileSelectionScreen.kt:563)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
            boolean zIsTouchDevice = ((DeviceType) composerStartRestartGroup.consume(DeviceTypeKt.getLocalDeviceType())).isTouchDevice();
            if (zIsTouchDevice) {
                composerStartRestartGroup.startReplaceGroup(-123479087);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new com.arflix.tv.ui.screens.player.d0(14);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                composerStartRestartGroup.endReplaceGroup();
                aVar2 = (r7.a) objRememberedValue2;
            } else {
                composerStartRestartGroup.startReplaceGroup(1520037750);
                composerStartRestartGroup.endReplaceGroup();
                aVar2 = aVar;
            }
            Modifier.Companion companion2 = Modifier.INSTANCE;
            if (zIsTouchDevice) {
                composerStartRestartGroup.startReplaceGroup(1520040966);
                boolean z5 = (i11 & 112) == 32;
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z5 || objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = new com.arflix.tv.ui.components.g(27, aVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(companion2, false, null, null, (r7.a) objRememberedValue3, 7, null);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1520041879);
                composerStartRestartGroup.endReplaceGroup();
                modifierM214clickableXHw0xAI$default = companion2;
            }
            Modifier modifierThen = companion2.then(modifierM214clickableXHw0xAI$default);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new w1(mutableIntState, 4);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierThen, (r7.l) objRememberedValue4);
            float f10 = 4;
            RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10));
            ClickableSurfaceDefaults clickableSurfaceDefaults = ClickableSurfaceDefaults.f5608a;
            g1 g1VarShape = clickableSurfaceDefaults.shape(roundedCornerShapeM798RoundedCornerShape0680j_4, null, null, null, null, composerStartRestartGroup, 0, 30);
            Color.Companion companion3 = Color.INSTANCE;
            SurfaceKt.m6007Surface05tvjtU(aVar2, modifierOnFocusChanged, null, false, 0.0f, g1VarShape, clickableSurfaceDefaults.m5986colorsoq7We08(companion3.m3507getTransparent0d7_KjU(), 0L, Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 390, AnimationConstants.DURATION_IMAGE_CROSSFADE), null, clickableSurfaceDefaults.border(new androidx.tv.material3.d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)), 2), new androidx.tv.material3.d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(2), companion3.m3509getWhite0d7_KjU()), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)), 2), null, null, null, composerStartRestartGroup, 0, 28), null, null, ComposableLambdaKt.rememberComposableLambda(-221831856, true, new r7.q() { // from class: com.arflix.tv.ui.screens.profile.z
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return ProfileSelectionScreenKt.ManageProfilesButton$lambda$6(z, (BoxScope) obj, (Composer) obj2, iIntValue);
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 0, 48, 1692);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p0(z, aVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ManageProfilesButton$lambda$4$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ManageProfilesButton$lambda$5$0(MutableIntState mutableIntState, FocusState focusState) {
        mutableIntState.setIntValue(focusState.isFocused() ? 1 : 0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ManageProfilesButton$lambda$6(boolean z, BoxScope boxScope, Composer composer, int i10) {
        int i11;
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-221831856, i10, -1, "com.arflix.tv.ui.screens.profile.ManageProfilesButton.<anonymous> (ProfileSelectionScreen.kt:590)");
            }
            if (z) {
                composer.startReplaceGroup(1567952909);
                i11 = R.string.done;
            } else {
                composer.startReplaceGroup(1567954040);
                i11 = R.string.manage_profiles;
            }
            String strStringResource = StringResources_androidKt.stringResource(i11, composer, 0);
            composer.endReplaceGroup();
            TextKt.m6020Text4IGK_g(strStringResource, PaddingKt.m530paddingVpY3zN4(Modifier.INSTANCE, Dp.m5678constructorimpl(24), Dp.m5678constructorimpl(12)), Color.INSTANCE.m3509getWhite0d7_KjU(), TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 200112, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ManageProfilesButton$lambda$7(boolean z, r7.a aVar, int i10, Composer composer, int i11) {
        ManageProfilesButton(z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x05a4  */
    /* JADX WARN: Removed duplicated region for block: B:198:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0195  */
    /* JADX INFO: renamed from: ProfileAvatar-aA_HZ9I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6279ProfileAvataraA_HZ9I(final com.arflix.tv.data.model.Profile r52, final boolean r53, boolean r54, float r55, androidx.compose.ui.Modifier r56, r7.a<x6.t0> r57, r7.a<x6.t0> r58, final r7.a<x6.t0> r59, androidx.compose.runtime.Composer r60, final int r61, final int r62) {
        /*
            Method dump skipped, instruction units count: 1466
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt.m6279ProfileAvataraA_HZ9I(com.arflix.tv.data.model.Profile, boolean, boolean, float, androidx.compose.ui.Modifier, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float ProfileAvatar_aA_HZ9I$lambda$4(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileAvatar_aA_HZ9I$lambda$5$0$0(Profile profile, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-152332630, i10, -1, "com.arflix.tv.ui.screens.profile.ProfileAvatar.<anonymous>.<anonymous>.<anonymous> (ProfileSelectionScreen.kt:412)");
            }
            ProfileAvatarVisualKt.m6181ProfileAvatarVisualxjFF98(profile, null, TextUnitKt.getSp(48), Dp.m5678constructorimpl(12), composer, 3456, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileAvatar_aA_HZ9I$lambda$5$0$1$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileAvatar_aA_HZ9I$lambda$5$0$3$0(r7.a aVar, MutableIntState mutableIntState, FocusState focusState) {
        boolean z = mutableIntState.getIntValue() > 0;
        mutableIntState.setIntValue(focusState.isFocused() ? 1 : 0);
        if (!z && focusState.isFocused()) {
            aVar.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileAvatar_aA_HZ9I$lambda$5$0$4(r7.p pVar, BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(219273485, i10, -1, "com.arflix.tv.ui.screens.profile.ProfileAvatar.<anonymous>.<anonymous>.<anonymous> (ProfileSelectionScreen.kt:451)");
            }
            if (a0.c.A(composer, 6, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileAvatar_aA_HZ9I$lambda$5$0$5$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileAvatar_aA_HZ9I$lambda$6(Profile profile, boolean z, boolean z5, float f10, Modifier modifier, r7.a aVar, r7.a aVar2, r7.a aVar3, int i10, int i11, Composer composer, int i12) {
        m6279ProfileAvataraA_HZ9I(profile, z, z5, f10, modifier, aVar, aVar2, aVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x04b2  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x05aa  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x075d  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0786  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x078e  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x079a  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x07a2  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x07b0  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x07b6  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x07c8  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x07d3  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0810  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0829  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0914  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0921  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x093f  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0999  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x09b0  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0a15  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0a2e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0b54  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0b77  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0b7f  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0bac  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0bc4  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0bcf  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x0dcf  */
    /* JADX WARN: Removed duplicated region for block: B:588:0x0df8  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x0e00  */
    /* JADX WARN: Removed duplicated region for block: B:593:0x0e21  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:655:0x0f40  */
    /* JADX WARN: Removed duplicated region for block: B:658:0x0f4f  */
    /* JADX WARN: Removed duplicated region for block: B:662:0x0f5d  */
    /* JADX WARN: Removed duplicated region for block: B:669:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01c4  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ProfileSelectionScreen(com.arflix.tv.ui.screens.profile.ProfileViewModel r56, r7.a<x6.t0> r57, r7.a<x6.t0> r58, r7.a<x6.t0> r59, boolean r60, androidx.compose.runtime.Composer r61, int r62, int r63) {
        /*
            Method dump skipped, instruction units count: 3950
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt.ProfileSelectionScreen(com.arflix.tv.ui.screens.profile.ProfileViewModel, r7.a, r7.a, r7.a, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ProfileUiState ProfileSelectionScreen$lambda$1(State<ProfileUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$1$0(final State state, final float f10, final List list, final ProfileViewModel profileViewModel, MutableState mutableState, LazyListScope lazyListScope) {
        List<Profile> profiles = ProfileSelectionScreen$lambda$1(state).getProfiles();
        lazyListScope.items(profiles.size(), null, new ProfileSelectionScreenKt$ProfileSelectionScreen$lambda$13$0$1$0$$inlined$itemsIndexed$default$2(profiles), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new ProfileSelectionScreenKt$ProfileSelectionScreen$lambda$13$0$1$0$$inlined$itemsIndexed$default$3(profiles, f10, list, state, profileViewModel, mutableState)));
        if (ProfileSelectionScreen$lambda$1(state).getProfiles().size() < 5) {
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(-1006071071, true, new r7.q() { // from class: com.arflix.tv.ui.screens.profile.x
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$13$0$1$0$1(f10, list, profileViewModel, state, (LazyItemScope) obj, (Composer) obj2, iIntValue);
                }
            }), 3, null);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$1$0$1(float f10, List list, ProfileViewModel profileViewModel, State state, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1006071071, i10, -1, "com.arflix.tv.ui.screens.profile.ProfileSelectionScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileSelectionScreen.kt:228)");
            }
            Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, (FocusRequester) list.get(ProfileSelectionScreen$lambda$1(state).getProfiles().size()));
            boolean zChangedInstance = composer.changedInstance(profileViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new b0(profileViewModel, 11);
                composer.updateRememberedValue(objRememberedValue);
            }
            m6278AddProfileButtonKz89ssw(f10, modifierFocusRequester, (r7.a) objRememberedValue, composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$1$0$1$0$0(ProfileViewModel profileViewModel) {
        profileViewModel.showAddDialog();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$2$0$0$0(ProfileViewModel profileViewModel, Profile profile, MutableState mutableState, State state, MutableState mutableState2) {
        boolean zProfileSelectionScreen$lambda$7 = ProfileSelectionScreen$lambda$7(mutableState);
        t0 t0Var = t0.f22605a;
        if (zProfileSelectionScreen$lambda$7 && !ProfileSelectionScreen$lambda$1(state).isSwitchingProfile()) {
            if (ProfileSelectionScreen$lambda$1(state).isManageMode()) {
                profileViewModel.showEditDialog(profile);
            } else {
                ProfileSelectionScreen$lambda$5(mutableState2, true);
                profileViewModel.selectProfileWithLockCheck(profile);
            }
        }
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$2$0$1$0(ProfileViewModel profileViewModel, Profile profile) {
        profileViewModel.preloadForProfile(profile);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$2$0$2$0(ProfileViewModel profileViewModel, Profile profile) {
        profileViewModel.deleteProfile(profile);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$2$1$0(ProfileViewModel profileViewModel, MutableState mutableState, State state) {
        if (ProfileSelectionScreen$lambda$7(mutableState) && !ProfileSelectionScreen$lambda$1(state).isSwitchingProfile()) {
            profileViewModel.showAddDialog();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$3$0(boolean z, ProfileViewModel profileViewModel, MutableState mutableState, State state) {
        if ((z || ProfileSelectionScreen$lambda$7(mutableState)) && !ProfileSelectionScreen$lambda$1(state).isSwitchingProfile()) {
            profileViewModel.toggleManageMode();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$0$4$0(boolean z, r7.a aVar, MutableState mutableState, State state) {
        if ((z || ProfileSelectionScreen$lambda$7(mutableState)) && !ProfileSelectionScreen$lambda$1(state).isSwitchingProfile()) {
            aVar.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$1$0(ProfileViewModel profileViewModel, String str) {
        profileViewModel.setNewProfileName(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$10$0(ProfileViewModel profileViewModel, String str) {
        profileViewModel.verifyPinAndSelectProfile(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$11$0(ProfileViewModel profileViewModel) {
        profileViewModel.hidePinDialog();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$12$0(ProfileViewModel profileViewModel, String str) {
        profileViewModel.setupProfilePin(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$13$0(ProfileViewModel profileViewModel) {
        profileViewModel.hidePinDialog();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$2$0(ProfileViewModel profileViewModel, int i10) {
        profileViewModel.setSelectedColorIndex(i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$3$0(ProfileViewModel profileViewModel, int i10) {
        profileViewModel.setSelectedAvatarId(i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$4$0(ProfileViewModel profileViewModel, String str) {
        profileViewModel.setSelectedAvatarImage(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$5$0(ProfileViewModel profileViewModel) {
        profileViewModel.removeSelectedAvatarImage();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$6$0(ProfileViewModel profileViewModel) {
        profileViewModel.createProfile();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$7$0(ProfileViewModel profileViewModel) {
        profileViewModel.hideAddDialog();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$0$0(ProfileViewModel profileViewModel, String str) {
        profileViewModel.setNewProfileName(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$1$0(ProfileViewModel profileViewModel, int i10) {
        profileViewModel.setSelectedColorIndex(i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$2$0(ProfileViewModel profileViewModel, int i10) {
        profileViewModel.setSelectedAvatarId(i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$3$0(ProfileViewModel profileViewModel, String str) {
        profileViewModel.setSelectedAvatarImage(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$4$0(ProfileViewModel profileViewModel) {
        profileViewModel.removeSelectedAvatarImage();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$5$0(ProfileViewModel profileViewModel) {
        profileViewModel.updateProfile();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$6$0(ProfileViewModel profileViewModel, Profile profile) {
        profileViewModel.deleteProfile(profile);
        profileViewModel.hideEditDialog();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$7$0(ProfileViewModel profileViewModel) {
        profileViewModel.hideEditDialog();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$8$0(ProfileViewModel profileViewModel) {
        profileViewModel.showPinSetupDialog();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$8$9$0(ProfileViewModel profileViewModel) {
        profileViewModel.removeProfilePin();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$13$9$0(ProfileViewModel profileViewModel) {
        profileViewModel.dismissToast();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileSelectionScreen$lambda$14(ProfileViewModel profileViewModel, r7.a aVar, r7.a aVar2, r7.a aVar3, boolean z, int i10, int i11, Composer composer, int i12) {
        ProfileSelectionScreen(profileViewModel, aVar, aVar2, aVar3, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProfileSelectionScreen$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ProfileSelectionScreen$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProfileSelectionScreen$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ProfileSelectionScreen$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }
}
