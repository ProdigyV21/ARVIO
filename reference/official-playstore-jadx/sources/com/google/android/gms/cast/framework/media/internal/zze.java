package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.cast.a;
import com.google.android.gms.internal.cast.a1;

/* JADX INFO: loaded from: classes4.dex */
public final class zze extends a implements zzg {
    public zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zzg
    public final Bitmap zze(Uri uri) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, uri);
        Parcel parcelZzb = zzb(1, parcelZza);
        Bitmap bitmap = (Bitmap) a1.b(parcelZzb, Bitmap.CREATOR);
        parcelZzb.recycle();
        return bitmap;
    }
}
