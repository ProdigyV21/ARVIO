package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import c4.b;
import c4.c;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.f;
import com.google.android.gms.common.internal.t;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class GoogleSignInAccount extends a implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zab();
    public static final b zaa = c.f7401a;
    final List zab;
    private final String zac;
    private final String zad;
    private final String zae;
    private final String zaf;
    private final Uri zag;
    private String zah;
    private final long zai;
    private final String zaj;
    private final String zak;
    private final String zal;
    private final Set zam = new HashSet();

    public GoogleSignInAccount(String str, String str2, String str3, String str4, Uri uri, String str5, long j10, String str6, List list, String str7, String str8) {
        this.zac = str;
        this.zad = str2;
        this.zae = str3;
        this.zaf = str4;
        this.zag = uri;
        this.zah = str5;
        this.zai = j10;
        this.zaj = str6;
        this.zab = list;
        this.zak = str7;
        this.zal = str8;
    }

    public static GoogleSignInAccount createDefault() {
        return zae(new Account(f.DEFAULT_ACCOUNT, "com.google"), new HashSet());
    }

    public static GoogleSignInAccount fromAccount(Account account) {
        return zae(account, new t.f(0));
    }

    public static GoogleSignInAccount fromAccountAndScopes(Account account, Scope scope, Scope... scopeArr) {
        t.i(account);
        t.i(scope);
        HashSet hashSet = new HashSet();
        hashSet.add(scope);
        hashSet.addAll(Arrays.asList(scopeArr));
        return zae(account, hashSet);
    }

    public static GoogleSignInAccount zaa(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("photoUrl");
        Uri uri = !TextUtils.isEmpty(strOptString) ? Uri.parse(strOptString) : null;
        long j10 = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            hashSet.add(new Scope(1, jSONArray.getString(i10)));
        }
        GoogleSignInAccount googleSignInAccountZab = zab(jSONObject.optString(TtmlNode.ATTR_ID), jSONObject.has("tokenId") ? jSONObject.optString("tokenId") : null, jSONObject.has("email") ? jSONObject.optString("email") : null, jSONObject.has("displayName") ? jSONObject.optString("displayName") : null, jSONObject.has("givenName") ? jSONObject.optString("givenName") : null, jSONObject.has("familyName") ? jSONObject.optString("familyName") : null, uri, Long.valueOf(j10), jSONObject.getString("obfuscatedIdentifier"), hashSet);
        googleSignInAccountZab.zah = jSONObject.has("serverAuthCode") ? jSONObject.optString("serverAuthCode") : null;
        return googleSignInAccountZab;
    }

    public static GoogleSignInAccount zab(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Long l10, String str7, Set set) {
        long jLongValue = l10.longValue();
        t.f(str7);
        t.i(set);
        return new GoogleSignInAccount(str, str2, str3, str4, uri, null, jLongValue, str7, new ArrayList(set), str5, str6);
    }

    private static GoogleSignInAccount zae(Account account, Set set) {
        return zab(null, null, account.name, null, null, null, null, 0L, account.name, set);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.zaj.equals(this.zaj) && googleSignInAccount.getRequestedScopes().equals(getRequestedScopes());
    }

    public Account getAccount() {
        String str = this.zae;
        if (str == null) {
            return null;
        }
        return new Account(str, "com.google");
    }

    public String getDisplayName() {
        return this.zaf;
    }

    public String getEmail() {
        return this.zae;
    }

    public String getFamilyName() {
        return this.zal;
    }

    public String getGivenName() {
        return this.zak;
    }

    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.zab);
    }

    public String getId() {
        return this.zac;
    }

    public String getIdToken() {
        return this.zad;
    }

    public Uri getPhotoUrl() {
        return this.zag;
    }

    public Set<Scope> getRequestedScopes() {
        HashSet hashSet = new HashSet(this.zab);
        hashSet.addAll(this.zam);
        return hashSet;
    }

    public String getServerAuthCode() {
        return this.zah;
    }

    public int hashCode() {
        return ((this.zaj.hashCode() + 527) * 31) + getRequestedScopes().hashCode();
    }

    public boolean isExpired() {
        return zaa.currentTimeMillis() / 1000 >= this.zai + (-300);
    }

    public GoogleSignInAccount requestExtraScopes(Scope... scopeArr) {
        if (scopeArr != null) {
            Collections.addAll(this.zam, scopeArr);
        }
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, getId(), false);
        a.a.g0(parcel, 3, getIdToken(), false);
        a.a.g0(parcel, 4, getEmail(), false);
        a.a.g0(parcel, 5, getDisplayName(), false);
        a.a.f0(parcel, 6, getPhotoUrl(), i10, false);
        a.a.g0(parcel, 7, getServerAuthCode(), false);
        long j10 = this.zai;
        a.a.l0(parcel, 8, 8);
        parcel.writeLong(j10);
        a.a.g0(parcel, 9, this.zaj, false);
        a.a.k0(parcel, 10, this.zab, false);
        a.a.g0(parcel, 11, getGivenName(), false);
        a.a.g0(parcel, 12, getFamilyName(), false);
        a.a.n0(iM0, parcel);
    }

    public final String zac() {
        return this.zaj;
    }

    public final String zad() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put(TtmlNode.ATTR_ID, getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            Uri photoUrl = getPhotoUrl();
            if (photoUrl != null) {
                jSONObject.put("photoUrl", photoUrl.toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.zai);
            jSONObject.put("obfuscatedIdentifier", this.zaj);
            JSONArray jSONArray = new JSONArray();
            List list = this.zab;
            Scope[] scopeArr = (Scope[]) list.toArray(new Scope[list.size()]);
            Arrays.sort(scopeArr, zaa.zaa);
            for (Scope scope : scopeArr) {
                jSONArray.put(scope.f12669l);
            }
            jSONObject.put("grantedScopes", jSONArray);
            jSONObject.remove("serverAuthCode");
            return jSONObject.toString();
        } catch (JSONException e5) {
            throw new RuntimeException(e5);
        }
    }
}
