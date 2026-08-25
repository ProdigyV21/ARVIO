package androidx.compose.foundation.text2.input.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2$2$1 extends r implements r7.a<String> {
    final /* synthetic */ TransformedTextFieldState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2$2$1(TransformedTextFieldState transformedTextFieldState) {
        super(0);
        this.$state = transformedTextFieldState;
    }

    @Override // r7.a
    public final String invoke() {
        return "createInputConnection(value=\"" + ((Object) this.$state.getText()) + "\")";
    }
}
