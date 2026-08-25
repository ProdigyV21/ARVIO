package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.c;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\t\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B_\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u00126\u0010\u0014\u001a2\u0012\u0004\u0012\u00020\f\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0003HÂ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0005HÂ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0007HÂ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\tHÂ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ@\u0010\u001f\u001a2\u0012\u0004\u0012\u00020\f\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u0013HÂ\u0003¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0002H\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0002H\u0016¢\u0006\u0004\b$\u0010%J\u0013\u0010'\u001a\u00020\u0012*\u00020&H\u0016¢\u0006\u0004\b'\u0010(Jr\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t28\b\u0002\u0010\u0014\u001a2\u0012\u0004\u0012\u00020\f\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u0013HÆ\u0001¢\u0006\u0004\b)\u0010*J\u0010\u0010,\u001a\u00020+HÖ\u0001¢\u0006\u0004\b,\u0010-J\u0010\u0010/\u001a\u00020.HÖ\u0001¢\u0006\u0004\b/\u00100J\u001a\u00103\u001a\u00020\t2\b\u00102\u001a\u0004\u0018\u000101HÖ\u0003¢\u0006\u0004\b3\u00104R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u00105R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u00106R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u00107R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u00108RD\u0010\u0014\u001a2\u0012\u0004\u0012\u00020\f\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u00109¨\u0006:"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextFieldTextLayoutModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/text2/input/internal/TextFieldTextLayoutModifierNode;", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldState", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "", "singleLine", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lx6/y;", ContentDisposition.Parameters.Name, "getResult", "Lx6/t0;", "Lx6/n;", "onTextLayout", "<init>", "(Landroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/ui/text/TextStyle;ZLr7/p;)V", "component1", "()Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "component2", "()Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "component3", "()Landroidx/compose/ui/text/TextStyle;", "component4", "()Z", "component5", "()Lr7/p;", "create", "()Landroidx/compose/foundation/text2/input/internal/TextFieldTextLayoutModifierNode;", "node", "update", "(Landroidx/compose/foundation/text2/input/internal/TextFieldTextLayoutModifierNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "copy", "(Landroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/ui/text/TextStyle;ZLr7/p;)Landroidx/compose/foundation/text2/input/internal/TextFieldTextLayoutModifier;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "Landroidx/compose/ui/text/TextStyle;", "Z", "Lr7/p;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class TextFieldTextLayoutModifier extends ModifierNodeElement<TextFieldTextLayoutModifierNode> {
    public static final int $stable = 0;
    private final p<Density, r7.a<TextLayoutResult>, t0> onTextLayout;
    private final boolean singleLine;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private final TextStyle textStyle;

    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldTextLayoutModifier(TextLayoutState textLayoutState, TransformedTextFieldState transformedTextFieldState, TextStyle textStyle, boolean z, p<? super Density, ? super r7.a<TextLayoutResult>, t0> pVar) {
        this.textLayoutState = textLayoutState;
        this.textFieldState = transformedTextFieldState;
        this.textStyle = textStyle;
        this.singleLine = z;
        this.onTextLayout = pVar;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final TextLayoutState getTextLayoutState() {
        return this.textLayoutState;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final TextStyle getTextStyle() {
        return this.textStyle;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final boolean getSingleLine() {
        return this.singleLine;
    }

    private final p<Density, r7.a<TextLayoutResult>, t0> component5() {
        return this.onTextLayout;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TextFieldTextLayoutModifier copy$default(TextFieldTextLayoutModifier textFieldTextLayoutModifier, TextLayoutState textLayoutState, TransformedTextFieldState transformedTextFieldState, TextStyle textStyle, boolean z, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textLayoutState = textFieldTextLayoutModifier.textLayoutState;
        }
        if ((i10 & 2) != 0) {
            transformedTextFieldState = textFieldTextLayoutModifier.textFieldState;
        }
        if ((i10 & 4) != 0) {
            textStyle = textFieldTextLayoutModifier.textStyle;
        }
        if ((i10 & 8) != 0) {
            z = textFieldTextLayoutModifier.singleLine;
        }
        if ((i10 & 16) != 0) {
            pVar = textFieldTextLayoutModifier.onTextLayout;
        }
        p pVar2 = pVar;
        TextStyle textStyle2 = textStyle;
        return textFieldTextLayoutModifier.copy(textLayoutState, transformedTextFieldState, textStyle2, z, pVar2);
    }

    public final TextFieldTextLayoutModifier copy(TextLayoutState textLayoutState, TransformedTextFieldState textFieldState, TextStyle textStyle, boolean singleLine, p<? super Density, ? super r7.a<TextLayoutResult>, t0> onTextLayout) {
        return new TextFieldTextLayoutModifier(textLayoutState, textFieldState, textStyle, singleLine, onTextLayout);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextFieldTextLayoutModifier)) {
            return false;
        }
        TextFieldTextLayoutModifier textFieldTextLayoutModifier = (TextFieldTextLayoutModifier) other;
        return kotlin.jvm.internal.p.a(this.textLayoutState, textFieldTextLayoutModifier.textLayoutState) && kotlin.jvm.internal.p.a(this.textFieldState, textFieldTextLayoutModifier.textFieldState) && kotlin.jvm.internal.p.a(this.textStyle, textFieldTextLayoutModifier.textStyle) && this.singleLine == textFieldTextLayoutModifier.singleLine && kotlin.jvm.internal.p.a(this.onTextLayout, textFieldTextLayoutModifier.onTextLayout);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int iB = (c.b((this.textFieldState.hashCode() + (this.textLayoutState.hashCode() * 31)) * 31, 31, this.textStyle) + (this.singleLine ? 1231 : 1237)) * 31;
        p<Density, r7.a<TextLayoutResult>, t0> pVar = this.onTextLayout;
        return iB + (pVar == null ? 0 : pVar.hashCode());
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
    }

    public String toString() {
        return "TextFieldTextLayoutModifier(textLayoutState=" + this.textLayoutState + ", textFieldState=" + this.textFieldState + ", textStyle=" + this.textStyle + ", singleLine=" + this.singleLine + ", onTextLayout=" + this.onTextLayout + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public TextFieldTextLayoutModifierNode create() {
        return new TextFieldTextLayoutModifierNode(this.textLayoutState, this.textFieldState, this.textStyle, this.singleLine, this.onTextLayout);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(TextFieldTextLayoutModifierNode node) {
        node.updateNode(this.textLayoutState, this.textFieldState, this.textStyle, this.singleLine, this.onTextLayout);
    }
}
