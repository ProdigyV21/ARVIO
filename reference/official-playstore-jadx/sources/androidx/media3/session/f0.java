package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f0 implements ListenerSet.Event, MediaSessionImpl.RemoteControllerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3975i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PlaybackParameters f3976l;

    public /* synthetic */ f0(int i10, PlaybackParameters playbackParameters) {
        this.f3975i = i10;
        this.f3976l = playbackParameters;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((PlayerWrapper) obj).setPlaybackParameters(this.f3976l);
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3975i) {
            case 0:
                ((Player.Listener) obj).onPlaybackParametersChanged(this.f3976l);
                break;
            default:
                ((Player.Listener) obj).onPlaybackParametersChanged(this.f3976l);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onPlaybackParametersChanged(i10, this.f3976l);
    }
}
