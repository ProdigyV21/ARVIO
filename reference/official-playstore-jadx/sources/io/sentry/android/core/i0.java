package io.sentry.android.core;

import com.google.android.gms.cast.MediaTrack;
import io.sentry.f2;
import io.sentry.j5;
import io.sentry.k5;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 implements j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ k0 f16518a;

    public i0(k0 k0Var) {
        this.f16518a = k0Var;
    }

    @Override // io.sentry.android.core.j0
    public final boolean a(io.sentry.hints.c cVar) {
        return cVar instanceof io.sentry.hints.a;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:18|(1:20)(4:21|(1:23)|134|135)|184|24|(5:26|187|27|185|28)(2:42|43)|44|(1:46)|(0)(2:53|(2:57|128)(4:58|(1:60)(6:62|(4:65|(1:221)(6:219|68|(6:70|(2:71|(2:73|(1:227)(1:76))(2:226|77))|75|78|(2:80|225)(2:81|224)|82)|223|83|222)|220|63)|218|84|(0)(1:87)|(0)(8:90|(10:93|(3:95|(6:97|(1:99)(1:100)|101|(1:103)|104|197)(1:198)|105)|196|106|(4:109|(2:111|200)(1:201)|112|107)|199|113|(2:115|194)(1:195)|116|91)|193|117|(1:119)(1:120)|121|(2:123|(1:125))(1:127)|126))|61|(0)))|135) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a4, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a5, code lost:
    
        r13 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01ea  */
    @Override // io.sentry.android.core.j0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(io.sentry.j5 r35, io.sentry.hints.c r36, io.sentry.hints.c r37) {
        /*
            Method dump skipped, instruction units count: 1242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.i0.b(io.sentry.j5, io.sentry.hints.c, io.sentry.hints.c):void");
    }

    @Override // io.sentry.android.core.j0
    public final void c(j5 j5Var, io.sentry.hints.c cVar, io.sentry.hints.c cVar2) {
        ArrayList arrayList;
        boolean zEquals = cVar2 instanceof io.sentry.hints.a ? "anr_background".equals(((io.sentry.hints.a) cVar2).h()) : false;
        if (j5Var.f17991r == null) {
            j5Var.f17991r = "java";
        }
        if (j5Var.d() != null) {
            return;
        }
        io.sentry.protocol.m mVar = new io.sentry.protocol.m();
        if (cVar.b()) {
            mVar.f17638i = "AppExitInfo";
        } else {
            mVar.f17638i = "HistoricalAppExitInfo";
        }
        ApplicationNotResponding applicationNotResponding = new ApplicationNotResponding(zEquals ? "Background ANR" : "ANR", Thread.currentThread());
        ArrayList<io.sentry.protocol.d0> arrayListE = j5Var.e();
        if (arrayListE != null) {
            for (io.sentry.protocol.d0 d0Var : arrayListE) {
                String str = d0Var.f17558m;
                if (str != null && str.equals(MediaTrack.ROLE_MAIN)) {
                    break;
                }
            }
            d0Var = null;
        } else {
            d0Var = null;
        }
        if (d0Var == null) {
            d0Var = new io.sentry.protocol.d0();
            d0Var.f17564s = new io.sentry.protocol.b0();
        }
        this.f16518a.f16659n.getClass();
        io.sentry.protocol.b0 b0Var = d0Var.f17564s;
        if (b0Var == null) {
            arrayList = new ArrayList(0);
        } else {
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(k5.c(applicationNotResponding, mVar, d0Var.f17556i, b0Var.f17545i, true));
            arrayList = arrayList2;
        }
        j5Var.D = new f2(arrayList);
    }
}
