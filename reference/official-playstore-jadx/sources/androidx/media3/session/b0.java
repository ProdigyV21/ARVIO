package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b0 implements ListenerSet.Event, MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ TrackSelectionParameters f3927i;

    public /* synthetic */ b0(TrackSelectionParameters trackSelectionParameters) {
        this.f3927i = trackSelectionParameters;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ((Player.Listener) obj).onTrackSelectionParametersChanged(this.f3927i);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onTrackSelectionParametersChanged(i10, this.f3927i);
    }
}
