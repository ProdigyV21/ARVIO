package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.api.n;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SmsRetrieverClient extends n implements SmsRetrieverApi {
    private static final h zza;
    private static final a zzb;
    private static final i zzc;

    static {
        h hVar = new h();
        zza = hVar;
        zza zzaVar = new zza();
        zzb = zzaVar;
        zzc = new i("SmsRetriever.API", zzaVar, hVar);
    }

    public SmsRetrieverClient(Activity activity) {
        super(activity, activity, zzc, d.f12682a, m.f12824c);
    }

    @Override // com.google.android.gms.auth.api.phone.SmsRetrieverApi
    public abstract com.google.android.gms.tasks.h startSmsRetriever();

    @Override // com.google.android.gms.auth.api.phone.SmsRetrieverApi
    public abstract com.google.android.gms.tasks.h startSmsUserConsent(String str);

    public SmsRetrieverClient(Context context) {
        super(context, null, zzc, d.f12682a, m.f12824c);
    }
}
