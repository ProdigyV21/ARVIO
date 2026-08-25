package androidx.compose.ui.focus;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000f\u001a\u00020\u000b*\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\"\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u001f\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\u0012¨\u0006\""}, d2 = {"Landroidx/compose/ui/focus/FocusRestorerElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/focus/FocusRestorerNode;", "Lkotlin/Function0;", "Landroidx/compose/ui/focus/FocusRequester;", "onRestoreFailed", "<init>", "(Lr7/a;)V", "create", "()Landroidx/compose/ui/focus/FocusRestorerNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/ui/focus/FocusRestorerNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "component1", "()Lr7/a;", "copy", "(Lr7/a;)Landroidx/compose/ui/focus/FocusRestorerElement;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lr7/a;", "getOnRestoreFailed", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FocusRestorerElement extends ModifierNodeElement<FocusRestorerNode> {
    private final r7.a<FocusRequester> onRestoreFailed;

    public FocusRestorerElement(r7.a<FocusRequester> aVar) {
        this.onRestoreFailed = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FocusRestorerElement copy$default(FocusRestorerElement focusRestorerElement, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            aVar = focusRestorerElement.onRestoreFailed;
        }
        return focusRestorerElement.copy(aVar);
    }

    public final r7.a<FocusRequester> component1() {
        return this.onRestoreFailed;
    }

    public final FocusRestorerElement copy(r7.a<FocusRequester> onRestoreFailed) {
        return new FocusRestorerElement(onRestoreFailed);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FocusRestorerElement) && p.a(this.onRestoreFailed, ((FocusRestorerElement) other).onRestoreFailed);
    }

    public final r7.a<FocusRequester> getOnRestoreFailed() {
        return this.onRestoreFailed;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        r7.a<FocusRequester> aVar = this.onRestoreFailed;
        if (aVar == null) {
            return 0;
        }
        return aVar.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("focusRestorer");
        inspectorInfo.getProperties().set("onRestoreFailed", this.onRestoreFailed);
    }

    public String toString() {
        return "FocusRestorerElement(onRestoreFailed=" + this.onRestoreFailed + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public FocusRestorerNode create() {
        return new FocusRestorerNode(this.onRestoreFailed);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(FocusRestorerNode node) {
        node.setOnRestoreFailed(this.onRestoreFailed);
    }
}
