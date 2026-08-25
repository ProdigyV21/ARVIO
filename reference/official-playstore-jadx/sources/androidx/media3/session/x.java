package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaControllerImplBase;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class x implements MediaControllerImplBase.RemoteSessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4193i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f4194l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4195m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f4196n;

    public /* synthetic */ x(MediaControllerImplBase mediaControllerImplBase, int i10, int i11, int i12) {
        this.f4193i = i12;
        this.f4194l = mediaControllerImplBase;
        this.f4195m = i10;
        this.f4196n = i11;
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public final void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4193i) {
            case 0:
                this.f4194l.lambda$moveMediaItem$38(this.f4195m, this.f4196n, iMediaSession, i10);
                break;
            case 1:
                this.f4194l.lambda$removeMediaItems$36(this.f4195m, this.f4196n, iMediaSession, i10);
                break;
            default:
                this.f4194l.lambda$setDeviceVolume$58(this.f4195m, this.f4196n, iMediaSession, i10);
                break;
        }
    }
}
