package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d2 implements MediaSessionImpl.RemoteControllerTask, MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3955i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ com.google.common.collect.h1 f3956l;

    public /* synthetic */ d2(int i10, com.google.common.collect.h1 h1Var) {
        this.f3955i = i10;
        this.f3956l = h1Var;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return MediaSessionStub.lambda$replaceMediaItems$51(this.f3956l, mediaSessionImpl, controllerInfo, i10);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f3955i) {
            case 0:
                controllerCb.setMediaButtonPreferences(i10, this.f3956l);
                break;
            case 1:
                controllerCb.setMediaButtonPreferences(i10, this.f3956l);
                break;
            case 2:
                controllerCb.setCustomLayout(i10, this.f3956l);
                break;
            default:
                controllerCb.setCustomLayout(i10, this.f3956l);
                break;
        }
    }
}
