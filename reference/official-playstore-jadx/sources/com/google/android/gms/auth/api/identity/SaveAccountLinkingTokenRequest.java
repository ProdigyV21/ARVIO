package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.t;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class SaveAccountLinkingTokenRequest extends a implements ReflectedParcelable {
    public static final Parcelable.Creator<SaveAccountLinkingTokenRequest> CREATOR = new zbq();
    public static final String EXTRA_TOKEN = "extra_token";
    public static final String TOKEN_TYPE_AUTH_CODE = "auth_code";
    private final PendingIntent zba;
    private final String zbb;
    private final String zbc;
    private final List zbd;
    private final String zbe;
    private final int zbf;

    public static final class Builder {
        private PendingIntent zba;
        private String zbb;
        private String zbc;
        private List zbd = new ArrayList();
        private String zbe;
        private int zbf;

        public SaveAccountLinkingTokenRequest build() {
            t.a("Consent PendingIntent cannot be null", this.zba != null);
            t.a("Invalid tokenType", SaveAccountLinkingTokenRequest.TOKEN_TYPE_AUTH_CODE.equals(this.zbb));
            t.a("serviceId cannot be null or empty", !TextUtils.isEmpty(this.zbc));
            t.a("scopes cannot be null", this.zbd != null);
            return new SaveAccountLinkingTokenRequest(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf);
        }

        public Builder setConsentPendingIntent(PendingIntent pendingIntent) {
            this.zba = pendingIntent;
            return this;
        }

        public Builder setScopes(List<String> list) {
            this.zbd = list;
            return this;
        }

        public Builder setServiceId(String str) {
            this.zbc = str;
            return this;
        }

        public Builder setTokenType(String str) {
            this.zbb = str;
            return this;
        }

        public final Builder zba(String str) {
            this.zbe = str;
            return this;
        }

        public final Builder zbb(int i10) {
            this.zbf = i10;
            return this;
        }
    }

    public SaveAccountLinkingTokenRequest(PendingIntent pendingIntent, String str, String str2, List list, String str3, int i10) {
        this.zba = pendingIntent;
        this.zbb = str;
        this.zbc = str2;
        this.zbd = list;
        this.zbe = str3;
        this.zbf = i10;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zba(SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest) {
        t.i(saveAccountLinkingTokenRequest);
        Builder builder = builder();
        builder.setScopes(saveAccountLinkingTokenRequest.getScopes());
        builder.setServiceId(saveAccountLinkingTokenRequest.getServiceId());
        builder.setConsentPendingIntent(saveAccountLinkingTokenRequest.getConsentPendingIntent());
        builder.setTokenType(saveAccountLinkingTokenRequest.getTokenType());
        builder.zbb(saveAccountLinkingTokenRequest.zbf);
        String str = saveAccountLinkingTokenRequest.zbe;
        if (!TextUtils.isEmpty(str)) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SaveAccountLinkingTokenRequest)) {
            return false;
        }
        SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest = (SaveAccountLinkingTokenRequest) obj;
        return this.zbd.size() == saveAccountLinkingTokenRequest.zbd.size() && this.zbd.containsAll(saveAccountLinkingTokenRequest.zbd) && t.l(this.zba, saveAccountLinkingTokenRequest.zba) && t.l(this.zbb, saveAccountLinkingTokenRequest.zbb) && t.l(this.zbc, saveAccountLinkingTokenRequest.zbc) && t.l(this.zbe, saveAccountLinkingTokenRequest.zbe) && this.zbf == saveAccountLinkingTokenRequest.zbf;
    }

    public PendingIntent getConsentPendingIntent() {
        return this.zba;
    }

    public List<String> getScopes() {
        return this.zbd;
    }

    public String getServiceId() {
        return this.zbc;
    }

    public String getTokenType() {
        return this.zbb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba, this.zbb, this.zbc, this.zbd, this.zbe});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 1, getConsentPendingIntent(), i10, false);
        a.a.g0(parcel, 2, getTokenType(), false);
        a.a.g0(parcel, 3, getServiceId(), false);
        a.a.i0(parcel, getScopes(), 4);
        a.a.g0(parcel, 5, this.zbe, false);
        int i11 = this.zbf;
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(i11);
        a.a.n0(iM0, parcel);
    }
}
