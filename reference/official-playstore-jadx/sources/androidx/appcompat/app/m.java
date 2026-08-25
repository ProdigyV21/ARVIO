package androidx.appcompat.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class m extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1115a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f1116b;

    public /* synthetic */ m() {
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        String string;
        switch (this.f1115a) {
            case 0:
                int i10 = message.what;
                if (i10 == -3 || i10 == -2 || i10 == -1) {
                    ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) ((WeakReference) this.f1116b).get(), message.what);
                    break;
                } else if (i10 == 1) {
                    ((DialogInterface) message.obj).dismiss();
                    break;
                }
                break;
            case 1:
                androidx.mediarouter.app.g gVar = (androidx.mediarouter.app.g) this.f1116b;
                m mVar = gVar.D;
                int i11 = message.what;
                if (i11 == 1) {
                    gVar.d((List) message.obj);
                } else if (i11 == 2) {
                    if (gVar.f4286p.isEmpty()) {
                        gVar.f(2);
                        mVar.removeMessages(2);
                        mVar.removeMessages(3);
                        mVar.sendMessageDelayed(mVar.obtainMessage(3), 15000L);
                    }
                } else if (i11 == 3 && gVar.f4286p.isEmpty()) {
                    gVar.f(3);
                    mVar.removeMessages(2);
                    mVar.removeMessages(3);
                    mVar.removeMessages(1);
                    gVar.f4283m.e(gVar.f4284n);
                }
                break;
            default:
                if (message.what != 3) {
                    Log.w("SessionLifecycleClient", "Received unexpected event from the SessionLifecycleService: " + message);
                    super.handleMessage(message);
                } else {
                    Bundle data = message.getData();
                    if (data == null || (string = data.getString("SessionUpdateExtra")) == null) {
                        string = "";
                    }
                    Log.d("SessionLifecycleClient", "Session update received: ".concat(string));
                    ka.m0.p(ka.l0.a((d7.j) this.f1116b), null, 0, new androidx.tv.foundation.lazy.grid.a1(string, (d7.d) null, 4), 3);
                }
                break;
        }
    }

    public m(d7.j jVar) {
        super(Looper.getMainLooper());
        this.f1116b = jVar;
    }

    public m(androidx.mediarouter.app.g gVar) {
        this.f1116b = gVar;
    }
}
