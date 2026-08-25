package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z implements com.google.android.gms.common.api.internal.t {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13700i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ a0 f13701l;

    public /* synthetic */ z(a0 a0Var, int i10) {
        this.f13700i = i10;
        this.f13701l = a0Var;
    }

    @Override // com.google.android.gms.common.api.internal.t
    public final void accept(Object obj, Object obj2) throws RemoteException {
        com.google.android.gms.tasks.i iVar = (com.google.android.gms.tasks.i) obj2;
        t tVar = (t) obj;
        switch (this.f13700i) {
            case 0:
                com.google.android.gms.internal.auth.l4 l4Var = new com.google.android.gms.internal.auth.l4(this.f13701l, iVar, 3);
                v vVar = (v) tVar.getService();
                Parcel parcelZza = vVar.zza();
                a1.d(parcelZza, l4Var);
                vVar.zzc(3, parcelZza);
                break;
            default:
                com.google.android.gms.internal.auth.l4 l4Var2 = new com.google.android.gms.internal.auth.l4(this.f13701l, iVar, 4);
                v vVar2 = (v) tVar.getService();
                Parcel parcelZza2 = vVar2.zza();
                a1.d(parcelZza2, l4Var2);
                vVar2.zzc(4, parcelZza2);
                break;
        }
    }
}
