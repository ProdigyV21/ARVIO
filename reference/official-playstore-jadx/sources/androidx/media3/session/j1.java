package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j1 implements MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4038i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SessionCommand f4039l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Bundle f4040m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Bundle f4041n;

    public /* synthetic */ j1(int i10, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) {
        this.f4038i = i10;
        this.f4039l = sessionCommand;
        this.f4040m = bundle;
        this.f4041n = bundle2;
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onCustomCommandProgressUpdate(this.f4038i, this.f4039l, this.f4040m, this.f4041n);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.sendCustomCommandProgressUpdate(this.f4038i, this.f4039l, this.f4040m, this.f4041n);
    }
}
