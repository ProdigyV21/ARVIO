package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.cast.zzaq;

/* JADX INFO: loaded from: classes4.dex */
public final class t extends com.google.android.gms.common.internal.k {
    @Override // com.google.android.gms.common.internal.f
    public final IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.devicesuggestions.internal.IDeviceSuggestionsService");
        return iInterfaceQueryLocalInterface instanceof v ? (v) iInterfaceQueryLocalInterface : new v(iBinder, "com.google.android.gms.cast.framework.devicesuggestions.internal.IDeviceSuggestionsService");
    }

    @Override // com.google.android.gms.common.internal.f
    public final o3.d[] getApiFeatures() {
        return zzaq.zzo;
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 19390000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.framework.devicesuggestions.internal.IDeviceSuggestionsService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.devicesuggestions.api.DeviceSuggestionsApiService.START";
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
