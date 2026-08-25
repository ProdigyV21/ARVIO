package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaControllerImplBase;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d0 implements MediaControllerImplBase.RemoteSessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3948i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f3949l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3950m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MediaItem f3951n;

    public /* synthetic */ d0(MediaControllerImplBase mediaControllerImplBase, int i10, MediaItem mediaItem, int i11) {
        this.f3948i = i11;
        this.f3949l = mediaControllerImplBase;
        this.f3950m = i10;
        this.f3951n = mediaItem;
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public final void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f3948i) {
            case 0:
                this.f3949l.lambda$replaceMediaItem$40(this.f3950m, this.f3951n, iMediaSession, i10);
                break;
            default:
                this.f3949l.lambda$addMediaItem$32(this.f3950m, this.f3951n, iMediaSession, i10);
                break;
        }
    }
}
