package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.zzr;

/* JADX INFO: loaded from: classes4.dex */
public final class o8 implements zzr, SessionManagerListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ androidx.core.provider.e f13522i;

    public /* synthetic */ o8(androidx.core.provider.e eVar) {
        this.f13522i = eVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionEnded(Session session, int i10) {
        androidx.appcompat.widget.f0 f0Var = new androidx.appcompat.widget.f0(9, 2);
        f0Var.f1567m = Integer.valueOf(i10);
        androidx.core.provider.e eVar = this.f13522i;
        f0Var.f1568n = Boolean.valueOf(((c0) eVar.f2142n).a());
        eVar.a(new k1(f0Var));
        eVar.c();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionEnding(Session session) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResumeFailed(Session session, int i10) {
        androidx.appcompat.widget.f0 f0Var = new androidx.appcompat.widget.f0(8, 2);
        f0Var.f1567m = Integer.valueOf(i10);
        k1 k1Var = new k1(f0Var);
        androidx.core.provider.e eVar = this.f13522i;
        eVar.a(k1Var);
        eVar.c();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* synthetic */ void onSessionResumed(Session session, boolean z) {
        k1 k1Var = new k1(new androidx.appcompat.widget.f0(4, 2));
        androidx.core.provider.e eVar = this.f13522i;
        eVar.a(k1Var);
        b bVar = (b) eVar.f2143o;
        com.google.android.gms.common.internal.t.i(bVar);
        bVar.a((CastSession) session);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResuming(Session session, String str) {
        k1 k1Var = new k1(new androidx.appcompat.widget.f0(7, 2));
        androidx.core.provider.e eVar = this.f13522i;
        eVar.a(k1Var);
        b bVar = (b) eVar.f2143o;
        com.google.android.gms.common.internal.t.i(bVar);
        bVar.a((CastSession) session);
        b bVar2 = (b) eVar.f2143o;
        com.google.android.gms.common.internal.t.i(bVar2);
        String str2 = bVar2.k;
        if (str2 == null) {
            bVar2.k = str;
        } else {
            if (TextUtils.equals(str, str2)) {
                return;
            }
            bVar2.b(4);
        }
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStartFailed(Session session, int i10) {
        androidx.appcompat.widget.f0 f0Var = new androidx.appcompat.widget.f0(5, 2);
        f0Var.f1567m = Integer.valueOf(i10);
        k1 k1Var = new k1(f0Var);
        androidx.core.provider.e eVar = this.f13522i;
        eVar.a(k1Var);
        eVar.c();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarted(Session session, String str) {
        k1 k1Var = new k1(new androidx.appcompat.widget.f0(4, 2));
        androidx.core.provider.e eVar = this.f13522i;
        eVar.a(k1Var);
        b bVar = (b) eVar.f2143o;
        com.google.android.gms.common.internal.t.i(bVar);
        bVar.a((CastSession) session);
        b bVar2 = (b) eVar.f2143o;
        com.google.android.gms.common.internal.t.i(bVar2);
        String str2 = bVar2.k;
        if (str2 == null) {
            bVar2.k = str;
        } else {
            if (TextUtils.equals(str, str2)) {
                return;
            }
            bVar2.b(4);
        }
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarting(Session session) {
        CastSession castSession = (CastSession) session;
        androidx.appcompat.widget.f0 f0Var = new androidx.appcompat.widget.f0(2, 2);
        androidx.core.provider.e eVar = this.f13522i;
        f0Var.f1568n = Boolean.valueOf(((c0) eVar.f2142n).a());
        eVar.a(new k1(f0Var));
        b bVar = (b) eVar.f2143o;
        com.google.android.gms.common.internal.t.i(bVar);
        bVar.a(castSession);
        castSession.zzb((o8) eVar.f2144p);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionSuspended(Session session, int i10) {
        androidx.appcompat.widget.f0 f0Var = new androidx.appcompat.widget.f0(6, 2);
        f0Var.f1567m = Integer.valueOf(i10);
        k1 k1Var = new k1(f0Var);
        androidx.core.provider.e eVar = this.f13522i;
        eVar.a(k1Var);
        b bVar = (b) eVar.f2143o;
        com.google.android.gms.common.internal.t.i(bVar);
        bVar.a((CastSession) session);
    }

    @Override // com.google.android.gms.cast.framework.zzr
    public void zza() {
        this.f13522i.a(new k1(new androidx.appcompat.widget.f0(3, 2)));
    }

    @Override // com.google.android.gms.cast.framework.zzr
    public void zzb(String str, long j10, int i10, long j11, long j12) {
        b bVarB = this.f13522i.b();
        i1 i1Var = new i1(str);
        i1Var.f13301b = j10;
        i1Var.f13302c = i10;
        i1Var.f13303d = j11;
        i1Var.f13304e = j12;
        j1 j1Var = new j1(i1Var);
        j1Var.f13336f = bVarB.f13179h;
        bVarB.f13175d.add(j1Var);
    }

    @Override // com.google.android.gms.cast.framework.zzr
    public void zzc(MediaStatus mediaStatus) {
        if (mediaStatus == null) {
            return;
        }
        b bVarB = this.f13522i.b();
        androidx.leanback.widget.r rVar = new androidx.leanback.widget.r(mediaStatus);
        androidx.appcompat.app.g1 g1Var = new androidx.appcompat.app.g1();
        g1Var.f1051a = rVar.f3109a;
        g1Var.f1052b = System.currentTimeMillis();
        androidx.appcompat.app.g1 g1Var2 = bVarB.f13183m;
        if (g1Var2 == null || g1Var2.f1051a != 2) {
            g1Var.f1053c = bVarB.f13179h;
            bVarB.f13183m = g1Var;
        }
    }

    @Override // com.google.android.gms.cast.framework.zzr
    public void zzd() {
        this.f13522i.b().f13191u++;
    }
}
