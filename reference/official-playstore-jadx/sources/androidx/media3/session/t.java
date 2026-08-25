package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class t implements ListenerSet.Event, MediaSessionImpl.RemoteControllerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4152i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f4153l;

    public /* synthetic */ t(float f10, int i10) {
        this.f4152i = i10;
        this.f4153l = f10;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f4152i) {
            case 3:
                ((PlayerWrapper) obj).setPlaybackSpeed(this.f4153l);
                break;
            default:
                ((PlayerWrapper) obj).setVolume(this.f4153l);
                break;
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f4152i) {
            case 0:
                ((Player.Listener) obj).onVolumeChanged(this.f4153l);
                break;
            default:
                ((Player.Listener) obj).onVolumeChanged(this.f4153l);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onVolumeChanged(i10, this.f4153l);
    }
}
