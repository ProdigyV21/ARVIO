package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class p0 extends n4.d implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public f f12894d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f12895e;

    public p0(f fVar, int i10) {
        super("com.google.android.gms.common.internal.IGmsCallbacks", 1);
        this.f12894d = fVar;
        this.f12895e = i10;
    }

    @Override // n4.d
    public final boolean c(int i10, Parcel parcel, Parcel parcel2) {
        int i11 = this.f12895e;
        if (i10 == 1) {
            int i12 = parcel.readInt();
            IBinder strongBinder = parcel.readStrongBinder();
            Bundle bundle = (Bundle) p4.g.a(parcel, Bundle.CREATOR);
            p4.g.c(parcel);
            t.j(this.f12894d, "onPostInitComplete can be called only once per call to getRemoteService");
            this.f12894d.onPostInitHandler(i12, strongBinder, bundle, i11);
            this.f12894d = null;
        } else if (i10 == 2) {
            parcel.readInt();
            p4.g.c(parcel);
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        } else {
            if (i10 != 3) {
                return false;
            }
            int i13 = parcel.readInt();
            IBinder strongBinder2 = parcel.readStrongBinder();
            t0 t0Var = (t0) p4.g.a(parcel, t0.CREATOR);
            p4.g.c(parcel);
            f fVar = this.f12894d;
            t.j(fVar, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            t.i(t0Var);
            fVar.zzc(t0Var);
            Bundle bundle2 = t0Var.f12913i;
            t.j(this.f12894d, "onPostInitComplete can be called only once per call to getRemoteService");
            this.f12894d.onPostInitHandler(i13, strongBinder2, bundle2, i11);
            this.f12894d = null;
        }
        parcel2.writeNoException();
        return true;
    }
}
