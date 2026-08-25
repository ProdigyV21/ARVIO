package androidx.media3.session;

import android.app.PendingIntent;
import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l1 implements MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4065i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4066l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4067m;

    public /* synthetic */ l1(int i10, Object obj, int i11) {
        this.f4065i = i11;
        this.f4066l = i10;
        this.f4067m = obj;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        MediaSessionStub.lambda$sendLibraryResultWhenReady$10((MediaSession.ControllerInfo) this.f4067m, this.f4066l, (com.google.common.util.concurrent.d1) obj);
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        switch (this.f4065i) {
            case 0:
                mediaControllerImplBase.onSetCustomLayout(this.f4066l, (List) this.f4067m);
                break;
            case 1:
                mediaControllerImplBase.onSetMediaButtonPreferences(this.f4066l, (com.google.common.collect.h1) this.f4067m);
                break;
            case 2:
                mediaControllerImplBase.onError(this.f4066l, (SessionError) this.f4067m);
                break;
            default:
                mediaControllerImplBase.onSetSessionActivity(this.f4066l, (PendingIntent) this.f4067m);
                break;
        }
    }

    public /* synthetic */ l1(Object obj, int i10, int i11) {
        this.f4065i = i11;
        this.f4067m = obj;
        this.f4066l = i10;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f4065i) {
            case 4:
                MediaSessionImpl.PlayerListener.lambda$onPlaybackStateChanged$4(this.f4066l, (PlayerWrapper) this.f4067m, controllerCb, i10);
                break;
            case 5:
                controllerCb.onTimelineChanged(i10, (Timeline) this.f4067m, this.f4066l);
                break;
            default:
                controllerCb.onMediaItemTransition(i10, (MediaItem) this.f4067m, this.f4066l);
                break;
        }
    }
}
