package androidx.compose.ui.focus;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000e\u001a\u00020\n*\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aHÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001f\u001a\u0004\b \u0010\u0011¨\u0006!"}, d2 = {"Landroidx/compose/ui/focus/FocusRequesterElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/focus/FocusRequesterNode;", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "<init>", "(Landroidx/compose/ui/focus/FocusRequester;)V", "create", "()Landroidx/compose/ui/focus/FocusRequesterNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/ui/focus/FocusRequesterNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "component1", "()Landroidx/compose/ui/focus/FocusRequester;", "copy", "(Landroidx/compose/ui/focus/FocusRequester;)Landroidx/compose/ui/focus/FocusRequesterElement;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FocusRequesterElement extends ModifierNodeElement<FocusRequesterNode> {
    private final FocusRequester focusRequester;

    public FocusRequesterElement(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public static /* synthetic */ FocusRequesterElement copy$default(FocusRequesterElement focusRequesterElement, FocusRequester focusRequester, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            focusRequester = focusRequesterElement.focusRequester;
        }
        return focusRequesterElement.copy(focusRequester);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final FocusRequesterElement copy(FocusRequester focusRequester) {
        return new FocusRequesterElement(focusRequester);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FocusRequesterElement) && p.a(this.focusRequester, ((FocusRequesterElement) other).focusRequester);
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.focusRequester.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("focusRequester");
        inspectorInfo.getProperties().set("focusRequester", this.focusRequester);
    }

    public String toString() {
        return "FocusRequesterElement(focusRequester=" + this.focusRequester + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public FocusRequesterNode create() {
        return new FocusRequesterNode(this.focusRequester);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(FocusRequesterNode node) {
        node.getFocusRequester().getFocusRequesterNodes$ui_release().remove(node);
        node.setFocusRequester(this.focusRequester);
        node.getFocusRequester().getFocusRequesterNodes$ui_release().add(node);
    }
}
