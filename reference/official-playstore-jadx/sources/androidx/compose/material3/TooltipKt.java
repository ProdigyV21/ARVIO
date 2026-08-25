package androidx.compose.material3;

import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.layout.AlignmentLineKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.media3.exoplayer.RendererCapabilities;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u001an\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u00062\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0085\u0001\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\u0011\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a-\u0010$\u001a\u00020\b2\b\b\u0002\u0010 \u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\f2\b\b\u0002\u0010#\u001a\u00020\"H\u0007¢\u0006\u0004\b$\u0010%\u001a-\u0010&\u001a\u00020\b2\b\b\u0002\u0010 \u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\f2\b\b\u0002\u0010#\u001a\u00020\"H\u0007¢\u0006\u0004\b&\u0010'\u001a#\u0010*\u001a\u00020\n*\u00020\n2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\fH\u0003¢\u0006\u0004\b*\u0010+\u001a!\u0010.\u001a\u00020\n*\u00020\n2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\f0,H\u0000¢\u0006\u0004\b.\u0010/\"\u001a\u00100\u001a\u00020\u00198\u0000X\u0080\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u001a\u00104\u001a\u00020\u00198\u0000X\u0080\u0004¢\u0006\f\n\u0004\b4\u00101\u001a\u0004\b5\u00103\"\u001a\u00106\u001a\u00020\u00198\u0000X\u0080\u0004¢\u0006\f\n\u0004\b6\u00101\u001a\u0004\b7\u00103\"\u001a\u00108\u001a\u00020\u00198\u0000X\u0080\u0004¢\u0006\f\n\u0004\b8\u00101\u001a\u0004\b9\u00103\"\u0014\u0010:\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00101\"\u0014\u0010;\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u00101\"\u001a\u0010=\u001a\u00020<8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@\"\u0014\u0010A\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u00101\"\u001a\u0010B\u001a\u00020\u00198\u0000X\u0080\u0004¢\u0006\f\n\u0004\bB\u00101\u001a\u0004\bC\u00103\"\u0014\u0010D\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u00101\"\u0014\u0010E\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u00101\"\u0014\u0010F\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u00101\"\u0014\u0010G\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u00101\"\u0014\u0010H\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u00101\"\u0014\u0010J\u001a\u00020I8\u0000X\u0080T¢\u0006\u0006\n\u0004\bJ\u0010K\"\u0014\u0010L\u001a\u00020I8\u0000X\u0080T¢\u0006\u0006\n\u0004\bL\u0010K\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006R²\u0006\u0010\u0010N\u001a\u0004\u0018\u00010M8\n@\nX\u008a\u008e\u0002²\u0006\f\u0010P\u001a\u00020O8\nX\u008a\u0084\u0002²\u0006\f\u0010Q\u001a\u00020O8\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/window/PopupPositionProvider;", "positionProvider", "Lkotlin/Function1;", "Landroidx/compose/material3/CaretScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "tooltip", "Landroidx/compose/material3/TooltipState;", "state", "Landroidx/compose/ui/Modifier;", "modifier", "", "focusable", "enableUserInput", "Lkotlin/Function0;", "content", "TooltipBox", "(Landroidx/compose/ui/window/PopupPositionProvider;Lr7/q;Landroidx/compose/material3/TooltipState;Landroidx/compose/ui/Modifier;ZZLr7/p;Landroidx/compose/runtime/Composer;II)V", LinkHeader.Parameters.Title, "action", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/material3/RichTooltipColors;", "colors", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "shadowElevation", "text", "RichTooltip-1tP8Re8", "(Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;FFLr7/p;Landroidx/compose/runtime/Composer;II)V", "RichTooltip", "initialIsVisible", "isPersistent", "Landroidx/compose/foundation/MutatorMutex;", "mutatorMutex", "rememberTooltipState", "(ZZLandroidx/compose/foundation/MutatorMutex;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TooltipState;", "TooltipState", "(ZZLandroidx/compose/foundation/MutatorMutex;)Landroidx/compose/material3/TooltipState;", "subheadExists", "actionExists", "textVerticalPadding", "(Landroidx/compose/ui/Modifier;ZZ)Landroidx/compose/ui/Modifier;", "Landroidx/compose/animation/core/Transition;", "transition", "animateTooltip", "(Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/Transition;)Landroidx/compose/ui/Modifier;", "SpacingBetweenTooltipAndAnchor", "F", "getSpacingBetweenTooltipAndAnchor", "()F", "TooltipMinHeight", "getTooltipMinHeight", "TooltipMinWidth", "getTooltipMinWidth", "PlainTooltipMaxWidth", "getPlainTooltipMaxWidth", "PlainTooltipVerticalPadding", "PlainTooltipHorizontalPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "PlainTooltipContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getPlainTooltipContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "RichTooltipMaxWidth", "RichTooltipHorizontalPadding", "getRichTooltipHorizontalPadding", "HeightToSubheadFirstLine", "HeightFromSubheadToTextFirstLine", "TextBottomPadding", "ActionLabelMinHeight", "ActionLabelBottomPadding", "", "TooltipFadeInDuration", "I", "TooltipFadeOutDuration", "Landroidx/compose/ui/layout/LayoutCoordinates;", "anchorBounds", "", "scale", "alpha", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TooltipKt {
    private static final float ActionLabelBottomPadding;
    private static final float ActionLabelMinHeight;
    private static final float HeightFromSubheadToTextFirstLine;
    private static final float HeightToSubheadFirstLine;
    private static final PaddingValues PlainTooltipContentPadding;
    private static final float PlainTooltipHorizontalPadding;
    private static final float PlainTooltipVerticalPadding;
    private static final float RichTooltipHorizontalPadding;
    private static final float RichTooltipMaxWidth;
    private static final float SpacingBetweenTooltipAndAnchor;
    private static final float TextBottomPadding;
    public static final int TooltipFadeInDuration = 150;
    public static final int TooltipFadeOutDuration = 75;
    private static final float TooltipMinHeight;
    private static final float TooltipMinWidth = Dp.m5678constructorimpl(40);
    private static final float PlainTooltipMaxWidth = Dp.m5678constructorimpl(200);

    /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$TooltipBox$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TooltipKt$TooltipBox$scope$1$1 $scope;
        final /* synthetic */ q<CaretScope, Composer, Integer, t0> $tooltip;
        final /* synthetic */ Transition<Boolean> $transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Transition<Boolean> transition, q<? super CaretScope, ? super Composer, ? super Integer, t0> qVar, TooltipKt$TooltipBox$scope$1$1 tooltipKt$TooltipBox$scope$1$1) {
            super(2);
            this.$transition = transition;
            this.$tooltip = qVar;
            this.$scope = tooltipKt$TooltipBox$scope$1$1;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-149611544, i10, -1, "androidx.compose.material3.TooltipBox.<anonymous> (Tooltip.kt:141)");
            }
            Modifier modifierAnimateTooltip = TooltipKt.animateTooltip(Modifier.INSTANCE, this.$transition);
            q<CaretScope, Composer, Integer, t0> qVar = this.$tooltip;
            TooltipKt$TooltipBox$scope$1$1 tooltipKt$TooltipBox$scope$1$1 = this.$scope;
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierAnimateTooltip);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            qVar.invoke(tooltipKt$TooltipBox$scope$1$1, composer, 6);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$TooltipBox$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $enableUserInput;
        final /* synthetic */ boolean $focusable;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ PopupPositionProvider $positionProvider;
        final /* synthetic */ TooltipState $state;
        final /* synthetic */ q<CaretScope, Composer, Integer, t0> $tooltip;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(PopupPositionProvider popupPositionProvider, q<? super CaretScope, ? super Composer, ? super Integer, t0> qVar, TooltipState tooltipState, Modifier modifier, boolean z, boolean z5, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$positionProvider = popupPositionProvider;
            this.$tooltip = qVar;
            this.$state = tooltipState;
            this.$modifier = modifier;
            this.$focusable = z;
            this.$enableUserInput = z5;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TooltipKt.TooltipBox(this.$positionProvider, this.$tooltip, this.$state, this.$modifier, this.$focusable, this.$enableUserInput, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$animateTooltip$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05862 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ Transition<Boolean> $transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05862(Transition<Boolean> transition) {
            super(3);
            this.$transition = transition;
        }

        private static final float invoke$lambda$1(State<Float> state) {
            return state.getValue().floatValue();
        }

        private static final float invoke$lambda$3(State<Float> state) {
            return state.getValue().floatValue();
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(-1498516085);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1498516085, i10, -1, "androidx.compose.material3.animateTooltip.<anonymous> (Tooltip.kt:655)");
            }
            Transition<Boolean> transition = this.$transition;
            TooltipKt$animateTooltip$2$scale$2 tooltipKt$animateTooltip$2$scale$2 = TooltipKt$animateTooltip$2$scale$2.INSTANCE;
            composer.startReplaceableGroup(-1338768149);
            i iVar = i.f19743a;
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(iVar);
            composer.startReplaceableGroup(-142660079);
            boolean zBooleanValue = transition.getCurrentState().booleanValue();
            composer.startReplaceableGroup(-1553362193);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1553362193, 0, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:672)");
            }
            float f10 = zBooleanValue ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            Float fValueOf = Float.valueOf(f10);
            boolean zBooleanValue2 = transition.getTargetState().booleanValue();
            composer.startReplaceableGroup(-1553362193);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1553362193, 0, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:672)");
            }
            float f11 = zBooleanValue2 ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            State stateCreateTransitionAnimation = TransitionKt.createTransitionAnimation(transition, fValueOf, Float.valueOf(f11), (FiniteAnimationSpec) tooltipKt$animateTooltip$2$scale$2.invoke((Object) transition.getSegment(), (Object) composer, (Object) 0), vectorConverter, "tooltip transition: scaling", composer, 196608);
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            Transition<Boolean> transition2 = this.$transition;
            TooltipKt$animateTooltip$2$alpha$2 tooltipKt$animateTooltip$2$alpha$2 = TooltipKt$animateTooltip$2$alpha$2.INSTANCE;
            composer.startReplaceableGroup(-1338768149);
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(iVar);
            composer.startReplaceableGroup(-142660079);
            boolean zBooleanValue3 = transition2.getCurrentState().booleanValue();
            composer.startReplaceableGroup(2073045083);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2073045083, 0, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:691)");
            }
            float f12 = zBooleanValue3 ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            Float fValueOf2 = Float.valueOf(f12);
            boolean zBooleanValue4 = transition2.getTargetState().booleanValue();
            composer.startReplaceableGroup(2073045083);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2073045083, 0, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:691)");
            }
            float f13 = zBooleanValue4 ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            State stateCreateTransitionAnimation2 = TransitionKt.createTransitionAnimation(transition2, fValueOf2, Float.valueOf(f13), (FiniteAnimationSpec) tooltipKt$animateTooltip$2$alpha$2.invoke((Object) transition2.getSegment(), (Object) composer, (Object) 0), vectorConverter2, "tooltip transition: alpha", composer, 196608);
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            Modifier modifierM3633graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m3633graphicsLayerAp8cVGQ$default(modifier, invoke$lambda$1(stateCreateTransitionAnimation), invoke$lambda$1(stateCreateTransitionAnimation), invoke$lambda$3(stateCreateTransitionAnimation2), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131064, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierM3633graphicsLayerAp8cVGQ$default;
        }
    }

    static {
        float f10 = 4;
        SpacingBetweenTooltipAndAnchor = Dp.m5678constructorimpl(f10);
        float f11 = 24;
        TooltipMinHeight = Dp.m5678constructorimpl(f11);
        float fM5678constructorimpl = Dp.m5678constructorimpl(f10);
        PlainTooltipVerticalPadding = fM5678constructorimpl;
        float f12 = 8;
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(f12);
        PlainTooltipHorizontalPadding = fM5678constructorimpl2;
        PlainTooltipContentPadding = PaddingKt.m523PaddingValuesYgX7TsA(fM5678constructorimpl2, fM5678constructorimpl);
        RichTooltipMaxWidth = Dp.m5678constructorimpl(320);
        float f13 = 16;
        RichTooltipHorizontalPadding = Dp.m5678constructorimpl(f13);
        HeightToSubheadFirstLine = Dp.m5678constructorimpl(28);
        HeightFromSubheadToTextFirstLine = Dp.m5678constructorimpl(f11);
        TextBottomPadding = Dp.m5678constructorimpl(f13);
        ActionLabelMinHeight = Dp.m5678constructorimpl(36);
        ActionLabelBottomPadding = Dp.m5678constructorimpl(f12);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010d  */
    /* JADX INFO: renamed from: RichTooltip-1tP8Re8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2299RichTooltip1tP8Re8(androidx.compose.ui.Modifier r24, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r25, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r26, androidx.compose.ui.graphics.Shape r27, androidx.compose.material3.RichTooltipColors r28, float r29, float r30, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, androidx.compose.runtime.Composer r32, int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 507
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipKt.m2299RichTooltip1tP8Re8(androidx.compose.ui.Modifier, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.RichTooltipColors, float, float, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TooltipBox(androidx.compose.ui.window.PopupPositionProvider r19, r7.q<? super androidx.compose.material3.CaretScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r20, androidx.compose.material3.TooltipState r21, androidx.compose.ui.Modifier r22, boolean r23, boolean r24, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r25, androidx.compose.runtime.Composer r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipKt.TooltipBox(androidx.compose.ui.window.PopupPositionProvider, r7.q, androidx.compose.material3.TooltipState, androidx.compose.ui.Modifier, boolean, boolean, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutCoordinates TooltipBox$lambda$1(MutableState<LayoutCoordinates> mutableState) {
        return mutableState.getValue();
    }

    public static final TooltipState TooltipState(boolean z, boolean z5, MutatorMutex mutatorMutex) {
        return new TooltipStateImpl(z, z5, mutatorMutex);
    }

    public static /* synthetic */ TooltipState TooltipState$default(boolean z, boolean z5, MutatorMutex mutatorMutex, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        if ((i10 & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        return TooltipState(z, z5, mutatorMutex);
    }

    public static final Modifier animateTooltip(Modifier modifier, Transition<Boolean> transition) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new TooltipKt$animateTooltip$$inlined$debugInspectorInfo$1(transition) : InspectableValueKt.getNoInspectorInfo(), new C05862(transition));
    }

    public static final PaddingValues getPlainTooltipContentPadding() {
        return PlainTooltipContentPadding;
    }

    public static final float getPlainTooltipMaxWidth() {
        return PlainTooltipMaxWidth;
    }

    public static final float getRichTooltipHorizontalPadding() {
        return RichTooltipHorizontalPadding;
    }

    public static final float getSpacingBetweenTooltipAndAnchor() {
        return SpacingBetweenTooltipAndAnchor;
    }

    public static final float getTooltipMinHeight() {
        return TooltipMinHeight;
    }

    public static final float getTooltipMinWidth() {
        return TooltipMinWidth;
    }

    public static final TooltipState rememberTooltipState(boolean z, boolean z5, MutatorMutex mutatorMutex, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1413230530);
        if ((i11 & 1) != 0) {
            z = false;
        }
        if ((i11 & 2) != 0) {
            z5 = false;
        }
        if ((i11 & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1413230530, i10, -1, "androidx.compose.material3.rememberTooltipState (Tooltip.kt:513)");
        }
        composer.startReplaceableGroup(512858205);
        boolean z10 = ((((i10 & 112) ^ 48) > 32 && composer.changed(z5)) || (i10 & 48) == 32) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(mutatorMutex)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new TooltipStateImpl(z, z5, mutatorMutex);
            composer.updateRememberedValue(objRememberedValue);
        }
        TooltipStateImpl tooltipStateImpl = (TooltipStateImpl) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return tooltipStateImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier textVerticalPadding(Modifier modifier, boolean z, boolean z5) {
        return (z || z5) ? PaddingKt.m533paddingqDBjuR0$default(AlignmentLineKt.m416paddingFromBaselineVpY3zN4$default(modifier, HeightFromSubheadToTextFirstLine, 0.0f, 2, null), 0.0f, 0.0f, 0.0f, TextBottomPadding, 7, null) : PaddingKt.m531paddingVpY3zN4$default(modifier, 0.0f, PlainTooltipVerticalPadding, 1, null);
    }
}
