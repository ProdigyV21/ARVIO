package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException;

/* JADX INFO: loaded from: classes4.dex */
public enum c implements Parcelable {
    NONE("none"),
    /* JADX INFO: Fake field, exist only in values array */
    INDIRECT("indirect"),
    /* JADX INFO: Fake field, exist only in values array */
    DIRECT("direct");

    public static final Parcelable.Creator<c> CREATOR = new k0(14);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16085i;

    c(String str) {
        this.f16085i = str;
    }

    public static c a(String str) throws AttestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException {
        for (c cVar : values()) {
            if (str.equals(cVar.f16085i)) {
                return cVar;
            }
        }
        throw new AttestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException(a0.c.l("Attestation conveyance preference ", str, " not supported"));
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f16085i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f16085i);
    }
}
