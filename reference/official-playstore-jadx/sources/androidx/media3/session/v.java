package androidx.media3.session;

import android.app.ForegroundServiceStartNotAllowedException;
import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.DefaultMediaNotificationProvider;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class v implements ListenerSet.Event, MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask, MediaSessionStub.MediaItemsWithStartPositionPlayerTask, Consumer, MediaSessionStub.MediaItemPlayerTask, DefaultMediaNotificationProvider.NotificationIdProvider {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4173i;

    public /* synthetic */ v(int i10) {
        this.f4173i = i10;
    }

    public static /* bridge */ /* synthetic */ ForegroundServiceStartNotAllowedException a(Object obj) {
        return (ForegroundServiceStartNotAllowedException) obj;
    }

    public static /* bridge */ /* synthetic */ boolean b(Object obj) {
        return obj instanceof ForegroundServiceStartNotAllowedException;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        PlayerWrapper playerWrapper = (PlayerWrapper) obj;
        switch (this.f4173i) {
            case 6:
                playerWrapper.mute();
                break;
            case 7:
                playerWrapper.pause();
                break;
            case 8:
                playerWrapper.seekToNextMediaItem();
                break;
            case 9:
                playerWrapper.increaseDeviceVolume();
                break;
            case 10:
                playerWrapper.seekBack();
                break;
            case 11:
                playerWrapper.seekToPrevious();
                break;
            case 12:
                playerWrapper.seekToPreviousMediaItem();
                break;
            case 13:
                playerWrapper.seekForward();
                break;
            case 14:
                playerWrapper.seekToNext();
                break;
            case 15:
                playerWrapper.decreaseDeviceVolume();
                break;
            case 16:
            case 17:
            default:
                playerWrapper.clearMediaItems();
                break;
            case 18:
                playerWrapper.seekToDefaultPosition();
                break;
            case 19:
                playerWrapper.stop();
                break;
            case 20:
                playerWrapper.unmute();
                break;
            case 21:
                playerWrapper.prepare();
                break;
        }
    }

    @Override // androidx.media3.session.DefaultMediaNotificationProvider.NotificationIdProvider
    public int getNotificationId(MediaSession mediaSession) {
        switch (this.f4173i) {
            case 23:
                return DefaultMediaNotificationProvider.lambda$new$0(mediaSession);
            default:
                return DefaultMediaNotificationProvider.Builder.lambda$new$0(mediaSession);
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        Player.Listener listener = (Player.Listener) obj;
        switch (this.f4173i) {
            case 0:
                listener.onVolumeChanged(0.0f);
                break;
            default:
                listener.onPlaybackStateChanged(1);
                break;
        }
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        switch (this.f4173i) {
            case 2:
                mediaControllerImplBase.onRenderedFirstFrame();
                break;
            default:
                MediaControllerStub.lambda$onDisconnected$1(mediaControllerImplBase);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onRenderedFirstFrame(i10);
    }

    @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
    public void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        switch (this.f4173i) {
            case 16:
                playerWrapper.addMediaItems(list);
                break;
            default:
                playerWrapper.addMediaItems(list);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionStub.MediaItemsWithStartPositionPlayerTask
    public void run(PlayerWrapper playerWrapper, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
        MediaUtils.setMediaItemsWithStartIndexAndPosition(playerWrapper, mediaItemsWithStartPosition);
    }
}
