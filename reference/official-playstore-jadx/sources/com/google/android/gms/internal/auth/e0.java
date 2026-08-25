package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class e0 extends y {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f13025c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f13026d;

    public /* synthetic */ e0(Object obj, int i10) {
        this.f13025c = i10;
        this.f13026d = obj;
    }

    @Override // com.google.android.gms.internal.auth.y, com.google.android.gms.internal.auth.a0
    public void D(String str) {
        switch (this.f13025c) {
            case 1:
                qb.d.S(str != null ? Status.f12670o : new Status(AuthApiStatusCodes.AUTH_APP_CERT_ERROR, null, null, null), str, (com.google.android.gms.tasks.i) this.f13026d);
                break;
            default:
                super.D(str);
                break;
        }
    }

    @Override // com.google.android.gms.internal.auth.y, com.google.android.gms.internal.auth.a0
    public void d(ProxyResponse proxyResponse) {
        switch (this.f13025c) {
            case 0:
                qb.d.S(new Status(proxyResponse.googlePlayServicesStatusCode, null, null, null), proxyResponse, (com.google.android.gms.tasks.i) this.f13026d);
                break;
            case 1:
            default:
                super.d(proxyResponse);
                break;
            case 2:
                ((g0) this.f13026d).setResult(new k0(proxyResponse));
                break;
        }
    }
}
