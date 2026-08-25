package androidx.compose.foundation.text2.input.internal;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.internal.selection.AndroidTextFieldMagnifier_androidKt;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNode;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.h;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextPainter;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.Constraints;
import d7.d;
import f7.e;
import f7.j;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.k0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import na.y0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006BG\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J&\u0010 \u001a\u00020\u001d*\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ&\u0010\"\u001a\u00020\u001d*\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001fJ\u001a\u0010(\u001a\u00020%2\u0006\u0010$\u001a\u00020#H\u0002ø\u0001\u0000¢\u0006\u0004\b&\u0010'J)\u0010.\u001a\u00020-2\b\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020%H\u0002¢\u0006\u0004\b.\u0010/J&\u00106\u001a\u00020-*\u0002002\u0006\u00101\u001a\u00020#2\u0006\u00103\u001a\u000202H\u0002ø\u0001\u0000¢\u0006\u0004\b4\u00105J\u001b\u00107\u001a\u00020-*\u0002002\u0006\u00103\u001a\u000202H\u0002¢\u0006\u0004\b7\u00108J\u0013\u00109\u001a\u00020-*\u000200H\u0002¢\u0006\u0004\b9\u0010:JM\u0010;\u001a\u00020-2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b;\u0010\u0017J&\u0010=\u001a\u00020\u001d*\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0016ø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u0013\u0010?\u001a\u00020-*\u00020>H\u0016¢\u0006\u0004\b?\u0010@J\u0017\u0010C\u001a\u00020-2\u0006\u0010B\u001a\u00020AH\u0016¢\u0006\u0004\bC\u0010DJ\u0013\u0010F\u001a\u00020-*\u00020EH\u0016¢\u0006\u0004\bF\u0010GR\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010HR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010IR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010JR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010KR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010LR\u0016\u0010\u0011\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010HR\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010MR\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010NR \u0010R\u001a\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020Q0O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0018\u0010U\u001a\u0004\u0018\u00010T8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u001e\u0010W\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010Y\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010ZR\u0014\u0010\\\u001a\u00020[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010]R\u0014\u0010`\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006a"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextFieldCoreModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "", "isFocused", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "textFieldSelectionState", "Landroidx/compose/ui/graphics/Brush;", "cursorBrush", "writeable", "Landroidx/compose/foundation/ScrollState;", "scrollState", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "<init>", "(ZLandroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/graphics/Brush;ZLandroidx/compose/foundation/ScrollState;Landroidx/compose/foundation/gestures/Orientation;)V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measureVerticalScroll-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measureVerticalScroll", "measureHorizontalScroll-3p2s80s", "measureHorizontalScroll", "Landroidx/compose/ui/text/TextRange;", "currSelection", "", "calculateOffsetToFollow-5zc-tL8", "(J)I", "calculateOffsetToFollow", "Landroidx/compose/ui/geometry/Rect;", "cursorRect", "containerSize", "textFieldSize", "Lx6/t0;", "updateScrollState", "(Landroidx/compose/ui/geometry/Rect;II)V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "selection", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "drawSelection-Sb-Bc2M", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/text/TextLayoutResult;)V", "drawSelection", "drawText", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;)V", "drawCursor", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "updateNode", "measure-3p2s80s", "measure", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "onGloballyPositioned", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "applySemantics", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "Z", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "Landroidx/compose/ui/graphics/Brush;", "Landroidx/compose/foundation/ScrollState;", "Landroidx/compose/foundation/gestures/Orientation;", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "cursorAlpha", "Landroidx/compose/animation/core/Animatable;", "Lka/v1;", "changeObserverJob", "Lka/v1;", "previousSelection", "Landroidx/compose/ui/text/TextRange;", "previousCursorRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldMagnifierNode;", "textFieldMagnifierNode", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldMagnifierNode;", "getShowCursor", "()Z", "showCursor", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldCoreModifierNode extends DelegatingNode implements LayoutModifierNode, DrawModifierNode, CompositionLocalConsumerModifierNode, GlobalPositionAwareModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private v1 changeObserverJob;
    private Brush cursorBrush;
    private boolean isFocused;
    private Orientation orientation;
    private TextRange previousSelection;
    private ScrollState scrollState;
    private final TextFieldMagnifierNode textFieldMagnifierNode;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;
    private boolean writeable;
    private final Animatable<Float, AnimationVector1D> cursorAlpha = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
    private Rect previousCursorRect = new Rect(-1.0f, -1.0f, -1.0f, -1.0f);

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$1", f = "TextFieldCoreModifier.kt", l = {217}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return TextFieldCoreModifierNode.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                Animatable animatable = TextFieldCoreModifierNode.this.cursorAlpha;
                Float f10 = new Float(0.0f);
                this.label = 1;
                Object objSnapTo = animatable.snapTo(f10, this);
                e7.a aVar = e7.a.f15033i;
                if (objSnapTo == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2", f = "TextFieldCoreModifier.kt", l = {226}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ TransformedTextFieldState $textFieldState;
        int label;
        final /* synthetic */ TextFieldCoreModifierNode this$0;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1", f = "TextFieldCoreModifier.kt", l = {228}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ TransformedTextFieldState $textFieldState;
            int label;
            final /* synthetic */ TextFieldCoreModifierNode this$0;

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class C00661 extends r implements r7.a<TextFieldCharSequence> {
                final /* synthetic */ TransformedTextFieldState $textFieldState;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00661(TransformedTextFieldState transformedTextFieldState) {
                    super(0);
                    this.$textFieldState = transformedTextFieldState;
                }

                @Override // r7.a
                public final TextFieldCharSequence invoke() {
                    return this.$textFieldState.getText();
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "it", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1$2", f = "TextFieldCoreModifier.kt", l = {230, 232}, m = "invokeSuspend")
            public static final class C00672 extends j implements p<TextFieldCharSequence, d<? super t0>, Object> {
                int label;
                final /* synthetic */ TextFieldCoreModifierNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00672(TextFieldCoreModifierNode textFieldCoreModifierNode, d<? super C00672> dVar) {
                    super(2, dVar);
                    this.this$0 = textFieldCoreModifierNode;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    return new C00672(this.this$0, dVar);
                }

                @Override // r7.p
                public final Object invoke(TextFieldCharSequence textFieldCharSequence, d<? super t0> dVar) {
                    return ((C00672) create(textFieldCharSequence, dVar)).invokeSuspend(t0.f22605a);
                }

                /* JADX WARN: Code restructure failed: missing block: B:14:0x0051, code lost:
                
                    if (androidx.compose.animation.core.Animatable.animateTo$default(r4, r5, r6, null, null, r12, 12, null) == r3) goto L15;
                 */
                @Override // f7.a
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                    /*
                        r12 = this;
                        int r0 = r12.label
                        r1 = 2
                        r2 = 1
                        e7.a r3 = e7.a.f15033i
                        if (r0 == 0) goto L1c
                        if (r0 == r2) goto L18
                        if (r0 != r1) goto L10
                        k2.c.G(r13)
                        goto L54
                    L10:
                        java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r13.<init>(r0)
                        throw r13
                    L18:
                        k2.c.G(r13)
                        goto L35
                    L1c:
                        k2.c.G(r13)
                        androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode r13 = r12.this$0
                        androidx.compose.animation.core.Animatable r13 = androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode.access$getCursorAlpha$p(r13)
                        java.lang.Float r0 = new java.lang.Float
                        r4 = 1065353216(0x3f800000, float:1.0)
                        r0.<init>(r4)
                        r12.label = r2
                        java.lang.Object r13 = r13.snapTo(r0, r12)
                        if (r13 != r3) goto L35
                        goto L53
                    L35:
                        androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode r13 = r12.this$0
                        androidx.compose.animation.core.Animatable r4 = androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode.access$getCursorAlpha$p(r13)
                        java.lang.Float r5 = new java.lang.Float
                        r13 = 0
                        r5.<init>(r13)
                        androidx.compose.animation.core.AnimationSpec r6 = androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierKt.access$getCursorAnimationSpec$p()
                        r12.label = r1
                        r7 = 0
                        r8 = 0
                        r10 = 12
                        r11 = 0
                        r9 = r12
                        java.lang.Object r13 = androidx.compose.animation.core.Animatable.animateTo$default(r4, r5, r6, r7, r8, r9, r10, r11)
                        if (r13 != r3) goto L54
                    L53:
                        return r3
                    L54:
                        x6.t0 r13 = x6.t0.f22605a
                        return r13
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode.AnonymousClass2.AnonymousClass1.C00672.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TransformedTextFieldState transformedTextFieldState, TextFieldCoreModifierNode textFieldCoreModifierNode, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$textFieldState = transformedTextFieldState;
                this.this$0 = textFieldCoreModifierNode;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.$textFieldState, this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new C00661(this.$textFieldState));
                    C00672 c00672 = new C00672(this.this$0, null);
                    this.label = 1;
                    Object objG = y0.g(jVarSnapshotFlow, c00672, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objG == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TransformedTextFieldState transformedTextFieldState, TextFieldCoreModifierNode textFieldCoreModifierNode, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$textFieldState = transformedTextFieldState;
            this.this$0 = textFieldCoreModifierNode;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$textFieldState, this.this$0, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                FixedMotionDurationScale fixedMotionDurationScale = FixedMotionDurationScale.INSTANCE;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$textFieldState, this.this$0, null);
                this.label = 1;
                Object objY = m0.y(fixedMotionDurationScale, anonymousClass1, this);
                e7.a aVar = e7.a.f15033i;
                if (objY == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateScrollState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateScrollState$1", f = "TextFieldCoreModifier.kt", l = {453}, m = "invokeSuspend")
    public static final class C04171 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ float $offsetDifference;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04171(float f10, d<? super C04171> dVar) {
            super(2, dVar);
            this.$offsetDifference = f10;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return TextFieldCoreModifierNode.this.new C04171(this.$offsetDifference, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ScrollState scrollState = TextFieldCoreModifierNode.this.scrollState;
                float fRoundToNext = TextFieldCoreModifierKt.roundToNext(this.$offsetDifference);
                this.label = 1;
                Object objScrollBy = ScrollExtensionsKt.scrollBy(scrollState, fRoundToNext, this);
                e7.a aVar = e7.a.f15033i;
                if (objScrollBy == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C04171) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public TextFieldCoreModifierNode(boolean z, TextLayoutState textLayoutState, TransformedTextFieldState transformedTextFieldState, TextFieldSelectionState textFieldSelectionState, Brush brush, boolean z5, ScrollState scrollState, Orientation orientation) {
        this.isFocused = z;
        this.textLayoutState = textLayoutState;
        this.textFieldState = transformedTextFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = brush;
        this.writeable = z5;
        this.scrollState = scrollState;
        this.orientation = orientation;
        this.textFieldMagnifierNode = (TextFieldMagnifierNode) delegate(AndroidTextFieldMagnifier_androidKt.textFieldMagnifierNode(this.textFieldState, this.textFieldSelectionState, this.textLayoutState, this.isFocused));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateOffsetToFollow-5zc-tL8, reason: not valid java name */
    public final int m1105calculateOffsetToFollow5zctL8(long currSelection) {
        TextRange textRange = this.previousSelection;
        if (textRange == null || TextRange.m5187getEndimpl(currSelection) != TextRange.m5187getEndimpl(textRange.getPackedValue())) {
            return TextRange.m5187getEndimpl(currSelection);
        }
        TextRange textRange2 = this.previousSelection;
        if (textRange2 == null || TextRange.m5192getStartimpl(currSelection) != TextRange.m5192getStartimpl(textRange2.getPackedValue())) {
            return TextRange.m5192getStartimpl(currSelection);
        }
        return -1;
    }

    private final void drawCursor(DrawScope drawScope) {
        if (this.cursorAlpha.getValue().floatValue() <= 0.0f || !getShowCursor()) {
            return;
        }
        float fM = qb.d.m(this.cursorAlpha.getValue().floatValue(), 0.0f, 1.0f);
        if (fM == 0.0f) {
            return;
        }
        Rect cursorRect = this.textFieldSelectionState.getCursorRect();
        androidx.compose.ui.graphics.drawscope.c.B(drawScope, this.cursorBrush, cursorRect.m3270getTopCenterF1C5BW0(), cursorRect.m3263getBottomCenterF1C5BW0(), cursorRect.getWidth(), 0, null, fM, null, 0, 432, null);
    }

    /* JADX INFO: renamed from: drawSelection-Sb-Bc2M, reason: not valid java name */
    private final void m1106drawSelectionSbBc2M(DrawScope drawScope, long j10, TextLayoutResult textLayoutResult) {
        int iM5190getMinimpl = TextRange.m5190getMinimpl(j10);
        int iM5189getMaximpl = TextRange.m5189getMaximpl(j10);
        if (iM5190getMinimpl != iM5189getMaximpl) {
            androidx.compose.ui.graphics.drawscope.c.G(drawScope, textLayoutResult.getPathForRange(iM5190getMinimpl, iM5189getMaximpl), ((TextSelectionColors) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, TextSelectionColorsKt.getLocalTextSelectionColors())).getBackgroundColor(), 0.0f, null, null, 0, 60, null);
        }
    }

    private final void drawText(DrawScope drawScope, TextLayoutResult textLayoutResult) {
        TextPainter.INSTANCE.paint(drawScope.getDrawContext().getCanvas(), textLayoutResult);
    }

    private final boolean getShowCursor() {
        return this.writeable && this.isFocused && TextFieldCoreModifierKt.isSpecified(this.cursorBrush);
    }

    /* JADX INFO: renamed from: measureHorizontalScroll-3p2s80s, reason: not valid java name */
    private final MeasureResult m1107measureHorizontalScroll3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        long j11;
        if (measurable.maxIntrinsicWidth(Constraints.m5645getMaxHeightimpl(j10)) < Constraints.m5646getMaxWidthimpl(j10)) {
            j11 = j10;
        } else {
            j11 = j10;
            j10 = Constraints.m5637copyZbe2FdA$default(j11, 0, Integer.MAX_VALUE, 0, 0, 13, null);
        }
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
        int iMin = Math.min(placeableMo4631measureBRTryo0.getWidth(), Constraints.m5646getMaxWidthimpl(j11));
        return MeasureScope.CC.q(measureScope, iMin, placeableMo4631measureBRTryo0.getHeight(), null, new TextFieldCoreModifierNode$measureHorizontalScroll$1(this, measureScope, placeableMo4631measureBRTryo0, iMin), 4, null);
    }

    /* JADX INFO: renamed from: measureVerticalScroll-3p2s80s, reason: not valid java name */
    private final MeasureResult m1108measureVerticalScroll3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, Integer.MAX_VALUE, 7, null));
        int iMin = Math.min(placeableMo4631measureBRTryo0.getHeight(), Constraints.m5645getMaxHeightimpl(j10));
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), iMin, null, new TextFieldCoreModifierNode$measureVerticalScroll$1(this, measureScope, placeableMo4631measureBRTryo0, iMin), 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateScrollState(androidx.compose.ui.geometry.Rect r8, int r9, int r10) {
        /*
            r7 = this;
            int r10 = r10 - r9
            androidx.compose.foundation.ScrollState r0 = r7.scrollState
            r0.setMaxValue$foundation_release(r10)
            boolean r10 = r7.getShowCursor()
            if (r10 == 0) goto L85
            if (r8 != 0) goto L10
            goto L85
        L10:
            float r10 = r8.getLeft()
            androidx.compose.ui.geometry.Rect r0 = r7.previousCursorRect
            float r0 = r0.getLeft()
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 != 0) goto L2d
            float r10 = r8.getTop()
            androidx.compose.ui.geometry.Rect r0 = r7.previousCursorRect
            float r0 = r0.getTop()
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 != 0) goto L2d
            goto L85
        L2d:
            androidx.compose.foundation.gestures.Orientation r10 = r7.orientation
            androidx.compose.foundation.gestures.Orientation r0 = androidx.compose.foundation.gestures.Orientation.Vertical
            r1 = 1
            if (r10 != r0) goto L36
            r10 = r1
            goto L37
        L36:
            r10 = 0
        L37:
            if (r10 == 0) goto L3e
            float r0 = r8.getTop()
            goto L42
        L3e:
            float r0 = r8.getLeft()
        L42:
            if (r10 == 0) goto L49
            float r10 = r8.getBottom()
            goto L4d
        L49:
            float r10 = r8.getRight()
        L4d:
            androidx.compose.foundation.ScrollState r2 = r7.scrollState
            int r2 = r2.getValue()
            int r3 = r2 + r9
            float r3 = (float) r3
            int r4 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r4 <= 0) goto L5c
        L5a:
            float r10 = r10 - r3
            goto L75
        L5c:
            float r2 = (float) r2
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L69
            float r5 = r10 - r0
            float r6 = (float) r9
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L69
            goto L5a
        L69:
            if (r4 >= 0) goto L74
            float r10 = r10 - r0
            float r9 = (float) r9
            int r9 = (r10 > r9 ? 1 : (r10 == r9 ? 0 : -1))
            if (r9 > 0) goto L74
            float r10 = r0 - r2
            goto L75
        L74:
            r10 = 0
        L75:
            r7.previousCursorRect = r8
            ka.k0 r8 = r7.getCoroutineScope()
            androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateScrollState$1 r9 = new androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateScrollState$1
            r0 = 0
            r9.<init>(r10, r0)
            r10 = 4
            ka.m0.p(r8, r0, r10, r9, r1)
        L85:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode.updateScrollState(androidx.compose.ui.geometry.Rect, int, int):void");
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        this.textFieldMagnifierNode.applySemantics(semanticsPropertyReceiver);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        TextFieldCharSequence text = this.textFieldState.getText();
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return;
        }
        if (TextRange.m5186getCollapsedimpl(text.getSelectionInChars())) {
            drawText(contentDrawScope, layoutResult);
            drawCursor(contentDrawScope);
        } else {
            m1106drawSelectionSbBc2M(contentDrawScope, text.getSelectionInChars(), layoutResult);
            drawText(contentDrawScope, layoutResult);
        }
        this.textFieldMagnifierNode.draw(contentDrawScope);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    /* JADX INFO: renamed from: getShouldClearDescendantSemantics */
    public final /* synthetic */ boolean getIsClearingSemantics() {
        return h.a(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return h.b(this);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.a(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.b(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        return this.orientation == Orientation.Vertical ? m1108measureVerticalScroll3p2s80s(measureScope, measurable, j10) : m1107measureHorizontalScroll3p2s80s(measureScope, measurable, j10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.c(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.d(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.textLayoutState.setCoreNodeCoordinates(coordinates);
        this.textFieldMagnifierNode.onGloballyPositioned(coordinates);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final /* synthetic */ void onMeasureResultChanged() {
        androidx.compose.ui.node.b.a(this);
    }

    public final void updateNode(boolean isFocused, TextLayoutState textLayoutState, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, Brush cursorBrush, boolean writeable, ScrollState scrollState, Orientation orientation) {
        boolean showCursor = getShowCursor();
        boolean z = this.isFocused;
        TransformedTextFieldState transformedTextFieldState = this.textFieldState;
        TextLayoutState textLayoutState2 = this.textLayoutState;
        TextFieldSelectionState textFieldSelectionState2 = this.textFieldSelectionState;
        ScrollState scrollState2 = this.scrollState;
        this.isFocused = isFocused;
        this.textLayoutState = textLayoutState;
        this.textFieldState = textFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = cursorBrush;
        this.writeable = writeable;
        this.scrollState = scrollState;
        this.orientation = orientation;
        this.textFieldMagnifierNode.update(textFieldState, textFieldSelectionState, textLayoutState, isFocused);
        if (!getShowCursor()) {
            v1 v1Var = this.changeObserverJob;
            if (v1Var != null) {
                v1Var.cancel((CancellationException) null);
            }
            this.changeObserverJob = null;
            m0.p(getCoroutineScope(), null, 0, new AnonymousClass1(null), 3);
        } else if (!z || !kotlin.jvm.internal.p.a(transformedTextFieldState, textFieldState) || !showCursor) {
            this.changeObserverJob = m0.p(getCoroutineScope(), null, 0, new AnonymousClass2(textFieldState, this, null), 3);
        }
        if (kotlin.jvm.internal.p.a(transformedTextFieldState, textFieldState) && kotlin.jvm.internal.p.a(textLayoutState2, textLayoutState) && kotlin.jvm.internal.p.a(textFieldSelectionState2, textFieldSelectionState) && kotlin.jvm.internal.p.a(scrollState2, scrollState)) {
            return;
        }
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }
}
