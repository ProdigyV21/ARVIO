package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.t;
import com.google.android.gms.internal.cast.u0;
import com.google.android.gms.tasks.i;

/* JADX INFO: loaded from: classes4.dex */
final /* synthetic */ class zzas implements t {
    static final /* synthetic */ zzas zza = new zzas();

    private /* synthetic */ zzas() {
    }

    @Override // com.google.android.gms.common.api.internal.t
    public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        com.google.android.gms.cast.internal.zzy zzyVar = (com.google.android.gms.cast.internal.zzy) obj;
        int i10 = zzbm.zzf;
        com.google.android.gms.cast.internal.zzah zzahVar = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
        zzyVar.getContext();
        zzahVar.zzr(u0.i());
        ((i) obj2).b(Boolean.TRUE);
    }
}
