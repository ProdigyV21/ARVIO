package m4;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.zbc;
import com.google.android.gms.auth.api.zbd;

/* JADX INFO: loaded from: classes4.dex */
public final class p extends com.google.android.gms.common.internal.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final zbd f20245a;

    public p(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, zbd zbdVar, com.google.android.gms.common.api.o oVar, com.google.android.gms.common.api.p pVar) {
        super(context, looper, 68, hVar, oVar, pVar);
        zbc zbcVar = new zbc(zbdVar == null ? zbd.zba : zbdVar);
        zbcVar.zba(l.a());
        this.f20245a = new zbd(zbcVar);
    }

    @Override // com.google.android.gms.common.internal.f
    public final IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        return iInterfaceQueryLocalInterface instanceof q ? (q) iInterfaceQueryLocalInterface : new q(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    @Override // com.google.android.gms.common.internal.f
    public final Bundle getGetServiceRequestExtraArgs() {
        return this.f20245a.zba();
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12800000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }
}
