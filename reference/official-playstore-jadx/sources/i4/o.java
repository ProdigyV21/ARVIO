package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.ErrorCode$UnsupportedErrorCodeException;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public enum o implements Parcelable {
    NOT_SUPPORTED_ERR(9),
    INVALID_STATE_ERR(11),
    SECURITY_ERR(18),
    NETWORK_ERR(19),
    ABORT_ERR(20),
    TIMEOUT_ERR(23),
    ENCODING_ERR(27),
    UNKNOWN_ERR(28),
    CONSTRAINT_ERR(29),
    DATA_ERR(30),
    NOT_ALLOWED_ERR(35),
    ATTESTATION_NOT_PRIVATE_ERR(36);

    public static final Parcelable.Creator<o> CREATOR = new s0(2);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f16155i;

    o(int i10) {
        this.f16155i = i10;
    }

    public static o a(int i10) throws ErrorCode$UnsupportedErrorCodeException {
        for (o oVar : values()) {
            if (i10 == oVar.f16155i) {
                return oVar;
            }
        }
        Locale locale = Locale.US;
        throw new ErrorCode$UnsupportedErrorCodeException(androidx.compose.foundation.c.o(i10, "Error code ", " is not supported"));
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f16155i);
    }
}
