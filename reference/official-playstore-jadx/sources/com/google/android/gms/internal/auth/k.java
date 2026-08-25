package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.auth.account.zzd;
import com.google.android.gms.auth.zze;

/* JADX INFO: loaded from: classes4.dex */
public final class k extends com.google.android.gms.common.internal.k {
    @Override // com.google.android.gms.common.internal.f
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        return zzd.zzb(iBinder);
    }

    @Override // com.google.android.gms.common.internal.f
    public final o3.d[] getApiFeatures() {
        return new o3.d[]{zze.zzn};
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.account.IWorkAccountService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.account.workaccount.START";
    }

    @Override // com.google.android.gms.common.internal.f
    public final boolean usesClientTelemetry() {
        return true;
    }
}
