package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.input.pointer.PointerInputScope;
import d7.d;
import f7.e;
import f7.j;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1", f = "TextFieldDecoratorModifier.kt", l = {158}, m = "invokeSuspend")
public final class TextFieldDecoratorModifierNode$pointerInputNode$1 extends j implements p<PointerInputScope, d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TextFieldDecoratorModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldDecoratorModifierNode$pointerInputNode$1(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, d<? super TextFieldDecoratorModifierNode$pointerInputNode$1> dVar) {
        super(2, dVar);
        this.this$0 = textFieldDecoratorModifierNode;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        TextFieldDecoratorModifierNode$pointerInputNode$1 textFieldDecoratorModifierNode$pointerInputNode$1 = new TextFieldDecoratorModifierNode$pointerInputNode$1(this.this$0, dVar);
        textFieldDecoratorModifierNode$pointerInputNode$1.L$0 = obj;
        return textFieldDecoratorModifierNode$pointerInputNode$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
        return ((TextFieldDecoratorModifierNode$pointerInputNode$1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            TextFieldSelectionState textFieldSelectionState = this.this$0.getTextFieldSelectionState();
            TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = this.this$0;
            TextFieldDecoratorModifierNode$pointerInputNode$1$1$1 textFieldDecoratorModifierNode$pointerInputNode$1$1$1 = new TextFieldDecoratorModifierNode$pointerInputNode$1$1$1(textFieldSelectionState, textFieldDecoratorModifierNode);
            TextFieldDecoratorModifierNode$pointerInputNode$1$1$2 textFieldDecoratorModifierNode$pointerInputNode$1$1$2 = new TextFieldDecoratorModifierNode$pointerInputNode$1$1$2(textFieldDecoratorModifierNode);
            this.label = 1;
            Object objTextFieldGestures = textFieldSelectionState.textFieldGestures(pointerInputScope, textFieldDecoratorModifierNode$pointerInputNode$1$1$1, textFieldDecoratorModifierNode$pointerInputNode$1$1$2, this);
            e7.a aVar = e7.a.f15033i;
            if (objTextFieldGestures == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0.f22605a;
    }
}
