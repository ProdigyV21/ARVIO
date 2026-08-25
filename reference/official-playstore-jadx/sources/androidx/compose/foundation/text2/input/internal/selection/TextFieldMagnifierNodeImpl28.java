package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.MagnifierNode;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.text.selection.SelectionMagnifierKt;
import androidx.compose.foundation.text2.input.internal.TextLayoutState;
import androidx.compose.foundation.text2.input.internal.TransformedTextFieldState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.IntSize;
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
import na.k;
import r7.a;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u000fJ/\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0011\u0010\fJ\u0013\u0010\u0013\u001a\u00020\r*\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u001a\u001a\u00020\r*\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0004\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u001cR\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u001dR\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u001eR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u001fR1\u0010(\u001a\u00020 2\u0006\u0010!\u001a\u00020 8B@BX\u0082\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R \u0010,\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0018\u00102\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00064"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/selection/TextFieldMagnifierNodeImpl28;", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldMagnifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "textFieldSelectionState", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "textLayoutState", "", "isFocused", "<init>", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/foundation/text2/input/internal/TextLayoutState;Z)V", "Lx6/t0;", "restartAnimationJob", "()V", "onAttach", "update", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "onGloballyPositioned", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "applySemantics", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "Z", "Landroidx/compose/ui/unit/IntSize;", "<set-?>", "magnifierSize$delegate", "Landroidx/compose/runtime/MutableState;", "getMagnifierSize-YbymL2g", "()J", "setMagnifierSize-ozmzZPI", "(J)V", "magnifierSize", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/animation/core/AnimationVector2D;", "animatable", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/foundation/MagnifierNode;", "magnifierNode", "Landroidx/compose/foundation/MagnifierNode;", "Lka/v1;", "animationJob", "Lka/v1;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldMagnifierNodeImpl28 extends TextFieldMagnifierNode implements CompositionLocalConsumerModifierNode {
    public static final int $stable = 0;
    private final Animatable<Offset, AnimationVector2D> animatable;
    private v1 animationJob;
    private boolean isFocused;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;

    /* JADX INFO: renamed from: magnifierSize$delegate, reason: from kotlin metadata */
    private final MutableState magnifierSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m5836boximpl(IntSize.INSTANCE.m5849getZeroYbymL2g()), null, 2, null);
    private final MagnifierNode magnifierNode = (MagnifierNode) delegate(new MagnifierNode(new TextFieldMagnifierNodeImpl28$magnifierNode$1(this), null, new TextFieldMagnifierNodeImpl28$magnifierNode$2(this), 0.0f, true, 0, 0.0f, 0.0f, false, null, 1002, null));

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1", f = "AndroidTextFieldMagnifier.android.kt", l = {126}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "invoke-F1C5BW0", "()J"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C00721 extends r implements a<Offset> {
            final /* synthetic */ TextFieldMagnifierNodeImpl28 this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00721(TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28) {
                super(0);
                this.this$0 = textFieldMagnifierNodeImpl28;
            }

            @Override // r7.a
            public /* bridge */ /* synthetic */ Object invoke() {
                return Offset.m3225boximpl(m1158invokeF1C5BW0());
            }

            /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
            public final long m1158invokeF1C5BW0() {
                return TextFieldMagnifierKt.m1151calculateSelectionMagnifierCenterAndroidhUlJWOE(this.this$0.textFieldState, this.this$0.textFieldSelectionState, this.this$0.textLayoutState, this.this$0.m1154getMagnifierSizeYbymL2g());
            }
        }

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = TextFieldMagnifierNodeImpl28.this.new AnonymousClass1(dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                final k0 k0Var = (k0) this.L$0;
                na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new C00721(TextFieldMagnifierNodeImpl28.this));
                final TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28 = TextFieldMagnifierNodeImpl28.this;
                k kVar = new k() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNodeImpl28.restartAnimationJob.1.2

                    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1$2$1, reason: invalid class name and collision with other inner class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1$2$1", f = "AndroidTextFieldMagnifier.android.kt", l = {140}, m = "invokeSuspend")
                    public static final class C00731 extends j implements p<k0, d<? super t0>, Object> {
                        final /* synthetic */ long $targetValue;
                        int label;
                        final /* synthetic */ TextFieldMagnifierNodeImpl28 this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C00731(TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28, long j10, d<? super C00731> dVar) {
                            super(2, dVar);
                            this.this$0 = textFieldMagnifierNodeImpl28;
                            this.$targetValue = j10;
                        }

                        @Override // f7.a
                        public final d<t0> create(Object obj, d<?> dVar) {
                            return new C00731(this.this$0, this.$targetValue, dVar);
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            int i10 = this.label;
                            if (i10 == 0) {
                                c.G(obj);
                                Animatable animatable = this.this$0.animatable;
                                Offset offsetM3225boximpl = Offset.m3225boximpl(this.$targetValue);
                                SpringSpec<Offset> magnifierSpringSpec = SelectionMagnifierKt.getMagnifierSpringSpec();
                                this.label = 1;
                                Object objAnimateTo$default = Animatable.animateTo$default(animatable, offsetM3225boximpl, magnifierSpringSpec, null, null, this, 12, null);
                                e7.a aVar = e7.a.f15033i;
                                if (objAnimateTo$default == aVar) {
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
                            return ((C00731) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                        }
                    }

                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return m1159emit3MmeM6k(((Offset) obj2).getPackedValue(), dVar);
                    }

                    /* JADX INFO: renamed from: emit-3MmeM6k, reason: not valid java name */
                    public final Object m1159emit3MmeM6k(long j10, d<? super t0> dVar) {
                        boolean zM3255isSpecifiedk4lQ0M = OffsetKt.m3255isSpecifiedk4lQ0M(((Offset) textFieldMagnifierNodeImpl28.animatable.getValue()).getPackedValue());
                        t0 t0Var = t0.f22605a;
                        if (zM3255isSpecifiedk4lQ0M && OffsetKt.m3255isSpecifiedk4lQ0M(j10) && Offset.m3237getYimpl(((Offset) textFieldMagnifierNodeImpl28.animatable.getValue()).getPackedValue()) != Offset.m3237getYimpl(j10)) {
                            m0.p(k0Var, null, 0, new C00731(textFieldMagnifierNodeImpl28, j10, null), 3);
                            return t0Var;
                        }
                        Object objSnapTo = textFieldMagnifierNodeImpl28.animatable.snapTo(Offset.m3225boximpl(j10), dVar);
                        return objSnapTo == e7.a.f15033i ? objSnapTo : t0Var;
                    }
                };
                this.label = 1;
                Object objCollect = jVarSnapshotFlow.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
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

    public TextFieldMagnifierNodeImpl28(TransformedTextFieldState transformedTextFieldState, TextFieldSelectionState textFieldSelectionState, TextLayoutState textLayoutState, boolean z) {
        this.textFieldState = transformedTextFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.textLayoutState = textLayoutState;
        this.isFocused = z;
        this.animatable = new Animatable<>(Offset.m3225boximpl(TextFieldMagnifierKt.m1151calculateSelectionMagnifierCenterAndroidhUlJWOE(this.textFieldState, this.textFieldSelectionState, this.textLayoutState, m1154getMagnifierSizeYbymL2g())), SelectionMagnifierKt.getUnspecifiedSafeOffsetVectorConverter(), Offset.m3225boximpl(SelectionMagnifierKt.getOffsetDisplacementThreshold()), null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getMagnifierSize-YbymL2g, reason: not valid java name */
    public final long m1154getMagnifierSizeYbymL2g() {
        return ((IntSize) this.magnifierSize.getValue()).getPackedValue();
    }

    private final void restartAnimationJob() {
        v1 v1Var = this.animationJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.animationJob = null;
        if (this.isFocused && Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null)) {
            this.animationJob = m0.p(getCoroutineScope(), null, 0, new AnonymousClass1(null), 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setMagnifierSize-ozmzZPI, reason: not valid java name */
    public final void m1155setMagnifierSizeozmzZPI(long j10) {
        this.magnifierSize.setValue(IntSize.m5836boximpl(j10));
    }

    @Override // androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNode, androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        this.magnifierNode.applySemantics(semanticsPropertyReceiver);
    }

    @Override // androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNode, androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        this.magnifierNode.draw(contentDrawScope);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        restartAnimationJob();
    }

    @Override // androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNode, androidx.compose.ui.layout.OnGloballyPositionedModifier
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.magnifierNode.onGloballyPositioned(coordinates);
    }

    @Override // androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNode
    public void update(TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, TextLayoutState textLayoutState, boolean isFocused) {
        TransformedTextFieldState transformedTextFieldState = this.textFieldState;
        TextFieldSelectionState textFieldSelectionState2 = this.textFieldSelectionState;
        TextLayoutState textLayoutState2 = this.textLayoutState;
        boolean z = this.isFocused;
        this.textFieldState = textFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.textLayoutState = textLayoutState;
        this.isFocused = isFocused;
        if (kotlin.jvm.internal.p.a(textFieldState, transformedTextFieldState) && kotlin.jvm.internal.p.a(textFieldSelectionState, textFieldSelectionState2) && kotlin.jvm.internal.p.a(textLayoutState, textLayoutState2) && isFocused == z) {
            return;
        }
        restartAnimationJob();
    }
}
