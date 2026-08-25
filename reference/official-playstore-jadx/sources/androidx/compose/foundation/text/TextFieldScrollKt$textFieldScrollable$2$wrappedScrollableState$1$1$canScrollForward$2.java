package androidx.compose.foundation.text;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1$canScrollForward$2 extends r implements a<Boolean> {
    final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1$canScrollForward$2(TextFieldScrollerPosition textFieldScrollerPosition) {
        super(0);
        this.$scrollerPosition = textFieldScrollerPosition;
    }

    @Override // r7.a
    public final Boolean invoke() {
        return Boolean.valueOf(this.$scrollerPosition.getOffset() < this.$scrollerPosition.getMaximum());
    }
}
