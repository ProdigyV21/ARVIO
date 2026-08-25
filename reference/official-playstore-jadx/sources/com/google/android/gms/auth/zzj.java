package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.arflix.tv.data.repository.g;
import com.google.android.gms.internal.auth.v1;
import h.f0;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
final class zzj implements zzk {
    final /* synthetic */ String zza;
    final /* synthetic */ Context zzb;

    public zzj(String str, Context context) {
        this.zza = str;
        this.zzb = context;
    }

    @Override // com.google.android.gms.auth.zzk
    public final /* bridge */ /* synthetic */ Object zza(IBinder iBinder) throws IOException, GoogleAuthException, RemoteException {
        Bundle bundleY = v1.a(iBinder).Y(this.zza);
        zzl.zzc(bundleY);
        String string = bundleY.getString("Error");
        Intent intent = (Intent) bundleY.getParcelable("userRecoveryIntent");
        PendingIntent pendingIntent = (PendingIntent) bundleY.getParcelable("userRecoveryPendingIntent");
        if (f0.b(3, g.b(string))) {
            return Boolean.TRUE;
        }
        zzl.zzn(this.zzb, "requestGoogleAccountsAccess", string, intent, pendingIntent);
        throw new GoogleAuthException("Invalid state. Shouldn't happen");
    }
}
