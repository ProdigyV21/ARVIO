package com.google.android.gms.auth.account;

import a1.a;
import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.api.n;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.auth.j;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public class WorkAccountClient extends n {
    private final WorkAccountApi zza;

    public WorkAccountClient(Activity activity) {
        super(activity, activity, WorkAccount.API, d.f12682a, m.f12824c);
        this.zza = new j();
    }

    public h addWorkAccount(String str) {
        return t.n(this.zza.addWorkAccount(asGoogleApiClient(), str), new zzg(this));
    }

    public h removeWorkAccount(Account account) {
        return t.n(this.zza.removeWorkAccount(asGoogleApiClient(), account), new a());
    }

    public h setWorkAuthenticatorEnabled(boolean z) {
        return t.n(this.zza.setWorkAuthenticatorEnabledWithResult(asGoogleApiClient(), z), new a());
    }

    public WorkAccountClient(Context context) {
        super(context, null, WorkAccount.API, d.f12682a, m.f12824c);
        this.zza = new j();
    }
}
