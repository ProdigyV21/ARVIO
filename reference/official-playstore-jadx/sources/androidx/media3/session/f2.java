package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f2 implements MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3978i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SessionError f3979l;

    public /* synthetic */ f2(int i10, SessionError sessionError) {
        this.f3978i = i10;
        this.f3979l = sessionError;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f3978i) {
            case 0:
                controllerCb.onError(i10, this.f3979l);
                break;
            case 1:
                controllerCb.onError(i10, this.f3979l);
                break;
            default:
                controllerCb.onError(i10, this.f3979l);
                break;
        }
    }
}
