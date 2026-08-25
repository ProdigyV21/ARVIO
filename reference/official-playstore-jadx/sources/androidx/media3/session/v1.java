package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class v1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4175i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaLibrarySessionImpl f4176l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ com.google.common.util.concurrent.d1 f4177m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MediaSession.ControllerInfo f4178n;

    public /* synthetic */ v1(MediaLibrarySessionImpl mediaLibrarySessionImpl, com.google.common.util.concurrent.d1 d1Var, MediaSession.ControllerInfo controllerInfo, int i10) {
        this.f4175i = i10;
        this.f4176l = mediaLibrarySessionImpl;
        this.f4177m = d1Var;
        this.f4178n = controllerInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4175i) {
            case 0:
                this.f4176l.lambda$onGetItemOnHandler$1(this.f4177m, this.f4178n);
                break;
            default:
                this.f4176l.lambda$onSearchOnHandler$5(this.f4177m, this.f4178n);
                break;
        }
    }
}
