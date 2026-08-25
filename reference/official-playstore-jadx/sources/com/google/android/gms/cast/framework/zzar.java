package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface zzar extends IInterface {
    void zze() throws RemoteException;

    int zzf(Intent intent, int i10, int i11) throws RemoteException;

    IBinder zzg(Intent intent) throws RemoteException;

    void zzh() throws RemoteException;
}
