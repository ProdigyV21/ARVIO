package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import com.google.android.gms.auth.zze;

/* JADX INFO: loaded from: classes4.dex */
public final class e4 extends com.google.android.gms.common.internal.k {
    @Override // com.google.android.gms.common.internal.f
    public final IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.account.data.IGoogleAuthService");
        return iInterfaceQueryLocalInterface instanceof i4 ? (i4) iInterfaceQueryLocalInterface : new i4(iBinder, "com.google.android.gms.auth.account.data.IGoogleAuthService");
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final void disconnect(String str) {
        Log.w("GoogleAuthSvcClientImpl", "GoogleAuthServiceClientImpl disconnected with reason: ".concat(String.valueOf(str)));
        super.disconnect(str);
    }

    @Override // com.google.android.gms.common.internal.f
    public final o3.d[] getApiFeatures() {
        return new o3.d[]{zze.zzl, zze.zzk, zze.zza};
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 17895000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.account.data.IGoogleAuthService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.account.authapi.START";
    }

    @Override // com.google.android.gms.common.internal.f
    public final boolean getUseDynamicLookup() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.f
    public final boolean usesClientTelemetry() {
        return true;
    }
}
