package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class BeginSignInResult extends a {
    public static final Parcelable.Creator<BeginSignInResult> CREATOR = new zbh();
    private final PendingIntent zba;

    public BeginSignInResult(PendingIntent pendingIntent) {
        t.i(pendingIntent);
        this.zba = pendingIntent;
    }

    public PendingIntent getPendingIntent() {
        return this.zba;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 1, getPendingIntent(), i10, false);
        a.a.n0(iM0, parcel);
    }
}
