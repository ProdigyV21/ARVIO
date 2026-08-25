package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o1 implements MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ SessionCommands f4095i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Player.Commands f4096l;

    public /* synthetic */ o1(SessionCommands sessionCommands, Player.Commands commands) {
        this.f4095i = sessionCommands;
        this.f4096l = commands;
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onAvailableCommandsChangedFromSession(this.f4095i, this.f4096l);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onAvailableCommandsChangedFromSession(i10, this.f4095i, this.f4096l);
    }
}
