package androidx.compose.foundation.text2.input.internal;

import a0.c;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BQ\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u000b¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0003HÂ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0005HÂ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0007HÂ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\tHÂ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u000bHÂ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u000bHÂ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0010\u0010 \u001a\u00020\u000eHÂ\u0003¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0010HÂ\u0003¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u000bHÂ\u0003¢\u0006\u0004\b$\u0010\u001eJ\u000f\u0010%\u001a\u00020\u0002H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010)\u001a\u00020(2\u0006\u0010'\u001a\u00020\u0002H\u0016¢\u0006\u0004\b)\u0010*J\u0013\u0010,\u001a\u00020(*\u00020+H\u0016¢\u0006\u0004\b,\u0010-Jl\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u000bHÆ\u0001¢\u0006\u0004\b.\u0010/J\u0010\u00101\u001a\u000200HÖ\u0001¢\u0006\u0004\b1\u00102J\u0010\u00104\u001a\u000203HÖ\u0001¢\u0006\u0004\b4\u00105J\u001a\u00108\u001a\u00020\u000b2\b\u00107\u001a\u0004\u0018\u000106HÖ\u0003¢\u0006\u0004\b8\u00109R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010:R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010;R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010<R\u0016\u0010\n\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010=R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010>R\u0014\u0010\r\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010>R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010?R\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010@R\u0014\u0010\u0012\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010>¨\u0006A"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifierNode;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "textFieldSelectionState", "Landroidx/compose/foundation/text2/input/InputTransformation;", "filter", "", "enabled", "readOnly", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "singleLine", "<init>", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/foundation/text2/input/InputTransformation;ZZLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Z)V", "component1", "()Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "component2", "()Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "component3", "()Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "component4", "()Landroidx/compose/foundation/text2/input/InputTransformation;", "component5", "()Z", "component6", "component7", "()Landroidx/compose/foundation/text/KeyboardOptions;", "component8", "()Landroidx/compose/foundation/text/KeyboardActions;", "component9", "create", "()Landroidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifierNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifierNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "copy", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/foundation/text2/input/InputTransformation;ZZLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Z)Landroidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifier;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "Landroidx/compose/foundation/text2/input/InputTransformation;", "Z", "Landroidx/compose/foundation/text/KeyboardOptions;", "Landroidx/compose/foundation/text/KeyboardActions;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class TextFieldDecoratorModifier extends ModifierNodeElement<TextFieldDecoratorModifierNode> {
    public static final int $stable = 0;
    private final boolean enabled;
    private final InputTransformation filter;
    private final KeyboardActions keyboardActions;
    private final KeyboardOptions keyboardOptions;
    private final boolean readOnly;
    private final boolean singleLine;
    private final TextFieldSelectionState textFieldSelectionState;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;

    public TextFieldDecoratorModifier(TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation inputTransformation, boolean z, boolean z5, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z10) {
        this.textFieldState = transformedTextFieldState;
        this.textLayoutState = textLayoutState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.filter = inputTransformation;
        this.enabled = z;
        this.readOnly = z5;
        this.keyboardOptions = keyboardOptions;
        this.keyboardActions = keyboardActions;
        this.singleLine = z10;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final TextLayoutState getTextLayoutState() {
        return this.textLayoutState;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final TextFieldSelectionState getTextFieldSelectionState() {
        return this.textFieldSelectionState;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final InputTransformation getFilter() {
        return this.filter;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    private final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    private final boolean getReadOnly() {
        return this.readOnly;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    private final KeyboardOptions getKeyboardOptions() {
        return this.keyboardOptions;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    private final KeyboardActions getKeyboardActions() {
        return this.keyboardActions;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    private final boolean getSingleLine() {
        return this.singleLine;
    }

    public static /* synthetic */ TextFieldDecoratorModifier copy$default(TextFieldDecoratorModifier textFieldDecoratorModifier, TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation inputTransformation, boolean z, boolean z5, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            transformedTextFieldState = textFieldDecoratorModifier.textFieldState;
        }
        if ((i10 & 2) != 0) {
            textLayoutState = textFieldDecoratorModifier.textLayoutState;
        }
        if ((i10 & 4) != 0) {
            textFieldSelectionState = textFieldDecoratorModifier.textFieldSelectionState;
        }
        if ((i10 & 8) != 0) {
            inputTransformation = textFieldDecoratorModifier.filter;
        }
        if ((i10 & 16) != 0) {
            z = textFieldDecoratorModifier.enabled;
        }
        if ((i10 & 32) != 0) {
            z5 = textFieldDecoratorModifier.readOnly;
        }
        if ((i10 & 64) != 0) {
            keyboardOptions = textFieldDecoratorModifier.keyboardOptions;
        }
        if ((i10 & 128) != 0) {
            keyboardActions = textFieldDecoratorModifier.keyboardActions;
        }
        if ((i10 & 256) != 0) {
            z10 = textFieldDecoratorModifier.singleLine;
        }
        KeyboardActions keyboardActions2 = keyboardActions;
        boolean z11 = z10;
        boolean z12 = z5;
        KeyboardOptions keyboardOptions2 = keyboardOptions;
        boolean z13 = z;
        TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
        return textFieldDecoratorModifier.copy(transformedTextFieldState, textLayoutState, textFieldSelectionState2, inputTransformation, z13, z12, keyboardOptions2, keyboardActions2, z11);
    }

    public final TextFieldDecoratorModifier copy(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation filter, boolean enabled, boolean readOnly, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine) {
        return new TextFieldDecoratorModifier(textFieldState, textLayoutState, textFieldSelectionState, filter, enabled, readOnly, keyboardOptions, keyboardActions, singleLine);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextFieldDecoratorModifier)) {
            return false;
        }
        TextFieldDecoratorModifier textFieldDecoratorModifier = (TextFieldDecoratorModifier) other;
        return p.a(this.textFieldState, textFieldDecoratorModifier.textFieldState) && p.a(this.textLayoutState, textFieldDecoratorModifier.textLayoutState) && p.a(this.textFieldSelectionState, textFieldDecoratorModifier.textFieldSelectionState) && p.a(this.filter, textFieldDecoratorModifier.filter) && this.enabled == textFieldDecoratorModifier.enabled && this.readOnly == textFieldDecoratorModifier.readOnly && p.a(this.keyboardOptions, textFieldDecoratorModifier.keyboardOptions) && p.a(this.keyboardActions, textFieldDecoratorModifier.keyboardActions) && this.singleLine == textFieldDecoratorModifier.singleLine;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int iHashCode = (this.textFieldSelectionState.hashCode() + ((this.textLayoutState.hashCode() + (this.textFieldState.hashCode() * 31)) * 31)) * 31;
        InputTransformation inputTransformation = this.filter;
        return ((this.keyboardActions.hashCode() + ((this.keyboardOptions.hashCode() + ((((((iHashCode + (inputTransformation == null ? 0 : inputTransformation.hashCode())) * 31) + (this.enabled ? 1231 : 1237)) * 31) + (this.readOnly ? 1231 : 1237)) * 31)) * 31)) * 31) + (this.singleLine ? 1231 : 1237);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TextFieldDecoratorModifier(textFieldState=");
        sb2.append(this.textFieldState);
        sb2.append(", textLayoutState=");
        sb2.append(this.textLayoutState);
        sb2.append(", textFieldSelectionState=");
        sb2.append(this.textFieldSelectionState);
        sb2.append(", filter=");
        sb2.append(this.filter);
        sb2.append(", enabled=");
        sb2.append(this.enabled);
        sb2.append(", readOnly=");
        sb2.append(this.readOnly);
        sb2.append(", keyboardOptions=");
        sb2.append(this.keyboardOptions);
        sb2.append(", keyboardActions=");
        sb2.append(this.keyboardActions);
        sb2.append(", singleLine=");
        return c.r(sb2, this.singleLine, ')');
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public TextFieldDecoratorModifierNode create() {
        return new TextFieldDecoratorModifierNode(this.textFieldState, this.textLayoutState, this.textFieldSelectionState, this.filter, this.enabled, this.readOnly, this.keyboardOptions, this.keyboardActions, this.singleLine);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(TextFieldDecoratorModifierNode node) {
        node.updateNode(this.textFieldState, this.textLayoutState, this.textFieldSelectionState, this.filter, this.enabled, this.readOnly, this.keyboardOptions, this.keyboardActions, this.singleLine);
    }
}
