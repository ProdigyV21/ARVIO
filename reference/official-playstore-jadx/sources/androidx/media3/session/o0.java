package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.MediaSessionLegacyStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o0 implements Consumer, MediaSessionImpl.RemoteControllerTask, MediaSessionLegacyStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f4091i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f4092l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4093m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4094n;

    public /* synthetic */ o0(SessionPositionInfo sessionPositionInfo, boolean z, boolean z5, MediaSession.ControllerInfo controllerInfo) {
        this.f4093m = sessionPositionInfo;
        this.f4091i = z;
        this.f4092l = z5;
        this.f4094n = controllerInfo;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((MediaControllerImplBase) this.f4093m).lambda$onExtrasChanged$119((Bundle) this.f4094n, this.f4091i, this.f4092l, (MediaController.Listener) obj);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        MediaSessionImpl.lambda$dispatchOnPeriodicSessionPositionInfoChanged$22((SessionPositionInfo) this.f4093m, this.f4091i, this.f4092l, (MediaSession.ControllerInfo) this.f4094n, controllerCb, i10);
    }

    public /* synthetic */ o0(Object obj, boolean z, Object obj2, boolean z5) {
        this.f4093m = obj;
        this.f4094n = obj2;
        this.f4091i = z;
        this.f4092l = z5;
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        ((MediaSessionLegacyStub) this.f4093m).lambda$handleMediaRequest$26((MediaItem) this.f4094n, this.f4091i, this.f4092l, controllerInfo);
    }
}
