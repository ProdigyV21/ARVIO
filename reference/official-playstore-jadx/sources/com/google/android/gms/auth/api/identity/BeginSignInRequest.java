package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class BeginSignInRequest extends a {
    public static final Parcelable.Creator<BeginSignInRequest> CREATOR = new zbg();
    private final PasswordRequestOptions zba;
    private final GoogleIdTokenRequestOptions zbb;
    private final String zbc;
    private final boolean zbd;
    private final int zbe;
    private final PasskeysRequestOptions zbf;
    private final PasskeyJsonRequestOptions zbg;
    private final boolean zbh;

    @Deprecated
    public static final class Builder {
        private PasswordRequestOptions zba;
        private GoogleIdTokenRequestOptions zbb;
        private PasskeysRequestOptions zbc;
        private PasskeyJsonRequestOptions zbd;
        private String zbe;
        private boolean zbf;
        private int zbg;
        private boolean zbh;

        public Builder() {
            PasswordRequestOptions.Builder builder = PasswordRequestOptions.builder();
            builder.setSupported(false);
            this.zba = builder.build();
            GoogleIdTokenRequestOptions.Builder builder2 = GoogleIdTokenRequestOptions.builder();
            builder2.setSupported(false);
            this.zbb = builder2.build();
            PasskeysRequestOptions.Builder builder3 = PasskeysRequestOptions.builder();
            builder3.setSupported(false);
            this.zbc = builder3.build();
            PasskeyJsonRequestOptions.Builder builder4 = PasskeyJsonRequestOptions.builder();
            builder4.setSupported(false);
            this.zbd = builder4.build();
        }

        public BeginSignInRequest build() {
            return new BeginSignInRequest(this.zba, this.zbb, this.zbe, this.zbf, this.zbg, this.zbc, this.zbd, this.zbh);
        }

        public Builder setAutoSelectEnabled(boolean z) {
            this.zbf = z;
            return this;
        }

        public Builder setGoogleIdTokenRequestOptions(GoogleIdTokenRequestOptions googleIdTokenRequestOptions) {
            t.i(googleIdTokenRequestOptions);
            this.zbb = googleIdTokenRequestOptions;
            return this;
        }

        public Builder setPasskeyJsonSignInRequestOptions(PasskeyJsonRequestOptions passkeyJsonRequestOptions) {
            t.i(passkeyJsonRequestOptions);
            this.zbd = passkeyJsonRequestOptions;
            return this;
        }

        @Deprecated
        public Builder setPasskeysSignInRequestOptions(PasskeysRequestOptions passkeysRequestOptions) {
            t.i(passkeysRequestOptions);
            this.zbc = passkeysRequestOptions;
            return this;
        }

        public Builder setPasswordRequestOptions(PasswordRequestOptions passwordRequestOptions) {
            t.i(passwordRequestOptions);
            this.zba = passwordRequestOptions;
            return this;
        }

        public Builder setPreferImmediatelyAvailableCredentials(boolean z) {
            this.zbh = z;
            return this;
        }

        public final Builder zba(String str) {
            this.zbe = str;
            return this;
        }

        public final Builder zbb(int i10) {
            this.zbg = i10;
            return this;
        }
    }

    @Deprecated
    public static final class GoogleIdTokenRequestOptions extends a {
        public static final Parcelable.Creator<GoogleIdTokenRequestOptions> CREATOR = new zbm();
        private final boolean zba;
        private final String zbb;
        private final String zbc;
        private final boolean zbd;
        private final String zbe;
        private final List zbf;
        private final boolean zbg;

        public static final class Builder {
            private boolean zba = false;
            private String zbb = null;
            private String zbc = null;
            private boolean zbd = true;
            private String zbe = null;
            private List zbf = null;
            private boolean zbg = false;

            public Builder associateLinkedAccounts(String str, List<String> list) {
                t.j(str, "linkedServiceId must be provided if you want to associate linked accounts.");
                this.zbe = str;
                this.zbf = list;
                return this;
            }

            public GoogleIdTokenRequestOptions build() {
                return new GoogleIdTokenRequestOptions(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg);
            }

            public Builder setFilterByAuthorizedAccounts(boolean z) {
                this.zbd = z;
                return this;
            }

            public Builder setNonce(String str) {
                this.zbc = str;
                return this;
            }

            @Deprecated
            public Builder setRequestVerifiedPhoneNumber(boolean z) {
                this.zbg = z;
                return this;
            }

            public Builder setServerClientId(String str) {
                t.f(str);
                this.zbb = str;
                return this;
            }

            public Builder setSupported(boolean z) {
                this.zba = z;
                return this;
            }
        }

        public GoogleIdTokenRequestOptions(boolean z, String str, String str2, boolean z5, String str3, List list, boolean z10) {
            boolean z11 = true;
            if (z5 && z10) {
                z11 = false;
            }
            t.a("filterByAuthorizedAccounts and requestVerifiedPhoneNumber must not both be true; the Verified Phone Number feature only works in sign-ups.", z11);
            this.zba = z;
            if (z) {
                t.j(str, "serverClientId must be provided if Google ID tokens are requested");
            }
            this.zbb = str;
            this.zbc = str2;
            this.zbd = z5;
            Parcelable.Creator<BeginSignInRequest> creator = BeginSignInRequest.CREATOR;
            ArrayList arrayList = null;
            if (list != null && !list.isEmpty()) {
                arrayList = new ArrayList(list);
                Collections.sort(arrayList);
            }
            this.zbf = arrayList;
            this.zbe = str3;
            this.zbg = z10;
        }

        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof GoogleIdTokenRequestOptions)) {
                return false;
            }
            GoogleIdTokenRequestOptions googleIdTokenRequestOptions = (GoogleIdTokenRequestOptions) obj;
            return this.zba == googleIdTokenRequestOptions.zba && t.l(this.zbb, googleIdTokenRequestOptions.zbb) && t.l(this.zbc, googleIdTokenRequestOptions.zbc) && this.zbd == googleIdTokenRequestOptions.zbd && t.l(this.zbe, googleIdTokenRequestOptions.zbe) && t.l(this.zbf, googleIdTokenRequestOptions.zbf) && this.zbg == googleIdTokenRequestOptions.zbg;
        }

        public boolean filterByAuthorizedAccounts() {
            return this.zbd;
        }

        public List<String> getIdTokenDepositionScopes() {
            return this.zbf;
        }

        public String getLinkedServiceId() {
            return this.zbe;
        }

        public String getNonce() {
            return this.zbc;
        }

        public String getServerClientId() {
            return this.zbb;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zba), this.zbb, this.zbc, Boolean.valueOf(this.zbd), this.zbe, this.zbf, Boolean.valueOf(this.zbg)});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Deprecated
        public boolean requestVerifiedPhoneNumber() {
            return this.zbg;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i10) {
            int iM0 = a.a.m0(20293, parcel);
            boolean zIsSupported = isSupported();
            a.a.l0(parcel, 1, 4);
            parcel.writeInt(zIsSupported ? 1 : 0);
            a.a.g0(parcel, 2, getServerClientId(), false);
            a.a.g0(parcel, 3, getNonce(), false);
            boolean zFilterByAuthorizedAccounts = filterByAuthorizedAccounts();
            a.a.l0(parcel, 4, 4);
            parcel.writeInt(zFilterByAuthorizedAccounts ? 1 : 0);
            a.a.g0(parcel, 5, getLinkedServiceId(), false);
            a.a.i0(parcel, getIdTokenDepositionScopes(), 6);
            boolean zRequestVerifiedPhoneNumber = requestVerifiedPhoneNumber();
            a.a.l0(parcel, 7, 4);
            parcel.writeInt(zRequestVerifiedPhoneNumber ? 1 : 0);
            a.a.n0(iM0, parcel);
        }
    }

    @Deprecated
    public static final class PasskeyJsonRequestOptions extends a {
        public static final Parcelable.Creator<PasskeyJsonRequestOptions> CREATOR = new zbn();
        private final boolean zba;
        private final String zbb;

        public static final class Builder {
            private boolean zba = false;
            private String zbb;

            public PasskeyJsonRequestOptions build() {
                return new PasskeyJsonRequestOptions(this.zba, this.zbb);
            }

            public Builder setRequestJson(String str) {
                this.zbb = str;
                return this;
            }

            public Builder setSupported(boolean z) {
                this.zba = z;
                return this;
            }
        }

        public PasskeyJsonRequestOptions(boolean z, String str) {
            if (z) {
                t.i(str);
            }
            this.zba = z;
            this.zbb = str;
        }

        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PasskeyJsonRequestOptions)) {
                return false;
            }
            PasskeyJsonRequestOptions passkeyJsonRequestOptions = (PasskeyJsonRequestOptions) obj;
            return this.zba == passkeyJsonRequestOptions.zba && t.l(this.zbb, passkeyJsonRequestOptions.zbb);
        }

        public String getRequestJson() {
            return this.zbb;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zba), this.zbb});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i10) {
            int iM0 = a.a.m0(20293, parcel);
            boolean zIsSupported = isSupported();
            a.a.l0(parcel, 1, 4);
            parcel.writeInt(zIsSupported ? 1 : 0);
            a.a.g0(parcel, 2, getRequestJson(), false);
            a.a.n0(iM0, parcel);
        }
    }

    @Deprecated
    public static final class PasskeysRequestOptions extends a {
        public static final Parcelable.Creator<PasskeysRequestOptions> CREATOR = new zbo();
        private final boolean zba;
        private final byte[] zbb;
        private final String zbc;

        public static final class Builder {
            private boolean zba = false;
            private byte[] zbb;
            private String zbc;

            public PasskeysRequestOptions build() {
                return new PasskeysRequestOptions(this.zba, this.zbb, this.zbc);
            }

            public Builder setChallenge(byte[] bArr) {
                this.zbb = bArr;
                return this;
            }

            public Builder setRpId(String str) {
                this.zbc = str;
                return this;
            }

            public Builder setSupported(boolean z) {
                this.zba = z;
                return this;
            }
        }

        public PasskeysRequestOptions(boolean z, byte[] bArr, String str) {
            if (z) {
                t.i(bArr);
                t.i(str);
            }
            this.zba = z;
            this.zbb = bArr;
            this.zbc = str;
        }

        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PasskeysRequestOptions)) {
                return false;
            }
            PasskeysRequestOptions passkeysRequestOptions = (PasskeysRequestOptions) obj;
            return this.zba == passkeysRequestOptions.zba && Arrays.equals(this.zbb, passkeysRequestOptions.zbb) && Objects.equals(this.zbc, passkeysRequestOptions.zbc);
        }

        public byte[] getChallenge() {
            return this.zbb;
        }

        public String getRpId() {
            return this.zbc;
        }

        public int hashCode() {
            return Arrays.hashCode(this.zbb) + (Objects.hash(Boolean.valueOf(this.zba), this.zbc) * 31);
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i10) {
            int iM0 = a.a.m0(20293, parcel);
            boolean zIsSupported = isSupported();
            a.a.l0(parcel, 1, 4);
            parcel.writeInt(zIsSupported ? 1 : 0);
            a.a.a0(parcel, 2, getChallenge(), false);
            a.a.g0(parcel, 3, getRpId(), false);
            a.a.n0(iM0, parcel);
        }
    }

    @Deprecated
    public static final class PasswordRequestOptions extends a {
        public static final Parcelable.Creator<PasswordRequestOptions> CREATOR = new zbp();
        private final boolean zba;

        public static final class Builder {
            private boolean zba = false;

            public PasswordRequestOptions build() {
                return new PasswordRequestOptions(this.zba);
            }

            public Builder setSupported(boolean z) {
                this.zba = z;
                return this;
            }
        }

        public PasswordRequestOptions(boolean z) {
            this.zba = z;
        }

        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            return (obj instanceof PasswordRequestOptions) && this.zba == ((PasswordRequestOptions) obj).zba;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zba)});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i10) {
            int iM0 = a.a.m0(20293, parcel);
            boolean zIsSupported = isSupported();
            a.a.l0(parcel, 1, 4);
            parcel.writeInt(zIsSupported ? 1 : 0);
            a.a.n0(iM0, parcel);
        }
    }

    public BeginSignInRequest(PasswordRequestOptions passwordRequestOptions, GoogleIdTokenRequestOptions googleIdTokenRequestOptions, String str, boolean z, int i10, PasskeysRequestOptions passkeysRequestOptions, PasskeyJsonRequestOptions passkeyJsonRequestOptions, boolean z5) {
        t.i(passwordRequestOptions);
        this.zba = passwordRequestOptions;
        t.i(googleIdTokenRequestOptions);
        this.zbb = googleIdTokenRequestOptions;
        this.zbc = str;
        this.zbd = z;
        this.zbe = i10;
        if (passkeysRequestOptions == null) {
            PasskeysRequestOptions.Builder builder = PasskeysRequestOptions.builder();
            builder.setSupported(false);
            passkeysRequestOptions = builder.build();
        }
        this.zbf = passkeysRequestOptions;
        if (passkeyJsonRequestOptions == null) {
            PasskeyJsonRequestOptions.Builder builder2 = PasskeyJsonRequestOptions.builder();
            builder2.setSupported(false);
            passkeyJsonRequestOptions = builder2.build();
        }
        this.zbg = passkeyJsonRequestOptions;
        this.zbh = z5;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zba(BeginSignInRequest beginSignInRequest) {
        t.i(beginSignInRequest);
        Builder builder = builder();
        builder.setGoogleIdTokenRequestOptions(beginSignInRequest.getGoogleIdTokenRequestOptions());
        builder.setPasswordRequestOptions(beginSignInRequest.getPasswordRequestOptions());
        builder.setPasskeysSignInRequestOptions(beginSignInRequest.getPasskeysRequestOptions());
        builder.setPasskeyJsonSignInRequestOptions(beginSignInRequest.getPasskeyJsonRequestOptions());
        builder.setAutoSelectEnabled(beginSignInRequest.zbd);
        builder.zbb(beginSignInRequest.zbe);
        builder.setPreferImmediatelyAvailableCredentials(beginSignInRequest.zbh);
        String str = beginSignInRequest.zbc;
        if (str != null) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BeginSignInRequest)) {
            return false;
        }
        BeginSignInRequest beginSignInRequest = (BeginSignInRequest) obj;
        return t.l(this.zba, beginSignInRequest.zba) && t.l(this.zbb, beginSignInRequest.zbb) && t.l(this.zbf, beginSignInRequest.zbf) && t.l(this.zbg, beginSignInRequest.zbg) && t.l(this.zbc, beginSignInRequest.zbc) && this.zbd == beginSignInRequest.zbd && this.zbe == beginSignInRequest.zbe && this.zbh == beginSignInRequest.zbh;
    }

    public GoogleIdTokenRequestOptions getGoogleIdTokenRequestOptions() {
        return this.zbb;
    }

    public PasskeyJsonRequestOptions getPasskeyJsonRequestOptions() {
        return this.zbg;
    }

    public PasskeysRequestOptions getPasskeysRequestOptions() {
        return this.zbf;
    }

    public PasswordRequestOptions getPasswordRequestOptions() {
        return this.zba;
    }

    public boolean getPreferImmediatelyAvailableCredentials() {
        return this.zbh;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba, this.zbb, this.zbf, this.zbg, this.zbc, Boolean.valueOf(this.zbd), Integer.valueOf(this.zbe), Boolean.valueOf(this.zbh)});
    }

    public boolean isAutoSelectEnabled() {
        return this.zbd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 1, getPasswordRequestOptions(), i10, false);
        a.a.f0(parcel, 2, getGoogleIdTokenRequestOptions(), i10, false);
        a.a.g0(parcel, 3, this.zbc, false);
        boolean zIsAutoSelectEnabled = isAutoSelectEnabled();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(zIsAutoSelectEnabled ? 1 : 0);
        int i11 = this.zbe;
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(i11);
        a.a.f0(parcel, 6, getPasskeysRequestOptions(), i10, false);
        a.a.f0(parcel, 7, getPasskeyJsonRequestOptions(), i10, false);
        boolean preferImmediatelyAvailableCredentials = getPreferImmediatelyAvailableCredentials();
        a.a.l0(parcel, 8, 4);
        parcel.writeInt(preferImmediatelyAvailableCredentials ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
