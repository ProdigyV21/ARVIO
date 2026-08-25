package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.o0;
import com.google.android.gms.internal.auth.w;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zza extends w implements zzb {
    public zza() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    @Override // com.google.android.gms.internal.auth.w
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 != 1) {
            if (i10 != 2) {
                return false;
            }
            int i12 = o0.f13083a;
            int i13 = parcel.readInt();
            o0.b(parcel);
            zzc(i13 != 0);
        } else {
            Account account = (Account) o0.a(parcel, Account.CREATOR);
            o0.b(parcel);
            zzb(account);
        }
        return true;
    }
}
