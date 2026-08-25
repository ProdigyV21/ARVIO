package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.t;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class SignInConfiguration extends a implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInConfiguration> CREATOR = new zbu();
    private final String zba;
    private final GoogleSignInOptions zbb;

    public SignInConfiguration(String str, GoogleSignInOptions googleSignInOptions) {
        t.f(str);
        this.zba = str;
        this.zbb = googleSignInOptions;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SignInConfiguration)) {
            return false;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
        if (this.zba.equals(signInConfiguration.zba)) {
            GoogleSignInOptions googleSignInOptions = this.zbb;
            GoogleSignInOptions googleSignInOptions2 = signInConfiguration.zbb;
            if (googleSignInOptions == null) {
                if (googleSignInOptions2 == null) {
                    return true;
                }
            } else if (googleSignInOptions.equals(googleSignInOptions2)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return new HashAccumulator().addObject(this.zba).addObject(this.zbb).hash();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        String str = this.zba;
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, str, false);
        a.a.f0(parcel, 5, this.zbb, i10, false);
        a.a.n0(iM0, parcel);
    }

    public final GoogleSignInOptions zba() {
        return this.zbb;
    }
}
