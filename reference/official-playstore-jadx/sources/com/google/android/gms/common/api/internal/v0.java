package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.internal.auth.j4;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class v0 extends u4.c implements com.google.android.gms.common.api.o, com.google.android.gms.common.api.p {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final j4 f12798j = t4.b.f21888a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f12799c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f12800d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j4 f12801e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Set f12802f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final com.google.android.gms.common.internal.h f12803g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public t4.c f12804h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g0 f12805i;

    public v0(Context context, Handler handler, com.google.android.gms.common.internal.h hVar) {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
        this.f12799c = context;
        this.f12800d = handler;
        this.f12803g = hVar;
        this.f12802f = hVar.f12846b;
        this.f12801e = f12798j;
    }

    @Override // com.google.android.gms.common.api.internal.o
    public final void a(o3.b bVar) {
        this.f12805i.C(bVar);
    }

    @Override // com.google.android.gms.common.api.internal.g
    public final void c(int i10) {
        g0 g0Var = this.f12805i;
        e0 e0Var = (e0) ((h) g0Var.f12738f).f12749t.get((a) g0Var.f12735c);
        if (e0Var != null) {
            if (e0Var.k) {
                e0Var.n(new o3.b(17, null, null));
            } else {
                e0Var.c(i10);
            }
        }
    }

    public final void f0(u4.g gVar) {
        this.f12800d.post(new com.google.common.util.concurrent.w0(13, this, false, gVar));
    }

    @Override // com.google.android.gms.common.api.internal.g
    public final void onConnected() {
        this.f12804h.b(this);
    }
}
