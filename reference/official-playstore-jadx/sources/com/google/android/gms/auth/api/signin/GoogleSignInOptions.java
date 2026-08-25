package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.t;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class GoogleSignInOptions extends a implements d.e, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    public static final Scope zaa = new Scope(1, "profile");
    public static final Scope zab = new Scope(1, "email");
    public static final Scope zac = new Scope(1, "openid");
    public static final Scope zad;
    public static final Scope zae;
    private static final Comparator zaq;
    final int zaf;
    private final ArrayList zag;
    private Account zah;
    private boolean zai;
    private final boolean zaj;
    private final boolean zak;
    private String zal;
    private String zam;
    private ArrayList zan;
    private String zao;
    private Map zap;

    @Deprecated
    public static final class Builder {
        private Set zaa;
        private boolean zab;
        private boolean zac;
        private boolean zad;
        private String zae;
        private Account zaf;
        private String zag;
        private Map zah;
        private String zai;

        public Builder() {
            this.zaa = new HashSet();
            this.zah = new HashMap();
        }

        private final String zaa(String str) {
            t.f(str);
            String str2 = this.zae;
            boolean z = true;
            if (str2 != null && !str2.equals(str)) {
                z = false;
            }
            t.a("two different server client ids provided", z);
            return str;
        }

        public Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (this.zah.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            List<Scope> impliedScopes = googleSignInOptionsExtension.getImpliedScopes();
            if (impliedScopes != null) {
                this.zaa.addAll(impliedScopes);
            }
            this.zah.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
            return this;
        }

        public GoogleSignInOptions build() {
            if (this.zaa.contains(GoogleSignInOptions.zae)) {
                Set set = this.zaa;
                Scope scope = GoogleSignInOptions.zad;
                if (set.contains(scope)) {
                    this.zaa.remove(scope);
                }
            }
            if (this.zad && (this.zaf == null || !this.zaa.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.zaa), this.zaf, this.zad, this.zab, this.zac, this.zae, this.zag, this.zah, this.zai, null);
        }

        public Builder requestEmail() {
            this.zaa.add(GoogleSignInOptions.zab);
            return this;
        }

        public Builder requestId() {
            this.zaa.add(GoogleSignInOptions.zac);
            return this;
        }

        public Builder requestIdToken(String str) {
            this.zad = true;
            zaa(str);
            this.zae = str;
            return this;
        }

        public Builder requestProfile() {
            this.zaa.add(GoogleSignInOptions.zaa);
            return this;
        }

        public Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zaa.add(scope);
            this.zaa.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public Builder requestServerAuthCode(String str) {
            requestServerAuthCode(str, false);
            return this;
        }

        public Builder setAccountName(String str) {
            t.f(str);
            this.zaf = new Account(str, "com.google");
            return this;
        }

        public Builder setHostedDomain(String str) {
            t.f(str);
            this.zag = str;
            return this;
        }

        public Builder setLogSessionId(String str) {
            this.zai = str;
            return this;
        }

        public Builder requestServerAuthCode(String str, boolean z) {
            this.zab = true;
            zaa(str);
            this.zae = str;
            this.zac = z;
            return this;
        }

        public Builder(GoogleSignInOptions googleSignInOptions) {
            this.zaa = new HashSet();
            this.zah = new HashMap();
            t.i(googleSignInOptions);
            this.zaa = new HashSet(googleSignInOptions.zad());
            this.zab = googleSignInOptions.zag();
            this.zac = googleSignInOptions.zah();
            this.zad = googleSignInOptions.zaf();
            this.zae = googleSignInOptions.zai();
            this.zaf = googleSignInOptions.zae();
            this.zag = googleSignInOptions.zaj();
            this.zah = GoogleSignInOptions.zam(googleSignInOptions.zak());
            this.zai = googleSignInOptions.zal();
        }
    }

    static {
        Scope scope = new Scope(1, "https://www.googleapis.com/auth/games_lite");
        zad = scope;
        zae = new Scope(1, "https://www.googleapis.com/auth/games");
        Builder builder = new Builder();
        builder.requestId();
        builder.requestProfile();
        DEFAULT_SIGN_IN = builder.build();
        Builder builder2 = new Builder();
        builder2.requestScopes(scope, new Scope[0]);
        DEFAULT_GAMES_SIGN_IN = builder2.build();
        CREATOR = new zad();
        zaq = new zac();
    }

    private GoogleSignInOptions(int i10, ArrayList arrayList, Account account, boolean z, boolean z5, boolean z10, String str, String str2, Map map, String str3) {
        this.zaf = i10;
        this.zag = arrayList;
        this.zah = account;
        this.zai = z;
        this.zaj = z5;
        this.zak = z10;
        this.zal = str;
        this.zam = str2;
        this.zan = new ArrayList(map.values());
        this.zap = map;
        this.zao = str3;
    }

    public static GoogleSignInOptions zaa(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            hashSet.add(new Scope(1, jSONArray.getString(i10)));
        }
        String strOptString = jSONObject.has("accountName") ? jSONObject.optString("accountName") : null;
        return new GoogleSignInOptions(3, new ArrayList(hashSet), !TextUtils.isEmpty(strOptString) ? new Account(strOptString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.has("serverClientId") ? jSONObject.optString("serverClientId") : null, jSONObject.has("hostedDomain") ? jSONObject.optString("hostedDomain") : null, new HashMap(), (String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map zam(List list) {
        HashMap map = new HashMap();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable = (GoogleSignInOptionsExtensionParcelable) it.next();
                map.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable);
            }
        }
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0050 A[Catch: ClassCastException -> 0x008e, TryCatch #0 {ClassCastException -> 0x008e, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0033, B:17:0x0037, B:22:0x0048, B:24:0x0050, B:30:0x0068, B:32:0x0070, B:34:0x0078, B:36:0x0080, B:27:0x005b, B:20:0x003e), top: B:42:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b A[Catch: ClassCastException -> 0x008e, TryCatch #0 {ClassCastException -> 0x008e, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0033, B:17:0x0037, B:22:0x0048, B:24:0x0050, B:30:0x0068, B:32:0x0070, B:34:0x0078, B:36:0x0080, B:27:0x005b, B:20:0x003e), top: B:42:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r5 = (com.google.android.gms.auth.api.signin.GoogleSignInOptions) r5     // Catch: java.lang.ClassCastException -> L8e
            java.util.ArrayList r1 = r4.zan     // Catch: java.lang.ClassCastException -> L8e
            boolean r1 = r1.isEmpty()     // Catch: java.lang.ClassCastException -> L8e
            if (r1 == 0) goto L8e
            java.util.ArrayList r1 = r5.zan     // Catch: java.lang.ClassCastException -> L8e
            boolean r1 = r1.isEmpty()     // Catch: java.lang.ClassCastException -> L8e
            if (r1 != 0) goto L18
            goto L8e
        L18:
            java.util.ArrayList r1 = r4.zag     // Catch: java.lang.ClassCastException -> L8e
            int r2 = r1.size()     // Catch: java.lang.ClassCastException -> L8e
            java.util.ArrayList r3 = r5.getScopes()     // Catch: java.lang.ClassCastException -> L8e
            int r3 = r3.size()     // Catch: java.lang.ClassCastException -> L8e
            if (r2 != r3) goto L8e
            java.util.ArrayList r2 = r5.getScopes()     // Catch: java.lang.ClassCastException -> L8e
            boolean r1 = r1.containsAll(r2)     // Catch: java.lang.ClassCastException -> L8e
            if (r1 != 0) goto L33
            goto L8e
        L33:
            android.accounts.Account r1 = r4.zah     // Catch: java.lang.ClassCastException -> L8e
            if (r1 != 0) goto L3e
            android.accounts.Account r1 = r5.getAccount()     // Catch: java.lang.ClassCastException -> L8e
            if (r1 != 0) goto L8e
            goto L48
        L3e:
            android.accounts.Account r2 = r5.getAccount()     // Catch: java.lang.ClassCastException -> L8e
            boolean r1 = r1.equals(r2)     // Catch: java.lang.ClassCastException -> L8e
            if (r1 == 0) goto L8e
        L48:
            java.lang.String r1 = r4.zal     // Catch: java.lang.ClassCastException -> L8e
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.ClassCastException -> L8e
            if (r1 == 0) goto L5b
            java.lang.String r1 = r5.getServerClientId()     // Catch: java.lang.ClassCastException -> L8e
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.ClassCastException -> L8e
            if (r1 == 0) goto L8e
            goto L68
        L5b:
            java.lang.String r1 = r4.zal     // Catch: java.lang.ClassCastException -> L8e
            java.lang.String r2 = r5.getServerClientId()     // Catch: java.lang.ClassCastException -> L8e
            boolean r1 = r1.equals(r2)     // Catch: java.lang.ClassCastException -> L8e
            if (r1 != 0) goto L68
            goto L8e
        L68:
            boolean r1 = r4.zak     // Catch: java.lang.ClassCastException -> L8e
            boolean r2 = r5.isForceCodeForRefreshToken()     // Catch: java.lang.ClassCastException -> L8e
            if (r1 != r2) goto L8e
            boolean r1 = r4.zai     // Catch: java.lang.ClassCastException -> L8e
            boolean r2 = r5.isIdTokenRequested()     // Catch: java.lang.ClassCastException -> L8e
            if (r1 != r2) goto L8e
            boolean r1 = r4.zaj     // Catch: java.lang.ClassCastException -> L8e
            boolean r2 = r5.isServerAuthCodeRequested()     // Catch: java.lang.ClassCastException -> L8e
            if (r1 != r2) goto L8e
            java.lang.String r1 = r4.zao     // Catch: java.lang.ClassCastException -> L8e
            java.lang.String r5 = r5.getLogSessionId()     // Catch: java.lang.ClassCastException -> L8e
            boolean r5 = android.text.TextUtils.equals(r1, r5)     // Catch: java.lang.ClassCastException -> L8e
            if (r5 == 0) goto L8e
            r5 = 1
            return r5
        L8e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.equals(java.lang.Object):boolean");
    }

    public Account getAccount() {
        return this.zah;
    }

    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zan;
    }

    public String getLogSessionId() {
        return this.zao;
    }

    public Scope[] getScopeArray() {
        ArrayList arrayList = this.zag;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }

    public ArrayList<Scope> getScopes() {
        return new ArrayList<>(this.zag);
    }

    public String getServerClientId() {
        return this.zal;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.zag;
        int size = arrayList2.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(((Scope) arrayList2.get(i10)).f12669l);
        }
        Collections.sort(arrayList);
        HashAccumulator hashAccumulator = new HashAccumulator();
        hashAccumulator.addObject(arrayList);
        hashAccumulator.addObject(this.zah);
        hashAccumulator.addObject(this.zal);
        hashAccumulator.zaa(this.zak);
        hashAccumulator.zaa(this.zai);
        hashAccumulator.zaa(this.zaj);
        hashAccumulator.addObject(this.zao);
        return hashAccumulator.hash();
    }

    public boolean isForceCodeForRefreshToken() {
        return this.zak;
    }

    public boolean isIdTokenRequested() {
        return this.zai;
    }

    public boolean isServerAuthCodeRequested() {
        return this.zaj;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int i11 = this.zaf;
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        a.a.k0(parcel, 2, getScopes(), false);
        a.a.f0(parcel, 3, getAccount(), i10, false);
        boolean zIsIdTokenRequested = isIdTokenRequested();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(zIsIdTokenRequested ? 1 : 0);
        boolean zIsServerAuthCodeRequested = isServerAuthCodeRequested();
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(zIsServerAuthCodeRequested ? 1 : 0);
        boolean zIsForceCodeForRefreshToken = isForceCodeForRefreshToken();
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(zIsForceCodeForRefreshToken ? 1 : 0);
        a.a.g0(parcel, 7, getServerClientId(), false);
        a.a.g0(parcel, 8, this.zam, false);
        a.a.k0(parcel, 9, getExtensions(), false);
        a.a.g0(parcel, 10, getLogSessionId(), false);
        a.a.n0(iM0, parcel);
    }

    public final String zab() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            ArrayList arrayList = this.zag;
            Collections.sort(arrayList, zaq);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Scope) it.next()).f12669l);
            }
            jSONObject.put("scopes", jSONArray);
            Account account = this.zah;
            if (account != null) {
                jSONObject.put("accountName", account.name);
            }
            jSONObject.put("idTokenRequested", this.zai);
            jSONObject.put("forceCodeForRefreshToken", this.zak);
            jSONObject.put("serverAuthRequested", this.zaj);
            if (!TextUtils.isEmpty(this.zal)) {
                jSONObject.put("serverClientId", this.zal);
            }
            if (!TextUtils.isEmpty(this.zam)) {
                jSONObject.put("hostedDomain", this.zam);
            }
            return jSONObject.toString();
        } catch (JSONException e5) {
            throw new RuntimeException(e5);
        }
    }

    public final /* synthetic */ ArrayList zad() {
        return this.zag;
    }

    public final /* synthetic */ Account zae() {
        return this.zah;
    }

    public final /* synthetic */ boolean zaf() {
        return this.zai;
    }

    public final /* synthetic */ boolean zag() {
        return this.zaj;
    }

    public final /* synthetic */ boolean zah() {
        return this.zak;
    }

    public final /* synthetic */ String zai() {
        return this.zal;
    }

    public final /* synthetic */ String zaj() {
        return this.zam;
    }

    public final /* synthetic */ ArrayList zak() {
        return this.zan;
    }

    public final /* synthetic */ String zal() {
        return this.zao;
    }

    public /* synthetic */ GoogleSignInOptions(int i10, ArrayList arrayList, Account account, boolean z, boolean z5, boolean z10, String str, String str2, Map map, String str3, byte[] bArr) {
        this(3, arrayList, account, z, z5, z10, str, str2, map, str3);
    }

    public GoogleSignInOptions(int i10, ArrayList arrayList, Account account, boolean z, boolean z5, boolean z10, String str, String str2, ArrayList arrayList2, String str3) {
        this(i10, arrayList, account, z, z5, z10, str, str2, zam(arrayList2), str3);
    }
}
