package com.google.android.gms.internal.cast;

import android.os.Looper;
import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class c1 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Logger f13207j = new Logger("SessionTransController");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CastOptions f13208a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f13213f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public SessionManager f13214g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public e0.h f13215h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SessionState f13216i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f13209b = Collections.synchronizedSet(new HashSet());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f13212e = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final androidx.loader.content.j f13210c = new androidx.loader.content.j(Looper.getMainLooper(), 2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b1 f13211d = new b1(this, 1);

    public c1(CastOptions castOptions) {
        this.f13208a = castOptions;
    }

    public final void a(SessionTransferCallback sessionTransferCallback) {
        f13207j.d("register callback = %s", sessionTransferCallback);
        com.google.android.gms.common.internal.t.e("Must be called from the main thread.");
        this.f13209b.add(sessionTransferCallback);
    }

    public final void b() {
        androidx.loader.content.j jVar = this.f13210c;
        com.google.android.gms.common.internal.t.i(jVar);
        b1 b1Var = this.f13211d;
        com.google.android.gms.common.internal.t.i(b1Var);
        jVar.removeCallbacks(b1Var);
        this.f13212e = 0;
        this.f13216i = null;
    }

    public final void c(int i10) {
        e0.h hVar = this.f13215h;
        if (hVar != null) {
            hVar.f14996d = true;
            e0.k kVar = hVar.f14994b;
            if (kVar != null && kVar.f14999l.cancel(true)) {
                hVar.f14993a = null;
                hVar.f14994b = null;
                hVar.f14995c = null;
            }
        }
        f13207j.d("notify failed transfer with type = %d, reason = %d", Integer.valueOf(this.f13212e), Integer.valueOf(i10));
        Iterator it = new HashSet(this.f13209b).iterator();
        while (it.hasNext()) {
            ((SessionTransferCallback) it.next()).onTransferFailed(this.f13212e, i10);
        }
        b();
    }

    public final RemoteMediaClient d() {
        SessionManager sessionManager = this.f13214g;
        Logger logger = f13207j;
        if (sessionManager == null) {
            logger.d("skip transferring as SessionManager is null", new Object[0]);
            return null;
        }
        CastSession currentCastSession = sessionManager.getCurrentCastSession();
        if (currentCastSession != null) {
            return currentCastSession.getRemoteMediaClient();
        }
        logger.d("skip transferring as CastSession is null", new Object[0]);
        return null;
    }
}
