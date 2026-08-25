package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzo;
import com.google.android.gms.auth.api.accounttransfer.zzw;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class l extends q {
    public l() {
        super("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferCallbacks");
    }

    public void zzb(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public void zzc(DeviceMetaData deviceMetaData) {
        throw new UnsupportedOperationException();
    }

    public void zze() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.auth.r
    public final void zzf(Status status, zzw zzwVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.auth.r
    public final void zzg(Status status, zzo zzoVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.auth.r
    public final void zzh(Status status) throws RemoteException {
        throw new UnsupportedOperationException();
    }
}
