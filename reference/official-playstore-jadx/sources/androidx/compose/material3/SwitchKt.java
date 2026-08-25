package androidx.compose.material3;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
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
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\u001al\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0002\b\b2\b\b\u0002\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001ay\u0010\u001e\u001a\u00020\u0003*\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\u000e\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0003ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\"\u001a\u0010\u001f\u001a\u00020\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u001a\u0010#\u001a\u00020\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b$\u0010\"\"\u0014\u0010%\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010 \"\u0014\u0010&\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010 \"\u0014\u0010'\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010 \"\u0014\u0010(\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010 \"\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00130)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006-²\u0006\f\u0010,\u001a\u00020\u00008\nX\u008a\u0084\u0002"}, d2 = {"", "checked", "Lkotlin/Function1;", "Lx6/t0;", "onCheckedChange", "Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "thumbContent", "enabled", "Landroidx/compose/material3/SwitchColors;", "colors", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Switch", "(ZLr7/l;Landroidx/compose/ui/Modifier;Lr7/p;ZLandroidx/compose/material3/SwitchColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/State;", "", "thumbValue", "Landroidx/compose/foundation/interaction/InteractionSource;", "Landroidx/compose/ui/graphics/Shape;", "thumbShape", "Landroidx/compose/ui/unit/Dp;", "uncheckedThumbDiameter", "minBound", "maxBound", "SwitchImpl-0DmnUew", "(Landroidx/compose/foundation/layout/BoxScope;ZZLandroidx/compose/material3/SwitchColors;Landroidx/compose/runtime/State;Lr7/p;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;FFFLandroidx/compose/runtime/Composer;II)V", "SwitchImpl", "ThumbDiameter", "F", "getThumbDiameter", "()F", "UncheckedThumbDiameter", "getUncheckedThumbDiameter", "SwitchWidth", "SwitchHeight", "ThumbPadding", "ThumbPathLength", "Landroidx/compose/animation/core/TweenSpec;", "AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "isPressed", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt {
    private static final TweenSpec<Float> AnimationSpec;
    private static final float SwitchHeight;
    private static final float SwitchWidth;
    private static final float ThumbDiameter;
    private static final float ThumbPadding;
    private static final float ThumbPathLength;
    private static final float UncheckedThumbDiameter;

    /* JADX INFO: renamed from: androidx.compose.material3.SwitchKt$Switch$5, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ boolean $checked;
        final /* synthetic */ SwitchColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<Boolean, t0> $onCheckedChange;
        final /* synthetic */ p<Composer, Integer, t0> $thumbContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(boolean z, l<? super Boolean, t0> lVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, boolean z5, SwitchColors switchColors, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
            super(2);
            this.$checked = z;
            this.$onCheckedChange = lVar;
            this.$modifier = modifier;
            this.$thumbContent = pVar;
            this.$enabled = z5;
            this.$colors = switchColors;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SwitchKt.Switch(this.$checked, this.$onCheckedChange, this.$modifier, this.$thumbContent, this.$enabled, this.$colors, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    static {
        SwitchTokens switchTokens = SwitchTokens.INSTANCE;
        float fM2850getSelectedHandleWidthD9Ej5fM = switchTokens.m2850getSelectedHandleWidthD9Ej5fM();
        ThumbDiameter = fM2850getSelectedHandleWidthD9Ej5fM;
        UncheckedThumbDiameter = switchTokens.m2857getUnselectedHandleWidthD9Ej5fM();
        float fM2855getTrackWidthD9Ej5fM = switchTokens.m2855getTrackWidthD9Ej5fM();
        SwitchWidth = fM2855getTrackWidthD9Ej5fM;
        float fM2853getTrackHeightD9Ej5fM = switchTokens.m2853getTrackHeightD9Ej5fM();
        SwitchHeight = fM2853getTrackHeightD9Ej5fM;
        float fM5678constructorimpl = Dp.m5678constructorimpl(Dp.m5678constructorimpl(fM2853getTrackHeightD9Ej5fM - fM2850getSelectedHandleWidthD9Ej5fM) / 2);
        ThumbPadding = fM5678constructorimpl;
        ThumbPathLength = Dp.m5678constructorimpl(Dp.m5678constructorimpl(fM2855getTrackWidthD9Ej5fM - fM2850getSelectedHandleWidthD9Ej5fM) - fM5678constructorimpl);
        AnimationSpec = new TweenSpec<>(100, 0, null, 6, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:166:? A[RETURN, SYNTHETIC] */
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
    /* JADX WARN: Removed duplicated region for block: B:93:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0116  */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v8, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Switch(boolean r23, r7.l<? super java.lang.Boolean, x6.t0> r24, androidx.compose.ui.Modifier r25, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r26, boolean r27, androidx.compose.material3.SwitchColors r28, androidx.compose.foundation.interaction.MutableInteractionSource r29, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 918
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SwitchKt.Switch(boolean, r7.l, androidx.compose.ui.Modifier, r7.p, boolean, androidx.compose.material3.SwitchColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchImpl-0DmnUew, reason: not valid java name */
    public static final void m2037SwitchImpl0DmnUew(BoxScope boxScope, boolean z, boolean z5, SwitchColors switchColors, State<Float> state, p<? super Composer, ? super Integer, t0> pVar, InteractionSource interactionSource, Shape shape, float f10, float f11, float f12, Composer composer, int i10, int i11) {
        int i12;
        State<Float> state2;
        int i13;
        float fFloatValue;
        InteractionSource interactionSource2;
        Shape shape2;
        p<? super Composer, ? super Integer, t0> pVar2 = pVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1968109941);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(boxScope) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? 32 : 16;
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
            interactionSource2 = interactionSource;
            shape2 = shape;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1968109941, i12, i13, "androidx.compose.material3.SwitchImpl (Switch.kt:186)");
            }
            long jM2034trackColorWaAFU9c$material3_release = switchColors.m2034trackColorWaAFU9c$material3_release(z5, z);
            State<Boolean> stateCollectIsPressedAsState = PressInteractionKt.collectIsPressedAsState(interactionSource, composerStartRestartGroup, (i12 >> 18) & 14);
            float fM2848getPressedHandleWidthD9Ej5fM = SwitchImpl_0DmnUew$lambda$8(stateCollectIsPressedAsState) ? SwitchTokens.INSTANCE.m2848getPressedHandleWidthD9Ej5fM() : Dp.m5678constructorimpl(Dp.m5678constructorimpl((Dp.m5678constructorimpl(((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo281toDpu2uoSUM(state2.getValue().floatValue()) - f11) / Dp.m5678constructorimpl(f12 - f11)) * Dp.m5678constructorimpl(ThumbDiameter - f10)) + f10);
            composerStartRestartGroup.startReplaceableGroup(-993794132);
            if (SwitchImpl_0DmnUew$lambda$8(stateCollectIsPressedAsState)) {
                fFloatValue = ((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo285toPx0680j_4(z ? Dp.m5678constructorimpl(ThumbPathLength - SwitchTokens.INSTANCE.m2854getTrackOutlineWidthD9Ej5fM()) : SwitchTokens.INSTANCE.m2854getTrackOutlineWidthD9Ej5fM());
            } else {
                fFloatValue = state2.getValue().floatValue();
            }
            composerStartRestartGroup.endReplaceableGroup();
            SwitchTokens switchTokens = SwitchTokens.INSTANCE;
            Shape value = ShapesKt.getValue(switchTokens.getTrackShape(), composerStartRestartGroup, 6);
            Modifier.Companion companion = Modifier.INSTANCE;
            Alignment.Companion companion2 = Alignment.INSTANCE;
            int i14 = i12;
            Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(BorderKt.m191borderxT4_qwU(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(boxScope.align(companion, companion2.getCenter()), SwitchWidth), SwitchHeight), switchTokens.m2854getTrackOutlineWidthD9Ej5fM(), switchColors.m2014borderColorWaAFU9c$material3_release(z5, z), value), jM2034trackColorWaAFU9c$material3_release, value);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            long jM2033thumbColorWaAFU9c$material3_release = switchColors.m2033thumbColorWaAFU9c$material3_release(z5, z);
            Modifier modifierAlign = boxScopeInstance.align(companion, companion2.getCenterStart());
            composerStartRestartGroup.startReplaceableGroup(1420969929);
            boolean zChanged = composerStartRestartGroup.changed(fFloatValue);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SwitchKt$SwitchImpl$1$1$1(fFloatValue);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierOffset = OffsetKt.offset(modifierAlign, (l) objRememberedValue);
            Indication indicationM1208rememberRipple9IZ8Weo = RippleKt.m1208rememberRipple9IZ8Weo(false, Dp.m5678constructorimpl(switchTokens.m2852getStateLayerSizeD9Ej5fM() / 2), 0L, composerStartRestartGroup, 54, 4);
            interactionSource2 = interactionSource;
            composerStartRestartGroup = composerStartRestartGroup;
            Modifier modifierM570requiredSize3ABfNKs = SizeKt.m570requiredSize3ABfNKs(IndicationKt.indication(modifierOffset, interactionSource2, indicationM1208rememberRipple9IZ8Weo), fM2848getPressedHandleWidthD9Ej5fM);
            shape2 = shape;
            Modifier modifierM178backgroundbw27NRU2 = BackgroundKt.m178backgroundbw27NRU(modifierM570requiredSize3ABfNKs, jM2033thumbColorWaAFU9c$material3_release, shape2);
            Alignment center = companion2.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU2);
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
            p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            composerStartRestartGroup.startReplaceableGroup(1420970455);
            if (pVar != null) {
                pVar2 = pVar;
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(switchColors.m2032iconColorWaAFU9c$material3_release(z5, z))), pVar2, composerStartRestartGroup, ProvidedValue.$stable | ((i14 >> 12) & 112));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new SwitchKt$SwitchImpl$2(boxScope, z, z5, switchColors, state, pVar2, interactionSource2, shape2, f10, f11, f12, i10, i11));
        }
    }

    private static final boolean SwitchImpl_0DmnUew$lambda$8(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }

    public static final float getUncheckedThumbDiameter() {
        return UncheckedThumbDiameter;
    }
}
