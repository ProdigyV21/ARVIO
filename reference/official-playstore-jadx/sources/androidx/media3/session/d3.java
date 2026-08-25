package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d3 implements MediaSessionStub.SessionTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ CommandButton f3957i;

    public /* synthetic */ d3(CommandButton commandButton) {
        this.f3957i = commandButton;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        this.f3957i.executePlayerAction((PlayerWrapper) obj);
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return MediaSessionStub.lambda$dispatchCustomCommandAsPredefinedCommand$25(this.f3957i, mediaSessionImpl, controllerInfo, i10);
    }
}
