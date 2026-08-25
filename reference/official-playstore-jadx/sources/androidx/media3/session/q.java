package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplBase;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class q implements ListenerSet.Event, Consumer, MediaControllerImplBase.RemoteSessionTask, ListenerSet.IterationFinishedEvent {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4111i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f4112l;

    public /* synthetic */ q(MediaControllerImplBase mediaControllerImplBase, int i10) {
        this.f4111i = i10;
        this.f4112l = mediaControllerImplBase;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f4111i) {
            case 1:
                this.f4112l.lambda$onAvailableCommandsChangedFromPlayer$115((MediaController.Listener) obj);
                break;
            case 2:
                this.f4112l.lambda$onAvailableCommandsChangedFromPlayer$116((MediaController.Listener) obj);
                break;
            case 20:
                this.f4112l.lambda$onAvailableCommandsChangedFromSession$112((MediaController.Listener) obj);
                break;
            default:
                this.f4112l.lambda$onAvailableCommandsChangedFromSession$113((MediaController.Listener) obj);
                break;
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f4111i) {
            case 0:
                this.f4112l.lambda$onAvailableCommandsChangedFromPlayer$114((Player.Listener) obj);
                break;
            default:
                this.f4112l.lambda$onAvailableCommandsChangedFromSession$110((Player.Listener) obj);
                break;
        }
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4111i) {
            case 3:
                this.f4112l.lambda$mute$52(0.0f, iMediaSession, i10);
                break;
            case 4:
                this.f4112l.lambda$seekToNextMediaItem$43(iMediaSession, i10);
                break;
            case 5:
                this.f4112l.lambda$decreaseDeviceVolume$64(iMediaSession, i10);
                break;
            case 6:
                this.f4112l.lambda$seekForward$13(iMediaSession, i10);
                break;
            case 7:
                this.f4112l.lambda$seekToDefaultPosition$8(iMediaSession, i10);
                break;
            case 8:
                this.f4112l.lambda$seekToPreviousMediaItem$42(iMediaSession, i10);
                break;
            case 9:
                this.f4112l.lambda$seekToPrevious$44(iMediaSession, i10);
                break;
            case 10:
                this.f4112l.lambda$clearMediaItems$37(iMediaSession, i10);
                break;
            case 11:
            default:
                this.f4112l.lambda$prepare$7(iMediaSession, i10);
                break;
            case 12:
                this.f4112l.lambda$seekBack$12(iMediaSession, i10);
                break;
            case 13:
                this.f4112l.lambda$pause$6(iMediaSession, i10);
                break;
            case 14:
                this.f4112l.lambda$increaseDeviceVolume$60(iMediaSession, i10);
                break;
            case 15:
                this.f4112l.lambda$seekToNext$45(iMediaSession, i10);
                break;
            case 16:
                this.f4112l.lambda$play$5(iMediaSession, i10);
                break;
            case 17:
                this.f4112l.lambda$stop$2(iMediaSession, i10);
                break;
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
    public void invoke(Object obj, FlagSet flagSet) {
        this.f4112l.lambda$new$0((Player.Listener) obj, flagSet);
    }
}
