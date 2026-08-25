package com.arflix.tv.ui.components;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
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
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import com.arflix.tv.ui.theme.ColorKt;
import com.arflix.tv.ui.theme.ThemeKt;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u001aE\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\b\u0010\t\u001a\u0019\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a/\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\"\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013¨\u0006\u001b²\u0006\f\u0010\u0015\u001a\u00020\r8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0016\u001a\u00020\r8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0017\u001a\u00020\r8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0018\u001a\u00020\r8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0019\u001a\u00020\r8\nX\u008a\u0084\u0002²\u0006\f\u0010\u001a\u001a\u00020\r8\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "", "backdropUrl", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, "loadingMessage", "Lx6/t0;", "PlayerLoadingScreen", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "BufferingIndicator", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "sourceName", "", "progress", "SourceLoadingScreen", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/Float;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/animation/core/CubicBezierEasing;", "EaseInOutCubic", "Landroidx/compose/animation/core/CubicBezierEasing;", "EaseOut", "playScale", "playGlow", "ringScale", "ringAlpha", "rotation", "iconGlow", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PlayerLoadingScreenKt {
    private static final CubicBezierEasing EaseInOutCubic = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    private static final CubicBezierEasing EaseOut = new CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f);

    public static final void BufferingIndicator(Modifier modifier, Composer composer, int i10, int i11) {
        Modifier modifier2;
        int i12;
        Modifier modifier3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-302840214);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
            modifier2 = modifier;
        } else if ((i10 & 6) == 0) {
            modifier2 = modifier;
            i12 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i10;
        } else {
            modifier2 = modifier;
            i12 = i10;
        }
        if ((i12 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i13 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-302840214, i12, -1, "com.arflix.tv.ui.components.BufferingIndicator (PlayerLoadingScreen.kt:283)");
            }
            State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(InfiniteTransitionKt.rememberInfiniteTransition("buffering", composerStartRestartGroup, 6, 0), 0.0f, 360.0f, AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(1500, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "rotation", composerStartRestartGroup, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m578size3ABfNKs(modifier3, Dp.m5678constructorimpl(60)), 16), Color.m3471copywmQWz5c$default(ThemeKt.appBackgroundDark(composerStartRestartGroup, 0), 0.8f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
            Alignment.Companion companion = Alignment.INSTANCE;
            Alignment center = companion.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier.Companion companion3 = Modifier.INSTANCE;
            Modifier modifierClip = ClipKt.clip(SizeKt.m578size3ABfNKs(companion3, Dp.m5678constructorimpl(40)), RoundedCornerShapeKt.getCircleShape());
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierClip);
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
            r7.p pVarU2 = a0.c.u(companion2, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null);
            boolean zChanged = composerStartRestartGroup.changed(stateAnimateFloat);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new c(stateAnimateFloat, 3);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifierFillMaxSize$default, (r7.l) objRememberedValue, composerStartRestartGroup, 6);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new n1(modifier3, i10, i11, 0));
        }
    }

    private static final float BufferingIndicator$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BufferingIndicator$lambda$1$0$0$0(State state, DrawScope drawScope) {
        androidx.compose.ui.graphics.drawscope.c.u(drawScope, Brush.Companion.m3433sweepGradientUv8p0NA$default(Brush.INSTANCE, t7.a.E(Color.m3462boximpl(ColorKt.getCyan()), Color.m3462boximpl(ColorKt.getPurple()), Color.m3462boximpl(ColorKt.getPink()), Color.m3462boximpl(Color.INSTANCE.m3507getTransparent0d7_KjU())), 0L, 2, (Object) null), BufferingIndicator$lambda$0(state), 270.0f, false, 0L, 0L, 0.0f, new Stroke(drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl(4)), 0.0f, StrokeCap.INSTANCE.m3804getRoundKaPHkGw(), 0, null, 26, null), null, 0, 880, null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BufferingIndicator$lambda$2(Modifier modifier, int i10, int i11, Composer composer, int i12) {
        BufferingIndicator(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x05d7  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0606  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0660  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0717  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0793  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x07a3  */
    /* JADX WARN: Removed duplicated region for block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PlayerLoadingScreen(androidx.compose.ui.Modifier r49, java.lang.String r50, java.lang.String r51, java.lang.String r52, java.lang.String r53, androidx.compose.runtime.Composer r54, int r55, int r56) {
        /*
            Method dump skipped, instruction units count: 1969
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PlayerLoadingScreenKt.PlayerLoadingScreen(androidx.compose.ui.Modifier, java.lang.String, java.lang.String, java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float PlayerLoadingScreen$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float PlayerLoadingScreen$lambda$2(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float PlayerLoadingScreen$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float PlayerLoadingScreen$lambda$4(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerLoadingScreen$lambda$6(Modifier modifier, String str, String str2, String str3, String str4, int i10, int i11, Composer composer, int i12) {
        PlayerLoadingScreen(modifier, str, str2, str3, str4, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x064b  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x06c6  */
    /* JADX WARN: Removed duplicated region for block: B:155:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SourceLoadingScreen(androidx.compose.ui.Modifier r44, java.lang.String r45, java.lang.Float r46, androidx.compose.runtime.Composer r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 1747
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PlayerLoadingScreenKt.SourceLoadingScreen(androidx.compose.ui.Modifier, java.lang.String, java.lang.Float, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float SourceLoadingScreen$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceLoadingScreen$lambda$2(Modifier modifier, String str, Float f10, int i10, int i11, Composer composer, int i12) {
        SourceLoadingScreen(modifier, str, f10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }
}
