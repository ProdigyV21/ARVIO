package androidx.compose.ui.platform;

import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$2 extends kotlin.jvm.internal.r implements r7.a<t0> {
    final /* synthetic */ k0 $disposer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$2(k0 k0Var) {
        super(0);
        this.$disposer = k0Var;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m5000invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m5000invoke() {
        ((r7.a) this.$disposer.f19746i).invoke();
    }
}
