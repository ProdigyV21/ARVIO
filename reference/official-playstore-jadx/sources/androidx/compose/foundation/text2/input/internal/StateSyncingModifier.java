package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B3\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001b\u001a\u00020\b*\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001dR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001eR \u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001fR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010 ¨\u0006!"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/StateSyncingModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/text2/input/internal/StateSyncingModifierNode;", "Landroidx/compose/foundation/text2/input/TextFieldState;", "state", "Landroidx/compose/ui/text/input/TextFieldValue;", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChanged", "", "writeSelectionFromTextFieldValue", "<init>", "(Landroidx/compose/foundation/text2/input/TextFieldState;Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Z)V", "create", "()Landroidx/compose/foundation/text2/input/internal/StateSyncingModifierNode;", "node", "update", "(Landroidx/compose/foundation/text2/input/internal/StateSyncingModifierNode;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "Landroidx/compose/foundation/text2/input/TextFieldState;", "Landroidx/compose/ui/text/input/TextFieldValue;", "Lr7/l;", "Z", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class StateSyncingModifier extends ModifierNodeElement<StateSyncingModifierNode> {
    private final l<TextFieldValue, t0> onValueChanged;
    private final TextFieldState state;
    private final TextFieldValue value;
    private final boolean writeSelectionFromTextFieldValue;

    /* JADX WARN: Multi-variable type inference failed */
    public StateSyncingModifier(TextFieldState textFieldState, TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, boolean z) {
        this.state = textFieldState;
        this.value = textFieldValue;
        this.onValueChanged = lVar;
        this.writeSelectionFromTextFieldValue = z;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        return false;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.state.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public StateSyncingModifierNode create() {
        return new StateSyncingModifierNode(this.state, this.onValueChanged, this.writeSelectionFromTextFieldValue);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(StateSyncingModifierNode node) {
        node.update(this.value, this.onValueChanged);
    }
}
