package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.dynamic.b;

/* JADX INFO: loaded from: classes4.dex */
public final class zzaa {
    private static final Logger zza = new Logger("DiscoveryManager");
    private final zzao zzb;

    public zzaa(zzao zzaoVar) {
        this.zzb = zzaoVar;
    }

    public final b zza() {
        try {
            return this.zzb.zze();
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "getWrappedThis", "zzao");
            return null;
        }
    }
}
