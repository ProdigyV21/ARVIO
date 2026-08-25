package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o2 implements MediaSessionLegacyStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4097i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionLegacyStub f4098l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4099m;

    public /* synthetic */ o2(MediaSessionLegacyStub mediaSessionLegacyStub, int i10, int i11) {
        this.f4097i = i11;
        this.f4098l = mediaSessionLegacyStub;
        this.f4099m = i10;
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        switch (this.f4097i) {
            case 0:
                this.f4098l.lambda$onSetRepeatMode$15(this.f4099m, controllerInfo);
                break;
            default:
                this.f4098l.lambda$onSetShuffleMode$16(this.f4099m, controllerInfo);
                break;
        }
    }
}
