package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class a0 implements DialogInterface.OnCancelListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f12693i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile boolean f12694l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicReference f12695m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final androidx.loader.content.j f12696n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final o3.e f12697o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final t.f f12698p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final h f12699q;

    public a0(k kVar, h hVar) {
        o3.e eVar = o3.e.f20741d;
        this.f12693i = kVar;
        this.f12695m = new AtomicReference(null);
        this.f12696n = new androidx.loader.content.j(Looper.getMainLooper(), 3);
        this.f12697o = eVar;
        this.f12698p = new t.f(0);
        this.f12699q = hVar;
        kVar.b(this);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.common.api.internal.k, java.lang.Object] */
    public final Activity a() {
        Activity activityC = this.f12693i.c();
        com.google.android.gms.common.internal.t.i(activityC);
        return activityC;
    }

    public final void b(Bundle bundle) {
        if (bundle != null) {
            this.f12695m.set(bundle.getBoolean("resolving_error", false) ? new e1(new o3.b(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"), null), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    public final void c() {
        this.f12694l = false;
        h hVar = this.f12699q;
        hVar.getClass();
        synchronized (h.B) {
            try {
                if (hVar.f12750u == this) {
                    hVar.f12750u = null;
                    hVar.f12751v.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void d() {
        if (this.f12698p.isEmpty()) {
            return;
        }
        this.f12699q.f(this);
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        o3.b bVar = new o3.b(13, null, null);
        AtomicReference atomicReference = this.f12695m;
        e1 e1Var = (e1) atomicReference.get();
        int i10 = e1Var == null ? -1 : e1Var.f12728a;
        atomicReference.set(null);
        this.f12699q.j(bVar, i10);
    }
}
