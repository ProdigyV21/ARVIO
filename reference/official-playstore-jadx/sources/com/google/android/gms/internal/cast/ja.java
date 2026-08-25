package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.internal.zza;
import com.google.android.gms.cast.internal.Logger;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class ja implements k, gb, sb, com.google.android.gms.tasks.c, zza, com.google.android.gms.common.api.internal.t, SessionManagerListener {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k5 f13363m = new k5(24);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13364i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f13365l;

    public /* synthetic */ ja(Object obj, int i10) {
        this.f13364i = i10;
        this.f13365l = obj;
    }

    @Override // com.google.android.gms.internal.cast.gb
    public boolean a(Class cls) {
        for (int i10 = 0; i10 < 2; i10++) {
            if (((gb[]) this.f13365l)[i10].a(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) throws RemoteException {
        l3 l3Var = new l3((com.google.android.gms.internal.auth.f0) this.f13365l, (com.google.android.gms.tasks.i) obj2);
        t3 t3Var = (t3) ((v3) obj).getService();
        Parcel parcelZza = t3Var.zza();
        a1.d(parcelZza, l3Var);
        t3Var.zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.gb
    public fb b(Class cls) {
        for (int i10 = 0; i10 < 2; i10++) {
            gb gbVar = ((gb[]) this.f13365l)[i10];
            if (gbVar.a(cls)) {
                return gbVar.b(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    public void c(int i10, List list) {
        ia iaVar = (ia) this.f13365l;
        int i11 = 0;
        if (!(list instanceof ab)) {
            while (i11 < list.size()) {
                iaVar.G(i10, (String) list.get(i11));
                i11++;
            }
            return;
        }
        ab abVar = (ab) list;
        while (i11 < list.size()) {
            Object objZza = abVar.zza();
            if (objZza instanceof String) {
                iaVar.G(i10, (String) objZza);
            } else {
                iaVar.H(i10, (ha) objZza);
            }
            i11++;
        }
    }

    public void d(int i10, List list) {
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((ia) this.f13365l).H(i10, (ha) list.get(i11));
        }
    }

    public void e(int i10, int i11) {
        ((ia) this.f13365l).D(i10, i11);
    }

    public void f(int i10, long j10) {
        ((ia) this.f13365l).E(i10, j10);
    }

    public void g(int i10, long j10) {
        ((ia) this.f13365l).F(i10, j10);
    }

    public void h(float f10, int i10) {
        ((ia) this.f13365l).D(i10, Float.floatToRawIntBits(f10));
    }

    public void i(int i10, double d4) {
        ((ia) this.f13365l).F(i10, Double.doubleToRawLongBits(d4));
    }

    public void j(int i10, int i11) {
        ((ia) this.f13365l).u(i10, i11);
    }

    public void k(int i10, long j10) {
        ((ia) this.f13365l).E(i10, j10);
    }

    public void l(int i10, int i11) {
        ((ia) this.f13365l).u(i10, i11);
    }

    public void m(int i10, long j10) {
        ((ia) this.f13365l).F(i10, j10);
    }

    public void n(int i10, int i11) {
        ((ia) this.f13365l).D(i10, i11);
    }

    public void o(int i10, boolean z) throws zzxo {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i11;
        ia iaVar = (ia) this.f13365l;
        iaVar.J(i10 << 3);
        int i12 = iaVar.f13330p;
        try {
            i11 = i12 + 1;
        } catch (IndexOutOfBoundsException e5) {
            indexOutOfBoundsException = e5;
        }
        try {
            iaVar.f13328n[i12] = z ? (byte) 1 : (byte) 0;
            iaVar.f13330p = i11;
        } catch (IndexOutOfBoundsException e6) {
            indexOutOfBoundsException = e6;
            i12 = i11;
            throw new zzxo(i12, iaVar.f13329o, 1, indexOutOfBoundsException);
        }
    }

    @Override // com.google.android.gms.tasks.c
    public void onComplete(com.google.android.gms.tasks.h hVar) {
        CastOptions castOptions;
        x0 x0Var = (x0) this.f13365l;
        Logger logger = x0.f13672j;
        boolean z = x0Var.f13677g;
        if (hVar.h()) {
            Bundle bundle = (Bundle) hVar.f();
            boolean z5 = bundle != null && bundle.containsKey("com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED");
            logger.d("The module-to-client output switcher flag %s", true != z5 ? "not existed" : "existed");
            if (z5) {
                x0Var.f13678h = bundle.getBoolean("com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED");
            }
        }
        boolean z10 = x0Var.f13678h;
        if (x0Var.f13673c == null || (castOptions = x0Var.f13674d) == null) {
            return;
        }
        boolean zZzd = castOptions.zzd();
        boolean zZzc = castOptions.zzc();
        boolean z11 = z10 && castOptions.zzf();
        androidx.mediarouter.media.m0 m0Var = new androidx.mediarouter.media.m0();
        int i10 = Build.VERSION.SDK_INT;
        m0Var.f4441a = i10 >= 30;
        if (i10 >= 30) {
            m0Var.f4441a = z11;
        }
        if (i10 >= 30) {
            m0Var.f4443c = zZzd;
        }
        if (i10 >= 30) {
            m0Var.f4442b = zZzc;
        }
        boolean zZzi = castOptions.zzi();
        if (i10 >= 30) {
            m0Var.f4444d = zZzi;
        }
        androidx.mediarouter.media.o0 o0Var = new androidx.mediarouter.media.o0(m0Var);
        androidx.mediarouter.media.k0.b();
        androidx.mediarouter.media.f fVarC = androidx.mediarouter.media.k0.c();
        androidx.mediarouter.media.o0 o0Var2 = fVarC.f4346v;
        fVarC.f4346v = o0Var;
        if (fVarC.h()) {
            if (fVarC.f4343s == null) {
                androidx.mediarouter.media.l lVar = new androidx.mediarouter.media.l(fVarC.f4333h, new androidx.mediarouter.media.b(fVarC));
                fVarC.f4343s = lVar;
                fVarC.a(lVar, true);
                fVarC.n();
            }
            androidx.mediarouter.media.l lVar2 = fVarC.f4343s;
            boolean z12 = o0Var.f4469d;
            lVar2.z = z12;
            lVar2.j();
            androidx.mediarouter.media.m1 m1Var = fVarC.f4328c;
            m1Var.f4450f = z12;
            m1Var.f4447c.post(m1Var.f4453i);
            if ((o0Var2 != null && o0Var2.f4468c) != o0Var.f4468c) {
                androidx.mediarouter.media.l lVar3 = fVarC.f4343s;
                lVar3.f4497o = fVarC.B;
                if (!lVar3.f4498p) {
                    lVar3.f4498p = true;
                    lVar3.f4495m.sendEmptyMessage(2);
                }
            }
        } else {
            androidx.mediarouter.media.l lVar4 = fVarC.f4343s;
            if (lVar4 != null) {
                fVarC.k(lVar4);
                fVarC.f4343s = null;
                androidx.mediarouter.media.m1 m1Var2 = fVarC.f4328c;
                m1Var2.f4447c.post(m1Var2.f4453i);
            }
        }
        fVarC.f4326a.b(769, o0Var);
        logger.i("media transfer = %b, session transfer = %b, transfer to local = %b, in-app output switcher = %b", Boolean.valueOf(z), Boolean.valueOf(z11), Boolean.valueOf(zZzd), Boolean.valueOf(zZzc));
        c1 c1Var = x0Var.f13676f;
        if (c1Var != null) {
            c1Var.f13213f = z && z11;
        }
        if (z && z11) {
            l6.a(m5.CAST_OUTPUT_SWITCHER_ENABLED);
        }
        if (zZzd) {
            l6.a(m5.CAST_TRANSFER_TO_LOCAL_ENABLED);
        }
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* synthetic */ void onSessionEnded(Session session, int i10) {
        i5 i5Var = (i5) this.f13365l;
        i5Var.f13323h = (CastSession) session;
        i5Var.b(i10);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* synthetic */ void onSessionEnding(Session session) {
        ((i5) this.f13365l).f13323h = (CastSession) session;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* synthetic */ void onSessionResumeFailed(Session session, int i10) {
        i5 i5Var = (i5) this.f13365l;
        i5Var.f13323h = (CastSession) session;
        i5Var.b(i10);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResumed(Session session, boolean z) {
        Logger logger = i5.f13315j;
        i5.f13315j.d("onSessionResumed with wasSuspended = %b", Boolean.valueOf(z));
        i5 i5Var = (i5) this.f13365l;
        i5Var.f13323h = (CastSession) session;
        i5Var.e();
        com.google.android.gms.common.internal.t.i(i5Var.f13322g);
        l5 l5Var = i5Var.f13318c;
        j5 j5Var = i5Var.f13322g;
        z4 z4Var = i5Var.f13316a;
        b6 b6VarB = l5Var.b(j5Var);
        v5 v5VarO = w5.o(b6VarB.f());
        v5VarO.c();
        ((w5) v5VarO.f13530l).r(z);
        b6VarB.c();
        ((c6) b6VarB.f13530l).x((w5) v5VarO.e());
        z4Var.a((c6) b6VarB.e(), 227);
        i5Var.a();
        i5Var.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f3  */
    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onSessionResuming(com.google.android.gms.cast.framework.Session r11, java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.ja.onSessionResuming(com.google.android.gms.cast.framework.Session, java.lang.String):void");
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* synthetic */ void onSessionStartFailed(Session session, int i10) {
        i5 i5Var = (i5) this.f13365l;
        i5Var.f13323h = (CastSession) session;
        i5Var.b(i10);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarted(Session session, String str) {
        i5.f13315j.d("onSessionStarted with sessionId = %s", str);
        i5 i5Var = (i5) this.f13365l;
        i5Var.f13323h = (CastSession) session;
        i5Var.e();
        j5 j5Var = i5Var.f13322g;
        j5Var.f13348f = str;
        i5Var.f13316a.a((c6) i5Var.f13318c.b(j5Var).e(), 222);
        i5Var.a();
        i5Var.c();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarting(Session session) {
        Logger logger = i5.f13315j;
        logger.d("onSessionStarting", new Object[0]);
        i5 i5Var = (i5) this.f13365l;
        i5Var.f13323h = (CastSession) session;
        if (i5Var.f13322g != null) {
            logger.w("Start a session while there's already an active session. Create a new one.", new Object[0]);
        }
        i5Var.d();
        j5 j5Var = i5Var.f13322g;
        l5 l5Var = i5Var.f13318c;
        z4 z4Var = i5Var.f13316a;
        b6 b6VarB = l5Var.b(j5Var);
        if (j5Var.f13357p == 1) {
            v5 v5VarO = w5.o(b6VarB.f());
            v5VarO.c();
            ((w5) v5VarO.f13530l).t(17);
            b6VarB.g((w5) v5VarO.e());
        }
        z4Var.a((c6) b6VarB.e(), 221);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionSuspended(Session session, int i10) {
        Logger logger = i5.f13315j;
        i5.f13315j.d("onSessionSuspended with reason = %d", Integer.valueOf(i10));
        i5 i5Var = (i5) this.f13365l;
        i5Var.f13323h = (CastSession) session;
        i5Var.e();
        com.google.android.gms.common.internal.t.i(i5Var.f13322g);
        i5Var.f13316a.a(i5Var.f13318c.a(i5Var.f13322g, i10), 225);
        i5Var.a();
        i5Var.f13320e.removeCallbacks(i5Var.f13319d);
    }

    public void p(int i10, ha haVar) {
        ((ia) this.f13365l).H(i10, haVar);
    }

    public void q(int i10, int i11) {
        ((ia) this.f13365l).C(i10, i11);
    }

    public void r(int i10, int i11) {
        ((ia) this.f13365l).C(i10, (i11 >> 31) ^ (i11 + i11));
    }

    public void s(int i10, long j10) {
        ((ia) this.f13365l).E(i10, (j10 >> 63) ^ (j10 + j10));
    }

    public void t(int i10, Object obj, rb rbVar) {
        ia iaVar = (ia) this.f13365l;
        ba baVar = (ba) obj;
        iaVar.r(i10, 2);
        iaVar.J(baVar.c(rbVar));
        rbVar.e(baVar, this);
    }

    public void u(int i10, Object obj, rb rbVar) {
        ia iaVar = (ia) this.f13365l;
        iaVar.r(i10, 3);
        rbVar.e((ba) obj, this);
        iaVar.r(i10, 4);
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public void zza(Bitmap bitmap) {
        switch (this.f13364i) {
            case 4:
                if (bitmap != null) {
                    ((o1) this.f13365l).f13504a.setImageBitmap(bitmap);
                }
                break;
            default:
                if (bitmap != null) {
                    r1 r1Var = (r1) this.f13365l;
                    View view = r1Var.f13578d;
                    ImageView imageView = r1Var.f13575a;
                    if (view != null) {
                        view.setVisibility(4);
                    }
                    imageView.setVisibility(0);
                    imageView.setImageBitmap(bitmap);
                    p1 p1Var = r1Var.f13580f;
                    if (p1Var != null) {
                        p1Var.zza();
                    }
                }
                break;
        }
    }

    public ja() {
        this.f13364i = 2;
        int i10 = da.f13235a;
        ja jaVar = new ja(new gb[]{k5.I, f13363m}, 1);
        Charset charset = ya.f13698a;
        this.f13365l = jaVar;
    }

    public ja(ia iaVar) {
        this.f13364i = 0;
        Charset charset = ya.f13698a;
        this.f13365l = iaVar;
        iaVar.f13327m = this;
    }
}
