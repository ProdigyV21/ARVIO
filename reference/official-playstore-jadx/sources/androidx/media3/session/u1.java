package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class u1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4165i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaLibrarySessionImpl f4166l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ com.google.common.util.concurrent.d1 f4167m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MediaSession.ControllerInfo f4168n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f4169o;

    public /* synthetic */ u1(MediaLibrarySessionImpl mediaLibrarySessionImpl, com.google.common.util.concurrent.d1 d1Var, MediaSession.ControllerInfo controllerInfo, int i10, int i11) {
        this.f4165i = i11;
        this.f4166l = mediaLibrarySessionImpl;
        this.f4167m = d1Var;
        this.f4168n = controllerInfo;
        this.f4169o = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4165i) {
            case 0:
                this.f4166l.lambda$onGetChildrenOnHandler$0(this.f4167m, this.f4168n, this.f4169o);
                break;
            default:
                this.f4166l.lambda$onGetSearchResultOnHandler$6(this.f4167m, this.f4168n, this.f4169o);
                break;
        }
    }
}
