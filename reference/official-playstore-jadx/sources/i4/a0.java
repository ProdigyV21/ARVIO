package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.ResidentKeyRequirement$UnsupportedResidentKeyRequirementException;

/* JADX INFO: loaded from: classes4.dex */
public enum a0 implements Parcelable {
    /* JADX INFO: Fake field, exist only in values array */
    RESIDENT_KEY_DISCOURAGED("discouraged"),
    /* JADX INFO: Fake field, exist only in values array */
    RESIDENT_KEY_PREFERRED("preferred"),
    RESIDENT_KEY_REQUIRED("required");

    public static final Parcelable.Creator<a0> CREATOR = new k0(10);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16078i;

    a0(String str) {
        this.f16078i = str;
    }

    public static a0 a(String str) throws ResidentKeyRequirement$UnsupportedResidentKeyRequirementException {
        for (a0 a0Var : values()) {
            if (str.equals(a0Var.f16078i)) {
                return a0Var;
            }
        }
        throw new ResidentKeyRequirement$UnsupportedResidentKeyRequirementException(a0.c.l("Resident key requirement ", str, " not supported"));
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f16078i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f16078i);
    }
}
