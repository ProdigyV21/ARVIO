package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class h0 extends y {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ i0 f13041c;

    public h0(i0 i0Var) {
        this.f13041c = i0Var;
    }

    @Override // com.google.android.gms.internal.auth.y, com.google.android.gms.internal.auth.a0
    public final void D(String str) {
        i0 i0Var = this.f13041c;
        if (str != null) {
            i0Var.setResult(new l0(str));
        } else {
            i0Var.setResult(new l0(new Status(AuthApiStatusCodes.AUTH_APP_CERT_ERROR, null, null, null)));
        }
    }
}
