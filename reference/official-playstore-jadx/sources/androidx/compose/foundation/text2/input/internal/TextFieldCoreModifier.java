package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BG\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0003HÂ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0005HÂ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0007HÂ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\tHÂ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u000bHÂ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0003HÂ\u0003¢\u0006\u0004\b\u001e\u0010\u0015J\u0010\u0010\u001f\u001a\u00020\u000eHÂ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0010HÂ\u0003¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u0002H\u0016¢\u0006\u0004\b#\u0010$J\u0017\u0010'\u001a\u00020&2\u0006\u0010%\u001a\u00020\u0002H\u0016¢\u0006\u0004\b'\u0010(J\u0013\u0010*\u001a\u00020&*\u00020)H\u0016¢\u0006\u0004\b*\u0010+J`\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010HÆ\u0001¢\u0006\u0004\b,\u0010-J\u0010\u0010/\u001a\u00020.HÖ\u0001¢\u0006\u0004\b/\u00100J\u0010\u00102\u001a\u000201HÖ\u0001¢\u0006\u0004\b2\u00103J\u001a\u00106\u001a\u00020\u00032\b\u00105\u001a\u0004\u0018\u000104HÖ\u0003¢\u0006\u0004\b6\u00107R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u00108R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u00109R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010:R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010;R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010<R\u0014\u0010\r\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u00108R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010=R\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010>¨\u0006?"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextFieldCoreModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/text2/input/internal/TextFieldCoreModifierNode;", "", "isFocused", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "textFieldSelectionState", "Landroidx/compose/ui/graphics/Brush;", "cursorBrush", "writeable", "Landroidx/compose/foundation/ScrollState;", "scrollState", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "<init>", "(ZLandroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/graphics/Brush;ZLandroidx/compose/foundation/ScrollState;Landroidx/compose/foundation/gestures/Orientation;)V", "component1", "()Z", "component2", "()Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "component3", "()Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "component4", "()Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "component5", "()Landroidx/compose/ui/graphics/Brush;", "component6", "component7", "()Landroidx/compose/foundation/ScrollState;", "component8", "()Landroidx/compose/foundation/gestures/Orientation;", "create", "()Landroidx/compose/foundation/text2/input/internal/TextFieldCoreModifierNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/foundation/text2/input/internal/TextFieldCoreModifierNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "copy", "(ZLandroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/graphics/Brush;ZLandroidx/compose/foundation/ScrollState;Landroidx/compose/foundation/gestures/Orientation;)Landroidx/compose/foundation/text2/input/internal/TextFieldCoreModifier;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "Landroidx/compose/ui/graphics/Brush;", "Landroidx/compose/foundation/ScrollState;", "Landroidx/compose/foundation/gestures/Orientation;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class TextFieldCoreModifier extends ModifierNodeElement<TextFieldCoreModifierNode> {
    public static final int $stable = 0;
    private final Brush cursorBrush;
    private final boolean isFocused;
    private final Orientation orientation;
    private final ScrollState scrollState;
    private final TextFieldSelectionState textFieldSelectionState;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private final boolean writeable;

    public TextFieldCoreModifier(boolean z, TextLayoutState textLayoutState, TransformedTextFieldState transformedTextFieldState, TextFieldSelectionState textFieldSelectionState, Brush brush, boolean z5, ScrollState scrollState, Orientation orientation) {
        this.isFocused = z;
        this.textLayoutState = textLayoutState;
        this.textFieldState = transformedTextFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = brush;
        this.writeable = z5;
        this.scrollState = scrollState;
        this.orientation = orientation;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final boolean getIsFocused() {
        return this.isFocused;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final TextLayoutState getTextLayoutState() {
        return this.textLayoutState;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final TextFieldSelectionState getTextFieldSelectionState() {
        return this.textFieldSelectionState;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    private final Brush getCursorBrush() {
        return this.cursorBrush;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    private final boolean getWriteable() {
        return this.writeable;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    private final ScrollState getScrollState() {
        return this.scrollState;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    private final Orientation getOrientation() {
        return this.orientation;
    }

    public static /* synthetic */ TextFieldCoreModifier copy$default(TextFieldCoreModifier textFieldCoreModifier, boolean z, TextLayoutState textLayoutState, TransformedTextFieldState transformedTextFieldState, TextFieldSelectionState textFieldSelectionState, Brush brush, boolean z5, ScrollState scrollState, Orientation orientation, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = textFieldCoreModifier.isFocused;
        }
        if ((i10 & 2) != 0) {
            textLayoutState = textFieldCoreModifier.textLayoutState;
        }
        if ((i10 & 4) != 0) {
            transformedTextFieldState = textFieldCoreModifier.textFieldState;
        }
        if ((i10 & 8) != 0) {
            textFieldSelectionState = textFieldCoreModifier.textFieldSelectionState;
        }
        if ((i10 & 16) != 0) {
            brush = textFieldCoreModifier.cursorBrush;
        }
        if ((i10 & 32) != 0) {
            z5 = textFieldCoreModifier.writeable;
        }
        if ((i10 & 64) != 0) {
            scrollState = textFieldCoreModifier.scrollState;
        }
        if ((i10 & 128) != 0) {
            orientation = textFieldCoreModifier.orientation;
        }
        ScrollState scrollState2 = scrollState;
        Orientation orientation2 = orientation;
        Brush brush2 = brush;
        boolean z10 = z5;
        return textFieldCoreModifier.copy(z, textLayoutState, transformedTextFieldState, textFieldSelectionState, brush2, z10, scrollState2, orientation2);
    }

    public final TextFieldCoreModifier copy(boolean isFocused, TextLayoutState textLayoutState, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, Brush cursorBrush, boolean writeable, ScrollState scrollState, Orientation orientation) {
        return new TextFieldCoreModifier(isFocused, textLayoutState, textFieldState, textFieldSelectionState, cursorBrush, writeable, scrollState, orientation);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextFieldCoreModifier)) {
            return false;
        }
        TextFieldCoreModifier textFieldCoreModifier = (TextFieldCoreModifier) other;
        return this.isFocused == textFieldCoreModifier.isFocused && p.a(this.textLayoutState, textFieldCoreModifier.textLayoutState) && p.a(this.textFieldState, textFieldCoreModifier.textFieldState) && p.a(this.textFieldSelectionState, textFieldCoreModifier.textFieldSelectionState) && p.a(this.cursorBrush, textFieldCoreModifier.cursorBrush) && this.writeable == textFieldCoreModifier.writeable && p.a(this.scrollState, textFieldCoreModifier.scrollState) && this.orientation == textFieldCoreModifier.orientation;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.orientation.hashCode() + ((this.scrollState.hashCode() + ((((this.cursorBrush.hashCode() + ((this.textFieldSelectionState.hashCode() + ((this.textFieldState.hashCode() + ((this.textLayoutState.hashCode() + ((this.isFocused ? 1231 : 1237) * 31)) * 31)) * 31)) * 31)) * 31) + (this.writeable ? 1231 : 1237)) * 31)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
    }

    public String toString() {
        return "TextFieldCoreModifier(isFocused=" + this.isFocused + ", textLayoutState=" + this.textLayoutState + ", textFieldState=" + this.textFieldState + ", textFieldSelectionState=" + this.textFieldSelectionState + ", cursorBrush=" + this.cursorBrush + ", writeable=" + this.writeable + ", scrollState=" + this.scrollState + ", orientation=" + this.orientation + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public TextFieldCoreModifierNode create() {
        return new TextFieldCoreModifierNode(this.isFocused, this.textLayoutState, this.textFieldState, this.textFieldSelectionState, this.cursorBrush, this.writeable, this.scrollState, this.orientation);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(TextFieldCoreModifierNode node) {
        node.updateNode(this.isFocused, this.textLayoutState, this.textFieldState, this.textFieldSelectionState, this.cursorBrush, this.writeable, this.scrollState, this.orientation);
    }
}
