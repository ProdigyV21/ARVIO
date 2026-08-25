package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class GetSignInIntentRequest extends a {
    public static final Parcelable.Creator<GetSignInIntentRequest> CREATOR = new zbl();
    private final String zba;
    private final String zbb;
    private final String zbc;
    private final String zbd;
    private final boolean zbe;
    private final int zbf;

    public static final class Builder {
        private String zba;
        private String zbb;
        private String zbc;
        private String zbd;
        private boolean zbe;
        private int zbf;

        public GetSignInIntentRequest build() {
            return new GetSignInIntentRequest(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf);
        }

        public Builder filterByHostedDomain(String str) {
            this.zbb = str;
            return this;
        }

        public Builder setNonce(String str) {
            this.zbd = str;
            return this;
        }

        @Deprecated
        public Builder setRequestVerifiedPhoneNumber(boolean z) {
            this.zbe = z;
            return this;
        }

        public Builder setServerClientId(String str) {
            t.i(str);
            this.zba = str;
            return this;
        }

        public final Builder zba(String str) {
            this.zbc = str;
            return this;
        }

        public final Builder zbb(int i10) {
            this.zbf = i10;
            return this;
        }
    }

    public GetSignInIntentRequest(String str, String str2, String str3, String str4, boolean z, int i10) {
        t.i(str);
        this.zba = str;
        this.zbb = str2;
        this.zbc = str3;
        this.zbd = str4;
        this.zbe = z;
        this.zbf = i10;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zba(GetSignInIntentRequest getSignInIntentRequest) {
        t.i(getSignInIntentRequest);
        Builder builder = builder();
        builder.setServerClientId(getSignInIntentRequest.getServerClientId());
        builder.setNonce(getSignInIntentRequest.getNonce());
        builder.filterByHostedDomain(getSignInIntentRequest.getHostedDomainFilter());
        builder.setRequestVerifiedPhoneNumber(getSignInIntentRequest.zbe);
        builder.zbb(getSignInIntentRequest.zbf);
        String str = getSignInIntentRequest.zbc;
        if (str != null) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetSignInIntentRequest)) {
            return false;
        }
        GetSignInIntentRequest getSignInIntentRequest = (GetSignInIntentRequest) obj;
        return t.l(this.zba, getSignInIntentRequest.zba) && t.l(this.zbd, getSignInIntentRequest.zbd) && t.l(this.zbb, getSignInIntentRequest.zbb) && t.l(Boolean.valueOf(this.zbe), Boolean.valueOf(getSignInIntentRequest.zbe)) && this.zbf == getSignInIntentRequest.zbf;
    }

    public String getHostedDomainFilter() {
        return this.zbb;
    }

    public String getNonce() {
        return this.zbd;
    }

    public String getServerClientId() {
        return this.zba;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba, this.zbb, this.zbd, Boolean.valueOf(this.zbe), Integer.valueOf(this.zbf)});
    }

    @Deprecated
    public boolean requestVerifiedPhoneNumber() {
        return this.zbe;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, getServerClientId(), false);
        a.a.g0(parcel, 2, getHostedDomainFilter(), false);
        a.a.g0(parcel, 3, this.zbc, false);
        a.a.g0(parcel, 4, getNonce(), false);
        boolean zRequestVerifiedPhoneNumber = requestVerifiedPhoneNumber();
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(zRequestVerifiedPhoneNumber ? 1 : 0);
        int i11 = this.zbf;
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(i11);
        a.a.n0(iM0, parcel);
    }
}
