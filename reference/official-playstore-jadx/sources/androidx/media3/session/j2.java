package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j2 implements MediaSessionImpl.RemoteControllerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4042i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f4043l;

    public /* synthetic */ j2(long j10, int i10) {
        this.f4042i = i10;
        this.f4043l = j10;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((PlayerWrapper) obj).seekTo(this.f4043l);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f4042i) {
            case 0:
                controllerCb.onSeekForwardIncrementChanged(i10, this.f4043l);
                break;
            default:
                controllerCb.onSeekBackIncrementChanged(i10, this.f4043l);
                break;
        }
    }
}
