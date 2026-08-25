package android.support.v4.media.session;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ResultReceiver;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver extends ResultReceiver {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public WeakReference f845i;

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i10, Bundle bundle) {
        d dVar;
        g gVar = (g) this.f845i.get();
        if (gVar == null || bundle == null) {
            return;
        }
        synchronized (gVar.f878b) {
            MediaSessionCompat$Token mediaSessionCompat$Token = gVar.f881e;
            IBinder binder = bundle.getBinder(MediaSessionCompat.KEY_EXTRA_BINDER);
            int i11 = n.f884d;
            if (binder == null) {
                dVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = binder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
                if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) {
                    c cVar = new c();
                    cVar.f875c = binder;
                    dVar = cVar;
                } else {
                    dVar = (d) iInterfaceQueryLocalInterface;
                }
            }
            mediaSessionCompat$Token.b(dVar);
            gVar.f881e.c(k2.c.s(bundle));
            gVar.a();
        }
    }
}
