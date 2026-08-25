package androidx.compose.ui.input.nestedscroll;

import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lka/k0;", "invoke", "()Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NestedScrollDispatcher$calculateNestedScrollScope$1 extends r implements r7.a<k0> {
    final /* synthetic */ NestedScrollDispatcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NestedScrollDispatcher$calculateNestedScrollScope$1(NestedScrollDispatcher nestedScrollDispatcher) {
        super(0);
        this.this$0 = nestedScrollDispatcher;
    }

    @Override // r7.a
    public final k0 invoke() {
        return this.this$0.getScope();
    }
}
