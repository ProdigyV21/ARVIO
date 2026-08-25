package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class u implements ListenerSet.Event, MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4159i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4160l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4161m;

    public /* synthetic */ u(int i10, int i11, int i12) {
        this.f4159i = i12;
        this.f4160l = i10;
        this.f4161m = i11;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f4159i) {
            case 3:
                ((PlayerWrapper) obj).setDeviceVolume(this.f4160l, this.f4161m);
                break;
            default:
                ((PlayerWrapper) obj).moveMediaItem(this.f4160l, this.f4161m);
                break;
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ((Player.Listener) obj).onSurfaceSizeChanged(this.f4160l, this.f4161m);
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onSurfaceSizeChanged(this.f4160l, this.f4161m);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onSurfaceSizeChanged(i10, this.f4160l, this.f4161m);
    }
}
