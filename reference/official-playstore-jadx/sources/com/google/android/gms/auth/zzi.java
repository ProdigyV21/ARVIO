package com.google.android.gms.auth;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.v1;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
final class zzi implements zzk {
    final /* synthetic */ AccountChangeEventsRequest zza;

    public zzi(AccountChangeEventsRequest accountChangeEventsRequest) {
        this.zza = accountChangeEventsRequest;
    }

    @Override // com.google.android.gms.auth.zzk
    public final /* bridge */ /* synthetic */ Object zza(IBinder iBinder) throws IOException, RemoteException, GoogleAuthException {
        AccountChangeEventsResponse accountChangeEventsResponseN = v1.a(iBinder).N(this.zza);
        zzl.zzc(accountChangeEventsResponseN);
        return accountChangeEventsResponseN.getEvents();
    }
}
