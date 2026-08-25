package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class Status extends t3.a implements x, ReflectedParcelable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12675i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f12676l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final PendingIntent f12677m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final o3.b f12678n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Status f12670o = new Status(0, null, null, null);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Status f12671p = new Status(14, null, null, null);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Status f12672q = new Status(8, null, null, null);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Status f12673r = new Status(15, null, null, null);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Status f12674s = new Status(16, null, null, null);
    public static final Parcelable.Creator<Status> CREATOR = new e0(3);

    public Status(int i10, String str, PendingIntent pendingIntent, o3.b bVar) {
        this.f12675i = i10;
        this.f12676l = str;
        this.f12677m = pendingIntent;
        this.f12678n = bVar;
    }

    public final boolean d() {
        return this.f12675i <= 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.f12675i == status.f12675i && com.google.android.gms.common.internal.t.l(this.f12676l, status.f12676l) && com.google.android.gms.common.internal.t.l(this.f12677m, status.f12677m) && com.google.android.gms.common.internal.t.l(this.f12678n, status.f12678n);
    }

    @Override // com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f12675i), this.f12676l, this.f12677m, this.f12678n});
    }

    public final String toString() {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this);
        String statusCodeString = this.f12676l;
        if (statusCodeString == null) {
            statusCodeString = k.getStatusCodeString(this.f12675i);
        }
        cVar.w(statusCodeString, "statusCode");
        cVar.w(this.f12677m, "resolution");
        return cVar.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12675i);
        a.a.g0(parcel, 2, this.f12676l, false);
        a.a.f0(parcel, 3, this.f12677m, i10, false);
        a.a.f0(parcel, 4, this.f12678n, i10, false);
        a.a.n0(iM0, parcel);
    }
}
