package androidx.tv.material3;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001an\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0002\b\b2\b\b\u0002\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001ay\u0010\u001e\u001a\u00020\u0003*\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\u000e\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0003ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\"²\u0006\f\u0010 \u001a\u00020\u001f8\nX\u008a\u0084\u0002²\u0006\f\u0010!\u001a\u00020\u001f8\nX\u008a\u0084\u0002"}, d2 = {"", "checked", "Lkotlin/Function1;", "Lx6/t0;", "onCheckedChange", "Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "thumbContent", "enabled", "Landroidx/tv/material3/SwitchColors;", "colors", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Switch", "(ZLr7/l;Landroidx/compose/ui/Modifier;Lr7/p;ZLandroidx/tv/material3/SwitchColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/State;", "", "thumbValue", "Landroidx/compose/foundation/interaction/InteractionSource;", "Landroidx/compose/ui/graphics/Shape;", "thumbShape", "Landroidx/compose/ui/unit/Dp;", "uncheckedThumbDiameter", "minBound", "maxBound", "SwitchImpl-0DmnUew", "(Landroidx/compose/foundation/layout/BoxScope;ZZLandroidx/tv/material3/SwitchColors;Landroidx/compose/runtime/State;Lr7/p;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;FFFLandroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/ui/graphics/Color;", "trackColor", "thumbColor", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f5757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5758b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f5759c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f5760d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f5761e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final TweenSpec f5762f;

    static {
        float f10 = j2.l.f19126b;
        f5757a = f10;
        f5758b = j2.l.k;
        float f11 = j2.l.f19132h;
        f5759c = f11;
        float f12 = j2.l.f19129e;
        f5760d = f12;
        f5761e = Dp.m5678constructorimpl(Dp.m5678constructorimpl(f11 - f10) - Dp.m5678constructorimpl(Dp.m5678constructorimpl(f12 - f10) / 2));
        f5762f = new TweenSpec(100, 0, null, 6, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:169:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0118  */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Switch(boolean r25, r7.l<? super java.lang.Boolean, x6.t0> r26, androidx.compose.ui.Modifier r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, boolean r29, androidx.tv.material3.SwitchColors r30, androidx.compose.foundation.interaction.MutableInteractionSource r31, androidx.compose.runtime.Composer r32, int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 928
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.SwitchKt.Switch(boolean, r7.l, androidx.compose.ui.Modifier, r7.p, boolean, androidx.tv.material3.SwitchColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchImpl-0DmnUew, reason: not valid java name */
    public static final void m6013SwitchImpl0DmnUew(BoxScope boxScope, boolean z, boolean z5, SwitchColors switchColors, State<Float> state, r7.p<? super Composer, ? super Integer, x6.t0> pVar, InteractionSource interactionSource, Shape shape, float f10, float f11, float f12, Composer composer, int i10, int i11) {
        int i12;
        State<Float> state2;
        int i13;
        Shape shape2;
        boolean z10 = z;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar2 = pVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(1340395367);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(boxScope) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(z10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(z5) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(switchColors) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            state2 = state;
            i12 |= composerStartRestartGroup.changed(state2) ? 16384 : 8192;
        } else {
            state2 = state;
        }
        if ((i10 & 196608) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 131072 : 65536;
        }
        if ((i10 & 1572864) == 0) {
            i12 |= composerStartRestartGroup.changed(interactionSource) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i12 |= composerStartRestartGroup.changed(shape) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= composerStartRestartGroup.changed(f11) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i11 & 6) == 0) {
            i13 = i11 | (composerStartRestartGroup.changed(f12) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i12 & 306783379) == 306783378 && (i13 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            shape2 = shape;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1340395367, i12, i13, "androidx.tv.material3.SwitchImpl (Switch.kt:180)");
            }
            int i14 = ((i12 >> 6) & 14) | (i12 & 112) | ((i12 >> 3) & 896);
            State<Color> stateTrackColor$tv_material_release = switchColors.trackColor$tv_material_release(z5, z10, composerStartRestartGroup, i14);
            float fM5678constructorimpl = Dp.m5678constructorimpl(Dp.m5678constructorimpl((Dp.m5678constructorimpl(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo281toDpu2uoSUM(state2.getValue().floatValue()) - f11) / Dp.m5678constructorimpl(f12 - f11)) * Dp.m5678constructorimpl(f5757a - f10)) + f10);
            float fFloatValue = state2.getValue().floatValue();
            Shape shape3 = ShapesKt.toShape(j2.l.f19131g, composerStartRestartGroup, 6);
            Modifier.Companion companion = Modifier.INSTANCE;
            Alignment.Companion companion2 = Alignment.INSTANCE;
            int i15 = i12;
            Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(BorderKt.m191borderxT4_qwU(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(boxScope.align(companion, companion2.getCenter()), f5759c), f5760d), j2.l.f19130f, switchColors.borderColor$tv_material_release(z5, z10, composerStartRestartGroup, i14).getValue().m3482unboximpl(), shape3), stateTrackColor$tv_material_release.getValue().m3482unboximpl(), shape3);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            long jM3482unboximpl = switchColors.thumbColor$tv_material_release(z5, z10, composerStartRestartGroup, i14).getValue().m3482unboximpl();
            Modifier modifierAlign = boxScopeInstance.align(companion, companion2.getCenterStart());
            composerStartRestartGroup.startReplaceableGroup(-1722484634);
            boolean zChanged = composerStartRestartGroup.changed(fFloatValue);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new a4(fFloatValue);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierM570requiredSize3ABfNKs = SizeKt.m570requiredSize3ABfNKs(IndicationKt.indication(OffsetKt.offset(modifierAlign, (r7.l) objRememberedValue), interactionSource, null), fM5678constructorimpl);
            shape2 = shape;
            Modifier modifierM178backgroundbw27NRU2 = BackgroundKt.m178backgroundbw27NRU(modifierM570requiredSize3ABfNKs, jM3482unboximpl, shape2);
            Alignment center = companion2.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU2);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            composerStartRestartGroup.startReplaceableGroup(1556465902);
            z10 = z;
            if (pVar != null) {
                pVar2 = pVar;
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>) o1.f6258a.provides(switchColors.iconColor$tv_material_release(z5, z10, composerStartRestartGroup, i14).getValue()), pVar2, composerStartRestartGroup, ProvidedValue.$stable | ((i15 >> 12) & 112));
            } else {
                pVar2 = pVar;
            }
            composerStartRestartGroup.endReplaceableGroup();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new b4(boxScope, z10, z5, switchColors, state, pVar2, interactionSource, shape2, f10, f11, f12, i10, i11));
        }
    }
}
