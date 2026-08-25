package androidx.compose.runtime;

import androidx.compose.runtime.BroadcastFrameClock;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"R", "", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BroadcastFrameClock$withFrameNanos$2$1 extends r implements r7.l<Throwable, t0> {
    final /* synthetic */ BroadcastFrameClock.FrameAwaiter<R> $awaiter;
    final /* synthetic */ BroadcastFrameClock this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BroadcastFrameClock$withFrameNanos$2$1(BroadcastFrameClock broadcastFrameClock, BroadcastFrameClock.FrameAwaiter<R> frameAwaiter) {
        super(1);
        this.this$0 = broadcastFrameClock;
        this.$awaiter = frameAwaiter;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    public final void invoke(Throwable th) {
        Object obj = this.this$0.lock;
        BroadcastFrameClock broadcastFrameClock = this.this$0;
        Object obj2 = this.$awaiter;
        synchronized (obj) {
            broadcastFrameClock.awaiters.remove(obj2);
            if (broadcastFrameClock.awaiters.isEmpty()) {
                broadcastFrameClock.hasAwaitersUnlocked.set(0);
            }
        }
    }
}
