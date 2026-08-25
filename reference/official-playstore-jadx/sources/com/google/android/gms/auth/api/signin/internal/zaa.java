package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zaa implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        Bundle bundleP = null;
        int iZ0 = 0;
        int iZ02 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                iZ0 = d.z0(i10, parcel);
            } else if (c10 == 2) {
                iZ02 = d.z0(i10, parcel);
            } else if (c10 != 3) {
                d.J0(i10, parcel);
            } else {
                bundleP = d.P(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new GoogleSignInOptionsExtensionParcelable(iZ0, iZ02, bundleP);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new GoogleSignInOptionsExtensionParcelable[i10];
    }
}
