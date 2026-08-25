package androidx.compose.material3;

import android.view.accessibility.AccessibilityManager;
import androidx.lifecycle.p;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/lifecycle/p;", "event", "Lx6/t0;", "invoke", "(Landroidx/lifecycle/p;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TouchExplorationStateProvider_androidKt$touchExplorationState$1$1 extends r implements l<p, t0> {
    final /* synthetic */ AccessibilityManager $accessibilityManager;
    final /* synthetic */ Listener $listener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchExplorationStateProvider_androidKt$touchExplorationState$1$1(Listener listener, AccessibilityManager accessibilityManager) {
        super(1);
        this.$listener = listener;
        this.$accessibilityManager = accessibilityManager;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((p) obj);
        return t0.f22605a;
    }

    public final void invoke(p pVar) {
        if (pVar == p.ON_RESUME) {
            this.$listener.register(this.$accessibilityManager);
        }
    }
}
