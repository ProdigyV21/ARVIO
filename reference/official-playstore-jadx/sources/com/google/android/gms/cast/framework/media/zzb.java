package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.cast.a;
import r3.b;

/* JADX INFO: loaded from: classes4.dex */
public final class zzb extends a implements zzd {
    public zzb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.IImagePicker");
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final b zze(MediaMetadata mediaMetadata, int i10) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final com.google.android.gms.dynamic.b zzf() throws RemoteException {
        Parcel parcelZzb = zzb(2, zza());
        com.google.android.gms.dynamic.b bVarF0 = d.f0(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return bVarF0;
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final b zzg(MediaMetadata mediaMetadata, ImageHints imageHints) throws RemoteException {
        throw null;
    }
}
