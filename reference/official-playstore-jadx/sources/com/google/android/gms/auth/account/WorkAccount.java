package com.google.android.gms.auth.account;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.i;
import com.google.android.gms.internal.auth.j;

/* JADX INFO: loaded from: classes4.dex */
public class WorkAccount {
    public static final i API;

    @Deprecated
    public static final WorkAccountApi WorkAccountApi;
    private static final h zza;
    private static final a zzb;

    static {
        h hVar = new h();
        zza = hVar;
        zzf zzfVar = new zzf();
        zzb = zzfVar;
        API = new i("WorkAccount.API", zzfVar, hVar);
        WorkAccountApi = new j();
    }

    private WorkAccount() {
    }

    public static WorkAccountClient getClient(Activity activity) {
        return new WorkAccountClient(activity);
    }

    public static WorkAccountClient getClient(Context context) {
        return new WorkAccountClient(context);
    }
}
