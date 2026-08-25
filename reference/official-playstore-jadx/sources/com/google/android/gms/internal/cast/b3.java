package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: loaded from: classes4.dex */
public abstract class b3 extends e0 implements c3 {
    public b3() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplayCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 == 1) {
            int i12 = parcel.readInt();
            int i13 = parcel.readInt();
            Surface surface = (Surface) a1.b(parcel, Surface.CREATOR);
            com.google.android.gms.common.api.j jVar = (com.google.android.gms.common.api.j) a1.b(parcel, com.google.android.gms.common.api.j.CREATOR);
            a1.e(parcel);
            zzb(i12, i13, surface, jVar);
        } else if (i10 == 2) {
            int i14 = parcel.readInt();
            com.google.android.gms.common.api.j jVar2 = (com.google.android.gms.common.api.j) a1.b(parcel, com.google.android.gms.common.api.j.CREATOR);
            a1.e(parcel);
            zzd(i14, jVar2);
        } else if (i10 == 3) {
            com.google.android.gms.common.api.j jVar3 = (com.google.android.gms.common.api.j) a1.b(parcel, com.google.android.gms.common.api.j.CREATOR);
            a1.e(parcel);
            zzf(jVar3);
        } else if (i10 == 4) {
            com.google.android.gms.common.api.j jVar4 = (com.google.android.gms.common.api.j) a1.b(parcel, com.google.android.gms.common.api.j.CREATOR);
            a1.e(parcel);
            zzc(jVar4);
        } else {
            if (i10 != 5) {
                return false;
            }
            boolean zA = a1.a(parcel);
            com.google.android.gms.common.api.j jVar5 = (com.google.android.gms.common.api.j) a1.b(parcel, com.google.android.gms.common.api.j.CREATOR);
            a1.e(parcel);
            zze(zA, jVar5);
        }
        parcel2.writeNoException();
        return true;
    }
}
