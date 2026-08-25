package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;
import r3.b;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzc extends e0 implements zzd {
    public zzc() {
        super("com.google.android.gms.cast.framework.media.IImagePicker");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 == 1) {
            MediaMetadata mediaMetadata = (MediaMetadata) a1.b(parcel, MediaMetadata.CREATOR);
            int i12 = parcel.readInt();
            a1.e(parcel);
            b bVarZze = zze(mediaMetadata, i12);
            parcel2.writeNoException();
            if (bVarZze == null) {
                parcel2.writeInt(0);
                return true;
            }
            parcel2.writeInt(1);
            bVarZze.writeToParcel(parcel2, 1);
            return true;
        }
        if (i10 == 2) {
            com.google.android.gms.dynamic.b bVarZzf = zzf();
            parcel2.writeNoException();
            a1.d(parcel2, bVarZzf);
            return true;
        }
        if (i10 == 3) {
            parcel2.writeNoException();
            parcel2.writeInt(12451000);
            return true;
        }
        if (i10 != 4) {
            return false;
        }
        MediaMetadata mediaMetadata2 = (MediaMetadata) a1.b(parcel, MediaMetadata.CREATOR);
        ImageHints imageHints = (ImageHints) a1.b(parcel, ImageHints.CREATOR);
        a1.e(parcel);
        b bVarZzg = zzg(mediaMetadata2, imageHints);
        parcel2.writeNoException();
        if (bVarZzg == null) {
            parcel2.writeInt(0);
            return true;
        }
        parcel2.writeInt(1);
        bVarZzg.writeToParcel(parcel2, 1);
        return true;
    }
}
