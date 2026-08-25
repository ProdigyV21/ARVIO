package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;

/* JADX INFO: loaded from: classes4.dex */
public interface zzae extends IInterface {
    void zzb(String str, String str2) throws RemoteException;

    void zzc(String str, LaunchOptions launchOptions) throws RemoteException;

    void zzd(String str) throws RemoteException;

    void zze(int i10) throws RemoteException;
}
