package androidx.appcompat.view.menu;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.core.view.b3;
import androidx.core.view.r2;
import androidx.core.view.w2;
import androidx.work.impl.WorkDatabase;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.internal.cast.b1;
import com.google.android.gms.internal.cast.c1;
import com.google.android.gms.internal.cast.l6;
import com.google.android.gms.internal.cast.m5;
import com.google.android.gms.internal.cast.v0;
import com.google.android.gms.internal.cast.z0;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1292i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1293l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f1294m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f1295n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f1296o;

    public /* synthetic */ h(int i10, Object obj, Object obj2, Object obj3, Object obj4, boolean z) {
        this.f1292i = i10;
        this.f1293l = obj;
        this.f1294m = obj2;
        this.f1295n = obj3;
        this.f1296o = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        androidx.work.impl.model.p pVarK;
        int i10 = this.f1292i;
        int i11 = 2;
        Object obj = this.f1296o;
        Object obj2 = this.f1295n;
        Object obj3 = this.f1294m;
        Object obj4 = this.f1293l;
        boolean z = false;
        switch (i10) {
            case 0:
                k kVar = (k) ((a8.e) obj).f183l;
                t tVar = (t) obj3;
                i iVar = (i) obj4;
                if (iVar != null) {
                    kVar.J = true;
                    iVar.f1311b.c(false);
                    kVar.J = false;
                }
                if (tVar.isEnabled() && tVar.hasSubMenu()) {
                    ((q) obj2).q(tVar, null, 4);
                    return;
                }
                return;
            case 1:
                w2.g((View) obj4, (b3) obj3, (r2) obj2);
                ((ValueAnimator) obj).start();
                return;
            case 2:
                androidx.work.impl.utils.futures.k kVar2 = (androidx.work.impl.utils.futures.k) obj2;
                UUID uuid = (UUID) obj4;
                String string = uuid.toString();
                m2.t tVarD = m2.t.d();
                String str = androidx.work.impl.utils.s.f7071c;
                StringBuilder sb2 = new StringBuilder("Updating progress for ");
                sb2.append(uuid);
                sb2.append(" (");
                m2.h hVar = (m2.h) obj3;
                sb2.append(hVar);
                sb2.append(")");
                tVarD.a(str, sb2.toString());
                WorkDatabase workDatabase = ((androidx.work.impl.utils.s) obj).f7072a;
                workDatabase.c();
                try {
                    pVarK = workDatabase.t().k(string);
                } finally {
                    try {
                    } finally {
                    }
                    break;
                }
                if (pVarK == null) {
                    throw new IllegalStateException("Calls to setProgressAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
                }
                if (pVarK.f6923b == 2) {
                    workDatabase.s().g(new androidx.work.impl.model.m(string, hVar));
                } else {
                    m2.t.d().g(str, "Ignoring setProgressAsync(...). WorkSpec (" + string + ") is not in a RUNNING state.");
                }
                kVar2.h(null);
                workDatabase.o();
                return;
            default:
                androidx.mediarouter.media.i0 i0Var = (androidx.mediarouter.media.i0) obj3;
                androidx.mediarouter.media.i0 i0Var2 = (androidx.mediarouter.media.i0) obj2;
                e0.h hVar2 = (e0.h) obj;
                c1 c1Var = ((v0) obj4).f13629a;
                c1Var.getClass();
                Logger logger = c1.f13207j;
                Set set = c1Var.f13209b;
                if (new HashSet(set).isEmpty()) {
                    logger.d("No need to prepare transfer without any callback", new Object[0]);
                    hVar2.a();
                    return;
                }
                if (i0Var.f4396l != 1) {
                    logger.d("No need to prepare transfer when transferring from local", new Object[0]);
                    hVar2.a();
                    return;
                }
                RemoteMediaClient remoteMediaClientD = c1Var.d();
                if (remoteMediaClientD == null || !remoteMediaClientD.hasMediaSession()) {
                    logger.d("No need to prepare transfer when there is no media session", new Object[0]);
                    hVar2.a();
                    return;
                }
                logger.d("Prepare route transfer for changing endpoint", new Object[0]);
                if (i0Var2.f4396l == 0) {
                    l6.a(m5.CAST_TRANSFER_TO_LOCAL_USED);
                    i11 = 1;
                } else if (CastDevice.getFromBundle(i0Var2.f4403s) == null) {
                    i11 = 3;
                }
                c1Var.f13212e = i11;
                c1Var.f13215h = hVar2;
                logger.d("notify transferring with type = %d", Integer.valueOf(i11));
                Iterator it = new HashSet(set).iterator();
                while (it.hasNext()) {
                    ((SessionTransferCallback) it.next()).onTransferring(c1Var.f13212e);
                }
                c1Var.f13216i = null;
                com.google.android.gms.tasks.h hVarZzf = remoteMediaClientD.zzf(null);
                z0 z0Var = new z0(c1Var, z);
                com.google.android.gms.tasks.t tVar2 = (com.google.android.gms.tasks.t) hVarZzf;
                tVar2.getClass();
                com.google.android.gms.tasks.s sVar = com.google.android.gms.tasks.j.f13856a;
                tVar2.c(sVar, z0Var);
                tVar2.b(sVar, new z0(c1Var, z));
                androidx.loader.content.j jVar = c1Var.f13210c;
                com.google.android.gms.common.internal.t.i(jVar);
                b1 b1Var = c1Var.f13211d;
                com.google.android.gms.common.internal.t.i(b1Var);
                jVar.postDelayed(b1Var, 20000L);
                return;
        }
    }

    public /* synthetic */ h(Object obj, Object obj2, Object obj3, Object obj4, int i10) {
        this.f1292i = i10;
        this.f1296o = obj;
        this.f1293l = obj2;
        this.f1294m = obj3;
        this.f1295n = obj4;
    }
}
