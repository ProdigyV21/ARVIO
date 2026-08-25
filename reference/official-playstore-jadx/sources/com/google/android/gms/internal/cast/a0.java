package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsCallback;
import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.zzaq;

/* JADX INFO: loaded from: classes4.dex */
public final class a0 extends com.google.android.gms.common.api.n implements DeviceSuggestionsClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.android.gms.common.api.i f13155a = new com.google.android.gms.common.api.i("DeviceSuggestions.API", new com.google.android.gms.internal.auth.j4(1), new com.google.android.gms.common.api.h());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Logger f13156b = new Logger("InternalDeviceSuggestionsClient");

    @Override // com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient
    public final com.google.android.gms.tasks.h clearClientData() {
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12807a = new z(this, 1);
        xVarBuilder.f12809c = new o3.d[]{zzaq.zzn};
        xVarBuilder.f12810d = 37604;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient
    public final com.google.android.gms.tasks.h registerCallback(DeviceSuggestionsCallback deviceSuggestionsCallback) {
        com.google.android.gms.common.api.internal.n nVarRegisterListener = registerListener(deviceSuggestionsCallback, "DeviceSuggestionsCallback");
        final x xVar = new x(this, nVarRegisterListener);
        final int i10 = 1;
        com.google.android.gms.common.api.internal.t tVar = new com.google.android.gms.common.api.internal.t(this) { // from class: com.google.android.gms.internal.cast.y

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            public final /* synthetic */ a0 f13689l;

            {
                this.f13689l = this;
            }

            @Override // com.google.android.gms.common.api.internal.t
            public final void accept(Object obj, Object obj2) throws RemoteException {
                com.google.android.gms.tasks.i iVar = (com.google.android.gms.tasks.i) obj2;
                t tVar2 = (t) obj;
                switch (i10) {
                    case 0:
                        com.google.android.gms.internal.auth.l4 l4Var = new com.google.android.gms.internal.auth.l4(this.f13689l, iVar, 2);
                        v vVar = (v) tVar2.getService();
                        Parcel parcelZza = vVar.zza();
                        a1.d(parcelZza, l4Var);
                        a1.d(parcelZza, xVar);
                        vVar.zzc(2, parcelZza);
                        break;
                    default:
                        com.google.android.gms.internal.auth.l4 l4Var2 = new com.google.android.gms.internal.auth.l4(this.f13689l, iVar, 1);
                        v vVar2 = (v) tVar2.getService();
                        Parcel parcelZza2 = vVar2.zza();
                        a1.d(parcelZza2, l4Var2);
                        a1.d(parcelZza2, xVar);
                        vVar2.zzc(1, parcelZza2);
                        break;
                }
            }
        };
        final int i11 = 0;
        com.google.android.gms.common.api.internal.t tVar2 = new com.google.android.gms.common.api.internal.t(this) { // from class: com.google.android.gms.internal.cast.y

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            public final /* synthetic */ a0 f13689l;

            {
                this.f13689l = this;
            }

            @Override // com.google.android.gms.common.api.internal.t
            public final void accept(Object obj, Object obj2) throws RemoteException {
                com.google.android.gms.tasks.i iVar = (com.google.android.gms.tasks.i) obj2;
                t tVar22 = (t) obj;
                switch (i11) {
                    case 0:
                        com.google.android.gms.internal.auth.l4 l4Var = new com.google.android.gms.internal.auth.l4(this.f13689l, iVar, 2);
                        v vVar = (v) tVar22.getService();
                        Parcel parcelZza = vVar.zza();
                        a1.d(parcelZza, l4Var);
                        a1.d(parcelZza, xVar);
                        vVar.zzc(2, parcelZza);
                        break;
                    default:
                        com.google.android.gms.internal.auth.l4 l4Var2 = new com.google.android.gms.internal.auth.l4(this.f13689l, iVar, 1);
                        v vVar2 = (v) tVar22.getService();
                        Parcel parcelZza2 = vVar2.zza();
                        a1.d(parcelZza2, l4Var2);
                        a1.d(parcelZza2, xVar);
                        vVar2.zzc(1, parcelZza2);
                        break;
                }
            }
        };
        com.google.android.gms.common.api.internal.r rVar = new com.google.android.gms.common.api.internal.r();
        rVar.f12787b = tVar;
        rVar.f12788c = tVar2;
        rVar.f12789d = nVarRegisterListener;
        rVar.f12790e = new o3.d[]{zzaq.zzn};
        rVar.f12786a = 37601;
        return doRegisterEventListener(rVar.b());
    }

    @Override // com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient
    public final com.google.android.gms.tasks.h requestDeviceSuggestions() {
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12807a = new z(this, 0);
        xVarBuilder.f12809c = new o3.d[]{zzaq.zzn};
        xVarBuilder.f12810d = 37603;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient
    public final com.google.android.gms.tasks.h unregisterCallback(DeviceSuggestionsCallback deviceSuggestionsCallback) {
        com.google.android.gms.common.internal.t.j(deviceSuggestionsCallback, "Listener must not be null");
        com.google.android.gms.common.internal.t.g("DeviceSuggestionsCallback", "Listener type must not be empty");
        return doUnregisterEventListener(new com.google.android.gms.common.api.internal.l(deviceSuggestionsCallback, "DeviceSuggestionsCallback"), 37602);
    }
}
