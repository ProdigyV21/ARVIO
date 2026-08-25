package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.unit.Velocity;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentDisposition;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import m2.f0;
import ma.j;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B¿\u0001\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b\u0012<\u0010\u0017\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\u0002\b\u0016\u0012<\u0010\u001a\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\u0002\b\u0016\u0012\u0006\u0010\u001b\u001a\u00020\u0006¢\u0006\u0004\b\u001c\u0010\u001dJ9\u0010!\u001a\u00020\u00142'\u0010 \u001a#\b\u0001\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001e¢\u0006\u0002\b\u0016H¦@¢\u0006\u0004\b!\u0010\"J\u001c\u0010%\u001a\u00020\u0014*\u00020\u001f2\u0006\u0010$\u001a\u00020#H¦@¢\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u0014H\u0016¢\u0006\u0004\b'\u0010(J*\u00101\u001a\u00020\u00142\u0006\u0010*\u001a\u00020)2\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-H\u0016ø\u0001\u0000¢\u0006\u0004\b/\u00100J\u000f\u00102\u001a\u00020\u0014H\u0016¢\u0006\u0004\b2\u0010(J\r\u00103\u001a\u00020\u0014¢\u0006\u0004\b3\u0010(J\u000f\u00104\u001a\u00020\u0014H\u0002¢\u0006\u0004\b4\u0010(J\u001c\u00107\u001a\u00020\u0014*\u00020\u000e2\u0006\u00106\u001a\u000205H\u0082@¢\u0006\u0004\b7\u00108J\u001c\u0010:\u001a\u00020\u0014*\u00020\u000e2\u0006\u00106\u001a\u000209H\u0082@¢\u0006\u0004\b:\u0010;J\u0014\u0010<\u001a\u00020\u0014*\u00020\u000eH\u0082@¢\u0006\u0004\b<\u0010=R.\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010>\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\"\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010H\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR(\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QRX\u0010\u0017\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\u0002\b\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VRX\u0010\u001a\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\u0002\b\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010R\u001a\u0004\bW\u0010T\"\u0004\bX\u0010VR\"\u0010\u001b\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010C\u001a\u0004\bY\u0010E\"\u0004\bZ\u0010GR \u0010[\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010>R\u001a\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010MR\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010CR\u0017\u0010b\u001a\u00020a8\u0006¢\u0006\f\n\u0004\bb\u0010c\u001a\u0004\bd\u0010eR\u001a\u0010h\u001a\b\u0012\u0004\u0012\u00020g0f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010iR\u0018\u0010k\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010p\u001a\u00020m8&X¦\u0004¢\u0006\u0006\u001a\u0004\bn\u0010o\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006q"}, d2 = {"Landroidx/compose/foundation/gestures/AbstractDraggableNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "canDrag", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function0;", "startDragImmediately", "Lkotlin/Function3;", "Lka/k0;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/y;", ContentDisposition.Parameters.Name, "startedPosition", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "onDragStarted", "Landroidx/compose/ui/unit/Velocity;", "velocity", "onDragStopped", "reverseDirection", "<init>", "(Lr7/l;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/a;Lr7/q;Lr7/q;Z)V", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/AbstractDragScope;", "block", "drag", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "dragDelta", "draggingBy", "(Landroidx/compose/foundation/gestures/AbstractDragScope;Landroidx/compose/foundation/gestures/DragEvent$DragDelta;Ld7/d;)Ljava/lang/Object;", "onDetach", "()V", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Landroidx/compose/ui/unit/IntSize;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPointerEvent", "onCancelPointerInput", "disposeInteractionSource", "startListeningForEvents", "Landroidx/compose/foundation/gestures/DragEvent$DragStarted;", "event", "processDragStart", "(Lka/k0;Landroidx/compose/foundation/gestures/DragEvent$DragStarted;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/gestures/DragEvent$DragStopped;", "processDragStop", "(Lka/k0;Landroidx/compose/foundation/gestures/DragEvent$DragStopped;Ld7/d;)Ljava/lang/Object;", "processDragCancel", "(Lka/k0;Ld7/d;)Ljava/lang/Object;", "Lr7/l;", "getCanDrag", "()Lr7/l;", "setCanDrag", "(Lr7/l;)V", "Z", "getEnabled", "()Z", "setEnabled", "(Z)V", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "setInteractionSource", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "Lr7/a;", "getStartDragImmediately", "()Lr7/a;", "setStartDragImmediately", "(Lr7/a;)V", "Lr7/q;", "getOnDragStarted", "()Lr7/q;", "setOnDragStarted", "(Lr7/q;)V", "getOnDragStopped", "setOnDragStopped", "getReverseDirection", "setReverseDirection", "_canDrag", "_startDragImmediately", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "velocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "isListeningForEvents", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "getPointerInputNode", "()Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "Lma/j;", "Landroidx/compose/foundation/gestures/DragEvent;", "channel", "Lma/j;", "Landroidx/compose/foundation/interaction/DragInteraction$Start;", "dragInteraction", "Landroidx/compose/foundation/interaction/DragInteraction$Start;", "Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "getPointerDirectionConfig", "()Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "pointerDirectionConfig", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class AbstractDraggableNode extends DelegatingNode implements PointerInputModifierNode, CompositionLocalConsumerModifierNode {
    public static final int $stable = 8;
    private l<? super PointerInputChange, Boolean> canDrag;
    private DragInteraction.Start dragInteraction;
    private boolean enabled;
    private MutableInteractionSource interactionSource;
    private boolean isListeningForEvents;
    private q<? super k0, ? super Offset, ? super d7.d<? super t0>, ? extends Object> onDragStarted;
    private q<? super k0, ? super Velocity, ? super d7.d<? super t0>, ? extends Object> onDragStopped;
    private boolean reverseDirection;
    private r7.a<Boolean> startDragImmediately;
    private final l<PointerInputChange, Boolean> _canDrag = new AbstractDraggableNode$_canDrag$1(this);
    private final r7.a<Boolean> _startDragImmediately = new AbstractDraggableNode$_startDragImmediately$1(this);
    private final VelocityTracker velocityTracker = new VelocityTracker();
    private final SuspendingPointerInputModifierNode pointerInputNode = (SuspendingPointerInputModifierNode) delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AbstractDraggableNode$pointerInputNode$1(this, null)));
    private final j<DragEvent> channel = f0.a(Integer.MAX_VALUE, 0, 6);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$processDragCancel$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.AbstractDraggableNode", f = "Draggable.kt", l = {554, 557}, m = "processDragCancel")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AbstractDraggableNode.this.processDragCancel(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$processDragStart$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.AbstractDraggableNode", f = "Draggable.kt", l = {536, 539, 541}, m = "processDragStart")
    public static final class C02951 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C02951(d7.d<? super C02951> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AbstractDraggableNode.this.processDragStart(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$processDragStop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.AbstractDraggableNode", f = "Draggable.kt", l = {546, 549}, m = "processDragStop")
    public static final class C02961 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C02961(d7.d<? super C02961> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AbstractDraggableNode.this.processDragStop(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$startListeningForEvents$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.AbstractDraggableNode$startListeningForEvents$1", f = "Draggable.kt", l = {MediaError.DetailedErrorCode.SMOOTH_MANIFEST, 433, 435, 442, 444, 447}, m = "invokeSuspend")
    public static final class C02971 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$startListeningForEvents$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/AbstractDragScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/AbstractDragScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.AbstractDraggableNode$startListeningForEvents$1$1", f = "Draggable.kt", l = {437, 438}, m = "invokeSuspend")
        public static final class C00161 extends f7.j implements p<AbstractDragScope, d7.d<? super t0>, Object> {
            final /* synthetic */ kotlin.jvm.internal.k0 $event;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ AbstractDraggableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00161(kotlin.jvm.internal.k0 k0Var, AbstractDraggableNode abstractDraggableNode, d7.d<? super C00161> dVar) {
                super(2, dVar);
                this.$event = k0Var;
                this.this$0 = abstractDraggableNode;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C00161 c00161 = new C00161(this.$event, this.this$0, dVar);
                c00161.L$0 = obj;
                return c00161;
            }

            @Override // r7.p
            public final Object invoke(AbstractDragScope abstractDragScope, d7.d<? super t0> dVar) {
                return ((C00161) create(abstractDragScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Code restructure failed: missing block: B:24:0x006c, code lost:
            
                if (r7 != r3) goto L26;
             */
            /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
            /* JADX WARN: Removed duplicated region for block: B:23:0x0059 A[PHI: r0
              0x0059: PHI (r0v2 androidx.compose.foundation.gestures.AbstractDragScope) = 
              (r0v4 androidx.compose.foundation.gestures.AbstractDragScope)
              (r0v4 androidx.compose.foundation.gestures.AbstractDragScope)
              (r0v8 androidx.compose.foundation.gestures.AbstractDragScope)
             binds: [B:19:0x0046, B:21:0x0056, B:9:0x0020] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x006c -> B:26:0x006f). Please report as a decompilation issue!!! */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                /*
                    r6 = this;
                    int r0 = r6.label
                    r1 = 2
                    r2 = 1
                    e7.a r3 = e7.a.f15033i
                    if (r0 == 0) goto L28
                    if (r0 == r2) goto L20
                    if (r0 != r1) goto L18
                    java.lang.Object r0 = r6.L$1
                    kotlin.jvm.internal.k0 r0 = (kotlin.jvm.internal.k0) r0
                    java.lang.Object r4 = r6.L$0
                    androidx.compose.foundation.gestures.AbstractDragScope r4 = (androidx.compose.foundation.gestures.AbstractDragScope) r4
                    k2.c.G(r7)
                    goto L6f
                L18:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r0)
                    throw r7
                L20:
                    java.lang.Object r0 = r6.L$0
                    androidx.compose.foundation.gestures.AbstractDragScope r0 = (androidx.compose.foundation.gestures.AbstractDragScope) r0
                    k2.c.G(r7)
                    goto L59
                L28:
                    k2.c.G(r7)
                    java.lang.Object r7 = r6.L$0
                    androidx.compose.foundation.gestures.AbstractDragScope r7 = (androidx.compose.foundation.gestures.AbstractDragScope) r7
                    r0 = r7
                L30:
                    kotlin.jvm.internal.k0 r7 = r6.$event
                    java.lang.Object r7 = r7.f19746i
                    boolean r4 = r7 instanceof androidx.compose.foundation.gestures.DragEvent.DragStopped
                    if (r4 != 0) goto L73
                    boolean r4 = r7 instanceof androidx.compose.foundation.gestures.DragEvent.DragCancelled
                    if (r4 != 0) goto L73
                    boolean r4 = r7 instanceof androidx.compose.foundation.gestures.DragEvent.DragDelta
                    r5 = 0
                    if (r4 == 0) goto L45
                    r4 = r7
                    androidx.compose.foundation.gestures.DragEvent$DragDelta r4 = (androidx.compose.foundation.gestures.DragEvent.DragDelta) r4
                    goto L46
                L45:
                    r4 = r5
                L46:
                    if (r4 == 0) goto L59
                    androidx.compose.foundation.gestures.AbstractDraggableNode r4 = r6.this$0
                    androidx.compose.foundation.gestures.DragEvent$DragDelta r7 = (androidx.compose.foundation.gestures.DragEvent.DragDelta) r7
                    r6.L$0 = r0
                    r6.L$1 = r5
                    r6.label = r2
                    java.lang.Object r7 = r4.draggingBy(r0, r7, r6)
                    if (r7 != r3) goto L59
                    goto L6e
                L59:
                    r4 = r0
                    kotlin.jvm.internal.k0 r0 = r6.$event
                    androidx.compose.foundation.gestures.AbstractDraggableNode r7 = r6.this$0
                    ma.j r7 = androidx.compose.foundation.gestures.AbstractDraggableNode.access$getChannel$p(r7)
                    r6.L$0 = r4
                    r6.L$1 = r0
                    r6.label = r1
                    java.lang.Object r7 = r7.receive(r6)
                    if (r7 != r3) goto L6f
                L6e:
                    return r3
                L6f:
                    r0.f19746i = r7
                    r0 = r4
                    goto L30
                L73:
                    x6.t0 r7 = x6.t0.f22605a
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AbstractDraggableNode.C02971.C00161.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public C02971(d7.d<? super C02971> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C02971 c02971 = AbstractDraggableNode.this.new C02971(dVar);
            c02971.L$0 = obj;
            return c02971;
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x00ee, code lost:
        
            if (r7.processDragCancel(r0, r6) != r2) goto L11;
         */
        /* JADX WARN: Path cross not found for [B:40:0x00cd, B:36:0x00b9], limit reached: 53 */
        /* JADX WARN: Path cross not found for [B:42:0x00d1, B:19:0x005b], limit reached: 53 */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0032 A[PHI: r0 r3
          0x0032: PHI (r0v14 kotlin.jvm.internal.k0) = (r0v6 kotlin.jvm.internal.k0), (r0v22 kotlin.jvm.internal.k0) binds: [B:13:0x002f, B:32:0x00b0] A[DONT_GENERATE, DONT_INLINE]
          0x0032: PHI (r3v8 ka.k0) = (r3v5 ka.k0), (r3v12 ka.k0) binds: [B:13:0x002f, B:32:0x00b0] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0088  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00b9 A[Catch: CancellationException -> 0x00cb, TryCatch #1 {CancellationException -> 0x00cb, blocks: (B:34:0x00b3, B:36:0x00b9, B:40:0x00cd, B:42:0x00d1), top: B:54:0x00b3 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00cd A[Catch: CancellationException -> 0x00cb, TryCatch #1 {CancellationException -> 0x00cb, blocks: (B:34:0x00b3, B:36:0x00b9, B:40:0x00cd, B:42:0x00d1), top: B:54:0x00b3 }] */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00f1  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00f4  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00c8 -> B:19:0x005b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00cf -> B:19:0x005b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00de -> B:19:0x005b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00ee -> B:11:0x0025). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x00f1 -> B:19:0x005b). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                Method dump skipped, instruction units count: 266
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AbstractDraggableNode.C02971.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C02971) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public AbstractDraggableNode(l<? super PointerInputChange, Boolean> lVar, boolean z, MutableInteractionSource mutableInteractionSource, r7.a<Boolean> aVar, q<? super k0, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, q<? super k0, ? super Velocity, ? super d7.d<? super t0>, ? extends Object> qVar2, boolean z5) {
        this.canDrag = lVar;
        this.enabled = z;
        this.interactionSource = mutableInteractionSource;
        this.startDragImmediately = aVar;
        this.onDragStarted = qVar;
        this.onDragStopped = qVar2;
        this.reverseDirection = z5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        if (r1.emit(r6, r0) == r5) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0077, code lost:
    
        if (r10.invoke(r9, r1, r0) != r5) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0079, code lost:
    
        return r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object processDragCancel(ka.k0 r9, d7.d<? super x6.t0> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.AbstractDraggableNode.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.foundation.gestures.AbstractDraggableNode$processDragCancel$1 r0 = (androidx.compose.foundation.gestures.AbstractDraggableNode.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.AbstractDraggableNode$processDragCancel$1 r0 = new androidx.compose.foundation.gestures.AbstractDraggableNode$processDragCancel$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L3f
            if (r1 == r3) goto L33
            if (r1 != r2) goto L2b
            k2.c.G(r10)
            goto L7a
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            java.lang.Object r9 = r0.L$1
            ka.k0 r9 = (ka.k0) r9
            java.lang.Object r1 = r0.L$0
            androidx.compose.foundation.gestures.AbstractDraggableNode r1 = (androidx.compose.foundation.gestures.AbstractDraggableNode) r1
            k2.c.G(r10)
            goto L5d
        L3f:
            k2.c.G(r10)
            androidx.compose.foundation.interaction.DragInteraction$Start r10 = r8.dragInteraction
            if (r10 == 0) goto L60
            androidx.compose.foundation.interaction.MutableInteractionSource r1 = r8.interactionSource
            if (r1 == 0) goto L5c
            androidx.compose.foundation.interaction.DragInteraction$Cancel r6 = new androidx.compose.foundation.interaction.DragInteraction$Cancel
            r6.<init>(r10)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r3
            java.lang.Object r10 = r1.emit(r6, r0)
            if (r10 != r5) goto L5c
            goto L79
        L5c:
            r1 = r8
        L5d:
            r1.dragInteraction = r4
            goto L61
        L60:
            r1 = r8
        L61:
            r7.q<? super ka.k0, ? super androidx.compose.ui.unit.Velocity, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r10 = r1.onDragStopped
            androidx.compose.ui.unit.Velocity$Companion r1 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r6 = r1.m5920getZero9UxMQ8M()
            androidx.compose.ui.unit.Velocity r1 = androidx.compose.ui.unit.Velocity.m5900boximpl(r6)
            r0.L$0 = r4
            r0.L$1 = r4
            r0.label = r2
            java.lang.Object r9 = r10.invoke(r9, r1, r0)
            if (r9 != r5) goto L7a
        L79:
            return r5
        L7a:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AbstractDraggableNode.processDragCancel(ka.k0, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
    
        if (r1.emit(r6, r0) == r5) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b6, code lost:
    
        if (r10.invoke(r8, r9, r0) == r5) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object processDragStart(ka.k0 r8, androidx.compose.foundation.gestures.DragEvent.DragStarted r9, d7.d<? super x6.t0> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.AbstractDraggableNode.C02951
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.foundation.gestures.AbstractDraggableNode$processDragStart$1 r0 = (androidx.compose.foundation.gestures.AbstractDraggableNode.C02951) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.AbstractDraggableNode$processDragStart$1 r0 = new androidx.compose.foundation.gestures.AbstractDraggableNode$processDragStart$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L5b
            if (r1 == r4) goto L4a
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            k2.c.G(r10)
            goto Lb9
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L36:
            java.lang.Object r8 = r0.L$3
            androidx.compose.foundation.interaction.DragInteraction$Start r8 = (androidx.compose.foundation.interaction.DragInteraction.Start) r8
            java.lang.Object r9 = r0.L$2
            androidx.compose.foundation.gestures.DragEvent$DragStarted r9 = (androidx.compose.foundation.gestures.DragEvent.DragStarted) r9
            java.lang.Object r1 = r0.L$1
            ka.k0 r1 = (ka.k0) r1
            java.lang.Object r3 = r0.L$0
            androidx.compose.foundation.gestures.AbstractDraggableNode r3 = (androidx.compose.foundation.gestures.AbstractDraggableNode) r3
            k2.c.G(r10)
            goto L98
        L4a:
            java.lang.Object r8 = r0.L$2
            r9 = r8
            androidx.compose.foundation.gestures.DragEvent$DragStarted r9 = (androidx.compose.foundation.gestures.DragEvent.DragStarted) r9
            java.lang.Object r8 = r0.L$1
            ka.k0 r8 = (ka.k0) r8
            java.lang.Object r1 = r0.L$0
            androidx.compose.foundation.gestures.AbstractDraggableNode r1 = (androidx.compose.foundation.gestures.AbstractDraggableNode) r1
            k2.c.G(r10)
            goto L7b
        L5b:
            k2.c.G(r10)
            androidx.compose.foundation.interaction.DragInteraction$Start r10 = r7.dragInteraction
            if (r10 == 0) goto L7a
            androidx.compose.foundation.interaction.MutableInteractionSource r1 = r7.interactionSource
            if (r1 == 0) goto L7a
            androidx.compose.foundation.interaction.DragInteraction$Cancel r6 = new androidx.compose.foundation.interaction.DragInteraction$Cancel
            r6.<init>(r10)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r4
            java.lang.Object r10 = r1.emit(r6, r0)
            if (r10 != r5) goto L7a
            goto Lb8
        L7a:
            r1 = r7
        L7b:
            androidx.compose.foundation.interaction.DragInteraction$Start r10 = new androidx.compose.foundation.interaction.DragInteraction$Start
            r10.<init>()
            androidx.compose.foundation.interaction.MutableInteractionSource r4 = r1.interactionSource
            if (r4 == 0) goto L9b
            r0.L$0 = r1
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.label = r3
            java.lang.Object r3 = r4.emit(r10, r0)
            if (r3 != r5) goto L95
            goto Lb8
        L95:
            r3 = r1
            r1 = r8
            r8 = r10
        L98:
            r10 = r8
            r8 = r1
            r1 = r3
        L9b:
            r1.dragInteraction = r10
            r7.q<? super ka.k0, ? super androidx.compose.ui.geometry.Offset, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r10 = r1.onDragStarted
            long r3 = r9.getStartPoint()
            androidx.compose.ui.geometry.Offset r9 = androidx.compose.ui.geometry.Offset.m3225boximpl(r3)
            r1 = 0
            r0.L$0 = r1
            r0.L$1 = r1
            r0.L$2 = r1
            r0.L$3 = r1
            r0.label = r2
            java.lang.Object r8 = r10.invoke(r8, r9, r0)
            if (r8 != r5) goto Lb9
        Lb8:
            return r5
        Lb9:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AbstractDraggableNode.processDragStart(ka.k0, androidx.compose.foundation.gestures.DragEvent$DragStarted, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0060, code lost:
    
        if (r1.emit(r6, r0) == r5) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007e, code lost:
    
        if (r11.invoke(r9, r10, r0) != r5) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0080, code lost:
    
        return r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object processDragStop(ka.k0 r9, androidx.compose.foundation.gestures.DragEvent.DragStopped r10, d7.d<? super x6.t0> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.AbstractDraggableNode.C02961
            if (r0 == 0) goto L13
            r0 = r11
            androidx.compose.foundation.gestures.AbstractDraggableNode$processDragStop$1 r0 = (androidx.compose.foundation.gestures.AbstractDraggableNode.C02961) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.AbstractDraggableNode$processDragStop$1 r0 = new androidx.compose.foundation.gestures.AbstractDraggableNode$processDragStop$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L44
            if (r1 == r3) goto L33
            if (r1 != r2) goto L2b
            k2.c.G(r11)
            goto L81
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            java.lang.Object r9 = r0.L$2
            r10 = r9
            androidx.compose.foundation.gestures.DragEvent$DragStopped r10 = (androidx.compose.foundation.gestures.DragEvent.DragStopped) r10
            java.lang.Object r9 = r0.L$1
            ka.k0 r9 = (ka.k0) r9
            java.lang.Object r1 = r0.L$0
            androidx.compose.foundation.gestures.AbstractDraggableNode r1 = (androidx.compose.foundation.gestures.AbstractDraggableNode) r1
            k2.c.G(r11)
            goto L64
        L44:
            k2.c.G(r11)
            androidx.compose.foundation.interaction.DragInteraction$Start r11 = r8.dragInteraction
            if (r11 == 0) goto L67
            androidx.compose.foundation.interaction.MutableInteractionSource r1 = r8.interactionSource
            if (r1 == 0) goto L63
            androidx.compose.foundation.interaction.DragInteraction$Stop r6 = new androidx.compose.foundation.interaction.DragInteraction$Stop
            r6.<init>(r11)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r3
            java.lang.Object r11 = r1.emit(r6, r0)
            if (r11 != r5) goto L63
            goto L80
        L63:
            r1 = r8
        L64:
            r1.dragInteraction = r4
            goto L68
        L67:
            r1 = r8
        L68:
            r7.q<? super ka.k0, ? super androidx.compose.ui.unit.Velocity, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r11 = r1.onDragStopped
            long r6 = r10.getVelocity()
            androidx.compose.ui.unit.Velocity r10 = androidx.compose.ui.unit.Velocity.m5900boximpl(r6)
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r0.label = r2
            java.lang.Object r9 = r11.invoke(r9, r10, r0)
            if (r9 != r5) goto L81
        L80:
            return r5
        L81:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AbstractDraggableNode.processDragStop(ka.k0, androidx.compose.foundation.gestures.DragEvent$DragStopped, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startListeningForEvents() {
        this.isListeningForEvents = true;
        m0.p(getCoroutineScope(), null, 0, new C02971(null), 3);
    }

    public final void disposeInteractionSource() {
        DragInteraction.Start start = this.dragInteraction;
        if (start != null) {
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                mutableInteractionSource.tryEmit(new DragInteraction.Cancel(start));
            }
            this.dragInteraction = null;
        }
    }

    public abstract Object drag(p<? super AbstractDragScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super t0> dVar);

    public abstract Object draggingBy(AbstractDragScope abstractDragScope, DragEvent.DragDelta dragDelta, d7.d<? super t0> dVar);

    public final l<PointerInputChange, Boolean> getCanDrag() {
        return this.canDrag;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    public final q<k0, Offset, d7.d<? super t0>, Object> getOnDragStarted() {
        return this.onDragStarted;
    }

    public final q<k0, Velocity, d7.d<? super t0>, Object> getOnDragStopped() {
        return this.onDragStopped;
    }

    public abstract PointerDirectionConfig getPointerDirectionConfig();

    public final SuspendingPointerInputModifierNode getPointerInputNode() {
        return this.pointerInputNode;
    }

    public final boolean getReverseDirection() {
        return this.reverseDirection;
    }

    public final r7.a<Boolean> getStartDragImmediately() {
        return this.startDragImmediately;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return androidx.compose.ui.node.f.a(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        this.pointerInputNode.onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onDensityChange() {
        androidx.compose.ui.node.f.b(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.isListeningForEvents = false;
        disposeInteractionSource();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo148onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.pointerInputNode.mo148onPointerEventH0pRuoY(pointerEvent, pass, bounds);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onViewConfigurationChange() {
        androidx.compose.ui.node.f.c(this);
    }

    public final void setCanDrag(l<? super PointerInputChange, Boolean> lVar) {
        this.canDrag = lVar;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final void setInteractionSource(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    public final void setOnDragStarted(q<? super k0, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar) {
        this.onDragStarted = qVar;
    }

    public final void setOnDragStopped(q<? super k0, ? super Velocity, ? super d7.d<? super t0>, ? extends Object> qVar) {
        this.onDragStopped = qVar;
    }

    public final void setReverseDirection(boolean z) {
        this.reverseDirection = z;
    }

    public final void setStartDragImmediately(r7.a<Boolean> aVar) {
        this.startDragImmediately = aVar;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean sharePointerInputWithSiblings() {
        return androidx.compose.ui.node.f.d(this);
    }
}
