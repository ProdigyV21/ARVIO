package androidx.compose.ui.platform;

import android.os.SystemClock;
import android.view.MotionEvent;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidComposeView$resendMotionEventOnLayout$1 extends kotlin.jvm.internal.r implements r7.a<t0> {
    final /* synthetic */ AndroidComposeView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidComposeView$resendMotionEventOnLayout$1(AndroidComposeView androidComposeView) {
        super(0);
        this.this$0 = androidComposeView;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m4930invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m4930invoke() {
        MotionEvent motionEvent = this.this$0.previousMotionEvent;
        if (motionEvent != null) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 7 || actionMasked == 9) {
                this.this$0.relayoutTime = SystemClock.uptimeMillis();
                AndroidComposeView androidComposeView = this.this$0;
                androidComposeView.post(androidComposeView.resendMotionEventRunnable);
            }
        }
    }
}
