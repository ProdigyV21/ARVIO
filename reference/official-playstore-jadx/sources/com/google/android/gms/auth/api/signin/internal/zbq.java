package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.api.Status;
import m4.m;
import m4.n;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zbq extends m implements zbr {
    public zbq() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    @Override // m4.m
    public final boolean zba(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        switch (i10) {
            case 101:
                GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) n.a(parcel, GoogleSignInAccount.CREATOR);
                Status status = (Status) n.a(parcel, Status.CREATOR);
                n.b(parcel);
                zbd(googleSignInAccount, status);
                break;
            case MediaError.DetailedErrorCode.MEDIA_DECODE /* 102 */:
                Status status2 = (Status) n.a(parcel, Status.CREATOR);
                n.b(parcel);
                zbc(status2);
                break;
            case MediaError.DetailedErrorCode.MEDIA_NETWORK /* 103 */:
                Status status3 = (Status) n.a(parcel, Status.CREATOR);
                n.b(parcel);
                zbb(status3);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
