package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class GoogleSignInOptionsExtensionParcelable extends a {
    public static final Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> CREATOR = new zaa();
    final int zaa;
    private int zab;
    private Bundle zac;

    public GoogleSignInOptionsExtensionParcelable(int i10, int i11, Bundle bundle) {
        this.zaa = i10;
        this.zab = i11;
        this.zac = bundle;
    }

    public int getType() {
        return this.zab;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zaa;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        int type = getType();
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(type);
        a.a.Z(parcel, 3, this.zac, false);
        a.a.n0(iM0, parcel);
    }

    public GoogleSignInOptionsExtensionParcelable(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        this(1, googleSignInOptionsExtension.getExtensionType(), googleSignInOptionsExtension.toBundle());
    }
}
