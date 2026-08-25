package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import j$.util.Objects;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class z0 implements SessionManagerListener, com.google.android.gms.tasks.e, com.google.android.gms.tasks.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c1 f13702i;

    public /* synthetic */ z0(c1 c1Var, boolean z) {
        this.f13702i = c1Var;
    }

    @Override // com.google.android.gms.tasks.d
    public /* synthetic */ void onFailure(Exception exc) {
        c1 c1Var = this.f13702i;
        c1Var.getClass();
        c1.f13207j.w(exc, "Fail to store SessionState", new Object[0]);
        c1Var.c(100);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* synthetic */ void onSessionEnded(Session session, int i10) {
        Logger logger = c1.f13207j;
        Object[] objArr = {Integer.valueOf(i10)};
        Logger logger2 = c1.f13207j;
        logger2.d("onSessionEnded with error = %d", objArr);
        c1 c1Var = this.f13702i;
        int i11 = c1Var.f13212e;
        if (i11 == 0) {
            logger2.d("No need to notify transferred if the transfer type is unknown", new Object[0]);
        } else {
            SessionState sessionState = c1Var.f13216i;
            if (sessionState == null) {
                logger2.d("No need to notify with null sessionState", new Object[0]);
            } else {
                logger2.d("notify transferred with type = %d, sessionState = %s", Integer.valueOf(i11), c1Var.f13216i);
                Iterator it = new HashSet(c1Var.f13209b).iterator();
                while (it.hasNext()) {
                    ((SessionTransferCallback) it.next()).onTransferred(c1Var.f13212e, sessionState);
                }
            }
        }
        if (c1Var.f13212e == 2) {
            return;
        }
        c1Var.b();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionEnding(Session session) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionResumeFailed(Session session, int i10) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionResumed(Session session, boolean z) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionStartFailed(Session session, int i10) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarted(Session session, String str) {
        c1 c1Var = this.f13702i;
        Object[] objArr = {Integer.valueOf(c1Var.f13212e)};
        Logger logger = c1.f13207j;
        logger.d("onSessionStarted with transferType = %d", objArr);
        if (c1Var.f13213f && c1Var.f13208a.zzh() && c1Var.f13212e == 2) {
            if (c1Var.f13216i == null) {
                logger.d("skip restoring session state due to null SessionState", new Object[0]);
            } else {
                RemoteMediaClient remoteMediaClientD = c1Var.d();
                if (remoteMediaClientD == null) {
                    logger.d("skip restoring session state due to null RemoteMediaClient", new Object[0]);
                } else {
                    logger.d("resume SessionState to current session", new Object[0]);
                    remoteMediaClientD.zzg(c1Var.f13216i);
                }
            }
        }
        c1Var.b();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionSuspended(Session session, int i10) {
    }

    @Override // com.google.android.gms.tasks.e
    public /* synthetic */ void onSuccess(Object obj) {
        c1 c1Var = this.f13702i;
        c1Var.f13216i = (SessionState) obj;
        e0.h hVar = c1Var.f13215h;
        if (hVar != null) {
            hVar.a();
        }
    }

    public /* synthetic */ z0(c1 c1Var) {
        Objects.requireNonNull(c1Var);
        this.f13702i = c1Var;
    }
}
