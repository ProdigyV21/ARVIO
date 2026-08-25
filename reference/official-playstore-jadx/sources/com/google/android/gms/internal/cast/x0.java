package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.internal.zzn;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class x0 extends i0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Logger f13672j = new Logger("MediaRouterProxy");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final androidx.mediarouter.media.k0 f13673c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CastOptions f13674d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap f13675e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c1 f13676f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f13677g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f13678h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f13679i;

    public x0(Context context, androidx.mediarouter.media.k0 k0Var, CastOptions castOptions, zzn zznVar) {
        super("com.google.android.gms.cast.framework.internal.IMediaRouter");
        this.f13675e = new HashMap();
        this.f13673c = k0Var;
        this.f13674d = castOptions;
        if (Build.VERSION.SDK_INT < 33) {
            return;
        }
        f13672j.d("Set up MediaRouterParams based on module flag and CastOptions for Android T or above", new Object[0]);
        this.f13676f = new c1(castOptions);
        new Intent(context, (Class<?>) androidx.mediarouter.media.t0.class).setPackage(context.getPackageName());
        this.f13677g = !context.getPackageManager().queryBroadcastReceivers(r5, 0).isEmpty();
        this.f13678h = true;
        zznVar.zzb(new String[]{"com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED"}).a(new ja(this, 3));
    }

    public final void a(android.support.v4.media.session.v vVar) {
        androidx.appcompat.app.i1 i1Var;
        this.f13673c.getClass();
        androidx.mediarouter.media.k0.b();
        androidx.mediarouter.media.f fVarC = androidx.mediarouter.media.k0.c();
        if (vVar != null) {
            fVarC.getClass();
            i1Var = new androidx.appcompat.app.i1(fVarC, vVar);
        } else {
            i1Var = null;
        }
        androidx.appcompat.app.i1 i1Var2 = fVarC.D;
        if (i1Var2 != null) {
            i1Var2.f();
        }
        fVarC.D = i1Var;
        if (i1Var != null) {
            fVarC.o();
        }
    }

    public final void c(androidx.mediarouter.media.z zVar, int i10) {
        Set set = (Set) this.f13675e.get(zVar);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            this.f13673c.a(zVar, (androidx.mediarouter.media.a0) it.next(), i10);
        }
    }

    public final void f0(androidx.mediarouter.media.z zVar) {
        Set set = (Set) this.f13675e.get(zVar);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            this.f13673c.e((androidx.mediarouter.media.a0) it.next());
        }
    }
}
