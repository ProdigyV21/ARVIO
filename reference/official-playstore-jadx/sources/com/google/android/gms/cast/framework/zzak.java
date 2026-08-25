package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import o3.b;

/* JADX INFO: loaded from: classes4.dex */
public interface zzak extends IInterface {
    void zze(Bundle bundle) throws RemoteException;

    void zzf(int i10) throws RemoteException;

    void zzg(b bVar) throws RemoteException;

    void zzh(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException;

    void zzi(int i10) throws RemoteException;

    void zzj(boolean z, int i10) throws RemoteException;
}
