package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final class q2 extends v2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f13552b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ x2 f13553c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q2(x2 x2Var, com.google.android.gms.common.api.q qVar, String str) {
        super(x2Var, qVar);
        this.f13552b = str;
        this.f13553c = x2Var;
    }

    @Override // com.google.android.gms.common.api.internal.d
    public final void doExecute(com.google.android.gms.common.api.b bVar) throws RemoteException {
        a3 a3Var = (a3) bVar;
        t2 t2Var = new t2(this, a3Var);
        p2 p2Var = this.f13553c.f13685c;
        a3Var.getClass();
        a3.f13164d.d("startRemoteDisplay", new Object[0]);
        z2 z2Var = new z2(a3Var, p2Var);
        d3 d3Var = (d3) a3Var.getService();
        String deviceId = a3Var.f13166b.getDeviceId();
        a3Var.getContext();
        com.google.android.gms.common.api.j jVarI = u0.i();
        Bundle bundle = a3Var.f13167c;
        Parcel parcelZza = d3Var.zza();
        a1.d(parcelZza, t2Var);
        a1.d(parcelZza, z2Var);
        parcelZza.writeString(deviceId);
        parcelZza.writeString(this.f13552b);
        a1.c(parcelZza, bundle);
        a1.c(parcelZza, jVarI);
        d3Var.zzd(7, parcelZza);
    }
}
