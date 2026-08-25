package androidx.compose.ui.platform;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/platform/ScrollObservationScope;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/ScrollObservationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeededLambda$1 extends kotlin.jvm.internal.r implements r7.l<ScrollObservationScope, t0> {
    final /* synthetic */ AndroidComposeViewAccessibilityDelegateCompat this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeededLambda$1(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat) {
        super(1);
        this.this$0 = androidComposeViewAccessibilityDelegateCompat;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ScrollObservationScope) obj);
        return t0.f22605a;
    }

    public final void invoke(ScrollObservationScope scrollObservationScope) {
        this.this$0.scheduleScrollEventIfNeeded(scrollObservationScope);
    }
}
