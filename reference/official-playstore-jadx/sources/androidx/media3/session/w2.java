package androidx.media3.session;

import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class w2 implements MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4188i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f4189l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4190m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f4191n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MediaLibraryService.LibraryParams f4192o;

    public /* synthetic */ w2(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams, int i12) {
        this.f4188i = i12;
        this.f4189l = str;
        this.f4190m = i10;
        this.f4191n = i11;
        this.f4192o = libraryParams;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f4188i) {
            case 0:
                return MediaSessionStub.lambda$getChildren$73(this.f4189l, this.f4190m, this.f4191n, this.f4192o, (MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$getSearchResult$75(this.f4189l, this.f4190m, this.f4191n, this.f4192o, (MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i10);
        }
    }
}
