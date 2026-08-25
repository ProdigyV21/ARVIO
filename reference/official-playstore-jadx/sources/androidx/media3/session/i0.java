package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i0 implements ListenerSet.Event, MediaSessionImpl.RemoteControllerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4020i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaMetadata f4021l;

    public /* synthetic */ i0(int i10, MediaMetadata mediaMetadata) {
        this.f4020i = i10;
        this.f4021l = mediaMetadata;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((PlayerWrapper) obj).setPlaylistMetadata(this.f4021l);
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ((Player.Listener) obj).onPlaylistMetadataChanged(this.f4021l);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f4020i) {
            case 1:
                controllerCb.onPlaylistMetadataChanged(i10, this.f4021l);
                break;
            default:
                controllerCb.onMediaMetadataChanged(i10, this.f4021l);
                break;
        }
    }
}
