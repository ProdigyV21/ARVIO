package androidx.compose.ui.node;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/node/ObserverNodeOwnerScope;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/ObserverNodeOwnerScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ObserverNodeOwnerScope$Companion$OnObserveReadsChanged$1 extends r implements l<ObserverNodeOwnerScope, t0> {
    public static final ObserverNodeOwnerScope$Companion$OnObserveReadsChanged$1 INSTANCE = new ObserverNodeOwnerScope$Companion$OnObserveReadsChanged$1();

    public ObserverNodeOwnerScope$Companion$OnObserveReadsChanged$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ObserverNodeOwnerScope) obj);
        return t0.f22605a;
    }

    public final void invoke(ObserverNodeOwnerScope observerNodeOwnerScope) {
        if (observerNodeOwnerScope.isValidOwnerScope()) {
            observerNodeOwnerScope.getObserverNode().onObservedReadsChanged();
        }
    }
}
