package androidx.compose.ui.focus;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000f\u001a\u00020\u0005*\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001c\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J&\u0010\u0013\u001a\u00020\u00002\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR#\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\u0012¨\u0006\""}, d2 = {"Landroidx/compose/ui/focus/FocusEventElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/focus/FocusEventNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusState;", "Lx6/t0;", "onFocusEvent", "<init>", "(Lr7/l;)V", "create", "()Landroidx/compose/ui/focus/FocusEventNode;", "node", "update", "(Landroidx/compose/ui/focus/FocusEventNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "component1", "()Lr7/l;", "copy", "(Lr7/l;)Landroidx/compose/ui/focus/FocusEventElement;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lr7/l;", "getOnFocusEvent", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FocusEventElement extends ModifierNodeElement<FocusEventNode> {
    private final l<FocusState, t0> onFocusEvent;

    /* JADX WARN: Multi-variable type inference failed */
    public FocusEventElement(l<? super FocusState, t0> lVar) {
        this.onFocusEvent = lVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FocusEventElement copy$default(FocusEventElement focusEventElement, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = focusEventElement.onFocusEvent;
        }
        return focusEventElement.copy(lVar);
    }

    public final l<FocusState, t0> component1() {
        return this.onFocusEvent;
    }

    public final FocusEventElement copy(l<? super FocusState, t0> onFocusEvent) {
        return new FocusEventElement(onFocusEvent);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FocusEventElement) && p.a(this.onFocusEvent, ((FocusEventElement) other).onFocusEvent);
    }

    public final l<FocusState, t0> getOnFocusEvent() {
        return this.onFocusEvent;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.onFocusEvent.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("onFocusEvent");
        inspectorInfo.getProperties().set("onFocusEvent", this.onFocusEvent);
    }

    public String toString() {
        return "FocusEventElement(onFocusEvent=" + this.onFocusEvent + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public FocusEventNode create() {
        return new FocusEventNode(this.onFocusEvent);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(FocusEventNode node) {
        node.setOnFocusEvent(this.onFocusEvent);
    }
}
