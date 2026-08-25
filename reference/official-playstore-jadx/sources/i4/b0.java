package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.TokenBinding$UnsupportedTokenBindingStatusException;

/* JADX INFO: loaded from: classes4.dex */
public enum b0 implements Parcelable {
    /* JADX INFO: Fake field, exist only in values array */
    PRESENT("present"),
    /* JADX INFO: Fake field, exist only in values array */
    SUPPORTED("supported"),
    /* JADX INFO: Fake field, exist only in values array */
    NOT_SUPPORTED("not-supported");

    public static final Parcelable.Creator<b0> CREATOR = new k0(12);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16082i;

    b0(String str) {
        this.f16082i = str;
    }

    public static b0 a(String str) throws TokenBinding$UnsupportedTokenBindingStatusException {
        for (b0 b0Var : values()) {
            if (str.equals(b0Var.f16082i)) {
                return b0Var;
            }
        }
        throw new TokenBinding$UnsupportedTokenBindingStatusException(a0.c.l("TokenBindingStatus ", str, " not supported"));
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f16082i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f16082i);
    }
}
