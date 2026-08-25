package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class SaveAccountLinkingTokenResult extends a {
    public static final Parcelable.Creator<SaveAccountLinkingTokenResult> CREATOR = new zbr();
    private final PendingIntent zba;

    public SaveAccountLinkingTokenResult(PendingIntent pendingIntent) {
        this.zba = pendingIntent;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SaveAccountLinkingTokenResult) {
            return t.l(this.zba, ((SaveAccountLinkingTokenResult) obj).zba);
        }
        return false;
    }

    public PendingIntent getPendingIntent() {
        return this.zba;
    }

    public boolean hasResolution() {
        return this.zba != null;
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
