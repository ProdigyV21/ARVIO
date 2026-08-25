package androidx.compose.ui.platform;

import android.os.Looper;
import android.view.Choreographer;
import ka.m0;
import ka.x0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Ld7/j;", "invoke", "()Ld7/j;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidUiDispatcher$Companion$Main$2 extends kotlin.jvm.internal.r implements r7.a<d7.j> {
    public static final AndroidUiDispatcher$Companion$Main$2 INSTANCE = new AndroidUiDispatcher$Companion$Main$2();

    public AndroidUiDispatcher$Companion$Main$2() {
        super(0);
    }

    @Override // r7.a
    public final d7.j invoke() {
        Choreographer choreographer;
        if (AndroidUiDispatcher_androidKt.isMainThread()) {
            choreographer = Choreographer.getInstance();
        } else {
            x0 x0Var = x0.f19652a;
            choreographer = (Choreographer) m0.s(pa.q.f21294a, new AndroidUiDispatcher$Companion$Main$2$dispatcher$1(null));
        }
        AndroidUiDispatcher androidUiDispatcher = new AndroidUiDispatcher(choreographer, a.a.I(Looper.getMainLooper()), null);
        return androidUiDispatcher.plus(androidUiDispatcher.getFrameClock());
    }
}
