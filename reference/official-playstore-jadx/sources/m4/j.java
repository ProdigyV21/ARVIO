package m4;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends com.google.android.gms.common.internal.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f20235a;

    public j(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, com.google.android.gms.common.api.internal.g gVar, com.google.android.gms.common.api.internal.o oVar) {
        super(context, looper, 212, hVar, gVar, oVar);
        this.f20235a = new Bundle();
    }

    @Override // com.google.android.gms.common.internal.f
    public final IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.identity.internal.ISignInService");
        return iInterfaceQueryLocalInterface instanceof b0 ? (b0) iInterfaceQueryLocalInterface : new b0(iBinder, "com.google.android.gms.auth.api.identity.internal.ISignInService");
    }

    @Override // com.google.android.gms.common.internal.f
    public final o3.d[] getApiFeatures() {
        return k.f20242g;
    }

    @Override // com.google.android.gms.common.internal.f
    public final Bundle getGetServiceRequestExtraArgs() {
        return this.f20235a;
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 17895000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.identity.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.identity.service.signin.START";
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
