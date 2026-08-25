package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaControllerImplBase;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements MediaControllerImplBase.RemoteSessionTask, ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4100i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f4101l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4102m;

    public /* synthetic */ p(int i10, int i11, MediaControllerImplBase mediaControllerImplBase) {
        this.f4100i = i11;
        this.f4101l = mediaControllerImplBase;
        this.f4102m = i10;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f4100i) {
            case 3:
                this.f4101l.lambda$setDeviceVolume$57(this.f4102m, (Player.Listener) obj);
                break;
            case 4:
                this.f4101l.lambda$decreaseDeviceVolume$65(this.f4102m, (Player.Listener) obj);
                break;
            case 5:
            case 8:
            default:
                this.f4101l.lambda$increaseDeviceVolume$63(this.f4102m, (Player.Listener) obj);
                break;
            case 6:
                this.f4101l.lambda$decreaseDeviceVolume$67(this.f4102m, (Player.Listener) obj);
                break;
            case 7:
                this.f4101l.lambda$setDeviceVolume$59(this.f4102m, (Player.Listener) obj);
                break;
            case 9:
                this.f4101l.lambda$increaseDeviceVolume$61(this.f4102m, (Player.Listener) obj);
                break;
        }
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4100i) {
            case 0:
                this.f4101l.lambda$seekToDefaultPosition$9(this.f4102m, iMediaSession, i10);
                break;
            case 1:
                this.f4101l.lambda$setRepeatMode$46(this.f4102m, iMediaSession, i10);
                break;
            case 2:
                this.f4101l.lambda$setDeviceVolume$56(this.f4102m, iMediaSession, i10);
                break;
            case 3:
            case 4:
            case 6:
            case 7:
            default:
                this.f4101l.lambda$increaseDeviceVolume$62(this.f4102m, iMediaSession, i10);
                break;
            case 5:
                this.f4101l.lambda$decreaseDeviceVolume$66(this.f4102m, iMediaSession, i10);
                break;
            case 8:
                this.f4101l.lambda$removeMediaItem$35(this.f4102m, iMediaSession, i10);
                break;
        }
    }
}
