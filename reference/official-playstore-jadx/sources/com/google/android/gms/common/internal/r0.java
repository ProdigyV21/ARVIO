package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.fragment.app.a2;

/* JADX INFO: loaded from: classes4.dex */
public final class r0 extends k0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final IBinder f12907g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ f f12908h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r0(f fVar, int i10, IBinder iBinder, Bundle bundle) {
        super(fVar, i10, bundle);
        this.f12908h = fVar;
        this.f12907g = iBinder;
    }

    @Override // com.google.android.gms.common.internal.k0
    public final boolean a() {
        IBinder iBinder = this.f12907g;
        try {
            t.i(iBinder);
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            f fVar = this.f12908h;
            if (!fVar.getServiceDescriptor().equals(interfaceDescriptor)) {
                String serviceDescriptor = fVar.getServiceDescriptor();
                Log.w("GmsClient", a2.n(new StringBuilder(String.valueOf(serviceDescriptor).length() + 34 + String.valueOf(interfaceDescriptor).length()), "service descriptor mismatch: ", serviceDescriptor, " vs. ", interfaceDescriptor));
                return false;
            }
            IInterface iInterfaceCreateServiceInterface = fVar.createServiceInterface(iBinder);
            if (iInterfaceCreateServiceInterface == null || !(fVar.zze(2, 4, iInterfaceCreateServiceInterface) || fVar.zze(3, 4, iInterfaceCreateServiceInterface))) {
                return false;
            }
            fVar.zzn(null);
            b bVarZzk = fVar.zzk();
            Bundle connectionHint = fVar.getConnectionHint();
            if (bVarZzk == null) {
                return true;
            }
            fVar.zzk().b(connectionHint);
            return true;
        } catch (RemoteException unused) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }

    @Override // com.google.android.gms.common.internal.k0
    public final void b(o3.b bVar) {
        f fVar = this.f12908h;
        if (fVar.zzl() != null) {
            fVar.zzl().a(bVar);
        }
        fVar.onConnectionFailed(bVar);
    }
}
