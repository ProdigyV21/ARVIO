package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n0 implements ListenerSet.Event, MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4083i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PlaybackException f4084l;

    public /* synthetic */ n0(int i10, PlaybackException playbackException) {
        this.f4083i = i10;
        this.f4084l = playbackException;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f4083i) {
            case 0:
                ((Player.Listener) obj).onPlayerErrorChanged(this.f4084l);
                break;
            case 1:
                ((Player.Listener) obj).onPlayerError(this.f4084l);
                break;
            case 2:
                ((Player.Listener) obj).onPlayerErrorChanged(this.f4084l);
                break;
            default:
                ((Player.Listener) obj).onPlayerError(this.f4084l);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onPlayerError(i10, this.f4084l);
    }
}
