package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g2 implements MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3997i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SessionCommand f3998l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Bundle f3999m;

    public /* synthetic */ g2(int i10, SessionCommand sessionCommand, Bundle bundle) {
        this.f3997i = i10;
        this.f3998l = sessionCommand;
        this.f3999m = bundle;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f3997i) {
            case 0:
                controllerCb.sendCustomCommand(i10, this.f3998l, this.f3999m);
                break;
            default:
                controllerCb.sendCustomCommand(i10, this.f3998l, this.f3999m);
                break;
        }
    }
}
