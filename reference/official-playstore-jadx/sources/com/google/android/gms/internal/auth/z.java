package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.auth.api.AuthProxyOptions;

/* JADX INFO: loaded from: classes4.dex */
public final class z extends com.google.android.gms.common.internal.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f13153a;

    public z(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, AuthProxyOptions authProxyOptions, com.google.android.gms.common.api.internal.g gVar, com.google.android.gms.common.api.internal.o oVar) {
        super(context, looper, 16, hVar, gVar, oVar);
        this.f13153a = authProxyOptions == null ? new Bundle() : authProxyOptions.zza();
    }

    @Override // com.google.android.gms.common.internal.f
    public final IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
        return iInterfaceQueryLocalInterface instanceof b0 ? (b0) iInterfaceQueryLocalInterface : new b0(iBinder, "com.google.android.gms.auth.api.internal.IAuthService");
    }

    @Override // com.google.android.gms.common.internal.f
    public final Bundle getGetServiceRequestExtraArgs() {
        return this.f13153a;
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.service.START";
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final boolean requiresSignIn() {
        com.google.android.gms.common.internal.h clientSettings = getClientSettings();
        Account account = clientSettings.f12845a;
        if (TextUtils.isEmpty(account != null ? account.name : null)) {
            return false;
        }
        if (clientSettings.f12848d.get(AuthProxy.API) == null) {
            return !clientSettings.f12846b.isEmpty();
        }
        throw new ClassCastException();
    }

    @Override // com.google.android.gms.common.internal.f
    public final boolean usesClientTelemetry() {
        return true;
    }
}
