package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.ConnectedControllersManager;
import androidx.media3.session.MediaNotificationManager;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.MediaSessionStub;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3934i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3935l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3936m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3937n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3938o;

    public /* synthetic */ c(ConnectedControllersManager connectedControllersManager, AtomicBoolean atomicBoolean, ConnectedControllersManager.ConnectedControllerRecord connectedControllerRecord, AtomicBoolean atomicBoolean2) {
        this.f3934i = 0;
        this.f3935l = connectedControllersManager;
        this.f3936m = atomicBoolean;
        this.f3938o = connectedControllerRecord;
        this.f3937n = atomicBoolean2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3934i) {
            case 0:
                ((ConnectedControllersManager) this.f3935l).lambda$flushCommandQueue$2((AtomicBoolean) this.f3936m, (ConnectedControllersManager.ConnectedControllerRecord) this.f3938o, (AtomicBoolean) this.f3937n);
                break;
            case 1:
                ((MediaLibraryServiceLegacyStub) this.f3935l).lambda$onSubscribe$1((MediaSession.ControllerInfo) this.f3936m, (Bundle) this.f3937n, (String) this.f3938o);
                break;
            case 2:
                ((MediaLibraryServiceLegacyStub) this.f3935l).lambda$onLoadItem$4((MediaSession.ControllerInfo) this.f3936m, (MediaBrowserServiceCompat.Result) this.f3937n, (String) this.f3938o);
                break;
            case 3:
                ((MediaLibrarySessionImpl) this.f3935l).lambda$onSubscribeOnHandler$2((com.google.common.util.concurrent.d1) this.f3936m, (MediaSession.ControllerInfo) this.f3937n, (String) this.f3938o);
                break;
            case 4:
                ((MediaNotificationManager) this.f3935l).lambda$onCustomAction$2((MediaController) this.f3936m, (String) this.f3937n, (Bundle) this.f3938o);
                break;
            case 5:
                ((MediaNotificationManager) this.f3935l).lambda$addSession$1((com.google.common.util.concurrent.d1) this.f3936m, (MediaNotificationManager.MediaControllerListener) this.f3937n, (MediaSession) this.f3938o);
                break;
            case 6:
                ((MediaSessionLegacyStub.ControllerLegacyCbForBroadcast) this.f3935l).lambda$updateQueue$0((AtomicInteger) this.f3936m, (List) this.f3937n, (ArrayList) this.f3938o);
                break;
            case 7:
                ((MediaSessionServiceLegacyStub) this.f3935l).lambda$onGetRoot$0((AtomicReference) this.f3936m, (MediaSession.ControllerInfo) this.f3937n, (ConditionVariable) this.f3938o);
                break;
            case 8:
                MediaSessionStub.lambda$handleSessionTaskWhenReady$16((MediaSessionImpl) this.f3935l, (com.google.common.util.concurrent.q1) this.f3936m, (Consumer) this.f3937n, (com.google.common.util.concurrent.d1) this.f3938o);
                break;
            case 9:
                ((MediaSessionStub) this.f3935l).lambda$connect$17((MediaSession.ControllerInfo) this.f3936m, (MediaSessionImpl) this.f3937n, (IMediaController) this.f3938o);
                break;
            default:
                MediaSessionStub.lambda$handleMediaItemsWhenReady$4((MediaSessionImpl) this.f3935l, (MediaSessionStub.MediaItemPlayerTask) this.f3936m, (MediaSession.ControllerInfo) this.f3937n, (List) this.f3938o);
                break;
        }
    }

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, Object obj4, int i10) {
        this.f3934i = i10;
        this.f3935l = obj;
        this.f3936m = obj2;
        this.f3937n = obj3;
        this.f3938o = obj4;
    }
}
