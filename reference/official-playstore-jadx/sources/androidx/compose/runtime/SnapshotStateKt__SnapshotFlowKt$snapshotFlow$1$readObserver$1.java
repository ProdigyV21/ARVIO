package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import t.d0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1$readObserver$1 extends r implements r7.l<Object, t0> {
    final /* synthetic */ d0 $readSet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1$readObserver$1(d0 d0Var) {
        super(1);
        this.$readSet = d0Var;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m2989invoke(obj);
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m2989invoke(Object obj) {
        if (obj instanceof StateObjectImpl) {
            ((StateObjectImpl) obj).m3108recordReadInh_f27i8$runtime_release(ReaderKind.m3091constructorimpl(4));
        }
        this.$readSet.d(obj);
    }
}
