package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Velocity;
import com.google.android.gms.cast.MediaError;
import f7.j;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0089\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005*\u00013\b\u0000\u0018\u00002\u00020\u0001BÏ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\r\u0012<\u0010\u0019\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u000f¢\u0006\u0002\b\u0018\u0012<\u0010\u001c\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u000f¢\u0006\u0002\b\u0018\u0012\u0006\u0010\u001d\u001a\u00020\u0006¢\u0006\u0004\b\u001e\u0010\u001fJ9\u0010#\u001a\u00020\u00162'\u0010\"\u001a#\b\u0001\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170 ¢\u0006\u0002\b\u0018H\u0096@¢\u0006\u0004\b#\u0010$J\u001c\u0010'\u001a\u00020\u0016*\u00020!2\u0006\u0010&\u001a\u00020%H\u0096@¢\u0006\u0004\b'\u0010(JÕ\u0001\u0010)\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\r2<\u0010\u0019\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u000f¢\u0006\u0002\b\u00182<\u0010\u001c\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u000f¢\u0006\u0002\b\u00182\u0006\u0010\u001d\u001a\u00020\u0006¢\u0006\u0004\b)\u0010\u001fR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010*R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010+R\"\u0010-\u001a\u00020,8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u001a\u00107\u001a\u0002068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:¨\u0006;"}, d2 = {"Landroidx/compose/foundation/gestures/DraggableNode;", "Landroidx/compose/foundation/gestures/AbstractDraggableNode;", "Landroidx/compose/foundation/gestures/DraggableState;", "state", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "canDrag", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function0;", "startDragImmediately", "Lkotlin/Function3;", "Lka/k0;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/y;", ContentDisposition.Parameters.Name, "startedPosition", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "onDragStarted", "Landroidx/compose/ui/unit/Velocity;", "velocity", "onDragStopped", "reverseDirection", "<init>", "(Landroidx/compose/foundation/gestures/DraggableState;Lr7/l;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/a;Lr7/q;Lr7/q;Z)V", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/AbstractDragScope;", "block", "drag", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "dragDelta", "draggingBy", "(Landroidx/compose/foundation/gestures/AbstractDragScope;Landroidx/compose/foundation/gestures/DragEvent$DragDelta;Ld7/d;)Ljava/lang/Object;", "update", "Landroidx/compose/foundation/gestures/DraggableState;", "Landroidx/compose/foundation/gestures/Orientation;", "Landroidx/compose/foundation/gestures/DragScope;", "dragScope", "Landroidx/compose/foundation/gestures/DragScope;", "getDragScope", "()Landroidx/compose/foundation/gestures/DragScope;", "setDragScope", "(Landroidx/compose/foundation/gestures/DragScope;)V", "androidx/compose/foundation/gestures/DraggableNode$abstractDragScope$1", "abstractDragScope", "Landroidx/compose/foundation/gestures/DraggableNode$abstractDragScope$1;", "Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "pointerDirectionConfig", "Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "getPointerDirectionConfig", "()Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DraggableNode extends AbstractDraggableNode {
    public static final int $stable = 8;
    private final DraggableNode$abstractDragScope$1 abstractDragScope;
    private DragScope dragScope;
    private Orientation orientation;
    private final PointerDirectionConfig pointerDirectionConfig;
    private DraggableState state;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableNode$drag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/DragScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/DragScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DraggableNode$drag$2", f = "Draggable.kt", l = {MediaError.DetailedErrorCode.DASH_NO_INIT}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<DragScope, d7.d<? super t0>, Object> {
        final /* synthetic */ p<AbstractDragScope, d7.d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(p<? super AbstractDragScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$block = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = DraggableNode.this.new AnonymousClass2(this.$block, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(DragScope dragScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(dragScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                DraggableNode.this.setDragScope((DragScope) this.L$0);
                p<AbstractDragScope, d7.d<? super t0>, Object> pVar = this.$block;
                DraggableNode$abstractDragScope$1 draggableNode$abstractDragScope$1 = DraggableNode.this.abstractDragScope;
                this.label = 1;
                Object objInvoke = pVar.invoke(draggableNode$abstractDragScope$1, this);
                e7.a aVar = e7.a.f15033i;
                if (objInvoke == aVar) {
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
    }

    /* JADX WARN: Type inference failed for: r9v2, types: [androidx.compose.foundation.gestures.DraggableNode$abstractDragScope$1] */
    public DraggableNode(DraggableState draggableState, l<? super PointerInputChange, Boolean> lVar, Orientation orientation, boolean z, MutableInteractionSource mutableInteractionSource, r7.a<Boolean> aVar, q<? super k0, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, q<? super k0, ? super Velocity, ? super d7.d<? super t0>, ? extends Object> qVar2, boolean z5) {
        super(lVar, z, mutableInteractionSource, aVar, qVar, qVar2, z5);
        this.state = draggableState;
        this.orientation = orientation;
        this.dragScope = DraggableKt.NoOpDragScope;
        this.abstractDragScope = new AbstractDragScope() { // from class: androidx.compose.foundation.gestures.DraggableNode$abstractDragScope$1
            @Override // androidx.compose.foundation.gestures.AbstractDragScope
            /* JADX INFO: renamed from: dragBy-k-4lQ0M */
            public void mo290dragByk4lQ0M(long pixels) {
                this.this$0.getDragScope().dragBy(DraggableKt.m349toFloat3MmeM6k(pixels, this.this$0.orientation));
            }
        };
        this.pointerDirectionConfig = DragGestureDetectorKt.toPointerDirectionConfig(this.orientation);
    }

    @Override // androidx.compose.foundation.gestures.AbstractDraggableNode
    public Object drag(p<? super AbstractDragScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super t0> dVar) {
        Object objDrag = this.state.drag(MutatePriority.UserInput, new AnonymousClass2(pVar, null), dVar);
        return objDrag == e7.a.f15033i ? objDrag : t0.f22605a;
    }

    @Override // androidx.compose.foundation.gestures.AbstractDraggableNode
    public Object draggingBy(AbstractDragScope abstractDragScope, DragEvent.DragDelta dragDelta, d7.d<? super t0> dVar) {
        abstractDragScope.mo290dragByk4lQ0M(dragDelta.getDelta());
        return t0.f22605a;
    }

    public final DragScope getDragScope() {
        return this.dragScope;
    }

    @Override // androidx.compose.foundation.gestures.AbstractDraggableNode
    public PointerDirectionConfig getPointerDirectionConfig() {
        return this.pointerDirectionConfig;
    }

    public final void setDragScope(DragScope dragScope) {
        this.dragScope = dragScope;
    }

    public final void update(DraggableState state, l<? super PointerInputChange, Boolean> canDrag, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, r7.a<Boolean> startDragImmediately, q<? super k0, ? super Offset, ? super d7.d<? super t0>, ? extends Object> onDragStarted, q<? super k0, ? super Velocity, ? super d7.d<? super t0>, ? extends Object> onDragStopped, boolean reverseDirection) {
        boolean z;
        boolean z5 = true;
        if (kotlin.jvm.internal.p.a(this.state, state)) {
            z = false;
        } else {
            this.state = state;
            z = true;
        }
        setCanDrag(canDrag);
        if (this.orientation != orientation) {
            this.orientation = orientation;
            z = true;
        }
        if (getEnabled() != enabled) {
            setEnabled(enabled);
            if (!enabled) {
                disposeInteractionSource();
            }
            z = true;
        }
        if (!kotlin.jvm.internal.p.a(getInteractionSource(), interactionSource)) {
            disposeInteractionSource();
            setInteractionSource(interactionSource);
        }
        setStartDragImmediately(startDragImmediately);
        setOnDragStarted(onDragStarted);
        setOnDragStopped(onDragStopped);
        if (getReverseDirection() != reverseDirection) {
            setReverseDirection(reverseDirection);
        } else {
            z5 = z;
        }
        if (z5) {
            getPointerInputNode().resetPointerInputHandler();
        }
    }
}
