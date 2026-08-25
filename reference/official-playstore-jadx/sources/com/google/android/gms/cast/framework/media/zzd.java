package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import r3.b;

/* JADX INFO: loaded from: classes4.dex */
public interface zzd extends IInterface {
    b zze(MediaMetadata mediaMetadata, int i10) throws RemoteException;

    com.google.android.gms.dynamic.b zzf() throws RemoteException;

    b zzg(MediaMetadata mediaMetadata, ImageHints imageHints) throws RemoteException;
}
