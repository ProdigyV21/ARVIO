package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaControllerImplBase;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class w implements MediaControllerImplBase.RemoteSessionTask, ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4181i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f4182l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f4183m;

    public /* synthetic */ w(MediaControllerImplBase mediaControllerImplBase, boolean z, int i10) {
        this.f4181i = i10;
        this.f4182l = mediaControllerImplBase;
        this.f4183m = z;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f4181i) {
            case 1:
                this.f4182l.lambda$setDeviceMuted$71(this.f4183m, (Player.Listener) obj);
                break;
            default:
                this.f4182l.lambda$setDeviceMuted$69(this.f4183m, (Player.Listener) obj);
                break;
        }
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4181i) {
            case 0:
                this.f4182l.lambda$setPlayWhenReady$14(this.f4183m, iMediaSession, i10);
                break;
            case 1:
            default:
                this.f4182l.lambda$setDeviceMuted$68(this.f4183m, iMediaSession, i10);
                break;
            case 2:
                this.f4182l.lambda$setShuffleModeEnabled$48(this.f4183m, iMediaSession, i10);
                break;
        }
    }
}
