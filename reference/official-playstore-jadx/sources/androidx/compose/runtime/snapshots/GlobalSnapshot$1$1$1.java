package androidx.compose.runtime.snapshots;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "state", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class GlobalSnapshot$1$1$1 extends r implements l<Object, t0> {
    final /* synthetic */ List<l<Object, t0>> $it;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public GlobalSnapshot$1$1$1(List<? extends l<Object, t0>> list) {
        super(1);
        this.$it = list;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m3088invoke(obj);
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m3088invoke(Object obj) {
        List<l<Object, t0>> list = this.$it;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            list.get(i10).invoke(obj);
        }
    }
}
