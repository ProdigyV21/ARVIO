package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m1 implements MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4076i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f4077l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4078m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MediaLibraryService.LibraryParams f4079n;

    public /* synthetic */ m1(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) {
        this.f4076i = i11;
        this.f4077l = str;
        this.f4078m = i10;
        this.f4079n = libraryParams;
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        switch (this.f4076i) {
            case 0:
                ((MediaBrowserImplBase) mediaControllerImplBase).notifySearchResultChanged(this.f4077l, this.f4078m, this.f4079n);
                break;
            default:
                ((MediaBrowserImplBase) mediaControllerImplBase).notifyChildrenChanged(this.f4077l, this.f4078m, this.f4079n);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onSearchResultChanged(i10, this.f4077l, this.f4078m, this.f4079n);
    }
}
