package w5;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes4.dex */
public final class k0 implements ServiceConnection {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ androidx.emoji2.text.e0 f22463i;

    public k0(androidx.emoji2.text.e0 e0Var) {
        this.f22463i = e0Var;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        StringBuilder sb2 = new StringBuilder("Connected to SessionLifecycleService. Queue size ");
        androidx.emoji2.text.e0 e0Var = this.f22463i;
        LinkedBlockingDeque linkedBlockingDeque = (LinkedBlockingDeque) e0Var.f2602n;
        sb2.append(linkedBlockingDeque.size());
        Log.d("SessionLifecycleClient", sb2.toString());
        e0Var.f2601m = new Messenger(iBinder);
        ArrayList arrayList = new ArrayList();
        linkedBlockingDeque.drainTo(arrayList);
        ka.m0.p(ka.l0.a((d7.j) e0Var.f2600l), null, 0, new androidx.work.impl.constraints.controllers.c(e0Var, arrayList, null, 7), 3);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Log.d("SessionLifecycleClient", "Disconnected from SessionLifecycleService");
        androidx.emoji2.text.e0 e0Var = this.f22463i;
        e0Var.f2601m = null;
        e0Var.getClass();
    }
}
