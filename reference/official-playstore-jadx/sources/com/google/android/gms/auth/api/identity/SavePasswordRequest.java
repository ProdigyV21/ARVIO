package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class SavePasswordRequest extends a {
    public static final Parcelable.Creator<SavePasswordRequest> CREATOR = new zbs();
    private final SignInPassword zba;
    private final String zbb;
    private final int zbc;

    @Deprecated
    public static final class Builder {
        private SignInPassword zba;
        private String zbb;
        private int zbc;

        public SavePasswordRequest build() {
            return new SavePasswordRequest(this.zba, this.zbb, this.zbc);
        }

        public Builder setSignInPassword(SignInPassword signInPassword) {
            this.zba = signInPassword;
            return this;
        }

        public final Builder zba(String str) {
            this.zbb = str;
            return this;
        }

        public final Builder zbb(int i10) {
            this.zbc = i10;
            return this;
        }
    }

    public SavePasswordRequest(SignInPassword signInPassword, String str, int i10) {
        t.i(signInPassword);
        this.zba = signInPassword;
        this.zbb = str;
        this.zbc = i10;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zba(SavePasswordRequest savePasswordRequest) {
        t.i(savePasswordRequest);
        Builder builder = builder();
        builder.setSignInPassword(savePasswordRequest.getSignInPassword());
        builder.zbb(savePasswordRequest.zbc);
        String str = savePasswordRequest.zbb;
        if (str != null) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SavePasswordRequest)) {
            return false;
        }
        SavePasswordRequest savePasswordRequest = (SavePasswordRequest) obj;
        return t.l(this.zba, savePasswordRequest.zba) && t.l(this.zbb, savePasswordRequest.zbb) && this.zbc == savePasswordRequest.zbc;
    }

    public SignInPassword getSignInPassword() {
        return this.zba;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zba, this.zbb});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 1, getSignInPassword(), i10, false);
        a.a.g0(parcel, 2, this.zbb, false);
        int i11 = this.zbc;
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(i11);
        a.a.n0(iM0, parcel);
    }
}
