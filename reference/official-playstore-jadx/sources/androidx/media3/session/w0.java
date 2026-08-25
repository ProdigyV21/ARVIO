package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class w0 implements Consumer, MediaSessionImpl.RemoteControllerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4184i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4185l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4186m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4187n;

    public /* synthetic */ w0(int i10, SessionCommand sessionCommand, Bundle bundle, MediaControllerImplBase mediaControllerImplBase) {
        this.f4185l = mediaControllerImplBase;
        this.f4186m = sessionCommand;
        this.f4187n = bundle;
        this.f4184i = i10;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((MediaControllerImplBase) this.f4185l).lambda$onCustomCommand$109((SessionCommand) this.f4186m, (Bundle) this.f4187n, this.f4184i, (MediaController.Listener) obj);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        ((MediaLibrarySessionImpl) this.f4185l).lambda$notifyChildrenChanged$4((String) this.f4186m, this.f4184i, (MediaLibraryService.LibraryParams) this.f4187n, controllerCb, i10);
    }

    public /* synthetic */ w0(MediaLibrarySessionImpl mediaLibrarySessionImpl, String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
        this.f4185l = mediaLibrarySessionImpl;
        this.f4186m = str;
        this.f4184i = i10;
        this.f4187n = libraryParams;
    }
}
