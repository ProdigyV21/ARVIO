package androidx.compose.foundation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import d7.d;
import f7.e;
import f7.j;
import java.util.concurrent.CancellationException;
import ka.k0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import na.y0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B7\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0013J@\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ&\u0010$\u001a\u00020!*\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001fH\u0016ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J#\u0010(\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001e\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0005H\u0016¢\u0006\u0004\b(\u0010)J#\u0010*\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001e\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0005H\u0016¢\u0006\u0004\b*\u0010)J#\u0010,\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001e\u001a\u00020&2\u0006\u0010+\u001a\u00020\u0005H\u0016¢\u0006\u0004\b,\u0010)J#\u0010-\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001e\u001a\u00020&2\u0006\u0010+\u001a\u00020\u0005H\u0016¢\u0006\u0004\b-\u0010)J\u0013\u0010/\u001a\u00020\u0011*\u00020.H\u0016¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\u0011H\u0002¢\u0006\u0004\b1\u0010\u0013J\u0010\u00102\u001a\u00020\u0011H\u0082@¢\u0006\u0004\b2\u00103R\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u00104R\u0016\u0010\t\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u00104R\u0016\u0010\n\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u00104R\u001c\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000e\u00105R+\u0010=\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R+\u0010A\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b>\u00108\u001a\u0004\b?\u0010:\"\u0004\b@\u0010<R+\u0010I\u001a\u00020B2\u0006\u00106\u001a\u00020B8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR+\u0010\f\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u000b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bM\u0010D\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR1\u0010\b\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00078F@FX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bR\u0010D\u001a\u0004\bS\u0010:\"\u0004\bT\u0010<R \u0010X\u001a\u000e\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020W0U8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YR\u001b\u0010]\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010:R\u0014\u0010`\u001a\u00020V8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006a"}, d2 = {"Landroidx/compose/foundation/MarqueeModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "", "iterations", "Landroidx/compose/foundation/MarqueeAnimationMode;", "animationMode", "delayMillis", "initialDelayMillis", "Landroidx/compose/foundation/MarqueeSpacing;", "spacing", "Landroidx/compose/ui/unit/Dp;", "velocity", "<init>", "(IIIILandroidx/compose/foundation/MarqueeSpacing;FLkotlin/jvm/internal/h;)V", "Lx6/t0;", "onAttach", "()V", "onDetach", "update-lWfNwf4", "(IIIILandroidx/compose/foundation/MarqueeSpacing;F)V", "update", "Landroidx/compose/ui/focus/FocusState;", "focusState", "onFocusEvent", "(Landroidx/compose/ui/focus/FocusState;)V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/layout/IntrinsicMeasurable;I)I", "maxIntrinsicWidth", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "restartAnimation", "runAnimation", "(Ld7/d;)Ljava/lang/Object;", "I", "F", "<set-?>", "contentWidth$delegate", "Landroidx/compose/runtime/MutableIntState;", "getContentWidth", "()I", "setContentWidth", "(I)V", "contentWidth", "containerWidth$delegate", "getContainerWidth", "setContainerWidth", "containerWidth", "", "hasFocus$delegate", "Landroidx/compose/runtime/MutableState;", "getHasFocus", "()Z", "setHasFocus", "(Z)V", "hasFocus", "Lka/v1;", "animationJob", "Lka/v1;", "spacing$delegate", "getSpacing", "()Landroidx/compose/foundation/MarqueeSpacing;", "setSpacing", "(Landroidx/compose/foundation/MarqueeSpacing;)V", "animationMode$delegate", "getAnimationMode-ZbEOnfQ", "setAnimationMode-97h66l8", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "offset", "Landroidx/compose/animation/core/Animatable;", "spacingPx$delegate", "Landroidx/compose/runtime/State;", "getSpacingPx", "spacingPx", "getDirection", "()F", "direction", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MarqueeModifierNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, FocusEventModifierNode {
    private v1 animationJob;

    /* JADX INFO: renamed from: animationMode$delegate, reason: from kotlin metadata */
    private final MutableState animationMode;

    /* JADX INFO: renamed from: containerWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState containerWidth;

    /* JADX INFO: renamed from: contentWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState contentWidth;
    private int delayMillis;

    /* JADX INFO: renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus;
    private int initialDelayMillis;
    private int iterations;
    private final Animatable<Float, AnimationVector1D> offset;

    /* JADX INFO: renamed from: spacing$delegate, reason: from kotlin metadata */
    private final MutableState spacing;

    /* JADX INFO: renamed from: spacingPx$delegate, reason: from kotlin metadata */
    private final State spacingPx;
    private float velocity;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$restartAnimation$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.MarqueeModifierNode$restartAnimation$1", f = "BasicMarquee.kt", l = {345, 346}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ v1 $oldJob;
        int label;
        final /* synthetic */ MarqueeModifierNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(v1 v1Var, MarqueeModifierNode marqueeModifierNode, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$oldJob = v1Var;
            this.this$0 = marqueeModifierNode;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$oldJob, this.this$0, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
        
            if (r5.runAnimation(r4) == r3) goto L17;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                int r0 = r4.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L1c
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r5)
                goto L37
            L10:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L18:
                k2.c.G(r5)
                goto L2c
            L1c:
                k2.c.G(r5)
                ka.v1 r5 = r4.$oldJob
                if (r5 == 0) goto L2c
                r4.label = r2
                java.lang.Object r5 = r5.join(r4)
                if (r5 != r3) goto L2c
                goto L36
            L2c:
                androidx.compose.foundation.MarqueeModifierNode r5 = r4.this$0
                r4.label = r1
                java.lang.Object r5 = androidx.compose.foundation.MarqueeModifierNode.access$runAnimation(r5, r4)
                if (r5 != r3) goto L37
            L36:
                return r3
            L37:
                x6.t0 r5 = x6.t0.f22605a
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MarqueeModifierNode.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$runAnimation$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.MarqueeModifierNode$runAnimation$2", f = "BasicMarquee.kt", l = {367}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$runAnimation$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass1 extends r implements r7.a<Float> {
            final /* synthetic */ MarqueeModifierNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(MarqueeModifierNode marqueeModifierNode) {
                super(0);
                this.this$0 = marqueeModifierNode;
            }

            @Override // r7.a
            public final Float invoke() {
                if (this.this$0.getContentWidth() <= this.this$0.getContainerWidth()) {
                    return null;
                }
                if (!MarqueeAnimationMode.m257equalsimpl0(this.this$0.m269getAnimationModeZbEOnfQ(), MarqueeAnimationMode.INSTANCE.m264getWhileFocusedZbEOnfQ()) || this.this$0.getHasFocus()) {
                    return Float.valueOf(this.this$0.getContentWidth() + this.this$0.getSpacingPx());
                }
                return null;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$runAnimation$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "contentWithSpacingWidth", "Lx6/t0;", "<anonymous>", "(F)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.MarqueeModifierNode$runAnimation$2$2", f = "BasicMarquee.kt", l = {380, 382, 386, 386}, m = "invokeSuspend")
        public static final class C00122 extends j implements p<Float, d<? super t0>, Object> {
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ MarqueeModifierNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00122(MarqueeModifierNode marqueeModifierNode, d<? super C00122> dVar) {
                super(2, dVar);
                this.this$0 = marqueeModifierNode;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C00122 c00122 = new C00122(this.this$0, dVar);
                c00122.L$0 = obj;
                return c00122;
            }

            @Override // r7.p
            public final Object invoke(Float f10, d<? super t0> dVar) {
                return ((C00122) create(f10, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Removed duplicated region for block: B:30:0x00be A[RETURN] */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r22) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 216
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MarqueeModifierNode.AnonymousClass2.C00122.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return MarqueeModifierNode.this.new AnonymousClass2(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new AnonymousClass1(MarqueeModifierNode.this));
                C00122 c00122 = new C00122(MarqueeModifierNode.this, null);
                this.label = 1;
                Object objG = y0.g(jVarSnapshotFlow, c00122, this);
                e7.a aVar = e7.a.f15033i;
                if (objG == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public /* synthetic */ MarqueeModifierNode(int i10, int i11, int i12, int i13, MarqueeSpacing marqueeSpacing, float f10, h hVar) {
        this(i10, i11, i12, i13, marqueeSpacing, f10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getContainerWidth() {
        return this.containerWidth.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getContentWidth() {
        return this.contentWidth.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getDirection() {
        float fSignum = Math.signum(this.velocity);
        int i10 = WhenMappings.$EnumSwitchMapping$0[DelegatableNodeKt.requireLayoutDirection(this).ordinal()];
        int i11 = 1;
        if (i10 != 1) {
            if (i10 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i11 = -1;
        }
        return fSignum * i11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSpacingPx() {
        return ((Number) this.spacingPx.getValue()).intValue();
    }

    private final void restartAnimation() {
        v1 v1Var = this.animationJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        if (getIsAttached()) {
            this.animationJob = m0.p(getCoroutineScope(), null, 0, new AnonymousClass1(v1Var, this, null), 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object runAnimation(d<? super t0> dVar) {
        Object objY;
        int i10 = this.iterations;
        t0 t0Var = t0.f22605a;
        return (i10 > 0 && (objY = m0.y(FixedMotionDurationScale.INSTANCE, new AnonymousClass2(null), dVar)) == e7.a.f15033i) ? objY : t0Var;
    }

    private final void setContainerWidth(int i10) {
        this.containerWidth.setIntValue(i10);
    }

    private final void setContentWidth(int i10) {
        this.contentWidth.setIntValue(i10);
    }

    private final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        float fFloatValue = this.offset.getValue().floatValue() * getDirection();
        boolean z = getDirection() != 1.0f ? this.offset.getValue().floatValue() < ((float) getContainerWidth()) : this.offset.getValue().floatValue() < ((float) getContentWidth());
        boolean z5 = getDirection() != 1.0f ? this.offset.getValue().floatValue() > ((float) getSpacingPx()) : this.offset.getValue().floatValue() > ((float) ((getContentWidth() + getSpacingPx()) - getContainerWidth()));
        float contentWidth = getDirection() == 1.0f ? getContentWidth() + getSpacingPx() : (-getContentWidth()) - getSpacingPx();
        float fM3302getHeightimpl = Size.m3302getHeightimpl(contentDrawScope.mo3916getSizeNHjbRc());
        int iM3461getIntersectrtfAjoo = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        DrawContext drawContext = contentDrawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3925clipRectN_I0leg(fFloatValue, 0.0f, fFloatValue + getContainerWidth(), fM3302getHeightimpl, iM3461getIntersectrtfAjoo);
        if (z) {
            contentDrawScope.drawContent();
        }
        if (z5) {
            contentDrawScope.getDrawContext().getTransform().translate(contentWidth, 0.0f);
            contentDrawScope.drawContent();
            contentDrawScope.getDrawContext().getTransform().translate(-contentWidth, -0.0f);
        }
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getAnimationMode-ZbEOnfQ, reason: not valid java name */
    public final int m269getAnimationModeZbEOnfQ() {
        return ((MarqueeAnimationMode) this.animationMode.getValue()).getValue();
    }

    public final MarqueeSpacing getSpacing() {
        return (MarqueeSpacing) this.spacing.getValue();
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return intrinsicMeasurable.maxIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return intrinsicMeasurable.maxIntrinsicWidth(i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, Integer.MAX_VALUE, 0, 0, 13, null));
        setContainerWidth(ConstraintsKt.m5660constrainWidthK40F9xA(j10, placeableMo4631measureBRTryo0.getWidth()));
        setContentWidth(placeableMo4631measureBRTryo0.getWidth());
        return MeasureScope.CC.q(measureScope, getContainerWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new MarqueeModifierNode$measure$1(placeableMo4631measureBRTryo0, this), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return intrinsicMeasurable.minIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return 0;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        restartAnimation();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        v1 v1Var = this.animationJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.animationJob = null;
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        setHasFocus(focusState.getHasFocus());
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final /* synthetic */ void onMeasureResultChanged() {
        androidx.compose.ui.node.b.a(this);
    }

    /* JADX INFO: renamed from: setAnimationMode-97h66l8, reason: not valid java name */
    public final void m270setAnimationMode97h66l8(int i10) {
        this.animationMode.setValue(MarqueeAnimationMode.m254boximpl(i10));
    }

    public final void setSpacing(MarqueeSpacing marqueeSpacing) {
        this.spacing.setValue(marqueeSpacing);
    }

    /* JADX INFO: renamed from: update-lWfNwf4, reason: not valid java name */
    public final void m271updatelWfNwf4(int iterations, int animationMode, int delayMillis, int initialDelayMillis, MarqueeSpacing spacing, float velocity) {
        setSpacing(spacing);
        m270setAnimationMode97h66l8(animationMode);
        if (this.iterations == iterations && this.delayMillis == delayMillis && this.initialDelayMillis == initialDelayMillis && Dp.m5683equalsimpl0(this.velocity, velocity)) {
            return;
        }
        this.iterations = iterations;
        this.delayMillis = delayMillis;
        this.initialDelayMillis = initialDelayMillis;
        this.velocity = velocity;
        restartAnimation();
    }

    private MarqueeModifierNode(int i10, int i11, int i12, int i13, MarqueeSpacing marqueeSpacing, float f10) {
        this.iterations = i10;
        this.delayMillis = i12;
        this.initialDelayMillis = i13;
        this.velocity = f10;
        this.contentWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.containerWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.hasFocus = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
        this.spacing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(marqueeSpacing, null, 2, null);
        this.animationMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MarqueeAnimationMode.m254boximpl(i11), null, 2, null);
        this.offset = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
        this.spacingPx = SnapshotStateKt.derivedStateOf(new MarqueeModifierNode$spacingPx$2(marqueeSpacing, this));
    }
}
