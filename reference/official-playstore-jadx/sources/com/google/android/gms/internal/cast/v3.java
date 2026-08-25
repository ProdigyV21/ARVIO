package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class v3 extends com.google.android.gms.common.internal.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f13637a;

    public v3(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, com.google.android.gms.common.api.o oVar, com.google.android.gms.common.api.p pVar) {
        super(context, looper, 41, hVar, oVar, pVar);
        this.f13637a = new AtomicReference();
    }

    @Override // com.google.android.gms.common.internal.f
    public final IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.usagereporting.internal.IUsageReportingService");
        return iInterfaceQueryLocalInterface instanceof t3 ? (t3) iInterfaceQueryLocalInterface : new t3(iBinder, "com.google.android.gms.usagereporting.internal.IUsageReportingService");
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final void disconnect() {
        try {
            if (this.f13637a.getAndSet(null) != null) {
                throw new ClassCastException();
            }
        } catch (RemoteException e5) {
            Log.e("UsageReportingClientImp", "disconnect(): Could not unregister listener from remote:", e5);
        }
        super.disconnect();
    }

    @Override // com.google.android.gms.common.internal.f
    public final o3.d[] getApiFeatures() {
        return u0.f13619l;
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12600000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.usagereporting.internal.IUsageReportingService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.usagereporting.service.START";
    }

    @Override // com.google.android.gms.common.internal.f
    public final boolean usesClientTelemetry() {
        return true;
    }
}
