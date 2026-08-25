package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

/* JADX INFO: loaded from: classes4.dex */
public final class e1 extends a implements h2 {
    @Override // com.google.android.gms.internal.auth.h2
    public final Bundle H(Account account, String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        o0.c(parcelZza, account);
        parcelZza.writeString(str);
        o0.c(parcelZza, bundle);
        Parcel parcelZzb = zzb(5, parcelZza);
        Bundle bundle2 = (Bundle) o0.a(parcelZzb, Bundle.CREATOR);
        parcelZzb.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.auth.h2
    public final AccountChangeEventsResponse N(AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
        Parcel parcelZza = zza();
        o0.c(parcelZza, accountChangeEventsRequest);
        Parcel parcelZzb = zzb(3, parcelZza);
        AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) o0.a(parcelZzb, AccountChangeEventsResponse.CREATOR);
        parcelZzb.recycle();
        return accountChangeEventsResponse;
    }

    @Override // com.google.android.gms.internal.auth.h2
    public final Bundle Q(Account account) throws RemoteException {
        Parcel parcelZza = zza();
        o0.c(parcelZza, account);
        Parcel parcelZzb = zzb(7, parcelZza);
        Bundle bundle = (Bundle) o0.a(parcelZzb, Bundle.CREATOR);
        parcelZzb.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.auth.h2
    public final Bundle R(Bundle bundle, String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        o0.c(parcelZza, bundle);
        Parcel parcelZzb = zzb(2, parcelZza);
        Bundle bundle2 = (Bundle) o0.a(parcelZzb, Bundle.CREATOR);
        parcelZzb.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.auth.h2
    public final Bundle Y(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZzb = zzb(8, parcelZza);
        Bundle bundle = (Bundle) o0.a(parcelZzb, Bundle.CREATOR);
        parcelZzb.recycle();
        return bundle;
    }
}
