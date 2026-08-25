package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.internal.cast.u0;
import o3.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzy extends k {
    private static final Logger zze = new Logger("CastClientImplCxless");
    private final CastDevice zzf;
    private final long zzg;
    private final Bundle zzh;
    private final String zzi;

    public zzy(Context context, Looper looper, h hVar, CastDevice castDevice, long j10, Bundle bundle, String str, o oVar, p pVar) {
        super(context, looper, 10, hVar, oVar, pVar);
        this.zzf = castDevice;
        this.zzg = j10;
        this.zzh = bundle;
        this.zzi = str;
    }

    @Override // com.google.android.gms.common.internal.f
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        return iInterfaceQueryLocalInterface instanceof zzah ? (zzah) iInterfaceQueryLocalInterface : new zzah(iBinder);
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final void disconnect() {
        try {
            try {
                zzah zzahVar = (zzah) getService();
                getContext();
                zzahVar.zze(u0.i());
            } finally {
                super.disconnect();
            }
        } catch (RemoteException | IllegalStateException e5) {
            zze.d(e5, "Error while disconnecting the controller interface", new Object[0]);
        }
    }

    @Override // com.google.android.gms.common.internal.f
    public final d[] getApiFeatures() {
        return com.google.android.gms.cast.zzaq.zzo;
    }

    @Override // com.google.android.gms.common.internal.f
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        zze.d("getRemoteService()", new Object[0]);
        this.zzf.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzg);
        bundle.putString("connectionless_client_record_id", this.zzi);
        Bundle bundle2 = this.zzh;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 19390000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
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
