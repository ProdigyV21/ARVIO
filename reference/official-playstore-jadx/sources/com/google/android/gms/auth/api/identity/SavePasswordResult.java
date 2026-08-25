package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class SavePasswordResult extends a {
    public static final Parcelable.Creator<SavePasswordResult> CREATOR = new zbt();
    private final PendingIntent zba;

    public SavePasswordResult(PendingIntent pendingIntent) {
        t.i(pendingIntent);
        this.zba = pendingIntent;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SavePasswordResult) {
            return t.l(this.zba, ((SavePasswordResult) obj).zba);
        }
        return false;
    }

    public PendingIntent getPendingIntent() {
        return this.zba;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 1, getPendingIntent(), i10, false);
        a.a.n0(iM0, parcel);
    }
}
