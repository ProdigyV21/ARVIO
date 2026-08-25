package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a3 implements MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3922i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaItem f3923l;

    public /* synthetic */ a3(MediaItem mediaItem, int i10) {
        this.f3922i = i10;
        this.f3923l = mediaItem;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f3922i) {
            case 0:
                return MediaSessionStub.lambda$addMediaItemWithIndex$39(this.f3923l, mediaSessionImpl, controllerInfo, i10);
            case 1:
                return MediaSessionStub.lambda$replaceMediaItem$49(this.f3923l, mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$addMediaItem$37(this.f3923l, mediaSessionImpl, controllerInfo, i10);
        }
    }
}
