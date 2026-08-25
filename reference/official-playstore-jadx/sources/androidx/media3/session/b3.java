package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b3 implements MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3932i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ List f3933l;

    public /* synthetic */ b3(int i10, com.google.common.collect.h1 h1Var) {
        this.f3932i = i10;
        this.f3933l = h1Var;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f3932i) {
            case 0:
                return MediaSessionStub.lambda$addMediaItemsWithIndex$43(this.f3933l, mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$addMediaItems$41(this.f3933l, mediaSessionImpl, controllerInfo, i10);
        }
    }
}
