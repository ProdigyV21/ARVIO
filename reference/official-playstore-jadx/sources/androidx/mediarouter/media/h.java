package androidx.mediarouter.media;

import android.media.MediaRouter2;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends s {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f4368f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final MediaRouter2.RoutingController f4369g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Messenger f4370h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Messenger f4371i;
    public final Handler k;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public m f4376o;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final SparseArray f4372j = new SparseArray();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AtomicInteger f4373l = new AtomicInteger(1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a f4374m = new a(this, 1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4375n = -1;

    public h(MediaRouter2.RoutingController routingController, String str) {
        this.f4369g = routingController;
        this.f4368f = str;
        Messenger messengerH = l.h(routingController);
        this.f4370h = messengerH;
        this.f4371i = messengerH == null ? null : new Messenger(new f1(this));
        this.k = new Handler(Looper.getMainLooper());
    }

    @Override // androidx.mediarouter.media.u
    public final void d() {
        this.f4369g.release();
    }

    @Override // androidx.mediarouter.media.u
    public final void f(int i10) {
        MediaRouter2.RoutingController routingController = this.f4369g;
        if (routingController == null) {
            return;
        }
        routingController.setVolume(i10);
        this.f4375n = i10;
        Handler handler = this.k;
        a aVar = this.f4374m;
        handler.removeCallbacks(aVar);
        handler.postDelayed(aVar, 1000L);
    }

    @Override // androidx.mediarouter.media.u
    public final void i(int i10) {
        MediaRouter2.RoutingController routingController = this.f4369g;
        if (routingController == null) {
            return;
        }
        int volume = this.f4375n;
        if (volume < 0) {
            volume = routingController.getVolume();
        }
        int iMax = Math.max(0, Math.min(volume + i10, this.f4369g.getVolumeMax()));
        this.f4375n = iMax;
        this.f4369g.setVolume(iMax);
        Handler handler = this.k;
        a aVar = this.f4374m;
        handler.removeCallbacks(aVar);
        handler.postDelayed(aVar, 1000L);
    }

    public final String k() {
        m mVar = this.f4376o;
        return mVar != null ? mVar.d() : this.f4369g.getId();
    }

    public final void l(int i10, String str) {
        Messenger messenger;
        MediaRouter2.RoutingController routingController = this.f4369g;
        if (routingController == null || routingController.isReleased() || (messenger = this.f4370h) == null) {
            return;
        }
        int andIncrement = this.f4373l.getAndIncrement();
        Message messageObtain = Message.obtain();
        messageObtain.what = 7;
        messageObtain.arg1 = andIncrement;
        Bundle bundle = new Bundle();
        bundle.putInt("volume", i10);
        bundle.putString("routeId", str);
        messageObtain.setData(bundle);
        messageObtain.replyTo = this.f4371i;
        try {
            messenger.send(messageObtain);
        } catch (DeadObjectException unused) {
        } catch (RemoteException e5) {
            Log.e("MR2Provider", "Could not send control request to service.", e5);
        }
    }

    public final void m(int i10, String str) {
        Messenger messenger;
        MediaRouter2.RoutingController routingController = this.f4369g;
        if (routingController == null || routingController.isReleased() || (messenger = this.f4370h) == null) {
            return;
        }
        int andIncrement = this.f4373l.getAndIncrement();
        Message messageObtain = Message.obtain();
        messageObtain.what = 8;
        messageObtain.arg1 = andIncrement;
        Bundle bundle = new Bundle();
        bundle.putInt("volume", i10);
        bundle.putString("routeId", str);
        messageObtain.setData(bundle);
        messageObtain.replyTo = this.f4371i;
        try {
            messenger.send(messageObtain);
        } catch (DeadObjectException unused) {
        } catch (RemoteException e5) {
            Log.e("MR2Provider", "Could not send control request to service.", e5);
        }
    }
}
