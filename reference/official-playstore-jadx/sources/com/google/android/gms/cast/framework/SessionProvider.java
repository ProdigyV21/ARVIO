package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SessionProvider {
    private final Context zza;
    private final String zzb;
    private final zzbh zzc = new zzbh(this, null);

    public SessionProvider(Context context, String str) {
        t.i(context);
        this.zza = context.getApplicationContext();
        t.f(str);
        this.zzb = str;
    }

    public abstract Session createSession(String str);

    public final String getCategory() {
        return this.zzb;
    }

    public final Context getContext() {
        return this.zza;
    }

    public abstract boolean isSessionRecoverable();

    public final IBinder zza() {
        return this.zzc;
    }
}
