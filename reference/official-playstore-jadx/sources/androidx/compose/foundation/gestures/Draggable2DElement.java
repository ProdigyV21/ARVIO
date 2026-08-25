package androidx.compose.foundation.gestures;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Velocity;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BÇ\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u0012<\u0010\u0018\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u0017\u0012<\u0010\u001b\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u0017\u0012\u0006\u0010\u001c\u001a\u00020\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\"\u0010#J\u001a\u0010%\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u0016H\u0096\u0002¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)J\u0013\u0010+\u001a\u00020\u0015*\u00020*H\u0016¢\u0006\u0004\b+\u0010,R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010-R \u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010.R\u0014\u0010\t\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010/R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u00100R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u00101RJ\u0010\u0018\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u00102RJ\u0010\u001b\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u00102R\u0014\u0010\u001c\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010/¨\u00063"}, d2 = {"Landroidx/compose/foundation/gestures/Draggable2DElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/gestures/Draggable2DNode;", "Landroidx/compose/foundation/gestures/Draggable2DState;", "state", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "canDrag", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function0;", "startDragImmediately", "Lkotlin/Function3;", "Lka/k0;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/y;", ContentDisposition.Parameters.Name, "startedPosition", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "onDragStarted", "Landroidx/compose/ui/unit/Velocity;", "velocity", "onDragStopped", "reverseDirection", "<init>", "(Landroidx/compose/foundation/gestures/Draggable2DState;Lr7/l;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/a;Lr7/q;Lr7/q;Z)V", "create", "()Landroidx/compose/foundation/gestures/Draggable2DNode;", "node", "update", "(Landroidx/compose/foundation/gestures/Draggable2DNode;)V", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "Landroidx/compose/foundation/gestures/Draggable2DState;", "Lr7/l;", "Z", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Lr7/a;", "Lr7/q;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Draggable2DElement extends ModifierNodeElement<Draggable2DNode> {
    public static final int $stable = 0;
    private final l<PointerInputChange, Boolean> canDrag;
    private final boolean enabled;
    private final MutableInteractionSource interactionSource;
    private final q<k0, Offset, d7.d<? super t0>, Object> onDragStarted;
    private final q<k0, Velocity, d7.d<? super t0>, Object> onDragStopped;
    private final boolean reverseDirection;
    private final r7.a<Boolean> startDragImmediately;
    private final Draggable2DState state;

    /* JADX WARN: Multi-variable type inference failed */
    public Draggable2DElement(Draggable2DState draggable2DState, l<? super PointerInputChange, Boolean> lVar, boolean z, MutableInteractionSource mutableInteractionSource, r7.a<Boolean> aVar, q<? super k0, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, q<? super k0, ? super Velocity, ? super d7.d<? super t0>, ? extends Object> qVar2, boolean z5) {
        this.state = draggable2DState;
        this.canDrag = lVar;
        this.enabled = z;
        this.interactionSource = mutableInteractionSource;
        this.startDragImmediately = aVar;
        this.onDragStarted = qVar;
        this.onDragStopped = qVar2;
        this.reverseDirection = z5;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || Draggable2DElement.class != other.getClass()) {
            return false;
        }
        Draggable2DElement draggable2DElement = (Draggable2DElement) other;
        return p.a(this.state, draggable2DElement.state) && p.a(this.canDrag, draggable2DElement.canDrag) && this.enabled == draggable2DElement.enabled && p.a(this.interactionSource, draggable2DElement.interactionSource) && p.a(this.startDragImmediately, draggable2DElement.startDragImmediately) && p.a(this.onDragStarted, draggable2DElement.onDragStarted) && p.a(this.onDragStopped, draggable2DElement.onDragStopped) && this.reverseDirection == draggable2DElement.reverseDirection;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int iHashCode = (((this.canDrag.hashCode() + (this.state.hashCode() * 31)) * 31) + (this.enabled ? 1231 : 1237)) * 31;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        return ((this.onDragStopped.hashCode() + ((this.onDragStarted.hashCode() + ((this.startDragImmediately.hashCode() + ((iHashCode + (mutableInteractionSource != null ? mutableInteractionSource.hashCode() : 0)) * 31)) * 31)) * 31)) * 31) + (this.reverseDirection ? 1231 : 1237);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("draggable2D");
        inspectorInfo.getProperties().set("canDrag", this.canDrag);
        a0.c.f(this.enabled, inspectorInfo.getProperties(), "enabled", inspectorInfo).set("interactionSource", this.interactionSource);
        inspectorInfo.getProperties().set("startDragImmediately", this.startDragImmediately);
        inspectorInfo.getProperties().set("onDragStarted", this.onDragStarted);
        inspectorInfo.getProperties().set("onDragStopped", this.onDragStopped);
        a0.c.f(this.reverseDirection, inspectorInfo.getProperties(), "reverseDirection", inspectorInfo).set("state", this.state);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public Draggable2DNode create() {
        return new Draggable2DNode(this.state, this.canDrag, this.enabled, this.interactionSource, this.startDragImmediately, this.onDragStarted, this.onDragStopped, this.reverseDirection);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(Draggable2DNode node) {
        node.update(this.state, this.canDrag, this.enabled, this.interactionSource, this.startDragImmediately, this.onDragStarted, this.onDragStopped, this.reverseDirection);
    }
}
