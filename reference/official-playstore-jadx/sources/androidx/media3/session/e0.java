package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaControllerImplBase;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e0 implements MediaControllerImplBase.RemoteSessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3960i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f3961l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f3962m;

    public /* synthetic */ e0(int i10, List list, MediaControllerImplBase mediaControllerImplBase) {
        this.f3960i = i10;
        this.f3961l = mediaControllerImplBase;
        this.f3962m = list;
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public final void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f3960i) {
            case 0:
                this.f3961l.lambda$addMediaItems$33(this.f3962m, iMediaSession, i10);
                break;
            default:
                this.f3961l.lambda$setMediaItems$26(this.f3962m, iMediaSession, i10);
                break;
        }
    }
}
