package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class GetPhoneNumberHintIntentRequest extends a {
    public static final Parcelable.Creator<GetPhoneNumberHintIntentRequest> CREATOR = new zbk();
    private final int zba;

    public static final class Builder {
        private Builder() {
            throw null;
        }

        public GetPhoneNumberHintIntentRequest build() {
            return new GetPhoneNumberHintIntentRequest(0);
        }

        public /* synthetic */ Builder(zbj zbjVar) {
        }
    }

    public GetPhoneNumberHintIntentRequest(int i10) {
        this.zba = i10;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    public boolean equals(Object obj) {
        if (obj instanceof GetPhoneNumberHintIntentRequest) {
            return t.l(Integer.valueOf(this.zba), Integer.valueOf(((GetPhoneNumberHintIntentRequest) obj).zba));
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zba)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int i11 = this.zba;
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        a.a.n0(iM0, parcel);
    }
}
