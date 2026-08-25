package androidx.media3.session;

import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.legacy.MediaSessionManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3991i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f3992l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3993m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3994n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3995o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f3996p;

    public /* synthetic */ g1(MediaControllerImplLegacy mediaControllerImplLegacy, AtomicInteger atomicInteger, List list, ArrayList arrayList, int i10) {
        this.f3993m = mediaControllerImplLegacy;
        this.f3994n = atomicInteger;
        this.f3995o = list;
        this.f3996p = arrayList;
        this.f3992l = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3991i) {
            case 0:
                ((MediaControllerImplLegacy) this.f3993m).lambda$addQueueItems$4((AtomicInteger) this.f3994n, (List) this.f3995o, (ArrayList) this.f3996p, this.f3992l);
                break;
            default:
                ((MediaSessionLegacyStub) this.f3993m).lambda$dispatchSessionTaskWithSessionCommandInternal$22((SessionCommand) this.f3994n, this.f3992l, (MediaSessionManager.RemoteUserInfo) this.f3995o, (MediaSessionLegacyStub.SessionTask) this.f3996p);
                break;
        }
    }

    public /* synthetic */ g1(MediaSessionLegacyStub mediaSessionLegacyStub, SessionCommand sessionCommand, int i10, MediaSessionManager.RemoteUserInfo remoteUserInfo, MediaSessionLegacyStub.SessionTask sessionTask) {
        this.f3993m = mediaSessionLegacyStub;
        this.f3994n = sessionCommand;
        this.f3992l = i10;
        this.f3995o = remoteUserInfo;
        this.f3996p = sessionTask;
    }
}
