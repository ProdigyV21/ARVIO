package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import java.util.List;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class AuthorizationResult extends a {
    public static final Parcelable.Creator<AuthorizationResult> CREATOR = new zbf();
    private final String zba;
    private final String zbb;
    private final String zbc;
    private final List zbd;
    private final GoogleSignInAccount zbe;
    private final PendingIntent zbf;

    public AuthorizationResult(String str, String str2, String str3, List<String> list, GoogleSignInAccount googleSignInAccount, PendingIntent pendingIntent) {
        this.zba = str;
        this.zbb = str2;
        this.zbc = str3;
        t.i(list);
        this.zbd = list;
        this.zbf = pendingIntent;
        this.zbe = googleSignInAccount;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthorizationResult)) {
            return false;
        }
        AuthorizationResult authorizationResult = (AuthorizationResult) obj;
        return t.l(this.zba, authorizationResult.zba) && t.l(this.zbb, authorizationResult.zbb) && t.l(this.zbc, authorizationResult.zbc) && t.l(this.zbd, authorizationResult.zbd) && t.l(this.zbf, authorizationResult.zbf) && t.l(this.zbe, authorizationResult.zbe);
    }

    public String getAccessToken() {
        return this.zbb;
    }

    public List<String> getGrantedScopes() {
        return this.zbd;
    }

    public PendingIntent getPendingIntent() {
        return this.zbf;
    }

    public String getServerAuthCode() {
        return this.zba;
    }

    public boolean hasResolution() {
        return this.zbf != null;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba, this.zbb, this.zbc, this.zbd, this.zbf, this.zbe});
    }

    public GoogleSignInAccount toGoogleSignInAccount() {
        return this.zbe;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, getServerAuthCode(), false);
        a.a.g0(parcel, 2, getAccessToken(), false);
        a.a.g0(parcel, 3, this.zbc, false);
        a.a.i0(parcel, getGrantedScopes(), 4);
        a.a.f0(parcel, 5, toGoogleSignInAccount(), i10, false);
        a.a.f0(parcel, 6, getPendingIntent(), i10, false);
        a.a.n0(iM0, parcel);
    }
}
