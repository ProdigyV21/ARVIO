package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import java.util.List;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class AccountChangeEventsResponse extends a {
    public static final Parcelable.Creator<AccountChangeEventsResponse> CREATOR = new zzc();
    final int zza;
    final List zzb;

    public AccountChangeEventsResponse(int i10, List list) {
        this.zza = i10;
        t.i(list);
        this.zzb = list;
    }

    public List<AccountChangeEvent> getEvents() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zza;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        a.a.k0(parcel, 2, this.zzb, false);
        a.a.n0(iM0, parcel);
    }

    public AccountChangeEventsResponse(List<AccountChangeEvent> list) {
        this.zza = 1;
        t.i(list);
        this.zzb = list;
    }
}
