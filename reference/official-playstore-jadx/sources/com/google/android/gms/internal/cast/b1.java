package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13192i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ c1 f13193l;

    public /* synthetic */ b1(c1 c1Var, int i10) {
        this.f13192i = i10;
        this.f13193l = c1Var;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        int i10 = this.f13192i;
        c1 c1Var = this.f13193l;
        switch (i10) {
            case 0:
                c1Var.getClass();
                z0 z0Var = new z0(c1Var);
                SessionManager sessionManager = c1Var.f13214g;
                com.google.android.gms.common.internal.t.i(sessionManager);
                sessionManager.addSessionManagerListener(z0Var, CastSession.class);
                break;
            default:
                c1.f13207j.i("transfer with type = %d has timed out", Integer.valueOf(c1Var.f13212e));
                c1Var.c(101);
                break;
        }
    }
}
