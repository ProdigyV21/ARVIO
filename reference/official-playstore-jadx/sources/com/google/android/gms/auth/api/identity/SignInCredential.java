package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import i4.r;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class SignInCredential extends a {
    public static final Parcelable.Creator<SignInCredential> CREATOR = new zbu();
    private final String zba;
    private final String zbb;
    private final String zbc;
    private final String zbd;
    private final Uri zbe;
    private final String zbf;
    private final String zbg;
    private final String zbh;
    private final r zbi;

    public SignInCredential(String str, String str2, String str3, String str4, Uri uri, String str5, String str6, String str7, r rVar) {
        t.i(str);
        this.zba = str;
        this.zbb = str2;
        this.zbc = str3;
        this.zbd = str4;
        this.zbe = uri;
        this.zbf = str5;
        this.zbg = str6;
        this.zbh = str7;
        this.zbi = rVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignInCredential)) {
            return false;
        }
        SignInCredential signInCredential = (SignInCredential) obj;
        return t.l(this.zba, signInCredential.zba) && t.l(this.zbb, signInCredential.zbb) && t.l(this.zbc, signInCredential.zbc) && t.l(this.zbd, signInCredential.zbd) && t.l(this.zbe, signInCredential.zbe) && t.l(this.zbf, signInCredential.zbf) && t.l(this.zbg, signInCredential.zbg) && t.l(this.zbh, signInCredential.zbh) && t.l(this.zbi, signInCredential.zbi);
    }

    public String getDisplayName() {
        return this.zbb;
    }

    public String getFamilyName() {
        return this.zbd;
    }

    public String getGivenName() {
        return this.zbc;
    }

    public String getGoogleIdToken() {
        return this.zbg;
    }

    public String getId() {
        return this.zba;
    }

    public String getPassword() {
        return this.zbf;
    }

    @Deprecated
    public String getPhoneNumber() {
        return this.zbh;
    }

    public Uri getProfilePictureUri() {
        return this.zbe;
    }

    public r getPublicKeyCredential() {
        return this.zbi;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg, this.zbh, this.zbi});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, getId(), false);
        a.a.g0(parcel, 2, getDisplayName(), false);
        a.a.g0(parcel, 3, getGivenName(), false);
        a.a.g0(parcel, 4, getFamilyName(), false);
        a.a.f0(parcel, 5, getProfilePictureUri(), i10, false);
        a.a.g0(parcel, 6, getPassword(), false);
        a.a.g0(parcel, 7, getGoogleIdToken(), false);
        a.a.g0(parcel, 8, getPhoneNumber(), false);
        a.a.f0(parcel, 9, getPublicKeyCredential(), i10, false);
        a.a.n0(iM0, parcel);
    }
}
