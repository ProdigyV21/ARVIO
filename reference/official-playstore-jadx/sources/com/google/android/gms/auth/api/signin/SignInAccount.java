package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.t;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class SignInAccount extends a implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInAccount> CREATOR = new zbc();

    @Deprecated
    final String zba;

    @Deprecated
    final String zbb;
    private final GoogleSignInAccount zbc;

    public SignInAccount(String str, GoogleSignInAccount googleSignInAccount, String str2) {
        this.zbc = googleSignInAccount;
        t.g(str, "8.3 and 8.4 SDKs require non-null email");
        this.zba = str;
        t.g(str2, "8.3 and 8.4 SDKs require non-null userId");
        this.zbb = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        String str = this.zba;
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 4, str, false);
        a.a.f0(parcel, 7, this.zbc, i10, false);
        a.a.g0(parcel, 8, this.zbb, false);
        a.a.n0(iM0, parcel);
    }

    public final GoogleSignInAccount zba() {
        return this.zbc;
    }
}
