package androidx.media3.session;

import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h3 implements MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4012i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f4013l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaLibraryService.LibraryParams f4014m;

    public /* synthetic */ h3(String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
        this.f4012i = i10;
        this.f4013l = str;
        this.f4014m = libraryParams;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f4012i) {
            case 0:
                return MediaSessionStub.lambda$subscribe$76(this.f4013l, this.f4014m, (MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$search$74(this.f4013l, this.f4014m, (MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i10);
        }
    }
}
