package com.google.android.gms.internal.auth;

import android.os.Parcel;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class m4 extends w implements g4 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f13075c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m4(com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.auth.account.data.IGetAccountChangeEventsCallback");
        this.f13075c = iVar;
    }

    @Override // com.google.android.gms.internal.auth.w
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 != 2) {
            return false;
        }
        Status status = (Status) o0.a(parcel, Status.CREATOR);
        AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) o0.a(parcel, AccountChangeEventsResponse.CREATOR);
        o0.b(parcel);
        c.c(status, accountChangeEventsResponse, this.f13075c);
        return true;
    }
}
