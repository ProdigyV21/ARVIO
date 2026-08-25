package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.v1;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
final class zzh implements zzk {
    final /* synthetic */ String zza;
    final /* synthetic */ Bundle zzb;

    public zzh(String str, Bundle bundle) {
        this.zza = str;
        this.zzb = bundle;
    }

    @Override // com.google.android.gms.auth.zzk
    public final /* bridge */ /* synthetic */ Object zza(IBinder iBinder) throws IOException, GoogleAuthException, RemoteException {
        Bundle bundleR = v1.a(iBinder).R(this.zzb, this.zza);
        zzl.zzc(bundleR);
        String string = bundleR.getString("Error");
        if (bundleR.getBoolean("booleanResult")) {
            return null;
        }
        throw new GoogleAuthException(string);
    }
}
