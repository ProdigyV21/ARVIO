package o3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import i4.s0;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends t3.a {
    public static final Parcelable.Creator<d> CREATOR = new s0(6);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f20736i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f20737l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f20738m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f20739n;

    public d(String str, int i10, long j10, boolean z) {
        this.f20736i = str;
        this.f20737l = i10;
        this.f20738m = j10;
        this.f20739n = z;
    }

    public final long d() {
        long j10 = this.f20738m;
        return j10 == -1 ? this.f20737l : j10;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (t.l(this.f20736i, dVar.f20736i) && d() == dVar.d() && this.f20739n == dVar.f20739n) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f20736i, Long.valueOf(d()), Boolean.valueOf(this.f20739n)});
    }

    public final String toString() {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this);
        cVar.w(this.f20736i, ContentDisposition.Parameters.Name);
        cVar.w(Long.valueOf(d()), "version");
        cVar.w(Boolean.valueOf(this.f20739n), "is_fully_rolled_out");
        return cVar.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, this.f20736i, false);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f20737l);
        long jD = d();
        a.a.l0(parcel, 3, 8);
        parcel.writeLong(jD);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f20739n ? 1 : 0);
        a.a.n0(iM0, parcel);
    }

    public d(String str, long j10) {
        this(str, -1, j10, false);
    }
}
