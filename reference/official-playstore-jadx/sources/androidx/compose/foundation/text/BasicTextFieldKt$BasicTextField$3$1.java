package androidx.compose.foundation.text;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BasicTextFieldKt$BasicTextField$3$1 extends r implements a<t0> {
    final /* synthetic */ TextFieldValue $textFieldValue;
    final /* synthetic */ MutableState<TextFieldValue> $textFieldValueState$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicTextFieldKt$BasicTextField$3$1(TextFieldValue textFieldValue, MutableState<TextFieldValue> mutableState) {
        super(0);
        this.$textFieldValue = textFieldValue;
        this.$textFieldValueState$delegate = mutableState;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m803invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m803invoke() {
        if (TextRange.m5185equalsimpl0(this.$textFieldValue.getSelection(), BasicTextFieldKt.BasicTextField$lambda$2(this.$textFieldValueState$delegate).getSelection()) && p.a(this.$textFieldValue.getComposition(), BasicTextFieldKt.BasicTextField$lambda$2(this.$textFieldValueState$delegate).getComposition())) {
            return;
        }
        this.$textFieldValueState$delegate.setValue(this.$textFieldValue);
    }
}
