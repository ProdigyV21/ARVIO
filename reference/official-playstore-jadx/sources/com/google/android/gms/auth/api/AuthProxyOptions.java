package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class AuthProxyOptions implements d.e {
    public static final AuthProxyOptions zza = new AuthProxyOptions(new Bundle(), null);
    private final Bundle zzb;

    public /* synthetic */ AuthProxyOptions(Bundle bundle, zzb zzbVar) {
        this.zzb = bundle;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AuthProxyOptions) {
            return t.c(this.zzb, ((AuthProxyOptions) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzb});
    }

    public final Bundle zza() {
        return new Bundle(this.zzb);
    }
}
