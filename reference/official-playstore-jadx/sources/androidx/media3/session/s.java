package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class s implements MediaControllerImplBase.RemoteSessionTask, MediaSessionLegacyStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4137i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4138l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f4139m;

    public /* synthetic */ s(Object obj, float f10, int i10) {
        this.f4137i = i10;
        this.f4138l = obj;
        this.f4139m = f10;
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4137i) {
            case 0:
                ((MediaControllerImplBase) this.f4138l).lambda$unmute$54(this.f4139m, iMediaSession, i10);
                break;
            case 1:
                ((MediaControllerImplBase) this.f4138l).lambda$setPlaybackSpeed$17(this.f4139m, iMediaSession, i10);
                break;
            default:
                ((MediaControllerImplBase) this.f4138l).lambda$setVolume$50(this.f4139m, iMediaSession, i10);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        ((MediaSessionLegacyStub) this.f4138l).lambda$onSetPlaybackSpeed$11(this.f4139m, controllerInfo);
    }
}
