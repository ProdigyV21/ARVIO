package o3;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import i4.s0;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends t3.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f20728i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f20729l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final PendingIntent f20730m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f20731n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Integer f20732o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final b f20727p = new b(0, null, null);
    public static final Parcelable.Creator<b> CREATOR = new s0(5);

    public b(int i10, int i11, PendingIntent pendingIntent, String str, Integer num) {
        this.f20728i = i10;
        this.f20729l = i11;
        this.f20730m = pendingIntent;
        this.f20731n = str;
        this.f20732o = num;
    }

    public static String e(int i10) {
        if (i10 == 99) {
            return "UNFINISHED";
        }
        if (i10 == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i10) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i10) {
                    case 13:
                        return "CANCELED";
                    case 14:
                        return "TIMEOUT";
                    case 15:
                        return "INTERRUPTED";
                    case 16:
                        return "API_UNAVAILABLE";
                    case 17:
                        return "SIGN_IN_FAILED";
                    case 18:
                        return "SERVICE_UPDATING";
                    case 19:
                        return "SERVICE_MISSING_PERMISSION";
                    case 20:
                        return "RESTRICTED_PROFILE";
                    case 21:
                        return "API_VERSION_UPDATE_REQUIRED";
                    case 22:
                        return "RESOLUTION_ACTIVITY_NOT_FOUND";
                    case 23:
                        return "API_DISABLED";
                    case 24:
                        return "API_DISABLED_FOR_CONNECTION";
                    case 25:
                        return "API_INSTALL_REQUIRED";
                    default:
                        StringBuilder sb2 = new StringBuilder(String.valueOf(i10).length() + 20);
                        sb2.append("UNKNOWN_ERROR_CODE(");
                        sb2.append(i10);
                        sb2.append(")");
                        return sb2.toString();
                }
        }
    }

    public final boolean d() {
        return this.f20729l == 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f20729l == bVar.f20729l && t.l(this.f20730m, bVar.f20730m) && t.l(this.f20731n, bVar.f20731n) && t.l(this.f20732o, bVar.f20732o);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f20729l), this.f20730m, this.f20731n, this.f20732o});
    }

    public final String toString() {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this);
        cVar.w(e(this.f20729l), "statusCode");
        cVar.w(this.f20730m, "resolution");
        cVar.w(this.f20731n, "message");
        cVar.w(this.f20732o, "clientMethodKey");
        return cVar.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f20728i);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f20729l);
        a.a.f0(parcel, 3, this.f20730m, i10, false);
        a.a.g0(parcel, 4, this.f20731n, false);
        a.a.d0(parcel, 5, this.f20732o);
        a.a.n0(iM0, parcel);
    }

    public b(int i10, PendingIntent pendingIntent, String str) {
        this(1, i10, pendingIntent, str, null);
    }
}
