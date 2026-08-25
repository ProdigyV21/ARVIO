package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.Attachment$UnsupportedAttachmentException;

/* JADX INFO: loaded from: classes4.dex */
public enum b implements Parcelable {
    /* JADX INFO: Fake field, exist only in values array */
    PLATFORM("platform"),
    /* JADX INFO: Fake field, exist only in values array */
    CROSS_PLATFORM("cross-platform");

    public static final Parcelable.Creator<b> CREATOR = new android.support.v4.media.d(25);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16080i;

    b(String str) {
        this.f16080i = str;
    }

    public static b a(String str) throws Attachment$UnsupportedAttachmentException {
        for (b bVar : values()) {
            if (str.equals(bVar.f16080i)) {
                return bVar;
            }
        }
        throw new Attachment$UnsupportedAttachmentException(a0.c.l("Attachment ", str, " not supported"));
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f16080i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f16080i);
    }
}
