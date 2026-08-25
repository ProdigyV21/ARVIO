package com.google.android.gms.cast.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

/* JADX INFO: loaded from: classes4.dex */
public interface zzaj extends IInterface {
    void zzb(int i10) throws RemoteException;

    void zzc(int i10) throws RemoteException;

    void zzd(int i10) throws RemoteException;

    void zze(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException;

    void zzf(int i10) throws RemoteException;

    void zzg(int i10) throws RemoteException;

    void zzh(int i10) throws RemoteException;

    void zzi(int i10) throws RemoteException;

    void zzj(String str, double d4, boolean z) throws RemoteException;

    void zzk(zzac zzacVar) throws RemoteException;

    void zzl(zza zzaVar) throws RemoteException;

    void zzm(String str, String str2) throws RemoteException;

    void zzn(String str, byte[] bArr) throws RemoteException;

    void zzo(String str, long j10, int i10) throws RemoteException;

    void zzp(String str, long j10) throws RemoteException;
}
