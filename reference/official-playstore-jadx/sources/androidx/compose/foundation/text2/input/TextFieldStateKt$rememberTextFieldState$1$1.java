package androidx.compose.foundation.text2.input;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text2/input/TextFieldState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TextFieldStateKt$rememberTextFieldState$1$1 extends r implements r7.a<TextFieldState> {
    final /* synthetic */ long $initialSelectionInChars;
    final /* synthetic */ String $initialText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldStateKt$rememberTextFieldState$1$1(String str, long j10) {
        super(0);
        this.$initialText = str;
        this.$initialSelectionInChars = j10;
    }

    @Override // r7.a
    public final TextFieldState invoke() {
        return new TextFieldState(this.$initialText, this.$initialSelectionInChars, (h) null);
    }
}
