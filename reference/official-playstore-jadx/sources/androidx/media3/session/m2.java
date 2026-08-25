package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m2 implements MediaSessionLegacyStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4080i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionLegacyStub f4081l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f4082m;

    public /* synthetic */ m2(MediaSessionLegacyStub mediaSessionLegacyStub, long j10, int i10) {
        this.f4080i = i10;
        this.f4081l = mediaSessionLegacyStub;
        this.f4082m = j10;
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        switch (this.f4080i) {
            case 0:
                this.f4081l.lambda$onSkipToQueueItem$12(this.f4082m, controllerInfo);
                break;
            default:
                this.f4081l.lambda$onSeekTo$6(this.f4082m, controllerInfo);
                break;
        }
    }
}
