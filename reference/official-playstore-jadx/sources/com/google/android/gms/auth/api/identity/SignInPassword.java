package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class SignInPassword extends a {
    public static final Parcelable.Creator<SignInPassword> CREATOR = new zbw();
    private final String zba;
    private final String zbb;

    public SignInPassword(String str, String str2) {
        t.j(str, "Account identifier cannot be null");
        String strTrim = str.trim();
        t.g(strTrim, "Account identifier cannot be empty");
        this.zba = strTrim;
        t.f(str2);
        this.zbb = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignInPassword)) {
            return false;
        }
        SignInPassword signInPassword = (SignInPassword) obj;
        return t.l(this.zba, signInPassword.zba) && t.l(this.zbb, signInPassword.zbb);
    }

    public String getId() {
        return this.zba;
    }

    public String getPassword() {
        return this.zbb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba, this.zbb});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, getId(), false);
        a.a.g0(parcel, 2, getPassword(), false);
        a.a.n0(iM0, parcel);
    }
}
