package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplLegacy;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d1 implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3952i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplLegacy f3953l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplLegacy.ControllerInfo f3954m;

    public /* synthetic */ d1(MediaControllerImplLegacy mediaControllerImplLegacy, MediaControllerImplLegacy.ControllerInfo controllerInfo, int i10) {
        this.f3952i = i10;
        this.f3953l = mediaControllerImplLegacy;
        this.f3954m = controllerInfo;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f3952i) {
            case 0:
                this.f3953l.lambda$updateControllerInfo$26(this.f3954m, (MediaController.Listener) obj);
                break;
            case 1:
                this.f3953l.lambda$updateControllerInfo$27(this.f3954m, (MediaController.Listener) obj);
                break;
            case 2:
                this.f3953l.lambda$updateControllerInfo$28(this.f3954m, (MediaController.Listener) obj);
                break;
            default:
                this.f3953l.lambda$updateControllerInfo$6(this.f3954m, (MediaController.Listener) obj);
                break;
        }
    }
}
