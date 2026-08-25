package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.session.ConnectedControllersManager;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3908i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3909l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3910m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3911n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3912o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f3913p;

    public /* synthetic */ a(ConnectedControllersManager connectedControllersManager, ConnectedControllersManager.AsyncCommand asyncCommand, AtomicBoolean atomicBoolean, ConnectedControllersManager.ConnectedControllerRecord connectedControllerRecord, AtomicBoolean atomicBoolean2) {
        this.f3908i = 0;
        this.f3909l = connectedControllersManager;
        this.f3910m = asyncCommand;
        this.f3911n = atomicBoolean;
        this.f3913p = connectedControllerRecord;
        this.f3912o = atomicBoolean2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3908i) {
            case 0:
                ((ConnectedControllersManager) this.f3909l).lambda$flushCommandQueue$3((ConnectedControllersManager.AsyncCommand) this.f3910m, (AtomicBoolean) this.f3911n, (ConnectedControllersManager.ConnectedControllerRecord) this.f3913p, (AtomicBoolean) this.f3912o);
                break;
            case 1:
                ((MediaLibraryServiceLegacyStub) this.f3909l).lambda$createMediaItemsToBrowserItemsAsyncFunction$11((AtomicInteger) this.f3910m, (com.google.common.collect.h1) this.f3911n, (ArrayList) this.f3912o, (com.google.common.util.concurrent.q1) this.f3913p);
                break;
            case 2:
                ((MediaLibraryServiceLegacyStub) this.f3909l).lambda$onGetRoot$0((AtomicReference) this.f3910m, (MediaSession.ControllerInfo) this.f3911n, (MediaLibraryService.LibraryParams) this.f3912o, (ConditionVariable) this.f3913p);
                break;
            case 3:
                ((MediaNotificationManager) this.f3909l).lambda$onCustomAction$3((MediaSession) this.f3910m, (String) this.f3911n, (Bundle) this.f3912o, (MediaController) this.f3913p);
                break;
            default:
                SessionToken.lambda$createSessionToken$0((Context) this.f3909l, (String) this.f3910m, (MediaSessionCompat.Token) this.f3911n, (MediaControllerCompat) this.f3912o, (com.google.common.util.concurrent.q1) this.f3913p);
                break;
        }
    }

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i10) {
        this.f3908i = i10;
        this.f3909l = obj;
        this.f3910m = obj2;
        this.f3911n = obj3;
        this.f3912o = obj4;
        this.f3913p = obj5;
    }
}
