package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class l implements Parcelable {
    public static final Parcelable.Creator<l> CREATOR = new k0(28);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Enum f16133i;

    /* JADX WARN: Multi-variable type inference failed */
    public l(a aVar) {
        this.f16133i = (Enum) aVar;
    }

    public static l a(int i10) throws COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException {
        a aVar;
        if (i10 == -262) {
            aVar = z.RS1;
        } else {
            z[] zVarArrValues = z.values();
            int length = zVarArrValues.length;
            int i11 = 0;
            while (true) {
                if (i11 >= length) {
                    for (n nVar : n.values()) {
                        if (nVar.f16141i == i10) {
                            aVar = nVar;
                        }
                    }
                    throw new COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException(androidx.compose.foundation.c.o(i10, "Algorithm with COSE value ", " not supported"));
                }
                z zVar = zVarArrValues[i11];
                if (zVar.f16214i == i10) {
                    aVar = zVar;
                    break;
                }
                i11++;
            }
        }
        return new l(aVar);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [i4.a, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r2v3, types: [i4.a, java.lang.Enum] */
    public final boolean equals(Object obj) {
        return (obj instanceof l) && this.f16133i.a() == ((l) obj).f16133i.a();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16133i});
    }

    public final String toString() {
        return a0.c.l("COSEAlgorithmIdentifier{algorithm=", String.valueOf(this.f16133i), "}");
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [i4.a, java.lang.Enum] */
    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f16133i.a());
    }
}
