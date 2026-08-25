package com.google.android.gms.auth.api.identity;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import java.util.List;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class AuthorizationRequest extends a implements ReflectedParcelable {
    public static final Parcelable.Creator<AuthorizationRequest> CREATOR = new zbe();
    private final List zba;
    private final String zbb;
    private final boolean zbc;
    private final boolean zbd;
    private final Account zbe;
    private final String zbf;
    private final String zbg;
    private final boolean zbh;
    private final Bundle zbi;

    public static final class Builder {
        private List zba;
        private String zbb;
        private boolean zbc;
        private boolean zbd;
        private Account zbe;
        private String zbf;
        private String zbg;
        private boolean zbh;
        private Bundle zbi;

        private final String zbd(String str) {
            t.i(str);
            String str2 = this.zbb;
            boolean z = true;
            if (str2 != null && !str2.equals(str)) {
                z = false;
            }
            t.a("two different server client ids provided", z);
            return str;
        }

        public AuthorizationRequest build() {
            return new AuthorizationRequest(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg, this.zbh, this.zbi);
        }

        public Builder filterByHostedDomain(String str) {
            t.f(str);
            this.zbf = str;
            return this;
        }

        public Builder requestOfflineAccess(String str) {
            requestOfflineAccess(str, false);
            return this;
        }

        public Builder setAccount(Account account) {
            t.i(account);
            this.zbe = account;
            return this;
        }

        public Builder setRequestedScopes(List<Scope> list) {
            boolean z = false;
            if (list != null && !list.isEmpty()) {
                z = true;
            }
            t.a("requestedScopes cannot be null or empty", z);
            this.zba = list;
            return this;
        }

        public final Builder zba(zbd zbdVar, String str) {
            t.j(zbdVar, "Resource parameter cannot be null");
            t.j(str, "Resource parameter value cannot be null");
            if (this.zbi == null) {
                this.zbi = new Bundle();
            }
            this.zbi.putString(zbdVar.zbc, str);
            return this;
        }

        public final Builder zbb(String str) {
            zbd(str);
            this.zbb = str;
            this.zbd = true;
            return this;
        }

        public final Builder zbc(String str) {
            this.zbg = str;
            return this;
        }

        public Builder requestOfflineAccess(String str, boolean z) {
            zbd(str);
            this.zbb = str;
            this.zbc = true;
            this.zbh = z;
            return this;
        }
    }

    public AuthorizationRequest(List list, String str, boolean z, boolean z5, Account account, String str2, String str3, boolean z10, Bundle bundle) {
        boolean z11 = false;
        if (list != null && !list.isEmpty()) {
            z11 = true;
        }
        t.a("requestedScopes cannot be null or empty", z11);
        this.zba = list;
        this.zbb = str;
        this.zbc = z;
        this.zbd = z5;
        this.zbe = account;
        this.zbf = str2;
        this.zbg = str3;
        this.zbh = z10;
        this.zbi = bundle;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zba(AuthorizationRequest authorizationRequest) {
        zbd zbdVar;
        t.i(authorizationRequest);
        Builder builder = builder();
        builder.setRequestedScopes(authorizationRequest.getRequestedScopes());
        Bundle bundle = authorizationRequest.zbi;
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                String string = bundle.getString(str);
                zbd[] zbdVarArrValues = zbd.values();
                int length = zbdVarArrValues.length;
                int i10 = 0;
                while (true) {
                    if (i10 >= length) {
                        zbdVar = null;
                        break;
                    }
                    zbdVar = zbdVarArrValues[i10];
                    if (zbdVar.zbc.equals(str)) {
                        break;
                    }
                    i10++;
                }
                if (string != null && zbdVar != null) {
                    builder.zba(zbdVar, string);
                }
            }
        }
        boolean zIsForceCodeForRefreshToken = authorizationRequest.isForceCodeForRefreshToken();
        String str2 = authorizationRequest.zbg;
        String hostedDomain = authorizationRequest.getHostedDomain();
        Account account = authorizationRequest.getAccount();
        String serverClientId = authorizationRequest.getServerClientId();
        if (str2 != null) {
            builder.zbc(str2);
        }
        if (hostedDomain != null) {
            builder.filterByHostedDomain(hostedDomain);
        }
        if (account != null) {
            builder.setAccount(account);
        }
        if (authorizationRequest.zbd && serverClientId != null) {
            builder.zbb(serverClientId);
        }
        if (authorizationRequest.isOfflineAccessRequested() && serverClientId != null) {
            builder.requestOfflineAccess(serverClientId, zIsForceCodeForRefreshToken);
        }
        return builder;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthorizationRequest)) {
            return false;
        }
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) obj;
        if (this.zba.size() == authorizationRequest.zba.size() && this.zba.containsAll(authorizationRequest.zba)) {
            Bundle bundle = authorizationRequest.zbi;
            Bundle bundle2 = this.zbi;
            if (bundle2 == null) {
                if (bundle == null) {
                    bundle = null;
                }
                return false;
            }
            if (bundle2 == null || bundle != null) {
                if (bundle2 != null) {
                    if (bundle2.size() != bundle.size()) {
                        return false;
                    }
                    for (String str : this.zbi.keySet()) {
                        if (!t.l(this.zbi.getString(str), bundle.getString(str))) {
                            return false;
                        }
                    }
                }
                if (this.zbc == authorizationRequest.zbc && this.zbh == authorizationRequest.zbh && this.zbd == authorizationRequest.zbd && t.l(this.zbb, authorizationRequest.zbb) && t.l(this.zbe, authorizationRequest.zbe) && t.l(this.zbf, authorizationRequest.zbf) && t.l(this.zbg, authorizationRequest.zbg)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public Account getAccount() {
        return this.zbe;
    }

    public String getHostedDomain() {
        return this.zbf;
    }

    public List<Scope> getRequestedScopes() {
        return this.zba;
    }

    public String getServerClientId() {
        return this.zbb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba, this.zbb, Boolean.valueOf(this.zbc), Boolean.valueOf(this.zbh), Boolean.valueOf(this.zbd), this.zbe, this.zbf, this.zbg, this.zbi});
    }

    public boolean isForceCodeForRefreshToken() {
        return this.zbh;
    }

    public boolean isOfflineAccessRequested() {
        return this.zbc;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.k0(parcel, 1, getRequestedScopes(), false);
        a.a.g0(parcel, 2, getServerClientId(), false);
        boolean zIsOfflineAccessRequested = isOfflineAccessRequested();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(zIsOfflineAccessRequested ? 1 : 0);
        boolean z = this.zbd;
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(z ? 1 : 0);
        a.a.f0(parcel, 5, getAccount(), i10, false);
        a.a.g0(parcel, 6, getHostedDomain(), false);
        a.a.g0(parcel, 7, this.zbg, false);
        boolean zIsForceCodeForRefreshToken = isForceCodeForRefreshToken();
        a.a.l0(parcel, 8, 4);
        parcel.writeInt(zIsForceCodeForRefreshToken ? 1 : 0);
        a.a.Z(parcel, 9, this.zbi, false);
        a.a.n0(iM0, parcel);
    }
}
