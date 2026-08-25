package r3;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.foundation.c;
import com.google.android.gms.common.internal.t;
import i4.s0;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends t3.a {
    public static final Parcelable.Creator<b> CREATOR = new s0(7);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f21387i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Uri f21388l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f21389m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f21390n;

    public b(int i10, Uri uri, int i11, int i12) {
        this.f21387i = i10;
        this.f21388l = uri;
        this.f21389m = i11;
        this.f21390n = i12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof b)) {
            b bVar = (b) obj;
            if (t.l(this.f21388l, bVar.f21388l) && this.f21389m == bVar.f21389m && this.f21390n == bVar.f21390n) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f21388l, Integer.valueOf(this.f21389m), Integer.valueOf(this.f21390n)});
    }

    public final String toString() {
        Locale locale = Locale.US;
        String string = this.f21388l.toString();
        StringBuilder sbV = c.v("Image ", this.f21389m, "x", this.f21390n, " ");
        sbV.append(string);
        return sbV.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f21387i);
        a.a.f0(parcel, 2, this.f21388l, i10, false);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f21389m);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f21390n);
        a.a.n0(iM0, parcel);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public b(JSONObject jSONObject) {
        Uri uri = Uri.EMPTY;
        if (jSONObject.has("url")) {
            try {
                uri = Uri.parse(jSONObject.getString("url"));
            } catch (JSONException unused) {
            }
        }
        int iOptInt = jSONObject.optInt("width", 0);
        int iOptInt2 = jSONObject.optInt("height", 0);
        this(1, uri, iOptInt, iOptInt2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (iOptInt < 0 || iOptInt2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }
}
