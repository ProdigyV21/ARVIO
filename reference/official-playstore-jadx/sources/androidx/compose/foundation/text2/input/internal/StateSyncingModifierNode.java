package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B+\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J)\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001c\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\"\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\u001eR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u001fR\u0016\u0010 \u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010\u001fR\u0018\u0010!\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010%\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006&"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/StateSyncingModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/foundation/text2/input/TextFieldState;", "state", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/TextFieldValue;", "Lx6/t0;", "onValueChanged", "", "writeSelectionFromTextFieldValue", "<init>", "(Landroidx/compose/foundation/text2/input/TextFieldState;Lr7/l;Z)V", "value", "updateState", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "fireOnValueChanged", "observeTextState", "(Z)V", "update", "(Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;)V", "onAttach", "()V", "Landroidx/compose/ui/focus/FocusState;", "focusState", "onFocusEvent", "(Landroidx/compose/ui/focus/FocusState;)V", "onObservedReadsChanged", "Landroidx/compose/foundation/text2/input/TextFieldState;", "Lr7/l;", "Z", "isFocused", "lastValueWhileFocused", "Landroidx/compose/ui/text/input/TextFieldValue;", "getShouldAutoInvalidate", "()Z", "shouldAutoInvalidate", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class StateSyncingModifierNode extends Modifier.Node implements ObserverModifierNode, FocusEventModifierNode {
    private boolean isFocused;
    private TextFieldValue lastValueWhileFocused;
    private l<? super TextFieldValue, t0> onValueChanged;
    private final TextFieldState state;
    private final boolean writeSelectionFromTextFieldValue;

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.StateSyncingModifierNode$observeTextState$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ k0 $text;
        final /* synthetic */ StateSyncingModifierNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(k0 k0Var, StateSyncingModifierNode stateSyncingModifierNode) {
            super(0);
            this.$text = k0Var;
            this.this$0 = stateSyncingModifierNode;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1103invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1103invoke() {
            this.$text.f19746i = this.this$0.state.getText();
        }
    }

    public StateSyncingModifierNode(TextFieldState textFieldState, l<? super TextFieldValue, t0> lVar, boolean z) {
        this.state = textFieldState;
        this.onValueChanged = lVar;
        this.writeSelectionFromTextFieldValue = z;
    }

    private final void observeTextState(boolean fireOnValueChanged) {
        k0 k0Var = new k0();
        ObserverModifierNodeKt.observeReads(this, new AnonymousClass1(k0Var, this));
        if (fireOnValueChanged) {
            Object obj = k0Var.f19746i;
            if (obj == null) {
                p.i("text");
                throw null;
            }
            String string = ((TextFieldCharSequence) obj).toString();
            Object obj2 = k0Var.f19746i;
            if (obj2 == null) {
                p.i("text");
                throw null;
            }
            long selectionInChars = ((TextFieldCharSequence) obj2).getSelectionInChars();
            Object obj3 = k0Var.f19746i;
            if (obj3 == null) {
                p.i("text");
                throw null;
            }
            this.onValueChanged.invoke(new TextFieldValue(string, selectionInChars, ((TextFieldCharSequence) obj3).getCompositionInChars(), (h) null));
        }
    }

    public static /* synthetic */ void observeTextState$default(StateSyncingModifierNode stateSyncingModifierNode, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        stateSyncingModifierNode.observeTextState(z);
    }

    private final void updateState(TextFieldValue value) {
        TextFieldState textFieldState = this.state;
        TextFieldBuffer textFieldBufferStartEdit = textFieldState.startEdit(textFieldState.getText());
        textFieldBufferStartEdit.setTextIfChanged$foundation_release(value.getText());
        if (this.writeSelectionFromTextFieldValue) {
            textFieldBufferStartEdit.m1061selectCharsIn5zctL8(value.getSelection());
        }
        textFieldState.commitEdit(textFieldBufferStartEdit);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        observeTextState(false);
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        if (this.isFocused && !focusState.isFocused()) {
            TextFieldValue textFieldValue = this.lastValueWhileFocused;
            if (textFieldValue != null) {
                updateState(textFieldValue);
            }
            this.lastValueWhileFocused = null;
        }
        this.isFocused = focusState.isFocused();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        observeTextState$default(this, false, 1, null);
    }

    public final void update(TextFieldValue value, l<? super TextFieldValue, t0> onValueChanged) {
        this.onValueChanged = onValueChanged;
        if (this.isFocused) {
            this.lastValueWhileFocused = value;
        } else {
            updateState(value);
        }
    }
}
