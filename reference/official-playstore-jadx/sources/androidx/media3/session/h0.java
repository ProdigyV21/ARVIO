package androidx.media3.session;

import android.os.RemoteException;
import android.view.Surface;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaControllerImplBase;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h0 implements MediaControllerImplBase.RemoteSessionTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4002i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4003l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4004m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f4005n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4006o;

    public /* synthetic */ h0(Object obj, Object obj2, int i10, int i11, int i12) {
        this.f4002i = i12;
        this.f4003l = obj;
        this.f4006o = obj2;
        this.f4004m = i10;
        this.f4005n = i11;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((MediaSessionStub) this.f4003l).lambda$setVideoSurfaceWithSize$56((Surface) this.f4006o, this.f4004m, this.f4005n, (PlayerWrapper) obj);
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4002i) {
            case 0:
                ((MediaControllerImplBase) this.f4003l).lambda$setVideoSurfaceWithSize$74((Surface) this.f4006o, this.f4004m, this.f4005n, iMediaSession, i10);
                break;
            default:
                ((MediaControllerImplBase) this.f4003l).lambda$replaceMediaItems$41((List) this.f4006o, this.f4004m, this.f4005n, iMediaSession, i10);
                break;
        }
    }
}
