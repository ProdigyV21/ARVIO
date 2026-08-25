package androidx.compose.foundation;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ScrollState$canScrollForward$2 extends r implements r7.a<Boolean> {
    final /* synthetic */ ScrollState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollState$canScrollForward$2(ScrollState scrollState) {
        super(0);
        this.this$0 = scrollState;
    }

    @Override // r7.a
    public final Boolean invoke() {
        return Boolean.valueOf(this.this$0.getValue() < this.this$0.getMaxValue());
    }
}
