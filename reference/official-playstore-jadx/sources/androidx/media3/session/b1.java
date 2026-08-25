package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaControllerImplBase;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b1 implements MediaControllerImplBase.RemoteSessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3928i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase.SurfaceCallback f3929l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3930m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3931n;

    public /* synthetic */ b1(MediaControllerImplBase.SurfaceCallback surfaceCallback, int i10, int i11, int i12) {
        this.f3928i = i12;
        this.f3929l = surfaceCallback;
        this.f3930m = i10;
        this.f3931n = i11;
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public final void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f3928i) {
            case 0:
                this.f3929l.lambda$surfaceChanged$0(this.f3930m, this.f3931n, iMediaSession, i10);
                break;
            default:
                this.f3929l.lambda$onSurfaceTextureSizeChanged$1(this.f3930m, this.f3931n, iMediaSession, i10);
                break;
        }
    }
}
