package androidx.compose.foundation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0018\u001a\u00020\f*\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R%\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/ExcludeFromSystemGestureElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/ExcludeFromSystemGestureNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Rect;", "exclusion", "<init>", "(Lr7/l;)V", "create", "()Landroidx/compose/foundation/ExcludeFromSystemGestureNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/foundation/ExcludeFromSystemGestureNode;)V", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "Lr7/l;", "getExclusion", "()Lr7/l;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ExcludeFromSystemGestureElement extends ModifierNodeElement<ExcludeFromSystemGestureNode> {
    private final l<LayoutCoordinates, Rect> exclusion;

    /* JADX WARN: Multi-variable type inference failed */
    public ExcludeFromSystemGestureElement(l<? super LayoutCoordinates, Rect> lVar) {
        this.exclusion = lVar;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (other instanceof ExcludeFromSystemGestureElement) {
            return p.a(this.exclusion, ((ExcludeFromSystemGestureElement) other).exclusion);
        }
        return false;
    }

    public final l<LayoutCoordinates, Rect> getExclusion() {
        return this.exclusion;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        l<LayoutCoordinates, Rect> lVar = this.exclusion;
        if (lVar != null) {
            return lVar.hashCode();
        }
        return 0;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("systemGestureExclusion");
        if (this.exclusion != null) {
            inspectorInfo.getProperties().set("exclusion", this.exclusion);
        }
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public ExcludeFromSystemGestureNode create() {
        return new ExcludeFromSystemGestureNode(this.exclusion);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(ExcludeFromSystemGestureNode node) {
        node.setRect(this.exclusion);
    }
}
