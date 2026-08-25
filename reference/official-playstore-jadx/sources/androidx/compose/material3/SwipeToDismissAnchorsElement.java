package androidx.compose.material3;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0018\u001a\u00020\r*\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001aR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001bR\u0014\u0010\u0007\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/material3/SwipeToDismissAnchorsElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/material3/SwipeToDismissAnchorsNode;", "Landroidx/compose/material3/SwipeToDismissBoxState;", "state", "", "enableDismissFromStartToEnd", "enableDismissFromEndToStart", "<init>", "(Landroidx/compose/material3/SwipeToDismissBoxState;ZZ)V", "create", "()Landroidx/compose/material3/SwipeToDismissAnchorsNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/material3/SwipeToDismissAnchorsNode;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "Landroidx/compose/material3/SwipeToDismissBoxState;", "Z", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SwipeToDismissAnchorsElement extends ModifierNodeElement<SwipeToDismissAnchorsNode> {
    private final boolean enableDismissFromEndToStart;
    private final boolean enableDismissFromStartToEnd;
    private final SwipeToDismissBoxState state;

    public SwipeToDismissAnchorsElement(SwipeToDismissBoxState swipeToDismissBoxState, boolean z, boolean z5) {
        this.state = swipeToDismissBoxState;
        this.enableDismissFromStartToEnd = z;
        this.enableDismissFromEndToStart = z5;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        SwipeToDismissAnchorsElement swipeToDismissAnchorsElement = (SwipeToDismissAnchorsElement) other;
        return p.a(this.state, swipeToDismissAnchorsElement.state) && this.enableDismissFromStartToEnd == swipeToDismissAnchorsElement.enableDismissFromStartToEnd && this.enableDismissFromEndToStart == swipeToDismissAnchorsElement.enableDismissFromEndToStart;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return (((this.state.hashCode() * 31) + (this.enableDismissFromStartToEnd ? 1231 : 1237)) * 31) + (this.enableDismissFromEndToStart ? 1231 : 1237);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        if (InspectableValueKt.isDebugInspectorInfoEnabled()) {
            new SwipeToDismissAnchorsElement$inspectableProperties$$inlined$debugInspectorInfo$1(this);
        } else {
            InspectableValueKt.getNoInspectorInfo();
        }
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public SwipeToDismissAnchorsNode create() {
        return new SwipeToDismissAnchorsNode(this.state, this.enableDismissFromStartToEnd, this.enableDismissFromEndToStart);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(SwipeToDismissAnchorsNode node) {
        node.setState(this.state);
        node.setEnableDismissFromStartToEnd(this.enableDismissFromStartToEnd);
        node.setEnableDismissFromEndToStart(this.enableDismissFromEndToStart);
    }
}
