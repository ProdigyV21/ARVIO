package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.cast.MediaError;
import m4.a;
import m4.n;

/* JADX INFO: loaded from: classes4.dex */
public final class zbs extends a implements IInterface {
    public zbs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zbc(zbr zbrVar, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel parcelZba = zba();
        n.d(parcelZba, zbrVar);
        n.c(parcelZba, googleSignInOptions);
        zbb(MediaError.DetailedErrorCode.MEDIA_NETWORK, parcelZba);
    }

    public final void zbd(zbr zbrVar, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel parcelZba = zba();
        n.d(parcelZba, zbrVar);
        n.c(parcelZba, googleSignInOptions);
        zbb(MediaError.DetailedErrorCode.MEDIA_DECODE, parcelZba);
    }

    public final void zbe(zbr zbrVar, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel parcelZba = zba();
        n.d(parcelZba, zbrVar);
        n.c(parcelZba, googleSignInOptions);
        zbb(101, parcelZba);
    }
}
