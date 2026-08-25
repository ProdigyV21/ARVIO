package androidx.compose.foundation.text2;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/input/TextFieldValue;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BasicSecureTextFieldKt$BasicSecureTextField$1$1 extends r implements l<TextFieldValue, t0> {
    final /* synthetic */ l<String, t0> $onValueChange;
    final /* synthetic */ MutableState<TextFieldValue> $valueWithSelection$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BasicSecureTextFieldKt$BasicSecureTextField$1$1(l<? super String, t0> lVar, MutableState<TextFieldValue> mutableState) {
        super(1);
        this.$onValueChange = lVar;
        this.$valueWithSelection$delegate = mutableState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TextFieldValue) obj);
        return t0.f22605a;
    }

    public final void invoke(TextFieldValue textFieldValue) {
        if (!p.a(textFieldValue.getText(), BasicSecureTextFieldKt.BasicSecureTextField_TLP4tmw$lambda$2(this.$valueWithSelection$delegate).getText())) {
            this.$onValueChange.invoke(textFieldValue.getText());
        }
        this.$valueWithSelection$delegate.setValue(textFieldValue);
    }
}
