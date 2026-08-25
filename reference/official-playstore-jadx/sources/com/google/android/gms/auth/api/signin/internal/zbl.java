package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.internal.d;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
abstract class zbl extends d {
    public zbl(q qVar) {
        super(Auth.GOOGLE_SIGN_IN_API, qVar);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult, com.google.android.gms.common.api.internal.e
    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        setResult((x) obj);
    }
}
