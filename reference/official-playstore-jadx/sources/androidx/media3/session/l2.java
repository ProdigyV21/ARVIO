package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l2 implements MediaSessionLegacyStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4068i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionLegacyStub f4069l;

    public /* synthetic */ l2(MediaSessionLegacyStub mediaSessionLegacyStub, int i10) {
        this.f4068i = i10;
        this.f4069l = mediaSessionLegacyStub;
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        switch (this.f4068i) {
            case 0:
                this.f4069l.lambda$onFastForward$13(controllerInfo);
                break;
            case 1:
                this.f4069l.lambda$handleMediaPlayPauseOnHandler$2(controllerInfo);
                break;
            case 2:
                this.f4069l.lambda$onSkipToPrevious$9(controllerInfo);
                break;
            case 3:
                this.f4069l.lambda$onSkipToPrevious$10(controllerInfo);
                break;
            case 4:
                this.f4069l.lambda$onRewind$14(controllerInfo);
                break;
            case 5:
                this.f4069l.lambda$dispatchSessionTaskWithPlayRequest$18(controllerInfo);
                break;
            case 6:
                this.f4069l.lambda$onPrepare$3(controllerInfo);
                break;
            case 7:
                this.f4069l.lambda$onStop$5(controllerInfo);
                break;
            case 8:
                this.f4069l.lambda$onSkipToNext$7(controllerInfo);
                break;
            case 9:
                this.f4069l.lambda$onSkipToNext$8(controllerInfo);
                break;
            default:
                this.f4069l.lambda$onPause$4(controllerInfo);
                break;
        }
    }
}
