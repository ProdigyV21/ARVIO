package dagger.hilt.android.lifecycle;

import androidx.lifecycle.d1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u000e\u0010\u0002\u001a\n \u0001*\u0004\u0018\u00010\u00000\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "kotlin.jvm.PlatformType", "factory", "Landroidx/lifecycle/d1;", "invoke", "(Ljava/lang/Object;)Landroidx/lifecycle/d1;", "<anonymous>"}, k = 3, mv = {2, 1, 0})
public final class HiltViewModelExtensions$addCreationCallback$1$1 extends r implements l<Object, d1> {
    final /* synthetic */ l<VMF, d1> $callback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HiltViewModelExtensions$addCreationCallback$1$1(l<? super VMF, ? extends d1> lVar) {
        super(1);
        this.$callback = lVar;
    }

    @Override // r7.l
    public final d1 invoke(Object obj) {
        return (d1) this.$callback.invoke(obj);
    }
}
