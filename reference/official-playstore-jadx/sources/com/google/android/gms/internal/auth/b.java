package com.google.android.gms.internal.auth;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends w implements f4 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f12995c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f12996d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(int i10, com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.auth.account.data.IBundleCallback");
        this.f12995c = i10;
        this.f12996d = iVar;
    }

    @Override // com.google.android.gms.internal.auth.f4
    public final void y(Status status, Bundle bundle) {
        switch (this.f12995c) {
            case 0:
                c.c(status, bundle, this.f12996d);
                break;
            default:
                c.c(status, bundle, this.f12996d);
                break;
        }
    }

    @Override // com.google.android.gms.internal.auth.w
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 != 2) {
            return false;
        }
        Status status = (Status) o0.a(parcel, Status.CREATOR);
        Bundle bundle = (Bundle) o0.a(parcel, Bundle.CREATOR);
        o0.b(parcel);
        y(status, bundle);
        return true;
    }
}
