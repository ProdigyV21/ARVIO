package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class u2 implements MediaSessionStub.ControllerPlayerTask, MediaSessionStub.MediaItemPlayerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MediaSessionStub f4170i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4171l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4172m;

    public /* synthetic */ u2(MediaSessionStub mediaSessionStub, int i10, int i11) {
        this.f4170i = mediaSessionStub;
        this.f4171l = i10;
        this.f4172m = i11;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        this.f4170i.lambda$onSurfaceSizeChanged$57(this.f4171l, this.f4172m, (PlayerWrapper) obj);
    }

    @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
    public void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        this.f4170i.lambda$removeMediaItems$46(this.f4171l, this.f4172m, playerWrapper, controllerInfo);
    }

    @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
    public void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        this.f4170i.lambda$replaceMediaItems$52(this.f4171l, this.f4172m, playerWrapper, controllerInfo, list);
    }
}
