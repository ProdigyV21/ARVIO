package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class x implements Parcelable {

    /* JADX INFO: Fake field, exist only in values array */
    x EF5;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ x[] f16207i = {new x("PUBLIC_KEY", 0)};
    public static final Parcelable.Creator<x> CREATOR = new k0(8);

    public static x a(String str) throws PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException {
        for (x xVar : values()) {
            xVar.getClass();
            if (str.equals("public-key")) {
                return xVar;
            }
        }
        throw new PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException(a0.c.l("PublicKeyCredentialType ", str, " not supported"));
    }

    public static x valueOf(String str) {
        return (x) Enum.valueOf(x.class, str);
    }

    public static x[] values() {
        return (x[]) f16207i.clone();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "public-key";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString("public-key");
    }
}
