package androidx.compose.material3;

import android.view.accessibility.AccessibilityManager;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TouchExplorationStateProvider_androidKt$touchExplorationState$2$1 extends r implements r7.a<t0> {
    final /* synthetic */ AccessibilityManager $accessibilityManager;
    final /* synthetic */ Listener $listener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchExplorationStateProvider_androidKt$touchExplorationState$2$1(Listener listener, AccessibilityManager accessibilityManager) {
        super(0);
        this.$listener = listener;
        this.$accessibilityManager = accessibilityManager;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m2317invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m2317invoke() {
        this.$listener.unregister(this.$accessibilityManager);
    }
}
