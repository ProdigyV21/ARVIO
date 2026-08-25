package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i1 implements MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4022i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Bundle f4023l;

    public /* synthetic */ i1(int i10, Bundle bundle) {
        this.f4022i = i10;
        this.f4023l = bundle;
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onExtrasChanged(this.f4023l);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f4022i) {
            case 1:
                controllerCb.onSessionExtrasChanged(i10, this.f4023l);
                break;
            case 2:
                controllerCb.onSessionExtrasChanged(i10, this.f4023l);
                break;
            default:
                controllerCb.onSessionExtrasChanged(i10, this.f4023l);
                break;
        }
    }
}
