package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaControllerImplBase;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j0 implements MediaControllerImplBase.RemoteSessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4035i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f4036l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaItem f4037m;

    public /* synthetic */ j0(MediaControllerImplBase mediaControllerImplBase, MediaItem mediaItem, int i10) {
        this.f4035i = i10;
        this.f4036l = mediaControllerImplBase;
        this.f4037m = mediaItem;
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public final void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4035i) {
            case 0:
                this.f4036l.lambda$setMediaItem$23(this.f4037m, iMediaSession, i10);
                break;
            default:
                this.f4036l.lambda$addMediaItem$31(this.f4037m, iMediaSession, i10);
                break;
        }
    }
}
