package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaBrowserImplLegacy;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l implements Consumer, MediaControllerImplBase.RemoteSessionTask, MediaSessionLegacyStub.SessionTask, com.google.common.util.concurrent.w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f4059i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4060l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4061m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4062n;

    public /* synthetic */ l(MediaLibraryService.MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        this.f4059i = controllerInfo;
        this.f4061m = mediaLibrarySession;
        this.f4060l = str;
        this.f4062n = libraryParams;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((MediaBrowserImplLegacy.SubscribeCallback) this.f4059i).lambda$onChildrenLoadedInternal$1((String) this.f4060l, (List) this.f4061m, (MediaLibraryService.LibraryParams) this.f4062n, (MediaBrowser.Listener) obj);
    }

    @Override // com.google.common.util.concurrent.w
    public com.google.common.util.concurrent.d1 apply(Object obj) {
        return p1.h((MediaSession.ControllerInfo) this.f4059i, (MediaLibraryService.MediaLibrarySession) this.f4061m, (String) this.f4060l, (MediaLibraryService.LibraryParams) this.f4062n, (LibraryResult) obj);
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        ((MediaControllerImplBase) this.f4059i).lambda$sendCustomCommand$22((MediaController.ProgressListener) this.f4060l, (SessionCommand) this.f4061m, (Bundle) this.f4062n, iMediaSession, i10);
    }

    public /* synthetic */ l(Object obj, Object obj2, Object obj3, Object obj4) {
        this.f4059i = obj;
        this.f4060l = obj2;
        this.f4061m = obj3;
        this.f4062n = obj4;
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        ((MediaSessionLegacyStub) this.f4059i).lambda$onCommand$0((SessionCommand) this.f4060l, (Bundle) this.f4061m, (ResultReceiver) this.f4062n, controllerInfo);
    }
}
