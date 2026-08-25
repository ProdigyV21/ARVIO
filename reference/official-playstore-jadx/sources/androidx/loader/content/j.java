package androidx.loader.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes3.dex */
public class j extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3346a;

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        CountDownLatch countDownLatch;
        switch (this.f3346a) {
            case 0:
                i iVar = (i) message.obj;
                int i10 = message.what;
                if (i10 != 1) {
                    if (i10 != 2) {
                        return;
                    }
                    a aVar = iVar.f3344a;
                    return;
                }
                a aVar2 = iVar.f3344a;
                Object obj = iVar.f3345b[0];
                if (aVar2.f3334n.get()) {
                    countDownLatch = aVar2.f3336p;
                    try {
                        aVar2.f3338r.dispatchOnCancelled(aVar2, obj);
                        countDownLatch.countDown();
                    } finally {
                    }
                } else {
                    countDownLatch = aVar2.f3336p;
                    try {
                        aVar2.f3338r.dispatchOnLoadComplete(aVar2, obj);
                    } finally {
                    }
                }
                aVar2.f3333m = 3;
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(Looper looper, int i10, boolean z) {
        super(looper);
        this.f3346a = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(Looper looper, Handler.Callback callback, int i10) {
        super(looper, callback);
        this.f3346a = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Looper looper, int i10) {
        super(looper);
        this.f3346a = i10;
        switch (i10) {
            case 3:
                super(looper);
                Looper.getMainLooper();
                break;
            case 4:
                super(looper);
                Looper.getMainLooper();
                break;
            default:
                Looper.getMainLooper();
                break;
        }
    }
}
