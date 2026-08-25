package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class r0 implements ListenerSet.Event, MediaSessionImpl.RemoteControllerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4125i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f4126l;

    public /* synthetic */ r0(boolean z, int i10) {
        this.f4125i = i10;
        this.f4126l = z;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f4125i) {
            case 4:
                ((PlayerWrapper) obj).setPlayWhenReady(this.f4126l);
                break;
            case 5:
                ((PlayerWrapper) obj).setDeviceMuted(this.f4126l);
                break;
            default:
                ((PlayerWrapper) obj).setShuffleModeEnabled(this.f4126l);
                break;
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ((Player.Listener) obj).onShuffleModeEnabledChanged(this.f4126l);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f4125i) {
            case 1:
                controllerCb.onIsLoadingChanged(i10, this.f4126l);
                break;
            case 2:
                controllerCb.onShuffleModeEnabledChanged(i10, this.f4126l);
                break;
            default:
                controllerCb.onIsPlayingChanged(i10, this.f4126l);
                break;
        }
    }
}
