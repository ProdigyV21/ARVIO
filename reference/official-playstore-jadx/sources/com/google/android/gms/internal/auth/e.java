package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.auth.account.zza;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends zza {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f13023c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.common.api.internal.d f13024d;

    public /* synthetic */ e(int i10, com.google.android.gms.common.api.internal.d dVar) {
        this.f13023c = i10;
        this.f13024d = dVar;
    }

    @Override // com.google.android.gms.auth.account.zzb
    public void zzb(Account account) {
        switch (this.f13023c) {
            case 0:
                ((f) this.f13024d).setResult(new h(account != null ? Status.f12670o : j.f13049a, account));
                return;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.android.gms.auth.account.zzb
    public void zzc(boolean z) {
        switch (this.f13023c) {
            case 1:
                ((g) this.f13024d).setResult(new i(z ? Status.f12670o : j.f13049a, 1));
                return;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
