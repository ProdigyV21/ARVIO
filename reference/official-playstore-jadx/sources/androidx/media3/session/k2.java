package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k2 implements MediaSessionImpl.RemoteControllerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4054i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f4055l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4056m;

    public /* synthetic */ k2(int i10, boolean z) {
        this.f4054i = 0;
        this.f4056m = i10;
        this.f4055l = z;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((PlayerWrapper) obj).setDeviceMuted(this.f4055l, this.f4056m);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f4054i) {
            case 0:
                controllerCb.onDeviceVolumeChanged(i10, this.f4056m, this.f4055l);
                break;
            default:
                controllerCb.onPlayWhenReadyChanged(i10, this.f4055l, this.f4056m);
                break;
        }
    }

    public /* synthetic */ k2(boolean z, int i10, int i11) {
        this.f4054i = i11;
        this.f4055l = z;
        this.f4056m = i10;
    }
}
