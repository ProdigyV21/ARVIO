package androidx.compose.animation;

import a0.c;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u001e\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005R\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u001e\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005R\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u001e\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005R\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J \u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ \u0010\u001d\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0017J&\u0010&\u001a\u00020#*\u00020\u001e2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!H\u0016ø\u0001\u0000¢\u0006\u0004\b$\u0010%J \u0010(\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b'\u0010\u0017R(\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R:\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005R\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R:\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005R\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010.\u001a\u0004\b3\u00100\"\u0004\b4\u00102R:\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005R\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010.\u001a\u0004\b5\u00100\"\u0004\b6\u00102R\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\"\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0016\u0010G\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u001c\u0010I\u001a\u00020\u00068\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bI\u0010JR*\u0010L\u001a\u00020!2\u0006\u0010K\u001a\u00020!8\u0002@BX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bL\u0010J\"\u0004\bM\u0010NR$\u0010P\u001a\u0004\u0018\u00010O8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR4\u0010Z\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030W\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060X0V¢\u0006\u0002\bY8\u0006¢\u0006\f\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010]R4\u0010^\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030W\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0X0V¢\u0006\u0002\bY8\u0006¢\u0006\f\n\u0004\b^\u0010[\u001a\u0004\b_\u0010]R\u0013\u0010a\u001a\u0004\u0018\u00010O8F¢\u0006\u0006\u001a\u0004\b`\u0010S\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006b"}, d2 = {"Landroidx/compose/animation/EnterExitTransitionModifierNode;", "Landroidx/compose/animation/LayoutModifierNodeWithPassThroughIntrinsics;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "transition", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/animation/core/AnimationVector2D;", "sizeAnimation", "Landroidx/compose/ui/unit/IntOffset;", "offsetAnimation", "slideAnimation", "Landroidx/compose/animation/EnterTransition;", "enter", "Landroidx/compose/animation/ExitTransition;", "exit", "Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "graphicsLayerBlock", "<init>", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;)V", "targetState", "fullSize", "sizeByState-Uzc_VyU", "(Landroidx/compose/animation/EnterExitState;J)J", "sizeByState", "Lx6/t0;", "onAttach", "()V", "targetOffsetByState-oFUgxo0", "targetOffsetByState", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "slideTargetValueByState-oFUgxo0", "slideTargetValueByState", "Landroidx/compose/animation/core/Transition;", "getTransition", "()Landroidx/compose/animation/core/Transition;", "setTransition", "(Landroidx/compose/animation/core/Transition;)V", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "getSizeAnimation", "()Landroidx/compose/animation/core/Transition$DeferredAnimation;", "setSizeAnimation", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;)V", "getOffsetAnimation", "setOffsetAnimation", "getSlideAnimation", "setSlideAnimation", "Landroidx/compose/animation/EnterTransition;", "getEnter", "()Landroidx/compose/animation/EnterTransition;", "setEnter", "(Landroidx/compose/animation/EnterTransition;)V", "Landroidx/compose/animation/ExitTransition;", "getExit", "()Landroidx/compose/animation/ExitTransition;", "setExit", "(Landroidx/compose/animation/ExitTransition;)V", "Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "getGraphicsLayerBlock", "()Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "setGraphicsLayerBlock", "(Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;)V", "", "lookaheadConstraintsAvailable", "Z", "lookaheadSize", "J", "value", "lookaheadConstraints", "setLookaheadConstraints-BRTryo0", "(J)V", "Landroidx/compose/ui/Alignment;", "currentAlignment", "Landroidx/compose/ui/Alignment;", "getCurrentAlignment", "()Landroidx/compose/ui/Alignment;", "setCurrentAlignment", "(Landroidx/compose/ui/Alignment;)V", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lx6/n;", "sizeTransitionSpec", "Lr7/l;", "getSizeTransitionSpec", "()Lr7/l;", "slideSpec", "getSlideSpec", "getAlignment", "alignment", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class EnterExitTransitionModifierNode extends LayoutModifierNodeWithPassThroughIntrinsics {
    private Alignment currentAlignment;
    private EnterTransition enter;
    private ExitTransition exit;
    private GraphicsLayerBlockForEnterExit graphicsLayerBlock;
    private boolean lookaheadConstraintsAvailable;
    private Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> offsetAnimation;
    private Transition<EnterExitState>.DeferredAnimation<IntSize, AnimationVector2D> sizeAnimation;
    private Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> slideAnimation;
    private Transition<EnterExitState> transition;
    private long lookaheadSize = AnimationModifierKt.getInvalidSize();
    private long lookaheadConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
    private final l<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntSize>> sizeTransitionSpec = new EnterExitTransitionModifierNode$sizeTransitionSpec$1(this);
    private final l<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntOffset>> slideSpec = new EnterExitTransitionModifierNode$slideSpec$1(this);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EnterExitState.values().length];
            try {
                iArr[EnterExitState.Visible.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EnterExitState.PreEnter.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EnterExitState.PostExit.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public EnterExitTransitionModifierNode(Transition<EnterExitState> transition, Transition<EnterExitState>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation, Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> deferredAnimation2, Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> deferredAnimation3, EnterTransition enterTransition, ExitTransition exitTransition, GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit) {
        this.transition = transition;
        this.sizeAnimation = deferredAnimation;
        this.offsetAnimation = deferredAnimation2;
        this.slideAnimation = deferredAnimation3;
        this.enter = enterTransition;
        this.exit = exitTransition;
        this.graphicsLayerBlock = graphicsLayerBlockForEnterExit;
    }

    /* JADX INFO: renamed from: setLookaheadConstraints-BRTryo0, reason: not valid java name */
    private final void m54setLookaheadConstraintsBRTryo0(long j10) {
        this.lookaheadConstraintsAvailable = true;
        this.lookaheadConstraints = j10;
    }

    public final Alignment getAlignment() {
        Alignment alignment;
        Alignment alignment2;
        if (this.transition.getSegment().isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
            ChangeSize changeSize = this.enter.getData().getChangeSize();
            if (changeSize != null && (alignment2 = changeSize.getAlignment()) != null) {
                return alignment2;
            }
            ChangeSize changeSize2 = this.exit.getData().getChangeSize();
            if (changeSize2 != null) {
                return changeSize2.getAlignment();
            }
            return null;
        }
        ChangeSize changeSize3 = this.exit.getData().getChangeSize();
        if (changeSize3 != null && (alignment = changeSize3.getAlignment()) != null) {
            return alignment;
        }
        ChangeSize changeSize4 = this.enter.getData().getChangeSize();
        if (changeSize4 != null) {
            return changeSize4.getAlignment();
        }
        return null;
    }

    public final Alignment getCurrentAlignment() {
        return this.currentAlignment;
    }

    public final EnterTransition getEnter() {
        return this.enter;
    }

    public final ExitTransition getExit() {
        return this.exit;
    }

    public final GraphicsLayerBlockForEnterExit getGraphicsLayerBlock() {
        return this.graphicsLayerBlock;
    }

    public final Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> getOffsetAnimation() {
        return this.offsetAnimation;
    }

    public final Transition<EnterExitState>.DeferredAnimation<IntSize, AnimationVector2D> getSizeAnimation() {
        return this.sizeAnimation;
    }

    public final l<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntSize>> getSizeTransitionSpec() {
        return this.sizeTransitionSpec;
    }

    public final Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> getSlideAnimation() {
        return this.slideAnimation;
    }

    public final l<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntOffset>> getSlideSpec() {
        return this.slideSpec;
    }

    public final Transition<EnterExitState> getTransition() {
        return this.transition;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        State<IntOffset> stateAnimate;
        State<IntOffset> stateAnimate2;
        if (this.transition.getCurrentState() == this.transition.getTargetState()) {
            this.currentAlignment = null;
        } else if (this.currentAlignment == null) {
            Alignment alignment = getAlignment();
            if (alignment == null) {
                alignment = Alignment.INSTANCE.getTopStart();
            }
            this.currentAlignment = alignment;
        }
        if (measureScope.isLookingAhead()) {
            Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
            long jIntSize = IntSizeKt.IntSize(placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight());
            this.lookaheadSize = jIntSize;
            m54setLookaheadConstraintsBRTryo0(j10);
            return MeasureScope.CC.q(measureScope, IntSize.m5844getWidthimpl(jIntSize), IntSize.m5843getHeightimpl(jIntSize), null, new EnterExitTransitionModifierNode$measure$1(placeableMo4631measureBRTryo0), 4, null);
        }
        l<GraphicsLayerScope, t0> lVarInit = this.graphicsLayerBlock.init();
        Placeable placeableMo4631measureBRTryo02 = measurable.mo4631measureBRTryo0(j10);
        long jIntSize2 = IntSizeKt.IntSize(placeableMo4631measureBRTryo02.getWidth(), placeableMo4631measureBRTryo02.getHeight());
        long j11 = AnimationModifierKt.m33isValidozmzZPI(this.lookaheadSize) ? this.lookaheadSize : jIntSize2;
        Transition<EnterExitState>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation = this.sizeAnimation;
        State<IntSize> stateAnimate3 = deferredAnimation != null ? deferredAnimation.animate(this.sizeTransitionSpec, new EnterExitTransitionModifierNode$measure$animSize$1(this, j11)) : null;
        if (stateAnimate3 != null) {
            jIntSize2 = stateAnimate3.getValue().getPackedValue();
        }
        long jM5657constrain4WqzIAM = ConstraintsKt.m5657constrain4WqzIAM(j10, jIntSize2);
        Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> deferredAnimation2 = this.offsetAnimation;
        long jM5812getZeronOccac = (deferredAnimation2 == null || (stateAnimate2 = deferredAnimation2.animate(EnterExitTransitionModifierNode$measure$offsetDelta$1.INSTANCE, new EnterExitTransitionModifierNode$measure$offsetDelta$2(this, j11))) == null) ? IntOffset.INSTANCE.m5812getZeronOccac() : stateAnimate2.getValue().getPackedValue();
        Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> deferredAnimation3 = this.slideAnimation;
        long jM5812getZeronOccac2 = (deferredAnimation3 == null || (stateAnimate = deferredAnimation3.animate(this.slideSpec, new EnterExitTransitionModifierNode$measure$slideOffset$1(this, j11))) == null) ? IntOffset.INSTANCE.m5812getZeronOccac() : stateAnimate.getValue().getPackedValue();
        Alignment alignment2 = this.currentAlignment;
        long jMo3109alignKFBX0sM = alignment2 != null ? alignment2.mo3109alignKFBX0sM(j11, jM5657constrain4WqzIAM, LayoutDirection.Ltr) : IntOffset.INSTANCE.m5812getZeronOccac();
        return MeasureScope.CC.q(measureScope, IntSize.m5844getWidthimpl(jM5657constrain4WqzIAM), IntSize.m5843getHeightimpl(jM5657constrain4WqzIAM), null, new EnterExitTransitionModifierNode$measure$2(placeableMo4631measureBRTryo02, c.c(IntOffset.m5803getYimpl(jMo3109alignKFBX0sM), IntOffset.m5802getXimpl(jM5812getZeronOccac2) + IntOffset.m5802getXimpl(jMo3109alignKFBX0sM), jM5812getZeronOccac2), jM5812getZeronOccac, lVarInit), 4, null);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        this.lookaheadConstraintsAvailable = false;
        this.lookaheadSize = AnimationModifierKt.getInvalidSize();
    }

    public final void setCurrentAlignment(Alignment alignment) {
        this.currentAlignment = alignment;
    }

    public final void setEnter(EnterTransition enterTransition) {
        this.enter = enterTransition;
    }

    public final void setExit(ExitTransition exitTransition) {
        this.exit = exitTransition;
    }

    public final void setGraphicsLayerBlock(GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit) {
        this.graphicsLayerBlock = graphicsLayerBlockForEnterExit;
    }

    public final void setOffsetAnimation(Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> deferredAnimation) {
        this.offsetAnimation = deferredAnimation;
    }

    public final void setSizeAnimation(Transition<EnterExitState>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation) {
        this.sizeAnimation = deferredAnimation;
    }

    public final void setSlideAnimation(Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> deferredAnimation) {
        this.slideAnimation = deferredAnimation;
    }

    public final void setTransition(Transition<EnterExitState> transition) {
        this.transition = transition;
    }

    /* JADX INFO: renamed from: sizeByState-Uzc_VyU, reason: not valid java name */
    public final long m56sizeByStateUzc_VyU(EnterExitState targetState, long fullSize) {
        l<IntSize, IntSize> size;
        l<IntSize, IntSize> size2;
        int i10 = WhenMappings.$EnumSwitchMapping$0[targetState.ordinal()];
        if (i10 != 1) {
            if (i10 == 2) {
                ChangeSize changeSize = this.enter.getData().getChangeSize();
                if (changeSize != null && (size = changeSize.getSize()) != null) {
                    return ((IntSize) size.invoke(IntSize.m5836boximpl(fullSize))).getPackedValue();
                }
            } else {
                if (i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                ChangeSize changeSize2 = this.exit.getData().getChangeSize();
                if (changeSize2 != null && (size2 = changeSize2.getSize()) != null) {
                    return ((IntSize) size2.invoke(IntSize.m5836boximpl(fullSize))).getPackedValue();
                }
            }
        }
        return fullSize;
    }

    /* JADX INFO: renamed from: slideTargetValueByState-oFUgxo0, reason: not valid java name */
    public final long m57slideTargetValueByStateoFUgxo0(EnterExitState targetState, long fullSize) {
        l<IntSize, IntOffset> slideOffset;
        l<IntSize, IntOffset> slideOffset2;
        Slide slide = this.enter.getData().getSlide();
        long jM5812getZeronOccac = (slide == null || (slideOffset2 = slide.getSlideOffset()) == null) ? IntOffset.INSTANCE.m5812getZeronOccac() : ((IntOffset) slideOffset2.invoke(IntSize.m5836boximpl(fullSize))).getPackedValue();
        Slide slide2 = this.exit.getData().getSlide();
        long jM5812getZeronOccac2 = (slide2 == null || (slideOffset = slide2.getSlideOffset()) == null) ? IntOffset.INSTANCE.m5812getZeronOccac() : ((IntOffset) slideOffset.invoke(IntSize.m5836boximpl(fullSize))).getPackedValue();
        int i10 = WhenMappings.$EnumSwitchMapping$0[targetState.ordinal()];
        if (i10 == 1) {
            return IntOffset.INSTANCE.m5812getZeronOccac();
        }
        if (i10 == 2) {
            return jM5812getZeronOccac;
        }
        if (i10 == 3) {
            return jM5812getZeronOccac2;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: targetOffsetByState-oFUgxo0, reason: not valid java name */
    public final long m58targetOffsetByStateoFUgxo0(EnterExitState targetState, long fullSize) {
        if (this.currentAlignment == null) {
            return IntOffset.INSTANCE.m5812getZeronOccac();
        }
        if (getAlignment() == null) {
            return IntOffset.INSTANCE.m5812getZeronOccac();
        }
        if (p.a(this.currentAlignment, getAlignment())) {
            return IntOffset.INSTANCE.m5812getZeronOccac();
        }
        int i10 = WhenMappings.$EnumSwitchMapping$0[targetState.ordinal()];
        if (i10 == 1) {
            return IntOffset.INSTANCE.m5812getZeronOccac();
        }
        if (i10 == 2) {
            return IntOffset.INSTANCE.m5812getZeronOccac();
        }
        if (i10 != 3) {
            throw new NoWhenBranchMatchedException();
        }
        ChangeSize changeSize = this.exit.getData().getChangeSize();
        if (changeSize == null) {
            return IntOffset.INSTANCE.m5812getZeronOccac();
        }
        long packedValue = ((IntSize) changeSize.getSize().invoke(IntSize.m5836boximpl(fullSize))).getPackedValue();
        Alignment alignment = getAlignment();
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        long jMo3109alignKFBX0sM = alignment.mo3109alignKFBX0sM(fullSize, packedValue, layoutDirection);
        long jMo3109alignKFBX0sM2 = this.currentAlignment.mo3109alignKFBX0sM(fullSize, packedValue, layoutDirection);
        return IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jMo3109alignKFBX0sM) - IntOffset.m5802getXimpl(jMo3109alignKFBX0sM2), IntOffset.m5803getYimpl(jMo3109alignKFBX0sM) - IntOffset.m5803getYimpl(jMo3109alignKFBX0sM2));
    }
}
