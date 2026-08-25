package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.zzbc;

/* JADX INFO: loaded from: classes4.dex */
public enum e0 implements Parcelable {
    /* JADX INFO: Fake field, exist only in values array */
    USER_VERIFICATION_REQUIRED("required"),
    /* JADX INFO: Fake field, exist only in values array */
    USER_VERIFICATION_PREFERRED("preferred"),
    /* JADX INFO: Fake field, exist only in values array */
    USER_VERIFICATION_DISCOURAGED("discouraged");

    public static final Parcelable.Creator<e0> CREATOR = new k0(16);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16107i;

    e0(String str) {
        this.f16107i = str;
    }

    public static e0 a(String str) throws zzbc {
        for (e0 e0Var : values()) {
            if (str.equals(e0Var.f16107i)) {
                return e0Var;
            }
        }
        throw new zzbc(a0.c.l("User verification requirement ", str, " not supported"));
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f16107i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f16107i);
    }
}
