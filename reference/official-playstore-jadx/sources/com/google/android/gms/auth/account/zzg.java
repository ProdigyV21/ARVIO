package com.google.android.gms.auth.account;

import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.x;
import com.google.android.gms.common.internal.s;

/* JADX INFO: loaded from: classes4.dex */
final class zzg implements s {
    public zzg(WorkAccountClient workAccountClient) {
    }

    @Override // com.google.android.gms.common.internal.s
    public final /* synthetic */ Object convert(x xVar) {
        return ((WorkAccountApi.AddAccountResult) xVar).getAccount();
    }
}
