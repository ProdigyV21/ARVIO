package com.google.android.gms.internal.cast;

import android.os.Looper;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class v0 implements androidx.mediarouter.media.d0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Logger f13628c = new Logger("MediaRouterOPTListener");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c1 f13629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.loader.content.j f13630b;

    public v0(c1 c1Var) {
        com.google.android.gms.common.internal.t.i(c1Var);
        this.f13629a = c1Var;
        this.f13630b = new androidx.loader.content.j(Looper.getMainLooper(), 2);
    }

    @Override // androidx.mediarouter.media.d0
    public final e0.k a(androidx.mediarouter.media.i0 i0Var, androidx.mediarouter.media.i0 i0Var2) {
        f13628c.d("Prepare transfer from Route(%s) to Route(%s)", i0Var, i0Var2);
        e0.h hVar = new e0.h();
        hVar.f14995c = new e0.l();
        e0.k kVar = new e0.k(hVar);
        hVar.f14994b = kVar;
        hVar.f14993a = u0.class;
        try {
            try {
                hVar.f14993a = Boolean.valueOf(this.f13630b.post(new androidx.appcompat.view.menu.h(3, this, i0Var, i0Var2, hVar, false)));
                return kVar;
            } catch (Exception e5) {
                e = e5;
                kVar.f14999l.o(e);
                return kVar;
            }
        } catch (Exception e6) {
            e = e6;
        }
    }
}
