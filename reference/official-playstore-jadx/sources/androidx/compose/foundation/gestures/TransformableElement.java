package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B3\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001b\u001a\u00020\u0010*\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001dR \u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001eR\u0014\u0010\t\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001fR\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u001f¨\u0006 "}, d2 = {"Landroidx/compose/foundation/gestures/TransformableElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/gestures/TransformableNode;", "Landroidx/compose/foundation/gestures/TransformableState;", "state", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "", "canPan", "lockRotationOnZoomPan", "enabled", "<init>", "(Landroidx/compose/foundation/gestures/TransformableState;Lr7/l;ZZ)V", "create", "()Landroidx/compose/foundation/gestures/TransformableNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/foundation/gestures/TransformableNode;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "Landroidx/compose/foundation/gestures/TransformableState;", "Lr7/l;", "Z", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TransformableElement extends ModifierNodeElement<TransformableNode> {
    private final l<Offset, Boolean> canPan;
    private final boolean enabled;
    private final boolean lockRotationOnZoomPan;
    private final TransformableState state;

    /* JADX WARN: Multi-variable type inference failed */
    public TransformableElement(TransformableState transformableState, l<? super Offset, Boolean> lVar, boolean z, boolean z5) {
        this.state = transformableState;
        this.canPan = lVar;
        this.lockRotationOnZoomPan = z;
        this.enabled = z5;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || TransformableElement.class != other.getClass()) {
            return false;
        }
        TransformableElement transformableElement = (TransformableElement) other;
        return p.a(this.state, transformableElement.state) && p.a(this.canPan, transformableElement.canPan) && this.lockRotationOnZoomPan == transformableElement.lockRotationOnZoomPan && this.enabled == transformableElement.enabled;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return ((((this.canPan.hashCode() + (this.state.hashCode() * 31)) * 31) + (this.lockRotationOnZoomPan ? 1231 : 1237)) * 31) + (this.enabled ? 1231 : 1237);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("transformable");
        inspectorInfo.getProperties().set("state", this.state);
        inspectorInfo.getProperties().set("canPan", this.canPan);
        a0.c.f(this.enabled, inspectorInfo.getProperties(), "enabled", inspectorInfo).set("lockRotationOnZoomPan", Boolean.valueOf(this.lockRotationOnZoomPan));
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public TransformableNode create() {
        return new TransformableNode(this.state, this.canPan, this.lockRotationOnZoomPan, this.enabled);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(TransformableNode node) {
        node.update(this.state, this.canPan, this.lockRotationOnZoomPan, this.enabled);
    }
}
