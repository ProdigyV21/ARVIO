package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3945i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionImpl f3946l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaSession.ControllerInfo f3947m;

    public /* synthetic */ d(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        this.f3945i = i10;
        this.f3946l = mediaSessionImpl;
        this.f3947m = controllerInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3945i) {
            case 0:
                ConnectedControllersManager.lambda$removeController$0(this.f3946l, this.f3947m);
                break;
            case 1:
                this.f3946l.lambda$applyMediaButtonKeyEvent$25(this.f3947m);
                break;
            case 2:
                this.f3946l.lambda$applyMediaButtonKeyEvent$26(this.f3947m);
                break;
            case 3:
                this.f3946l.lambda$applyMediaButtonKeyEvent$27(this.f3947m);
                break;
            case 4:
                this.f3946l.lambda$applyMediaButtonKeyEvent$28(this.f3947m);
                break;
            case 5:
                this.f3946l.lambda$applyMediaButtonKeyEvent$29(this.f3947m);
                break;
            case 6:
                this.f3946l.lambda$applyMediaButtonKeyEvent$30(this.f3947m);
                break;
            case 7:
                this.f3946l.lambda$applyMediaButtonKeyEvent$31(this.f3947m);
                break;
            case 8:
                this.f3946l.lambda$applyMediaButtonKeyEvent$32(this.f3947m);
                break;
            default:
                this.f3946l.lambda$applyMediaButtonKeyEvent$33(this.f3947m);
                break;
        }
    }
}
