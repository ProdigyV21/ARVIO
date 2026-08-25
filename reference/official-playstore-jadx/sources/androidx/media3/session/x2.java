package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class x2 implements MediaSessionStub.ControllerPlayerTask, MediaSessionStub.MediaItemPlayerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4205i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionStub f4206l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4207m;

    public /* synthetic */ x2(MediaSessionStub mediaSessionStub, int i10, int i11) {
        this.f4205i = i11;
        this.f4206l = mediaSessionStub;
        this.f4207m = i10;
    }

    @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
    public void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        switch (this.f4205i) {
            case 0:
                this.f4206l.lambda$seekToDefaultPositionWithMediaItemIndex$21(this.f4207m, playerWrapper, controllerInfo);
                break;
            default:
                this.f4206l.lambda$removeMediaItem$45(this.f4207m, playerWrapper, controllerInfo);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
    public void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        switch (this.f4205i) {
            case 1:
                this.f4206l.lambda$addMediaItemWithIndex$40(this.f4207m, playerWrapper, controllerInfo, list);
                break;
            case 2:
                this.f4206l.lambda$replaceMediaItem$50(this.f4207m, playerWrapper, controllerInfo, list);
                break;
            default:
                this.f4206l.lambda$addMediaItemsWithIndex$44(this.f4207m, playerWrapper, controllerInfo, list);
                break;
        }
    }
}
