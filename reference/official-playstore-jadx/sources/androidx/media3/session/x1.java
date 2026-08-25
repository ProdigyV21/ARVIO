package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class x1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4200i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4201l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4202m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4203n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4204o;

    public /* synthetic */ x1(int i10, int i11, Object obj, Object obj2, Object obj3) {
        this.f4200i = i11;
        this.f4202m = obj;
        this.f4201l = i10;
        this.f4203n = obj2;
        this.f4204o = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4200i) {
            case 0:
                ((MediaNotificationManager) this.f4202m).lambda$updateNotification$4(this.f4201l, (MediaSession) this.f4203n, (MediaNotification) this.f4204o);
                break;
            case 1:
                ((MediaSessionLegacyStub.AnonymousClass2) this.f4202m).lambda$onSuccess$0(this.f4201l, (List) this.f4203n, (MediaSession.ControllerInfo) this.f4204o);
                break;
            default:
                ((MediaSessionStub.SessionTask) this.f4202m).run((MediaSessionImpl) this.f4203n, (MediaSession.ControllerInfo) this.f4204o, this.f4201l);
                break;
        }
    }

    public /* synthetic */ x1(MediaSessionStub.SessionTask sessionTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        this.f4200i = 2;
        this.f4202m = sessionTask;
        this.f4203n = mediaSessionImpl;
        this.f4204o = controllerInfo;
        this.f4201l = i10;
    }
}
