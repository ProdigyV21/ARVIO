package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.common.internal.k;
import o3.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzo extends k {
    public zzo(Context context, Looper looper, h hVar, o oVar, p pVar) {
        super(context, looper, 161, hVar, oVar, pVar);
    }

    @Override // com.google.android.gms.common.internal.f
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastService");
        return iInterfaceQueryLocalInterface instanceof zzak ? (zzak) iInterfaceQueryLocalInterface : new zzak(iBinder);
    }

    @Override // com.google.android.gms.common.internal.f
    public final d[] getApiFeatures() {
        return com.google.android.gms.cast.zzaq.zzo;
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    @Override // com.google.android.gms.common.internal.f
    public final boolean usesClientTelemetry() {
        return true;
    }
}
