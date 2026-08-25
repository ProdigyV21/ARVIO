package com.google.firebase.installations;

import android.text.TextUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14369i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ b f14370l;

    public /* synthetic */ a(b bVar, int i10) {
        this.f14369i = i10;
        this.f14370l = bVar;
    }

    /* JADX WARN: Finally extract failed */
    private final void a() {
        o5.b bVarU;
        b bVar = this.f14370l;
        synchronized (b.f14371m) {
            try {
                z4.g gVar = bVar.f14372a;
                gVar.a();
                io.sentry.internal.debugmeta.c cVarV = io.sentry.internal.debugmeta.c.v(gVar.f23184a);
                try {
                    bVarU = bVar.f14374c.U();
                    int i10 = bVarU.f20783b;
                    boolean z = true;
                    if (i10 != 2 && i10 != 1) {
                        z = false;
                    }
                    if (z) {
                        String strD = bVar.d(bVarU);
                        fi.iki.elonen.f fVar = bVar.f14374c;
                        o5.a aVarA = bVarU.a();
                        aVarA.f20775b = strD;
                        aVarA.f20776c = 3;
                        bVarU = aVarA.a();
                        fVar.R(bVarU);
                    }
                    if (cVarV != null) {
                        cVarV.D();
                    }
                } catch (Throwable th) {
                    if (cVarV != null) {
                        cVarV.D();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        bVar.g(bVarU);
        bVar.f14380i.execute(new a(bVar, 1));
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.lang.Runnable
    public final void run() {
        o5.b bVarU;
        o5.b bVarE;
        switch (this.f14369i) {
            case 0:
                a();
                return;
            default:
                b bVar = this.f14370l;
                synchronized (b.f14371m) {
                    try {
                        z4.g gVar = bVar.f14372a;
                        gVar.a();
                        io.sentry.internal.debugmeta.c cVarV = io.sentry.internal.debugmeta.c.v(gVar.f23184a);
                        try {
                            bVarU = bVar.f14374c.U();
                            if (cVarV != null) {
                                cVarV.D();
                            }
                        } catch (Throwable th) {
                            if (cVarV != null) {
                                cVarV.D();
                            }
                            throw th;
                        }
                    } finally {
                    }
                }
                try {
                    int i10 = bVarU.f20783b;
                    if (i10 == 5) {
                        bVarE = bVar.e(bVarU);
                    } else {
                        if (i10 == 3) {
                            bVarE = bVar.e(bVarU);
                        } else {
                            h hVar = bVar.f14375d;
                            hVar.getClass();
                            if (!TextUtils.isEmpty(bVarU.f20784c) && bVarU.f20787f + bVarU.f20786e >= TimeUnit.MILLISECONDS.toSeconds(hVar.f14390a.currentTimeMillis()) + h.f14387b) {
                                return;
                            }
                            bVarE = bVar.b(bVarU);
                        }
                    }
                    bVar.c(bVarE);
                    bVar.i(bVarU, bVarE);
                    if (bVarE.f20783b == 4) {
                        bVar.h(bVarE.f20782a);
                    }
                    int i11 = bVarE.f20783b;
                    if (i11 == 5) {
                        bVar.f(new FirebaseInstallationsException());
                        return;
                    } else if (i11 == 2 || i11 == 1) {
                        bVar.f(new IOException("Installation ID could not be validated with the Firebase servers (maybe it was deleted). Firebase Installations will need to create a new Installation ID and auth token. Please retry your last request."));
                        return;
                    } else {
                        bVar.g(bVarE);
                        return;
                    }
                } catch (FirebaseInstallationsException e5) {
                    bVar.f(e5);
                    return;
                }
        }
    }
}
