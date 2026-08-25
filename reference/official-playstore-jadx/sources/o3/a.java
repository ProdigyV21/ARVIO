package o3;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.t;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements ServiceConnection {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f20725i = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final LinkedBlockingQueue f20726l = new LinkedBlockingQueue();

    public final IBinder a() {
        t.h("BlockingServiceConnection.getService() called on main thread");
        if (this.f20725i) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.f20725i = true;
        return (IBinder) this.f20726l.take();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f20726l.add(iBinder);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
