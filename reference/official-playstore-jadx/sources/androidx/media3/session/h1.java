package androidx.media3.session;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplLegacy;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h1 implements Consumer, MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4007i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f4008l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4009m;

    public /* synthetic */ h1(int i10, Object obj, boolean z) {
        this.f4007i = i10;
        this.f4009m = obj;
        this.f4008l = z;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f4007i) {
            case 0:
                ((MediaControllerImplLegacy.ControllerCompatCallback) this.f4009m).lambda$onCaptioningEnabledChanged$2(this.f4008l, (MediaController.Listener) obj);
                break;
            default:
                ((PlayerWrapper) obj).setAudioAttributes((AudioAttributes) this.f4009m, this.f4008l);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f4007i) {
            case 1:
                return MediaSessionStub.lambda$setMediaItemItemWithResetPositionForControllerInfo$33((MediaItem) this.f4009m, this.f4008l, mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$setMediaItemsWithResetPosition$34((List) this.f4009m, this.f4008l, mediaSessionImpl, controllerInfo, i10);
        }
    }
}
