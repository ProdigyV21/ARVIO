package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.DefaultMediaNotificationProvider;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class z implements ListenerSet.Event, MediaSessionImpl.RemoteControllerTask, Consumer, DefaultMediaNotificationProvider.NotificationIdProvider {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4219i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4220l;

    public /* synthetic */ z(int i10, int i11) {
        this.f4219i = i11;
        this.f4220l = i10;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f4219i) {
            case 4:
                ((PlayerWrapper) obj).decreaseDeviceVolume(this.f4220l);
                break;
            case 5:
                ((PlayerWrapper) obj).increaseDeviceVolume(this.f4220l);
                break;
            case 6:
                ((PlayerWrapper) obj).setRepeatMode(this.f4220l);
                break;
            default:
                ((PlayerWrapper) obj).setDeviceVolume(this.f4220l);
                break;
        }
    }

    @Override // androidx.media3.session.DefaultMediaNotificationProvider.NotificationIdProvider
    public int getNotificationId(MediaSession mediaSession) {
        return DefaultMediaNotificationProvider.Builder.lambda$setNotificationId$1(this.f4220l, mediaSession);
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ((Player.Listener) obj).onRepeatModeChanged(this.f4220l);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f4219i) {
            case 1:
                controllerCb.onPlaybackSuppressionReasonChanged(i10, this.f4220l);
                break;
            case 2:
                controllerCb.onRepeatModeChanged(i10, this.f4220l);
                break;
            default:
                controllerCb.onAudioSessionIdChanged(i10, this.f4220l);
                break;
        }
    }
}
